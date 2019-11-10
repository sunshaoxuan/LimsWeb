package nc.ui.pub.bill;

import java.awt.AWTKeyStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EventObject;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.CellEditor;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.JViewport;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.Popup;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicScrollPaneUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import nc.bs.logging.Logger;
import nc.ui.bill.depend.IPageNavigationBar;
import nc.ui.bill.depend.PageNavigationBar;
import nc.ui.pub.beans.ToolTipOnTheFly;
import nc.ui.pub.beans.TooltipUtil;
import nc.ui.pub.beans.UICheckBox;
import nc.ui.pub.beans.UIComboBox;
import nc.ui.pub.beans.UIFractionTextField;
import nc.ui.pub.beans.UIMultiLangCombox;
import nc.ui.pub.beans.UIPasswordField;
import nc.ui.pub.beans.UIPopupMenu;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.beans.UIScrollPane;
import nc.ui.pub.beans.UITable;
import nc.ui.pub.beans.action.TableNavigationalAction;
import nc.ui.pub.beans.bill.IBillScrollPane;
import nc.ui.pub.beans.bill.IBillTable;
import nc.ui.pub.beans.calculator.UICalculator;
import nc.ui.pub.beans.constenum.DefaultConstEnum;
import nc.ui.pub.beans.constenum.IConstEnum;
import nc.ui.pub.beans.table.ColoredCell;
import nc.ui.pub.beans.table.UIVarLenTextField;
import nc.ui.pub.bill.action.BillCardUISetAction;
import nc.ui.pub.bill.action.BillListUISetAction;
import nc.ui.pub.bill.table.BillTableBooleanCellRenderer;
import nc.ui.pub.bill.table.BillTableLockEvent;
import nc.ui.pub.bill.table.BillTableMultiLangComboxCellRenderer;
import nc.ui.pub.bill.table.BillTableTextAreaRenderer;
import nc.ui.pub.bill.table.DefaultTableHeaderCellRenderer;
import nc.ui.pub.bill.table.IBillTableHeaderCellRender;
import nc.ui.pub.bill.table.IBillTableLockListener;
import nc.ui.pub.bill.tableaction.BillTableActionManager;
import nc.ui.pub.bill.tableaction.LocateCellAction;
import nc.ui.pub.bill.tableaction.TableNavigationSwitcherAction;
import nc.ui.pub.bill.treetable.BillTreeTableTools;
import nc.ui.pub.bill.treetable.IBillTreeCreateStrategy;
import nc.ui.pub.bill.treetable.IBillTreeTableModel;
import nc.ui.pub.bill.treetable.ITableTreeFactory;
import nc.ui.pub.bill.treetable.ITableTreeListener;
import nc.ui.pub.bill.treetable.TableTreeFactory;
import nc.ui.pub.bill.treetable.TreeTableFactory;
import nc.ui.pub.style.Style;
import nc.uitheme.ui.ThemeResourceCenter;
import nc.vo.bill.pub.BillUtil;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.bill.BillRendererVO;
import nc.vo.pub.bill.BillTabVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.querytemplate.TreeUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * 具有行号列,合计行功能的ScrollPane. 创建日期:(01-2-22 14:20:41) 作者:宋杰
 */
