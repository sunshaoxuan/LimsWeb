package nc.ui.pubapp.uif2app.event.card;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillEditEvent;

/**
 * ��Ƭ�����з����仯�󴥷����¼��� ��ӵ�һ�б任�������� ���¼��ļ�������Ҫ�����κ�ֵ 2010-1-11
 */
public class CardBodyRowChangedEvent extends CardPanelEvent {

  private BillEditEvent billEditEvent;

  public CardBodyRowChangedEvent(BillCardPanel cardPanel, BillEditEvent e) {
    super(cardPanel, CardBodyRowChangedEvent.class.getName());
    this.setSource(e.getSource());
    this.billEditEvent = e;
  }

  /**
   * ���ԭ��
   * 
   * @return int
   */
  public int getOldRow() {
    return this.billEditEvent.getOldRow();
  }

  /**
   * ��õ�ǰ��
   * 
   * @return int
   */
  public int getRow() {
    return this.billEditEvent.getRow();
  }

	public BillEditEvent getBillEditEvent() {
		return billEditEvent;
	}
  
  
}
