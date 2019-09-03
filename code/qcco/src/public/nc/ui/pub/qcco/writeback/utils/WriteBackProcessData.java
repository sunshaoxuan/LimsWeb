
package nc.ui.pub.qcco.writeback.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.qcco.writeback.utils.LIMSVO.*;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.qcco.commission.AggCommissionHVO;
import nc.vo.qcco.commission.CommissionBVO;
import nc.vo.qcco.commission.CommissionHVO;
import nc.vo.qcco.commission.CommissionRVO;
import nc.vo.qcco.task.AggTaskHVO;
import nc.vo.qcco.task.TaskBVO;
import nc.vo.qcco.task.TaskHVO;
import nc.vo.qcco.task.TaskRVO;
import nc.vo.qcco.task.TaskSVO;

public class WriteBackProcessData {
	
	//委托单主子孙
	private AggCommissionHVO aggCommissionHVO;
	
	//任务单主子孙
	private AggTaskHVO aggTaskHVO;
	
	//LIMS委托单
	private Project project;  
	
	//LIMS样品组  顺序和委托单子表一致
	private List<CProjLoginSample>loginSampleList;
	
	//LIMS第一次回写sample 一条task对应一个sample 顺序都和任务单子表一致
	private List<Sample> firstSampleList; 
	
	// LIMS第二次回写样品  key:c_proj_login_sample.seq_num 样品组主键  第二次回写,一个sample代表一个样品
	private Map<String,List<Sample>> secSampleListMap;  
	
	//LIMS 实验前参数
	//1.key:c_proj_login_sample.seq_num
	//2.list的顺序和委托单对应样品的孙表一致
	private Map<Integer,List<ParaA>> paraAListMap;    
	
	//LIMS任务行  顺序和任务单行一致
	private List<CProjTask> taskList; 
	
	//LIMS 实验后参数
	//1.key:c_proj_task.seq_num 任务行主键
	//2.list的顺序和任务单对应任务的试验后参数孙表一致
	private Map<Integer,List<ParaB>> paraBListMap;    
	
	//LIMS第一次回写test
	//key:一个task对应一个test 顺序和任务单子表任务顺序一致
	private List<Test> firstTestList;  
	
	//LIMS第二次回写test
	//key:Sample.sample_number 
	//test第二次回写,一个sample(对应第二次回写的sample)对应一条test
	private Map<Integer,Test> secTestList;  
	
	//1.key:c_proj_task.seq_num
	//2.list的顺序和任务单对应任务的实验条件孙表一致
	private Map<Integer,List<Result>> firstResultListMap;    
	
	//LIMS第二次回写Result 
	//key:sample.sample_number   
	//result第二次回写时,result,sample,test三者都是一一对应
	private Map<Integer,Result> secResultMap;  

	
	//三张表的主键最大值
	private int maxSamplePK = 0;
	
	private int maxTestPK = 0;
	
	private int maxResult = 0;
	
	//sample表对应的task表缓存
	private Map<String,CProjTask> sampleIdTaskMap = null;
	

	/**
	 * 根据pk_commission_h 初始化nc委托单和任务单
	 * @param pk_commission_h
	 */
	@SuppressWarnings("unchecked")
	private void initNCDataFromPK(String pk_commission_h){
		if(pk_commission_h != null){
			IUAPQueryBS bs = NCLocator.getInstance().lookup(IUAPQueryBS.class);
			try {
				//委托单
				CommissionHVO hvo = (CommissionHVO)bs.retrieveByPK(CommissionHVO.class, pk_commission_h);
				aggCommissionHVO = new AggCommissionHVO();
				aggCommissionHVO.setParent(hvo);
				
				List<CommissionBVO> bvoList = 
						(List<CommissionBVO>) bs.retrieveByClause(CommissionBVO.class, " pk_commission_h = '"+hvo.getPk_commission_h()+"'  and dr = 0 ");
				if(bvoList!=null && bvoList.size() > 0){
					for(CommissionBVO bvo : bvoList){
						List<CommissionRVO> rvoList = 
								(List<CommissionRVO>) bs.retrieveByClause(CommissionRVO.class, 
										" pk_commission_b = '"+bvo.getPk_commission_b()+"'  and dr = 0 ");
						if(rvoList!=null && rvoList.size() > 0){
							bvo.setPk_commission_r(rvoList.toArray(new CommissionRVO[0]));
						}
					}
					aggCommissionHVO.setChildren(CommissionBVO.class, bvoList.toArray(new CommissionBVO[0]));
				}else{
					throw new BusinessException("样品不能为空!");
				}
				
				String sql = "select pk_task_h from qc_task_h where pk_commission_h = '"+pk_commission_h+"' and dr = 0 ";
				String pk_task_h = (String)bs.executeQuery(sql, new ColumnProcessor());
				
				
				//任务单
				TaskHVO taskhvo = (TaskHVO)bs.retrieveByPK(TaskHVO.class, pk_task_h);
				aggTaskHVO = new AggTaskHVO();
				aggTaskHVO.setParentVO(taskhvo);
				
				List<TaskBVO> taskbvoList = 
						(List<TaskBVO>) bs.retrieveByClause(TaskBVO.class, " pk_task_h = '"+taskhvo.getPk_task_h()+"'  and dr = 0 ");
				if(taskbvoList!=null && taskbvoList.size() > 0){
					for(TaskBVO bvo : taskbvoList){
						List<TaskRVO> rvoList = 
								(List<TaskRVO>) bs.retrieveByClause(TaskRVO.class, 
										" pk_task_b = '"+bvo.getPk_task_b()+"'  and dr = 0 ");
						if(rvoList!=null && rvoList.size() > 0){
							bvo.setPk_task_r(rvoList.toArray(new TaskRVO[0]));
						}
						
						List<TaskSVO> svoList = 
								(List<TaskSVO>) bs.retrieveByClause(TaskSVO.class, 
										" pk_task_b = '"+bvo.getPk_task_b()+"'  and dr = 0 ");
						if(svoList!=null && svoList.size() > 0){
							bvo.setPk_task_s(svoList.toArray(new TaskSVO[0]));
						}
					}
					aggTaskHVO.setChildren(TaskBVO.class, taskbvoList.toArray(new TaskBVO[0]));
				}else{
					throw new BusinessException("样品不能为空!");
				}
				
				
			} catch (BusinessException e) {
				ExceptionUtils.wrappException(e);
			}
		}
	}
	
