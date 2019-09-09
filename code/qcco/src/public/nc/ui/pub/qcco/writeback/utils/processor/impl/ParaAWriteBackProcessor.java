package nc.ui.pub.qcco.writeback.utils.processor.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.mozilla.javascript.edu.emory.mathcs.backport.java.util.Collections;

import nc.ui.pub.qcco.writeback.utils.WriteBackProcessData;
import nc.ui.pub.qcco.writeback.utils.LIMSVO.ParaA;
import nc.ui.pub.qcco.writeback.utils.common.CommonUtils;
import nc.ui.pub.qcco.writeback.utils.mapping.FirstWriteBackStaticMaping;
import nc.ui.pub.qcco.writeback.utils.processor.IFirstWriteBackProcessor;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.qcco.commission.AggCommissionHVO;
import nc.vo.qcco.commission.CommissionBVO;
import nc.vo.qcco.commission.CommissionRVO;

/**
 * 实验前参数回写
 * 
 * @author 91967
 * 
 */
public class ParaAWriteBackProcessor implements IFirstWriteBackProcessor {

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
		// 获取所有的实验前参数VO
		ISuperVO[] superVOs = getAllCommissionRVO(processData);
		Collections.addAll(srcDataList, superVOs);
		if (null == srcDataList || srcDataList.size() <= 0) {
			return;
		}

		// 需要回写的LIMS数据
		// LIMS 实验前参数
		List<ParaA> allParaAList = initWriteBackList(srcDataList.size());

		// 预申请pk
		List<Integer> pkList = utils.getPrePk(CommissionRVO.class, srcDataList.size());
		//处理ENTRY_CODE(选取C_PROJ_PARA_A的最大值)
        //预申请ENTRY_CODE
        List<Integer> entryCodeList = utils.getPrePk("entry_code","c_proj_para_a",srcDataList.size());
		
		
		// 进行列循环
		for (Entry<String, String> map : FirstWriteBackStaticMaping.GRAND_BEFORE_MAPPING.entrySet()) {
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
					Object realValue = utils.getRealValue(unDofieldValue, fieldName, CommissionRVO.class);
					for (String field : fields) {
						allParaAList.get(i).setAttributeValue(field, realValue);
					}
				}
			}
		}

		for (int i = 0; i < srcDataList.size(); i++) {
			// 写入主键和父主键(只支持单主键,要多主键,需要参考上面的字段添加逻辑)

			// 获取上层的主键:
			String fatherPk = (String) (((ISuperVO) srcDataList.get(i)).getAttributeValue("pk_commission_b"));

			// 处理委托单子表字段需要写到孙表
			for (String limsField : FirstWriteBackStaticMaping.BODY_SIMPLE_2_CHILDREN_MAPPING.keySet()) {
				// 上层的NCVO 需要回写到子表的字段
				String ncField = FirstWriteBackStaticMaping.BODY_SIMPLE_2_CHILDREN_MAPPING.get(limsField);
				// 获取上层的数据
				ISuperVO fatherObj = (ISuperVO) utils.getNCObjByPK(fatherPk, CommissionBVO.class);

				// 获取需要回写的值
				Object oldFieldValue = fatherObj == null ? null : fatherObj.getAttributeValue(ncField);
				// 处理参照
				Object realValue = utils.getRealValue(oldFieldValue, ncField, CommissionBVO.class);

				allParaAList.get(i).setAttributeValue(utils.getWriteBackFields(new String[] { limsField })[0], realValue);
			}
			// 处理RULE_TYPE的值
			CommissionRVO rvo = ((CommissionRVO) srcDataList.get(i));
			allParaAList.get(i).setAttributeValue("rule_type", dealRuleType(rvo));
			allParaAList.get(i).setAttributeValue("analysis_version", 1);
			// 主键
			allParaAList.get(i).setAttributeValue("seq_num", pkList.get(i));
			// 外键
			allParaAList.get(i).setAttributeValue("proj_logsamp_seqnum", utils.getLIMSPKByNCPK(fatherPk, CommissionBVO.class));
			// project name
			allParaAList.get(i).setAttributeValue("project", processData.getProject().getAttributeValue("name"));
			// entry_code
			allParaAList.get(i).setAttributeValue("entry_code", entryCodeList.get(i));

		}
		processData.setParaAListMap(sortParaA(allParaAList));
	}

	private List<ParaA> initWriteBackList(int size) {
		List<ParaA> rsList = new ArrayList<>();
		
		for(int i = 0;i < size ;i++){
			rsList.add(new ParaA());
		}
		
		return rsList;
	}

	/**
	 * 整理成 map<c_proj_login_sample.seq_num,List<ParaA>>
	 * 
	 * @param allParaAList
	 * @return
	 */
	private Map<Integer, List<ParaA>> sortParaA(List<ParaA> allParaAList) {
		Map<Integer, List<ParaA>> rsListMap = new HashMap<>();
		for (ParaA paraa : allParaAList) {
			Integer sampleId = (Integer) paraa.getAttributeValue("proj_logsamp_seqnum");
			if (rsListMap.containsKey(sampleId)) {
				rsListMap.get(sampleId).add(paraa);
			} else {
				List<ParaA> paraAList = new ArrayList<>();
				paraAList.add(paraa);
				rsListMap.put(sampleId, paraAList);
			}
		}
		return rsListMap;
	}

	/**
	 * 获取所有的实验前参数
	 * 
	 * @param processData
	 * @return
	 */
	private ISuperVO[] getAllCommissionRVO(WriteBackProcessData processData) {
		List<ISuperVO> rsList = new ArrayList<>();
		AggCommissionHVO aggvo = processData.getAggCommissionHVO();
		ISuperVO[] bvos = aggvo.getChildren(CommissionBVO.class);
		if (bvos != null && bvos.length > 0) {
			for (ISuperVO bvo : bvos) {
				CommissionBVO cbvo = (CommissionBVO) bvo;
				if (cbvo.getPk_commission_r() != null && cbvo.getPk_commission_r().length > 0) {
					Collections.addAll(rsList, cbvo.getPk_commission_r());
				}
			}
		}
		return rsList.toArray(new ISuperVO[0]);
	}

	/**
	 * RuleType 字段
	 * 
	 * @param rvo
	 * @return
	 */
	private String dealRuleType(CommissionRVO rvo) {
		if (rvo != null) {
			return dealRuleType(rvo.getStdmaxvalue(), rvo.getStdminvalue());
		}
		return null;
	}

	private String dealRuleType(Object maxValue, Object minValue) {
		/*
		 * 委托单孙表 RuleType 此为对应项，列表如下： 
		 * 针对number数据类型，只有max_value有数据时，rule_type数据为LET_MAX，
		 * 只有min_value有数据时，rule_type数据为GET_MIN，
		 *  max_value和min_value都有数据时，rule_type数据为MNLTELTEMX，
		 *  component列的数据为“温度”或者“湿度”时，rule_type数据为EMPTY，
		 *  number类型的其他情况，rule_type数据为EMPTY
		 */
		if ((maxValue != null && !"-".equals(String.valueOf(maxValue))) && (minValue == null || "-".equals(String.valueOf(minValue)))) {
			return "LET_MAX";
		} else if ((maxValue == null || "-".equals(String.valueOf(maxValue)))
				&& (minValue != null && !"-".equals(String.valueOf(minValue)))) {
			return "GTE_MIN";
		} else if ((maxValue != null && !"-".equals(String.valueOf(maxValue)) && (minValue != null && !"-".equals(String.valueOf(minValue))))) {
			return "MNLTELTEMX";
		} else{
			return "EMPTY";
		}

		//return null;
	}

}
