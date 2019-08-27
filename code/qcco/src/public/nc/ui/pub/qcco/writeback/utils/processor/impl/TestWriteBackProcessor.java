package nc.ui.pub.qcco.writeback.utils.processor.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.dao.BaseDAO;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.ui.pub.qcco.writeback.utils.WriteBackProcessData;
import nc.ui.pub.qcco.writeback.utils.LIMSVO.CProjTask;
import nc.ui.pub.qcco.writeback.utils.LIMSVO.Sample;
import nc.ui.pub.qcco.writeback.utils.LIMSVO.Test;
import nc.ui.pub.qcco.writeback.utils.common.CommonUtils;
import nc.ui.pub.qcco.writeback.utils.mapping.FirstWriteBackStaticMaping;
import nc.ui.pub.qcco.writeback.utils.mapping.SecWriteBackStaticMaping;
import nc.ui.pub.qcco.writeback.utils.processor.IFirstWriteBackProcessor;
import nc.ui.pub.qcco.writeback.utils.processor.ISecWriteBackProcessor;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.qcco.task.TaskBVO;
import nc.vo.qcco.task.TaskHVO;

/**
 * test��һ�κ͵ڶ��λ�д
 * @author 91967
 *
 */
public class TestWriteBackProcessor implements IFirstWriteBackProcessor, ISecWriteBackProcessor {
	
	private CommonUtils utils;
	@Override
	public void setUtils(CommonUtils utils) {
		this.utils = utils;
	}
	
	@Override
	public void processSec(WriteBackProcessData data) throws BusinessException{
		// TODO �Զ����ɵķ������

	}

	@Override
	public void processFirst(WriteBackProcessData data) throws BusinessException{
		process(data);
	}


	/**
     * ��һ�λ�дTest��
     * @param object 
     * @param object
     * @param pk_test Ԥ�����test����
     * @param pk_firstSample ��һ�λ�д��sample������
     * @throws BusinessException 
     */
    private void process(WriteBackProcessData processData) throws BusinessException {
    	//NC����
    	ISuperVO[] bvos = processData.getAggTaskHVO().getChildren(TaskBVO.class);
    	TaskHVO taskHvo = processData.getAggTaskHVO().getParentVO();
    	
    	//sample����
    	List<Sample> firstSampleList = processData.getFirstSampleList();
    	//task ����
    	List<CProjTask> taskList = processData.getTaskList();
    	
    	//��Ҫ��д��LIMS���� --test ��һ�λ�д, һ��sample��Ӧһ��test ����Ӧһ��task����
    	List<Test> firstTestList = initData(bvos.length);
    	
    	List<Integer> test_numberList = 
    			utils.getPrePk("test_number","test",processData.getTaskList().size());
    	
    	for(int i = 0 ; i < bvos.length ; i++){
    			TaskBVO taskBvo = (TaskBVO)bvos[i];
    			
    			//���� ���
    			firstTestList.get(i).setAttributeValue("test_number", test_numberList.get(i));
    			firstTestList.get(i).setAttributeValue("original_test", test_numberList.get(i));
    			firstTestList.get(i).setAttributeValue("sample_number", firstSampleList.get(i).getAttributeValue("sample_number"));
    			
    			//task �������
    			taskList.get(i).setAttributeValue("test_number", test_numberList.get(i));
    			
    			//���񵥴���ʱ��
    			UFDateTime creatTime = taskHvo.getCreationtime();
    			String time = "to_timestamp('"+creatTime+"','yyyy-mm-dd hh24:mi:ss.ff')";
    			firstTestList.get(i).setAttributeValue("date_received", time);
    			firstTestList.get(i).setAttributeValue("date_started", time);
    			firstTestList.get(i).setAttributeValue("t_date_enabled", time);
    			
    			//����޸�ʱ��
    			UFDateTime modifyTime = taskHvo.getModifiedtime()==null?creatTime:taskHvo.getModifiedtime();
    			firstTestList.get(i).setAttributeValue("changed_on", "to_timestamp('"+modifyTime+"','yyyy-mm-dd hh24:mi:ss.ff')");
    			
    			//������Խ������
    			firstTestList.get(i).setAttributeValue("analysis", taskBvo.getPk_testresultname());
    			
    			//���Խ��������
    			firstTestList.get(i).setAttributeValue("common_name", taskBvo.getTestresultshortname());
    			
    			//������Ŀ
    			firstTestList.get(i).setAttributeValue("reported_name", taskBvo.getTestitem());
    			
    			//�����汾
    			firstTestList.get(i).setAttributeValue("version", utils.getAnalysisVerionFromName(taskBvo.getPk_testresultname()));
    	}
    	processData.setFirstTestList(firstTestList);
	}

	private List<Test> initData(int length) {
		List<Test> rsList = new ArrayList<>();
		for(int i = 0;i<length;i++){
			rsList.add(new Test());
		}
		return rsList;
	}
	
	
	
	
	
	
	
	
	
	

}
