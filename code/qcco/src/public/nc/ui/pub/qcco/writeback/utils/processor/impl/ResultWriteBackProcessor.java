package nc.ui.pub.qcco.writeback.utils.processor.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.mozilla.javascript.edu.emory.mathcs.backport.java.util.Collections;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.hr.utils.InSQLCreator;
import nc.jdbc.framework.processor.MapListProcessor;
import nc.ui.pub.qcco.writeback.utils.WriteBackProcessData;
import nc.ui.pub.qcco.writeback.utils.LIMSVO.CProjTask;
import nc.ui.pub.qcco.writeback.utils.LIMSVO.Result;
import nc.ui.pub.qcco.writeback.utils.LIMSVO.Sample;
import nc.ui.pub.qcco.writeback.utils.LIMSVO.Test;
import nc.ui.pub.qcco.writeback.utils.common.CommonUtils;
import nc.ui.pub.qcco.writeback.utils.mapping.FirstWriteBackStaticMaping;
import nc.ui.pub.qcco.writeback.utils.mapping.SecWriteBackStaticMaping;
import nc.ui.pub.qcco.writeback.utils.processor.IFirstWriteBackProcessor;
import nc.ui.pub.qcco.writeback.utils.processor.ISecWriteBackProcessor;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.qcco.task.AggTaskHVO;
import nc.vo.qcco.task.TaskBVO;
import nc.vo.qcco.task.TaskSVO;

/**
 * result��һ�κ͵ڶ��λ�д
 * @author 91967
 *
 */
public class ResultWriteBackProcessor implements IFirstWriteBackProcessor, ISecWriteBackProcessor {

	private CommonUtils utils;
	private BaseDAO baseDao = new BaseDAO();
	@Override
	public void setUtils(CommonUtils utils) {
		this.utils = utils;
	}
	
	@Override
	public void processSec(WriteBackProcessData data) throws BusinessException{
		processSecond(data);
	}

	@Override
	public void processFirst(WriteBackProcessData data) throws BusinessException{
		process(data);
	}

	
	/**
	 * test ���λ�д
	 * 
	 * @param data
	 * @throws DAOException
	 */
	private void processSecond(WriteBackProcessData processData) throws DAOException {
		// LIMS Data
		//List<Sample> allSampleList = processData.getAllSecSampleList();
		List<Test> secTestList = processData.getSecTestList();
		

		List<Result> secResultList = new ArrayList<>();

		if (secTestList != null && secTestList.size() > 0) {
			for (int i = 0; i < secTestList.size(); i++) {
				//ͨ��test ��ȡtask,�ٻ�ȡan ,�ڻ�ȡʵ��������
				List<Map<String,Object>> compoentList = 
						utils.getResultCompoentList(String.valueOf(secTestList.get(i).getAttributeValue("c_task_seq_num")));
				if(compoentList!=null && compoentList.size() > 0){
					for(Map<String,Object> component : compoentList){
						// ��ʼ����result
						Result result = new Result();
						
						result.setAttributeValue("result_number", processData.getMaxResult()+1);
						processData.setMaxResult(processData.getMaxResult()+1);
						
						Integer sampleNumber = Integer.parseInt(String.valueOf(secTestList.get(i).getAttributeValue("sample_number")));
						
						//RESULT.SAMPLE_NUMBER	sample��ڶ���д������������Խ���ж����У��˴�һֻ��Ʒ���ж�����
						result.setAttributeValue("sample_number", sampleNumber);
						
						//RESULT.NAME ���Խ����Ӧ����ʾֵ
						result.setAttributeValue("name", component.get("name"));
						
						//RESULT.ENTRY	null				
						result.setAttributeValue("entry", null);
						
						//RESULT.TEST_NUMBER	test�����д���Ӧsample_number��test_number				
						result.setAttributeValue("test_number", secTestList.get(i).getAttributeValue("test_number"));
						
						//RESULT.ORDER_NUMBER ���Խ��������	  			
						result.setAttributeValue("order_number", component.get("order_number"));
						
						//RESULT.ENTERED_ON	default:null			����ʱ����ʲôʱ��?��ô��ȡ??	��һ��д��Ϊд��ʱ��ϵͳʱ��
						result.setAttributeValue("entered_on", null);
						
						//RESULT.ANALYSIS	 ���Խ�����ݵķ������ƣ���A�ģ�
						result.setAttributeValue("analysis", component.get("analysis"));
						
						//RESULT.FORMAT_CALCULATION	XXX result 2 ʱ��ת�����˴�limsԭ��Ϊ�ֶ���Ӧ�ļ��㣬���������ֻ��һ�γ��򣬼���ʱ��ת��ΪСʱ��ʾ���˴�Ϊʵ�ʲ���ʱ��ת����
						//ת���ĸ�ʱ��?ʵ�ʲ���ʱ����ʲôʱ��?��ô��ȡ?	ת��ͨ����ʽ�������ʱ��
						result.setAttributeValue("format_calculation", "");
						
						//RESULT.ATTRIBUTE_1	default:null				
						result.setAttributeValue("attribute_1", null);
						
						result.setAttributeValue("places", 0);

						secResultList.add(result);
					
						
					}
					}
				}
			processData.setSecResultList(secResultList);
		}
	}

	

