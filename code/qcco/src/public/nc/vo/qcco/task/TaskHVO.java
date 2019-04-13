package nc.vo.qcco.task;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <b> ��̎��Ҫ��������� </b>
 * <p>
 * ��̎����۵�������Ϣ
 * </p>
 * ��������:2019/4/13
 * 
 * @author yonyouBQ
 * @version NCPrj ??
 */

public class TaskHVO extends SuperVO {

	/**
	 * �������I
	 */
	public String pk_task_h;
	/**
	 * ίӚ�����I
	 */
	public String pk_commission_h;
	/**
	 * ���F
	 */
	public String pk_group;
	/**
	 * �M��
	 */
	public String pk_org;
	/**
	 * �M���汾
	 */
	public String pk_org_v;
	/**
	 * �Ɔ�����
	 */
	public UFDate dbilldate;
	/**
	 * ������
	 */
	public String creator;
	/**
	 * �����r�g
	 */
	public UFDateTime creationtime;
	/**
	 * �޸���
	 */
	public String modifier;
	/**
	 * �޸ĕr�g
	 */
	public UFDateTime modifiedtime;
	/**
	 * �ƆΕr�g
	 */
	public UFDateTime maketime;
	/**
	 * �����޸ĕr�g
	 */
	public UFDateTime lastmaketime;
	/**
	 * �Γ�ID
	 */
	public String billid;
	/**
	 * �Γ�̖
	 */
	public String billno;
	/**
	 * �I�����
	 */
	public String busitype;
	/**
	 * �Ɔ���
	 */
	public String billmaker;
	/**
	 * ������
	 */
	public String approver;
	/**
	 * ������B
	 */
	public Integer approvestatus;
	/**
	 * �������Z
	 */
	public String approvenote;
	/**
	 * �����r�g
	 */
	public UFDateTime approvedate;
	/**
	 * �������
	 */
	public String transtype;
	/**
	 * �Γ����
	 */
	public String billtype;
	/**
	 * �������pk
	 */
	public String transtypepk;
	/**
	 * ��Դ�Γ����
	 */
	public String srcbilltype;
	/**
	 * ��Դ�Γ�id
	 */
	public String srcbillid;
	/**
	 * ��ӆö�e
	 */
	public Integer emendenum;
	/**
	 * �Γ��汾pk
	 */
	public String billversionpk;
	/**
	 * �r�g��
	 */
	public UFDateTime ts;

	/**
	 * ���� pk_task_h��Getter����.���������������I ��������:2019/4/13
	 * 
	 * @return java.lang.String
	 */
	public String getPk_task_h() {
		return this.pk_task_h;
	}

	/**
	 * ����pk_task_h��Setter����.���������������I ��������:2019/4/13
	 * 
	 * @param newPk_task_h
	 *            java.lang.String
	 */
	public void setPk_task_h(String pk_task_h) {
		this.pk_task_h = pk_task_h;
	}

	/**
	 * ���� pk_commission_h��Getter����.��������ίӚ�����I ��������:2019/4/13
	 * 
	 * @return nc.vo.qcco.commission.CommissionHVO
	 */
	public String getPk_commission_h() {
		return this.pk_commission_h;
	}

	/**
	 * ����pk_commission_h��Setter����.��������ίӚ�����I ��������:2019/4/13
	 * 
	 * @param newPk_commission_h
	 *            nc.vo.qcco.commission.CommissionHVO
	 */
	public void setPk_commission_h(String pk_commission_h) {
		this.pk_commission_h = pk_commission_h;
	}

	/**
	 * ���� pk_group��Getter����.�����������F ��������:2019/4/13
	 * 
	 * @return nc.vo.org.GroupVO
	 */
	public String getPk_group() {
		return this.pk_group;
	}

	/**
	 * ����pk_group��Setter����.�����������F ��������:2019/4/13
	 * 
	 * @param newPk_group
	 *            nc.vo.org.GroupVO
	 */
	public void setPk_group(String pk_group) {
		this.pk_group = pk_group;
	}

