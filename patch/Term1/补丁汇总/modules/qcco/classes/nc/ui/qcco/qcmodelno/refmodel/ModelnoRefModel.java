package nc.ui.qcco.qcmodelno.refmodel;

import nc.ui.bd.ref.AbstractRefModel;

public class ModelnoRefModel extends AbstractRefModel {

	public ModelnoRefModel() {
		super();
		init();
	}
	
	private void init(){
	
		setRefNodeName("����");
		setRefTitle("����");
		setFieldCode(new String[] {
		"code",
		"name"
				});
		setFieldName(new String[] {
		"����",
		"����"
				});
		setHiddenFieldCode(new String[] {
		"pk_modelno",
		"pk_group",
		"pk_org",
		"creator",
		"creationtime",
		"modifier",
		"modifiedtime",
		"pk_productserial",
		"pk_entstandard"
			});
		setPkFieldCode("pk_modelno");
		setWherePart("1=1 and isnull(dr,0)=0");
		setTableName("qc_modelno");
		setRefCodeField("code");
		setRefNameField("name");
	
	}
	
}