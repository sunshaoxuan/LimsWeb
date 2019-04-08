package nc.vo.qcco.commission;

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
 * 創建日期:2019/4/8
 * 
 * @author yonyouBQ
 * @version NCPrj ??
 */

public class CommissionBVO extends SuperVO {

	/**
	 * 樣品行主鍵
	 */
	public String pk_commission_b;
	/**
	 * 行號
	 */
	public String rowno;
	/**
	 * 產品系列
	 */
	public String pk_productserial;
	/**
	 * 企業標準
	 */
	public String pk_enterprisestandard;
	/**
	 * 規格型號
	 */
	public String typeno;
	/**
	 * 規格號
	 */
	public String pk_productspec;
	/**
	 * 結構類型
	 */
	public String pk_structuretype;
	/**
	 * 觸點類型
	 */
	public String contacttype;
	/**
	 * 樣品數量
	 */
	public UFDouble quantity;
	/**
	 * 製造商
	 */
	public String manufacturer;
	/**
	 * 觸點牌號
	 */
	public String pk_contactbrand;
	/**
	 * 觸點型號
	 */
	public String contactmodel;
	/**
	 * 溫度
	 */
	public String productstage;
	/**
	 * 樣品組別
	 */
	public String pk_samplegroup;
	/**
	 * 實驗前參數
	 */
	public String analysisref;
	/**
	 * 其他信息
	 */
	public String otherinfo;
	/**
	 * 上層單據主鍵
	 */
	public String pk_commission_h;
	/**
	 * 時間戳
	 */
	public UFDateTime ts;

	public CommissionRVO[] pk_commission_r;
	/**
	 * 屬性 pk_commission_b的Getter方法.屬性名：樣品行主鍵 創建日期:2019/4/8
	 * 
	 * @return java.lang.String
	 */
	public String getPk_commission_b() {
		return this.pk_commission_b;
	}

	/**
	 * 屬性pk_commission_b的Setter方法.屬性名：樣品行主鍵 創建日期:2019/4/8
	 * 
	 * @param newPk_commission_b
	 *            java.lang.String
	 */
	public void setPk_commission_b(String pk_commission_b) {
		this.pk_commission_b = pk_commission_b;
	}

	/**
	 * 屬性 rowno的Getter方法.屬性名：行號 創建日期:2019/4/8
	 * 
	 * @return java.lang.String
	 */
	public String getRowno() {
		return this.rowno;
	}

	/**
	 * 屬性rowno的Setter方法.屬性名：行號 創建日期:2019/4/8
	 * 
	 * @param newRowno
	 *            java.lang.String
	 */
	public void setRowno(String rowno) {
		this.rowno = rowno;
	}

	/**
	 * 屬性 pk_productserial的Getter方法.屬性名：產品系列 創建日期:2019/4/8
	 * 
	 * @return nc.vo.bd.defdoc.DefdocVO
	 */
	public String getPk_productserial() {
		return this.pk_productserial;
	}

	/**
	 * 屬性pk_productserial的Setter方法.屬性名：產品系列 創建日期:2019/4/8
	 * 
	 * @param newPk_productserial
	 *            nc.vo.bd.defdoc.DefdocVO
	 */
	public void setPk_productserial(String pk_productserial) {
		this.pk_productserial = pk_productserial;
	}

	/**
	 * 屬性 pk_enterprisestandard的Getter方法.屬性名：企業標準 創建日期:2019/4/8
	 * 
	 * @return nc.vo.bd.defdoc.DefdocVO
	 */
	public String getPk_enterprisestandard() {
		return this.pk_enterprisestandard;
	}

	/**
	 * 屬性pk_enterprisestandard的Setter方法.屬性名：企業標準 創建日期:2019/4/8
	 * 
	 * @param newPk_enterprisestandard
	 *            nc.vo.bd.defdoc.DefdocVO
	 */
	public void setPk_enterprisestandard(String pk_enterprisestandard) {
		this.pk_enterprisestandard = pk_enterprisestandard;
	}

	/**
	 * 屬性 typeno的Getter方法.屬性名：規格型號 創建日期:2019/4/8
	 * 
	 * @return java.lang.String
	 */
	public String getTypeno() {
		return this.typeno;
	}

	/**
	 * 屬性typeno的Setter方法.屬性名：規格型號 創建日期:2019/4/8
	 * 
	 * @param newTypeno
	 *            java.lang.String
	 */
	public void setTypeno(String typeno) {
		this.typeno = typeno;
	}

	/**
	 * 屬性 pk_productspec的Getter方法.屬性名：規格號 創建日期:2019/4/8
	 * 
	 * @return nc.vo.bd.defdoc.DefdocVO
	 */
	public String getPk_productspec() {
		return this.pk_productspec;
	}

	/**
	 * 屬性pk_productspec的Setter方法.屬性名：規格號 創建日期:2019/4/8
	 * 
	 * @param newPk_productspec
	 *            nc.vo.bd.defdoc.DefdocVO
	 */
	public void setPk_productspec(String pk_productspec) {
		this.pk_productspec = pk_productspec;
	}

	/**
	 * 屬性 pk_structuretype的Getter方法.屬性名：結構類型 創建日期:2019/4/8
	 * 
	 * @return nc.vo.bd.defdoc.DefdocVO
	 */
	public String getPk_structuretype() {
		return this.pk_structuretype;
	}

	/**
	 * 屬性pk_structuretype的Setter方法.屬性名：結構類型 創建日期:2019/4/8
	 * 
	 * @param newPk_structuretype
	 *            nc.vo.bd.defdoc.DefdocVO
	 */
	public void setPk_structuretype(String pk_structuretype) {
		this.pk_structuretype = pk_structuretype;
	}

