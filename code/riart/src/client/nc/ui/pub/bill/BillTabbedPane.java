package nc.ui.pub.bill;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.plaf.TabbedPaneUI;

import nc.bs.logging.Logger;
import nc.bs.pubapp.utils.UserDefineRefUtils;
import nc.ui.pub.beans.ActionsBar;
import nc.ui.pub.beans.ExtTabbedPane;
import nc.ui.pub.beans.UIScrollPane;
import nc.ui.pub.beans.UIToolBar;
import nc.ui.pub.beans.bill.IBillTabbedPane;
import nc.ui.pub.beans.toolbar.NCToolbar;
import nc.ui.pub.bill.action.SeparatorAction;
import nc.ui.pubapp.uif2app.components.grand.CardGrandPanelComposite;
import nc.ui.pubapp.uif2app.components.grand.mediator.MainGrandMediator;
import nc.ui.pubapp.uif2app.components.grand.util.CardPanelEventUtil;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.vo.pub.bill.BillTabVO;

/**
 * TabbedPane,以tablecode为标志,存放UIScrollPane. 创建日期:(2002-09-10 13:01:36)
 */

@SuppressWarnings("serial")
public class BillTabbedPane extends ExtTabbedPane implements
		IBillTabbedPane {
//	private boolean isOneTable = true;
	
	private ActionsBar toolBar = null;

	private int intPos = BillItem.HEAD; //在BillCardPanel中所处位置

	//
	private Vector<BillTabVO> vTabVOs = new Vector<BillTabVO>();

	private BillTabbedPaneTabChangeListener tabChangedListener;

	private BillTabbedPaneTabChangeListener2 tabChangedListener2;

	BillTabbedPaneTabChangeEvent e = new BillTabbedPaneTabChangeEvent(this);

    private Component tabAreaComponent = null;

    /**
	 * BillBodyTabbedPane 构造子注解.
	 */
	public BillTabbedPane() {
		super();
		setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		
		}
	
	
//	/**
//	 * BillBodyTabbedPane 构造子注解.
//	 * 
//	 * @param p0
//	 *            int
//	 */
//	public BillTabbedPane(int p0) {
//		super(p0);
//		setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
////		setFont(new Font("dialog", java.awt.Font.PLAIN, 12));
//		addTabAreaComponent(getToolBar());
//	}

	public BillTabbedPaneTabChangeListener getTabChangedListener() {
		return tabChangedListener;
	}


	public void setTabChangedListener(BillTabbedPaneTabChangeListener tabChangedListener) {
		this.tabChangedListener = tabChangedListener;
	}


	public BillTabbedPaneTabChangeListener2 getTabChangedListener2() {
		return tabChangedListener2;
	}


	public void setTabChangedListener2(BillTabbedPaneTabChangeListener2 tabChangedListener2) {
		this.tabChangedListener2 = tabChangedListener2;
	}


	/**
	 * 
	 * 创建日期:(2002-09-10 13:40:02)
	 * 
	 * @param tablename
	 *            java.lang.String
	 * @param scrollPane
	 *            nc.ui.pub.bill1.BillScrollPane
	 */
	public void addScrollPane(BillTabVO[] btvos, UIScrollPane[] scrollPanes) {
		if (btvos == null || scrollPanes == null)
			return;
		for (int i = 0; i < btvos.length; i++)
			addScrollPane(btvos[i], scrollPanes[i]);
		updateUI();
	}

	/**
	 * 
	 * 创建日期:(2002-09-10 13:40:02)
	 * 
	 * @param tablename
	 *            java.lang.String
	 * @param scrollPane
	 *            nc.ui.pub.bill1.BillScrollPane
	 */
	public void addScrollPane(BillTabVO btvo, UIScrollPane scrollPane) {
		if (btvo == null || scrollPane == null)
			return;
		String tabname = btvo.getTabname();
		
		if(btvo.getPos() != IBillItem.BODY && btvo.getBasetab() != null)
			if(tabname != null)
				tabname = "*" + tabname;
			else
				tabname = "*";
		
		if (indexOfComponent(scrollPane) != -1) {
			return;
		}
		scrollPane.setName(tabname);

		//应该按tablecode进行排序
		vTabVOs.addElement(btvo);
		addTab(tabname, null, scrollPane, tabname);
	}

	void removeScrollPane(UIScrollPane scrollPane) {
		if (scrollPane == null)
			return;
		int index = indexOfComponent(scrollPane);
		if (index >= 0) {
			removeTabAt(index);
			vTabVOs.remove(index);
		}
	}

	/**
	 * 更改页签名称. 创建日期:(2002-10-30 19:38:58)
	 * 
	 * @param tableCode
	 *            java.lang.String
	 * @param newTableName
	 *            java.lang.String
	 */
	public int getIndexofTableCode(BillTabVO btvo) {
		int size = 0;
		if (vTabVOs == null || (size = vTabVOs.size()) == 0)
			return -1;
		int pos = btvo.getPos().intValue();
		String tablecode = btvo.getTabcode();
		BillTabVO btvo1;
		for (int i = 0; i < size; i++) {
			btvo1 = vTabVOs.elementAt(i);
			if (btvo1.getPos().intValue() == pos && tablecode != null
					&& tablecode.equals(btvo1.getTabcode()))
				return i;
		}
		return -1;
	}
	
	public void clearShowWarning(){
		if (vTabVOs == null || (vTabVOs.size()) == 0)
			return ;

		for (int i = 0; i < vTabVOs.size(); i++) {
			setShowWarning(i, false);
		}
		
		
	}

	public void clearShowWarning(String tabCode){
		setShowWarning(tabCode,false);
	}
	
	public void showWarning(String tabCode){
		setShowWarning(tabCode,true);
	}
	
	private void setShowWarning(String tabCode,boolean showWarning) {
		if (vTabVOs == null || (vTabVOs.size()) == 0 || tabCode == null)
			return ;

		int index = -1;
		for (int i = 0; i < vTabVOs.size(); i++) {
			if (tabCode.equals(vTabVOs.elementAt(i).getTabcode())){
				index = i;
				break;
			}
		}
		
		if(index > -1)
			setShowWarning(index, showWarning);
	}
	
	@Override
	public void setSelectedIndex(int tabIndex) {
		int oldSelectedIndex = getSelectedIndex();
		if (tabIndex != oldSelectedIndex && tabIndex >= 0
				&& oldSelectedIndex >= 0) {
			if (tabChangedListener2 != null) {
				BillTabVO btvo = vTabVOs.elementAt(oldSelectedIndex);
				e.setBtvo(btvo);
				if(!tabChangedListener2.beforeTabChanged(e))
					return;
			}
		}
		super.setSelectedIndex(tabIndex);
		if (getSelectedComponent() instanceof BillScrollPane){
			((BillScrollPane)getSelectedScrollPane()).getTable().requestFocus();
		}
	}


	/**
	 * 
	 * 创建日期:(2002-09-23 15:02:00)
	 * 
	 * @return int
	 */
	public int getPos() {
		return intPos;
	}

	/**
	 * 
	 * 创建日期:(2002-09-10 13:37:32)
	 * 
	 * @param tablecode
	 *            java.lang.String
	 */
	public UIScrollPane getScrollPane(BillTabVO btvo) {
		int index = getIndexofTableCode(btvo);
		return (index < 0) ? null : (UIScrollPane) getComponentAt(index);
	}

	/**
	 * 
	 * 创建日期:(2002-09-10 15:12:26)
	 * 
	 * @return nc.ui.pub.beans.UIScrollPane
	 */
	public UIScrollPane getSelectedScrollPane() {
		return (UIScrollPane) getSelectedComponent();
	}

	/**
	 * 
	 * 创建日期:(2002-09-11 15:36:35)
	 * 
	 * @return java.lang.String
	 */
	public String getSelectedTableCode() {
		int index = getSelectedIndex();
		if (index < 0 || vTabVOs.size() <= index)
			return null;
		return vTabVOs.elementAt(index).getTabcode();
	}

	/**
	 * Returns the tooltip text for the component determined by the mouse event
	 * location.
	 * 
	 * @param event
	 *            the MouseEvent that tells where the cursor is lingering
	 * @return the String with the tooltip text
	 */
	public String getToolTipText(MouseEvent event) {
		return null;
	}

	/**
	 * Identifies whether or not this component can receive the focus. A
	 * disabled button, for example, would return false.
	 * 
	 * @return true if this component can receive the focus
	 */
	public boolean isFocusable() {
		return super.isFocusable() && isEnabled()
				&& isRequestFocusEnabled() && isTabVisible();
	}

	/**
	 * 
	 * 创建日期:(2003-9-24 9:09:21)
	 * 
	 * @return boolean
	 */
	public boolean isTabVisible() {
		TabbedPaneUI ui = getUI();
		boolean visible = true;
		if (ui instanceof UITabbedPaneUI) {
			visible = ((UITabbedPaneUI) ui).isTabShowable();
		}
		return visible;
	}

	/**
	 * 返回单据体Panel.
	 * 
	 * @return BillScrollPane
	 */
	static void onBillTabbedChange(BillTabbedPane btp, int oldSelectedIndex) {
		if (btp == null || oldSelectedIndex < 0)
			return;
		int tabIndex = btp.getSelectedIndex();
		if (tabIndex != oldSelectedIndex && tabIndex >= 0
				&& oldSelectedIndex >= 0) {
			Component com1 = btp.getComponentAt(oldSelectedIndex);
			Component com2 = btp.getComponentAt(tabIndex);
			BillScrollPane bsp1 = com1 instanceof BillScrollPane ? (BillScrollPane) com1
					: null;
			BillScrollPane bsp2 = com2 instanceof BillScrollPane ? (BillScrollPane) com2
					: null;
			if (bsp1 != null && bsp2 != null) {
				BillModel model1 = bsp1.getTableModel();
				BillModel model2 = bsp2.getTableModel();
		        if (bsp1.getTable().isEditing())
		            if (bsp1.getTable().getCellEditor() != null)
		            	bsp1.getTable().getCellEditor().stopCellEditing();
				if (model1 == model2) {
					//int[] indexes = bsp1.getTable().getSelectedRows();
					bsp2.getTable().setSelectionModel(
							bsp1.getTable().getSelectionModel());
				}
			}
			if (btp.tabChangedListener != null) {
				BillTabVO btvo = btp.vTabVOs.elementAt(tabIndex);
				btp.e.setBtvo(btvo);
				btp.tabChangedListener.afterTabChanged(btp.e);
			}
		}
		//tank 2019年6月16日16:12:12 页签切换的时候刷新孙表
		refreshGrand(btp);
		
	}

	private static void refreshGrand(BillTabbedPane btp) {
		BillForm grandBillForm = null;
		String grandTabCode = null;
		List<Object> grandVOList = null;
		
		String currentbodyTabCode = null;
		try{
			if(btp.getTabChangedListener() instanceof MainGrandMediator){
				MainGrandMediator cpc = (MainGrandMediator)btp.getTabChangedListener();
				// 获得当前选中行
				int currentRow = cpc.getMainBillForm().getBillCardPanel().getBillTable().getSelectedRow();
				if(currentRow >= 0){
					currentbodyTabCode = cpc.getMainBillForm().getBillCardPanel().getCurrentBodyTableCode();//pk_task_b
					// 根据当前子页签获取孙表视图
					grandBillForm = (BillForm) cpc.getMainGrandRelationShip().getBodyTabTOGrandCardComposite().get(currentbodyTabCode);
					if(grandBillForm!=null){
						grandTabCode = grandBillForm.getBillCardPanel().getCurrentBodyTableCode();//pk_task_s pk_task_r
						grandVOList = cpc.getMainGrandAssist().getGrandCardDataByGrandTab(cpc.getMainBillForm(), currentRow, grandBillForm,
								cpc.getMainGrandRelationShip());
					}
					
				}
			}
		}catch(Exception e){
			Logger.debug(e.getMessage());
		}
		if(grandBillForm!=null && grandTabCode !=null && grandVOList!=null && "pk_task_b".equals(currentbodyTabCode) 
				&& ( "pk_task_s".equals(grandTabCode) || "pk_task_r".equals(grandTabCode))){
			UserDefineRefUtils.refreshBillCardGrandDefRefs(grandBillForm, grandTabCode, grandVOList);
		}
		
	}


	/**
	 * 
	 * 创建日期:(2002-09-17 14:14:34)
	 */
	public void removeAll() {
		if (getTabCount() > 0)
			super.removeAll();

		//htBodyPanes = null;
		//vTablecodes = new Vector();
		vTabVOs.clear();
	}

	/**
	 * 
	 * 创建日期:(2002-09-20 10:13:43)
	 * 
	 * @param newPos
	 *            int
	 */
	public void setPos(int newPos) {
		intPos = newPos;
	}

	/**
	 * 
	 * 创建日期:(2003-8-7 9:49:38)
	 * 
	 * @param e
	 *            nc.ui.pub.bill.BillTabbedPaneTabChangeListener
	 * @param pos
	 *            int
	 */
	void addTabChangeListener(BillTabbedPaneTabChangeListener l) {
		this.tabChangedListener = l;
	}

	void removeTabChangeListener() {
		this.tabChangedListener = null;
	}

	/**
	 * 
	 * 创建日期:(2003-8-7 9:49:38)
	 * 
	 * @param e
	 *            nc.ui.pub.bill.BillTabbedPaneTabChangeListener
	 * @param pos
	 *            int
	 */
	void addTabChangeListener2(BillTabbedPaneTabChangeListener2 l) {
		this.tabChangedListener2 = l;
	}

	/**
	 * 
	 * 创建日期:(2003-8-7 9:49:38)
	 * 
	 * @param e
	 *            nc.ui.pub.bill.BillTabbedPaneTabChangeListener
	 * @param pos
	 *            int
	 */
	void removeTabChangeListener2() {
		this.tabChangedListener2 = null;
	}

	/**
	 * 
	 * 创建日期:(2003-9-24 9:09:21)
	 * 
	 * @param newTabVisible
	 *            boolean
	 */
	public void setTabVisible(boolean newTabVisible) {
		TabbedPaneUI ui = getUI();
		if (ui instanceof UITabbedPaneUI) {
			if (isTabVisible() != newTabVisible) {
				((UITabbedPaneUI) ui).setTabVisible(newTabVisible);
				revalidate();
			}
		}
	}

	/**
	 * 
	 * 创建日期:(2003-9-24 9:09:21)
	 * 
	 * @param newTabVisible
	 *            boolean
	 */
	public void setSingleTabVisible(boolean newTabVisible) {
		TabbedPaneUI ui = getUI();
		if (ui instanceof UITabbedPaneUI) {
			if (isSingleTabVisible() != newTabVisible) {
				((UITabbedPaneUI) ui).setSingleTabVisible(newTabVisible);
				revalidate();
			}
		}
	}
	
	private boolean isSingleTabVisible(){
		TabbedPaneUI ui = getUI();
		boolean visible = true;
		if (ui instanceof UITabbedPaneUI) {
			visible = ((UITabbedPaneUI) ui).isSingleTabVisible();
		}
		return visible;
	}

	/**
	 * 
	 * 创建日期:(2003-9-24 9:09:21)
	 * 
	 * @param visible
	 *            boolean
	 */
	public void setTabVisible(int index, boolean visible) {
		TabbedPaneUI ui = getUI();
		if (ui instanceof UITabbedPaneUI) {
			((UITabbedPaneUI) ui).setTabVisible(index, visible);
			if (index < getTabCount())
				setEnabledAt(index, visible);
			if (!visible) {
				int tabs = getTabCount();
				for (int i = 0; i < tabs; i++) {
					if (isEnabledAt(i)) {
						setSelectedIndex(i);
					}
				}
			}
			revalidate();
		}
	}

	/**
	 * 
	 * 创建日期:(2003-6-9 14:34:04)
	 */
	public void updateUI() {
		setUI(new UITabbedPaneUI(false));
//		setUI(new nc.ui.pub.beans.UITabbedPaneUI(false));
	}

	private ActionsBar getToolBar() {
		if (toolBar == null) {
//			toolBar = new UIToolBar() {
//			    public void addSeparator( Dimension size )
//			    {
//			        JToolBar.Separator s = new JToolBar.Separator( size );
//			        s.setUI(new WindowsToolBarSeparatorUI());
//			        add(s);
//			    }				
//			};
//			toolBar.setOpaque(false);
//			toolBar.setFloatable(false);
//			toolBar.setBorder(null);
//			toolBar.setPreferredSize(new Dimension(200, 21));
//			FlowLayout flowLayout = new FlowLayout(FlowLayout.RIGHT);
//			flowLayout.setVgap(1);
//			toolBar.setLayout(flowLayout);
//lkp update 单据模板采用统一工具栏控件实现肩膀按钮
			toolBar = new ActionsBar();
		}
		return toolBar;
	}
	
	public void addTabActions(List<? extends Action> acts){
		if(acts == null || acts.size() == 0){
			romoveTabAreaComponent();
			return;
		}
//		if (getToolBar().getActions()!=null){
//			Action[] as =  getToolBar().getActions();
//			for (Action action : as){
//				getToolBar().removeAction(action);
//			}
//		}
		
		
		List<Action> actions = filterAction(acts);
		
		getToolBar().setActions(actions);
		
//		for (Action action : actions) {
//			if(action instanceof nc.ui.pub.beans.toolbar.SeparatorAction)
////				getToolBar().addSeparator(new Dimension(2,20));
//				getToolBar().addSeparator();
//			else
//				getToolBar().addAction(action);
//		}
		
		addToolbar2TabArea(actions);
	}

    
	/**
	 * lkp add 确保toolbar右侧有5px的空隙，上侧2px的空隙
	 * @param actions
	 */
	private void addToolbar2TabArea(List<Action> actions) {
		
//		int preWidth = NCToolbar.computePrefreWidth(actions);
//		getToolBar().setPreferredSize(new Dimension(preWidth, 23));
		JPanel tabAreaComp = new JPanel();
		tabAreaComp.setOpaque(false);
//		tabAreaComp.setPreferredSize(new Dimension(preWidth+45, 25));
		tabAreaComp.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 2));
		tabAreaComp.add(getToolBar());
		addTabAreaComponent(tabAreaComp);
	}
	
	
	
	/**
	 * 将acts中的nc.ui.pub.bill.action.SeparatorAction转换成ncbeans中NCToolbar需要的nc.ui.pub.beans.toolbar.SeparatorAction
	 * @param acts
	 * @return
	 */
	private List<Action> filterAction(List<? extends Action> acts)
	{
		List<Action> list = new ArrayList<Action>();
		for(Action action : acts)
		{
			if(action instanceof SeparatorAction || action instanceof nc.funcnode.ui.action.SeparatorAction)
				list.add(new nc.ui.pub.beans.toolbar.SeparatorAction());
			else
				list.add(action);
		}
		return list;
	}
	
	public void removeTableActions(){
		romoveTabAreaComponent();
	}
	
    public void addTabAreaComponent(Component comp){
        Component oldComp = getTabAreaComponent();
        if((oldComp != null && oldComp.equals(comp)) || (oldComp == null && comp == null)){
            return;
        }
        tabAreaComponent = comp;
        firePropertyChange("tabAreaComponent", oldComp, tabAreaComponent);
    }
    public Component getTabAreaComponent(){
        return tabAreaComponent;
    }

    private void romoveTabAreaComponent(){
    	Component oldComp = getTabAreaComponent();
    	tabAreaComponent = null;
        firePropertyChange("tabAreaComponent", oldComp, tabAreaComponent);
	}
	@Override
	public boolean isClosableTab(int index) {
		return false;
	}

	public boolean isTabShowable() {
		
		boolean show = true;
		
		TabbedPaneUI ui = getUI();
		if (ui instanceof UITabbedPaneUI) {
			show = ((UITabbedPaneUI)ui).isTabShowable() || tabAreaComponent!= null;
		}
		return show;
	}
	public Action[] getToolBarActions(){
		return getToolBar().getActions();
	}
}