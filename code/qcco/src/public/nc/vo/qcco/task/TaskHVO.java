package nc.vo.qcco.task;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <b> 此處簡要描述此類功能 </b>
 * <p>
 * 此處添加累的描述信息
 * </p>
 * 創建日期:2019/4/13
 * 
 * @author yonyouBQ
 * @version NCPrj ??
 */

public class TaskHVO extends SuperVO {

	/**
	 * 主表主鍵
	 */
	public String pk_task_h;
	/**
	 * 委託單主鍵
	 */
	public String pk_commission_h;
	/**
	 * 集團
	 */
	public String pk_group;
	/**
	 * 組織
	 */
	public String pk_org;
	/**
	 * 組織版本
	 */
	public String pk_org_v;
	/**
	 * 制單日期
	 */
	public UFDate dbilldate;
	/**
	 * 創建人
	 */
	public String creator;
	/**
	 * 創建時間
	 */
	public UFDateTime creationtime;
	/**
	 * 修改人
	 */
	public String modifier;
	/**
	 * 修改時間
	 */
	public UFDateTime modifiedtime;
	/**
	 * 制單時間
	 */
	public UFDateTime maketime;
	/**
	 * 最後修改時間
	 */
	public UFDateTime lastmaketime;
	/**
	 * 單據ID
	 */
	public String billid;
	/**
	 * 單據號
	 */
	public String billno;
	/**
	 * 業務類型
	 */
	public String busitype;
	/**
	 * 制單人
	 */
	public String billmaker;
	/**
	 * 審批人
	 */
	public String approver;
	/**
	 * 審批狀態
	 */
	public Integer approvestatus;
	/**
	 * 審批批語
	 */
	public String approvenote;
	/**
	 * 審批時間
	 */
	public UFDateTime approvedate;
	/**
	 * 交易類型
	 */
	public String transtype;
	/**
	 * 單據類型
	 */
	public String billtype;
	/**
	 * 交易類型pk
	 */
	public String transtypepk;
	/**
	 * 來源單據類型
	 */
	public String srcbilltype;
	/**
	 * 來源單據id
	 */
	public String srcbillid;
	/**
	 * 修訂枚舉
	 */
	public Integer emendenum;
	/**
	 * 單據版本pk
	 */
	public String billversionpk;
	/**
	 * 時間戳
	 */
	public UFDateTime ts;

	/**
	 * 屬性 pk_task_h的Getter方法.屬性名：主表主鍵 創建日期:2019/4/13
	 * 
	 * @return java.lang.String
	 */
	public String getPk_task_h() {
		return this.pk_task_h;
	}

	/**
	 * 屬性pk_task_h的Setter方法.屬性名：主表主鍵 創建日期:2019/4/13
	 * 
	 * @param newPk_task_h
	 *            java.lang.String
	 */
	public void setPk_task_h(String pk_task_h) {
		this.pk_task_h = pk_task_h;
	}

	/**
	 * 屬性 pk_commission_h的Getter方法.屬性名：委託單主鍵 創建日期:2019/4/13
	 * 
	 * @return nc.vo.qcco.commission.CommissionHVO
	 */
	public String getPk_commission_h() {
		return this.pk_commission_h;
	}

	/**
	 * 屬性pk_commission_h的Setter方法.屬性名：委託單主鍵 創建日期:2019/4/13
	 * 
	 * @param newPk_commission_h
	 *            nc.vo.qcco.commission.CommissionHVO
	 */
	public void setPk_commission_h(String pk_commission_h) {
		this.pk_commission_h = pk_commission_h;
	}

	/**
	 * 屬性 pk_group的Getter方法.屬性名：集團 創建日期:2019/4/13
	 * 
	 * @return nc.vo.org.GroupVO
	 */
	public String getPk_group() {
		return this.pk_group;
	}

	/**
	 * 屬性pk_group的Setter方法.屬性名：集團 創建日期:2019/4/13
	 * 
	 * @param newPk_group
	 *            nc.vo.org.GroupVO
	 */
	public void setPk_group(String pk_group) {
		this.pk_group = pk_group;
	}

	/**
	 * 屬性 pk_org的Getter方法.屬性名：組織 創建日期:2019/4/13
	 * 
	 * @return nc.vo.org.OrgVO
	 */
	public String getPk_org() {
		return this.pk_org;
	}

