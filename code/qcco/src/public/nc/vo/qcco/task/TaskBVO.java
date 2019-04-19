package nc.vo.qcco.task;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <b> ��̎��Ҫ��������� </b>
 * <p>
 * ��̎����۵�������Ϣ
 * </p>
 * ��������:2019/4/19
 *
 * @author yonyouBQ
 * @version NCPrj ??
 */

public class TaskBVO extends SuperVO {

	/**
	 * �΄������I
	 */
	public String pk_task_b;
	/**
	 * �΄վ�̖
	 */
	public String taskcode;
	/**
	 * �΄����Q
	 */
	public String taskname;
	/**
	 * �������
	 */
	public Integer runorder;
	/**
	 * ��̖
	 */
	public String rowno;
	/**
	 * �yԇ�Y�����Q
	 */
	public String pk_testresultname;
	/**
	 * �yԇ�Y�������Q
	 */
	public String testresultshortname;
	/**
	 * �yԇ�Ŀ
	 */
	public String testitem;
	/**
	 * testnumber
	 */
	public String testnumber;
	/**
	 * ��Ʒ����
	 */
	public String sampleallocation;
	/**
	 * ��Ʒ����
	 */
	public UFDouble samplequantity;
	/**
	 * �˜ʗl��
	 */
	public String standardclause;
	/**
	 * �όӆΓ����I
	 */
	public String pk_task_h;
	/**
	 * �r�g��
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
	 * ���� pk_task_b��Getter����.���������΄������I ��������:2019/4/19
	 *
	 * @return java.lang.String
	 */
	public String getPk_task_b() {
		return this.pk_task_b;
	}

	/**
	 * ����pk_task_b��Setter����.���������΄������I ��������:2019/4/19
	 *
	 * @param newPk_task_b
	 *            java.lang.String
	 */
	public void setPk_task_b(String pk_task_b) {
		this.pk_task_b = pk_task_b;
	}

	/**
	 * ���� taskcode��Getter����.���������΄վ�̖ ��������:2019/4/19
	 *
	 * @return java.lang.String
	 */
	public String getTaskcode() {
		return this.taskcode;
	}

	/**
	 * ����taskcode��Setter����.���������΄վ�̖ ��������:2019/4/19
	 *
	 * @param newTaskcode
	 *            java.lang.String
	 */
	public void setTaskcode(String taskcode) {
		this.taskcode = taskcode;
	}

	/**
	 * ���� taskname��Getter����.���������΄����Q ��������:2019/4/19
	 *
	 * @return nc.vo.bd.defdoc.DefdocVO
	 */
	public String getTaskname() {
		return this.taskname;
	}

