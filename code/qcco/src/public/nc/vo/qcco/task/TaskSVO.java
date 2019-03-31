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
 *  ��������:2019-3-12
 * @author yonyouBQ
 * @version NCPrj ??
 */
 
public class TaskSVO extends SuperVO {
	
/**
*������������
*/
public String pk_task_s;
/**
*����������
*/
public String pk_testconditionitem;
/**
*״̬
*/
public UFBoolean conditionstatus;
/**
*�Ƿ��ѡ
*/
public UFBoolean isoptional;
/**
*�Ƿ�ɱ���
*/
public UFBoolean isallow_out;
/**
*����
*/
public String instrument;
/**
*ֵ����
*/
public String valuetype;
/**
*ֵ
*/
public String entry;
/**
*��λ
*/
public String unit;
/**
*��ʽ��ֵ
*/
public String formatted_entry;
/**
*��Сֵ
*/
public UFDouble min_limit;
/**
*���ֵ
*/
public UFDouble max_limit;
/**
*Ӣ��˵��
*/
public String englishdescription;
/**
*�ϲ㵥������
*/
public String pk_task_b;
/**
*ʱ���
*/
public UFDateTime ts;
public Integer Dr;

public Integer getDr() {
		return Dr;
	}

	public void setDr(Integer dr) {
		Dr = dr;
	}
    

 
public String getPk_task_s() {
		return pk_task_s;
	}

