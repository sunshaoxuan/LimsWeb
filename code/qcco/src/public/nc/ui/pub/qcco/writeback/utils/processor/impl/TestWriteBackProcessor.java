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

		
		//��������
		String dateCreateDate = "to_timestamp('"+
				processData.getAggCommissionHVO().getParentVO().getCreationtime().getEndDate().toStdString()
					+"','yyyy-mm-dd hh24:mi:ss.ff')";
		
		
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
						//���񵥱�����Խ������
						test.setAttributeValue("analysis", String.valueOf(task.getAttributeValue("analysis")));
						Map<String, Object> analysis = utils.getAnalysis(String.valueOf(task.getAttributeValue("analysis")));
						//�����汾
						test.setAttributeValue("version", analysis.get("version"));
						//���񵥱��������Ŀ
						test.setAttributeValue("reported_name", task.getAttributeValue("task_reported_name"));
						//����
						test.setAttributeValue("original_test", processData.getMaxTestPK()+1);
						//����޸�ʱ��
						test.setAttributeValue("changed_on", task.getAttributeValue("changed_on"));
						//�Ƶ�����
						test.setAttributeValue("t_date_enabled", dateCreateDate);
						//������
						test.setAttributeValue("common_name", analysis.get("common_name"));
						//task.removeAttributeValue("shot_name_for_test");
						//�ų����
						test.setAttributeValue("c_arrange_type", analysis.get("c_arrange_type"));

						
						//һЩ�̶�ֵ
						test.setAttributeValue("analysis_count", 0);
						test.setAttributeValue("replicate_count", 1);
						test.setAttributeValue("old_status", "I");
						test.setAttributeValue("batch_parent_test", 0);
						test.setAttributeValue("batch_sibling_test", 0);
						test.setAttributeValue("parent_test", 0);
						test.setAttributeValue("date_received", null);
						test.setAttributeValue("date_started", null);
						test.setAttributeValue("assigned_operator", null);
						test.setAttributeValue("prep", "F");
						test.setAttributeValue("prep_date", null);
						test.setAttributeValue("prep_by", null);
						test.setAttributeValue("date_completed", null);
						test.setAttributeValue("date_reviewed", null);
						test.setAttributeValue("reviewer", null);
						test.setAttributeValue("replicate_test", "F");
						test.setAttributeValue("test_priority", 0);
						test.setAttributeValue("in_spec", "T");
						test.setAttributeValue("in_cal", "T");
						test.setAttributeValue("test_location", "DEFAULT");
						test.setAttributeValue("resolve_reqd", "F");
						test.setAttributeValue("stage", "NONE");
						test.setAttributeValue("primary_in_spec", "T");
						test.setAttributeValue("in_control", "T");
						test.setAttributeValue("re_tested", "F");
						test.setAttributeValue("modified_results", "F");
						test.setAttributeValue("on_worksheet", "F");
						test.setAttributeValue("aliquoted_to", 0);
						test.setAttributeValue("display_results", "T");
						test.setAttributeValue("split_replicates", "F");
						test.setAttributeValue("cross_sample", "F");
						test.setAttributeValue("released", "F");
						test.setAttributeValue("aliquot_group", "DEFAULT");
						test.setAttributeValue("double_entry", "F");
						test.setAttributeValue("child_out_spec", "F");
						test.setAttributeValue("charge_entry", 0);
						test.setAttributeValue("signed", "F");
						test.setAttributeValue("batch_original_test", 0);
						test.setAttributeValue("test_sequence_no", 0);
						test.setAttributeValue("invoice_number", 0);
						test.setAttributeValue("cntrct_qte_item_no", 0);
						test.setAttributeValue("reported_rslt_oos", "F");
						test.setAttributeValue("double_blind", "F");
						test.setAttributeValue("pre_invoice_number", 0);
						test.setAttributeValue("t_charge_group", 0);
						test.setAttributeValue("t_needs_location", "F");
						test.setAttributeValue("t_prep_test", 0);
						test.setAttributeValue("t_qc_reference", 0);
						test.setAttributeValue("t_turnaround_actua", 0);
						test.setAttributeValue("t_turnaround_charg", 0);
						test.setAttributeValue("t_turnaround_met", "F");
						test.setAttributeValue("trans_num", 0);
						test.setAttributeValue("c_if_arranged", "F");
						test.setAttributeValue("c_arrange_seq_num", 0);
						test.setAttributeValue("c_apply_review", "F");
						test.setAttributeValue("c_task_status", 0);
						test.setAttributeValue("c_test_cycle", 0);
						test.setAttributeValue("c_failure_cycle", 0);
						test.setAttributeValue("c_base_para_temp", "T");
						test.setAttributeValue("group_name", "DEFAULT");
						
						
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
			//��������һ�����ɵ�sampleֻ��һ��
			firstTestList.get(i).setAttributeValue("sample_number", firstSampleList.get(0).getAttributeValue("sample_number"));

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
			
			firstTestList.get(i).setAttributeValue("t_date_enabled", "to_timestamp('" + creatTime.getEndDate().toStdString() + "','yyyy-mm-dd hh24:mi:ss.ff')");
			firstTestList.get(i).setAttributeValue("date_completed", "to_timestamp('" + new UFDateTime().toStdString() + "','yyyy-mm-dd hh24:mi:ss.ff')");
			
			// ����޸�ʱ��
			UFDateTime modifyTime = taskHvo.getModifiedtime() == null ? creatTime : taskHvo.getModifiedtime();
			firstTestList.get(i).setAttributeValue("changed_on", "to_timestamp('" + modifyTime + "','yyyy-mm-dd hh24:mi:ss.ff')");

			String resultAnalysis = taskBvo.getPk_testresultname();
			String analysis = null;
			if(resultAnalysis.length() > 2){
				if(resultAnalysis.substring(resultAnalysis.length()-2).equals("_A")){
					analysis = resultAnalysis.substring(0, resultAnalysis.length()-2);
				}else{
					analysis = resultAnalysis.substring(0, resultAnalysis.length()-1);
				}
			}
			// ������Խ������
			firstTestList.get(i).setAttributeValue("analysis", analysis);

			// ���Խ��������
			firstTestList.get(i).setAttributeValue("common_name", taskBvo.getTestresultshortname());

			// ������Ŀ
			firstTestList.get(i).setAttributeValue("reported_name", taskBvo.getTestitem());

			// �����汾
			firstTestList.get(i).setAttributeValue("version", utils.getAnalysisVerionFromName(analysis));
			//lab
			firstTestList.get(i).setAttributeValue("lab", utils.getLabFromAnalysisName(analysis));
			if(0==i){
				firstSampleList.get(0).setAttributeValue("lab",utils.getLabFromAnalysisName(analysis));
				
			}
			//��������
			firstTestList.get(i).setAttributeValue("t_analysis_method", utils.getMethodFromAnalysisName(analysis));
			firstSampleList.get(0).setAttributeValue("status","C");
			
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