	/**
     * ��ȡInsert���Ƭ��
     *
     * @param processData processData
     * @throws BusinessException
     */
    private void process(WriteBackProcessData processData)
            throws BusinessException {
		// NC����
		List<ISuperVO> srcDataList = new ArrayList<>();
		// ��ȡ���е�ʵ����������VO
		ISuperVO[] superVOs = getAllTaskSVO(processData);
		Collections.addAll(srcDataList, superVOs);
		if (null == srcDataList || srcDataList.size() <= 0) {
			return ;
		}

		//�������
		List<Sample> firstSampleList = processData.getFirstSampleList();
		List<Test> firstTestList = processData.getFirstTestList();
		List<CProjTask> taskList = processData.getTaskList();
		//������
		Map<String,Map<String,Object>> analysisMapMap = getAnalysis(taskList);
		//�������ͱ� name->code
		Map<String,String> analysisTypeCodeMap = getanalysisTypeCode();
		
		//Ҫ��д��LIMS ����--���������������
		List<Result> allResultList = initWriteBackList(srcDataList.size());
		
		// Ԥ����PK
		List<Integer> pkList = utils.getPrePk(TaskSVO.class, srcDataList.size());
		processData.setMaxResult(pkList.get(srcDataList.size()-1));

		// ������ѭ��
		for (Entry<String, String> map : FirstWriteBackStaticMaping.GRAND_CONDITION_MAPPING.entrySet()) {
			String fieldName = map.getKey();
			String[] fields = null;
			if (map.getValue().contains(";")) {
				fields = utils.getWriteBackFields(map.getValue().split(";"));
			} else {
				fields = utils.getWriteBackFields(new String[] { map.getValue() });
			}
			if (srcDataList != null && srcDataList.size() > 0) {
				for (int i = 0; i < srcDataList.size(); i++) {
					Object unDofieldValue = srcDataList.get(i).getAttributeValue(fieldName);
					Object realValue = utils.getRealValue(unDofieldValue, fieldName, TaskSVO.class);
					for (String field : fields) {
						allResultList.get(i).setAttributeValue(field, realValue);
					}
				}
			}
		}

		for (int i = 0; i < srcDataList.size(); i++) {
			// д�������͸�����(ֻ֧�ֵ�����,Ҫ������,��Ҫ�ο�������ֶ�����߼�)
			// ��ȡ�ϲ������:
			String fatherPk = (String) (((ISuperVO) srcDataList.get(i)).getAttributeValue("pk_task_b"));
			//�ϴ�obj��index
			int fatherIndex = utils.getNCObjIndexByPK(fatherPk, TaskBVO.class);
			// ����
			allResultList.get(i).setAttributeValue("result_number", pkList.get(i));
			//�����
			allResultList.get(i).setAttributeValue("sample_number", firstSampleList.get(fatherIndex).getAttributeValue("sample_number"));
			allResultList.get(i).setAttributeValue("test_number", firstTestList.get(fatherIndex).getAttributeValue("test_number"));
			
			
			
			//flag���,����sort��֮�����
			allResultList.get(i).setAttributeValue("task_seq_num", taskList.get(fatherIndex).getAttributeValue("seq_num"));
			//��Ӧ��analysis
			Map<String,Object> analysisMap = analysisMapMap.get(taskList.get(fatherIndex).getAttributeValue("analysis"));
			allResultList.get(i).setAttributeValue("analysis", analysisMap.get("name"));
			String result_type = analysisTypeCodeMap.get(analysisMap.get("analysis_type"));
			allResultList.get(i).setAttributeValue("result_type", result_type==null?null:result_type.substring(0, 1));
			
			
			allResultList.get(i).setAttributeValue("places", 0);
		}

		processData.setFirstResultListMap(sortResult(allResultList));
	}

