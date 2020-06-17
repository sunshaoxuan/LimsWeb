package nc.ui.qcco.qcitemvalue.refmodel;

import nc.ui.bd.ref.AbstractRefModel;

public class ItemvalueRefModel extends AbstractRefModel {

	public ItemvalueRefModel() {
		super();
		init();
	}
	
	private void init(){
	
		setRefNodeName("ֵ��ֵ");
		setRefTitle("ֵ��ֵ");
		setFieldCode(new String[] {
		"code",
		"name"
				});
		setFieldName(new String[] {
		"����",
		"����"
				});
		setHiddenFieldCode(new String[] {
		"pk_itemvalue",
		"pk_valueitem",
		"pk_bizpk",
		"itemvalue",
		"valuestring",
		"pk_group",
		"pk_org",
		"pk_org_v",
		"creator",
		"creationtime",
		"modifier",
		"modifiedtime",
		"maketime",
		"lastmaketime",
		"dmakedate"
			});
		setPkFieldCode("pk_itemvalue");
		setWherePart("1=1 and isnull(dr,0)=0");
		setTableName("qc_itemvalue");
		setRefCodeField("code");
		setRefNameField("name");
	
	}
	
}