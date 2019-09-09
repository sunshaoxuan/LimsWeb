package nc.ui.pub.qcco.writeback.utils.processor.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.mozilla.javascript.edu.emory.mathcs.backport.java.util.Collections;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.qcco.writeback.utils.WriteBackProcessData;
import nc.ui.pub.qcco.writeback.utils.LIMSVO.ParaB;
import nc.ui.pub.qcco.writeback.utils.common.CommonUtils;
import nc.ui.pub.qcco.writeback.utils.mapping.FirstWriteBackStaticMaping;
import nc.ui.pub.qcco.writeback.utils.processor.IFirstWriteBackProcessor;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.qcco.commission.CommissionBVO;
import nc.vo.qcco.task.AggTaskHVO;
import nc.vo.qcco.task.TaskBVO;
import nc.vo.qcco.task.TaskRVO;

/**
 * 试验后参数回写
 * 
 * @author 91967
 * 
 */
public class ParaBWriteBackProcessor implements IFirstWriteBackProcessor {
	private CommonUtils utils;
	private BaseDAO baseDao = new BaseDAO();
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
	 * @param fieldMap
	 * @param clazz
	 * @param pkname
	 * @param pkvalue
	 * @param ncPK2LimsPkMap
	 *            ncpk和lims系统pk对应关系
	 * @param ncPK2ObjectMap
	 *            本次保存的所有NCOBJ
	 * @param testFirstExtends
	 *            sample表第一次回写的部分sql
	 * 
	 * @return fieldValues[0]: 字段名片断<br />
	 *         fieldValues[1-n]：值片断
	 * @return project 委托单编号,用于各个表的project
	 * @throws BusinessException
	 */
	private void process(WriteBackProcessData processData) throws BusinessException {

		// NC数据
		List<ISuperVO> srcDataList = new ArrayList<>();
		// 获取所有的实验前参数VO
		ISuperVO[] superVOs = getAllTaskRVO(processData);
		Collections.addAll(srcDataList, superVOs);
		if (null == srcDataList || srcDataList.size() <= 0) {
			return ;
		}
		// 需要回写的LIMS数据
		// LIMS 实验前参数
		List<ParaB> allParaBList = initWriteBackList(srcDataList.size());
		// 预申请pk
		List<Integer> pkList = utils.getPrePk(TaskRVO.class, srcDataList.size());
		//处理ENTRY_CODE(选取C_PROJ_PARA_A的最大值)
        //预申请ENTRY_CODE
        List<Integer> entryCodeList = utils.getPrePk("entry_code","c_proj_task_para_b",srcDataList.size());

		// 进行列循环
		for (Entry<String, String> map : FirstWriteBackStaticMaping.GRAND_AFTER_MAPPING.entrySet()) {
			String fieldName = map.getKey();

			String[] fields = null;
			if (map.getValue().contains(";")) {
				fields = utils.getWriteBackFields(map.getValue().split(";"));
			} else {
				fields = utils.getWriteBackFields(new String[] { map.getValue() });
			}

			if (srcDataList != null && srcDataList.size() > 0) {
				for (int i = 0; i < srcDataList.size(); i++) {
					Object unDofieldValue = srcDataList.get(i).getAttributeValue(fieldName);
					Object realValue = utils.getRealValue(unDofieldValue, fieldName, TaskRVO.class);
					for (String field : fields) {
						allParaBList.get(i).setAttributeValue(field, realValue);
					}
				}
			}
		}
		for (int i = 0; i < srcDataList.size(); i++) {
				// 获取上层的主键:
				String fatherPk = (String) (((ISuperVO) srcDataList.get(i)).getAttributeValue("pk_task_b"));

				// 处理RULE_TYPE的值
				TaskRVO rvo = ((TaskRVO) srcDataList.get(i));
				allParaBList.get(i).setAttributeValue("spec_rule", dealRuleType(rvo));
				
				// 回写 分析名
				TaskBVO bvo = (TaskBVO) utils.getNCObjByPK(rvo.getPk_task_b(), TaskBVO.class);
				String analysisName = bvo == null ? null : bvo.getPk_testresultname();
				allParaBList.get(i).setAttributeValue("task_name", analysisName);
				//analysis_version 分析版本
				allParaBList.get(i).setAttributeValue("analysis_version", utils.getAnalysisVerionFromName(analysisName));
				// proj_logsamp_seqnum 通过组别获取对应的委托单子表
				allParaBList.get(i).setAttributeValue("proj_logsamp_seqnum", 
						getCommissionBFromGroup(rvo.getPk_samplegroup(), bvo == null ? null : bvo.getPk_task_h()));

				// project name
				allParaBList.get(i).setAttributeValue("project", processData.getProject().getAttributeValue("name"));

				// 主键
				allParaBList.get(i).setAttributeValue("seq_num", pkList.get(i));
				// 外键
				allParaBList.get(i).setAttributeValue("task_seq_num", utils.getLIMSPKByNCPK(fatherPk, TaskBVO.class));
				// 主键
				allParaBList.get(i).setAttributeValue("entry_code", entryCodeList.get(i));
			
		}
		processData.setParaBListMap(sortParaB(allParaBList));
	}

