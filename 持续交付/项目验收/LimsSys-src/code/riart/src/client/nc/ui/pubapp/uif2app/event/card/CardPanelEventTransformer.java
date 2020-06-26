package nc.ui.pubapp.uif2app.event.card;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import nc.ui.pub.beans.UIMenuItem;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillActionListener;
import nc.ui.pub.bill.BillBodyMenuListener;
import nc.ui.pub.bill.BillCardBeforeEditListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillEditEvent;
import nc.ui.pub.bill.BillEditListener;
import nc.ui.pub.bill.BillEditListener2;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillItemEvent;
import nc.ui.pub.bill.BillItemHyperlinkEvent;
import nc.ui.pub.bill.BillItemHyperlinkListener;
import nc.ui.pub.bill.BillModel;
import nc.ui.pub.bill.BillSortListener;
import nc.ui.pub.bill.BillTabbedPaneTabChangeEvent;
import nc.ui.pub.bill.BillTabbedPaneTabChangeListener;
import nc.ui.pub.bill.BillTabbedPaneTabChangeListener2;
import nc.ui.pub.bill.BillTotalListener;
import nc.ui.pub.bill.IBillItem;
import nc.ui.pub.bill.IBillModelRowStateChangeEventListener;
import nc.ui.pub.bill.IBillModelSortPrepareListener;
import nc.ui.pub.bill.RowStateChangeEvent;
import nc.ui.pubapp.uif2app.PubExceptionHanler;
import nc.ui.pubapp.uif2app.event.EventCurrentThread;
import nc.ui.pubapp.uif2app.model.IAppModelEx;
import nc.ui.uif2.IExceptionHandler;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.uif2.LoginContext;

import org.apache.commons.lang.ObjectUtils;

