package nc.vo.qcco.analysemethod;

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
 
public class TestitemBVO extends SuperVO {
	
/**
*�ӱ�����
*/
public String pk_testitem;
/**
*ֵ����
*/
public String pk_valuetype;
/**
*���
*/
public String pk_component;
/**
*�к�
*/
public String rowno;
/**
*�ϲ㵥������
*/
public String pk_analysemethod;
/**
*ʱ���
*/
public UFDateTime ts;
    
    
/**
* ���� pk_testitem��Getter����.���������ӱ�����
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getPk_testitem() {
return this.pk_testitem;
} 

/**
* ����pk_testitem��Setter����.���������ӱ�����
* ��������:2018-12-5
* @param newPk_testitem java.lang.String
*/
public void setPk_testitem ( String pk_testitem) {
this.pk_testitem=pk_testitem;
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
public String getPk_analysemethod(){
return this.pk_analysemethod;
}
/**
* ���������ϲ�������Setter����.���������ϲ�����
* ��������:2018-12-5
* @param newPk_analysemethod String
*/
public void setPk_analysemethod(String pk_analysemethod){
this.pk_analysemethod=pk_analysemethod;
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
    return VOMetaFactory.getInstance().getVOMeta("qcco.testitem");
    }
   }
    