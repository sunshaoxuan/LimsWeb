package nc.ui.qcco.commission.ace.handler;

import nc.bs.pubapp.utils.UserDefineRefUtils;
import nc.ui.bd.ref.AbstractRefModel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.qcco.commission.refmodel.EntTypeRefModel;
import nc.ui.qcco.commission.refmodel.ProductContactRefModel;
import nc.ui.qcco.commission.refmodel.SampleInfoRefModel;
import nc.ui.qcco.commission.refmodel.TestInitRefModel;
import nc.vo.qcco.commission.CommissionBVO;

import org.apache.commons.lang.StringUtils;

public class AceBodyBeforeEditHandler implements IAppEventHandler<CardBodyBeforeEditEvent> {

	@Override
	public void handleAppEvent(CardBodyBeforeEditEvent e) {
		if ("productserial".equals(e.getKey())) {
			BillItem bitem = (BillItem) e.getSource();

			String code = ((UIRefPane) ((BillItem) e.getBillCardPanel().getHeadItem("pk_maincategory")).getComponent())
					.getRefCode();
			((SampleInfoRefModel) ((UIRefPane) bitem.getComponent()).getRefModel()).setFirstClassCode(code);
			code = ((UIRefPane) ((BillItem) e.getBillCardPanel().getHeadItem("pk_subcategory")).getComponent())
					.getRefCode();
			((SampleInfoRefModel) ((UIRefPane) bitem.getComponent()).getRefModel()).setSecondClassCode(code);
			code = ((UIRefPane) ((BillItem) e.getBillCardPanel().getHeadItem("pk_lastcategory")).getComponent())
					.getRefCode();
			((SampleInfoRefModel) ((UIRefPane) bitem.getComponent()).getRefModel()).setThirdClassCode(code);
		} else if ("pk_analysisref".equals(e.getKey())) {
			String entStandard = (String) e.getBillCardPanel().getBodyValueAt(e.getRow(), "enterprisestandard");
			String productSpec = (String) e.getBillCardPanel().getBodyValueAt(e.getRow(), "productspec");
			String productGrade = (String) e.getBillCardPanel().getBodyValueAt(e.getRow(), "structuretype");
			String productStage = (String) e.getBillCardPanel().getBodyValueAt(e.getRow(), "productstage");

			BillItem bitem = (BillItem) e.getSource();
			((TestInitRefModel) ((UIRefPane) bitem.getComponent()).getRefModel()).setEnterpriseStandard(entStandard);
			((TestInitRefModel) ((UIRefPane) bitem.getComponent()).getRefModel()).setProductSpec(productSpec);
			((TestInitRefModel) ((UIRefPane) bitem.getComponent()).getRefModel()).setProductGrade(productGrade);
			((TestInitRefModel) ((UIRefPane) bitem.getComponent()).getRefModel()).setProductStage(productStage);
		} else if ("enterprisestandard".equals(e.getKey()) || "productspec".equals(e.getKey())
				|| "structuretype".equals(e.getKey()) || "productstage".equals(e.getKey())) {
			String entStandard = (String) e.getBillCardPanel().getBodyValueAt(e.getRow(), "pk_" + e.getKey());
			e.getBillCardPanel().setBodyValueAt(entStandard, e.getRow(), e.getKey());
		}else if ("contacttype".equals(e.getKey())) {
			String pk_productserial = (String)e.getBillCardPanel().getBodyValueAt(e.getRow(), "pk_productserial");
			CommissionBVO bodyVO = new CommissionBVO();
			UIRefPane pane = 
					(UIRefPane)(e.getBillCardPanel().getBodyItem("contacttype").getComponent());
			ProductContactRefModel refModel = (ProductContactRefModel)(pane.getRefModel());
			refModel.setCacheEnabled(false);
			pane.setCacheEnabled(false);
			refModel.setPk_productserial(pk_productserial);
			refModel.reloadData();
			refModel.fireChange();
			
			
			//refModel.reloadData1();
			//refModel.reset();
			
			//pane.repaint();
			//pane.updateUI();
			//pane.setCacheEnabled(false);
			
			/*bodyVO.setAttributeValue("contacttype", pk_productserial);
			UserDefineRefUtils.refreshItemRefValue(bodyVO, e.getBillCardPanel().getBodyPanel().getTable(),
					e.getRow(), e.getBillCardPanel().getBodyItem("contacttype"), true);*/
		}

		if ("enterprisestandard".equals(e.getKey())) {
			BillItem bitem = (BillItem) e.getSource();
			if (StringUtils.isEmpty(((UIRefPane) bitem.getComponent()).getRefPK())) {
				String productserial = (String) e.getBillCardPanel().getBodyValueAt(e.getRow(), "pk_productserial");
				((EntTypeRefModel) ((UIRefPane) bitem.getComponent()).getRefModel()).setPk_basprod_type(productserial);
			}
		}

		e.setReturnValue(true);
	}
}
