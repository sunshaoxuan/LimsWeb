package nc.vo.qcco.qctestref;

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
 
public class TestrefDetailVO extends SuperVO {
	
/**
*�ӱ�����
*/
public String pk_testrefdetail;
/**
*�к�
*/
public String rowno;
/**
*���
*/
public String pk_component;
/**
*ֵ����
*/
public String pk_valuetype;
/**
*�ϲ㵥������
*/
public String pk_testrefs;
/**
*ʱ���
*/
public UFDateTime ts;
    
    
/**
* ���� pk_testrefdetail��Getter����.���������ӱ�����
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getPk_testrefdetail() {
return this.pk_testrefdetail;
} 

/**
* ����pk_testrefdetail��Setter����.���������ӱ�����
* ��������:2018-12-5
* @param newPk_testrefdetail java.lang.String
*/
public void setPk_testrefdetail ( String pk_testrefdetail) {
this.pk_testrefdetail=pk_testrefdetail;
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
* ���� pk_component��Getter����.�����������
*  ��������:2018-12-5
* @return nc.vo.bd.defdoc.DefdocVO
*/
public String getPk_component() {
return this.pk_component;
} 

/**
* ����pk_component��Setter����.�����������
* ��������:2018-12-5
* @param newPk_component nc.vo.bd.defdoc.DefdocVO
*/
public void setPk_component ( String pk_component) {
this.pk_component=pk_component;
} 
 
/**
* ���� pk_valuetype��Getter����.��������ֵ����
*  ��������:2018-12-5
* @return nc.vo.qcco.qcvaluetype.ValueTypeHVO
*/
public String getPk_valuetype() {
return this.pk_valuetype;
} 

/**
* ����pk_valuetype��Setter����.��������ֵ����
* ��������:2018-12-5
* @param newPk_valuetype nc.vo.qcco.qcvaluetype.ValueTypeHVO
*/
public void setPk_valuetype ( String pk_valuetype) {
this.pk_valuetype=pk_valuetype;
} 
 
/**
* ���� �����ϲ�������Getter����.���������ϲ�����
*  ��������:2018-12-5
* @return String
*/
public String getPk_testrefs(){
return this.pk_testrefs;
}
/**
* ���������ϲ�������Setter����.���������ϲ�����
* ��������:2018-12-5
* @param newPk_testrefs String
*/
public void setPk_testrefs(String pk_testrefs){
this.pk_testrefs=pk_testrefs;
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
    return VOMetaFactory.getInstance().getVOMeta("qcco.testrefdetail");
    }
   }
    