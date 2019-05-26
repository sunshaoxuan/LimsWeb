package nc.ui.qcco.task.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.qcco.commission.CommissionBVO;
import nc.vo.qcco.commission.CommissionHVO;
import nc.vo.qcco.task.TaskBVO;
import nc.vo.qcco.task.TaskRVO;
import nc.vo.qcco.task.TaskSVO;

public class WriteBackLimsUtils {
	public final static String[] LIMS_PK_HEAD = new String[] { "PROJECT", "NAME" }; // project.NAME

	public final static String[] LIMS_PK_SAMPLE = new String[] { "C_PROJ_LOGIN_SAMPLE", "SEQ_NUM" }; // C_PROJ_LOGIN_SAMPLE.SEQ_NUM
	public final static String[] LIMS_FK_SAMPLE = new String[] { "C_PROJ_LOGIN_SAMPLE", "PROJECT" };// C_PROJ_LOGIN_SAMPLE.PROJECT

	public final static String[] LIMS_PK_BEFORE = new String[] { "C_PROJ_PARA_A", "SEQ_NUM" }; // C_PROJ_PARA_A.SEQ_NUM
	public final static String[] LIMS_FK_BEFORE = new String[] { "C_PROJ_PARA_A", "PROJECT" }; // C_PROJ_PARA_A.PROJECT

	public final static String[] LIMS_PK_TASK = new String[] { "c_proj_task", "SEQ_NUM" }; // c_proj_task.SEQ_NUM
	public final static String[] LIMS_FK_TASK = new String[] { "c_proj_task", "PROJECT" }; // c_proj_task.PROJECT

	public final static String[] LIMS_PK_CONDITION = new String[] { "result", "RESULT_NUMBER" }; // result.RESULT_NUMBER
	public final static String[] LIMS_FK_CONDITION_TASK = new String[] { "result", "TEST_NUMBER" }; // result.TEST_NUMBER
	public final static String[] LIMS_FK_CONDITION_SAMPLE = new String[] { "result", "SAMPLE_NUMBER" }; // result.SAMPLE_NUMBER

	public final static String[] LIMS_PK_AFTER = new String[] { "c_proj_task_para_b", "SEQ_NUM" }; // c_proj_task_para_b.SEQ_NUM
	public final static String[] LIMS_FK_AFTER = new String[] { "c_proj_task_para_b", "TASK_SEQ_NUM" };// c_proj_task_para_b.TASK_SEQ_NUM

	// ί�е�
	private Map<String, String> headMapping; // qc_commission_h
	private Map<String, String> bodySampleMapping; // qc_commission_b
	private Map<String, String> grandBeforeMapping; // qc_commission_r

	// ����
	private Map<String, String> bodyTaskMapping; // qc_task_b
	private Map<String, String> grandConditionMapping; // qc_task_s
	private Map<String, String> grandAfterMapping; // qc_task_r

	/**
	 * ȡί�е�/���񵥻�дLIMS�Ĳ���SQL
	 * 
	 * @param pk_commission_h
	 * @return
	 * @throws BusinessException
	 */
	public String[] getInsertLIMSSQL(String pk_commission_h) throws BusinessException {
		List<String> sqlList = new ArrayList<String>();

		String[] lists = null;
		String headCond = "pk_commission_h = '" + pk_commission_h + "'";
		// ί�е���ͷ
		lists = getInsertSQLByMap(getHeadMapping(), CommissionHVO.class, headCond);
		sqlList.add(getHeadInsertSQL(lists));

		// ��Ʒ��
		lists = getInsertSQLByMap(getHeadMapping(), CommissionBVO.class, headCond);
		sqlList.add(getSampleInsertSQL(lists));

		// ʵ��ǰ����
		String subCondition = "pk_commission_b in (select pk_commission_b from qc_commission_b where " + headCond + ")";
		lists = getInsertSQLByMap(getHeadMapping(), CommissionBVO.class, subCondition);
		sqlList.add(getBeforeInsertSQL(lists));

		// ������
		subCondition = "pk_task_h in (select pk_task_h from qc_task_h where " + headCond + ")";
		//
		lists = getInsertSQLByMap(getHeadMapping(), TaskBVO.class, subCondition);
		sqlList.add(getTaskInsertSQL(lists));

		// ʵ������
		subCondition = "pk_task_b in (select pk_task_b from qc_task_b where "
				+ " pk_task_h in (select pk_task_h from qc_task_h where " + headCond + " ))";
		lists = getInsertSQLByMap(getHeadMapping(), TaskSVO.class, subCondition);
		sqlList.add(getConditionInsertSQL(lists));

		// ʵ������
		subCondition = "pk_task_b in (select pk_task_b from qc_task_b where "
				+ " pk_task_h in (select pk_task_h from qc_task_h where " + headCond + " ))";
		lists = getInsertSQLByMap(getHeadMapping(), TaskRVO.class, subCondition);
		sqlList.add(getAfterInsertSQL(lists));

		return sqlList.toArray(new String[0]);
	}

