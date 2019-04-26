package nc.ui.qcco.task.ace.handler;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.jdbc.framework.processor.MapListProcessor;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class AceHeadTailAfterEditHandler implements IAppEventHandler<CardHeadTailAfterEditEvent> {

	@Override
	public void handleAppEvent(CardHeadTailAfterEditEvent e) {
		if("pk_commission_h".equals(e.getKey())){
			IUAPQueryBS iUAPQueryBS = (IUAPQueryBS) NCLocator.getInstance().lookup(
					IUAPQueryBS.class.getName());
				try {
					List<Map<String, Object>> refList = (List<Map<String, Object>>) iUAPQueryBS
							.executeQuery(
									"select billno from QC_COMMISSION_H where pk_commission_h ='"+e.getValue()+"'; ",
									new MapListProcessor());
					if (null != refList && refList.size()>0) {
						e.getBillCardPanel().setHeadItem("billno", refList.get(0).get("billno"));
					}
				} catch (BusinessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
	}

	private void clearBody(BillCardPanel billcardpanel) {
		for (int i = billcardpanel.getBillModel().getRowCount() - 1; i >= 0; i--) {
			billcardpanel.getBodyPanel().delLine(new int[] { i });
		}
	}

}
