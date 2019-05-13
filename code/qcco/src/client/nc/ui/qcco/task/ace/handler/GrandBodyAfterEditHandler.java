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
		

		/*if ("valueways".equals(e.getKey())) {
			if (e.getValue() != null) {
				if ((int)e.getValue() == 1) {
					e.getBillCardPanel().getBodyItem("pk_refvalue").setEdit(false);
					e.getBillCardPanel().getBodyItem("textvalue").setEdit(true);
				}else if((int)e.getValue() == 2) {
					e.getBillCardPanel().getBodyItem("pk_refvalue").setEdit(true);
					e.getBillCardPanel().getBodyItem("textvalue").setEdit(false);
				}else {
					e.getBillCardPanel().getBodyItem("textvalue").setEdit(false);
					e.getBillCardPanel().getBodyItem("pk_refvalue").setEdit(false);
				}
			}
		}else if ("textvalue".equals(e.getKey())){
			if (e.getValue() != null) {
				e.getBillCardPanel().setBodyValueAt("已修改", e.getRow(), "conditionstatus");
			}else {
				e.getBillCardPanel().setBodyValueAt("未录入", e.getRow(), "conditionstatus");
			}
		}else if ("refvalue".equals(e.getKey())){
			if (e.getValue() != null) {
				e.getBillCardPanel().setBodyValueAt("已修改", e.getRow(), "conditionstatus");
			}else {
				e.getBillCardPanel().setBodyValueAt("未录入", e.getRow(), "conditionstatus");
			}
		}*/
		
	}
}