	private String getAfterInsertSQL(String[] lists) {
		// TODO �ԄӮa���ķ��� Stub
		return null;
	}

	private String getConditionInsertSQL(String[] lists) {
		// TODO �ԄӮa���ķ��� Stub
		return null;
	}

	private String getTaskInsertSQL(String[] lists) {
		// TODO �ԄӮa���ķ��� Stub
		return null;
	}

	private String getBeforeInsertSQL(String[] lists) {
		// TODO �ԄӮa���ķ��� Stub
		return null;
	}

	private String getSampleInsertSQL(String[] lists) {
		// TODO �ԄӮa���ķ��� Stub
		return null;
	}

	private String getHeadInsertSQL(String[] lists) {
		String insertSQL = "INSERT INTO PROJECT";
		return null;
	}

	/**
	 * ��ȡInsert���Ƭ��
	 * 
	 * @param fieldMap
	 * @param clazz
	 * @param pkname
	 * @param pkvalue
	 * @return fieldValues[0]: �ֶ���Ƭ��<br />
	 *         fieldValues[1-n]��ֵƬ��
	 * @throws BusinessException
	 */
	private String[] getInsertSQLByMap(Map<String, String> fieldMap, Class<?> clazz, String condition)
			throws BusinessException {
		//����ĳ���ֶε�ֵ,һ�����ݶ�Ӧarray�е�һ��
		String[] fieldValues = null;
		IUAPQueryBS query = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		Collection<?> srcData = query.retrieveByClause(clazz, condition);
		//����ƴ���ֶ���
		String insertFields = "";
		//������ѭ��
		for (Entry<String, String> map : fieldMap.entrySet()) {
			String fieldName = map.getKey();

			String field = null;
			field = getWriteBackFields( map.getValue());
			/*if (map.getValue().contains(";")) {
				fields = getWriteBackFields(map.getValue().split(";"));
			} else {
				fields = getWriteBackFields(new String[] { map.getValue() });
			}*/
			//times�Ǵ���һ�Զ��ϵ��,Ŀǰ��Ϊ1,
			//int times = fields.length;
			/*for (String field : fields) {
				insertFields += field + ",";
			}*/
			insertFields = field;
			if (srcData != null && srcData.size() > 0) {
				fieldValues = new String[srcData.size() + 1];
				int row = 1;
				for (Object data : srcData) {
					ISuperVO vo = (ISuperVO) data;
					String fieldValue = String.valueOf(vo.getAttributeValue(fieldName));
					if(fieldValues[row]!=null){
						fieldValues[row] += "'" + fieldValue + "',";
					}else{
						fieldValues[row] = "'" + fieldValue + "',";
					}
					
					row++;
				}
			}
		}

		if (srcData != null && srcData.size() > 0) {
			fieldValues[0] = insertFields;
		}
		return fieldValues;
	}

	private String getWriteBackFields(String field) {
		/*List<String> fieldList = new ArrayList<String>();
		for (String field : splitFields) {
			fieldList.add(field.split("\\.")[1]);
		}*/
		return field.split("\\.")[1];
	}

