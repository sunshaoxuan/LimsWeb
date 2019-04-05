package nc.ui.qcco.commission.ace.handler;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillCellEditor;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.qcco.commission.refmodel.AnalyseComponentRefModel;
import nc.ui.qcco.commission.refmodel.ProductContactRefModel;
import nc.ui.uif2.components.IAutoShowUpComponent;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.qcco.commission.AggCommissionHVO;
import nc.vo.qcco.commission.CommissionBVO;

public class GrandBodyBeforeEditHandler implements
		IAppEventHandler<CardBodyBeforeEditEvent> {
	
	
	
	public GrandBodyBeforeEditHandler(BillForm billform) {
		super();
		this.billform = billform;
	}





	private BillForm billform;

	

	public BillForm getBillform() {
		return billform;
	}



	public void setBillform(BillForm billform) {
		this.billform = billform;
	}
	
	



	@Override
	public void handleAppEvent(CardBodyBeforeEditEvent e) {
		e.setReturnValue(true);
		if("component".equals(e.getKey())){
			
			String pk_enterprisestandard = 
					(String)e.getBillCardPanel().getBodyValueAt(e.getRow(), "pk_enterprisestandard");
			
			UIRefPane pane = (UIRefPane) (e.getBillCardPanel().getBodyItem(
					"component").getComponent());
			AnalyseComponentRefModel refModel = (AnalyseComponentRefModel) (pane
					.getRefModel());
			refModel.setCacheEnabled(false);
			pane.setCacheEnabled(false);
			
			UFBoolean ifAuto = 
					(UFBoolean)e.getBillCardPanel().getBodyValueAt(e.getRow(), "isAutoGeneration");
			

			if(ifAuto!=null && ifAuto.booleanValue()){
				refModel.setPk_ncEnstardCode(pk_enterprisestandard);
			}else{
				refModel.setPk_ncEnstardCode(null);
			}
			
			
			refModel.reloadData();
			refModel.fireChange();
		}
	}
}
