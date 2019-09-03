
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
	
	//ί�е�������
	private AggCommissionHVO aggCommissionHVO;
	
	//����������
	private AggTaskHVO aggTaskHVO;
	
	//LIMSί�е�
	private Project project;  
	
	//LIMS��Ʒ��  ˳���ί�е��ӱ�һ��
	private List<CProjLoginSample>loginSampleList;
	
	//LIMS��һ�λ�дsample һ��task��Ӧһ��sample ˳�򶼺������ӱ�һ��
	private List<Sample> firstSampleList; 
	
	// LIMS�ڶ��λ�д��Ʒ  key:c_proj_login_sample.seq_num ��Ʒ������  �ڶ��λ�д,һ��sample����һ����Ʒ
	private Map<String,List<Sample>> secSampleListMap;  
	
	//LIMS ʵ��ǰ����
	//1.key:c_proj_login_sample.seq_num
	//2.list��˳���ί�е���Ӧ��Ʒ�����һ��
	private Map<Integer,List<ParaA>> paraAListMap;    
	
	//LIMS������  ˳���������һ��
	private List<CProjTask> taskList; 
	
	//LIMS ʵ������
	//1.key:c_proj_task.seq_num ����������
	//2.list��˳������񵥶�Ӧ����������������һ��
	private Map<Integer,List<ParaB>> paraBListMap;    
	
	//LIMS��һ�λ�дtest
	//key:һ��task��Ӧһ��test ˳��������ӱ�����˳��һ��
	private List<Test> firstTestList;  
	
	//LIMS�ڶ��λ�дtest
	//key:Sample.sample_number 
	//test�ڶ��λ�д,һ��sample(��Ӧ�ڶ��λ�д��sample)��Ӧһ��test
	private Map<Integer,Test> secTestList;  
	
	//1.key:c_proj_task.seq_num
	//2.list��˳������񵥶�Ӧ�����ʵ���������һ��
	private Map<Integer,List<Result>> firstResultListMap;    
	
	//LIMS�ڶ��λ�дResult 
	//key:sample.sample_number   
	//result�ڶ��λ�дʱ,result,sample,test���߶���һһ��Ӧ
	private Map<Integer,Result> secResultMap;  

	
	//���ű���������ֵ
	private int maxSamplePK = 0;
	
	private int maxTestPK = 0;
	
	private int maxResult = 0;
	
	//sample���Ӧ��task����
	private Map<String,CProjTask> sampleIdTaskMap = null;
	

	/**
	 * ����pk_commission_h ��ʼ��ncί�е�������
	 * @param pk_commission_h
	 */
	@SuppressWarnings("unchecked")
	private void initNCDataFromPK(String pk_commission_h){
		if(pk_commission_h != null){
			IUAPQueryBS bs = NCLocator.getInstance().lookup(IUAPQueryBS.class);
			try {
				//ί�е�
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
					throw new BusinessException("��Ʒ����Ϊ��!");
				}
				
				String sql = "select pk_task_h from qc_task_h where pk_commission_h = '"+pk_commission_h+"' and dr = 0 ";
				String pk_task_h = (String)bs.executeQuery(sql, new ColumnProcessor());
				
				
				//����
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
					throw new BusinessException("��Ʒ����Ϊ��!");
				}
				
				
			} catch (BusinessException e) {
				ExceptionUtils.wrappException(e);
			}
		}
	}
	
	/**
	 * ��ȡ���еڶ��λ�д��sample����
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
	 * ���ݵڶ��λ�д��sample���ҳ���Ӧ��task
	 * ���ǲ����ĸ�task������sampe id ���� A1,B4֮���
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
