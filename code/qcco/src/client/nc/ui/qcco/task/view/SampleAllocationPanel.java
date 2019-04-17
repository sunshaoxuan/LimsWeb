package nc.ui.qcco.task.view;

import java.awt.Component;
import java.awt.Container;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.LayoutManager;
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

import bsh.This;

import com.borland.jbcl.control.CheckboxPanel;
import com.hazelcast.spi.annotation.PrivateApi;
import com.ibm.db2.jcc.sqlj.e;
import com.ufida.report.free.plugin.fieldattribute.SmartCheckBoxList;
import com.ufida.report.free.plugin.fieldattribute.SmartListData;

import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.hr.utils.ResHelper;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.SQLParameter;
import nc.jdbc.framework.processor.MapListProcessor;
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
import nc.ui.pub.formula.ui.InputHandler.insert_break;
import nc.ui.qcco.commission.refmodel.SampleAllocationRefModel;
import nc.ui.qcco.commission.refmodel.UnitTypeRefModel;
import nc.ui.qcco.task.utils.StringOrderUtils;
import nc.vo.pub.BusinessException;
import nc.vo.trade.bdinfo.BaseDocVO;



public class SampleAllocationPanel extends UIDialog implements ActionListener, ValueChangedListener{
	  private SmartCheckBoxList list = null;
	  private Map<String, String> mapselect = new HashMap<String,String>();
	  private String selectedstr;
	  private Integer testnum;
	  public Integer getTestnum() {
		return testnum;
	}

	public void setTestnum(Integer testnum) {
		this.testnum = testnum;
	}
	List<Integer> listnum = new ArrayList<Integer>();
	  
	 
	
	public String getSelectedstr() {
		return selectedstr;
	}

	public void setSelectedstr(String selectedstr) {
		this.selectedstr = selectedstr;
	}
		// 样品分配
		private String cname = null;
		private String pk_commission_h;
		
		public String getPk_commission_h() {
			return pk_commission_h;
		}

		public void setPk_commission_h(String pk_commission_h) {
			this.pk_commission_h = pk_commission_h;
		}

		public SampleAllocationPanel(String pk_commission_h) {
			super();
			this.pk_commission_h = pk_commission_h;
			selectedstr = null;
			initialize(mapselect);
		}

		public SampleAllocationPanel(Container arg1, String arg2) {
			super(arg1, arg2);
		}

		public SampleAllocationPanel() {
			super();
		}

		public SampleAllocationPanel(Frame arg1, String arg2) {
			super(arg1, arg2);
		}

		public SampleAllocationPanel(Container arg1) {
			super(arg1);
			//initialize();
		}


		private void initialize(Map<String, String> mapselect) {
			try {
				setName("SampleAllocationPanel");
				setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
				setSize(600, 400);
				setContentPane(getUIDialogContentPane(mapselect));
				
			} catch (Throwable ivjExc) {
				handleException(ivjExc);
			}

		}

		

		
		private JPanel getUIDialogContentPane(Map<String, String> mapselect) {
			if (ivjUIDialogContentPane == null) {
				try {
					ivjUIDialogContentPane = new javax.swing.JPanel();
					ivjUIDialogContentPane.setName("ivjUIDialogContentPane");
					ivjUIDialogContentPane.setLayout(new java.awt.BorderLayout());
					getUIDialogContentPane(mapselect).add(getUIDialogContentPane1(mapselect), "Center");
					getUIDialogContentPane(mapselect).add(getUIPanel2(), "South");
				} catch (Throwable ivjExc) {
					handleException(ivjExc);
				}
			}
			return ivjUIDialogContentPane;
		}
		