//20120222 ���µ�UE�������з�ѡ������ʾ�����󡢾�����Ϣ����ʾ�ڴ����������ܶԻ�����ʾ���޸�����ĵ��õڶ�������Ϊfalse
public class CardPanelEventTransformer {
	public class CardListener implements BillEditListener, BillEditListener2,
			BillCardBeforeEditListener, BillTabbedPaneTabChangeListener,
			BillTabbedPaneTabChangeListener2, BillSortListener,
			BillBodyMenuListener, BillActionListener, BillTotalListener,
			IBillModelSortPrepareListener, ChangeListener,
			IBillModelRowStateChangeEventListener {

		// ��ű༭ǰ�¼���ֵ�����ݸ��༭���¼�ʹ��(��ű�ͷ��β�ֶ�ֵ����keyΪitemkey)
		private Map<String, Object> oldValueMap = new HashMap<String, Object>();

		public Map<String, Object> getOldValueMap() {
			return oldValueMap;
		}

		// ��ű༭ǰ�¼���ֵ�����ݸ��༭���¼�ʹ��(��ű����ֶ�ֵ����keyΪitemkey+"_"+row)
		private Map<String, Object> oldBodyValueMap = new HashMap<String, Object>();

		private String separator = "_";// �ָ���

		private String tableCode;

		/**
		 * �����Ҽ��˵�������¼�
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				// �¼���ʼ
				EventCurrentThread.start();

				UIMenuItem item = (UIMenuItem) e.getSource();
				CardBodyMenuActionEvent event = new CardBodyMenuActionEvent(cardPanel, item);
				CardPanelEventTransformer.this.exModel.fireEvent(event);

				if (null != billBodyMenuListener) {
					billBodyMenuListener.actionPerformed(e);
				}

				// �¼�����
				EventCurrentThread.end();
			} catch (Exception ex) {
				CardPanelEventTransformer.this.exceptionProcess(ex, false);
			}
		}

		/**
		 * �༭���¼�
		 */
		@Override
		public void afterEdit(BillEditEvent e) {
			try {
				// �¼���ʼ
				EventCurrentThread.start();

				if (IBillItem.HEAD == e.getPos()) {
					CardHeadTailAfterEditEvent event = new CardHeadTailAfterEditEvent(cardPanel, e,	getOldValue(e.getKey()));
					event.setExcelImprting(cardPanel.getBillData().isImporting());
					exModel.fireEvent(event);
				} else {
					CardBodyAfterEditEvent event = new CardBodyAfterEditEvent(cardPanel, e, 
							getOldBodyValue(e.getKey(), e.getRow()));
					event.setExcelImprting(cardPanel.getBillData().isImporting());
					processBatchAfterEdit(event);
					exModel.fireEvent(event);
				}
				if (null != CardPanelEventTransformer.this.billEditListener) {
					CardPanelEventTransformer.this.billEditListener.afterEdit(e);
				}


				// ����Ԫ���������
				BillModel bm = CardPanelEventTransformer.this.cardPanel.getBillModel();
				// �Ƿ����ڽ���Excel����
				boolean isExcelImprting = cardPanel.getBillData().isImporting();

				// ���δѡ����壬rowsΪ�գ�����ִ�м��ع�������������������ò��ϡ�2012-03-06 modified
				// int[] rows =
				// CardPanelEventTransformer.this.cardPanel.getBillTable()
				// .getSelectedRows();
				// ʹ����קʱֻ�����һ�α༭���¼��м��������
				// ִ����ʾ��ʽ����Ϊ�������������ϵ�˺͵�ַ���գ�ֻ�᷵��PK����Ҫִ������ʾ��ʽ������ʾ����
				// if (null != bm && rows != null && rows.length > 0 &&
				// (rows[rows.length-1] == e.getRow()||.length==1)) {
				
				if (null != bm && !isExcelImprting) {
					bm.loadLoadRelationItemValue();
					bm.execLoadFormula();
				}

				// �¼�����
				EventCurrentThread.end();
			} catch (Exception ex) {
				CardPanelEventTransformer.this.exceptionProcess(ex, false);
			}
			finally {
				// �¼����������
				if (IBillItem.HEAD == e.getPos() || IBillItem.TAIL == e.getPos()) {
					this.oldValueMap.put(e.getKey(), null);
				} else if (IBillItem.BODY == e.getPos()) {
					this.oldBodyValueMap.put(e.getKey() + separator + e.getRow(), null);
				}
			}
		}

		/**
		 * ƽ̨�ṩ��һ��Alt+Shift��ϼ���ק���ܣ�������ܿ������¸���ĳ���ֶΣ� ����ק���ʱ��ÿһ�и��Ƴ����ֶλ����δ����༭���¼���
		 * ��������ק�����ı༭��ʱ�������жϱ༭���¼��е�״̬������� BATCHCOPYEND ״̬�� �����һ��ҵ�����������ظ�����ҵ����
		 * 
		 * @param event
		 */
		private void processBatchAfterEdit(CardBodyAfterEditEvent event) {
			int[] rows = event.getBillCardPanel().getBillTable().getSelectedRows();

			if (rows == null || rows.length <= 1) {
				event.setAfterEditEventState(CardBodyAfterEditEvent.NOTBATCHCOPY);
			} else if (rows.length == 2) {
				// ѡ��2�����޸�ʱ����ֻ�����һ��aftereditor����������ֱ������״̬ΪBATCHCOPYEND
				event.setAfterEditEventState(CardBodyAfterEditEvent.BATCHCOPYEND);
				event.getAfterEditIndexList().add(rows[1]);
			} else if (rows.length > 2) {
				if (rows[1] == event.getRow()) {
					event.setAfterEditEventState(CardBodyAfterEditEvent.BATCHCOPYBEGIN);
				} else if (rows[rows.length - 1] == event.getRow()) {
					event.setAfterEditEventState(CardBodyAfterEditEvent.BATCHCOPYEND);
				} else {
					event.setAfterEditEventState(CardBodyAfterEditEvent.BATCHCOPING);
				}
				for (int i = 1; i < rows.length; i++) {
					event.getAfterEditIndexList().add(rows[i]);
				}
			}
		}

		/**
		 * ������¼�
		 */
		@Override
		public void afterSort(String key) {
			try {
				// �¼���ʼ
				EventCurrentThread.start();

				CardBodyAfterSortEvent event = new CardBodyAfterSortEvent(cardPanel, key, this.getTableCode());
				CardPanelEventTransformer.this.exModel.fireEvent(event);

				// �¼�����
				EventCurrentThread.end();
			} catch (Exception ex) {
				CardPanelEventTransformer.this.exceptionProcess(ex, false);
			}
		}

		/**
		 * ����ҳǩ�л����¼�
		 */
		@Override
		public void afterTabChanged(BillTabbedPaneTabChangeEvent e) {
			try {
				// �¼���ʼ
				EventCurrentThread.start();

				CardBodyTabChangedEvent event = new CardBodyTabChangedEvent(cardPanel, e);
				exModel.fireEvent(event);

				BillTabbedPaneTabChangeListener tabChangeListener = billTabbedPaneTabChangeListener;
				if (null != tabChangeListener) {
					tabChangeListener.afterTabChanged(e);
				}

				// �¼�����
				EventCurrentThread.end();
			} catch (Exception ex) {
				CardPanelEventTransformer.this.exceptionProcess(ex, false);
			}
		}

		/**
		 * ����༭ǰ�¼�
		 */
		@Override
		public boolean beforeEdit(BillEditEvent e) {
			Boolean editable = null;
			CardBodyBeforeBatchEditEvent batchEvent = null;
			CardBodyBeforeEditEvent event = null;
			int[] rows = cardPanel.getBillTable().getSelectedRows();
			try {
				// �¼���ʼ
				EventCurrentThread.start();

				if (rows.length > 1 && e.getRow() != rows[0]) {
					batchEvent = new CardBodyBeforeBatchEditEvent(cardPanel, e, rows[0]);
					exModel.fireEvent(batchEvent);
					editable = batchEvent.getReturnValue();
				} else {
					event = new CardBodyBeforeEditEvent(cardPanel, e);
					//����������
					event.setHitCount(e.getClickCount());
					exModel.fireEvent(event);
					editable = event.getReturnValue();
				}

				// ���༭ǰ��ֵ�������������༭���¼�����
				this.setBodyOldValue(e.getRow(), e.getKey());

				if (null == editable) {
					String message = "����༭ǰ�¼�δ���÷���ֵ��";/* -=notranslate=- */
					ExceptionUtils.wrappBusinessException(message);
					return false;
				}

				if (null != CardPanelEventTransformer.this.billEditListener2) {
					editable = Boolean.valueOf(editable.booleanValue() && billEditListener2.beforeEdit(e));
				}

				// �¼�����
				EventCurrentThread.end();
			} catch (Exception ex) {
				CardPanelEventTransformer.this.exceptionProcess(ex, false);
				return false;
			}
			return editable.booleanValue();
		}

		/**
		 * ��ͷ/��β�༭ǰ�¼�
		 */
		@Override
		public boolean beforeEdit(BillItemEvent e) {
			Boolean editable = null;
			try {
				// �¼���ʼ
				EventCurrentThread.start();

				CardHeadTailBeforeEditEvent event = new CardHeadTailBeforeEditEvent(cardPanel, e);
				exModel.fireEvent(event);
				editable = event.getReturnValue();

				// ���༭ǰ��ֵ�������������༭���¼�����
				this.setHeadTailOldValue(e.getItem());

				if (null == editable) {
					String message = "��ͷ/��β�༭ǰ�¼�δ���÷���ֵ��";/* -=notranslate=- */
					ExceptionUtils.wrappBusinessException(message);
					return false;
				}

				// �¼�����
				EventCurrentThread.end();
			} catch (Exception ex) {
				// �쳣�����п��ܵ��򣬽���ȡ�����ֻ��ȡ���㣬�ٴγ����༭ǰ��
				// �ᵼ���ٴε����쳣�򣬵�����ѭ�����˴��ڴ���֮ǰ�Ƚ���������
				CardPanelEventTransformer.this.cardPanel.transferFocus();

				CardPanelEventTransformer.this.exceptionProcess(ex, false);
				return false;
			}
			return editable.booleanValue();
		}

		/**
		 * ����ҳǩ�л�ǰ�¼�
		 */
		@Override
		public boolean beforeTabChanged(BillTabbedPaneTabChangeEvent e) {
			Boolean editable = null;
			try {
				// �¼���ʼ
				EventCurrentThread.start();

				// ҳǩ�л�ǰֹͣ�༭�����⽹�㻹δ��ʧʱ�л�ҳǩȡֵ��������
				CardPanelEventTransformer.this.cardPanel.stopEditing();

				CardBodyBeforeTabChangeEvent event = new CardBodyBeforeTabChangeEvent(cardPanel, e);
				exModel.fireEvent(event);
				editable = event.getReturnValue();

				if (null == editable) {
					String message = "����ҳǩ�л�ǰ�¼�δ���÷���ֵ��";/* -=notranslate=- */
					ExceptionUtils.wrappBusinessException(message);
					return false;
				}

				// �¼�����
				EventCurrentThread.end();
			} catch (Exception ex) {
				CardPanelEventTransformer.this.exceptionProcess(ex, false);
				return false;
			}
			return editable.booleanValue();
		}

		/**
		 * �����иı��¼�
		 */
		@Override
		public void bodyRowChange(BillEditEvent e) {
			try {
				// �¼���ʼ
				EventCurrentThread.start();

				CardBodyRowChangedEvent event = new CardBodyRowChangedEvent(cardPanel, e);
				exModel.fireEvent(event);

				if (null != billEditListener) {
					billEditListener.bodyRowChange(e);
				}

				// �¼�����
				EventCurrentThread.end();
			} catch (Exception ex) {
				CardPanelEventTransformer.this.exceptionProcess(ex, false);
			}
		}

		/**
		 * ��Ƭ�����кϼ��¼�
		 */
		@Override
		public UFDouble calcurateTotal(String key) {
			UFDouble value = null;
			boolean ret = true;
			try {
				// �¼���ʼ
				EventCurrentThread.start();

				CardBodyTotalEvent event = new CardBodyTotalEvent(cardPanel, key, this.getTableCode());
				CardPanelEventTransformer.this.exModel.fireEvent(event);
				value = event.getReturnValue();
				if (null == value || value.getDouble() == 0) {
					BillModel model = cardPanel.getBillModel(this.getTableCode());

					value = new UFDouble(0.0);
					int column = model.getBodyColByKey(key);
					ret = false;
					for (int i = 0; i < model.getRowCount(); i++) {
						Object o = model.getValueAt(i, column);
						if (o == null) {
							continue;
						}
						if (!ret) {
							ret = true;
						}

						value = value.add(CardPanelEventTransformer.this.createUFDouble(o));
					}
					// throw new SystemRuntimeException("��Ƭ�����кϼ��¼�δ���÷���ֵ��");
				}

				// �¼�����
				EventCurrentThread.end();
			} catch (Exception ex) {
				CardPanelEventTransformer.this.exceptionProcess(ex, false);
				return new UFDouble(0);
			}
			return ret ? value : null;
		}

		/**
		 * ��������ǰ�¼�
		 */
		@Override
		public int getSortTypeByBillItemKey(String key) {
			int sortType = -1;
			try {
				// �¼���ʼ
				EventCurrentThread.start();

				CardBodyBeforeSortEvent event = new CardBodyBeforeSortEvent(cardPanel, key,	this.getTableCode());
				CardPanelEventTransformer.this.exModel.fireEvent(event);

				if (null == event.getReturnValue()) {
					String message = "��Ƭ��������ǰ�¼�δ���÷���ֵ��";/* -=notranslate=- */
					ExceptionUtils.wrappBusinessException(message);
				}
				sortType = event.getReturnValue().getType();

				// �¼�����
				EventCurrentThread.end();
			} catch (Exception ex) {
				CardPanelEventTransformer.this.exceptionProcess(ex, false);
				return CardBodyBeforeSortEvent.SortTypeEnum.Default.getType();
			}
			return sortType;
		}

		/**
		 * ��Ƭ�б༭�¼�
		 */
		@Override
		public boolean onEditAction(int action) {
			Boolean editable = null;
			try {
				// �¼���ʼ
				EventCurrentThread.start();

				CardBodyRowEditEvent event = new CardBodyRowEditEvent(cardPanel, BodyRowEditType.valueOf(action));
				CardPanelEventTransformer.this.exModel.fireEvent(event);
				editable = event.getReturnValue();

				if (null == editable) {
					String message = "��Ƭ�б༭�¼�δ���÷���ֵ��";/* -=notranslate=- */
					ExceptionUtils.wrappBusinessException(message);
					return false;
				}

				// �¼�����
				EventCurrentThread.end();
			} catch (Exception ex) {
				CardPanelEventTransformer.this.exceptionProcess(ex, false);
				return false;
			}
			return editable.booleanValue();
		}

		/**
		 * �����Ҽ��˵�������¼�
		 */
		@Override
		public void onMenuItemClick(ActionEvent e) {
			try {
				// �¼���ʼ
				EventCurrentThread.start();

				UIMenuItem item = (UIMenuItem) e.getSource();
				CardBodyMenuActionEvent event = new CardBodyMenuActionEvent(cardPanel, item);
				CardPanelEventTransformer.this.exModel.fireEvent(event);

				if (null != billBodyMenuListener) {
					billBodyMenuListener.onMenuItemClick(e);
				}

				// �¼�����
				EventCurrentThread.end();
			} catch (Exception ex) {
				CardPanelEventTransformer.this.exceptionProcess(ex, false);
			}
		}

		/**
		 * ����ҳǩ�л�ǰ�¼�
		 */
		@Override
		public void stateChanged(ChangeEvent e) {
			try {
				// �¼���ʼ
				EventCurrentThread.start();
				CardBodyBeforeTabChangedEvent event = new CardBodyBeforeTabChangedEvent(cardPanel, e);
				CardPanelEventTransformer.this.exModel.fireEvent(event);

				// �¼�����
				EventCurrentThread.end();
			} catch (Exception ex) {
				CardPanelEventTransformer.this.exceptionProcess(ex, false);
			}
		}

		/**
		 * ����ѡ����״̬�仯�¼�
		 */
		@Override
		public void valueChanged(RowStateChangeEvent e) {
			try {
				// �¼���ʼ
				EventCurrentThread.start();
				CardBodyRowStateChangeEvent event = new CardBodyRowStateChangeEvent(cardPanel, e, this.getTableCode());
				CardPanelEventTransformer.this.exModel.fireEvent(event);

				// �¼�����
				EventCurrentThread.end();
			} catch (Exception ex) {
				CardPanelEventTransformer.this.exceptionProcess(ex, false);
			}
		}

		void setTableCode(String tableCode) {
			this.tableCode = tableCode;
		}

		/**
		 * ��ȡ��ͷ���β�ֶα༭ǰ�¼���ֵ
		 * 
		 * @param key
		 * @return Object
		 */
		private Object getOldValue(String key) {
			return this.oldValueMap.get(key);
		}

		/**
		 * ��ȡ�����ֶα༭ǰ�¼���ֵ
		 * 
		 * @param key
		 * @return Object
		 */
		private Object getOldBodyValue(String key, int row) {
			return this.oldBodyValueMap.get(key + separator + row);
		}

		/**
		 * ��ȡ���ն�Ӧ��pkValue
		 * 
		 * @param billItem
		 * @return String
		 */
		private String getPkValue(BillItem billItem) {
			String pkValue = null;
			UIRefPane refPane = (UIRefPane) billItem.getComponent();
			if (refPane.getRefModel() != null) {
				pkValue = refPane.getRefModel().getPkValue();
			}
			return pkValue;
		}

		private String getTableCode() {
			return this.tableCode;
		}

		/**
		 * �������༭ǰ�¼���ֵ
		 * 
		 * @param row
		 * @param key
		 * @return void
		 */
		private void setBodyOldValue(int row, String key) {
			BillItem billItem = cardPanel.getBodyItem(key);
			// billItem�п���Ϊ�գ������ʼ챨���϶�̬����billItem
			if (null == billItem) {
				return;
			}

			if (this.oldBodyValueMap.containsKey(key + separator + row)
					&& this.oldBodyValueMap.get(key + separator + row) != null
					&& !"".equals(this.oldBodyValueMap.get(key + separator + row))) {
				return;
			}

			if (IBillItem.UFREF == billItem.getDataType()) {
				this.oldBodyValueMap.put(key + separator + row, this.getPkValue(billItem));
			} else {
				this.oldBodyValueMap.put(key + separator + row,	cardPanel.getBodyValueAt(row, key));
			}
		}

		/**
		 * �����ͷ��β�༭ǰ�¼���ֵ
		 * 
		 * @param billItem
		 * @return void
		 */
		private void setHeadTailOldValue(BillItem billItem) {
			String key = billItem.getKey();
			if (this.oldValueMap.containsKey(key) && this.oldValueMap.get(key) != null
					&& !"".equals(this.oldValueMap.get(key))) {
				return;
			}

			if (IBillItem.UFREF == billItem.getDataType()) {
				this.oldValueMap.put(key, this.getPkValue(billItem));
			} else {
				this.oldValueMap.put(key, billItem.getValueObject());
			}
		}
	}

