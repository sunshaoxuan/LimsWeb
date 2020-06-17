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
 
public class TestlistentryEVO extends SuperVO {
	
/**
*�������
*/
public String pk_testlistentry;
/**
*����
*/
public String code;
/**
*����
*/
public String name;
/**
*��ʾ˳��
*/
public Integer displayorder;
/**
*��׼����
*/
public String standardtest;
/**
*��λ
*/
public UFBoolean iscarry;
/**
*������
*/
public String refname;
/**
*����ֵ
*/
public String refvalue;
/**
*���Եȼ�
*/
public String testlevel;
/**
*����
*/
public String pk_instrument;
/**
*���Բ���
*/
public String pk_dept;
/**
*��־λ
*/
public UFBoolean flag;
/**
*�仯
*/
public String change;
/**
*����ͳ��
*/
public String analysestates;
/**
*��������
*/
public String reportname;
/**
*�Ƿ񱨸�
*/
public UFBoolean isreport;
/**
*��������
*/
public String condcomp;
/**
*�汾
*/
public String version;
/**
*�к�
*/
public String rowno;
/**
*�ϲ㵥������
*/
public String pk_testlistcomp;
/**
*ʱ���
*/
public UFDateTime ts;
    
    
/**
* ���� pk_testlistentry��Getter����.���������������
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getPk_testlistentry() {
return this.pk_testlistentry;
} 

/**
* ����pk_testlistentry��Setter����.���������������
* ��������:2018-12-5
* @param newPk_testlistentry java.lang.String
*/
public void setPk_testlistentry ( String pk_testlistentry) {
this.pk_testlistentry=pk_testlistentry;
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
* ���� standardtest��Getter����.����������׼����
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getStandardtest() {
return this.standardtest;
} 

/**
* ����standardtest��Setter����.����������׼����
* ��������:2018-12-5
* @param newStandardtest java.lang.String
*/
public void setStandardtest ( String standardtest) {
this.standardtest=standardtest;
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
* ���� refname��Getter����.��������������
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getRefname() {
return this.refname;
} 

/**
* ����refname��Setter����.��������������
* ��������:2018-12-5
* @param newRefname java.lang.String
*/
public void setRefname ( String refname) {
this.refname=refname;
} 
 
/**
* ���� refvalue��Getter����.������������ֵ
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getRefvalue() {
return this.refvalue;
} 

/**
* ����refvalue��Setter����.������������ֵ
* ��������:2018-12-5
* @param newRefvalue java.lang.String
*/
public void setRefvalue ( String refvalue) {
this.refvalue=refvalue;
} 
 
/**
* ���� testlevel��Getter����.�����������Եȼ�
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getTestlevel() {
return this.testlevel;
} 

/**
* ����testlevel��Setter����.�����������Եȼ�
* ��������:2018-12-5
* @param newTestlevel java.lang.String
*/
public void setTestlevel ( String testlevel) {
this.testlevel=testlevel;
} 
 
/**
* ���� pk_instrument��Getter����.������������
*  ��������:2018-12-5
* @return nc.vo.bd.defdoc.DefdocVO
*/
public String getPk_instrument() {
return this.pk_instrument;
} 

/**
* ����pk_instrument��Setter����.������������
* ��������:2018-12-5
* @param newPk_instrument nc.vo.bd.defdoc.DefdocVO
*/
public void setPk_instrument ( String pk_instrument) {
this.pk_instrument=pk_instrument;
} 
 
/**
* ���� pk_dept��Getter����.�����������Բ���
*  ��������:2018-12-5
* @return nc.vo.om.hrdept.HRDeptVO
*/
public String getPk_dept() {
return this.pk_dept;
} 

/**
* ����pk_dept��Setter����.�����������Բ���
* ��������:2018-12-5
* @param newPk_dept nc.vo.om.hrdept.HRDeptVO
*/
public void setPk_dept ( String pk_dept) {
this.pk_dept=pk_dept;
} 
 
/**
* ���� flag��Getter����.����������־λ
*  ��������:2018-12-5
* @return nc.vo.pub.lang.UFBoolean
*/
public UFBoolean getFlag() {
return this.flag;
} 

/**
* ����flag��Setter����.����������־λ
* ��������:2018-12-5
* @param newFlag nc.vo.pub.lang.UFBoolean
*/
public void setFlag ( UFBoolean flag) {
this.flag=flag;
} 
 
/**
* ���� change��Getter����.���������仯
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getChange() {
return this.change;
} 

/**
* ����change��Setter����.���������仯
* ��������:2018-12-5
* @param newChange java.lang.String
*/
public void setChange ( String change) {
this.change=change;
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
* ���� reportname��Getter����.����������������
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getReportname() {
return this.reportname;
} 

/**
* ����reportname��Setter����.����������������
* ��������:2018-12-5
* @param newReportname java.lang.String
*/
public void setReportname ( String reportname) {
this.reportname=reportname;
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
* ���� condcomp��Getter����.����������������
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getCondcomp() {
return this.condcomp;
} 

/**
* ����condcomp��Setter����.����������������
* ��������:2018-12-5
* @param newCondcomp java.lang.String
*/
public void setCondcomp ( String condcomp) {
this.condcomp=condcomp;
} 
 
/**
* ���� version��Getter����.���������汾
*  ��������:2018-12-5
* @return java.lang.String
*/
public String getVersion() {
return this.version;
} 

/**
* ����version��Setter����.���������汾
* ��������:2018-12-5
* @param newVersion java.lang.String
*/
public void setVersion ( String version) {
this.version=version;
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
public String getPk_testlistcomp(){
return this.pk_testlistcomp;
}
/**
* ���������ϲ�������Setter����.���������ϲ�����
* ��������:2018-12-5
* @param newPk_testlistcomp String
*/
public void setPk_testlistcomp(String pk_testlistcomp){
this.pk_testlistcomp=pk_testlistcomp;
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
    return VOMetaFactory.getInstance().getVOMeta("qcco.testlistentry");
    }
    
    @Override
   	public String getParentPKFieldName() {
   		// TODO Auto-generated method stub
   		return "pk_testlistcomp";
   	}
   }
    