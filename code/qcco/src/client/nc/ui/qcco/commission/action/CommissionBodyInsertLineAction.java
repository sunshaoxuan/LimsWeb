package nc.ui.qcco.commission.action;

import java.awt.event.ActionEvent;
import java.util.UUID;

import nc.ui.pubapp.uif2app.actions.BodyInsertLineAction;

public class CommissionBodyInsertLineAction extends BodyInsertLineAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8735882997111929724L;
	@Override
	protected boolean doBeforeAction(ActionEvent e) {
		boolean rt = super.doBeforeAction(e);
		/*getCardPanel().getBodyPanel().addLine();
		getCardPanel().getBodyPanel().delLine();*/
		
		return rt;
		
	}

	@Override
	protected void afterLineInsert(int index) {
		super.afterLineInsert(index);
		// …Ë÷√uuid
		if (getCardPanel().getBodyItem("uniqueid") != null) {

			String uuid = UUID.randomUUID().toString();
			uuid = uuid.replace("-", "");
			getCardPanel().setBodyValueAt(uuid, index, "uniqueid");
			getCardPanel().getBillModel().setValueAt(uuid,index, "uniqueid");
		}
	}
}
