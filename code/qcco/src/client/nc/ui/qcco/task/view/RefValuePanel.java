package nc.ui.qcco.task.view;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;

import uap.distribution.message.MessageErrors;
import bsh.This;

import com.borland.jbcl.control.CheckboxPanel;
import com.hazelcast.spi.annotation.PrivateApi;
import com.ibm.db2.jcc.sqlj.e;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;
import com.ufida.report.free.plugin.fieldattribute.SmartCheckBoxList;
import com.ufida.report.free.plugin.fieldattribute.SmartListData;

import nc.bs.dao.DAOException;
import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.hr.utils.ResHelper;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.SQLParameter;
import nc.jdbc.framework.processor.MapListProcessor;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIButton;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UILabel;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.beans.UIScrollPane;
import nc.ui.pub.beans.UITextField;
import nc.ui.pub.beans.ValueChangedEvent;
import nc.ui.pub.beans.ValueChangedListener;
import nc.ui.pub.beans.progress.IProgressMonitor;
import nc.ui.pub.beans.progress.NCProgresses;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillEditEvent;
import nc.ui.pub.bill.BillListData;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.formula.ui.InputHandler.insert_break;
import nc.ui.qcco.commission.refmodel.SampleAllocationRefModel;
import nc.ui.qcco.commission.refmodel.UnitTypeRefModel;
import nc.ui.qcco.task.utils.StringOrderUtils;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.qcco.task.RefValueVO;
import nc.vo.qcco.task.TaskBodyVO;
import nc.vo.trade.bdinfo.BaseDocVO;

