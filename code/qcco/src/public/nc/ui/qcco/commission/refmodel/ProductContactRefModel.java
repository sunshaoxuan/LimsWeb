package nc.ui.qcco.commission.refmodel;

import nc.ui.bd.ref.AbstractRefModel;

public class ProductContactRefModel extends AbstractRefModel {
	public ProductContactRefModel() {
		super();
		this.setTableName("NC_BASPROD_CONTACT");
		this.setMutilLangNameRef(false);
	}

	public java.lang.String[] getFieldCode() {
		return new String[] { "NC_BASPRODCONTACT_CODE", "NC_BASPRODCONTACT_NAME" };
	}

	public java.lang.String[] getFieldName() {
		return new String[] { "�������ͱ���", "������������" };
	}

	public java.lang.String[] getHiddenFieldCode() {
		return new String[] { "PK_BASPROD_CONTACT" };
	}

	public java.lang.String getPkFieldCode() {
		return "PK_BASPROD_CONTACT";
	}

	public java.lang.String getOrderPart() {
		return "NC_BASPRODCONTACT_CODE";
	}

	public int getDefaultFieldCount() {
		return 2;
	}

	public java.lang.String getRefTitle() {
		return "��ѡ�񴥵�����";
	}

	protected String getSql(String strPatch, String[] strFieldCode, String[] hiddenFields, String strTableName,
			String strWherePart, String strGroupField, String strOrderField) {
		return "select TRIM(NC_BASPRODCONTACT_CODE) NC_BASPRODCONTACT_CODE, NC_BASPRODCONTACT_NAME, PK_BASPROD_CONTACT from NC_BASPROD_CONTACT";
	}
}
