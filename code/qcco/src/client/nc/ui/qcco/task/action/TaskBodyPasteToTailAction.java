package nc.ui.qcco.task.action;

import java.util.ArrayList;
import java.util.List;

import nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.VOStatus;
import nc.vo.qcco.task.AggTaskHVO;
import nc.vo.qcco.task.TaskBVO;
import nc.vo.qcco.task.TaskSVO;

public class TaskBodyPasteToTailAction extends BodyPasteToTailAction {

	/**
	 * serial no
	 */
	private static final long serialVersionUID = 8292276981135372115L;

	public void doAction() {
		super.doAction();

		Integer order = (Integer) getCardPanel().getBillModel().getValueAt(lastPastedRow(), "runorder");
		AggTaskHVO aggvo = (AggTaskHVO) this.getModel().getSelectedData();
		ISuperVO[] bodyVOs = aggvo.getChildren(TaskBVO.class);
		if (bodyVOs != null && bodyVOs.length > 0) {
			List<TaskSVO> pastedSVOs = new ArrayList<TaskSVO>();
			for (ISuperVO bodyvo : bodyVOs) {
				Integer bodyOrder = (Integer) bodyvo.getAttributeValue("runorder");
				if (bodyOrder.equals(order)) {
					TaskBVO bodySource = (TaskBVO) bodyvo;
					TaskSVO[] taskSVOs = bodySource.getPk_task_s();
					for (TaskSVO rvo : taskSVOs) {
						TaskSVO pvo = (TaskSVO) rvo.clone();
						pvo.setPk_task_b(null);
						pvo.setPk_task_s(null);
						pvo.setStatus(VOStatus.NEW);
						pastedSVOs.add(pvo);
					}
					break;
				}
			}
			TaskBVO tgtVO = (TaskBVO) getCardPanel().getBillModel().getBodyValueRowVO(lastPastedRow(),
					TaskBVO.class.getName());
			tgtVO.setPk_task_s(pastedSVOs.toArray(new TaskSVO[0]));
		}

	}
}
