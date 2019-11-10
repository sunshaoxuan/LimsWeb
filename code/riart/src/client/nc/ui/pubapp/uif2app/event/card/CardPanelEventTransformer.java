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

//20120222 最新的UE规则：所有非选择型提示、错误、警告信息均显示在错误区，不能对话框显示，修改下面的调用第二个参数为false
public class CardPanelEventTransformer {
	public class CardListener implements BillEditListener, BillEditListener2,
			BillCardBeforeEditListener, BillTabbedPaneTabChangeListener,
			BillTabbedPaneTabChangeListener2, BillSortListener,
			BillBodyMenuListener, BillActionListener, BillTotalListener,
			IBillModelSortPrepareListener, ChangeListener,
			IBillModelRowStateChangeEventListener {

		// 存放编辑前事件的值，传递给编辑后事件使用(存放表头表尾字段值，其key为itemkey)
		private Map<String, Object> oldValueMap = new HashMap<String, Object>();

		public Map<String, Object> getOldValueMap() {
			return oldValueMap;
		}

		// 存放编辑前事件的值，传递给编辑后事件使用(存放表体字段值，其key为itemkey+"_"+row)
		private Map<String, Object> oldBodyValueMap = new HashMap<String, Object>();

		private String separator = "_";// 分隔符

		private String tableCode;

		/**
		 * 表体右键菜单点击后事件
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				// 事件开始
				EventCurrentThread.start();

				UIMenuItem item = (UIMenuItem) e.getSource();
				CardBodyMenuActionEvent event = new CardBodyMenuActionEvent(cardPanel, item);
				CardPanelEventTransformer.this.exModel.fireEvent(event);

				if (null != billBodyMenuListener) {
					billBodyMenuListener.actionPerformed(e);
				}

				// 事件结束
				EventCurrentThread.end();
			} catch (Exception ex) {
				CardPanelEventTransformer.this.exceptionProcess(ex, false);
			}
		}

		/**
		 * 编辑后事件
		 */
		@Override
		public void afterEdit(BillEditEvent e) {
			try {
				// 事件开始
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


				// 加载元数据相关项
				BillModel bm = CardPanelEventTransformer.this.cardPanel.getBillModel();
				// 是否正在进行Excel导入
				boolean isExcelImprting = cardPanel.getBillData().isImporting();

				// 如果未选择表体，rows为空，不会执行加载关联项，关联参照数据设置不上。2012-03-06 modified
				// int[] rows =
				// CardPanelEventTransformer.this.cardPanel.getBillTable()
				// .getSelectedRows();
				// 使批拖拽时只在最后一次编辑后事件中加载相关项
				// 执行显示公式是因为有两个特殊的联系人和地址参照，只会返回PK，需要执行其显示公式才能显示名称
				// if (null != bm && rows != null && rows.length > 0 &&
				// (rows[rows.length-1] == e.getRow()||.length==1)) {
				
				if (null != bm && !isExcelImprting) {
					bm.loadLoadRelationItemValue();
					bm.execLoadFormula();
				}

				// 事件结束
				EventCurrentThread.end();
			} catch (Exception ex) {
				CardPanelEventTransformer.this.exceptionProcess(ex, false);
			}
			finally {
				// 事件结束后清空
				if (IBillItem.HEAD == e.getPos() || IBillItem.TAIL == e.getPos()) {
					this.oldValueMap.put(e.getKey(), null);
				} else if (IBillItem.BODY == e.getPos()) {
					this.oldBodyValueMap.put(e.getKey() + separator + e.getRow(), null);
				}
			}
		}

		/**
		 * 平台提供了一个Alt+Shift组合键拖拽功能，这个功能可以向下复制某个字段， 当拖拽完成时，每一行复制出的字段会依次触发编辑后事件。
		 * 当是由拖拽触发的编辑后时，可以判断编辑后事件中的状态，如果是 BATCHCOPYEND 状态， 则进行一次业务处理，而不用重复调用业务处理
		 * 
		 * @param event
		 */
		private void processBatchAfterEdit(CardBodyAfterEditEvent event) {
			int[] rows = event.getBillCardPanel().getBillTable().getSelectedRows();

			if (rows == null || rows.length <= 1) {
				event.setAfterEditEventState(CardBodyAfterEditEvent.NOTBATCHCOPY);
			} else if (rows.length == 2) {
				// 选中2行批修改时，因只会进入一次aftereditor方法，所以直接设置状态为BATCHCOPYEND
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
		 * 排序后事件
		 */
		@Override
		public void afterSort(String key) {
			try {
				// 事件开始
				EventCurrentThread.start();

				CardBodyAfterSortEvent event = new CardBodyAfterSortEvent(cardPanel, key, this.getTableCode());
				CardPanelEventTransformer.this.exModel.fireEvent(event);

				// 事件结束
				EventCurrentThread.end();
			} catch (Exception ex) {
				CardPanelEventTransformer.this.exceptionProcess(ex, false);
			}
		}

		/**
		 * 表体页签切换后事件
		 */
		@Override
		public void afterTabChanged(BillTabbedPaneTabChangeEvent e) {
			try {
				// 事件开始
				EventCurrentThread.start();

				CardBodyTabChangedEvent event = new CardBodyTabChangedEvent(cardPanel, e);
				exModel.fireEvent(event);

				BillTabbedPaneTabChangeListener tabChangeListener = billTabbedPaneTabChangeListener;
				if (null != tabChangeListener) {
					tabChangeListener.afterTabChanged(e);
				}

				// 事件结束
				EventCurrentThread.end();
			} catch (Exception ex) {
				CardPanelEventTransformer.this.exceptionProcess(ex, false);
			}
		}

		/**
		 * 表体编辑前事件
		 */
		@Override
		public boolean beforeEdit(BillEditEvent e) {
			Boolean editable = null;
			CardBodyBeforeBatchEditEvent batchEvent = null;
			CardBodyBeforeEditEvent event = null;
			int[] rows = cardPanel.getBillTable().getSelectedRows();
			try {
				// 事件开始
				EventCurrentThread.start();

				if (rows.length > 1 && e.getRow() != rows[0]) {
					batchEvent = new CardBodyBeforeBatchEditEvent(cardPanel, e, rows[0]);
					exModel.fireEvent(batchEvent);
					editable = batchEvent.getReturnValue();
				} else {
					event = new CardBodyBeforeEditEvent(cardPanel, e);
					//传入点击次数
					event.setHitCount(e.getClickCount());
					exModel.fireEvent(event);
					editable = event.getReturnValue();
				}

				// 将编辑前的值保存下来，供编辑后事件调用
				this.setBodyOldValue(e.getRow(), e.getKey());

				if (null == editable) {
					String message = "表体编辑前事件未设置返回值！";/* -=notranslate=- */
					ExceptionUtils.wrappBusinessException(message);
					return false;
				}

				if (null != CardPanelEventTransformer.this.billEditListener2) {
					editable = Boolean.valueOf(editable.booleanValue() && billEditListener2.beforeEdit(e));
				}

				// 事件结束
				EventCurrentThread.end();
			} catch (Exception ex) {
				CardPanelEventTransformer.this.exceptionProcess(ex, false);
				return false;
			}
			return editable.booleanValue();
		}

		/**
		 * 表头/表尾编辑前事件
		 */
		@Override
		public boolean beforeEdit(BillItemEvent e) {
			Boolean editable = null;
			try {
				// 事件开始
				EventCurrentThread.start();

				CardHeadTailBeforeEditEvent event = new CardHeadTailBeforeEditEvent(cardPanel, e);
				exModel.fireEvent(event);
				editable = event.getReturnValue();

				// 将编辑前的值保存下来，供编辑后事件调用
				this.setHeadTailOldValue(e.getItem());

				if (null == editable) {
					String message = "表头/表尾编辑前事件未设置返回值！";/* -=notranslate=- */
					ExceptionUtils.wrappBusinessException(message);
					return false;
				}

				// 事件结束
				EventCurrentThread.end();
			} catch (Exception ex) {
				// 异常处理有可能弹框，将框取消后，又会获取焦点，再次出发编辑前，
				// 会导致再次弹出异常框，导致死循环，此处在处理之前先将焦点移走
				CardPanelEventTransformer.this.cardPanel.transferFocus();

				CardPanelEventTransformer.this.exceptionProcess(ex, false);
				return false;
			}
			return editable.booleanValue();
		}

		/**
		 * 表体页签切换前事件
		 */
		@Override
		public boolean beforeTabChanged(BillTabbedPaneTabChangeEvent e) {
			Boolean editable = null;
			try {
				// 事件开始
				EventCurrentThread.start();

				// 页签切换前停止编辑，避免焦点还未丢失时切换页签取值错误的情况
				CardPanelEventTransformer.this.cardPanel.stopEditing();

				CardBodyBeforeTabChangeEvent event = new CardBodyBeforeTabChangeEvent(cardPanel, e);
				exModel.fireEvent(event);
				editable = event.getReturnValue();

				if (null == editable) {
					String message = "表体页签切换前事件未设置返回值！";/* -=notranslate=- */
					ExceptionUtils.wrappBusinessException(message);
					return false;
				}

				// 事件结束
				EventCurrentThread.end();
			} catch (Exception ex) {
				CardPanelEventTransformer.this.exceptionProcess(ex, false);
				return false;
			}
			return editable.booleanValue();
		}

		/**
		 * 表体行改变事件
		 */
		@Override
		public void bodyRowChange(BillEditEvent e) {
			try {
				// 事件开始
				EventCurrentThread.start();

				CardBodyRowChangedEvent event = new CardBodyRowChangedEvent(cardPanel, e);
				exModel.fireEvent(event);

				if (null != billEditListener) {
					billEditListener.bodyRowChange(e);
				}

				// 事件结束
				EventCurrentThread.end();
			} catch (Exception ex) {
				CardPanelEventTransformer.this.exceptionProcess(ex, false);
			}
		}

		/**
		 * 卡片表体行合计事件
		 */
		@Override
		public UFDouble calcurateTotal(String key) {
			UFDouble value = null;
			boolean ret = true;
			try {
				// 事件开始
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
					// throw new SystemRuntimeException("卡片表体行合计事件未设置返回值！");
				}

				// 事件结束
				EventCurrentThread.end();
			} catch (Exception ex) {
				CardPanelEventTransformer.this.exceptionProcess(ex, false);
				return new UFDouble(0);
			}
			return ret ? value : null;
		}

		/**
		 * 表体排序前事件
		 */
		@Override
		public int getSortTypeByBillItemKey(String key) {
			int sortType = -1;
			try {
				// 事件开始
				EventCurrentThread.start();

				CardBodyBeforeSortEvent event = new CardBodyBeforeSortEvent(cardPanel, key,	this.getTableCode());
				CardPanelEventTransformer.this.exModel.fireEvent(event);

				if (null == event.getReturnValue()) {
					String message = "卡片表体排序前事件未设置返回值！";/* -=notranslate=- */
					ExceptionUtils.wrappBusinessException(message);
				}
				sortType = event.getReturnValue().getType();

				// 事件结束
				EventCurrentThread.end();
			} catch (Exception ex) {
				CardPanelEventTransformer.this.exceptionProcess(ex, false);
				return CardBodyBeforeSortEvent.SortTypeEnum.Default.getType();
			}
			return sortType;
		}

		/**
		 * 卡片行编辑事件
		 */
		@Override
		public boolean onEditAction(int action) {
			Boolean editable = null;
			try {
				// 事件开始
				EventCurrentThread.start();

				CardBodyRowEditEvent event = new CardBodyRowEditEvent(cardPanel, BodyRowEditType.valueOf(action));
				CardPanelEventTransformer.this.exModel.fireEvent(event);
				editable = event.getReturnValue();

				if (null == editable) {
					String message = "卡片行编辑事件未设置返回值！";/* -=notranslate=- */
					ExceptionUtils.wrappBusinessException(message);
					return false;
				}

				// 事件结束
				EventCurrentThread.end();
			} catch (Exception ex) {
				CardPanelEventTransformer.this.exceptionProcess(ex, false);
				return false;
			}
			return editable.booleanValue();
		}

		/**
		 * 表体右键菜单点击后事件
		 */
		@Override
		public void onMenuItemClick(ActionEvent e) {
			try {
				// 事件开始
				EventCurrentThread.start();

				UIMenuItem item = (UIMenuItem) e.getSource();
				CardBodyMenuActionEvent event = new CardBodyMenuActionEvent(cardPanel, item);
				CardPanelEventTransformer.this.exModel.fireEvent(event);

				if (null != billBodyMenuListener) {
					billBodyMenuListener.onMenuItemClick(e);
				}

				// 事件结束
				EventCurrentThread.end();
			} catch (Exception ex) {
				CardPanelEventTransformer.this.exceptionProcess(ex, false);
			}
		}

		/**
		 * 表体页签切换前事件
		 */
		@Override
		public void stateChanged(ChangeEvent e) {
			try {
				// 事件开始
				EventCurrentThread.start();
				CardBodyBeforeTabChangedEvent event = new CardBodyBeforeTabChangedEvent(cardPanel, e);
				CardPanelEventTransformer.this.exModel.fireEvent(event);

				// 事件结束
				EventCurrentThread.end();
			} catch (Exception ex) {
				CardPanelEventTransformer.this.exceptionProcess(ex, false);
			}
		}

		/**
		 * 表体选择框的状态变化事件
		 */
		@Override
		public void valueChanged(RowStateChangeEvent e) {
			try {
				// 事件开始
				EventCurrentThread.start();
				CardBodyRowStateChangeEvent event = new CardBodyRowStateChangeEvent(cardPanel, e, this.getTableCode());
				CardPanelEventTransformer.this.exModel.fireEvent(event);

				// 事件结束
				EventCurrentThread.end();
			} catch (Exception ex) {
				CardPanelEventTransformer.this.exceptionProcess(ex, false);
			}
		}

		void setTableCode(String tableCode) {
			this.tableCode = tableCode;
		}

		/**
		 * 获取表头或表尾字段编辑前事件的值
		 * 
		 * @param key
		 * @return Object
		 */
		private Object getOldValue(String key) {
			return this.oldValueMap.get(key);
		}

		/**
		 * 获取表体字段编辑前事件的值
		 * 
		 * @param key
		 * @return Object
		 */
		private Object getOldBodyValue(String key, int row) {
			return this.oldBodyValueMap.get(key + separator + row);
		}

		/**
		 * 获取参照对应的pkValue
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
		 * 保存表体编辑前事件的值
		 * 
		 * @param row
		 * @param key
		 * @return void
		 */
		private void setBodyOldValue(int row, String key) {
			BillItem billItem = cardPanel.getBodyItem(key);
			// billItem有可能为空，例如质检报告上动态创建billItem
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
		 * 保存表头表尾编辑前事件的值
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
			// 事件开始
			EventCurrentThread.start();
			CardBillItemHyperlinkEvent e = new CardBillItemHyperlinkEvent(event, cardPanel);
			CardPanelEventTransformer.this.exModel.fireEvent(e);
			// 事件结束
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
	 * 只有当用户最终调用时抛出提示吃异常，如果不是则将异常抛出
	 * 
	 * @param ex
	 * @param showErrorDlg
	 *            为true时弹出提示框，为false时不弹出提示框
	 *            （原因：编辑前事件报错后弹出提示，点确定后焦点有可能再次回到编辑框，发生死循环。目前编辑前事件仍设为true）
	 * @return void
	 */
	void exceptionProcess(Exception ex, boolean showErrorDlg) {
		EventCurrentThread.end();
		// 如果当前线程中为空，则抛出异常，用以区分事件的链式调用时只在最外层调用处抛出异常
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
