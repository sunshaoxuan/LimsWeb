package nc.ui.qcco.commission.action;

import java.awt.event.ActionEvent;

import nc.ui.pubapp.uif2app.actions.CancelAction;
import nc.ui.pubapp.uif2app.components.grand.CardGrandPanelComposite;

public class CommissionCancelAction extends CancelAction {
	private CardGrandPanelComposite mainGrandPanel;
	
	

	public CardGrandPanelComposite getMainGrandPanel() {
		return mainGrandPanel;
	}



	public void setMainGrandPanel(CardGrandPanelComposite mainGrandPanel) {
		this.mainGrandPanel = mainGrandPanel;
	}



	@Override
	public void doAction(ActionEvent e) throws Exception {
		super.doAction(e);
		mainGrandPanel.showMeUp();
	}
	
	
	
}
