package nc.ui.qcco.qcproductserial.refmodel;

import nc.ui.bd.ref.AbstractRefModel;

public class ProductserialRefModel extends AbstractRefModel {

	public ProductserialRefModel() {
		super();
		init();
	}
	
	private void init(){
	
		setRefNodeName("��Ʒϵ��");
		setRefTitle("��Ʒϵ��");
		setFieldCode(new String[] {
		"code",
		"name"
				});
		setFieldName(new String[] {
		"����",
		"����"
				});
		setHiddenFieldCode(new String[] {
		"pk_productserial",
		"creator",
		"creationtime",
		"modifier",
		"modifiedtime",
		"maketime",
		"lastmaketime",
		"pk_category",
		"pk_group",
		"pk_org",
		"pk_org_v"
			});
		setPkFieldCode("pk_productserial");
		setWherePart("1=1 and isnull(dr,0)=0");
		setTableName("qc_productserial");
		setRefCodeField("code");
		setRefNameField("name");
	
	}
	
}