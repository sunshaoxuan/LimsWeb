package nc.ui.pub.bill;

/**
 * ���ݱ༭�¼�. ��������:(2001-3-23 1:52:04)
 * 
 * @author:�ν�
 */
@SuppressWarnings("serial")
public class BillEditEvent extends java.util.EventObject {
	private String m_strTableCode = null; // �����ӱ����

	private int m_iOldrow = -1; // ԭ��
	private int m_iRow = -1; // ����
	private String m_strKey = null; // �ؼ���
	private Object m_oOldValue = null; // ԭֵ
	private Object m_oValue = null; // ֵ
	private int m_iPos = -1; // λ��

	private int[] m_iOldrows = null; // ԭ��
	private int[] m_iRows = null; // ����
	//����-������� tank 2019��11��10��22:41:15
	private int clickCount = 0;
	

	/**
	 * ���б任�¼�.
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
	 * ����༭�¼�.
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
	 * ��ͷ,βԪ�ر༭�¼�.
	 */
	public BillEditEvent(Object source, Object value, String key) {
		this(source, value, key, -1);
	}

	/**
	 * ����༭�¼�.
	 */
	public BillEditEvent(Object source, Object value, String key, int row) {
		super(source);
		this.m_oValue = value;
		this.m_strKey = key;
		this.m_iRow = row;
	}

	/**
	 * ����༭�¼�.
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
	 * ��ùؼ���. ��������:(2001-3-23 1:55:35)
	 * 
	 * @return java.lang.String
	 */
	public String getKey() {
		return m_strKey;
	}

	/**
	 * ���ԭ��. ��������:(2001-3-23 1:56:28)
	 * 
	 * @return int
	 */
	public int getOldRow() {
		return m_iOldrow;
	}

	/**
	 * ���ԭֵ. ��������:(2001-3-23 1:55:35)
	 * 
	 * @return java.lang.String
	 */
	public Object getOldValue() {
		return m_oOldValue;
	}

	/**
	 * ���λ��. ��������:(2001-3-23 1:56:28)
	 * 
	 * @return int
	 */
	public int getPos() {
		return m_iPos;
	}

	/**
	 * ��õ�ǰ��. ��������:(2001-3-23 1:56:28)
	 * 
	 * @return int
	 */
	public int getRow() {
		return m_iRow;
	}

	/**
	 * ��������ӱ�. ��������:(2001-3-23 1:55:35)
	 * 
	 * @return java.lang.String
	 */
	public String getTableCode() {
		return m_strTableCode;
	}

	/**
	 * ���ֵ. ��������:(2001-3-23 1:55:35)
	 * 
	 * @return java.lang.String
	 */
	public Object getValue() {
		return m_oValue;
	}

	/**
	 * ��������ӱ�. ��������:(2001-3-23 1:55:35)
	 * 
	 * @return java.lang.String
	 */
	public void setTableCode(String newTableCode) {
		m_strTableCode = newTableCode;
	}

	/**
	 * ��ùؼ���. ��������:(2001-3-23 1:55:35)
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
