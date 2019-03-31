package nc.vo.qcco.commission;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFLiteralDate;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <b> ��̎��Ҫ��������� </b>
 * <p>
 * ��̎����۵�������Ϣ
 * </p>
 * ��������:2019/2/25
 * 
 * @author yonyouBQ
 * @version NCPrj ??
 */

public class CommissionHVO extends SuperVO {

	/**
	 * �������I
	 */
	public String pk_commission_h;
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
	 * �����޸ĕr�g
	 */
	public UFDateTime lastmaketime;
	/**
	 * �Ɔ�����
	 */
	public UFDate dmakedate;
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
	 * ίӚ�����
	 */
	public String pk_commissiontype;
	/**
	 * ίӚ�ξ��aǰ�Y
	 */
	public String codeprefix;
	/**
	 * ίӚ�ξ��a
	 */
	public String billno;
	/**
	 * ίӚ�����Q
	 */
	public String billname;
	/**
	 * ίӚ��λ
	 */
	public String pk_owner;
	/**
	 * ���T
	 */
	public String pk_dept;
	/**
	 * ���M��λ
	 */
	public String pk_payer;
	/**
	 * �M��
	 */
	public String contract;
	/**
	 * �����]��
	 */
	public String email;
	/**
	 * �M�Ԓ
	 */
	public String teleno;
	/**
	 * �aƷ���
	 */
	public String pk_maincategory;
	/**
	 * �������
	 */
	public String pk_subcategory;
	/**
	 * �������
	 */
	public String pk_lastcategory;
	/**
	 * �Ɔ���
	 */
	public String cuserid;
	/**
	 * ����ʽ
	 */
	public String reportformat;
	/**
	 * ����Z��
	 */
	public String reportlang;
	/**
	 * ���܌��˰l���]��
	 */
	public UFBoolean managersendflag;
	/**
	 * �΄��_ʼ�l���]��
	 */
	public UFBoolean taskbeginsendflag;
	/**
	 * �΄սY������]��
	 */
	public UFBoolean taskendsendflag;
	/**
	 * ��溞�l�l���]��
	 */
	public UFBoolean reportsendflag;
	/**
	 * �Ƿ񱣴��ģ��
	 */
	public UFBoolean savetotemplateflag;
	/**
	 * Ӌ�M���]������
	 */
	public UFBoolean receiptsendflag;
	/**
	 * ��r���]������
	 */
	public UFBoolean quotaionsendflag;
	/**
	 * �yԇĿ��
	 */
	public String testaim;
	/**
	 * �M��Ҫ��
	 */
	public String progressneed;
	/**
	 * �z���Ʒ̎��
	 */
	public String sampledealtype;
	/**
	 * �aƷ����
	 */
	public String productproperty;
	/**
	 * �͑����Q
	 */
	public String customername;
	/**
	 * �͑����
	 */
	public String customertype;
	/**
	 * �yԇ����
	 */
	public String testrequirement;
	/**
	 * �z�y���|
	 */
	public String checkingproperty;
	/**
	 * ���a�a��
	 */
	public String productline;
	/**
	 * ���a����
	 */
	public String batchnumber;
	/**
	 * ���a����
	 */
	public UFLiteralDate productdate;
	/**
	 * ���a��̖
	 */
	public String batchserial;
	/**
	 * �aƷ�b�����
	 */
	public String identificationtype;
	/**
	 * �J�C���
	 */
	public String certificationtype;
	/**
	 * �Ŀ̖
	 */
	public String itemnumber;
	/**
	 * �r�g��
	 */
	public UFDateTime ts;

	/**
	 * ���� pk_commission_h��Getter����.���������������I ��������:2019/2/25
	 * 
	 * @return java.lang.String
	 */
	public String getPk_commission_h() {
		return this.pk_commission_h;
	}

	/**
	 * ����pk_commission_h��Setter����.���������������I ��������:2019/2/25
	 * 
	 * @param newPk_commission_h
	 *            java.lang.String
	 */
	public void setPk_commission_h(String pk_commission_h) {
		this.pk_commission_h = pk_commission_h;
	}

