package nc.vo.qcco.qcitemvalue;

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
 
public class ItemValueVO extends SuperVO {
	
/**
*����
*/
public String pk_itemvalue;
/**
*ֵ��
*/
public String pk_valueitem;
/**
*ҵ��
*/
public String pk_bizpk;
/**
*ȡֵ
*/
public String itemvalue;
/**
*ȡֵ��ʾ
*/
public String valuestring;
/**
*����
*/
public String pk_group;
/**
*��֯
*/
public String pk_org;
/**
*��֯�汾
*/
public String pk_org_v;
/**
*������
*/
public String creator;
/**
*����ʱ��
*/
public UFDateTime creationtime;
/**
*�޸���
*/
public String modifier;
/**
*�޸�ʱ��
*/
public UFDateTime modifiedtime;
/**
*�Ƶ�ʱ��
*/
public UFDateTime maketime;
/**
*����޸�ʱ��
*/
public UFDateTime lastmaketime;
/**
*�Ƶ�����
*/
public UFDate dmakedate;
/**
*����
*/
public String code;
/**
*����
*/
public String name;
/**
*ʱ���
*/
public UFDateTime ts;
    
    
/**
* ���� pk_itemvalue��Getter����.������������
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getPk_itemvalue() {
return this.pk_itemvalue;
} 

/**
* ����pk_itemvalue��Setter����.������������
* ��������:2018-12-5
* @param newPk_itemvalue java.lang.String
*/
public void setPk_itemvalue ( String pk_itemvalue) {
this.pk_itemvalue=pk_itemvalue;
} 
 
/**
* ���� pk_valueitem��Getter����.��������ֵ��
*  ��������:2018-12-5
* @return nc.vo.qcco.qcvaluetype.ValueListBVO
*/
public String getPk_valueitem() {
return this.pk_valueitem;
} 

/**
* ����pk_valueitem��Setter����.��������ֵ��
* ��������:2018-12-5
* @param newPk_valueitem nc.vo.qcco.qcvaluetype.ValueListBVO
*/
public void setPk_valueitem ( String pk_valueitem) {
this.pk_valueitem=pk_valueitem;
} 
 
/**
* ���� pk_bizpk��Getter����.��������ҵ��
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getPk_bizpk() {
return this.pk_bizpk;
} 

/**
* ����pk_bizpk��Setter����.��������ҵ��
* ��������:2018-12-5
* @param newPk_bizpk java.lang.String
*/
public void setPk_bizpk ( String pk_bizpk) {
this.pk_bizpk=pk_bizpk;
} 
 
/**
* ���� itemvalue��Getter����.��������ȡֵ
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getItemvalue() {
return this.itemvalue;
} 

/**
* ����itemvalue��Setter����.��������ȡֵ
* ��������:2018-12-5
* @param newItemvalue java.lang.String
*/
public void setItemvalue ( String itemvalue) {
this.itemvalue=itemvalue;
} 
 
/**
* ���� valuestring��Getter����.��������ȡֵ��ʾ
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getValuestring() {
return this.valuestring;
} 

/**
* ����valuestring��Setter����.��������ȡֵ��ʾ
* ��������:2018-12-5
* @param newValuestring java.lang.String
*/
public void setValuestring ( String valuestring) {
this.valuestring=valuestring;
} 
 
/**
* ���� pk_group��Getter����.������������
*  ��������:2018-12-5
* @return nc.vo.org.GroupVO
*/
public String getPk_group() {
return this.pk_group;
} 

/**
* ����pk_group��Setter����.������������
* ��������:2018-12-5
* @param newPk_group nc.vo.org.GroupVO
*/
public void setPk_group ( String pk_group) {
this.pk_group=pk_group;
} 
 
/**
* ���� pk_org��Getter����.����������֯
*  ��������:2018-12-5
* @return nc.vo.org.OrgVO
*/
public String getPk_org() {
return this.pk_org;
} 

/**
* ����pk_org��Setter����.����������֯
* ��������:2018-12-5
* @param newPk_org nc.vo.org.OrgVO
*/
public void setPk_org ( String pk_org) {
this.pk_org=pk_org;
} 
 
/**
* ���� pk_org_v��Getter����.����������֯�汾
*  ��������:2018-12-5
* @return nc.vo.vorg.OrgVersionVO
*/
public String getPk_org_v() {
return this.pk_org_v;
} 

/**
* ����pk_org_v��Setter����.����������֯�汾
* ��������:2018-12-5
* @param newPk_org_v nc.vo.vorg.OrgVersionVO
*/
public void setPk_org_v ( String pk_org_v) {
this.pk_org_v=pk_org_v;
} 
 
/**
* ���� creator��Getter����.��������������
*  ��������:2018-12-5
* @return nc.vo.sm.UserVO
*/
public String getCreator() {
return this.creator;
} 

/**
* ����creator��Setter����.��������������
* ��������:2018-12-5
* @param newCreator nc.vo.sm.UserVO
*/
public void setCreator ( String creator) {
this.creator=creator;
} 
 
/**
* ���� creationtime��Getter����.������������ʱ��
*  ��������:2018-12-5
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getCreationtime() {
return this.creationtime;
} 

/**
* ����creationtime��Setter����.������������ʱ��
* ��������:2018-12-5
* @param newCreationtime nc.vo.pub.lang.UFDateTime
*/
public void setCreationtime ( UFDateTime creationtime) {
this.creationtime=creationtime;
} 
 
/**
* ���� modifier��Getter����.���������޸���
*  ��������:2018-12-5
* @return nc.vo.sm.UserVO
*/
public String getModifier() {
return this.modifier;
} 

/**
* ����modifier��Setter����.���������޸���
* ��������:2018-12-5
* @param newModifier nc.vo.sm.UserVO
*/
public void setModifier ( String modifier) {
this.modifier=modifier;
} 
 
/**
* ���� modifiedtime��Getter����.���������޸�ʱ��
*  ��������:2018-12-5
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getModifiedtime() {
return this.modifiedtime;
} 

/**
* ����modifiedtime��Setter����.���������޸�ʱ��
* ��������:2018-12-5
* @param newModifiedtime nc.vo.pub.lang.UFDateTime
*/
public void setModifiedtime ( UFDateTime modifiedtime) {
this.modifiedtime=modifiedtime;
} 
 
/**
* ���� maketime��Getter����.���������Ƶ�ʱ��
*  ��������:2018-12-5
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getMaketime() {
return this.maketime;
} 

/**
* ����maketime��Setter����.���������Ƶ�ʱ��
* ��������:2018-12-5
* @param newMaketime nc.vo.pub.lang.UFDateTime
*/
public void setMaketime ( UFDateTime maketime) {
this.maketime=maketime;
} 
 
/**
* ���� lastmaketime��Getter����.������������޸�ʱ��
*  ��������:2018-12-5
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getLastmaketime() {
return this.lastmaketime;
} 

/**
* ����lastmaketime��Setter����.������������޸�ʱ��
* ��������:2018-12-5
* @param newLastmaketime nc.vo.pub.lang.UFDateTime
*/
public void setLastmaketime ( UFDateTime lastmaketime) {
this.lastmaketime=lastmaketime;
} 
 
/**
* ���� dmakedate��Getter����.���������Ƶ�����
*  ��������:2018-12-5
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getDmakedate() {
return this.dmakedate;
} 

/**
* ����dmakedate��Setter����.���������Ƶ�����
* ��������:2018-12-5
* @param newDmakedate nc.vo.pub.lang.UFDate
*/
public void setDmakedate ( UFDate dmakedate) {
this.dmakedate=dmakedate;
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
    return VOMetaFactory.getInstance().getVOMeta("qcco.itemvalue");
    }
   }
    