	/**
	 * 屬性pk_org的Setter方法.屬性名：組織 創建日期:2019/4/13
	 * 
	 * @param newPk_org
	 *            nc.vo.org.OrgVO
	 */
	public void setPk_org(String pk_org) {
		this.pk_org = pk_org;
	}

	/**
	 * 屬性 pk_org_v的Getter方法.屬性名：組織版本 創建日期:2019/4/13
	 * 
	 * @return nc.vo.vorg.OrgVersionVO
	 */
	public String getPk_org_v() {
		return this.pk_org_v;
	}

	/**
	 * 屬性pk_org_v的Setter方法.屬性名：組織版本 創建日期:2019/4/13
	 * 
	 * @param newPk_org_v
	 *            nc.vo.vorg.OrgVersionVO
	 */
	public void setPk_org_v(String pk_org_v) {
		this.pk_org_v = pk_org_v;
	}

	/**
	 * 屬性 dbilldate的Getter方法.屬性名：制單日期 創建日期:2019/4/13
	 * 
	 * @return nc.vo.pub.lang.UFDate
	 */
	public UFDate getDbilldate() {
		return this.dbilldate;
	}

	/**
	 * 屬性dbilldate的Setter方法.屬性名：制單日期 創建日期:2019/4/13
	 * 
	 * @param newDbilldate
	 *            nc.vo.pub.lang.UFDate
	 */
	public void setDbilldate(UFDate dbilldate) {
		this.dbilldate = dbilldate;
	}

	/**
	 * 屬性 creator的Getter方法.屬性名：創建人 創建日期:2019/4/13
	 * 
	 * @return nc.vo.sm.UserVO
	 */
	public String getCreator() {
		return this.creator;
	}

	/**
	 * 屬性creator的Setter方法.屬性名：創建人 創建日期:2019/4/13
	 * 
	 * @param newCreator
	 *            nc.vo.sm.UserVO
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}

	/**
	 * 屬性 creationtime的Getter方法.屬性名：創建時間 創建日期:2019/4/13
	 * 
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public UFDateTime getCreationtime() {
		return this.creationtime;
	}

	/**
	 * 屬性creationtime的Setter方法.屬性名：創建時間 創建日期:2019/4/13
	 * 
	 * @param newCreationtime
	 *            nc.vo.pub.lang.UFDateTime
	 */
	public void setCreationtime(UFDateTime creationtime) {
		this.creationtime = creationtime;
	}

	/**
	 * 屬性 modifier的Getter方法.屬性名：修改人 創建日期:2019/4/13
	 * 
	 * @return nc.vo.sm.UserVO
	 */
	public String getModifier() {
		return this.modifier;
	}

	/**
	 * 屬性modifier的Setter方法.屬性名：修改人 創建日期:2019/4/13
	 * 
	 * @param newModifier
	 *            nc.vo.sm.UserVO
	 */
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	/**
	 * 屬性 modifiedtime的Getter方法.屬性名：修改時間 創建日期:2019/4/13
	 * 
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public UFDateTime getModifiedtime() {
		return this.modifiedtime;
	}

	/**
	 * 屬性modifiedtime的Setter方法.屬性名：修改時間 創建日期:2019/4/13
	 * 
	 * @param newModifiedtime
	 *            nc.vo.pub.lang.UFDateTime
	 */
	public void setModifiedtime(UFDateTime modifiedtime) {
		this.modifiedtime = modifiedtime;
	}

	/**
	 * 屬性 maketime的Getter方法.屬性名：制單時間 創建日期:2019/4/13
	 * 
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public UFDateTime getMaketime() {
		return this.maketime;
	}

	/**
	 * 屬性maketime的Setter方法.屬性名：制單時間 創建日期:2019/4/13
	 * 
	 * @param newMaketime
	 *            nc.vo.pub.lang.UFDateTime
	 */
	public void setMaketime(UFDateTime maketime) {
		this.maketime = maketime;
	}