	/**
	 * ���� creator��Getter����.�������������� ��������:2019/2/25
	 * 
	 * @return nc.vo.sm.UserVO
	 */
	public String getCreator() {
		return this.creator;
	}

	/**
	 * ����creator��Setter����.�������������� ��������:2019/2/25
	 * 
	 * @param newCreator
	 *            nc.vo.sm.UserVO
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}

	/**
	 * ���� creationtime��Getter����.�������������r�g ��������:2019/2/25
	 * 
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public UFDateTime getCreationtime() {
		return this.creationtime;
	}

	/**
	 * ����creationtime��Setter����.�������������r�g ��������:2019/2/25
	 * 
	 * @param newCreationtime
	 *            nc.vo.pub.lang.UFDateTime
	 */
	public void setCreationtime(UFDateTime creationtime) {
		this.creationtime = creationtime;
	}

	/**
	 * ���� modifier��Getter����.���������޸��� ��������:2019/2/25
	 * 
	 * @return nc.vo.sm.UserVO
	 */
	public String getModifier() {
		return this.modifier;
	}

	/**
	 * ����modifier��Setter����.���������޸��� ��������:2019/2/25
	 * 
	 * @param newModifier
	 *            nc.vo.sm.UserVO
	 */
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	/**
	 * ���� modifiedtime��Getter����.���������޸ĕr�g ��������:2019/2/25
	 * 
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public UFDateTime getModifiedtime() {
		return this.modifiedtime;
	}

	/**
	 * ����modifiedtime��Setter����.���������޸ĕr�g ��������:2019/2/25
	 * 
	 * @param newModifiedtime
	 *            nc.vo.pub.lang.UFDateTime
	 */
	public void setModifiedtime(UFDateTime modifiedtime) {
		this.modifiedtime = modifiedtime;
	}

	/**
	 * ���� lastmaketime��Getter����.�������������޸ĕr�g ��������:2019/2/25
	 * 
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public UFDateTime getLastmaketime() {
		return this.lastmaketime;
	}

	/**
	 * ����lastmaketime��Setter����.�������������޸ĕr�g ��������:2019/2/25
	 * 
	 * @param newLastmaketime
	 *            nc.vo.pub.lang.UFDateTime
	 */
	public void setLastmaketime(UFDateTime lastmaketime) {
		this.lastmaketime = lastmaketime;
	}

	/**
	 * ���� dmakedate��Getter����.���������Ɔ����� ��������:2019/2/25
	 * 
	 * @return nc.vo.pub.lang.UFDate
	 */
	public UFDate getDmakedate() {
		return this.dmakedate;
	}

	/**
	 * ����dmakedate��Setter����.���������Ɔ����� ��������:2019/2/25
	 * 
	 * @param newDmakedate
	 *            nc.vo.pub.lang.UFDate
	 */
	public void setDmakedate(UFDate dmakedate) {
		this.dmakedate = dmakedate;
	}

	/**
	 * ���� pk_group��Getter����.�����������F ��������:2019/2/25
	 * 
	 * @return nc.vo.org.GroupVO
	 */
	public String getPk_group() {
		return this.pk_group;
	}

	/**
	 * ����pk_group��Setter����.�����������F ��������:2019/2/25
	 * 
	 * @param newPk_group
	 *            nc.vo.org.GroupVO
	 */
	public void setPk_group(String pk_group) {
		this.pk_group = pk_group;
	}

	/**
	 * ���� pk_org��Getter����.���������M�� ��������:2019/2/25
	 * 
	 * @return nc.vo.org.OrgVO
	 */
	public String getPk_org() {
		return this.pk_org;
	}

	/**
	 * ����pk_org��Setter����.���������M�� ��������:2019/2/25
	 * 
	 * @param newPk_org
	 *            nc.vo.org.OrgVO
	 */
	public void setPk_org(String pk_org) {
		this.pk_org = pk_org;
	}

	/**
	 * ���� pk_org_v��Getter����.���������M���汾 ��������:2019/2/25
	 * 
	 * @return nc.vo.vorg.OrgVersionVO
	 */
	public String getPk_org_v() {
		return this.pk_org_v;
	}

