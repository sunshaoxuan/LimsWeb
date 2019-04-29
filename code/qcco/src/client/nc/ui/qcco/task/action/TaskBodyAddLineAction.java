package nc.ui.qcco.task.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.bs.dao.DAOException;
import nc.bs.framework.common.NCLocator;
import nc.hr.utils.InSQLCreator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.MapListProcessor;
import nc.ui.pub.formula.ui.InputHandler.insert_break;
import nc.ui.pubapp.uif2app.actions.BodyAddLineAction;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.qcco.task.view.SunlistPanel;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.qcco.task.TaskBodyVO;
import nc.vo.qcco.task.TaskHBodyVO;

public class TaskBodyAddLineAction extends BodyAddLineAction {
	private ShowUpableBillForm grandCard;
	
	public ShowUpableBillForm getGrandCard() {
		return grandCard;
	}

	public void setGrandCard(ShowUpableBillForm grandCard) {
		this.grandCard = grandCard;
	}

	@Override
	public void doAction() {
		// TODO Auto-generated method stub
		try {
			String pk_commission_h = super.getCardPanel()
					.getHeadItem("pk_commission_h").getValue();
			SunlistPanel sunlistPanel = new SunlistPanel(pk_commission_h);
			if (sunlistPanel.showModal() == 1) {
				List<TaskHBodyVO> pklists = sunlistPanel.getPklist();
				if (pklists != null && pklists.size() > 0) {
					for (int i = 0; i < pklists.size(); i++) {
						super.doAction();
						/*super.getCardPanel().setBodyValueAt(String.valueOf(super.getCardPanel().getRowCount()),
								this.getCardPanel().getRowCount() - 1,
								"rowno");*/
						/*super.getCardPanel().setBodyValueAt(super.getCardPanel().getRowCount(),
								this.getCardPanel().getRowCount() - 1,
								"taskcode");*/
						super.getCardPanel().setBodyValueAt(pklists.get(i).getReportName(),
								this.getCardPanel().getRowCount() - 1,
								"testitem");
						super.getCardPanel().setBodyValueAt(pklists.get(i).getProjectName(),
								this.getCardPanel().getRowCount() - 1,
								"standardclause");
						super.getCardPanel().setBodyValueAt(pklists.get(i).getTestresultname(),
								this.getCardPanel().getRowCount() - 1,
								"pk_testresultname");
						super.getCardPanel().setBodyValueAt(pklists.get(i).getTestresultshortname(),
								this.getCardPanel().getRowCount() - 1,
								"testresultshortname");
						super.getCardPanel().setBodyValueAt(super.getCardPanel().getRowCount(),
								this.getCardPanel().getRowCount() - 1,
								"runorder");
						

						insertTestCondition(pklists.get(i));
					}
				}
				//生成孙表测试条件
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void insertTestCondition(TaskHBodyVO taskHBodyVO) {
		/*List<String>list_NA = new ArrayList<>();
		List<String>nolist_NA = new ArrayList<>();
		
		InSQLCreator insql = new InSQLCreator();*/
		IUAPQueryBS iUAPQueryBS = (IUAPQueryBS) NCLocator.getInstance().lookup(
				IUAPQueryBS.class.getName());
		try {
		
			//for (TaskHBodyVO taskHBodyVO:pklists) {
				if(taskHBodyVO.getTestlistName().equals("_NA")){
					List<Map<String, String>> custlist = (List<Map<String, String>>) iUAPQueryBS
							.executeQuery(
									"select cmp.name,cmp.OPTIONAL,cmp.REPORTABLE,cmp.RESULT_TYPE,cmp.UNITS,cmp.C_DEFAULT_VALUE,cmp.MINIMUM,"
									+ "cmp.MAXIMUM,cmp.C_EN_DEFAULT_Value,ana.INSTRUMENT from nc_component_table cmp "
									+ "inner join analysis ana on cmp.analysis = ana.name where analysis in (select TRIM(NC_TASK_ADDNAME) "
									+ "NC_TASK_ADDNAME from nc_task_addunion where pk_task_addunion ='"+taskHBodyVO.getUnique()+"') ",
									new MapListProcessor());
					if(custlist != null && custlist.size() > 0){
						for(Map<String, String> map : custlist){
							this.getGrandCard().getBillCardPanel().getBodyPanel("pk_task_s").addLine();
							int row = this.getGrandCard().getBillCardPanel().getRowCount("pk_task_s") - 1;
							for(Entry<String, String> refValue : map.entrySet()){
								if (refValue.getKey().equals("name")) {
									this.getGrandCard().getBillCardPanel().setBodyValueAt(map.get("name"), row, "pk_testconditionitem", "pk_task_s");
								} else if (refValue.getKey().equalsIgnoreCase("optional")) {
									this.getGrandCard().getBillCardPanel().setBodyValueAt(map.get("optional").equals("T")?UFBoolean.TRUE:UFBoolean.FALSE, row, "isoptional", "pk_task_s");
								} else if (refValue.getKey().equals("reportable")) {
									this.getGrandCard().getBillCardPanel().setBodyValueAt(map.get("reportable").equals("T")?UFBoolean.TRUE:UFBoolean.FALSE, row, "isallow_out", "pk_task_s");
								} else if (refValue.getKey().equals("result_type")) {
									this.getGrandCard().getBillCardPanel().setBodyValueAt(map.get("result_type"), row, "pk_valuetype", "pk_task_s");
								} else if (refValue.getKey().equals("units")) {
									this.getGrandCard().getBillCardPanel().setBodyValueAt(map.get("units"), row, "unit", "pk_task_s");
								} else if (refValue.getKey().equals("c_defvalue_value")) {
									this.getGrandCard().getBillCardPanel().setBodyValueAt(map.get("c_defvalue_value"), row, "formatted_entry", "pk_task_s");
								} else if (refValue.getKey().equals("minimum")) {
									this.getGrandCard().getBillCardPanel().setBodyValueAt(map.get("minimum"), row, "min_limit", "pk_task_s");
								} else if (refValue.getKey().equals("maximum")) {
									this.getGrandCard().getBillCardPanel().setBodyValueAt(map.get("maximum"), row, "max_limit", "pk_task_s");
								}else if (refValue.getKey().equals("c_en_default_value")) {
									this.getGrandCard().getBillCardPanel().setBodyValueAt(map.get("c_en_default_value"), row, "englishdescription", "pk_task_s");
								}else if (refValue.getKey().equals("instrument")) {
									this.getGrandCard().getBillCardPanel().setBodyValueAt(map.get("instrument"), row, "instrument", "pk_task_s");
								}
								
							}
							if (this.getGrandCard().getBillCardPanel().getBodyValueAt(row, "refvalue") == null && this.getGrandCard().getBillCardPanel().getBodyValueAt(row, "textvalue") ==null) {
								this.getGrandCard().getBillCardPanel().setBodyValueAt(1, row, "conditionstatus", "pk_task_s");
							}
							//this.getGrandCard().getBillCardPanel().setBodyValueAt(UFBoolean.TRUE, row, "conditionstatus", "pk_task_s");
							
						}
					}
					
				}else {
					List<Map<String, String>> custlists = (List<Map<String, String>>) iUAPQueryBS
							.executeQuery(
									"select NC_TESTLIST_COMP.NC_TESTCOMP_NAME,NC_TESTLIST_COMP.OPTIONAL,NC_TESTLIST_COMP.REPORTABLE,"
									+ " NC_TESTLIST_COMP.PK_UNITS_TYPE,NC_TESTLIST_COMP.UNITS,NC_TESTLIST_COMP.C_DEFAULT_VALUE, "
									+ " NC_TESTLIST_COMP.MIN_LIMIT,NC_TESTLIST_COMP.MAX_LIMIT,NC_TESTLIST_COMP.C_EN_DEFAULT_VALUE,"
									+ " nc_result_type.pk_result_type,NC_RESULT_TYPE.nc_result_namecn,analysis.INSTRUMENT from NC_TESTLIST_COMP,NC_COMPONENT_table, "
									+ " NC_RESULT_TYPE,analysis where NC_ANALYSIS_NAME = (select TRIM(NC_ANALYSIS_NAME) NC_ANALYSIS_NAME "
									+ " from nc_analysis_list nal2 where nal2.name in (select distinct nal.nc_test_condition from "
									+ " nc_analysis_list nal , nc_task_addunion nta where nal.nc_analysis_name = nta.nc_task_addname "
									+ " and pk_task_addunion='"+taskHBodyVO.getUnique()+"' )) AND NC_TESTLIST_NAME = (select TRIM(NC_TESTLIST_NAME) NC_TESTLIST_NAME "
									+ " from nc_task_addunion where pk_task_addunion='"+taskHBodyVO.getUnique()+"' ) and NC_TESTLIST_COMP.NC_ANALYSIS_NAME = "
									+ " NC_COMPONENT_table.ANALYSIS and NC_TESTLIST_COMP.NC_TLC_COMPONENT = NC_COMPONENT_table.NAME "
									+ " and NC_COMPONENT_table.pk_result_type= NC_RESULT_TYPE.pk_result_type and "
									+ " NC_COMPONENT_table.analysis = analysis.name ",
									new MapListProcessor());
					if (custlists != null && custlists.size() > 0) {
						for(Map<String, String> map : custlists){
							this.getGrandCard().getBillCardPanel().getBodyPanel("pk_task_s").addLine();
							int row = this.getGrandCard().getBillCardPanel().getRowCount("pk_task_s") - 1;
							for(Entry<String, String> refValue : map.entrySet()){
								if (refValue.getKey().equals("nc_testcomp_name")) {
									this.getGrandCard().getBillCardPanel().setBodyValueAt(map.get("nc_testcomp_name"), row, "pk_testconditionitem", "pk_task_s");
								} else if (refValue.getKey().equalsIgnoreCase("optional")) {
									this.getGrandCard().getBillCardPanel().setBodyValueAt(map.get("optional").equals("T")?UFBoolean.TRUE:UFBoolean.FALSE, row, "isoptional", "pk_task_s");
								} else if (refValue.getKey().equals("reportable")) {
									this.getGrandCard().getBillCardPanel().setBodyValueAt(map.get("reportable").equals("T")?UFBoolean.TRUE:UFBoolean.FALSE, row, "isallow_out", "pk_task_s");
								} else if (refValue.getKey().equals("pk_result_type")) {
									this.getGrandCard().getBillCardPanel().setBodyValueAt(map.get("pk_result_type"), row, "pk_valuetype", "pk_task_s");
								} else if (refValue.getKey().equals("units")) {
									this.getGrandCard().getBillCardPanel().setBodyValueAt(map.get("units"), row, "unit", "pk_task_s");
								} else if (refValue.getKey().equals("c_defvalue_value")) {
									this.getGrandCard().getBillCardPanel().setBodyValueAt(map.get("c_defvalue_value"), row, "formatted_entry", "pk_task_s");
								} else if (refValue.getKey().equals("min_limit")) {
									this.getGrandCard().getBillCardPanel().setBodyValueAt(map.get("minimum"), row, "min_limit", "pk_task_s");
								} else if (refValue.getKey().equals("max_limit")) {
									this.getGrandCard().getBillCardPanel().setBodyValueAt(map.get("maximum"), row, "max_limit", "pk_task_s");
								}else if (refValue.getKey().equals("c_en_default_value")) {
									this.getGrandCard().getBillCardPanel().setBodyValueAt(map.get("c_en_default_value"), row, "englishdescription", "pk_task_s");
								}else if (refValue.getKey().equals("nc_result_namecn")) {
									this.getGrandCard().getBillCardPanel().setBodyValueAt(map.get("nc_result_namecn"), row, "valuetype", "pk_task_s");
								}else if (refValue.getKey().equals("instrument")) {
									this.getGrandCard().getBillCardPanel().setBodyValueAt(map.get("instrument"), row, "instrument", "pk_task_s");
								}
								}
							if (this.getGrandCard().getBillCardPanel().getBodyValueAt(row, "refvalue") == null && this.getGrandCard().getBillCardPanel().getBodyValueAt(row, "textvalue") ==null) {
								this.getGrandCard().getBillCardPanel().setBodyValueAt(1, row, "conditionstatus", "pk_task_s");
							}
							}
					}
				}
			
			//}
		//String psInSQL = insql.getInSQL(list_NA.toArray(new String[list_NA.size()]));
		//String psInSQLs = insql.getInSQL(nolist_NA.toArray(new String[nolist_NA.size()]));
		
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
