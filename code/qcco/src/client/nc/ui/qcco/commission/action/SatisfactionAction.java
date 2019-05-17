package nc.ui.qcco.commission.action;

import java.awt.event.ActionEvent;

import nc.ui.qcco.commission.ace.view.ConfirmDialog;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.pub.SuperVO;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;

public class SatisfactionAction extends NCAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1L;

	public SatisfactionAction() {
		setBtnName("满意度评价");
		setCode("satisfaction");
	}

	protected AbstractAppModel model = null;

	public AbstractAppModel getModel() {
		return model;
	}

	public void setModel(AbstractAppModel model) {
		this.model = model;
		this.model.addAppEventListener(this);
	}

	@Override
	public void doAction(ActionEvent paramActionEvent) throws Exception {
		String value = (String) ConfirmDialog.showSelectDlg(this.getModel().getContext().getEntranceUI(), "满意度评价",
				"请选择评价内容", new String[] { "非常满意", "比较满意", "满意", "不满意" }, 4);

	}

	protected boolean isActionEnable() {
		AbstractBill aggVO = (AbstractBill) this.getModel().getSelectedData();
		if (aggVO == null) {
			return false;
		}
		SuperVO hvo = (SuperVO) aggVO.getParentVO();
		if (hvo == null) {
			return false;
		}
		return this.getModel().getUiState() == UIState.NOT_EDIT;
	}
}
