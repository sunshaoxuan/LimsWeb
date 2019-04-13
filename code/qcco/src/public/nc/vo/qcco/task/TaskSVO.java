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
 * ��������:2019/4/13
 * 
 * @author yonyouBQ
 * @version NCPrj ??
 */

public class TaskSVO extends SuperVO {

	/**
	 * �yԇ�l�����I
	 */
	public String pk_task_s;
	/**
	 * �yԇ�l���
	 */
	public String pk_testconditionitem;
	/**
	 * ��B
	 */
	public Boolean conditionstatus;
	/**
	 * �Ƿ���x
	 */
	public Boolean isoptional;
	/**
	 * �Ƿ�Ɉ��
	 */
	public Boolean isallow_out;
	/**
	 * �x��
	 */
	public String instrument;
	/**
	 * ֵ���
	 */
	public String valuetype;
	/**
	 * ȡֵ��ʽ
	 */
	public Integer valueways;
	/**
	 * �ı�ֵ
	 */
	public String textvalue;
	/**
	 * ����ֵ
	 */
	public String refvalue;
	/**
	 * ��λ
	 */
	public String unit;
	/**
	 * ��ʽ��ֵ
	 */
	public String formatted_entry;
	/**
	 * ��Сֵ
	 */
	public UFDouble min_limit;
	/**
	 * ���ֵ
	 */
	public UFDouble max_limit;
	/**
	 * Ӣ���f��
	 */
	public String englishdescription;
	/**
	 * �όӆΓ����I
	 */
	public String pk_task_b;
	/**
	 * �r�g��
	 */
	public UFDateTime ts;

	/**
	 * ���� pk_task_s��Getter����.���������yԇ�l�����I ��������:2019/4/13
	 * 
	 * @return java.lang.String
	 */
	public String getPk_task_s() {
		return this.pk_task_s;
	}

	/**
	 * ����pk_task_s��Setter����.���������yԇ�l�����I ��������:2019/4/13
	 * 
	 * @param newPk_task_s
	 *            java.lang.String
	 */
	public void setPk_task_s(String pk_task_s) {
		this.pk_task_s = pk_task_s;
	}

	/**
	 * ���� pk_testconditionitem��Getter����.���������yԇ�l��� ��������:2019/4/13
	 * 
	 * @return nc.vo.bd.defdoc.DefdocVO
	 */
	public String getPk_testconditionitem() {
		return this.pk_testconditionitem;
	}

	/**
	 * ����pk_testconditionitem��Setter����.���������yԇ�l��� ��������:2019/4/13
	 * 
	 * @param newPk_testconditionitem
	 *            nc.vo.bd.defdoc.DefdocVO
	 */
	public void setPk_testconditionitem(String pk_testconditionitem) {
		this.pk_testconditionitem = pk_testconditionitem;
	}

	/**
	 * ���� conditionstatus��Getter����.����������B ��������:2019/4/13
	 * 
	 * @return nc.vo.pub.lang.UFBoolean
	 */
	public Boolean getConditionstatus() {
		return this.conditionstatus;
	}

	/**
	 * ����conditionstatus��Setter����.����������B ��������:2019/4/13
	 * 
	 * @param newConditionstatus
	 *            nc.vo.pub.lang.UFBoolean
	 */
	public void setConditionstatus(Boolean conditionstatus) {
		this.conditionstatus = conditionstatus;
	}

	/**
	 * ���� isoptional��Getter����.���������Ƿ���x ��������:2019/4/13
	 * 
	 * @return nc.vo.pub.lang.UFBoolean
	 */
	public Boolean getIsoptional() {
		return this.isoptional;
	}

	/**
	 * ����isoptional��Setter����.���������Ƿ���x ��������:2019/4/13
	 * 
	 * @param newIsoptional
	 *            nc.vo.pub.lang.UFBoolean
	 */
	public void setIsoptional(Boolean isoptional) {
		this.isoptional = isoptional;
	}

	/**
	 * ���� isallow_out��Getter����.���������Ƿ�Ɉ�� ��������:2019/4/13
	 * 
	 * @return nc.vo.pub.lang.UFBoolean
	 */
	public Boolean getIsallow_out() {
		return this.isallow_out;
	}

	/**
	 * ����isallow_out��Setter����.���������Ƿ�Ɉ�� ��������:2019/4/13
	 * 
	 * @param newIsallow_out
	 *            nc.vo.pub.lang.UFBoolean
	 */
	public void setIsallow_out(Boolean isallow_out) {
		this.isallow_out = isallow_out;
	}

	/**
	 * ���� instrument��Getter����.���������x�� ��������:2019/4/13
	 * 
	 * @return nc.vo.bd.defdoc.DefdocVO
	 */
	public String getInstrument() {
		return this.instrument;
	}

	/**
	 * ����instrument��Setter����.���������x�� ��������:2019/4/13
	 * 
	 * @param newInstrument
	 *            nc.vo.bd.defdoc.DefdocVO
	 */
	public void setInstrument(String instrument) {
		this.instrument = instrument;
	}

