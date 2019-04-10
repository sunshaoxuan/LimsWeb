package nc.ui.qcco.commission.action;

import java.util.List;
import java.util.Vector;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.beans.constenum.DefaultConstEnum;
import nc.ui.pub.beans.constenum.IConstEnum;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.actions.BodyAddLineAction;
import nc.ui.pubapp.uif2app.components.grand.CardGrandPanelComposite;
import nc.ui.pubapp.uif2app.components.grand.MainGrandRelationShip;
import nc.ui.pubapp.uif2app.components.grand.util.RowChangeBean;
import nc.ui.pubapp.uif2app.view.BillForm;

public class CommissionBodyAddLineAction extends BodyAddLineAction {
	
	private CardGrandPanelComposite mainGrandPanel;
	
	

	public CardGrandPanelComposite getMainGrandPanel() {
		return mainGrandPanel;
	}



	public void setMainGrandPanel(CardGrandPanelComposite mainGrandPanel) {
		this.mainGrandPanel = mainGrandPanel;
	}



	@Override
	protected void afterLineInsert(int index) {
		super.afterLineInsert(index);
		setGroup(index);
		/*BillCardPanel mainBillCardPanel = ((BillForm) mainGrandPanel.getMainPanel()).getBillCardPanel();
		String currentbodyTabCode = mainBillCardPanel.getCurrentBodyTableCode();
		MainGrandRelationShip relationShip = mainGrandPanel.getMaingrandrelationship();
		BillForm grandBillForm = (BillForm) relationShip.getBodyTabTOGrandCardComposite().get(currentbodyTabCode);
		BillCardPanel grandBillCardPanel = grandBillForm.getBillCardPanel();
		String grandTabCode = grandBillCardPanel.getCurrentBodyTableCode();
		RowChangeBean rowChangeBean = setChangeRowInfo(mainGrandPanel, lastrow, currentRow, grandBillForm);
		List<Object> grandVOList = mainGrandPanel.getMainGrandAssist().getGrandCardDataByMainRow(rowChangeBean,
				relationShip, null, null);
		
		grandBillCardPanel.getBillModel(grandTabCode).clearBodyData();*/
	}



	private void setGroup(int index) {
		if (index < 4 && index >= 0) {
			UIRefPane refpane = ((UIRefPane) getCardPanel().getBodyItem("samplegroup").getComponent());
			Vector matchedData = refpane.getRefModel().matchBlurData(String.valueOf(index + 1));
			IConstEnum val = new DefaultConstEnum(((Vector) matchedData.get(0)).get(2),
					(String) ((Vector) matchedData.get(0)).get(1));
			getCardPanel().setBodyValueAt(val, index, "samplegroup");
			getCardPanel().setBodyValueAt(val, index, "pk_samplegroup");
		}

	}
}
