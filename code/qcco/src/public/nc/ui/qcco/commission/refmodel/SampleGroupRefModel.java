package nc.ui.qcco.commission.refmodel;

import nc.ui.bd.ref.AbstractRefModel;

public class SampleGroupRefModel extends AbstractRefModel {
	public SampleGroupRefModel() {
		super();
		this.setTableName("NC_SAMPLE_GROUP");
		this.setMutilLangNameRef(false);
	}

	public java.lang.String[] getFieldCode() {
		return new String[] { "NC_SAMPLE_CODE" };
	}

	public java.lang.String[] getFieldName() {
		return new String[] { "��Ʒ������" };
	}

	public java.lang.String[] getHiddenFieldCode() {
		return new String[] { "PK_SAMPLE_GROUP","NC_SAMPLE_NAME" };
	}

	public java.lang.String getPkFieldCode() {
		return "PK_SAMPLE_GROUP";
	}

	public java.lang.String getOrderPart() {
		return "NC_SAMPLE_CODE";
	}

	public int getDefaultFieldCount() {
		return 1;
	}

	public java.lang.String getRefTitle() {
		return "��ѡ����Ʒ���";
	}
	
	@Override
	public String getRefNameField() {
		return "NC_SAMPLE_CODE";
	}

	protected String getSql(String strPatch, String[] strFieldCode,
			String[] hiddenFields, String strTableName, String strWherePart,
			String strGroupField, String strOrderField) {
		return "select NC_SAMPLE_NAME, PK_SAMPLE_GROUP from NC_SAMPLE_GROUP WHERE ISENABLE=1 ORDER BY NC_SAMPLE_CODE";
	}
}
