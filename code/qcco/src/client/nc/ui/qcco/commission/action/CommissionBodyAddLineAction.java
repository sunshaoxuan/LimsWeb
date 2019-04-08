package nc.ui.qcco.commission.action;

import java.util.Vector;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.beans.constenum.DefaultConstEnum;
import nc.ui.pub.beans.constenum.IConstEnum;
import nc.ui.pubapp.uif2app.actions.BodyAddLineAction;

public class CommissionBodyAddLineAction extends BodyAddLineAction {

	@Override
	protected void afterLineInsert(int index) {
		super.afterLineInsert(index);
		setGroup(index);
	}

	private void setGroup(int index) {
		if (index < 4 && index >= 0) {
			UIRefPane refpane = ((UIRefPane) getCardPanel().getBodyItem("samplegroup").getComponent());
			Vector matchedData = refpane.getRefModel().matchBlurData(String.valueOf(index + 1));
			IConstEnum val = new DefaultConstEnum(((Vector) matchedData.get(0)).get(2),
					(String) ((Vector) matchedData.get(0)).get(1));
			getCardPanel().setBodyValueAt(val, index, "samplegroup");
			getCardPanel().setBodyValueAt(val, index, "pk_samplegroup");
		}

	}
}
