package nc.vo.qcco.commission;

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
 * ��������:2019/3/25
 * 
 * @author yonyouBQ
 * @version NCPrj ??
 */

public class CommissionBVO extends SuperVO {

	/**
	 * ��Ʒ�����I
	 */
	public String pk_commission_b;
	/**
	 * ��̖
	 */
	public String rowno;
	/**
	 * �aƷϵ��
	 */
	public String pk_productserial;
	/**
	 * ��I�˜�
	 */
	public String pk_enterprisestandard;
	/**
	 * Ҏ����̖
	 */
	public String typeno;
	/**
	 * Ҏ��̖
	 */
	public String pk_productspec;
	/**
	 * �Y�����
	 */
	public String pk_structuretype;
	/**
	 * �|�c���
	 */
	public String pk_contacttype;
	/**
	 * ��Ʒ����
	 */
	public UFDouble quantity;
	/**
	 * �u����
	 */
	public String manufacturer;
	/**
	 * �|�c��̖
	 */
	public String pk_contactbrand;
	/**
	 * �|�c��̖
	 */
	public String contactmodel;
	/**
	 * �ض�
	 */
	public String pk_productstage;
	/**
	 * ��Ʒ�M�e
	 */
	public String pk_samplegroup;
	/**
	 * ���ǰ����
	 */
	public String analysisref;
	/**
	 * ������Ϣ
	 */
	public String otherinfo;
	/**
	 * �όӆΓ����I
	 */
	public String pk_commission_h;
	/**
	 * �r�g��
	 */
	public UFDateTime ts;

	public CommissionRVO[] pk_commission_r;

	/**
	 * ���� pk_commission_b��Getter����.����������Ʒ�����I ��������:2019/3/25
	 * 
	 * @return java.lang.String
	 */
	public String getPk_commission_b() {
		return this.pk_commission_b;
	}

	/**
	 * ����pk_commission_b��Setter����.����������Ʒ�����I ��������:2019/3/25
	 * 
	 * @param newPk_commission_b
	 *            java.lang.String
	 */
	public void setPk_commission_b(String pk_commission_b) {
		this.pk_commission_b = pk_commission_b;
	}

	/**
	 * ���� rowno��Getter����.����������̖ ��������:2019/3/25
	 * 
	 * @return java.lang.String
	 */
	public String getRowno() {
		return this.rowno;
	}

	/**
	 * ����rowno��Setter����.����������̖ ��������:2019/3/25
	 * 
	 * @param newRowno
	 *            java.lang.String
	 */
	public void setRowno(String rowno) {
		this.rowno = rowno;
	}

	/**
	 * ���� pk_productserial��Getter����.���������aƷϵ�� ��������:2019/3/25
	 * 
	 * @return nc.vo.bd.defdoc.DefdocVO
	 */
	public String getPk_productserial() {
		return this.pk_productserial;
	}

	/**
	 * ����pk_productserial��Setter����.���������aƷϵ�� ��������:2019/3/25
	 * 
	 * @param newPk_productserial
	 *            nc.vo.bd.defdoc.DefdocVO
	 */
	public void setPk_productserial(String pk_productserial) {
		this.pk_productserial = pk_productserial;
	}

	/**
	 * ���� pk_enterprisestandard��Getter����.����������I�˜� ��������:2019/3/25
	 * 
	 * @return java.lang.String
	 */
	public String getPk_enterprisestandard() {
		return this.pk_enterprisestandard;
	}

	/**
	 * ����pk_enterprisestandard��Setter����.����������I�˜� ��������:2019/3/25
	 * 
	 * @param newPk_enterprisestandard
	 *            java.lang.String
	 */
	public void setPk_enterprisestandard(String pk_enterprisestandard) {
		this.pk_enterprisestandard = pk_enterprisestandard;
	}

	/**
	 * ���� typeno��Getter����.��������Ҏ����̖ ��������:2019/3/25
	 * 
	 * @return java.lang.String
	 */
	public String getTypeno() {
		return this.typeno;
	}

	/**
	 * ����typeno��Setter����.��������Ҏ����̖ ��������:2019/3/25
	 * 
	 * @param newTypeno
	 *            java.lang.String
	 */
	public void setTypeno(String typeno) {
		this.typeno = typeno;
	}

	/**
	 * ���� pk_productspec��Getter����.��������Ҏ��̖ ��������:2019/3/25
	 * 
	 * @return java.lang.String
	 */
	public String getPk_productspec() {
		return this.pk_productspec;
	}

	/**
	 * ����pk_productspec��Setter����.��������Ҏ��̖ ��������:2019/3/25
	 * 
	 * @param newPk_productspec
	 *            java.lang.String
	 */
	public void setPk_productspec(String pk_productspec) {
		this.pk_productspec = pk_productspec;
	}

	/**
	 * ���� pk_structuretype��Getter����.���������Y����� ��������:2019/3/25
	 * 
	 * @return java.lang.String
	 */
	public String getPk_structuretype() {
		return this.pk_structuretype;
	}

	/**
	 * ����pk_structuretype��Setter����.���������Y����� ��������:2019/3/25
	 * 
	 * @param newPk_structuretype
	 *            java.lang.String
	 */
	public void setPk_structuretype(String pk_structuretype) {
		this.pk_structuretype = pk_structuretype;
	}

	/**
	 * ���� pk_contacttype��Getter����.���������|�c��� ��������:2019/3/25
	 * 
	 * @return java.lang.String
	 */
	public String getPk_contacttype() {
		return this.pk_contacttype;
	}

