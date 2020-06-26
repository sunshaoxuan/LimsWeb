package nc.ui.qcco.task.action;

import java.awt.event.ActionEvent;

import nc.bs.logging.Logger;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.progress.IProgressMonitor;
import nc.ui.pub.beans.progress.NCProgresses;
import nc.ui.pubapp.uif2app.actions.pflow.ApproveScriptAction;

public class TaskApproveAction extends ApproveScriptAction {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 6806890916145681621L;

	@Override
	public void doAction(final ActionEvent e) throws Exception {
		//super.doAction(e);
		new Thread(new Runnable() {
			@Override
			public void run() {
				IProgressMonitor progressMonitor = NCProgresses
						.createDialogProgressMonitor(TaskApproveAction.this
								.getModel().getContext().getEntranceUI());

				progressMonitor.beginTask("������..", -1);
				progressMonitor.setProcessInfo("���ݻ�д��,���Ժ�..."); // ���ݵ�����,���Ժ�......
				try {
					TaskApproveAction.super.doAction(e);
					MessageDialog.showHintDlg(TaskApproveAction.this
							.getModel().getContext().getEntranceUI(), null, "����ͨ��,��д�ɹ�!"); // ���ݵ���ɹ���
				} catch (Exception e) {
					Logger.error(e);
					MessageDialog.showErrorDlg(
							TaskApproveAction.this
							.getModel().getContext().getEntranceUI(), null,
							e.getMessage());
				} finally {
					progressMonitor.done();
				}
			}
		}).start();
		
	}
}