	public Map<String, String> getHeadMapping() {
		if (headMapping == null) {
			headMapping = new HashMap<String, String>();

			headMapping.put("pk_commissiontype", "project.template");// ί�е�����
			headMapping.put("billno", "project.name");// ί�е����
			headMapping.put("billname", "project.title");// ί�е�����
			headMapping.put("pk_owner", "project.customer");// ί�е�λ
			headMapping.put("pk_dept", "project.c_user_department");// ����
			headMapping.put("pk_payer", "project.t_source_customer");// ���ѵ�λ
			headMapping.put("contract", "project.customer_contact");// ��ϵ��
			headMapping.put("cuserid", "project.owner;project.created_by;");// ������
			headMapping.put("email", "project.c_email_address");// Email
			headMapping.put("teleno", "project.c_phone_number");// ��ϵ�绰
			headMapping.put("pk_maincategory", "project.c_product_type");// ��Ʒ����
			headMapping.put("pk_subcategory", "project.c_prod_type_c1");// ��������
			headMapping.put("pk_lastcategory", "project.c_prod_type_c2");// ��������
			headMapping.put("reportformat", "project.c_coa_format");// �����ʽ
			headMapping.put("reportlang", "project.c_coa_language");// ��������
			headMapping.put("managersendflag", "project.c_mail_lab_approval");// ʵ����������˷����ʼ�
			headMapping.put("taskbeginsendflag", "project.c_mail_task_end");// ����ʼ�����ʼ�
			headMapping.put("taskendsendflag", "project.c_mail_task_start");// ������������ʼ�
			headMapping.put("reportsendflag", "project.c_mail_coa_sign");// ����ǩ�������ʼ�
			headMapping.put("savetotemplateflag", "project.c_is_template");// �Ƿ񱣴�Ϊģ��
			headMapping.put("receiptsendflag", "project.c_mail_charge");// �Ʒѵ����͸��ͻ��ʼ�����
			headMapping.put("quotaionsendflag", "project.c_mail_quotes");// ���۵����͸��ͻ��ʼ�����
			headMapping.put("testaim", "project.c_test_purpose");// ����Ŀ��
			headMapping.put("progressneed", "project.description");// ����Ҫ��
			headMapping.put("sampledealtype", "project.c_retain_handle");// �����Ʒ����
			headMapping.put("productproperty", "project.c_product_property");// ��Ʒ����
			headMapping.put("customername", "project.c_terminal_client");// �ͻ�����
			headMapping.put("customertype", "project.c_client_type");// �ͻ�����
			headMapping.put("testrequirement", "project.c_product_requirement");// ��������
			headMapping.put("checkingproperty", "project.c_checking_property");// �������
			headMapping.put("productline", "project.c_product_line");// ��������
			headMapping.put("batchnumber", "project.c_batch_number");// ��������
			headMapping.put("productdate", "project.c_product_date");// ��������
			headMapping.put("batchserial", "project.c_batch_serial");// ��������
			headMapping.put("identificationtype", "project.c_identification_type");// ��Ʒ��������
			headMapping.put("certificationtype", "project.c_certification_type");// ��֤����
			headMapping.put("itemnumber", "project.c_item_number");// ��Ŀ��
			headMapping.put("modifiedtime", "project.date_createdproject.date_updated");// �Ƶ�ʱ��
		}

		return headMapping;
	}

	public Map<String, String> getBodySampleMapping() {
		if (bodySampleMapping == null) {
			bodySampleMapping = new HashMap<String, String>();

			bodySampleMapping.put("pk_productserial", "C_PROJ_LOGIN_SAMPLE.product_series");// ��Ʒϵ��
			bodySampleMapping.put("pk_enterprisestandard",
					"C_PROJ_LOGIN_SAMPLE.product_standard;c_proj_para_a.product_standard;");// ��ҵ��׼
			bodySampleMapping.put("typeno", "C_PROJ_LOGIN_SAMPLE.prodname;");// ����ͺ�
			bodySampleMapping.put("pk_productspec",
					"C_PROJ_LOGIN_SAMPLE.production_spec;c_proj_para_a.production_spec;");// ����
			bodySampleMapping.put("pk_structuretype",
					"C_PROJ_LOGIN_SAMPLE.structure_type;C_PROJ_PARA_A.structure_type;");// �ṹ����
			bodySampleMapping.put("contacttype", "C_PROJ_LOGIN_SAMPLE.contact_type");// ��������
			bodySampleMapping.put("quantity", "C_PROJ_LOGIN_SAMPLE.sample_quantity");// ��Ʒ����
			bodySampleMapping.put("manufacturer", "C_PROJ_LOGIN_SAMPLE.manufacturer");// ������
			bodySampleMapping.put("pk_contactbrand", "C_PROJ_LOGIN_SAMPLE.contact_brand");// �����ƺ�
			bodySampleMapping.put("contactmodel", "C_PROJ_LOGIN_SAMPLE.contact_model");// �����ͺ�
			bodySampleMapping.put("productstage", "C_PROJ_LOGIN_SAMPLE.product_stage;C_PROJ_PARA_A.stage;");// �¶�
			bodySampleMapping.put("pk_samplegroup", "C_PROJ_LOGIN_SAMPLE.sample_group");// ��Ʒ���
			bodySampleMapping.put("otherinfo", "C_PROJ_LOGIN_SAMPLE.other_req");// ������Ϣ
		}

		return bodySampleMapping;
	}

