package nc.ui.qcco.commission.action;

import java.awt.event.ActionEvent;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.components.grand.CardGrandPanelComposite;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.qcco.commission.model.MainSubBillModel;
import nc.ui.uif2.actions.EditAction;
import nc.vo.qcco.commission.AggCommissionHVO;
import nc.vo.qcco.commission.CommissionHVO;

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
		setBtnName("变更");
		setCode("ChangeAction");
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		super.doAction(e);

		((MainSubBillModel) this.getModel()).setChangeStatus(true);
		((MainSubBillModel) this.getModel()).resetBillFormEnableState();
		((MainSubBillModel) this.getModel()).cacheOldValues();
		
		
		// 刷新产品分类
		AggCommissionHVO aggvo = (AggCommissionHVO) getModel()
				.getSelectedData();
		if (aggvo != null && aggvo.getParentVO() != null) {
			CommissionHVO hvo = aggvo.getParentVO();
			BillCardPanel mainBillCardPanel = ((BillForm) mainGrandPanel
					.getMainPanel()).getBillCardPanel();
			if (hvo.getPk_subcategory() != null) {
				((UIRefPane) mainBillCardPanel.getHeadItem("pk_subcategory")
						.getComponent()).setPK(hvo.getPk_subcategory());
			}
			if (hvo.getPk_lastcategory() != null) {
				((UIRefPane) mainBillCardPanel.getHeadItem("pk_lastcategory")
						.getComponent()).setPK(hvo.getPk_lastcategory());
			}
		}
	}

}
