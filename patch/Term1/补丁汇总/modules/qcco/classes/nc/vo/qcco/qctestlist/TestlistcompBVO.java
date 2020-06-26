package nc.vo.qcco.qctestlist;

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
 
public class TestlistcompBVO extends SuperVO {
	public TestlistentryEVO [] pk_testlistentry;
public TestlistentryEVO[] getPk_testlistentry() {
		return pk_testlistentry;
	}

	public void setPk_testlistentry(TestlistentryEVO[] pk_testlistentry) {
		this.pk_testlistentry = pk_testlistentry;
	}

/**
*�ӱ�����
*/
public String pk_testlistcomp;
/**
*����
*/
public String code;
/**
*����
*/
public String name;
/**
*���
*/
public String component;
/**
*����ͳ��
*/
public String analysestates;
/**
*��ʾ˳��
*/
public Integer displayorder;
/**
*�����ʾ˳��
*/
public Integer resultorder;
/**
*��λ
*/
public String pk_unitdoc;
/**
*�Ƿ���������
*/
public UFBoolean isrounding;
/**
*С��λ
*/
public Integer decbits;
/**
*��λ
*/
public UFBoolean iscarry;
/**
*��Сֵ
*/
public UFDouble minvalue;
/**
*���ֵ
*/
public UFDouble maxvalue;
/**
*�Ƿ񱨸�
*/
public UFBoolean isreport;
/**
*�Ƿ�Ǳ���
*/
public UFBoolean isoptional;
/**
*�Ƿ���ʾ
*/
public UFBoolean isdisplay;
/**
*Ҫ��ֵ
*/
public String factorvalue;
/**
*�����汾
*/
public String analyseversion;
/**
*����Ĭ��ֵ
*/
public String cndefaultvalue;
/**
*Ӣ��Ĭ��ֵ
*/
public String endefaultvalue;
/**
*List
*/
public String pk_listdoc;
/**
*Ĭ�����ݴ洢����
*/
public String storagename;
/**
*�к�
*/
public String rowno;
/**
*�ϲ㵥������
*/
public String pk_testlist;
/**
*ʱ���
*/
public UFDateTime ts;
    
    
/**
* ���� pk_testlistcomp��Getter����.���������ӱ�����
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getPk_testlistcomp() {
return this.pk_testlistcomp;
} 

/**
* ����pk_testlistcomp��Setter����.���������ӱ�����
* ��������:2018-12-5
* @param newPk_testlistcomp java.lang.String
*/
public void setPk_testlistcomp ( String pk_testlistcomp) {
this.pk_testlistcomp=pk_testlistcomp;
} 
 
/**
* ���� code��Getter����.������������
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getCode() {
return this.code;
} 

/**
* ����code��Setter����.������������
* ��������:2018-12-5
* @param newCode java.lang.String
*/
public void setCode ( String code) {
this.code=code;
} 
 
/**
* ���� name��Getter����.������������
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getName() {
return this.name;
} 

/**
* ����name��Setter����.������������
* ��������:2018-12-5
* @param newName java.lang.String
*/
public void setName ( String name) {
this.name=name;
} 
 
/**
* ���� component��Getter����.�����������
*  ��������:2018-12-5
* @return nc.vo.bd.defdoc.DefdocVO
*/
public String getComponent() {
return this.component;
} 

/**
* ����component��Setter����.�����������
* ��������:2018-12-5
* @param newComponent nc.vo.bd.defdoc.DefdocVO
*/
public void setComponent ( String component) {
this.component=component;
} 
 
/**
* ���� analysestates��Getter����.������������ͳ��
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getAnalysestates() {
return this.analysestates;
} 

/**
* ����analysestates��Setter����.������������ͳ��
* ��������:2018-12-5
* @param newAnalysestates java.lang.String
*/
public void setAnalysestates ( String analysestates) {
this.analysestates=analysestates;
} 
 
/**
* ���� displayorder��Getter����.����������ʾ˳��
*  ��������:2018-12-5
* @return java.lang.Integer
*/
public Integer getDisplayorder() {
return this.displayorder;
} 

/**
* ����displayorder��Setter����.����������ʾ˳��
* ��������:2018-12-5
* @param newDisplayorder java.lang.Integer
*/
public void setDisplayorder ( Integer displayorder) {
this.displayorder=displayorder;
} 
 
/**
* ���� resultorder��Getter����.�������������ʾ˳��
*  ��������:2018-12-5
* @return java.lang.Integer
*/
public Integer getResultorder() {
return this.resultorder;
} 

/**
* ����resultorder��Setter����.�������������ʾ˳��
* ��������:2018-12-5
* @param newResultorder java.lang.Integer
*/
public void setResultorder ( Integer resultorder) {
this.resultorder=resultorder;
} 
 
/**
* ���� pk_unitdoc��Getter����.����������λ
*  ��������:2018-12-5
* @return nc.vo.bd.defdoc.DefdocVO
*/
public String getPk_unitdoc() {
return this.pk_unitdoc;
} 

/**
* ����pk_unitdoc��Setter����.����������λ
* ��������:2018-12-5
* @param newPk_unitdoc nc.vo.bd.defdoc.DefdocVO
*/
public void setPk_unitdoc ( String pk_unitdoc) {
this.pk_unitdoc=pk_unitdoc;
} 
 
/**
* ���� isrounding��Getter����.���������Ƿ���������
*  ��������:2018-12-5
* @return nc.vo.pub.lang.UFBoolean
*/
public UFBoolean getIsrounding() {
return this.isrounding;
} 

/**
* ����isrounding��Setter����.���������Ƿ���������
* ��������:2018-12-5
* @param newIsrounding nc.vo.pub.lang.UFBoolean
*/
public void setIsrounding ( UFBoolean isrounding) {
this.isrounding=isrounding;
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
* ���� iscarry��Getter����.����������λ
*  ��������:2018-12-5
* @return nc.vo.pub.lang.UFBoolean
*/
public UFBoolean getIscarry() {
return this.iscarry;
} 

/**
* ����iscarry��Setter����.����������λ
* ��������:2018-12-5
* @param newIscarry nc.vo.pub.lang.UFBoolean
*/
public void setIscarry ( UFBoolean iscarry) {
this.iscarry=iscarry;
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
* ���� isoptional��Getter����.���������Ƿ�Ǳ���
*  ��������:2018-12-5
* @return nc.vo.pub.lang.UFBoolean
*/
public UFBoolean getIsoptional() {
return this.isoptional;
} 

/**
* ����isoptional��Setter����.���������Ƿ�Ǳ���
* ��������:2018-12-5
* @param newIsoptional nc.vo.pub.lang.UFBoolean
*/
public void setIsoptional ( UFBoolean isoptional) {
this.isoptional=isoptional;
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
* ���� factorvalue��Getter����.��������Ҫ��ֵ
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getFactorvalue() {
return this.factorvalue;
} 

/**
* ����factorvalue��Setter����.��������Ҫ��ֵ
* ��������:2018-12-5
* @param newFactorvalue java.lang.String
*/
public void setFactorvalue ( String factorvalue) {
this.factorvalue=factorvalue;
} 
 
/**
* ���� analyseversion��Getter����.�������������汾
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getAnalyseversion() {
return this.analyseversion;
} 

/**
* ����analyseversion��Setter����.�������������汾
* ��������:2018-12-5
* @param newAnalyseversion java.lang.String
*/
public void setAnalyseversion ( String analyseversion) {
this.analyseversion=analyseversion;
} 
 
/**
* ���� cndefaultvalue��Getter����.������������Ĭ��ֵ
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getCndefaultvalue() {
return this.cndefaultvalue;
} 

/**
* ����cndefaultvalue��Setter����.������������Ĭ��ֵ
* ��������:2018-12-5
* @param newCndefaultvalue java.lang.String
*/
public void setCndefaultvalue ( String cndefaultvalue) {
this.cndefaultvalue=cndefaultvalue;
} 
 
/**
* ���� endefaultvalue��Getter����.��������Ӣ��Ĭ��ֵ
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getEndefaultvalue() {
return this.endefaultvalue;
} 

/**
* ����endefaultvalue��Setter����.��������Ӣ��Ĭ��ֵ
* ��������:2018-12-5
* @param newEndefaultvalue java.lang.String
*/
public void setEndefaultvalue ( String endefaultvalue) {
this.endefaultvalue=endefaultvalue;
} 
 
/**
* ���� pk_listdoc��Getter����.��������List
*  ��������:2018-12-5
* @return nc.vo.qcco.qclistdoc.ListdocHVO
*/
public String getPk_listdoc() {
return this.pk_listdoc;
} 

/**
* ����pk_listdoc��Setter����.��������List
* ��������:2018-12-5
* @param newPk_listdoc nc.vo.qcco.qclistdoc.ListdocHVO
*/
public void setPk_listdoc ( String pk_listdoc) {
this.pk_listdoc=pk_listdoc;
} 
 
/**
* ���� storagename��Getter����.��������Ĭ�����ݴ洢����
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getStoragename() {
return this.storagename;
} 

/**
* ����storagename��Setter����.��������Ĭ�����ݴ洢����
* ��������:2018-12-5
* @param newStoragename java.lang.String
*/
public void setStoragename ( String storagename) {
this.storagename=storagename;
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
public String getPk_testlist(){
return this.pk_testlist;
}
/**
* ���������ϲ�������Setter����.���������ϲ�����
* ��������:2018-12-5
* @param newPk_testlist String
*/
public void setPk_testlist(String pk_testlist){
this.pk_testlist=pk_testlist;
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
    return VOMetaFactory.getInstance().getVOMeta("qcco.testlistcomp");
    }
   }
    