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
 *  ��������:2018-12-5
 * @author yonyouBQ
 * @version NCPrj ??
 */
 
public class TaskSVO extends SuperVO {
	
/**
*��Ʒ������
*/
public String pk_sample;
/**
*��Ʒ���
*/
public String sampleno;
/**
*�к�
*/
public String rowno;
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
* ���� pk_sample��Getter����.����������Ʒ������
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getPk_sample() {
return this.pk_sample;
} 

/**
* ����pk_sample��Setter����.����������Ʒ������
* ��������:2018-12-5
* @param newPk_sample java.lang.String
*/
public void setPk_sample ( String pk_sample) {
this.pk_sample=pk_sample;
} 
 
/**
* ���� sampleno��Getter����.����������Ʒ���
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getSampleno() {
return this.sampleno;
} 

/**
* ����sampleno��Setter����.����������Ʒ���
* ��������:2018-12-5
* @param newSampleno java.lang.String
*/
public void setSampleno ( String sampleno) {
this.sampleno=sampleno;
} 
 
/**
* ���� rowno��Getter����.���������к�
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getRowno() {
return this.rowno;
} 

/**
* ����rowno��Setter����.���������к�
* ��������:2018-12-5
* @param newRowno java.lang.String
*/
public void setRowno ( String rowno) {
this.rowno=rowno;
} 
 
/**
* ���� �����ϲ�������Getter����.���������ϲ�����
*  ��������:2018-12-5
* @return String
*/
public String getPk_task_b(){
return this.pk_task_b;
}
/**
* ���������ϲ�������Setter����.���������ϲ�����
* ��������:2018-12-5
* @param newPk_task_b String
*/
public void setPk_task_b(String pk_task_b){
this.pk_task_b=pk_task_b;
} 
/**
* ���� ����ʱ�����Getter����.��������ʱ���
*  ��������:2018-12-5
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTs() {
return this.ts;
}
/**
* ��������ʱ�����Setter����.��������ʱ���
* ��������:2018-12-5
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
    