	/**
	 * ����taskname��Setter����.���������΄����Q ��������:2019/4/19
	 *
	 * @param newTaskname
	 *            nc.vo.bd.defdoc.DefdocVO
	 */
	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}

	/**
	 * ���� runorder��Getter����.��������������� ��������:2019/4/19
	 *
	 * @return java.lang.Integer
	 */
	public Integer getRunorder() {
		return this.runorder;
	}

	/**
	 * ����runorder��Setter����.��������������� ��������:2019/4/19
	 *
	 * @param newRunorder
	 *            java.lang.Integer
	 */
	public void setRunorder(Integer runorder) {
		this.runorder = runorder;
	}

	/**
	 * ���� rowno��Getter����.����������̖ ��������:2019/4/19
	 *
	 * @return java.lang.String
	 */
	public String getRowno() {
		return this.rowno;
	}

	/**
	 * ����rowno��Setter����.����������̖ ��������:2019/4/19
	 *
	 * @param newRowno
	 *            java.lang.String
	 */
	public void setRowno(String rowno) {
		this.rowno = rowno;
	}

	/**
	 * ���� pk_testresultname��Getter����.���������yԇ�Y�����Q ��������:2019/4/19
	 *
	 * @return java.lang.String
	 */
	public String getPk_testresultname() {
		return this.pk_testresultname;
	}

	/**
	 * ����pk_testresultname��Setter����.���������yԇ�Y�����Q ��������:2019/4/19
	 *
	 * @param newPk_testresultname
	 *            java.lang.String
	 */
	public void setPk_testresultname(String pk_testresultname) {
		this.pk_testresultname = pk_testresultname;
	}

	/**
	 * ���� testresultshortname��Getter����.���������yԇ�Y�������Q ��������:2019/4/19
	 *
	 * @return java.lang.String
	 */
	public String getTestresultshortname() {
		return this.testresultshortname;
	}

	/**
	 * ����testresultshortname��Setter����.���������yԇ�Y�������Q ��������:2019/4/19
	 *
	 * @param newTestresultshortname
	 *            java.lang.String
	 */
	public void setTestresultshortname(String testresultshortname) {
		this.testresultshortname = testresultshortname;
	}

	/**
	 * ���� testitem��Getter����.���������yԇ�Ŀ ��������:2019/4/19
	 *
	 * @return java.lang.String
	 */
	public String getTestitem() {
		return this.testitem;
	}

	/**
	 * ����testitem��Setter����.���������yԇ�Ŀ ��������:2019/4/19
	 *
	 * @param newTestitem
	 *            java.lang.String
	 */
	public void setTestitem(String testitem) {
		this.testitem = testitem;
	}

	/**
	 * ���� testnumber��Getter����.��������testnumber ��������:2019/4/19
	 *
	 * @return java.lang.String
	 */
	public String getTestnumber() {
		return this.testnumber;
	}

	/**
	 * ����testnumber��Setter����.��������testnumber ��������:2019/4/19
	 *
	 * @param newTestnumber
	 *            java.lang.String
	 */
	public void setTestnumber(String testnumber) {
		this.testnumber = testnumber;
	}

	/**
	 * ���� sampleallocation��Getter����.����������Ʒ���� ��������:2019/4/19
	 *
	 * @return java.lang.String
	 */
	public String getSampleallocation() {
		return this.sampleallocation;
	}

	/**
	 * ����sampleallocation��Setter����.����������Ʒ���� ��������:2019/4/19
	 *
	 * @param newSampleallocation
	 *            java.lang.String
	 */
	public void setSampleallocation(String sampleallocation) {
		this.sampleallocation = sampleallocation;
	}

	/**
	 * ���� samplequantity��Getter����.����������Ʒ���� ��������:2019/4/19
	 *
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public UFDouble getSamplequantity() {
		return this.samplequantity;
	}

	/**
	 * ����samplequantity��Setter����.����������Ʒ���� ��������:2019/4/19
	 *
	 * @param newSamplequantity
	 *            nc.vo.pub.lang.UFDouble
	 */
	public void setSamplequantity(UFDouble samplequantity) {
		this.samplequantity = samplequantity;
	}

	/**
	 * ���� standardclause��Getter����.���������˜ʗl�� ��������:2019/4/19
	 *
	 * @return java.lang.String
	 */
	public String getStandardclause() {
		return this.standardclause;
	}

	/**
	 * ����standardclause��Setter����.���������˜ʗl�� ��������:2019/4/19
	 *
	 * @param newStandardclause
	 *            java.lang.String
	 */
	public void setStandardclause(String standardclause) {
		this.standardclause = standardclause;
	}

	/**
	 * ���� �����ό����I��Getter����.���������ό����I ��������:2019/4/19
	 *
	 * @return String
	 */
	public String getPk_task_h() {
		return this.pk_task_h;
	}

	/**
	 * ���������ό����I��Setter����.���������ό����I ��������:2019/4/19
	 *
	 * @param newPk_task_h
	 *            String
	 */
	public void setPk_task_h(String pk_task_h) {
		this.pk_task_h = pk_task_h;
	}

	/**
	 * ���� ���ɕr�g����Getter����.���������r�g�� ��������:2019/4/19
	 *
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public UFDateTime getTs() {
		return this.ts;
	}

	/**
	 * �������ɕr�g����Setter����.���������r�g�� ��������:2019/4/19
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
