package nc.ui.pubapp.uif2app.event.card;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillEditEvent;

/**
 * 卡片表体行发生变化后触发的事件。 如从第一行变换到第三行 本事件的监听不需要返回任何值 2010-1-11
 */
public class CardBodyRowChangedEvent extends CardPanelEvent {

  private BillEditEvent billEditEvent;

  public CardBodyRowChangedEvent(BillCardPanel cardPanel, BillEditEvent e) {
    super(cardPanel, CardBodyRowChangedEvent.class.getName());
    this.setSource(e.getSource());
    this.billEditEvent = e;
  }

  /**
   * 获得原行
   * 
   * @return int
   */
  public int getOldRow() {
    return this.billEditEvent.getOldRow();
  }

  /**
   * 获得当前行
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
