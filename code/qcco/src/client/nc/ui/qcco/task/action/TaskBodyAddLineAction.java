package nc.ui.qcco.task.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import nc.bs.dao.DAOException;
import nc.bs.framework.common.NCLocator;
import nc.hr.utils.InSQLCreator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.MapListProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.actions.BodyAddLineAction;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.qcco.task.view.SunlistPanel;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.qcco.task.TaskHBodyVO;

import org.apache.commons.lang.StringUtils;
import org.docx4j.docProps.variantTypes.Array;

public class TaskBodyAddLineAction extends BodyAddLineAction {
	private ShowUpableBillForm grandCard;
	List<TaskHBodyVO> pklists = null;
	private BillForm mainBillForm;//

	public ShowUpableBillForm getGrandCard() {
		return grandCard;
	}

	public void setGrandCard(ShowUpableBillForm grandCard) {
		this.grandCard = grandCard;
	}

	public BillForm getMainBillForm() {
		return mainBillForm;
	}

	public void setMainBillForm(BillForm mainBillForm) {
		this.mainBillForm = mainBillForm;
	}

	@Override
	public void doAction() {
		// TODO Auto-generated method stub
		try {
			String tablecode = this.getGrandCard().getBillCardPanel().getBodyPanel().getTableCode();
			if (!tablecode.equals("pk_task_s")) {
				MessageDialog.showErrorDlg(this.getCardPanel(), "提示", "请将孙表切换到详细测试条件页签！");
				return;
			}
			String pk_commission_h = super.getCardPanel().getHeadItem("pk_commission_h").getValue();
			String pk_task_h = super.getCardPanel().getHeadItem("pk_task_h").getValue();
			String reportType = getReportType(pk_commission_h);// 1是中文，2是英文
			SunlistPanel sunlistPanel = new SunlistPanel(pk_commission_h);
			if (sunlistPanel.showModal() == 1) {
				pklists = sunlistPanel.getPklist();
				if (pklists != null && pklists.size() > 0) {
					int rowno = 0;
					for (int i = 0; i <= pklists.size(); i++) {

						super.doAction();
						/*
						 * super.getCardPanel().setBodyValueAt(String.valueOf(super
						 * .getCardPanel().getRowCount()),
						 * this.getCardPanel().getRowCount() - 1, "rowno");
						 */
						/*
						 * super.getCardPanel().setBodyValueAt(super.getCardPanel
						 * ().getRowCount(), this.getCardPanel().getRowCount() -
						 * 1, "taskcode");
						 */

						if (i != pklists.size()) {
							super.getCardPanel().setBodyValueAt(pklists.get(i).getReportName(),
									this.getCardPanel().getRowCount() - 1, "testitem");
							super.getCardPanel().setBodyValueAt(pklists.get(i).getTestresultname(),
									this.getCardPanel().getRowCount() - 1, "standardclause");
							super.getCardPanel().setBodyValueAt(pklists.get(i).getProjectName(),
									this.getCardPanel().getRowCount() - 1, "pk_testresultname");
							super.getCardPanel().setBodyValueAt(pklists.get(i).getTestresultshortname(),
									this.getCardPanel().getRowCount() - 1, "testresultshortname");
							super.getCardPanel().setBodyValueAt(super.getCardPanel().getRowCount(),
									this.getCardPanel().getRowCount() - 1, "runorder");

							super.getCardPanel().setBodyValueAt(pk_task_h, this.getCardPanel().getRowCount() - 1,
									"pk_task_h");

							// 生成孙表测试条件
							insertTestCondition(pklists.get(i), reportType);

						}
						rowno = this.getCardPanel().getRowCount();
					}
					this.getMainBillForm().getBillCardPanel().getBodyPanel("pk_task_b")
							.delLine(new int[] { rowno - 1 });
				}
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 生成编号
		doSortAndReCode();
	}

	private String getReportType(String pk_commission_h) {
		IUAPQueryBS iUAPQueryBS = (IUAPQueryBS) NCLocator.getInstance().lookup(IUAPQueryBS.class.getName());
		String reportCode = null;
		try {

			// for (TaskHBodyVO taskHBodyVO:pklists) {

			List<Map<String, String>> custlist = (List<Map<String, String>>) iUAPQueryBS.executeQuery(
					"select RP_REPORT_CODE from QC_COMMISSION_H left join NC_REPORT_LANG "
							+ "on NC_REPORT_LANG.pk_report_lang=qc_commission_h.REPORTLANG"
							+ " where pk_commission_h='" + pk_commission_h + "'", new MapListProcessor());
			for (Map<String, String> map : custlist) {
				reportCode = map.get("rp_report_code");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reportCode;
	}

	@Override
	protected void afterLineInsert(int index) {
		// TODO Auto-generated method stub
		super.afterLineInsert(index);
		// insertTestCondition(pklists.get(index));
	}

	private void doSortAndReCode() {
		// get 委托单号
		BillItem billItem = mainBillForm.getBillCardPanel().getHeadItem("pk_commission_h.billno");
		String commissionCode = "";
		if (billItem != null && billItem.getValueObject() != null
				&& !String.valueOf(billItem.getValueObject()).replaceAll(" ", "").equals("")) {
			commissionCode = String.valueOf(billItem.getValueObject());
		} else {
			commissionCode = "";
		}
		getCardPanel().getBillModel().sortByColumn("runorder", true);
		Vector dataVector = getCardPanel().getBillModel().getDataVector();
		StringBuilder sb = new StringBuilder();
		sb.append(commissionCode);
		if (dataVector != null && dataVector.size() > 0) {
			for (int i = 0; i < dataVector.size(); i++) {
				if (dataVector != null) {
					int rowNoColNumber = getCardPanel().getBillModel().getBodyColByKey("rowno");
					if (rowNoColNumber >= 0) {
						// 改变行号
						if (dataVector.get(i) != null) {
							Vector colData = (Vector) dataVector.get(i);
							colData.set(rowNoColNumber, i + 1);
						}

					}
					int runorderColNumber = getCardPanel().getBillModel().getBodyColByKey("taskcode");
					if (runorderColNumber >= 0) {
						// 重新生成编号
						if (dataVector.get(i) != null) {
							sb.append("-");
							if (i < 9) {
								sb.append(0);
							}
							sb.append(i + 1);
							Vector colData = (Vector) dataVector.get(i);
							colData.set(runorderColNumber, sb.toString());
							sb.delete(sb.length() - 3, sb.length());
						}
					} else {
						ShowStatusBarMsgUtil.showErrorMsg("重新生成编号失败!", "未找到'任务编号'字段", getModel().getContext());
						break;
					}
				}
			}
		}

	}

	@SuppressWarnings("unchecked")
	private void insertTestCondition(TaskHBodyVO taskHBodyVO, String reportType) {
		/*
		 * List<String>list_NA = new ArrayList<>(); List<String>nolist_NA = new
		 * ArrayList<>();
		 * 
		 * InSQLCreator insql = new InSQLCreator();
		 */
		IUAPQueryBS iUAPQueryBS = (IUAPQueryBS) NCLocator.getInstance().lookup(IUAPQueryBS.class.getName());
		try {

			// for (TaskHBodyVO taskHBodyVO:pklists) {
			if (taskHBodyVO.getTestlistName().equals("_NA")) {
				//select TRIM(NC_TASK_ADDNAME) NC_TASK_ADDNAME from nc_task_addunion where pk_task_addunion ='"taskHBodyVO.getUnique() + "'""
				List<Map<String, String>> custlists = (List<Map<String, String>>) iUAPQueryBS
						.executeQuery("select TRIM(NC_TASK_ADDNAME) NC_TASK_ADDNAME from nc_task_addunion where pk_task_addunion ='"+taskHBodyVO.getUnique() + "'", new MapListProcessor());
				List<String> strlist = new ArrayList();
				if(null != custlists && custlists.size()>0){
					for (Map<String, String> map : custlists) {
						if (map.get("nc_task_addname")!=null 
								&& map.get("nc_task_addname").toString().substring(map.get("nc_task_addname").toString().length()-2, map.get("nc_task_addname").toString().length()).equals("_A")) {
							String newVlaue = map.get("nc_task_addname").toString().substring(0, map.get("nc_task_addname").toString().length()-2);
							strlist.add(newVlaue);
						}else if (map.get("nc_task_addname")!=null && map.get("nc_task_addname").toString().substring(map.get("nc_task_addname").toString().length()-1, map.get("nc_task_addname").toString().length()).equals("A")) {
							String newVlaue = map.get("nc_task_addname").toString().substring(0, map.get("nc_task_addname").toString().length()-1);
							strlist.add(newVlaue);
						}
					}
				}
				InSQLCreator insql = new InSQLCreator();
				String string = insql.getInSQL((strlist==null || strlist.size() <=0)?null:strlist.toArray(new String[0]));
				List<Map<String, String>> custlist = (List<Map<String, String>>) iUAPQueryBS
						.executeQuery(
								"select  cmp.name,cmp.OPTIONAL,cmp.REPORTABLE,trim(NC_RESULT_TYPE.nc_result_namecn) as nc_result_namecn ,cmp.PK_RESULT_TYPE,trim(NC_UNITS_TYPE.NC_UNITS_DISP) as units,cmp.C_DEFAULT_VALUE,cmp.MINIMUM,"
										+ "cmp.MAXIMUM,cmp.C_EN_DEFAULT_Value,ana.INSTRUMENT, cmp.pk_list_table from nc_component_table cmp "
										+ "inner join analysis ana on cmp.analysis = ana.name"
										+ " left join NC_UNITS_TYPE "
										+ " on cmp.pk_units_type=NC_UNITS_TYPE.pk_units_type left join NC_RESULT_TYPE "
										+ " on NC_RESULT_TYPE.pk_result_type=cmp.pk_result_type where analysis in"
										+ " ("+string+") ", new MapListProcessor());
				if (custlist != null && custlist.size() > 0) {
					for (Map<String, String> map : custlist) {
						this.getGrandCard().getBillCardPanel().getBodyPanel("pk_task_s").addLine();
						int row = this.getGrandCard().getBillCardPanel().getRowCount("pk_task_s") - 1;
						for (Entry<String, String> refValue : map.entrySet()) {
							if (refValue.getKey().equals("name")) {
								this.getGrandCard().getBillCardPanel()
										.setBodyValueAt(map.get("name"), row, "pk_testconditionitem", "pk_task_s");
							} else if (refValue.getKey().equalsIgnoreCase("optional")) {
								this.getGrandCard()
										.getBillCardPanel()
										.setBodyValueAt(
												map.get("optional").equals("T") ? UFBoolean.TRUE : UFBoolean.FALSE,
												row, "isoptional", "pk_task_s");
							} else if (refValue.getKey().equals("reportable")) {
								this.getGrandCard()
										.getBillCardPanel()
										.setBodyValueAt(
												map.get("reportable").equals("T") ? UFBoolean.TRUE : UFBoolean.FALSE,
												row, "isallow_out", "pk_task_s");
							} else if (refValue.getKey().equals("pk_result_type")) {
								this.getGrandCard().getBillCardPanel()
										.setBodyValueAt(map.get("pk_result_type"), row, "pk_valuetype", "pk_task_s");
							} else if (refValue.getKey().equals("nc_result_namecn")) {
								this.getGrandCard().getBillCardPanel()
										.setBodyValueAt(map.get("nc_result_namecn"), row, "valuetype", "pk_task_s");
							} else if (refValue.getKey().equals("units")) {
								this.getGrandCard().getBillCardPanel()
										.setBodyValueAt(map.get("units"), row, "unit", "pk_task_s");
							} else if (refValue.getKey().equals("c_defvalue_value")) {
								this.getGrandCard()
										.getBillCardPanel()
										.setBodyValueAt(map.get("c_defvalue_value"), row, "formatted_entry",
												"pk_task_s");
							} else if (refValue.getKey().equals("minimum")) {
								this.getGrandCard()
										.getBillCardPanel()
										.setBodyValueAt(
												new UFDouble(map.get("minimum") == null ? "0" : map.get("minimum")),
												row, "min_limit", "pk_task_s");
							} else if (refValue.getKey().equals("maximum")) {
								this.getGrandCard()
										.getBillCardPanel()
										.setBodyValueAt(
												new UFDouble(map.get("maximum") == null ? "0" : map.get("maximum")),
												row, "max_limit", "pk_task_s");
							} else if (refValue.getKey().equals("c_en_default_value")) {
								this.getGrandCard()
										.getBillCardPanel()
										.setBodyValueAt(map.get("c_en_default_value"), row, "englishdescription",
												"pk_task_s");
							} else if (refValue.getKey().equals("instrument")) {
								this.getGrandCard().getBillCardPanel()
										.setBodyValueAt(map.get("instrument"), row, "instrument", "pk_task_s");
							} else if (refValue.getKey().equals("pk_list_table")) {
								if (map.get("pk_list_table") == null) {
									if (map.get("c_en_default_value") == null || map.get("c_default_value") == null) {
										this.getGrandCard().getBillCardPanel()
												.setBodyValueAt(1, row, "valueways", "pk_task_s");
									} else {
										this.getGrandCard().getBillCardPanel()
												.setBodyValueAt(3, row, "valueways", "pk_task_s");
									}
									// 文本值
									if (null != reportType && reportType.equals("1")) {
										// yingwen
										this.getGrandCard()
												.getBillCardPanel()
												.setBodyValueAt(map.get("c_en_default_value"), row, "textvalue",
														"pk_task_s");
									} else if (null != reportType && reportType.equals("2")) {
										this.getGrandCard()
												.getBillCardPanel()
												.setBodyValueAt(map.get("c_default_value"), row, "textvalue",
														"pk_task_s");
									}
								} else {
									this.getGrandCard().getBillCardPanel()
											.setBodyValueAt(2, row, "valueways", "pk_task_s");
									this.getGrandCard().getBillCardPanel().setBodyValueAt(map.get("pk_list_table"), row, "pk_value_type", "pk_task_s");
									// 参照值
									String refvalue = getRefValue(map.get("pk_list_table"), reportType);
									this.getGrandCard().getBillCardPanel()
											.setBodyValueAt(refvalue, row, "pk_refvalue", "pk_task_s");
								}
							}
						}
						if (this.getGrandCard().getBillCardPanel().getBodyValueAt(row, "refvalue") == null
								&& this.getGrandCard().getBillCardPanel().getBodyValueAt(row, "textvalue") == null) {
							this.getGrandCard().getBillCardPanel()
									.setBodyValueAt("未录入", row, "conditionstatus", "pk_task_s");
						} else {
							this.getGrandCard().getBillCardPanel()
									.setBodyValueAt("已录入", row, "conditionstatus", "pk_task_s");
						}
					}
				}

			} else {
				List<Map<String, String>> custlists = (List<Map<String, String>>) iUAPQueryBS
						.executeQuery(
								"select  NC_TESTLIST_COMP.pk_list_table, NC_TESTLIST_COMP.NC_TESTCOMP_NAME,NC_TESTLIST_COMP.OPTIONAL,NC_TESTLIST_COMP.REPORTABLE,"
										+ " NC_TESTLIST_COMP.PK_UNITS_TYPE,trim(NC_UNITS_TYPE.NC_UNITS_DISP) as units,NC_TESTLIST_COMP.C_DEFAULT_VALUE,NC_TESTLIST_COMP.c_en_default_value, "
										+ " NC_COMPONENT_table.minimum, NC_COMPONENT_table.maximum,NC_TESTLIST_COMP.C_EN_DEFAULT_VALUE,"
										+ " nc_result_type.pk_result_type,trim(NC_RESULT_TYPE.nc_result_namecn) as nc_result_namecn,analysis.INSTRUMENT from NC_TESTLIST_COMP"
										+ " LEFT JOIN NC_COMPONENT_table ON NC_TESTLIST_COMP.NC_ANALYSIS_NAME = NC_COMPONENT_table.ANALYSIS AND NC_TESTLIST_COMP.NC_TLC_COMPONENT = NC_COMPONENT_table.NAME "
										+ " LEFT JOIN NC_RESULT_TYPE ON NC_COMPONENT_table.pk_result_type = NC_RESULT_TYPE.pk_result_type "
										+ " LEFT JOIN analysis ON NC_COMPONENT_table.analysis = analysis.name "
										+ " LEFT JOIN NC_UNITS_TYPE ON nc_component_table.pk_units_type=NC_UNITS_TYPE.pk_units_type "
										+ " where NC_ANALYSIS_NAME = (select TRIM(NC_ANALYSIS_NAME) NC_ANALYSIS_NAME "
										+ " from nc_analysis_list nal2 where nal2.name in (select distinct nal.nc_test_condition from "
										+ " nc_analysis_list nal , nc_task_addunion nta where nal.nc_analysis_name = nta.nc_task_addname "
										+ " and pk_task_addunion='"
										+ taskHBodyVO.getUnique()
										+ "' )) AND NC_TESTLIST_NAME = (select TRIM(NC_TESTLIST_NAME) NC_TESTLIST_NAME "
										+ " from nc_task_addunion where pk_task_addunion='"
										+ taskHBodyVO.getUnique()
										+ "' ) ", new MapListProcessor());
				if (custlists != null && custlists.size() > 0) {
					for (Map<String, String> map : custlists) {
						this.getGrandCard().getBillCardPanel().getBodyPanel("pk_task_s").addLine();
						int row = this.getGrandCard().getBillCardPanel().getRowCount("pk_task_s") - 1;
						for (Entry<String, String> refValue : map.entrySet()) {
							if (refValue.getKey().equals("nc_testcomp_name")) {
								this.getGrandCard()
										.getBillCardPanel()
										.setBodyValueAt(map.get("nc_testcomp_name"), row, "pk_testconditionitem",
												"pk_task_s");
							} else if (refValue.getKey().equalsIgnoreCase("optional")) {
								this.getGrandCard()
										.getBillCardPanel()
										.setBodyValueAt(
												map.get("optional").equals("T") ? UFBoolean.TRUE : UFBoolean.FALSE,
												row, "isoptional", "pk_task_s");
							} else if (refValue.getKey().equals("reportable")) {
								this.getGrandCard()
										.getBillCardPanel()
										.setBodyValueAt(
												map.get("reportable").equals("T") ? UFBoolean.TRUE : UFBoolean.FALSE,
												row, "isallow_out", "pk_task_s");
							} else if (refValue.getKey().equals("pk_result_type")) {
								this.getGrandCard().getBillCardPanel()
										.setBodyValueAt(map.get("pk_result_type"), row, "pk_valuetype", "pk_task_s");
							} else if (refValue.getKey().equals("pk_list_table")) {
								if (map.get("pk_list_table") == null) {
									if (map.get("c_en_default_value") == null || map.get("c_default_value") == null) {
										this.getGrandCard().getBillCardPanel()
												.setBodyValueAt(1, row, "valueways", "pk_task_s");
									} else {
										this.getGrandCard().getBillCardPanel()
												.setBodyValueAt(3, row, "valueways", "pk_task_s");
									}
									// 文本值
									if (null != reportType && reportType.equals("1")) {
										// yingwen
										this.getGrandCard()
												.getBillCardPanel()
												.setBodyValueAt(map.get("c_en_default_value"), row, "textvalue",
														"pk_task_s");
									} else if (null != reportType && reportType.equals("2")) {
										this.getGrandCard()
												.getBillCardPanel()
												.setBodyValueAt(map.get("c_default_value"), row, "textvalue",
														"pk_task_s");
									}
								} else {
									this.getGrandCard().getBillCardPanel()
											.setBodyValueAt(2, row, "valueways", "pk_task_s");
									// 参照值
									String refvalue = getRefValue(map.get("pk_list_table"), reportType);
									this.getGrandCard().getBillCardPanel()
											.setBodyValueAt(refvalue, row, "pk_refvalue", "pk_task_s");
								}

							} else if (refValue.getKey().equals("units")) {
								this.getGrandCard().getBillCardPanel()
										.setBodyValueAt(StringUtils.trim(map.get("units")), row, "unit", "pk_task_s");
							} else if (refValue.getKey().equals("c_defvalue_value")) {
								this.getGrandCard()
										.getBillCardPanel()
										.setBodyValueAt(map.get("c_defvalue_value"), row, "formatted_entry",
												"pk_task_s");
							} else if (refValue.getKey().equals("minimum")) {
								this.getGrandCard().getBillCardPanel()
										.setBodyValueAt(map.get("minimum"), row, "min_limit", "pk_task_s");
							} else if (refValue.getKey().equals("maximum")) {
								this.getGrandCard().getBillCardPanel()
										.setBodyValueAt(map.get("maximum"), row, "max_limit", "pk_task_s");
							} else if (refValue.getKey().equals("c_en_default_value")) {
								this.getGrandCard()
										.getBillCardPanel()
										.setBodyValueAt(map.get("c_en_default_value"), row, "englishdescription",
												"pk_task_s");
							} else if (refValue.getKey().equals("nc_result_namecn")) {
								this.getGrandCard().getBillCardPanel()
										.setBodyValueAt(map.get("nc_result_namecn"), row, "valuetype", "pk_task_s");
							} else if (refValue.getKey().equals("instrument")) {
								this.getGrandCard().getBillCardPanel()
										.setBodyValueAt(map.get("instrument"), row, "instrument", "pk_task_s");
							}
						}
						if (this.getGrandCard().getBillCardPanel().getBodyValueAt(row, "refvalue") == null
								&& this.getGrandCard().getBillCardPanel().getBodyValueAt(row, "textvalue") == null) {
							this.getGrandCard().getBillCardPanel()
									.setBodyValueAt("未录入", row, "conditionstatus", "pk_task_s");
						} else {
							this.getGrandCard().getBillCardPanel()
									.setBodyValueAt("已录入", row, "conditionstatus", "pk_task_s");
						}
					}
				}
			}

			// }
			// String psInSQL = insql.getInSQL(list_NA.toArray(new
			// String[list_NA.size()]));
			// String psInSQLs = insql.getInSQL(nolist_NA.toArray(new
			// String[nolist_NA.size()]));

		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String getRefValue(String str, String reportType) {
		IUAPQueryBS iUAPQueryBS = (IUAPQueryBS) NCLocator.getInstance().lookup(IUAPQueryBS.class.getName());
		String refvalue = null;
		try {

			// for (TaskHBodyVO taskHBodyVO:pklists) {

			List<Map<String, String>> custlist = (List<Map<String, String>>) iUAPQueryBS
					.executeQuery(
							"SELECT pk_list_entry, c_en_value,c_cont_value FROM nc_list_entry WHERE pk_list_table =( SELECT    pk_list_table FROM  nc_testlist_comp WHERE   pk_testlist_comp = '"
									+ str
									+ "') AND trim(nc_list_name) = (  SELECT   trim(c_default_value) "
									+ " FROM nc_testlist_comp WHERE  pk_testlist_comp = '" + str + "');",
							new MapListProcessor());
			for (Map<String, String> map : custlist) {
				if (reportType.equals("1")) {
					refvalue = map.get("c_en_value");
				} else if (reportType.equals("2")) {
					refvalue = map.get("c_cont_value");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return refvalue;
	}

	@Override
	public void batchBodyLineOperate(int rowLen) {
		// TODO Auto-generated method stub
		super.batchBodyLineOperate(5);
	}

	@Override
	protected boolean doBeforeAction(ActionEvent e) {
		return super.doBeforeAction(e);
	}

}
