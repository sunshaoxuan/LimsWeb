package nc.ui.qcco.task.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.util.ApproveFlowUtil;
import nc.itf.qcco.ITaskMaintain;
import nc.md.data.access.NCObject;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.actions.pflow.ScriptPFlowAction;
import nc.vo.qcco.task.AggTaskHVO;


public class WriteBackLimsAction extends ScriptPFlowAction {

	public WriteBackLimsAction(){
		setBtnName("回写LIMS");
		setCode("writebacklims");
	}
	private static final long serialVersionUID = 1L;

	@Override
	public void doAction(ActionEvent e) throws Exception {
		Object selectedData = model.getSelectedData();
		if(selectedData != null){
			NCLocator.getInstance().lookup(ITaskMaintain.class).writeBackLims((AggTaskHVO)selectedData);
		}else{
			MessageDialog.showWarningDlg(null, "提示", "未选择任何数据!");
		}
	}
	
	protected boolean isActionEnable() {
		/*Object[] objs = model.getSelectedOperaDatas();
		if ((objs != null) && (objs.length > 1)) {
			return true;
		}

		Object selectedData = model.getSelectedData();
		int status = -1;
		if (selectedData != null) {
			NCObject obj = NCObject.newInstance(selectedData);
			if (obj != null) {
				status = ApproveFlowUtil.getBillStatus(obj).intValue();
			}
		}

		boolean isEnable = (model.getAppUiState() == nc.ui.pubapp.uif2app.AppUiState.NOT_EDIT)
				&& (selectedData != null)
				&& ((status == 1) || (status == 2) || (status == 0));

		return isEnable;*/
		return true;
	}
}
