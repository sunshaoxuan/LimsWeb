package nc.ui.pub.bill;

import java.awt.Color;
import java.awt.Font;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.EventListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.swing.event.EventListenerList;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.itf.qcco.ICommissionMaintain;
import nc.md.data.access.DASFacade;
import nc.md.data.access.NCObject;
import nc.md.model.IBusinessEntity;
import nc.mddb.constant.ElementConstant;
import nc.ui.bd.ref.AbstractRefModel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.beans.UITable.SortItem;
import nc.ui.pub.beans.constenum.DefaultConstEnum;
import nc.ui.pub.beans.constenum.IConstEnum;
import nc.ui.pub.beans.table.CellFont;
import nc.ui.pub.beans.table.ColoredCell;
import nc.ui.pub.beans.table.IMutilSortableTableModel;
import nc.ui.pub.beans.table.ISpanCell;
import nc.ui.pub.bill.table.BillModelCellSpan;
import nc.ui.pub.bill.table.IBillModeCellSpan;
import nc.ui.pub.formulaparse.FormulaParse;
import nc.vo.bill.pub.BillUtil;
import nc.vo.jcom.util.SortUtils;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.VOStatus;
import nc.vo.pub.bill.BillTabVO;
import nc.vo.pub.general.GeneralSuperVO;
import nc.vo.pub.lang.MultiLangText;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.qcco.task.AggTaskHVO;
import nc.vo.qcco.task.TaskHVO;

import org.apache.commons.lang.ArrayUtils;