@SuppressWarnings("serial")
public class BillScrollPane extends UIScrollPane implements
		BillScrollPaneConstants, IPageNavigationBar, IBillScrollPane,
		BillSortListener2 {

	public static HashSet<AWTKeyStroke> focusTraversalKeyHashSet = new HashSet<AWTKeyStroke>();
	// 动作
	public final static int ADDLINE = 0; // 增行

	public final static int INSERTLINE = 1; // 插入行

	public final static int DELLINE = 2; // 删行

	public final static int COPYLINE = 3; // 复制行

	public final static int PASTELINE = 4; // 粘贴行

	public final static int PASTELINETOTAIL = 5; // 粘贴行

	public final static int LOCK = 6; // 锁定

	public final static int ALLSELECT = 7; // 全选

	public final static int CANCELALLSELECT = 8; // 全消

	public final static int AUTOADDLINE = 1234; // 自动增行

	// 标志是否为卡片模板所用
	private boolean m_bIsCard = false;

	protected JViewport pageNavigationBarVP;

	protected JViewport rowNO; // 行号

	protected JViewport rowNOHeader; // 行号头部

	protected JViewport fixRow; // 固定行

	protected JViewport fixRowNO;

	protected JViewport fixRowLock;

	protected JViewport userRow;

	protected JViewport userRowNO;

	protected JViewport userRowLock;

	protected BillTable mainTable = null; // 主表

	protected BillTable fixRowTable = null; // 固定行表

	private BillTable fixRowLockTable = null; // fixRow lockTable total

	private BillTable fixRowNOTable = null; // user fix row header table

	private BillTable userRowTable = null; // user fix row table

	private BillTable userRowLockTable = null; // fixRow lockTable user

	private BillTable userRowNOTable = null;

	protected BillTable lockTable = null; // 固定列表

	protected BillTable rowNOTable = null; // 行号

	protected BillModel m_bmModel = null; // 表模式

	protected boolean m_bTotalRow = true; // 合计表

	protected boolean m_bRowNO = false; // 行号

	protected boolean m_bLockCol = false; // 锁定表

	private int max_lockCount = 5; // default

	private int m_iLockCol = -1; // 已锁定列

	protected boolean bBodySelectd = true; // 表选择

	protected boolean bFixColSelectd = true; // 固定列选择

	protected boolean bRowNOSelectd = true; // 行号选择

	private int oldrow = -1; // 行号

	private BillEditListener el = null; // 表格编辑监听

	private BillTableMouseListener ml = null; // 表格鼠标监听

	private BillEditListener2 el2 = null; // 表格编辑监听扩展

	private IBillTableBatchCopyListener batchCopyListner = null;

	private BillComponentMouseListener ml2 = null; // 表格鼠标监听,得到所属BillItem

	// private MouseListener sortListener = null;

	private boolean m_bAutoAddLine = false; // 自动增行

	private boolean m_bKeyAutoAddLine = true; // 键盘自动增行

	// private boolean m_bNegativeSign = true; //负数是否显示符号
	// private boolean m_bShowRed = false; //负数是否显示红字
	// private boolean m_bShowThMark = false; //显示千分位
	// private boolean m_bShowZeroLikeNull = false; //将零显示为空串
	private BillRendererVO m_RendererVO = new BillRendererVO();

	private Hashtable<String, TableColumn> hHideCol = null; // 隐藏列

	private Hashtable<String, TableColumn> hShowCol = null; // 显示列

	// tableCode
	private String tableCode = null;

	private String tableName = null;

	// // 右键菜单监听
	// private BillBodyMenuListener lbm = null;

	// //编辑监听
	// public BillEditListener lbe;
	// public BillEditListener2 lbe2;
	// private MenuItemListener menuItemListener = null;

	// 动作监听
	private BillActionListener lba;

	// 是否显示右键菜单
	private boolean bBodyMenuShow = false;

	// show body tableHeader popupMenu
	private boolean bBodyHeaderMenuShow = true;

	// 单据体菜单
	private UIPopupMenu pmBodyHead = null;

	private UIPopupMenu pmBody = null;

	private PageNavigationBar pageNavigationBar = null;

	private boolean m_bShowPageNavigationBar = false;

	// BillCardPanel or BillListPanel
	private Component billParent = null;

	private boolean autoCallAddLineWhenFocusGainedAndZeroRows = true;

	private IBillTableLockListener billTableLockListener = null;

	private BillTableSelectionChangeListener rowSelectionChangeListener;

	private BillTableSelectionChangeListener colSelectionChangeListener;

	// only for auto addline temp control,if not add this flag,when auto
	// addline,changeselection will set pro column one more
	// private boolean changeSelectionWhenAddLine = true;
	
	private boolean isListTableItemAdjustButtonShow = false;

	// only for auto addline temp control,if not add this flag,when auto
	// addline,changeselection will set pro column one more
	// private boolean changeSelectionWhenAddLine = true;

	public boolean isListTableItemAdjustButtonShow() {
		return isListTableItemAdjustButtonShow;
	}

	public void setListTableItemAdjustButtonShow(
			boolean isListTableItemAdjustButtonShow) {
		this.isListTableItemAdjustButtonShow = isListTableItemAdjustButtonShow;
	}

	private class ColumChangeListener implements PropertyChangeListener {
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			if (evt.getPropertyName().equals("width")) {
				TableColumn tcl = (TableColumn) evt.getSource();
				BillItem item = getTableModel().getBodyItems()[tcl
						.getModelIndex()];

				if (item.getUiSet() == null) {
					item.setUiSet(new BillItemUISet(item));
				}
				item.getUiSet().setWidth((Integer) evt.getNewValue());

			}
		}
	}

	private ColumChangeListener ccl = new ColumChangeListener();

	private boolean userFixRowVisible = false;

	private class BillScrollPanelUI extends BasicScrollPaneUI {
		protected void syncScrollPaneWithViewport() {
			JViewport viewport = scrollpane.getViewport();
			JScrollBar vsb = scrollpane.getVerticalScrollBar();
			JScrollBar hsb = scrollpane.getHorizontalScrollBar();
			JViewport rowHead = scrollpane.getRowHeader();
			JViewport colHead = scrollpane.getColumnHeader();
			boolean ltr = scrollpane.getComponentOrientation().isLeftToRight();

			if (viewport != null) {
				Dimension extentSize = viewport.getExtentSize();
				Dimension viewSize = viewport.getViewSize();
				Point viewPosition = viewport.getViewPosition();

				if (vsb != null) {
					int extent = extentSize.height;
					int max = viewSize.height;
					int value = Math.max(0, Math.min(viewPosition.y, max
							- extent));
					vsb.setValues(value, extent, 0, max);
				}

				if (hsb != null) {
					int extent = extentSize.width;
					int max = viewSize.width;
					int value;

					if (ltr) {
						value = Math.max(0, Math.min(viewPosition.x, max
								- extent));
					} else {
						int currentValue = hsb.getValue();

						/*
						 * Use a particular formula to calculate "value" until
						 * effective x coordinate is calculated.
						 */
						if (((max - currentValue) == viewPosition.x)) {
							value = Math.max(0, Math.min(max - extent,
									currentValue));
							/*
							 * After "extent" is set, turn setValueCalled flag
							 * off.
							 */
						} else {
							if (extent > max) {
								viewPosition.x = max - extent;
								viewport.setViewPosition(viewPosition);
								value = 0;
							} else {
								/*
								 * The following line can't handle a small value
								 * of viewPosition.x like Integer.MIN_VALUE
								 * correctly because (max - extent -
								 * viewPositoiin.x) causes an overflow. As a
								 * result, value becomes zero. (e.g.
								 * setViewPosition(Integer.MAX_VALUE, ...) in a
								 * user program causes a overflow. Its expected
								 * value is (max - extent).) However, this seems
								 * a trivial bug and adding a fix makes this
								 * often-called method slow, so I'll leave it
								 * until someone claims.
								 */
								value = Math.max(0, Math.min(max - extent, max
										- extent - viewPosition.x));
							}
						}
					}
					hsb.setValues(value, extent, 0, max);
				}

				if (rowHead != null) {
					Point p = rowHead.getViewPosition();
					p.y = viewport.getViewPosition().y;
					rowHead.setViewPosition(p);
				}

				if (colHead != null) {
					Point p = colHead.getViewPosition();
					if (ltr) {
						p.x = viewport.getViewPosition().x;
					} else {
						p.x = Math.max(0, viewport.getViewPosition().x);
					}
					p.y = 0;
					colHead.setViewPosition(p);
				}
			}
		}

	}

	/**
	 * 主表行选择监听. 创建日期:(01-2-21 11:20:47)
	 */
	int[] oldrows = null;

	public class RowSelectListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent e) {
			if (!e.getValueIsAdjusting()) {
				if (getRowSelectionChangeListener() != null) {
					BillTableSelectionEvent be = new BillTableSelectionEvent(
							BillScrollPane.this);
					be.setSelectIndex(((ListSelectionModel) e.getSource())
							.getAnchorSelectionIndex());
					getRowSelectionChangeListener().selectionChanged(be);
				}
			}
			// if (el != null) {
			//	
			// int row = getTable().getSelectedRow();
			// int oldviewrow = -1;
			// if (oldrow != -1)
			// oldviewrow = oldrow;
			// // if (oldviewrow != row) {
			// //为了支持多选时发事件，暂时注释掉判断，目前传入BillEditEvent的参数是有问题的。
			// BillEditEvent ev = new BillEditEvent(getTable(), oldviewrow,
			// row);
			//
			// int column = getTable().getSelectedColumn();
			// if (column >= 0) {
			// BillItem item = getTableModel().getBodyItems()[column];
			// ev.setKey(item.getKey());
			// }
			// el.bodyRowChange(ev);
			// // }
			// oldrow = row;
			// }

			if (el != null) {

				int row = getTable().getSelectedRow();
				int oldviewrow = -1;
				if (oldrow != -1)
					oldviewrow = oldrow;
				// if (oldviewrow != row) {
				// //为了支持多选时发事件，暂时注释掉判断，目前传入BillEditEvent的参数是有问题的。
				int[] rows = getTable().getSelectedRows();
				if (!isEqual(oldrows, rows)) {
					BillEditEvent ev = new BillEditEvent(getTable(),
							oldviewrow, row);
					ev.setOldrows(oldrows);
					ev.setRows(rows);

					int column = getTable().getSelectedColumn();
					if (column >= 0) {
						BillItem item = getTableModel().getBodyItems()[column];
						ev.setKey(item.getKey());
					}
					el.bodyRowChange(ev);
				}
				oldrow = row;
				oldrows = rows;
			}

		}
	}

	private boolean isEqual(int[] oldRows, int[] rows) {

		if (ArrayUtils.isEmpty(oldRows) && ArrayUtils.isEmpty(rows)) {
			return true;
		} else if (!ArrayUtils.isEmpty(oldRows) && !ArrayUtils.isEmpty(rows)) {

			Arrays.sort(oldRows);
			Arrays.sort(rows);
			return Arrays.equals(oldRows, rows);
		}

		return false;

	}

	/**
	 * 主表行选择监听. 创建日期:(01-2-21 11:20:47)
	 */
	public class ColSelectListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent e) {
			if (!e.getValueIsAdjusting()) {
				if (getColSelectionChangeListener() != null) {
					BillTableSelectionEvent be = new BillTableSelectionEvent(
							BillScrollPane.this);
					be.setSelectIndex(((ListSelectionModel) e.getSource())
							.getAnchorSelectionIndex());
					be.setMode(BillTableSelectionEvent.COLUMN);
					getColSelectionChangeListener().selectionChanged(be);
				}
			}
		}
	}

	// private class MouseDraggedSelectHandler extends MouseMotionAdapter {
	//    	
	// int oldrow = -1;
	//		
	// public void mouseDragged(MouseEvent e) {
	//			
	// if(getTable().getMouseSelect() != null && !getTable().isEditing() &&
	// e.isControlDown()){
	// if(getTable().getMouseSelect().isSupportCtrlSelect()){
	// Point p = e.getPoint();
	// int row = getTable().rowAtPoint(p);
	// if(row != oldrow)
	// selectTableRow(row,row,true);
	//		            
	// oldrow = row;
	// }
	// }
	// }
	// }

	/**
	 * 可触发编辑事件表格. 创建日期:(01-2-21 11:20:47)
	 */
	@SuppressWarnings("serial")
	public class BillTable extends UITable implements IBillTable {
		// public class BillTable extends UISpanTable implements IBillTable {
		TableCellEditor editor;

		IBillTableMouseSelectControl mouseSelect = null;

		private class selectAction extends AbstractAction {

			public void actionPerformed(ActionEvent e) {
				setRowSelectState(getSelectedRow(), true);
			}

		}

		private class pasteAction extends AbstractAction {

			public void actionPerformed(ActionEvent e) {
				pasteValuesByClipboard();
			}

		}
		private class calculateAction extends AbstractAction {

			public void actionPerformed(ActionEvent e) {
				showCalculator();
			}

		}
		
		public BillTable() {
			this(null);
		}

		public BillTable(javax.swing.table.TableModel dm) {
			super(dm);
			billRegisterKey();

			// getTableHeader().setBackground(new Color(0XECF3FC));
			// setSelectionBackground(new Color(0XFBF2AA));
			// setSelectionBackground(new Color(0XFFFFCC));
			//
			// setGridColor(new Color(0X9EB6CE));

			setFocusTraversalKeysEnabled(false);
			// // 关闭。否则会吃掉TabbedPane Ctrl+Tab快捷键
			setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS,
					focusTraversalKeyHashSet);
			setFocusTraversalKeys(KeyboardFocusManager.UP_CYCLE_TRAVERSAL_KEYS,
					focusTraversalKeyHashSet);
		}

		private void billRegisterKey() {
			setEnterKeyNavigationHorizontal(true);
			TableNavigationalAction navigationalAction = getEnterKeyNavigationalAction();
			if (navigationalAction != null) {
				navigationalAction.setFindEditCell(true);
				navigationalAction.setBeforeNavigateAction(new AddRowAction());
			}

			String key;
			InputMap map = getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
			ActionMap am = getActionMap();

			key = TableNavigationalAction.SELECT_NEXT_ROW_CELL;
			KeyStroke ks = KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false);
			map.put(ks, key);
			navigationalAction = TableNavigationalAction.createAction(key);
			navigationalAction.setBeforeNavigateAction(new AddRowAction(
					AddRowAction.ROW));
			am.put(key, navigationalAction);

			key = "paste";
			ks = KeyStroke.getKeyStroke(KeyEvent.VK_V,
					InputEvent.CTRL_DOWN_MASK, false);
			map.put(ks, key);

			am.put(key, new pasteAction());
			
			key = "_f9_showcalculator";
			ks = KeyStroke.getKeyStroke(KeyEvent.VK_F9, 0, false);
			map.put(ks, key);
			am.put(key, new calculateAction());

		}

		public void editingStopped(ChangeEvent e) {

			int row = editingRow;
			int col = editingColumn;

			if (row < 0 || col < 0)
				return;

			BillItem item = getTableModel().getBodyItems()[convertColumnIndexToModel(col)];
			TableCellEditor editor = getCellEditor();
			Object value = null;
			Object oldValue = getValueAt(row, col);
			// 设置固定列
			if (this.equals(getTable()))
				getTableModel().setFixCol(m_iLockCol);
			else
				getTableModel().setFixCol(-1);
			// boolean needTransFocus = false;
			// 结束编辑状态
			if (editor != null) {
				removeEditor();
				value = editor.getCellEditorValue();
			}

			// 有效性检测
			boolean bValueChanged = false;
			boolean isMultiItemSelected = false;
			if (editor != null && editor instanceof IBillCellEditer) {
				isMultiItemSelected = ((IBillCellEditer) editor)
						.isMultiItemSelected();
				if (!isMultiItemSelected)
					bValueChanged = ((IBillCellEditer) editor).isValueChanged();
			}
			// do value change action
			if (bValueChanged) {
				if (value != null && value.toString() != null) {
					String sv = value.toString().trim();
					if ("".equals(sv))
						value = null;
					else {
						try {
							if (item.getDataType() == IBillItem.INTEGER) {
								Integer.parseInt(sv);
							} else if (item.getDataType() == IBillItem.DECIMAL
									|| item.getDataType() == IBillItem.MONEY) {
								Double.parseDouble(sv);
							}
						} catch (Exception ex) {
							Logger.info("非法数字！");
							bValueChanged = false;
						}
					}
				}
			}

			if (bValueChanged) {

				if (item.getDataType() == BillItem.UFREF && value != null) {
					UIRefPane ref = (UIRefPane) item.getComponent();
					if (value instanceof String
							&& ((String) value).length() != 0)
						value = new DefaultConstEnum(ref.getRefPK(),
								(String) value);
				}

				setValueAt(value, row, col);

				if (item.getMetaDataProperty() == null) {
					// set ID column value
					updateIDColumnValue(row, col);
				} else
					// 加载关联项的值
					getTableModel().loadEditRelationItemValue(row,
							convertColumnIndexToModel(col));

				// 执行公式
				getTableModel().execEditFormulasByModelColumn(row,
						convertColumnIndexToModel(col));
				// 修改状态
				if (getTableModel().getRowState(row) == BillModel.NORMAL)
					getTableModel().setRowState(row, BillModel.MODIFICATION);

				// if(value == null || IBillItem.EMPTYSTRING.equals(value))
				// setCellBackGround(row, item.getKey(),null);
				// else
				// setCellBackGround(row, item.getKey(), Color.YELLOW);
			}
			// 自动增行
			// if (isAutoAddLine() && row == getTableModel().getRowCount() - 1)
			// execAddRowAction(false);
			// addLine(false);
			// process afterEdit
			if (isMultiItemSelected || bValueChanged) {
				String key = item.getKey();
				getTableModel().clearCellShowWarning(row, key);
				// 触发编辑后事件
				if (el != null) {
					value = getValueAt(row, col);
					BillEditEvent ev = new BillEditEvent(e.getSource(),
							getEnumValue(oldValue), getEnumValue(value), key,
							row, BillItem.BODY);
					ev.setTableCode(getTableCode());
					el.afterEdit(ev);
				}
			}
			// 自动增行
			// 2011-3-30 调整了自动增行的代码顺续，主要是因为有人在编辑后事件中要检查本行是否是最后一行，如果先增行，本行就不是
			// 最后一行了（判断最后一行的算法是看getTableModel().getRowCount()-1是否等于本行）
			if (isAutoAddLine() && row == getTableModel().getRowCount() - 1)
				execAddRowAction(false);

			// repaint rowcells
			Rectangle reg = getTable().getCellRect(row, col, true);
			reg.add(getTable().getVisibleRect().getMaxX(), reg.getY());
			getTable().repaint(reg);
		}

		private Object getEnumValue(Object o) {
			IConstEnum em = null;
			if (o instanceof IConstEnum)
				em = (IConstEnum) o;
			else
				return o;

			if (em == null)
				return null;

			return em.getValue();
		}

		private void updateIDColumnValue(int row, int col) {
			// set ID column value
			int column = convertColumnIndexToModel(col);
			BillItem item = getTableModel().getBodyItems()[column];
			if (item.getIDColName() != null
					&& item.getIDColName().trim().length() != 0
					&& item.getDataType() == BillItem.UFREF) {
				UIRefPane ref = (UIRefPane) item.getComponent();
				BillItem idItem = getTableModel().getItemByKey(
						item.getIDColName());
				if (idItem != null) {
					if (idItem.getDataType() == BillItem.UFREF) {
						// getTableModel().setValueAt(ref.getRefPK(), row,
						// idItem.getKey()+IBillItem.ID_SUFFIX);

						getTableModel().setValueAtModel(
								new DefaultConstEnum(ref.getRefPK(), ref
										.getRefPK()),
								row,
								getTableModel()
										.getBodyColByKey(idItem.getKey()));

					} else
						getTableModel().setValueAt(ref.getRefPK(), row,
								idItem.getKey());
				}
			}
		}

		public boolean isCellEditable(int row, int column) {

			BillModel bm = getTableModel();
			// 设置固定列
			if (this.equals(getTable()))
				bm.setFixCol(m_iLockCol);
			else
				bm.setFixCol(-1);
			if (this == BillScrollPane.this.lockTable)
				return false;
			return super.isCellEditable(row, column);
		}

		public void sizeColumnsToFit(int resizingColumn) {
			super.sizeColumnsToFit(resizingColumn);
			// 刷新显示
			if (resizingColumn != -1) {
				if (fixRowTable != null)
					fixRowTable.revalidate();
			}
		}

		public boolean editCellAt(int row, int column, java.util.EventObject e) {
			// 编辑前事件
			// TableCellEditor editor = getCellEditor(row, column);

			if (el2 != null) {
				if ( e instanceof KeyEvent && ((KeyEvent) e).getKeyCode() != KeyEvent.VK_DELETE){//下面代码会导致删除单元格删除不掉,特殊判断
					//
					if (e instanceof KeyEvent
							&& !(((KeyEvent) e).getID() == KeyEvent.KEY_TYPED)) {
						return false;
					} // 
				}
				

				boolean isAllowEdit = isBeforeEditEventAllowEdit(row, column, e);
				if (!isAllowEdit) {
					return isAllowEdit;
				}
				
			}
			
			// 鼠标点击事件
			if (e instanceof MouseEvent) {
				MouseEvent em = (MouseEvent) e;

				if (mouseSelect != null) {
					if (!em.isControlDown()) {

						if (e.getSource().equals(getRowNOTable())
								&& column == 0) {

						} else {

							if (em.isShiftDown()
									&& mouseSelect.isSupportShiftSelect())
								setRowSelectState(getSelectedRow(), row, true);
							else {
								if (mouseSelect.getClickCountSelect() > 0
										&& em.getClickCount() >= mouseSelect
												.getClickCountSelect())
									if (!isCellEditable(row, column))
										setRowSelectState(row, true);
							}
						}
					} else {
						if (mouseSelect.isSupportCtrlSelect()) {
							setRowSelectState(row, true);
						}
					}
				}

				if (ml != null) {

					BillMouseEnent ev = new BillMouseEnent(e.getSource(), row);

					if (em.getClickCount() >= 2)
						ml.mouse_doubleclick(ev);
				}
			}
			boolean b = super.editCellAt(row, column, e);
			if (getSelectedRow() == -1) {
				int editrow = getEditingRow();
				int editcol = getEditingColumn();
				if (editrow != -1 && editcol != -1) {
					setRowSelectionInterval(editrow, editrow);
					setColumnSelectionInterval(editcol, editcol);
				}
			}

			return b;
		}

		public boolean isBeforeEditEventAllowEdit(int row, int column,
				java.util.EventObject e) {
			
			if (allowEdit(row, column, e)||(getTableCode()!=null && getTableCode().equals("pk_task_s"))) {
				BillItem item = getTableModel().getBodyItems()[convertColumnIndexToModel(column)];
				String key = item.getKey();// getBodyKeyByCol(column);
				BillEditEvent ev = new BillEditEvent(item, null, key, row,
						BillItem.BODY);
				ev.setTableCode(getTableCode());
				if(e instanceof MouseEvent){
					ev.setClickCount(((MouseEvent) e).getClickCount());
				}
				if (el2 != null && !el2.beforeEdit(ev))
					return false;
			}
			return true;
		}

		private boolean allowEdit(int row, int column, EventObject e) {

			if (row < 0 || row >= getRowCount() || column < 0
					|| column >= getColumnCount()) {
				return false;
			}

			if (!isCellEditable(row, column))
				return false;

			TableCellEditor editor = getCellEditor(row, column);
			if (editor != null && editor.isCellEditable(e)) {
				Component editorComp = prepareEditor(editor, row, column);
				Object value=null;
				if(editorComp instanceof UIRefPane){
				 value = ((UIRefPane)editorComp).getText();
				}
				Object obj = value;
				if (editorComp == null) {
					return false;
				}
				return true;
			}
			return false;
		}

		@Override
		public void changeSelection(int rowIndex, int columnIndex,
				boolean toggle, boolean extend) {
			super.changeSelection(rowIndex, columnIndex, toggle, extend);
			getTableHeader().repaint();
		}

		public void clearSelection() {
			if (BillScrollPane.this != null) {
				oldrow = -1;
				oldrows = null;
			}

			super.clearSelection();
		}

		// 使用自动调整CellEditor的宽度的功能:
		public TableCellEditor getCellEditor(int row, int column) {
			TableColumn tableColumn = getColumnModel().getColumn(column);
			TableCellEditor editor = tableColumn.getCellEditor();

			if (editor == null) {
				BillItem item = getTableModel().getBodyItems()[convertColumnIndexToModel(column)];
				setCellEditor(tableColumn, item);
				editor = tableColumn.getCellEditor();
			}

			if (editor == null) {
				editor = getDefaultEditor(getColumnClass(column));
			}
			if (this.editor != editor && editor instanceof BillCellEditor) {
				if (((BillCellEditor) editor).getComponent() instanceof UIRefPane) {
					UIRefPane pane = (UIRefPane) ((BillCellEditor) editor)
							.getComponent();
					//
					int maxWidth = 0;
					TableColumnModel model = getColumnModel();
					// 使用Table列宽计算:
					for (int i = column; i < model.getColumnCount(); i++) {
						maxWidth += model.getColumn(i).getWidth();
					}
					if (getParent() instanceof JViewport) {
						// 使用Viewport的宽度计算:
						int width = (int) (((JViewport) getParent()).getSize()
								.getWidth() - ((UIVarLenTextField) (pane
								.getUITextField())).getBounds().getX());
						if (width < maxWidth) {
							maxWidth = width;
						}
					}
					//
					((UIVarLenTextField) (pane.getUITextField()))
							.setMaxWidth(maxWidth);
					((UIVarLenTextField) (pane.getUITextField()))
							.setMinWidth(model.getColumn(column).getWidth());
				}
				this.editor = editor;
			}
			return editor;
		}

		/**
		 * 初始化Table. type in set("main","fix" ), main -- mainTable, fix -
		 * fixColtable 创建日期:(01-3-6 10:48:33)
		 */
		public void setCellEditor(TableColumn tclCol, BillItem item) {
			if (tclCol == null || item == null)
				return;
			// 初始化编辑控件
			switch (item.getDataType()) {
			case BillItem.BOOLEAN: {
				tclCol.setCellEditor(new BillCellEditor((UICheckBox) item
						.getComponent()));
				break;
			}
			case BillItem.COMBO: {
				tclCol.setCellEditor(new BillCellEditor((UIComboBox) item
						.getComponent()));
				break;
			}
			case BillItem.PASSWORDFIELD: {
				tclCol.setCellEditor(new BillCellEditor((UIPasswordField) item
						.getComponent()));
				break;
			}
			case BillItem.MULTILANGTEXT: {
				tclCol.setCellEditor(new BillCellEditor(
						(UIMultiLangCombox) item.getComponent()));
				break;
			}
			case BillItem.FRACTION: {
				tclCol.setCellEditor(new BillCellEditor(
						(UIFractionTextField) item.getComponent()));
				break;
			}
			case BillItem.TEXTAREA: {
				tclCol.setCellEditor(new BillCellEditor(
						(nc.ui.pub.beans.UITextAreaScrollPane) item
								.getComponent()));
				break;
			}
			default: {
				if (item.getComponent() instanceof UIRefPane) {
					tclCol.setCellEditor(new BillCellEditor((UIRefPane) item
							.getComponent()));
				}
				break;
			}
			}
			// 绘制器
		}

		// /**
		// * process cellColor
		// */
		// public void paint(Graphics g) {
		// // updateCellColors(this);
		// super.paint(g);
		// }

		String selectkey = "SPACE_SELECT_ROW";;
		KeyStroke spaceks = KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true);

		protected void addKeySelect() {

			InputMap map = getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
			ActionMap am = getActionMap();

			map.put(spaceks, selectkey);
			am.put(selectkey, new selectAction());

		}

		protected void removeKeySelect() {
			InputMap map = getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
			ActionMap am = getActionMap();

			map.remove(spaceks);
			am.remove(selectkey);

		}

		protected void addMouseSelect(IBillTableMouseSelectControl mouseSelect) {
			this.mouseSelect = mouseSelect;
		}

		protected void removeMouseSelect() {
			this.mouseSelect = null;
		}

		protected IBillTableMouseSelectControl getMouseSelect() {
			return mouseSelect;
		}

		@Override
		public void setRowHeight(int row, int rowHeight) {
			if (mode == TREETABLESHOWMODE)
				setRowHeight(rowHeight);
			else
				super.setRowHeight(row, rowHeight);

			if (this.equals(mainTable) && rowNOTable != null) {
				rowNOTable.setRowHeight(row, rowHeight);
			}

		}

		@Override
		public void setRowHeight(int rowHeight) {
			super.setRowHeight(rowHeight);
			if (mode == TREETABLESHOWMODE) {
				if (tree != null)
					tree.setRowHeight(rowHeight);
			}
		}

		ITableTreeListener tl = null;

		public void addTableTreeListener(ITableTreeListener tl) {
			this.tl = tl;
		}

		@Override
		protected final void processMouseEvent(MouseEvent e) {
			if (mode == TREETABLESHOWMODE && tl != null) {
				tl.processMouseEvent(e);
			}

			super.processMouseEvent(e);

		}

		@Override
		public Component prepareEditor(TableCellEditor editor, int row,
				int column) {
			if (mode == TREETABLESHOWMODE && tl != null) {
				final Component comp = super.prepareEditor(editor, row, column);

				return tl.getEditerWapper(comp, row, column);
			} else {
				return super.prepareEditor(editor, row, column);
			}
		}

		@Override
		public void requestFocus() {
			super.requestFocus();
			if (getRowCount() > 0 && getColumnCount() > 0)
				if (getSelectedRow() < 1 && getSelectedColumn() < 0) {
					if (isCellVisible(0, 0))
						changeSelection(0, 0, false, false);
				}
		}

		private boolean isCellVisible(int row, int column) {
			Rectangle rjt = getVisibleRect();
			Rectangle rcell = getCellRect(row, column, false);

			return rjt.intersects(rcell);

		}

		@Override
		public String getToolTipText(MouseEvent event) {

			String tip = null;
			Point p = event.getPoint();

			// Locate the renderer under the event location
			int hitColumnIndex = columnAtPoint(p);
			int hitRowIndex = rowAtPoint(p);

			if ((hitColumnIndex != -1) && (hitRowIndex != -1)) {
				TableCellRenderer renderer = getCellRenderer(hitRowIndex,
						hitColumnIndex);
				Component component = prepareRenderer(renderer, hitRowIndex,
						hitColumnIndex);

				// Now have to see if the component is a JComponent before
				// getting the tip
				if (component instanceof JComponent) {
					MouseEvent newEvent = null;
					if (component instanceof JTree) {
						newEvent = event;
					} else {
						Rectangle cellRect = getCellRect(hitRowIndex,
								hitColumnIndex, false);
						p.translate(-cellRect.x, -cellRect.y);
						newEvent = new MouseEvent(component, event.getID(),
								event.getWhen(), event.getModifiers(), p.x,
								p.y, event.getXOnScreen(),
								event.getYOnScreen(), event.getClickCount(),
								event.isPopupTrigger(), MouseEvent.NOBUTTON);
					}

					tip = ((JComponent) component).getToolTipText(newEvent);
				}

				// No tip from the renderer get our own tip
				if (tip == null)
					tip = getToolTipText();

				// return tip;
			}

			// String tooltips = super.getToolTipText(event);
			if (StringUtil.isEmptyWithTrim(tip))
				return null;
			else
				return TooltipUtil.getComposedText(tip, this);
		}
	}

	private class AddRowAction extends AbstractAction {

		// final static String APPENDNEWROW = "AppendNewRow";
		// BillTable table = null;
		// KeyStroke ks;

		final static int BOTH = 0; // 最后一行且最后一列

		final static int ROW = 1; // 最后一列

		final static int COLUMN = 2; // 最后一行

		private int mode = BOTH;

		public AddRowAction() {
		}

		public AddRowAction(int mode) {
			this.mode = mode;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
		 * )
		 */
		public void actionPerformed(ActionEvent e) {
			if (isKeyAutoAddLine() && getTableModel().isEnabled()) {
				JTable table = (JTable) e.getSource();
				int[] rows = table.getSelectedRows();
				int[] cols = table.getSelectedColumns();
				int rowcount = table.getRowCount();
				if (table == mainTable && isEnabled()) {
					if (mode == COLUMN
							&& (rowcount == 0 || (getIndexOfInt(cols, table
									.getColumnCount() - 1) >= 0))) {
						execAddRowButtonAction();
					} else if (mode == ROW
							&& (getIndexOfInt(rows, table.getRowCount() - 1) >= 0)) {
						execAddRowButtonAction();
					} else if ((rowcount == 0 || (getIndexOfInt(cols, table
							.getColumnCount() - 1) >= 0 && getIndexOfInt(rows,
							table.getRowCount() - 1) >= 0))) {
						if (getBillTableEnterKeyControler() != null) {
							KeyEvent ke = new KeyEvent(table,
									KeyEvent.KEY_RELEASED, e.getWhen(), 0,
									KeyEvent.VK_ENTER, '\n');
							getBillTableEnterKeyControler().processKeyEvent(ke);
							ke.consume();
						} else {
							execAddRowButtonAction();
						}
						return;
					}
				}
			}
		}

		/**
		 * 查找整数在数组中的序号.
		 * 
		 * @param is
		 *            the int array
		 * @param i
		 *            the int to find
		 * @return the index of the int in the int array
		 */
		private int getIndexOfInt(int[] is, int i) {
			if (is == null || is.length == 0)
				return -1;
			for (int ii = 0; ii < is.length; ii++) {
				if (is[ii] == i)
					return ii;
			}
			return -1;
		}

		private void execAddRowButtonAction() {
			// if(getBillActionListener() != null) {
			// if(!getTable().isEditing())
			// addLine();
			// }else {
			// ToftPanel tp = getToftPanel(BillScrollPane.this);
			// if (tp != null)
			// tp.callButtonObjectMethod("增行", true);
			// else
			// if(!getTable().isEditing())
			// addLine();
			// }
			if (!getTable().isEditing())
				execAddRowAction(true);
		}
		//
		// /**
		// * 取得NC节点的Toftpanel,参数是窗口中的一个swing对象.
		// *
		// * @param c
		// * the child component
		// * @return the parent instanceof toftpanel
		// */
		// private ToftPanel getToftPanel(Component c) {
		// return (ToftPanel) BillUtil.getParentComonent(c, ToftPanel.class);
		// }
	}

	// private class LockActionListener implements java.awt.event.ActionListener
	// {
	// public void actionPerformed(java.awt.event.ActionEvent e) {
	// onLock(this, e);
	// }
	// }

	// // 菜单监听
	// class MenuItemListener implements ActionListener {
	// public void actionPerformed(ActionEvent e) {
	// onMenuItemClick(e);
	// };
	// }

	private Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
	private Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);

	class TableCellMouseListener extends MouseAdapter {
		int oldrow = -1;

		public void mouseDragged(MouseEvent e) {

			if (getTable().getMouseSelect() != null && !getTable().isEditing()
					&& e.isControlDown()) {
				if (getTable().getMouseSelect().isSupportCtrlSelect()) {
					Point p = e.getPoint();
					int row = getTable().rowAtPoint(p);
					if (row != oldrow)
						setRowSelectState(row, true);

					oldrow = row;
				}
			}
		}

		public void mouseClicked(MouseEvent e) {

			if (e.getClickCount() == 1) {

				UITable table = getTable();
				if (table.getRowCount() == 0)
					return;
				Point p = e.getPoint();
				int col = table.columnAtPoint(p);
				int row = table.rowAtPoint(p);
				if (col < 0 || row < 0)
					return;

				Object value = getTable().getValueAt(row, col);
				
				//若是附件类型，显示附件编辑界面
				BillItem item = getBillItems()[table.convertColumnIndexToModel(col)];
				if(item.getDataType()==BillItem.ATTACHMENT){
					boolean isshowonly = false;
					isshowonly = item.doAccessorylink(getTable(), row, col);
					AccessoryUtils accessoryUtil= new AccessoryUtils(getTableModel(), table, item, e, isshowonly);
					accessoryUtil.showAcessoryMenue();
				}
				if (contains(e.getPoint()) && value != null) {
//					BillItem item = getBillItems()[table
//							.convertColumnIndexToModel(col)];
					item.doHyperlink(getTable(), value, row);
				}
			}
		}

		public void mouseMoved(MouseEvent e) {

			if (contains(e.getPoint()))
				getTable().setCursor(handCursor);
			else
				getTable().setCursor(normalCursor);

		}

		public void mouseReleased(java.awt.event.MouseEvent e) {
			mouseReleasedTableCell(e);
		};
		
		public void mousePressed(java.awt.event.MouseEvent e) {
			mouseReleasedTableCell(e);
		};


		private boolean contains(Point p) {

			UITable table = getTable();
			if (table.getRowCount() == 0)
				return false;

			int col = table.columnAtPoint(p);
			int row = table.rowAtPoint(p);
			if (col < 0 || row < 0)
				return false;

			BillItem item = getBillItems()[getTable()
					.convertColumnIndexToModel(col)];
			if(item.getDataType()==BillItem.ATTACHMENT){
				return true;
			}
			if (!(item.isHyperlink() || item.isListHyperlink()))
				return false;
			// if (!item.isListHyperlink())
			// return false;

			Object o = getTable().getValueAt(row, col);

			if (o == null || o.toString() == null)
				return false;

			TableCellRenderer render = getTable().getCellRenderer(row, col);
			Rectangle rectTree = new Rectangle(0, 0, 0, 0);
			if (render instanceof JTree) {
				JTree tree = (JTree) render;
				rectTree = tree.getPathBounds(tree.getPathForRow(row));
			}

			FontMetrics fm = getTable().getFontMetrics(getTable().getFont());

			Rectangle rect = fm.getStringBounds(o.toString(),
					getTable().getGraphics()).getBounds();

			Rectangle total = getTable().getCellRect(row, col, false);

			Insets insets = getInsets();

			rect.y = total.y
					+ (total.height - rect.height - insets.top - insets.bottom)
					/ 2;

			switch (item.getDataType()) {
			case IBillItem.INTEGER:
			case IBillItem.DECIMAL:
				rect.x = total.x + total.width - rect.width - insets.left
						- insets.right;
				break;
			default:
				rect.x = total.x + rectTree.x + insets.left;
				break;
			}

			if (rect.contains(p))
				return true;

			return false;

		}

	}

	// private TableCellMouseListener tableCellMouseListener = null;

	private BillTableEnterKeyControler billTableEnterKeyControler = null; // 表格回车监听

	/**
	 * BillScrollPane 构造子注解.
	 */
	public BillScrollPane() {
		this(null, VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_AS_NEEDED);
	}

	/**
	 * BillScrollPane 构造子注解.
	 * 
	 * @param vsbPolicy
	 *            int
	 * @param hsbPolicy
	 *            int
	 */
	public BillScrollPane(int vsbPolicy, int hsbPolicy) {
		this(null, vsbPolicy, hsbPolicy);
	}

	/**
	 * BillScrollPane 构造子注解.
	 * 
	 * @param view
	 *            java.awt.Component
	 */
	public BillScrollPane(java.awt.Component view) {
		this(view, VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_AS_NEEDED);
	}

	/**
	 * BillScrollPane 构造子注解.
	 * 
	 * @param view
	 *            java.awt.Component
	 * @param vsbPolicy
	 *            int
	 * @param hsbPolicy
	 *            int
	 */
	public BillScrollPane(java.awt.Component view, int vsbPolicy, int hsbPolicy) {
		setLayout(new BillScrollPaneLayout());
		setVerticalScrollBarPolicy(vsbPolicy);
		setHorizontalScrollBarPolicy(hsbPolicy);
		setViewport(createViewport());
		setVerticalScrollBar(createVerticalScrollBar());
		setHorizontalScrollBar(createHorizontalScrollBar());
		if (view != null) {
			setViewportView(view);
		}
		setUI(new BillScrollPanelUI());
		// updateUI();
		initialize();
		//addAction();


		//setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(3,3,3,3),BorderFactory.createLineBorder(Style.NCborderColor)));
		setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		//this.setBorder(BorderFactory.createLineBorder(Color.red));
	}
	
	public void addAction(){

		if (getBillParent() instanceof BillCardPanel){
			addNotEditAction(getActionManager().getBillTableAction(LOCK));
			addFixAction(new LocateCellAction(this));
//			if (getBillParent() instanceof BillCardPanel) {
//				// addFixAction(new TableUISetAction(this));
//				
//			}
			addFixAction(new TableNavigationSwitcherAction());
			if (((BillCardPanel) getBillParent()).isBillCardUISetActionButtonShow()){
			addFixAction(new BillCardUISetAction((BillCardPanel) getBillParent()));
			}
			this.addMouseListener(new MouseAdapter() {
				public void mouseReleased(MouseEvent e) {
					if (e.isPopupTrigger() ) {
						if(((BillCardPanel) getBillParent()).isBillCardUISetActionButtonShow()){
						UIPopupMenu menu = new UIPopupMenu();
						BillCardUISetAction action = new BillCardUISetAction((BillCardPanel) getBillParent());
						menu.add(action);
						action.setActionTabName(getTableName());
						menu.show((Component) e.getSource(), e.getX(), e.getY());
						}
					}
				}
			});
		}
		if (getBillParent() instanceof BillListPanel){
			if (isListTableItemAdjustButtonShow()){
				
				BillListUISetAction action = new BillListUISetAction((BillListPanel) getBillParent());
				action.setActionTabName(getTableName());
				addFixAction(action);
				
			}
			setBBodyMenuShow(true);
			this.addMouseListener(new MouseAdapter() {
				public void mouseReleased(MouseEvent e) {
					if (e.isPopupTrigger()) {
						UIPopupMenu menu = new UIPopupMenu();
						if (isListTableItemAdjustButtonShow()) {
						BillListUISetAction action = new BillListUISetAction((BillListPanel) getBillParent());
						menu.add(action);
						action.setActionTabName(getTableName());
						}
						menu.show((Component) e.getSource(), e.getX(), e.getY());
						
					}
				}
				public void mousePressed(MouseEvent e) {
					if (e.isPopupTrigger()) {
						UIPopupMenu menu = new UIPopupMenu();
						if (isListTableItemAdjustButtonShow()) {
						BillListUISetAction action = new BillListUISetAction((BillListPanel) getBillParent());
						menu.add(action);
						action.setActionTabName(getTableName());
						}
						menu.show((Component) e.getSource(), e.getX(), e.getY());
						
					}
				}
			});
		}
		
	}

	/**
	 * BillScrollPane 构造子注解.
	 */
	public BillScrollPane(boolean isCardFlag) {
		this(null, VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_AS_NEEDED);
		setIsCard(isCardFlag);
	}

	/**
	 * 创建日期:(2003-3-5 16:29:42)
	 * 
	 * @param actionListener
	 *            nc.ui.pub.bill.BillActionListener
	 */
	public void addBillActionListener(BillActionListener actionListener) {
		lba = actionListener;
	}

	// /**
	// * 创建日期:(2003-3-5 16:28:38)
	// *
	// * @param menuListener
	// * nc.ui.pub.bill.BillBodyMenuListener
	// */
	// public void addBillBodyMenuListener(BillBodyMenuListener menuListener) {
	// lbm = menuListener;
	// }

	/**
	 * 添加编辑监听. 创建日期:(2001-3-23 2:20:34)
	 * 
	 * @param l
	 *            BillEditListener
	 */
	public void addBillTableLockListener(IBillTableLockListener tll) {
		this.billTableLockListener = tll;
	}

	/**
	 * 添加编辑监听. 创建日期:(2001-3-23 2:20:34)
	 * 
	 * @param l
	 *            BillEditListener
	 */
	public void removeBillTableLockListener() {
		this.billTableLockListener = null;
	}

	/**
	 * 添加编辑监听. 创建日期:(2001-3-23 2:20:34)
	 * 
	 * @param l
	 *            BillEditListener
	 */
	public void addEditListener(BillEditListener el) {
		this.el = el;
	}

	/**
	 * 添加编辑监听. 创建日期:(2001-3-23 2:20:34)
	 * 
	 * @param l
	 *            BillEditListener
	 */
	public void addEditListener2(BillEditListener2 el) {
		this.el2 = el;
	}

	/**
	 * 增加行.
	 */
	public void addLine() {
		addLine(1);
	}

	/**
	 * 增加行.
	 */
	public void addLine(boolean changeSelection) {
		addLine(1, changeSelection);
	}

	/**
	 * 增加行.
	 */
	public void addLine(int count) {
		addLine(count, true);
	}

	/**
	 * 增加行.
	 */
	public void addLine(int count, boolean changeSelection) {
		if (count < 1)
			return;
		boolean isdo = true;
		BillScrollPane bsp = this;
		if (bsp.getBillActionListener() != null)
			isdo = bsp.getBillActionListener().onEditAction(ADDLINE);
		if (isdo) {

			int selectrow = getTable().getSelectedRow();

			boolean f = bsp.getTable().hasFocus();// isManagingFocus();
			BillModel bm = bsp.getTableModel();
			if (mode == TABLESHOWMODE)
				bm.addLine(count);
			else
				bm.addLine(selectrow);

			if (f)
				bsp.getTable().requestFocus();

			if (changeSelection) {
				int col = BillUtil.getFirstEditItemIndex(getTableModel()
						.getBodyItems());

				if (col < 0)
					col = 0;

				col = bsp.getTable().convertColumnIndexToView(col);

				if (mode == TABLESHOWMODE)
					bsp.getTable().changeSelection(
							bsp.getTableModel().getRowCount() - 1, col, false,
							false);

				if (mode == TREETABLESHOWMODE)
					bsp.getTable()
							.changeSelection(selectrow, col, false, false);
			}
			bsp.getTable().updateUI();
		}
	}

	/**
	 * 添加鼠标监听. 创建日期:(2001-3-23 2:20:34)
	 * 
	 * @param ml
	 *            BillTableMouseListener
	 */
	public void addMouseListener(BillTableMouseListener ml) {
		this.ml = ml;
	}

	/**
	 * 添加扩展的鼠标监听. 创建日期:(2001-3-23 2:20:34)
	 * 
	 * @param ml
	 *            BillTableMouseListener
	 */
	public void addMouseListener2(BillComponentMouseListener ml) {
		this.ml2 = ml;
		javax.swing.table.JTableHeader jth = getTable().getTableHeader();
		jth.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				// 鼠标点击事件
				if (ml2 != null) {
					javax.swing.table.TableColumnModel tcm = getTable()
							.getColumnModel();
					int column = tcm.getColumnIndexAtX(e.getX());
					// String key = getTableModel().getBodyKeyByCol(column);
					// int column = getTableModel().getco
					BillItem item = getTableModel().getBodyItems()[column];
					BillComponentMouseEvent ev = new BillComponentMouseEvent(e
							.getSource(), item, e);
					if (e.getClickCount() >= 2)
						ml2.mouse_doubleclick(ev);
				}
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
				// 鼠标点击事件
				if (ml2 != null) {
					javax.swing.table.TableColumnModel tcm = getTable()
							.getColumnModel();
					int column = tcm.getColumnIndexAtX(e.getX());
					BillItem item = getTableModel().getBodyItems()[column];
					BillComponentMouseEvent ev = new BillComponentMouseEvent(e
							.getSource(), item, e);
					ml2.mouseReleased(ev);
				}
			}
		});
	}

	/*
	 * addTableCellMouseListener
	 */
	public void addTableBodyMenu() {
		// if (tableCellMouseListener == null)
		// tableCellMouseListener = new TableCellMouseListener();
		// else
		// getTable().removeMouseListener(tableCellMouseListener);
		//
		// getTable().addMouseListener(tableCellMouseListener);

		setBBodyMenuShow(true);

		// if(getTableModel().isRowSelectMode()){
		// setMiBody(miSelectBody);
		// }
	}

	public void addTableSortListener() {
		getTable().addSortListener();
	}

	/**
	 * 清除选择. 创建日期:(2001-7-26 15:53:07)
	 */
	public void clearSelect() {
		oldrow = -1;
	}

	/**
	 * get view column by model column
	 */
	int convertModelColumnIntoViewColumn(int col) {
		if (col < 0)
			return -1;
		BillItem[] items = getTableModel() != null ? getTableModel()
				.getBodyItems() : null;
		if (items == null || col >= items.length)
			return -1;
		int vcol = -1;
		if (lockTable != null)
			vcol = lockTable.convertColumnIndexToView(col);
		if (vcol < 0 && mainTable != null)
			vcol = mainTable.convertColumnIndexToView(col);
		return vcol;
	}

	/*
	 * get model column index from view index
	 */
	int convertViewColumnIndexToModel(int col) {
		if (col < 0)
			return -1;
		int mcol = -1;
		// if (lockTable != null && lockTable.getColumnCount() > col)
		// mcol = lockTable.convertColumnIndexToModel(col);
		// if (mcol < 0 && mainTable != null)
		if (mainTable != null)
			mcol = mainTable.convertColumnIndexToModel(col);
		return mcol;
	}

	/**
	 * 复制行.
	 */
	public void copyLine() {
		boolean isdo = true;
		BillScrollPane bsp = this;
		if (bsp.getBillActionListener() != null)
			isdo = bsp.getBillActionListener().onEditAction(COPYLINE);
		if (isdo) {
			int row[] = bsp.getTable().getSelectedRows();
			bsp.getTableModel().copyLine(row);
		}
	}

	protected static BillScrollPane createDefaultBillScrollPane() {
		return new BillScrollPane(VERTICAL_SCROLLBAR_AS_NEEDED,
				HORIZONTAL_SCROLLBAR_AS_NEEDED);
	}

	/*
	 * 合并单元格
	 */

	public void combineCellBySelect() {
		if (getTableModel().getCellSpan() != null)
			getTableModel().getCellSpan().combine(getTable().getSelectedRows(),
					getTable().getSelectedColumns());

		updateUI();
	}

	/**
	 * 创建头表 创建日期:(01-2-23 15:29:06)
	 * 
	 * @return ufbill.BillModel
	 */
	protected BillTable createDefaultFixColTable() {
		BillTable btTable = new BillTable(getTableModel());
		btTable.setName("FixColTable");
		btTable.setSelectionModel(getTable().getSelectionModel());
		btTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		btTable.getTableHeader().setReorderingAllowed(false);
		btTable.getTableHeader().setResizingAllowed(false);
		btTable.setCellSelectionEnabled(false);
		btTable.setRowSelectionAllowed(true);
		initTable(btTable, "fix");
		btTable.getHeaderPopupMenu().addSeparator();
		btTable.getHeaderPopupMenu().add(
				getActionManager().getBillTableAction(LOCK));
		btTable.setFocusable(false);
		btTable.setBackground(btTable.getTableHeader().getBackground());
		btTable.setRowHeight(getTable().getRowHeight());
		btTable.addMouseListener(new TableCellMouseListener());
		return btTable;
	}

	/**
	 * 创建合计表 创建日期:(01-2-23 15:29:06)
	 * 
	 * @return ufbill.BillModel
	 */
	protected BillTable createDefaultFixRowTable() {
		BillTable btTable = createBillTable(getTableModel()
				.getTotalTableModel(), false, true);
		btTable.setName("FixRowTable");
		// btTable.setEnabled(false);
		btTable.setBackground(btTable.getTableHeader().getBackground());
		btTable.setRowHeight(getTable().getRowHeight());
		btTable.setColumnModel(getTable().getColumnModel());
		btTable.setCellSelectionEnabled(true);

		return btTable;
	}

	/**
	 * 创建行号表 创建日期:(01-2-23 15:29:06)
	 */
	protected BillTable createDefaultRowNOTable() {

		BillTable btTable = createBillTable(getTableModel()
				.getRowNOTableModel(), true, false);
		btTable.setName("RowNOTable");
		btTable.setFocusable(false);
		btTable.getColumnModel().getColumn(0).setCellRenderer(
				new RowNOCellRenderer());

		if (btTable.getColumnCount() > 1) {
			btTable.getColumnModel().getColumn(1).setHeaderRenderer(
					new BillTableBooleanCellRenderer());
			btTable.getColumnModel().getColumn(1).setCellRenderer(
					new BooleanRenderer());

		}

		btTable.setBackground(btTable.getTableHeader().getBackground());
		btTable.setRowSelectionAllowed(true);

		btTable.setSelectionModel(getTable().getSelectionModel());
		btTable.setRowHeight(getTable().getRowHeight());
		btTable.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				int count = getTable().getColumnModel().getColumnCount();
				getTable().setColumnSelectionInterval(count - 1, 0);
				getTable().getTableHeader().repaint();
			}

		});
		JTableHeader jth = btTable.getTableHeader();
		// 全选处理
		jth.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				TableColumnModel tcm = getRowNOTable().getColumnModel();
				int column = tcm.getColumnIndexAtX(e.getX());
				// 鼠标点击事件
				if (getTable().getMouseSelect() != null && column == 1)
					if (e.getClickCount() >= 1
							&& getTable().getMouseSelect()
									.isSupportCornerAllSelect()) {
						swithAllSelectTableRowStats();
					}
			}
		});
		return btTable;
	}

	/**
	 * 创建主表 创建日期:(01-2-23 15:29:06)
	 * 
	 * @return ufbill.BillModel
	 */
	protected BillTable createDefaultTable() {
		BillTable btTable = new BillTable(getTableModel());
		btTable.setName("Table");
		if (isCard()) {
			// btTable.setUI(new CardTableUI());
		} else {
			// for jdk1.4 or later
			// btTable.setEnterKeyNavigationHorizontal(true);
			btTable.setSurrendersFocusOnKeystroke(true);
		}
		// btTable.setUI(new BillTableUI());
		btTable.setCellSelectionEnabled(true);
		btTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		setColumnHeaderView(btTable.getTableHeader());
		// btTable.getTableHeader().setReorderingAllowed(false);
		btTable.getSelectionModel().addListSelectionListener(
				new RowSelectListener());
		btTable.getColumnModel().getSelectionModel().addListSelectionListener(
				new ColSelectListener());
		initTable(btTable, "main");
		btTable.getHeaderPopupMenu().addSeparator();
		btTable.getHeaderPopupMenu().add(
				getActionManager().getBillTableAction(LOCK));
		//yxq add ------------------------------------------2013/12/26----------------
		if (getBillParent() instanceof BillCardPanel) {
//			btTable.getHeaderPopupMenu().addSeparator();
//			BillCardUISetAction action  =  new BillCardUISetAction((BillCardPanel) getBillParent());
//			action.setActionTabName(getTableName());
//			btTable.getHeaderPopupMenu().add(action);
		}
		if (getBillParent() instanceof BillListPanel) {
			
			if (isListTableItemAdjustButtonShow()) {
				btTable.getHeaderPopupMenu().addSeparator();
				BillListUISetAction action = new BillListUISetAction((BillListPanel) getBillParent());
				action.setActionTabName(getTableName());
				btTable.getHeaderPopupMenu().add(action);
			}
			
		}
		//yxq -- ------------------------------------------2013/12/26----------------
		btTable.addSortListener();
		

		btTable.getTableHeader().addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() >= 2) {
					Point p = e.getPoint();

					int col = getTable().getTableHeader().columnAtPoint(p);

					resizingColumn(p, col);

				}
			}

			private void resizingColumn(Point p, int column) {
				if (column == -1) {
					return;
				}
				Rectangle r = getTable().getTableHeader().getHeaderRect(column);
				r.grow(-3, 0);
				if (r.contains(p)) {
					return;
				}
				int midPoint = r.x + r.width / 2;
				int columnIndex = -1;
				if (getTable().getTableHeader().getComponentOrientation()
						.isLeftToRight()) {
					columnIndex = (p.x < midPoint) ? column - 1 : column;
				} else {
					columnIndex = (p.x < midPoint) ? column : column - 1;
				}
				if (columnIndex == -1) {
					return;
				}
				TableColumn tcl = getTable().getTableHeader().getColumnModel()
						.getColumn(columnIndex);

				if (tcl != null) {
					int startrow = getTable().rowAtPoint(
							getViewport().getViewPosition());

					Point p2 = new Point(getViewport().getViewPosition());

					p2.y = p2.y + getViewport().getExtentSize().height + 3
							* getTable().getRowHeight();

					int endrow = getTable().rowAtPoint(p2);

					if (endrow > getTable().getRowCount() || endrow < 0)
						endrow = getTable().getRowCount();

					BillItem item = getTableModel().getBodyItems()[getTable()
							.convertColumnIndexToModel(columnIndex)];

					java.awt.FontMetrics fm = getTable().getTableHeader()
							.getFontMetrics(
									getTable().getTableHeader().getFont());

					int iwidth = Math.max(item.getWidth(), fm.stringWidth(item
							.getName()) + 2);

					for (int i = startrow; i < endrow; i++) {
						Object o = getTableModel().getValueAt(
								i,
								getTable().convertColumnIndexToModel(
										columnIndex));

						if (o != null) {
							int len = fm.stringWidth(o.toString()) + 5;
							iwidth = Math.max(iwidth, len);
						}
					}

					tcl.setPreferredWidth(iwidth);
				}
			}
		});

		// btTable.addMouseMotionListener(new MouseDraggedSelectHandler());

		btTable.addMouseListener(new TableCellMouseListener());

		btTable.addMouseMotionListener(new TableCellMouseListener());

		return btTable;
	}

	/**
	 * 创建主表 创建日期:(01-2-23 15:29:06)
	 * 
	 * @return ufbill.BillModel
	 */
	protected BillModel createDefaultTableModel() {
		BillModel model = new BillModel();
		model.addBillSortListener2(this);
		return model;
	}

	/**
	 * 删除行.
	 */
	public boolean delLine() {
		int rows[] = getTable().getSelectedRows();
		return delLine(rows);
	}

	/**
	 * 删除行.
	 */
	public boolean delLine(final int[] rows) {
		boolean isdo = true;
		BillScrollPane bsp = this;
		if (bsp.getBillActionListener() != null)
			isdo = bsp.getBillActionListener().onEditAction(DELLINE);
		if (isdo) {
			if (rows.length == 0)
				return false;
			// stop Cell editing
			stopCellEditing();

			final int col = bsp.getTable().getSelectedColumn() < 0 ? 0 : bsp
					.getTable().getSelectedColumn();

			bsp.getTableModel().delLine(rows);

			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					int rowCount = getTable().getRowCount();
					if (rowCount > 0) {
						if (rows[0] < rowCount)
							getTable().changeSelection(rows[0], col, false,
									false);
						else
							getTable().changeSelection(rowCount - 1, col,
									false, false);
					}
				}
			});
			return true;
		}
		return false;
	}

	/**
	 * 创建日期:(2003-3-5 16:29:42)
	 * 
	 * @param actionListener
	 *            nc.ui.pub.bill.BillActionListener
	 */
	public BillActionListener getBillActionListener() {
		return lba;
	}

	// /**
	// * 创建日期:(2003-3-5 16:28:38)
	// *
	// * @param menuListener
	// * nc.ui.pub.bill.BillBodyMenuListener
	// */
	// public BillBodyMenuListener getBillBodyMenuListener() {
	// return lbm;
	// }

	/**
	 * 创建日期:(2003-6-19 16:30:38)
	 */
	private BillItem[] getBillItems() {
		BillItem[] items = null;
		BillModel bmodel = getTableModel();
		if (bmodel != null)
			items = bmodel.getBodyItems();
		BillUtil.convertBillItemsToTableCode(items, getTableCode());
		return items;
	}

	/**
	 * @return Returns the billParent.
	 */
	Component getBillParent() {
		return billParent;
	}

	/**
	 * 创建日期:(2003-10-14 15:31:57)
	 * 
	 * @return nc.ui.pub.bill.BillTableEnterKeyControler
	 */
	public BillTableEnterKeyControler getBillTableEnterKeyControler() {
		return billTableEnterKeyControler;
	}

	/**
	 * 由显示列对应关键字. 创建日期:(01-2-21 14:47:10)
	 */
	public String getBodyKeyByCol(int col) {
		col = convertViewColumnIndexToModel(col);
		if (col < 0)
			return null;
		return getTableModel().getBodyItems()[col].getKey();
	}

	/**
	 * 创建日期:(2003-3-6 16:18:21)
	 * 
	 * @return int
	 */
	public int getColumnCurrentHeader() {
		return getTable().getSelectedColumn();
	}

	/**
	 * Returns the component at the specified corner. The <code>key</code> value
	 * specifying the corner is one of:
	 * <ul>
	 * <li>JScrollPane.LOWER_LEFT_CORNER
	 * <li>JScrollPane.LOWER_RIGHT_CORNER
	 * <li>JScrollPane.UPPER_LEFT_CORNER
	 * <li>JScrollPane.UPPER_RIGHT_CORNER
	 * </ul>
	 * 
	 * @return the Component at the specified corner
	 * @see #setCorner
	 */
	public Component getCorner(String key) {
		if (key.equals(ROW_NO_HEADER)) {
			return rowNOHeader;
		} else if (key.equals(ROW_NO)) {
			return rowNO;
		} else if (key.equals(USER_ROW)) {
			return userRow;

		} else if (key.equals(USER_ROW_NO)) {
			return userRowNO;

		} else if (key.equals(USER_ROW_LOCK)) {
			return userRowLock;
		} else if (key.equals(FIX_ROW)) {
			return fixRow;
		} else if (key.equals(FIX_ROW_NO)) {
			return fixRowNO;
		} else if (key.equals(FIX_ROW_LOCK)) {
			return fixRowLock;

		} else if (key.equals(LOWER_LEFT_CORNER)) {
			return lowerLeft;
		} else if (key.equals(LOWER_RIGHT_CORNER)) {
			return lowerRight;
		} else if (key.equals(UPPER_LEFT_CORNER)) {
			return upperLeft;
		} else if (key.equals(UPPER_RIGHT_CORNER)) {
			return upperRight;
		} else {
			boolean isLeftToRight = getComponentOrientation().isLeftToRight();
			if (key.equals(LOWER_LEADING_CORNER)) {
				key = isLeftToRight ? LOWER_LEFT_CORNER : LOWER_RIGHT_CORNER;
			} else if (key.equals(LOWER_TRAILING_CORNER)) {
				key = isLeftToRight ? LOWER_RIGHT_CORNER : LOWER_LEFT_CORNER;
			} else if (key.equals(UPPER_LEADING_CORNER)) {
				key = isLeftToRight ? UPPER_LEFT_CORNER : UPPER_RIGHT_CORNER;
			} else if (key.equals(UPPER_TRAILING_CORNER)) {
				key = isLeftToRight ? UPPER_RIGHT_CORNER : UPPER_LEFT_CORNER;
			}
			return super.getCorner(key);
		}
	}

	/**
	 * 添加编辑监听. 创建日期:(2001-3-23 2:20:34)
	 * 
	 * @param l
	 *            BillEditListener
	 */
	public BillEditListener getEditListener() {
		return el;
	}

	/**
	 * 添加编辑监听. 创建日期:(2001-3-23 2:20:34)
	 * 
	 * @param l
	 *            BillEditListener
	 */
	public BillEditListener2 getEditListener2() {
		return el2;
	}

	/**
	 * 创建日期:(01-2-28 9:08:39)
	 * 
	 * @return ufbill.BillTable
	 */
	public BillTable getFixColTable() {
		if (lockTable == null)
			lockTable = createDefaultFixColTable();
		return lockTable;
	}

	public JViewport getFixRow() {
		return fixRow;
	}

	/**
	 * 获得合计行表. 创建日期:(01-2-28 9:08:39)
	 * 
	 * @return ufbill.BillTable
	 */
	public BillTable getFixRowTable() {
		if (fixRowTable == null)
			fixRowTable = createDefaultFixRowTable();
		return fixRowTable;
	}

	/**
	 * 创建日期:(2001-8-14 9:59:58)
	 * 
	 * @return int
	 */
	public int getLockCol() {
		return m_iLockCol;
	}

	/**
	 * 创建日期:(2003-7-4 15:04:19)
	 * 
	 * @return int
	 */
	public int getMax_lockCount() {
		return max_lockCount;
	}

	BillTableActionManager actionmanager = null;

	private BillTableActionManager getActionManager() {
		if (actionmanager == null)
			actionmanager = BillTableActionManager.getInstance(this);

		return actionmanager;
	}

	// /**
	// * 创建日期:(2003-4-7 18:09:33)
	// *
	// * @return nc.ui.pub.beans.UIMenuItem
	// */
	// public nc.ui.pub.beans.UIMenuItem getMiAddLine() {
	// return getActionManager().getBillTableMenuItem(ADDLINE);
	// }

	// /**
	// * 创建日期:(2003-4-7 18:09:33)
	// *
	// * @return nc.ui.pub.beans.UIMenuItem[]
	// */
	// public nc.ui.pub.beans.UIMenuItem[] getMiBody() {
	// return miBody;
	// }
	//
	// /**
	// * 创建日期:(2003-4-7 18:09:33)
	// *
	// * @return nc.ui.pub.beans.UIMenuItem
	// */
	// public nc.ui.pub.beans.UIMenuItem getMiCopyLine() {
	// return getActionManager().getBillTableMenuItem(COPYLINE);
	// }
	//
	// /**
	// * 创建日期:(2003-4-7 18:09:33)
	// *
	// * @return nc.ui.pub.beans.UIMenuItem
	// */
	// public nc.ui.pub.beans.UIMenuItem getMiDelLine() {
	// return getActionManager().getBillTableMenuItem(DELLINE);
	// }
	//
	// /**
	// * 创建日期:(2003-4-7 18:09:33)
	// *
	// * @return nc.ui.pub.beans.UIMenuItem
	// */
	// public nc.ui.pub.beans.UIMenuItem getMiInsertLine() {
	// return getActionManager().getBillTableMenuItem(INSERTLINE);
	// }
	//
	// /**
	// * 创建日期:(2003-4-7 18:08:16)
	// *
	// * @return nc.ui.pub.beans.UIMenuItem
	// */
	// public nc.ui.pub.beans.UIMenuItem getMiLock() {
	// return getActionManager().getBillTableMenuItem(LOCK);
	// }
	//
	// /**
	// * 创建日期:(2003-4-7 18:09:33)
	// *
	// * @return nc.ui.pub.beans.UIMenuItem
	// */
	// public nc.ui.pub.beans.UIMenuItem getMiPasteLine() {
	// return getActionManager().getBillTableMenuItem(PASTELINE);
	// }
	//
	// /**
	// * 创建日期:(2003-8-25 9:08:39)
	// *
	// * @return nc.ui.pub.beans.UIMenuItem
	// */
	// public nc.ui.pub.beans.UIMenuItem getMiPasteLineToTail() {
	// return getActionManager().getBillTableMenuItem(PASTELINETOTAIL);
	// }
	//
	// /**
	// * 创建日期:(2003-8-25 9:08:39)
	// *
	// * @return nc.ui.pub.beans.UIMenuItem
	// */
	// public nc.ui.pub.beans.UIMenuItem getMiAllSelctRow() {
	// return getActionManager().getBillTableMenuItem(ALLSELECT);
	// }
	//
	// /**
	// * 创建日期:(2003-8-25 9:08:39)
	// *
	// * @return nc.ui.pub.beans.UIMenuItem
	// */
	// public nc.ui.pub.beans.UIMenuItem getMiCancelAllSelctRow() {
	// return getActionManager().getBillTableMenuItem(CANCELALLSELECT);
	// }

	/**
	 * 添加鼠标监听. 创建日期:(2001-3-23 2:20:34)
	 * 
	 * @param ml
	 *            BillTableMouseListener
	 */
	public BillTableMouseListener getMouseListener() {
		return ml;
	}

	/**
	 * 添加扩展的鼠标监听. 创建日期:(2001-3-23 2:20:34)
	 * 
	 * @param ml
	 *            BillTableMouseListener
	 */
	public BillComponentMouseListener getMouseListener2() {
		return ml2;
	}

	// // 设置负数是否显示符号
	// public boolean getNegativeSign() {
	// return m_RendererVO.isNegativeSign();
	// }

	/**
	 * 创建日期:(2003-9-2 15:09:40)
	 * 
	 * @return nc.ui.test.PageNavigationBar
	 */
	public PageNavigationBar getPageNavigationBar() {
		if (pageNavigationBar == null)
			pageNavigationBar = new PageNavigationBar();
		return pageNavigationBar;
	}

	/**
	 * 创建日期:(2003-9-2 18:56:05)
	 * 
	 * @return javax.swing.JViewport
	 */
	private javax.swing.JViewport getPageNavigationBarVP() {
		if (pageNavigationBarVP == null)
			pageNavigationBarVP = createViewport();
		return pageNavigationBarVP;
	}

	/**
	 * 获得表体菜单 创建日期:(2003-4-7 18:05:42)
	 * 
	 * @return nc.ui.pub.beans.UIPopupMenu
	 */
	public nc.ui.pub.beans.UIPopupMenu getPmBody() {
		// if (pmBody == null) {
		// BillScrollPane bsp = this;
		// if (bsp.getMiBody() == null)
		// return null;
		// if (menuItemListener == null)
		// menuItemListener = new MenuItemListener();
		// ActionListener listener = menuItemListener;
		// pmBody = new UIPopupMenu();
		// if (bsp.getPmBody() == null) {
		// bsp.setPmBody(new UIPopupMenu());
		// }
		// updateBodyMenu();
		// }
		initBodyMenuByAction();
		return pmBody;
	}

	/**
	 * 创建日期:(2003-4-7 18:05:42)
	 * 
	 * @return nc.ui.pub.beans.UIPopupMenu
	 */
	public nc.ui.pub.beans.UIPopupMenu getPmBodyHead() {
		if (pmBodyHead != null)
			return pmBodyHead;
		return getTable().getHeaderPopupMenu();
		// return pmBodyHead;
	}

	// 得到参数VO
	public BillRendererVO getRendererVO() {
		return m_RendererVO;
	}

	public JViewport getRowNO() {
		return rowNO;
	}

	/**
	 * 创建日期:(01-2-28 9:08:39)
	 * 
	 * @return ufbill.BillTable
	 */
	public BillTable getRowNOTable() {
		if (rowNOTable == null)
			rowNOTable = createDefaultRowNOTable();
		return rowNOTable;
	}

	/**
	 * 判断列是否显示. 创建日期:(2001-6-4 15:06:20)
	 * 
	 * @param strKey
	 *            java.lang.String
	 * @return boolean
	 */
	public TableColumn getShowCol(String strKey) {
		if (hShowCol == null)
			return null;
		if (hShowCol.containsKey(strKey))
			return (TableColumn) hShowCol.get(strKey);
		return null;
	}

	// // 得到负数是否显示红字
	// public boolean getShowRed() {
	// return m_RendererVO.isShowRed();
	// }

	// 得到是否显示千分位
	public boolean getShowThMark() {
		return m_RendererVO.isShowThMark();
	}

	/**
	 * 获得表. 创建日期:(01-2-28 9:08:39)
	 * 
	 * @return ufbill.BillTable
	 */
	public BillTable getTable() {
		if (mainTable == null) {
			mainTable = createDefaultTable();
			// 添加合计行
			if (m_bTotalRow) {
				setFixRowNOShow(true);
				// setFixRowView(getFixRowTable());
				// setFixRowNOShow()
				// fixRowTable.setColumnModel(mainTable.getColumnModel());
				// updateUI();
			}
			updateTablesColumnModelAndSelectionModel();
		}
		return mainTable;
	}

	/**
	 * 创建日期:(2003-3-7 9:27:27)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getTableCode() {
		return tableCode;
	}

	/**
	 * 创建日期:(01-2-28 9:08:39)
	 * 
	 * @return ufbill.BillTable
	 */
	public BillModel getTableModel() {
		if (m_bmModel == null)
			return m_bmModel = createDefaultTableModel();
		return m_bmModel;
	}

	/**
	 * 创建日期:(2003-3-7 9:49:12)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getTableName() {
		return tableName;
	}

	/**
	 * 判断列是否隐藏. 创建日期:(2001-6-4 15:06:20)
	 * 
	 * @param strKey
	 *            java.lang.String
	 * @return boolean
	 */
	public boolean hasHideCol(String strKey) {
		if (hHideCol == null)
			return false;
		if (hHideCol.containsKey(strKey))
			return true;
		return false;
	}

	/**
	 * 判断列是否显示. 创建日期:(2001-6-4 15:06:20)
	 * 
	 * @param strKey
	 *            java.lang.String
	 * @return boolean
	 */
	public boolean hasShowCol(String strKey) {
		if (hShowCol == null)
			return false;
		if (hShowCol.containsKey(strKey))
			return true;
		return false;
	}

	/**
	 * 隐藏表体列. 创建日期:(2001-5-29 9:13:20)
	 * 
	 * @param strKey
	 *            java.lang.String
	 */
	public void hideTableCol(String strKey) {
		if (hasHideCol(strKey))
			return;
		if (getTableModel().getBodyColByKey(strKey) < 0)
			return;
		BillItem item = getBillItems()[getTableModel().getBodyColByKey(strKey)];
		TableColumn tclCol;
		try {
			tclCol = getShowCol(strKey);
			// tclCol = getTable().getColumn(tclCol.getIdentifier());
			if (tclCol != null) {
				TableColumnModel cm = getTable().getColumnModel();
				cm.removeColumn(tclCol);
				item.setShow(false);
				// 保存
				if (hHideCol == null)
					hHideCol = new Hashtable<String, TableColumn>();
				hHideCol.put(strKey, tclCol);
			}
		} catch (Throwable e) {
			Logger.debug(strKey + "列已被隐藏!");
		}
	}

	/**
	 * 创建日期:(2003-3-18 11:46:40)
	 */
	private void initialize() {
		// ActionListener listener = new LockActionListener();
		// miLock.addActionListener(listener);
		// miUnLock.addActionListener(listener);
		getHorizontalScrollBar().setFocusTraversalKeysEnabled(false);
		getVerticalScrollBar().setFocusTraversalKeysEnabled(false);

		// addEditAction(getActionManager().getBillTableAction(ADDLINE));
		// addEditAction(getActionManager().getBillTableAction(DELLINE));
		// addEditAction(getActionManager().getBillTableAction(INSERTLINE));
		// addEditAction(getActionManager().getBillTableAction(COPYLINE));
		// addEditAction(getActionManager().getBillTableAction(PASTELINE));
		// addEditAction(getActionManager().getBillTableAction(PASTELINETOTAIL));


	}

	/**
	 * 初始化Table. type in set("main","fix" ), main -- mainTable, fix -
	 * fixColtable 创建日期:(01-3-6 10:48:33)
	 */
	protected void initTable(BillTable btTable, String type) {
		BillItem[] items = getBillItems();
		if (items == null)
			return;
		btTable.createDefaultColumnsFromModel();
		TableColumnModel cm = btTable.getColumnModel();

		Hashtable<String, TableColumn> hShowCol = new Hashtable<String, TableColumn>();
		Hashtable<String, TableColumn> hHideCol = new Hashtable<String, TableColumn>();

		BillItem[] itemsorts = new BillItem[items.length];

		for (int i = 0; i < items.length; i++) {
			BillItem item = items[i];
			// item.setBillParent(this);
			TableColumn tclCol = cm.getColumn(i);
			hShowCol.put(item.getKey(), tclCol);
			itemsorts[i] = items[i];
		}

//		BillUtil.sortBillItemsByShowOrder(itemsorts);

		DefaultTableColumnModel tcm = new DefaultTableColumnModel();

		for (int i = 0; i < itemsorts.length; i++) {
			BillItem item = itemsorts[i];
			try {
				// 获得列
				TableColumn tclFixColCol = hShowCol.get(item.getKey());

				PropertyChangeListener[] listeners = tclFixColCol
						.getPropertyChangeListeners();
				for (PropertyChangeListener propertyChangeListener : listeners) {
					tclFixColCol
							.removePropertyChangeListener(propertyChangeListener);
				}

				// setCellEditor(btTable, tclFixColCol, item);

				if (tclFixColCol != null) {

					setTableCellRenderer(tclFixColCol, item);
					setHeaderRendererForeground(tclFixColCol, item);

					// cm.removeColumn(tclFixColCol);

					// 设置列宽度
					int width = item.getWidth();
					if (item.getDataType() != IBillItem.UNSET) {
						java.awt.FontMetrics fm = btTable.getTableHeader()
								.getFontMetrics(
										btTable.getTableHeader().getFont());
						int len = fm.stringWidth(item.getName());
						width = Math.max(width, len + 2);
					}

					tclFixColCol.setWidth(width);

					if (!item.isShow() || item.getDataType() == IBillItem.BLANK) {
						// cm.removeColumn(tclFixColCol);
						hShowCol.remove(item.getKey());
						hHideCol.put(item.getKey(), tclFixColCol);
					} else {
						// cm.addColumn(tclFixColCol);
						tcm.addColumn(tclFixColCol);
						tclFixColCol.addPropertyChangeListener(ccl);
					}
				}

			} catch (Throwable e) {
				Logger.error(item.getKey() + "表体设置失败!", e);
			}
		}
		btTable.setColumnModel(tcm);
		for (int i = 0; i < btTable.getColumnCount(); i++)
			btTable.sizeColumnsToFit(i);
		if ("main".equals(type)) {
			this.hShowCol = hShowCol;
			this.hHideCol = hHideCol;
		}

	}

	private HashMap<Integer, TableCellRenderer> defaultRenderersByItemType = new HashMap<Integer, TableCellRenderer>();

	private void setTableCellRenderer(TableColumn tclFixColCol, BillItem item) {

		int type = -1;

		switch (item.getDataType()) {
		case BillItem.BOOLEAN:

			type = BillItem.BOOLEAN;

			if (defaultRenderersByItemType.get(type) == null)
				defaultRenderersByItemType.put(type,
						new BillTableBooleanCellRenderer());

			break;
		case BillItem.MULTILANGTEXT:
			type = BillItem.MULTILANGTEXT;

			if (defaultRenderersByItemType.get(type) == null)
				defaultRenderersByItemType.put(type,
						new BillTableMultiLangComboxCellRenderer());

			break;
		case BillItem.TEXTAREA:
			if (item.getRefType() != null && item.getRefType().endsWith("Y")) {

				type = BillItem.TEXTAREA;
				if (defaultRenderersByItemType.get(type) == null)
					defaultRenderersByItemType.put(type,
							new BillTableTextAreaRenderer());

			}
			break;
		
		case BillItem.ATTACHMENT:
			type = BillItem.ATTACHMENT;

			if (defaultRenderersByItemType.get(type) == null)
				defaultRenderersByItemType.put(type,
						new AccessoryBillTableCellRenderer());	
			
		default:

			if (defaultRenderersByItemType.get(type) == null)
				defaultRenderersByItemType.put(type,
						new BillTableCellRenderer());
			break;
		}

		TableCellRenderer cellRenderer = defaultRenderersByItemType.get(type);

		tclFixColCol.setCellRenderer(cellRenderer);
	}

	/**
	 * 插入行.
	 */
	public boolean insertLine() {
		BillScrollPane bsp = this;
		int row = bsp.getTable().getSelectedRow();
		if (row >= 0) {
			return insertLine(row, 1);
		}
		return false;
	}

	/**
	 * 插入行.
	 */
	public boolean insertLine(final int index, int rowCount) {
		boolean succeed = false;
		BillScrollPane bsp = this;
		BillModel bm = bsp.getTableModel();
		if (index >= 0 && index < bm.getRowCount() && rowCount > 0) {
			boolean isdo = true;
			if (bsp.getBillActionListener() != null)
				isdo = bsp.getBillActionListener().onEditAction(INSERTLINE);
			if (isdo) {
				final int col = bsp.getTable().getSelectedColumn();

				for (int i = 0; i < rowCount; i++) {
					bm.insertRow(index);
				}
				if (col > -1)
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							getTable()
									.changeSelection(index, col, false, false);
						}

					});

				succeed = true;
			}
		}
		return succeed;
	}

	/**
	 * 创建日期:(2004-6-30 11:41:59)
	 * 
	 * @return boolean
	 */
	public boolean isAutoCallAddLineWhenFocusGainedAndZeroRows() {
		return autoCallAddLineWhenFocusGainedAndZeroRows;
	}

	/**
	 * 创建日期:(2003-4-7 17:35:24)
	 * 
	 * @return boolean
	 */
	public boolean isBBodyHeaderMenuShow() {
		return bBodyHeaderMenuShow;
	}

	/**
	 * 创建日期:(2003-4-7 17:35:24)
	 * 
	 * @return boolean
	 */
	public boolean isBBodyMenuShow() {
		return bBodyMenuShow;
	}

	/**
	 * 创建日期:(2002-11-08 9:53:31)
	 * 
	 * @return boolean
	 */
	public boolean isCard() {
		return m_bIsCard;
	}

	/**
	 * 创建日期:(01-2-28 9:08:39)
	 * 
	 * @return ufbill.BillTable
	 */
	public boolean isLockCol() {
		return m_bLockCol;
	}

	/**
	 * 创建日期:(2003-9-2 15:11:21)
	 * 
	 * @return boolean
	 */
	public boolean isPageNavigationBarVisible() {
		return m_bShowPageNavigationBar;
	}

	/**
	 * 创建日期:(01-2-28 9:08:39)
	 * 
	 * @return ufbill.BillTable
	 */
	public boolean isTatolRow() {
		return m_bTotalRow;
	}

	/**
	 * 锁定列
	 */
	public void lockTableCol() {
		lockTableCol(getColumnCurrentHeader());
	}

	/**
	 * 。 锁定列
	 */

	// 2009-9-1 liujian 用bq的活动修改lockTableCol和unLockTableCol以支持锁定鼠标所选定的列，而不是前一列
	public void lockTableCol(int lockCol) {
		if (m_bLockCol)
			return;

		// setTotalRowShow(true);

		if (lockCol < 0 || lockCol >= getTable().getColumnCount() - 1)
			return;

		m_iLockCol = lockCol;

		Point p = getViewport().getViewPosition();

		int viewWidth = getViewport().getWidth();

		// int iMaxWidth = getViewport().getWidth() / 3 * 2;

		TableColumnModel cm = getTable().getColumnModel();
		TableColumnModel fcm = getFixColTable().getColumnModel();

		// 移去头表行
		for (int i = getFixColTable().getColumnCount() - 1; i > lockCol; i--) {
			fcm.removeColumn(fcm.getColumn(i));
		}

		// 移去主表行
		int iWidth = 0;
		TableColumn tcol;
		for (int i = 0; i <= lockCol; i++) {
			tcol = cm.getColumn(0);
			iWidth = iWidth + tcol.getPreferredWidth();
			fcm.getColumn(i).setPreferredWidth(tcol.getPreferredWidth());
			fcm.getColumn(i).setCellRenderer(tcol.getCellRenderer());
			cm.removeColumn(tcol);
		}

		int showwidth = iWidth - p.x;

		if (viewWidth > 0) {
			if (showwidth >= viewWidth - 50) {
				showwidth = viewWidth / 3 * 2;
				p.x = iWidth - showwidth;
			}
		}

		getFixColTable().setPreferredScrollableViewportSize(
				new java.awt.Dimension(showwidth, 10));

		// if(iWidth < iMaxWidth || iMaxWidth == 0)
		// getFixColTable().setPreferredScrollableViewportSize(new
		// java.awt.Dimension(iWidth, 10));
		// else
		// getFixColTable().setPreferredScrollableViewportSize(new
		// java.awt.Dimension(iMaxWidth, 10));

		setRowHeaderView(getFixColTable());

		JViewport v = createViewport(false);

		v.setView(getFixColTable().getTableHeader());

		setCorner(JScrollPane.UPPER_LEFT_CORNER, v);

		if (getCorner(USER_ROW) != null) {
			BillTable table = getFixRowLockUserTable();
			table.setColumnModel(getFixColTable().getColumnModel());

			if (userRowLock == null)
				userRowLock = createViewport(true);
			userRowLock.setView(table);
			add(USER_ROW_LOCK, userRowLock);
			table.setPreferredScrollableViewportSize(getFixColTable()
					.getPreferredScrollableViewportSize());
		}
		if (getCorner(FIX_ROW) != null) {
			BillTable table = getFixRowLockTotalTable();
			table.setColumnModel(getFixColTable().getColumnModel());

			if (fixRowLock == null)
				fixRowLock = createViewport(true);
			fixRowLock.setView(table);
			add(FIX_ROW_LOCK, fixRowLock);

			table.setPreferredScrollableViewportSize(getFixColTable()
					.getPreferredScrollableViewportSize());
		}

		m_bLockCol = true;

		// //修改菜单
		// miLock.setText(nc.ui.ml.NCLangRes.getInstance().getStrByID("_Bill",
		// "UPP_Bill-000010")/*
		// * @res
		// * "解锁"
		// */);
		// miLock.setName(nc.ui.ml.NCLangRes.getInstance().getStrByID("_Bill",
		// "UPP_Bill-000010")/*
		// * @res
		// * "解锁"
		// */);
		setTableBorder();

		// //同步显示
		// if((iWidth - iMaxWidth) > 0 && iMaxWidth != 0){
		// Point p = new Point(iWidth - iMaxWidth,0);
		v.setViewPosition(p);
		getRowHeader().setViewPosition(p);
		if (getCorner(USER_ROW) != null) {
			userRowLock.setViewPosition(p);
		}
		if (getCorner(FIX_ROW) != null) {
			fixRowLock.setViewPosition(p);
		}
		// }

		getViewport().setViewPosition(new Point(0, p.y));

		if (billTableLockListener != null) {
			BillTableLockEvent e = new BillTableLockEvent(this, true);
			billTableLockListener.valueChanged(e);
		}
	}

	/**
	 * 创建日期:(2003-7-2 17:15:00)
	 * 
	 * @param e
	 *            java.awt.event.MouseEvent
	 */
	private void mouseReleasedTableCell(MouseEvent e) {
		if (e.isPopupTrigger()) {
			BillScrollPane bsp = this;
			if (!bsp.isBBodyMenuShow())
				return;
			UITable table = getTable();
			if (table.getRowCount() == 0)
				return;
			Point p = e.getPoint();
			int col = table.columnAtPoint(p);
			int row = table.rowAtPoint(p);
			if (!(col < 0 || row < 0)) {
				int[] cols = table.getSelectedColumns();
				int[] rows = table.getSelectedRows();
				if (cols == null || rows == null
						|| java.util.Arrays.binarySearch(cols, col) < 0
						|| java.util.Arrays.binarySearch(rows, row) < 0) {
					table.setColumnSelectionInterval(col, col);
					table.setRowSelectionInterval(row, row);
				}
			}
			// Component c = this;
			// boolean isedit = true;
			// while (true) {
			// c = c.getParent();
			// if (c instanceof BillCardPanel) {
			// isedit = ((BillCardPanel) c).getBillData().getEnabled();
			// break;
			// }
			// if (c instanceof BillListPanel) {
			// isedit = ((BillListPanel) c).getBillListData().isEnabled();
			// break;
			// }
			// if (c instanceof Frame)
			// break;
			// }
			// if (isedit || getTableModel().isRowSelectMode())
			{
				if (getPmBody() != null)
					getPmBody().show((Component) e.getSource(), e.getX(),
							e.getY());
			}
		} else if ((e.getModifiers() & InputEvent.SHIFT_MASK) != 0
				&& (e.getModifiers() & InputEvent.ALT_MASK) != 0) {
			fillValueFromStartCell();
		}
	}

	/**
	 * 创建日期:(2003-6-19 16:30:38)
	 */
	void newMethod() {
	}

	// /**
	// * 创建日期:(2003-3-18 14:08:22)
	// */
	// public void onLock(LockActionListener listener,
	// java.awt.event.ActionEvent e) {
	// if (!isLockCol()) {
	// lockTableCol();
	// } else {
	// unlockTableCol();
	// }
	// }
	//
	// /**
	// * 创建日期:(2003-6-11 10:44:14)
	// */
	// private void onMenuItemClick(ActionEvent e) {
	// BillScrollPane bsp = this;
	// UIMenuItem item = (UIMenuItem) e.getSource();
	// if (bsp.getBillBodyMenuListener() != null)
	// bsp.getBillBodyMenuListener().onMenuItemClick(e);
	// else {
	// if (item == bsp.getMiInsertLine()) {
	// insertLine();
	// } else if (item == bsp.getMiAddLine()) {
	// addLine();
	// } else if (item == bsp.getMiDelLine()) {
	// delLine();
	// } else if (item == bsp.getMiCopyLine()) {
	// copyLine();
	// } else if (item == bsp.getMiPasteLine()) {
	// pasteLine();
	// } else if (item == bsp.getMiPasteLineToTail()) {
	// pasteLineToTail();
	// } else if (item == bsp.getMiAllSelctRow()) {
	// selectAllTableRow();
	// } else if (item == bsp.getMiCancelAllSelctRow()) {
	// cancelSelectAllTableRow();
	// }
	// }
	// }

	/**
	 * 粘贴行.
	 */
	public void pasteLine() {
		BillScrollPane bsp = this;
		boolean isdo = true;
		if (bsp.getBillActionListener() != null)
			isdo = bsp.getBillActionListener().onEditAction(PASTELINE);
		if (isdo) {
			UITable table = bsp.getTable();
			int row = table.getSelectedRow();
			//兼容下如果全部删除了行的情况
			if (row ==-1){
				row = 0;
			}
			int rowlength = bsp.getTableModel().getPasteLineNumer();
			if (rowlength > 0) {
				bsp.getTableModel().pasteLine(row);
				if (table.getSelectedRows().length>0) {
					row = row + rowlength;
					table.setRowSelectionInterval(row, row);
				}
				else
				{
			    	table.setRowSelectionInterval(0, 0);
				}
			}
			table.updateUI();
		}
	}


	public void splitCellBySelect() {
		if (getTableModel().getCellSpan() != null)
			getTableModel().getCellSpan().split(getTable().getSelectedRow(),
					getTable().getSelectedColumn());
		updateUI();
	}

	/**
	 * 粘贴行.
	 */
	public void pasteLineToTail() {
		BillScrollPane bsp = this;
		boolean isdo = true;
		if (bsp.getBillActionListener() != null)
			isdo = bsp.getBillActionListener().onEditAction(PASTELINETOTAIL);
		if (isdo) {
			bsp.getTableModel().pasteLineToTail();
		}
		updateUI();
	}

	/**
	 * 创建日期:(2003-3-6 15:26:12)
	 */
	public void removeBillActionListener() {
		lba = null;
	}

	// /**
	// * 创建日期:(2003-3-6 15:29:11)
	// */
	// public void removeBillBodyMenuListener() {
	// lbm = null;
	// }

	/**
	 * 移除编辑监听. 创建日期:(2001-3-23 2:20:34)
	 * 
	 * @param l
	 *            BillEditListener
	 */
	public void removeEditListener() {
		this.el = null;
	}

	/**
	 * 移除编辑监听. 创建日期:(2001-3-23 2:20:34)
	 * 
	 * @param l
	 *            BillEditListener
	 */
	public void removeEditListener2() {
		this.el2 = null;
	}

	/**
	 * 移除鼠标监听. 创建日期:(2001-3-23 2:20:34)
	 * 
	 * @param ml
	 *            BillTableMouseListener
	 */
	public void removeMouseListener() {
		this.ml = null;
	}

	public void removeTableSortListener() {
		getTable().removeSortListener();
		// if (sortListener != null) {
		// javax.swing.table.JTableHeader jth = getTable().getTableHeader();
		// jth.removeMouseListener(sortListener);
		// }
	}

	/**
	 * 排序后选择显示. 创建日期:(2001-11-22 13:32:58) 排序算法变化,不用调用此方法也可维护选择行
	 * 
	 * @deprecated
	 */
	public void reSelect(int[] oldSortIndex, int oldrow) {
		// if (oldSortIndex == null) {
		// oldSortIndex = new int[getTableModel().getRowCount()];
		// for (int i = 0; i < oldSortIndex.length; i++) {
		// oldSortIndex[i] = i;
		// }
		// }
		// if (oldrow > -1 && getTableModel().getSortIndex() != null) {

		// int[] array = getTableModel().getSortIndex();
		// int[] array1 = new int[array.length];
		// for (int i = 0; i < array.length; i++) {
		// array1[array[i]] = i;
		// }
		// // for(int i=0;i<array.length;i++){

		// // }
		// int newrow = array1[oldSortIndex[oldrow]];

		// getTable().setRowSelectionInterval(newrow, newrow);
		// getTable().scrollRectToVisible(getTable().getCellRect(newrow, 0,
		// false));
		// }
		getTable().setRowSelectionInterval(oldrow, oldrow);
		getTable()
				.scrollRectToVisible(getTable().getCellRect(oldrow, 0, false));
	}

	/**
	 * 创建日期:(2003-6-19 16:30:38)
	 */
	public void resetCellBackGround() {
		resetCellColor("back");
	}

	/**
	 * 创建日期:(2003-6-19 16:30:38)
	 */
	private void resetCellColor(String type) {
		if (getTable().getRowCount() <= 0)
			return;
		getTableModel().clearCellColor(type);
		if (lockTable != null)
			lockTable.repaint();
		getTable().repaint();
	}

	/**
	 * 创建日期:(2003-6-19 16:30:38)
	 */
	public void resetCellForeGround() {
		resetCellColor("fore");
	}

	/**
	 * 重新设置表绘制器 创建日期:(01-2-28 9:08:39)
	 * 
	 * @deprecated
	 */
	public void resetTableCellRenderer(String strkey) {
		BillModel model = getTableModel();
		int itemIndex = model.getItemIndex(strkey);
		BillItem item = getBillItems()[itemIndex];
		int col = convertModelColumnIntoViewColumn(itemIndex);
		if (col < 0)
			return;
		TableColumn tclFixColCol = getTable().getColumnModel().getColumn(col);
		// tclFixColCol.setCellRenderer(new BillTableCellRenderer(item,
		// m_RendererVO));

		setTableCellRenderer(tclFixColCol, item);

	}

	// 设置是否自动增行
	public void setAutoAddLine(boolean newValue) {
		m_bAutoAddLine = newValue;
		setKeyAutoAddLine(newValue);
	}

	// 设置是否自动增行
	public boolean isAutoAddLine() {
		return m_bAutoAddLine;
	}

	// 设置是否自动增行
	private void setKeyAutoAddLine(boolean newValue) {
		m_bKeyAutoAddLine = newValue;
	}

	// 设置是否自动增行
	private boolean isKeyAutoAddLine() {
		return m_bKeyAutoAddLine;
	}

	/**
	 * 创建日期:(2004-6-30 11:41:59)
	 * 
	 * @param newAutoCallAddLineWhenFocusGainedAndZeroRows
	 *            boolean
	 */
	public void setAutoCallAddLineWhenFocusGainedAndZeroRows(
			boolean newAutoCallAddLineWhenFocusGainedAndZeroRows) {
		autoCallAddLineWhenFocusGainedAndZeroRows = newAutoCallAddLineWhenFocusGainedAndZeroRows;
	}

	/**
	 * 创建日期:(2003-4-7 17:35:24)
	 * 
	 * @param newBBodyHeaderMenuShow
	 *            boolean
	 */
	public void setBBodyHeaderMenuShow(boolean newBBodyHeaderMenuShow) {
		bBodyHeaderMenuShow = newBBodyHeaderMenuShow;
	}

	/**
	 * 创建日期:(2003-4-7 17:35:24)
	 * 
	 * @param newBBodyMenuShow
	 *            boolean
	 */
	public void setBBodyMenuShow(boolean newBBodyMenuShow) {
		bBodyMenuShow = newBBodyMenuShow;
	}

	/**
	 * @param billParent
	 *            The billParent to set.
	 * 
	 *            2011-5-5 支持业务组 修改可见性为private-->public
	 */
	public void setBillParent(Component billParent) {
		this.billParent = billParent;
	}

	/**
	 * 创建日期:(2003-10-14 15:31:57)
	 * 
	 * @param newBillTableEnterKeyControler
	 *            nc.ui.pub.bill.BillTableEnterKeyControler
	 */
	public void setBillTableEnterKeyControler(
			BillTableEnterKeyControler newBillTableEnterKeyControler) {
		billTableEnterKeyControler = newBillTableEnterKeyControler;
	}

	/**
	 * 创建日期:(2003-6-19 16:30:38)
	 */
	public void setCellBackGround(int row, String itemkey, Color color) {
		setCellColor(row, itemkey, color, ColoredCell.BACKCOLOR);
	}

	/**
	 * 创建日期:(2003-6-19 16:30:38)
	 */
	public void cellShowWarning(int row, String itemkey) {
		getTableModel().cellShowWarning(row, itemkey);
	}

	/**
	 * 创建日期:(2003-6-19 16:30:38)
	 */
	private void setCellColor(int row, String itemkey, Color color, int type) {
		if (itemkey == null || (itemkey = itemkey.trim()).length() == 0)
			return;
		if (getTable().getRowCount() <= 0)
			return;
		int modelcol = getTableModel().getBodyColByKey(itemkey);
		if (modelcol < 0)
			return;
		getTableModel().setCellColor(row, itemkey, color, type);
		int col = getTable().convertColumnIndexToModel(0);
		if (modelcol >= col)
			getTable().repaint();
		else if (getLockCol() > 0)
			getFixColTable().repaint();
	}

	// /**
	// * 初始化Table. type in set("main","fix" ), main -- mainTable, fix -
	// * fixColtable 创建日期:(01-3-6 10:48:33)
	// */
	// protected void setCellEditor(BillTable btTable, TableColumn tclCol,
	// BillItem item) {
	// if (btTable == null || tclCol == null || item == null)
	// return;
	// // 初始化编辑控件
	// switch (item.getDataType()) {
	// case BillItem.BOOLEAN: {
	// tclCol.setCellEditor(new BillCellEditor((UICheckBox)
	// item.getComponent()));
	// break;
	// }
	// case BillItem.COMBO: {
	// tclCol.setCellEditor(new BillCellEditor((UIComboBox)
	// item.getComponent()));
	// break;
	// }
	// default: {
	// if (item.getComponent() instanceof UIRefPane) {
	// ((UIRefPane) item.getComponent()).setTable(btTable);
	// tclCol.setCellEditor(new BillCellEditor((UIRefPane)
	// item.getComponent()));
	// }
	// break;
	// }
	// }
	// // 绘制器
	// }

	/**
	 * 创建日期:(2003-6-19 16:30:38)
	 */
	public void setCellForeGround(int row, String itemkey, Color color) {
		setCellColor(row, itemkey, color, ColoredCell.FORECOLOR);
	}

	/**
	 * Adds a child that will appear in one of the scroll panes corners, if
	 * there's room. For example with both scrollbars showing (on the right and
	 * bottom edges of the scrollpane) the lower left corner component will be
	 * shown in the space between ends of the two scrollbars. Legal values for
	 * the <b>key </b> are:
	 * <ul>
	 * <li>JScrollPane.LOWER_LEFT_CORNER
	 * <li>JScrollPane.LOWER_RIGHT_CORNER
	 * <li>JScrollPane.UPPER_LEFT_CORNER
	 * <li>JScrollPane.UPPER_RIGHT_CORNER
	 * </ul>
	 * <p>
	 * Although "corner" isn't doesn't match any beans property signature,
	 * PropertyChange events are generated with the property name set to the
	 * corner key.
	 * 
	 * @param key
	 *            identifies which corner the component will appear in
	 * @param corner
	 *            any component
	 */
	public void setCorner(String key, Component corner) {
		Component old = null;
		boolean bs = false;

		if (key.equals(ROW_NO_HEADER)) {
			old = rowNOHeader;
			rowNOHeader = (JViewport) corner;
		} else if (key.equals(ROW_NO)) {
			old = rowNO;
			rowNO = (JViewport) corner;
		} else if (key.equals(USER_ROW)) {
			old = userRow;
			userRow = (JViewport) corner;

		} else if (key.equals(USER_ROW_NO)) {
			old = userRowNO;
			userRowNO = (JViewport) corner;

		} else if (key.equals(USER_ROW_LOCK)) {
			old = userRowLock;
			userRowLock = (JViewport) corner;
		} else if (key.equals(FIX_ROW)) {
			old = fixRow;
			fixRow = (JViewport) corner;
		} else if (key.equals(FIX_ROW_NO)) {
			old = fixRowNO;
			fixRowNO = (JViewport) corner;
		} else if (key.equals(FIX_ROW_LOCK)) {
			old = fixRowLock;
			fixRowLock = (JViewport) corner;
		} else {
			bs = true;
			super.setCorner(key, corner);
		}

		if (!bs) {
			if (old != null) {
				remove(old);
			}
			if (corner != null) {
				add(corner, key);
			}
			firePropertyChange(key, old, corner);
			revalidate();
			repaint();
		}
	}

	/**
	 * 创建日期:(01-2-28 9:08:39)
	 * 
	 * @return ufbill.BillTable
	 */
	public void setFixColTable(BillTable newFixColTable) {
		lockTable = newFixColTable;
	}

	/**
	 * If an old FixRow exists, remove it. If the new FixRow isn't null, sync
	 * the y coordinate of the its viewPosition with the viewport (if there is
	 * one) and then add it to the ScrollPane.
	 * <p>
	 * Most applications will find it more convenient to use setFixRowView to
	 * add a row header component and its viewport to the scrollpane.
	 * 
	 * @see #getFixRow
	 * @see #setFixRowView
	 */
	public void setFixRow(JViewport fixRow) {
		setCorner(FIX_ROW, fixRow);
	}

	/**
	 * 创建日期:(01-2-28 9:08:39)
	 * 
	 * @return ufbill.BillTable
	 */
	public void setFixRowTable(BillTable newFixRowTable) {
		fixRowTable = newFixRowTable;
	}

	public void setFixRowView(Component view) {
		if (getFixRow() == null && view != null) {
			setCorner(FIX_ROW, createViewport(view, false));
		}

	}

	private TableCellRenderer defaultTableCellRenderer = null;

	// protected void setHeaderRendererForeground(TableColumn tc, Color
	// rgb,boolean isnull) {
	//    	
	// TableCellRenderer renderer = tc.getHeaderRenderer();
	// if (renderer == null || !(renderer instanceof
	// IBillTableHeaderCellRender)) {
	//    	  
	// renderer = getDefaultTableCellRenderer();
	//          	
	// tc.setHeaderRenderer(renderer);
	//       	  
	// }
	// if (rgb != null && renderer instanceof IBillTableHeaderCellRender) {
	// ((IBillTableHeaderCellRender) renderer).setForeground(rgb);
	// }
	//
	// }

	private TableCellRenderer getDefaultTableCellRenderer() {
		if (defaultTableCellRenderer == null)
			defaultTableCellRenderer = creatDefaultTableCellRenderer();

		return defaultTableCellRenderer;
	}

	protected TableCellRenderer creatDefaultTableCellRenderer() {
		return new DefaultTableHeaderCellRenderer();
	}

	/**
	 * 创建日期:(2003-4-1 16:20:55)
	 * 
	 * @param tc
	 *            javax.swing.table.TableColumn
	 * @param item
	 *            nc.ui.pub.bill.BillItem
	 */
	protected void setHeaderRendererForeground(TableColumn tc, BillItem item) {
		// if(item.getDataType() == IBillItem.UNSET) {
		// tc.setHeaderValue("ERROR");
		// setHeaderRendererForeground(tc, Color.RED,false);
		// }
		// else
		// setHeaderRendererForeground(tc,
		// ColorConstants.getColor(item.getForeground()),item.isNull());
		TableCellRenderer renderer = tc.getHeaderRenderer();
		if (renderer == null
				|| !(renderer instanceof IBillTableHeaderCellRender)) {

			renderer = getDefaultTableCellRenderer();

			tc.setHeaderRenderer(renderer);

		}
	}

	/**
	 * 创建日期:(2002-11-08 9:53:31)
	 * 
	 * @param newM_bIsCard
	 *            boolean
	 */
	public void setIsCard(boolean newIsCard) {
		m_bIsCard = newIsCard;
	}

	/**
	 * 创建日期:(2003-7-4 15:04:19)
	 * 
	 * @param newMax_lockCount
	 *            int
	 */
	public void setMax_lockCount(int newMax_lockCount) {
		max_lockCount = newMax_lockCount;
	}

	// /**
	// * 创建日期:(2003-4-7 18:09:33)
	// *
	// * @param newMiAddLine
	// * nc.ui.pub.beans.UIMenuItem
	// */
	// public void setMiAddLine(nc.ui.pub.beans.UIMenuItem newMiAddLine) {
	// getActionManager().setBillTableMenuItem(ADDLINE, newMiAddLine);
	// }

	// /**
	// * 创建日期:(2003-4-7 18:09:33)
	// *
	// * @param newMiBody
	// * nc.ui.pub.beans.UIMenuItem[]
	// */
	// public void setMiBody(nc.ui.pub.beans.UIMenuItem[] newMiBody) {
	// miBody = newMiBody;
	// }
	//
	// /**
	// * 创建日期:(2003-4-7 18:09:33)
	// *
	// * @param newMiCopyLine
	// * nc.ui.pub.beans.UIMenuItem
	// */
	// public void setMiCopyLine(nc.ui.pub.beans.UIMenuItem newMiCopyLine) {
	// getActionManager().setBillTableMenuItem(COPYLINE, newMiCopyLine);
	// }
	//
	// /**
	// * 创建日期:(2003-4-7 18:09:33)
	// *
	// * @param newMiDelLine
	// * nc.ui.pub.beans.UIMenuItem
	// */
	// public void setMiDelLine(nc.ui.pub.beans.UIMenuItem newMiDelLine) {
	// getActionManager().setBillTableMenuItem(DELLINE, newMiDelLine);
	// }
	//
	// /**
	// * 创建日期:(2003-4-7 18:09:33)
	// *
	// * @param newMiInsertLine
	// * nc.ui.pub.beans.UIMenuItem
	// */
	// public void setMiInsertLine(nc.ui.pub.beans.UIMenuItem newMiInsertLine) {
	// getActionManager().setBillTableMenuItem(INSERTLINE, newMiInsertLine);
	// }
	//
	// /**
	// * 创建日期:(2003-4-7 18:08:16)
	// *
	// * @param newMiLock
	// * nc.ui.pub.beans.UIMenuItem
	// */
	// public void setMiLock(nc.ui.pub.beans.UIMenuItem newMiLock) {
	// getActionManager().setBillTableMenuItem(LOCK, newMiLock);
	// }
	//
	// /**
	// * 创建日期:(2003-4-7 18:09:33)
	// *
	// * @param newMiPasteLine
	// * nc.ui.pub.beans.UIMenuItem
	// */
	// public void setMiPasteLine(nc.ui.pub.beans.UIMenuItem newMiPasteLine) {
	// getActionManager().setBillTableMenuItem(PASTELINE, newMiPasteLine);
	// }

	/**
	 * 设置负数是否显示符号
	 * 
	 * @deprecated
	 */
	public void setNegativeSign(boolean newValue) {
		m_RendererVO.setNegativeSign(newValue);

		BillItem[] items = getBillItems();
		if ((items != null) && (items.length > 0))
			for (int i = 0; i < items.length; i++) {
				BillItem item = items[i];
				if ((item.getDataType() == BillItem.INTEGER)
						|| (item.getDataType() == BillItem.DECIMAL || item
								.getDataType() == IBillItem.MONEY))
					if (item.isShow() && item.getNumberFormat() != null) {
						item.getNumberFormat().setNegativeSign(newValue);
					}
			}
	}

	/**
	 * 创建日期:(2003-9-2 15:10:37)
	 * 
	 * @param showflag
	 *            boolean
	 */
	public void setPageNavigationBarVisible(boolean showflag) {
		if (showflag == m_bShowPageNavigationBar)
			return;
		m_bShowPageNavigationBar = showflag;
		if (showflag) {
			getPageNavigationBarVP().setView(getPageNavigationBar());
			getPageNavigationBarVP().setPreferredSize(
					new Dimension(getPageNavigationBar().getWidth(),
							getPageNavigationBar().getHeight()));
			add(getPageNavigationBarVP(), PAGE_NAVIGATION_BAR);
		} else {
			getPageNavigationBarVP().setView(null);
			remove(getPageNavigationBarVP());
		}
		getPageNavigationBarVP().setVisible(showflag);
		updateUI();
		// validate();
		// repaint();
	}

	/**
	 * 创建日期:(2003-4-7 18:05:42)
	 * 
	 * @param newPmBody
	 *            nc.ui.pub.beans.UIPopupMenu
	 */
	public void setPmBody(nc.ui.pub.beans.UIPopupMenu newPmBody) {
		pmBody = newPmBody;
	}

	/**
	 * 创建日期:(2003-4-7 18:05:42)
	 * 
	 * @param newPmBodyHead
	 *            nc.ui.pub.beans.UIPopupMenu
	 */
	public void setPmBodyHead(nc.ui.pub.beans.UIPopupMenu newPmBodyHead) {
		pmBodyHead = newPmBodyHead;
	}

	public void setRowNO(JViewport rowNO) {
		JViewport old = getRowNO();
		this.rowNO = rowNO;
		if (rowNO != null) {
			add(rowNO, ROW_NO);
		} else if (old != null) {
			remove(old);
		}
		firePropertyChange("rowNO", old, rowNO);
		revalidate();
		repaint();
	}

	/**
	 * 设置是否显示行号. 创建日期:(01-2-28 9:08:39)
	 * 
	 * @return ufbill.BillTable
	 */
	public void setRowNOShow(boolean newValue) {
		// setRowNOShow(newValue, 20, 20);
		m_bRowNO = newValue;
		if (newValue) {
			setRowNOView(getRowNOTable());
			// getTable().setHeaderHeight(iHeightForNewVersion);
			JViewport rowNOHeader = createViewport(true);
			rowNOHeader.setView(getRowNOTable().getTableHeader());
			setCorner(ROW_NO_HEADER, rowNOHeader);
		} else {
			setRowNOView(null);
			setCorner(ROW_NO_HEADER, null);
		}
	}

	// /**
	// * 设置是否显示行号. 创建日期:(01-2-28 9:08:39)
	// *
	// * @return ufbill.BillTable
	// */
	// public void setRowNOShow(boolean newValue, int iHeight, int
	// iHeightForNewVersion) {
	// m_bRowNO = newValue;
	// if (newValue) {
	// setRowNOView(getRowNOTable());
	// // getTable().setHeaderHeight(iHeightForNewVersion);
	// JViewport rowNOHeader = createViewport(true);
	// rowNOHeader.setView(getRowNOTable().getTableHeader());
	// setCorner(ROW_NO_HEADER, rowNOHeader);
	// } else {
	// setRowNOView(null);
	// setCorner(ROW_NO_HEADER, null);
	// }
	// }

	/**
	 * 设置行号表. 创建日期:(01-2-28 9:08:39)
	 * 
	 * @return ufbill.BillTable
	 */
	public void setRowNOTable(BillTable newRowNOTable) {
		rowNOTable = newRowNOTable;
	}

	private JViewport createViewport(Component view, boolean isDefault) {
		JViewport vp = isDefault ? createViewport(true) : createViewport();
		vp.setView(view);
		return vp;
	}

	/**
	 * 设置行号 创建日期:(01-2-28 9:08:39)
	 */
	public void setRowNOView(Component view) {
		setCorner(ROW_NO, createViewport(view, false));
	}

	/**
	 * 设置显示千分位和将零显示为空串的标志. 创建日期:(01-2-28 9:08:39)
	 * 
	 * @deprecated
	 */
	public void setShowFlags(BillRendererVO newRendererVO) {
		m_RendererVO = newRendererVO;
		// resetTableNumberColCellReanderer();
		if (newRendererVO != null) {
			BillItem[] items = getBillItems();
			if ((items != null) && (items.length > 0))
				for (int i = 0; i < items.length; i++) {
					BillItem item = items[i];
					if ((item.getDataType() == BillItem.INTEGER)
							|| (item.getDataType() == BillItem.DECIMAL || item
									.getDataType() == IBillItem.MONEY))
						if (item.isShow() && item.getNumberFormat() != null) {
							item.getNumberFormat().setNegativeSign(
									newRendererVO.isNegativeSign());
							item.getNumberFormat().setShowRed(
									newRendererVO.isShowRed());
							item.getNumberFormat().setShowThMark(
									newRendererVO.isShowThMark());
							item.getNumberFormat().setShowZeroLikeNull(
									newRendererVO.isShowZeroLikeNull());
						}
				}
		}
	}

	/**
	 * 设置负数是否显示红字. 创建日期:(01-2-28 9:08:39)
	 * 
	 * @deprecated
	 */
	public void setShowRed(boolean newValue) {
		m_RendererVO.setShowRed(newValue);
		BillItem[] items = getBillItems();
		if ((items != null) && (items.length > 0))
			for (int i = 0; i < items.length; i++) {
				BillItem item = items[i];
				if ((item.getDataType() == BillItem.INTEGER)
						|| (item.getDataType() == BillItem.DECIMAL || item
								.getDataType() == IBillItem.MONEY))
					if (item.isShow() && item.getNumberFormat() != null) {
						item.getNumberFormat().setShowRed(newValue);
					}
			}
	}

	// private void resetTableNumberColCellReanderer(){
	// BillItem[] items = getBillItems();
	// if ((items != null) && (items.length > 0))
	// for (int i = 0; i < items.length; i++) {
	// BillItem item = items[i];
	// if ((item.getDataType() == BillItem.INTEGER) || (item.getDataType() ==
	// BillItem.DECIMAL))
	// if (item.isShow()) {
	// TableColumn tclFixColCol = (TableColumn) hShowCol.get(item.getKey());
	// if (tclFixColCol != null)
	// tclFixColCol.setCellRenderer(new BillTableCellRenderer(item));
	// }
	// }
	// }
	/**
	 * 设置是否显示千分位. 创建日期:(01-2-28 9:08:39)
	 * 
	 * @deprecated
	 */
	public void setShowThMark(boolean newValue) {
		m_RendererVO.setShowThMark(newValue);
		// resetTableNumberColCellReanderer();
		BillItem[] items = getBillItems();
		if ((items != null) && (items.length > 0))
			for (int i = 0; i < items.length; i++) {
				BillItem item = items[i];
				if ((item.getDataType() == BillItem.INTEGER)
						|| (item.getDataType() == BillItem.DECIMAL || item
								.getDataType() == IBillItem.MONEY))
					if (item.isShow() && item.getNumberFormat() != null) {
						item.getNumberFormat().setShowThMark(newValue);
					}
			}
	}

	/**
	 * 设置表. 创建日期:(01-2-28 9:08:39)
	 * 
	 * @return ufbill.BillTable
	 */
	public void setTable(BillTable newTable) {
		mainTable = newTable;
	}

	/**
	 * 设置表编辑器 创建日期:(01-2-28 9:08:39)
	 */
	public void setTableCellEditor(String strkey, TableCellEditor anEditor) {
		BillItem item = getBillItems()[getTableModel().getBodyColByKey(strkey)];
		TableColumn tclFixColCol = getTable().getColumn(item.getName());
		tclFixColCol.setCellEditor(anEditor);
	}

	/**
	 * 创建日期:(2003-3-7 9:27:27)
	 * 
	 * @param newTableCode
	 *            java.lang.String
	 */
	public void setTableCode(java.lang.String newTableCode) {
		tableCode = newTableCode;
	}

	/**
	 * 设置表的编辑状态.
	 */
	public void setTableEnabled(boolean newEdit) {
		getTable().setCellSelectionEnabled(newEdit);
	}

	/**
	 * 设置表模式. 创建日期:(01-2-28 9:08:39)
	 * 
	 * @return ufbill.BillTable
	 */
