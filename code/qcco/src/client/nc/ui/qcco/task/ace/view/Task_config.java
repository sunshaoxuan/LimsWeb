package nc.ui.qcco.task.ace.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class Task_config extends AbstractJavaBeanDefinition {
	private Map<String, Object> context = new HashMap();

	public nc.vo.uif2.LoginContext getContext() {
		if (context.get("context") != null)
			return (nc.vo.uif2.LoginContext) context.get("context");
		nc.vo.uif2.LoginContext bean = new nc.vo.uif2.LoginContext();
		context.put("context", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.qcco.task.ace.serviceproxy.AceTaskDeleteProxy getDeleteProxy() {
		if (context.get("deleteProxy") != null)
			return (nc.ui.qcco.task.ace.serviceproxy.AceTaskDeleteProxy) context.get("deleteProxy");
		nc.ui.qcco.task.ace.serviceproxy.AceTaskDeleteProxy bean = new nc.ui.qcco.task.ace.serviceproxy.AceTaskDeleteProxy();
		context.put("deleteProxy", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.qcco.task.ace.serviceproxy.AceTaskMaintainProxy getBmModelModelService() {
		if (context.get("bmModelModelService") != null)
			return (nc.ui.qcco.task.ace.serviceproxy.AceTaskMaintainProxy) context.get("bmModelModelService");
		nc.ui.qcco.task.ace.serviceproxy.AceTaskMaintainProxy bean = new nc.ui.qcco.task.ace.serviceproxy.AceTaskMaintainProxy();
		context.put("bmModelModelService", bean);
		bean.setGrandTabAndVOMap(getManagedMap0());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private Map getManagedMap0() {
		Map map = new HashMap();
		map.put("pk_task_r", getTaskRVO());
		map.put("pk_task_s", getTaskSVO());
		return map;
	}

	public nc.vo.bd.meta.GeneralBDObjectAdapterFactory getBOAdapterFactory() {
		if (context.get("BOAdapterFactory") != null)
			return (nc.vo.bd.meta.GeneralBDObjectAdapterFactory) context.get("BOAdapterFactory");
		nc.vo.bd.meta.GeneralBDObjectAdapterFactory bean = new nc.vo.bd.meta.GeneralBDObjectAdapterFactory();
		context.put("BOAdapterFactory", bean);
		bean.setMode("MD");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.vo.bd.meta.BDObjectAdpaterFactory getBoadatorfactory2() {
		if (context.get("boadatorfactory2") != null)
			return (nc.vo.bd.meta.BDObjectAdpaterFactory) context.get("boadatorfactory2");
		nc.vo.bd.meta.BDObjectAdpaterFactory bean = new nc.vo.bd.meta.BDObjectAdpaterFactory();
		context.put("boadatorfactory2", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.model.BillManageModel getManageAppModel() {
		if (context.get("manageAppModel") != null)
			return (nc.ui.pubapp.uif2app.model.BillManageModel) context.get("manageAppModel");
		nc.ui.pubapp.uif2app.model.BillManageModel bean = new nc.ui.pubapp.uif2app.model.BillManageModel();
		context.put("manageAppModel", bean);
		bean.setContext(getContext());
		bean.setBusinessObjectAdapterFactory(getBOAdapterFactory());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.qcco.task.model.SubGrandBillModel getManageAppModel2() {
		if (context.get("manageAppModel2") != null)
			return (nc.ui.qcco.task.model.SubGrandBillModel) context.get("manageAppModel2");
		nc.ui.qcco.task.model.SubGrandBillModel bean = new nc.ui.qcco.task.model.SubGrandBillModel();
		context.put("manageAppModel2", bean);
		bean.setBusinessObjectAdapterFactory(getBoadatorfactory2());
		bean.setContext(getContext());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.components.grand.model.MainGrandModel getMainGrandModel() {
		if (context.get("mainGrandModel") != null)
			return (nc.ui.pubapp.uif2app.components.grand.model.MainGrandModel) context.get("mainGrandModel");
		nc.ui.pubapp.uif2app.components.grand.model.MainGrandModel bean = new nc.ui.pubapp.uif2app.components.grand.model.MainGrandModel();
		context.put("mainGrandModel", bean);
		bean.setHandleListCardIsShow(true);
		bean.setMainModel(getManageAppModel());
		bean.setGrandModel(getManageAppModel2());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.query2.model.ModelDataManager getBmModelModelDataManager() {
		if (context.get("bmModelModelDataManager") != null)
			return (nc.ui.pubapp.uif2app.query2.model.ModelDataManager) context.get("bmModelModelDataManager");
		nc.ui.pubapp.uif2app.query2.model.ModelDataManager bean = new nc.ui.pubapp.uif2app.query2.model.ModelDataManager();
		context.put("bmModelModelDataManager", bean);
		bean.setModel(getManageAppModel());
		bean.setService(getBmModelModelService());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.components.grand.MainGrandRelationShip getMainGrandRelationShip() {
		if (context.get("mainGrandRelationShip") != null)
			return (nc.ui.pubapp.uif2app.components.grand.MainGrandRelationShip) context.get("mainGrandRelationShip");
		nc.ui.pubapp.uif2app.components.grand.MainGrandRelationShip bean = new nc.ui.pubapp.uif2app.components.grand.MainGrandRelationShip();
		context.put("mainGrandRelationShip", bean);
		bean.setBodyTabTOGrandListComposite(getManagedMap1());
		bean.setBodyTabTOGrandCardComposite(getManagedMap2());
		bean.setGrandTabAndVOMap(getManagedMap3());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private Map getManagedMap1() {
		Map map = new HashMap();
		map.put("pk_task_b", getSunlistView1());
		return map;
	}

	private Map getManagedMap2() {
		Map map = new HashMap();
		map.put("pk_task_b", getSunbillFormEditor1());
		return map;
	}

	private Map getManagedMap3() {
		Map map = new HashMap();
		map.put("pk_task_r", getTaskRVO());
		map.put("pk_task_s", getTaskSVO());
		return map;
	}

	public nc.vo.qcco.task.TaskRVO getTaskRVO() {
		if (context.get("TaskRVO") != null)
			return (nc.vo.qcco.task.TaskRVO) context.get("TaskRVO");
		nc.vo.qcco.task.TaskRVO bean = new nc.vo.qcco.task.TaskRVO();
		context.put("TaskRVO", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.vo.qcco.task.TaskSVO getTaskSVO() {
		if (context.get("TaskSVO") != null)
			return (nc.vo.qcco.task.TaskSVO) context.get("TaskSVO");
		nc.vo.qcco.task.TaskSVO bean = new nc.vo.qcco.task.TaskSVO();
		context.put("TaskSVO", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.components.grand.action.ExpendShrinkGrandListAction getExpendShrinkGrandListAction() {
		if (context.get("expendShrinkGrandListAction") != null)
			return (nc.ui.pubapp.uif2app.components.grand.action.ExpendShrinkGrandListAction) context.get("expendShrinkGrandListAction");
		nc.ui.pubapp.uif2app.components.grand.action.ExpendShrinkGrandListAction bean = new nc.ui.pubapp.uif2app.components.grand.action.ExpendShrinkGrandListAction();
		context.put("expendShrinkGrandListAction", bean);
		bean.setMainGrandModel(getMainGrandModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.components.grand.action.ExpendShrinkGrandCardAction getExpendShrinkGrandCardAction() {
		if (context.get("expendShrinkGrandCardAction") != null)
			return (nc.ui.pubapp.uif2app.components.grand.action.ExpendShrinkGrandCardAction) context.get("expendShrinkGrandCardAction");
		nc.ui.pubapp.uif2app.components.grand.action.ExpendShrinkGrandCardAction bean = new nc.ui.pubapp.uif2app.components.grand.action.ExpendShrinkGrandCardAction();
		context.put("expendShrinkGrandCardAction", bean);
		bean.setMainGrandModel(getMainGrandModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.components.grand.ListGrandPanelComposite getMainGrandlistView() {
		if (context.get("MainGrandlistView") != null)
			return (nc.ui.pubapp.uif2app.components.grand.ListGrandPanelComposite) context.get("MainGrandlistView");
		nc.ui.pubapp.uif2app.components.grand.ListGrandPanelComposite bean = new nc.ui.pubapp.uif2app.components.grand.ListGrandPanelComposite();
		context.put("MainGrandlistView", bean);
		bean.setModel(getMainGrandModel());
		bean.setMaingrandrelationship(getMainGrandRelationShip());
		bean.setMediator(getMainGrandMediator());
		bean.setMainPanel(getListView());
		bean.setExpendShrinkGrandListAction(getExpendShrinkGrandListAction());
		bean.setGrandString("孙表信息");
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.components.grand.CardGrandPanelComposite getMainGrandbillFormEditor() {
		if (context.get("MainGrandbillFormEditor") != null)
			return (nc.ui.pubapp.uif2app.components.grand.CardGrandPanelComposite) context.get("MainGrandbillFormEditor");
		nc.ui.pubapp.uif2app.components.grand.CardGrandPanelComposite bean = new nc.ui.pubapp.uif2app.components.grand.CardGrandPanelComposite();
		context.put("MainGrandbillFormEditor", bean);
		bean.setMainPanel(getBillFormEditor());
		bean.setModel(getMainGrandModel());
		bean.setMaingrandrelationship(getMainGrandRelationShip());
		bean.setMainGrandBlankFilter(getMainGrandBlankFilter());
		bean.setMediator(getMainGrandMediator());
		bean.setExpendShrinkGrandCardAction(getExpendShrinkGrandCardAction());
		bean.setGrandString("孙表信息");
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.components.grand.MainGrandBlankFilter getMainGrandBlankFilter() {
		if (context.get("mainGrandBlankFilter") != null)
			return (nc.ui.pubapp.uif2app.components.grand.MainGrandBlankFilter) context.get("mainGrandBlankFilter");
		nc.ui.pubapp.uif2app.components.grand.MainGrandBlankFilter bean = new nc.ui.pubapp.uif2app.components.grand.MainGrandBlankFilter();
		context.put("mainGrandBlankFilter", bean);
		bean.setChildFilterMap(getManagedMap4());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private Map getManagedMap4() {
		Map map = new HashMap();
		map.put("pk_task_b", getManagedList0());
		return map;
	}

	private List getManagedList0() {
		List list = new ArrayList();
		list.add("rowno");
		return list;
	}

	public nc.ui.pubapp.uif2app.components.grand.mediator.MainGrandMediator getMainGrandMediator() {
		if (context.get("mainGrandMediator") != null)
			return (nc.ui.pubapp.uif2app.components.grand.mediator.MainGrandMediator) context.get("mainGrandMediator");
		nc.ui.pubapp.uif2app.components.grand.mediator.MainGrandMediator bean = new nc.ui.pubapp.uif2app.components.grand.mediator.MainGrandMediator();
		context.put("mainGrandMediator", bean);
		bean.setMainBillForm(getBillFormEditor());
		bean.setMainBillListView(getListView());
		bean.setMainGrandModel(getMainGrandModel());
		bean.setMainGrandRelationShip(getMainGrandRelationShip());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.view.ShowUpableBillListView getSunlistView1() {
		if (context.get("sunlistView1") != null)
			return (nc.ui.pubapp.uif2app.view.ShowUpableBillListView) context.get("sunlistView1");
		nc.ui.pubapp.uif2app.view.ShowUpableBillListView bean = new nc.ui.pubapp.uif2app.view.ShowUpableBillListView();
		context.put("sunlistView1", bean);
		bean.setModel(getManageAppModel2());
		bean.setTemplateContainer(getTemplateContainer());
		bean.setNodekey("sunparas1");
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.editor.value.BillCardPanelMetaDataValueAdapter getComponentValueManager() {
		if (context.get("componentValueManager") != null)
			return (nc.ui.uif2.editor.value.BillCardPanelMetaDataValueAdapter) context.get("componentValueManager");
		nc.ui.uif2.editor.value.BillCardPanelMetaDataValueAdapter bean = new nc.ui.uif2.editor.value.BillCardPanelMetaDataValueAdapter();
		context.put("componentValueManager", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.view.ShowUpableBillForm getSunbillFormEditor1() {
		if (context.get("sunbillFormEditor1") != null)
			return (nc.ui.pubapp.uif2app.view.ShowUpableBillForm) context.get("sunbillFormEditor1");
		nc.ui.pubapp.uif2app.view.ShowUpableBillForm bean = new nc.ui.pubapp.uif2app.view.ShowUpableBillForm();
		context.put("sunbillFormEditor1", bean);
		bean.setModel(getManageAppModel2());
		bean.setComponentValueManager(getComponentValueManager());
		bean.setTemplateContainer(getTemplateContainer());
		bean.setNodekey("sunparas");
		bean.setShowOrgPanel(false);
		bean.setAutoAddLine(false);
		bean.setBodyLineActions(getManagedList1());
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList1() {
		List list = new ArrayList();
		list.add(getChildAddLineAction_8db6db());
		list.add(getChildInsertLineAction_1dfeb53());
		list.add(getBodyDelLineAction_1d2d4a7());
		list.add(getBodyCopyLineAction_874f09());
		list.add(getBodyPasteToTailAction_156108f());
		list.add(getBodyLineEditAction_1595877());
		return list;
	}

	private nc.ui.qcco.task.action.ChildAddLineAction getChildAddLineAction_8db6db() {
		if (context.get("nc.ui.qcco.task.action.ChildAddLineAction#8db6db") != null)
			return (nc.ui.qcco.task.action.ChildAddLineAction) context.get("nc.ui.qcco.task.action.ChildAddLineAction#8db6db");
		nc.ui.qcco.task.action.ChildAddLineAction bean = new nc.ui.qcco.task.action.ChildAddLineAction(getBillFormEditor());
		context.put("nc.ui.qcco.task.action.ChildAddLineAction#8db6db", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.qcco.task.action.ChildInsertLineAction getChildInsertLineAction_1dfeb53() {
		if (context.get("nc.ui.qcco.task.action.ChildInsertLineAction#1dfeb53") != null)
			return (nc.ui.qcco.task.action.ChildInsertLineAction) context.get("nc.ui.qcco.task.action.ChildInsertLineAction#1dfeb53");
		nc.ui.qcco.task.action.ChildInsertLineAction bean = new nc.ui.qcco.task.action.ChildInsertLineAction(getBillFormEditor());
		context.put("nc.ui.qcco.task.action.ChildInsertLineAction#1dfeb53", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.actions.BodyDelLineAction getBodyDelLineAction_1d2d4a7() {
		if (context.get("nc.ui.pubapp.uif2app.actions.BodyDelLineAction#1d2d4a7") != null)
			return (nc.ui.pubapp.uif2app.actions.BodyDelLineAction) context.get("nc.ui.pubapp.uif2app.actions.BodyDelLineAction#1d2d4a7");
		nc.ui.pubapp.uif2app.actions.BodyDelLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyDelLineAction();
		context.put("nc.ui.pubapp.uif2app.actions.BodyDelLineAction#1d2d4a7", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.actions.BodyCopyLineAction getBodyCopyLineAction_874f09() {
		if (context.get("nc.ui.pubapp.uif2app.actions.BodyCopyLineAction#874f09") != null)
			return (nc.ui.pubapp.uif2app.actions.BodyCopyLineAction) context.get("nc.ui.pubapp.uif2app.actions.BodyCopyLineAction#874f09");
		nc.ui.pubapp.uif2app.actions.BodyCopyLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyCopyLineAction();
		context.put("nc.ui.pubapp.uif2app.actions.BodyCopyLineAction#874f09", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction getBodyPasteToTailAction_156108f() {
		if (context.get("nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction#156108f") != null)
			return (nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction) context
					.get("nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction#156108f");
		nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction bean = new nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction();
		context.put("nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction#156108f", bean);
		bean.setClearItems(getManagedList2());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList2() {
		List list = new ArrayList();
		list.add("pk_task_r");
		list.add("dr");
		return list;
	}

	private nc.ui.pubapp.uif2app.actions.BodyLineEditAction getBodyLineEditAction_1595877() {
		if (context.get("nc.ui.pubapp.uif2app.actions.BodyLineEditAction#1595877") != null)
			return (nc.ui.pubapp.uif2app.actions.BodyLineEditAction) context.get("nc.ui.pubapp.uif2app.actions.BodyLineEditAction#1595877");
		nc.ui.pubapp.uif2app.actions.BodyLineEditAction bean = new nc.ui.pubapp.uif2app.actions.BodyLineEditAction();
		context.put("nc.ui.pubapp.uif2app.actions.BodyLineEditAction#1595877", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.query2.model.ModelDataManager getModelDataManager() {
		if (context.get("modelDataManager") != null)
			return (nc.ui.pubapp.uif2app.query2.model.ModelDataManager) context.get("modelDataManager");
		nc.ui.pubapp.uif2app.query2.model.ModelDataManager bean = new nc.ui.pubapp.uif2app.query2.model.ModelDataManager();
		context.put("modelDataManager", bean);
		bean.setModel(getManageAppModel());
		bean.setService(getBmModelModelService());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.view.TemplateContainer getTemplateContainer() {
		if (context.get("templateContainer") != null)
			return (nc.ui.pubapp.uif2app.view.TemplateContainer) context.get("templateContainer");
		nc.ui.pubapp.uif2app.view.TemplateContainer bean = new nc.ui.pubapp.uif2app.view.TemplateContainer();
		context.put("templateContainer", bean);
		bean.setContext(getContext());
		bean.setNodeKeies(getManagedList3());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList3() {
		List list = new ArrayList();
		list.add("bt");
		list.add("sunparas");
		return list;
	}

	public nc.ui.uif2.editor.QueryTemplateContainer getQueryTemplateContainer() {
		if (context.get("queryTemplateContainer") != null)
			return (nc.ui.uif2.editor.QueryTemplateContainer) context.get("queryTemplateContainer");
		nc.ui.uif2.editor.QueryTemplateContainer bean = new nc.ui.uif2.editor.QueryTemplateContainer();
		context.put("queryTemplateContainer", bean);
		bean.setContext(getContext());
		bean.setNodeKey("qt");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.view.ShowUpableBillListView getListView() {
		if (context.get("listView") != null)
			return (nc.ui.pubapp.uif2app.view.ShowUpableBillListView) context.get("listView");
		nc.ui.pubapp.uif2app.view.ShowUpableBillListView bean = new nc.ui.pubapp.uif2app.view.ShowUpableBillListView();
		context.put("listView", bean);
		bean.setModel(getManageAppModel());
		bean.setTemplateContainer(getTemplateContainer());
		bean.setNodekey("bt");
		bean.setUserdefitemListPreparator(getCompositeBillListDataPrepare_bf209c());
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare getCompositeBillListDataPrepare_bf209c() {
		if (context.get("nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare#bf209c") != null)
			return (nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare) context
					.get("nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare#bf209c");
		nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare bean = new nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare();
		context.put("nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare#bf209c", bean);
		bean.setBillListDataPrepares(getManagedList4());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList4() {
		List list = new ArrayList();
		list.add(getUserdefitemlistPreparator());
		list.add(getMarAsstPreparator());
		return list;
	}

	public nc.ui.uif2.editor.UserdefitemContainerListPreparator getUserdefitemlistPreparator() {
		if (context.get("userdefitemlistPreparator") != null)
			return (nc.ui.uif2.editor.UserdefitemContainerListPreparator) context.get("userdefitemlistPreparator");
		nc.ui.uif2.editor.UserdefitemContainerListPreparator bean = new nc.ui.uif2.editor.UserdefitemContainerListPreparator();
		context.put("userdefitemlistPreparator", bean);
		bean.setContainer(getUserdefitemContainer());
		bean.setParams(getManagedList5());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList5() {
		List list = new ArrayList();
		list.add(getUserdefQueryParam_1f60921());
		list.add(getUserdefQueryParam_1d161d1());
		return list;
	}

	private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_1f60921() {
		if (context.get("nc.ui.uif2.editor.UserdefQueryParam#1f60921") != null)
			return (nc.ui.uif2.editor.UserdefQueryParam) context.get("nc.ui.uif2.editor.UserdefQueryParam#1f60921");
		nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
		context.put("nc.ui.uif2.editor.UserdefQueryParam#1f60921", bean);
		bean.setMdfullname("qcco.task");
		bean.setPos(0);
		bean.setPrefix("vdef");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_1d161d1() {
		if (context.get("nc.ui.uif2.editor.UserdefQueryParam#1d161d1") != null)
			return (nc.ui.uif2.editor.UserdefQueryParam) context.get("nc.ui.uif2.editor.UserdefQueryParam#1d161d1");
		nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
		context.put("nc.ui.uif2.editor.UserdefQueryParam#1d161d1", bean);
		bean.setMdfullname("qcco.TaskBVO");
		bean.setPos(1);
		bean.setPrefix("vbdef");
		bean.setTabcode("TaskBVO");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.view.ShowUpableBillForm getBillFormEditor() {
		if (context.get("billFormEditor") != null)
			return (nc.ui.pubapp.uif2app.view.ShowUpableBillForm) context.get("billFormEditor");
		nc.ui.pubapp.uif2app.view.ShowUpableBillForm bean = new nc.ui.pubapp.uif2app.view.ShowUpableBillForm();
		context.put("billFormEditor", bean);
		bean.setModel(getManageAppModel());
		bean.setTemplateContainer(getTemplateContainer());
		bean.setNodekey("bt");
		bean.setShowOrgPanel(true);
		bean.setAutoAddLine(false);
		bean.setBodyLineActions(getManagedList6());
		bean.setUserdefitemPreparator(getCompositeBillDataPrepare_16d949f());
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList6() {
		List list = new ArrayList();
		list.add(getTaskBodyAddLineAction_16ced95());
		list.add(getTaskBodyDelLineAction_61d1ee());
		list.add(getBodyCopyLineAction_1d6bbc());
		list.add(getTaskBodyPasteToTailAction_18c3d25());
		list.add(getBodyLineEditAction_25644());
		return list;
	}

	private nc.ui.qcco.task.action.TaskBodyAddLineAction getTaskBodyAddLineAction_16ced95() {
		if (context.get("nc.ui.qcco.task.action.TaskBodyAddLineAction#16ced95") != null)
			return (nc.ui.qcco.task.action.TaskBodyAddLineAction) context.get("nc.ui.qcco.task.action.TaskBodyAddLineAction#16ced95");
		nc.ui.qcco.task.action.TaskBodyAddLineAction bean = new nc.ui.qcco.task.action.TaskBodyAddLineAction();
		context.put("nc.ui.qcco.task.action.TaskBodyAddLineAction#16ced95", bean);
		bean.setMainBillForm(getBillFormEditor());
		bean.setGrandCard(getSunbillFormEditor1());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.qcco.task.action.TaskBodyDelLineAction getTaskBodyDelLineAction_61d1ee() {
		if (context.get("nc.ui.qcco.task.action.TaskBodyDelLineAction#61d1ee") != null)
			return (nc.ui.qcco.task.action.TaskBodyDelLineAction) context.get("nc.ui.qcco.task.action.TaskBodyDelLineAction#61d1ee");
		nc.ui.qcco.task.action.TaskBodyDelLineAction bean = new nc.ui.qcco.task.action.TaskBodyDelLineAction(getBillFormEditor(),
				getSunbillFormEditor1());
		context.put("nc.ui.qcco.task.action.TaskBodyDelLineAction#61d1ee", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.actions.BodyCopyLineAction getBodyCopyLineAction_1d6bbc() {
		if (context.get("nc.ui.pubapp.uif2app.actions.BodyCopyLineAction#1d6bbc") != null)
			return (nc.ui.pubapp.uif2app.actions.BodyCopyLineAction) context.get("nc.ui.pubapp.uif2app.actions.BodyCopyLineAction#1d6bbc");
		nc.ui.pubapp.uif2app.actions.BodyCopyLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyCopyLineAction();
		context.put("nc.ui.pubapp.uif2app.actions.BodyCopyLineAction#1d6bbc", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.qcco.task.action.TaskBodyPasteToTailAction getTaskBodyPasteToTailAction_18c3d25() {
		if (context.get("nc.ui.qcco.task.action.TaskBodyPasteToTailAction#18c3d25") != null)
			return (nc.ui.qcco.task.action.TaskBodyPasteToTailAction) context
					.get("nc.ui.qcco.task.action.TaskBodyPasteToTailAction#18c3d25");
		nc.ui.qcco.task.action.TaskBodyPasteToTailAction bean = new nc.ui.qcco.task.action.TaskBodyPasteToTailAction();
		context.put("nc.ui.qcco.task.action.TaskBodyPasteToTailAction#18c3d25", bean);
		bean.setClearItems(getManagedList7());
		bean.setGrandCard(getSunbillFormEditor1());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList7() {
		List list = new ArrayList();
		list.add("pk_task_b");
		list.add("rowno");
		return list;
	}

	private nc.ui.pubapp.uif2app.actions.BodyLineEditAction getBodyLineEditAction_25644() {
		if (context.get("nc.ui.pubapp.uif2app.actions.BodyLineEditAction#25644") != null)
			return (nc.ui.pubapp.uif2app.actions.BodyLineEditAction) context.get("nc.ui.pubapp.uif2app.actions.BodyLineEditAction#25644");
		nc.ui.pubapp.uif2app.actions.BodyLineEditAction bean = new nc.ui.pubapp.uif2app.actions.BodyLineEditAction();
		context.put("nc.ui.pubapp.uif2app.actions.BodyLineEditAction#25644", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare getCompositeBillDataPrepare_16d949f() {
		if (context.get("nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare#16d949f") != null)
			return (nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare) context
					.get("nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare#16d949f");
		nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare bean = new nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare();
		context.put("nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare#16d949f", bean);
		bean.setBillDataPrepares(getManagedList8());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList8() {
		List list = new ArrayList();
		list.add(getUserdefitemPreparator());
		list.add(getMarAsstPreparator());
		return list;
	}

	public nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator getMarAsstPreparator() {
		if (context.get("marAsstPreparator") != null)
			return (nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator) context.get("marAsstPreparator");
		nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator bean = new nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator();
		context.put("marAsstPreparator", bean);
		bean.setModel(getManageAppModel());
		bean.setContainer(getUserdefitemContainer());
		bean.setPrefix("vfree");
		bean.setMaterialField("pk_material");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.userdefitem.UserDefItemContainer getUserdefitemContainer() {
		if (context.get("userdefitemContainer") != null)
			return (nc.ui.uif2.userdefitem.UserDefItemContainer) context.get("userdefitemContainer");
		nc.ui.uif2.userdefitem.UserDefItemContainer bean = new nc.ui.uif2.userdefitem.UserDefItemContainer();
		context.put("userdefitemContainer", bean);
		bean.setContext(getContext());
		bean.setParams(getManagedList9());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList9() {
		List list = new ArrayList();
		list.add(getQueryParam_13865eb());
		list.add(getQueryParam_1ab46b3());
		list.add(getQueryParam_1aeb373());
		return list;
	}

	private nc.ui.uif2.userdefitem.QueryParam getQueryParam_13865eb() {
		if (context.get("nc.ui.uif2.userdefitem.QueryParam#13865eb") != null)
			return (nc.ui.uif2.userdefitem.QueryParam) context.get("nc.ui.uif2.userdefitem.QueryParam#13865eb");
		nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
		context.put("nc.ui.uif2.userdefitem.QueryParam#13865eb", bean);
		bean.setMdfullname("qcco.task");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.userdefitem.QueryParam getQueryParam_1ab46b3() {
		if (context.get("nc.ui.uif2.userdefitem.QueryParam#1ab46b3") != null)
			return (nc.ui.uif2.userdefitem.QueryParam) context.get("nc.ui.uif2.userdefitem.QueryParam#1ab46b3");
		nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
		context.put("nc.ui.uif2.userdefitem.QueryParam#1ab46b3", bean);
		bean.setMdfullname("qcco.TaskBVO");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.userdefitem.QueryParam getQueryParam_1aeb373() {
		if (context.get("nc.ui.uif2.userdefitem.QueryParam#1aeb373") != null)
			return (nc.ui.uif2.userdefitem.QueryParam) context.get("nc.ui.uif2.userdefitem.QueryParam#1aeb373");
		nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
		context.put("nc.ui.uif2.userdefitem.QueryParam#1aeb373", bean);
		bean.setRulecode("materialassistant");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.editor.UserdefitemContainerPreparator getUserdefitemPreparator() {
		if (context.get("userdefitemPreparator") != null)
			return (nc.ui.uif2.editor.UserdefitemContainerPreparator) context.get("userdefitemPreparator");
		nc.ui.uif2.editor.UserdefitemContainerPreparator bean = new nc.ui.uif2.editor.UserdefitemContainerPreparator();
		context.put("userdefitemPreparator", bean);
		bean.setContainer(getUserdefitemContainer());
		bean.setParams(getManagedList10());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList10() {
		List list = new ArrayList();
		list.add(getUserdefQueryParam_876df0());
		list.add(getUserdefQueryParam_142e24e());
		return list;
	}

	private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_876df0() {
		if (context.get("nc.ui.uif2.editor.UserdefQueryParam#876df0") != null)
			return (nc.ui.uif2.editor.UserdefQueryParam) context.get("nc.ui.uif2.editor.UserdefQueryParam#876df0");
		nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
		context.put("nc.ui.uif2.editor.UserdefQueryParam#876df0", bean);
		bean.setMdfullname("qcco.task");
		bean.setPos(0);
		bean.setPrefix("vdef");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_142e24e() {
		if (context.get("nc.ui.uif2.editor.UserdefQueryParam#142e24e") != null)
			return (nc.ui.uif2.editor.UserdefQueryParam) context.get("nc.ui.uif2.editor.UserdefQueryParam#142e24e");
		nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
		context.put("nc.ui.uif2.editor.UserdefQueryParam#142e24e", bean);
		bean.setMdfullname("task.TaskBVO");
		bean.setPos(1);
		bean.setPrefix("vbdef");
		bean.setTabcode("TaskBVO");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell getQueryArea() {
		if (context.get("queryArea") != null)
			return (nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell) context.get("queryArea");
		nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell bean = new nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell();
		context.put("queryArea", bean);
		bean.setQueryAreaCreator(getQueryAction());
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel getQueryInfo() {
		if (context.get("queryInfo") != null)
			return (nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel) context.get("queryInfo");
		nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel bean = new nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel();
		context.put("queryInfo", bean);
		bean.setModel(getManageAppModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel getCardInfoPnl() {
		if (context.get("cardInfoPnl") != null)
			return (nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel) context.get("cardInfoPnl");
		nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel bean = new nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel();
		context.put("cardInfoPnl", bean);
		bean.setTitleAction(getReturnaction());
		bean.setModel(getManageAppModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.actions.UEReturnAction getReturnaction() {
		if (context.get("returnaction") != null)
			return (nc.ui.pubapp.uif2app.actions.UEReturnAction) context.get("returnaction");
		nc.ui.pubapp.uif2app.actions.UEReturnAction bean = new nc.ui.pubapp.uif2app.actions.UEReturnAction();
		context.put("returnaction", bean);
		bean.setGoComponent(getMainGrandlistView());
		bean.setSaveAction(getSaveAction());
		bean.setModel(getManageAppModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.TangramContainer getContainer() {
		if (context.get("container") != null)
			return (nc.ui.uif2.TangramContainer) context.get("container");
		nc.ui.uif2.TangramContainer bean = new nc.ui.uif2.TangramContainer();
		context.put("container", bean);
		bean.setModel(getManageAppModel());
		bean.setTangramLayoutRoot(getTBNode_78be8e());
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.TBNode getTBNode_78be8e() {
		if (context.get("nc.ui.uif2.tangramlayout.node.TBNode#78be8e") != null)
			return (nc.ui.uif2.tangramlayout.node.TBNode) context.get("nc.ui.uif2.tangramlayout.node.TBNode#78be8e");
		nc.ui.uif2.tangramlayout.node.TBNode bean = new nc.ui.uif2.tangramlayout.node.TBNode();
		context.put("nc.ui.uif2.tangramlayout.node.TBNode#78be8e", bean);
		bean.setShowMode("CardLayout");
		bean.setTabs(getManagedList11());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList11() {
		List list = new ArrayList();
		list.add(getHSNode_5334c1());
		list.add(getVSNode_3bd665());
		return list;
	}

	private nc.ui.uif2.tangramlayout.node.HSNode getHSNode_5334c1() {
		if (context.get("nc.ui.uif2.tangramlayout.node.HSNode#5334c1") != null)
			return (nc.ui.uif2.tangramlayout.node.HSNode) context.get("nc.ui.uif2.tangramlayout.node.HSNode#5334c1");
		nc.ui.uif2.tangramlayout.node.HSNode bean = new nc.ui.uif2.tangramlayout.node.HSNode();
		context.put("nc.ui.uif2.tangramlayout.node.HSNode#5334c1", bean);
		bean.setLeft(getCNode_177bd6());
		bean.setRight(getVSNode_2005a7());
		bean.setDividerLocation(210f);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.CNode getCNode_177bd6() {
		if (context.get("nc.ui.uif2.tangramlayout.node.CNode#177bd6") != null)
			return (nc.ui.uif2.tangramlayout.node.CNode) context.get("nc.ui.uif2.tangramlayout.node.CNode#177bd6");
		nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
		context.put("nc.ui.uif2.tangramlayout.node.CNode#177bd6", bean);
		bean.setComponent(getQueryArea());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.VSNode getVSNode_2005a7() {
		if (context.get("nc.ui.uif2.tangramlayout.node.VSNode#2005a7") != null)
			return (nc.ui.uif2.tangramlayout.node.VSNode) context.get("nc.ui.uif2.tangramlayout.node.VSNode#2005a7");
		nc.ui.uif2.tangramlayout.node.VSNode bean = new nc.ui.uif2.tangramlayout.node.VSNode();
		context.put("nc.ui.uif2.tangramlayout.node.VSNode#2005a7", bean);
		bean.setUp(getCNode_1c35b2b());
		bean.setDown(getCNode_50d252());
		bean.setDividerLocation(25f);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.CNode getCNode_1c35b2b() {
		if (context.get("nc.ui.uif2.tangramlayout.node.CNode#1c35b2b") != null)
			return (nc.ui.uif2.tangramlayout.node.CNode) context.get("nc.ui.uif2.tangramlayout.node.CNode#1c35b2b");
		nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
		context.put("nc.ui.uif2.tangramlayout.node.CNode#1c35b2b", bean);
		bean.setComponent(getQueryInfo());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.CNode getCNode_50d252() {
		if (context.get("nc.ui.uif2.tangramlayout.node.CNode#50d252") != null)
			return (nc.ui.uif2.tangramlayout.node.CNode) context.get("nc.ui.uif2.tangramlayout.node.CNode#50d252");
		nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
		context.put("nc.ui.uif2.tangramlayout.node.CNode#50d252", bean);
		bean.setName(getI18nFB_96a594());
		bean.setComponent(getMainGrandlistView());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_96a594() {
		if (context.get("nc.ui.uif2.I18nFB#96a594") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#96a594");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#96a594", bean);
		bean.setResDir("common");
		bean.setResId("UC001-0000107");
		bean.setDefaultValue("列表");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#96a594", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private nc.ui.uif2.tangramlayout.node.VSNode getVSNode_3bd665() {
		if (context.get("nc.ui.uif2.tangramlayout.node.VSNode#3bd665") != null)
			return (nc.ui.uif2.tangramlayout.node.VSNode) context.get("nc.ui.uif2.tangramlayout.node.VSNode#3bd665");
		nc.ui.uif2.tangramlayout.node.VSNode bean = new nc.ui.uif2.tangramlayout.node.VSNode();
		context.put("nc.ui.uif2.tangramlayout.node.VSNode#3bd665", bean);
		bean.setUp(getCNode_ac7139());
		bean.setDown(getCNode_1407ac3());
		bean.setDividerLocation(30f);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.CNode getCNode_ac7139() {
		if (context.get("nc.ui.uif2.tangramlayout.node.CNode#ac7139") != null)
			return (nc.ui.uif2.tangramlayout.node.CNode) context.get("nc.ui.uif2.tangramlayout.node.CNode#ac7139");
		nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
		context.put("nc.ui.uif2.tangramlayout.node.CNode#ac7139", bean);
		bean.setComponent(getCardInfoPnl());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.CNode getCNode_1407ac3() {
		if (context.get("nc.ui.uif2.tangramlayout.node.CNode#1407ac3") != null)
			return (nc.ui.uif2.tangramlayout.node.CNode) context.get("nc.ui.uif2.tangramlayout.node.CNode#1407ac3");
		nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
		context.put("nc.ui.uif2.tangramlayout.node.CNode#1407ac3", bean);
		bean.setName(getI18nFB_13d6b12());
		bean.setComponent(getMainGrandbillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_13d6b12() {
		if (context.get("nc.ui.uif2.I18nFB#13d6b12") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#13d6b12");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#13d6b12", bean);
		bean.setResDir("common");
		bean.setResId("UC001-0000106");
		bean.setDefaultValue("卡片");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#13d6b12", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public nc.ui.pubapp.uif2app.event.ChildrenPicky getChildrenPicky() {
		if (context.get("childrenPicky") != null)
			return (nc.ui.pubapp.uif2app.event.ChildrenPicky) context.get("childrenPicky");
		nc.ui.pubapp.uif2app.event.ChildrenPicky bean = new nc.ui.pubapp.uif2app.event.ChildrenPicky();
		context.put("childrenPicky", bean);
		bean.setBillform(getBillFormEditor());
		bean.setBodyVoClasses(getManagedList12());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList12() {
		List list = new ArrayList();
		list.add("nc.vo.qcco.task.TaskBVO");
		return list;
	}

	public nc.ui.pubapp.uif2app.model.AppEventHandlerMediator getGrandModelEventMediator() {
		if (context.get("grandModelEventMediator") != null)
			return (nc.ui.pubapp.uif2app.model.AppEventHandlerMediator) context.get("grandModelEventMediator");
		nc.ui.pubapp.uif2app.model.AppEventHandlerMediator bean = new nc.ui.pubapp.uif2app.model.AppEventHandlerMediator();
		context.put("grandModelEventMediator", bean);
		bean.setModel(getManageAppModel2());
		bean.setHandlerGroup(getManagedList13());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList13() {
		List list = new ArrayList();
		list.add(getEventHandlerGroup_1d10052());
		list.add(getEventHandlerGroup_88f77());
		return list;
	}

	private nc.ui.pubapp.uif2app.event.EventHandlerGroup getEventHandlerGroup_1d10052() {
		if (context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#1d10052") != null)
			return (nc.ui.pubapp.uif2app.event.EventHandlerGroup) context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#1d10052");
		nc.ui.pubapp.uif2app.event.EventHandlerGroup bean = new nc.ui.pubapp.uif2app.event.EventHandlerGroup();
		context.put("nc.ui.pubapp.uif2app.event.EventHandlerGroup#1d10052", bean);
		bean.setEvent("nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent");
		bean.setHandler(getGrandBodyBeforeEditHandler_1266d49());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.qcco.task.ace.handler.GrandBodyBeforeEditHandler getGrandBodyBeforeEditHandler_1266d49() {
		if (context.get("nc.ui.qcco.task.ace.handler.GrandBodyBeforeEditHandler#1266d49") != null)
			return (nc.ui.qcco.task.ace.handler.GrandBodyBeforeEditHandler) context
					.get("nc.ui.qcco.task.ace.handler.GrandBodyBeforeEditHandler#1266d49");
		nc.ui.qcco.task.ace.handler.GrandBodyBeforeEditHandler bean = new nc.ui.qcco.task.ace.handler.GrandBodyBeforeEditHandler();
		context.put("nc.ui.qcco.task.ace.handler.GrandBodyBeforeEditHandler#1266d49", bean);
		bean.setMainBillForm(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.event.EventHandlerGroup getEventHandlerGroup_88f77() {
		if (context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#88f77") != null)
			return (nc.ui.pubapp.uif2app.event.EventHandlerGroup) context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#88f77");
		nc.ui.pubapp.uif2app.event.EventHandlerGroup bean = new nc.ui.pubapp.uif2app.event.EventHandlerGroup();
		context.put("nc.ui.pubapp.uif2app.event.EventHandlerGroup#88f77", bean);
		bean.setEvent("nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent");
		bean.setHandler(getGrandBodyAfterEditHandler_6c15c2());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.qcco.task.ace.handler.GrandBodyAfterEditHandler getGrandBodyAfterEditHandler_6c15c2() {
		if (context.get("nc.ui.qcco.task.ace.handler.GrandBodyAfterEditHandler#6c15c2") != null)
			return (nc.ui.qcco.task.ace.handler.GrandBodyAfterEditHandler) context
					.get("nc.ui.qcco.task.ace.handler.GrandBodyAfterEditHandler#6c15c2");
		nc.ui.qcco.task.ace.handler.GrandBodyAfterEditHandler bean = new nc.ui.qcco.task.ace.handler.GrandBodyAfterEditHandler();
		context.put("nc.ui.qcco.task.ace.handler.GrandBodyAfterEditHandler#6c15c2", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.model.AppEventHandlerMediator getEventMediator() {
		if (context.get("eventMediator") != null)
			return (nc.ui.pubapp.uif2app.model.AppEventHandlerMediator) context.get("eventMediator");
		nc.ui.pubapp.uif2app.model.AppEventHandlerMediator bean = new nc.ui.pubapp.uif2app.model.AppEventHandlerMediator();
		context.put("eventMediator", bean);
		bean.setModel(getManageAppModel());
		bean.setHandlerGroup(getManagedList14());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList14() {
		List list = new ArrayList();
		list.add(getEventHandlerGroup_109f1c8());
		list.add(getEventHandlerGroup_1914979());
		list.add(getEventHandlerGroup_9d65a9());
		list.add(getEventHandlerGroup_6653ae());
		list.add(getEventHandlerGroup_a316f9());
		list.add(getEventHandlerGroup_1569e87());
		return list;
	}

	private nc.ui.pubapp.uif2app.event.EventHandlerGroup getEventHandlerGroup_109f1c8() {
		if (context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#109f1c8") != null)
			return (nc.ui.pubapp.uif2app.event.EventHandlerGroup) context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#109f1c8");
		nc.ui.pubapp.uif2app.event.EventHandlerGroup bean = new nc.ui.pubapp.uif2app.event.EventHandlerGroup();
		context.put("nc.ui.pubapp.uif2app.event.EventHandlerGroup#109f1c8", bean);
		bean.setEvent("nc.ui.pubapp.uif2app.event.billform.AddEvent");
		bean.setHandler(getAceAddHandler_3081e());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.qcco.task.ace.handler.AceAddHandler getAceAddHandler_3081e() {
		if (context.get("nc.ui.qcco.task.ace.handler.AceAddHandler#3081e") != null)
			return (nc.ui.qcco.task.ace.handler.AceAddHandler) context.get("nc.ui.qcco.task.ace.handler.AceAddHandler#3081e");
		nc.ui.qcco.task.ace.handler.AceAddHandler bean = new nc.ui.qcco.task.ace.handler.AceAddHandler();
		context.put("nc.ui.qcco.task.ace.handler.AceAddHandler#3081e", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.event.EventHandlerGroup getEventHandlerGroup_1914979() {
		if (context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#1914979") != null)
			return (nc.ui.pubapp.uif2app.event.EventHandlerGroup) context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#1914979");
		nc.ui.pubapp.uif2app.event.EventHandlerGroup bean = new nc.ui.pubapp.uif2app.event.EventHandlerGroup();
		context.put("nc.ui.pubapp.uif2app.event.EventHandlerGroup#1914979", bean);
		bean.setEvent("nc.ui.pubapp.uif2app.event.OrgChangedEvent");
		bean.setHandler(getAceOrgChangeHandler_1e09db2());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.qcco.task.ace.handler.AceOrgChangeHandler getAceOrgChangeHandler_1e09db2() {
		if (context.get("nc.ui.qcco.task.ace.handler.AceOrgChangeHandler#1e09db2") != null)
			return (nc.ui.qcco.task.ace.handler.AceOrgChangeHandler) context.get("nc.ui.qcco.task.ace.handler.AceOrgChangeHandler#1e09db2");
		nc.ui.qcco.task.ace.handler.AceOrgChangeHandler bean = new nc.ui.qcco.task.ace.handler.AceOrgChangeHandler();
		context.put("nc.ui.qcco.task.ace.handler.AceOrgChangeHandler#1e09db2", bean);
		bean.setBillForm(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.event.EventHandlerGroup getEventHandlerGroup_9d65a9() {
		if (context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#9d65a9") != null)
			return (nc.ui.pubapp.uif2app.event.EventHandlerGroup) context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#9d65a9");
		nc.ui.pubapp.uif2app.event.EventHandlerGroup bean = new nc.ui.pubapp.uif2app.event.EventHandlerGroup();
		context.put("nc.ui.pubapp.uif2app.event.EventHandlerGroup#9d65a9", bean);
		bean.setEvent("nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent");
		bean.setHandler(getAceBodyAfterEditHandler_16ec268());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.qcco.task.ace.handler.AceBodyAfterEditHandler getAceBodyAfterEditHandler_16ec268() {
		if (context.get("nc.ui.qcco.task.ace.handler.AceBodyAfterEditHandler#16ec268") != null)
			return (nc.ui.qcco.task.ace.handler.AceBodyAfterEditHandler) context
					.get("nc.ui.qcco.task.ace.handler.AceBodyAfterEditHandler#16ec268");
		nc.ui.qcco.task.ace.handler.AceBodyAfterEditHandler bean = new nc.ui.qcco.task.ace.handler.AceBodyAfterEditHandler();
		context.put("nc.ui.qcco.task.ace.handler.AceBodyAfterEditHandler#16ec268", bean);
		bean.setMainBillForm(getBillFormEditor());
		bean.setGrandCard(getSunbillFormEditor1());
		bean.setBillForm(getMainGrandbillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.event.EventHandlerGroup getEventHandlerGroup_6653ae() {
		if (context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#6653ae") != null)
			return (nc.ui.pubapp.uif2app.event.EventHandlerGroup) context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#6653ae");
		nc.ui.pubapp.uif2app.event.EventHandlerGroup bean = new nc.ui.pubapp.uif2app.event.EventHandlerGroup();
		context.put("nc.ui.pubapp.uif2app.event.EventHandlerGroup#6653ae", bean);
		bean.setEvent("nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent");
		bean.setHandler(getAceBodyBeforeEditHandler_17d68b9());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.qcco.task.ace.handler.AceBodyBeforeEditHandler getAceBodyBeforeEditHandler_17d68b9() {
		if (context.get("nc.ui.qcco.task.ace.handler.AceBodyBeforeEditHandler#17d68b9") != null)
			return (nc.ui.qcco.task.ace.handler.AceBodyBeforeEditHandler) context
					.get("nc.ui.qcco.task.ace.handler.AceBodyBeforeEditHandler#17d68b9");
		nc.ui.qcco.task.ace.handler.AceBodyBeforeEditHandler bean = new nc.ui.qcco.task.ace.handler.AceBodyBeforeEditHandler();
		context.put("nc.ui.qcco.task.ace.handler.AceBodyBeforeEditHandler#17d68b9", bean);
		bean.setMainBillForm(getBillFormEditor());
		bean.setGrandCard(getSunbillFormEditor1());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.event.EventHandlerGroup getEventHandlerGroup_a316f9() {
		if (context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#a316f9") != null)
			return (nc.ui.pubapp.uif2app.event.EventHandlerGroup) context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#a316f9");
		nc.ui.pubapp.uif2app.event.EventHandlerGroup bean = new nc.ui.pubapp.uif2app.event.EventHandlerGroup();
		context.put("nc.ui.pubapp.uif2app.event.EventHandlerGroup#a316f9", bean);
		bean.setEvent("nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent");
		bean.setHandler(getAceHeadTailAfterEditHandler_1ffcbe4());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.qcco.task.ace.handler.AceHeadTailAfterEditHandler getAceHeadTailAfterEditHandler_1ffcbe4() {
		if (context.get("nc.ui.qcco.task.ace.handler.AceHeadTailAfterEditHandler#1ffcbe4") != null)
			return (nc.ui.qcco.task.ace.handler.AceHeadTailAfterEditHandler) context
					.get("nc.ui.qcco.task.ace.handler.AceHeadTailAfterEditHandler#1ffcbe4");
		nc.ui.qcco.task.ace.handler.AceHeadTailAfterEditHandler bean = new nc.ui.qcco.task.ace.handler.AceHeadTailAfterEditHandler();
		context.put("nc.ui.qcco.task.ace.handler.AceHeadTailAfterEditHandler#1ffcbe4", bean);
		bean.setMainBillForm(getBillFormEditor());
		bean.setGrandCard(getSunbillFormEditor1());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.event.EventHandlerGroup getEventHandlerGroup_1569e87() {
		if (context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#1569e87") != null)
			return (nc.ui.pubapp.uif2app.event.EventHandlerGroup) context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#1569e87");
		nc.ui.pubapp.uif2app.event.EventHandlerGroup bean = new nc.ui.pubapp.uif2app.event.EventHandlerGroup();
		context.put("nc.ui.pubapp.uif2app.event.EventHandlerGroup#1569e87", bean);
		bean.setEvent("nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent");
		bean.setHandler(getAceHeadTailBeforeEditHandler_1393efe());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.qcco.task.ace.handler.AceHeadTailBeforeEditHandler getAceHeadTailBeforeEditHandler_1393efe() {
		if (context.get("nc.ui.qcco.task.ace.handler.AceHeadTailBeforeEditHandler#1393efe") != null)
			return (nc.ui.qcco.task.ace.handler.AceHeadTailBeforeEditHandler) context
					.get("nc.ui.qcco.task.ace.handler.AceHeadTailBeforeEditHandler#1393efe");
		nc.ui.qcco.task.ace.handler.AceHeadTailBeforeEditHandler bean = new nc.ui.qcco.task.ace.handler.AceHeadTailBeforeEditHandler();
		context.put("nc.ui.qcco.task.ace.handler.AceHeadTailBeforeEditHandler#1393efe", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.actions.ActionContributors getToftpanelActionContributors() {
		if (context.get("toftpanelActionContributors") != null)
			return (nc.ui.uif2.actions.ActionContributors) context.get("toftpanelActionContributors");
		nc.ui.uif2.actions.ActionContributors bean = new nc.ui.uif2.actions.ActionContributors();
		context.put("toftpanelActionContributors", bean);
		bean.setContributors(getManagedList15());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList15() {
		List list = new ArrayList();
		list.add(getActionsOfList());
		list.add(getActionsOfCard());
		return list;
	}

	public nc.ui.uif2.actions.StandAloneToftPanelActionContainer getActionsOfList() {
		if (context.get("actionsOfList") != null)
			return (nc.ui.uif2.actions.StandAloneToftPanelActionContainer) context.get("actionsOfList");
		nc.ui.uif2.actions.StandAloneToftPanelActionContainer bean = new nc.ui.uif2.actions.StandAloneToftPanelActionContainer(
				getListView());
		context.put("actionsOfList", bean);
		bean.setModel(getManageAppModel());
		bean.setActions(getManagedList16());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList16() {
		List list = new ArrayList();
		list.add(getAddAction());
		list.add(getEditAction());
		list.add(getDeleteAction());
		list.add(getSeparatorAction());
		list.add(getQueryAction());
		list.add(getRefreshAction());
		list.add(getSeparatorAction());
		list.add(getPrintMenuAction());
		list.add(getSeparatorAction());
		list.add(getCommitScriptAction());
		list.add(getUnCommitScriptAction());
		list.add(getApproveScriptAction());
		list.add(getUNApproveScriptAction());
		list.add(getSeparatorAction());
		list.add(getSeparatorAction());
		list.add(getOutputAction());
		list.add(getSeparatorAction());
		list.add(getPFApproveStatusInfoAction());
		list.add(getFileupload());
		list.add(getSeparatorAction());
		list.add(getFlowCardAction());
		list.add(getTaskStatusAction());
		list.add(getSampleProgressAction());
		list.add(getSeparatorAction());
		return list;
	}

	public nc.ui.uif2.actions.StandAloneToftPanelActionContainer getActionsOfCard() {
		if (context.get("actionsOfCard") != null)
			return (nc.ui.uif2.actions.StandAloneToftPanelActionContainer) context.get("actionsOfCard");
		nc.ui.uif2.actions.StandAloneToftPanelActionContainer bean = new nc.ui.uif2.actions.StandAloneToftPanelActionContainer(
				getBillFormEditor());
		context.put("actionsOfCard", bean);
		bean.setModel(getManageAppModel());
		bean.setActions(getManagedList17());
		bean.setEditActions(getManagedList18());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList17() {
		List list = new ArrayList();
		list.add(getAddAction());
		list.add(getEditAction());
		list.add(getDeleteAction());
		list.add(getSeparatorAction());
		list.add(getQueryAction());
		list.add(getCardRefreshAction());
		list.add(getSeparatorAction());
		list.add(getPrintMenuAction());
		list.add(getSeparatorAction());
		list.add(getCommitScriptAction());
		list.add(getUnCommitScriptAction());
		list.add(getApproveScriptAction());
		list.add(getUNApproveScriptAction());
		list.add(getSeparatorAction());
		list.add(getSeparatorAction());
		list.add(getOutputAction());
		list.add(getSeparatorAction());
		list.add(getPFApproveStatusInfoAction());
		list.add(getFileupload());
		list.add(getSeparatorAction());
		list.add(getFlowCardAction());
		list.add(getTaskStatusAction());
		list.add(getSampleProgressAction());
		list.add(getSeparatorAction());
		return list;
	}

	private List getManagedList18() {
		List list = new ArrayList();
		list.add(getSaveAction());
		list.add(getTemporarilySaveAction());
		list.add(getCancelAction());
		return list;
	}

	public nc.funcnode.ui.action.SeparatorAction getSeparatorAction() {
		if (context.get("separatorAction") != null)
			return (nc.funcnode.ui.action.SeparatorAction) context.get("separatorAction");
		nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
		context.put("separatorAction", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor getShowListInterceptor() {
		if (context.get("showListInterceptor") != null)
			return (nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor) context.get("showListInterceptor");
		nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor bean = new nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor();
		context.put("showListInterceptor", bean);
		bean.setShowUpComponent(getMainGrandlistView());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor getShowCardInterceptor() {
		if (context.get("showCardInterceptor") != null)
			return (nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor) context.get("showCardInterceptor");
		nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor bean = new nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor();
		context.put("showCardInterceptor", bean);
		bean.setShowUpComponent(getMainGrandbillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.actions.AddAction getAddAction() {
		if (context.get("addAction") != null)
			return (nc.ui.uif2.actions.AddAction) context.get("addAction");
		nc.ui.uif2.actions.AddAction bean = new nc.ui.uif2.actions.AddAction();
		context.put("addAction", bean);
		bean.setModel(getManageAppModel());
		bean.setInterceptor(getShowCardInterceptor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.actions.EditAction getEditAction() {
		if (context.get("editAction") != null)
			return (nc.ui.uif2.actions.EditAction) context.get("editAction");
		nc.ui.uif2.actions.EditAction bean = new nc.ui.uif2.actions.EditAction();
		context.put("editAction", bean);
		bean.setModel(getManageAppModel());
		bean.setInterceptor(getShowCardInterceptor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.qcco.task.action.TaskDeleteAction getDeleteAction() {
		if (context.get("deleteAction") != null)
			return (nc.ui.qcco.task.action.TaskDeleteAction) context.get("deleteAction");
		nc.ui.qcco.task.action.TaskDeleteAction bean = new nc.ui.qcco.task.action.TaskDeleteAction();
		context.put("deleteAction", bean);
		bean.setModel(getManageAppModel());
		bean.setMainGrandModel(getMainGrandModel());
		bean.setSingleBillService(getDeleteProxy());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.qcco.task.action.TaskSaveAction getSaveAction() {
		if (context.get("saveAction") != null)
			return (nc.ui.qcco.task.action.TaskSaveAction) context.get("saveAction");
		nc.ui.qcco.task.action.TaskSaveAction bean = new nc.ui.qcco.task.action.TaskSaveAction();
		context.put("saveAction", bean);
		bean.setModel(getManageAppModel());
		bean.setService(getBmModelModelService());
		bean.setEditor(getBillFormEditor());
		bean.setMainGrandModel(getMainGrandModel());
		getTaskBodyPasteToTailAction_18c3d25().setBillForm(getMainGrandbillFormEditor());
		bean.setRefresh(getCardRefreshAction());
		bean.setBillFormEditor(getBillFormEditor());
		bean.setBillForm(getMainGrandbillFormEditor());
		bean.setValidationService(getValidateService());
		bean.setExceptionHandler(getExceptionHandler());
		bean.setGrandCard(getSunbillFormEditor1());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.qcco.task.action.FileUploadAction getFileupload() {
		if (context.get("fileupload") != null)
			return (nc.ui.qcco.task.action.FileUploadAction) context.get("fileupload");
		nc.ui.qcco.task.action.FileUploadAction bean = new nc.ui.qcco.task.action.FileUploadAction();
		context.put("fileupload", bean);
		bean.setModel(getManageAppModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.qcco.task.action.TaskTemporarilySaveAction getTemporarilySaveAction() {
		if (context.get("temporarilySaveAction") != null)
			return (nc.ui.qcco.task.action.TaskTemporarilySaveAction) context.get("temporarilySaveAction");
		nc.ui.qcco.task.action.TaskTemporarilySaveAction bean = new nc.ui.qcco.task.action.TaskTemporarilySaveAction();
		context.put("temporarilySaveAction", bean);
		bean.setModel(getManageAppModel());
		bean.setService(getBmModelModelService());
		bean.setEditor(getBillFormEditor());
		bean.setMainGrandModel(getMainGrandModel());
		bean.setBillFormEditor(getBillFormEditor());
		bean.setBillForm(getMainGrandbillFormEditor());
		bean.setValidationService(getValidateService());
		bean.setRefresh(getCardRefreshAction());
		bean.setExceptionHandler(getExceptionHandler());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.validation.CompositeValidation getValidateService() {
		if (context.get("validateService") != null)
			return (nc.ui.pubapp.uif2app.validation.CompositeValidation) context.get("validateService");
		nc.ui.pubapp.uif2app.validation.CompositeValidation bean = new nc.ui.pubapp.uif2app.validation.CompositeValidation();
		context.put("validateService", bean);
		bean.setValidators(getManagedList19());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList19() {
		List list = new ArrayList();
		list.add(getTemplateNotNullValidation_1c1d4a1());
		return list;
	}

	private nc.ui.pubapp.uif2app.validation.TemplateNotNullValidation getTemplateNotNullValidation_1c1d4a1() {
		if (context.get("nc.ui.pubapp.uif2app.validation.TemplateNotNullValidation#1c1d4a1") != null)
			return (nc.ui.pubapp.uif2app.validation.TemplateNotNullValidation) context
					.get("nc.ui.pubapp.uif2app.validation.TemplateNotNullValidation#1c1d4a1");
		nc.ui.pubapp.uif2app.validation.TemplateNotNullValidation bean = new nc.ui.pubapp.uif2app.validation.TemplateNotNullValidation();
		context.put("nc.ui.pubapp.uif2app.validation.TemplateNotNullValidation#1c1d4a1", bean);
		bean.setBillForm(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.CancelAction getCancelAction() {
		if (context.get("cancelAction") != null)
			return (nc.ui.pubapp.uif2app.actions.CancelAction) context.get("cancelAction");
		nc.ui.pubapp.uif2app.actions.CancelAction bean = new nc.ui.pubapp.uif2app.actions.CancelAction();
		context.put("cancelAction", bean);
		bean.setModel(getManageAppModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.CopyAction getCopyAction() {
		if (context.get("copyAction") != null)
			return (nc.ui.pubapp.uif2app.actions.CopyAction) context.get("copyAction");
		nc.ui.pubapp.uif2app.actions.CopyAction bean = new nc.ui.pubapp.uif2app.actions.CopyAction();
		context.put("copyAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setExceptionHandler(getExceptionHandler());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction getQueryAction() {
		if (context.get("queryAction") != null)
			return (nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction) context.get("queryAction");
		nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction bean = new nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction();
		context.put("queryAction", bean);
		bean.setModel(getManageAppModel());
		bean.setDataManager(getModelDataManager());
		bean.setShowUpComponent(getMainGrandlistView());
		bean.setTemplateContainer(getQueryTemplateContainer());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction getRefreshAction() {
		if (context.get("refreshAction") != null)
			return (nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction) context.get("refreshAction");
		nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction bean = new nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction();
		context.put("refreshAction", bean);
		bean.setDataManager(getModelDataManager());
		bean.setModel(getManageAppModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.RefreshSingleAction getCardRefreshAction() {
		if (context.get("cardRefreshAction") != null)
			return (nc.ui.pubapp.uif2app.actions.RefreshSingleAction) context.get("cardRefreshAction");
		nc.ui.pubapp.uif2app.actions.RefreshSingleAction bean = new nc.ui.pubapp.uif2app.actions.RefreshSingleAction();
		context.put("cardRefreshAction", bean);
		bean.setModel(getManageAppModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction getPrintAction() {
		if (context.get("printAction") != null)
			return (nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction) context.get("printAction");
		nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction bean = new nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction();
		context.put("printAction", bean);
		bean.setPreview(false);
		bean.setModel(getManageAppModel());
		bean.setNodeKey("ot");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction getPreviewAction() {
		if (context.get("previewAction") != null)
			return (nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction) context.get("previewAction");
		nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction bean = new nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction();
		context.put("previewAction", bean);
		bean.setPreview(true);
		bean.setModel(getManageAppModel());
		bean.setNodeKey("ot");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.OutputAction getOutputAction() {
		if (context.get("outputAction") != null)
			return (nc.ui.pubapp.uif2app.actions.OutputAction) context.get("outputAction");
		nc.ui.pubapp.uif2app.actions.OutputAction bean = new nc.ui.pubapp.uif2app.actions.OutputAction();
		context.put("outputAction", bean);
		bean.setModel(getManageAppModel());
		bean.setParent(getBillFormEditor());
		bean.setNodeKey("ot");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.funcnode.ui.action.GroupAction getPrintMenuAction() {
		if (context.get("printMenuAction") != null)
			return (nc.funcnode.ui.action.GroupAction) context.get("printMenuAction");
		nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
		context.put("printMenuAction", bean);
		bean.setCode("printMenuAction");
		bean.setName(getI18nFB_6eb3a6());
		bean.setActions(getManagedList20());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_6eb3a6() {
		if (context.get("nc.ui.uif2.I18nFB#6eb3a6") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#6eb3a6");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#6eb3a6", bean);
		bean.setResDir("common");
		bean.setResId("UC001-0000007");
		bean.setDefaultValue("打印");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#6eb3a6", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private List getManagedList20() {
		List list = new ArrayList();
		list.add(getPrintAction());
		list.add(getPreviewAction());
		list.add(getOutputAction());
		return list;
	}

	public nc.ui.qcco.task.action.FlowCardAction getFlowCardAction() {
		if (context.get("flowCardAction") != null)
			return (nc.ui.qcco.task.action.FlowCardAction) context.get("flowCardAction");
		nc.ui.qcco.task.action.FlowCardAction bean = new nc.ui.qcco.task.action.FlowCardAction();
		context.put("flowCardAction", bean);
		bean.setModel(getManageAppModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.qcco.task.action.TaskStatusAction getTaskStatusAction() {
		if (context.get("taskStatusAction") != null)
			return (nc.ui.qcco.task.action.TaskStatusAction) context.get("taskStatusAction");
		nc.ui.qcco.task.action.TaskStatusAction bean = new nc.ui.qcco.task.action.TaskStatusAction();
		context.put("taskStatusAction", bean);
		bean.setModel(getManageAppModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.qcco.task.action.SampleProgressAction getSampleProgressAction() {
		if (context.get("sampleProgressAction") != null)
			return (nc.ui.qcco.task.action.SampleProgressAction) context.get("sampleProgressAction");
		nc.ui.qcco.task.action.SampleProgressAction bean = new nc.ui.qcco.task.action.SampleProgressAction();
		context.put("sampleProgressAction", bean);
		bean.setModel(getManageAppModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.funcnode.ui.action.GroupAction getPreviewActionGroup() {
		if (context.get("previewActionGroup") != null)
			return (nc.funcnode.ui.action.GroupAction) context.get("previewActionGroup");
		nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
		context.put("previewActionGroup", bean);
		bean.setCode("previewActionGroup");
		bean.setName("预览");
		bean.setActions(getManagedList21());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList21() {
		List list = new ArrayList();
		list.add((javax.swing.Action) findBeanInUIF2BeanFactory("quotationAction"));
		list.add((javax.swing.Action) findBeanInUIF2BeanFactory("payDemandAction"));
		list.add((javax.swing.Action) findBeanInUIF2BeanFactory("officialReportAction"));
		return list;
	}

	public nc.ui.pubapp.uif2app.lazilyload.DefaultBillLazilyLoader getBillLazilyLoader() {
		if (context.get("billLazilyLoader") != null)
			return (nc.ui.pubapp.uif2app.lazilyload.DefaultBillLazilyLoader) context.get("billLazilyLoader");
		nc.ui.pubapp.uif2app.lazilyload.DefaultBillLazilyLoader bean = new nc.ui.pubapp.uif2app.lazilyload.DefaultBillLazilyLoader();
		context.put("billLazilyLoader", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager getLasilyLodadMediator() {
		if (context.get("lasilyLodadMediator") != null)
			return (nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager) context.get("lasilyLodadMediator");
		nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager bean = new nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager();
		context.put("lasilyLodadMediator", bean);
		bean.setModel(getManageAppModel());
		bean.setLoader(getBillLazilyLoader());
		bean.setLazilyLoadSupporter(getManagedList22());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList22() {
		List list = new ArrayList();
		list.add(getCardPanelLazilyLoad_1d48acd());
		list.add(getListPanelLazilyLoad_e4a90e());
		return list;
	}

	private nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad getCardPanelLazilyLoad_1d48acd() {
		if (context.get("nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad#1d48acd") != null)
			return (nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad) context
					.get("nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad#1d48acd");
		nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad bean = new nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad();
		context.put("nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad#1d48acd", bean);
		bean.setBillform(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad getListPanelLazilyLoad_e4a90e() {
		if (context.get("nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad#e4a90e") != null)
			return (nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad) context
					.get("nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad#e4a90e");
		nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad bean = new nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad();
		context.put("nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad#e4a90e", bean);
		bean.setListView(getListView());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.model.BillBodySortMediator getBillBodySortMediator() {
		if (context.get("billBodySortMediator") != null)
			return (nc.ui.pubapp.uif2app.model.BillBodySortMediator) context.get("billBodySortMediator");
		nc.ui.pubapp.uif2app.model.BillBodySortMediator bean = new nc.ui.pubapp.uif2app.model.BillBodySortMediator(getManageAppModel(),
				getBillFormEditor(), getListView());
		context.put("billBodySortMediator", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener getInitDataListener() {
		if (context.get("InitDataListener") != null)
			return (nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener) context.get("InitDataListener");
		nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener bean = new nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener();
		context.put("InitDataListener", bean);
		bean.setModel(getManageAppModel());
		bean.setContext(getContext());
		bean.setVoClassName("nc.vo.qcco.task.AggTaskHVO");
		bean.setAutoShowUpComponent(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.common.validateservice.ClosingCheck getClosingListener() {
		if (context.get("ClosingListener") != null)
			return (nc.ui.pubapp.common.validateservice.ClosingCheck) context.get("ClosingListener");
		nc.ui.pubapp.common.validateservice.ClosingCheck bean = new nc.ui.pubapp.common.validateservice.ClosingCheck();
		context.put("ClosingListener", bean);
		bean.setModel(getManageAppModel());
		bean.setSaveAction(getSaveAction());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.view.FractionFixMediator getFractionFixMediator() {
		if (context.get("fractionFixMediator") != null)
			return (nc.ui.pubapp.uif2app.view.FractionFixMediator) context.get("fractionFixMediator");
		nc.ui.pubapp.uif2app.view.FractionFixMediator bean = new nc.ui.pubapp.uif2app.view.FractionFixMediator(getBillFormEditor());
		context.put("fractionFixMediator", bean);
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator getMouseClickShowPanelMediator() {
		if (context.get("mouseClickShowPanelMediator") != null)
			return (nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator) context.get("mouseClickShowPanelMediator");
		nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator bean = new nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator();
		context.put("mouseClickShowPanelMediator", bean);
		bean.setListView(getListView());
		bean.setShowUpComponent(getMainGrandbillFormEditor());
		bean.setHyperLinkColumn("vbillcode");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.bill.BillCodeMediator getBillCodeMediator() {
		if (context.get("billCodeMediator") != null)
			return (nc.ui.pubapp.bill.BillCodeMediator) context.get("billCodeMediator");
		nc.ui.pubapp.bill.BillCodeMediator bean = new nc.ui.pubapp.bill.BillCodeMediator();
		context.put("billCodeMediator", bean);
		bean.setBillForm(getBillFormEditor());
		bean.setBillCodeKey("vbillcode");
		bean.setBillType("QC08");
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.view.RowNoMediator getRowNoMediator() {
		if (context.get("rowNoMediator") != null)
			return (nc.ui.pubapp.uif2app.view.RowNoMediator) context.get("rowNoMediator");
		nc.ui.pubapp.uif2app.view.RowNoMediator bean = new nc.ui.pubapp.uif2app.view.RowNoMediator();
		context.put("rowNoMediator", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller getRemoteCallCombinatorCaller() {
		if (context.get("remoteCallCombinatorCaller") != null)
			return (nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller) context.get("remoteCallCombinatorCaller");
		nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller bean = new nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller();
		context.put("remoteCallCombinatorCaller", bean);
		bean.setRemoteCallers(getManagedList23());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList23() {
		List list = new ArrayList();
		list.add(getQueryTemplateContainer());
		list.add(getTemplateContainer());
		list.add(getUserdefitemContainer());
		return list;
	}

	public nc.ui.pubapp.uif2app.actions.pflow.CommitScriptAction getCommitScriptAction() {
		if (context.get("commitScriptAction") != null)
			return (nc.ui.pubapp.uif2app.actions.pflow.CommitScriptAction) context.get("commitScriptAction");
		nc.ui.pubapp.uif2app.actions.pflow.CommitScriptAction bean = new nc.ui.pubapp.uif2app.actions.pflow.CommitScriptAction();
		context.put("commitScriptAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setBillType("QC08");
		bean.setFilledUpInFlow(true);
		bean.setActionName("SAVE");
		bean.setExceptionHandler(getExceptionHandler());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.pflow.UnCommitScriptAction getUnCommitScriptAction() {
		if (context.get("unCommitScriptAction") != null)
			return (nc.ui.pubapp.uif2app.actions.pflow.UnCommitScriptAction) context.get("unCommitScriptAction");
		nc.ui.pubapp.uif2app.actions.pflow.UnCommitScriptAction bean = new nc.ui.pubapp.uif2app.actions.pflow.UnCommitScriptAction();
		context.put("unCommitScriptAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setBillType("QC08");
		bean.setFilledUpInFlow(true);
		bean.setActionName("UNSAVEBILL");
		bean.setExceptionHandler(getExceptionHandler());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.pflow.ApproveScriptAction getApproveScriptAction() {
		if (context.get("approveScriptAction") != null)
			return (nc.ui.pubapp.uif2app.actions.pflow.ApproveScriptAction) context.get("approveScriptAction");
		nc.ui.pubapp.uif2app.actions.pflow.ApproveScriptAction bean = new nc.ui.pubapp.uif2app.actions.pflow.ApproveScriptAction();
		context.put("approveScriptAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setBillType("QC08");
		bean.setFilledUpInFlow(true);
		bean.setActionName("APPROVE");
		bean.setExceptionHandler(getExceptionHandler());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.pflow.UNApproveScriptAction getUNApproveScriptAction() {
		if (context.get("uNApproveScriptAction") != null)
			return (nc.ui.pubapp.uif2app.actions.pflow.UNApproveScriptAction) context.get("uNApproveScriptAction");
		nc.ui.pubapp.uif2app.actions.pflow.UNApproveScriptAction bean = new nc.ui.pubapp.uif2app.actions.pflow.UNApproveScriptAction();
		context.put("uNApproveScriptAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setBillType("QC08");
		bean.setFilledUpInFlow(true);
		bean.setActionName("UNAPPROVE");
		bean.setExceptionHandler(getExceptionHandler());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.qcco.task.action.WriteBackLimsAction getWriteBackLims() {
		if (context.get("writeBackLims") != null)
			return (nc.ui.qcco.task.action.WriteBackLimsAction) context.get("writeBackLims");
		nc.ui.qcco.task.action.WriteBackLimsAction bean = new nc.ui.qcco.task.action.WriteBackLimsAction();
		context.put("writeBackLims", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setBillType("QC08");
		bean.setFilledUpInFlow(true);
		bean.setActionName("UNAPPROVE");
		bean.setExceptionHandler(getExceptionHandler());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.LinkQueryAction getLinkQueryAction() {
		if (context.get("linkQueryAction") != null)
			return (nc.ui.pubapp.uif2app.actions.LinkQueryAction) context.get("linkQueryAction");
		nc.ui.pubapp.uif2app.actions.LinkQueryAction bean = new nc.ui.pubapp.uif2app.actions.LinkQueryAction();
		context.put("linkQueryAction", bean);
		bean.setModel(getManageAppModel());
		bean.setExceptionHandler(getExceptionHandler());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction getMetaDataBasedPrintAction() {
		if (context.get("metaDataBasedPrintAction") != null)
			return (nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction) context.get("metaDataBasedPrintAction");
		nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction bean = new nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction();
		context.put("metaDataBasedPrintAction", bean);
		bean.setModel(getManageAppModel());
		bean.setActioncode("Preview");
		bean.setActionname("预览");
		bean.setPreview(true);
		bean.setNodeKey("ot");
		bean.setExceptionHandler(getExceptionHandler());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.pflow.PFApproveStatusInfoAction getPFApproveStatusInfoAction() {
		if (context.get("pFApproveStatusInfoAction") != null)
			return (nc.ui.pubapp.uif2app.actions.pflow.PFApproveStatusInfoAction) context.get("pFApproveStatusInfoAction");
		nc.ui.pubapp.uif2app.actions.pflow.PFApproveStatusInfoAction bean = new nc.ui.pubapp.uif2app.actions.pflow.PFApproveStatusInfoAction();
		context.put("pFApproveStatusInfoAction", bean);
		bean.setModel(getManageAppModel());
		bean.setExceptionHandler(getExceptionHandler());
		bean.setBillType("QC08");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.DefaultExceptionHanler getExceptionHandler() {
		if (context.get("exceptionHandler") != null)
			return (nc.ui.uif2.DefaultExceptionHanler) context.get("exceptionHandler");
		nc.ui.uif2.DefaultExceptionHanler bean = new nc.ui.uif2.DefaultExceptionHanler(getContainer());
		context.put("exceptionHandler", bean);
		bean.setContext(getContext());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

}