	private Map<String, String> getanalysisTypeCode() throws DAOException {
		Map<String, String> rsMap = new HashMap<>();
		@SuppressWarnings("unchecked")
		List<Map<String,Object>> rs = 
				(List<Map<String,Object>>)baseDao.executeQuery("select * from nc_analysis_type", new MapListProcessor());
		if(rs!=null && rs.size() > 0){
			for(Map<String,Object> data : rs){
				rsMap.put(String.valueOf(data.get("nc_type_name")).replaceAll(" ", ""), String.valueOf(data.get("nc_type_code")).replaceAll(" ", ""));
			}
		}
		return rsMap;
	}
	/**
	 * key : analysis��name
	 * @param taskList
	 * @return
	 * @throws BusinessException
	 */
	private Map<String, Map<String, Object>> getAnalysis(List<CProjTask> taskList) throws BusinessException {
		Map<String, Map<String, Object>> rsMap = new HashMap<>();
		Set<String> analysisNameSet = new HashSet<>();
		if(taskList!=null && taskList.size() > 0){
			for(CProjTask task : taskList){
				analysisNameSet.add(String.valueOf(task.getAttributeValue("analysis")).replaceAll(" ", ""));
			}
			
		}
		InSQLCreator insql = new InSQLCreator();
		String insqlstr = insql.getInSQL(analysisNameSet.toArray(new String[0]));
		@SuppressWarnings("unchecked")
		List<Map<String,Object>> rs = 
		(List<Map<String,Object>>)baseDao.executeQuery("select * from analysis where name in ("+insqlstr+")", new MapListProcessor());
		if(rs!=null && rs.size() > 0){
			for(Map<String,Object> data : rs){
				rsMap.put(String.valueOf(data.get("name")).replaceAll(" ", ""), data);
			}
		}
		return rsMap;
	}

	private Map<Integer, List<Result>> sortResult(List<Result> allResultList) {
		Map<Integer, List<Result>> rsListMap = new HashMap<>();
		for (Result result : allResultList) {
			Integer taskid = (Integer) result.getAttributeValue("task_seq_num");
			//���ֶβ���дֻ��flag
			result.removeAttributeValue("task_seq_num");
			if (rsListMap.containsKey(taskid)) {
				rsListMap.get(taskid).add(result);
			} else {
				List<Result> resultList = new ArrayList<>();
				resultList.add(result);
				rsListMap.put(taskid, resultList);
			}
		}
		return rsListMap;
	}

	private List<Result> initWriteBackList(int size) {
		List<Result> rsList = new ArrayList<>();
		for(int i = 0;i < size ;i++){
			rsList.add(new Result());
		}
		return rsList;
	}

	private ISuperVO[] getAllTaskSVO(WriteBackProcessData processData) {
		List<ISuperVO> rsList = new ArrayList<>();
		AggTaskHVO aggvo = processData.getAggTaskHVO();
		ISuperVO[] bvos = aggvo.getChildren(TaskBVO.class);
		if (bvos != null && bvos.length > 0) {
			for (ISuperVO bvo : bvos) {
				TaskBVO cbvo = (TaskBVO) bvo;
				if (cbvo.getPk_task_s() != null && cbvo.getPk_task_s().length > 0) {
					Collections.addAll(rsList, cbvo.getPk_task_s());
				}
			}
		}
		return rsList.toArray(new ISuperVO[0]);
	}



}
