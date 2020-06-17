package nc.vo.qcco.qcanalysecondition;

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
 
public class AnalyseConditionHVO extends SuperVO {
	
/**
*��������
*/
public String pk_analysecondition;
/**
*����
*/
public String pk_group;
/**
*��֯
*/
public String pk_org;
/**
*��֯�汾
*/
public String pk_org_v;
/**
*������
*/
public String creator;
/**
*����ʱ��
*/
public UFDateTime creationtime;
/**
*�޸���
*/
public String modifier;
/**
*�޸�ʱ��
*/
public UFDateTime modifiedtime;
/**
*������������
*/
public String code;
/**
*������������
*/
public String name;
/**
*�Ƶ�ʱ��
*/
public UFDateTime maketime;
/**
*����޸�ʱ��
*/
public UFDateTime lastmaketime;
/**
*ֵ����
*/
public String pk_valuetype;
/**
*�������
*/
public String componentname;
/**
*�����汾
*/
public String docversion;
/**
*�����ʾ˳��
*/
public Integer displayorder;
/**
*�������
*/
public String resulttype;
/**
*��λ
*/
public String unit;
/**
*��Сֵ
*/
public UFDouble minvalue;
/**
*���ֵ
*/
public UFDouble maxvalue;
/**
*�Ƿ��Ͽ�
*/
public UFBoolean isconfirm;
/**
*�Ƿ��ȡ��λ
*/
public UFBoolean istruncate;
/**
*С��λ
*/
public Integer decbits;
/**
*attrDisplayName8
*/
public String attrname8;
/**
*ʹ������
*/
public String pk_instrument;
/**
*ʹ�ñ���
*/
public String usecode;
/**
*�Ƿ��Զ�����
*/
public UFBoolean isautocalc;
/**
*�б��
*/
public String pk_listdoc;
/**
*�Ƿ�����ȡ��
*/
public UFBoolean cancelable;
/**
*�Ƿ񱨸�
*/
public UFBoolean isreport;
/**
*�Ƿ��ѡ
*/
public UFBoolean selectable;
/**
*�Ƿ����������λ
*/
public UFBoolean iscarry;
/**
*�Ƿ�������
*/
public UFBoolean hasprop;
/**
*��ʽ������
*/
public UFBoolean isformatcalc;
/**
*�Ƿ���ʾ
*/
public UFBoolean isdisplay;
/**
*Lims����
*/
public String limsprogram;
/**
*λ��2
*/
public Integer bit2;
/**
*������
*/
public String result;
/**
*ת������
*/
public String transtype;
/**
*��Դ����
*/
public String sourcetype;
/**
*��Դ��λ
*/
public String pk_sourcedept;
/**
*Ŀ�굥λ
*/
public String pk_targetdept;
/**
*��������
*/
public String pk_contactortype;
/**
*����ͺ�
*/
public String pk_modelno;
/**
*����Ĭ��ֵ
*/
public String cndefvalue;
/**
*Ӣ��Ĭ��ֵ
*/
public String endefvalue;
/**
*attrDisplayName4
*/
public String reportname;
/**
*�Ƶ�����
*/
public UFDate dbilldate;
/**
*ʱ���
*/
public UFDateTime ts;
    
    
/**
* ���� pk_analysecondition��Getter����.����������������
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getPk_analysecondition() {
return this.pk_analysecondition;
} 

/**
* ����pk_analysecondition��Setter����.����������������
* ��������:2018-12-5
* @param newPk_analysecondition java.lang.String
*/
public void setPk_analysecondition ( String pk_analysecondition) {
this.pk_analysecondition=pk_analysecondition;
} 
 
/**
* ���� pk_group��Getter����.������������
*  ��������:2018-12-5
* @return nc.vo.org.GroupVO
*/
public String getPk_group() {
return this.pk_group;
} 

/**
* ����pk_group��Setter����.������������
* ��������:2018-12-5
* @param newPk_group nc.vo.org.GroupVO
*/
public void setPk_group ( String pk_group) {
this.pk_group=pk_group;
} 
 
/**
* ���� pk_org��Getter����.����������֯
*  ��������:2018-12-5
* @return nc.vo.org.OrgVO
*/
public String getPk_org() {
return this.pk_org;
} 

/**
* ����pk_org��Setter����.����������֯
* ��������:2018-12-5
* @param newPk_org nc.vo.org.OrgVO
*/
public void setPk_org ( String pk_org) {
this.pk_org=pk_org;
} 
 
/**
* ���� pk_org_v��Getter����.����������֯�汾
*  ��������:2018-12-5
* @return nc.vo.vorg.OrgVersionVO
*/
public String getPk_org_v() {
return this.pk_org_v;
} 

/**
* ����pk_org_v��Setter����.����������֯�汾
* ��������:2018-12-5
* @param newPk_org_v nc.vo.vorg.OrgVersionVO
*/
public void setPk_org_v ( String pk_org_v) {
this.pk_org_v=pk_org_v;
} 
 
/**
* ���� creator��Getter����.��������������
*  ��������:2018-12-5
* @return nc.vo.sm.UserVO
*/
public String getCreator() {
return this.creator;
} 

/**
* ����creator��Setter����.��������������
* ��������:2018-12-5
* @param newCreator nc.vo.sm.UserVO
*/
public void setCreator ( String creator) {
this.creator=creator;
} 
 
/**
* ���� creationtime��Getter����.������������ʱ��
*  ��������:2018-12-5
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getCreationtime() {
return this.creationtime;
} 

/**
* ����creationtime��Setter����.������������ʱ��
* ��������:2018-12-5
* @param newCreationtime nc.vo.pub.lang.UFDateTime
*/
public void setCreationtime ( UFDateTime creationtime) {
this.creationtime=creationtime;
} 
 
/**
* ���� modifier��Getter����.���������޸���
*  ��������:2018-12-5
* @return nc.vo.sm.UserVO
*/
public String getModifier() {
return this.modifier;
} 

/**
* ����modifier��Setter����.���������޸���
* ��������:2018-12-5
* @param newModifier nc.vo.sm.UserVO
*/
public void setModifier ( String modifier) {
this.modifier=modifier;
} 
 
/**
* ���� modifiedtime��Getter����.���������޸�ʱ��
*  ��������:2018-12-5
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getModifiedtime() {
return this.modifiedtime;
} 

/**
* ����modifiedtime��Setter����.���������޸�ʱ��
* ��������:2018-12-5
* @param newModifiedtime nc.vo.pub.lang.UFDateTime
*/
public void setModifiedtime ( UFDateTime modifiedtime) {
this.modifiedtime=modifiedtime;
} 
 
/**
* ���� code��Getter����.��������������������
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getCode() {
return this.code;
} 

/**
* ����code��Setter����.��������������������
* ��������:2018-12-5
* @param newCode java.lang.String
*/
public void setCode ( String code) {
this.code=code;
} 
 
/**
* ���� name��Getter����.��������������������
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getName() {
return this.name;
} 

/**
* ����name��Setter����.��������������������
* ��������:2018-12-5
* @param newName java.lang.String
*/
public void setName ( String name) {
this.name=name;
} 
 
/**
* ���� maketime��Getter����.���������Ƶ�ʱ��
*  ��������:2018-12-5
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getMaketime() {
return this.maketime;
} 

/**
* ����maketime��Setter����.���������Ƶ�ʱ��
* ��������:2018-12-5
* @param newMaketime nc.vo.pub.lang.UFDateTime
*/
public void setMaketime ( UFDateTime maketime) {
this.maketime=maketime;
} 
 
/**
* ���� lastmaketime��Getter����.������������޸�ʱ��
*  ��������:2018-12-5
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getLastmaketime() {
return this.lastmaketime;
} 

/**
* ����lastmaketime��Setter����.������������޸�ʱ��
* ��������:2018-12-5
* @param newLastmaketime nc.vo.pub.lang.UFDateTime
*/
public void setLastmaketime ( UFDateTime lastmaketime) {
this.lastmaketime=lastmaketime;
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
* ���� componentname��Getter����.���������������
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getComponentname() {
return this.componentname;
} 

/**
* ����componentname��Setter����.���������������
* ��������:2018-12-5
* @param newComponentname java.lang.String
*/
public void setComponentname ( String componentname) {
this.componentname=componentname;
} 
 
/**
* ���� docversion��Getter����.�������������汾
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getDocversion() {
return this.docversion;
} 

/**
* ����docversion��Setter����.�������������汾
* ��������:2018-12-5
* @param newDocversion java.lang.String
*/
public void setDocversion ( String docversion) {
this.docversion=docversion;
} 
 
/**
* ���� displayorder��Getter����.�������������ʾ˳��
*  ��������:2018-12-5
* @return java.lang.Integer
*/
public Integer getDisplayorder() {
return this.displayorder;
} 

/**
* ����displayorder��Setter����.�������������ʾ˳��
* ��������:2018-12-5
* @param newDisplayorder java.lang.Integer
*/
public void setDisplayorder ( Integer displayorder) {
this.displayorder=displayorder;
} 
 
/**
* ���� resulttype��Getter����.���������������
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getResulttype() {
return this.resulttype;
} 

/**
* ����resulttype��Setter����.���������������
* ��������:2018-12-5
* @param newResulttype java.lang.String
*/
public void setResulttype ( String resulttype) {
this.resulttype=resulttype;
} 
 
/**
* ���� unit��Getter����.����������λ
*  ��������:2018-12-5
* @return nc.vo.bd.defdoc.DefdocVO
*/
public String getUnit() {
return this.unit;
} 

/**
* ����unit��Setter����.����������λ
* ��������:2018-12-5
* @param newUnit nc.vo.bd.defdoc.DefdocVO
*/
public void setUnit ( String unit) {
this.unit=unit;
} 
 
/**
* ���� minvalue��Getter����.����������Сֵ
*  ��������:2018-12-5
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getMinvalue() {
return this.minvalue;
} 

/**
* ����minvalue��Setter����.����������Сֵ
* ��������:2018-12-5
* @param newMinvalue nc.vo.pub.lang.UFDouble
*/
public void setMinvalue ( UFDouble minvalue) {
this.minvalue=minvalue;
} 
 
/**
* ���� maxvalue��Getter����.�����������ֵ
*  ��������:2018-12-5
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getMaxvalue() {
return this.maxvalue;
} 

/**
* ����maxvalue��Setter����.�����������ֵ
* ��������:2018-12-5
* @param newMaxvalue nc.vo.pub.lang.UFDouble
*/
public void setMaxvalue ( UFDouble maxvalue) {
this.maxvalue=maxvalue;
} 
 
/**
* ���� isconfirm��Getter����.���������Ƿ��Ͽ�
*  ��������:2018-12-5
* @return nc.vo.pub.lang.UFBoolean
*/
public UFBoolean getIsconfirm() {
return this.isconfirm;
} 

/**
* ����isconfirm��Setter����.���������Ƿ��Ͽ�
* ��������:2018-12-5
* @param newIsconfirm nc.vo.pub.lang.UFBoolean
*/
public void setIsconfirm ( UFBoolean isconfirm) {
this.isconfirm=isconfirm;
} 
 
/**
* ���� istruncate��Getter����.���������Ƿ��ȡ��λ
*  ��������:2018-12-5
* @return nc.vo.pub.lang.UFBoolean
*/
public UFBoolean getIstruncate() {
return this.istruncate;
} 

/**
* ����istruncate��Setter����.���������Ƿ��ȡ��λ
* ��������:2018-12-5
* @param newIstruncate nc.vo.pub.lang.UFBoolean
*/
public void setIstruncate ( UFBoolean istruncate) {
this.istruncate=istruncate;
} 
 
/**
* ���� decbits��Getter����.��������С��λ
*  ��������:2018-12-5
* @return java.lang.Integer
*/
public Integer getDecbits() {
return this.decbits;
} 

/**
* ����decbits��Setter����.��������С��λ
* ��������:2018-12-5
* @param newDecbits java.lang.Integer
*/
public void setDecbits ( Integer decbits) {
this.decbits=decbits;
} 
 
/**
* ���� attrname8��Getter����.��������attrDisplayName8
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getAttrname8() {
return this.attrname8;
} 

/**
* ����attrname8��Setter����.��������attrDisplayName8
* ��������:2018-12-5
* @param newAttrname8 java.lang.String
*/
public void setAttrname8 ( String attrname8) {
this.attrname8=attrname8;
} 
 
/**
* ���� pk_instrument��Getter����.��������ʹ������
*  ��������:2018-12-5
* @return nc.vo.bd.defdoc.DefdocVO
*/
public String getPk_instrument() {
return this.pk_instrument;
} 

/**
* ����pk_instrument��Setter����.��������ʹ������
* ��������:2018-12-5
* @param newPk_instrument nc.vo.bd.defdoc.DefdocVO
*/
public void setPk_instrument ( String pk_instrument) {
this.pk_instrument=pk_instrument;
} 
 
/**
* ���� usecode��Getter����.��������ʹ�ñ���
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getUsecode() {
return this.usecode;
} 

/**
* ����usecode��Setter����.��������ʹ�ñ���
* ��������:2018-12-5
* @param newUsecode java.lang.String
*/
public void setUsecode ( String usecode) {
this.usecode=usecode;
} 
 
/**
* ���� isautocalc��Getter����.���������Ƿ��Զ�����
*  ��������:2018-12-5
* @return nc.vo.pub.lang.UFBoolean
*/
public UFBoolean getIsautocalc() {
return this.isautocalc;
} 

/**
* ����isautocalc��Setter����.���������Ƿ��Զ�����
* ��������:2018-12-5
* @param newIsautocalc nc.vo.pub.lang.UFBoolean
*/
public void setIsautocalc ( UFBoolean isautocalc) {
this.isautocalc=isautocalc;
} 
 
/**
* ���� pk_listdoc��Getter����.���������б��
*  ��������:2018-12-5
* @return nc.vo.qcco.qclistdoc.ListdocHVO
*/
public String getPk_listdoc() {
return this.pk_listdoc;
} 

/**
* ����pk_listdoc��Setter����.���������б��
* ��������:2018-12-5
* @param newPk_listdoc nc.vo.qcco.qclistdoc.ListdocHVO
*/
public void setPk_listdoc ( String pk_listdoc) {
this.pk_listdoc=pk_listdoc;
} 
 
/**
* ���� cancelable��Getter����.���������Ƿ�����ȡ��
*  ��������:2018-12-5
* @return nc.vo.pub.lang.UFBoolean
*/
public UFBoolean getCancelable() {
return this.cancelable;
} 

/**
* ����cancelable��Setter����.���������Ƿ�����ȡ��
* ��������:2018-12-5
* @param newCancelable nc.vo.pub.lang.UFBoolean
*/
public void setCancelable ( UFBoolean cancelable) {
this.cancelable=cancelable;
} 
 
/**
* ���� isreport��Getter����.���������Ƿ񱨸�
*  ��������:2018-12-5
* @return nc.vo.pub.lang.UFBoolean
*/
public UFBoolean getIsreport() {
return this.isreport;
} 

/**
* ����isreport��Setter����.���������Ƿ񱨸�
* ��������:2018-12-5
* @param newIsreport nc.vo.pub.lang.UFBoolean
*/
public void setIsreport ( UFBoolean isreport) {
this.isreport=isreport;
} 
 
/**
* ���� selectable��Getter����.���������Ƿ��ѡ
*  ��������:2018-12-5
* @return nc.vo.pub.lang.UFBoolean
*/
public UFBoolean getSelectable() {
return this.selectable;
} 

/**
* ����selectable��Setter����.���������Ƿ��ѡ
* ��������:2018-12-5
* @param newSelectable nc.vo.pub.lang.UFBoolean
*/
public void setSelectable ( UFBoolean selectable) {
this.selectable=selectable;
} 
 
/**
* ���� iscarry��Getter����.���������Ƿ����������λ
*  ��������:2018-12-5
* @return nc.vo.pub.lang.UFBoolean
*/
public UFBoolean getIscarry() {
return this.iscarry;
} 

/**
* ����iscarry��Setter����.���������Ƿ����������λ
* ��������:2018-12-5
* @param newIscarry nc.vo.pub.lang.UFBoolean
*/
public void setIscarry ( UFBoolean iscarry) {
this.iscarry=iscarry;
} 
 
/**
* ���� hasprop��Getter����.���������Ƿ�������
*  ��������:2018-12-5
* @return nc.vo.pub.lang.UFBoolean
*/
public UFBoolean getHasprop() {
return this.hasprop;
} 

/**
* ����hasprop��Setter����.���������Ƿ�������
* ��������:2018-12-5
* @param newHasprop nc.vo.pub.lang.UFBoolean
*/
public void setHasprop ( UFBoolean hasprop) {
this.hasprop=hasprop;
} 
 
/**
* ���� isformatcalc��Getter����.����������ʽ������
*  ��������:2018-12-5
* @return nc.vo.pub.lang.UFBoolean
*/
public UFBoolean getIsformatcalc() {
return this.isformatcalc;
} 

/**
* ����isformatcalc��Setter����.����������ʽ������
* ��������:2018-12-5
* @param newIsformatcalc nc.vo.pub.lang.UFBoolean
*/
public void setIsformatcalc ( UFBoolean isformatcalc) {
this.isformatcalc=isformatcalc;
} 
 
/**
* ���� isdisplay��Getter����.���������Ƿ���ʾ
*  ��������:2018-12-5
* @return nc.vo.pub.lang.UFBoolean
*/
public UFBoolean getIsdisplay() {
return this.isdisplay;
} 

/**
* ����isdisplay��Setter����.���������Ƿ���ʾ
* ��������:2018-12-5
* @param newIsdisplay nc.vo.pub.lang.UFBoolean
*/
public void setIsdisplay ( UFBoolean isdisplay) {
this.isdisplay=isdisplay;
} 
 
/**
* ���� limsprogram��Getter����.��������Lims����
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getLimsprogram() {
return this.limsprogram;
} 

/**
* ����limsprogram��Setter����.��������Lims����
* ��������:2018-12-5
* @param newLimsprogram java.lang.String
*/
public void setLimsprogram ( String limsprogram) {
this.limsprogram=limsprogram;
} 
 
/**
* ���� bit2��Getter����.��������λ��2
*  ��������:2018-12-5
* @return java.lang.Integer
*/
public Integer getBit2() {
return this.bit2;
} 

/**
* ����bit2��Setter����.��������λ��2
* ��������:2018-12-5
* @param newBit2 java.lang.Integer
*/
public void setBit2 ( Integer bit2) {
this.bit2=bit2;
} 
 
/**
* ���� result��Getter����.��������������
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getResult() {
return this.result;
} 

/**
* ����result��Setter����.��������������
* ��������:2018-12-5
* @param newResult java.lang.String
*/
public void setResult ( String result) {
this.result=result;
} 
 
/**
* ���� transtype��Getter����.��������ת������
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getTranstype() {
return this.transtype;
} 

/**
* ����transtype��Setter����.��������ת������
* ��������:2018-12-5
* @param newTranstype java.lang.String
*/
public void setTranstype ( String transtype) {
this.transtype=transtype;
} 
 
/**
* ���� sourcetype��Getter����.����������Դ����
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getSourcetype() {
return this.sourcetype;
} 

/**
* ����sourcetype��Setter����.����������Դ����
* ��������:2018-12-5
* @param newSourcetype java.lang.String
*/
public void setSourcetype ( String sourcetype) {
this.sourcetype=sourcetype;
} 
 
/**
* ���� pk_sourcedept��Getter����.����������Դ��λ
*  ��������:2018-12-5
* @return nc.vo.bd.defdoc.DefdocVO
*/
public String getPk_sourcedept() {
return this.pk_sourcedept;
} 

/**
* ����pk_sourcedept��Setter����.����������Դ��λ
* ��������:2018-12-5
* @param newPk_sourcedept nc.vo.bd.defdoc.DefdocVO
*/
public void setPk_sourcedept ( String pk_sourcedept) {
this.pk_sourcedept=pk_sourcedept;
} 
 
/**
* ���� pk_targetdept��Getter����.��������Ŀ�굥λ
*  ��������:2018-12-5
* @return nc.vo.bd.defdoc.DefdocVO
*/
public String getPk_targetdept() {
return this.pk_targetdept;
} 

/**
* ����pk_targetdept��Setter����.��������Ŀ�굥λ
* ��������:2018-12-5
* @param newPk_targetdept nc.vo.bd.defdoc.DefdocVO
*/
public void setPk_targetdept ( String pk_targetdept) {
this.pk_targetdept=pk_targetdept;
} 
 
/**
* ���� pk_contactortype��Getter����.����������������
*  ��������:2018-12-5
* @return nc.vo.qcco.qccontactortype.ContactorTypeVO
*/
public String getPk_contactortype() {
return this.pk_contactortype;
} 

/**
* ����pk_contactortype��Setter����.����������������
* ��������:2018-12-5
* @param newPk_contactortype nc.vo.qcco.qccontactortype.ContactorTypeVO
*/
public void setPk_contactortype ( String pk_contactortype) {
this.pk_contactortype=pk_contactortype;
} 
 
/**
* ���� pk_modelno��Getter����.������������ͺ�
*  ��������:2018-12-5
* @return nc.vo.qcco.qcmodelno.ModelnoVO
*/
public String getPk_modelno() {
return this.pk_modelno;
} 

/**
* ����pk_modelno��Setter����.������������ͺ�
* ��������:2018-12-5
* @param newPk_modelno nc.vo.qcco.qcmodelno.ModelnoVO
*/
public void setPk_modelno ( String pk_modelno) {
this.pk_modelno=pk_modelno;
} 
 
/**
* ���� cndefvalue��Getter����.������������Ĭ��ֵ
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getCndefvalue() {
return this.cndefvalue;
} 

/**
* ����cndefvalue��Setter����.������������Ĭ��ֵ
* ��������:2018-12-5
* @param newCndefvalue java.lang.String
*/
public void setCndefvalue ( String cndefvalue) {
this.cndefvalue=cndefvalue;
} 
 
/**
* ���� endefvalue��Getter����.��������Ӣ��Ĭ��ֵ
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getEndefvalue() {
return this.endefvalue;
} 

/**
* ����endefvalue��Setter����.��������Ӣ��Ĭ��ֵ
* ��������:2018-12-5
* @param newEndefvalue java.lang.String
*/
public void setEndefvalue ( String endefvalue) {
this.endefvalue=endefvalue;
} 
 
/**
* ���� reportname��Getter����.��������attrDisplayName4
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getReportname() {
return this.reportname;
} 

/**
* ����reportname��Setter����.��������attrDisplayName4
* ��������:2018-12-5
* @param newReportname java.lang.String
*/
public void setReportname ( String reportname) {
this.reportname=reportname;
} 
 
/**
* ���� dbilldate��Getter����.���������Ƶ�����
*  ��������:2018-12-5
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getDbilldate() {
return this.dbilldate;
} 

/**
* ����dbilldate��Setter����.���������Ƶ�����
* ��������:2018-12-5
* @param newDbilldate nc.vo.pub.lang.UFDate
*/
public void setDbilldate ( UFDate dbilldate) {
this.dbilldate=dbilldate;
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
    return VOMetaFactory.getInstance().getVOMeta("qcco.analysecondition");
    }
   }
    