		private JPanel getUIDialogContentPane1(Map<String, String> mapselect) {
			if (ivjUIDialogContentPane1 == null) {
				try {
					ivjUIDialogContentPane1 = new javax.swing.JPanel();
					ivjUIDialogContentPane1.setName("ivjUIDialogContentPane1");
					ivjUIDialogContentPane1.setLayout(null);
					getUIDialogContentPane1(mapselect).add(getUIPanel1());
					getUIDialogContentPane1(mapselect).add(getUIPanel4(mapselect));
					
				} catch (Throwable ivjExc) {
					handleException(ivjExc);
				}
			}
			return ivjUIDialogContentPane1;
		}

		
		
		
		
		
		
		
		private JPanel getUIPanel4(Map<String, String> mapselect) {
			if(null == checkbox){
				checkbox = new UIPanel();
				//查找list数据
				Vector<SmartListData> listData = new Vector<SmartListData>();
				List<Integer> indexlist = new ArrayList<Integer>();
				Map<String, Integer> maps = getListDatas();
				List<String> lists = getLastDatas(maps);
				if(null != lists && lists.size() > 0){
					for(String string :lists){
						SmartListData smartListData = new SmartListData(null);
						smartListData.setSmartName(string);
						smartListData.setEnable(true);
						listData.add(smartListData);
					}
				}
				this.list = new SmartCheckBoxList(listData);
				if (null != mapselect && mapselect.size() > 0) {
					for(SmartListData sdata : listData){
						for(String map : mapselect.keySet()){
							if(map.toString().equals(sdata.toString())){
								indexlist.add(listData.indexOf(sdata));
							}
						}
					}
					int[] d = new int[indexlist.size()];
					for(int i = 0;i<indexlist.size();i++){
						d[i] = indexlist.get(i);
					}
					// this.list.
					//this.list.setSelectedValue(this.list.get, arg1);
					this.list.setSelectedIndices(d);
				}
			    //this.list.updateUI();
			  //  this.list.setSelectionBackground(arg0);
			   // this.list.setSelectionModel(arg0);
			    //ListSelectionModel
			  //  this.list.setSelectedIndex(0);
			    //getUIPanel4(mapselect).add(new UIScrollPane());
			   // UIScrollPane.setViewportView(this.list);
				
			    checkbox.setBounds(100, 110, 300, 450);
			    checkbox.add(this.list);
			}
			if(this.list.getSelectedValuesList() != null && this.list.getSelectedValuesList().size() > 0){
				try {
					List<SmartListData> selectedValuesList = this.list.getSelectedValuesList();
					List<String> strlist = new ArrayList<String>();
					for (SmartListData st : selectedValuesList) {
						strlist.add(st.getSmartName());
					}
					if (null != listnum) {
						selectedstr = StringOrderUtils.outOrderString(strlist.toArray(new String[strlist.size()]),listnum);
						testnum = strlist.size();
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
			return checkbox;
		}
		
		
		private List<String> getLastDatas(Map<String, Integer> maps) {
			List<String> list = new ArrayList<String>();
			for(String key : maps.keySet()){
				Integer quantity = Integer.valueOf(maps.get(key));
				if(quantity > 0){
					for(int x = 1; x<= quantity; x++){
						list.add(key + String.valueOf(x));
					}
				}
			}
			return list;
		}

		private Map<String, Integer> getListDatas() {
			 IUAPQueryBS iUAPQueryBS = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName());   
					
					Map<String, Integer> maps = new HashMap<String,Integer>();
					
					try {
						List<Map<String, Object>> custlist = (List<Map<String,Object>>) iUAPQueryBS. executeQuery("select "
								+ "samplegroup.nc_sample_name, commission.QUANTITY from QC_COMMISSION_B commission left join NC_SAMPLE_GROUP samplegroup "
								+ "on commission.PK_SAMPLEGROUP = samplegroup.pk_sample_group where "
								+ "commission.PK_COMMISSION_H='"+pk_commission_h+"' order by samplegroup.nc_sample_code",new MapListProcessor());
					
					if (null != custlist && custlist.size() > 0 ) {
						for(Map<String,Object> map : custlist ){
							maps.put(map.get("nc_sample_name").toString(), Integer.parseInt(String.valueOf(map.get("quantity"))));
						}
						if (maps != null && maps.size() > 0 && listnum.size() <= 0) {
							if (maps.containsKey("A")) {
								listnum.add(maps.get("A"));
							}else {
								listnum.add(0);
							}
							if (maps.containsKey("B")) {
								listnum.add(maps.get("B"));
							}else {
								listnum.add(0);
							}
							if (maps.containsKey("C")) {
								listnum.add(maps.get("C"));
							}else {
								listnum.add(0);
							}
							if (maps.containsKey("D")) {
								listnum.add(maps.get("D"));
							}else {
								listnum.add(0);
							}
						}
					}
					} catch (BusinessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			return maps;
		}

		/**
		 * 样品分配
		 * @return
		 */
		private UIPanel getUIPanel1() {
			if (ivjUIPanel1 == null) {
				try {
					ivjUIPanel1 = new UIPanel();
					ivjUIPanel1.setName("ivjUIPanel1");
					ivjUIPanel1.setLayout(null);
					// 样品分配
					ivjUIPanel1.add(getSampleNameLabel());
					ivjUIPanel1.add(getSampleNameField());
					ivjUIPanel1.add(getBtnOKtop());
					ivjUIPanel1.setBounds(50, 10,580, 50);
				} catch (Throwable ivjExc) {
					handleException(ivjExc);
				}
			}
			return ivjUIPanel1;
		}

		
		/**
		 * 确定 取消按钮
		 * @return
		 */
		private nc.ui.pub.beans.UIPanel getUIPanel2() {
			if (ivjUIPanel2 == null) {
				try {
					ivjUIPanel2 = new nc.ui.pub.beans.UIPanel();
					ivjUIPanel2.setName("UIPanel2");
					ivjUIPanel2.setBackground(new java.awt.Color(204, 204, 204));
					getUIPanel2().add(getBtnOK(), getBtnOK().getName());
					getUIPanel2().add(getBtnCancel(), getBtnCancel().getName());
				} catch (Throwable ivjExc) {
					handleException(ivjExc);
				}
			}
			return ivjUIPanel2;
		}

		/**
		 * 样品分配名称
		 * @return
		 */
		private UILabel getSampleNameLabel() {
			if (sampleNameLabel == null) {
				try {
					sampleNameLabel = new UILabel();
					sampleNameLabel.setName("sampleNameLabel");
					sampleNameLabel.setText("样品分配");
					sampleNameLabel.setBounds(0, 10, 80, 30);
				} catch (Throwable ivjExc) {
					handleException(ivjExc);
				}
			}
			return sampleNameLabel;
		}
		
		
		

		/**
		 * 样品分配前输入框
		 * 
		 * @return
		 */
		private JTextField getSampleNameField() {
			if (this.sampleNameField == null) {
				try {
					sampleNameField = new UITextField();
					sampleNameField.setName("sampleNameField");
					sampleNameField.setBounds(100, 12, 300, 50);
					sampleNameField.setText(null);
				} catch (Throwable ivjExc) {
					handleException(ivjExc);
				}

			}
			return this.sampleNameField;
		}
		
		/**
		 * 确定操作
		 * 
		 * @throws Exception
		 */
		private void onButtonOKClicked() {
			this.sampleName = getSampleNameField().getText();
			//selectedstr = null;
			getUIPanel4(null);
			checkbox = null;
			this.closeOK();

		}

		/**
		 * 确定操作
		 * 
		 * @throws Exception
		 */
		private void onButtonOKtopClicked() {
			this.sampleName = getSampleNameField().getText();
			String[] strs = null;
			try {
				if (listnum != null ) {
					strs = StringOrderUtils.outDisOrderArray(sampleName,  listnum);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(strs != null && strs.length > 0){
				for(String str : strs){
					mapselect.put(str, null);
				}
				ivjUIDialogContentPane = null;
				ivjUIDialogContentPane1 = null;
				checkbox = null;
				//listnum.clear();
				//getUIPanel4(mapselect);
				getContentPane().hide();
				//getRootPane().getLayeredPane().remove(getRootPane().getContentPane());
				initialize(mapselect);
				//mapselect = null;
				//listnum = null;
				//getUIDialogContentPane1()
				
				
				
			}

		}
		
		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == this.getBtnOK()) {
				new Thread() {
					@Override
					public void run() {

						IProgressMonitor progressMonitor = NCProgresses.createDialogProgressMonitor(getParent());

						try {
							progressMonitor.beginTask("匯出中...", IProgressMonitor.UNKNOWN_REMAIN_TIME);
							progressMonitor.setProcessInfo("匯出中，請稍候.....");
							onButtonOKClicked();
						} finally {

							progressMonitor.done(); // 进度任务结束
						}
					}
				}.start();
			} else if(e.getSource() == this.getBtnOKtop()){
				onButtonOKtopClicked();
			}
			if (e.getSource() == this.getBtnCancel()) {
				this.closeCancel();
			}

		}

		public void valueChanged(ValueChangedEvent e) {
			
		}

		public boolean equals(Object obj) {
			return super.equals(obj);
		}

		protected void finalize() throws Throwable {
			super.finalize();
		}

		protected LayoutManager getLayoutManager() {
			return new GridLayout(2, 2);
		}

		/**
		 * 生成接收器的散列码。 此方法主要由散列表 支持，如 java.util 中提供的那些散列表。@return 接收器的整数散列码
		 */
		public int hashCode() {
			return super.hashCode();
		}
		private UIButton getBtnOKtop() {
			if (btnOKtop == null) {
				try {
					btnOKtop = new UIButton();
					btnOKtop.setName("btnOKtop");
					btnOKtop.setText("确定");
					btnOKtop.addActionListener(this);
					btnOKtop.setBounds(420, 12, 30, 30);
					btnOKtop.registerKeyboardAction(this, KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.ALT_MASK),
							JComponent.WHEN_IN_FOCUSED_WINDOW);
				} catch (Throwable ivjExc) {
					handleException(ivjExc);
				}
			}
			return btnOKtop;
		}
		private UIButton getBtnOK() {
			if (btnOK == null) {
				try {
					btnOK = new UIButton();
					btnOK.setName("btnOK");
					btnOK.setText("确定");
					btnOK.addActionListener(this);
					btnOK.registerKeyboardAction(this, KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.ALT_MASK),
							JComponent.WHEN_IN_FOCUSED_WINDOW);
				} catch (Throwable ivjExc) {
					handleException(ivjExc);
				}
			}
			return btnOK;
		}

		private UIButton getBtnCancel() {
			if (btnCancel == null) {
				try {
					btnCancel = new UIButton();
					btnCancel.setName("btnCancel");
					btnCancel.setText("取消");
					btnCancel.addActionListener(this);
					btnCancel.registerKeyboardAction(this, KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_MASK),
							JComponent.WHEN_IN_FOCUSED_WINDOW);
				} catch (Throwable ivjExc) {
					handleException(ivjExc);
				}
			}
			return btnCancel;
		}


		public String getSampleName() {
			return sampleName;
		}

		public void setSampleName(String sampleName) {
			this.sampleName = sampleName;
		}
		
		


		private void handleException(Throwable exception) {
			Logger.error("--------- 未捕捉到的异常 ---------");
			MessageDialog.showErrorDlg(this, null, exception.getMessage());
			exception.printStackTrace();
		}
		private UIButton btnOKtop = null;// 确定按钮
		
		private UIButton btnOK = null;// 确定按钮
		private UIButton btnCancel = null;// 取消按钮
		private JPanel ivjUIDialogContentPane = null;
		private JPanel ivjUIDialogContentPane1 = null;
		private UIPanel ivjUIPanel1 = null;
		private UIPanel ivjUIPanel2 = null;
		private UILabel sampleNameLabel = null;// 样品分配
		private UIButton button = null;// 确定按钮
		private JTextField sampleNameField = null;// 样品分配
		//private UIRefPane refpanel;
		private JPanel checkbox = null;
		private String sampleName = null;// 样品分配

}