	/**
	 * 获取所有第二次回写的sample数据
	 * @return
	 */
	public List<Sample> getAllSecSampleList() {
		List<Sample> rsList = new ArrayList<>();
		if(getSecSampleListMap()!=null && getSecSampleListMap().size() > 0){
			for(List<Sample> list : getSecSampleListMap().values()){
				rsList.addAll(list);
			}
		}
		return rsList;
	}
	/**
	 * 根据第二次回写的sample查找出对应的task
	 * 就是查找哪个task包含了sampe id 比如 A1,B4之类的
	 * @param sample
	 * @return
	 */
	public CProjTask getTaskFromSampleSec(Sample sample) {

		if(sample!=null&&sample.getAttributeValue("text_id")!=null){
			String sampleId = String.valueOf(sample.getAttributeValue("text_id")).split("-")[1];
			return getSampleIdTaskMap().get(sampleId);
		}
		return null;
	}

	public Map<String, CProjTask> getSampleIdTaskMap() {
		if(null == sampleIdTaskMap && getTaskList()!=null && getTaskList().size() > 0){
			sampleIdTaskMap = new HashMap<>();
			for(CProjTask task : getTaskList()){
				if(task.getAttributeValue("assigned_sample") !=null){
					String[] sampleIDs = String.valueOf(task.getAttributeValue("assigned_sample")).split(",");
					if(sampleIDs!=null && sampleIDs.length > 0){
						for(String sampleID : sampleIDs){
							sampleIdTaskMap.put(sampleID, task);
						}
					}
				}
			}
		}
		return sampleIdTaskMap;
	}
	
	public int getMaxSamplePK() {
		return maxSamplePK;
	}

	public void setMaxSamplePK(int maxSamplePK) {
		this.maxSamplePK = maxSamplePK;
	}

	public int getMaxTestPK() {
		return maxTestPK;
	}

	public void setMaxTestPK(int maxTestPK) {
		this.maxTestPK = maxTestPK;
	}

	public int getMaxResult() {
		return maxResult;
	}

	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}

	public WriteBackProcessData(String pk_commission_h){
		initNCDataFromPK(pk_commission_h);
	}

	public AggCommissionHVO getAggCommissionHVO() {
		return aggCommissionHVO;
	}

	public void setAggCommissionHVO(AggCommissionHVO aggCommissionHVO) {
		this.aggCommissionHVO = aggCommissionHVO;
	}

	public AggTaskHVO getAggTaskHVO() {
		return aggTaskHVO;
	}

	public void setAggTaskHVO(AggTaskHVO aggTaskHVO) {
		this.aggTaskHVO = aggTaskHVO;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<CProjLoginSample> getLoginSampleList() {
		return loginSampleList;
	}

	public void setLoginSampleList(List<CProjLoginSample> loginSampleList) {
		this.loginSampleList = loginSampleList;
	}

	public List<Sample> getFirstSampleList() {
		return firstSampleList;
	}

	public void setFirstSampleList(List<Sample> firstSampleList) {
		this.firstSampleList = firstSampleList;
	}

	public Map<String, List<Sample>> getSecSampleListMap() {
		return secSampleListMap;
	}

	public void setSecSampleListMap(Map<String, List<Sample>> secSampleListMap) {
		this.secSampleListMap = secSampleListMap;
	}

	public Map<Integer, List<ParaA>> getParaAListMap() {
		return paraAListMap;
	}

	public void setParaAListMap(Map<Integer, List<ParaA>> paraAListMap) {
		this.paraAListMap = paraAListMap;
	}

	public List<CProjTask> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<CProjTask> taskList) {
		this.taskList = taskList;
	}

	public Map<Integer, List<ParaB>> getParaBListMap() {
		return paraBListMap;
	}

	public void setParaBListMap(Map<Integer, List<ParaB>> paraBListMap) {
		this.paraBListMap = paraBListMap;
	}

	public List<Test> getFirstTestList() {
		return firstTestList;
	}

	public void setFirstTestList(List<Test> firstTestList) {
		this.firstTestList = firstTestList;
	}

	public Map<Integer, Test> getSecTestList() {
		return secTestList;
	}

	public void setSecTestList(Map<Integer, Test> secTestList) {
		this.secTestList = secTestList;
	}

	public Map<Integer, List<Result>> getFirstResultListMap() {
		return firstResultListMap;
	}

	public void setFirstResultListMap(Map<Integer, List<Result>> firstResultListMap) {
		this.firstResultListMap = firstResultListMap;
	}

	public Map<Integer, Result> getSecResultMap() {
		return secResultMap;
	}

	public void setSecResultMap(Map<Integer, Result> secResultMap) {
		this.secResultMap = secResultMap;
	}

	
	

	

	
}
