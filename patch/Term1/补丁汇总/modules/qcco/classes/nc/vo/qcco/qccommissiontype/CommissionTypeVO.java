package nc.vo.qcco.qccommissiontype;

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
 
public class CommissionTypeVO extends SuperVO {
	
/**
*����
*/
public String pk_commissiontype;
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
public String pk_group;
/**
*��֯
*/
public String pk_org;
/**
*����
*/
public String code;
/**
*����
*/
public String name;
/**
*����
*/
public String describe;
/**
*ʱ���
*/
public UFDateTime ts;
    
    
/**
* ���� pk_commissiontype��Getter����.������������
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getPk_commissiontype() {
return this.pk_commissiontype;
} 

/**
* ����pk_commissiontype��Setter����.������������
* ��������:2018-12-5
* @param newPk_commissiontype java.lang.String
*/
public void setPk_commissiontype ( String pk_commissiontype) {
this.pk_commissiontype=pk_commissiontype;
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
* ���� describe��Getter����.������������
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getDescribe() {
return this.describe;
} 

/**
* ����describe��Setter����.������������
* ��������:2018-12-5
* @param newDescribe java.lang.String
*/
public void setDescribe ( String describe) {
this.describe=describe;
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
    return VOMetaFactory.getInstance().getVOMeta("qcco.commissiontype");
    }
   }
    