	/**
	 * ����pk_org_v��Setter����.���������M���汾 ��������:2019/2/25
	 * 
	 * @param newPk_org_v
	 *            nc.vo.vorg.OrgVersionVO
	 */
	public void setPk_org_v(String pk_org_v) {
		this.pk_org_v = pk_org_v;
	}

	/**
	 * ���� pk_commissiontype��Getter����.��������ίӚ����� ��������:2019/2/25
	 * 
	 * @return java.lang.String
	 */
	public String getPk_commissiontype() {
		return this.pk_commissiontype;
	}

	/**
	 * ����pk_commissiontype��Setter����.��������ίӚ����� ��������:2019/2/25
	 * 
	 * @param newPk_commissiontype
	 *            java.lang.String
	 */
	public void setPk_commissiontype(String pk_commissiontype) {
		this.pk_commissiontype = pk_commissiontype;
	}

	/**
	 * ���� codeprefix��Getter����.��������ίӚ�ξ��aǰ�Y ��������:2019/2/25
	 * 
	 * @return java.lang.String
	 */
	public String getCodeprefix() {
		return this.codeprefix;
	}

	/**
	 * ����codeprefix��Setter����.��������ίӚ�ξ��aǰ�Y ��������:2019/2/25
	 * 
	 * @param newCodeprefix
	 *            java.lang.String
	 */
	public void setCodeprefix(String codeprefix) {
		this.codeprefix = codeprefix;
	}

	/**
	 * ���� billno��Getter����.��������ίӚ�ξ��a ��������:2019/2/25
	 * 
	 * @return java.lang.String
	 */
	public String getBillno() {
		return this.billno;
	}

	/**
	 * ����billno��Setter����.��������ίӚ�ξ��a ��������:2019/2/25
	 * 
	 * @param newBillno
	 *            java.lang.String
	 */
	public void setBillno(String billno) {
		this.billno = billno;
	}

	/**
	 * ���� billname��Getter����.��������ίӚ�����Q ��������:2019/2/25
	 * 
	 * @return java.lang.String
	 */
	public String getBillname() {
		return this.billname;
	}

	/**
	 * ����billname��Setter����.��������ίӚ�����Q ��������:2019/2/25
	 * 
	 * @param newBillname
	 *            java.lang.String
	 */
	public void setBillname(String billname) {
		this.billname = billname;
	}

	/**
	 * ���� pk_owner��Getter����.��������ίӚ��λ ��������:2019/2/25
	 * 
	 * @return nc.vo.org.OrgVO
	 */
	public String getPk_owner() {
		return this.pk_owner;
	}

	/**
	 * ����pk_owner��Setter����.��������ίӚ��λ ��������:2019/2/25
	 * 
	 * @param newPk_owner
	 *            nc.vo.org.OrgVO
	 */
	public void setPk_owner(String pk_owner) {
		this.pk_owner = pk_owner;
	}

	/**
	 * ���� pk_dept��Getter����.�����������T ��������:2019/2/25
	 * 
	 * @return nc.vo.org.DeptVO
	 */
	public String getPk_dept() {
		return this.pk_dept;
	}

	/**
	 * ����pk_dept��Setter����.�����������T ��������:2019/2/25
	 * 
	 * @param newPk_dept
	 *            nc.vo.org.DeptVO
	 */
	public void setPk_dept(String pk_dept) {
		this.pk_dept = pk_dept;
	}

	/**
	 * ���� pk_payer��Getter����.�����������M��λ ��������:2019/2/25
	 * 
	 * @return nc.vo.org.OrgVO
	 */
	public String getPk_payer() {
		return this.pk_payer;
	}

	/**
	 * ����pk_payer��Setter����.�����������M��λ ��������:2019/2/25
	 * 
	 * @param newPk_payer
	 *            nc.vo.org.OrgVO
	 */
	public void setPk_payer(String pk_payer) {
		this.pk_payer = pk_payer;
	}

	/**
	 * ���� contract��Getter����.���������M�� ��������:2019/2/25
	 * 
	 * @return java.lang.String
	 */
	public String getContract() {
		return this.contract;
	}

