package nc.ui.qcco.commission.ace.handler;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.qcco.commission.refmodel.AnalyseComponentRefModel;
import nc.ui.qcco.commission.refmodel.ProductContactRefModel;
import nc.ui.uif2.components.IAutoShowUpComponent;
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
		if("component".equals(e.getKey())){
			e.setReturnValue(true);
			String pk_enterprisestandard = null;
			AggCommissionHVO aggvo = 
					(AggCommissionHVO)this.billform.getModel().getSelectedData();
			if(aggvo!=null && aggvo.getChildren(new CommissionBVO().getMetaData())!=null
					&& aggvo.getChildren(new CommissionBVO().getMetaData())[0]!=null){
				
				CommissionBVO bvo = (CommissionBVO)aggvo.getChildren(new CommissionBVO().getMetaData())[0];
				pk_enterprisestandard = bvo.getPk_enterprisestandard();
						
			}
			
			UIRefPane pane = (UIRefPane) (e.getBillCardPanel().getBodyItem(
					"component").getComponent());
			AnalyseComponentRefModel refModel = (AnalyseComponentRefModel) (pane
					.getRefModel());
			refModel.setCacheEnabled(false);
			pane.setCacheEnabled(false);
			refModel.setPk_ncEnstardCode(pk_enterprisestandard);
			
			refModel.reloadData();
			refModel.fireChange();
		}
	}
}
