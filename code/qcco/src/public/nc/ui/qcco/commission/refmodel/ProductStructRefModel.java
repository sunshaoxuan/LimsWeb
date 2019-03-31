package nc.ui.qcco.commission.refmodel;

import nc.ui.bd.ref.AbstractRefModel;

public class ProductStructRefModel extends AbstractRefModel {
	public ProductStructRefModel() {
		super();
		this.setTableName("NC_BASPROD_STRUCT");
		this.setMutilLangNameRef(false);
	}

	public java.lang.String[] getFieldCode() {
		return new String[] { "NC_BASPRODSTRUCT_CODE", "NC_BASPRODSTRUCT_NAME" };
	}

	public java.lang.String[] getFieldName() {
		return new String[] { "�ṹ���ͱ���", "�ṹ��������" };
	}

	public java.lang.String[] getHiddenFieldCode() {
		return new String[] { "PK_BASPROD_STRUCT" };
	}

	public java.lang.String getPkFieldCode() {
		return "PK_BASPROD_STRUCT";
	}

	public java.lang.String getOrderPart() {
		return "NC_BASPRODSTRUCT_CODE";
	}

	public int getDefaultFieldCount() {
		return 2;
	}

	public java.lang.String getRefTitle() {
		return "��ѡ��ṹ����";
	}

	protected String getSql(String strPatch, String[] strFieldCode, String[] hiddenFields, String strTableName,
			String strWherePart, String strGroupField, String strOrderField) {
		return "select TRIM(NC_BASPRODSTRUCT_CODE) NC_BASPRODSTRUCT_CODE, NC_BASPRODSTRUCT_NAME, PK_BASPROD_STRUCT from NC_BASPROD_STRUCT";
	}
}
