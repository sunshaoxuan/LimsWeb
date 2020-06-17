package nc.vo.qcco.qclistdoc;

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
 
public class ListentryBVO extends SuperVO {
	
/**
*�ӱ�����
*/
public String pk_listentry;
/**
*�к�
*/
public String rowno;
/**
*����
*/
public String code;
/**
*����
*/
public String name;
/**
*ֵ
*/
public String value;
/**
*�Ƿ�����
*/
public UFBoolean isorder;
/**
*Ӣ��ֵ
*/
public String envalue;
/**
*����
*/
public String constvalue;
/**
*�ϲ㵥������
*/
public String pk_listdoc;
/**
*ʱ���
*/
public UFDateTime ts;
    
    
/**
* ���� pk_listentry��Getter����.���������ӱ�����
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getPk_listentry() {
return this.pk_listentry;
} 

/**
* ����pk_listentry��Setter����.���������ӱ�����
* ��������:2018-12-5
* @param newPk_listentry java.lang.String
*/
public void setPk_listentry ( String pk_listentry) {
this.pk_listentry=pk_listentry;
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
* ���� code��Getter����.������������
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getCode() {
return this.code;
} 

/**
* ����code��Setter����.������������
* ��������:2018-12-5
* @param newCode java.lang.String
*/
public void setCode ( String code) {
this.code=code;
} 
 
/**
* ���� name��Getter����.������������
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getName() {
return this.name;
} 

/**
* ����name��Setter����.������������
* ��������:2018-12-5
* @param newName java.lang.String
*/
public void setName ( String name) {
this.name=name;
} 
 
/**
* ���� value��Getter����.��������ֵ
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getValue() {
return this.value;
} 

/**
* ����value��Setter����.��������ֵ
* ��������:2018-12-5
* @param newValue java.lang.String
*/
public void setValue ( String value) {
this.value=value;
} 
 
/**
* ���� isorder��Getter����.���������Ƿ�����
*  ��������:2018-12-5
* @return nc.vo.pub.lang.UFBoolean
*/
public UFBoolean getIsorder() {
return this.isorder;
} 

/**
* ����isorder��Setter����.���������Ƿ�����
* ��������:2018-12-5
* @param newIsorder nc.vo.pub.lang.UFBoolean
*/
public void setIsorder ( UFBoolean isorder) {
this.isorder=isorder;
} 
 
/**
* ���� envalue��Getter����.��������Ӣ��ֵ
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getEnvalue() {
return this.envalue;
} 

/**
* ����envalue��Setter����.��������Ӣ��ֵ
* ��������:2018-12-5
* @param newEnvalue java.lang.String
*/
public void setEnvalue ( String envalue) {
this.envalue=envalue;
} 
 
/**
* ���� constvalue��Getter����.������������
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getConstvalue() {
return this.constvalue;
} 

/**
* ����constvalue��Setter����.������������
* ��������:2018-12-5
* @param newConstvalue java.lang.String
*/
public void setConstvalue ( String constvalue) {
this.constvalue=constvalue;
} 
 
/**
* ���� �����ϲ�������Getter����.���������ϲ�����
*  ��������:2018-12-5
* @return String
*/
public String getPk_listdoc(){
return this.pk_listdoc;
}
/**
* ���������ϲ�������Setter����.���������ϲ�����
* ��������:2018-12-5
* @param newPk_listdoc String
*/
public void setPk_listdoc(String pk_listdoc){
this.pk_listdoc=pk_listdoc;
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
    return VOMetaFactory.getInstance().getVOMeta("qcco.listentry");
    }
   }
    