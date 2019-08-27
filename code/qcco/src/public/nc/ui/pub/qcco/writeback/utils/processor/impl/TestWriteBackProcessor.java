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
 * test第一次和第二次回写
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
		// TODO 自动生成的方法存根

	}

	@Override
	public void processFirst(WriteBackProcessData data) throws BusinessException{
		process(data);
	}


	/**
     * 第一次回写Test表
     * @param object 
     * @param object
     * @param pk_test 预申请的test主键
     * @param pk_firstSample 第一次回写的sample表主键
     * @throws BusinessException 
     */
    private void process(WriteBackProcessData processData) throws BusinessException {
    	//NC数据
    	ISuperVO[] bvos = processData.getAggTaskHVO().getChildren(TaskBVO.class);
    	TaskHVO taskHvo = processData.getAggTaskHVO().getParentVO();
    	
    	//sample数据
    	List<Sample> firstSampleList = processData.getFirstSampleList();
    	//task 数据
    	List<CProjTask> taskList = processData.getTaskList();
    	
    	//需要回写的LIMS数据 --test 第一次回写, 一个sample对应一个test 都对应一条task任务
    	List<Test> firstTestList = initData(bvos.length);
    	
    	List<Integer> test_numberList = 
    			utils.getPrePk("test_number","test",processData.getTaskList().size());
    	
    	for(int i = 0 ; i < bvos.length ; i++){
    			TaskBVO taskBvo = (TaskBVO)bvos[i];
    			
    			//主键 外键
    			firstTestList.get(i).setAttributeValue("test_number", test_numberList.get(i));
    			firstTestList.get(i).setAttributeValue("original_test", test_numberList.get(i));
    			firstTestList.get(i).setAttributeValue("sample_number", firstSampleList.get(i).getAttributeValue("sample_number"));
    			
    			//task 任务关联
    			taskList.get(i).setAttributeValue("test_number", test_numberList.get(i));
    			
    			//任务单创建时间
    			UFDateTime creatTime = taskHvo.getCreationtime();
    			String time = "to_timestamp('"+creatTime+"','yyyy-mm-dd hh24:mi:ss.ff')";
    			firstTestList.get(i).setAttributeValue("date_received", time);
    			firstTestList.get(i).setAttributeValue("date_started", time);
    			firstTestList.get(i).setAttributeValue("t_date_enabled", time);
    			
    			//最后修改时间
    			UFDateTime modifyTime = taskHvo.getModifiedtime()==null?creatTime:taskHvo.getModifiedtime();
    			firstTestList.get(i).setAttributeValue("changed_on", "to_timestamp('"+modifyTime+"','yyyy-mm-dd hh24:mi:ss.ff')");
    			
    			//表体测试结果名称
    			firstTestList.get(i).setAttributeValue("analysis", taskBvo.getPk_testresultname());
    			
    			//测试结果短名称
    			firstTestList.get(i).setAttributeValue("common_name", taskBvo.getTestresultshortname());
    			
    			//测试项目
    			firstTestList.get(i).setAttributeValue("reported_name", taskBvo.getTestitem());
    			
    			//分析版本
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