	/**
	 * ���� pk_org��Getter����.���������M�� ��������:2019/4/13
	 * 
	 * @return nc.vo.org.OrgVO
	 */
	public String getPk_org() {
		return this.pk_org;
	}

	/**
	 * ����pk_org��Setter����.���������M�� ��������:2019/4/13
	 * 
	 * @param newPk_org
	 *            nc.vo.org.OrgVO
	 */
	public void setPk_org(String pk_org) {
		this.pk_org = pk_org;
	}

	/**
	 * ���� pk_org_v��Getter����.���������M���汾 ��������:2019/4/13
	 * 
	 * @return nc.vo.vorg.OrgVersionVO
	 */
	public String getPk_org_v() {
		return this.pk_org_v;
	}

	/**
	 * ����pk_org_v��Setter����.���������M���汾 ��������:2019/4/13
	 * 
	 * @param newPk_org_v
	 *            nc.vo.vorg.OrgVersionVO
	 */
	public void setPk_org_v(String pk_org_v) {
		this.pk_org_v = pk_org_v;
	}

	/**
	 * ���� dbilldate��Getter����.���������Ɔ����� ��������:2019/4/13
	 * 
	 * @return nc.vo.pub.lang.UFDate
	 */
	public UFDate getDbilldate() {
		return this.dbilldate;
	}

	/**
	 * ����dbilldate��Setter����.���������Ɔ����� ��������:2019/4/13
	 * 
	 * @param newDbilldate
	 *            nc.vo.pub.lang.UFDate
	 */
	public void setDbilldate(UFDate dbilldate) {
		this.dbilldate = dbilldate;
	}

	/**
	 * ���� creator��Getter����.�������������� ��������:2019/4/13
	 * 
	 * @return nc.vo.sm.UserVO
	 */
	public String getCreator() {
		return this.creator;
	}

	/**
	 * ����creator��Setter����.�������������� ��������:2019/4/13
	 * 
	 * @param newCreator
	 *            nc.vo.sm.UserVO
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}

	/**
	 * ���� creationtime��Getter����.�������������r�g ��������:2019/4/13
	 * 
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public UFDateTime getCreationtime() {
		return this.creationtime;
	}

	/**
	 * ����creationtime��Setter����.�������������r�g ��������:2019/4/13
	 * 
	 * @param newCreationtime
	 *            nc.vo.pub.lang.UFDateTime
	 */
	public void setCreationtime(UFDateTime creationtime) {
		this.creationtime = creationtime;
	}

	/**
	 * ���� modifier��Getter����.���������޸��� ��������:2019/4/13
	 * 
	 * @return nc.vo.sm.UserVO
	 */
	public String getModifier() {
		return this.modifier;
	}

	/**
	 * ����modifier��Setter����.���������޸��� ��������:2019/4/13
	 * 
	 * @param newModifier
	 *            nc.vo.sm.UserVO
	 */
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	/**
	 * ���� modifiedtime��Getter����.���������޸ĕr�g ��������:2019/4/13
	 * 
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public UFDateTime getModifiedtime() {
		return this.modifiedtime;
	}

	/**
	 * ����modifiedtime��Setter����.���������޸ĕr�g ��������:2019/4/13
	 * 
	 * @param newModifiedtime
	 *            nc.vo.pub.lang.UFDateTime
	 */
	public void setModifiedtime(UFDateTime modifiedtime) {
		this.modifiedtime = modifiedtime;
	}

	/**
	 * ���� maketime��Getter����.���������ƆΕr�g ��������:2019/4/13
	 * 
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public UFDateTime getMaketime() {
		return this.maketime;
	}

	/**
	 * ����maketime��Setter����.���������ƆΕr�g ��������:2019/4/13
	 * 
	 * @param newMaketime
	 *            nc.vo.pub.lang.UFDateTime
	 */
	public void setMaketime(UFDateTime maketime) {
		this.maketime = maketime;
	}

