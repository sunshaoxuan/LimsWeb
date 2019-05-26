package nc.ui.qcco.task.ace.handler;

import java.util.Vector;

import nc.bs.dao.DAOException;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.qcco.commission.refmodel.ValueTypeRefModel;
import nc.ui.qcco.task.view.RefValuePanel;

import org.apache.commons.lang.StringUtils;

public class GrandBodyBeforeEditHandler implements IAppEventHandler<CardBodyBeforeEditEvent> {

	private BillForm mainBillForm;//

	public BillForm getMainBillForm() {
		return mainBillForm;
	}

	public void setMainBillForm(BillForm mainBillForm) {
		this.mainBillForm = mainBillForm;
	}

	@Override
	public void handleAppEvent(CardBodyBeforeEditEvent e) {
		String pk_commission_h = getMainBillForm().getBillCardPanel().getHeadItem("pk_commission_h").getValue();
		if ("textvalue".equals(e.getKey())) {
			Integer valueways = e.getBillCardPanel().getBodyValueAt(e.getRow(), "valueways") == null ? null
					: (Integer) e.getBillCardPanel().getBodyValueAt(e.getRow(), "valueways");
			String pk_result_type = (String) e.getBillCardPanel().getBodyValueAt(e.getRow(), "pk_valuetype");
			String typeCode = "";
			if (!StringUtils.isEmpty(pk_result_type)) {
				Vector refValue = new ValueTypeRefModel().matchPkData(pk_result_type);
				if (refValue != null) {
					typeCode = (String) ((Vector) refValue.get(0)).get(0);
				}
			}
			if (null == valueways) {
				MessageDialog.showErrorDlg(e.getContext().getEntranceUI(), "错误", "取值方式不能为空。");
				e.setReturnValue(true);
				return;

			} else if (valueways == 1) {
				e.setReturnValue(true);
				return;
			} else if (valueways == 2) {
				if (!StringUtils.isEmpty(typeCode) && typeCode.trim().equals("4")) {
					e.setReturnValue(true);
					return;
				} else {
					e.setReturnValue(false);
					return;
				}
			} else {
				if (!StringUtils.isEmpty(typeCode) && typeCode.trim().equals("2")) {
					e.setReturnValue(true);
					return;
				} else {
					e.setReturnValue(false);
					return;
				}
			}

		}
		if ("pk_refvalue".equals(e.getKey())) {

			Integer valueways = e.getBillCardPanel().getBodyValueAt(e.getRow(), "valueways") == null ? null
					: (Integer) e.getBillCardPanel().getBodyValueAt(e.getRow(), "valueways");
			if (null == valueways) {
				MessageDialog.showErrorDlg(e.getContext().getEntranceUI(), "错误", "取值方式不能为空。");
				e.setReturnValue(false);
				return;
			} else if (valueways == 1) {
				e.setReturnValue(false);
				return;
			} else if (valueways == 2) {
				e.setReturnValue(true);
				String pk_list_table = e.getBillCardPanel().getBodyValueAt(e.getRow(), "pk_list_table") == null ? null
						: (String) e.getBillCardPanel().getBodyValueAt(e.getRow(), "pk_list_table");
				try {
					RefValuePanel refValuePanel = new RefValuePanel(pk_commission_h, pk_list_table);
					if (refValuePanel.showModal() == 1) {
						String strvalue = refValuePanel.getSelectedstr();
						e.getBillCardPanel().setBodyValueAt(strvalue, e.getRow(), "pk_refvalue", "pk_task_s");
						e.getBillCardPanel().setBodyValueAt("已修改", e.getRow(), "conditionstatus", "pk_task_s");

					}
				} catch (DAOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				return;
			} else {
				e.setReturnValue(false);
				return;
			}

		}
		e.setReturnValue(true);
	}
}
