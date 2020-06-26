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
 
public class TaskBVO extends SuperVO {
	public TaskRVO [] pk_task_r;
	public TaskSVO [] pk_sample;
public TaskRVO[] getPk_task_r() {
		return pk_task_r;
	}

	public void setPk_task_r(TaskRVO[] pk_task_r) {
		this.pk_task_r = pk_task_r;
	}

	public TaskSVO[] getPk_sample() {
		return pk_sample;
	}

	public void setPk_sample(TaskSVO[] pk_sample) {
		this.pk_sample = pk_sample;
	}

/**
*����������
*/
public String pk_task_b;
/**
*������
*/
public String taskcode;
/**
*��������
*/
public String taskname;
/**
*��Ʒ����
*/
public String samplequantity;
/**
*ִ��˳��
*/
public Integer runorder;
/**
*�к�
*/
public String rowno;
/**
*�ϲ㵥������
*/
public String pk_task_h;
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
* ���� pk_task_b��Getter����.������������������
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getPk_task_b() {
return this.pk_task_b;
} 

/**
* ����pk_task_b��Setter����.������������������
* ��������:2018-12-5
* @param newPk_task_b java.lang.String
*/
public void setPk_task_b ( String pk_task_b) {
this.pk_task_b=pk_task_b;
} 
 
/**
* ���� taskcode��Getter����.��������������
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getTaskcode() {
return this.taskcode;
} 

/**
* ����taskcode��Setter����.��������������
* ��������:2018-12-5
* @param newTaskcode java.lang.String
*/
public void setTaskcode ( String taskcode) {
this.taskcode=taskcode;
} 
 
/**
* ���� taskname��Getter����.����������������
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getTaskname() {
return this.taskname;
} 

/**
* ����taskname��Setter����.����������������
* ��������:2018-12-5
* @param newTaskname java.lang.String
*/
public void setTaskname ( String taskname) {
this.taskname=taskname;
} 
 
/**
* ���� samplequantity��Getter����.����������Ʒ����
*  ��������:2018-12-5
* @return nc.vo.pub.lang.UFDouble
*/
public String getSamplequantity() {
return this.samplequantity;
} 

/**
* ����samplequantity��Setter����.����������Ʒ����
* ��������:2018-12-5
* @param newSamplequantity nc.vo.pub.lang.UFDouble
*/
public void setSamplequantity ( String samplequantity) {
this.samplequantity=samplequantity;
} 
 
/**
* ���� runorder��Getter����.��������ִ��˳��
*  ��������:2018-12-5
* @return java.lang.Integer
*/
public Integer getRunorder() {
return this.runorder;
} 

/**
* ����runorder��Setter����.��������ִ��˳��
* ��������:2018-12-5
* @param newRunorder java.lang.Integer
*/
public void setRunorder ( Integer runorder) {
this.runorder=runorder;
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
public String getPk_task_h(){
return this.pk_task_h;
}
/**
* ���������ϲ�������Setter����.���������ϲ�����
* ��������:2018-12-5
* @param newPk_task_h String
*/
public void setPk_task_h(String pk_task_h){
this.pk_task_h=pk_task_h;
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
    return VOMetaFactory.getInstance().getVOMeta("qcco.task_b");
    }
   }
    