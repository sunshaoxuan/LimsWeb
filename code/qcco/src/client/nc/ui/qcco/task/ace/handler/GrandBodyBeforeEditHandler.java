package nc.ui.qcco.task.ace.handler;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import nc.bs.dao.DAOException;
import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.jdbc.framework.processor.MapListProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.qcco.commission.refmodel.ValueTypeRefModel;
import nc.ui.qcco.task.view.RefValuePanel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.qcco.commission.refmodel.SampleGroupRefModel;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.ui.qcco.task.refmodel.TaskAnalyseComponentRefModel;

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
			/*
			 * Integer valueways =
			 * e.getBillCardPanel().getBodyValueAt(e.getRow(), "valueways") ==
			 * null ? null : (Integer)
			 * e.getBillCardPanel().getBodyValueAt(e.getRow(), "valueways");
			 * String pk_result_type = (String)
			 * e.getBillCardPanel().getBodyValueAt(e.getRow(), "pk_valuetype");
			 * String typeCode = ""; if (!StringUtils.isEmpty(pk_result_type)) {
			 * Vector refValue = new
			 * ValueTypeRefModel().matchPkData(pk_result_type); if (refValue !=
			 * null) { typeCode = (String) ((Vector) refValue.get(0)).get(0); }
			 * } if (null == valueways) {
			 * MessageDialog.showErrorDlg(e.getContext().getEntranceUI(), "错误",
			 * "取值方式不能为空。"); e.setReturnValue(true); return;
			 * 
			 * } else if (valueways == 1) { e.setReturnValue(true); return; }
			 * else if (valueways == 2) { if (!StringUtils.isEmpty(typeCode) &&
			 * typeCode.trim().equals("4")) { e.setReturnValue(true); return; }
			 * else { e.setReturnValue(false); return; } } else { if
			 * (!StringUtils.isEmpty(typeCode) && typeCode.trim().equals("2")) {
			 * e.setReturnValue(true); return; } else { e.setReturnValue(false);
			 * return; } }
			 */

			Integer valueways = e.getBillCardPanel().getBodyValueAt(e.getRow(), "valueways") == null ? null : (Integer) e
					.getBillCardPanel().getBodyValueAt(e.getRow(), "valueways");
			if (2 == valueways) {
				e.setReturnValue(false);
				return;
			}
			// 如果参照已经有值,那么文本不能输入
			/*
			 * String pk_refvalue =
			 * (String)e.getBillCardPanel().getBodyValueAt(e.getRow(),
			 * "pk_refvalue"); if(pk_refvalue==null || "".equals(pk_refvalue)){
			 * e.setReturnValue(true); }else{ e.setReturnValue(false); }
			 */
		} else if ("pk_refvalue".equals(e.getKey())) {
			// 如果文本已经有值,那么参照不能输入
			/*
			 * String textvalue =
			 * (String)e.getBillCardPanel().getBodyValueAt(e.getRow(),
			 * "textvalue"); if(textvalue!=null && !"".equals(textvalue)){
			 * e.setReturnValue(false); return; }
			 */

			Integer valueways = e.getBillCardPanel().getBodyValueAt(e.getRow(), "valueways") == null ? null : (Integer) e
					.getBillCardPanel().getBodyValueAt(e.getRow(), "valueways");
			if (null == valueways) {
				MessageDialog.showErrorDlg(e.getContext().getEntranceUI(), "错误", "取值方式不能为空。");
			} else if (valueways == 2) {
				e.setReturnValue(true);
				String pk_list_table = e.getBillCardPanel().getBodyValueAt(e.getRow(), "pk_list_table") == null ? null : (String) e
						.getBillCardPanel().getBodyValueAt(e.getRow(), "pk_list_table");
				try {
					RefValuePanel refValuePanel = new RefValuePanel(pk_commission_h, pk_list_table);
					if (refValuePanel.showModal() == 1) {
						String strvalue = refValuePanel.getSelectedstr();
						e.getBillCardPanel().setBodyValueAt(strvalue, e.getRow(), "pk_refvalue", "pk_task_s");
						e.getBillCardPanel().setBodyValueAt("已修改", e.getRow(), "conditionstatus", "pk_task_s");

					}
				} catch (DAOException e1) {
					e1.printStackTrace();
				}
			}
			e.setReturnValue(false);
			return;
		} else if ("samplegroup".equals(e.getKey())) {
			// 查出组别
			String sql = "select pk_samplegroup pk_samplegroup from QC_COMMISSION_B where PK_COMMISSION_H = '" + pk_commission_h
					+ "' and dr = 0";
			IUAPQueryBS bs = NCLocator.getInstance().lookup(IUAPQueryBS.class);
			Set<String> groupPkSet = new HashSet<>();
			try {
				@SuppressWarnings("unchecked")
				List<Map<String, String>> list = (List<Map<String, String>>) bs.executeQuery(sql, new MapListProcessor());
				if (list != null && list.size() > 0) {
					for (Map<String, String> map : list) {
						groupPkSet.add(map.get("pk_samplegroup"));
					}
				}
			} catch (BusinessException e1) {
				ExceptionUtils.wrappException(e1);
			}
			String groupWhere = " pk_sample_group in (";
			if (groupPkSet != null && groupPkSet.size() > 0) {
				boolean isFist = true;
				for (String pk_group : groupPkSet) {
					if (isFist) {
						groupWhere += "'" + pk_group + "'";
						isFist = false;
					} else {
						groupWhere += ",'" + pk_group + "'";
					}

				}
				groupWhere += ") ";

				BillItem item = (BillItem) e.getSource();
				((SampleGroupRefModel) ((UIRefPane) item.getComponent()).getRefModel()).setGroupWhere(groupWhere);
			}
			// e.getBillCardPanel().getHeadItem(item)
		} else if ("component".equals(e.getKey())) {
			// 手工输入参照值,需要进行过滤
			String anaName = (String) e.getBillCardPanel().getBodyValueAt(e.getRow(), "analysisname");
			BillItem item = (BillItem) e.getSource();
			((TaskAnalyseComponentRefModel) ((UIRefPane) item.getComponent()).getRefModel()).setAnalysisName(anaName);

		}
		e.setReturnValue(true);
	}
}