	/**
	 * 屬性 lastmaketime的Getter方法.屬性名：最後修改時間 創建日期:2019/4/13
	 * 
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public UFDateTime getLastmaketime() {
		return this.lastmaketime;
	}

	/**
	 * 屬性lastmaketime的Setter方法.屬性名：最後修改時間 創建日期:2019/4/13
	 * 
	 * @param newLastmaketime
	 *            nc.vo.pub.lang.UFDateTime
	 */
	public void setLastmaketime(UFDateTime lastmaketime) {
		this.lastmaketime = lastmaketime;
	}

	/**
	 * 屬性 billid的Getter方法.屬性名：單據ID 創建日期:2019/4/13
	 * 
	 * @return java.lang.String
	 */
	public String getBillid() {
		return this.billid;
	}

	/**
	 * 屬性billid的Setter方法.屬性名：單據ID 創建日期:2019/4/13
	 * 
	 * @param newBillid
	 *            java.lang.String
	 */
	public void setBillid(String billid) {
		this.billid = billid;
	}

	/**
	 * 屬性 billno的Getter方法.屬性名：單據號 創建日期:2019/4/13
	 * 
	 * @return java.lang.String
	 */
	public String getBillno() {
		return this.billno;
	}

	/**
	 * 屬性billno的Setter方法.屬性名：單據號 創建日期:2019/4/13
	 * 
	 * @param newBillno
	 *            java.lang.String
	 */
	public void setBillno(String billno) {
		this.billno = billno;
	}

	/**
	 * 屬性 busitype的Getter方法.屬性名：業務類型 創建日期:2019/4/13
	 * 
	 * @return java.lang.String
	 */
	public String getBusitype() {
		return this.busitype;
	}

	/**
	 * 屬性busitype的Setter方法.屬性名：業務類型 創建日期:2019/4/13
	 * 
	 * @param newBusitype
	 *            java.lang.String
	 */
	public void setBusitype(String busitype) {
		this.busitype = busitype;
	}

	/**
	 * 屬性 billmaker的Getter方法.屬性名：制單人 創建日期:2019/4/13
	 * 
	 * @return java.lang.String
	 */
	public String getBillmaker() {
		return this.billmaker;
	}

	/**
	 * 屬性billmaker的Setter方法.屬性名：制單人 創建日期:2019/4/13
	 * 
	 * @param newBillmaker
	 *            java.lang.String
	 */
	public void setBillmaker(String billmaker) {
		this.billmaker = billmaker;
	}

	/**
	 * 屬性 approver的Getter方法.屬性名：審批人 創建日期:2019/4/13
	 * 
	 * @return java.lang.String
	 */
	public String getApprover() {
		return this.approver;
	}

	/**
	 * 屬性approver的Setter方法.屬性名：審批人 創建日期:2019/4/13
	 * 
	 * @param newApprover
	 *            java.lang.String
	 */
	public void setApprover(String approver) {
		this.approver = approver;
	}

	/**
	 * 屬性 approvestatus的Getter方法.屬性名：審批狀態 創建日期:2019/4/13
	 * 
	 * @return nc.vo.pub.pf.BillStatusEnum
	 */
	public Integer getApprovestatus() {
		return this.approvestatus;
	}

	/**
	 * 屬性approvestatus的Setter方法.屬性名：審批狀態 創建日期:2019/4/13
	 * 
	 * @param newApprovestatus
	 *            nc.vo.pub.pf.BillStatusEnum
	 */
	public void setApprovestatus(Integer approvestatus) {
		this.approvestatus = approvestatus;
	}

	/**
	 * 屬性 approvenote的Getter方法.屬性名：審批批語 創建日期:2019/4/13
	 * 
	 * @return java.lang.String
	 */
	public String getApprovenote() {
		return this.approvenote;
	}

	/**
	 * 屬性approvenote的Setter方法.屬性名：審批批語 創建日期:2019/4/13
	 * 
	 * @param newApprovenote
	 *            java.lang.String
	 */
	public void setApprovenote(String approvenote) {
		this.approvenote = approvenote;
	}

	/**
	 * 屬性 approvedate的Getter方法.屬性名：審批時間 創建日期:2019/4/13
	 * 
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public UFDateTime getApprovedate() {
		return this.approvedate;
	}

	/**
	 * 屬性approvedate的Setter方法.屬性名：審批時間 創建日期:2019/4/13
	 * 
	 * @param newApprovedate
	 *            nc.vo.pub.lang.UFDateTime
	 */
	public void setApprovedate(UFDateTime approvedate) {
		this.approvedate = approvedate;
	}

