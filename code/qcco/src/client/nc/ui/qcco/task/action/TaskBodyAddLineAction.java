package nc.ui.qcco.task.action;

import java.awt.event.ActionEvent;
import java.util.List;

import nc.bs.dao.DAOException;
import nc.ui.pubapp.uif2app.actions.BodyAddLineAction;
import nc.ui.qcco.task.view.SunlistPanel;
import nc.vo.qcco.task.TaskBodyVO;
import nc.vo.qcco.task.TaskHBodyVO;

public class TaskBodyAddLineAction extends BodyAddLineAction {

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
						super.getCardPanel().setBodyValueAt(super.getCardPanel().getRowCount(),
								this.getCardPanel().getRowCount() - 1,
								"taskcode");
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
			}
		} catch (DAOException e) {
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