//	public void setTableModel(BillModel newModel) {
//		if (m_bLockCol) {
//			unlockTableCol();
//			getFixColTable().setModel(newModel);
//		}
//		if (m_bmModel != null && mainTable != null){
////			m_bmModel.removeTableModelListener(mainTable);
//			initTable(mainTable, "main");
//		}
//
//		boolean cellSelectionEnabled = false;
//		int selectionMode = 0;
//		boolean rowSelectionAllowed = false;
//
//		boolean isinit = false;
//
//		if (mainTable != null) {
//			cellSelectionEnabled = mainTable.getCellSelectionEnabled();
//			selectionMode = mainTable.getSelectionModel().getSelectionMode();
//			rowSelectionAllowed = mainTable.getRowSelectionAllowed();
//
////			isinit = true;
//		}
////		 mainTable = null;
//
//		m_bmModel = newModel;
//
//
//
//		if (isTatolRow() != m_bmModel.isNeedCalculate()) {
//			m_bmModel.setNeedCalculate(isTatolRow());
//		}
//		if (m_bRowNO) {
//			setRowNOShow(false);
//			getRowNOTable().setModel(m_bmModel.getRowNOTableModel());
//			getRowNOTable().getColumnModel().getColumn(0).setCellRenderer(
//					new RowNOCellRenderer());
//			setRowNOShow(true);
//		}
//		setViewportView(getTable());
//
//		if (isinit) {
//			getTable().setCellSelectionEnabled(cellSelectionEnabled);
//			getTable().setSelectionMode(selectionMode);
//			getTable().setRowSelectionAllowed(rowSelectionAllowed);
//		}
//
//		if (fixRowTable != null) {
//			getFixRowTable().setModel(m_bmModel.getTotalTableModel());
//		}
//
//		if (userRowTable != null) {
//			m_bmModel.setUserFixRowModel((UserFixRowTableModel) userRowTable
//					.getModel());
//		}
//
//		// initTable(getTable());
//		// add from v2.4
//		m_bmModel.setBillScrollPane(this);
//
//		// update table's columnModel and selectionModel
//		updateTablesColumnModelAndSelectionModel();
//		// revalidate
//		// revalidate();
//		invalidate();
//		validate();
//		repaint();
//	}
	
	public void setTableModel(BillModel newModel) {
		if (m_bLockCol) {
			unlockTableCol();
			getFixColTable().setModel(newModel);
		}

		if (m_bmModel != null && mainTable != null)
			m_bmModel.removeTableModelListener(mainTable);

		boolean cellSelectionEnabled = false;
		int selectionMode = 0;
		boolean rowSelectionAllowed = false;

		boolean isinit = false;

		if (mainTable != null) {
			cellSelectionEnabled = mainTable.getCellSelectionEnabled();
			selectionMode = mainTable.getSelectionModel().getSelectionMode();
			rowSelectionAllowed = mainTable.getRowSelectionAllowed();

			isinit = true;
		}

		mainTable = null;

		m_bmModel = newModel;

		if (isTatolRow() != m_bmModel.isNeedCalculate()) {
			m_bmModel.setNeedCalculate(isTatolRow());
		}
		if (m_bRowNO) {
			setRowNOShow(false);
			getRowNOTable().setModel(m_bmModel.getRowNOTableModel());
			getRowNOTable().getColumnModel().getColumn(0).setCellRenderer(
					new RowNOCellRenderer());
			setRowNOShow(true);
		}
		setViewportView(getTable());

		if (isinit) {
			getTable().setCellSelectionEnabled(cellSelectionEnabled);
			getTable().setSelectionMode(selectionMode);
			getTable().setRowSelectionAllowed(rowSelectionAllowed);
		}

		if (fixRowTable != null) {
			getFixRowTable().setModel(m_bmModel.getTotalTableModel());
		}

		if (userRowTable != null) {
			m_bmModel.setUserFixRowModel((UserFixRowTableModel) userRowTable
					.getModel());
		}

		// initTable(getTable());
		// add from v2.4
		m_bmModel.setBillScrollPane(this);

		// update table's columnModel and selectionModel
		updateTablesColumnModelAndSelectionModel();
		// revalidate
		// revalidate();
		invalidate();
		validate();
		repaint();
	}


	/**
	 * 创建日期:(2003-3-7 9:49:12)
	 * 
	 * @param newTableName
	 *            java.lang.String
	 */
	public void setTableName(java.lang.String newTableName) {
		tableName = newTableName;
	}

	/**
	 * 设置合计行是否显示. 创建日期:(01-2-28 9:08:39)
	 */
	public void setTotalRowShow(boolean newValue) {
		m_bTotalRow = newValue;
		if (mainTable != null) {
			if (newValue) {
				setCorner(FIX_ROW, createViewport(getFixRowTable(), false));
				getFixRowTable().setColumnModel(getTable().getColumnModel());
			} else {
				setCorner(FIX_ROW, null);
				fixRowNOTable = null;
			}
			BillModel model = getTableModel();
			if (m_bTotalRow != model.isNeedCalculate())
				model.setNeedCalculate(m_bTotalRow);
			setFixRowNOShow(newValue);
		}
		setTableBorder();
	}

	/**
	 * 设置合计行是否显示. 创建日期:(01-2-28 9:08:39)
	 * 
	 * @return ufbill.BillTable
	 * @deprecated replaced by setTotalRowShow(boolean newValue)
	 */
	public void setTatolRowShow(boolean newValue) {
		setTotalRowShow(newValue);
	}

	/**
	 * 显示隐藏表体列. 创建日期:(2001-5-29 9:52:49)
	 * 
	 * @param strKey
	 *            java.lang.String
	 */
	public void showTableCol(String strKey) {
		if (hHideCol == null)
			return;
		if (hHideCol.containsKey(strKey)) {
			// 增加列
			TableColumn tclCol = (TableColumn) hHideCol.get(strKey);
			TableColumnModel cm = getTable().getColumnModel();
			cm.addColumn(tclCol);
			hHideCol.remove(strKey);
			hShowCol.put(strKey, tclCol);
			// 移动列
			int last = cm.getColumnCount() - 1;
			int pos = -1;
			int nModelIndex = cm.getColumn(last).getModelIndex();
			int nCount = cm.getColumnCount();
			for (int i = 0; i < nCount; i++) {
				int nTempIndex = cm.getColumn(i).getModelIndex();
				if (nTempIndex < nModelIndex && nTempIndex > pos)
					pos = i;
			}
			BillItem[] items = getBillItems();
			for (int i = 0; i < items.length; i++) {
				BillItem item = items[i];
				try {
					if (item.getKey().equals(strKey)) {
						item.setShow(true);
						break;
					}
				} catch (Throwable e) {
				}
			}
			if (last != pos)
				cm.moveColumn(last, pos + 1);
		}
	}

	/**
	 * stopTableCellEditing.
	 */
	private void stopCellEditing() {
		CellEditor editor;
		if (lockTable != null && (editor = lockTable.getCellEditor()) != null) {
			editor.stopCellEditing();
		}
		if ((editor = getTable().getCellEditor()) != null)
			editor.stopCellEditing();
	}

	/**
	 * 解除锁定列
	 */
	public void unlockTableCol() {
		if (!m_bLockCol)
			return;

		TableColumnModel cm = getTable().getColumnModel();
		TableColumn tcol;
		TableColumnModel hcm = getFixColTable().getColumnModel();
		// 添加主表行
		for (int i = hcm.getColumnCount() - 1; i >= 0; i--) {
			tcol = hcm.getColumn(i);
			cm.addColumn(tcol);
			int numCols = cm.getColumnCount();
			cm.moveColumn(numCols - 1, 0);
		}

		lockTable = null;
		userRowLockTable = null;
		fixRowLockTable = null;

		setCorner(USER_ROW_LOCK, null);
		setCorner(FIX_ROW_LOCK, null);
		setRowHeader(null);
		m_bLockCol = false;

		m_iLockCol = -1;
		// miLock.setText(nc.ui.ml.NCLangRes.getInstance().getStrByID("_Bill",
		// "UPP_Bill-000009")/*
		// * @res
		// * "锁定"
		// */);
		// miLock.setName(nc.ui.ml.NCLangRes.getInstance().getStrByID("_Bill",
		// "UPP_Bill-000009")/*
		// * @res
		// * "锁定"
		// */);
		if (billTableLockListener != null) {
			BillTableLockEvent e = new BillTableLockEvent(this, false);
			billTableLockListener.valueChanged(e);
		}

		setTableBorder();
		repaint();
	}

	/**
	 * 创建日期:(2003-6-11 10:44:14)
	 */
	// void updateBodyMenu() {
	// BillScrollPane bsp = this;
	// if (bsp.getMiBody() == null)
	// return;
	// if (menuItemListener == null)
	// menuItemListener = new MenuItemListener();
	// UIPopupMenu popupMenu = new UIPopupMenu();
	// if (bsp.getPmBody() == null) {
	// bsp.setPmBody(popupMenu);
	// }
	// bsp.getPmBody().removeAll();
	// for (int i = 0; i < bsp.getMiBody().length; i++) {
	// { miAddLine, miDelLine, miInsertLine, miCopyLine,
	// miPasteLine,miPasteLineToTail };
	// if (bsp.getMiBody()[i] == bsp.getMiAddLine() || bsp.getMiBody()[i] ==
	// bsp.getMiDelLine()
	// || bsp.getMiBody()[i] == bsp.getMiInsertLine() || bsp.getMiBody()[i] ==
	// bsp.getMiCopyLine()
	// || bsp.getMiBody()[i] == bsp.getMiPasteLine() || bsp.getMiBody()[i] ==
	// bsp.getMiPasteLineToTail()
	// || bsp.getMiBody()[i] == bsp.getMiAllSelctRow() || bsp.getMiBody()[i] ==
	// bsp.getMiCancelAllSelctRow()
	// ) {
	// bsp.getMiBody()[i].removeActionListener(menuItemListener);
	// bsp.getMiBody()[i].addActionListener(menuItemListener);
	// }
	// bsp.getPmBody().add(bsp.getMiBody()[i]);
	// popupMenu.add(bsp.getMiBody()[i]);
	// }
	//        
	// bsp.setPmBody(popupMenu);
	// }
	// /**
	// * 创建日期:(2003-6-19 16:30:38)
	// */
	// void updateCellColors(BillTable table) {
	// int rowcount = table.getRowCount();
	// if (rowcount <= 0)
	// return;
	// TableModel model = table.getModel();
	// if (!(model instanceof BillModel))
	// return;
	// BillModel bmodel = (BillModel) model;
	// // int[] rows = new int[rowcount];
	// // for (int i = 0; i < rows.length; i++)
	// // rows[i] = i;
	// int colcount = table.getColumnCount();
	// // int[] cols = new int[colcount];
	// // for (int i = 0; i < cols.length; i++) {
	// // cols[i] = table.convertColumnIndexToModel(i);
	// // }
	// // HashMap<Cell, Color>[] hashColors = bmodel.getCellColor(rows, cols);
	// IBillTableColorCellRenderer billrenderer;
	// TableCellRenderer[] renderer = new TableCellRenderer[colcount];
	// Color color;
	// // Cell cell;
	// for (int i = 0; i < colcount; i++) {
	// renderer[i] = table.getCellRenderer(0, i);
	//            
	// int col = table.convertColumnIndexToModel(i);
	//            
	// if (renderer[i] instanceof IBillTableColorCellRenderer) {
	// billrenderer = (IBillTableColorCellRenderer) renderer[i];
	// billrenderer.resumeDefaultBackGround();
	// billrenderer.resumeDefaultForeGround();
	//                
	// for (int row = 0; row < rowcount; row++) {
	// if ((color = bmodel.getRowAttribute(row).getCellBackColor(col)) != null)
	// billrenderer.setBackGround(row, i, color);
	//                    	
	// if ((color = bmodel.getRowAttribute(row).getCellForeColor(col)) != null)
	// billrenderer.setForeGround(row, i, color);
	// }
	// }
	// }
	// }
	/**
	 * 初始化Table. type in set("main","fix" ), main -- mainTable, fix -
	 * fixColtable 创建日期:(01-3-6 10:48:33)
	 */
	public void updateCellEditor(BillTable btTable, BillItem item) {
		int index = getTableModel().getBodyColByKey(item.getKey());
		if (index < 0)
			return;
		TableColumn tclCol = btTable.getColumnModel().getColumn(index);
		btTable.setCellEditor(tclCol, item);
	}

	/**
	 * @return Returns the colSelectionChangeListener.
	 */
	public BillTableSelectionChangeListener getColSelectionChangeListener() {
		return colSelectionChangeListener;
	}

	/**
	 * @param colSelectionChangeListener
	 *            The colSelectionChangeListener to set.
	 */
	public void addColSelectionChangeListener(
			BillTableSelectionChangeListener colSelectionChangeListener) {
		this.colSelectionChangeListener = colSelectionChangeListener;
	}

	/**
	 * @return Returns the rowSelectionChangeListener.
	 */
	public BillTableSelectionChangeListener getRowSelectionChangeListener() {
		return rowSelectionChangeListener;
	}

	/**
	 * @param rowSelectionChangeListener
	 *            The rowSelectionChangeListener to set.
	 */
	public void addRowSelectionChangeListener(
			BillTableSelectionChangeListener rowSelectionChangeListener) {
		this.rowSelectionChangeListener = rowSelectionChangeListener;
	}

	/**
	 * 设置合计行是否显示. 创建日期:(01-2-28 9:08:39)
	 */
	public void setUserFixRowShow(boolean newValue, int rowcount) {
		if (userFixRowVisible != newValue) {
			userFixRowVisible = newValue;
			if (mainTable != null) {
				if (newValue) {
					JViewport vp = createViewport(getUserFixRowTable(), false);
					((DefaultTableModel) getUserFixRowTable().getModel())
							.setRowCount(rowcount);
					getUserFixRowTable().setColumnModel(
							getTable().getColumnModel());
					if (fixRowTable != null)
						fixRowTable.setBorder(null);
					setCorner(USER_ROW, vp);
				} else {
					setCorner(USER_ROW, null);
					userRowTable = null;
				}
				setUserRowNOShow(newValue);
			}
		}
		setTableBorder();
	}

	private void setUserRowNOShow(boolean newValue) {

		if (newValue) {
			if (userRowNOTable == null && userRowTable != null
					&& rowNOTable != null) {
				getUserRowNOTable().setColumnModel(rowNOTable.getColumnModel());
				setCorner(USER_ROW_NO,
						createViewport(getUserRowNOTable(), true));
			}
		} else {
			setCorner(USER_ROW_NO, null);
			userRowNOTable = null;
		}
	}

	private JTable getUserRowNOTable() {

		if (userRowNOTable == null) {
			BillTable btTable = createBillTable(getTableModel()
					.getUserRowHeaderModel(), false, false);
			btTable.setEnabled(false);
			btTable.setBackground(btTable.getTableHeader().getBackground());
			btTable.setRowHeight(getTable().getRowHeight());
			userRowNOTable = btTable;
		}
		return userRowNOTable;
	}

	private BillTable getUserFixRowTable() {
		if (userRowTable == null) {
			userRowTable = createUserFixRowTable();
		}
		return userRowTable;
	}

	/**
	 * 创建合计表 创建日期:(01-2-23 15:29:06)
	 * 
	 * @return ufbill.BillModel
	 */
	private BillTable createUserFixRowTable() {
		BillTable btTable = new BillTable(getTableModel().getUserFixRowModel()) {
			public boolean isFocusTraversable() {
				return false;
			}
		};
		btTable.setCellSelectionEnabled(true);
		btTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
		btTable.setEnabled(false);
		btTable.setBackground(btTable.getTableHeader().getBackground());
		btTable.setRowHeight(getTable().getRowHeight());
		btTable.setAutoCreateColumnsFromModel(false);
		return btTable;
	}

	public void setUserFixRowModel(UserFixRowTableModel m) {
		if (userRowTable != null)
			userRowTable.setModel(m);
		getTableModel().setUserFixRowModel(m);
	}

	/**
	 * 创建行号表 创建日期:(01-2-23 15:29:06)
	 */
	private BillTable createFixRowNOTable() {
		BillTable btTable = createBillTable(getTableModel()
				.getFixRowHeaderModel(), true, false);
		btTable.setEnabled(false);
		btTable.setBackground(btTable.getTableHeader().getBackground());
		btTable.setRowHeight(getTable().getRowHeight());

		return btTable;
	}

	private BillTable getFixRowNOTable() {
		if (fixRowNOTable == null)
			fixRowNOTable = createFixRowNOTable();
		return fixRowNOTable;
	}

	private void setFixRowNOShow(boolean newValue) {
		if (newValue) {
			if (fixRowNOTable == null && fixRowTable != null
					&& rowNOTable != null) {
				// getFixRowNOTable().setColumnModel(rowNOTable.getColumnModel());
				setCorner(FIX_ROW_NO, createViewport(getFixRowNOTable(), true));
			}
		} else {
			setCorner(FIX_ROW_NO, null);
			fixRowNOTable = null;
		}
	}

	private BillTable createFixRowLockTotalTable() {
		BillTable btTable = new BillTable(getTableModel().getTotalTableModel());
		btTable.setEnabled(false);
		btTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
		btTable.setBackground(mainTable.getTableHeader().getBackground());
		btTable.setRowHeight(getTable().getRowHeight());
		return btTable;
	}

	private BillTable createFixRowLockUserTable() {
		BillTable btTable = new BillTable(getTableModel().getUserFixRowModel());
		btTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
		btTable.setEnabled(false);
		btTable.setBackground(mainTable.getTableHeader().getBackground());
		btTable.setRowHeight(getTable().getRowHeight());
		btTable.addMouseListener(new TableCellMouseListener());
		return btTable;
	}

	private BillTable getFixRowLockTotalTable() {
		if (fixRowLockTable == null)
			fixRowLockTable = createFixRowLockTotalTable();
		return fixRowLockTable;
	}

	private BillTable getFixRowLockUserTable() {
		if (userRowLockTable == null)
			userRowLockTable = createFixRowLockUserTable();

		return userRowLockTable;
	}

	// public void setMenuEnabled(int menu, boolean enabled) {
	// UIMenuItem mi = getMenuItem(menu);
	// if (mi != null && mi.isEnabled() != enabled) {
	// mi.setEnabled(enabled);
	// }
	// }
	//
	// private UIMenuItem getMenuItem(int menu) {
	// return getActionManager().getBillTableMenuItem(menu);
	// }
	//
	// public boolean isMenuEnabled(int menu) {
	// UIMenuItem mi = getMenuItem(menu);
	// return mi != null && mi.isEnabled();
	// }

	protected JViewport createViewport() {
		return createViewport(false);
	}

	private JViewport createViewport(boolean defaultFlag) {
		return new BillViewport(defaultFlag);
	}

	public class BillViewport extends JViewport {
		boolean defaultFlag = false;

		boolean boundsFlag = true;

		Rectangle rc;

		public BillViewport(boolean defaultFlag) {
			this.defaultFlag = defaultFlag;
		}

		public void setBoundsFlag(boolean boundsFlag) {
			this.boundsFlag = boundsFlag;
		}

		public void setBounds(int x, int y, int width, int height) {
			if (!boundsFlag)
				rc = new Rectangle(x, y, width, height);
			else
				super.setBounds(x, y, width, height);
		}

		public Rectangle getBoundsBak() {
			return rc;
		}

		private void updateViewPosition(JViewport v) {
			if (v != null)
				v.setViewPosition(v.getViewPosition());
		}

		public void setSupperViewPosition(Point p) {
			super.setViewPosition(p);
		}

		public void setViewPosition(Point p) {
			if (defaultFlag) {
				super.setViewPosition(p);
				return;
			}

			if (this == getViewport()) {
				// 主View区变化
				super.setViewPosition(p);
				if (fixRow != null && fixRow.isVisible()) {
					// updateViewPosition(fixRow);
					Point p0 = getFixRow().getViewPosition();
					Point vp = getViewport().getViewPosition();
					p0.x = vp.x;
					((BillViewport) getFixRow()).setSupperViewPosition(p0);
				}
				if (userRow != null && userRow.isVisible()) {
					updateViewPosition(userRow);
				}
				if (rowNO != null && rowNO.isVisible()) {
					updateViewPosition(rowNO);
				}
			}
			if (this == getFixRow()) {
				super.setViewPosition(p);

				Point p0 = getFixRow().getViewPosition();
				Point vp = getViewport().getViewPosition();

				vp.x = p0.x;

				((BillViewport) getViewport()).setSupperViewPosition(vp);
			} else {
				Point vp = getViewport().getViewPosition();
				if (this == fixRow) {
					// p.x = vp.x;
				} else if (this == userRow) {
					p.x = vp.x;
				} else if (this == rowNO) {
					p.y = vp.y;
				}
				super.setViewPosition(p);
			}
		}
	}

	/**
	 * 创建行号表 创建日期:(01-2-23 15:29:06)
	 */
	private BillTable createBillTable(TableModel model,
			boolean autoCreateColumnsFromModel, boolean resizeOFF) {
		BillTable btTable = new BillTable();
		if (resizeOFF)
			btTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		btTable.setAutoCreateColumnsFromModel(autoCreateColumnsFromModel);
		btTable.setModel(model);

		return btTable;
	}

	/**
	 * 创建行号表 创建日期:(01-2-23 15:29:06)
	 */
	class PartLineBorder extends EmptyBorder {
		Color lineColor = new Color(126, 127, 127);
		{
			if (mainTable != null)
				lineColor = mainTable.getGridColor();
		}

		public PartLineBorder(int top, int left, int bottom, int right) {
			super(top, left, bottom, right);
		}

		public void setColor(Color lineColor) {
			this.lineColor = lineColor;
		}

		/**
		 * Does no drawing by default.
		 */
		public void paintBorder(Component c, Graphics g, int x, int y,
				int width, int height) {
			Color oldColor = g.getColor();
			// int i;

			g.setColor(lineColor);
			if (right > 0) {
				g.drawLine(x + width - 1, y, x + width - 1, y + height - 1);
			}
			if (top > 0) {
				g.drawLine(x, y, x + width - 1, y);
			}
			if (left > 0)
				g.drawLine(x, y, x, y + height - 1);
			if (bottom > 0)
				g.drawLine(x, y + height - 1, x + width - 1, y + height - 1);

			g.setColor(oldColor);
		}
	}

	private void setTableBorder() {
		PartLineBorder b = new PartLineBorder(1, 0, 0, 0);
		if (userRowTable != null) {
			userRowTable.setBorder(b);
			if (userRowNOTable != null)
				userRowNOTable.setBorder(b);
			if (userRowLockTable != null)
				userRowLockTable.setBorder(b);

			if (fixRowTable != null) {
				fixRowTable.setBorder(null);
				if (fixRowNOTable != null)
					fixRowNOTable.setBorder(null);
				if (fixRowLockTable != null)
					fixRowLockTable.setBorder(null);
			}
		} else if (fixRowTable != null) {
			fixRowTable.setBorder(b);
			if (fixRowNOTable != null)
				fixRowNOTable.setBorder(b);
			if (fixRowLockTable != null)
				fixRowLockTable.setBorder(b);
		}
	}

	class RowNOCellRenderer extends DefaultTableCellRenderer {
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			Component com = super.getTableCellRendererComponent(table, value,
					isSelected, hasFocus, row, column);
			setHorizontalAlignment(JLabel.CENTER);
			if (isRowShowWarning(row)) {
				setForeground(UIManager.getColor("Table.errorForeColor"));
				// setForeground(new Color(0Xf35118));
			} else {
				setForeground(Style.getCtrFgColor());
			}
			// if (isSelected) {
			// setBackground(new Color(0XFEFEDD));
			// setBorder(BorderFactory.createLineBorder(new Color(0XE59A03)));
			// } else {
			// setBackground(table.getTableHeader().getBackground());
			// setBorder(null);
			// }
			BillModel model = getTableModel();
			if (model.getHighLightRowsSet().contains(row)) {
				super.setIcon(rowHeadIcon);
				setHorizontalAlignment(JLabel.LEFT);
				super.setIconTextGap(5);
			}
		
			return com;
		}
	}
	private static ImageIcon rowHeadIcon = ThemeResourceCenter.getInstance().getImage("themeres/control/grid/rowHead.png");
	// this method can replace other setColumnModel or set SelectionModel in
	// this filex
	private void updateTablesColumnModelAndSelectionModel() {
		BillTable table = getTable();

		if (table != null) {
			if (rowNOTable != null) {
				if (rowNOTable.getSelectionModel() != table.getSelectionModel()) {
					rowNOTable.setSelectionModel(table.getSelectionModel());
				}
				if (fixRowNOTable != null) {
					// if (fixRowNOTable.getColumnModel() != rowNOTable
					// .getColumnModel()) {
					// fixRowNOTable.setColumnModel(rowNOTable
					// .getColumnModel());
					// }
				}
				if (userRowNOTable != null) {
					if (userRowNOTable.getColumnModel() != rowNOTable
							.getColumnModel()) {
						userRowNOTable.setColumnModel(rowNOTable
								.getColumnModel());
					}
				}
			}
			if (lockTable != null) {
				if (lockTable.getSelectionModel() != table.getSelectionModel()) {
					lockTable.setSelectionModel(table.getSelectionModel());
				}
				if (fixRowLockTable != null) {
					if (fixRowLockTable.getColumnModel() != lockTable
							.getColumnModel()) {
						fixRowLockTable.setColumnModel(lockTable
								.getColumnModel());
					}
				}
				if (userRowLockTable != null) {
					if (userRowLockTable.getColumnModel() != lockTable
							.getColumnModel()) {
						userRowLockTable.setColumnModel(lockTable
								.getColumnModel());
					}
				}
			}
			if (fixRowTable != null) {
				if (fixRowTable.getColumnModel() != table.getColumnModel()) {
					fixRowTable.setColumnModel(table.getColumnModel());
				}
			}
			if (userRowTable != null) {
				if (userRowTable.getColumnModel() != table.getColumnModel()) {
					userRowTable.setColumnModel(table.getColumnModel());
				}
			}
		}
	}

	public void startRowCardEdit(BillTabVO baseTab, BillTabVO[] shareTabs) {

		if (getTableModel().isEnabled()) {

			if (getTable().getCellEditor() != null)
				getTable().getCellEditor().stopCellEditing();

//			BillModelRowEditDialog rowEditDialog = new BillModelRowEditDialog(
//					this, getTableModel(), baseTab, shareTabs, oldrow);
			int rowCount = getTable().getRowCount();
			if (rowCount<=0){
				return;
			}
			int row = getTable().getSelectedRow();
			if (row==-1){
				getTable().getSelectionModel().setSelectionInterval(0, 0);
			}
			BillModelRowEditDialog rowEditDialog = new BillModelRowEditDialog(
					this, getTableModel(), baseTab, shareTabs, getTable().getSelectedRow());

			rowEditDialog.addEditListener(getEditListener());

			rowEditDialog.addEditListener2(getEditListener2());

			rowEditDialog.setLocationRelativeTo(getBillParent());
			
			centered(rowEditDialog);
			
			rowEditDialog.setVisible(true);

			rowEditDialog.destroy();

			getTable().requestFocus();
		}

	}
	/**
	  * 让对话框居中的方法 
	  */
	 private void centered(Container container) {  
	        Toolkit toolkit = Toolkit.getDefaultToolkit();  
	        Dimension screenSize = toolkit.getScreenSize();  
	        int w = container.getWidth();  
	        int h = container.getHeight();  
	        container.setBounds((screenSize.width - w) / 2,  
	                (screenSize.height - h) / 2, w, h);  
	 }
	public void currentRowChange(int row) {
		oldrow = row;
	}

	/**
	 * 设置行标志
	 * 
	 * @param startrow
	 *            ：开始行
	 * @param endrow
	 *            ：结束行
	 * @param isreverse
	 *            :是否需要反向选择
	 */
	public void selectTableRow(int startrow, int endrow, boolean isreverse) {
		if (startrow < 0 || endrow < 0)
			return;

		if (startrow > endrow) {
			int temp = startrow;
			startrow = endrow;
			endrow = temp;
		}

		for (int i = startrow; i <= endrow; i++) {
			setRowSelectState(i, isreverse);
		}

		updateUI();
	}

	private void setRowSelectState(int startrow, int endrow, boolean isSelect) {
		if (isSelect)
			getTableModel().setRowState(startrow, endrow, BillModel.SELECTED);
		else
			getTableModel().setRowState(startrow, endrow, BillModel.UNSTATE);
	}

	private void setRowSelectState(int row, boolean isreverse) {
		if (row >= getTable().getRowCount())
			return;
		if (isreverse) {
			if (getTableModel().getRowState(row) == BillModel.SELECTED) {
				getTableModel().setRowState(row, BillModel.UNSTATE);
			} else {
				getTableModel().setRowState(row, BillModel.SELECTED);
			}
		} else {
			getTableModel().setRowState(row, BillModel.SELECTED);
		}
		updateUI();
	}

	/**
	 * 设置所有行为选择
	 */
	public void selectAllTableRow() {
		getTableModel().setRowState(0, getTable().getRowCount() - 1,
				BillModel.SELECTED);
		updateUI();
	}

	/**
	 * 设置所有行为不选择
	 */
	public void cancelSelectAllTableRow() {
		getTableModel().setRowState(0, getTable().getRowCount() - 1,
				BillModel.UNSTATE);
		updateUI();
	}

	private void swithAllSelectTableRowStats() {

		boolean isselect = true;

		if (getTable().getRowCount() > 0) {

			for (int i = 0; i < getTableModel().getRowCount(); i++) {
				// if (getTableModel().getRowState(i) == BillModel.UNSTATE) {
				// isselect = false;
				// break;
				// }

				if (getTableModel().getRowState(i) != BillModel.SELECTED) {
					isselect = false;
					break;
				}
			}

			if (isselect) {
				cancelSelectAllTableRow();
			} else {
				selectAllTableRow();
			}
		}
	}

	private void setRowSelectMode(boolean rowSelectMode) {
		if (getTableModel().isRowSelectMode() != rowSelectMode) {
			getTableModel().setRowSelectMode(rowSelectMode);
			setRowNOTable(null);
			setRowNOShow(m_bRowNO);

			clearNotEditAction();
			if (rowSelectMode) {
				addNotEditAction(getActionManager().getBillTableAction(
						ALLSELECT));
				addNotEditAction(getActionManager().getBillTableAction(
						CANCELALLSELECT));
			}
			addNotEditAction(getActionManager().getBillTableAction(LOCK));
		}
	}

	protected void setRowSelectdMode(boolean rowSelectMode,
			boolean isKeySelect, IBillTableMouseSelectControl tableMouseSelect,
			IBillTableMouseSelectControl rowNOMouseSelect) {
		setRowSelectMode(rowSelectMode);
		if (rowSelectMode) {
			if (isKeySelect)
				getTable().addKeySelect();

			if (tableMouseSelect != null)
				getTable().addMouseSelect(tableMouseSelect);

			if (rowNOMouseSelect != null)
				getRowNOTable().addMouseSelect(rowNOMouseSelect);
		} else {
			getTable().removeKeySelect();
			getTable().removeMouseSelect();
			getRowNOTable().removeMouseSelect();
		}
	}

	private void fillValueFromStartCell() {
		if (getBatchCopyListner() != null) {
			getBatchCopyListner().batchCopyBegin();
		}
		if (getTable().getSelectedRowCount() <= 1)
			return;

		int[] rows = getTable().getSelectedRows();

		int[] cols = getTable().getSelectedColumns();

		for (int j = 0; j < cols.length; j++) {
			int column = cols[j];

			if (!getTable().editCellAt(rows[0], column))
				return;

			stopCellEditing();

			Object value = getTable().getValueAt(rows[0], column);

			BillItem item = getTableModel().getBodyItems()[getTable()
					.convertColumnIndexToModel(column)];

			if (item.isFillEnabled()) {

				Object id = null;

				if (item.getMetaDataProperty() == null
						&& item.getDataType() == BillItem.UFREF
						&& item.getIDColName() != null
						&& item.getIDColName().trim().length() != 0) {
					id = getTableModel().getValueAtModel(rows[0],
							item.getIDColName());
					item.setValue(id);
				}

				boolean needCalculate = getTableModel().isNeedCalculate();
				getTableModel().setNeedCalculate(false);

				for (int i = 1; i < rows.length; i++) {
					// 如果是参照类型 这个方法会使参照Model中的Pk值清空，2011-5-25 改用 下面方法
					// if (getTable().editCellAt(rows[i], column)) {
					// 2015-10-20发现getTable().isCellEditable(rows[i], column) 这个方法没走编辑前事件
					if (getTable().isCellEditable(rows[i], column) && isBeforeEditEventAllowEdit(rows[i],item) ) {
						setValueAt(item, rows[i], column, value, id, true);
					}
				}

				getTableModel().setNeedCalculate(needCalculate);
			}

			// stopCellEditing();
		}

		if (getBatchCopyListner() != null) {
			getBatchCopyListner().batchCopyEnd();
		}
	}
	private boolean isBeforeEditEventAllowEdit(int row, BillItem item) {
		BillEditEvent ev = new BillEditEvent(item, null, item.getKey(), row,
				BillItem.BODY);
		ev.setTableCode(getTableCode());
		if (el2 != null && !el2.beforeEdit(ev))
			return false;
		return true;
	}
	private void pasteValuesByClipboard() {

		int row = getTable().getSelectedRow();

		int col = getTable().getSelectedColumn();

		getTable().setSurrendersFocusOnKeystroke(false);

		Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();

		Object obj;
		try {
			obj = cb.getData(DataFlavor.stringFlavor);
			String stringOfExcel = (String) obj;
			String[] ss = stringOfExcel.split("\\n");

			setValuesAt(row, col, ss);

		} catch (UnsupportedFlavorException e) {
		} catch (IOException e) {
		}

	}
	
	private void showCalculator() {
		// if (!textField.getTextType().equalsIgnoreCase("TextInt")
		// && !textField.getTextType().equalsIgnoreCase("TextDbl"))
		// return;

		UICalculator calculator;
		int row = getTable().getSelectedRow();
		int col = getTable().getSelectedColumn();
		String columnName = getTable().getColumnName(col);
		if (!(getTable().getModel() instanceof BillModel)) {
			return;
		}
		BillItem item = ((BillModel) getTable().getModel())
				.getItemByKey(columnName);

		if (item == null) {
			return;
		}

		if (!(item.getDataType() == IBillItem.INTEGER || item.getDataType() == IBillItem.DECIMAL)) {
			return;
		}

		Object input = getTable().getValueAt(row, col);
		String strInput = input == null ? "" : input.toString();
		double d = 0.0;
		try {
			d = Double.valueOf(strInput).doubleValue();
		} catch (Exception exc) {
		}
		if (strInput.equals("")) {
			calculator = new UICalculator(this);
		} else {
			calculator = new UICalculator(this, d);
		}
		calculator.showModal();
		if (calculator.processCheck()) {
			double result = calculator.getResultData();
			UFDouble ufd = null;
			if (item.getDataType() == IBillItem.INTEGER) {
				ufd = new UFDouble(result, 0);

			} else {
				ufd = new UFDouble(result, -item.getDecimalDigits());
			}
			getTable().setValueAt(ufd, row, col);
		}
		

	}

	private Popup errtip = null;

	private void ShowErrToolTip(String msg, int row, int col) {

		closeErrToolTip();

		Rectangle r = getTable().getCellRect(row, col, false);

		errtip = ToolTipOnTheFly.showToolTip(msg, new Point(getTable()
				.getLocationOnScreen().x
				+ r.getLocation().x, getTable().getLocationOnScreen().y
				+ r.getLocation().y + r.height), 3000);
	}

	private void closeErrToolTip() {
		if (errtip != null) {
			errtip.hide();
			errtip = null;
		}
	}

	private void setValuesAt(int startRow, int col, String[] values) {

		if (startRow < 0
				|| (startRow + values.length) > getTable().getRowCount()) {
			ShowErrToolTip(nc.ui.ml.NCLangRes.getInstance().getStrByID("_bill",
					"UPP_Bill-000562"/* @res"设值区域不匹配" */), startRow, col);
			return;
		}

		BillItem item = getTableModel().getBodyItems()[getTable()
				.convertColumnIndexToModel(col)];
		for (int i = 0; i < values.length; i++) {
			Object id = null;

			// 2012-6-21 增加编辑前事件调用
			if (getTable().isCellEditable(startRow + i, col)
					&& getTable().isBeforeEditEventAllowEdit(startRow + i, col,
							null)) {
				// 所有单表的档案或数据，通过复制动作粘贴行后，保存报异常错误。2011-6-8

				// if (item.getMetaDataProperty() != null && values[i] != null)
				// {
				// DefaultConstEnum aValue = new DefaultConstEnum(id,
				// values[i]);
				// setValueAt(item, startRow + i, col, aValue, id);
				// } else {

				Object objValue = values[i];
				if (item.getDataType() == BillItem.UFREF) {
					UIRefPane ref = (UIRefPane) item.getComponent();
					if (values[i] != null) {
						ref.setBlurValue(values[i]);
						id = ref.getRefPK();
					}
					if (ref.isAutoCheck() && id == null) {
						objValue = new DefaultConstEnum(id, null);
					} else {
						objValue = new DefaultConstEnum(id, ref
								.getRefShowName());
					}

				}
				objValue = checkStringLength(item, objValue);
				BillCellEditor editor = (BillCellEditor) getTable()
						.getCellEditor(startRow + i, col);
				if (editor != null) {
					getTable().removeEditor();
				}
				setValueAt(item, startRow + i, col, objValue, id);

			}
		}
	}

	private Object checkStringLength(BillItem item, Object objValue) {
		// 超长字符的处理
		if (objValue != null) {
			String sValue = objValue.toString();
			if (IBillItem.STRING == item.getDataType()
					|| IBillItem.TEXTAREA == item.getDataType()
					|| IBillItem.MULTILANGTEXT == item.getDataType()) {
				if (sValue.toCharArray().length > item.getLength()) {
					objValue = null;
				}
			}
		}
		return objValue;
	}

	protected void setValueAt(BillItem item, int row, int col, Object value,
			Object idvalue, boolean isFireEditEvent) {

		Object oldValue = getTable().getValueAt(row, col);

		getTable().setValueAt(value, row, col);

		// if (item.getIDColName() != null)
		// getTableModel().setValueAt(idvalue, row, item.getIDColName());
		// else
		// getTableModel().setValueAt(idvalue, row, item.getKey() +
		// IBillItem.ID_SUFFIX);

		// 加载关联项的值
		if (item.getMetaDataProperty() != null)
			getTableModel().loadEditRelationItemValue(row,
					getTable().convertColumnIndexToModel(col));
		else if (item.getIDColName() != null)
			getTableModel().setValueAt(idvalue, row, item.getIDColName());

		// 执行公式
		getTableModel().execEditFormulasByModelColumn(row,
				getTable().convertColumnIndexToModel(col));
		// 修改状态
		if (getTableModel().getRowState(row) == BillModel.NORMAL)
			getTableModel().setRowState(row, BillModel.MODIFICATION);

		if (getTable().getEditorComponent() != null
				&& getTable().getEditorComponent().isVisible()) {
			// 如果编辑控件已经激活，由编辑控件触发编辑后事件
			return;
		}
		// 触发编辑后事件
		if (el != null && isFireEditEvent) {
			String key = item.getKey();
			BillEditEvent ev = new BillEditEvent(getTable(), oldValue, value,
					key, row, BillItem.BODY);
			ev.setTableCode(getTableCode());
			el.afterEdit(ev);
		}
	}

	protected void setValueAt(BillItem item, int row, int col, Object value,
			Object idvalue) {

		setValueAt(item, row, col, value, idvalue, true);

	}

	private final int TABLESHOWMODE = 0;
	private final int TREETABLESHOWMODE = 1;

	public int mode = TABLESHOWMODE;
	public JTree tree = null;

	// private IBillTreeTableModel treeTableModel = null;

	public void switchTableShow() {
		mode = TABLESHOWMODE;
		tree = null;
		m_bmModel = null;
	}

	public void switchTreeTableShow(
			IBillTreeCreateStrategy billTreeCreateStrategy) {
		ITableTreeFactory tabletreefactory = new TreeTableFactory();
		;
		switch (billTreeCreateStrategy.getTreeMode()) {
		case IBillTreeCreateStrategy.TREETABLE:
			tabletreefactory = new TreeTableFactory();
			break;
		case IBillTreeCreateStrategy.TABLETREE:
			tabletreefactory = new TableTreeFactory();
			break;

		default:
			break;
		}

		initTableTree(billTreeCreateStrategy, tabletreefactory);

	}

	public TreeModel switchTableTreeShow(DefaultTreeModel dataTreeModel,
			String showColName) {

		ITableTreeFactory tabletreefactory = new TableTreeFactory();

		IBillTreeTableModel treeTableModel = BillTreeTableTools
				.createTreeModelByDataTreeModel(getTableModel(), dataTreeModel,
						showColName);

		tree = tabletreefactory.creatTreeTable(this, treeTableModel).getTree();

		mode = TREETABLESHOWMODE;

		return treeTableModel;
	}

	public TreeModel switchTreeTableShow(DefaultTreeModel dataTreeModel) {

		ITableTreeFactory tabletreefactory = new TreeTableFactory();

		IBillTreeTableModel treeTableModel = BillTreeTableTools
				.createTreeModelByDataTreeModel(getTableModel(), dataTreeModel,
						null);

		tree = tabletreefactory.creatTreeTable(this, treeTableModel).getTree();

		mode = TREETABLESHOWMODE;

		return treeTableModel;
	}

	private TreeModel initTableTree(
			IBillTreeCreateStrategy billTreeCreateStrategy,
			ITableTreeFactory tabletreefactory) {

		IBillTreeTableModel treeTableModel = BillTreeTableTools
				.creatTreeModelByStrategy(getTableModel(),
						billTreeCreateStrategy);

		tree = tabletreefactory.creatTreeTable(this, treeTableModel).getTree();

		mode = TREETABLESHOWMODE;

		return treeTableModel;
	}

	public void expandAllTree() {
		TreePath path = new TreePath(tree.getModel().getRoot());
		TreeUtils.expandAll(tree, path, true);
	}

	 public JTree getTree() {
		 return tree;
	 }
	//
	// public final void setRootVisible(boolean visible) {
	// if (tree != null) {
	// tree.setRootVisible(visible);
	// }
	// }
	//
	// public final TreePath getSelectionPath() {
	// if (tree != null)
	// return tree.getSelectionPath();
	// else
	// return null;
	// }
	//
	// public final TreePath[] getSelectionPaths() {
	// if (tree != null) {
	// return tree.getSelectionPaths();
	// } else {
	// return null;
	// }
	// }
	//	
	// public final TreePath getPathForRow(int row){
	// if (tree != null) {
	// return tree.getPathForRow(row);
	// } else {
	// return null;
	// }
	// }

	public void changeSelectionByNode(MutableTreeNode node) {
		if (node == null)
			clearSelect();
		else {
			TreePath path = new TreePath(((DefaultTreeModel) tree.getModel())
					.getPathToRoot(node));
			tree.expandPath(path);

			int row = tree.getRowForPath(path);

			getTable().changeSelection(row, 0, false, false);
		}
	}

	private List<Action> editAction = new ArrayList<Action>();
	private List<Action> noteditAction = new ArrayList<Action>();
	private List<Action> fixAction = new ArrayList<Action>();

	public void addEditAction(Action a) {
		editAction.add(a);
	}

	public void addNotEditAction(Action a) {
		noteditAction.add(a);
	}

	public void addFixAction(Action a) {
		fixAction.add(a);
	}

	public void removeEditAction(Action a) {
		editAction.remove(a);
	}

	public void removeDefaultAction(int op) {
		getActionManager().setBillTableAction(op, null);
	}

	public void replaceDefaultAction(int op, Action a) {
		getActionManager().setBillTableAction(op, a);
	}

	public Action getBillTableAction(int op) {
		return getActionManager().getBillTableAction(op);
	}

	public void removeNotEditAction(Action a) {
		noteditAction.remove(a);
	}

	public void removeFixAction(Action a) {
		fixAction.remove(a);
	}

	public void clearDefalutEditAction() {
		for (int i = 0; i < defaultactions.length; i++) {
			getActionManager().setBillTableAction(defaultactions[i], null);
		}

	}

	public void clearEditAction() {
		editAction.clear();
	}

	public void clearNotEditAction() {
		noteditAction.clear();
	}

	public void clearFixAction() {
		fixAction.clear();
	}

	private void initBodyMenuByAction() {

		pmBody = new UIPopupMenu();

		int n = 0;

		if (getTableModel().isEnabled()) {

			n = addPopAction(getDefaultEditAction(), false);

			n = n + addPopAction(editAction, false);

		} else {
			n = addPopAction(noteditAction, false);
		}

		n = n + addFixPopAction(fixAction, n != 0);
	}

	private int addPopAction(List<Action> al, boolean addSep) {
		int n = 0;
		if (al.size() > 0) {
			if (addSep)
				pmBody.addSeparator();
			for (int i = 0; i < al.size(); i++) {
				if (al.get(i) != null) {
					pmBody.add(al.get(i));
					n++;
				}
			}
		}

		return n;
	}

	private HashMap<String, List<Action>> popMenuHasActionMap = new HashMap<String, List<Action>>();
	private HashMap<Action, String> actionInMenuMap = new HashMap<Action, String>();
	
	public void putPopMenuActionMap(String menuname, Action action) {
		actionInMenuMap.put(action, menuname);
		if (popMenuHasActionMap.get(menuname) == null) {
			List<Action> actionList = new ArrayList<Action>();
			actionList.add(action);
			popMenuHasActionMap.put(menuname, actionList);
		} else {
			popMenuHasActionMap.get(menuname).add(action);
		}
	}
	
	private int addFixPopAction(List<Action> al, boolean addSep) {
		if (popMenuHasActionMap.size() == 0 || actionInMenuMap.size() == 0)
			return addPopAction(al, addSep);

		int n = 0;
		if (al.size() == 0) 
			return n;
		
		if (addSep)
			pmBody.addSeparator();
		
		int counter = 0;
		HashMap<Integer, Object> lastMap = new HashMap<Integer, Object>();
		HashMap<String, Boolean> menuPutFlagMap = new HashMap<String, Boolean>();
		for (int i = 0; i < al.size(); i++) {
			if (actionInMenuMap.get(al.get(i)) == null) {
				lastMap.put(counter, al.get(i));
				counter++;
			} else {
				String menuname = actionInMenuMap.get(al.get(i));
				if (menuPutFlagMap.get(menuname) != null && menuPutFlagMap.get(menuname).booleanValue() == true)
					continue;
				
				List<Action> list = popMenuHasActionMap.get(menuname);
				JMenu menu = new JMenu(menuname);
				for(Action act : list) {
					menu.add(act);
				}
				menuPutFlagMap.put(menuname, Boolean.TRUE);
				lastMap.put(counter, menu);
				counter++;
			}
		}
		
		for (int i = 0; i < counter; i++) {
			if (lastMap.get(i) instanceof Action) {
				pmBody.add((Action)lastMap.get(i));
			} else if (lastMap.get(i) instanceof JMenu) {
				pmBody.add((JMenu)lastMap.get(i));
			}
		}

		return n;
	}
	
	public void clearPopAction(){
		actionInMenuMap.clear();
		popMenuHasActionMap.clear();
	}
	
	private final int[] defaultactions = { ADDLINE, INSERTLINE, DELLINE,
			COPYLINE, PASTELINE, PASTELINETOTAIL };

	private List<Action> getDefaultEditAction() {

		List<Action> actions = new ArrayList<Action>();

		for (int i = 0; i < defaultactions.length; i++) {
			actions.add(getActionManager().getBillTableAction(i));
		}

		return actions;
	}

	protected void execAddRowAction(boolean isChangSelected) {
		if (getBillTableAction(ADDLINE) != null) {
			ActionEvent ae = null;
			if (isChangSelected)
				ae = new ActionEvent(getTable(), ADDLINE, "AddLine");
			else
				ae = new ActionEvent(getTable(), AUTOADDLINE, "AutoLine");
			getBillTableAction(ADDLINE).actionPerformed(ae);
		} else {
			addLine(isChangSelected);
		}
	}

	// public final MutableTreeNode getTreeNode(int row) {
	// TreePath path = getPathForRow(row);
	// if (path != null) {
	// return (MutableTreeNode)path.getLastPathComponent();
	// }
	// return null;
	// }
	//
	// public final MutableTreeNode getRoot(){
	// if(getTree() != null){
	// return (MutableTreeNode)getTree().getModel().getRoot();
	// }
	// return null;
	// }
	//	
	// public void insertNodeIntoParent(MutableTreeNode newChild,MutableTreeNode
	// parent, int index){
	// TreeModel model = tree.getModel();
	// if(model instanceof DefaultTreeModel){
	// DefaultTreeModel treeModel = (DefaultTreeModel)model;
	// treeModel.insertNodeInto(newChild, parent, index);
	// TreePath path = new TreePath(treeModel.getPathToRoot(parent));
	// tree.expandPath(path);
	//			
	// }
	//		
	// }
	// public void removeNodeFromParent(MutableTreeNode node){
	// TreeModel model = tree.getModel();
	// if(model instanceof DefaultTreeModel){
	// DefaultTreeModel treeModel = (DefaultTreeModel)model;
	// treeModel.removeNodeFromParent(node);
	// }
	// }
	public void refreshTableCol(String strKey) {

		if (getTableModel().getBodyColByKey(strKey) < 0)
			return;
		BillItem item = getBillItems()[getTableModel().getBodyColByKey(strKey)];
		TableColumn tclCol;
		tclCol = getShowCol(strKey);

		if (tclCol != null) {
			tclCol.setHeaderValue(item.getName());
			setTableCellRenderer(tclCol, item);
			if (tclCol.getCellEditor() != null) {
				Component com = ((BillCellEditor) tclCol.getCellEditor())
						.getComponent();
				if (com != item.getComponent())
					getTable().setCellEditor(tclCol, item);
			}

		}

	}

	public boolean isRowShowWarning(int row) {
		boolean showWarning = false;
		int columnCount = getTableModel().getColumnCount();
		for (int i = 0; i < columnCount; i++) {
			showWarning = getTableModel().isCellShowWarning(row, i);
			if (showWarning) {
				break;
			}
		}
		return showWarning;
	}

	public IBillTableBatchCopyListener getBatchCopyListner() {
		return batchCopyListner;
	}

	public void addBatchCopyListener(
			IBillTableBatchCopyListener batchCopyListner) {
		this.batchCopyListner = batchCopyListner;
	}

	class BooleanRenderer extends UICheckBox implements TableCellRenderer,
			UIResource {

		public BooleanRenderer() {
			super();
			setHorizontalAlignment(JLabel.CENTER);
			setBorderPainted(true);
		}

		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			if (isSelected) {
				setForeground(table.getSelectionForeground());
				super.setBackground(table.getSelectionBackground());
			} else {
				setForeground(table.getForeground());
				setBackground(table.getBackground());
			}
			setSelected((value != null && ((Boolean) value).booleanValue()));

			return this;
		}
	}

}