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
 
public class TaskRVO extends SuperVO {
	
/**
*����������
*/
public String pk_task_r;
/**
*��Ʒ���
*/
public String pk_samplegroup;
/**
*��Сֵ
*/
public UFDouble stdminvalue;
/**
*���ֵ
*/
public UFDouble stdmaxvalue;
/**
*��λ
*/
public String pk_unit;
/**
*���Ա��
*/
public UFBoolean testflag;
/**
*�ж����
*/
public UFBoolean judgeflag;
/**
*�����¶�
*/
public String pk_testtemperature;
/**
*ʵ���������
*/
public String analysisname;
/**
*������
*/
public String pk_component;
/**
*ֵ����
*/
public String valuetype;
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
    
/**
* ���� pk_task_r��Getter����.������������������
*  ��������:2019-3-12
* @return java.lang.String
*/
public String getPk_task_r() {
return this.pk_task_r;
} 

/**
* ����pk_task_r��Setter����.������������������
* ��������:2019-3-12
* @param newPk_task_r java.lang.String
*/
public void setPk_task_r ( String pk_task_r) {
this.pk_task_r=pk_task_r;
} 
 
/**
* ���� pk_samplegroup��Getter����.����������Ʒ���
*  ��������:2019-3-12
* @return nc.vo.bd.defdoc.DefdocVO
*/
public String getPk_samplegroup() {
return this.pk_samplegroup;
} 

/**
* ����pk_samplegroup��Setter����.����������Ʒ���
* ��������:2019-3-12
* @param newPk_samplegroup nc.vo.bd.defdoc.DefdocVO
*/
public void setPk_samplegroup ( String pk_samplegroup) {
this.pk_samplegroup=pk_samplegroup;
} 
 
/**
* ���� stdminvalue��Getter����.����������Сֵ
*  ��������:2019-3-12
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getStdminvalue() {
return this.stdminvalue;
} 

/**
* ����stdminvalue��Setter����.����������Сֵ
* ��������:2019-3-12
* @param newStdminvalue nc.vo.pub.lang.UFDouble
*/
public void setStdminvalue ( UFDouble stdminvalue) {
this.stdminvalue=stdminvalue;
} 
 
/**
* ���� stdmaxvalue��Getter����.�����������ֵ
*  ��������:2019-3-12
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getStdmaxvalue() {
return this.stdmaxvalue;
} 

/**
* ����stdmaxvalue��Setter����.�����������ֵ
* ��������:2019-3-12
* @param newStdmaxvalue nc.vo.pub.lang.UFDouble
*/
public void setStdmaxvalue ( UFDouble stdmaxvalue) {
this.stdmaxvalue=stdmaxvalue;
} 
 
/**
* ���� pk_unit��Getter����.����������λ
*  ��������:2019-3-12
* @return nc.vo.bd.defdoc.DefdocVO
*/
public String getPk_unit() {
return this.pk_unit;
} 

/**
* ����pk_unit��Setter����.����������λ
* ��������:2019-3-12
* @param newPk_unit nc.vo.bd.defdoc.DefdocVO
*/
public void setPk_unit ( String pk_unit) {
this.pk_unit=pk_unit;
} 
 
/**
* ���� testflag��Getter����.�����������Ա��
*  ��������:2019-3-12
* @return nc.vo.pub.lang.UFUFBoolean
*/
public UFBoolean getTestflag() {
return this.testflag;
} 

/**
* ����testflag��Setter����.�����������Ա��
* ��������:2019-3-12
* @param newTestflag nc.vo.pub.lang.UFUFBoolean
*/
public void setTestflag ( UFBoolean testflag) {
this.testflag=testflag;
} 
 
/**
* ���� judgeflag��Getter����.���������ж����
*  ��������:2019-3-12
* @return nc.vo.pub.lang.UFUFBoolean
*/
public UFBoolean getJudgeflag() {
return this.judgeflag;
} 

/**
* ����judgeflag��Setter����.���������ж����
* ��������:2019-3-12
* @param newJudgeflag nc.vo.pub.lang.UFUFBoolean
*/
public void setJudgeflag ( UFBoolean judgeflag) {
this.judgeflag=judgeflag;
} 
 
/**
* ���� pk_testtemperature��Getter����.�������������¶�
*  ��������:2019-3-12
* @return nc.vo.bd.defdoc.DefdocVO
*/
public String getPk_testtemperature() {
return this.pk_testtemperature;
} 

/**
* ����pk_testtemperature��Setter����.�������������¶�
* ��������:2019-3-12
* @param newPk_testtemperature nc.vo.bd.defdoc.DefdocVO
*/
public void setPk_testtemperature ( String pk_testtemperature) {
this.pk_testtemperature=pk_testtemperature;
} 
 
/**
* ���� analysisname��Getter����.��������ʵ���������
*  ��������:2019-3-12
* @return java.lang.String
*/
public String getAnalysisname() {
return this.analysisname;
} 

/**
* ����analysisname��Setter����.��������ʵ���������
* ��������:2019-3-12
* @param newAnalysisname java.lang.String
*/
public void setAnalysisname ( String analysisname) {
this.analysisname=analysisname;
} 
 
/**
* ���� pk_component��Getter����.��������������
*  ��������:2019-3-12
* @return nc.vo.bd.defdoc.DefdocVO
*/
public String getPk_component() {
return this.pk_component;
} 

/**
* ����pk_component��Setter����.��������������
* ��������:2019-3-12
* @param newPk_component nc.vo.bd.defdoc.DefdocVO
*/
public void setPk_component ( String pk_component) {
this.pk_component=pk_component;
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
    return VOMetaFactory.getInstance().getVOMeta("qcco.task_r");
    }
    @Override
   	public String getParentPKFieldName() {
   		// TODO Auto-generated method stub
   		return "pk_task_b";
   	}
   }
    