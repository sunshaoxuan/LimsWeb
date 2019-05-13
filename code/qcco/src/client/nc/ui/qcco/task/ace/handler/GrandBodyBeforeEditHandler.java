package nc.ui.qcco.task.ace.handler;

import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;

public class GrandBodyBeforeEditHandler implements
IAppEventHandler<CardBodyBeforeEditEvent> {

@Override
public void handleAppEvent(CardBodyBeforeEditEvent e) {
		if ("textvalue".equals(e.getKey())) {
			Integer valueways =e.getBillCardPanel().getBodyValueAt(e.getRow(), "valueways")== null ?null: (Integer) e.getBillCardPanel().getBodyValueAt(e.getRow(), "valueways");
			if(null == valueways){
				MessageDialog.showErrorDlg(e.getContext().getEntranceUI(), "错误", "取值方式不能为空。"); 
				e.setReturnValue(true);
				return;
				
			}else if(valueways == 1) {
				e.setReturnValue(true);
				return;
			}else if (valueways == 2) {
				e.setReturnValue(false);
				return;
			}else  {
				e.setReturnValue(false);
				return;
			}
			
		}
		if ("pk_refvalue".equals(e.getKey())) {
			Integer valueways =e.getBillCardPanel().getBodyValueAt(e.getRow(), "valueways")== null ?null: (Integer) e.getBillCardPanel().getBodyValueAt(e.getRow(), "valueways");
			if(null == valueways){
				MessageDialog.showErrorDlg(e.getContext().getEntranceUI(), "错误", "取值方式不能为空。"); 
				e.setReturnValue(true);
				return;
			}else if (valueways ==1) {
				e.setReturnValue(false);
				return;
			}else if (valueways ==2) {
				e.setReturnValue(true);
				return;
			}else  {
				e.setReturnValue(false);
				return;
			}
		}
		e.setReturnValue(true);
	}
}
