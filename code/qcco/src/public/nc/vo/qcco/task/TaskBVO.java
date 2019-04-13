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
 
public class TaskBVO extends SuperVO {
	
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
public UFDouble samplequantity;
/**
*ִ��˳��
*/
public Integer runorder;
/**
*�к�
*/
public String rowno;
/**
*��������
*/
public String[] pk_testcondition;
/**
*���Խ������
*/
public String pk_testresultname;
/**
*���Խ��������
*/
public String testresultshortname;
/**
*�ϲ㵥������
*/
public String pk_task_h;
/**
*ʱ���
*/
public UFDateTime ts;
/**
*testnumber
*/
public String testnumber;
/**
*��Ʒ����
*/
public String sampleallocation;


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
* ���� pk_task_b��Getter����.������������������
*  ��������:2019-3-12
* @return java.lang.String
*/
public String getPk_task_b() {
return this.pk_task_b;
} 

/**
* ����pk_task_b��Setter����.������������������
* ��������:2019-3-12
* @param newPk_task_b java.lang.String
*/
public void setPk_task_b ( String pk_task_b) {
this.pk_task_b=pk_task_b;
} 
 
/**
* ���� taskcode��Getter����.��������������
*  ��������:2019-3-12
* @return java.lang.String
*/
public String getTaskcode() {
return this.taskcode;
} 

/**
* ����taskcode��Setter����.��������������
* ��������:2019-3-12
* @param newTaskcode java.lang.String
*/
public void setTaskcode ( String taskcode) {
this.taskcode=taskcode;
} 
 
/**
* ���� taskname��Getter����.����������������
*  ��������:2019-3-12
* @return java.lang.String
*/
public String getTaskname() {
return this.taskname;
} 

/**
* ����taskname��Setter����.����������������
* ��������:2019-3-12
* @param newTaskname java.lang.String
*/
public void setTaskname ( String taskname) {
this.taskname=taskname;
} 
 
/**
* ���� samplequantity��Getter����.����������Ʒ����
*  ��������:2019-3-12
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getSamplequantity() {
return this.samplequantity;
} 

/**
* ����samplequantity��Setter����.����������Ʒ����
* ��������:2019-3-12
* @param newSamplequantity nc.vo.pub.lang.UFDouble
*/
public void setSamplequantity ( UFDouble samplequantity) {
this.samplequantity=samplequantity;
} 
 
/**
* ���� runorder��Getter����.��������ִ��˳��
*  ��������:2019-3-12
* @return java.lang.Integer
*/
public Integer getRunorder() {
return this.runorder;
} 

/**
* ����runorder��Setter����.��������ִ��˳��
* ��������:2019-3-12
* @param newRunorder java.lang.Integer
*/
public void setRunorder ( Integer runorder) {
this.runorder=runorder;
} 
 
/**
* ���� rowno��Getter����.���������к�
*  ��������:2019-3-12
* @return java.lang.String
*/
public String getRowno() {
return this.rowno;
} 

/**
* ����rowno��Setter����.���������к�
* ��������:2019-3-12
* @param newRowno java.lang.String
*/
public void setRowno ( String rowno) {
this.rowno=rowno;
} 
 
/**
* ���� pk_testcondition��Getter����.����������������
*  ��������:2019-3-12
* @return nc.vo.bd.defdoc.DefdocVO
*/
public String[] getPk_testcondition() {
return this.pk_testcondition;
} 

/**
* ����pk_testcondition��Setter����.����������������
* ��������:2019-3-12
* @param newPk_testcondition nc.vo.bd.defdoc.DefdocVO
*/
public void setPk_testcondition ( String[] pk_testcondition) {
this.pk_testcondition=pk_testcondition;
} 
 
/**
* ���� pk_testresultname��Getter����.�����������Խ������
*  ��������:2019-3-12
* @return nc.vo.bd.defdoc.DefdocVO
*/
public String getPk_testresultname() {
return this.pk_testresultname;
} 

/**
* ����pk_testresultname��Setter����.�����������Խ������
* ��������:2019-3-12
* @param newPk_testresultname nc.vo.bd.defdoc.DefdocVO
*/
public void setPk_testresultname ( String pk_testresultname) {
this.pk_testresultname=pk_testresultname;
} 
 
/**
* ���� testresultshortname��Getter����.�����������Խ��������
*  ��������:2019-3-12
* @return java.lang.String
*/
public String getTestresultshortname() {
return this.testresultshortname;
} 

/**
* ����testresultshortname��Setter����.�����������Խ��������
* ��������:2019-3-12
* @param newTestresultshortname java.lang.String
*/
public void setTestresultshortname ( String testresultshortname) {
this.testresultshortname=testresultshortname;
} 
 
/**
* ���� �����ϲ�������Getter����.���������ϲ�����
*  ��������:2019-3-12
* @return String
*/
public String getPk_task_h(){
return this.pk_task_h;
}
/**
* ���������ϲ�������Setter����.���������ϲ�����
* ��������:2019-3-12
* @param newPk_task_h String
*/
public void setPk_task_h(String pk_task_h){
this.pk_task_h=pk_task_h;
} 
/**
* ���� ����ʱ�����Getter����.��������ʱ���
*  ��������:2019-3-12
* @return nc.vo.pub.lang.UFDateTime
*/
/**
* ���� testnumber��Getter����.��������testnumber
*  ��������:2019-3-14
* @return java.lang.String
*/
public String getTestnumber() {
return this.testnumber;
} 

/**
* ����testnumber��Setter����.��������testnumber
* ��������:2019-3-14
* @param newTestnumber java.lang.String
*/
public void setTestnumber ( String testnumber) {
this.testnumber=testnumber;
} 
 
/**
* ���� sampleallocation��Getter����.����������Ʒ����
*  ��������:2019-3-14
* @return nc.vo.bd.defdoc.DefdocVO
*/
public String getSampleallocation() {
return this.sampleallocation;
} 

/**
* ����sampleallocation��Setter����.����������Ʒ����
* ��������:2019-3-14
* @param newSampleallocation nc.vo.bd.defdoc.DefdocVO
*/
public void setSampleallocation ( String sampleallocation) {
this.sampleallocation=sampleallocation;
} 
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
    return VOMetaFactory.getInstance().getVOMeta("qcco.task_b");
    }
   }
    