package nc.ui.pub.qcco.writeback.utils.processor.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.mozilla.javascript.edu.emory.mathcs.backport.java.util.Collections;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.MapListProcessor;
import nc.jdbc.framework.processor.ResultSetProcessor;
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
 * ʵ��ǰ������д
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
	 * ��ȡInsert���Ƭ��
	 * 
	 * @param processData
	 *            processData
	 * @throws BusinessException
	 */
	private void process(WriteBackProcessData processData) throws BusinessException {

		// NC����
		List<ISuperVO> srcDataList = new ArrayList<>();
		// ��ȡ���е�ʵ��ǰ����VO
		ISuperVO[] superVOs = getAllCommissionRVO(processData);
		Collections.addAll(srcDataList, superVOs);
		if (null == srcDataList || srcDataList.size() <= 0) {
			return;
		}

		// ��Ҫ��д��LIMS����
		// LIMS ʵ��ǰ����
		List<ParaA> allParaAList = initWriteBackList(srcDataList.size());

		// Ԥ����pk
		List<Integer> pkList = utils.getPrePk(CommissionRVO.class, srcDataList.size());
		//����ENTRY_CODE(ѡȡC_PROJ_PARA_A�����ֵ)
        //Ԥ����ENTRY_CODE
        List<Integer> entryCodeList = utils.getPrePk("entry_code","c_proj_para_a",srcDataList.size());
		
		
		// ������ѭ��
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
		//��ȡ��������Ӧ��ʵ��ǰ����������
		Map<String,Integer> numberMap = getGroupParaANum(processData.getAggCommissionHVO().getPrimaryKey());
		//��λ����
		@SuppressWarnings("unchecked")
		Map<String,String> unitMap = (Map<String, String>) NCLocator.getInstance().lookup(IUAPQueryBS.class)
					.executeQuery("select NC_UNITS_NAME,UNIT_CODE from nc_units_type", 
							new ResultSetProcessor() {
								private static final long serialVersionUID = 6620032648507337165L;
								Map<String,String> rsMap = new HashMap<>();
								@Override
								public Object handleResultSet(ResultSet rs) throws SQLException {
									while(rs.next()){
										String key = (rs.getString(1)==null?null:rs.getString(1).replaceAll(" ", ""));
										String value = (rs.getString(2)==null?null:rs.getString(2).replaceAll(" ", ""));
										rsMap.put(key, value);
									}
									return rsMap;
								}
							});
		for (int i = 0; i < srcDataList.size(); i++) {
			// д�������͸�����(ֻ֧�ֵ�����,Ҫ������,��Ҫ�ο�������ֶ�����߼�)

			// ��ȡ�ϲ������:
			String fatherPk = (String) (((ISuperVO) srcDataList.get(i)).getAttributeValue("pk_commission_b"));

			// ����ί�е��ӱ��ֶ���Ҫд�����
			for (String limsField : FirstWriteBackStaticMaping.BODY_SIMPLE_2_CHILDREN_MAPPING.keySet()) {
				// �ϲ��NCVO ��Ҫ��д���ӱ���ֶ�
				String ncField = FirstWriteBackStaticMaping.BODY_SIMPLE_2_CHILDREN_MAPPING.get(limsField);
				// ��ȡ�ϲ������
				ISuperVO fatherObj = (ISuperVO) utils.getNCObjByPK(fatherPk, CommissionBVO.class);

				// ��ȡ��Ҫ��д��ֵ
				Object oldFieldValue = fatherObj == null ? null : fatherObj.getAttributeValue(ncField);
				// �������
				Object realValue = utils.getRealValue(oldFieldValue, ncField, CommissionBVO.class);

				allParaAList.get(i).setAttributeValue(utils.getWriteBackFields(new String[] { limsField })[0], realValue);
			}
			// ����RULE_TYPE��ֵ
			CommissionRVO rvo = ((CommissionRVO) srcDataList.get(i));
			allParaAList.get(i).setAttributeValue("rule_type", dealRuleType(rvo));
			allParaAList.get(i).setAttributeValue("analysis_version", 1);
			// ����
			allParaAList.get(i).setAttributeValue("seq_num", pkList.get(i));
			// ���
			allParaAList.get(i).setAttributeValue("proj_logsamp_seqnum", utils.getLIMSPKByNCPK(fatherPk, CommissionBVO.class));
			// project name
			allParaAList.get(i).setAttributeValue("project", processData.getProject().getAttributeValue("name"));
			// entry_code
			allParaAList.get(i).setAttributeValue("entry_code", entryCodeList.get(i));
			//order_number ��ͬ�����һ�����
			int ordernum = 0;
			if(allParaAList.get(i).getAttributeValue("order_number")!=null){
				ordernum = Integer.parseInt(String.valueOf(allParaAList.get(i).getAttributeValue("order_number")));
			}
			if("B".equalsIgnoreCase(String.valueOf(allParaAList.get(i).getAttributeValue("sample_group")))){
				allParaAList.get(i).setAttributeValue("order_number",ordernum+numberMap.get("A"));
			}else if("C".equalsIgnoreCase(String.valueOf(allParaAList.get(i).getAttributeValue("sample_group")))){
				allParaAList.get(i).setAttributeValue("order_number",ordernum+numberMap.get("A")+numberMap.get("B"));
			}else if("D".equalsIgnoreCase(String.valueOf(allParaAList.get(i).getAttributeValue("sample_group")))){
				allParaAList.get(i).setAttributeValue("order_number",ordernum+numberMap.get("A")+numberMap.get("B")+numberMap.get("C"));
			}
			//��λת�� 2019��10��20��20:28:50
			String unitname = (String)allParaAList.get(i).getAttributeValue("units");
			if(unitname!=null){
				String changeName = unitMap.get(unitname.replaceAll(" ", ""));
				if(changeName!=null){
					allParaAList.get(i).setAttributeValue("units", changeName);
				}
				
			}
		}
		processData.setParaAListMap(sortParaA(allParaAList));
	}

	/**
	 * ��ȡ��������Ӧ��ʵ��ǰ����������
	 * @param pk_commission_h
	 * @return
	 * @throws BusinessException 
	 */
	private Map<String, Integer> getGroupParaANum(String pk_commission_h) throws BusinessException {
		Map<String, Integer> rs = new HashMap<>();
		String sql = " select sg.NC_SAMPLE_NAME sggroup,max(to_number(r.ROWNO)) num from QC_COMMISSION_R  r "
				+" left join QC_COMMISSION_B b on b.PK_COMMISSION_B = r.PK_COMMISSION_B "
				+" left join NC_SAMPLE_GROUP sg on sg.PK_SAMPLE_GROUP = b.PK_SAMPLEGROUP "
				+" where r.PK_COMMISSION_B in (select PK_COMMISSION_B  "
				+" from QC_COMMISSION_B  where PK_COMMISSION_H = '"+pk_commission_h+"')  "
				+" GROUP by sg.NC_SAMPLE_NAME ";
		IUAPQueryBS bs = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		@SuppressWarnings("unchecked")
		List<Map<String,Object>> rsList = 
				(List<Map<String,Object>>)bs.executeQuery(sql, new MapListProcessor());
		if(rsList!=null && rsList.size() > 0){
			for(Map<String,Object> rsMap : rsList){
				if(rsMap!=null && rsMap.get("sggroup")!=null){
					if(rsMap.get("num")!=null){
						rs.put(String.valueOf(rsMap.get("sggroup")), Integer.parseInt(String.valueOf(rsMap.get("num"))));
					}else{
						rs.put(String.valueOf(rsMap.get("sggroup")), 0);
					}
					
				}
			}
		}
		return rs;
	}

	private List<ParaA> initWriteBackList(int size) {
		List<ParaA> rsList = new ArrayList<>();
		
		for(int i = 0;i < size ;i++){
			rsList.add(new ParaA());
		}
		
		return rsList;
	}

	/**
	 * ����� map<c_proj_login_sample.seq_num,List<ParaA>>
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
	 * ��ȡ���е�ʵ��ǰ����
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
	 * RuleType �ֶ�
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
		 * ί�е���� RuleType ��Ϊ��Ӧ��б����£� 
		 * ���number�������ͣ�ֻ��max_value������ʱ��rule_type����ΪLET_MAX��
		 * ֻ��min_value������ʱ��rule_type����ΪGET_MIN��
		 *  max_value��min_value��������ʱ��rule_type����ΪMNLTELTEMX��
		 *  component�е�����Ϊ���¶ȡ����ߡ�ʪ�ȡ�ʱ��rule_type����ΪEMPTY��
		 *  number���͵����������rule_type����ΪEMPTY
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