public class RefValuePanel extends UIDialog implements
		nc.ui.pub.bill.BillEditListener2, ActionListener {
	private BillListPanel billListHeadPanel = null;
	private BillCardPanel billListBodyPanel = null;
	private static final long serialVersionUID = 1L;
	private UIPanel bnPanel = null;
	private UIButton okButton = null;
	private UIButton cancelButton = null;
	private Checkbox checkbox = null;

	private JPanel ivjUIDialogContentPane1;

	private UIPanel ivjUIPanel1 = null;
	private JTextField projectTypeField = null;// 项目类型
	private UILabel projectTypeLabel = null;// 项目类型

	private UIButton btnOKtop = null;// 确定按钮
	List<Integer> listnum = new ArrayList<Integer>();
	private String projectType;

	private CircularlyAccessibleValueObject[] ss = null;
	private JPanel ivjUIDialogContentPane = null;
	private String selectedstr;
	private Integer testnum;
	private String[] strs;
	private String beforesample;
	private String reportLang;
	
	

	public String getReportLang() {
		return reportLang;
	}

	public void setReportLang(String reportLang) {
		this.reportLang = reportLang;
	}

	public String[] getStrs() {
		return strs;
	}

	public void setStrs(String[] strs) {
		this.strs = strs;
	}

	public Integer getTestnum() {
		return testnum;
	}

	public void setTestnum(Integer testnum) {
		this.testnum = testnum;
	}

	public String getSelectedstr() {
		return selectedstr;
	}

	public void setSelectedstr(String selectedstr) {
		this.selectedstr = selectedstr;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public RefValuePanel(String pk_commission_h) throws DAOException {
		this.reportLang = getReportLangs(pk_commission_h);
		initialize();
	}

	private String getReportLangs(String pk_commission_h) {
		IUAPQueryBS iUAPQueryBS = (IUAPQueryBS) NCLocator.getInstance().lookup(IUAPQueryBS.class.getName());
		String reportCode = null;
		try {

			// for (TaskHBodyVO taskHBodyVO:pklists) {
			
				List<Map<String, String>> custlist = (List<Map<String, String>>) iUAPQueryBS
						.executeQuery("select RP_REPORT_CODE from QC_COMMISSION_H left join NC_REPORT_LANG "
								+ "on NC_REPORT_LANG.pk_report_lang=qc_commission_h.REPORTLANG"
								+ " where pk_commission_h='"+pk_commission_h+"'"
								, new MapListProcessor());
				for (Map<String, String> map : custlist) {
					reportCode = map.get("rp_report_code");
				}
	}catch(Exception e){
		e.printStackTrace();
		}
		return reportCode;
	}

	private void initialize() {
		 this.setTitle("请选择参照");
		this.setLayout(new BorderLayout());
		this.setSize(new Dimension(410, 460));
		this.setContentPane(getUIDialogContentPane());

	}

	private Container getUIDialogContentPane() {
		if (ivjUIDialogContentPane == null) {
			try {
				ivjUIDialogContentPane = new javax.swing.JPanel();
				ivjUIDialogContentPane.setName("ivjUIDialogContentPane");
				ivjUIDialogContentPane.setLayout(new java.awt.BorderLayout());
				getUIDialogContentPane().add(getUIDialogContentPane1());
				getUIDialogContentPane().add(getBnPanel(), BorderLayout.SOUTH);
			} catch (Throwable ivjExc) {
				handleException(ivjExc);
			}
		}
		return ivjUIDialogContentPane;

	}

	private UIPanel getBnPanel() {
		if (this.bnPanel == null) {
			this.bnPanel = new UIPanel();
			this.bnPanel.setLayout(new FlowLayout());
			this.bnPanel.setPreferredSize(new Dimension(500, 50));
			this.bnPanel.add(getOkButton(), null);
			this.bnPanel.add(getCancelButton(), null);
		}
		return this.bnPanel;
	}

	private UIButton getOkButton() {
		if (this.okButton == null) {
			this.okButton = new UIButton();
			this.okButton.setBounds(new Rectangle(50, 5, 30, 20));
			this.okButton.setText(NCLangRes.getInstance().getStrByID("common",
					"UC001-0000044"));
			this.okButton.setPreferredSize(new Dimension(60, 22));
			this.okButton.addActionListener(this);
		}
		return this.okButton;
	}

	private UIButton getCancelButton() {
		if (this.cancelButton == null) {
			this.cancelButton = new UIButton();
			this.cancelButton.setBounds(new Rectangle(200, 5, 30, 20));
			this.cancelButton.setText(NCLangRes.getInstance().getStrByID(
					"common", "UC001-0000008"));
			this.cancelButton.setPreferredSize(new Dimension(60, 22));
			this.cancelButton.addActionListener(this);
		}
		return this.cancelButton;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(getOkButton())) {
			setResult(UIDialog.ID_OK);
			RefValueVO[] RefValueVOsAllocatVO = (RefValueVO[]) getBillListHeadPanel()
					.getBodyBillModel().getBodySelectedVOs(
							"nc.vo.qcco.task.RefValueVO");
			
			if (RefValueVOsAllocatVO != null
					&& RefValueVOsAllocatVO.length > 0) {
				if(RefValueVOsAllocatVO.length> 1){
					MessageDialog.showErrorDlg(this.getContentPane(), "错误", "请选择一条数据！");
					return;
				}
				for (RefValueVO taskBodyVO : RefValueVOsAllocatVO) {
					if (null!=reportLang && reportLang.equals("1")) {
						selectedstr = taskBodyVO.getEngname();
					}else if (null!=reportLang && reportLang.equals("2")) {
						selectedstr = taskBodyVO.getChinaname();
					}
				}
				try {
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			dispose();
		} else if (e.getSource().equals(getCancelButton())) {
			setResult(UIDialog.ID_CANCEL);
			dispose();
		}
	}

	private JPanel getUIDialogContentPane1() {
		if (ivjUIDialogContentPane1 == null) {
			ivjUIDialogContentPane1 = new javax.swing.JPanel();
			ivjUIDialogContentPane1.setName("ivjUIDialogContentPane1");
			ivjUIDialogContentPane1.setLayout(null);
			// ivjUIDialogContentPane1.setLayout(new BorderLayout());
			//getUIDialogContentPane1().add(getUIPanel1());
			getUIDialogContentPane1().add(getBillListHeadPanel());
		}
		return ivjUIDialogContentPane1;
	}

	
	
	

	private void handleException(Throwable exception) {
		Logger.error("--------- 未捕捉到的异常 ---------");
		MessageDialog.showErrorDlg(this, null, exception.getMessage());
		exception.printStackTrace();
	}



	/*
	 * 根据选择的收款单数据，对已经填写的数据进行校验。
	 */
	private String checkListData(String redate, String backdate) {

		return null;
	}

	private String checkCardData(String redate, String backdate) {
		String message = "";
		return message;
	}

	/*
	 * 获取模版
	 */
	private BillListPanel getBillListHeadPanel() {
		if (billListHeadPanel == null) {
			billListHeadPanel = new BillListPanel();
			billListHeadPanel.loadTemplet("1001ZZ1000000000480B");
			billListHeadPanel.setVisible(true);
			billListHeadPanel.setEnabled(true);
			billListHeadPanel.setBounds(10, 10, 385, 400);

			billListHeadPanel.setMultiSelect(true);
			List<RefValueVO> lists = getListDatas();
			
			
			billListHeadPanel.setBodyValueVO(lists
					.toArray(new RefValueVO[0]));
			
			
			billListHeadPanel.setAutoscrolls(true);
		}
		return billListHeadPanel;
	}

	@Override
	public boolean beforeEdit(BillEditEvent paramBillEditEvent) {
		// TODO Auto-generated method stub
		return false;
	}

	
	private List<RefValueVO>  getListDatas() {
		IUAPQueryBS iUAPQueryBS = (IUAPQueryBS) NCLocator.getInstance().lookup(
				IUAPQueryBS.class.getName());

		List<RefValueVO> lists = new ArrayList<>();

		try {
			List<Map<String, Object>> custlist = (List<Map<String, Object>>) iUAPQueryBS
					.executeQuery(
							"select distinct nc_list_code,c_en_value,c_cont_value from nc_list_entry where c_en_value is not null and c_cont_value is not null;",
							new MapListProcessor());

			if (null != custlist && custlist.size() > 0) {
				for (Map<String, Object> map : custlist) {
					RefValueVO refValueVO = new RefValueVO();
					refValueVO.setChinaname(map.get("c_cont_value")== null?null:map.get("c_cont_value").toString());
					refValueVO.setEngname(map.get("c_en_value")== null?null:map.get("c_en_value").toString());
					refValueVO.setCode(map.get("nc_list_code")== null?null:map.get("nc_list_code").toString());
					lists.add(refValueVO);
				}
				
			}
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lists;
	}

}
