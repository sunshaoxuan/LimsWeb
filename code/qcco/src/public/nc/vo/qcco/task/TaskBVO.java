package nc.vo.qcco.task;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <b> 此處簡要描述此類功能 </b>
 * <p>
 * 此處添加累的描述信息
 * </p>
 * 創建日期:2019/4/19
 *
 * @author yonyouBQ
 * @version NCPrj ??
 */

public class TaskBVO extends SuperVO {

	/**
	 * 任務行主鍵
	 */
	public String pk_task_b;
	/**
	 * 任務編號
	 */
	public String taskcode;
	/**
	 * 任務名稱
	 */
	public String taskname;
	/**
	 * 執行順序
	 */
	public Integer runorder;
	/**
	 * 行號
	 */
	public String rowno;
	/**
	 * 測試結果名稱
	 */
	public String pk_testresultname;
	/**
	 * 測試結果短名稱
	 */
	public String testresultshortname;
	/**
	 * 測試項目
	 */
	public String testitem;
	/**
	 * testnumber
	 */
	public String testnumber;
	/**
	 * 樣品分配
	 */
	public String sampleallocation;
	/**
	 * 樣品數量
	 */
	public UFDouble samplequantity;
	/**
	 * 標準條款
	 */
	public String standardclause;
	/**
	 * 上層單據主鍵
	 */
	public String pk_task_h;
	/**
	 * 時間戳
	 */
	public UFDateTime ts;

	public Integer Dr;
	public TaskRVO[] pk_task_r;
	public TaskSVO[] pk_task_s;

	public TaskSVO[] getPk_task_s() {
		return pk_task_s;
	}

	public void setPk_task_s(TaskSVO[] pk_task_s) {
		this.pk_task_s = pk_task_s;
	}

	public Integer getDr() {
		return Dr;
	}

	public void setDr(Integer dr) {
		Dr = dr;
	}

	public void setPk_task_r(TaskRVO[] originGrandvos) {
		pk_task_r = originGrandvos;
	}

	public TaskRVO[] getPk_task_r() {
		return pk_task_r;
	}

	/**
	 * 屬性 pk_task_b的Getter方法.屬性名：任務行主鍵 創建日期:2019/4/19
	 *
	 * @return java.lang.String
	 */
	public String getPk_task_b() {
		return this.pk_task_b;
	}

	/**
	 * 屬性pk_task_b的Setter方法.屬性名：任務行主鍵 創建日期:2019/4/19
	 *
	 * @param newPk_task_b
	 *            java.lang.String
	 */
	public void setPk_task_b(String pk_task_b) {
		this.pk_task_b = pk_task_b;
	}

	/**
	 * 屬性 taskcode的Getter方法.屬性名：任務編號 創建日期:2019/4/19
	 *
	 * @return java.lang.String
	 */
	public String getTaskcode() {
		return this.taskcode;
	}

	/**
	 * 屬性taskcode的Setter方法.屬性名：任務編號 創建日期:2019/4/19
	 *
	 * @param newTaskcode
	 *            java.lang.String
	 */
	public void setTaskcode(String taskcode) {
		this.taskcode = taskcode;
	}

	/**
	 * 屬性 taskname的Getter方法.屬性名：任務名稱 創建日期:2019/4/19
	 *
	 * @return nc.vo.bd.defdoc.DefdocVO
	 */
	public String getTaskname() {
		return this.taskname;
	}

