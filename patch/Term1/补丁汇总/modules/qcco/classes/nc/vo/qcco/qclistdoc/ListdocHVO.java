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
 
public class ListdocHVO extends SuperVO {
	
/**
*����
*/
public String pk_listdoc;
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
*����
*/
public String code;
/**
*����
*/
public String name;
/**
*�Ƶ�ʱ��
*/
public UFDateTime maketime;
/**
*��Ʒ���
*/
public String pk_samplegroup;
/**
*����
*/
public String memo;
/**
*�Ƿ��Ƴ�
*/
public UFBoolean isremove;
/**
*���Ŀ¼
*/
public String extdir;
/**
*����޸�ʱ��
*/
public UFDateTime lastmaketime;
/**
*�Ƿ�����
*/
public UFBoolean isorder;
/**
*�Ƶ�����
*/
public UFDate dbilldate;
/**
*ʱ���
*/
public UFDateTime ts;
    
    
/**
* ���� pk_listdoc��Getter����.������������
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getPk_listdoc() {
return this.pk_listdoc;
} 

/**
* ����pk_listdoc��Setter����.������������
* ��������:2018-12-5
* @param newPk_listdoc java.lang.String
*/
public void setPk_listdoc ( String pk_listdoc) {
this.pk_listdoc=pk_listdoc;
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
* ���� pk_samplegroup��Getter����.����������Ʒ���
*  ��������:2018-12-5
* @return nc.vo.bd.defdoc.DefdocVO
*/
public String getPk_samplegroup() {
return this.pk_samplegroup;
} 

/**
* ����pk_samplegroup��Setter����.����������Ʒ���
* ��������:2018-12-5
* @param newPk_samplegroup nc.vo.bd.defdoc.DefdocVO
*/
public void setPk_samplegroup ( String pk_samplegroup) {
this.pk_samplegroup=pk_samplegroup;
} 
 
/**
* ���� memo��Getter����.������������
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getMemo() {
return this.memo;
} 

/**
* ����memo��Setter����.������������
* ��������:2018-12-5
* @param newMemo java.lang.String
*/
public void setMemo ( String memo) {
this.memo=memo;
} 
 
/**
* ���� isremove��Getter����.���������Ƿ��Ƴ�
*  ��������:2018-12-5
* @return nc.vo.pub.lang.UFBoolean
*/
public UFBoolean getIsremove() {
return this.isremove;
} 

/**
* ����isremove��Setter����.���������Ƿ��Ƴ�
* ��������:2018-12-5
* @param newIsremove nc.vo.pub.lang.UFBoolean
*/
public void setIsremove ( UFBoolean isremove) {
this.isremove=isremove;
} 
 
/**
* ���� extdir��Getter����.�����������Ŀ¼
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getExtdir() {
return this.extdir;
} 

/**
* ����extdir��Setter����.�����������Ŀ¼
* ��������:2018-12-5
* @param newExtdir java.lang.String
*/
public void setExtdir ( String extdir) {
this.extdir=extdir;
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
* ���� dbilldate��Getter����.���������Ƶ�����
*  ��������:2018-12-5
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getDbilldate() {
return this.dbilldate;
} 

/**
* ����dbilldate��Setter����.���������Ƶ�����
* ��������:2018-12-5
* @param newDbilldate nc.vo.pub.lang.UFDate
*/
public void setDbilldate ( UFDate dbilldate) {
this.dbilldate=dbilldate;
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
    return VOMetaFactory.getInstance().getVOMeta("qcco.listdoc");
    }
   }
    