	/**
	 * 屬性 transtype的Getter方法.屬性名：交易類型 創建日期:2019/4/13
	 * 
	 * @return java.lang.String
	 */
	public String getTranstype() {
		return this.transtype;
	}

	/**
	 * 屬性transtype的Setter方法.屬性名：交易類型 創建日期:2019/4/13
	 * 
	 * @param newTranstype
	 *            java.lang.String
	 */
	public void setTranstype(String transtype) {
		this.transtype = transtype;
	}

	/**
	 * 屬性 billtype的Getter方法.屬性名：單據類型 創建日期:2019/4/13
	 * 
	 * @return java.lang.String
	 */
	public String getBilltype() {
		return this.billtype;
	}

	/**
	 * 屬性billtype的Setter方法.屬性名：單據類型 創建日期:2019/4/13
	 * 
	 * @param newBilltype
	 *            java.lang.String
	 */
	public void setBilltype(String billtype) {
		this.billtype = billtype;
	}

	/**
	 * 屬性 transtypepk的Getter方法.屬性名：交易類型pk 創建日期:2019/4/13
	 * 
	 * @return java.lang.String
	 */
	public String getTranstypepk() {
		return this.transtypepk;
	}

	/**
	 * 屬性transtypepk的Setter方法.屬性名：交易類型pk 創建日期:2019/4/13
	 * 
	 * @param newTranstypepk
	 *            java.lang.String
	 */
	public void setTranstypepk(String transtypepk) {
		this.transtypepk = transtypepk;
	}

	/**
	 * 屬性 srcbilltype的Getter方法.屬性名：來源單據類型 創建日期:2019/4/13
	 * 
	 * @return java.lang.String
	 */
	public String getSrcbilltype() {
		return this.srcbilltype;
	}

	/**
	 * 屬性srcbilltype的Setter方法.屬性名：來源單據類型 創建日期:2019/4/13
	 * 
	 * @param newSrcbilltype
	 *            java.lang.String
	 */
	public void setSrcbilltype(String srcbilltype) {
		this.srcbilltype = srcbilltype;
	}

	/**
	 * 屬性 srcbillid的Getter方法.屬性名：來源單據id 創建日期:2019/4/13
	 * 
	 * @return java.lang.String
	 */
	public String getSrcbillid() {
		return this.srcbillid;
	}

	/**
	 * 屬性srcbillid的Setter方法.屬性名：來源單據id 創建日期:2019/4/13
	 * 
	 * @param newSrcbillid
	 *            java.lang.String
	 */
	public void setSrcbillid(String srcbillid) {
		this.srcbillid = srcbillid;
	}

	/**
	 * 屬性 emendenum的Getter方法.屬性名：修訂枚舉 創建日期:2019/4/13
	 * 
	 * @return java.lang.Integer
	 */
	public Integer getEmendenum() {
		return this.emendenum;
	}

	/**
	 * 屬性emendenum的Setter方法.屬性名：修訂枚舉 創建日期:2019/4/13
	 * 
	 * @param newEmendenum
	 *            java.lang.Integer
	 */
	public void setEmendenum(Integer emendenum) {
		this.emendenum = emendenum;
	}

	/**
	 * 屬性 billversionpk的Getter方法.屬性名：單據版本pk 創建日期:2019/4/13
	 * 
	 * @return java.lang.String
	 */
	public String getBillversionpk() {
		return this.billversionpk;
	}

	/**
	 * 屬性billversionpk的Setter方法.屬性名：單據版本pk 創建日期:2019/4/13
	 * 
	 * @param newBillversionpk
	 *            java.lang.String
	 */
	public void setBillversionpk(String billversionpk) {
		this.billversionpk = billversionpk;
	}

	/**
	 * 屬性 生成時間戳的Getter方法.屬性名：時間戳 創建日期:2019/4/13
	 * 
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public UFDateTime getTs() {
		return this.ts;
	}

	/**
	 * 屬性生成時間戳的Setter方法.屬性名：時間戳 創建日期:2019/4/13
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
