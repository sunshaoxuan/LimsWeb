package nc.vo.qcco.qcvaluetype;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <b> �˴���Ҫ�������๦�� </b>
 * <p>
 *   �˴�����۵�������Ϣ
 * </p>
 *  ��������:2018-12-5
 * @author yonyouBQ
 * @version NCPrj ??
 */
 
public class ValueListBVO extends SuperVO {
	
/**
*�ӱ�����
*/
public String pk_valueitem;
/**
*ȡֵ��Ŀ����
*/
public String code;
/**
*ȡֵ��Ŀ����
*/
public String name;
/**
*ȡֵ��Ŀ����
*/
public Integer datatype;
/**
*�Ƿ��б�
*/
public UFBoolean islist;
/**
*�к�
*/
public String rowno;
/**
*�ϲ㵥������
*/
public String pk_valuetype;
/**
*ʱ���
*/
public UFDateTime ts;
    
    
/**
* ���� pk_valueitem��Getter����.���������ӱ�����
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getPk_valueitem() {
return this.pk_valueitem;
} 

/**
* ����pk_valueitem��Setter����.���������ӱ�����
* ��������:2018-12-5
* @param newPk_valueitem java.lang.String
*/
public void setPk_valueitem ( String pk_valueitem) {
this.pk_valueitem=pk_valueitem;
} 
 
/**
* ���� code��Getter����.��������ȡֵ��Ŀ����
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getCode() {
return this.code;
} 

/**
* ����code��Setter����.��������ȡֵ��Ŀ����
* ��������:2018-12-5
* @param newCode java.lang.String
*/
public void setCode ( String code) {
this.code=code;
} 
 
/**
* ���� name��Getter����.��������ȡֵ��Ŀ����
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getName() {
return this.name;
} 

/**
* ����name��Setter����.��������ȡֵ��Ŀ����
* ��������:2018-12-5
* @param newName java.lang.String
*/
public void setName ( String name) {
this.name=name;
} 
 
/**
* ���� datatype��Getter����.��������ȡֵ��Ŀ����
*  ��������:2018-12-5
* @return java.lang.Integer
*/
public Integer getDatatype() {
return this.datatype;
} 

/**
* ����datatype��Setter����.��������ȡֵ��Ŀ����
* ��������:2018-12-5
* @param newDatatype java.lang.Integer
*/
public void setDatatype ( Integer datatype) {
this.datatype=datatype;
} 
 
/**
* ���� islist��Getter����.���������Ƿ��б�
*  ��������:2018-12-5
* @return nc.vo.pub.lang.UFUFBoolean
*/
public UFBoolean getIslist() {
return this.islist;
} 

/**
* ����islist��Setter����.���������Ƿ��б�
* ��������:2018-12-5
* @param newIslist nc.vo.pub.lang.UFBoolean
*/
public void setIslist ( UFBoolean islist) {
this.islist=islist;
} 
 
/**
* ���� rowno��Getter����.���������к�
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getRowno() {
return this.rowno;
} 

/**
* ����rowno��Setter����.���������к�
* ��������:2018-12-5
* @param newRowno java.lang.String
*/
public void setRowno ( String rowno) {
this.rowno=rowno;
} 
 
/**
* ���� �����ϲ�������Getter����.���������ϲ�����
*  ��������:2018-12-5
* @return String
*/
public String getPk_valuetype(){
return this.pk_valuetype;
}
/**
* ���������ϲ�������Setter����.���������ϲ�����
* ��������:2018-12-5
* @param newPk_valuetype String
*/
public void setPk_valuetype(String pk_valuetype){
this.pk_valuetype=pk_valuetype;
} 
/**
* ���� ����ʱ�����Getter����.��������ʱ���
*  ��������:2018-12-5
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTs() {
return this.ts;
}
/**
* ��������ʱ�����Setter����.��������ʱ���
* ��������:2018-12-5
* @param newts nc.vo.pub.lang.UFDateTime
*/
public void setTs(UFDateTime ts){
this.ts=ts;
} 
     
    @Override
    public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("qcco.valuelist");
    }
   }
    