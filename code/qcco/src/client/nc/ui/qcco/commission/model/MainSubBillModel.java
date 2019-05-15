package nc.ui.qcco.commission.model;

import java.util.HashMap;
import java.util.Map;

import nc.bs.pubapp.utils.UserDefineRefUtils;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.qcco.commission.ace.view.CommissionGrandBillForm;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.qcco.commission.AggCommissionHVO;
import nc.vo.qcco.commission.CommissionBVO;
import uap.iweb.log.Logger;

public class MainSubBillModel extends BillManageModel {
	private nc.ui.pubapp.uif2app.components.grand.ListGrandPanelComposite billListView;
	private nc.ui.pubapp.uif2app.components.grand.CardGrandPanelComposite billFormView;
	private boolean isChangeStatus = false;
	private Map<String, String> oldValue = new HashMap<String, String>();

	public void initModel(Object data) {
		super.initModel(data);

		// 刷新列表页面自定义参照显示值
		// 只刷新主表和子表，孙表在点击子表后刷新
		AggCommissionHVO[] aggvos = (AggCommissionHVO[]) data;
		if (aggvos != null && aggvos.length > 0) {
			ShowUpableBillListView view = ((ShowUpableBillListView) this.getBillListView().getMainPanel());
			for (AggCommissionHVO aggvo : aggvos) {
				for (int row = 0; row < view.getBillListPanel().getHeadBillModel().getRowCount(); row++) {
					ISuperVO headVO = aggvo.getParentVO();
					if (headVO.getPrimaryKey().equals(
							view.getBillListPanel().getHeadBillModel().getValueAt(row, "pk_commission_h"))) {
						UserDefineRefUtils.refreshBillListHeadDefRefs(aggvo, view, row);
					}
				}
			}

			try {
				UserDefineRefUtils.refreshBillListBodyDefRefs(aggvos[0], view, "pk_commission_b", CommissionBVO.class);
			} catch (BusinessException e) {
				Logger.error(e.getMessage());
			}
		}
		//
	}

	public void cacheOldValues() {
		// testaim
		getOldValue().put(
				"testaim",
				(String) ((ShowUpableBillForm) getBillFormView().getMainPanel()).getBillCardPanel()
						.getHeadItem("testaim").getValueObject());
		// progressneed
		getOldValue().put(
				"progressneed",
				(String) ((ShowUpableBillForm) getBillFormView().getMainPanel()).getBillCardPanel()
						.getHeadItem("progressneed").getValueObject());
		// sampledealtype
		getOldValue().put(
				"sampledealtype",
				(String) ((ShowUpableBillForm) getBillFormView().getMainPanel()).getBillCardPanel()
						.getHeadItem("sampledealtype").getValueObject());
		// productproperty
		getOldValue().put(
				"productproperty",
				(String) ((ShowUpableBillForm) getBillFormView().getMainPanel()).getBillCardPanel()
						.getHeadItem("productproperty").getValueObject());
		// customername
		getOldValue().put(
				"customername",
				(String) ((ShowUpableBillForm) getBillFormView().getMainPanel()).getBillCardPanel()
						.getHeadItem("customername").getValueObject());
		// customertype
		getOldValue().put(
				"customertype",
				(String) ((ShowUpableBillForm) getBillFormView().getMainPanel()).getBillCardPanel()
						.getHeadItem("customertype").getValueObject());
		// testrequirement
		getOldValue().put(
				"testrequirement",
				(String) ((ShowUpableBillForm) getBillFormView().getMainPanel()).getBillCardPanel()
						.getHeadItem("testrequirement").getValueObject());
		// checkingproperty
		getOldValue().put(
				"checkingproperty",
				(String) ((ShowUpableBillForm) getBillFormView().getMainPanel()).getBillCardPanel()
						.getHeadItem("checkingproperty").getValueObject());
		// productline
		getOldValue().put(
				"productline",
				(String) ((ShowUpableBillForm) getBillFormView().getMainPanel()).getBillCardPanel()
						.getHeadItem("productline").getValueObject());
		// batchnumber
		getOldValue().put(
				"batchnumber",
				(String) ((ShowUpableBillForm) getBillFormView().getMainPanel()).getBillCardPanel()
						.getHeadItem("batchnumber").getValueObject());
		// productdate
		getOldValue().put(
				"productdate",
				(String) ((ShowUpableBillForm) getBillFormView().getMainPanel()).getBillCardPanel()
						.getHeadItem("productdate").getValueObject());
		// batchserial
		getOldValue().put(
				"batchserial",
				(String) ((ShowUpableBillForm) getBillFormView().getMainPanel()).getBillCardPanel()
						.getHeadItem("batchserial").getValueObject());
		// identificationtype
		getOldValue().put(
				"identificationtype",
				(String) ((ShowUpableBillForm) getBillFormView().getMainPanel()).getBillCardPanel()
						.getHeadItem("identificationtype").getValueObject());
		// certificationtype
		getOldValue().put(
				"certificationtype",
				(String) ((ShowUpableBillForm) getBillFormView().getMainPanel()).getBillCardPanel()
						.getHeadItem("certificationtype").getValueObject());
		// itemnumber
		getOldValue().put(
				"itemnumber",
				(String) ((ShowUpableBillForm) getBillFormView().getMainPanel()).getBillCardPanel()
						.getHeadItem("itemnumber").getValueObject());
	}