	/**
	 * ����contract��Setter����.���������M�� ��������:2019/2/25
	 * 
	 * @param newContract
	 *            java.lang.String
	 */
	public void setContract(String contract) {
		this.contract = contract;
	}

	/**
	 * ���� email��Getter����.�������������]�� ��������:2019/2/25
	 * 
	 * @return java.lang.String
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * ����email��Setter����.�������������]�� ��������:2019/2/25
	 * 
	 * @param newEmail
	 *            java.lang.String
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * ���� teleno��Getter����.���������M�Ԓ ��������:2019/2/25
	 * 
	 * @return java.lang.String
	 */
	public String getTeleno() {
		return this.teleno;
	}

	/**
	 * ����teleno��Setter����.���������M�Ԓ ��������:2019/2/25
	 * 
	 * @param newTeleno
	 *            java.lang.String
	 */
	public void setTeleno(String teleno) {
		this.teleno = teleno;
	}

	/**
	 * ���� pk_maincategory��Getter����.���������aƷ��� ��������:2019/2/25
	 * 
	 * @return java.lang.String
	 */
	public String getPk_maincategory() {
		return this.pk_maincategory;
	}

	/**
	 * ����pk_maincategory��Setter����.���������aƷ��� ��������:2019/2/25
	 * 
	 * @param newPk_maincategory
	 *            java.lang.String
	 */
	public void setPk_maincategory(String pk_maincategory) {
		this.pk_maincategory = pk_maincategory;
	}

	/**
	 * ���� pk_subcategory��Getter����.��������������� ��������:2019/2/25
	 * 
	 * @return java.lang.String
	 */
	public String getPk_subcategory() {
		return this.pk_subcategory;
	}

	/**
	 * ����pk_subcategory��Setter����.��������������� ��������:2019/2/25
	 * 
	 * @param newPk_subcategory
	 *            java.lang.String
	 */
	public void setPk_subcategory(String pk_subcategory) {
		this.pk_subcategory = pk_subcategory;
	}

	/**
	 * ���� pk_lastcategory��Getter����.��������������� ��������:2019/2/25
	 * 
	 * @return java.lang.String
	 */
	public String getPk_lastcategory() {
		return this.pk_lastcategory;
	}

	/**
	 * ����pk_lastcategory��Setter����.��������������� ��������:2019/2/25
	 * 
	 * @param newPk_lastcategory
	 *            java.lang.String
	 */
	public void setPk_lastcategory(String pk_lastcategory) {
		this.pk_lastcategory = pk_lastcategory;
	}

	/**
	 * ���� cuserid��Getter����.���������Ɔ��� ��������:2019/2/25
	 * 
	 * @return nc.vo.bd.psn.PsndocVO
	 */
	public String getCuserid() {
		return this.cuserid;
	}

	/**
	 * ����cuserid��Setter����.���������Ɔ��� ��������:2019/2/25
	 * 
	 * @param newCuserid
	 *            nc.vo.bd.psn.PsndocVO
	 */
	public void setCuserid(String cuserid) {
		this.cuserid = cuserid;
	}

	/**
	 * ���� reportformat��Getter����.������������ʽ ��������:2019/2/25
	 * 
	 * @return java.lang.String
	 */
	public String getReportformat() {
		return this.reportformat;
	}

	/**
	 * ����reportformat��Setter����.������������ʽ ��������:2019/2/25
	 * 
	 * @param newReportformat
	 *            java.lang.String
	 */
	public void setReportformat(String reportformat) {
		this.reportformat = reportformat;
	}

	/**
	 * ���� reportlang��Getter����.������������Z�� ��������:2019/2/25
	 * 
	 * @return java.lang.String
	 */
	public String getReportlang() {
		return this.reportlang;
	}

	/**
	 * ����reportlang��Setter����.������������Z�� ��������:2019/2/25
	 * 
	 * @param newReportlang
	 *            java.lang.String
	 */
	public void setReportlang(String reportlang) {
		this.reportlang = reportlang;
	}

	/**
	 * ���� managersendflag��Getter����.�����������܌��˰l���]�� ��������:2019/2/25
	 * 
	 * @return nc.vo.pub.lang.UFUFBoolean
	 */
	public UFBoolean getManagersendflag() {
		return this.managersendflag;
	}

