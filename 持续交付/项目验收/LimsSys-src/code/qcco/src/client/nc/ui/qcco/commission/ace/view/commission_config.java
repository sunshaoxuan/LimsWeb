package nc.ui.qcco.commission.ace.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class commission_config extends AbstractJavaBeanDefinition {
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

	public nc.ui.qcco.commission.ace.serviceproxy.AceCommissionDeleteProxy getDeleteProxy() {
		if (context.get("deleteProxy") != null)
			return (nc.ui.qcco.commission.ace.serviceproxy.AceCommissionDeleteProxy) context.get("deleteProxy");
		nc.ui.qcco.commission.ace.serviceproxy.AceCommissionDeleteProxy bean = new nc.ui.qcco.commission.ace.serviceproxy.AceCommissionDeleteProxy();
		context.put("deleteProxy", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.qcco.commission.ace.serviceproxy.AceCommissionMaintainProxy getBmModelModelService() {
		if (context.get("bmModelModelService") != null)
			return (nc.ui.qcco.commission.ace.serviceproxy.AceCommissionMaintainProxy) context.get("bmModelModelService");
		nc.ui.qcco.commission.ace.serviceproxy.AceCommissionMaintainProxy bean = new nc.ui.qcco.commission.ace.serviceproxy.AceCommissionMaintainProxy();
		context.put("bmModelModelService", bean);
		bean.setGrandTabAndVOMap(getManagedMap0());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private Map getManagedMap0() {
		Map map = new HashMap();
		map.put("pk_commission_r", getCommissionRVO());
		return map;
	}

	public nc.ui.pubapp.uif2app.view.value.AggVOMetaBDObjectAdapterFactory getBOAdapterFactory() {
		if (context.get("BOAdapterFactory") != null)
			return (nc.ui.pubapp.uif2app.view.value.AggVOMetaBDObjectAdapterFactory) context.get("BOAdapterFactory");
		nc.ui.pubapp.uif2app.view.value.AggVOMetaBDObjectAdapterFactory bean = new nc.ui.pubapp.uif2app.view.value.AggVOMetaBDObjectAdapterFactory();
		context.put("BOAdapterFactory", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.vo.bd.meta.BDObjectAdpaterFactory getBOAdapterFactory2() {
		if (context.get("BOAdapterFactory2") != null)
			return (nc.vo.bd.meta.BDObjectAdpaterFactory) context.get("BOAdapterFactory2");
		nc.vo.bd.meta.BDObjectAdpaterFactory bean = new nc.vo.bd.meta.BDObjectAdpaterFactory();
		context.put("BOAdapterFactory2", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.qcco.commission.model.MainSubBillModel getBmModel() {
		if (context.get("bmModel") != null)
			return (nc.ui.qcco.commission.model.MainSubBillModel) context.get("bmModel");
		nc.ui.qcco.commission.model.MainSubBillModel bean = new nc.ui.qcco.commission.model.MainSubBillModel();
		context.put("bmModel", bean);
		bean.setContext(getContext());
		bean.setBillFormView(getMainGrandbillForm());
		bean.setBillListView(getMainGrandlistView());
		bean.setBusinessObjectAdapterFactory(getBOAdapterFactory());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.qcco.commission.model.SubGrandBillModel getBmModel2() {
		if (context.get("bmModel2") != null)
			return (nc.ui.qcco.commission.model.SubGrandBillModel) context.get("bmModel2");
		nc.ui.qcco.commission.model.SubGrandBillModel bean = new nc.ui.qcco.commission.model.SubGrandBillModel();
		context.put("bmModel2", bean);
		bean.setContext(getContext());
		bean.setBusinessObjectAdapterFactory(getBOAdapterFactory2());
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
		bean.setMainModel(getBmModel());
		bean.setGrandModel(getBmModel2());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.components.grand.MainGrandBlankFilter getMainGrandBlankFilter() {
		if (context.get("mainGrandBlankFilter") != null)
			return (nc.ui.pubapp.uif2app.components.grand.MainGrandBlankFilter) context.get("mainGrandBlankFilter");
		nc.ui.pubapp.uif2app.components.grand.MainGrandBlankFilter bean = new nc.ui.pubapp.uif2app.components.grand.MainGrandBlankFilter();
		context.put("mainGrandBlankFilter", bean);
		bean.setChildFilterMap(getManagedMap1());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private Map getManagedMap1() {
		Map map = new HashMap();
		map.put("pk_commission_b", getManagedList0());
		return map;
	}

	private List getManagedList0() {
		List list = new ArrayList();
		list.add("rowno");
		return list;
	}

	public nc.ui.pubapp.uif2app.components.grand.MainGrandRelationShip getMainGrandRelationShip() {
		if (context.get("mainGrandRelationShip") != null)
			return (nc.ui.pubapp.uif2app.components.grand.MainGrandRelationShip) context.get("mainGrandRelationShip");
		nc.ui.pubapp.uif2app.components.grand.MainGrandRelationShip bean = new nc.ui.pubapp.uif2app.components.grand.MainGrandRelationShip();
		context.put("mainGrandRelationShip", bean);
		bean.setBodyTabTOGrandListComposite(getManagedMap2());
		bean.setBodyTabTOGrandCardComposite(getManagedMap3());
		bean.setGrandTabAndVOMap(getManagedMap4());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private Map getManagedMap2() {
		Map map = new HashMap();
		map.put("pk_commission_b", getSunlistView1());
		return map;
	}

	private Map getManagedMap3() {
		Map map = new HashMap();
		map.put("pk_commission_b", getSunbillForm1());
		return map;
	}

	private Map getManagedMap4() {
		Map map = new HashMap();
		map.put("pk_commission_r", getCommissionRVO());
		return map;
	}

	public nc.vo.qcco.commission.CommissionRVO getCommissionRVO() {
		if (context.get("CommissionRVO") != null)
			return (nc.vo.qcco.commission.CommissionRVO) context.get("CommissionRVO");
		nc.vo.qcco.commission.CommissionRVO bean = new nc.vo.qcco.commission.CommissionRVO();
		context.put("CommissionRVO", bean);
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
		bean.setMainPanel(getBillListView());
		bean.setExpendShrinkGrandListAction(getExpendShrinkGrandListAction());
		bean.setGrandString("实验前参数");
		bean.setDataManager(getBmModelModelDataManager());
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.components.grand.CardGrandPanelComposite getMainGrandbillForm() {
		if (context.get("MainGrandbillForm") != null)
			return (nc.ui.pubapp.uif2app.components.grand.CardGrandPanelComposite) context.get("MainGrandbillForm");
		nc.ui.pubapp.uif2app.components.grand.CardGrandPanelComposite bean = new nc.ui.pubapp.uif2app.components.grand.CardGrandPanelComposite();
		context.put("MainGrandbillForm", bean);
		bean.setMainPanel(getBillForm());
		bean.setModel(getMainGrandModel());
		bean.setMaingrandrelationship(getMainGrandRelationShip());
		bean.setMainGrandBlankFilter(getMainGrandBlankFilter());
		bean.setMediator(getMainGrandMediator());
		bean.setExpendShrinkGrandCardAction(getExpendShrinkGrandCardAction());
		bean.setGrandString("实验前参数");
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.components.grand.mediator.MainGrandMediator getMainGrandMediator() {
		if (context.get("mainGrandMediator") != null)
			return (nc.ui.pubapp.uif2app.components.grand.mediator.MainGrandMediator) context.get("mainGrandMediator");
		nc.ui.pubapp.uif2app.components.grand.mediator.MainGrandMediator bean = new nc.ui.pubapp.uif2app.components.grand.mediator.MainGrandMediator();
		context.put("mainGrandMediator", bean);
		bean.setMainBillForm(getBillForm());
		bean.setMainBillListView(getBillListView());
		bean.setMainGrandModel(getMainGrandModel());
		bean.setMainGrandRelationShip(getMainGrandRelationShip());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.qcco.commission.ace.view.GrandBillList getSunlistView1() {
		if (context.get("sunlistView1") != null)
			return (nc.ui.qcco.commission.ace.view.GrandBillList) context.get("sunlistView1");
		nc.ui.qcco.commission.ace.view.GrandBillList bean = new nc.ui.qcco.commission.ace.view.GrandBillList();
		context.put("sunlistView1", bean);
		bean.setModel(getBmModel2());
		bean.setTemplateContainer(getTemplateContainer());
		bean.setNodekey("param1");
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

	public nc.ui.qcco.commission.ace.view.CommissionGrandBillForm getSunbillForm1() {
		if (context.get("sunbillForm1") != null)
			return (nc.ui.qcco.commission.ace.view.CommissionGrandBillForm) context.get("sunbillForm1");
		nc.ui.qcco.commission.ace.view.CommissionGrandBillForm bean = new nc.ui.qcco.commission.ace.view.CommissionGrandBillForm();
		context.put("sunbillForm1", bean);
		bean.setModel(getBmModel2());
		bean.setComponentValueManager(getComponentValueManager());
		bean.setTemplateContainer(getTemplateContainer());
		bean.setNodekey("param");
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
		list.add(getSelectAllLineAction_f0b1d9());
		list.add(getSelectNoneLineAction_2c40eb());
		list.add(getGrandBodyAddLineAction_15a0fac());
		list.add(getBodyInsertLineAction_1baff8a());
		list.add(getBodyDelLineAction_1deed19());
		list.add(getBodyCopyLineAction_a014a7());
		list.add(getBodyPasteToTailAction_1ac138d());
		list.add(getBodyLineEditAction_37b3c4());
		return list;
	}

	private nc.ui.qcco.commission.action.SelectAllLineAction getSelectAllLineAction_f0b1d9() {
		if (context.get("nc.ui.qcco.commission.action.SelectAllLineAction#f0b1d9") != null)
			return (nc.ui.qcco.commission.action.SelectAllLineAction) context
					.get("nc.ui.qcco.commission.action.SelectAllLineAction#f0b1d9");
		nc.ui.qcco.commission.action.SelectAllLineAction bean = new nc.ui.qcco.commission.action.SelectAllLineAction();
		context.put("nc.ui.qcco.commission.action.SelectAllLineAction#f0b1d9", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.qcco.commission.action.SelectNoneLineAction getSelectNoneLineAction_2c40eb() {
		if (context.get("nc.ui.qcco.commission.action.SelectNoneLineAction#2c40eb") != null)
			return (nc.ui.qcco.commission.action.SelectNoneLineAction) context
					.get("nc.ui.qcco.commission.action.SelectNoneLineAction#2c40eb");
		nc.ui.qcco.commission.action.SelectNoneLineAction bean = new nc.ui.qcco.commission.action.SelectNoneLineAction();
		context.put("nc.ui.qcco.commission.action.SelectNoneLineAction#2c40eb", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.components.grand.action.GrandBodyAddLineAction getGrandBodyAddLineAction_15a0fac() {
		if (context.get("nc.ui.pubapp.uif2app.components.grand.action.GrandBodyAddLineAction#15a0fac") != null)
			return (nc.ui.pubapp.uif2app.components.grand.action.GrandBodyAddLineAction) context
					.get("nc.ui.pubapp.uif2app.components.grand.action.GrandBodyAddLineAction#15a0fac");
		nc.ui.pubapp.uif2app.components.grand.action.GrandBodyAddLineAction bean = new nc.ui.pubapp.uif2app.components.grand.action.GrandBodyAddLineAction();
		context.put("nc.ui.pubapp.uif2app.components.grand.action.GrandBodyAddLineAction#15a0fac", bean);
		bean.setMainForm(getBillForm());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.actions.BodyInsertLineAction getBodyInsertLineAction_1baff8a() {
		if (context.get("nc.ui.pubapp.uif2app.actions.BodyInsertLineAction#1baff8a") != null)
			return (nc.ui.pubapp.uif2app.actions.BodyInsertLineAction) context
					.get("nc.ui.pubapp.uif2app.actions.BodyInsertLineAction#1baff8a");
		nc.ui.pubapp.uif2app.actions.BodyInsertLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyInsertLineAction();
		context.put("nc.ui.pubapp.uif2app.actions.BodyInsertLineAction#1baff8a", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.actions.BodyDelLineAction getBodyDelLineAction_1deed19() {
		if (context.get("nc.ui.pubapp.uif2app.actions.BodyDelLineAction#1deed19") != null)
			return (nc.ui.pubapp.uif2app.actions.BodyDelLineAction) context.get("nc.ui.pubapp.uif2app.actions.BodyDelLineAction#1deed19");
		nc.ui.pubapp.uif2app.actions.BodyDelLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyDelLineAction();
		context.put("nc.ui.pubapp.uif2app.actions.BodyDelLineAction#1deed19", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.actions.BodyCopyLineAction getBodyCopyLineAction_a014a7() {
		if (context.get("nc.ui.pubapp.uif2app.actions.BodyCopyLineAction#a014a7") != null)
			return (nc.ui.pubapp.uif2app.actions.BodyCopyLineAction) context.get("nc.ui.pubapp.uif2app.actions.BodyCopyLineAction#a014a7");
		nc.ui.pubapp.uif2app.actions.BodyCopyLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyCopyLineAction();
		context.put("nc.ui.pubapp.uif2app.actions.BodyCopyLineAction#a014a7", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction getBodyPasteToTailAction_1ac138d() {
		if (context.get("nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction#1ac138d") != null)
			return (nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction) context
					.get("nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction#1ac138d");
		nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction bean = new nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction();
		context.put("nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction#1ac138d", bean);
		bean.setClearItems(getManagedList2());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList2() {
		List list = new ArrayList();
		list.add("pk_commission_r");
		list.add("rowno");
		return list;
	}

	private nc.ui.pubapp.uif2app.actions.BodyLineEditAction getBodyLineEditAction_37b3c4() {
		if (context.get("nc.ui.pubapp.uif2app.actions.BodyLineEditAction#37b3c4") != null)
			return (nc.ui.pubapp.uif2app.actions.BodyLineEditAction) context.get("nc.ui.pubapp.uif2app.actions.BodyLineEditAction#37b3c4");
		nc.ui.pubapp.uif2app.actions.BodyLineEditAction bean = new nc.ui.pubapp.uif2app.actions.BodyLineEditAction();
		context.put("nc.ui.pubapp.uif2app.actions.BodyLineEditAction#37b3c4", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.query2.model.ModelDataManager getBmModelModelDataManager() {
		if (context.get("bmModelModelDataManager") != null)
			return (nc.ui.pubapp.uif2app.query2.model.ModelDataManager) context.get("bmModelModelDataManager");
		nc.ui.pubapp.uif2app.query2.model.ModelDataManager bean = new nc.ui.pubapp.uif2app.query2.model.ModelDataManager();
		context.put("bmModelModelDataManager", bean);
		bean.setModel(getBmModel());
		bean.setService(getBmModelModelService());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.model.AppEventHandlerMediator getGrandModelEventMediator() {
		if (context.get("grandModelEventMediator") != null)
			return (nc.ui.pubapp.uif2app.model.AppEventHandlerMediator) context.get("grandModelEventMediator");
		nc.ui.pubapp.uif2app.model.AppEventHandlerMediator bean = new nc.ui.pubapp.uif2app.model.AppEventHandlerMediator();
		context.put("grandModelEventMediator", bean);
		bean.setModel(getBmModel2());
		bean.setHandlerGroup(getManagedList3());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList3() {
		List list = new ArrayList();
		list.add(getEventHandlerGroup_aae9d5());
		list.add(getEventHandlerGroup_dbe6bf());
		return list;
	}

	private nc.ui.pubapp.uif2app.event.EventHandlerGroup getEventHandlerGroup_aae9d5() {
		if (context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#aae9d5") != null)
			return (nc.ui.pubapp.uif2app.event.EventHandlerGroup) context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#aae9d5");
		nc.ui.pubapp.uif2app.event.EventHandlerGroup bean = new nc.ui.pubapp.uif2app.event.EventHandlerGroup();
		context.put("nc.ui.pubapp.uif2app.event.EventHandlerGroup#aae9d5", bean);
		bean.setEvent("nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent");
		bean.setHandler(getGrandBodyBeforeEditHandler_ebad00());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.qcco.commission.ace.handler.GrandBodyBeforeEditHandler getGrandBodyBeforeEditHandler_ebad00() {
		if (context.get("nc.ui.qcco.commission.ace.handler.GrandBodyBeforeEditHandler#ebad00") != null)
			return (nc.ui.qcco.commission.ace.handler.GrandBodyBeforeEditHandler) context
					.get("nc.ui.qcco.commission.ace.handler.GrandBodyBeforeEditHandler#ebad00");
		nc.ui.qcco.commission.ace.handler.GrandBodyBeforeEditHandler bean = new nc.ui.qcco.commission.ace.handler.GrandBodyBeforeEditHandler(
				getBillForm());
		context.put("nc.ui.qcco.commission.ace.handler.GrandBodyBeforeEditHandler#ebad00", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.event.EventHandlerGroup getEventHandlerGroup_dbe6bf() {
		if (context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#dbe6bf") != null)
			return (nc.ui.pubapp.uif2app.event.EventHandlerGroup) context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#dbe6bf");
		nc.ui.pubapp.uif2app.event.EventHandlerGroup bean = new nc.ui.pubapp.uif2app.event.EventHandlerGroup();
		context.put("nc.ui.pubapp.uif2app.event.EventHandlerGroup#dbe6bf", bean);
		bean.setEvent("nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent");
		bean.setHandler(getGrandBodyAfterEditHandler_ddb9f6());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.qcco.commission.ace.handler.GrandBodyAfterEditHandler getGrandBodyAfterEditHandler_ddb9f6() {
		if (context.get("nc.ui.qcco.commission.ace.handler.GrandBodyAfterEditHandler#ddb9f6") != null)
			return (nc.ui.qcco.commission.ace.handler.GrandBodyAfterEditHandler) context
					.get("nc.ui.qcco.commission.ace.handler.GrandBodyAfterEditHandler#ddb9f6");
		nc.ui.qcco.commission.ace.handler.GrandBodyAfterEditHandler bean = new nc.ui.qcco.commission.ace.handler.GrandBodyAfterEditHandler();
		context.put("nc.ui.qcco.commission.ace.handler.GrandBodyAfterEditHandler#ddb9f6", bean);
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
		bean.setNodeKeies(getManagedList4());
		bean.load();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList4() {
		List list = new ArrayList();
		list.add("bt");
		list.add("param");
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

	public nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell getViewa() {
		if (context.get("viewa") != null)
			return (nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell) context.get("viewa");
		nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell bean = new nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell();
		context.put("viewa", bean);
		bean.setQueryAreaCreator((nc.ui.uif2.actions.IQueryAreaCreator) findBeanInUIF2BeanFactory("defaultQueryAction"));
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.qcco.commission.ace.view.MainBillList getBillListView() {
		if (context.get("billListView") != null)
			return (nc.ui.qcco.commission.ace.view.MainBillList) context.get("billListView");
		nc.ui.qcco.commission.ace.view.MainBillList bean = new nc.ui.qcco.commission.ace.view.MainBillList();
		context.put("billListView", bean);
		bean.setModel(getBmModel());
		bean.setNodekey("bt");
		bean.setMultiSelectionEnable(false);
		bean.setTemplateContainer(getTemplateContainer());
		bean.setUserdefitemListPreparator(getCompositeBillListDataPrepare_10a1466());
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare getCompositeBillListDataPrepare_10a1466() {
		if (context.get("nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare#10a1466") != null)
			return (nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare) context
					.get("nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare#10a1466");
		nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare bean = new nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare();
		context.put("nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare#10a1466", bean);
		bean.setBillListDataPrepares(getManagedList5());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList5() {
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
		bean.setParams(getManagedList6());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList6() {
		List list = new ArrayList();
		list.add(getUserdefQueryParam_c14996());
		list.add(getUserdefQueryParam_1c64252());
		return list;
	}

	private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_c14996() {
		if (context.get("nc.ui.uif2.editor.UserdefQueryParam#c14996") != null)
			return (nc.ui.uif2.editor.UserdefQueryParam) context.get("nc.ui.uif2.editor.UserdefQueryParam#c14996");
		nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
		context.put("nc.ui.uif2.editor.UserdefQueryParam#c14996", bean);
		bean.setMdfullname("qcco.commission");
		bean.setPos(0);
		bean.setPrefix("vdef");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_1c64252() {
		if (context.get("nc.ui.uif2.editor.UserdefQueryParam#1c64252") != null)
			return (nc.ui.uif2.editor.UserdefQueryParam) context.get("nc.ui.uif2.editor.UserdefQueryParam#1c64252");
		nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
		context.put("nc.ui.uif2.editor.UserdefQueryParam#1c64252", bean);
		bean.setMdfullname("qcco.CommissionBVO");
		bean.setPos(1);
		bean.setPrefix("vbdef");
		bean.setTabcode("CommissionBVO");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.bd.pub.BDOrgPanel getOrgpanel() {
		if (context.get("orgpanel") != null)
			return (nc.ui.bd.pub.BDOrgPanel) context.get("orgpanel");
		nc.ui.bd.pub.BDOrgPanel bean = new nc.ui.bd.pub.BDOrgPanel();
		context.put("orgpanel", bean);
		bean.setModel(getBmModel());
		bean.setDataManager(getBmModelModelDataManager());
		bean.setPk_orgtype("BUSINESSUNIT00000000");
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.view.ShowUpableBillForm getBillForm() {
		if (context.get("billForm") != null)
			return (nc.ui.pubapp.uif2app.view.ShowUpableBillForm) context.get("billForm");
		nc.ui.pubapp.uif2app.view.ShowUpableBillForm bean = new nc.ui.pubapp.uif2app.view.ShowUpableBillForm();
		context.put("billForm", bean);
		bean.setModel(getBmModel());
		bean.setTemplateContainer(getTemplateContainer());
		bean.setNodekey("bt");
		bean.setShowOrgPanel(false);
		bean.setAutoAddLine(false);
		bean.setBodyLineActions(getManagedList7());
		bean.setUserdefitemPreparator(getCompositeBillDataPrepare_14ceb0());
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList7() {
		List list = new ArrayList();
		list.add(getCommissionBodyAddLineAction_12c4597());
		list.add(getCommissionBodyInsertLineAction_18cbe21());
		list.add(getCommissionBodyDelLineAction_1169154());
		list.add(getBodyCopyLineAction_9f32c4());
		list.add(getCommissionBodyPasteTailAction_1be0b36());
		list.add(getBodyLineEditAction_1d20825());
		return list;
	}

	private nc.ui.qcco.commission.action.CommissionBodyAddLineAction getCommissionBodyAddLineAction_12c4597() {
		if (context.get("nc.ui.qcco.commission.action.CommissionBodyAddLineAction#12c4597") != null)
			return (nc.ui.qcco.commission.action.CommissionBodyAddLineAction) context
					.get("nc.ui.qcco.commission.action.CommissionBodyAddLineAction#12c4597");
		nc.ui.qcco.commission.action.CommissionBodyAddLineAction bean = new nc.ui.qcco.commission.action.CommissionBodyAddLineAction();
		context.put("nc.ui.qcco.commission.action.CommissionBodyAddLineAction#12c4597", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.qcco.commission.action.CommissionBodyInsertLineAction getCommissionBodyInsertLineAction_18cbe21() {
		if (context.get("nc.ui.qcco.commission.action.CommissionBodyInsertLineAction#18cbe21") != null)
			return (nc.ui.qcco.commission.action.CommissionBodyInsertLineAction) context
					.get("nc.ui.qcco.commission.action.CommissionBodyInsertLineAction#18cbe21");
		nc.ui.qcco.commission.action.CommissionBodyInsertLineAction bean = new nc.ui.qcco.commission.action.CommissionBodyInsertLineAction();
		context.put("nc.ui.qcco.commission.action.CommissionBodyInsertLineAction#18cbe21", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.qcco.commission.action.CommissionBodyDelLineAction getCommissionBodyDelLineAction_1169154() {
		if (context.get("nc.ui.qcco.commission.action.CommissionBodyDelLineAction#1169154") != null)
			return (nc.ui.qcco.commission.action.CommissionBodyDelLineAction) context
					.get("nc.ui.qcco.commission.action.CommissionBodyDelLineAction#1169154");
		nc.ui.qcco.commission.action.CommissionBodyDelLineAction bean = new nc.ui.qcco.commission.action.CommissionBodyDelLineAction();
		context.put("nc.ui.qcco.commission.action.CommissionBodyDelLineAction#1169154", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.actions.BodyCopyLineAction getBodyCopyLineAction_9f32c4() {
		if (context.get("nc.ui.pubapp.uif2app.actions.BodyCopyLineAction#9f32c4") != null)
			return (nc.ui.pubapp.uif2app.actions.BodyCopyLineAction) context.get("nc.ui.pubapp.uif2app.actions.BodyCopyLineAction#9f32c4");
		nc.ui.pubapp.uif2app.actions.BodyCopyLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyCopyLineAction();
		context.put("nc.ui.pubapp.uif2app.actions.BodyCopyLineAction#9f32c4", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.qcco.commission.action.CommissionBodyPasteTailAction getCommissionBodyPasteTailAction_1be0b36() {
		if (context.get("nc.ui.qcco.commission.action.CommissionBodyPasteTailAction#1be0b36") != null)
			return (nc.ui.qcco.commission.action.CommissionBodyPasteTailAction) context
					.get("nc.ui.qcco.commission.action.CommissionBodyPasteTailAction#1be0b36");
		nc.ui.qcco.commission.action.CommissionBodyPasteTailAction bean = new nc.ui.qcco.commission.action.CommissionBodyPasteTailAction(
				getMainGrandbillForm());
		context.put("nc.ui.qcco.commission.action.CommissionBodyPasteTailAction#1be0b36", bean);
		bean.setClearItems(getManagedList8());
		bean.setGrandCard(getSunbillForm1());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList8() {
		List list = new ArrayList();
		list.add("pk_commission_b");
		list.add("rowno");
		return list;
	}

	private nc.ui.pubapp.uif2app.actions.BodyLineEditAction getBodyLineEditAction_1d20825() {
		if (context.get("nc.ui.pubapp.uif2app.actions.BodyLineEditAction#1d20825") != null)
			return (nc.ui.pubapp.uif2app.actions.BodyLineEditAction) context.get("nc.ui.pubapp.uif2app.actions.BodyLineEditAction#1d20825");
		nc.ui.pubapp.uif2app.actions.BodyLineEditAction bean = new nc.ui.pubapp.uif2app.actions.BodyLineEditAction();
		context.put("nc.ui.pubapp.uif2app.actions.BodyLineEditAction#1d20825", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare getCompositeBillDataPrepare_14ceb0() {
		if (context.get("nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare#14ceb0") != null)
			return (nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare) context
					.get("nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare#14ceb0");
		nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare bean = new nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare();
		context.put("nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare#14ceb0", bean);
		bean.setBillDataPrepares(getManagedList9());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList9() {
		List list = new ArrayList();
		list.add(getUserdefitemPreparator());
		list.add(getMarAsstPreparator());
		return list;
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
		list.add(getUserdefQueryParam_daff0());
		list.add(getUserdefQueryParam_99c707());
		return list;
	}

	private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_daff0() {
		if (context.get("nc.ui.uif2.editor.UserdefQueryParam#daff0") != null)
			return (nc.ui.uif2.editor.UserdefQueryParam) context.get("nc.ui.uif2.editor.UserdefQueryParam#daff0");
		nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
		context.put("nc.ui.uif2.editor.UserdefQueryParam#daff0", bean);
		bean.setMdfullname("qcco.commission");
		bean.setPos(0);
		bean.setPrefix("vdef");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_99c707() {
		if (context.get("nc.ui.uif2.editor.UserdefQueryParam#99c707") != null)
			return (nc.ui.uif2.editor.UserdefQueryParam) context.get("nc.ui.uif2.editor.UserdefQueryParam#99c707");
		nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
		context.put("nc.ui.uif2.editor.UserdefQueryParam#99c707", bean);
		bean.setMdfullname("qcco.CommissionBVO");
		bean.setPos(1);
		bean.setPrefix("vbdef");
		bean.setTabcode("CommissionBVO");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator getMarAsstPreparator() {
		if (context.get("marAsstPreparator") != null)
			return (nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator) context.get("marAsstPreparator");
		nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator bean = new nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator();
		context.put("marAsstPreparator", bean);
		bean.setModel(getBmModel());
		bean.setContainer(getUserdefitemContainer());
		bean.setPrefix("vfree");
		bean.setMaterialField("pk_productserial");
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
		bean.setParams(getManagedList11());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList11() {
		List list = new ArrayList();
		list.add(getQueryParam_1d9b57e());
		list.add(getQueryParam_1a83f1());
		list.add(getQueryParam_cc7bb0());
		return list;
	}

	private nc.ui.uif2.userdefitem.QueryParam getQueryParam_1d9b57e() {
		if (context.get("nc.ui.uif2.userdefitem.QueryParam#1d9b57e") != null)
			return (nc.ui.uif2.userdefitem.QueryParam) context.get("nc.ui.uif2.userdefitem.QueryParam#1d9b57e");
		nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
		context.put("nc.ui.uif2.userdefitem.QueryParam#1d9b57e", bean);
		bean.setMdfullname("qcco.commission");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.userdefitem.QueryParam getQueryParam_1a83f1() {
		if (context.get("nc.ui.uif2.userdefitem.QueryParam#1a83f1") != null)
			return (nc.ui.uif2.userdefitem.QueryParam) context.get("nc.ui.uif2.userdefitem.QueryParam#1a83f1");
		nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
		context.put("nc.ui.uif2.userdefitem.QueryParam#1a83f1", bean);
		bean.setMdfullname("qcco.CommissionBVO");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.userdefitem.QueryParam getQueryParam_cc7bb0() {
		if (context.get("nc.ui.uif2.userdefitem.QueryParam#cc7bb0") != null)
			return (nc.ui.uif2.userdefitem.QueryParam) context.get("nc.ui.uif2.userdefitem.QueryParam#cc7bb0");
		nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
		context.put("nc.ui.uif2.userdefitem.QueryParam#cc7bb0", bean);
		bean.setRulecode("materialassistant");
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
		bean.setModel(getBmModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel getViewb() {
		if (context.get("viewb") != null)
			return (nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel) context.get("viewb");
		nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel bean = new nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel();
		context.put("viewb", bean);
		bean.setModel(getBmModel());
		bean.setTitleAction(getReturnAction());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.actions.UEReturnAction getReturnAction() {
		if (context.get("returnAction") != null)
			return (nc.ui.pubapp.uif2app.actions.UEReturnAction) context.get("returnAction");
		nc.ui.pubapp.uif2app.actions.UEReturnAction bean = new nc.ui.pubapp.uif2app.actions.UEReturnAction();
		context.put("returnAction", bean);
		bean.setGoComponent(getMainGrandlistView());
		bean.setSaveAction(getSaveAction());
		bean.setModel(getBmModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.TangramContainer getContainer() {
		if (context.get("container") != null)
			return (nc.ui.uif2.TangramContainer) context.get("container");
		nc.ui.uif2.TangramContainer bean = new nc.ui.uif2.TangramContainer();
		context.put("container", bean);
		bean.setModel(getBmModel());
		bean.setTangramLayoutRoot(getTBNode_1c21d64());
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.TBNode getTBNode_1c21d64() {
		if (context.get("nc.ui.uif2.tangramlayout.node.TBNode#1c21d64") != null)
			return (nc.ui.uif2.tangramlayout.node.TBNode) context.get("nc.ui.uif2.tangramlayout.node.TBNode#1c21d64");
		nc.ui.uif2.tangramlayout.node.TBNode bean = new nc.ui.uif2.tangramlayout.node.TBNode();
		context.put("nc.ui.uif2.tangramlayout.node.TBNode#1c21d64", bean);
		bean.setShowMode("CardLayout");
		bean.setTabs(getManagedList12());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList12() {
		List list = new ArrayList();
		list.add(getVSNode_1b7c6b7());
		list.add(getVSNode_1d21e48());
		return list;
	}

	private nc.ui.uif2.tangramlayout.node.VSNode getVSNode_1b7c6b7() {
		if (context.get("nc.ui.uif2.tangramlayout.node.VSNode#1b7c6b7") != null)
			return (nc.ui.uif2.tangramlayout.node.VSNode) context.get("nc.ui.uif2.tangramlayout.node.VSNode#1b7c6b7");
		nc.ui.uif2.tangramlayout.node.VSNode bean = new nc.ui.uif2.tangramlayout.node.VSNode();
		context.put("nc.ui.uif2.tangramlayout.node.VSNode#1b7c6b7", bean);
		bean.setShowMode("NoDivider");
		bean.setUp(getCNode_cc6767());
		bean.setDown(getHSNode_4636cc());
		bean.setDividerLocation(30f);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.CNode getCNode_cc6767() {
		if (context.get("nc.ui.uif2.tangramlayout.node.CNode#cc6767") != null)
			return (nc.ui.uif2.tangramlayout.node.CNode) context.get("nc.ui.uif2.tangramlayout.node.CNode#cc6767");
		nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
		context.put("nc.ui.uif2.tangramlayout.node.CNode#cc6767", bean);
		bean.setComponent(getOrgpanel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.HSNode getHSNode_4636cc() {
		if (context.get("nc.ui.uif2.tangramlayout.node.HSNode#4636cc") != null)
			return (nc.ui.uif2.tangramlayout.node.HSNode) context.get("nc.ui.uif2.tangramlayout.node.HSNode#4636cc");
		nc.ui.uif2.tangramlayout.node.HSNode bean = new nc.ui.uif2.tangramlayout.node.HSNode();
		context.put("nc.ui.uif2.tangramlayout.node.HSNode#4636cc", bean);
		bean.setLeft(getCNode_198a4d5());
		bean.setRight(getVSNode_72076f());
		bean.setDividerLocation(210f);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.CNode getCNode_198a4d5() {
		if (context.get("nc.ui.uif2.tangramlayout.node.CNode#198a4d5") != null)
			return (nc.ui.uif2.tangramlayout.node.CNode) context.get("nc.ui.uif2.tangramlayout.node.CNode#198a4d5");
		nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
		context.put("nc.ui.uif2.tangramlayout.node.CNode#198a4d5", bean);
		bean.setComponent(getQueryArea());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.VSNode getVSNode_72076f() {
		if (context.get("nc.ui.uif2.tangramlayout.node.VSNode#72076f") != null)
			return (nc.ui.uif2.tangramlayout.node.VSNode) context.get("nc.ui.uif2.tangramlayout.node.VSNode#72076f");
		nc.ui.uif2.tangramlayout.node.VSNode bean = new nc.ui.uif2.tangramlayout.node.VSNode();
		context.put("nc.ui.uif2.tangramlayout.node.VSNode#72076f", bean);
		bean.setUp(getCNode_6db148());
		bean.setDown(getCNode_d40094());
		bean.setDividerLocation(25f);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.CNode getCNode_6db148() {
		if (context.get("nc.ui.uif2.tangramlayout.node.CNode#6db148") != null)
			return (nc.ui.uif2.tangramlayout.node.CNode) context.get("nc.ui.uif2.tangramlayout.node.CNode#6db148");
		nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
		context.put("nc.ui.uif2.tangramlayout.node.CNode#6db148", bean);
		bean.setComponent(getQueryInfo());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.CNode getCNode_d40094() {
		if (context.get("nc.ui.uif2.tangramlayout.node.CNode#d40094") != null)
			return (nc.ui.uif2.tangramlayout.node.CNode) context.get("nc.ui.uif2.tangramlayout.node.CNode#d40094");
		nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
		context.put("nc.ui.uif2.tangramlayout.node.CNode#d40094", bean);
		bean.setName(getI18nFB_192217a());
		bean.setComponent(getMainGrandlistView());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_192217a() {
		if (context.get("nc.ui.uif2.I18nFB#192217a") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#192217a");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#192217a", bean);
		bean.setResDir("common");
		bean.setResId("UC001-0000107");
		bean.setDefaultValue("列表");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#192217a", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private nc.ui.uif2.tangramlayout.node.VSNode getVSNode_1d21e48() {
		if (context.get("nc.ui.uif2.tangramlayout.node.VSNode#1d21e48") != null)
			return (nc.ui.uif2.tangramlayout.node.VSNode) context.get("nc.ui.uif2.tangramlayout.node.VSNode#1d21e48");
		nc.ui.uif2.tangramlayout.node.VSNode bean = new nc.ui.uif2.tangramlayout.node.VSNode();
		context.put("nc.ui.uif2.tangramlayout.node.VSNode#1d21e48", bean);
		bean.setUp(getCNode_572fd3());
		bean.setDown(getCNode_1a6d4a3());
		bean.setDividerLocation(30f);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.CNode getCNode_572fd3() {
		if (context.get("nc.ui.uif2.tangramlayout.node.CNode#572fd3") != null)
			return (nc.ui.uif2.tangramlayout.node.CNode) context.get("nc.ui.uif2.tangramlayout.node.CNode#572fd3");
		nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
		context.put("nc.ui.uif2.tangramlayout.node.CNode#572fd3", bean);
		bean.setComponent(getViewb());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.CNode getCNode_1a6d4a3() {
		if (context.get("nc.ui.uif2.tangramlayout.node.CNode#1a6d4a3") != null)
			return (nc.ui.uif2.tangramlayout.node.CNode) context.get("nc.ui.uif2.tangramlayout.node.CNode#1a6d4a3");
		nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
		context.put("nc.ui.uif2.tangramlayout.node.CNode#1a6d4a3", bean);
		bean.setName(getI18nFB_5056b());
		bean.setComponent(getMainGrandbillForm());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_5056b() {
		if (context.get("nc.ui.uif2.I18nFB#5056b") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#5056b");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#5056b", bean);
		bean.setResDir("common");
		bean.setResId("UC001-0000106");
		bean.setDefaultValue("卡片");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#5056b", product);
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
		bean.setBillform(getBillForm());
		bean.setBodyVoClasses(getManagedList13());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList13() {
		List list = new ArrayList();
		list.add("nc.vo.qcco.commission.CommissionBVO");
		return list;
	}

	public nc.ui.uif2.actions.ActionContributors getToftpanelActionContributors() {
		if (context.get("toftpanelActionContributors") != null)
			return (nc.ui.uif2.actions.ActionContributors) context.get("toftpanelActionContributors");
		nc.ui.uif2.actions.ActionContributors bean = new nc.ui.uif2.actions.ActionContributors();
		context.put("toftpanelActionContributors", bean);
		bean.setContributors(getManagedList14());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList14() {
		List list = new ArrayList();
		list.add(getActionsOfList());
		list.add(getActionsOfCard());
		return list;
	}

	public nc.ui.uif2.actions.StandAloneToftPanelActionContainer getActionsOfList() {
		if (context.get("actionsOfList") != null)
			return (nc.ui.uif2.actions.StandAloneToftPanelActionContainer) context.get("actionsOfList");
		nc.ui.uif2.actions.StandAloneToftPanelActionContainer bean = new nc.ui.uif2.actions.StandAloneToftPanelActionContainer(
				getBillListView());
		context.put("actionsOfList", bean);
		bean.setModel(getBmModel());
		bean.setActions(getManagedList15());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList15() {
		List list = new ArrayList();
		list.add(getAddAction());
		list.add(getEditAction());
		list.add(getDeleteAction());
		list.add(getCopyAction());
		list.add(getSeparatorAction());
		list.add(getQueryAction());
		list.add(getRefreshAction());
		list.add(getSeparatorAction());
		list.add(getPrintMenuAction());
		list.add(getSeparatorAction());
		list.add(getEditTaskAction());
		list.add(getSeparatorAction());
		list.add(getQuotationAction());
		list.add(getPayDemandAction());
		list.add(getOfficialReportAction());
		list.add(getSatisfactionAction());
		list.add(getSeparatorAction());
		list.add(getChangeAction());
		list.add(getSeparatorAction());
		return list;
	}

	public nc.ui.uif2.actions.StandAloneToftPanelActionContainer getActionsOfCard() {
		if (context.get("actionsOfCard") != null)
			return (nc.ui.uif2.actions.StandAloneToftPanelActionContainer) context.get("actionsOfCard");
		nc.ui.uif2.actions.StandAloneToftPanelActionContainer bean = new nc.ui.uif2.actions.StandAloneToftPanelActionContainer(
				getBillForm());
		context.put("actionsOfCard", bean);
		bean.setModel(getBmModel());
		bean.setActions(getManagedList16());
		bean.setEditActions(getManagedList17());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList16() {
		List list = new ArrayList();
		list.add(getAddAction());
		list.add(getEditAction());
		list.add(getDeleteAction());
		list.add(getCopyAction());
		list.add(getSeparatorAction());
		list.add(getQueryAction());
		list.add(getCardRefreshAction());
		list.add(getSeparatorAction());
		list.add(getPrintMenuAction());
		list.add(getSeparatorAction());
		list.add(getEditTaskAction());
		list.add(getSeparatorAction());
		list.add(getCommissionPreviewAction());
		list.add(getQuotationAction());
		list.add(getPayDemandAction());
		list.add(getOfficialReportAction());
		list.add(getSatisfactionAction());
		list.add(getSeparatorAction());
		list.add(getChangeAction());
		list.add(getSeparatorAction());
		return list;
	}

	private List getManagedList17() {
		List list = new ArrayList();
		list.add(getSaveAction());
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
		bean.setShowUpComponent(getMainGrandbillForm());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.actions.AddAction getAddAction() {
		if (context.get("addAction") != null)
			return (nc.ui.uif2.actions.AddAction) context.get("addAction");
		nc.ui.uif2.actions.AddAction bean = new nc.ui.uif2.actions.AddAction();
		context.put("addAction", bean);
		bean.setModel(getBmModel());
		bean.setInterceptor(getShowCardInterceptor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.qcco.commission.action.CommissionEditAction getEditAction() {
		if (context.get("editAction") != null)
			return (nc.ui.qcco.commission.action.CommissionEditAction) context.get("editAction");
		nc.ui.qcco.commission.action.CommissionEditAction bean = new nc.ui.qcco.commission.action.CommissionEditAction();
		context.put("editAction", bean);
		bean.setModel(getBmModel());
		bean.setInterceptor(getShowCardInterceptor());
		bean.setMainGrandPanel(getMainGrandbillForm());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.qcco.commission.action.ChangeAction getChangeAction() {
		if (context.get("changeAction") != null)
			return (nc.ui.qcco.commission.action.ChangeAction) context.get("changeAction");
		nc.ui.qcco.commission.action.ChangeAction bean = new nc.ui.qcco.commission.action.ChangeAction();
		context.put("changeAction", bean);
		bean.setModel(getBmModel());
		bean.setInterceptor(getShowCardInterceptor());
		bean.setMainGrandPanel(getMainGrandbillForm());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.qcco.commission.action.ConfirmAction getConfirmAction() {
		if (context.get("confirmAction") != null)
			return (nc.ui.qcco.commission.action.ConfirmAction) context.get("confirmAction");
		nc.ui.qcco.commission.action.ConfirmAction bean = new nc.ui.qcco.commission.action.ConfirmAction();
		context.put("confirmAction", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.qcco.commission.action.CommissionDeleteAction getDeleteAction() {
		if (context.get("deleteAction") != null)
			return (nc.ui.qcco.commission.action.CommissionDeleteAction) context.get("deleteAction");
		nc.ui.qcco.commission.action.CommissionDeleteAction bean = new nc.ui.qcco.commission.action.CommissionDeleteAction();
		context.put("deleteAction", bean);
		bean.setModel(getBmModel());
		bean.setBillType("QC07");
		bean.setFilledUpInFlow(true);
		bean.setActionName("DELETE");
		bean.setMainGrandModel(getMainGrandModel());
		bean.setSingleBillService(getDeleteProxy());
		bean.setExceptionHandler(getExceptionHandler());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.qcco.commission.action.CommissionSaveAction getSaveAction() {
		if (context.get("saveAction") != null)
			return (nc.ui.qcco.commission.action.CommissionSaveAction) context.get("saveAction");
		nc.ui.qcco.commission.action.CommissionSaveAction bean = new nc.ui.qcco.commission.action.CommissionSaveAction();
		context.put("saveAction", bean);
		bean.setModel(getBmModel());
		bean.setService(getBmModelModelService());
		bean.setEditor(getBillForm());
		bean.setMainGrandModel(getMainGrandModel());
		bean.setBillFormEditor(getBillForm());
		bean.setBillForm(getMainGrandbillForm());
		bean.setValidationService(getValidateService());
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
		bean.setValidators(getManagedList18());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList18() {
		List list = new ArrayList();
		list.add(getTemplateNotNullValidation_117bb31());
		return list;
	}

	private nc.ui.pubapp.uif2app.validation.TemplateNotNullValidation getTemplateNotNullValidation_117bb31() {
		if (context.get("nc.ui.pubapp.uif2app.validation.TemplateNotNullValidation#117bb31") != null)
			return (nc.ui.pubapp.uif2app.validation.TemplateNotNullValidation) context
					.get("nc.ui.pubapp.uif2app.validation.TemplateNotNullValidation#117bb31");
		nc.ui.pubapp.uif2app.validation.TemplateNotNullValidation bean = new nc.ui.pubapp.uif2app.validation.TemplateNotNullValidation();
		context.put("nc.ui.pubapp.uif2app.validation.TemplateNotNullValidation#117bb31", bean);
		bean.setBillForm(getBillForm());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.qcco.commission.action.CommissionCancelAction getCancelAction() {
		if (context.get("cancelAction") != null)
			return (nc.ui.qcco.commission.action.CommissionCancelAction) context.get("cancelAction");
		nc.ui.qcco.commission.action.CommissionCancelAction bean = new nc.ui.qcco.commission.action.CommissionCancelAction();
		context.put("cancelAction", bean);
		bean.setModel(getBmModel());
		bean.setMainGrandPanel(getMainGrandbillForm());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.qcco.commission.action.CommissionCopyAction getCopyAction() {
		if (context.get("copyAction") != null)
			return (nc.ui.qcco.commission.action.CommissionCopyAction) context.get("copyAction");
		nc.ui.qcco.commission.action.CommissionCopyAction bean = new nc.ui.qcco.commission.action.CommissionCopyAction();
		context.put("copyAction", bean);
		bean.setModel(getBmModel());
		bean.setInterceptor(getShowCardInterceptor());
		bean.setEditor(getBillForm());
		bean.setBillForm(getMainGrandbillForm());
		bean.setGrandCard(getSunbillForm1());
		bean.setCopyActionProcessor(getCommissionCopyActionProcessor_102c5eb());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.qcco.commission.action.CommissionCopyActionProcessor getCommissionCopyActionProcessor_102c5eb() {
		if (context.get("nc.ui.qcco.commission.action.CommissionCopyActionProcessor#102c5eb") != null)
			return (nc.ui.qcco.commission.action.CommissionCopyActionProcessor) context
					.get("nc.ui.qcco.commission.action.CommissionCopyActionProcessor#102c5eb");
		nc.ui.qcco.commission.action.CommissionCopyActionProcessor bean = new nc.ui.qcco.commission.action.CommissionCopyActionProcessor();
		context.put("nc.ui.qcco.commission.action.CommissionCopyActionProcessor#102c5eb", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction getRefreshAction() {
		if (context.get("refreshAction") != null)
			return (nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction) context.get("refreshAction");
		nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction bean = new nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction();
		context.put("refreshAction", bean);
		bean.setDataManager(getBmModelModelDataManager());
		bean.setModel(getBmModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.RefreshSingleAction getCardRefreshAction() {
		if (context.get("cardRefreshAction") != null)
			return (nc.ui.pubapp.uif2app.actions.RefreshSingleAction) context.get("cardRefreshAction");
		nc.ui.pubapp.uif2app.actions.RefreshSingleAction bean = new nc.ui.pubapp.uif2app.actions.RefreshSingleAction();
		context.put("cardRefreshAction", bean);
		bean.setModel(getBmModel());
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
		bean.setModel(getBmModel());
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
		bean.setModel(getBmModel());
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
		bean.setModel(getBmModel());
		bean.setParent(getBillForm());
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
		bean.setName(getI18nFB_10fe070());
		bean.setActions(getManagedList19());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_10fe070() {
		if (context.get("nc.ui.uif2.I18nFB#10fe070") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#10fe070");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#10fe070", bean);
		bean.setResDir("common");
		bean.setResId("UC001-0000007");
		bean.setDefaultValue("打印");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#10fe070", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private List getManagedList19() {
		List list = new ArrayList();
		list.add(getPrintAction());
		list.add(getPreviewAction());
		list.add(getOutputAction());
		return list;
	}

	public nc.ui.qcco.commission.action.CommissionPreviewAction getCommissionPreviewAction() {
		if (context.get("commissionPreviewAction") != null)
			return (nc.ui.qcco.commission.action.CommissionPreviewAction) context.get("commissionPreviewAction");
		nc.ui.qcco.commission.action.CommissionPreviewAction bean = new nc.ui.qcco.commission.action.CommissionPreviewAction();
		context.put("commissionPreviewAction", bean);
		bean.setModel(getBmModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.qcco.commission.action.QuotationAction getQuotationAction() {
		if (context.get("quotationAction") != null)
			return (nc.ui.qcco.commission.action.QuotationAction) context.get("quotationAction");
		nc.ui.qcco.commission.action.QuotationAction bean = new nc.ui.qcco.commission.action.QuotationAction();
		context.put("quotationAction", bean);
		bean.setModel(getBmModel());
		bean.setListView(getMainGrandlistView());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.qcco.commission.action.PayDemandAction getPayDemandAction() {
		if (context.get("payDemandAction") != null)
			return (nc.ui.qcco.commission.action.PayDemandAction) context.get("payDemandAction");
		nc.ui.qcco.commission.action.PayDemandAction bean = new nc.ui.qcco.commission.action.PayDemandAction();
		context.put("payDemandAction", bean);
		bean.setModel(getBmModel());
		bean.setListView(getMainGrandlistView());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.qcco.commission.action.OfficialReportAction getOfficialReportAction() {
		if (context.get("officialReportAction") != null)
			return (nc.ui.qcco.commission.action.OfficialReportAction) context.get("officialReportAction");
		nc.ui.qcco.commission.action.OfficialReportAction bean = new nc.ui.qcco.commission.action.OfficialReportAction();
		context.put("officialReportAction", bean);
		bean.setModel(getBmModel());
		bean.setListView(getMainGrandlistView());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.qcco.commission.action.SatisfactionAction getSatisfactionAction() {
		if (context.get("satisfactionAction") != null)
			return (nc.ui.qcco.commission.action.SatisfactionAction) context.get("satisfactionAction");
		nc.ui.qcco.commission.action.SatisfactionAction bean = new nc.ui.qcco.commission.action.SatisfactionAction();
		context.put("satisfactionAction", bean);
		bean.setModel(getBmModel());
		bean.setListView(getMainGrandlistView());
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
		bean.setActions(getManagedList20());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList20() {
		List list = new ArrayList();
		list.add(getQuotationAction());
		list.add(getPayDemandAction());
		list.add(getOfficialReportAction());
		list.add(getSatisfactionAction());
		return list;
	}

	public nc.ui.pubapp.uif2app.model.BillBodySortMediator getBillBodySortMediator() {
		if (context.get("billBodySortMediator") != null)
			return (nc.ui.pubapp.uif2app.model.BillBodySortMediator) context.get("billBodySortMediator");
		nc.ui.pubapp.uif2app.model.BillBodySortMediator bean = new nc.ui.pubapp.uif2app.model.BillBodySortMediator(getBmModel(),
				getBillForm(), getBillListView());
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
		bean.setModel(getBmModel());
		bean.setContext(getContext());
		bean.setVoClassName("nc.vo.qcco.commission.AggCommissionHVO");
		bean.setAutoShowUpComponent(getBillForm());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.common.validateservice.ClosingCheck getClosingListener() {
		if (context.get("ClosingListener") != null)
			return (nc.ui.pubapp.common.validateservice.ClosingCheck) context.get("ClosingListener");
		nc.ui.pubapp.common.validateservice.ClosingCheck bean = new nc.ui.pubapp.common.validateservice.ClosingCheck();
		context.put("ClosingListener", bean);
		bean.setModel(getBmModel());
		bean.setSaveAction(getSaveAction());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.model.AppEventHandlerMediator getBmModelEventMediator() {
		if (context.get("bmModelEventMediator") != null)
			return (nc.ui.pubapp.uif2app.model.AppEventHandlerMediator) context.get("bmModelEventMediator");
		nc.ui.pubapp.uif2app.model.AppEventHandlerMediator bean = new nc.ui.pubapp.uif2app.model.AppEventHandlerMediator();
		context.put("bmModelEventMediator", bean);
		bean.setModel(getBmModel());
		bean.setHandlerGroup(getManagedList21());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList21() {
		List list = new ArrayList();
		list.add(getEventHandlerGroup_5910c9());
		list.add(getEventHandlerGroup_fb00ce());
		list.add(getEventHandlerGroup_49a5c());
		list.add(getEventHandlerGroup_16bebd7());
		list.add(getEventHandlerGroup_126c233());
		list.add(getEventHandlerGroup_87b188());
		return list;
	}

	private nc.ui.pubapp.uif2app.event.EventHandlerGroup getEventHandlerGroup_5910c9() {
		if (context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#5910c9") != null)
			return (nc.ui.pubapp.uif2app.event.EventHandlerGroup) context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#5910c9");
		nc.ui.pubapp.uif2app.event.EventHandlerGroup bean = new nc.ui.pubapp.uif2app.event.EventHandlerGroup();
		context.put("nc.ui.pubapp.uif2app.event.EventHandlerGroup#5910c9", bean);
		bean.setEvent("nc.ui.pubapp.uif2app.event.OrgChangedEvent");
		bean.setHandler(getAceOrgChangeHandler_157f02c());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.qcco.commission.ace.handler.AceOrgChangeHandler getAceOrgChangeHandler_157f02c() {
		if (context.get("nc.ui.qcco.commission.ace.handler.AceOrgChangeHandler#157f02c") != null)
			return (nc.ui.qcco.commission.ace.handler.AceOrgChangeHandler) context
					.get("nc.ui.qcco.commission.ace.handler.AceOrgChangeHandler#157f02c");
		nc.ui.qcco.commission.ace.handler.AceOrgChangeHandler bean = new nc.ui.qcco.commission.ace.handler.AceOrgChangeHandler();
		context.put("nc.ui.qcco.commission.ace.handler.AceOrgChangeHandler#157f02c", bean);
		bean.setBillForm(getBillForm());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.event.EventHandlerGroup getEventHandlerGroup_fb00ce() {
		if (context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#fb00ce") != null)
			return (nc.ui.pubapp.uif2app.event.EventHandlerGroup) context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#fb00ce");
		nc.ui.pubapp.uif2app.event.EventHandlerGroup bean = new nc.ui.pubapp.uif2app.event.EventHandlerGroup();
		context.put("nc.ui.pubapp.uif2app.event.EventHandlerGroup#fb00ce", bean);
		bean.setEvent("nc.ui.pubapp.uif2app.event.billform.AddEvent");
		bean.setHandler(getAceAddHandler_15d10d0());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.qcco.commission.ace.handler.AceAddHandler getAceAddHandler_15d10d0() {
		if (context.get("nc.ui.qcco.commission.ace.handler.AceAddHandler#15d10d0") != null)
			return (nc.ui.qcco.commission.ace.handler.AceAddHandler) context.get("nc.ui.qcco.commission.ace.handler.AceAddHandler#15d10d0");
		nc.ui.qcco.commission.ace.handler.AceAddHandler bean = new nc.ui.qcco.commission.ace.handler.AceAddHandler();
		context.put("nc.ui.qcco.commission.ace.handler.AceAddHandler#15d10d0", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.event.EventHandlerGroup getEventHandlerGroup_49a5c() {
		if (context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#49a5c") != null)
			return (nc.ui.pubapp.uif2app.event.EventHandlerGroup) context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#49a5c");
		nc.ui.pubapp.uif2app.event.EventHandlerGroup bean = new nc.ui.pubapp.uif2app.event.EventHandlerGroup();
		context.put("nc.ui.pubapp.uif2app.event.EventHandlerGroup#49a5c", bean);
		bean.setEvent("nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent");
		bean.setHandler(getAceHeadTailBeforeEditHandler_f1f8ca());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.qcco.commission.ace.handler.AceHeadTailBeforeEditHandler getAceHeadTailBeforeEditHandler_f1f8ca() {
		if (context.get("nc.ui.qcco.commission.ace.handler.AceHeadTailBeforeEditHandler#f1f8ca") != null)
			return (nc.ui.qcco.commission.ace.handler.AceHeadTailBeforeEditHandler) context
					.get("nc.ui.qcco.commission.ace.handler.AceHeadTailBeforeEditHandler#f1f8ca");
		nc.ui.qcco.commission.ace.handler.AceHeadTailBeforeEditHandler bean = new nc.ui.qcco.commission.ace.handler.AceHeadTailBeforeEditHandler();
		context.put("nc.ui.qcco.commission.ace.handler.AceHeadTailBeforeEditHandler#f1f8ca", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.event.EventHandlerGroup getEventHandlerGroup_16bebd7() {
		if (context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#16bebd7") != null)
			return (nc.ui.pubapp.uif2app.event.EventHandlerGroup) context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#16bebd7");
		nc.ui.pubapp.uif2app.event.EventHandlerGroup bean = new nc.ui.pubapp.uif2app.event.EventHandlerGroup();
		context.put("nc.ui.pubapp.uif2app.event.EventHandlerGroup#16bebd7", bean);
		bean.setEvent("nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent");
		bean.setHandler(getAceHeadTailAfterEditHandler_3231ed());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.qcco.commission.ace.handler.AceHeadTailAfterEditHandler getAceHeadTailAfterEditHandler_3231ed() {
		if (context.get("nc.ui.qcco.commission.ace.handler.AceHeadTailAfterEditHandler#3231ed") != null)
			return (nc.ui.qcco.commission.ace.handler.AceHeadTailAfterEditHandler) context
					.get("nc.ui.qcco.commission.ace.handler.AceHeadTailAfterEditHandler#3231ed");
		nc.ui.qcco.commission.ace.handler.AceHeadTailAfterEditHandler bean = new nc.ui.qcco.commission.ace.handler.AceHeadTailAfterEditHandler();
		context.put("nc.ui.qcco.commission.ace.handler.AceHeadTailAfterEditHandler#3231ed", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.event.EventHandlerGroup getEventHandlerGroup_126c233() {
		if (context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#126c233") != null)
			return (nc.ui.pubapp.uif2app.event.EventHandlerGroup) context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#126c233");
		nc.ui.pubapp.uif2app.event.EventHandlerGroup bean = new nc.ui.pubapp.uif2app.event.EventHandlerGroup();
		context.put("nc.ui.pubapp.uif2app.event.EventHandlerGroup#126c233", bean);
		bean.setEvent("nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent");
		bean.setHandler(getAceBodyBeforeEditHandler_4894e2());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.qcco.commission.ace.handler.AceBodyBeforeEditHandler getAceBodyBeforeEditHandler_4894e2() {
		if (context.get("nc.ui.qcco.commission.ace.handler.AceBodyBeforeEditHandler#4894e2") != null)
			return (nc.ui.qcco.commission.ace.handler.AceBodyBeforeEditHandler) context
					.get("nc.ui.qcco.commission.ace.handler.AceBodyBeforeEditHandler#4894e2");
		nc.ui.qcco.commission.ace.handler.AceBodyBeforeEditHandler bean = new nc.ui.qcco.commission.ace.handler.AceBodyBeforeEditHandler();
		context.put("nc.ui.qcco.commission.ace.handler.AceBodyBeforeEditHandler#4894e2", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.event.EventHandlerGroup getEventHandlerGroup_87b188() {
		if (context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#87b188") != null)
			return (nc.ui.pubapp.uif2app.event.EventHandlerGroup) context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#87b188");
		nc.ui.pubapp.uif2app.event.EventHandlerGroup bean = new nc.ui.pubapp.uif2app.event.EventHandlerGroup();
		context.put("nc.ui.pubapp.uif2app.event.EventHandlerGroup#87b188", bean);
		bean.setEvent("nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent");
		bean.setHandler(getAceBodyAfterEditHandler_197cadd());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.qcco.commission.ace.handler.AceBodyAfterEditHandler getAceBodyAfterEditHandler_197cadd() {
		if (context.get("nc.ui.qcco.commission.ace.handler.AceBodyAfterEditHandler#197cadd") != null)
			return (nc.ui.qcco.commission.ace.handler.AceBodyAfterEditHandler) context
					.get("nc.ui.qcco.commission.ace.handler.AceBodyAfterEditHandler#197cadd");
		nc.ui.qcco.commission.ace.handler.AceBodyAfterEditHandler bean = new nc.ui.qcco.commission.ace.handler.AceBodyAfterEditHandler();
		context.put("nc.ui.qcco.commission.ace.handler.AceBodyAfterEditHandler#197cadd", bean);
		bean.setGrandCard(getSunbillForm1());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
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

	public nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager getBmModelLasilyLodadMediator() {
		if (context.get("bmModelLasilyLodadMediator") != null)
			return (nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager) context.get("bmModelLasilyLodadMediator");
		nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager bean = new nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager();
		context.put("bmModelLasilyLodadMediator", bean);
		bean.setModel(getBmModel());
		bean.setLoader(getBillLazilyLoader());
		bean.setLazilyLoadSupporter(getManagedList22());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList22() {
		List list = new ArrayList();
		list.add(getCardPanelLazilyLoad_98872d());
		list.add(getListPanelLazilyLoad_1d2c4b1());
		return list;
	}

	private nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad getCardPanelLazilyLoad_98872d() {
		if (context.get("nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad#98872d") != null)
			return (nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad) context
					.get("nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad#98872d");
		nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad bean = new nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad();
		context.put("nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad#98872d", bean);
		bean.setBillform(getBillForm());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad getListPanelLazilyLoad_1d2c4b1() {
		if (context.get("nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad#1d2c4b1") != null)
			return (nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad) context
					.get("nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad#1d2c4b1");
		nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad bean = new nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad();
		context.put("nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad#1d2c4b1", bean);
		bean.setListView(getBillListView());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.view.RowNoMediator getRowNoMediator() {
		if (context.get("rowNoMediator") != null)
			return (nc.ui.pubapp.uif2app.view.RowNoMediator) context.get("rowNoMediator");
		nc.ui.pubapp.uif2app.view.RowNoMediator bean = new nc.ui.pubapp.uif2app.view.RowNoMediator();
		context.put("rowNoMediator", bean);
		bean.setModel(getBmModel());
		bean.setEditor(getBillForm());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator getMouseClickShowPanelMediator() {
		if (context.get("mouseClickShowPanelMediator") != null)
			return (nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator) context.get("mouseClickShowPanelMediator");
		nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator bean = new nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator();
		context.put("mouseClickShowPanelMediator", bean);
		bean.setListView(getBillListView());
		bean.setShowUpComponent(getMainGrandbillForm());
		bean.setHyperLinkColumn("null");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.editor.QueryTemplateContainer getDefaultQueryActionQueryTemplateContainer() {
		if (context.get("defaultQueryActionQueryTemplateContainer") != null)
			return (nc.ui.uif2.editor.QueryTemplateContainer) context.get("defaultQueryActionQueryTemplateContainer");
		nc.ui.uif2.editor.QueryTemplateContainer bean = new nc.ui.uif2.editor.QueryTemplateContainer();
		context.put("defaultQueryActionQueryTemplateContainer", bean);
		bean.setNodeKey("qt");
		bean.setContext(getContext());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction getQueryAction() {
		if (context.get("queryAction") != null)
			return (nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction) context.get("queryAction");
		nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction bean = new nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction();
		context.put("queryAction", bean);
		bean.setModel(getBmModel());
		bean.setTemplateContainer(getDefaultQueryActionQueryTemplateContainer());
		bean.setNodeKey("qt");
		bean.setDataManager(getBmModelModelDataManager());
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
		bean.setModel(getBmModel());
		bean.setActioncode("Preview");
		bean.setActionname("预览");
		bean.setPreview(true);
		bean.setNodeKey("ot");
		bean.setExceptionHandler(getExceptionHandler());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction getMetaDataBasedPrintActiona() {
		if (context.get("metaDataBasedPrintActiona") != null)
			return (nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction) context.get("metaDataBasedPrintActiona");
		nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction bean = new nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction();
		context.put("metaDataBasedPrintActiona", bean);
		bean.setModel(getBmModel());
		bean.setActioncode("Print");
		bean.setActionname("打印");
		bean.setPreview(false);
		bean.setNodeKey("ot");
		bean.setExceptionHandler(getExceptionHandler());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.pflow.SaveScriptAction getSaveScriptAction() {
		if (context.get("saveScriptAction") != null)
			return (nc.ui.pubapp.uif2app.actions.pflow.SaveScriptAction) context.get("saveScriptAction");
		nc.ui.pubapp.uif2app.actions.pflow.SaveScriptAction bean = new nc.ui.pubapp.uif2app.actions.pflow.SaveScriptAction();
		context.put("saveScriptAction", bean);
		bean.setModel(getBmModel());
		bean.setEditor(getBillForm());
		bean.setBillType("QC07");
		bean.setFilledUpInFlow(true);
		bean.setActionName("SAVEBASE");
		bean.setExceptionHandler(getExceptionHandler());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.qcco.commission.action.CheckAllAction getCheckAllAction() {
		if (context.get("checkAllAction") != null)
			return (nc.ui.qcco.commission.action.CheckAllAction) context.get("checkAllAction");
		nc.ui.qcco.commission.action.CheckAllAction bean = new nc.ui.qcco.commission.action.CheckAllAction();
		context.put("checkAllAction", bean);
		bean.setBillListPanel(getBillListView());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.qcco.commission.action.EditTaskAction getEditTaskAction() {
		if (context.get("editTaskAction") != null)
			return (nc.ui.qcco.commission.action.EditTaskAction) context.get("editTaskAction");
		nc.ui.qcco.commission.action.EditTaskAction bean = new nc.ui.qcco.commission.action.EditTaskAction();
		context.put("editTaskAction", bean);
		bean.setBillListPanel(getBillListView());
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

	public nc.ui.pubapp.uif2app.view.FractionFixMediator getFractionFixMediator() {
		if (context.get("fractionFixMediator") != null)
			return (nc.ui.pubapp.uif2app.view.FractionFixMediator) context.get("fractionFixMediator");
		nc.ui.pubapp.uif2app.view.FractionFixMediator bean = new nc.ui.pubapp.uif2app.view.FractionFixMediator(getBillForm());
		context.put("fractionFixMediator", bean);
		bean.initUI();
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

}
