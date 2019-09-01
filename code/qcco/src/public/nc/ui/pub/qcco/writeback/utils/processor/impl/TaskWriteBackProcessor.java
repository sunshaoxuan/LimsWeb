package nc.ui.pub.qcco.writeback.utils.processor.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.mozilla.javascript.edu.emory.mathcs.backport.java.util.Collections;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.jdbc.framework.processor.MapProcessor;
import nc.ui.pub.qcco.writeback.utils.WriteBackProcessData;
import nc.ui.pub.qcco.writeback.utils.LIMSVO.CProjTask;
import nc.ui.pub.qcco.writeback.utils.common.CommonUtils;
import nc.ui.pub.qcco.writeback.utils.mapping.FirstWriteBackStaticMaping;
import nc.ui.pub.qcco.writeback.utils.processor.IFirstWriteBackProcessor;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.qcco.commission.CommissionBVO;
import nc.vo.qcco.task.TaskBVO;

/**
 * 任务行回写
 * 
 * @author 91967
 * 
 */
public class TaskWriteBackProcessor implements IFirstWriteBackProcessor {

	private CommonUtils utils;

	@Override
	public void setUtils(CommonUtils utils) {
		this.utils = utils;
	}

	@Override
	public void processFirst(WriteBackProcessData data) throws BusinessException {
		process(data);
	}

	/**
	 * 获取Insert语句片断
	 * 
	 * @param processData
	 *            processData
	 * @throws BusinessException
	 */
	private void process(WriteBackProcessData processData) throws BusinessException {

		// NC数据
		List<ISuperVO> srcDataList = new ArrayList<>();
		ISuperVO[] superVOs = processData.getAggTaskHVO().getChildren(TaskBVO.class);
		Collections.addAll(srcDataList, superVOs);
		if (null == srcDataList || srcDataList.size() <= 0) {
			return ;
		}

		//需要回写的LIMS数据
		List<CProjTask> taskList = initWriteBackList(srcDataList.size());
		
		// 预申请pk
		List<Integer> pkList = utils.getPrePk(TaskBVO.class, srcDataList.size());

		// 用于拼接字段名
		StringBuilder insertFields = new StringBuilder();
		// 进行列循环
		for (Entry<String, String> map : FirstWriteBackStaticMaping.BODY_TASK_MAPPING.entrySet()) {
			String fieldName = map.getKey();
			String[] fields = null;
			if (map.getValue().contains(";")) {
				fields = utils.getWriteBackFields(map.getValue().split(";"));
			} else {
				fields = utils.getWriteBackFields(new String[] { map.getValue() });
			}
			// times是处理一对多关系的
			for (String field : fields) {
				insertFields.append(field).append(",");
			}

			if (srcDataList != null && srcDataList.size() > 0) {
				for (int i = 0 ;i < srcDataList.size();i++) {
					Object unDofieldValue = srcDataList.get(i).getAttributeValue(fieldName);

					Object realValue = utils.getRealValue(unDofieldValue, fieldName, CommissionBVO.class);

					for (String field : fields) {
						taskList.get(i).setAttributeValue(field, realValue);
					}

				}
			}
		}

		
		// 回写主外键和其他信息
		if (srcDataList != null) {
			Map<String,String> rs = getAppoveInfo(processData.getAggCommissionHVO().getParentVO().getPk_commission_h());
			for (int i = 0; i < srcDataList.size(); i++) {
				taskList.get(i).setAttributeValue("seq_num", pkList.get(i));

				taskList.get(i).setAttributeValue("project", processData.getProject().getAttributeValue("name"));
				
				//approve info
				taskList.get(i).setAttributeValue("changed_by", rs.get("changed_by"));
				taskList.get(i).setAttributeValue("changed_on","to_timestamp('"+rs.get("changed_on")+"','yyyy-mm-dd hh24:mi:ss.ff')");
				taskList.get(i).setAttributeValue("c_submit_by", rs.get("c_submit_by"));
				taskList.get(i).setAttributeValue("c_submit_date", "to_timestamp('"+rs.get("c_submit_date")+"','yyyy-mm-dd hh24:mi:ss.ff') ");
				
			}
		}
		processData.setTaskList(taskList);
	}

	private Map<String, String> getAppoveInfo(String pk_commission_h) {
		String sql = "select job.psncode changed_by,taskh.modifiedtime changed_on,"
				+ " job2.psncode c_submit_by,ch.creationtime c_submit_date from qc_task_h taskh "
				+ " left join sm_user sm on sm.cuserid = taskh.modifier "
				+ " left join (select * from bd_psnjob jobinner where ismainjob = 'Y' ) job on rownum = 1 and job.pk_psndoc = sm.pk_psndoc "
				+ " left join qc_commission_h ch on ch.pk_commission_h = taskh.pk_commission_h "
				+ " left join sm_user sm2 on sm2.cuserid = ch.creator "
				+ " left join (select * from bd_psnjob jobinner where ismainjob = 'Y' ) job2 on rownum = 1 and job2.pk_psndoc = sm2.pk_psndoc "
				+ " where taskh.pk_commission_h = '" + pk_commission_h + "' ";
		Map<String, String> rs = null;
		try {
			rs = (Map<String, String>) new BaseDAO().executeQuery(sql, new MapProcessor());
		} catch (DAOException e) {
			rs = new HashMap<>();
			;
		}
		return rs;
	}

	private List<CProjTask> initWriteBackList(int size) {
		List<CProjTask> rsList = new ArrayList<>();
		
		for(int i = 0;i < size ;i++){
			rsList.add(new CProjTask());
		}
		return rsList;
	}
}