	public void resetBillFormEnableState() {
		// 表头不可变更项目
		((ShowUpableBillForm) getBillFormView().getMainPanel()).getBillCardPanel().getHeadItem("pk_commissiontype")
				.setEnabled(!isChangeStatus());
		((ShowUpableBillForm) getBillFormView().getMainPanel()).getBillCardPanel().getHeadItem("codeprefix")
				.setEnabled(!isChangeStatus());
		((ShowUpableBillForm) getBillFormView().getMainPanel()).getBillCardPanel().getHeadItem("billno")
				.setEnabled(!isChangeStatus());
		((ShowUpableBillForm) getBillFormView().getMainPanel()).getBillCardPanel().getHeadItem("billname")
				.setEnabled(!isChangeStatus());
		((ShowUpableBillForm) getBillFormView().getMainPanel()).getBillCardPanel().getHeadItem("pk_owner")
				.setEnabled(!isChangeStatus());
		((ShowUpableBillForm) getBillFormView().getMainPanel()).getBillCardPanel().getHeadItem("pk_dept")
				.setEnabled(!isChangeStatus());
		((ShowUpableBillForm) getBillFormView().getMainPanel()).getBillCardPanel().getHeadItem("pk_payer")
				.setEnabled(!isChangeStatus());
		((ShowUpableBillForm) getBillFormView().getMainPanel()).getBillCardPanel().getHeadItem("contract")
				.setEnabled(!isChangeStatus());
		((ShowUpableBillForm) getBillFormView().getMainPanel()).getBillCardPanel().getHeadItem("cuserid")
				.setEnabled(!isChangeStatus());
		((ShowUpableBillForm) getBillFormView().getMainPanel()).getBillCardPanel().getHeadItem("email")
				.setEnabled(!isChangeStatus());
		((ShowUpableBillForm) getBillFormView().getMainPanel()).getBillCardPanel().getHeadItem("teleno")
				.setEnabled(!isChangeStatus());
		((ShowUpableBillForm) getBillFormView().getMainPanel()).getBillCardPanel().getHeadItem("pk_maincategory")
				.setEnabled(!isChangeStatus());
		((ShowUpableBillForm) getBillFormView().getMainPanel()).getBillCardPanel().getHeadItem("pk_subcategory")
				.setEnabled(!isChangeStatus());
		((ShowUpableBillForm) getBillFormView().getMainPanel()).getBillCardPanel().getHeadItem("pk_lastcategory")
				.setEnabled(!isChangeStatus());
		((ShowUpableBillForm) getBillFormView().getMainPanel()).getBillCardPanel().getHeadItem("reportformat")
				.setEnabled(!isChangeStatus());
		((ShowUpableBillForm) getBillFormView().getMainPanel()).getBillCardPanel().getHeadItem("reportlang")
				.setEnabled(!isChangeStatus());

		// 表体不可变更
		((ShowUpableBillForm) getBillFormView().getMainPanel()).getBillCardPanel().getBodyItem("productserial")
				.setEnabled(!isChangeStatus());
		((ShowUpableBillForm) getBillFormView().getMainPanel()).getBillCardPanel().getBodyItem("enterprisestandard")
				.setEnabled(!isChangeStatus());
		((ShowUpableBillForm) getBillFormView().getMainPanel()).getBillCardPanel().getBodyItem("typeno")
				.setEnabled(!isChangeStatus());
		((ShowUpableBillForm) getBillFormView().getMainPanel()).getBillCardPanel().getBodyItem("productspec")
				.setEnabled(!isChangeStatus());
		((ShowUpableBillForm) getBillFormView().getMainPanel()).getBillCardPanel().getBodyItem("structuretype")
				.setEnabled(!isChangeStatus());
		((ShowUpableBillForm) getBillFormView().getMainPanel()).getBillCardPanel().getBodyItem("ref_contacttype")
				.setEnabled(!isChangeStatus());
		((ShowUpableBillForm) getBillFormView().getMainPanel()).getBillCardPanel().getBodyItem("manufacturer")
				.setEnabled(!isChangeStatus());
		((ShowUpableBillForm) getBillFormView().getMainPanel()).getBillCardPanel().getBodyItem("contactbrand")
				.setEnabled(!isChangeStatus());
		((ShowUpableBillForm) getBillFormView().getMainPanel()).getBillCardPanel().getBodyItem("contactmodel")
				.setEnabled(!isChangeStatus());
		((ShowUpableBillForm) getBillFormView().getMainPanel()).getBillCardPanel().getBodyItem("productstage")
				.setEnabled(!isChangeStatus());
		((ShowUpableBillForm) getBillFormView().getMainPanel()).getBillCardPanel().getBodyItem("samplegroup")
				.setEnabled(!isChangeStatus());
		((ShowUpableBillForm) getBillFormView().getMainPanel()).getBillCardPanel().getBodyItem("quantity")
				.setEnabled(!isChangeStatus());
		((ShowUpableBillForm) getBillFormView().getMainPanel()).getBillCardPanel().getBodyItem("analysisref")
				.setEnabled(!isChangeStatus());
		((ShowUpableBillForm) getBillFormView().getMainPanel()).getBillCardPanel().getBodyItem("otherinfo")
				.setEnabled(!isChangeStatus());

		// 孙表不可变更
		((CommissionGrandBillForm) getBillFormView().getMaingrandrelationship().getBodyTabTOGrandCardComposite()
				.get("pk_commission_b")).getBillCardPanel().getBodyItem("analysisname").setEnabled(!isChangeStatus());
		((CommissionGrandBillForm) getBillFormView().getMaingrandrelationship().getBodyTabTOGrandCardComposite()
				.get("pk_commission_b")).getBillCardPanel().getBodyItem("pk_component").setEnabled(!isChangeStatus());
		((CommissionGrandBillForm) getBillFormView().getMaingrandrelationship().getBodyTabTOGrandCardComposite()
				.get("pk_commission_b")).getBillCardPanel().getBodyItem("pk_valuetype").setEnabled(!isChangeStatus());
		((CommissionGrandBillForm) getBillFormView().getMaingrandrelationship().getBodyTabTOGrandCardComposite()
				.get("pk_commission_b")).getBillCardPanel().getBodyItem("unitname").setEnabled(!isChangeStatus());
		((CommissionGrandBillForm) getBillFormView().getMaingrandrelationship().getBodyTabTOGrandCardComposite()
				.get("pk_commission_b")).getBillCardPanel().getBodyItem("productstage").setEnabled(!isChangeStatus());
		((CommissionGrandBillForm) getBillFormView().getMaingrandrelationship().getBodyTabTOGrandCardComposite()
				.get("pk_commission_b")).getBillCardPanel().getBodyItem("pk_samplegroup").setEnabled(!isChangeStatus());
		((CommissionGrandBillForm) getBillFormView().getMaingrandrelationship().getBodyTabTOGrandCardComposite()
				.get("pk_commission_b")).getBillCardPanel().getBodyItem("stdmaxvalue").setEnabled(!isChangeStatus());
		((CommissionGrandBillForm) getBillFormView().getMaingrandrelationship().getBodyTabTOGrandCardComposite()
				.get("pk_commission_b")).getBillCardPanel().getBodyItem("stdminvalue").setEnabled(!isChangeStatus());
		((CommissionGrandBillForm) getBillFormView().getMaingrandrelationship().getBodyTabTOGrandCardComposite()
				.get("pk_commission_b")).getBillCardPanel().getBodyItem("judgeflag").setEnabled(!isChangeStatus());
		((CommissionGrandBillForm) getBillFormView().getMaingrandrelationship().getBodyTabTOGrandCardComposite()
				.get("pk_commission_b")).getBillCardPanel().getBodyItem("testflag").setEnabled(!isChangeStatus());
		((CommissionGrandBillForm) getBillFormView().getMaingrandrelationship().getBodyTabTOGrandCardComposite()
				.get("pk_commission_b")).getBillCardPanel().getBodyItem("analysisname").setEnabled(!isChangeStatus());
	}

	public nc.ui.pubapp.uif2app.components.grand.ListGrandPanelComposite getBillListView() {
		return billListView;
	}

	public void setBillListView(nc.ui.pubapp.uif2app.components.grand.ListGrandPanelComposite billListView) {
		this.billListView = billListView;
	}

	public nc.ui.pubapp.uif2app.components.grand.CardGrandPanelComposite getBillFormView() {
		return billFormView;
	}

	public void setBillFormView(nc.ui.pubapp.uif2app.components.grand.CardGrandPanelComposite billFormView) {
		this.billFormView = billFormView;
	}

	public boolean isChangeStatus() {
		return isChangeStatus;
	}

	public void setChangeStatus(boolean isChangeStatus) {
		this.isChangeStatus = isChangeStatus;
	}

	public Map<String, String> getOldValue() {
		return oldValue;
	}

	public void setOldValue(Map<String, String> oldValue) {
		this.oldValue = oldValue;
	}
}
