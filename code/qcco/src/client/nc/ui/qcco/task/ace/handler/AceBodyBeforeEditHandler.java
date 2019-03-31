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

import javax.swing.JComponent;
import javax.swing.KeyStroke;

import com.borland.jbcl.control.CheckboxPanel;

import nc.bs.framework.common.NCLocator;
import nc.hr.utils.ResHelper;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.MapListProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIButton;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.beans.ValueChangedEvent;
import nc.ui.pub.beans.ValueChangedListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBatchBillTable;
import nc.ui.qcco.commission.refmodel.SampleInfoRefModel;
import nc.ui.qcco.commission.refmodel.TestInitRefModel;
import nc.ui.qcco.commission.refmodel.UnitTypeRefModel;
import nc.ui.qcco.task.utils.StringOrderUtils;
import nc.ui.qcco.task.view.SampleAllocationPanel;
import nc.vo.pub.BusinessException;

public class AceBodyBeforeEditHandler implements
		IAppEventHandler<CardBodyBeforeEditEvent>,ValueChangedListener, ActionListener {
	private SampleAllocationPanel samplepanel;
	private UIPanel ivjUIPanel2 = null;
	private BillForm mainBillForm;//


	public BillForm getMainBillForm() {
		return mainBillForm;
	}
	public void setMainBillForm(BillForm mainBillForm) {
		this.mainBillForm = mainBillForm;
	}
	@Override
	public void handleAppEvent(CardBodyBeforeEditEvent e) {
		if ("sampleallocation".equals(e.getKey())) {
			try{
			String pk_commission_h = getMainBillForm().getBillCardPanel().getHeadItem("pk_commission_h").getValue();
			if(null == pk_commission_h){
				MessageDialog.showErrorDlg(e.getContext().getEntranceUI(), "错误", "委托单不能为空。"); 
				return;
			}
			
				
				SampleAllocationPanel samplepanel = new SampleAllocationPanel(pk_commission_h);
				samplepanel.setTitle("样品分配参照");
				if (samplepanel.showModal() == 1) {
					String strvalue = samplepanel.getSelectedstr();
					Integer testnum = samplepanel.getTestnum();
					BillCardPanel card = this.getMainBillForm().getBillCardPanel();
					//校验样品分配是否重复
					List<String> strlist = new ArrayList<String>();
					for (int i = 0; i < e.getBillCardPanel().getRowCount()-1; i++) {
						strlist.add((String) this.getMainBillForm().getBillCardPanel().getBodyValueAt(i, "sampleallocation"));
					}
					if (strlist != null && strlist.size()>0 && strvalue != null) {
						List<String> commList = validate(pk_commission_h,strlist,strvalue);
						if (commList.size() > 0) {
							MessageDialog.showErrorDlg(e.getContext().getEntranceUI(), "错误", "样品分配不能重复。"); 
							return;
						}	
					}
						
						card.setBodyValueAt(strvalue,
								e.getRow(), "sampleallocation");
						//card.getBodyValueAt(e.getRow()-1, "sampleallocation");
						card.setBodyValueAt(testnum,
								e.getRow(), "testnumber");
						samplepanel = null;
				}
						//e.setValue();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}else if ("testnumber".equals(e.getKey())) {
			return;
		} 
			
			
			
				
	}

	private List<String> validate(String pk_commission_h, List<String> strlist,
			Object value) {
		List<String[]> numlist = new ArrayList<String[]>();
		Map<String, Integer> maps = new HashMap<String,Integer>();
		IUAPQueryBS iUAPQueryBS = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName());   
		List<String> commlist = new ArrayList<String>();
		try {
			List<Map<String, Object>> custlist = (List<Map<String,Object>>) iUAPQueryBS. executeQuery("select "
					+ "samplegroup.nc_sample_name, commission.QUANTITY from QC_COMMISSION_B commission left join NC_SAMPLE_GROUP samplegroup "
					+ "on commission.PK_SAMPLEGROUP = samplegroup.pk_sample_group where "
					+ "commission.PK_COMMISSION_H='"+pk_commission_h+"' order by samplegroup.nc_sample_code",new MapListProcessor());
			List<Integer> listnum = new ArrayList<Integer>();
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
			if (listnum !=null && listnum.size() > 0) {
				for(String str : strlist){
					numlist.add(StringOrderUtils.outDisOrderArray(str, listnum));
				}
				String[] array = StringOrderUtils.outDisOrderArray(String.valueOf(value), listnum);
				
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
		   
		}catch(Exception e){
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
				btnCancel.setText("取消");
				btnCancel.addActionListener(this);
				btnCancel.registerKeyboardAction(this, KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_MASK),
						JComponent.WHEN_IN_FOCUSED_WINDOW);
			} catch (Throwable ivjExc) {
				ivjExc.getStackTrace();
			}
		}
		return btnCancel;
	}
	private UIButton btnOK = null;// 确定按钮
	private UIButton btnCancel = null;// 取消按钮
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
