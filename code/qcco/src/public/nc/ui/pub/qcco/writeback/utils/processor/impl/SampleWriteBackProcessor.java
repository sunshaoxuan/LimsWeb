package nc.ui.pub.qcco.writeback.utils.processor.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.dao.BaseDAO;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.qcco.writeback.utils.WriteBackProcessData;
import nc.ui.pub.qcco.writeback.utils.LIMSVO.CProjTask;
import nc.ui.pub.qcco.writeback.utils.LIMSVO.ParaB;
import nc.ui.pub.qcco.writeback.utils.LIMSVO.Sample;
import nc.ui.pub.qcco.writeback.utils.common.CommonUtils;
import nc.ui.pub.qcco.writeback.utils.processor.IFirstWriteBackProcessor;
import nc.ui.pub.qcco.writeback.utils.processor.ISecWriteBackProcessor;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.qcco.commission.CommissionHVO;

/**
 * 样品第一次和第二回写
 * 
 * @author 91967
 * 
 */
public class SampleWriteBackProcessor implements IFirstWriteBackProcessor, ISecWriteBackProcessor {

	private CommonUtils utils;

	@Override
	public void setUtils(CommonUtils utils) {
		this.utils = utils;
	}


	@Override
	public void processFirst(WriteBackProcessData data) throws BusinessException {
		process(data);

	}

	@Override
	public void processSec(WriteBackProcessData data) throws BusinessException {
		// TODO 自动生成的方法存根

	}
	
	
	/**
	 * 获取Insert语句片断--Sample表额外回写方法
	 *  sample 第一次回写与task对应,有几条task就有几个sample
	 * @param processData processData
	 * @throws BusinessException
	 */
	private void process(WriteBackProcessData processData) throws BusinessException {
		
		// 委托单
		CommissionHVO hvo = processData.getAggCommissionHVO().getParentVO();
		List<CProjTask> taskList = processData.getTaskList();
		// 修改日期--委托单
		UFDateTime modtime = hvo.getModifiedtime() == null ? hvo.getCreationtime() : hvo.getModifiedtime();
		if (modtime == null) {
			modtime = new UFDateTime();
		}
		
		
		//需要回写的LIMS数据
		List<Sample> firstSampleList = initData(taskList.size());
		
		// 主键
		List<Integer> prePk = utils.getPrePk("sample_number", "sample", taskList.size());
		
		//textid 
		String sql = "select max(str2) from (SELECT REGEXP_SUBSTR(TEXT_ID,'[^-]+',1,1,'i')  STR1, "
				+ " nvl(REGEXP_SUBSTR(TEXT_ID,'[^-]+',1,2,'i'),'1')  str2 FROM sample) origin " + " where str1 = '"
				+ String.valueOf(modtime.getYear()).substring(2, 4) + "' order by str2 desc ";
		Object maxNumObj = new BaseDAO().executeQuery(sql, new ColumnProcessor());
		int maxNum = 1;
		if (maxNumObj != null) {
			try {
				maxNum = Integer.valueOf(maxNumObj.toString());
			} catch (Exception e) {
				maxNum = 1;
			}
		}
		
		if(taskList!=null && taskList.size() > 0){
			for(int i = 0 ;i<taskList.size() ; i++){
				// 修改时间 任务单子表
				// 修改日期
				firstSampleList.get(i).setAttributeValue("changed_on", taskList.get(i).getAttributeValue("changed_on"));
				
				String time = "to_timestamp('" + modtime.toStdString() + "','yyyy-mm-dd hh24:mi:ss.ff')";
				
				firstSampleList.get(i).setAttributeValue("date_started", time);
				firstSampleList.get(i).setAttributeValue("login_date", time);
				firstSampleList.get(i).setAttributeValue("recd_date", time);

				// 员工号
				String pk_user = hvo.getModifier() == null ? hvo.getCreator() : hvo.getModifier();
				if (pk_user != null) {
					String userCode = String.valueOf(utils.dealSystemRef(CommissionHVO.class, "cuserid", pk_user));
					firstSampleList.get(i).setAttributeValue("login_by", userCode);
					firstSampleList.get(i).setAttributeValue("received_by", userCode);
				}

				
				Integer pkFirstSample = prePk.get(i);
				firstSampleList.get(i).setAttributeValue("sample_number", pkFirstSample);
				firstSampleList.get(i).setAttributeValue("original_sample", pkFirstSample);
				//task 任务关联
				taskList.get(i).setAttributeValue("sample_number", pkFirstSample);
				
				

				// SAMPLE.TEXT_ID此处有两种生成方式：由于本次是第一次写入，写入的是上表红色的格式(19-5673)，
				// 代表试验前的样品分类，格式为“年份-最大值+1”
				firstSampleList.get(i).setAttributeValue("text_id", String.valueOf(modtime.getYear()).substring(2, 4)+(++maxNum));

			}
		}
		processData.setFirstSampleList(firstSampleList);
	}


	private List<Sample> initData(int size) {
		List<Sample> rsList = new ArrayList<>();
		for(int i = 0;i<size;i++){
			rsList.add(new Sample());
		}
		return rsList;
	}

}
