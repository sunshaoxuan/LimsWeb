package nc.ui.pub.bill;

/**
 * 单据编辑事件. 创建日期:(2001-3-23 1:52:04)
 * 
 * @author:宋杰
 */
@SuppressWarnings("serial")
public class BillEditEvent extends java.util.EventObject {
	private String m_strTableCode = null; // 所属子表编码

	private int m_iOldrow = -1; // 原行
	private int m_iRow = -1; // 现行
	private String m_strKey = null; // 关键字
	private Object m_oOldValue = null; // 原值
	private Object m_oValue = null; // 值
	private int m_iPos = -1; // 位置

	private int[] m_iOldrows = null; // 原行
	private int[] m_iRows = null; // 现行
	//任务单-点击次数 tank 2019年11月10日22:41:15
	private int clickCount = 0;
	

	/**
	 * 行列变换事件.
	 */
	public BillEditEvent(Object source, int oldrow, int row) {
		super(source);
		this.m_iOldrow = oldrow;
		this.m_iRow = row;
	}

	public BillEditEvent(Object source, int[] oldrows, int[] rows) {
		super(source);
		this.m_iOldrows = oldrows;
		this.m_iRows = rows;
	}

	/**
	 * 表体编辑事件.
	 */
	public BillEditEvent(Object source, Object oldvalue, Object value,
			String key, int row, int pos) {
		super(source);
		this.m_oValue = value;
		this.m_oOldValue = oldvalue;
		this.m_strKey = key;
		this.m_iRow = row;
		this.m_iPos = pos;
	}

	/**
	 * 表头,尾元素编辑事件.
	 */
	public BillEditEvent(Object source, Object value, String key) {
		this(source, value, key, -1);
	}

	/**
	 * 表体编辑事件.
	 */
	public BillEditEvent(Object source, Object value, String key, int row) {
		super(source);
		this.m_oValue = value;
		this.m_strKey = key;
		this.m_iRow = row;
	}

	/**
	 * 表体编辑事件.
	 */
	public BillEditEvent(Object source, Object value, String key, int row,
			int pos) {
		super(source);
		this.m_oValue = value;
		this.m_strKey = key;
		this.m_iRow = row;
		this.m_iPos = pos;
	}

	/**
	 * 获得关键字. 创建日期:(2001-3-23 1:55:35)
	 * 
	 * @return java.lang.String
	 */
	public String getKey() {
		return m_strKey;
	}

	/**
	 * 获得原行. 创建日期:(2001-3-23 1:56:28)
	 * 
	 * @return int
	 */
	public int getOldRow() {
		return m_iOldrow;
	}

	/**
	 * 获得原值. 创建日期:(2001-3-23 1:55:35)
	 * 
	 * @return java.lang.String
	 */
	public Object getOldValue() {
		return m_oOldValue;
	}

	/**
	 * 获得位置. 创建日期:(2001-3-23 1:56:28)
	 * 
	 * @return int
	 */
	public int getPos() {
		return m_iPos;
	}

	/**
	 * 获得当前行. 创建日期:(2001-3-23 1:56:28)
	 * 
	 * @return int
	 */
	public int getRow() {
		return m_iRow;
	}

	/**
	 * 获得所属子表. 创建日期:(2001-3-23 1:55:35)
	 * 
	 * @return java.lang.String
	 */
	public String getTableCode() {
		return m_strTableCode;
	}

	/**
	 * 获得值. 创建日期:(2001-3-23 1:55:35)
	 * 
	 * @return java.lang.String
	 */
	public Object getValue() {
		return m_oValue;
	}

	/**
	 * 获得所属子表. 创建日期:(2001-3-23 1:55:35)
	 * 
	 * @return java.lang.String
	 */
	public void setTableCode(String newTableCode) {
		m_strTableCode = newTableCode;
	}

	/**
	 * 获得关键字. 创建日期:(2001-3-23 1:55:35)
	 * 
	 * @return java.lang.String
	 */
	public void setKey(String key) {
		m_strKey = key;
	}

	public int[] getOldrows() {
		return m_iOldrows;
	}

	public void setOldrows(int[] oldrows) {
		m_iOldrows = oldrows;
	}

	public int[] getRows() {
		return m_iRows;
	}

	public void setRows(int[] rows) {
		m_iRows = rows;
	}

	public int getClickCount() {
		return clickCount;
	}

	public void setClickCount(int clickCount) {
		this.clickCount = clickCount;
	}
}
