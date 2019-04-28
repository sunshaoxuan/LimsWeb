package nc.ui.qcco.task.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
						

					}
				}
				//生成孙表测试条件
				insertTestCondition(pklists);
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void insertTestCondition(List<TaskHBodyVO> pklists) {
		List<String>list_NA = new ArrayList<>();
		List<String>nolist_NA = new ArrayList<>();
		IUAPQueryBS iUAPQueryBS = (IUAPQueryBS) NCLocator.getInstance().lookup(
				IUAPQueryBS.class.getName());
		InSQLCreator insql = new InSQLCreator();
		try {
		
			for (TaskHBodyVO taskHBodyVO:pklists) {
				if(taskHBodyVO.getTestlistName().equals("_NA")){
					list_NA.add(taskHBodyVO.getUnique());
					
				}else {
					nolist_NA.add(taskHBodyVO.getUnique());
				}
			
			}
		String psInSQL = insql.getInSQL(list_NA.toArray(new String[list_NA.size()]));
		String psInSQLs = insql.getInSQL(nolist_NA.toArray(new String[nolist_NA.size()]));
		List<Map<String, String>> custlist = (List<Map<String, String>>) iUAPQueryBS
				.executeQuery(
						"select * from nc_component_table where analysis in "
						+ "(select TRIM(NC_TASK_ADDNAME) NC_TASK_ADDNAME from "
						+ "nc_task_addunion where pk_task_addunion in ("+psInSQL+")) ",
						new MapListProcessor());
		
		List<Map<String, String>> custlists = (List<Map<String, String>>) iUAPQueryBS
				.executeQuery(
						"select * from NC_TESTLIST_COMP where NC_ANALYSIS_NAME in (select TRIM(NC_ANALYSIS_NAME) NC_ANALYSIS_NAME  "
						+ "from nc_analysis_list nal2 where nal2.name in "
						+ "(select distinct nal.nc_test_condition from nc_analysis_list nal , "
						+ "nc_task_addunion nta  where nal.nc_analysis_name = nta.nc_task_addname "
						+ "and pk_task_addunion='7204D0E9D70246BDBB0C')) AND NC_TESTLIST_NAME in (select TRIM(NC_TESTLIST_NAME)"
						+ " NC_TESTLIST_NAME from nc_task_addunion where pk_task_addunion in("+psInSQLs+")); ",
						new MapListProcessor());
		if(custlist != null && custlist.size() > 0){
			this.getGrandCard().getBillCardPanel().getBodyPanel("pk_task_s").addLine();
		}
		
		if (custlists != null && custlists.size() > 0) {
			
		}
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
