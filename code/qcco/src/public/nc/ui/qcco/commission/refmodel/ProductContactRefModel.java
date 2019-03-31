package nc.ui.qcco.commission.refmodel;

import java.util.Vector;

import nc.ui.bd.ref.AbstractRefModel;
import nc.ui.pub.beans.constenum.DefaultConstEnum;
import nc.ui.pub.beans.constenum.IConstEnum;

public class ProductContactRefModel extends AbstractRefModel {
	private String pk_productserial = "";
	
	public String getPk_productserial() {
		return pk_productserial;
	}

	public void setPk_productserial(String pk_productserial) {
		this.pk_productserial = pk_productserial;
	}

	public ProductContactRefModel() {
		super();
		this.setTableName("NC_BASPROD_CONTACT");
		this.setMutilLangNameRef(false);
	}

	public java.lang.String[] getFieldCode() {
		return new String[] { "NC_BASPRODCONTACT_CODE", "NC_BASPRODCONTACT_NAME" };
	}

	public java.lang.String[] getFieldName() {
		return new String[] { "触点类型编码", "触点类型名称" };
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
		return "请选择触点类型";
	}
	
	@Override
	public Vector getData() {
		Vector data = super.getData();
		if(data.size() > 1){
			return null;
		}
		Vector result = new Vector();
		if(data!=null && data.get(0)!=null && ((Vector)data.get(0)).get(1) != null){
			//把data分解
			Vector data0 = (Vector)data.get(0);
			
			String dataCode = (String)data0.get(0);
			String codes = (String)data0.get(1);
			String pk = (String)data0.get(2);
			String [] codeArray = codes.replaceAll(" ", "").split(",");
			for(String code : codeArray){
				Vector line = new Vector(3);
				line.add(code);
				line.add(code);
				line.add(code);
				
				result.add(line);
			}
		}
		//处理下拉数据
		return result;
	};
	@Override
	public Vector matchData(String field, String value){
		Vector vector = super.matchData(field, value);
		if(vector == null){
			vector = new Vector();
			Vector line = new Vector(1,3);
			line.add(value);
			line.add(value);
			line.add(value);
			vector.add(line);
		}
		return vector;
	}
	
	protected String getSql(String strPatch, String[] strFieldCode, String[] hiddenFields, String strTableName,
			String strWherePart, String strGroupField, String strOrderField) {
		String sql = " SELECT TRIM(NC_BASPRODCONTACT_CODE) NC_BASPRODCONTACT_CODE, "
				+ " NC_BASPRODCONTACT_NAME, contact.PK_BASPROD_CONTACT PK_BASPROD_CONTACT"
				+ " FROM NC_BASPROD_CONTACT contact "
				+ " left join NC_SAMPLE_INFO info on info.PK_BASPROD_CONTACT = contact.PK_BASPROD_CONTACT ";
				if(pk_productserial != null&&!pk_productserial.equals("")){
					sql +=" where info.PK_SAMPLE_INFO = '"+pk_productserial+"' ";
				}
		return sql;
	}
}
