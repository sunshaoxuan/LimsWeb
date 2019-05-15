package nc.ui.qcco.commission.action;

import java.awt.event.ActionEvent;

import nc.ui.pubapp.uif2app.components.grand.CardGrandPanelComposite;
import nc.ui.qcco.commission.model.MainSubBillModel;
import nc.ui.uif2.actions.EditAction;

public class ChangeAction extends EditAction {
	/**
	 * serial no
	 */
	private static final long serialVersionUID = 6398003887713048604L;
	private CardGrandPanelComposite mainGrandPanel;

	public CardGrandPanelComposite getMainGrandPanel() {
		return mainGrandPanel;
	}

	public void setMainGrandPanel(CardGrandPanelComposite mainGrandPanel) {
		this.mainGrandPanel = mainGrandPanel;
	}

	public ChangeAction() {
		setBtnName("±ä¸ü");
		setCode("ChangeAction");
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		super.doAction(e);

		((MainSubBillModel) this.getModel()).setChangeStatus(true);
		((MainSubBillModel) this.getModel()).resetBillFormEnableState();
		((MainSubBillModel) this.getModel()).cacheOldValues();
	}

}
