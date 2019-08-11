package nc.ui.qcco.task.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import nc.bs.uif2.LockFailedException;
import nc.bs.uif2.VersionConflictException;
import nc.ui.hr.caculate.view.BannerTimerDialog;
import nc.ui.pubapp.uif2app.actions.pflow.ApproveScriptAction;
import nc.vo.pub.BusinessException;

public class TaskApproveAction extends ApproveScriptAction {
	

	@Override
	public void doAction(final ActionEvent e) throws Exception {
		super.doAction(e);
		/*new SwingWorker() {
			BannerTimerDialog dialog = new BannerTimerDialog(null);
			String error = null;

			protected Boolean doInBackground() throws Exception {
				try {
					dialog.setStartText("正在审批及回写数据中... ");
					dialog.start();
					super.doAction(e);


				} catch (LockFailedException le) {
					error = le.getMessage();
				} catch (VersionConflictException le) {
					throw new BusinessException(le.getBusiObject()
							.toString(), le);
				} catch (Exception ex) {
					error = ex.getMessage();
				} finally {
					dialog.end();
				}
				return Boolean.TRUE;
			}
		}.execute();*/
		
	}
}