	/**
	 * ����managersendflag��Setter����.�����������܌��˰l���]�� ��������:2019/2/25
	 * 
	 * @param newManagersendflag
	 *            nc.vo.pub.lang.UFUFBoolean
	 */
	public void setManagersendflag(UFBoolean managersendflag) {
		this.managersendflag = managersendflag;
	}

	/**
	 * ���� taskbeginsendflag��Getter����.���������΄��_ʼ�l���]�� ��������:2019/2/25
	 * 
	 * @return nc.vo.pub.lang.UFUFBoolean
	 */
	public UFBoolean getTaskbeginsendflag() {
		return this.taskbeginsendflag;
	}

	/**
	 * ����taskbeginsendflag��Setter����.���������΄��_ʼ�l���]�� ��������:2019/2/25
	 * 
	 * @param newTaskbeginsendflag
	 *            nc.vo.pub.lang.UFUFBoolean
	 */
	public void setTaskbeginsendflag(UFBoolean taskbeginsendflag) {
		this.taskbeginsendflag = taskbeginsendflag;
	}

	/**
	 * ���� taskendsendflag��Getter����.���������΄սY������]�� ��������:2019/2/25
	 * 
	 * @return nc.vo.pub.lang.UFUFBoolean
	 */
	public UFBoolean getTaskendsendflag() {
		return this.taskendsendflag;
	}

	/**
	 * ����taskendsendflag��Setter����.���������΄սY������]�� ��������:2019/2/25
	 * 
	 * @param newTaskendsendflag
	 *            nc.vo.pub.lang.UFUFBoolean
	 */
	public void setTaskendsendflag(UFBoolean taskendsendflag) {
		this.taskendsendflag = taskendsendflag;
	}

	/**
	 * ���� reportsendflag��Getter����.����������溞�l�l���]�� ��������:2019/2/25
	 * 
	 * @return nc.vo.pub.lang.UFUFBoolean
	 */
	public UFBoolean getReportsendflag() {
		return this.reportsendflag;
	}

	/**
	 * ����reportsendflag��Setter����.����������溞�l�l���]�� ��������:2019/2/25
	 * 
	 * @param newReportsendflag
	 *            nc.vo.pub.lang.UFUFBoolean
	 */
	public void setReportsendflag(UFBoolean reportsendflag) {
		this.reportsendflag = reportsendflag;
	}

	/**
	 * ���� savetotemplateflag��Getter����.���������Ƿ񱣴��ģ�� ��������:2019/2/25
	 * 
	 * @return nc.vo.pub.lang.UFUFBoolean
	 */
	public UFBoolean getSavetotemplateflag() {
		return this.savetotemplateflag;
	}

	/**
	 * ����savetotemplateflag��Setter����.���������Ƿ񱣴��ģ�� ��������:2019/2/25
	 * 
	 * @param newSavetotemplateflag
	 *            nc.vo.pub.lang.UFUFBoolean
	 */
	public void setSavetotemplateflag(UFBoolean savetotemplateflag) {
		this.savetotemplateflag = savetotemplateflag;
	}

	/**
	 * ���� receiptsendflag��Getter����.��������Ӌ�M���]������ ��������:2019/2/25
	 * 
	 * @return nc.vo.pub.lang.UFUFBoolean
	 */
	public UFBoolean getReceiptsendflag() {
		return this.receiptsendflag;
	}

	/**
	 * ����receiptsendflag��Setter����.��������Ӌ�M���]������ ��������:2019/2/25
	 * 
	 * @param newReceiptsendflag
	 *            nc.vo.pub.lang.UFUFBoolean
	 */
	public void setReceiptsendflag(UFBoolean receiptsendflag) {
		this.receiptsendflag = receiptsendflag;
	}

	/**
	 * ���� quotaionsendflag��Getter����.����������r���]������ ��������:2019/2/25
	 * 
	 * @return nc.vo.pub.lang.UFUFBoolean
	 */
	public UFBoolean getQuotaionsendflag() {
		return this.quotaionsendflag;
	}