	public void setPk_task_s(String pk_task_s) {
		this.pk_task_s = pk_task_s;
	}

/**
* ���� pk_testconditionitem��Getter����.������������������
*  ��������:2019-3-12
* @return nc.vo.bd.defdoc.DefdocVO
*/
public String getPk_testconditionitem() {
return this.pk_testconditionitem;
} 

/**
* ����pk_testconditionitem��Setter����.������������������
* ��������:2019-3-12
* @param newPk_testconditionitem nc.vo.bd.defdoc.DefdocVO
*/
public void setPk_testconditionitem ( String pk_testconditionitem) {
this.pk_testconditionitem=pk_testconditionitem;
} 
 
/**
* ���� conditionstatus��Getter����.��������״̬
*  ��������:2019-3-12
* @return nc.vo.pub.lang.UFUFBoolean
*/
public UFBoolean getConditionstatus() {
return this.conditionstatus;
} 

/**
* ����conditionstatus��Setter����.��������״̬
* ��������:2019-3-12
* @param newConditionstatus nc.vo.pub.lang.UFUFBoolean
*/
public void setConditionstatus ( UFBoolean conditionstatus) {
this.conditionstatus=conditionstatus;
} 
 
/**
* ���� isoptional��Getter����.���������Ƿ��ѡ
*  ��������:2019-3-12
* @return nc.vo.pub.lang.UFUFBoolean
*/
public UFBoolean getIsoptional() {
return this.isoptional;
} 

/**
* ����isoptional��Setter����.���������Ƿ��ѡ
* ��������:2019-3-12
* @param newIsoptional nc.vo.pub.lang.UFUFBoolean
*/
public void setIsoptional ( UFBoolean isoptional) {
this.isoptional=isoptional;
} 
 
/**
* ���� isallow_out��Getter����.���������Ƿ�ɱ���
*  ��������:2019-3-12
* @return nc.vo.pub.lang.UFUFBoolean
*/
public UFBoolean getIsallow_out() {
return this.isallow_out;
} 

/**
* ����isallow_out��Setter����.���������Ƿ�ɱ���
* ��������:2019-3-12
* @param newIsallow_out nc.vo.pub.lang.UFUFBoolean
*/
public void setIsallow_out ( UFBoolean isallow_out) {
this.isallow_out=isallow_out;
} 
 
/**
* ���� instrument��Getter����.������������
*  ��������:2019-3-12
* @return nc.vo.bd.defdoc.DefdocVO
*/
public String getInstrument() {
return this.instrument;
} 

/**
* ����instrument��Setter����.������������
* ��������:2019-3-12
* @param newInstrument nc.vo.bd.defdoc.DefdocVO
*/
public void setInstrument ( String instrument) {
this.instrument=instrument;
} 
 
/**
* ���� valuetype��Getter����.��������ֵ����
*  ��������:2019-3-12
* @return nc.vo.bd.defdoc.DefdocVO
*/
public String getValuetype() {
return this.valuetype;
} 

/**
* ����valuetype��Setter����.��������ֵ����
* ��������:2019-3-12
* @param newValuetype nc.vo.bd.defdoc.DefdocVO
*/
public void setValuetype ( String valuetype) {
this.valuetype=valuetype;
} 
 
/**
* ���� entry��Getter����.��������ֵ
*  ��������:2019-3-12
* @return java.lang.String
*/
public String getEntry() {
return this.entry;
} 

/**
* ����entry��Setter����.��������ֵ
* ��������:2019-3-12
* @param newEntry java.lang.String
*/
public void setEntry ( String entry) {
this.entry=entry;
} 
 
/**
* ���� unit��Getter����.����������λ
*  ��������:2019-3-12
* @return java.lang.String
*/
public String getUnit() {
return this.unit;
} 

/**
* ����unit��Setter����.����������λ
* ��������:2019-3-12
* @param newUnit java.lang.String
*/
public void setUnit ( String unit) {
this.unit=unit;
} 
 
/**
* ���� formatted_entry��Getter����.����������ʽ��ֵ
*  ��������:2019-3-12
* @return java.lang.String
*/
public String getFormatted_entry() {
return this.formatted_entry;
} 

/**
* ����formatted_entry��Setter����.����������ʽ��ֵ
* ��������:2019-3-12
* @param newFormatted_entry java.lang.String
*/
public void setFormatted_entry ( String formatted_entry) {
this.formatted_entry=formatted_entry;
} 
 
/**
* ���� min_limit��Getter����.����������Сֵ
*  ��������:2019-3-12
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getMin_limit() {
return this.min_limit;
} 

/**
* ����min_limit��Setter����.����������Сֵ
* ��������:2019-3-12
* @param newMin_limit nc.vo.pub.lang.UFDouble
*/
public void setMin_limit ( UFDouble min_limit) {
this.min_limit=min_limit;
} 
 
/**
* ���� max_limit��Getter����.�����������ֵ
*  ��������:2019-3-12
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getMax_limit() {
return this.max_limit;
} 

/**
* ����max_limit��Setter����.�����������ֵ
* ��������:2019-3-12
* @param newMax_limit nc.vo.pub.lang.UFDouble
*/
public void setMax_limit ( UFDouble max_limit) {
this.max_limit=max_limit;
} 
 
/**
* ���� englishdescription��Getter����.��������Ӣ��˵��
*  ��������:2019-3-12
* @return java.lang.String
*/
public String getEnglishdescription() {
return this.englishdescription;
} 

/**
* ����englishdescription��Setter����.��������Ӣ��˵��
* ��������:2019-3-12
* @param newEnglishdescription java.lang.String
*/
public void setEnglishdescription ( String englishdescription) {
this.englishdescription=englishdescription;
} 
 
/**
* ���� �����ϲ�������Getter����.���������ϲ�����
*  ��������:2019-3-12
* @return String
*/
public String getPk_task_b(){
return this.pk_task_b;
}
/**
* ���������ϲ�������Setter����.���������ϲ�����
* ��������:2019-3-12
* @param newPk_task_b String
*/
public void setPk_task_b(String pk_task_b){
this.pk_task_b=pk_task_b;
} 
/**
* ���� ����ʱ�����Getter����.��������ʱ���
*  ��������:2019-3-12
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTs() {
return this.ts;
}
/**
* ��������ʱ�����Setter����.��������ʱ���
* ��������:2019-3-12
* @param newts nc.vo.pub.lang.UFDateTime
*/
public void setTs(UFDateTime ts){
this.ts=ts;
} 
     
    @Override
    public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("qcco.task_s");
    }
    @Override
	public String getParentPKFieldName() {
		// TODO Auto-generated method stub
		return "pk_task_b";
	}
   }
    