	/**
	 * ���� valuetype��Getter����.��������ֵ��� ��������:2019/4/13
	 * 
	 * @return nc.vo.bd.defdoc.DefdocVO
	 */
	public String getValuetype() {
		return this.valuetype;
	}

	/**
	 * ����valuetype��Setter����.��������ֵ��� ��������:2019/4/13
	 * 
	 * @param newValuetype
	 *            nc.vo.bd.defdoc.DefdocVO
	 */
	public void setValuetype(String valuetype) {
		this.valuetype = valuetype;
	}

	/**
	 * ���� valueways��Getter����.��������ȡֵ��ʽ ��������:2019/4/13
	 * 
	 * @return nc.vo.qcco.task.ValueWaysEnum
	 */
	public Integer getValueways() {
		return this.valueways;
	}

	/**
	 * ����valueways��Setter����.��������ȡֵ��ʽ ��������:2019/4/13
	 * 
	 * @param newValueways
	 *            nc.vo.qcco.task.ValueWaysEnum
	 */
	public void setValueways(Integer valueways) {
		this.valueways = valueways;
	}

	/**
	 * ���� textvalue��Getter����.���������ı�ֵ ��������:2019/4/13
	 * 
	 * @return java.lang.String
	 */
	public String getTextvalue() {
		return this.textvalue;
	}

	/**
	 * ����textvalue��Setter����.���������ı�ֵ ��������:2019/4/13
	 * 
	 * @param newTextvalue
	 *            java.lang.String
	 */
	public void setTextvalue(String textvalue) {
		this.textvalue = textvalue;
	}

	/**
	 * ���� refvalue��Getter����.������������ֵ ��������:2019/4/13
	 * 
	 * @return nc.vo.bd.defdoc.DefdocVO
	 */
	public String getRefvalue() {
		return this.refvalue;
	}

	/**
	 * ����refvalue��Setter����.������������ֵ ��������:2019/4/13
	 * 
	 * @param newRefvalue
	 *            nc.vo.bd.defdoc.DefdocVO
	 */
	public void setRefvalue(String refvalue) {
		this.refvalue = refvalue;
	}

	/**
	 * ���� unit��Getter����.����������λ ��������:2019/4/13
	 * 
	 * @return java.lang.String
	 */
	public String getUnit() {
		return this.unit;
	}

	/**
	 * ����unit��Setter����.����������λ ��������:2019/4/13
	 * 
	 * @param newUnit
	 *            java.lang.String
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}

	/**
	 * ���� formatted_entry��Getter����.����������ʽ��ֵ ��������:2019/4/13
	 * 
	 * @return java.lang.String
	 */
	public String getFormatted_entry() {
		return this.formatted_entry;
	}

	/**
	 * ����formatted_entry��Setter����.����������ʽ��ֵ ��������:2019/4/13
	 * 
	 * @param newFormatted_entry
	 *            java.lang.String
	 */
	public void setFormatted_entry(String formatted_entry) {
		this.formatted_entry = formatted_entry;
	}

	/**
	 * ���� min_limit��Getter����.����������Сֵ ��������:2019/4/13
	 * 
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public UFDouble getMin_limit() {
		return this.min_limit;
	}

	/**
	 * ����min_limit��Setter����.����������Сֵ ��������:2019/4/13
	 * 
	 * @param newMin_limit
	 *            nc.vo.pub.lang.UFDouble
	 */
	public void setMin_limit(UFDouble min_limit) {
		this.min_limit = min_limit;
	}

	/**
	 * ���� max_limit��Getter����.�����������ֵ ��������:2019/4/13
	 * 
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public UFDouble getMax_limit() {
		return this.max_limit;
	}

	/**
	 * ����max_limit��Setter����.�����������ֵ ��������:2019/4/13
	 * 
	 * @param newMax_limit
	 *            nc.vo.pub.lang.UFDouble
	 */
	public void setMax_limit(UFDouble max_limit) {
		this.max_limit = max_limit;
	}

	/**
	 * ���� englishdescription��Getter����.��������Ӣ���f�� ��������:2019/4/13
	 * 
	 * @return java.lang.String
	 */
	public String getEnglishdescription() {
		return this.englishdescription;
	}

	/**
	 * ����englishdescription��Setter����.��������Ӣ���f�� ��������:2019/4/13
	 * 
	 * @param newEnglishdescription
	 *            java.lang.String
	 */
	public void setEnglishdescription(String englishdescription) {
		this.englishdescription = englishdescription;
	}

	/**
	 * ���� �����ό����I��Getter����.���������ό����I ��������:2019/4/13
	 * 
	 * @return String
	 */
	public String getPk_task_b() {
		return this.pk_task_b;
	}

	/**
	 * ���������ό����I��Setter����.���������ό����I ��������:2019/4/13
	 * 
	 * @param newPk_task_b
	 *            String
	 */
	public void setPk_task_b(String pk_task_b) {
		this.pk_task_b = pk_task_b;
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
		return VOMetaFactory.getInstance().getVOMeta("qcco.task_s");
	}

	public String getParentPKFieldName() {
		return "pk_task_b";
	}
}