	/**
	 * ����quotaionsendflag��Setter����.����������r���]������ ��������:2019/2/25
	 * 
	 * @param newQuotaionsendflag
	 *            nc.vo.pub.lang.UFUFBoolean
	 */
	public void setQuotaionsendflag(UFBoolean quotaionsendflag) {
		this.quotaionsendflag = quotaionsendflag;
	}

	/**
	 * ���� testaim��Getter����.���������yԇĿ�� ��������:2019/2/25
	 * 
	 * @return java.lang.String
	 */
	public String getTestaim() {
		return this.testaim;
	}

	/**
	 * ����testaim��Setter����.���������yԇĿ�� ��������:2019/2/25
	 * 
	 * @param newTestaim
	 *            java.lang.String
	 */
	public void setTestaim(String testaim) {
		this.testaim = testaim;
	}

	/**
	 * ���� progressneed��Getter����.���������M��Ҫ�� ��������:2019/2/25
	 * 
	 * @return java.lang.String
	 */
	public String getProgressneed() {
		return this.progressneed;
	}

	/**
	 * ����progressneed��Setter����.���������M��Ҫ�� ��������:2019/2/25
	 * 
	 * @param newProgressneed
	 *            java.lang.String
	 */
	public void setProgressneed(String progressneed) {
		this.progressneed = progressneed;
	}

	/**
	 * ���� sampledealtype��Getter����.���������z���Ʒ̎�� ��������:2019/2/25
	 * 
	 * @return java.lang.String
	 */
	public String getSampledealtype() {
		return this.sampledealtype;
	}

	/**
	 * ����sampledealtype��Setter����.���������z���Ʒ̎�� ��������:2019/2/25
	 * 
	 * @param newSampledealtype
	 *            java.lang.String
	 */
	public void setSampledealtype(String sampledealtype) {
		this.sampledealtype = sampledealtype;
	}

	/**
	 * ���� productproperty��Getter����.���������aƷ���� ��������:2019/2/25
	 * 
	 * @return java.lang.String
	 */
	public String getProductproperty() {
		return this.productproperty;
	}

	/**
	 * ����productproperty��Setter����.���������aƷ���� ��������:2019/2/25
	 * 
	 * @param newProductproperty
	 *            java.lang.String
	 */
	public void setProductproperty(String productproperty) {
		this.productproperty = productproperty;
	}

	/**
	 * ���� customername��Getter����.���������͑����Q ��������:2019/2/25
	 * 
	 * @return java.lang.String
	 */
	public String getCustomername() {
		return this.customername;
	}

	/**
	 * ����customername��Setter����.���������͑����Q ��������:2019/2/25
	 * 
	 * @param newCustomername
	 *            java.lang.String
	 */
	public void setCustomername(String customername) {
		this.customername = customername;
	}

	/**
	 * ���� customertype��Getter����.���������͑���� ��������:2019/2/25
	 * 
	 * @return java.lang.String
	 */
	public String getCustomertype() {
		return this.customertype;
	}

	/**
	 * ����customertype��Setter����.���������͑���� ��������:2019/2/25
	 * 
	 * @param newCustomertype
	 *            java.lang.String
	 */
	public void setCustomertype(String customertype) {
		this.customertype = customertype;
	}

	/**
	 * ���� testrequirement��Getter����.���������yԇ���� ��������:2019/2/25
	 * 
	 * @return java.lang.String
	 */
	public String getTestrequirement() {
		return this.testrequirement;
	}

	/**
	 * ����testrequirement��Setter����.���������yԇ���� ��������:2019/2/25
	 * 
	 * @param newTestrequirement
	 *            java.lang.String
	 */
	public void setTestrequirement(String testrequirement) {
		this.testrequirement = testrequirement;
	}

	/**
	 * ���� checkingproperty��Getter����.���������z�y���| ��������:2019/2/25
	 * 
	 * @return java.lang.String
	 */
	public String getCheckingproperty() {
		return this.checkingproperty;
	}

	/**
	 * ����checkingproperty��Setter����.���������z�y���| ��������:2019/2/25
	 * 
	 * @param newCheckingproperty
	 *            java.lang.String
	 */
	public void setCheckingproperty(String checkingproperty) {
		this.checkingproperty = checkingproperty;
	}