	private class CardBillItemHyperlinkListener implements
			BillItemHyperlinkListener {

		public CardBillItemHyperlinkListener() {
			// TODO Auto-generated constructor stub
		}

		@Override
		public void hyperlink(BillItemHyperlinkEvent event) {
			// �¼���ʼ
			EventCurrentThread.start();
			CardBillItemHyperlinkEvent e = new CardBillItemHyperlinkEvent(event, cardPanel);
			CardPanelEventTransformer.this.exModel.fireEvent(e);
			// �¼�����
			EventCurrentThread.end();
		}

	}

	BillBodyMenuListener billBodyMenuListener;

	BillEditListener billEditListener;

	BillEditListener2 billEditListener2;

	BillTabbedPaneTabChangeListener billTabbedPaneTabChangeListener;

	BillCardPanel cardPanel;

	IAppModelEx exModel;

	private LoginContext context;

	private IExceptionHandler exceptionHandler;

	private CardListener listener = new CardListener();

	public CardListener getListener() {
		return listener;
	}

	public void setListener(CardListener listener) {
		this.listener = listener;
	}

	public CardPanelEventTransformer(BillCardPanel cardPanel, IAppModelEx exModel) {
		super();
		this.cardPanel = cardPanel;
		this.exModel = exModel;

		this.addListener();
	}