	/**
	 * 获取所有实验前参数
	 * 
	 * @param processData
	 * @return
	 */
	private ISuperVO[] getAllTaskRVO(WriteBackProcessData processData) {
		List<ISuperVO> rsList = new ArrayList<>();
		AggTaskHVO aggvo = processData.getAggTaskHVO();
		ISuperVO[] bvos = aggvo.getChildren(TaskBVO.class);
		if (bvos != null && bvos.length > 0) {
			for (ISuperVO bvo : bvos) {
				TaskBVO cbvo = (TaskBVO) bvo;
				if (cbvo.getPk_task_r() != null && cbvo.getPk_task_r().length > 0) {
					Collections.addAll(rsList, cbvo.getPk_task_r());
				}
			}
		}
		return rsList.toArray(new ISuperVO[0]);
	}

	private String dealRuleType(TaskRVO rvo) {
		if (rvo != null) {
			return dealRuleType(rvo.getStdmaxvalue(), rvo.getStdminvalue());
		}
		return null;
	}

	private String dealRuleType(Object maxValue, Object minValue) {
		/*
		 * 委托单孙表 RuleType 此为对应项，列表如下： 只有最大值：LTE_MAX 只有最小值：GTE_MIN
		 * 最大最小都有：MNLTELTEMX 温湿度：EMPTY GT_MIN MNLTLTEMX
		 */
		if ((maxValue != null && !"-".equals(String.valueOf(maxValue))) && (minValue == null || "-".equals(String.valueOf(minValue)))) {
			return "LET_MAX";
		} else if ((maxValue == null || "-".equals(String.valueOf(maxValue)))
				&& (minValue != null && !"-".equals(String.valueOf(minValue)))) {
			return "GTE_MIN";
		} else if ((maxValue != null && !"-".equals(String.valueOf(maxValue)) && (minValue != null && !"-".equals(String.valueOf(minValue))))) {
			return "MNLTELTEMX";
		} else {
			return "EMPTY";
		}

		//return null;
	}

	/**
	 * 整理成 map<c_proj_task.test_number,List<ParaB>>
	 * 
	 * @param allParaAList
	 * @return
	 */
	private Map<Integer, List<ParaB>> sortParaB(List<ParaB> allParaBList) {
		Map<Integer, List<ParaB>> rsListMap = new HashMap<>();
		for (ParaB parab : allParaBList) {
			Integer taskid = (Integer) parab.getAttributeValue("task_seq_num");
			if (rsListMap.containsKey(taskid)) {
				rsListMap.get(taskid).add(parab);
			} else {
				List<ParaB> paraBList = new ArrayList<>();
				paraBList.add(parab);
				rsListMap.put(taskid, paraBList);
			}
		}
		return rsListMap;
	}

	
    private Integer getCommissionBFromGroup(String pk_simpleGroup,String pk_task_h) throws DAOException {
    	Integer pkSimple= null;
		if(pk_simpleGroup!=null && pk_task_h != null){
			String sql = " select cb.PK_COMMISSION_B from QC_COMMISSION_B cb "
						+" left join QC_TASK_H th on cb.PK_COMMISSION_H = th.PK_COMMISSION_H "
						+" where cb.PK_SAMPLEGROUP = '"+pk_simpleGroup+"' and th.PK_task_H = '"+pk_task_h+"' and cb.dr = 0 ";
			String pk_commission_b = (String)baseDao.executeQuery(sql, new ColumnProcessor());
			Object pkSimpleObj = utils.getLIMSPKByNCPK(pk_commission_b, CommissionBVO.class);
			try{
				pkSimple = Integer.valueOf(pkSimpleObj.toString());
			}catch(Exception e){
				pkSimple = null;
			}
		}
		return pkSimple;
	}
    
    private List<ParaB> initWriteBackList(int size) {
		List<ParaB> rsList = new ArrayList<>();
		
		for(int i = 0;i < size ;i++){
			rsList.add(new ParaB());
		}
		
		return rsList;
	}
}
