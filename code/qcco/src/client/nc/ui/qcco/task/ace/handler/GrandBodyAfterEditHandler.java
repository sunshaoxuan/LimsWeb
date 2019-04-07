package nc.ui.qcco.task.ace.handler;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCellEditor;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pub.lang.UFBoolean;

public class GrandBodyAfterEditHandler implements IAppEventHandler<CardBodyAfterEditEvent> {

	@Override
	public void handleAppEvent(CardBodyAfterEditEvent e) {
		

		if ("valueways".equals(e.getKey())) {
			if (e.getValue() != null) {
				if ((int)e.getValue() == 1) {
					e.getBillCardPanel().getBodyItem("refvalue").setEnabled(false);
					e.getBillCardPanel().getBodyItem("textvalue").setEnabled(true);
				}else if((int)e.getValue() == 2) {
					e.getBillCardPanel().getBodyItem("refvalue").setEnabled(true);
					e.getBillCardPanel().getBodyItem("textvalue").setEnabled(false);
				}else {
					e.getBillCardPanel().getBodyItem("textvalue").setEnabled(false);
					e.getBillCardPanel().getBodyItem("refvalue").setEnabled(false);
				}
			}
		}
		
	}
}
