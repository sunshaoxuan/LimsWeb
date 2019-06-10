package nc.ui.qcco.task.ace.handler;

import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;

public class GrandBodyAfterEditHandler implements IAppEventHandler<CardBodyAfterEditEvent> {

	@Override
	public void handleAppEvent(CardBodyAfterEditEvent e) {
		if ("textvalue".equals(e.getKey())) {
			if (e.getValue() != null) {
				e.getBillCardPanel().setBodyValueAt("���޸�", e.getRow(), "conditionstatus");
			} else {
				e.getBillCardPanel().setBodyValueAt("δ¼��", e.getRow(), "conditionstatus");
			}

			e.getBillCardPanel().setBodyValueAt(e.getValue(), e.getRow(), "formatted_entry");
		} else if ("refvalue".equals(e.getKey())) {
			if (e.getValue() != null) {
				e.getBillCardPanel().setBodyValueAt("���޸�", e.getRow(), "conditionstatus");
			} else {
				e.getBillCardPanel().setBodyValueAt("δ¼��", e.getRow(), "conditionstatus");
			}
		}

	}
}