@SuppressWarnings("serial")
public class BillModel extends AbstractTableModel implements
		IMutilSortableTableModel, ColoredCell, CellFont, ISpanCell {
	/**
	 * The <code>Vector</code> of <code>Vectors</code> of <code>Object</code>
	 * values.
	 */
	protected Vector dataVector;

	public final static int UNSTATE = -1; // ��״̬

	public final static int NORMAL = 0; // ����

	public final static int ADD = 1; // ����

	public final static int MODIFICATION = 2; // �޸�

	public final static int DELETE = 3; // ɾ��

	public final static int SELECTED = 4; // ѡ��

	protected BillItem[] m_biBodyItems = null; // ����Ԫ������

	protected Hashtable<String, Integer> htBodyItems = null; // ��¼����Ԫ��λ��

	private Hashtable<String, BillItem> htBillItems = new Hashtable<String, BillItem>();

	private Vector<Vector<?>> vViewBodyCache = null; // ���ݱ���

	private Vector<Vector<?>> vViewRowCopy = null; // �����ݱ���

	protected Vector<Vector<?>> vDeleteRow = null; // ɾ���м�¼

	protected DefaultTableModel m_tmlTotal = null; // �ϼ��б�ģʽ

	protected DefaultTableModel m_tmlRowNO = null; // �кű�ģʽ

	private FixRowNOModel fixRowHeaderModel = null;// fix row header

	private UserRowNOModel userRowHeaderModel = null;

	private UserFixRowTableModel m_userFixRowModel = null; // user fixrow model

	// �����Զ���RowAttribute
	protected List<RowAttribute> vRowAttrib = new ArrayList<RowAttribute>();

	// protected Hashtable htCellEditCopy = null; // ����

	protected int fixCol = -1; // ������

	protected BillTotalListener btl = null; // �ϼƼ���

	// ---------------------------------------------

	// �ж�setValueAtModel(..)�ĵ����Ƿ�����setBodyDataVO�����õ�ֵ,����ظ���ֵ
	private boolean isFirstSetBodyDataVO = false;

	// ---------------------------------------

	// ��ʽִ����
	protected FormulaParse m_formulaParse;

	// �б༭
	protected boolean m_bRowEditState = false;

	// �ɱ༭��
	// protected int m_intEditRow = -1;
	protected Vector<?> m_editRow = null;

	// ���ɱ༭��
	// protected ArrayList<RowAttribute> aryNotEditRow = null;

	/**
	 * ����ķ���
	 */
	// private boolean m_ascending = true;
	/**
	 * �����еĿɱ༭�Ե�Hash��
	 */
	// protected Hashtable m_htColEditable = null;
	/**
	 * �����пɱ༭�Ե�Hash��
	 */
	// protected Hashtable m_htRowEditable = null;
	/**
	 * �����������е�����
	 */
	private int[] m_indexes;

	/**
	 * �����������е����
	 */
	// private int m_sortColumn = -1;
	/**
	 * ������
	 */
	private List<SortItem> m_sortColumn = null;

	// ���������������
	protected EventListenerList sortRelaObjectListener = new EventListenerList();

	private IBillModelSortPrepareListener sortPrepareListener = null;

	private IBillModelRowStateChangeEventListener rowStateChangeListener = null;
	/**
	 * ����Ϊ��ͷMOdel��ӵļ�����Ϊ����б�ģʽ��ѡ�б������Զ�ѡ���ͷʱ����ͷ�ò����¼���Ӧ 2011-8-25
	 */
	private IBillModelHeadRowStateChangeEventListener headRowStateChangeListener = null;

	protected BillSortListener bsl = null; // �������

	protected EventListenerList afterSortListener = new EventListenerList();

	private final int DefaultSortType = -1;

	// private int sorttype = DefaultSortType; // value of billitem's types

	// ���Ӷ��Ƿ�ɱ༭��ȫ�ֿ���
	private boolean m_bEnabled = true;

	// �����Ƿ����ϼƵı�־
	private boolean m_bNeedCalculate = false;

	// private Hashtable<Cell,Color> hashCellBackColor = new
	// Hashtable<Cell,Color>();
	//
	// private Hashtable<Cell,Color> hashCellForeColor = new
	// Hashtable<Cell,Color>();

	private BillModelFormulaProcessor formulaProcessor = new BillModelFormulaProcessor(
			this);

	// for formula
	private BillScrollPane billScrollPane = null;

	// private BillItemContext context = null;

	private boolean changeTable = true;

	private BillModelCellEditableController cellEditableController = null;

	private String totalTitle = nc.ui.ml.NCLangRes.getInstance().getStrByID(
			"common", "UC000-0001146");

	// private boolean showUserFixRowTable = false;

	// public static boolean setVectorSize = false;
	//
	// public static boolean fireTable = false;

	private boolean ignoreScaleWhenSetValue = false;

	// ��ѡ��ģʽ
	private boolean rowSelectMode = false;

	// �ϲ���Ԫ������
	private IBillModeCellSpan cellSpan = null;

	private BillTabVO tabvo = null;

	private String selectModelTxt = nc.ui.ml.NCLangRes.getInstance()
			.getStrByID("_bill", "UPP_Bill-000563"/*
												 * @res"ѡ��ģʽֻ������SELECTED��UNSTATE"
												 */);
	// ���ڵ���
	private boolean isImporting = false;
	// �Ƿ񴥷�����������¼�
	boolean isFireTableRowInsertedEvent = true;

	/**
	 * �к�Model.
	 */
	public class RowNumberModel extends DefaultTableModel {

		public int getColumnCount() {
			if (isRowSelectMode())
				return 2;
			else
				return 1;
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {

			Class<?> c = null;

			switch (columnIndex) {
			case 0:
				c = String.class;
				break;
			case 1:
				c = Boolean.class;
				break;
			default:
				c = super.getColumnClass(columnIndex);
				break;
			}

			return c;
		}

		public String getColumnName(int col) {
			return "   ";// "�к�";
		}

		public boolean isCellEditable(int row, int col) {
			return false;
		}

		public void setNumRows(int rowCount) {
			int old = getRowCount();
			if (old == rowCount) {
				return;
			}
			dataVector.setSize(rowCount);
			if (rowCount <= old) {
			} else {
				justifyRows(old, rowCount);
			}
			fireTableChanged(new TableModelEvent(this));
		}

		@SuppressWarnings("unchecked")
		private void justifyRows(int from, int to) {
			dataVector.setSize(getRowCount());

			for (int i = from; i < to; i++) {
				if (dataVector.elementAt(i) == null) {
					dataVector.setElementAt(new Vector(), i);
				}
				((Vector) dataVector.elementAt(i)).setSize(getColumnCount());
			}
		}

		public Object getValueAt(int row, int column) {
			if (row >= 0 && column >= 0) {
				switch (column) {
				case 0:
					String value = getRowNO(row);
					return value;
				case 1:
					if (getRowState(row) == SELECTED) {
						return Boolean.TRUE;
					} else {
						return Boolean.FALSE;
					}
				default:
				}

			}
			return null;
		}
	}

	/**
	 * �к�Model.
	 */
	public class FixRowNOModel extends DefaultTableModel {
		public int getColumnCount() {
			if (isRowSelectMode())
				return 2;
			else
				return 1;
		}

		public int getRowCount() {
			int row = 0;
			if (BillModel.this == null)
				return row;
			if (m_tmlTotal != null)
				row += m_tmlTotal.getRowCount();
			return row;
		}

		public Object getValueAt(int row, int column) {
			if (column == 0)
				return totalTitle;
			else
				return null;
		}
	}

	/**
	 * �к�Model.
	 */
	public class UserRowNOModel extends DefaultTableModel {
		public int getColumnCount() {
			if (isRowSelectMode())
				return 2;
			else
				return 1;
		}

		public int getRowCount() {
			int row = 0;
			if (BillModel.this == null)
				return row;
			if (m_userFixRowModel != null)
				row += m_userFixRowModel.getRowCount();
			return row;
		}

		public Object getValueAt(int row, int column) {
			if (column == 0)
				return "" + row;
			else
				return null;
		}
	}

	/**
	 * �ϼ�Model.
	 */
	public class TotalTableModel extends DefaultTableModel {

		private BillModel model = null;

		public TotalTableModel(BillModel model) {
			super();
			this.model = model;
		}

		public int getColumnCount() {
			return BillModel.this.getColumnCount();
		}

		public String getColumnName(int col) {
			return BillModel.this.getColumnName(col);
		}

		public boolean isCellEditable(int row, int col) {
			return false;
		}

		public BillModel getMainModel() {
			return model;
		}
	}

	class MutiColumnComparator implements Comparator<Object> {

		Comparator<?>[] itemComparator = null;

		List<SortItem> sortitems = null;

		public MutiColumnComparator(List<SortItem> sortitems) {
			super();
			this.sortitems = sortitems;
			initComparator();
		}

		private void initComparator() {

			if (sortitems != null) {
				itemComparator = new ColumnComparator[sortitems.size()];
				for (int i = 0; i < sortitems.size(); i++) {
					itemComparator[i] = getItemComparator(sortitems.get(i));
				}
			}
		}

		@SuppressWarnings("unchecked")
		public int compare(Object o1, Object o2) {
			int result = -1;

			for (int i = 0; i < itemComparator.length; i++) {
				Comparator comparator = itemComparator[i];
				result = comparator.compare(o1, o2);

				if (result != 0)
					break;
			}

			return result;
		}

	}

	class ColumnComparator implements Comparator<Vector<?>> {

		private int sorttype = DefaultSortType;

		private Comparator<Object> comp = null;

		private SortItem sortitem = null;

		// int comparetype = 0;
		public ColumnComparator(int sorttype, SortItem sortitem) {
			this.sorttype = sorttype;
			this.sortitem = sortitem;
		}

		public ColumnComparator(Comparator<Object> comp, SortItem sortitem) {
			this.comp = comp;
			this.sortitem = sortitem;
		}

		public int compare(Vector<?> o1, Vector<?> o2) {

			int result = -1;

			Object co1 = o1.get(sortitem.getColumn());
			Object co2 = o2.get(sortitem.getColumn());

			if (comp == null) {

				result = compareObject(co1, co2);

			} else {
				result = comp.compare(co1, co2);
			}

			return sortitem.isAscending() ? result : -result;
		}

		@SuppressWarnings("unchecked")
		private int compareObject(Object o1, Object o2) {
			int type = this.sorttype;
			// �����Ϊnull,�򷵻�0
			if (o1 == o2) {
				return 0;
			} else if (o1 == null) {
				// Ĭ��null������ֵС
				return -1;
			} else if (o2 == null) {
				return 1;
			}

			int ret = 0;

			// ָ�����ͱȽ�
			switch (type) {
			case DefaultSortType:
				if (o1 instanceof IConstEnum)
					o1 = o1.toString();
				if (o2 instanceof IConstEnum)
					o2 = o2.toString();

				if (o1 instanceof MultiLangText)
					o1 = o1.toString();
				if (o2 instanceof MultiLangText)
					o2 = o2.toString();

				// if (o1 instanceof Comparable && o2 instanceof Comparable) {
				// ret = ((Comparable) o1).compareTo((Comparable) o2);
				// }
				ret = BillColumnCompareUtil.compare(o1, o2);
				break;
			case BillItem.DECIMAL:
			case BillItem.INTEGER:
			case BillItem.MONEY:

				UFDouble uo1 = new UFDouble(o1.toString());
				UFDouble uo2 = new UFDouble(o2.toString());

				ret = uo1.compareTo(uo2);
				break;
			default:

				if (o1 instanceof MultiLangText)
					o1 = o1.toString();
				if (o2 instanceof MultiLangText)
					o2 = o2.toString();

				// ret = MiscUtils.compareStringByBytes(o1, o2);
				// �����ﾳ���򣬷����ڹ��ʻ�ĳЩ�����£�����������
				ret = BillColumnCompareUtil.compare(o1, o2);
				break;
			}
			return ret;

		}

	}

	/**
	 * BillModel ������ע��.
	 */
	public BillModel() {
		this(0);
	}

	private static Vector newVector(int size) {
		Vector v = new Vector(size);
		v.setSize(size);
		return v;
	}

	/**
	 * Constructs a <code>DefaultTableModel</code> with <code>rowCount</code>
	 * and <code>columnCount</code> of <code>null</code> object values.
	 * 
	 * @param rowCount
	 *            the number of rows the table holds
	 * @param columnCount
	 *            the number of columns the table holds
	 * 
	 * @see #setValueAt
	 */
	private BillModel(int rowCount) {
		this.dataVector = newVector(rowCount);
		justifyRows(0, getRowCount());
		fireTableStructureChanged();
	}

	/**
	 * ������.
	 */
	public void addLine() {
		addLine(1);
	}

	/**
	 * ������.
	 */
	public void addLine(int count) {
		if (m_biBodyItems == null || count <= 0)
			return;
		for (int j = 0; j < count; j++) {
			// ����������
			int lastRow = getRowCount() - 1;
			Vector<Object> vNewRow = createRowVector(lastRow);
			vRowAttrib.add(createRowAttribute());

			addRow(vNewRow);
			// �����к�
			vNewRow = new Vector<Object>();
			vNewRow.add(null);
			getRowNOTableModel().addRow(vNewRow);

			if (getCellSpan() != null) {
				getCellSpan().addLine();
			}
		}
		// if (isChangeTable())
		// fireTableChanged(new TableModelEvent(this));
	}

	/**
	 * ����������. ��������:(2001-10-24 16:30:33)
	 * 
	 * @param row
	 *            ������λ��
	 */
	protected Vector<Object> createRowVector(int row) {
		Vector<Object> vNewRow;
		vNewRow = new Vector<Object>(m_biBodyItems.length);
		if (isChangeTable()) {
			for (int i = 0; i < m_biBodyItems.length; i++) {
				Object value = null;
				if (row > -1 && m_biBodyItems[i].isLock()) {
					value = getValueAt(row, i);
				} else if (m_biBodyItems[i].getDefaultValueObject() != null) {
					value = m_biBodyItems[i].getDefaultValueObject();
					// if(value != null){
					// switch (m_biBodyItems[i].getDataType()) {
					// case BillItem.BOOLEAN:
					// if (value.toString().equalsIgnoreCase("true")
					// || value.toString().equalsIgnoreCase("Y") ||
					// value.toString().equals("1"))
					// value = UFBoolean.TRUE;
					// else
					// value = UFBoolean.FALSE;
					//
					// break;
					// case BillItem.INTEGER:
					// value = new Integer(value.toString());
					// break;
					// case BillItem.DECIMAL:
					// value = new UFDouble(value.toString());
					// value = ((UFDouble) value).setScale(0 -
					// m_biBodyItems[i].getDecimalDigits(),
					// UFDouble.ROUND_HALF_UP);
					//
					// break;
					// case IBillItem.COMBO:
					// if(value.toString().length() == 0 )
					// value = null;
					// else if(m_biBodyItems[i].isComboBoxReturnInteger() &&
					// !(value instanceof Integer))
					// value = new Integer(value.toString());
					//
					// UIComboBox combo = ((UIComboBox)
					// m_biBodyItems[i].getComponent());
					//
					// int index = combo.getItemIndexByObject(value);
					// if (index < 0 && m_biBodyItems[i].isWithIndex()) {
					// if ("".equals(value))
					// index = 0;
					// else
					// index = Integer.parseInt(value.toString());
					// }
					//
					// if (index >= 0)
					// value = combo.getItemAt(index);
					// break;
					// default:
					// break;
					// }
					// }

					// + "=====" + m_biBodyItems[i].getDefaultValueObject());
				}
				vNewRow.add(value);
			}
		}

		return vNewRow;
	}

	protected RowAttribute createRowAttribute() {
		RowAttribute ra = new RowAttribute();

		return ra;
	}

	/**
	 * �����������. ��������:(2001-10-24 16:30:33)
	 * 
	 * @param btl
	 *            nc.ui.pub.bill.BillTotalListener
	 */
	public void addSortListener(BillSortListener bsl) {
		this.bsl = bsl;
	}

	/**
	 * ���ӺϼƼ���. ��������:(2001-10-24 16:30:33)
	 * 
	 * @param btl
	 *            nc.ui.pub.bill.BillTotalListener
	 */
	public void addTotalListener(BillTotalListener btl) {
		this.btl = btl;
	}

	// ------------------------------------------------------------------------
	/**
	 * ���Ӿ��ȼ���
	 */
	public void addDecimalListener(IBillModelDecimalListener bdl) {

		if (bdl instanceof IBillModelDecimalListener2) {
			String[] targets = ((IBillModelDecimalListener2) bdl).getTarget();
			if (targets != null && targets.length > 0) {
				for (int i = 0; i < targets.length; i++) {
					BillItem item = getItemByKey(targets[i]);
					if (item != null)
						item.addDecimalListener(bdl);
					else
						Logger.error(targets[i] + " col not exist");
				}
			}
		} else {// Ϊ�˱���ԭ���ӿ�(IBillModelDecimalListener)����
			BillItem[] items = getBodyItems();
			for (int i = 0; i < items.length; i++) {
				if (bdl.isTarget(items[i])) {
					items[i].addDecimalListener(bdl);
				}
			}
		}
	}

	/**
	 * ���ö�Ӧ�е�Item�ľ���
	 */
	private void setBillItemDecimalByVO(BillItem item, int row,
			CircularlyAccessibleValueObject bodyRowVO) {
		if (item.getDecimalListener() != null) {
			String source = item.getDecimalListener().getSource();
			Object pkValue = bodyRowVO.getAttributeValue(source);
			if (pkValue != null){
				 if ( item.getDecimalListener() instanceof IBillModelDecimalListener3) {
			          item.setDecimalDigits(
			              item.getDecimalListener().getDecimalFromSource( row, pkValue),
			              ((IBillModelDecimalListener3) item.getDecimalListener())
			                  .getRoundingModeFromSource( row, pkValue));
			          }
			     else {
			          item.setDecimalDigits(
			              item.getDecimalListener().getDecimalFromSource( row, pkValue));
			     }

//				item.setDecimalDigits(item.getDecimalListener()
//						.getDecimalFromSource(row, pkValue));
			}
				
		}
	}

	private void setBillItemDecimalByRow(BillItem item, int row) {
		if (item.getDecimalListener() != null) {
			int colIndex = getBodyColByKey(item.getDecimalListener()
					.getSource());
			Object id = getValueAt(row, colIndex);
			if (id != null && id instanceof IConstEnum)
				id = ((IConstEnum) id).getValue();
			if (id != null){
				 if ( item.getDecimalListener() instanceof IBillModelDecimalListener3) {
			          item.setDecimalDigits(
			              item.getDecimalListener().getDecimalFromSource( row, id),
			              ((IBillModelDecimalListener3) item.getDecimalListener())
			                  .getRoundingModeFromSource( row, id));
			          }
			     else {
			          item.setDecimalDigits(
			              item.getDecimalListener().getDecimalFromSource( row, id));
			     }

				
//				item.setDecimalDigits(item.getDecimalListener()
//						.getDecimalFromSource(row, id));
			}
				
		}
	}
	
	
	private void setBillItemDecimalByRow(BillItem item, int row,int digits) {
		if (item.getDecimalListener() != null) {
			int colIndex = getBodyColByKey(item.getDecimalListener()
					.getSource());
			Object id = getValueAt(row, colIndex);
			if (id != null && id instanceof IConstEnum)
				id = ((IConstEnum) id).getValue();
			if (id != null){
				 if ( item.getDecimalListener() instanceof IBillModelDecimalListener3) {
			          item.setDecimalDigits(
			              item.getDecimalListener().getDecimalFromSource( row, id),
			              ((IBillModelDecimalListener3) item.getDecimalListener())
			                  .getRoundingModeFromSource( row, id));
			          }
			     else {
			          item.setDecimalDigits(
			              item.getDecimalListener().getDecimalFromSource( row, id));
			     }
			}
			else{
				if(digits!=-100){
					item.setDecimalDigits(digits);
				}
			}
				
		}
	}

	// ----------------------------------------------------------------------

	// /**
	// * ������Ԫ��༭����. ��������:(2001-3-25 16:52:47)
	// *
	// * @param operation java.lang.String
	// * @param row int
	// */
	// protected void adjustCellEdit(int operation, int row) {
	// adjustCellEditModel(operation, convertIntoModelRow(row));
	// }

	/**
	 * ������Ԫ��༭����. ��������:(2001-3-25 16:52:47)
	 * 
	 * @param operation
	 *            java.lang.String
	 * @param row
	 *            int
	 */
	// private void adjustCellEditModel(int operation, int row) {
	// if (htCellEdit == null)
	// return;
	// Hashtable temp = new Hashtable();
	// // ����
	// if (operation == 0) {
	// for (Enumeration e = htCellEdit.keys(); e.hasMoreElements();) {
	// String key = e.nextElement().toString();
	// int oldRow = Integer.parseInt(key
	// .substring(0, key.indexOf(",")));
	// if (oldRow >= row) {
	// String newKey = (oldRow + 1)
	// + key.substring(key.indexOf(","));
	// temp.put(newKey, htCellEdit.get(key));
	// } else
	// temp.put(key, htCellEdit.get(key));
	// }
	// }
	// // ɾ��
	// if (operation == 1) {
	// for (Enumeration e = htCellEdit.keys(); e.hasMoreElements();) {
	// String key = e.nextElement().toString();
	// int oldRow = Integer.parseInt(key
	// .substring(0, key.indexOf(",")));
	// if (oldRow != row) {
	// if (oldRow > row) {
	// String newKey = (oldRow - 1)
	// + key.substring(key.indexOf(","));
	// temp.put(newKey, htCellEdit.get(key));
	// htCellEdit.remove(key);
	// } else
	// temp.put(key, htCellEdit.get(key));
	// }
	// }
	// }
	// htCellEdit = temp;
	// }
	/**
	 * ��ʼ��indexes����,Ϊ��������׼��
	 * 
	 * @throws
	 * @see
	 * @since v1.0
	 * @deprecated
	 */
	protected void allocate() {
		m_indexes = new int[getRowCount()];
		for (int i = 0; i < m_indexes.length; i++)
			m_indexes[i] = i;
	}

	/**
	 * �����ʾ����.
	 */
	@SuppressWarnings("unchecked")
	public void clearBodyData() {
		dataVector = new Vector();
		// ����к�
		getRowNOTableModel().setNumRows(0);
		// ���״̬
		// vRowState = new Vector();
		vRowAttrib = new Vector<RowAttribute>();
		// ����༭
		clearCellEdit();
		// �������
		m_indexes = null;
		// ����ϼ�
		clearTotalModel();
		// ������ɱ༭��
		setNotEditAllowedRows(null);
		// ���ɾ����
		if (vDeleteRow != null)
			vDeleteRow.removeAllElements();
		if (vViewRowCopy != null)
			vViewRowCopy.removeAllElements();

		// ������������ʱ��Ҫ������С��λ��
		if (m_biBodyItems != null)
			for (int i = 0; i < m_biBodyItems.length; i++) {
				m_biBodyItems[i].setMaxDecimalDigit(0);
			}

		// repaint
		fireTableChanged(new TableModelEvent(this));
	}

	/**
	 * ��������:(2003-6-20 11:11:56)
	 * 
	 * @param row
	 *            int
	 * @param col
	 *            int
	 * @param color
	 *            java.awt.Color
	 */
	void clearCellColor(String type) {
		for (int i = 0; i < getRowCount(); i++) {

			RowAttribute rowatt = getRowAttribute(i);

			if ("all".equals(type)) {
				rowatt.clearCellBackColor();
				rowatt.clearCellForeColor();
			} else if ("back".equals(type)) {
				rowatt.clearCellBackColor();
			} else
				rowatt.clearCellForeColor();
		}
	}

	/**
	 * ������ñ༭.
	 */
	public void clearCellEdit() {
		// htCellEdit = null;
		for (int i = 0; i < getRowCount(); i++) {
			getRowAttribute(i).clearCellEdit();
		}
	}

	/**
	 * ������ñ༭.
	 */
	public void clearCellShowWarning() {
		// htCellEdit = null;
		for (int i = 0; i < getRowCount(); i++) {
			getRowAttribute(i).clearCellShowWarning();
		}
	}

	/**
	 * ���ָ����ָ��������. ��������:(2001-9-14 10:18:13)
	 * 
	 * @param row
	 *            int :: view row index
	 * @param keys
	 *            java.lang.String[]
	 */
	public void clearRowData(int row, String[] keys) {
		if (row < 0 || row >= getRowCount())
			return;
		if (keys == null) {
			for (int i = 0; i < getColumnCount(); i++) {
				setValueAt("", row, i);
			}
		} else {
			for (int i = 0; i < keys.length; i++) {
				setValueAt("", row, keys[i]);
			}
		}
	}

	/**
	 * ����ϼƱ�����. ��������:(2001-11-2 16:13:22)
	 */
	public void clearTotalModel() {
		if (m_tmlTotal != null) {
			for (int i = 0; i < m_tmlTotal.getColumnCount(); i++) {

				m_tmlTotal.setValueAt(null, 0, i);
			}
		}
	}

	/**
	 * ��ñ��༭��. ��������:(2001-9-7 10:57:25)
	 * 
	 * @param newRowEdit
	 *            boolean
	 */
	// int[] convertIntoModelRow(int[] rows) {
	// if (rows != null) {
	// int[] mrows = new int[rows.length];
	// for (int i = 0; i < rows.length; i++)
	// mrows[i] = convertIntoModelRow(rows[i]);
	// rows = mrows;
	// }
	// return rows;
	// }
	/**
	 * �������ǰ���༭��. �㷨����,��ֱ��ʹ��row,����ת�� ��������:(2001-9-7 10:57:25)
	 * 
	 * @param newRowEdit
	 *            boolean
	 * @deprecated
	 */
	public int convertIntoModelRow(int row) {
		if (row < 0 || row >= getRowCount())
			return -1;
		// return (getSortIndex() != null && getSortIndex().length > row) ?
		// getSortIndex()[row]
		// : row;
		return row;
	}

	/**
	 * ��ñ��༭��. �㷨����,��ֱ��ʹ��row,����ת�� ��������:(2001-9-7 10:57:25)
	 * 
	 * @param newRowEdit
	 *            boolean
	 * @deprecated
	 */
	public int convertIntoViewRow(int row) {
		if (row < 0 || row >= getRowCount())
			return -1;
		// if (getSortIndex() != null && getSortIndex().length > row) {
		// for (int i = 0; i < getSortIndex().length; i++) {
		// if (getSortIndex()[i] == row)
		// return i;
		// }
		// }
		return row;
	}

	/**
	 * ���ƶ���.
	 */
	public void copyLine(int[] row) {
		if (vViewRowCopy == null)
			vViewRowCopy = new Vector<Vector<?>>();
		vViewRowCopy.removeAllElements();
		for (int i = 0; i < row.length; i++) {
			if (row[i] > -1 && row[i] < getRowCount()) {
				Vector<?> vRow = getRowVectorAtModel(row[i]);
				vViewRowCopy.add(vRow);
			}
		}
	}

	/**
	 * ��ʽִ����. ��������:(01-2-21 10:08:48)
	 */
	protected FormulaParse createDefaultFormulaParse() {
		return new FormulaParse();
	}

	/**
	 * �кű�ģʽ. ��������:(01-2-21 10:08:48)
	 */
	protected DefaultTableModel createDefaultRowNumberModel() {
		return new RowNumberModel();
	}

	/**
	 * �ϼ��б�ģʽ. ��������:(01-2-21 10:08:48)
	 */
	protected DefaultTableModel createDefaultTotalTableModel() {
		return new TotalTableModel(this);
	}

	private UFDouble createUFDouble(Object o) {
		if (o instanceof UFDouble)
			return (UFDouble) o;
		String v;
		if (o == null || (v = o.toString().trim()).length() == 0)
			v = "0";
		return new UFDouble(v);
	}

	/**
	 * ɾ������.
	 */
	@SuppressWarnings("unchecked")
	public void delLine(int[] array) {
		if (vDeleteRow == null)
			vDeleteRow = new Vector<Vector<?>>();

		// �ȶ�Ҫɾ�����кŽ�������,��ȷ���Ӻ���ǰɾ����
		int[] row = (int[]) array.clone();

		java.util.Arrays.sort(row);

		for (int i = row.length - 1; i >= 0; i--) {
			if (getRowStateModel(row[i]) == NORMAL
					|| getRowStateModel(row[i]) == MODIFICATION)
				vDeleteRow.add(getRowVectorAtModel(row[i]));

			if (getCellSpan() != null) {
				getCellSpan().delLine(row[i]);
			}

			removeRow(row[i]);

			// �к�
			getRowNOTableModel().removeRow(row[i]);

			vRowAttrib.remove(row[i]);
		}

		// ����ϼ�
		reCalcurateAll();
	}

	/**
	 * ִ�б༭��ʽ ��������:(01-4-26 15:29:06) please call method
	 * execEditFormulaByKey(int, String) instead column : view column
	 * 
	 * @deprecated
	 */
	public void execEditFormula(int row, int column) {
		column = getBodyColByCol(column);
		execEditFormulaByModelColumn(row, column);
	}

	/**
	 * ִ�б༭��ʽ ��������:(01-4-26 15:29:06)
	 */
	public void execEditFormulaByKey(int row, String key) {
		execEditFormulaByModelColumn(row, getBodyColByKey(key));
	}

	/**
	 * ִ�б༭��ʽ ��������:(01-4-26 15:29:06)
	 */
	void execEditFormulaByModelColumn(int row, int column) {
		formulaProcessor.execEditFormulaByModelColumn(row, column);
	}

	/**
	 * ִ�б༭��ʽ ��������:(01-4-26 15:29:06) please call method
	 * execEditFormulasByKey(int, String)
	 * 
	 * instead column : view column
	 * 
	 * @deprecated
	 */
	public void execEditFormulas(int row, int column) {
		// �����
		column = getBodyColByCol(column);
		execEditFormulasByModelColumn(row, column);
	}

	/**
	 * ִ�б༭��ʽ ��������:(01-4-26 15:29:06)
	 */
	public void execEditFormulasByKey(int row, String key) {
		execEditFormulasByModelColumn(row, getBodyColByKey(key));
	}

	/**
	 * ִ�б༭��ʽ ��������:(01-4-26 15:29:06)
	 */
	void execEditFormulasByModelColumn(int row, int column) {
		formulaProcessor.execEditFormulasByModelColumn(row, column);
	}

	/**
	 * ���ñ༭������ֵ ��������:(01-4-26 15:29:06)
	 */
	protected void loadEditRelationItemValue(int row, int column) {
		loadRelationItemValue(row, column, true);
	}

	/**
	 * ���ü��Թ�����ֵ ��������:(01-4-26 15:29:06)
	 */
	public void loadLoadRelationItemValue(int row, String itemkey) {
		loadRelationItemValue(row, getBodyColByKey(itemkey), false);
	}

	/**
	 * ���ñ༭������ֵ ��������:(01-4-26 15:29:06)
	 */
	private void loadRelationItemValue(int row, int column, boolean isedit) {
		BillItem item = getBodyItems()[column];

		if (item.getDataType() == IBillItem.UFREF
				&& item.getMetaDataProperty() != null) {

			ArrayList<IConstEnum> relationitem = getMetaDataRelationItems(item,
					isedit);

			if (relationitem != null) {
				if (getValueAt(row, column) != null) {
					String id = (String) ((IConstEnum) getValueAt(row, column))
							.getValue();
					// IConstEnum Value Object[]:,Key:itemkey
					IConstEnum[] o = item.getGetBillRelationItemValue()
							.getRelationItemValue(relationitem,
									new String[] { id });
					if (o != null) {
						for (int i = 0; i < o.length; i++) {
							if (o[i].getValue() != null) {
								Object[] v = (Object[]) o[i].getValue();
								setValueAt(v[0], row, o[i].getName());

							}
						}
					}
				} else {
					for (int i = 0; i < relationitem.size(); i++) {
						setValueAt(null, row, relationitem.get(i).getName());
					}
				}
			}
		}

	}

	public void loadEditRelationItemValue(int startrow, int endrow,
			String itemkey) {
		loadRelationItemValue(startrow, endrow, getBodyColByKey(itemkey), true);
	}

	/**
	 * ���ñ༭������ֵ ��������:(01-4-26 15:29:06)
	 */
	private void loadRelationItemValue(int startrow, int endrow, int column,
			boolean isedit) {

		if (endrow - startrow < 0)
			return;

		BillItem item = getBodyItems()[column];

		if (item.getDataType() == IBillItem.UFREF
				&& item.getMetaDataProperty() != null) {

			ArrayList<IConstEnum> relationitem = getMetaDataRelationItems(item,
					isedit);

			if (relationitem != null) {
				// if (getValueAt(row, column) != null) {
				// String id = (String) ((IConstEnum) getValueAt(row,
				// column)).getValue();

				String[] ids = new String[endrow - startrow + 1];

				for (int row = startrow; row < endrow + 1; row++) {
					Object o = getValueAt(row, column);
					if (o != null && o instanceof IConstEnum)
						ids[row - startrow] = (String) ((IConstEnum) o)
								.getValue();
				}

				// IConstEnum Value Object[]:,Key:itemkey
				IConstEnum[] o = item.getGetBillRelationItemValue()
						.getRelationItemValue(relationitem, ids);
				// if (o != null) {
				// for (int i = 0; i < o.length; i++) {
				// if (o[i].getValue() != null) {
				// Object[] v = (Object[]) o[i].getValue();
				// setValueAt(v[0], row, o[i].getName());
				//
				// }
				// }
				// }
				for (int row = startrow; row < endrow + 1; row++) {
					if (o != null) {
						for (int i = 0; i < o.length; i++) {
							if (o[i].getValue() != null) {
								Object[] v = (Object[]) o[i].getValue();
								setValueAt(v[row - startrow], row,
										o[i].getName());
							}
						}
					}
				}
				// } else {
				// for (int i = 0; i < relationitem.size(); i++) {
				// setValueAt(null, row, relationitem.get(i).getName());
				// }
				// }
			}
		}

	}

	/**
	 * ���ñ༭������ֵ ��������:(01-4-26 15:29:06)
	 */
	public void loadEditRelationItemValue(int row, String itemkey) {
		loadEditRelationItemValue(row, getBodyColByKey(itemkey));

	}

	// public void loadLoadRelationItemValue() {
	// if (getRowCount() < 0)
	// return;
	//
	// for (int col = 0; col < getBodyItems().length; col++) {
	// BillItem item = getBodyItems()[col];
	//
	// if (item.getDataType() == IBillItem.UFREF && item.getMetaDataProperty()
	// != null) {
	//
	// ArrayList<IConstEnum> relationitem = getMetaDataRelationItems(item,
	// false);
	//
	// if (relationitem != null) {
	//
	// String[] ids = new String[getRowCount()];
	//
	// for (int row = 0; row < getRowCount(); row++) {
	// Object o = getValueAt(row, col);
	// if (o != null && o instanceof IConstEnum)
	// ids[row] = (String) ((IConstEnum) o).getValue();
	// }
	//
	// IConstEnum[] o =
	// item.getGetBillRelationItemValue().getRelationItemValue(relationitem,
	// ids);
	//
	// for (int row = 0; row < getRowCount(); row++) {
	// if (o != null) {
	// for (int i = 0; i < o.length; i++) {
	// if (o[i].getValue() != null) {
	// Object[] v = (Object[]) o[i].getValue();
	// setValueAt(v[row], row, o[i].getName());
	// }
	// }
	// }
	// }
	// }
	// }
	// }
	// }

	public void loadLoadRelationItemValue() {
		if (getRowCount() <= 0)
			return;

		loadLoadRelationItemValue(0, getRowCount() - 1);

	}

	public void loadLoadRelationItemValue(BillItem[] billItems, int startRow,
			int endRow) {

		int erow = endRow;
		int srow = startRow;

		if (erow > getRowCount() - 1) {
			erow = getRowCount() - 1;
		}
		if (srow < 0) {
			srow = 0;
		}

		MetaDataGetBillModelRelationItemValue gvs = new MetaDataGetBillModelRelationItemValue();
		// 2011-4-2 ���ͯΰ �Զ����ϵȡֵ����
		Map<Integer, IConstEnum[]> valuemap = new HashMap<Integer, IConstEnum[]>();
		// ����������ظ���������¼���е��к�
		Map<String, List<Integer>> keyListMap = new HashMap<String, List<Integer>>();

		// ������ȡֵ
		getRelationItemValues(billItems, erow, srow, gvs, valuemap, keyListMap);
		// �����ֵ
		setRelationValueToModel(erow, srow, gvs, valuemap, keyListMap);

	}

	// 2011-4-2 ���ͯΰ �Զ����ϵȡֵ����
	Map<Integer, IConstEnum[]> valuemapByBatch = new HashMap<Integer, IConstEnum[]>();
	// ����������ظ���������¼���е��к�
	Map<String, List<Integer>> keyListMapByBatch = new HashMap<String, List<Integer>>();

	//��ҳǩ������׼������
	public void prepareLoadLoadRelationItemValues(
			MetaDataGetBillModelRelationItemValue gvs) {
		valuemapByBatch.clear();
		keyListMapByBatch.clear();
		int erow = getRowCount() - 1;
		int srow = 0;

		if (erow > getRowCount() - 1) {
			erow = getRowCount() - 1;
		}
		if (srow < 0) {
			srow = 0;
		}
        BillItem[] billItems = getBodyItems();
		
		int m = 0;
		for (int col = 0; col < billItems.length; col++) {
			BillItem item = billItems[col];

			if (item.getDataType() == IBillItem.UFREF
					&& item.getMetaDataProperty() != null) {

				ArrayList<IConstEnum> relationitem = getMetaDataRelationItems(
						item, false);

				if (relationitem != null) {

					// String[] ids = new String[getRowCount()];
					List<Integer> rowMapList = new ArrayList<Integer>();

					Map<String, Integer> pk2RowMap = new HashMap<String, Integer>();
					List<String> pkList = new ArrayList<String>();
					int dataIndex = 0;

					for (int row = srow; row <= erow; row++) {
						// Object o = getValueAt(row, col);
						Object o = getValueAt(row,
								getBodyColByKey(billItems[col].getKey()));
						if (o != null && o instanceof IConstEnum) {
							if (((IConstEnum) o).getName() == null) {
								rowMapList.add(null);
								continue;
							}
							String pk = (String) ((IConstEnum) o).getValue();

							if (pk2RowMap.get(pk) == null) {
								pk2RowMap.put(pk, Integer.valueOf(dataIndex));
								pkList.add(pk);
								dataIndex++;
							}
							// ������ظ��У���¼�������������е�λ��
							rowMapList.add(pk2RowMap.get(pk));
						} else {
							rowMapList.add(null);
						}
					}
					// ���ظ�Pk���У����кż���List
					if (pkList.size() > 0 && pkList.size() != (erow - srow + 1)) {

						for (int i = 0; i < relationitem.size(); i++) {
							keyListMapByBatch.put(relationitem.get(i).getName(),
									rowMapList);
						}
					}
					String[] ids = pkList.toArray(new String[0]);
					if (ids.length > 0) {
						gvs.addRelationItemByModel(this,item, relationitem, ids);
						// gvs.addRelationItem(item, relationitem, ids);
						if (!(item.getGetBillRelationItemValue() instanceof MetaDataGetBillRelationItemValue)) {
							valuemapByBatch.put(m, item.getGetBillRelationItemValue()
									.getRelationItemValue(relationitem, ids));
						}

						m++;
					}

				}
			}
		}
		

	}
	//��ҳǩ������������ֵ��
	public void setLoadLoadRelationItemValuesToModel(IConstEnum[] o) {
		int erow = getRowCount() - 1;
		int srow = 0;

		if (erow > getRowCount() - 1) {
			erow = getRowCount() - 1;
		}
		if (srow < 0) {
			srow = 0;
		}
		
		if (o == null) {
			return;
		}
        setNeedCalculate(false);
		for (int i = 0; i < o.length; i++) {
			if (valuemapByBatch.get(i) != null) {
				o[i] = valuemapByBatch.get(i)[0];
			}
		}

		for (int row = srow; row <= erow; row++) {
			if (o != null) {
				for (int i = 0; i < o.length; i++) {
					if (o[i].getValue() != null) {
						Object[] v = (Object[]) o[i].getValue();
						if (keyListMapByBatch.get(o[i].getName()) != null) {
							// ������ظ��������У����к����¶�λ
							List<Integer> list = keyListMapByBatch.get(o[i].getName());
							if (list.get(row - srow) != null) {
								setValueAt(v[list.get(row - srow)], row,
										o[i].getName());
							} else {
								setValueAt(null, row, o[i].getName());
							}

						} else {
							setValueAt(v[row - srow], row, o[i].getName());
						}

					}
				}
			}
		}
		setNeedCalculate(true);
	
	}

	/**
	 * ���itemkeys==null,��Ϊ��ȫ���� 2013-1-29 �����ظ��������У��ϲ��ظ�����
	 **/

	public void loadLoadRelationItemValue(int startRow, int endRow,
			String[] itemkeys) {
		BillItem[] billItems = null;
        if(itemkeys == null){
        	billItems = getBodyItems();
        }else{
        	billItems =  getBillItems(itemkeys);
        }
		
		loadLoadRelationItemValue(billItems, startRow, endRow);
	}

	// ������ȡֵ
	private void getRelationItemValues(BillItem[] billItems, int erow,
			int srow, MetaDataGetBillModelRelationItemValue gvs,
			Map<Integer, IConstEnum[]> valuemap,
			Map<String, List<Integer>> keyListMap) {

		// BillItem[] billItems = getBillItems(itemkeys);

		if (billItems == null) {
			billItems = getBodyItems();
		}
		int m = 0;
		for (int col = 0; col < billItems.length; col++) {
			BillItem item = billItems[col];

			if (item.getDataType() == IBillItem.UFREF
					&& item.getMetaDataProperty() != null) {

				ArrayList<IConstEnum> relationitem = getMetaDataRelationItems(
						item, false);

				if (relationitem != null) {

					// String[] ids = new String[getRowCount()];
					List<Integer> rowMapList = new ArrayList<Integer>();

					Map<String, Integer> pk2RowMap = new HashMap<String, Integer>();
					List<String> pkList = new ArrayList<String>();
					int dataIndex = 0;

					for (int row = srow; row <= erow; row++) {
						// Object o = getValueAt(row, col);
						Object o = getValueAt(row,
								getBodyColByKey(billItems[col].getKey()));
						if (o != null && o instanceof IConstEnum) {
							if (((IConstEnum) o).getName() == null) {
								rowMapList.add(null);
								continue;
							}
							String pk = (String) ((IConstEnum) o).getValue();

							if (pk2RowMap.get(pk) == null) {
								pk2RowMap.put(pk, Integer.valueOf(dataIndex));
								pkList.add(pk);
								dataIndex++;
							}
							// ������ظ��У���¼�������������е�λ��
							rowMapList.add(pk2RowMap.get(pk));
						} else {
							rowMapList.add(null);
						}
					}
					// ���ظ�Pk���У����кż���List
					if (pkList.size() > 0 && pkList.size() != (erow - srow + 1)) {

						for (int i = 0; i < relationitem.size(); i++) {
							keyListMap.put(relationitem.get(i).getName(),
									rowMapList);
						}
					}
					String[] ids = pkList.toArray(new String[0]);
					if (ids.length > 0) {
						gvs.addRelationItem(item, relationitem, ids);
						// gvs.addRelationItem(item, relationitem, ids);
						if (!(item.getGetBillRelationItemValue() instanceof MetaDataGetBillRelationItemValue)) {
							valuemap.put(m, item.getGetBillRelationItemValue()
									.getRelationItemValue(relationitem, ids));
						}

						m++;
					}

				}
			}
		}
	}

	// ������ֵ����Model
	private void setRelationValueToModel(int erow, int srow,
			MetaDataGetBillModelRelationItemValue gvs,
			Map<Integer, IConstEnum[]> valuemap,
			Map<String, List<Integer>> keyListMap) {
		IConstEnum[] o = gvs.getRelationItemValues();
		if (o == null) {
			return;
		}
		//������ղ���ʾ������
		o = dealRefValue4Task2CommissionType(o);
		for (int i = 0; i < o.length; i++) {
			if (valuemap.get(i) != null) {
				o[i] = valuemap.get(i)[0];
			}
		}

		for (int row = srow; row <= erow; row++) {
			if (o != null) {
				for (int i = 0; i < o.length; i++) {
					if (o[i].getValue() != null) {
						Object[] v = (Object[]) o[i].getValue();
						if (keyListMap.get(o[i].getName()) != null) {
							// ������ظ��������У����к����¶�λ
							List<Integer> list = keyListMap.get(o[i].getName());
							if (list.get(row - srow) != null) {
								setValueAt(v[list.get(row - srow)], row,
										o[i].getName());
							} else {
								setValueAt(null, row, o[i].getName());
							}

						} else {
							setValueAt(v[row - srow], row, o[i].getName());
						}

					}
				}
			}
		}
	}

	private IConstEnum[] dealRefValue4Task2CommissionType(IConstEnum[] o) {
		if(o==null||o.length <= 0){
			return o;
		}
		if(this.bodys!=null&&this.bodys[0]!=null
				&&this.bodys[0].getContainmentObject()!=null
				&&this.bodys[0].getContainmentObject() instanceof AggTaskHVO){
			try{
				//�ҵ����в���<reffield,<refPk,refName>>
				Map<String,Map<String,String>> refNamePkMap = new HashMap();
				//��֮��Ӧ�Ĳ���˳��
				Map<String,Object[]> realPksMap = new HashMap();
				
				for(IConstEnum constEnum : o){
					if(constEnum!=null && constEnum.getName()!=null){
						String itemKey = constEnum.getName();
						if(itemKey.equals("pk_commission_h.pk_maincategory_ID")){
							//��Ʒ����
							addItem(refNamePkMap,constEnum,"pk_maincategory",realPksMap);
						}else if(itemKey.equals("pk_commission_h.pk_subcategory_ID")){
							//��������
							addItem(refNamePkMap,constEnum,"pk_subcategory",realPksMap);
						}else if(itemKey.equals("pk_commission_h.pk_commissiontype_ID")){
							//ί�е����� 	pk_commissiontype
							addItem(refNamePkMap,constEnum,"pk_commissiontype",realPksMap);
						}else if(itemKey.equals("pk_commission_h.codeprefix_ID")){
							//��ʾ����	ί�е�����ǰ׺ ��Ŀ����	codeprefix
							addItem(refNamePkMap,constEnum,"codeprefix",realPksMap);
						}else if(itemKey.equals("pk_commission_h.pk_lastcategory_ID")){
							//��ʾ����	�������� ��Ŀ����	pk_lastcategory
							addItem(refNamePkMap,constEnum,"pk_lastcategory",realPksMap);
						}else if(itemKey.equals("pk_commission_h.reportformat_ID")){
							//��ʾ����	�����ʽ ��Ŀ����	reportformat
							addItem(refNamePkMap,constEnum,"reportformat",realPksMap);
						}else if(itemKey.equals("pk_commission_h.reportlang_ID")){
							//��ʾ����	�������� ��Ŀ����	reportlang
							addItem(refNamePkMap,constEnum,"reportlang",realPksMap);
						}else if(itemKey.equals("pk_commission_h.sampledealtype_ID")){
							//��ʾ����	�����Ʒ���� ��Ŀ����	sampledealtype
							addItem(refNamePkMap,constEnum,"sampledealtype",realPksMap);
						}else if(itemKey.equals("pk_commission_h.productproperty_ID")){
							//��ʾ����	��Ʒ���� ��Ŀ����	productproperty
							addItem(refNamePkMap,constEnum,"productproperty",realPksMap);
						}else if(itemKey.equals("pk_commission_h.customertype_ID")){
							//��ʾ����	�ͻ����� ��Ŀ����	customertype
							addItem(refNamePkMap,constEnum,"customertype",realPksMap);
						}else if(itemKey.equals("pk_commission_h.testrequirement_ID")){
							//��ʾ����	�������� ��Ŀ����	testrequirement
							addItem(refNamePkMap,constEnum,"testrequirement",realPksMap);
						}else if(itemKey.equals("pk_commission_h.checkingproperty_ID")){
							//��ʾ����	������� ��Ŀ����	checkingproperty
							addItem(refNamePkMap,constEnum,"checkingproperty",realPksMap);
						}else if(itemKey.equals("pk_commission_h.identificationtype_ID")){
							//��ʾ����	��Ʒ�������� ��Ŀ����	identificationtype
							addItem(refNamePkMap,constEnum,"identificationtype",realPksMap);
						}else if(itemKey.equals("pk_commission_h.certificationtype_ID")){
							//��ʾ����	��֤���� ��Ŀ����	certificationtype
							addItem(refNamePkMap,constEnum,"certificationtype",realPksMap);
						}
					}
				}
				o = NCLocator.getInstance().lookup(ICommissionMaintain.class)
						.getRefName(o,refNamePkMap,realPksMap);
				return o;
			}catch(Exception e){
				Logger.error(e.getCause());
			}
		}
		return o;
	}
	

	private static void addItem(Map<String, Map<String,String>> refNamePk,
			IConstEnum constEnum,String reffield,Map<String,Object[]> realPksMap) {
		Object[] pks = (Object[]) constEnum.getValue();
		realPksMap.put(reffield, pks);
		if (pks != null && pks.length > 0) {
			for (Object pk : pks) {
				if (pk != null) {
					if (refNamePk.get(reffield) == null) {
						refNamePk.put(reffield, new HashMap<String,String>());
					}
					refNamePk.get(reffield).put(String.valueOf(pk),null);
				}
			}
		}
	}

	private BillItem[] getBillItems(String[] itemkeys) {
		if (itemkeys == null)
			return null;
		BillItem[] billItems = new BillItem[itemkeys.length];
		for (int i = 0; i < itemkeys.length; i++) {
			billItems[i] = getItemByKey(itemkeys[i]);
		}
		return billItems;
	}

	public void loadLoadRelationItemValue(int startRow, int endRow) {

		loadLoadRelationItemValue(startRow, endRow, null);

	}

	public void loadLoadRelationItemValue(int row) {
		loadLoadRelationItemValue(row, row);
		// if (getRowCount() < 0)
		// return;
		//
		// for (int col = 0; col < getBodyItems().length; col++) {
		// BillItem item = getBodyItems()[col];
		//
		// if (item.getDataType() == IBillItem.UFREF &&
		// item.getMetaDataProperty() != null) {
		//
		// ArrayList<IConstEnum> relationitem = getMetaDataRelationItems(item,
		// false);
		//
		// if (relationitem != null) {
		//
		// String id = null;
		// Object obj = getValueAt(row, col);
		// if (obj != null && obj instanceof IConstEnum)
		// id = (String) ((IConstEnum) obj).getValue();
		//
		// IConstEnum[] o =
		// item.getGetBillRelationItemValue().getRelationItemValue(relationitem,
		// new String[] { id });
		//
		// if (o != null) {
		// for (int i = 0; i < o.length; i++) {
		// if (o[i].getValue() != null) {
		// Object[] v = (Object[]) o[i].getValue();
		// setValueAt(v[0], row, o[i].getName());
		// }
		// }
		// }
		// }
		// }
		// }
	}

	/*
	 * IConstEnum Value metadatapath:,Name:itemkey
	 */
	protected ArrayList<IConstEnum> getMetaDataRelationItems(BillItem item,
			boolean isediting) {

		if (item.getDataType() != IBillItem.UFREF)
			return null;

		if (item.getMetaDataProperty() == null)
			return null;

		if (item.getMetaDataProperty().isRefAttribute())
			return null;

		ArrayList<IConstEnum> ics = new ArrayList<IConstEnum>();

		// ���ITEM��ʾ����
		// ���ع������ʾ���Ƽ���
		if (!isediting) {
			String showattname = getRefItemShowAttributeName(item);

			if (showattname != null) {
				IConstEnum ic = new DefaultConstEnum(showattname, item.getKey());
				ics.add(ic);
			}
		}

		// ���ITEM�༭��������Ĭ��ֵ����
		// itemkey,����·��:AAAA=B.A,BBBB=B.B
		// �༭������
		if (item.getMetaDataRelation() != null && isediting) {
			IConstEnum[] ies = BillUtil.getConstEnumByString(item
					.getMetaDataRelation());

			for (int i = 0; i < ies.length; i++) {

				BillItem ritem = getItemByKey(ies[i].getName());

				if (ies[i].getValue() != null) {
					if (!addRefRelationItem(ics, ritem,
							(String) ies[i].getValue())) {
						ics.add(ies[i]);
					}
				}
			}
		}

		// ���ITEM��������
		if (item.getRelationItem() != null) {
			for (int i = 0; i < item.getRelationItem().size(); i++) {
				BillItem ritem = item.getRelationItem().get(i);

				if (ritem.getMetaDataAccessPath() != null) {
					if (!addRefRelationItem(ics, ritem,
							ritem.getMetaDataAccessPath())) {
						IConstEnum ic = new DefaultConstEnum(
								ritem.getMetaDataAccessPath(), ritem.getKey());
						ics.add(ic);
					}
				}

			}
		}

		if (ics.size() == 0)
			ics = null;

		return ics;

	}

	private boolean addRefRelationItem(ArrayList<IConstEnum> ics,
			BillItem ritem, String path) {

		if (ritem.getDataType() == IBillItem.UFREF) {
			IConstEnum ic = null;
			ic = new DefaultConstEnum(path, ritem.getKey()
					+ IBillItem.ID_SUFFIX);
			ics.add(ic);
			// UFREF����
			String showattname = getRefItemShowAttributeName(ritem);
			if (showattname != null) {
				ic = new DefaultConstEnum(path + "." + showattname,
						ritem.getKey());
				ics.add(ic);
				return true;
			}
		}

		return false;
	}

	private String getRefItemShowAttributeName(BillItem item) {
		String showattname = null;
//����ΪʲôҪ�ж��Ƿ���ҳǩ��ʾ�ŷ�����ʾ���ƻ���룿���������ʾ������
//Ϊ���������⣺60070401 ��ְ�Ǽǣ�֤�������ֶΣ� ���б����Ĭ�ϲ���ʾ�Ĳ��������ֶΣ��������Ŀ��������Ϊ��ʾ������ʾ������
		
		if (item.getDataType() == IBillItem.UFREF && (item.isShow()||item.isShareShow())){
//		if (item.getDataType() == IBillItem.UFREF && item.isShareShow()) {
			//����ʾ���ֶΣ�����ع������һ������������в���ȷ��ֵ������ĳ���ֶ�pk_org����pk_group��ֵ����� ���ع�������pk_orgֵ��ա�
			//���й���ע������֯����Ϊ���ŵĵ����ڵ�������ʱ��һ���ֶ�����ֵ����ȥ�������ֶ�ѡ���򱨴�,�������pk_orgԭ�������ع��������ڼ��ص�û��ֵ����ˡ�
			
//		if(item.getDataType() == IBillItem.UFREF){
			if (item.isRefReturnCode())
				showattname = item.getMetaDataProperty()
						.getBDCodeAttributeName();
			else
				showattname = item.getMetaDataProperty()
						.getBDNameAttributeName();
		}
		return showattname;
	}

	/**
	 * ִ�й�ʽ(�ӽ�����ȡֵ) ��������:(01-4-26 15:29:06)
	 */
	public void execFormula(int row, String[] formulas) {
		formulaProcessor.execFormula(row, formulas);
	}

	/**
	 * ����ִ�й�ʽ ��������:(01-4-26 15:29:06)
	 */
	public void execFormulas(String[] formulas) {
		execFormulas(formulas, -1, -1);
	}

	/**
	 * ����ִ�й�ʽ ��������:(01-4-26 15:29:06)
	 */
	public void execFormulas(String[] formulas, int beginRow, int endRow) {
		formulaProcessor.execFormulas(formulas, beginRow, endRow, false);
	}

	public void execEditFormulas(int row) {
		formulaProcessor.execEditFormulas(row, row, false);
	}
	public void execEditFormulasByRows(int beginRow,int endRow) {
		formulaProcessor.execEditFormulas(beginRow, endRow, false);
	}

	/**
	 * ����ִ�й�ʽ ��������:(01-4-26 15:29:06)
	 */
	public void execFormulas(int row, String[] formulas) {
		formulaProcessor.execFormulas(row, formulas);
	}

	/**
	 * ִ�й�ʽ,�����ֱ�Ӹ���VO,����ʾ������! ��������:(01-4-26 15:29:06)
	 */
	public void execFormulasWithVO(CircularlyAccessibleValueObject[] VOs,
			String[] formulas) {
		formulaProcessor.execFormulasWithVO(VOs, formulas);
	}

	/**
	 * ִ�м��ع�ʽ ��������:(01-4-26 15:29:06)
	 */
	public void execLoadFormula() {
		// long t = System.currentTimeMillis();
		if (getRowCount() > 0)
			formulaProcessor.execLoadFormula();

	}

	/**
	 * ִ�м��ع�ʽ ��������:(01-4-26 15:29:06)
	 */
	public void execLoadFormulaByRow(int row) {
		// long t = System.currentTimeMillis();
		if (getRowCount() > 0)
			formulaProcessor.execLoadFormulas(row, row, false);

	}

	/**
	 * ִ�м��ع�ʽ ��������:(01-4-26 15:29:06) column : model column please call method
	 * execLoadFormulaByKey(String) instead
	 * 
	 * @deprecated
	 */
	public void execLoadFormula(int column) {
		column = getBodyColByCol(column);
		formulaProcessor.execLoadFormula(column);
	}

	/**
	 * ִ�м��ع�ʽ ��������:(01-4-26 15:29:06)
	 */
	public void execLoadFormulaByKey(String key) {
		formulaProcessor.execLoadFormula(getBodyColByKey(key));
	}

	/**
	 * ִ�м��ع�ʽ ��������:(01-4-26 15:29:06) column : model column please call method
	 * execLoadFormulasByKey(String) instead
	 * 
	 * @deprecated
	 */
	public void execLoadFormulas(int column) {
		column = getBodyColByCol(column);
		formulaProcessor.execLoadFormulas(column);
	}

	/**
	 * ִ�м��ع�ʽ ��������:(01-4-26 15:29:06)
	 */
	public void execLoadFormulasByKey(String key) {
		formulaProcessor.execLoadFormulas(getBodyColByKey(key));
	}

	/*
	 * if formulas is not null, itemkeys ignore;
	 */
	public boolean execValidateForumlas(String[] formulas, String[] itemkeys,
			int[] rows) {
		return getFormulaProcessor().execValidateForumlas(formulas, itemkeys,
				rows);
	}

	/**
	 * ��ô���״̬����. ��������:(2001-5-9 14:03:18)
	 */
	@SuppressWarnings("unchecked")
	public Vector<?> getBillModelData() {
		Vector data = new Vector();
		// ��״̬
		// data.addElement(transferRowState(vRowState));
		data.add(vRowAttrib);
		// ����
		data.add(dataVector);
		return data;
	}

	/**
	 * @return Returns the billScrollPane.
	 */
	BillScrollPane getBillScrollPane() {
		return billScrollPane;
	}

	/**
	 * ����ʾ�ж�Ӧʵ���к�. ��������:(01-2-21 14:47:10)
	 * 
	 * ʹ��JTable.convertColumnIndexToModel�����滻
	 * 
	 * @param strKey
	 *            java.lang.String please call this method from Billscrollpane
	 * @return java.lang.String
	 * @deprecated
	 */
	public int getBodyColByCol(int col) {
		int n = -1;
		// ���Ϲ̶���
		if (fixCol > -1) {
			col = col + fixCol;
		}
		for (int i = 0; i < getBodyItems().length; i++) {
			BillItem item = getBodyItems()[i];
			if (item.isShow())
				n++;
			if (n == col)
				return i;
		}
		Logger.info("û���ҵ�" + col + "�ж�Ӧʵ����.");
		return -1;
	}

	/**
	 * ��item�кŵõ���ʾ��. ��������:(01-2-21 14:47:10)
	 * 
	 * ʹ��JTable.convertColumnIndexToModel�滻
	 * 
	 * @param strKey
	 *            java.lang.String please call this method from BillScrollPane
	 * @return java.lang.String
	 * @deprecated
	 */
	public int getBodyColByIndex(int index) {
		int n = 0;
		for (int i = 0; i <= index; i++) {
			BillItem item = getBodyItems()[i];
			if (item.isShow())
				n++;
		}
		if (n > 0)
			return n + fixCol;
		return -1;
	}

	/**
	 * �ɹؼ��ֶ�Ӧ�к�. ��������:(01-2-21 14:47:10)
	 */
	public int getBodyColByKey(String strKey) {
		if (strKey == null)
			return -1;
		try {
			if ((htBodyItems != null)
					&& (htBodyItems.containsKey(strKey = strKey.trim())))
				return ((Integer) htBodyItems.get(strKey)).intValue();
		} catch (Exception e) {
			Logger.debug(e.getMessage());
		}
		Logger.info("û���ҵ�" + strKey + "��Ӧ�ؼ�����.");
		return -1;
	}

	/**
	 * ���Ԫ��. ��������:(01-2-21 10:08:48)
	 */
	public BillItem[] getBodyItems() {
		return m_biBodyItems;
	}

	/**
	 * ����ʾ�ж�Ӧ�ؼ���. ��������:(01-2-21 14:47:10) please call this method from
	 * 
	 * ʹ��BillScrollPane.getBodyKeyByCol�滻
	 * 
	 * @deprecated
	 */
	public String getBodyKeyByCol(int col) {
		String key = null;
		int n = -1;

		// ���Ϲ̶���
		if (fixCol > -1) {
			col = col + fixCol;
		}
		for (int i = 0; i < getBodyItems().length; i++) {
			BillItem item = getBodyItems()[i];
			if (item.isShow())
				n++;
			if (n == col)
				return item.getKey();
		}
		Logger.debug("û���ҵ�" + col + "�ж�Ӧʵ����.");
		return key;
	}

	/*
	 * �õ���ѡ���VO����
	 */

	public CircularlyAccessibleValueObject[] getBodySelectedVOs(
			String bodyVOName) {
		return getBodySelectedVOs(bodyVOName, -1);
	}

	/*
	 * �õ���ѡ���VO����
	 */

	public CircularlyAccessibleValueObject[] getBodySelectedVOs(
			String bodyVOName, int count) {
		try {
			Class<?> bodyVOClass = Class.forName(bodyVOName);
			Vector<CircularlyAccessibleValueObject> vBodyVOs = getSelectedVector(
					bodyVOName, count, false)[0];
			if (vBodyVOs != null) {
				CircularlyAccessibleValueObject[] bodyVOs = (CircularlyAccessibleValueObject[]) Array
						.newInstance(bodyVOClass, vBodyVOs.size());
				vBodyVOs.copyInto(bodyVOs);
				return bodyVOs;
			}
		} catch (ClassNotFoundException e) {
			Logger.debug(e);
		}
		return null;
	}

	/*
	 * �õ���ѡ���VO����
	 */
	ArrayList<Object[]> getBodySelectedVOsAndRowNos(String bodyVOName,
			int count, boolean hasRowNumber) {
		Vector[] vecs = getSelectedVector(bodyVOName, count, hasRowNumber);
		if (vecs == null || vecs.length == 0)
			return null;
		Vector vecBody = vecs[0];
		Vector vecNo = vecs[1];
		if (vecBody == null)
			return null;
		int allCount = vecBody.size();
		ArrayList<Object[]> list = new ArrayList<Object[]>(allCount);
		for (int i = 0; i < allCount; i++) {
			list.add(new Object[] { vecBody.elementAt(i), vecNo.elementAt(i) });
		}
		return list;
	}

	/*
	 * �õ��仯��VO����
	 */

	public CircularlyAccessibleValueObject[] getBodyValueChangeVOs(
			String bodyVOName) {
		try {
			Class<?> bodyVOClass = Class.forName(bodyVOName);
			Vector<CircularlyAccessibleValueObject> vBodyVOs = getValueChangeVector(bodyVOName);
			if (vBodyVOs != null) {
				CircularlyAccessibleValueObject[] bodyVOs = (CircularlyAccessibleValueObject[]) Array
						.newInstance(bodyVOClass, vBodyVOs.size());
				vBodyVOs.copyInto(bodyVOs);
				return bodyVOs;
			}
		} catch (ClassNotFoundException e) {
			Logger.debug(e);
		}
		return null;
	}

	/*
	 * �õ��仯��VO����
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object>[] getBodyChangeValueByMetaData() {
		ArrayList<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		// ɾ����
		if (vDeleteRow != null) {
			for (int i = 0; i < vDeleteRow.size(); i++) {
				Vector rowVector = (Vector) vDeleteRow.elementAt(i);
				HashMap<String, Object> map = new HashMap<String, Object>();
				for (int j = 0; j < getBodyItems().length; j++) {
					BillItem item = getBodyItems()[j];
					if (item.getMetaDataProperty() != null
							&& item.getIDColName() == null) {
						Object aValue = rowVector.elementAt(j);
						aValue = item.converType(aValue);
						map.put(item.getMetaDataAccessPath(), aValue);
					}
				}
				// ����״̬
				map.put(ElementConstant.KEY_VOSTATUS, VOStatus.DELETED);
				maps.add(map);
			}
		}
		for (int i = 0; i < getRowCount(); i++) {
			if (getRowState(i) != NORMAL) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				for (int j = 0; j < getBodyItems().length; j++) {
					BillItem item = getBodyItems()[j];
					if (item.getMetaDataProperty() != null
							&& item.getIDColName() == null) {
						Object aValue = getValueAt(i, j);
						aValue = item.converType(aValue);
						map.put(item.getMetaDataAccessPath(), aValue);
					}
				}
				// ����״̬
				switch (getRowState(i)) {
				case ADD: {
					map.put(ElementConstant.KEY_VOSTATUS, VOStatus.NEW);
					break;
				}
				case MODIFICATION: {
					map.put(ElementConstant.KEY_VOSTATUS, VOStatus.UPDATED);
					break;
				}
				}
				maps.add(map);
			}
		}

		Map<String, Object>[] m = new Map[maps.size()];

		maps.toArray(m);

		return m;
	}

	/*
	 * �õ��仯��VO����
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object>[] getBodyValueByMetaData() {
		ArrayList<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();

		for (int i = 0; i < getRowCount(); i++) {
			Map<String, Object> map = getBodyRowValueByMetaData(i);
			maps.add(map);
		}

		Map<String, Object>[] m = new Map[maps.size()];

		maps.toArray(m);

		return m;
	}

	/*
	 * �õ���VO
	 */

	public Map<String, Object> getBodyRowValueByMetaData(int row) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		for (int j = 0; j < getBodyItems().length; j++) {
			BillItem item = getBodyItems()[j];
			if (item.getMetaDataProperty() != null
					&& item.getIDColName() == null) {
				Object aValue = getValueAt(row, j);
				aValue = item.converType(aValue);
				map.put(item.getMetaDataAccessPath(), aValue);
			}
		}
		// ����״̬
		switch (getRowState(row)) {
		case ADD: {
			map.put(ElementConstant.KEY_VOSTATUS, VOStatus.NEW);
			break;
		}
		case MODIFICATION: {
			map.put(ElementConstant.KEY_VOSTATUS, VOStatus.UPDATED);
			break;
		}
		}

		return map;
	}

	/*
	 * �õ��仯��VO����
	 */

	public GeneralSuperVO[] getBodyValueChangeVOs(GeneralSuperVO initVO) {
		try {
			Vector<GeneralSuperVO> vBodyVOs = getValueChangeVector(initVO);
			if (vBodyVOs != null) {
				GeneralSuperVO[] bodyVOs = GeneralSuperVO.createVOArray(initVO,
						vBodyVOs.size(), true);
				vBodyVOs.copyInto(bodyVOs);
				return bodyVOs;
			}
		} catch (Throwable ex) {
			Logger.debug(ex);
		}
		return null;
	}

	/*
	 * �õ���VO
	 */

	public CircularlyAccessibleValueObject getBodyValueRowVO(int row,
			String bodyVOName) {
		try {
			CircularlyAccessibleValueObject bodyRowVO = (CircularlyAccessibleValueObject) Class
					.forName(bodyVOName).newInstance();
			// for (int j = 0; j < getBodyItems().length; j++) {
			// BillItem item = getBodyItems()[j];
			// Object aValue = getValueAt(row, j);
			// aValue = item.converType(aValue);
			// bodyRowVO.setAttributeValue(item.getKey(), aValue);
			// }
			getBodyRowVO(row, bodyRowVO);
			// ����״̬
			switch (getRowState(row)) {
			case ADD:
				bodyRowVO.setStatus(nc.vo.pub.VOStatus.NEW);
				break;
			case MODIFICATION:
				bodyRowVO.setStatus(nc.vo.pub.VOStatus.UPDATED);
				break;
			}
			return bodyRowVO;
		} catch (ClassNotFoundException e) {

		} catch (InstantiationException e) {

		} catch (IllegalAccessException e) {

		}
		return null;
	}
	//ͨ��������ʾ��ֵ�õ�һ��ֵ��������������ͣ��õ����ǽ�����ʾ��Code����Name��������PK��
	//Ӧ�����ض��ĳ��������á�
	public CircularlyAccessibleValueObject getBodyValueRowVOByVisualValue(int row,
			String bodyVOName) {
		try {
			CircularlyAccessibleValueObject bodyRowVO = (CircularlyAccessibleValueObject) Class
					.forName(bodyVOName).newInstance();
			
			getBodyRowVO(row, bodyRowVO,false);
			// ����״̬
			switch (getRowState(row)) {
			case ADD:
				bodyRowVO.setStatus(nc.vo.pub.VOStatus.NEW);
				break;
			case MODIFICATION:
				bodyRowVO.setStatus(nc.vo.pub.VOStatus.UPDATED);
				break;
			}
			return bodyRowVO;
		} catch (ClassNotFoundException e) {

		} catch (InstantiationException e) {

		} catch (IllegalAccessException e) {

		}
		return null;
	}

	public void getBodyValueVOs(CircularlyAccessibleValueObject[] bodyVOs) {
		for (int i = 0; i < bodyVOs.length; i++) {
			if (i > getRowCount())
				break;
			getBodyRowVO(i, bodyVOs[i]);
			// for (int j = 0; j < getBodyItems().length; j++) {
			// BillItem item = getBodyItems()[j];
			// Object aValue = getValueAt(i, j);
			// aValue = item.converType(aValue);
			// bodyVOs[i].setAttributeValue(item.getKey(), aValue);
			// }
			// ����״̬
			switch (getRowState(i)) {
			case ADD:
				bodyVOs[i].setStatus(nc.vo.pub.VOStatus.NEW);
				break;
			case MODIFICATION:
				bodyVOs[i].setStatus(nc.vo.pub.VOStatus.UPDATED);
				break;
			}
		}
	}

	/*
	 * �õ�VO����
	 */

	public CircularlyAccessibleValueObject[] getBodyValueVOs(String bodyVOName) {
		try {
			Class<?> bodyVOClass = Class.forName(bodyVOName);
			int length = getRowCount();
			CircularlyAccessibleValueObject[] bodyVOs = (CircularlyAccessibleValueObject[]) Array
					.newInstance(bodyVOClass, length);
			for (int i = 0; i < getRowCount(); i++) {
				// bodyVOs[i] = (CircularlyAccessibleValueObject)
				// Class.forName(bodyVOName).newInstance();
				// BillItem[] items = getBodyItems();
				// BillItem item;
				// Object aValue;
				// for (int j = 0; j < items.length; j++) {
				// item = items[j];
				// aValue = getValueAt(i, j);
				// aValue = item.converType(aValue);
				// bodyVOs[i].setAttributeValue(item.getKey(), aValue);
				// }
				// // ����״̬
				// switch (getRowState(i)) {
				// case ADD:
				// bodyVOs[i].setStatus(nc.vo.pub.VOStatus.NEW);
				// break;
				// case MODIFICATION:
				// bodyVOs[i].setStatus(nc.vo.pub.VOStatus.UPDATED);
				// break;
				// }
				bodyVOs[i] = getBodyValueRowVO(i, bodyVOName);
			}
			return bodyVOs;
		} catch (ClassNotFoundException e) {

		}
		return null;
	}

	// /**
	// * ��������:(2003-6-20 11:11:56)
	// *
	// * @param row int
	// * @param col int
	// * @param color java.awt.Color
	// */
	// @SuppressWarnings("unchecked")
	// HashMap<Cell, Color>[] getCellColor(int[] rows, int[] cols) {
	// if (rows == null || cols == null || rows.length == 0
	// || cols.length == 0)
	// return null;
	//
	// HashMap[] hashColors = { new HashMap<Cell, Color>(), new HashMap<Cell,
	// Color>() };
	// Color color;
	// int row;
	// // hashCellBackGround, hashCellForeGround };
	// for (int i = 0; i < rows.length; i++) {
	// row = rows[i];
	// RowAttribute rowatt = getRowAttribute(row);
	// for (int j = 0; j < cols.length; j++) {
	// if ((color = rowatt.getCellBackColor(cols[j])) != null)
	// hashColors[0].put(new Cell(i, j), color);
	// if ((color = rowatt.getCellForeColor(cols[j])) != null)
	// hashColors[1].put(new Cell(i, j), color);
	// }
	// }
	// if (hashColors[0].size() == 0 && hashColors[1].size() == 0)
	// return null;
	// return hashColors;
	// }

	/**
	 * Returns Object.class by default
	 */
	public Class<?> getColumnClass(int columnIndex) {
		if (getBodyItems() == null)
			return super.getColumnClass(columnIndex);
		switch (getBodyItems()[columnIndex].getDataType()) {
		case BillItem.BOOLEAN:
			return Boolean.class;
		default:
			return String.class;
		}
	}

	/**
	 * �������.
	 */
	public int getColumnCount() {
		if (getBodyItems() == null)
			return 0;
		return getBodyItems().length;
	}

	/**
	 * �õ��кŶ�Ӧ����. ��������:(01-2-21 10:08:48)
	 */
	public String getColumnName(int col) {
		if (getBodyItems() == null)
			return super.getColumnName(col);
		return getBodyItems()[col].getName();
	}

	// /**
	// * ���ɾ��������. ��������:(01-2-21 10:08:48)
	// */
	// public Vector<Vector<?>> getDeleteRow() {
	// return vDeleteRow;
	// }

	/**
	 * ��ñ��༭��. ��������:(2001-9-7 10:57:25)
	 * 
	 * @param newRowEdit
	 *            boolean
	 */
	public int getEditRow() {
		if (m_editRow == null || !dataVector.contains(m_editRow))
			return -1;
		return dataVector.indexOf(m_editRow);
	}

	/**
	 * ��ʽִ����. ��������:(01-2-21 10:08:48)
	 */
	public FormulaParse getFormulaParse() {
		if (m_formulaParse == null) {
			m_formulaParse = createDefaultFormulaParse();
		}
		return m_formulaParse;
	}

	/**
	 * @return Returns the formulaProcessor.
	 */
	BillModelFormulaProcessor getFormulaProcessor() {
		return formulaProcessor;
	}

	/**
	 * billitem.
	 */
	public BillItem getItemByKey(String key) {
		return htBillItems.get(key);
	}

	/**
	 * �������.
	 */
	public int getItemIndex(Object itemOrKey) {
		if (itemOrKey == null)
			throw new NullPointerException("itemOrkey is null.");
		BillItem[] items = getBodyItems();
		if (items == null)
			return -1;
		if (itemOrKey instanceof String) {
			for (int i = 0; i < items.length; i++) {
				if ((itemOrKey).equals(items[i].getKey()))
					return i;
			}
		} else if (itemOrKey instanceof BillItem) {
			BillItem item = (BillItem) itemOrKey;
			for (int i = 0; i < items.length; i++) {
				if (item.getKey().equals(items[i].getKey()))
					return i;
			}
		}
		throw new java.lang.IllegalArgumentException("itemOrKey not found!");
	}

	public int getPasteLineNumer() {
		if (vViewRowCopy == null)
			return 0;
		return vViewRowCopy.size();
	}

	/**
	 * ���༭״̬. ��������:(2001-9-7 10:57:25)
	 * 
	 * @param newRowEdit
	 *            boolean
	 */
	public boolean getRowEditState() {
		return m_bRowEditState;
	}

	/**
	 * �кű�ģʽ. ��������:(01-2-21 10:08:48)
	 */
	public DefaultTableModel getRowNOTableModel() {
		if (m_tmlRowNO == null) {
			m_tmlRowNO = createDefaultRowNumberModel();
		}
		return m_tmlRowNO;
	}

	/**
	 * �����״̬. ��������:(2001-4-16 9:45:23)
	 * 
	 * @param row
	 *            int
	 * @return int
	 */
	public int getRowState(int row) {
		return getRowStateModel(row);
	}

	/**
	 * �����״̬. ��������:(2001-4-16 9:45:23)
	 * 
	 * @param row
	 *            int
	 * @return int
	 */
	protected int getRowStateModel(int row) {
		RowAttribute ra = getRowAttribute(row);

		if (ra == null)
			return UNSTATE;
		else
			return ra.getRowState();

	}

	public RowAttribute getRowAttribute(int row) {

		if (row < 0)
			return null;

		if (dataVector == null)
			return null;
		if (getRowCount() <= row)
			return null;

		return vRowAttrib.get(row);
	}

	public void addRowAttributeObject(int row, String key, Object o) {
		RowAttribute ra = getRowAttribute(row);
		if (ra != null) {
			ra.addAttributeObject(key, o);
		}
	}

	public Object getRowAttributeObject(int row, String key) {
		RowAttribute ra = getRowAttribute(row);
		if (ra != null) {
			return ra.getAttributeObject(key);
		}
		return null;
	}

	public void removeRowAttributeObject(int row, String key) {
		RowAttribute ra = getRowAttribute(row);
		if (ra != null) {
			ra.removeAttributeObject(key);
		}
	}

	/**
	 * ������ѡ����VO����
	 */
	protected Vector getSelectedVector(String bodyVOName) {
		return getSelectedVector(bodyVOName, -1, false)[0];
	}

	/**
	 * ������ѡ����VO����
	 */
	private Vector[] getSelectedVector(String bodyVOName, int number,
			boolean hasRowNumber) {
		Vector<CircularlyAccessibleValueObject> bodyVOs = new Vector<CircularlyAccessibleValueObject>();
		Vector<Integer> rowNumber = hasRowNumber ? new Vector<Integer>() : null;
		try {
			for (int i = 0; i < getRowCount(); i++) {
				if (getRowState(i) == SELECTED) {
					CircularlyAccessibleValueObject bodyVO = (CircularlyAccessibleValueObject) Class
							.forName(bodyVOName).newInstance();
					// for (int j = 0; j < getBodyItems().length; j++) {
					// BillItem item = getBodyItems()[j];
					// Object aValue = getValueAtModel(i, j);
					// aValue = item.converType(aValue);
					// bodyVO.setAttributeValue(item.getKey(), aValue);
					// }

					getBodyRowVO(i, bodyVO);

					bodyVOs.add(bodyVO);
					if (hasRowNumber)
						rowNumber.addElement(Integer.valueOf(i));
					if (number != -1 && bodyVOs.size() == number)
						return new Vector[] { bodyVOs, rowNumber };
				}
			}
		} catch (ClassNotFoundException e) {

		} catch (InstantiationException e) {

		} catch (IllegalAccessException e) {

		}
		return new Vector[] { bodyVOs, rowNumber };
	}

	private void getBodyRowVO(int row, CircularlyAccessibleValueObject bodyVO) {
		getBodyRowVO(row,bodyVO,true);
	}
	
	private void getBodyRowVO(int row, CircularlyAccessibleValueObject bodyVO,boolean isConvertValue) {
		for (int j = 0; j < getBodyItems().length; j++) {
			BillItem item = getBodyItems()[j];
			Object aValue = getValueAt(row, j);
			if (!isConvertValue && aValue instanceof DefaultConstEnum){
				IConstEnum e = (IConstEnum) aValue;
				aValue = e.getName();
				
			}else{
				aValue = item.converType(aValue);
			}
			
			setValue2BodyVO(bodyVO, item, aValue);
		}
	}

	private void setValue2BodyVO(CircularlyAccessibleValueObject bodyVO,
			BillItem item, Object aValue) {
		if (aValue != null && item.getDataType() == IBillItem.MULTILANGTEXT
				&& aValue instanceof MultiLangText) {
			MultiLangText mlt = (MultiLangText) aValue;

			bodyVO.setAttributeValue(item.getKey(), mlt.getText());
			bodyVO.setAttributeValue(item.getKey() + "2", mlt.getText2());
			bodyVO.setAttributeValue(item.getKey() + "3", mlt.getText3());
			bodyVO.setAttributeValue(item.getKey() + "4", mlt.getText4());
			bodyVO.setAttributeValue(item.getKey() + "5", mlt.getText5());
			bodyVO.setAttributeValue(item.getKey() + "6", mlt.getText6());

		} else {
			bodyVO.setAttributeValue(item.getKey(), aValue);
		}
	}

	/**
	 * ���ص�ǰ�����������е��к�
	 * 
	 * @return int
	 * @throws
	 * @see
	 * @since v1.0
	 * @deprecated
	 */
	public int getSortColumn() {
		if (getSortColumns() != null && getSortColumns().size() > 0)
			return getSortColumns().get(0).getColumn();
		return -1;
	}

	/**
	 * ���ص�ǰ�����������е��к�
	 * 
	 * @return int
	 * @exception
	 * @see
	 * @since v1.0
	 */
	public List<SortItem> getSortColumns() {
		return m_sortColumn;
	}

	/**
	 * ���ص�ǰ�����������еĹؼ���
	 * 
	 * @deprecated
	 */
	public String getSortColumnKey() {
		String itemkey = null;
		if (getSortColumnKeys() != null)
			itemkey = getSortColumnKeys().get(0);
		return itemkey;
	}

	/**
	 * ���ص�ǰ�����������е��к�
	 * 
	 * @return int
	 * @exception
	 * @see
	 * @since v1.0
	 */
	public List<String> getSortColumnKeys() {
		List<String> itemkeys = null;

		if (getSortColumns() != null && getSortColumns().size() > 0) {
			itemkeys = new ArrayList<String>();

			for (int i = 0; i < getSortColumns().size(); i++) {

				String itemkey = getBodyItems()[getSortColumns().get(i)
						.getColumn()].getKey();
				itemkeys.add(itemkey);

			}

		}

		return itemkeys;
	}

	/**
	 * ���ص�ǰ�����������е��к�.
	 * 
	 * �˷����Է���,��������Ϊ��ģ������,��ֱ��ʹ�ñ�����
	 * 
	 * @return int
	 * @throws
	 * @see
	 * @since v1.0
	 * @deprecated
	 */
	public int[] getSortIndex() {
		if (dataVector != null) {
			if (m_indexes == null || m_indexes.length != getRowCount()) {
				allocate();
			}
		}
		return m_indexes;
	}

	/**
	 * @return Returns the sortPrepareListener.
	 */
	public IBillModelSortPrepareListener getSortPrepareListener() {
		return sortPrepareListener;
	}

	/**
	 * �ϼ��б�ģʽ. ��������:(01-2-21 10:08:48)
	 */
	public DefaultTableModel getTotalTableModel() {
		if (m_tmlTotal == null) {
			m_tmlTotal = createDefaultTotalTableModel();
		}
		if (m_biBodyItems != null)
			m_tmlTotal.setNumRows(1);
		return m_tmlTotal;
	}

	/**
	 * �õ���Ԫֵ.
	 */
	public Object getValueAt(int row, int column) {
		return getValueAtModel(row, column);
	}

	/**
	 * �õ���Ԫֵ.
	 */
	public Object getValueAt(int rowIndex, String strKey) {

		Object o = getValueObjectAt(rowIndex, strKey);

		if (o instanceof IConstEnum) {
			if (strKey.endsWith(IBillItem.ID_SUFFIX))
				o = ((IConstEnum) o).getValue();
			else
				o = ((IConstEnum) o).getName();
		}

		return o;
	}

	/**
	 * �õ���Ԫֵ.
	 */
	public Object getValueObjectAt(int rowIndex, String strKey) {

		int colIndex = getBodyColByKey(strKey);
		Object o = getValueAt(rowIndex, colIndex);

		return o;
	}

	/**
	 * �õ���Ԫֵ.
	 */
	Object getValueAtModel(int row, int column) {
		Vector<?> rowVector = getRowVectorAtModel(row);
		if (column >= 0 && rowVector != null && rowVector.size() > column) {
			Object aValue = rowVector.elementAt(column);
			String sv;
			if (getBodyItems()[column].getDataType() == BillItem.BOOLEAN)
				if (aValue != null && aValue instanceof UFBoolean) {
					aValue = Boolean.valueOf(((UFBoolean) aValue)
							.booleanValue());
				}
			if (aValue instanceof String) {
				sv = ((String) aValue).trim();
				if (sv.length() == 0) {
					aValue = null;
				}
			}
			return aValue;
		}
		return null;
	}

	/**
	 * �õ���Ԫֵ.
	 */
	Object getValueAtModel(int rowIndex, String strKey) {
		int colIndex = getBodyColByKey(strKey);
		return getValueAtModel(rowIndex, colIndex);
	}

	/**
	 * �����ѱ仯��VO����
	 */
	protected Vector<CircularlyAccessibleValueObject> getValueChangeVector(
			String bodyVOName) {
		Vector<CircularlyAccessibleValueObject> bodyVOs = new Vector<CircularlyAccessibleValueObject>();
		try {
			if (vDeleteRow != null) {
				for (int i = 0; i < vDeleteRow.size(); i++) {
					CircularlyAccessibleValueObject bodyVO = (CircularlyAccessibleValueObject) Class
							.forName(bodyVOName).newInstance();
					Vector<?> rowVector = vDeleteRow.elementAt(i);
					for (int j = 0; j < getBodyItems().length; j++) {
						BillItem item = getBodyItems()[j];
						Object aValue = rowVector.elementAt(j);
						aValue = item.converType(aValue);
						setValue2BodyVO(bodyVO, item, aValue);
						// bodyVO.setAttributeValue(item.getKey(), aValue);
					}
					// ����״̬
					bodyVO.setStatus(nc.vo.pub.VOStatus.DELETED);
					bodyVOs.add(bodyVO);
				}
			}
			for (int i = 0; i < getRowCount(); i++) {
				if (getRowState(i) != NORMAL) {
					CircularlyAccessibleValueObject bodyVO = (CircularlyAccessibleValueObject) Class
							.forName(bodyVOName).newInstance();
					// for (int j = 0; j < getBodyItems().length; j++) {
					// BillItem item = getBodyItems()[j];
					// // Object aValue = getValueAt(i,item.getKey());
					// Object aValue = getValueAt(i, j);
					// aValue = item.converType(aValue);
					// bodyVO.setAttributeValue(item.getKey(), aValue);
					// }
					getBodyRowVO(i, bodyVO);
					// ����״̬
					switch (getRowState(i)) {
					case ADD: {
						bodyVO.setStatus(nc.vo.pub.VOStatus.NEW);
						break;
					}
					case MODIFICATION: {
						bodyVO.setStatus(nc.vo.pub.VOStatus.UPDATED);
						break;
					}
					}
					bodyVOs.add(bodyVO);
				}
			}
		} catch (ClassNotFoundException e) {
			Logger.debug(e);
		} catch (InstantiationException e) {
			Logger.debug(e);
		} catch (IllegalAccessException e) {
			Logger.debug(e);
		}
		return bodyVOs;
	}

	/**
	 * �����ѱ仯��VO����
	 */
	protected Vector<GeneralSuperVO> getValueChangeVector(GeneralSuperVO initVO) {
		Vector<GeneralSuperVO> bodyVOs = new Vector<GeneralSuperVO>();
		if (vDeleteRow != null) {
			for (int i = 0; i < vDeleteRow.size(); i++) {
				GeneralSuperVO bodyVO = GeneralSuperVO.createVOArray(initVO, 1,
						false)[0];
				// Vector rowVector = (Vector) vDeleteRow.elementAt(i);
				// for (int j = 0; j < getBodyItems().length; j++) {
				// BillItem item = getBodyItems()[j];
				// Object aValue = rowVector.elementAt(j);
				// aValue = item.converType(aValue);
				// bodyVO.setAttributeValue(item.getKey(), aValue);
				// }
				getBodyRowVO(i, bodyVO);
				// ����״̬
				bodyVO.setStatus(nc.vo.pub.VOStatus.DELETED);
				bodyVOs.add(bodyVO);
			}
		}
		for (int i = 0; i < getRowCount(); i++) {
			if (getRowState(i) != NORMAL) {
				GeneralSuperVO bodyVO = GeneralSuperVO.createVOArray(initVO, 1,
						false)[0];
				// for (int j = 0; j < getBodyItems().length; j++) {
				// BillItem item = getBodyItems()[j];
				// Object aValue = getValueAt(i, j);
				// aValue = item.converType(aValue);
				// bodyVO.setAttributeValue(item.getKey(), aValue);
				// }
				getBodyRowVO(i, bodyVO);
				// ����״̬
				switch (getRowState(i)) {
				case ADD: {
					bodyVO.setStatus(nc.vo.pub.VOStatus.NEW);
					break;
				}
				case MODIFICATION: {
					bodyVO.setStatus(nc.vo.pub.VOStatus.UPDATED);
					break;
				}
				}
				bodyVOs.add(bodyVO);
			}
		}
		return bodyVOs;
	}

	/**
	 * ������ ��������:(01-2-28 11:16:53)
	 */
	public void insertRow(int row) {
		// int mrow = convertIntoModelRow(row);
		// if (mrow < 0 && (row == getRowCount()))
		// mrow = row;
		// insertRowModel(mrow);
		insertRowModel(row);
	}

	/**
	 * ������ ��������:(01-2-28 11:16:53)
	 */
	private void insertRowModel(int row) {
		if (row < 0)
			return;
		Vector<Object> vNewRow = createRowVector(row);

		if (getCellSpan() != null) {
			getCellSpan().insertLine(row);
		}

		// ������״̬
		vRowAttrib.add(row, createRowAttribute());

		insertRow(row, vNewRow);

		vNewRow = new Vector<Object>();
		vNewRow.add(null);
		getRowNOTableModel().insertRow(row, vNewRow);

		fireTableChanged(new TableModelEvent(this));
	}

	/**
	 * ��Ԫ���Ƿ�ɱ༭
	 */
	public boolean isCellEditable(int row, int col) {
		boolean editable = isCellEditableModel(row, col);

		BillModelCellEditableController editableController = getCellEditableController();
		if (editableController != null) {
			editable = editableController.isCellEditable(editable, row,
					getBodyItems()[col].getKey());
		}
		return editable;
	}

	/**
	 * ��Ԫ���Ƿ�ɱ༭
	 */
	private boolean isCellEditableModel(int row, int col) {
		if (row < 0 || col < 0)
			return false;

		// whole switch
		if (!m_bEnabled)
			return false;

		// cell switch
		// ��Ԫ�༭
		BillItem item = getBodyItems()[col];
		// cell editable
		// if (htCellEdit != null) {
		// String strKey = row + "," + item.getKey().toString();
		// if (htCellEdit.containsKey(strKey)) {
		// return ((Boolean) htCellEdit.get(strKey)).booleanValue();
		// }
		// }
		Boolean cellEdit = getRowAttribute(row).isCellEdit(item.getKey());
		if (cellEdit != null)
			return cellEdit.booleanValue();
		// column switch
		if (!item.isEnabled())
			return false;
		// row switch
		boolean rowedit = true;
		// �б༭״̬
		if (getRowEditState()) {
			if (getEditRow() != -1) {
				if (getEditRow() != row)
					rowedit = false;
			} else {
				rowedit = !isNotEditRowModel(row);
			}
		}
		return rowedit;
	}

	/**
	 * ��Ԫ���Ƿ�ɱ༭
	 */
	public boolean isCellShowWarning(int row, int col) {
		if (row < 0 || col < 0)
			return false;

		// whole switch
		if (!m_bEnabled)
			return false;

		// ��Ԫ�༭
		BillItem item = getBodyItems()[col];

		if (getRowAttribute(row) != null) {
			return getRowAttribute(row).isCellShowWarning(item.getKey());
		}

		return false;
	}

	/**
	 * �������Ƿ�ѡ��. ��������:(2001-11-29 15:35:27)
	 * 
	 * @return boolean
	 */
	public boolean isHasSelectRow() {
		for (int i = 0, j = getRowCount(); i < j; i++) {
			if (getRowStateModel(i) == SELECTED)
				return true;
		}
		return false;
	}

	/**
	 * ��������:(2003-01-24 9:30:49)
	 * 
	 * @return boolean
	 */
	public boolean isNeedCalculate() {
		return m_bNeedCalculate;
	}

	/**
	 * �жϲ��ɱ༭��. ��������:(2001-9-18 15:29:11)
	 * 
	 * @param row
	 *            int
	 * @return boolean
	 */
	protected boolean isNotEditRow(int row) {
		return isNotEditRowModel(row);
	}

	protected boolean isNotEditRowModel(int row) {
		if (row > -1 && row < getRowCount())
			return !getRowAttribute(row).isEdit();
		// if (htNotEditRow == null)
		// return false;
		// Object o = dataVector.get(row);
		// Object key = "" + System.identityHashCode(o);
		// if (htNotEditRow.containsKey(key))
		// return true;
		return false;
	}

	/**
	 * ճ������.
	 */
	public void pasteLine(int row) {
		pasteLineModel(row);
	}

	/**
	 * ճ������.
	 */
	private void pasteLineModel(int row) {
		if (vViewRowCopy == null)
			return;
		if (row < 0)
			return;

		int[] rows = new int[vViewRowCopy.size()];
		for (int i = 0; i < vViewRowCopy.size(); i++) {
			Vector<Object> vRow = transferBodyRowData(vViewRowCopy.elementAt(i));

			// ��״̬
			vRowAttrib.add(row + i, createRowAttribute());
			insertRow(row + i, vRow);
			rows[i] = row + i;
			// �к�
			vRow = new Vector<Object>();
			vRow.add(null);
			getRowNOTableModel().insertRow(row, vRow);

			if (getCellSpan() != null)
				getCellSpan().insertLine(row + i);

		}
		reCalcurateAll();

		// fireTableChanged(new TableModelEvent(this));
	}

	/**
	 * ճ������.
	 */
	void pasteLineToTail() {
		pasteLineModel(getRowCount());
	}

	/**
	 * �����кϼ� ��������:(01-2-28 11:16:53)
	 * 
	 * @param column
	 *            int : model column
	 */
	public void reCalcurate(int column) {
		if (isNeedCalculate()) {
			UFDouble total = null;
			UFDouble aValue = null;
			BillItem item = getBodyItems()[column];
			if (btl == null) {
				for (int i = 0; i < getRowCount(); i++) {
					if (isRowSelectMode()
							&& !(getRowAttribute(i).getRowState() == SELECTED))
						continue;
					Object o = getValueAt(i, column);
					if (o != null && total == null) {
						total = new UFDouble(0.0);
					}
					if (o == null) {
						continue;
					}
					total = total.add(createUFDouble(o));
				}
			} else {
				total = btl.calcurateTotal(item.getKey());
			}
			if (total != null) {
				int digit = 0;
				if (item.getDataType() == BillItem.DECIMAL
						|| item.getDataType() == BillItem.MONEY) {
//					 digit = 0 - item.getDecimalDigits();
					//��Ƭ���б�����������������ϼ����ϵ�����û��ȡ��󾫶�,������ 
					digit = 0 - item.getMaxDecimalDigit();
				}
				aValue = total.setScale(digit, UFDouble.ROUND_HALF_UP);
			}
			if (m_tmlTotal != null)
				m_tmlTotal.setValueAt(aValue, 0, column);
		}
	}

	/**
	 * ���������кϼ� ��������:(01-2-28 11:16:53)
	 * 
	 * @param iCol
	 *            int
	 */
	public void reCalcurateAll() {
		if (!isNeedCalculate() || getBodyItems() == null)
			return;
		for (int column = 0; column < getBodyItems().length; column++) {
			if (getBodyItems()[column].isTotal()
					&& (getBodyItems()[column].getDataType() == BillItem.INTEGER
							|| getBodyItems()[column].getDataType() == BillItem.DECIMAL || getBodyItems()[column]
							.getDataType() == BillItem.MONEY))
				reCalcurate(column);
		}
	}

	// /**
	// * ��������:(2003-6-20 11:11:56)
	// *
	// * @param row int
	// * @param col int
	// * @param color java.awt.Color
	// */
	// void removeCellColors(int row, int type) {
	//
	// RowAttribute rowatt = getRowAttribute(row);
	//
	// int colcount = getColumnCount();
	// for (int i = 0; i < colcount; i++) {
	//
	// switch (type) {
	// case Cell.BACKANDFORECOLOR:
	// rowatt.clearCellBackColor(i);
	// rowatt.clearCellForeColor(i);
	// break;
	// case Cell.BACKCOLOR:
	// rowatt.clearCellBackColor(i);
	// break;
	// case Cell.FORECOLOR:
	// rowatt.clearCellForeColor(i);
	// break;
	// default:
	// break;
	// }
	// }
	// }

	/**
	 * �Ƴ��ϼƼ���. ��������:(2001-10-24 16:30:33)
	 * 
	 * @param btl
	 *            nc.ui.pub.bill.BillTotalListener
	 */
	public void removeTotalListener() {
		this.btl = null;
	}

	/**
	 * �Ƴ��ϼƼ���. ��������:(2001-10-24 16:30:33)
	 * 
	 * @param btl
	 *            nc.ui.pub.bill.BillTotalListener
	 */
	public void removeTotalListener(BillTotalListener btl) {
		this.btl = null;
	}

	// -----------------------------------------------------------------
	/**
	 * �Ƴ����ȼ���:
	 */
	public void removeDecimalListener() {
		BillItem[] items = getBodyItems();
		for (int i = 0; i < items.length; i++) {
			items[i].removeDecimalListener();
		}
	}

	// --------------------------------------------------------------

	/**
	 * �ָ���������.
	 */
	public void resumeValue() {
		dataVector = transferBodyData(vViewBodyCache);
		getRowNOTableModel().setNumRows(getRowCount());
		int size = getRowCount();
		// ��״̬�޸�Ϊ����
		vRowAttrib = new Vector<RowAttribute>(size);
		for (int i = 0; i < size; i++) {
			RowAttribute ra = createRowAttribute();
			ra.setRowState(NORMAL);
			vRowAttrib.add(ra);
		}
		vDeleteRow = null;
		reCalcurateAll();

		if (getSortColumnKeys() != null)
			sortByColumns(getSortColumns());
	}

	/**
	 * ���ô���״̬����. ��������:(2001-5-9 14:03:18)
	 */
	@SuppressWarnings("unchecked")
	public void setBillModelData(Vector vData) {
		if (vData == null)
			return;
		if (vData.size() < 2)
			return;
		// vRowState = (Vector) vData.elementAt(0);
		vRowAttrib = (Vector) vData.elementAt(0);
		dataVector = (Vector) vData.elementAt(1);

		//
		setSortIndex(null);
		getRowNOTableModel().setNumRows(getRowCount());
		newRowsAdded(new TableModelEvent(this, 0, getRowCount() - 1,
				TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT));
	}

	/**
	 * @param billScrollPane
	 *            The billScrollPane to set.
	 */
	void setBillScrollPane(BillScrollPane billScrollPane) {
		this.billScrollPane = billScrollPane;
	}

	/**
	 * ���ñ�������. ��������:(01-2-23 14:22:07)
	 */
	public void setBodyDataVO(CircularlyAccessibleValueObject[] bodyVOs) {

		clearBodyData();
		if (bodyVOs == null || bodyVOs.length == 0)
			return;
		isFirstSetBodyDataVO = true;
		boolean needCalculate = isNeedCalculate();
		setNeedCalculate(false);
		setChangeTable(false);
		// if (bodyVOs[0] instanceof nc.vo.pub.SuperVO
		// && !(bodyVOs[0] instanceof nc.vo.pub.general.GeneralSuperVO)) {
		// SuperVO bodyRowVO;
		// BillItem[] items = getBodyItems();
		// String[] fieldnames = new String[items.length];
		// for (int i = 0; i < fieldnames.length; i++)
		// fieldnames[i] = items[i].getKey();
		// java.lang.reflect.Method[] mts = SuperVOGetterSetter.getGetMethods(
		// bodyVOs[0].getClass(), fieldnames);
		// BillItem item;
		// Object aValue;
		// try {
		// dataVector.ensureCapacity(bodyVOs.length);
		// for (int i = 0; i < bodyVOs.length; i++) {
		// addLine();
		// bodyRowVO = (SuperVO) bodyVOs[i];
		// for (int ii = 0; ii < items.length; ii++) {
		// item = items[ii];
		// if (mts[ii] != null)
		// aValue = bodyRowVO.invokeGetMethod(mts[ii],
		// fieldnames[ii]);
		// else
		// aValue = bodyRowVO
		// .getAttributeValue(fieldnames[ii]);
		// // -------------------------
		// if (item.getDataType() == BillItem.DECIMAL)
		// setItemDecimal(bodyRowVO, i, item);
		// else if(aValue != null && item.getDataType() == IBillItem.UFREF)
		// aValue = new DefaultConstEnum(aValue,(String)aValue);
		// // --------------------------
		// setValueAtModel(aValue, i, ii);
		// }
		// // setBodyRowVOModel(bodyVOs[i], i);
		// }
		// } catch (Exception ex) {

		// }
		// } else {
		for (int i = 0; i < bodyVOs.length; i++) {
			addLine();
			setBodyRowVOModel(bodyVOs[i], i);
		}
		// }
		setNeedCalculate(needCalculate);

		if (getSortColumns() != null)
			sortByColumns(getSortColumns());

		setChangeTable(true);
		fireTableChanged(new TableModelEvent(this));
		isFirstSetBodyDataVO = false;

	}

	/**
	 * ���ñ�������. ��������:(01-2-23 14:22:07)
	 */
	NCObject[] bodys = null;

	public NCObject[] getNCObject() {
		return bodys;
	}

	protected void setBodyObjectByMetaData(NCObject[] bodys) {
		setBodyObjectByMetaData(bodys, true, true);
	}
	
	protected void setBodyObjectByMetaData(NCObject[] bodys, boolean isFireTableRowInsertedEvent,boolean isLoadLoadRelationValues) {
		boolean isFire = this.isFireTableRowInsertedEvent;
		this.isFireTableRowInsertedEvent = isFireTableRowInsertedEvent;
		
		this.bodys = bodys;
		clearBodyData();
		if (bodys == null || bodys.length == 0)
			return;
		isFirstSetBodyDataVO = true;
		boolean needCalculate = isNeedCalculate();
		setNeedCalculate(false);
		setChangeTable(false);

		try {
			dataVector.ensureCapacity(bodys.length);
			for (int i = 0; i < bodys.length; i++) {
				addLine();
				setBodyRowObjectModelByMetaData(bodys[i], i);
			}
			isFirstSetBodyDataVO = false;
			if (isLoadLoadRelationValues){
			loadLoadRelationItemValue();
			}

		} catch (Exception ex) {
			Logger.debug(ex);
		}
		setNeedCalculate(needCalculate);

		if (getSortColumns() != null)
			sortByColumns(getSortColumns());

		setChangeTable(true);
		fireTableChanged(new TableModelEvent(this));
		// isFirstSetBodyDataVO = false;
		
		// �ָ���ֵ
		this.isFireTableRowInsertedEvent = isFire;

	}

	protected void setBodyObjectByMetaData(NCObject[] bodys,
			boolean isFireTableRowInsertedEvent) {
		setBodyObjectByMetaData(bodys, isFireTableRowInsertedEvent, true);
	}

	public void setBodyRowObjectByMetaData(Object o, int row) {

		IBusinessEntity be = getTabvo().getBillMetaDataBusinessEntity();

		NCObject ncobject = DASFacade.newInstanceWithContainedObject(be, o);

		setBodyRowObjectByMetaData(ncobject, row);
	}

	public void setBodyRowObjectByMetaData(Object[] os, int startrow) {

		IBusinessEntity be = getTabvo().getBillMetaDataBusinessEntity();
		NCObject ncobject = null;
		int row = startrow;
		int endRow = startrow + os.length - 1;

		for (Object o : os) {
			ncobject = DASFacade.newInstanceWithContainedObject(be, o);
			setBodyRowObjectModelByMetaData(ncobject, row);
			row++;
		}
		loadLoadRelationItemValue(startrow, endRow);
	}

	protected void setBodyRowObjectByMetaData(NCObject o, int row) {
		setBodyRowObjectModelByMetaData(o, row);
		loadLoadRelationItemValue(row);
	}

	private void setBodyRowObjectModelByMetaData(NCObject o, int row) {
		if (o == null || row < 0)
			return;

		BillItem[] items = getBodyItems();

		Object aValue;

		for (int col = 0; col < items.length; col++) {
			BillItem item = items[col];

			if (item.getMetaDataProperty() != null) {

				// ��ʵ������
				if (item.getIDColName() == null) {

					aValue = o.getAttributeValue(item.getMetaDataProperty()
							.getAttribute());
					if (aValue != null) {
						setBillItemDecimalByType(o, row, item);
					}
					setValueAt(aValue, row, col);

				} else {
					// ����ʵ������
					// 2011-11-16 ����һ����֧��������ʵ���С�����ȵ�����
					// ����ʵ�岻��Ҫ������ setValueAt(aValue, row, col);�����ع�����ᴦ��

					setBillItemDecimalByType(o, row, item);

				}

			}
		}

	}

	private void setBillItemDecimalByType(NCObject o, int row, BillItem item) {
		switch (item.getDataType()) {
		case IBillItem.DECIMAL:
		case IBillItem.MONEY:
			setBillItemDecimalByMetaData(item, row, o);
			break;
		}
	}

	/**
	 * ���ö�Ӧ�е�Item�ľ���
	 */
	private void setBillItemDecimalByMetaData(BillItem item, int row, NCObject o) {
		if (item.getDecimalListener() != null) {
			String source = item.getDecimalListener().getSource();
			Object pkValue = o.getAttributeValue(source);
			if (pkValue != null){
				
				 if ( item.getDecimalListener() instanceof IBillModelDecimalListener3) {
			          item.setDecimalDigits(
			              item.getDecimalListener().getDecimalFromSource( row, pkValue),
			              ((IBillModelDecimalListener3) item.getDecimalListener())
			                  .getRoundingModeFromSource( row, pkValue));
			          }
			     else {
			          item.setDecimalDigits(
			              item.getDecimalListener().getDecimalFromSource( row, pkValue));
			     }

//				item.setDecimalDigits(item.getDecimalListener()
//						.getDecimalFromSource(row, pkValue));
				
				
			}
		}
	}

	/**
	 * ���ñ�������. ��������:(01-2-23 14:22:07)
	 */
	protected void setImportBodyDataVO(CircularlyAccessibleValueObject[] bodyVOs) {
		setImportBodyDataVO(bodyVOs, true, null);
	}

	protected void setImportBodyDataVO(
			CircularlyAccessibleValueObject[] bodyVOs,
			boolean isExecEditFormulas, BillActionListener addaction,
			boolean isPK) {
		setImportBodyDataVOImpl(bodyVOs, isExecEditFormulas, addaction, isPK);
	}

	protected void setImportBodyDataVO(
			CircularlyAccessibleValueObject[] bodyVOs,
			boolean isExecEditFormulas, BillActionListener addaction) {
		setImportBodyDataVOImpl(bodyVOs, isExecEditFormulas, addaction, false);
	}

	/**
	 * ���ñ�������. ��������:(01-2-23 14:22:07)
	 */
	private void setImportBodyDataVOImpl(
			CircularlyAccessibleValueObject[] bodyVOs,
			boolean isExecEditFormulas, BillActionListener addaction,
			boolean isPK) {

		// clearBodyData();
		if (bodyVOs == null || bodyVOs.length == 0)
			return;
		isFirstSetBodyDataVO = true;
		clearBodyData();

		boolean needCalculate = isNeedCalculate();
		setNeedCalculate(false);
		setChangeTable(false);
		for (int i = 0; i < bodyVOs.length; i++) {

			boolean isdo = true;
			if (addaction != null) {
				isdo = addaction.onEditAction(BillScrollPane.ADDLINE);
			}
			if (isdo) {
				setChangeTable(true);
				addLine();
				setChangeTable(false);
			}

			Object aValue;
			BillItem item;
			String key;

			// for (int j = 0; j < items.length; j++) {
			// ����ģ�壬��ֵ˳�򰴽���Item����ʾ˳�������Excel���룬��ʷԭ�򣬻��ǰ���VO������˳�� 2012-11-14
			int length;
			BillItem[] billitems = getBodyItems();
			BillUtil.sortBillItemsByShowOrder(billitems);
			if (isPK) {
				length = billitems.length;
			} else {
				length = bodyVOs[i].getAttributeNames().length;
			}

			for (int j = 0; j < length; j++) {
				// item = this.getItemByKey(bodyVOs[i].getAttributeNames()[j]);

				if (isPK) {
					item = billitems[j];
				} else {
					item = this.getItemByKey(bodyVOs[i].getAttributeNames()[j]);
				}

				if (item == null) {
					continue;
				}
				key = item.getKey();
				aValue = bodyVOs[i].getAttributeValue(key);
				if (aValue == null || aValue.toString().length() == 0) {
					continue;
				}

				// ���ñ༭ǰ�¼��������ܲ��ܱ༭��ֻ�ǵ����¡�
				isCellEditable(item);

				String pk = null;
				if (item.getDataType() == IBillItem.DECIMAL
						|| item.getDataType() == IBillItem.MONEY) {
					this.setBillItemDecimalByVO(item, this.getRowCount() - 1,
							bodyVOs[i]);
				} else if (item.getDataType() == IBillItem.MULTILANGTEXT) {
					if (!(aValue instanceof MultiLangText)) {
						String name = (String) aValue;
						String name2 = (String) bodyVOs[i]
								.getAttributeValue(key + "2");
						String name3 = (String) bodyVOs[i]
								.getAttributeValue(key + "3");

						String name4 = (String) bodyVOs[i]
								.getAttributeValue(key + "4");
						String name5 = (String) bodyVOs[i]
								.getAttributeValue(key + "5");
						String name6 = (String) bodyVOs[i]
								.getAttributeValue(key + "6");

						MultiLangText mlt = null;
						if (name != null || name2 != null || name3 != null) {

							mlt = new MultiLangText();
							mlt.setText(name);
							mlt.setText2(name2);
							mlt.setText3(name3);
							mlt.setText4(name4);
							mlt.setText5(name5);
							mlt.setText6(name6);

						}
						aValue = mlt;
					}
				} else if (item.getDataType() == IBillItem.UFREF) {

					UIRefPane ref = (UIRefPane) item.getComponent();
					if (isPK) {
						ref.setPK(aValue);

					} else {
						AbstractRefModel model = ref.getRefModel();
						boolean isCaseSensitive = false;
						if (model != null) {
							isCaseSensitive = model.isCaseSensitve();
							model.setCaseSensive(true);
						}
						ref.setBlurValue(aValue.toString());
						if (model != null) {
							model.setCaseSensive(isCaseSensitive);
						}

					}

					pk = ref.getRefPK();
					if (pk != null) {
						aValue = new DefaultConstEnum(pk, ref.getRefShowName());
					} else {
						aValue = null;
					}

				}

				if (aValue != null && aValue.toString().length() > 0) {
					BillUtil.checkStringLength(item, aValue);
					// this.setValueAt(aValue, this.getRowCount() - 1,
					// this.getBodyColByKey(key));
					int column = this.getColumnIndex(item);
					if (!item.isShow() || column == -1) {
						setValueAt(aValue, this.getRowCount() - 1,
								getBodyColByKey(item.getKey()));
						setItemRelationValueAt(aValue, this.getRowCount() - 1,
								item);

					} else {
						this.getBillScrollPane().setValueAt(item,
								this.getRowCount() - 1,
								this.getColumnIndex(item), aValue, pk);
					}

				}

			}

		}
		if (isExecEditFormulas && getRowCount() > 0)
			formulaProcessor.execEditFormulas(0, getRowCount() - 1, false);

		// loadLoadRelationItemValue();
		// �༭����ʾ��ʽ��Ҫִ�С�2011-3-3
//		loadEditRelationItemValue();
		//2015-5-14�޸� �����渳ֵ�߼����Ѿ������˼��ر༭����������ظ����ã��ظ�����Ҳ���и���ֵ�����⣨���������˱༭�������item���޷��������ݣ�
		loadLoadRelationItemValue();

		setNeedCalculate(needCalculate);

		// if(getSortColumn() >= 0){
		// sortByColumn(getSortColumn(),m_ascending);
		// }
		if (getSortColumns() != null)
			sortByColumns(getSortColumns());

		setChangeTable(true);
		fireTableChanged(new TableModelEvent(this));
		isFirstSetBodyDataVO = false;

	}

	private boolean isCellEditable(BillItem item) {

		int colIndex = this.getColumnIndex(item);

		if (colIndex < 0) {
			return false;
		}
		// �༭ǰ�¼�
		return this
				.getBillScrollPane()
				.getTable()
				.isBeforeEditEventAllowEdit(this.getRowCount() - 1, colIndex,
						null);
	}

	protected int getColumnIndex(BillItem item) {
		int colIndex = -1;
		try {

			colIndex = this.getBillScrollPane().getTable().getColumnModel()
					.getColumnIndex(item.getName());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return colIndex;
	}

	/**
	 * ���Ԫ��. ��������:(01-2-21 10:08:48)
	 */
	public void setBodyItems(BillItem[] newItems) {
		m_biBodyItems = newItems;
		htBodyItems = new Hashtable<String, Integer>();
		htBillItems.clear();
		setSortColumns(null);

		cellSpan = null;

		// HashMap<String, ArrayList<BillItem>> ritems = new
		// HashMap<String,ArrayList<BillItem>>();

		if (newItems != null) {
			for (int i = 0; i < newItems.length; i++) {
				htBodyItems.put(newItems[i].getKey(), Integer.valueOf(i));
				if (newItems[i].getDataType() == IBillItem.UFREF
						|| newItems[i].getDataType() == IBillItem.COMBO) {
					htBodyItems.put(newItems[i].getKey() + IBillItem.ID_SUFFIX,
							Integer.valueOf(i));
				}

				// if(newItems[i].getIDColName() != null &&
				// !newItems[i].getKey().equals(newItems[i].getIDColName())){
				// ArrayList<BillItem> ritemnames = null;
				// if(!ritems.containsKey(newItems[i].getIDColName())){
				// ritemnames = new ArrayList<BillItem>();
				// }else{
				// ritemnames = ritems.get(newItems[i].getIDColName());
				// }
				//
				// ritemnames.add(newItems[i]);
				// ritems.put(newItems[i].getIDColName(), ritemnames);
				// }

				htBillItems.put(newItems[i].getKey(), newItems[i]);
			}

			for (int i = 0; i < newItems.length; i++) {
				BillItem item = newItems[i];
				if (item != null) {
					if (item.getIDColName() != null
							&& !item.getKey().equals(item.getIDColName())) {
						BillItem iditem = htBillItems.get(item.getIDColName());
						if (iditem != null)
							iditem.addRelationItem(item);
					}
				}
			}
		}
		if (m_tmlTotal != null) {
			setTotalTableModel(null);
			getTotalTableModel();
		}

	}

	/**
	 * ���ñ���������. ��������:(01-2-23 14:22:07)
	 */
	public void setBodyRowVO(CircularlyAccessibleValueObject bodyRowVO, int row) {
		setBodyRowVOModel(bodyRowVO, row);
	}

	/**
	 * ���ñ���������. ��������:(01-2-23 14:22:07)
	 */
	private void setBodyRowVOModel(CircularlyAccessibleValueObject bodyRowVO,
			int row) {
		if (bodyRowVO == null)
			return;
		Object aValue;
		BillItem item;
		String key;
		BillItem[] items = getBodyItems();
		for (int i = 0; i < items.length; i++) {
			item = items[i];
			key = item.getKey();
			aValue = bodyRowVO.getAttributeValue(key);
			// aValue = convertVoValueToComboBoxName(aValue, item);
			// --------------
			switch (item.getDataType()) {
			case IBillItem.DECIMAL:
			case IBillItem.MONEY:
				setBillItemDecimalByVO(item, row, bodyRowVO);
				break;
			case IBillItem.UFREF:
				if (aValue == null || aValue.toString().length() == 0)
					aValue = null;
				// ��֪ȡ��ֵ��Ϊʲô����ע��
				// Object o = getValueAt(row, i);
				// if (o != null && o instanceof IConstEnum)
				if (aValue != null)
					// aValue = new DefaultConstEnum(((IConstEnum) o)
					// .getValue(), aValue.toString());
					aValue = new DefaultConstEnum(aValue, aValue.toString());
				else
					// aValue = new DefaultConstEnum(((IConstEnum)
					// o).getValue(), null); //���aValue = null
					aValue = new DefaultConstEnum(null, null); // ���aValue =
				// null,˵������Ϊ���ˣ�����ҲҪ���
				// 2011-2-28
				// else if (aValue != null)
				// aValue = new DefaultConstEnum(aValue, aValue.toString());
				break;
			case IBillItem.MULTILANGTEXT:
				String name = (String) bodyRowVO.getAttributeValue(key);
				String name2 = (String) bodyRowVO.getAttributeValue(key + "2");
				String name3 = (String) bodyRowVO.getAttributeValue(key + "3");
				String name4 = (String) bodyRowVO.getAttributeValue(key + "4");
				String name5 = (String) bodyRowVO.getAttributeValue(key + "5");
				String name6 = (String) bodyRowVO.getAttributeValue(key + "6");

				if (name != null || name2 != null || name3 != null
						|| name4 != null || name5 != null || name6 != null) {
					MultiLangText mlt = new MultiLangText();

					mlt.setText(name);
					mlt.setText2(name2);
					mlt.setText3(name3);
					mlt.setText4(name4);
					mlt.setText5(name5);
					mlt.setText6(name6);

					aValue = mlt;
				}

				// case IBillItem.COMBO:
				// if(aValue == null || aValue.toString().length() == 0 )
				// aValue = null;
				// else if(item.getComponent() != null &&
				// item.isComboBoxReturnInteger() && !(aValue instanceof
				// Integer))
				// aValue = new Integer(aValue.toString());
				// break;
			default:
				break;
			}

			// -------------
			setValueAt(aValue, row, i);
			setItemRelationValueAt(aValue, row, item);
		}
	}

	// ͬ�����¹�����ID��
	private void setItemRelationValueAt(Object aValue, int row, BillItem item) {

		if (item.getRelationItem() == null)
			return;

		for (int j = 0; j < item.getRelationItem().size(); j++) {
			BillItem ritem = item.getRelationItem().get(j);

			if (ritem.getDataType() == IBillItem.UFREF) {
				int column = getBodyColByKey(item.getRelationItem().get(j)
						.getKey());

				if (column >= 0) {
					Object o = getValueAt(row, column);

					if (aValue != null) {
						if (aValue instanceof IConstEnum)
							aValue = ((IConstEnum) aValue).getValue();

						if (aValue != null && aValue.toString().length() > 0) {
							if (o != null && o instanceof IConstEnum)
								aValue = new DefaultConstEnum(aValue,
										((IConstEnum) o).getName());
							else
								aValue = new DefaultConstEnum(aValue,
										(String) aValue);
						} else {
							aValue = null;
						}
					} else
						aValue = null;

					setValueAt(aValue, row, column);
				}
			}
			if(aValue==null){
				int column = getBodyColByKey(item.getRelationItem().get(j).getKey());
				setValueAt(aValue, row, column);
			}
		}
	}

	/**
	 * ���ñ�������. ��������:(01-2-23 14:22:07)
	 */
	public void setBodyRowVOs(CircularlyAccessibleValueObject[] bodyVOs,
			int[] rows) {
		if (bodyVOs == null || bodyVOs.length == 0 || rows == null
				|| bodyVOs.length != rows.length)
			return;
		// rows = convertIntoModelRow(rows);
		int rowcount = getRowCount();
		boolean needCalculate = isNeedCalculate();
		setNeedCalculate(false);
		setChangeTable(false);
		// if (bodyVOs[0] instanceof nc.vo.pub.SuperVO
		// && !(bodyVOs[0] instanceof nc.vo.pub.general.GeneralSuperVO)) {
		// SuperVO bodyRowVO;
		// BillItem[] items = getBodyItems();
		// String[] fieldnames = new String[items.length];
		// for (int i = 0; i < fieldnames.length; i++)
		// fieldnames[i] = items[i].getKey();
		// java.lang.reflect.Method[] mts = SuperVOGetterSetter.getGetMethods(
		// bodyVOs[0].getClass(), fieldnames);
		// // BillItem item;
		// Object aValue;
		// try {
		// for (int i = 0; i < bodyVOs.length; i++) {
		// if (rows[i] < 0 || rows[i] >= rowcount)
		// continue;
		// bodyRowVO = (SuperVO) bodyVOs[i];
		// for (int ii = 0; ii < items.length; ii++) {
		// // item = items[ii];
		// if (mts[ii] != null)
		// aValue = bodyRowVO.invokeGetMethod(mts[ii],
		// fieldnames[ii]);
		// else
		// aValue = bodyRowVO
		// .getAttributeValue(fieldnames[ii]);
		// // aValue = convertVoValueToComboBoxName(aValue, item);
		// if(aValue != null && items[ii].getDataType() == IBillItem.UFREF)
		// aValue = new DefaultConstEnum(aValue,(String)aValue);
		//
		// setValueAtModel(aValue, rows[i], ii);
		// }
		// // setBodyRowVOModel(bodyVOs[i], rows[i]);
		// }
		// } catch (Exception ex) {

		// }
		// } else {
		for (int i = 0; i < bodyVOs.length; i++) {
			if (rows[i] < 0 || rows[i] >= rowcount)
				continue;
			setBodyRowVOModel(bodyVOs[i], rows[i]);
		}
		// }
		setNeedCalculate(needCalculate);
		setChangeTable(true);
		fireTableChanged(new TableModelEvent(this));
	}

	/**
	 * lkp add �ڴ����Զ������BillItem���е���ʱ������ǲ��ջ�Combox���ͣ�����Ҫ����_ID�е��кš� ͨ���˷������á�
	 * 
	 * @param key
	 * @param index
	 */
	public void addBodyItemIndex(String key, int index) {
		htBodyItems.put(key, index);
	}

	/**
	 * ��������:(2003-6-20 11:11:56)
	 * 
	 * @param row
	 *            int
	 * @param col
	 *            int
	 * @param color
	 *            java.awt.Color
	 */
	void setCellColor(int row, String strKey, java.awt.Color color, int type) {

		RowAttribute rowatt = getRowAttribute(row);

		switch (type) {
		case ColoredCell.BACKANDFORECOLOR:
			rowatt.setCellBackColor(strKey, color);
			rowatt.setCellForeColor(strKey, color);
			break;
		case ColoredCell.FORECOLOR:
			rowatt.setCellForeColor(strKey, color);
			break;
		case ColoredCell.BACKCOLOR:
			rowatt.setCellBackColor(strKey, color);
			break;

		default:
			break;
		}
	}

	/**
	 * ���õ�Ԫ���Ƿ�ɱ༭
	 */
	public void setCellEditable(int row, String key, boolean edit) {
		getRowAttribute(row).addCellEdit(key, edit);
	}

	/**
	 * ���õ�Ԫ���Ƿ���ʾ����
	 */
	public void cellShowWarning(int row, String key) {
		getRowAttribute(row).setCellShowWarning(key);

		int column = getBodyColByKey(key);

		fireTableChanged(new TableModelEvent(this, row, row, column));
	}

	public void clearCellShowWarning(int row, String key) {
		 if(null!=getRowAttribute(row)){
			   getRowAttribute(row).clearCellShowWarning(key);
			  }

//		getRowAttribute(row).clearCellShowWarning(key);
	}

	public void setCellHigh(int row, String strKey, int height) {
		getRowAttribute(row).setCellHeight(strKey, height);
	}

	public int getMaxRowHigh(int row) {
		return getRowAttribute(row).getMaxRowHigh();
	}

	/**
	 * ��ʼ�����Ԫ��. ��������:(01-2-21 10:08:48)
	 */
	@SuppressWarnings("unchecked")
	public void setDataVector(Vector newData) {
		if (newData == null)
			throw new IllegalArgumentException(
					"setDataVector() - Null parameter");

		// �������
		dataVector = new Vector(0);

		// �������
		dataVector = newData;

		// clear sortindex
		setSortIndex(null);

		int size = newData.size();
		// �к�
		getRowNOTableModel().setNumRows(size);

		vRowAttrib = new Vector<RowAttribute>(size);
		for (int i = 0; i < size; i++) {
			RowAttribute ra = createRowAttribute();
			ra.setRowState(NORMAL);
			vRowAttrib.add(ra);
		}

		newRowsAdded(new TableModelEvent(this, 0, getRowCount() - 1,
				TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT));
	}

	/**
	 * ���ñ��༭��. ��������:(2001-9-7 10:57:25)
	 * 
	 * @param newRowEdit
	 *            boolean
	 */
	public void setEditRow(int newRow) {
		// if (newRow < 0 || newRow > getRowCount() - 1) {
		// m_editRow = null;
		// } else {
		// m_editRow = (Vector) dataVector.elementAt(newRow);
		// }
		m_editRow = getRowVectorAtModel(newRow);
	}

	/**
	 * ��������:(2002-07-02 16:05:36)
	 * 
	 * @param isEnabled
	 *            boolean
	 */
	public void setEnabled(boolean isEnabled) {
		m_bEnabled = isEnabled;
		if (isEnabled && getBillScrollPane() != null) {
			getBillScrollPane().unlockTableCol();
			// ����������̬�ͱ༭̬
			getBillScrollPane().getTable().updateUI();
		}

		if (getBodyItems() != null) {
			for (int i = 0; i < getBodyItems().length; i++) {
				initBillItemEnabled(getBodyItems()[i], isEnabled);
			}
		}

	}

	/**
	 * ��Hashtable�е�ÿһ��BillItem���ÿɱ༭����.
	 */
	private void initBillItemEnabled(BillItem item, boolean newEnabled) {
		if (newEnabled) {
			item.setEnabled(item.isEdit());
		} else {
			item.setEnabled(newEnabled);
		}
	}

	/**
	 * ��������:(2002-07-02 16:05:36)
	 * 
	 * @param isEnabled
	 *            boolean
	 */
	public boolean isEnabled() {
		return m_bEnabled;
	}

	/**
	 * ��������:(2002-07-02 16:05:36)
	 * 
	 * @param isEnabled
	 *            boolean
	 */
	public void setEnabledAllItems(boolean isEnabled) {
		BillItem[] items = getBodyItems();
		if (items != null) {
			for (int i = 0; i < items.length; i++)
				items[i].setEnabled(isEnabled);
		}
	}

	void setEnabledByEditFlag(boolean isEnabled) {
		BillItem[] items = getBodyItems();
		if (items != null) {
			if (isEnabled) {
				for (int i = 0; i < items.length; i++)
					items[i].setEnabled(items[i].isEdit());
			} else
				for (int i = 0; i < items.length; i++)
					items[i].setEnabled(isEnabled);
		}
	}

	/**
	 * ��������:(2001-6-20 19:44:39)
	 * 
	 * @param col
	 *            int
	 */
	public void setFixCol(int col) {
		fixCol = col;
	}

	/**
	 * ��ʽִ����. ��������:(01-2-21 10:08:48)
	 */
	public void setFormulaParse(FormulaParse newFormulaParse) {
		m_formulaParse = newFormulaParse;
	}

	/**
	 * ��������:(2003-01-24 9:29:30)
	 * 
	 * @param doCalculate
	 *            boolean
	 */
	public void setNeedCalculate(boolean doCalculate) {
		if (m_bNeedCalculate != doCalculate) {
			m_bNeedCalculate = doCalculate;
			if (m_bNeedCalculate)
				reCalcurateAll();
		}
	}

	/**
	 * ��������:(2003-4-9 10:58:47)
	 * 
	 * @param rows
	 *            int[]
	 */
	public void setNotEditAllowedRows(int[] rows) {
		// if (htNotEditRow == null) {
		// htNotEditRow = new Hashtable();
		// } else
		// htNotEditRow.clear();
		if (rows == null || rows.length == 0)
			return;
		// if (aryNotEditRow == null)
		// aryNotEditRow = new ArrayList<RowAttribute>();
		// else {
		// for (int i = 0; i < aryNotEditRow.size(); i++) {
		// aryNotEditRow.get(i).setEdit(true);
		// }
		// aryNotEditRow.clear();
		// }
		for (int i = 0; i < rows.length; i++) {
			if (rows[i] < getRowCount()) {
				getRowAttribute(rows[i]).setEdit(false);
				// aryNotEditRow.add(getRowAttribute(rows[i]));
				// Object o = dataVector.elementAt(rows[i]);
				// if (o != null) {
				// Object key = "" + System.identityHashCode(o);
				// htNotEditRow.put(key, "");
				// }
			}
		}
	}

	/**
	 * ���ñ��Ϊ�б༭״̬. ��������:(2001-9-7 10:57:25)
	 * 
	 * @param newRowEdit
	 *            boolean
	 */
	public void setRowEditState(boolean newRowEditState) {
		m_bRowEditState = newRowEditState;
	}

	/**
	 * ���ñ������. ��������:(2001-9-7 10:57:25)
	 * 
	 * @deprecated
	 * @param newRowEdit
	 *            boolean
	 */
	public void setRowSort(boolean newRowSort) {
	}

	/**
	 * ������״̬. ��������:(2001-4-16 9:45:23)
	 * 
	 * @param row
	 *            int : view row
	 * @return int
	 */
	public void setRowState(int row, int state) {
		if (row == -1)
			return;

		if (isRowSelectMode() && !(state == SELECTED || state == UNSTATE))
			throw new RuntimeException(selectModelTxt);

		RowAttribute ra = getRowAttribute(row);

		int oldstats = ra.getRowState();

		if (oldstats != state) {
			ra.setRowState(state);

			if (rowStateChangeListener != null) {
				RowStateChangeEvent event = new RowStateChangeEvent(this, row,
						oldstats, state);
				rowStateChangeListener.valueChanged(event);
			}

			if (isRowSelectMode())
				reCalcurateAll();
		}

	}

	/**
	 * ������״̬. ��������:(2001-4-16 9:45:23)
	 * 
	 * @param row
	 *            int : view row
	 * @return int
	 */
	public void setRowState(int startrow, int endrow, int state) {
		if (startrow == -1 || endrow == -1)
			return;

		if (endrow >= getRowCount())
			return;

		if (isRowSelectMode() && !(state == SELECTED || state == UNSTATE))
			throw new RuntimeException(selectModelTxt);

		if (startrow > endrow) {
			int temp = startrow;
			startrow = endrow;
			endrow = temp;
		}

		RowAttribute ra = getRowAttribute(startrow);
		int oldstats = ra.getRowState();

		// if (oldstats != state) {
		for (int i = startrow; i <= endrow; i++) {
			ra = getRowAttribute(i);
			ra.setRowState(state);
		}
		// }
		if (rowStateChangeListener != null) {
			RowStateChangeEvent event = new RowStateChangeEvent(this, startrow,
					endrow, oldstats, state);
			rowStateChangeListener.valueChanged(event);
		}
		if (isRowSelectMode())
			reCalcurateAll();
	}

	/**
	 * ��ס���������,�Ա�ɾ����ѡ����к󰴴�����������
	 * 
	 * @param newSortColumn
	 *            int
	 * @throws
	 * @see
	 * @since v1.0
	 * @deprecated
	 */
	protected void setSortColumn(int newSortColumn) {
		if (newSortColumn > -1) {
			SortItem si = new SortItem(newSortColumn);

			List<SortItem> lsi = new ArrayList<SortItem>();

			lsi.add(si);

			setSortColumns(lsi);
		} else {
			setSortColumns(null);
		}
	}

	/**
	 * ��ס���������,�Ա�ɾ����ѡ����к󰴴�����������
	 * 
	 * @param newSortColumn
	 *            int
	 * @throws
	 * @see
	 * @since v1.0
	 */
	protected void setSortColumns(List<SortItem> newSortColumns) {
		m_sortColumn = newSortColumns;
	}

	/**
	 * ��ס���������,�Ա�ɾ����ѡ����к󰴴�����������
	 * 
	 * @param newSortColumn
	 *            int
	 * @throws
	 * @see
	 * @since v1.0
	 */
	public void setSortColumn(String key) {
		setSortColumn(getBodyColByKey(key));
	}

	/**
	 * ��ס���������,�Ա�ɾ����ѡ����к󰴴�����������
	 * 
	 * @param newSortColumn
	 *            int
	 * @throws
	 * @see
	 * @since v1.0
	 */
	public void setSortColumn(String[] keys) {
		setSortColumns(null);
		if (keys != null) {
			List<SortItem> lsi = null;
			for (int i = 0; i < keys.length; i++) {
				int col = getBodyColByKey(keys[i]);

				SortItem si = new SortItem(col);

				if (lsi == null)
					lsi = new ArrayList<SortItem>();

				lsi.add(si);
			}
			setSortColumns(lsi);
		}
	}

	/**
	 * ���õ�ǰ�����������е��к���������
	 * 
	 * @return int
	 * @throws
	 * @see
	 * @since v1.0
	 */
	public void setSortIndex(int[] indexes) {
		m_indexes = indexes;
	}

	/**
	 * @param sortPrepareListener
	 *            The sortPrepareListener to set.
	 */
	public void setSortPrepareListener(
			IBillModelSortPrepareListener sortPrepareListener) {
		this.sortPrepareListener = sortPrepareListener;
	}

	/**
	 * �ϼ��б�ģʽ. ��������:(01-2-21 10:08:48)
	 */
	public void setTotalTableModel(DefaultTableModel newTotal) {
		m_tmlTotal = newTotal;
	}

	/**
	 * ���õ�Ԫֵ.
	 */
	public void setValueAt(Object aValue, int row, int column) {
		setValueAtModel(aValue, row, column);
	}
	
	/**
	 * ���õ�Ԫֵ(���о���ֵ)
	 * @param aValue
	 * @param row
	 * @param column
	 * @param digits
	 */
	public void setValueAt(Object aValue, int row, int column,int digits) {
		setValueAtModel(aValue, row, column,digits);
	}

	public void setValueAt(Object aValue, int row, String strKey) {

		int col = getBodyColByKey(strKey);

		if (col >= 0) {

			BillItem item = getBodyItems()[col];
			Object o = getValueAt(row, col);
			if (isImporting() && item.getBillItemSetValueListener() != null
					&& isRemainingOldValue(aValue, o, item)) {
				return;
			}

			if (aValue != null && item.getDataType() == IBillItem.UFREF
					&& !(aValue instanceof IConstEnum)) {

				if (isNull(o)) {

					if (strKey.endsWith(IBillItem.ID_SUFFIX)) {
						aValue = new DefaultConstEnum(aValue, (String) aValue);
					} else {
						aValue = new DefaultConstEnum(aValue, aValue.toString());
					}

				} else {
					if (strKey.endsWith(IBillItem.ID_SUFFIX)) {

						aValue = new DefaultConstEnum(aValue,
								((IConstEnum) o).getName());

					} else {
						aValue = new DefaultConstEnum(
								((IConstEnum) o).getValue(), aValue.toString());
					}
				}
			}

			setValueAt(aValue, row, col);

			setItemRelationValueAt(aValue, row, item);
		}
	}
	/**
	 * ������ֵ������ֵ
	 * @param aValue
	 * @param row
	 * @param strKey
	 * @param digits
	 */
	public void setValueAt(Object aValue, int row, String strKey,int digits) {

		int col = getBodyColByKey(strKey);

		if (col >= 0) {

			BillItem item = getBodyItems()[col];
			Object o = getValueAt(row, col);
			if (isImporting() && item.getBillItemSetValueListener() != null
					&& isRemainingOldValue(aValue, o, item)) {
				return;
			}

			if (aValue != null && item.getDataType() == IBillItem.UFREF
					&& !(aValue instanceof IConstEnum)) {

				if (isNull(o)) {

					if (strKey.endsWith(IBillItem.ID_SUFFIX)) {
						aValue = new DefaultConstEnum(aValue, (String) aValue);
					} else {
						aValue = new DefaultConstEnum(aValue, aValue.toString());
					}

				} else {
					if (strKey.endsWith(IBillItem.ID_SUFFIX)) {

						aValue = new DefaultConstEnum(aValue,
								((IConstEnum) o).getName());

					} else {
						aValue = new DefaultConstEnum(
								((IConstEnum) o).getValue(), aValue.toString());
					}
				}
			}

			setValueAt(aValue, row, col,digits);

			setItemRelationValueAt(aValue, row, item);
		}
	}

	private boolean isRemainingOldValue(Object newValue, Object oldValue,
			BillItem item) {
		return !item.getBillItemSetValueListener().isReplaceByNewValue(this,
				item, newValue, oldValue);

	}

	private boolean isNull(Object obj) {
		if (obj == null) {
			return true;
		}
		// ���һ�����������ǿյ�DefaultConstEnumҲ��Ϊ�ǿյģ�����ͳһ��
		if (obj instanceof IConstEnum) {
			IConstEnum o = (IConstEnum) obj;
			if (o.getValue() == null && o.getName() == null) {
				return true;
			}
		}
		return false;
	}

	/**
	 * ���õ�Ԫֵ.
	 */
	@SuppressWarnings("unchecked")
	void setValueAtModel(Object aValue, int row, int column) {
		if (row < 0 || column < 0)
			return;
		BillItem item = getBodyItems()[column];
		// Vector rowVector = (Vector) dataVector.elementAt(row);
		Vector rowVector = getRowVectorAtModel(row);
		//ʲô�������Ϊ��
		if (rowVector == null){
			return;
		}
		int datatype = item.getDataType();
		// String strValue;

		// �Ƚ���Ϊ�յ��ж�
		if (aValue != null) {

			// �޸�С������
			if (!isFirstSetBodyDataVO
					&& !isIgnoreScaleWhenSetValue()
					&& (item.getDataType() == IBillItem.DECIMAL || item
							.getDataType() == IBillItem.MONEY)) {
				// -----Ϊ���þ������ӵĴ��룬����С�����������¼���------------------
				setBillItemDecimalByRow(item, row);
				// aValue = BillUtil.getFormatUFDouble((UFDouble)aValue,
				// digits);
			}

			aValue = item.getConverter().convertToBillItem(datatype, aValue);

			// switch (datatype) {
			// case IBillItem.INTEGER:
			// // С��ʱ��ʽ��
			// if (!(aValue instanceof Integer)) {
			// strValue = aValue.toString().trim();
			// if (strValue.length() > 0) {
			// int pos = strValue.indexOf('.');
			// if (pos >= 0)
			// strValue = strValue.substring(0, pos);
			// if (strValue.length() == 0)
			// aValue = null;
			// else
			// aValue = new Integer(strValue);
			// } else
			// aValue = null;
			// }
			// break;
			// case IBillItem.DECIMAL:
			//
			// if (aValue.getClass() != UFDouble.class) {
			// strValue = aValue.toString().trim();
			// aValue = (strValue.length() > 0) ? new UFDouble(strValue): null;
			// }
			// if (aValue != null) {
			// if (!isIgnoreScaleWhenSetValue()){
			// int digits = item.getDecimalDigits();
			// //-----Ϊ���þ������ӵĴ��룬����С�����������¼���------------------
			// if (!isFirstSetBodyDataVO && item.getDecimalListener() != null){
			// int colIndex =
			// getBodyColByKey(item.getDecimalListener().getSource());
			// Object id = getValueAt(row, colIndex);
			// if(id != null && id instanceof IConstEnum)
			// id = ((IConstEnum)id).getValue();
			// //item.setDecimalDigits(item.getDecimalListener().getDecimalFromSource(row,
			// id));
			// digits = item.getDecimalListener().getDecimalFromSource(row, id);
			// }
			// aValue = ((UFDouble) aValue).setScale(0 - digits,
			// UFDouble.ROUND_HALF_UP);
			// }
			// }
			// break;
			// case IBillItem.BOOLEAN:
			// if (aValue instanceof String) {
			// aValue = UFBoolean.valueOf(aValue.toString());
			// }
			// break;
			// case IBillItem.COMBO:
			// if(!(aValue instanceof IConstEnum)){
			// // �߼�ʱ��ʽ��
			// UIComboBox combo = ((UIComboBox) item.getComponent());
			//
			// int index = combo.getItemIndexByObject(aValue);
			// if (index < 0 && item.isWithIndex()) {
			// if ("".equals(aValue))
			// index = 0;
			// else
			// index = Integer.parseInt(aValue.toString());
			// }
			//
			// if (index >= 0)
			// aValue = combo.getItemAt(index);
			// }
			// break;
			// default:
			// if(item.getRelationItem() != null && item.getMetaDataProperty()
			// == null){
			// setItemRelationValueAt(aValue, row, item);
			// }
			// break;
			// }
		}

		rowVector.setElementAt(aValue, column);

		// ����ϼ�
		if (isNeedCalculate()) {
			if (item.isTotal()
					&& (datatype == BillItem.INTEGER
							|| datatype == BillItem.DECIMAL || item
							.getDataType() == IBillItem.MONEY))
				reCalcurate(column);
		}
		if (isChangeTable())
			fireTableChanged(new TableModelEvent(this, row, row, column));
	}
	
	
	/**
	 * ���õ�Ԫֵ(������ֵ)
	 * @param aValue
	 * @param row
	 * @param column
	 * @param digits
	 */
	@SuppressWarnings("unchecked")
	void setValueAtModel(Object aValue, int row, int column,int digits) {
		if (row < 0 || column < 0)
			return;
		BillItem item = getBodyItems()[column];
		// Vector rowVector = (Vector) dataVector.elementAt(row);
		Vector rowVector = getRowVectorAtModel(row);
		//ʲô�������Ϊ��
		if (rowVector == null){
			return;
		}
		int datatype = item.getDataType();
		// String strValue;

		// �Ƚ���Ϊ�յ��ж�
		if (aValue != null) {

			// �޸�С������
			if (!isFirstSetBodyDataVO
					&& !isIgnoreScaleWhenSetValue()
					&& (item.getDataType() == IBillItem.DECIMAL || item
							.getDataType() == IBillItem.MONEY)) {
				// -----Ϊ���þ������ӵĴ��룬����С�����������¼���------------------
				setBillItemDecimalByRow(item, row,digits);
				// aValue = BillUtil.getFormatUFDouble((UFDouble)aValue,
				// digits);
			}

			aValue = item.getConverter().convertToBillItem(datatype, aValue);
		}

		rowVector.setElementAt(aValue, column);

		// ����ϼ�
		if (isNeedCalculate()) {
			if (item.isTotal()
					&& (datatype == BillItem.INTEGER
							|| datatype == BillItem.DECIMAL || item
							.getDataType() == IBillItem.MONEY))
				reCalcurate(column);
		}
		if (isChangeTable())
			fireTableChanged(new TableModelEvent(this, row, row, column));
	}
	

	void setValueAtModel(Object aValue, int row, String strKey) {
		int col = getBodyColByKey(strKey);
		setValueAtModel(aValue, row, col);
	}

	/**
	 * �����ƶ���������
	 * 
	 * @createDate:(2000-12-26 20:16:55)
	 * @see:
	 * @since:v5.0
	 * @param column
	 *            int : ��ģ�͵�˳�򣬲��Ǳ���ʾ��˳��
	 * @param ascending
	 *            boolean �����ǽ���
	 * @param noldrow
	 *            int[] : ��ά����������
	 * @deprecated
	 */
	public void sortByColumn(int column, boolean ascending, int[] noldrow) {
		SortItem sortitem = new SortItem(column, ascending);

		List<SortItem> lsi = new ArrayList<SortItem>();

		lsi.add(sortitem);

		sortByColumns(lsi, noldrow);

	}

	/**
	 * �����ƶ���������
	 * 
	 * @createDate:(2000-12-26 20:16:55)
	 * @see:
	 * @since:v5.0
	 * @param items
	 *            SortItem : �����ж���
	 */
	public void sortByColumns(List<SortItem> items) {
		sortByColumns(items, new int[0]);
	}

	/**
	 * �����ƶ���������
	 * 
	 * @createDate:(2000-12-26 20:16:55)
	 * @see:
	 * @since:v5.0
	 * @param items
	 *            SortItem : �����ж���
	 * @param noldrow
	 *            int[] : ��ά����������
	 */
	public void sortByColumns(List<SortItem> items, int[] noldrow) {

		setSortColumns(items);

		if (items == null)
			return;

		// ��ȡ�Ƚ�����
		Comparator<?> cp = new MutiColumnComparator(items);

		SortUtils.sort(dataVector, getSortObjectList(), getSortObjectArray(),
				noldrow, cp);

		getRowNOTableModel().fireTableDataChanged();

		fireTableDataChanged();

		// ����������¼�
		if (bsl != null) {
			String itemkey = getSortColumnKey();
			bsl.afterSort(itemkey);
		}

		EventListener[] ls = afterSortListener
				.getListeners(BillSortListener2.class);

		int currentrow = -1;
		if (getBillScrollPane() != null && noldrow != null
				&& noldrow.length > 0)
			currentrow = noldrow[0];

		for (int i = 0; i < ls.length; i++) {
			((BillSortListener2) ls[i]).currentRowChange(currentrow);
		}
		// reset
		// this.sorttype = DefaultSortType;

	}

	private Comparator<?> getItemComparator(SortItem sortitem) {
		Comparator<?> cp = null;

		BillItem item = getBodyItems()[sortitem.getColumn()];

		if (item.getItemComparator() == null) {
			int sorttype = DefaultSortType;
			IBillModelSortPrepareListener sl = getSortPrepareListener();
			String itemkey = item.getKey();
			if (sl != null) {
				sorttype = sl.getSortTypeByBillItemKey(itemkey);
			}
			if (sorttype == DefaultSortType) {

				switch (getItemByKey(itemkey).getDataType()) {
				case BillItem.BOOLEAN:
					sorttype = BillItem.BOOLEAN;
					break;
				case BillItem.MULTILANGTEXT:
				case BillItem.STRING:
					sorttype = BillItem.STRING;
					break;
				default:
					sorttype = getItemByKey(itemkey).getDataType();
					break;
				}
			}
			cp = new ColumnComparator(sorttype, sortitem);
		} else {
			cp = new ColumnComparator(item.getItemComparator(), sortitem);
		}

		return cp;

	}

	/**
	 * ������������
	 * 
	 * @createDate:(2000-12-26 20:16:55)
	 * @see:
	 * @since:v1.0
	 * @param column
	 *            int : ��ģ�͵�˳�򣬲��Ǳ���ʾ��˳��
	 * @param ascending
	 *            boolean �����ǽ���
	 */
	public void sortByColumn(int column, boolean ascending) {
		sortByColumn(column, ascending, new int[0]);
	}

	/**
	 * ������������
	 * 
	 * @createDate:(2000-12-26 20:16:55)
	 * @see:
	 * @since:v1.0
	 * @param column
	 *            int �е�����
	 * @param ascending
	 *            boolean �����ǽ���
	 */
	public void sortByColumn(String key, boolean ascending) {
		sortByColumn(getBodyColByKey(key), ascending);
	}

	/**
	 * ���ƶ�ά����
	 */
	private Vector<Vector<?>> transferBodyData(Vector<Vector<?>> vValue) {
		if (vValue == null) {
			return new Vector<Vector<?>>();
		}
		int size = vValue.size();
		Vector<Vector<?>> vData = new Vector<Vector<?>>(size);
		Vector<?> vRow;
		Vector<?> vNewRow;
		for (int i = 0; i < size; i++) {
			vRow = vValue.elementAt(i);
			vNewRow = transferBodyRowData(vRow);
			vData.addElement(vNewRow);
		}
		return vData;
	}

	/**
	 * ����һά����
	 */
	@SuppressWarnings("unchecked")
	private Vector<Object> transferBodyRowData(Vector v) {
		return v == null ? new Vector<Object>() : new Vector<Object>(v);
	}

	/**
	 * ���±�������.
	 */
	@SuppressWarnings("unchecked")
	public void updateValue() {
		vViewBodyCache = transferBodyData(dataVector);
		// htCellEditCopy = htCellEdit;
		int size = getRowCount();
		// ��״̬�޸�Ϊ����
		// vRowState = new Vector(size);
		// String normalState = NORMAL + "";
		for (int i = 0; i < size; i++) {
			RowAttribute ra = getRowAttribute(i);
			ra.setRowState(NORMAL);
			ra.updataCellEdit();
		}
		vDeleteRow = null;
	}

	/**
	 * �õ���Ԫֵ.
	 */
	private Vector<?> getRowVectorAtModel(int row) {
		if (row < 0)
			return null;
		if (dataVector == null || getRowCount() <= row)
			return null;
		Vector<?> rowVector = (Vector<?>) dataVector.elementAt(row);
		return rowVector;
	}

	/**
	 * @return Returns the changeTable.
	 */
	private boolean isChangeTable() {
		return changeTable;
	}

	/**
	 * @param changeTable
	 *            The changeTable to set.
	 */
	private void setChangeTable(boolean changeTable) {
		this.changeTable = changeTable;
		// if(changeTable)
		// fireTableChanged(new TableModelEvent(this));
	}

	/**
	 * @return Returns the m_userFixRowModel.
	 */
	public UserFixRowTableModel getUserFixRowModel() {
		if (m_userFixRowModel == null) {
			m_userFixRowModel = new UserFixRowTableModel(this);
		}
		return m_userFixRowModel;
	}

	void setUserFixRowModel(UserFixRowTableModel m) {
		this.m_userFixRowModel = m;
		if (m != null)
			m.setParentModel(this);
	}

	FixRowNOModel getFixRowHeaderModel() {
		if (fixRowHeaderModel == null)
			fixRowHeaderModel = new FixRowNOModel();
		return fixRowHeaderModel;
	}

	UserRowNOModel getUserRowHeaderModel() {
		if (userRowHeaderModel == null)
			userRowHeaderModel = new UserRowNOModel();
		return userRowHeaderModel;
	}

	public BillModelCellEditableController getCellEditableController() {
		return cellEditableController;
	}

	public void setCellEditableController(
			BillModelCellEditableController cellEditableController) {
		this.cellEditableController = cellEditableController;
	}

	private boolean isIgnoreScaleWhenSetValue() {
		return ignoreScaleWhenSetValue;
	}

	public void addSortRelaObjectListener(IBillRelaSortListener l) {
		sortRelaObjectListener.add(IBillRelaSortListener.class, l);
	}

	public void addSortRelaObjectListener2(IBillRelaSortListener2 l) {
		sortRelaObjectListener.add(IBillRelaSortListener2.class, l);
	}

	public void addBillSortListener2(BillSortListener2 l) {
		afterSortListener.add(BillSortListener2.class, l);
	}

	public void removeSortRelaObjectListener(IBillRelaSortListener l) {
		sortRelaObjectListener.remove(IBillRelaSortListener.class, l);
	}

	public void removeSortRelaObjectListener2(IBillRelaSortListener2 l) {
		sortRelaObjectListener.remove(IBillRelaSortListener2.class, l);
	}

	public void removeBillSortListener2(BillSortListener2 l) {
		afterSortListener.remove(BillSortListener2.class, l);
	}

	private List[] getSortObjectList() {
		List[] ret = null;

		ArrayList<List> sortObject = new ArrayList<List>();
		sortObject.add(vRowAttrib);

		EventListener[] ls = sortRelaObjectListener
				.getListeners(IBillRelaSortListener.class);
		for (int i = 0; i < ls.length; i++) {
			List o = (List) ((IBillRelaSortListener) ls[i]).getRelaSortObject();
			if (o != null && o.size() != 0)
				sortObject.add(o);
		}

		ret = new List[sortObject.size()];
		sortObject.toArray(ret);

		return ret;
	}

	private Object[][] getSortObjectArray() {
		Object[][] ret = null;

		ArrayList<Object[]> sortObject = new ArrayList<Object[]>();

		EventListener[] ls = sortRelaObjectListener
				.getListeners(IBillRelaSortListener2.class);
		for (int i = 0; i < ls.length; i++) {
			Object[] o = (Object[]) ((IBillRelaSortListener2) ls[i])
					.getRelaSortObjectArray();
			if (o != null)
				sortObject.add(o);
		}

		ret = new Object[sortObject.size()][];

		for (int i = 0; i < sortObject.size(); i++) {
			ret[i] = sortObject.get(i);
		}

		return ret;
	}

	/**
	 * �÷���ֻ���ڽ������ģ���һ����������
	 * 
	 * @deprecated
	 */
	public void setIgnoreScaleWhenSetValue(boolean ignoreScaleWhenSetValue) {
		this.ignoreScaleWhenSetValue = ignoreScaleWhenSetValue;
	}

	/**
	 * ���ģ��������
	 * 
	 * @deprecated
	 */
	public boolean isSortAscending() {
		if (getSortColumns() != null && getSortColumns().size() > 0)
			return getSortColumns().get(0).isAscending();

		return false;
	}

	protected boolean isRowSelectMode() {
		return rowSelectMode;
	}

	protected void setRowSelectMode(boolean rowSelectMode) {
		this.rowSelectMode = rowSelectMode;
	}

	public void addRowStateChangeEventListener(
			IBillModelRowStateChangeEventListener l) {
		this.rowStateChangeListener = l;
	}

	public void removeRowStateChangeEventListener() {
		this.rowStateChangeListener = null;
	}

	public IBillModelRowStateChangeEventListener getRowStateChangeEventListener() {
		return this.rowStateChangeListener;
	}

	public void addHeadRowStateChangeEventListener(
			IBillModelHeadRowStateChangeEventListener l) {
		this.headRowStateChangeListener = l;
	}

	public void removeHeadRowStateChangeEventListener() {
		this.headRowStateChangeListener = null;
	}

	public IBillModelHeadRowStateChangeEventListener getHeadRowStateChangeEventListener() {
		return this.headRowStateChangeListener;
	}

	public Color getBackground(int row, int column) {
		String strKey = getBodyItems()[column].getKey();
		if (getRowAttribute(row) == null) {
			return null;
		}
		return getRowAttribute(row).getCellBackColor(strKey);

	}

	public Color getForeground(int row, int column) {
		String strKey = getBodyItems()[column].getKey();
		if (getRowAttribute(row) == null) {
			return null;
		}
		return getRowAttribute(row).getCellForeColor(strKey);
	}

	public void setBackground(Color color, int[] rows, int[] columns) {
		for (int i = 0; i < rows.length; i++) {
			getRowAttribute(rows[i]).clearCellBackColor();
			for (int j = 0; j < columns.length; j++) {
				String strKey = getBodyItems()[columns[i]].getKey();
				getRowAttribute(rows[i]).setCellBackColor(strKey, color);
			}
		}
	}

	public void setBackground(Color color, int row, int column) {
		String strKey = getBodyItems()[column].getKey();
		getRowAttribute(row).setCellBackColor(strKey, color);
	}

	public void setForeground(Color color, int[] rows, int[] columns) {
		for (int i = 0; i < rows.length; i++) {
			getRowAttribute(rows[i]).clearCellForeColor();
			for (int j = 0; j < columns.length; j++) {
				String strKey = getBodyItems()[columns[i]].getKey();
				getRowAttribute(rows[i]).setCellForeColor(strKey, color);
			}
		}
	}

	public void setForeground(Color color, int row, int column) {
		String strKey = getBodyItems()[column].getKey();
		getRowAttribute(row).setCellForeColor(strKey, color);
	}

	public Font getFont(int row, int column) {
		String strKey = getBodyItems()[column].getKey();
		if (getRowAttribute(row) == null) {
			return null;
		}
		return getRowAttribute(row).getCellFont(strKey);
	}

	public void setFont(Font font, int[] rows, int[] columns) {
		for (int i = 0; i < rows.length; i++) {
			getRowAttribute(rows[i]).clearCellFont();
			for (int j = 0; j < columns.length; j++) {
				String strKey = getBodyItems()[columns[i]].getKey();
				getRowAttribute(rows[i]).setCellFont(strKey, font);
			}
		}
	}

	public void setFont(Font font, int row, int column) {
		String strKey = getBodyItems()[column].getKey();
		getRowAttribute(row).setCellFont(strKey, font);

	}

	public IBillModeCellSpan getCellSpan() {
		return cellSpan;
	}

	public void addDefaultCellSpan() {
		cellSpan = new BillModelCellSpan(this);
	}

	public void setTabvo(BillTabVO tabvo) {
		this.tabvo = tabvo;
	}

	public BillTabVO getTabvo() {
		return tabvo;
	}

	/**
	 * Returns the number of rows in this data table.
	 * 
	 * @return the number of rows in the model
	 */
	public int getRowCount() {
		return dataVector.size();
	}

	/**
	 * Adds a row to the end of the model. The new row will contain
	 * <code>null</code> values unless <code>rowData</code> is specified.
	 * Notification of the row being added will be generated.
	 * 
	 * @param rowData
	 *            optional data of the row being added
	 */
	public void addRow(Vector rowData) {
		insertRow(getRowCount(), rowData);
	}

	/**
	 * Inserts a row at <code>row</code> in the model. The new row will contain
	 * <code>null</code> values unless <code>rowData</code> is specified.
	 * Notification of the row being added will be generated.
	 * 
	 * @param row
	 *            the row index of the row to be inserted
	 * @param rowData
	 *            optional data of the row being added
	 * @exception ArrayIndexOutOfBoundsException
	 *                if the row was invalid
	 */
	public void insertRow(int row, Vector rowData) {
		dataVector.insertElementAt(rowData, row);
		justifyRows(row, row + 1);
		if (isFireTableRowInsertedEvent) {
			fireTableRowsInserted(row, row);
		}
	}

	private void justifyRows(int from, int to) {
		// Sometimes the DefaultTableModel is subclassed
		// instead of the AbstractTableModel by mistake.
		// Set the number of rows for the case when getRowCount
		// is overridden.
		dataVector.setSize(getRowCount());

		for (int i = from; i < to; i++) {
			if (dataVector.elementAt(i) == null) {
				dataVector.setElementAt(new Vector(), i);
			}
			((Vector) dataVector.elementAt(i)).setSize(getColumnCount());
		}
	}

	/**
	 * Ensures that the new rows have the correct number of columns. This is
	 * accomplished by using the <code>setSize</code> method in
	 * <code>Vector</code> which truncates vectors which are too long, and
	 * appends <code>null</code>s if they are too short. This method also sends
	 * out a <code>tableChanged</code> notification message to all the
	 * listeners.
	 * 
	 * @param e
	 *            this <code>TableModelEvent</code> describes where the rows
	 *            were added. If <code>null</code> it assumes all the rows were
	 *            newly added
	 * @see #getDataVector
	 */
	public void newRowsAdded(TableModelEvent e) {
		justifyRows(e.getFirstRow(), e.getLastRow() + 1);
		fireTableChanged(e);
	}

	/**
	 * Removes the row at <code>row</code> from the model. Notification of the
	 * row being removed will be sent to all the listeners.
	 * 
	 * @param row
	 *            the row index of the row to be removed
	 * @exception ArrayIndexOutOfBoundsException
	 *                if the row was invalid
	 */
	public void removeRow(int row) {
		dataVector.removeElementAt(row);
		fireTableRowsDeleted(row, row);
	}

	/**
	 * Returns the <code>Vector</code> of <code>Vectors</code> that contains the
	 * table's data values. The vectors contained in the outer vector are each a
	 * single row of values. In other words, to get to the cell at row 1, column
	 * 5:
	 * <p>
	 * 
	 * <code>((Vector)getDataVector().elementAt(1)).elementAt(5);</code>
	 * <p>
	 * 
	 * @return the vector of vectors containing the tables data values
	 * 
	 * @see #newDataAvailable
	 * @see #newRowsAdded
	 * @see #setDataVector
	 */
	public Vector getDataVector() {
		return dataVector;
	}

	public void moveRow(int start, int end, int to) {
		int shift = to - start;
		int first, last;
		if (shift < 0) {
			first = to;
			last = end;
		} else {
			first = start;
			last = to + end - start;
		}
		rotate(getDataVector(), first, last + 1, shift);

		fireTableRowsUpdated(first, last);
	}

	private static void rotate(Vector v, int a, int b, int shift) {
		int size = b - a;
		int r = size - shift;
		int g = gcd(size, r);
		for (int i = 0; i < g; i++) {
			int to = i;
			Object tmp = v.elementAt(a + to);
			for (int from = (to + r) % size; from != i; from = (to + r) % size) {
				v.setElementAt(v.elementAt(a + from), a + to);
				to = from;
			}
			v.setElementAt(tmp, a + to);
		}
	}

	private static int gcd(int i, int j) {
		return (j == 0) ? i : gcd(j, i % j);
	}

	protected String getRowNO(int row) {
		return row + 1 + "";
	}

	private void loadEditRelationItemValue() {
		if (getRowCount() <= 0)
			return;

		BillItem[] items = getBodyItems();
		for (int i = 0; i < items.length; i++) {
			BillItem item = items[i];
			if (item.getDataType() == IBillItem.UFREF
					&& item.getMetaDataProperty() != null) {
				loadEditRelationItemValue(0, getRowCount() - 1, item.getKey());
			}
		}
	}

	protected void setBodyRowsObjectByMetaData(NCObject[] os, int[] rows) {
		if (ArrayUtils.isEmpty(os) || ArrayUtils.isEmpty(rows)
				|| os.length != rows.length)
			return;
		for (int i = 0; i < rows.length; i++) {
			setBodyRowObjectModelByMetaData(os[i], rows[i]);
		}
		loadLoadRelationItemValue(rows);
	}

	public void loadLoadRelationItemValue(int[] rows) {
		if (ArrayUtils.isEmpty(rows))
			return;
		Arrays.sort(rows);

		MetaDataGetBillModelRelationItemValue gvs = new MetaDataGetBillModelRelationItemValue();
		// 2011-4-2 ���ͯΰ �Զ����ϵȡֵ����
		Map<Integer, IConstEnum[]> valuemap = new HashMap<Integer, IConstEnum[]>();
		int m = 0;

		for (int col = 0; col < getBodyItems().length; col++) {
			BillItem item = getBodyItems()[col];

			if (item.getDataType() == IBillItem.UFREF
					&& item.getMetaDataProperty() != null) {

				ArrayList<IConstEnum> relationitem = getMetaDataRelationItems(
						item, false);

				if (relationitem != null) {

					String[] ids = new String[getRowCount()];

					for (int i = 0; i < rows.length; i++) {
						Object o = getValueAt(rows[i], col);
						if (o != null && o instanceof IConstEnum)
							ids[rows[i]] = (String) ((IConstEnum) o).getValue();
					}
					gvs.addRelationItem(item, relationitem, ids);
					if (!(item.getGetBillRelationItemValue() instanceof MetaDataGetBillRelationItemValue)) {
						valuemap.put(m, item.getGetBillRelationItemValue()
								.getRelationItemValue(relationitem, ids));
					}
					m++;

				}
			}
		}

		IConstEnum[] o = gvs.getRelationItemValues();

		if (o == null) {
			return;
		}

		for (int i = 0; i < o.length; i++) {
			if (valuemap.get(i) != null) {
				o[i] = valuemap.get(i)[0];
			}
		}

		for (int i = 0; i < rows.length; i++) {
			for (int j = 0; j < o.length; j++) {
				if (o[j].getValue() != null) {
					Object[] v = (Object[]) o[j].getValue();
					setValueAt(v[rows[i]], rows[i], o[j].getName());
				}

			}
		}
	}

	public List<RowAttribute> getRowAttrib() {
		return vRowAttrib;
	}

	public boolean isImporting() {
		return isImporting;
	}

	public void setImporting(boolean isImporting) {
		this.isImporting = isImporting;
	}

	private Set<Integer> highLightRowSet = new HashSet<Integer>();

	public void setHighLightRows(int[] rows) {

		highLightRowSet.clear();
		if (rows != null) {
			for (int i = 0; i < rows.length; i++) {
				highLightRowSet.add(rows[i]);
			}
		}

	}

	public Set<Integer> getHighLightRowsSet() {

		return highLightRowSet;
	}
}