package nc.ui.qcco.task.ace.handler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JComponent;
import javax.swing.KeyStroke;

import org.apache.commons.lang.StringUtils;

import com.borland.jbcl.control.CheckboxPanel;
import com.google.gdata.util.parser.Strlit;

import nc.bs.framework.common.NCLocator;
import nc.hr.utils.InSQLCreator;
import nc.hr.utils.ResHelper;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.MapListProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIButton;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.beans.ValueChangedEvent;
import nc.ui.pub.beans.ValueChangedListener;
import nc.ui.pub.beans.constenum.DefaultConstEnum;
import nc.ui.pub.beans.constenum.IConstEnum;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBatchBillTable;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.qcco.commission.refmodel.SampleInfoRefModel;
import nc.ui.qcco.commission.refmodel.TestInitRefModel;
import nc.ui.qcco.commission.refmodel.UnitTypeRefModel;
import nc.ui.qcco.task.utils.StringOrderUtils;
import nc.ui.qcco.task.view.SampleAllocationPanel;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.qcco.commission.CommissionRVO;

public class AceBodyBeforeEditHandler implements
		IAppEventHandler<CardBodyBeforeEditEvent>, ValueChangedListener,
		ActionListener {
	private SampleAllocationPanel samplepanel;
	private UIPanel ivjUIPanel2 = null;
	private BillForm mainBillForm;//
	private ShowUpableBillForm grandCard;// mainBillForm
	public BillForm getMainBillForm() {
		return mainBillForm;
	}

	public void setMainBillForm(BillForm mainBillForm) {
		this.mainBillForm = mainBillForm;
	}
	public ShowUpableBillForm getGrandCard() {
		return grandCard;
	}

	public void setGrandCard(ShowUpableBillForm grandCard) {
		this.grandCard = grandCard;
	}

	@Override
	public void handleAppEvent(CardBodyBeforeEditEvent e) {
		if ("sampleallocation".equals(e.getKey())) {
			try {
				String pk_commission_h = getMainBillForm().getBillCardPanel()
						.getHeadItem("pk_commission_h").getValue();
				if (null == pk_commission_h) {
					MessageDialog.showErrorDlg(e.getContext().getEntranceUI(),
							"错误", "任务单不能为空");
					return;
				}
				//getMainBillForm().getBillCardPanel().getBodyItem("sampleallocation").getValue();
				SampleAllocationPanel samplepanel = new SampleAllocationPanel(
						pk_commission_h,getMainBillForm().getBillCardPanel().getBodyItem("sampleallocation").getValue());
				samplepanel.setTitle("样品分配参照");
				if (samplepanel.showModal() == 1) {
					String strvalue = samplepanel.getSelectedstr();
					Integer testnum = samplepanel.getTestnum();
					BillCardPanel card = this.getMainBillForm()
							.getBillCardPanel();
					// 校验样品分配是否重复
					List<String> strlist = new ArrayList<String>();
					card.setBodyValueAt(null, e.getRow(),
							"sampleallocation");
					card.setBodyValueAt(null, e.getRow(), "samplequantity");
					for (int i = 0; i <= e.getBillCardPanel().getRowCount() - 1; i++) {
						strlist.add((String) this.getMainBillForm()
								.getBillCardPanel()
								.getBodyValueAt(i, "sampleallocation"));
					}
					if (strlist != null && strlist.size() > 0
							&& strvalue != null) {
						
						List<String> commList = validate(pk_commission_h,
								strlist, strvalue);
						/*if (commList.size() > 0) {
							MessageDialog.showErrorDlg(e.getContext()
									.getEntranceUI(), "错误", "样品分配不能重复。");
							return;
						}*/
					}

					card.setBodyValueAt(strvalue, e.getRow(),
							"sampleallocation");
					// card.getBodyValueAt(e.getRow()-1, "sampleallocation");
					card.setBodyValueAt(testnum, e.getRow(), "samplequantity");
					//给孙表试验后参数赋值
					List<String> Alist = new ArrayList<>();
					if (samplepanel.getSelectedstr() !=null) {
						if (samplepanel.getSelectedstr().contains("A")) {
							Alist.add("A");
						}
						if (samplepanel.getSelectedstr().contains("B")) {
							Alist.add("B");
						}
						if (samplepanel.getSelectedstr().contains("C")) {
							Alist.add("C");
						}
						if (samplepanel.getSelectedstr().contains("D")) {
							Alist.add("D");
						}
					samplepanel = null;
				}
				// e.setValue();
				
					//根据条件查询实验前参数
					generateGrandLines(e,Alist,pk_commission_h);
				}
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if ("testnumber".equals(e.getKey())) {
			return;
		}

	}

	private List<CommissionRVO> generateGrandLines(CardBodyBeforeEditEvent e,List<String> alist,
			String pk_commission_h) {
		List<CommissionRVO> list = new ArrayList<>();
		InSQLCreator insql = new InSQLCreator();
		try {
			String psndocsInSQL = insql.getInSQL(alist.toArray(new String[0]));
			IUAPQueryBS iUAPQueryBS = (IUAPQueryBS) NCLocator.getInstance().lookup(
				IUAPQueryBS.class.getName());
			List<Map<String, Object>> refList = (List<Map<String, Object>>) iUAPQueryBS
					.executeQuery(
							"select r.analysisname,r.pk_samplegroup,sample.nc_sample_name,r.pk_component,test.test_init_name,r.pk_valuetype,resulttype.nc_result_namecn,"
							+ " r.stdmaxvalue,r.stdminvalue,r.unitname,r.judgeflag,r.testflag,r.productstage from QC_COMMISSION_R r "
							+ " left join NC_SAMPLE_GROUP sample on r.PK_SAMPLEGROUP= sample.PK_SAMPLE_GROUP left join NC_RESULT_TYPE resulttype ON"
							+ " resulttype.PK_RESULT_TYPE= r.PK_VALUETYPE left join QC_COMMISSION_B b on b.pk_commission_b=r.pk_commission_b"
							+ " left join NC_TEST_INIT test on test.pk_test_init=r.PK_COMPONENT where "
							+ "b.pk_commission_h='"+pk_commission_h+"' and sample.nc_sample_name in("+psndocsInSQL+");",
							new MapListProcessor());
				
				if (refList != null && refList.size() > 0) {
					int rowu = this.getGrandCard().getBillCardPanel().getRowCount();
					if(rowu >= 0){
						int[] rows = new int[rowu];
						for (int i = 0; i < rowu; i++) {
							rows[i]=i;
						}
						this.getGrandCard().getBillCardPanel().getBodyPanel("pk_task_r").delLine(rows);
					}
					
					for (Map<String, Object> refRow : refList) {
						
						this.getGrandCard().getBillCardPanel().getBodyPanel("pk_task_r").addLine();
						int row = this.getGrandCard().getBillCardPanel().getRowCount() - 1;
						
						String resultCode = "";
						String resultName = "";
						String refCode = "";
						String refName = "";
						for (Entry<String, Object> refValue : refRow.entrySet()) {
							if (refValue.getKey().equals("analysisname")) {
								this.getGrandCard().getBillCardPanel().setBodyValueAt(refValue.getValue(), row, "analysisname");
							} else if (refValue.getKey().equals("pk_component")) {
								this.getGrandCard().getBillCardPanel().setBodyValueAt(refValue.getValue(), row, "pk_component");
							} else if (refValue.getKey().equals("test_init_name")) {
								this.getGrandCard().getBillCardPanel().setBodyValueAt(refValue.getValue(), row, "component");
							}  else if (refValue.getKey().equals("test_init_code")) {
								refCode = (String) refValue.getValue();
							} else if (refValue.getKey().equals("test_init_name")) {
								refName = (String) refValue.getValue();
							} else if (refValue.getKey().equals("pk_samplegroup")) {
								this.getGrandCard().getBillCardPanel().setBodyValueAt(refValue.getValue(), row, "pk_samplegroup");
							} else if (refValue.getKey().equals("nc_sample_name")) {
								this.getGrandCard().getBillCardPanel().setBodyValueAt(refValue.getValue(), row, "samplegroup");
							} else if (refValue.getKey().equals("pk_valuetype")) {
								this.getGrandCard().getBillCardPanel().setBodyValueAt(refValue.getValue(), row, "pk_valuetype");
							} else if (refValue.getKey().equals("nc_result_namecn")) {
								this.getGrandCard().getBillCardPanel().setBodyValueAt(refValue.getValue(), row, "valuetype");
							} else if (refValue.getKey().equals("nc_result_code")) {
								resultCode = (String) refValue.getValue();
							} else if (refValue.getKey().equals("nc_result_namecn")) {
								resultName = (String) refValue.getValue();
							} else if (refValue.getKey().equals("stdmaxvalue")) {
								this.getGrandCard().getBillCardPanel().setBodyValueAt(refValue.getValue(), row, "stdmaxvalue");
							} else if (refValue.getKey().equals("stdminvalue")) {
								this.getGrandCard().getBillCardPanel().setBodyValueAt(refValue.getValue(), row, "stdminvalue");
							} else if (refValue.getKey().equals("productstage")) {
								this.getGrandCard().getBillCardPanel().setBodyValueAt(refValue.getValue(), row, "pk_testtemperature");
							} else if (refValue.getKey().equals("unitname")) {
								this.getGrandCard().getBillCardPanel().setBodyValueAt(refValue.getValue(), row, "pk_unit");
							} else if (refValue.getKey().equals("judgeflag")) {
								this.getGrandCard().getBillCardPanel().setBodyValueAt(refValue.getValue(), row, "judgeflag");
							} else if (refValue.getKey().equals("testflag")) {
								this.getGrandCard().getBillCardPanel().setBodyValueAt(refValue.getValue(), row, "testflag");
							}
						}
						if (!StringUtils.isEmpty(resultCode) && !StringUtils.isEmpty(resultName)) {
							IConstEnum aValue = new DefaultConstEnum(resultName, resultName);
							this.getGrandCard().getBillCardPanel().setBodyValueAt(aValue.getValue(), row, "valuetype");
						}

						if (!StringUtils.isEmpty(refCode) && !StringUtils.isEmpty(refName)) {
							IConstEnum aValue = new DefaultConstEnum(refName, refName);
							this.getGrandCard().getBillCardPanel().setBodyValueAt(aValue.getValue(), row, "component");
						}
					}
				}
				
		} catch (BusinessException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		return list;
	}

	private List<String> validate(String pk_commission_h, List<String> strlist,
			Object value) {
		List<String[]> numlist = new ArrayList<String[]>();
		Map<String, Integer> maps = new HashMap<String, Integer>();
		IUAPQueryBS iUAPQueryBS = (IUAPQueryBS) NCLocator.getInstance().lookup(
				IUAPQueryBS.class.getName());
		List<String> commlist = new ArrayList<String>();
		try {
			List<Map<String, Object>> custlist = (List<Map<String, Object>>) iUAPQueryBS
					.executeQuery(
							"select "
									+ "samplegroup.nc_sample_name, commission.QUANTITY from QC_COMMISSION_B commission left join NC_SAMPLE_GROUP samplegroup "
									+ "on commission.PK_SAMPLEGROUP = samplegroup.pk_sample_group where "
									+ "commission.PK_COMMISSION_H='"
									+ pk_commission_h
									+ "' order by samplegroup.nc_sample_code",
							new MapListProcessor());
			List<Integer> listnum = new ArrayList<Integer>();
			if (null != custlist && custlist.size() > 0) {
				for (Map<String, Object> map : custlist) {
					maps.put(map.get("nc_sample_name").toString(), Integer
							.parseInt(String.valueOf(map.get("quantity"))));
				}
				if (maps != null && maps.size() > 0 && listnum.size() <= 0) {
					if (maps.containsKey("A")) {
						listnum.add(maps.get("A"));
					} else {
						listnum.add(0);
					}
					if (maps.containsKey("B")) {
						listnum.add(maps.get("B"));
					} else {
						listnum.add(0);
					}
					if (maps.containsKey("C")) {
						listnum.add(maps.get("C"));
					} else {
						listnum.add(0);
					}
					if (maps.containsKey("D")) {
						listnum.add(maps.get("D"));
					} else {
						listnum.add(0);
					}
				}
			}
			if (listnum != null && listnum.size() > 0) {
				for (String str : strlist) {
					numlist.add(StringOrderUtils.outDisOrderArray(str, listnum));
				}
				String[] array = StringOrderUtils.outDisOrderArray(
						String.valueOf(value), listnum);

				for (String[] string : numlist) {
					for (String str : string) {
						for (String strs : array) {
							if (strs.equals(str)) {
								commlist.add(strs);
							}
						}
					}
				}

			}

		} catch (Exception e) {
			e.getStackTrace();
		}
		return commlist;
	}

	@Override
	public void valueChanged(ValueChangedEvent arg0) {
		// TODO Auto-generated method stub

	}

	private UIButton getBtnCancel() {
		if (btnCancel == null) {
			try {
				btnCancel = new UIButton();
				btnCancel.setName("btnCancel");
				btnCancel.setText("?秏");
				btnCancel.addActionListener(this);
				btnCancel.registerKeyboardAction(this, KeyStroke.getKeyStroke(
						KeyEvent.VK_C, InputEvent.ALT_MASK),
						JComponent.WHEN_IN_FOCUSED_WINDOW);
			} catch (Throwable ivjExc) {
				ivjExc.getStackTrace();
			}
		}
		return btnCancel;
	}

	private UIButton btnOK = null;// ?隅偌聽
	private UIButton btnCancel = null;// ?秏偌聽

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
}