	/**
	 * ���� lastmaketime��Getter����.�������������޸ĕr�g ��������:2019/4/13
	 * 
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public UFDateTime getLastmaketime() {
		return this.lastmaketime;
	}

	/**
	 * ����lastmaketime��Setter����.�������������޸ĕr�g ��������:2019/4/13
	 * 
	 * @param newLastmaketime
	 *            nc.vo.pub.lang.UFDateTime
	 */
	public void setLastmaketime(UFDateTime lastmaketime) {
		this.lastmaketime = lastmaketime;
	}

	/**
	 * ���� billid��Getter����.���������Γ�ID ��������:2019/4/13
	 * 
	 * @return java.lang.String
	 */
	public String getBillid() {
		return this.billid;
	}

	/**
	 * ����billid��Setter����.���������Γ�ID ��������:2019/4/13
	 * 
	 * @param newBillid
	 *            java.lang.String
	 */
	public void setBillid(String billid) {
		this.billid = billid;
	}

	/**
	 * ���� billno��Getter����.���������Γ�̖ ��������:2019/4/13
	 * 
	 * @return java.lang.String
	 */
	public String getBillno() {
		return this.billno;
	}

	/**
	 * ����billno��Setter����.���������Γ�̖ ��������:2019/4/13
	 * 
	 * @param newBillno
	 *            java.lang.String
	 */
	public void setBillno(String billno) {
		this.billno = billno;
	}

	/**
	 * ���� busitype��Getter����.���������I����� ��������:2019/4/13
	 * 
	 * @return java.lang.String
	 */
	public String getBusitype() {
		return this.busitype;
	}

	/**
	 * ����busitype��Setter����.���������I����� ��������:2019/4/13
	 * 
	 * @param newBusitype
	 *            java.lang.String
	 */
	public void setBusitype(String busitype) {
		this.busitype = busitype;
	}

	/**
	 * ���� billmaker��Getter����.���������Ɔ��� ��������:2019/4/13
	 * 
	 * @return java.lang.String
	 */
	public String getBillmaker() {
		return this.billmaker;
	}

	/**
	 * ����billmaker��Setter����.���������Ɔ��� ��������:2019/4/13
	 * 
	 * @param newBillmaker
	 *            java.lang.String
	 */
	public void setBillmaker(String billmaker) {
		this.billmaker = billmaker;
	}

	/**
	 * ���� approver��Getter����.�������������� ��������:2019/4/13
	 * 
	 * @return java.lang.String
	 */
	public String getApprover() {
		return this.approver;
	}

	/**
	 * ����approver��Setter����.�������������� ��������:2019/4/13
	 * 
	 * @param newApprover
	 *            java.lang.String
	 */
	public void setApprover(String approver) {
		this.approver = approver;
	}

	/**
	 * ���� approvestatus��Getter����.��������������B ��������:2019/4/13
	 * 
	 * @return nc.vo.pub.pf.BillStatusEnum
	 */
	public Integer getApprovestatus() {
		return this.approvestatus;
	}

	/**
	 * ����approvestatus��Setter����.��������������B ��������:2019/4/13
	 * 
	 * @param newApprovestatus
	 *            nc.vo.pub.pf.BillStatusEnum
	 */
	public void setApprovestatus(Integer approvestatus) {
		this.approvestatus = approvestatus;
	}

	/**
	 * ���� approvenote��Getter����.���������������Z ��������:2019/4/13
	 * 
	 * @return java.lang.String
	 */
	public String getApprovenote() {
		return this.approvenote;
	}

	/**
	 * ����approvenote��Setter����.���������������Z ��������:2019/4/13
	 * 
	 * @param newApprovenote
	 *            java.lang.String
	 */
	public void setApprovenote(String approvenote) {
		this.approvenote = approvenote;
	}

	/**
	 * ���� approvedate��Getter����.�������������r�g ��������:2019/4/13
	 * 
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public UFDateTime getApprovedate() {
		return this.approvedate;
	}

	/**
	 * ����approvedate��Setter����.�������������r�g ��������:2019/4/13
	 * 
	 * @param newApprovedate
	 *            nc.vo.pub.lang.UFDateTime
	 */
	public void setApprovedate(UFDateTime approvedate) {
		this.approvedate = approvedate;
	}

