package nc.ui.pub.qcco.writeback.utils.processor.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.dao.DAOException;
import nc.ui.pub.qcco.writeback.utils.WriteBackProcessData;
import nc.ui.pub.qcco.writeback.utils.LIMSVO.CProjTask;
import nc.ui.pub.qcco.writeback.utils.LIMSVO.Sample;
import nc.ui.pub.qcco.writeback.utils.LIMSVO.Test;
import nc.ui.pub.qcco.writeback.utils.common.CommonUtils;
import nc.ui.pub.qcco.writeback.utils.mapping.FirstWriteBackStaticMaping;
import nc.ui.pub.qcco.writeback.utils.processor.IFirstWriteBackProcessor;
import nc.ui.pub.qcco.writeback.utils.processor.ISecWriteBackProcessor;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.qcco.task.TaskBVO;
import nc.vo.qcco.task.TaskHVO;

/**
 * test��һ�κ͵ڶ��λ�д
 * 
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
	public void processSec(WriteBackProcessData data) throws BusinessException {
		processSecond(data);
	}

	@Override
	public void processFirst(WriteBackProcessData data) throws BusinessException {
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
		List<Sample> allSampleList = processData.getAllSecSampleList();
		//������Ʒ��Ӧ��task
		
		
		// ��Ҫ��д��LIMS
		// test�ڶ��λ�д,taskÿ����һ����Ʒ�Ͳ���һ��test
		List<Test> secTestList = new ArrayList<>();

		if (allSampleList != null && allSampleList.size() > 0) {
			for (int i = 0; i < allSampleList.size(); i++) {
				List<CProjTask> taskList = processData.getTaskFromSampleSec(allSampleList.get(i));
				int labCount = 1;
				if(taskList!=null && taskList.size() > 0){
					if(taskList.size() > 1){
						//��һ����Ʒ��ֹһ��taskʱ,����Ϊtrue
						allSampleList.get(i).setAttributeValue("c_is_sequnce", "T");
					}
					for(CProjTask task : taskList){
						Test test = new Test();
						// TEST.TEST_NUMBER ����sample_number��������
						test.setAttributeValue("test_number", processData.getMaxTestPK()+1);
						processData.setMaxTestPK(processData.getMaxTestPK()+1);
						
						// TEST.SAMPLE_NUMBER
						// ȡsample����β����sample_number��һ�������ж���ֻ��Ʒ�����ж�����
						test.setAttributeValue("sample_number", Integer.parseInt(String.valueOf(allSampleList.get(i).getAttributeValue("sample_number"))));

						// TEST.STATUS I
						test.setAttributeValue("status", "I");

						// TEST.C_TASK_SEQ_NUM c_proj_task������������Ӧ������
						test.setAttributeValue("c_task_seq_num", task.getAttributeValue("seq_num"));

						// TEST.C_TASK_ID �����c_proj_task.task_id
						test.setAttributeValue("c_task_id", task.getAttributeValue("task_id"));

						// TEST.C_ARRANGE_TYPE XXX test 2 �ų����
						test.setAttributeValue("c_arrange_type", "");

						// TEST.test���� 
						test.setAttributeValue("c_test_type", "���Խ��");

						// TEST.ORDER_NUMBER ��������
						test.setAttributeValue("order_number", task.getAttributeValue("order_number"));

						// TEST.LAB  ����С������
						test.setAttributeValue("lab", utils.getLabFromAnalysisName(String.valueOf(task.getAttributeValue("analysis"))));
						if(1 == labCount){
							allSampleList.get(i).setAttributeValue("lab", utils.getLabFromAnalysisName(String.valueOf(task.getAttributeValue("analysis"))));
							labCount++;
						}
						
						// TEST.VARIATION default:null
						test.setAttributeValue("variation", null);

						// TEST.T_ANALYSIS_METHOD ��������(��IEC61810-7)
						test.setAttributeValue("t_analysis_method", utils.getMethodFromAnalysisName(String.valueOf(task.getAttributeValue("analysis"))));

						secTestList.add(test);
					}
				}
			}
			processData.setSecTestList(secTestList);
		}
	}

	/**
	 * ��һ�λ�дTest��
	 * 
	 * @param object
	 * @param object
	 * @param pk_test
	 *            Ԥ�����test����
	 * @param pk_firstSample
	 *            ��һ�λ�д��sample������
	 * @throws BusinessException
	 */
	private void process(WriteBackProcessData processData) throws BusinessException {
		// NC����
		ISuperVO[] bvos = processData.getAggTaskHVO().getChildren(TaskBVO.class);
		TaskHVO taskHvo = processData.getAggTaskHVO().getParentVO();

		// sample����
		List<Sample> firstSampleList = processData.getFirstSampleList();
		// task ����
		List<CProjTask> taskList = processData.getTaskList();

		// ��Ҫ��д��LIMS���� --test ��һ�λ�д, һ��sample��Ӧһ��test ����Ӧһ��task����
		List<Test> firstTestList = initData(bvos.length);

		// Ԥ����PK
		List<Integer> test_numberList = utils.getPrePk("test_number", "test", processData.getTaskList().size());
		processData.setMaxTestPK(test_numberList.get(processData.getTaskList().size() - 1));

		for (int i = 0; i < bvos.length; i++) {
			TaskBVO taskBvo = (TaskBVO) bvos[i];

			// ���� ���
			firstTestList.get(i).setAttributeValue("test_number", test_numberList.get(i));
			firstTestList.get(i).setAttributeValue("original_test", test_numberList.get(i));
			firstTestList.get(i).setAttributeValue("sample_number", firstSampleList.get(i).getAttributeValue("sample_number"));

			// task �������
			taskList.get(i).setAttributeValue("test_number", test_numberList.get(i));

			// ���񵥴���ʱ��
			UFDateTime creatTime = taskHvo.getCreationtime();
			if(null==creatTime){
				creatTime = processData.getAggCommissionHVO().getParentVO().getCreationtime();
			}
			String time = "to_timestamp('" + creatTime + "','yyyy-mm-dd hh24:mi:ss.ff')";
			firstTestList.get(i).setAttributeValue("date_received", time);
			firstTestList.get(i).setAttributeValue("date_started", time);
			firstTestList.get(i).setAttributeValue("t_date_enabled", time);

			// ����޸�ʱ��
			UFDateTime modifyTime = taskHvo.getModifiedtime() == null ? creatTime : taskHvo.getModifiedtime();
			firstTestList.get(i).setAttributeValue("changed_on", "to_timestamp('" + modifyTime + "','yyyy-mm-dd hh24:mi:ss.ff')");

			// ������Խ������
			firstTestList.get(i).setAttributeValue("analysis", taskBvo.getPk_testresultname());

			// ���Խ��������
			firstTestList.get(i).setAttributeValue("common_name", taskBvo.getTestresultshortname());

			// ������Ŀ
			firstTestList.get(i).setAttributeValue("reported_name", taskBvo.getTestitem());

			// �����汾
			firstTestList.get(i).setAttributeValue("version", utils.getAnalysisVerionFromName(taskBvo.getPk_testresultname()));
		}
		processData.setFirstTestList(firstTestList);
	}

	private List<Test> initData(int length) {
		List<Test> rsList = new ArrayList<>();
		for (int i = 0; i < length; i++) {
			rsList.add(new Test());
		}
		return rsList;
	}

}
