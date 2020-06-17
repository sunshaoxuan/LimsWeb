package nc.vo.qcco.task;

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
 
public class TaskHVO extends SuperVO {
	
/**
*��������
*/
public String pk_task_h;
/**
*ί�е�����
*/
public String pk_commission_h;
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
*�Ƶ�����
*/
public UFDate dbilldate;
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
*code
*/
public String code;
/**
*name
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
*����ID
*/
public String billid;
/**
*���ݺ�
*/
public String billno;
/**
*ҵ������
*/
public String busitype;
/**
*�Ƶ���
*/
public String billmaker;
/**
*������
*/
public String approver;
/**
*����״̬
*/
public Integer approvestatus;
/**
*��������
*/
public String approvenote;
/**
*����ʱ��
*/
public UFDateTime approvedate;
/**
*��������
*/
public String transtype;
/**
*��������
*/
public String billtype;
/**
*��������pk
*/
public String transtypepk;
/**
*��Դ��������
*/
public String srcbilltype;
/**
*��Դ����id
*/
public String srcbillid;
/**
*�޶�ö��
*/
public Integer emendenum;
/**
*���ݰ汾pk
*/
public String billversionpk;
/**
*ʱ���
*/
public UFDateTime ts;
    
    
/**
* ���� pk_task_h��Getter����.����������������
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getPk_task_h() {
return this.pk_task_h;
} 

/**
* ����pk_task_h��Setter����.����������������
* ��������:2018-12-5
* @param newPk_task_h java.lang.String
*/
public void setPk_task_h ( String pk_task_h) {
this.pk_task_h=pk_task_h;
} 
 
/**
* ���� pk_commission_h��Getter����.��������ί�е�����
*  ��������:2018-12-5
* @return nc.vo.qcco.commission.CommissionHVO
*/
public String getPk_commission_h() {
return this.pk_commission_h;
} 

/**
* ����pk_commission_h��Setter����.��������ί�е�����
* ��������:2018-12-5
* @param newPk_commission_h nc.vo.qcco.commission.CommissionHVO
*/
public void setPk_commission_h ( String pk_commission_h) {
this.pk_commission_h=pk_commission_h;
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
* ���� code��Getter����.��������code
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getCode() {
return this.code;
} 

/**
* ����code��Setter����.��������code
* ��������:2018-12-5
* @param newCode java.lang.String
*/
public void setCode ( String code) {
this.code=code;
} 
 
/**
* ���� name��Getter����.��������name
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getName() {
return this.name;
} 

/**
* ����name��Setter����.��������name
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
* ���� billid��Getter����.������������ID
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getBillid() {
return this.billid;
} 

/**
* ����billid��Setter����.������������ID
* ��������:2018-12-5
* @param newBillid java.lang.String
*/
public void setBillid ( String billid) {
this.billid=billid;
} 
 
/**
* ���� billno��Getter����.�����������ݺ�
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getBillno() {
return this.billno;
} 

/**
* ����billno��Setter����.�����������ݺ�
* ��������:2018-12-5
* @param newBillno java.lang.String
*/
public void setBillno ( String billno) {
this.billno=billno;
} 
 
/**
* ���� busitype��Getter����.��������ҵ������
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getBusitype() {
return this.busitype;
} 

/**
* ����busitype��Setter����.��������ҵ������
* ��������:2018-12-5
* @param newBusitype java.lang.String
*/
public void setBusitype ( String busitype) {
this.busitype=busitype;
} 
 
/**
* ���� billmaker��Getter����.���������Ƶ���
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getBillmaker() {
return this.billmaker;
} 

/**
* ����billmaker��Setter����.���������Ƶ���
* ��������:2018-12-5
* @param newBillmaker java.lang.String
*/
public void setBillmaker ( String billmaker) {
this.billmaker=billmaker;
} 
 
/**
* ���� approver��Getter����.��������������
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getApprover() {
return this.approver;
} 

/**
* ����approver��Setter����.��������������
* ��������:2018-12-5
* @param newApprover java.lang.String
*/
public void setApprover ( String approver) {
this.approver=approver;
} 
 
/**
* ���� approvestatus��Getter����.������������״̬
*  ��������:2018-12-5
* @return nc.vo.pub.pf.BillStatusEnum
*/
public Integer getApprovestatus() {
return this.approvestatus;
} 

/**
* ����approvestatus��Setter����.������������״̬
* ��������:2018-12-5
* @param newApprovestatus nc.vo.pub.pf.BillStatusEnum
*/
public void setApprovestatus ( Integer approvestatus) {
this.approvestatus=approvestatus;
} 
 
/**
* ���� approvenote��Getter����.����������������
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getApprovenote() {
return this.approvenote;
} 

/**
* ����approvenote��Setter����.����������������
* ��������:2018-12-5
* @param newApprovenote java.lang.String
*/
public void setApprovenote ( String approvenote) {
this.approvenote=approvenote;
} 
 
/**
* ���� approvedate��Getter����.������������ʱ��
*  ��������:2018-12-5
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getApprovedate() {
return this.approvedate;
} 

/**
* ����approvedate��Setter����.������������ʱ��
* ��������:2018-12-5
* @param newApprovedate nc.vo.pub.lang.UFDateTime
*/
public void setApprovedate ( UFDateTime approvedate) {
this.approvedate=approvedate;
} 
 
/**
* ���� transtype��Getter����.����������������
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getTranstype() {
return this.transtype;
} 

/**
* ����transtype��Setter����.����������������
* ��������:2018-12-5
* @param newTranstype java.lang.String
*/
public void setTranstype ( String transtype) {
this.transtype=transtype;
} 
 
/**
* ���� billtype��Getter����.����������������
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getBilltype() {
return this.billtype;
} 

/**
* ����billtype��Setter����.����������������
* ��������:2018-12-5
* @param newBilltype java.lang.String
*/
public void setBilltype ( String billtype) {
this.billtype=billtype;
} 
 
/**
* ���� transtypepk��Getter����.����������������pk
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getTranstypepk() {
return this.transtypepk;
} 

/**
* ����transtypepk��Setter����.����������������pk
* ��������:2018-12-5
* @param newTranstypepk java.lang.String
*/
public void setTranstypepk ( String transtypepk) {
this.transtypepk=transtypepk;
} 
 
/**
* ���� srcbilltype��Getter����.����������Դ��������
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getSrcbilltype() {
return this.srcbilltype;
} 

/**
* ����srcbilltype��Setter����.����������Դ��������
* ��������:2018-12-5
* @param newSrcbilltype java.lang.String
*/
public void setSrcbilltype ( String srcbilltype) {
this.srcbilltype=srcbilltype;
} 
 
/**
* ���� srcbillid��Getter����.����������Դ����id
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getSrcbillid() {
return this.srcbillid;
} 

/**
* ����srcbillid��Setter����.����������Դ����id
* ��������:2018-12-5
* @param newSrcbillid java.lang.String
*/
public void setSrcbillid ( String srcbillid) {
this.srcbillid=srcbillid;
} 
 
/**
* ���� emendenum��Getter����.���������޶�ö��
*  ��������:2018-12-5
* @return java.lang.Integer
*/
public Integer getEmendenum() {
return this.emendenum;
} 

/**
* ����emendenum��Setter����.���������޶�ö��
* ��������:2018-12-5
* @param newEmendenum java.lang.Integer
*/
public void setEmendenum ( Integer emendenum) {
this.emendenum=emendenum;
} 
 
/**
* ���� billversionpk��Getter����.�����������ݰ汾pk
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getBillversionpk() {
return this.billversionpk;
} 

/**
* ����billversionpk��Setter����.�����������ݰ汾pk
* ��������:2018-12-5
* @param newBillversionpk java.lang.String
*/
public void setBillversionpk ( String billversionpk) {
this.billversionpk=billversionpk;
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
    return VOMetaFactory.getInstance().getVOMeta("qcco.task_h");
    }
   }
    