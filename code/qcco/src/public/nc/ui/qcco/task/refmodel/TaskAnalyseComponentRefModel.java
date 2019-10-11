package nc.ui.qcco.task.refmodel;



import nc.ui.bd.ref.AbstractRefModel;

import org.apache.commons.lang.StringUtils;

public class TaskAnalyseComponentRefModel extends AbstractRefModel {
	

	public TaskAnalyseComponentRefModel() {
		super();
		this.setTableName("NC_TEST_AFTER");
		this.setMutilLangNameRef(false);
	}

	public java.lang.String[] getFieldCode() {
		return new String[] { "test_after_code", "test_after_name" };
	}

	public java.lang.String[] getFieldName() {
		return new String[] { "参数项编码", "参数项名称" };
	}

	public java.lang.String[] getHiddenFieldCode() {
		return new String[] { "PK_TEST_AFTER" };
	}

	public java.lang.String getPkFieldCode() {
		return "PK_TEST_AFTER";
	}

	public java.lang.String getOrderPart() {
		return "test_after_code";
	}

	public int getDefaultFieldCount() {
		return 2;
	}

	public java.lang.String getRefTitle() {
		return "请选择参数项";
	}
	@Override
	public String getRefNameField() {
		return "test_after_name";
	}


	protected String getSql(String strPatch, String[] strFieldCode, String[] hiddenFields, String strTableName,
			String strWherePart, String strGroupField, String strOrderField) {
		return "select distinct test_after_code,test_after_name,PK_TEST_AFTER from NC_TEST_AFTER";
	}

	
}
