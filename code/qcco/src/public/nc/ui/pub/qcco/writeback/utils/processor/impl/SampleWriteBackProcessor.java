package nc.ui.pub.qcco.writeback.utils.processor.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.qcco.writeback.utils.WriteBackProcessData;
import nc.ui.pub.qcco.writeback.utils.LIMSVO.CProjLoginSample;
import nc.ui.pub.qcco.writeback.utils.LIMSVO.CProjTask;
import nc.ui.pub.qcco.writeback.utils.LIMSVO.Sample;
import nc.ui.pub.qcco.writeback.utils.common.CommonUtils;
import nc.ui.pub.qcco.writeback.utils.processor.IFirstWriteBackProcessor;
import nc.ui.pub.qcco.writeback.utils.processor.ISecWriteBackProcessor;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFLiteralDate;
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
		processSecond(data);
	}
	
	
	
	
	/**
	 * sample 二次回写
	 * 
	 * @param data
	 * @throws DAOException 
	 */
	private void processSecond(WriteBackProcessData processData) throws DAOException {
		//NC Data
		List<CProjLoginSample> sampleGroupList = processData.getLoginSampleList();
		
		//LIMS Data
		List<CProjTask> taskList = processData.getTaskList();
		
		//CLONED_FROM 自增pk
		int cloneFromPK = utils.getPrePk("cloned_from", "sample", 1).get(0)-1;
		int transNumPK = utils.getPrePk("trans_num", "sample", 1).get(0)-1;
		
		//时间前缀
		String timeStr = new UFLiteralDate().toStdString().replaceAll("-", "");
		
		//需要回写的LIMS数据 组数*每组数量
		Map<String,List<Sample>> secSampleListMap = new HashMap<>();
		
		if(sampleGroupList!=null && sampleGroupList.size() > 0){
			for(int i = 0 ;i < sampleGroupList.size() ;i++){
				if(sampleGroupList.get(i)!=null && sampleGroupList.get(i).getAttributeValue("sample_quantity")!=null){
					//样品组别
					String group = String.valueOf(sampleGroupList.get(i).getAttributeValue("sample_group"));
					//每组数量
					Integer gourpNum = Integer.parseInt(String.valueOf(sampleGroupList.get(i).getAttributeValue("sample_quantity")));
					
					//每组list
					List<Sample> sampleList = new ArrayList<>();
					for(int j = 0 ; j < gourpNum ;j++){
						//开始生成sample
						Sample sample = new Sample();
						//SAMPLE.PROJECT	所属委托单号
						sample.setAttributeValue("project", processData.getProject().getAttributeValue("name"));
						//SAMPLE.AUDIT	T

						sample.setAttributeValue("audit", "T");
						//SAMPLE.C_CONTACT_TYPE	触点类型

						sample.setAttributeValue("c_contact_type", sampleGroupList.get(i).getAttributeValue("contact_type"));
						//SAMPLE.C_SAMPLE_GROUP	样品组

						sample.setAttributeValue("c_sample_group", group);
						//SAMPLE.CLONED_FROM	主键式自增

						sample.setAttributeValue("cloned_from", ++cloneFromPK);
						
						//SAMPLE.CUSTOMER	提交人公司名
						
						sample.setAttributeValue("customer", taskList.get(i).getAttributeValue("C_SUBMIT_BY"));
						//SAMPLE.DATE_STARTED	null

						sample.setAttributeValue("date_started", null);
						//SAMPLE.LAB	XXX sample 2 所属测试小组名称

						sample.setAttributeValue("lab", null);
						//SAMPLE.LOGIN_BY	BACKGROUND

						sample.setAttributeValue("login_by", "BACKGROUND");
						//SAMPLE.OLD_STATUS	C

						sample.setAttributeValue("old_status", "C");
						//SAMPLE.PRODUCT	企标值

						sample.setAttributeValue("product", sampleGroupList.get(i).getAttributeValue("product_standard"));
						//SAMPLE.PRODUCT_VERSION	产品版本，默认1

						sample.setAttributeValue("product_version", 1);
						//SAMPLE.SAMPLE_NUMBER	"主键，
						//有多少只样品，要写入多少行"

						sample.setAttributeValue("sample_number", processData.getMaxSamplePK()+1);
						processData.setMaxSamplePK(processData.getMaxSamplePK()+1);
						//SAMPLE.SAMPLING_POINT	样品规格号

						sample.setAttributeValue("sampling_point", sampleGroupList.get(i).getAttributeValue("production_spec"));
						//SAMPLE.STARTED	F

						sample.setAttributeValue("started", "F");
						//SAMPLE.STATUS	U

						sample.setAttributeValue("status", "U");
						//SAMPLE.T_LOGIN_VERIFIED	T

						sample.setAttributeValue("t_login_verified", "T");
						//SAMPLE.TEMPLATE	HF-MAIN

						sample.setAttributeValue("template", "HF-MAIN");
						//SAMPLE.TEXT_ID	此处有两种生成方式：此次为第二种写入方式，写入值为“2位年月日-”+样品编号

						sample.setAttributeValue("text_id", timeStr+"-"+group+""+j);
						//SAMPLE.TRANS_NUM	主键式自增
						sample.setAttributeValue("trans_num", ++transNumPK);
						
						sampleList.add(new Sample());
					}
					secSampleListMap.put(String.valueOf(sampleGroupList.get(i).getAttributeValue("seq_num")), sampleList);
					
				}
			}
			processData.setSecSampleListMap(secSampleListMap);
		}
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
		processData.setMaxSamplePK(prePk.get(taskList.size()-1));
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