	public LoginContext getContext() {
		return this.context;
	}

	public void setBillBodyMenuListener(BillBodyMenuListener billBodyMenuListener) {
		this.billBodyMenuListener = billBodyMenuListener;
	}

	public void setBillEditListener2(BillEditListener2 billEditListener2) {
		this.billEditListener2 = billEditListener2;
	}

	public void setBillTabbedPaneTabChangeListener(BillTabbedPaneTabChangeListener billTabbedPaneTabChangeListener) {
		this.billTabbedPaneTabChangeListener = billTabbedPaneTabChangeListener;
	}

	public void setContext(LoginContext context) {
		this.context = context;
	}

	public void setOrgBillEditListener(BillEditListener orgBillEditListener) {
		this.billEditListener = orgBillEditListener;
	}

	UFDouble createUFDouble(Object o) {
		if (o instanceof UFDouble) {
			return (UFDouble) o;
		}
		String v = ObjectUtils.toString(o, "0");
		return new UFDouble(v);
	}

	/**
	 * ֻ�е��û����յ���ʱ�׳���ʾ���쳣������������쳣�׳�
	 * 
	 * @param ex
	 * @param showErrorDlg
	 *            Ϊtrueʱ������ʾ��Ϊfalseʱ��������ʾ��
	 *            ��ԭ�򣺱༭ǰ�¼�����󵯳���ʾ����ȷ���󽹵��п����ٴλص��༭�򣬷�����ѭ����Ŀǰ�༭ǰ�¼�����Ϊtrue��
	 * @return void
	 */
	void exceptionProcess(Exception ex, boolean showErrorDlg) {
		EventCurrentThread.end();
		// �����ǰ�߳���Ϊ�գ����׳��쳣�����������¼�����ʽ����ʱֻ���������ô��׳��쳣
		if (EventCurrentThread.isEmpty()) {
			this.getExceptionHandler(showErrorDlg).handlerExeption(ex);
		} else {
			ExceptionUtils.wrappException(ex);
		}
	}