	/**
	 * ���� transtype��Getter����.��������������� ��������:2019/4/13
	 * 
	 * @return java.lang.String
	 */
	public String getTranstype() {
		return this.transtype;
	}

	/**
	 * ����transtype��Setter����.��������������� ��������:2019/4/13
	 * 
	 * @param newTranstype
	 *            java.lang.String
	 */
	public void setTranstype(String transtype) {
		this.transtype = transtype;
	}

	/**
	 * ���� billtype��Getter����.���������Γ���� ��������:2019/4/13
	 * 
	 * @return java.lang.String
	 */
	public String getBilltype() {
		return this.billtype;
	}

	/**
	 * ����billtype��Setter����.���������Γ���� ��������:2019/4/13
	 * 
	 * @param newBilltype
	 *            java.lang.String
	 */
	public void setBilltype(String billtype) {
		this.billtype = billtype;
	}

	/**
	 * ���� transtypepk��Getter����.���������������pk ��������:2019/4/13
	 * 
	 * @return java.lang.String
	 */
	public String getTranstypepk() {
		return this.transtypepk;
	}

	/**
	 * ����transtypepk��Setter����.���������������pk ��������:2019/4/13
	 * 
	 * @param newTranstypepk
	 *            java.lang.String
	 */
	public void setTranstypepk(String transtypepk) {
		this.transtypepk = transtypepk;
	}

	/**
	 * ���� srcbilltype��Getter����.����������Դ�Γ���� ��������:2019/4/13
	 * 
	 * @return java.lang.String
	 */
	public String getSrcbilltype() {
		return this.srcbilltype;
	}

	/**
	 * ����srcbilltype��Setter����.����������Դ�Γ���� ��������:2019/4/13
	 * 
	 * @param newSrcbilltype
	 *            java.lang.String
	 */
	public void setSrcbilltype(String srcbilltype) {
		this.srcbilltype = srcbilltype;
	}

	/**
	 * ���� srcbillid��Getter����.����������Դ�Γ�id ��������:2019/4/13
	 * 
	 * @return java.lang.String
	 */
	public String getSrcbillid() {
		return this.srcbillid;
	}

	/**
	 * ����srcbillid��Setter����.����������Դ�Γ�id ��������:2019/4/13
	 * 
	 * @param newSrcbillid
	 *            java.lang.String
	 */
	public void setSrcbillid(String srcbillid) {
		this.srcbillid = srcbillid;
	}

	/**
	 * ���� emendenum��Getter����.����������ӆö�e ��������:2019/4/13
	 * 
	 * @return java.lang.Integer
	 */
	public Integer getEmendenum() {
		return this.emendenum;
	}

	/**
	 * ����emendenum��Setter����.����������ӆö�e ��������:2019/4/13
	 * 
	 * @param newEmendenum
	 *            java.lang.Integer
	 */
	public void setEmendenum(Integer emendenum) {
		this.emendenum = emendenum;
	}

	/**
	 * ���� billversionpk��Getter����.���������Γ��汾pk ��������:2019/4/13
	 * 
	 * @return java.lang.String
	 */
	public String getBillversionpk() {
		return this.billversionpk;
	}

	/**
	 * ����billversionpk��Setter����.���������Γ��汾pk ��������:2019/4/13
	 * 
	 * @param newBillversionpk
	 *            java.lang.String
	 */
	public void setBillversionpk(String billversionpk) {
		this.billversionpk = billversionpk;
	}

	/**
	 * ���� ���ɕr�g����Getter����.���������r�g�� ��������:2019/4/13
	 * 
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public UFDateTime getTs() {
		return this.ts;
	}

	/**
	 * �������ɕr�g����Setter����.���������r�g�� ��������:2019/4/13
	 * 
	 * @param newts
	 *            nc.vo.pub.lang.UFDateTime
	 */
	public void setTs(UFDateTime ts) {
		this.ts = ts;
	}

	@Override
	public IVOMeta getMetaData() {
		return VOMetaFactory.getInstance().getVOMeta("qcco.task_h");
	}
}