	/**
	 * 屬性taskname的Setter方法.屬性名：任務名稱 創建日期:2019/4/19
	 *
	 * @param newTaskname
	 *            nc.vo.bd.defdoc.DefdocVO
	 */
	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}

	/**
	 * 屬性 runorder的Getter方法.屬性名：執行順序 創建日期:2019/4/19
	 *
	 * @return java.lang.Integer
	 */
	public Integer getRunorder() {
		return this.runorder;
	}

	/**
	 * 屬性runorder的Setter方法.屬性名：執行順序 創建日期:2019/4/19
	 *
	 * @param newRunorder
	 *            java.lang.Integer
	 */
	public void setRunorder(Integer runorder) {
		this.runorder = runorder;
	}

	/**
	 * 屬性 rowno的Getter方法.屬性名：行號 創建日期:2019/4/19
	 *
	 * @return java.lang.String
	 */
	public String getRowno() {
		return this.rowno;
	}

	/**
	 * 屬性rowno的Setter方法.屬性名：行號 創建日期:2019/4/19
	 *
	 * @param newRowno
	 *            java.lang.String
	 */
	public void setRowno(String rowno) {
		this.rowno = rowno;
	}

	/**
	 * 屬性 pk_testresultname的Getter方法.屬性名：測試結果名稱 創建日期:2019/4/19
	 *
	 * @return java.lang.String
	 */
	public String getPk_testresultname() {
		return this.pk_testresultname;
	}

	/**
	 * 屬性pk_testresultname的Setter方法.屬性名：測試結果名稱 創建日期:2019/4/19
	 *
	 * @param newPk_testresultname
	 *            java.lang.String
	 */
	public void setPk_testresultname(String pk_testresultname) {
		this.pk_testresultname = pk_testresultname;
	}

	/**
	 * 屬性 testresultshortname的Getter方法.屬性名：測試結果短名稱 創建日期:2019/4/19
	 *
	 * @return java.lang.String
	 */
	public String getTestresultshortname() {
		return this.testresultshortname;
	}

	/**
	 * 屬性testresultshortname的Setter方法.屬性名：測試結果短名稱 創建日期:2019/4/19
	 *
	 * @param newTestresultshortname
	 *            java.lang.String
	 */
	public void setTestresultshortname(String testresultshortname) {
		this.testresultshortname = testresultshortname;
	}

	/**
	 * 屬性 testitem的Getter方法.屬性名：測試項目 創建日期:2019/4/19
	 *
	 * @return java.lang.String
	 */
	public String getTestitem() {
		return this.testitem;
	}

	/**
	 * 屬性testitem的Setter方法.屬性名：測試項目 創建日期:2019/4/19
	 *
	 * @param newTestitem
	 *            java.lang.String
	 */
	public void setTestitem(String testitem) {
		this.testitem = testitem;
	}

	/**
	 * 屬性 testnumber的Getter方法.屬性名：testnumber 創建日期:2019/4/19
	 *
	 * @return java.lang.String
	 */
	public String getTestnumber() {
		return this.testnumber;
	}

	/**
	 * 屬性testnumber的Setter方法.屬性名：testnumber 創建日期:2019/4/19
	 *
	 * @param newTestnumber
	 *            java.lang.String
	 */
	public void setTestnumber(String testnumber) {
		this.testnumber = testnumber;
	}

	/**
	 * 屬性 sampleallocation的Getter方法.屬性名：樣品分配 創建日期:2019/4/19
	 *
	 * @return java.lang.String
	 */
	public String getSampleallocation() {
		return this.sampleallocation;
	}

	/**
	 * 屬性sampleallocation的Setter方法.屬性名：樣品分配 創建日期:2019/4/19
	 *
	 * @param newSampleallocation
	 *            java.lang.String
	 */
	public void setSampleallocation(String sampleallocation) {
		this.sampleallocation = sampleallocation;
	}

	/**
	 * 屬性 samplequantity的Getter方法.屬性名：樣品數量 創建日期:2019/4/19
	 *
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public UFDouble getSamplequantity() {
		return this.samplequantity;
	}

	/**
	 * 屬性samplequantity的Setter方法.屬性名：樣品數量 創建日期:2019/4/19
	 *
	 * @param newSamplequantity
	 *            nc.vo.pub.lang.UFDouble
	 */
	public void setSamplequantity(UFDouble samplequantity) {
		this.samplequantity = samplequantity;
	}

	/**
	 * 屬性 standardclause的Getter方法.屬性名：標準條款 創建日期:2019/4/19
	 *
	 * @return java.lang.String
	 */
	public String getStandardclause() {
		return this.standardclause;
	}

	/**
	 * 屬性standardclause的Setter方法.屬性名：標準條款 創建日期:2019/4/19
	 *
	 * @param newStandardclause
	 *            java.lang.String
	 */
	public void setStandardclause(String standardclause) {
		this.standardclause = standardclause;
	}

	/**
	 * 屬性 生成上層主鍵的Getter方法.屬性名：上層主鍵 創建日期:2019/4/19
	 *
	 * @return String
	 */
	public String getPk_task_h() {
		return this.pk_task_h;
	}

	/**
	 * 屬性生成上層主鍵的Setter方法.屬性名：上層主鍵 創建日期:2019/4/19
	 *
	 * @param newPk_task_h
	 *            String
	 */
	public void setPk_task_h(String pk_task_h) {
		this.pk_task_h = pk_task_h;
	}

	/**
	 * 屬性 生成時間戳的Getter方法.屬性名：時間戳 創建日期:2019/4/19
	 *
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public UFDateTime getTs() {
		return this.ts;
	}

	/**
	 * 屬性生成時間戳的Setter方法.屬性名：時間戳 創建日期:2019/4/19
	 *
	 * @param newts
	 *            nc.vo.pub.lang.UFDateTime
	 */
	public void setTs(UFDateTime ts) {
		this.ts = ts;
	}

	@Override
	public IVOMeta getMetaData() {
		return VOMetaFactory.getInstance().getVOMeta("qcco.task_b");
	}
}
