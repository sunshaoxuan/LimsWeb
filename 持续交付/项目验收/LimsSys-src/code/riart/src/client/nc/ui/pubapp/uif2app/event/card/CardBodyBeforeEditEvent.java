package nc.ui.pubapp.uif2app.event.card;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillEditEvent;
import nc.ui.pubapp.uif2app.event.ICallBackEvent;

/**
 * 卡片表体编辑前触发的事件 在具体的事件响应方法中，我们必须返回boolean类型的值，以决定当前选择的卡片表体项目是否可以编辑 2010-1-12
 */
public class CardBodyBeforeEditEvent extends CardPanelEvent implements
    ICallBackEvent<Boolean> {

	//tank 2019年11月10日22:49:01 任务单孙边使用字段
	private int hitCount = 0;
	

  private BillEditEvent billEditEvent;

  private Boolean editable = Boolean.TRUE;

  public CardBodyBeforeEditEvent(BillCardPanel cardPanel, BillEditEvent e) {
    super(cardPanel, CardBodyBeforeEditEvent.class.getName());
    this.setSource(e.getSource());
    this.billEditEvent = e;
  }

  /**
   * 获得key
   * 
   * @return String
   */
  public String getKey() {
    return this.billEditEvent.getKey();
  }

  @Override
  public Boolean getReturnValue() {
    return this.editable;
  }

  /**
   * 获得当前行
   * 
   * @return int
   */
  public int getRow() {
    return this.billEditEvent.getRow();
  }

  /**
   * 获得所属子表
   * 
   * @return String
   */
  public String getTableCode() {
    return this.billEditEvent.getTableCode();
  }

  /**
   * 获得值
   * 
   * @return Object
   */
  public Object getValue() {
    return this.billEditEvent.getValue();
  }

  @Override
  public void setReturnValue(Boolean value) {
    if (value == null || this.editable == null) {
      this.editable = value;
    }
    else {
      boolean b = this.editable.booleanValue() & value.booleanValue();
      this.editable = Boolean.valueOf(b);
    }
  }

public int getHitCount() {
	return hitCount;
}

public void setHitCount(int hitCount) {
	this.hitCount = hitCount;
}
}