	/**
	 * ���� productline��Getter����.�����������a�a�� ��������:2019/2/25
	 * 
	 * @return java.lang.String
	 */
	public String getProductline() {
		return this.productline;
	}

	/**
	 * ����productline��Setter����.�����������a�a�� ��������:2019/2/25
	 * 
	 * @param newProductline
	 *            java.lang.String
	 */
	public void setProductline(String productline) {
		this.productline = productline;
	}

	/**
	 * ���� batchnumber��Getter����.�����������a���� ��������:2019/2/25
	 * 
	 * @return java.lang.String
	 */
	public String getBatchnumber() {
		return this.batchnumber;
	}

	/**
	 * ����batchnumber��Setter����.�����������a���� ��������:2019/2/25
	 * 
	 * @param newBatchnumber
	 *            java.lang.String
	 */
	public void setBatchnumber(String batchnumber) {
		this.batchnumber = batchnumber;
	}

	/**
	 * ���� productdate��Getter����.�����������a���� ��������:2019/2/25
	 * 
	 * @return nc.vo.pub.lang.UFLiteralDate
	 */
	public UFLiteralDate getProductdate() {
		return this.productdate;
	}

	/**
	 * ����productdate��Setter����.�����������a���� ��������:2019/2/25
	 * 
	 * @param newProductdate
	 *            nc.vo.pub.lang.UFLiteralDate
	 */
	public void setProductdate(UFLiteralDate productdate) {
		this.productdate = productdate;
	}

	/**
	 * ���� batchserial��Getter����.�����������a��̖ ��������:2019/2/25
	 * 
	 * @return java.lang.String
	 */
	public String getBatchserial() {
		return this.batchserial;
	}

	/**
	 * ����batchserial��Setter����.�����������a��̖ ��������:2019/2/25
	 * 
	 * @param newBatchserial
	 *            java.lang.String
	 */
	public void setBatchserial(String batchserial) {
		this.batchserial = batchserial;
	}

	/**
	 * ���� identificationtype��Getter����.���������aƷ�b����� ��������:2019/2/25
	 * 
	 * @return java.lang.String
	 */
	public String getIdentificationtype() {
		return this.identificationtype;
	}

	/**
	 * ����identificationtype��Setter����.���������aƷ�b����� ��������:2019/2/25
	 * 
	 * @param newIdentificationtype
	 *            java.lang.String
	 */
	public void setIdentificationtype(String identificationtype) {
		this.identificationtype = identificationtype;
	}

	/**
	 * ���� certificationtype��Getter����.���������J�C��� ��������:2019/2/25
	 * 
	 * @return java.lang.String
	 */
	public String getCertificationtype() {
		return this.certificationtype;
	}

	/**
	 * ����certificationtype��Setter����.���������J�C��� ��������:2019/2/25
	 * 
	 * @param newCertificationtype
	 *            java.lang.String
	 */
	public void setCertificationtype(String certificationtype) {
		this.certificationtype = certificationtype;
	}

	/**
	 * ���� itemnumber��Getter����.���������Ŀ̖ ��������:2019/2/25
	 * 
	 * @return java.lang.String
	 */
	public String getItemnumber() {
		return this.itemnumber;
	}

	/**
	 * ����itemnumber��Setter����.���������Ŀ̖ ��������:2019/2/25
	 * 
	 * @param newItemnumber
	 *            java.lang.String
	 */
	public void setItemnumber(String itemnumber) {
		this.itemnumber = itemnumber;
	}

	/**
	 * ���� ���ɕr�g����Getter����.���������r�g�� ��������:2019/2/25
	 * 
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public UFDateTime getTs() {
		return this.ts;
	}

	/**
	 * �������ɕr�g����Setter����.���������r�g�� ��������:2019/2/25
	 * 
	 * @param newts
	 *            nc.vo.pub.lang.UFDateTime
	 */
	public void setTs(UFDateTime ts) {
		this.ts = ts;
	}

	@Override
	public IVOMeta getMetaData() {
		return VOMetaFactory.getInstance().getVOMeta("qcco.commission_h");
	}
}
