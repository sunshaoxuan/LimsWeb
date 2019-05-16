package nc.ui.qcco.commission.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.qcco.commission.ace.view.ConfirmDialog;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.pub.SuperVO;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;

public class QuotationAction extends NCAction {

	/**
	 * serial no
	 */
	private static final long serialVersionUID = -1L;

	public QuotationAction() {
		setBtnName("���۵�Ԥ��");
		setCode("quotation");
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
		try {
			// ��ѯ
			IUAPQueryBS iUAPQueryBS = (IUAPQueryBS) NCLocator.getInstance().lookup(IUAPQueryBS.class.getName());
			String url = (String) iUAPQueryBS.executeQuery(
					"select vdef1 from report_path where nc_report_type = 'QUOTE'", new ColumnProcessor());
			if (null == url) {
				url = "404.html";
			}

			ConfirmDialog dlg = (ConfirmDialog) ConfirmDialog.showInputDlg(
					this.getModel().getContext().getEntranceUI(), ConfirmDialog.CONFIRM_REJECT_PREVIEW, "���۵�Ԥ��",
					"���������", "", 200, 0, ConfirmDialog.TEXT_STR, url);
			if (dlg.getResult() == ConfirmDialog.ID_CONFIRM) {

			} else if (dlg.getResult() == ConfirmDialog.ID_REJECT) {

			}

		} catch (Exception e) {
			Logger.error(e.getCause());
		}

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