	public Map<String, String> getGrandBeforeMapping() {
		if (grandBeforeMapping == null) {
			grandBeforeMapping = new HashMap<String, String>();

			grandBeforeMapping.put("analysisname", "C_PROJ_PARA_A.analysis");// ʵ��ǰ��������
			grandBeforeMapping.put("pk_samplegroup", "C_PROJ_PARA_A.sample_group");// ��Ʒ���
			grandBeforeMapping.put("pk_component", "C_PROJ_PARA_A.component");// ������
			grandBeforeMapping.put("stdmaxvalue", "C_PROJ_PARA_A.max_value");// ���ֵ
			grandBeforeMapping.put("stdminvalue", "C_PROJ_PARA_A.min_value");// ��Сֵ
			grandBeforeMapping.put("unitname", "C_PROJ_PARA_A.units");// ��λ
			grandBeforeMapping.put("judgeflag", "C_PROJ_PARA_A.check_spec");// �Ƿ��ж�
			grandBeforeMapping.put("testflag", "C_PROJ_PARA_A.is_added");// �Ƿ����
			grandBeforeMapping.put("productstage", "C_PROJ_PARA_A.stage");// �����¶�

		}

		return grandBeforeMapping;
	}

	public Map<String, String> getBodyTaskMapping() {
		if (bodyTaskMapping == null) {
			bodyTaskMapping = new HashMap<String, String>();

			bodyTaskMapping.put("taskcode", "c_proj_task.task_ID");// ������
			bodyTaskMapping.put("testitem", "c_proj_task.task_reported_name");// ������Ŀ
			bodyTaskMapping.put("pk_testresultname", "c_proj_task.analysis");// ���Խ������
			bodyTaskMapping.put("runorder", "c_proj_task.order_number");// ˳��
			bodyTaskMapping.put("sampleallocation", "c_proj_task.assigned_sample_display");// ��Ʒ����
			bodyTaskMapping.put("samplequantity", "c_proj_task.assigned_sample_quantity");// ��Ʒ����
		}

		return bodyTaskMapping;
	}

	public Map<String, String> getGrandConditionMapping() {
		if (grandConditionMapping == null) {
			grandConditionMapping = new HashMap<String, String>();

			grandConditionMapping.put("pk_testconditionitem", "result.name");// ����������
			grandConditionMapping.put("conditionstatus", "result.status");// ״̬
			grandConditionMapping.put("isoptional", "result.optional");// �Ƿ��ѡ
			grandConditionMapping.put("isallow_out", "result.allow_out");// �Ƿ�ɱ���
			grandConditionMapping.put("textvalue;refvalue;", "result.entry");// ֵ
			grandConditionMapping.put("unit", "result.units");// ��λ
			grandConditionMapping.put("formatted_entry", "result.formatted_entry");// ��ʽ��ֵ
			grandConditionMapping.put("min_limit", "result.min_limit");// ��Сֵ
			grandConditionMapping.put("max_limit", "result.max_limit");// ���ֵ
		}

		return grandConditionMapping;
	}

	public Map<String, String> getGrandAfterMapping() {
		if (grandAfterMapping == null) {
			grandAfterMapping = new HashMap<String, String>();

			grandAfterMapping.put("analysisname", "c_proj_task_para_b.analysis");// ʵ���������
			grandAfterMapping.put("pk_samplegroup", "c_proj_task_para_b.sample_group");// ��Ʒ���
			grandAfterMapping.put("pk_component", "c_proj_task_para_b.component");// ������
			grandAfterMapping.put("stdmaxvalue", "c_proj_task_para_b.max_value");// ���ֵ
			grandAfterMapping.put("stdminvalue", "c_proj_task_para_b.min_value");// ��Сֵ
			grandAfterMapping.put("pk_unit", "c_proj_task_para_b.units");// ��λ
			grandAfterMapping.put("judgeflag", "c_proj_task_para_b.check_spec");// �Ƿ��ж�
			grandAfterMapping.put("testflag", "c_proj_task_para_b.is_added");// �Ƿ����
			grandAfterMapping.put("pk_testtemperature", "c_proj_task_para_b.stage");// �����¶�

		}

		return grandAfterMapping;
	}
}
