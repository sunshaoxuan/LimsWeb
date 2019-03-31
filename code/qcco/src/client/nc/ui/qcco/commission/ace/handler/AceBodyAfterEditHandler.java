package nc.ui.qcco.commission.ace.handler;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.bs.pubapp.utils.UserDefineRefUtils;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.MapListProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.beans.constenum.DefaultConstEnum;
import nc.ui.pub.beans.constenum.IConstEnum;
import nc.ui.pub.bill.BillCellEditor;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.qcco.commission.CommissionBVO;

import org.apache.commons.lang.StringUtils;

public class AceBodyAfterEditHandler implements IAppEventHandler<CardBodyAfterEditEvent> {
	private ShowUpableBillForm grandCard;

	@Override
	public void handleAppEvent(CardBodyAfterEditEvent e) {
		if ("productserial".equals(e.getKey())) {
			// 产品系列
			e.getBillCardPanel().setBodyValueAt(e.getValue(), e.getRow(), "pk_productserial");
			clearBodyItems(e, new String[] { "pk_productserial", "productserial" });
		} else if ("enterprisestandard".equals(e.getKey())) {
			// 企业标准
			e.getBillCardPanel().setBodyValueAt(e.getValue(), e.getRow(), "pk_enterprisestandard");
			clearBodyItems(e, new String[] { "pk_productserial", "productserial", "pk_enterprisestandard",
					"enterprisestandard" });
		} else if ("productspec".equals(e.getKey())) {
			// 规格号
			e.getBillCardPanel().setBodyValueAt(e.getValue(), e.getRow(), "pk_productspec");
			clearBodyItems(e, new String[] { "pk_productserial", "productserial", "pk_enterprisestandard",
					"enterprisestandard", "pk_productspec", "productspec", "typeno" });
		} else if ("structuretype".equals(e.getKey())) {
			// 结构类型
			e.getBillCardPanel().setBodyValueAt(e.getValue(), e.getRow(), "pk_structuretype");
			clearBodyItems(e, new String[] { "pk_productserial", "productserial", "pk_enterprisestandard",
					"enterprisestandard", "pk_productspec", "productspec", "pk_structuretype", "structuretype",
					"typeno" });
		} else if ("contactbrand".equals(e.getKey())) {
			// 触点牌号
			BillCellEditor bitem = (BillCellEditor) e.getSource();
			UIRefPane refPane = (UIRefPane) bitem.getComponent();
			e.getBillCardPanel().setBodyValueAt(refPane.getRefPK(), e.getRow(), "pk_contactbrand");
		} else if ("samplegroup".equals(e.getKey())) {
			// mod tank 当样品组别和实验参数都不为空的时候进行重新生成孙表的工作,否则不进行如何工作
			BillCellEditor bitem = (BillCellEditor) e.getSource();
			UIRefPane refPane = (UIRefPane) bitem.getComponent();
			e.getBillCardPanel().setBodyValueAt(refPane.getRefPK(), e.getRow(), "pk_samplegroup");

			// 不可重复组别
			for (int i = 0; i < e.getBillCardPanel().getRowCount(); i++) {
				if (e.getRow() != i) {
					if (e.getValue().equals(e.getBillCardPanel().getBodyValueAt(i, "pk_samplegroup"))) {
						MessageDialog.showErrorDlg(e.getContext().getEntranceUI(), "错误", "样品组别 ["
								+ e.getBillCardPanel().getBodyValueAt(i, "samplegroup") + "] 发生重复。");
						e.getBillCardPanel().setBodyValueAt(e.getOldValue(), e.getRow(), "samplegroup");
						e.getBillCardPanel().setBodyValueAt(e.getOldValue(), e.getRow(), "pk_samplegroup");
						CommissionBVO bodyVO = new CommissionBVO();
						bodyVO.setAttributeValue("samplegroup", e.getOldValue());
						UserDefineRefUtils.refreshItemRefValue(bodyVO, e.getBillCardPanel().getBodyPanel().getTable(),
								e.getRow(), e.getBillCardPanel().getBodyItem("samplegroup"), true);
						return;
					}
				}
			}
			// 清空孙表样品
			clearGrandLines(e);
			if (e.getBillCardPanel().getBodyValueAt(e.getRow(), "analysisref") != null) {
				// 如果实验前参数不为空,那么进行生成孙表工作
				try {
					generateGrandLines(e);
				} catch (BusinessException ex) {
					Logger.error(ex.getMessage());
				}
			}
			// end
		} else if ("analysisref".equals(e.getKey())) {
			// mod tank 当样品组别和实验参数都不为空的时候进行重新生成孙表的工作,否则不进行如何工作
			BillCellEditor bitem = (BillCellEditor) e.getSource();
			UIRefPane refPane = (UIRefPane) bitem.getComponent();
			e.getBillCardPanel().setBodyValueAt(refPane.getRefPK(), e.getRow(), "pk_analysisref");

			// 清空孙表样品
			clearGrandLines(e);
			if (e.getBillCardPanel().getBodyValueAt(e.getRow(), "samplegroup") != null) {
				// 如果实验前参数不为空,那么进行生成孙表工作
				try {
					generateGrandLines(e);
				} catch (BusinessException ex) {
					Logger.error(ex.getMessage());
				}
			}
			// end
		}
	}

