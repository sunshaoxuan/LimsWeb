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
 * test第一次和第二次回写
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
	 * test 二次回写
	 * 
	 * @param data
	 * @throws DAOException
	 */
	private void processSecond(WriteBackProcessData processData) throws DAOException {
		// LIMS Data
		List<Sample> allSampleList = processData.getAllSecSampleList();
		//所有样品对应的task
		
		
		// 需要回写的LIMS
		// test第二次回写,task每分配一个样品就产生一个test
		List<Test> secTestList = new ArrayList<>();

		if (allSampleList != null && allSampleList.size() > 0) {
			for (int i = 0; i < allSampleList.size(); i++) {
				List<CProjTask> taskList = processData.getTaskFromSampleSec(allSampleList.get(i));
				int labCount = 1;
				if(taskList!=null && taskList.size() > 0){
					if(taskList.size() > 1){
						//当一个样品不止一条task时,序列为true
						allSampleList.get(i).setAttributeValue("c_is_sequnce", "T");
					}
					for(CProjTask task : taskList){
						Test test = new Test();
						// TEST.TEST_NUMBER 根据sample_number主键自增
						test.setAttributeValue("test_number", processData.getMaxTestPK()+1);
						processData.setMaxTestPK(processData.getMaxTestPK()+1);
						
						// TEST.SAMPLE_NUMBER
						// 取sample表二次插入的sample_number，一个任务有多少只样品，便有多少行
						test.setAttributeValue("sample_number", Integer.parseInt(String.valueOf(allSampleList.get(i).getAttributeValue("sample_number"))));

						// TEST.STATUS I
						test.setAttributeValue("status", "I");

						// TEST.C_TASK_SEQ_NUM c_proj_task表任务表任务对应的主键
						test.setAttributeValue("c_task_seq_num", task.getAttributeValue("seq_num"));

						// TEST.C_TASK_ID 任务表c_proj_task.task_id
						test.setAttributeValue("c_task_id", task.getAttributeValue("task_id"));

						// TEST.C_ARRANGE_TYPE XXX test 2 排程类别
						test.setAttributeValue("c_arrange_type", "");

						// TEST.test类型 
						test.setAttributeValue("c_test_type", "测试结果");

						// TEST.ORDER_NUMBER 任务排序
						test.setAttributeValue("order_number", task.getAttributeValue("order_number"));

						// TEST.LAB  测试小组名称
						test.setAttributeValue("lab", utils.getLabFromAnalysisName(String.valueOf(task.getAttributeValue("analysis"))));
						if(1 == labCount){
							allSampleList.get(i).setAttributeValue("lab", utils.getLabFromAnalysisName(String.valueOf(task.getAttributeValue("analysis"))));
							labCount++;
						}
						
						// TEST.VARIATION default:null
						test.setAttributeValue("variation", null);

						// TEST.T_ANALYSIS_METHOD 分析方法(如IEC61810-7)
						test.setAttributeValue("t_analysis_method", utils.getMethodFromAnalysisName(String.valueOf(task.getAttributeValue("analysis"))));

						secTestList.add(test);
					}
				}
			}
			processData.setSecTestList(secTestList);
		}
	}

	/**
	 * 第一次回写Test表
	 * 
	 * @param object
	 * @param object
	 * @param pk_test
	 *            预申请的test主键
	 * @param pk_firstSample
	 *            第一次回写的sample表主键
	 * @throws BusinessException
	 */
	private void process(WriteBackProcessData processData) throws BusinessException {
		// NC数据
		ISuperVO[] bvos = processData.getAggTaskHVO().getChildren(TaskBVO.class);
		TaskHVO taskHvo = processData.getAggTaskHVO().getParentVO();

		// sample数据
		List<Sample> firstSampleList = processData.getFirstSampleList();
		// task 数据
		List<CProjTask> taskList = processData.getTaskList();

		// 需要回写的LIMS数据 --test 第一次回写, 一个sample对应一个test 都对应一条task任务
		List<Test> firstTestList = initData(bvos.length);

		// 预申请PK
		List<Integer> test_numberList = utils.getPrePk("test_number", "test", processData.getTaskList().size());
		processData.setMaxTestPK(test_numberList.get(processData.getTaskList().size() - 1));

		for (int i = 0; i < bvos.length; i++) {
			TaskBVO taskBvo = (TaskBVO) bvos[i];

			// 主键 外键
			firstTestList.get(i).setAttributeValue("test_number", test_numberList.get(i));
			firstTestList.get(i).setAttributeValue("original_test", test_numberList.get(i));
			firstTestList.get(i).setAttributeValue("sample_number", firstSampleList.get(i).getAttributeValue("sample_number"));

			// task 任务关联
			taskList.get(i).setAttributeValue("test_number", test_numberList.get(i));

			// 任务单创建时间
			UFDateTime creatTime = taskHvo.getCreationtime();
			if(null==creatTime){
				creatTime = processData.getAggCommissionHVO().getParentVO().getCreationtime();
			}
			String time = "to_timestamp('" + creatTime + "','yyyy-mm-dd hh24:mi:ss.ff')";
			firstTestList.get(i).setAttributeValue("date_received", time);
			firstTestList.get(i).setAttributeValue("date_started", time);
			firstTestList.get(i).setAttributeValue("t_date_enabled", time);

			// 最后修改时间
			UFDateTime modifyTime = taskHvo.getModifiedtime() == null ? creatTime : taskHvo.getModifiedtime();
			firstTestList.get(i).setAttributeValue("changed_on", "to_timestamp('" + modifyTime + "','yyyy-mm-dd hh24:mi:ss.ff')");

			// 表体测试结果名称
			firstTestList.get(i).setAttributeValue("analysis", taskBvo.getPk_testresultname());

			// 测试结果短名称
			firstTestList.get(i).setAttributeValue("common_name", taskBvo.getTestresultshortname());

			// 测试项目
			firstTestList.get(i).setAttributeValue("reported_name", taskBvo.getTestitem());

			// 分析版本
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
