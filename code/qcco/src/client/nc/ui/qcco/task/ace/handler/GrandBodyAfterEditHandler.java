package nc.ui.qcco.task.ace.handler;

import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;

public class GrandBodyAfterEditHandler implements IAppEventHandler<CardBodyAfterEditEvent> {

	@Override
	public void handleAppEvent(CardBodyAfterEditEvent e) {
		if ("textvalue".equals(e.getKey())) {
			if (e.getValue() != null) {
				e.getBillCardPanel().setBodyValueAt("已修改", e.getRow(), "conditionstatus");
			} else {
				e.getBillCardPanel().setBodyValueAt("未录入", e.getRow(), "conditionstatus");
			}

			e.getBillCardPanel().setBodyValueAt(e.getValue(), e.getRow(), "formatted_entry");
		} else if ("refvalue".equals(e.getKey())) {
			if (e.getValue() != null) {
				e.getBillCardPanel().setBodyValueAt("已修改", e.getRow(), "conditionstatus");
			} else {
				e.getBillCardPanel().setBodyValueAt("未录入", e.getRow(), "conditionstatus");
			}
		}

	}
}
