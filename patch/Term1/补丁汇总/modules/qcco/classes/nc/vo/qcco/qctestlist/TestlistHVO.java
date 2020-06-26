package nc.vo.qcco.qctestlist;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pub.lang.UFLiteralDate;
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
 
public class TestlistHVO extends SuperVO {
	
/**
*��������
*/
public String pk_testlist;
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
*����޸�ʱ��
*/
public UFDateTime lastmaketime;
/**
*��������
*/
public UFLiteralDate changedate;
/**
*������
*/
public String changer;
/**
*�Ƿ��Ƴ�
*/
public UFBoolean isremove;
/**
*����
*/
public String groupname;
/**
*��չ����
*/
public String extlink;
/**
*ȫ�ֱ�־
*/
public UFBoolean isgloble;
/**
*������
*/
public String belongs;
/**
*�Ƿ�������
*/
public UFBoolean isresultorder;
/**
*������ɫ
*/
public String approvelrole;
/**
*��ҵ��׼
*/
public String pk_entstandart;
/**
*�Ƶ�����
*/
public UFDate dbilldate;
/**
*ʱ���
*/
public UFDateTime ts;
    
    
/**
* ���� pk_testlist��Getter����.����������������
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getPk_testlist() {
return this.pk_testlist;
} 

/**
* ����pk_testlist��Setter����.����������������
* ��������:2018-12-5
* @param newPk_testlist java.lang.String
*/
public void setPk_testlist ( String pk_testlist) {
this.pk_testlist=pk_testlist;
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
* ���� changedate��Getter����.����������������
*  ��������:2018-12-5
* @return nc.vo.pub.lang.UFLiteralDate
*/
public UFLiteralDate getChangedate() {
return this.changedate;
} 

/**
* ����changedate��Setter����.����������������
* ��������:2018-12-5
* @param newChangedate nc.vo.pub.lang.UFLiteralDate
*/
public void setChangedate ( UFLiteralDate changedate) {
this.changedate=changedate;
} 
 
/**
* ���� changer��Getter����.��������������
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getChanger() {
return this.changer;
} 

/**
* ����changer��Setter����.��������������
* ��������:2018-12-5
* @param newChanger java.lang.String
*/
public void setChanger ( String changer) {
this.changer=changer;
} 
 
/**
* ���� isremove��Getter����.���������Ƿ��Ƴ�
*  ��������:2018-12-5
* @return nc.vo.pub.lang.UFUFBoolean
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
* ���� groupname��Getter����.������������
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getGroupname() {
return this.groupname;
} 

/**
* ����groupname��Setter����.������������
* ��������:2018-12-5
* @param newGroupname java.lang.String
*/
public void setGroupname ( String groupname) {
this.groupname=groupname;
} 
 
/**
* ���� extlink��Getter����.����������չ����
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getExtlink() {
return this.extlink;
} 

/**
* ����extlink��Setter����.����������չ����
* ��������:2018-12-5
* @param newExtlink java.lang.String
*/
public void setExtlink ( String extlink) {
this.extlink=extlink;
} 
 
/**
* ���� isgloble��Getter����.��������ȫ�ֱ�־
*  ��������:2018-12-5
* @return nc.vo.pub.lang.UFBoolean
*/
public UFBoolean getIsgloble() {
return this.isgloble;
} 

/**
* ����isgloble��Setter����.��������ȫ�ֱ�־
* ��������:2018-12-5
* @param newIsgloble nc.vo.pub.lang.UFBoolean
*/
public void setIsgloble ( UFBoolean isgloble) {
this.isgloble=isgloble;
} 
 
/**
* ���� belongs��Getter����.��������������
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getBelongs() {
return this.belongs;
} 

/**
* ����belongs��Setter����.��������������
* ��������:2018-12-5
* @param newBelongs java.lang.String
*/
public void setBelongs ( String belongs) {
this.belongs=belongs;
} 
 
/**
* ���� isresultorder��Getter����.���������Ƿ�������
*  ��������:2018-12-5
* @return nc.vo.pub.lang.UFBoolean
*/
public UFBoolean getIsresultorder() {
return this.isresultorder;
} 

/**
* ����isresultorder��Setter����.���������Ƿ�������
* ��������:2018-12-5
* @param newIsresultorder nc.vo.pub.lang.UFBoolean
*/
public void setIsresultorder ( UFBoolean isresultorder) {
this.isresultorder=isresultorder;
} 
 
/**
* ���� approvelrole��Getter����.��������������ɫ
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getApprovelrole() {
return this.approvelrole;
} 

/**
* ����approvelrole��Setter����.��������������ɫ
* ��������:2018-12-5
* @param newApprovelrole java.lang.String
*/
public void setApprovelrole ( String approvelrole) {
this.approvelrole=approvelrole;
} 
 
/**
* ���� pk_entstandart��Getter����.����������ҵ��׼
*  ��������:2018-12-5
* @return nc.vo.qcco.qcentstandard.EntstandardVO
*/
public String getPk_entstandart() {
return this.pk_entstandart;
} 

/**
* ����pk_entstandart��Setter����.����������ҵ��׼
* ��������:2018-12-5
* @param newPk_entstandart nc.vo.qcco.qcentstandard.EntstandardVO
*/
public void setPk_entstandart ( String pk_entstandart) {
this.pk_entstandart=pk_entstandart;
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
    return VOMetaFactory.getInstance().getVOMeta("qcco.testlist");
    }
   }
    