	/**
	 * ����pk_contacttype��Setter����.���������|�c��� ��������:2019/3/25
	 * 
	 * @param newPk_contacttype
	 *            java.lang.String
	 */
	public void setPk_contacttype(String pk_contacttype) {
		this.pk_contacttype = pk_contacttype;
	}

	/**
	 * ���� quantity��Getter����.����������Ʒ���� ��������:2019/3/25
	 * 
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public UFDouble getQuantity() {
		return this.quantity;
	}

	/**
	 * ����quantity��Setter����.����������Ʒ���� ��������:2019/3/25
	 * 
	 * @param newQuantity
	 *            nc.vo.pub.lang.UFDouble
	 */
	public void setQuantity(UFDouble quantity) {
		this.quantity = quantity;
	}

	/**
	 * ���� manufacturer��Getter����.���������u���� ��������:2019/3/25
	 * 
	 * @return java.lang.String
	 */
	public String getManufacturer() {
		return this.manufacturer;
	}

	/**
	 * ����manufacturer��Setter����.���������u���� ��������:2019/3/25
	 * 
	 * @param newManufacturer
	 *            java.lang.String
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	/**
	 * ���� pk_contactbrand��Getter����.���������|�c��̖ ��������:2019/3/25
	 * 
	 * @return nc.vo.bd.defdoc.DefdocVO
	 */
	public String getPk_contactbrand() {
		return this.pk_contactbrand;
	}

	/**
	 * ����pk_contactbrand��Setter����.���������|�c��̖ ��������:2019/3/25
	 * 
	 * @param newPk_contactbrand
	 *            nc.vo.bd.defdoc.DefdocVO
	 */
	public void setPk_contactbrand(String pk_contactbrand) {
		this.pk_contactbrand = pk_contactbrand;
	}

	/**
	 * ���� contactmodel��Getter����.���������|�c��̖ ��������:2019/3/25
	 * 
	 * @return java.lang.String
	 */
	public String getContactmodel() {
		return this.contactmodel;
	}

	/**
	 * ����contactmodel��Setter����.���������|�c��̖ ��������:2019/3/25
	 * 
	 * @param newContactmodel
	 *            java.lang.String
	 */
	public void setContactmodel(String contactmodel) {
		this.contactmodel = contactmodel;
	}

	/**
	 * ���� pk_productstage��Getter����.���������ض� ��������:2019/3/25
	 * 
	 * @return java.lang.String
	 */
	public String getPk_productstage() {
		return this.pk_productstage;
	}

	/**
	 * ����pk_productstage��Setter����.���������ض� ��������:2019/3/25
	 * 
	 * @param newPk_productstage
	 *            java.lang.String
	 */
	public void setPk_productstage(String pk_productstage) {
		this.pk_productstage = pk_productstage;
	}

	/**
	 * ���� pk_samplegroup��Getter����.����������Ʒ�M�e ��������:2019/3/25
	 * 
	 * @return nc.vo.bd.defdoc.DefdocVO
	 */
	public String getPk_samplegroup() {
		return this.pk_samplegroup;
	}

	/**
	 * ����pk_samplegroup��Setter����.����������Ʒ�M�e ��������:2019/3/25
	 * 
	 * @param newPk_samplegroup
	 *            nc.vo.bd.defdoc.DefdocVO
	 */
	public void setPk_samplegroup(String pk_samplegroup) {
		this.pk_samplegroup = pk_samplegroup;
	}

	/**
	 * ���� analysisref��Getter����.�����������ǰ���� ��������:2019/3/25
	 * 
	 * @return java.lang.String
	 */
	public String getAnalysisref() {
		return this.analysisref;
	}

	/**
	 * ����analysisref��Setter����.�����������ǰ���� ��������:2019/3/25
	 * 
	 * @param newAnalysisref
	 *            java.lang.String
	 */
	public void setAnalysisref(String analysisref) {
		this.analysisref = analysisref;
	}

	/**
	 * ���� otherinfo��Getter����.��������������Ϣ ��������:2019/3/25
	 * 
	 * @return java.lang.String
	 */
	public String getOtherinfo() {
		return this.otherinfo;
	}

	/**
	 * ����otherinfo��Setter����.��������������Ϣ ��������:2019/3/25
	 * 
	 * @param newOtherinfo
	 *            java.lang.String
	 */
	public void setOtherinfo(String otherinfo) {
		this.otherinfo = otherinfo;
	}

	/**
	 * ���� �����ό����I��Getter����.���������ό����I ��������:2019/3/25
	 * 
	 * @return String
	 */
	public String getPk_commission_h() {
		return this.pk_commission_h;
	}

	/**
	 * ���������ό����I��Setter����.���������ό����I ��������:2019/3/25
	 * 
	 * @param newPk_commission_h
	 *            String
	 */
	public void setPk_commission_h(String pk_commission_h) {
		this.pk_commission_h = pk_commission_h;
	}

	/**
	 * ���� ���ɕr�g����Getter����.���������r�g�� ��������:2019/3/25
	 * 
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public UFDateTime getTs() {
		return this.ts;
	}

	/**
	 * �������ɕr�g����Setter����.���������r�g�� ��������:2019/3/25
	 * 
	 * @param newts
	 *            nc.vo.pub.lang.UFDateTime
	 */
	public void setTs(UFDateTime ts) {
		this.ts = ts;
	}

	@Override
	public IVOMeta getMetaData() {
		return VOMetaFactory.getInstance().getVOMeta("qcco.commission_b");
	}

	public CommissionRVO[] getPk_commission_r() {
		return pk_commission_r;
	}

	public void setPk_commission_r(CommissionRVO[] pk_commission_r) {
		this.pk_commission_r = pk_commission_r;
	}

	public String getParentPKFieldName() {
		return "pk_commission_h";
	}
}