	/**
	 * 屬性 contacttype的Getter方法.屬性名：觸點類型 創建日期:2019/4/8
	 * 
	 * @return java.lang.String
	 */
	public String getContacttype() {
		return this.contacttype;
	}

	/**
	 * 屬性contacttype的Setter方法.屬性名：觸點類型 創建日期:2019/4/8
	 * 
	 * @param newContacttype
	 *            java.lang.String
	 */
	public void setContacttype(String contacttype) {
		this.contacttype = contacttype;
	}

	/**
	 * 屬性 quantity的Getter方法.屬性名：樣品數量 創建日期:2019/4/8
	 * 
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public UFDouble getQuantity() {
		return this.quantity;
	}

	/**
	 * 屬性quantity的Setter方法.屬性名：樣品數量 創建日期:2019/4/8
	 * 
	 * @param newQuantity
	 *            nc.vo.pub.lang.UFDouble
	 */
	public void setQuantity(UFDouble quantity) {
		this.quantity = quantity;
	}

	/**
	 * 屬性 manufacturer的Getter方法.屬性名：製造商 創建日期:2019/4/8
	 * 
	 * @return java.lang.String
	 */
	public String getManufacturer() {
		return this.manufacturer;
	}

	/**
	 * 屬性manufacturer的Setter方法.屬性名：製造商 創建日期:2019/4/8
	 * 
	 * @param newManufacturer
	 *            java.lang.String
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	/**
	 * 屬性 pk_contactbrand的Getter方法.屬性名：觸點牌號 創建日期:2019/4/8
	 * 
	 * @return nc.vo.bd.defdoc.DefdocVO
	 */
	public String getPk_contactbrand() {
		return this.pk_contactbrand;
	}

	/**
	 * 屬性pk_contactbrand的Setter方法.屬性名：觸點牌號 創建日期:2019/4/8
	 * 
	 * @param newPk_contactbrand
	 *            nc.vo.bd.defdoc.DefdocVO
	 */
	public void setPk_contactbrand(String pk_contactbrand) {
		this.pk_contactbrand = pk_contactbrand;
	}

	/**
	 * 屬性 contactmodel的Getter方法.屬性名：觸點型號 創建日期:2019/4/8
	 * 
	 * @return java.lang.String
	 */
	public String getContactmodel() {
		return this.contactmodel;
	}

	/**
	 * 屬性contactmodel的Setter方法.屬性名：觸點型號 創建日期:2019/4/8
	 * 
	 * @param newContactmodel
	 *            java.lang.String
	 */
	public void setContactmodel(String contactmodel) {
		this.contactmodel = contactmodel;
	}

	/**
	 * 屬性 productstage的Getter方法.屬性名：溫度 創建日期:2019/4/8
	 * 
	 * @return java.lang.String
	 */
	public String getProductstage() {
		return this.productstage;
	}

	/**
	 * 屬性productstage的Setter方法.屬性名：溫度 創建日期:2019/4/8
	 * 
	 * @param newProductstage
	 *            java.lang.String
	 */
	public void setProductstage(String productstage) {
		this.productstage = productstage;
	}

	/**
	 * 屬性 pk_samplegroup的Getter方法.屬性名：樣品組別 創建日期:2019/4/8
	 * 
	 * @return nc.vo.bd.defdoc.DefdocVO
	 */
	public String getPk_samplegroup() {
		return this.pk_samplegroup;
	}

	/**
	 * 屬性pk_samplegroup的Setter方法.屬性名：樣品組別 創建日期:2019/4/8
	 * 
	 * @param newPk_samplegroup
	 *            nc.vo.bd.defdoc.DefdocVO
	 */
	public void setPk_samplegroup(String pk_samplegroup) {
		this.pk_samplegroup = pk_samplegroup;
	}

	/**
	 * 屬性 analysisref的Getter方法.屬性名：實驗前參數 創建日期:2019/4/8
	 * 
	 * @return java.lang.String
	 */
	public String getAnalysisref() {
		return this.analysisref;
	}

	/**
	 * 屬性analysisref的Setter方法.屬性名：實驗前參數 創建日期:2019/4/8
	 * 
	 * @param newAnalysisref
	 *            java.lang.String
	 */
	public void setAnalysisref(String analysisref) {
		this.analysisref = analysisref;
	}

	/**
	 * 屬性 otherinfo的Getter方法.屬性名：其他信息 創建日期:2019/4/8
	 * 
	 * @return java.lang.String
	 */
	public String getOtherinfo() {
		return this.otherinfo;
	}

	/**
	 * 屬性otherinfo的Setter方法.屬性名：其他信息 創建日期:2019/4/8
	 * 
	 * @param newOtherinfo
	 *            java.lang.String
	 */
	public void setOtherinfo(String otherinfo) {
		this.otherinfo = otherinfo;
	}

	/**
	 * 屬性 生成上層主鍵的Getter方法.屬性名：上層主鍵 創建日期:2019/4/8
	 * 
	 * @return String
	 */
	public String getPk_commission_h() {
		return this.pk_commission_h;
	}

	/**
	 * 屬性生成上層主鍵的Setter方法.屬性名：上層主鍵 創建日期:2019/4/8
	 * 
	 * @param newPk_commission_h
	 *            String
	 */
	public void setPk_commission_h(String pk_commission_h) {
		this.pk_commission_h = pk_commission_h;
	}

	/**
	 * 屬性 生成時間戳的Getter方法.屬性名：時間戳 創建日期:2019/4/8
	 * 
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public UFDateTime getTs() {
		return this.ts;
	}

	/**
	 * 屬性生成時間戳的Setter方法.屬性名：時間戳 創建日期:2019/4/8
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