	private void addListener() {
		this.cardPanel.getBillData().addHyperlinkListener(new CardBillItemHyperlinkListener());
		this.cardPanel.addBillEditListenerHeadTail(this.listener);
		this.cardPanel.setBillBeforeEditListenerHeadTail(this.listener);
		this.cardPanel.addTabbedPaneTabChangeListener(this.listener,IBillItem.BODY);
		this.cardPanel.addTabbedPaneTabChangeListener2(this.listener,IBillItem.BODY);

		this.cardPanel.getBodyTabbedPane().addChangeListener(this.listener);

		String[] tableCodes = this.cardPanel.getBillData().getBodyTableCodes();
		if (tableCodes == null || tableCodes.length == 0) {
			return;
		}
		for (String tableCode : tableCodes) {
			this.cardPanel.addEditListener(tableCode, this.listener);
			this.cardPanel.addBodyEditListener2(tableCode, this.listener);
			// cardPanel.addBodyMenuListener(tableCode, listener);
			this.cardPanel.addActionListener(tableCode, this.listener);

			BillModel billModel = this.cardPanel.getBillModel(tableCode);
			CardListener newListener = new CardListener();
			newListener.setTableCode(tableCode);

			billModel.addSortListener(newListener);
			billModel.addTotalListener(newListener);
			billModel.setSortPrepareListener(newListener);
			billModel.addRowStateChangeEventListener(newListener);
		}
	}

	private IExceptionHandler getExceptionHandler(boolean showErrorDlg) {
		if (this.exceptionHandler == null) {
			this.exceptionHandler = new PubExceptionHanler(this.context,
					showErrorDlg);
		}
		return this.exceptionHandler;
	}
}