	private void clearBodyItems(CardBodyAfterEditEvent e, String[] exceptions) {
		for (BillItem item : e.getBillCardPanel().getBodyItems()) {
			if (!Arrays.asList(exceptions).contains(item.getKey())) {
				e.getBillCardPanel().setBodyValueAt(null, e.getRow(), item.getKey());

				if ("samplegroup".equals(item.getKey()) || "analysisref".equals(item.getKey())) {
					// 当清空样品组别或实验前参数时，清空孙表
					clearGrandLines(e);
				}
			}
		}
	}

	private void clearGrandLines(CardBodyAfterEditEvent e) {
		int rowCount = this.getGrandCard().getBillCardPanel().getRowCount();
		if (rowCount > 0) {
			int[] lineSet = new int[rowCount];
			for (int i = 0; i < rowCount; i++) {
				lineSet[i] = i;
			}
			this.getGrandCard().getBillCardPanel().getBodyPanel().delLine(lineSet);
		}
	}

	@SuppressWarnings("unchecked")
	private void generateGrandLines(CardBodyAfterEditEvent e) throws BusinessException {
		IUAPQueryBS query = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		List<Map<String, Object>> refList = (List<Map<String, Object>>) query
				.executeQuery(
						"select * from (SELECT distinct trim(p.nc_analysis_name) nc_analysis_name, p.pk_result_type, trim(r.nc_result_code) nc_result_code, "
								+ "trim(r.nc_result_namecn) nc_result_namecn, p.pk_test_init, trim(p.test_init_code) test_init_code, trim(p.test_init_name) test_init_name, u.pk_units_type, "
								+ "trim(u.nc_units_code) nc_units_code, trim(u.nc_units_name) nc_units_name, p.nc_max_value, "
								+ "p.nc_min_value, p.nc_stage,cast(p.def1  as int) as def1 from NC_TEST_INIT p "
								+ " INNER JOIN NC_UNITS_TYPE u ON u.pk_units_type = p.pk_units_type "
								+ " INNER JOIN NC_RESULT_TYPE r ON r.pk_result_type = p.pk_result_type "
								+ "where p.nc_enstard = '"
								+ e.getBillCardPanel().getBodyValueAt(e.getRow(), "enterprisestandard")
								+ "'  and p.nc_sample_point = '"
								+ e.getBillCardPanel().getBodyValueAt(e.getRow(), "productspec")
								+ "'  and ' ' || p.NC_COIL_TYPE || ',' like '% "
								+ e.getBillCardPanel().getBodyValueAt(e.getRow(), "structuretype")
								+ ",%'    and p.nc_coil_current = '"
								+ e.getBillCardPanel().getBodyValueAt(e.getRow(), "structuretype")
								+ "'    and p.nc_stage = '"
								+ e.getBillCardPanel().getBodyValueAt(e.getRow(), "productstage")
								+ "' )"
								+ "order by def1", new MapListProcessor());
		if (refList != null && refList.size() > 0) {
			for (Map<String, Object> refRow : refList) {
				this.getGrandCard().getBillCardPanel().getBodyPanel().addLine();
				int row = this.getGrandCard().getBillCardPanel().getRowCount() - 1;
				this.getGrandCard()
						.getBillCardPanel()
						.setBodyValueAt(e.getBillCardPanel().getBodyValueAt(e.getRow(), "samplegroup"), row,
								"samplegroup");
				this.getGrandCard()
						.getBillCardPanel()
						.setBodyValueAt(e.getBillCardPanel().getBodyValueAt(e.getRow(), "pk_samplegroup"), row,
								"pk_samplegroup");
				this.getGrandCard().getBillCardPanel().setBodyValueAt(UFBoolean.TRUE, row, "judgeflag");
				this.getGrandCard().getBillCardPanel().setBodyValueAt(UFBoolean.TRUE, row, "testflag");
				String resultCode = "";
				String resultName = "";
				String refCode = "";
				String refName = "";
				for (Entry<String, Object> refValue : refRow.entrySet()) {
					if (refValue.getKey().equals("nc_analysis_name")) {
						this.getGrandCard().getBillCardPanel().setBodyValueAt(refValue.getValue(), row, "analysisname");
					} else if (refValue.getKey().equals("pk_test_init")) {
						this.getGrandCard().getBillCardPanel().setBodyValueAt(refValue.getValue(), row, "pk_component");
					} else if (refValue.getKey().equals("test_init_code")) {
						refCode = (String) refValue.getValue();
					} else if (refValue.getKey().equals("test_init_name")) {
						refName = (String) refValue.getValue();
					} else if (refValue.getKey().equals("pk_result_type")) {
						this.getGrandCard().getBillCardPanel().setBodyValueAt(refValue.getValue(), row, "pk_valuetype");
					} else if (refValue.getKey().equals("nc_result_code")) {
						resultCode = (String) refValue.getValue();
					} else if (refValue.getKey().equals("nc_result_namecn")) {
						resultName = (String) refValue.getValue();
					} else if (refValue.getKey().equals("nc_max_value")) {
						this.getGrandCard().getBillCardPanel().setBodyValueAt(refValue.getValue(), row, "stdmaxvalue");
					} else if (refValue.getKey().equals("nc_min_value")) {
						this.getGrandCard().getBillCardPanel().setBodyValueAt(refValue.getValue(), row, "stdminvalue");
					} else if (refValue.getKey().equals("nc_stage")) {
						this.getGrandCard().getBillCardPanel().setBodyValueAt(refValue.getValue(), row, "productstage");
					} else if (refValue.getKey().equals("nc_units_name")) {
						this.getGrandCard().getBillCardPanel().setBodyValueAt(refValue.getValue(), row, "unitname");
					}
				}
				if (!StringUtils.isEmpty(resultCode) && !StringUtils.isEmpty(resultName)) {
					IConstEnum aValue = new DefaultConstEnum(resultName, resultName);
					this.getGrandCard().getBillCardPanel().setBodyValueAt(aValue.getValue(), row, "valuetype");
				}

				if (!StringUtils.isEmpty(refCode) && !StringUtils.isEmpty(refName)) {
					IConstEnum aValue = new DefaultConstEnum(refName, refName);
					this.getGrandCard().getBillCardPanel().setBodyValueAt(aValue.getValue(), row, "component");
				}
			}
		}
	}

	public ShowUpableBillForm getGrandCard() {
		return grandCard;
	}

	public void setGrandCard(ShowUpableBillForm grandCard) {
		this.grandCard = grandCard;
	}
}
