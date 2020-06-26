package nc.ui.pubapp.uif2app.event.card;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillEditEvent;
import nc.ui.pubapp.uif2app.event.ICallBackEvent;

/**
 * ��Ƭ����༭ǰ�������¼� �ھ�����¼���Ӧ�����У����Ǳ��뷵��boolean���͵�ֵ���Ծ�����ǰѡ��Ŀ�Ƭ������Ŀ�Ƿ���Ա༭ 2010-1-12
 */
public class CardBodyBeforeEditEvent extends CardPanelEvent implements
    ICallBackEvent<Boolean> {

	//tank 2019��11��10��22:49:01 �������ʹ���ֶ�
	private int hitCount = 0;
	

  private BillEditEvent billEditEvent;

  private Boolean editable = Boolean.TRUE;

  public CardBodyBeforeEditEvent(BillCardPanel cardPanel, BillEditEvent e) {
    super(cardPanel, CardBodyBeforeEditEvent.class.getName());
    this.setSource(e.getSource());
    this.billEditEvent = e;
  }

  /**
   * ���key
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
   * ��õ�ǰ��
   * 
   * @return int
   */
  public int getRow() {
    return this.billEditEvent.getRow();
  }

  /**
   * ��������ӱ�
   * 
   * @return String
   */
  public String getTableCode() {
    return this.billEditEvent.getTableCode();
  }

  /**
   * ���ֵ
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
