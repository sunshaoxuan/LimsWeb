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
		bean.setGrandString("ʵ��ǰ����");
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
		bean.setGrandString("ʵ��ǰ����");
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
		list.add(getSelectAllLineAction_7d64a5());
		list.add(getSelectNoneLineAction_5ab8ff());
		list.add(getGrandBodyAddLineAction_278f61());
		list.add(getBodyInsertLineAction_136536a());
		list.add(getBodyDelLineAction_19e6635());
		list.add(getBodyCopyLineAction_484441());
		list.add(getBodyPasteToTailAction_7932a1());
		list.add(getBodyLineEditAction_eb135a());
		return list;
	}

	private nc.ui.qcco.commission.action.SelectAllLineAction getSelectAllLineAction_7d64a5() {
		if (context.get("nc.ui.qcco.commission.action.SelectAllLineAction#7d64a5") != null)
			return (nc.ui.qcco.commission.action.SelectAllLineAction) context
					.get("nc.ui.qcco.commission.action.SelectAllLineAction#7d64a5");
		nc.ui.qcco.commission.action.SelectAllLineAction bean = new nc.ui.qcco.commission.action.SelectAllLineAction();
		context.put("nc.ui.qcco.commission.action.SelectAllLineAction#7d64a5", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.qcco.commission.action.SelectNoneLineAction getSelectNoneLineAction_5ab8ff() {
		if (context.get("nc.ui.qcco.commission.action.SelectNoneLineAction#5ab8ff") != null)
			return (nc.ui.qcco.commission.action.SelectNoneLineAction) context
					.get("nc.ui.qcco.commission.action.SelectNoneLineAction#5ab8ff");
		nc.ui.qcco.commission.action.SelectNoneLineAction bean = new nc.ui.qcco.commission.action.SelectNoneLineAction();
		context.put("nc.ui.qcco.commission.action.SelectNoneLineAction#5ab8ff", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.components.grand.action.GrandBodyAddLineAction getGrandBodyAddLineAction_278f61() {
		if (context.get("nc.ui.pubapp.uif2app.components.grand.action.GrandBodyAddLineAction#278f61") != null)
			return (nc.ui.pubapp.uif2app.components.grand.action.GrandBodyAddLineAction) context
					.get("nc.ui.pubapp.uif2app.components.grand.action.GrandBodyAddLineAction#278f61");
		nc.ui.pubapp.uif2app.components.grand.action.GrandBodyAddLineAction bean = new nc.ui.pubapp.uif2app.components.grand.action.GrandBodyAddLineAction();
		context.put("nc.ui.pubapp.uif2app.components.grand.action.GrandBodyAddLineAction#278f61", bean);
		bean.setMainForm(getBillForm());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.actions.BodyInsertLineAction getBodyInsertLineAction_136536a() {
		if (context.get("nc.ui.pubapp.uif2app.actions.BodyInsertLineAction#136536a") != null)
			return (nc.ui.pubapp.uif2app.actions.BodyInsertLineAction) context
					.get("nc.ui.pubapp.uif2app.actions.BodyInsertLineAction#136536a");
		nc.ui.pubapp.uif2app.actions.BodyInsertLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyInsertLineAction();
		context.put("nc.ui.pubapp.uif2app.actions.BodyInsertLineAction#136536a", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.actions.BodyDelLineAction getBodyDelLineAction_19e6635() {
		if (context.get("nc.ui.pubapp.uif2app.actions.BodyDelLineAction#19e6635") != null)
			return (nc.ui.pubapp.uif2app.actions.BodyDelLineAction) context.get("nc.ui.pubapp.uif2app.actions.BodyDelLineAction#19e6635");
		nc.ui.pubapp.uif2app.actions.BodyDelLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyDelLineAction();
		context.put("nc.ui.pubapp.uif2app.actions.BodyDelLineAction#19e6635", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.actions.BodyCopyLineAction getBodyCopyLineAction_484441() {
		if (context.get("nc.ui.pubapp.uif2app.actions.BodyCopyLineAction#484441") != null)
			return (nc.ui.pubapp.uif2app.actions.BodyCopyLineAction) context.get("nc.ui.pubapp.uif2app.actions.BodyCopyLineAction#484441");
		nc.ui.pubapp.uif2app.actions.BodyCopyLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyCopyLineAction();
		context.put("nc.ui.pubapp.uif2app.actions.BodyCopyLineAction#484441", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction getBodyPasteToTailAction_7932a1() {
		if (context.get("nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction#7932a1") != null)
			return (nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction) context
					.get("nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction#7932a1");
		nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction bean = new nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction();
		context.put("nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction#7932a1", bean);
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

	private nc.ui.pubapp.uif2app.actions.BodyLineEditAction getBodyLineEditAction_eb135a() {
		if (context.get("nc.ui.pubapp.uif2app.actions.BodyLineEditAction#eb135a") != null)
			return (nc.ui.pubapp.uif2app.actions.BodyLineEditAction) context.get("nc.ui.pubapp.uif2app.actions.BodyLineEditAction#eb135a");
		nc.ui.pubapp.uif2app.actions.BodyLineEditAction bean = new nc.ui.pubapp.uif2app.actions.BodyLineEditAction();
		context.put("nc.ui.pubapp.uif2app.actions.BodyLineEditAction#eb135a", bean);
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
		list.add(getEventHandlerGroup_1ab33ca());
		list.add(getEventHandlerGroup_8eff28());
		return list;
	}

	private nc.ui.pubapp.uif2app.event.EventHandlerGroup getEventHandlerGroup_1ab33ca() {
		if (context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#1ab33ca") != null)
			return (nc.ui.pubapp.uif2app.event.EventHandlerGroup) context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#1ab33ca");
		nc.ui.pubapp.uif2app.event.EventHandlerGroup bean = new nc.ui.pubapp.uif2app.event.EventHandlerGroup();
		context.put("nc.ui.pubapp.uif2app.event.EventHandlerGroup#1ab33ca", bean);
		bean.setEvent("nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent");
		bean.setHandler(getGrandBodyBeforeEditHandler_143ab6b());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.qcco.commission.ace.handler.GrandBodyBeforeEditHandler getGrandBodyBeforeEditHandler_143ab6b() {
		if (context.get("nc.ui.qcco.commission.ace.handler.GrandBodyBeforeEditHandler#143ab6b") != null)
			return (nc.ui.qcco.commission.ace.handler.GrandBodyBeforeEditHandler) context
					.get("nc.ui.qcco.commission.ace.handler.GrandBodyBeforeEditHandler#143ab6b");
		nc.ui.qcco.commission.ace.handler.GrandBodyBeforeEditHandler bean = new nc.ui.qcco.commission.ace.handler.GrandBodyBeforeEditHandler(
				getBillForm());
		context.put("nc.ui.qcco.commission.ace.handler.GrandBodyBeforeEditHandler#143ab6b", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.event.EventHandlerGroup getEventHandlerGroup_8eff28() {
		if (context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#8eff28") != null)
			return (nc.ui.pubapp.uif2app.event.EventHandlerGroup) context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#8eff28");
		nc.ui.pubapp.uif2app.event.EventHandlerGroup bean = new nc.ui.pubapp.uif2app.event.EventHandlerGroup();
		context.put("nc.ui.pubapp.uif2app.event.EventHandlerGroup#8eff28", bean);
		bean.setEvent("nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent");
		bean.setHandler(getGrandBodyAfterEditHandler_d540c3());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.qcco.commission.ace.handler.GrandBodyAfterEditHandler getGrandBodyAfterEditHandler_d540c3() {
		if (context.get("nc.ui.qcco.commission.ace.handler.GrandBodyAfterEditHandler#d540c3") != null)
			return (nc.ui.qcco.commission.ace.handler.GrandBodyAfterEditHandler) context
					.get("nc.ui.qcco.commission.ace.handler.GrandBodyAfterEditHandler#d540c3");
		nc.ui.qcco.commission.ace.handler.GrandBodyAfterEditHandler bean = new nc.ui.qcco.commission.ace.handler.GrandBodyAfterEditHandler();
		context.put("nc.ui.qcco.commission.ace.handler.GrandBodyAfterEditHandler#d540c3", bean);
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
		bean.setUserdefitemListPreparator(getCompositeBillListDataPrepare_11b0ec());
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare getCompositeBillListDataPrepare_11b0ec() {
		if (context.get("nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare#11b0ec") != null)
			return (nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare) context
					.get("nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare#11b0ec");
		nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare bean = new nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare();
		context.put("nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare#11b0ec", bean);
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
		list.add(getUserdefQueryParam_1765f38());
		list.add(getUserdefQueryParam_657a2f());
		return list;
	}

	private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_1765f38() {
		if (context.get("nc.ui.uif2.editor.UserdefQueryParam#1765f38") != null)
			return (nc.ui.uif2.editor.UserdefQueryParam) context.get("nc.ui.uif2.editor.UserdefQueryParam#1765f38");
		nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
		context.put("nc.ui.uif2.editor.UserdefQueryParam#1765f38", bean);
		bean.setMdfullname("qcco.commission");
		bean.setPos(0);
		bean.setPrefix("vdef");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_657a2f() {
		if (context.get("nc.ui.uif2.editor.UserdefQueryParam#657a2f") != null)
			return (nc.ui.uif2.editor.UserdefQueryParam) context.get("nc.ui.uif2.editor.UserdefQueryParam#657a2f");
		nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
		context.put("nc.ui.uif2.editor.UserdefQueryParam#657a2f", bean);
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
		bean.setUserdefitemPreparator(getCompositeBillDataPrepare_66d8e4());
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList7() {
		List list = new ArrayList();
		list.add(getCommissionBodyAddLineAction_38ba6e());
		list.add(getCommissionBodyInsertLineAction_57a99e());
		list.add(getCommissionBodyDelLineAction_140f682());
		list.add(getBodyCopyLineAction_1f806ca());
		list.add(getCommissionBodyPasteTailAction_85cc46());
		list.add(getBodyLineEditAction_260e16());
		return list;
	}

	private nc.ui.qcco.commission.action.CommissionBodyAddLineAction getCommissionBodyAddLineAction_38ba6e() {
		if (context.get("nc.ui.qcco.commission.action.CommissionBodyAddLineAction#38ba6e") != null)
			return (nc.ui.qcco.commission.action.CommissionBodyAddLineAction) context
					.get("nc.ui.qcco.commission.action.CommissionBodyAddLineAction#38ba6e");
		nc.ui.qcco.commission.action.CommissionBodyAddLineAction bean = new nc.ui.qcco.commission.action.CommissionBodyAddLineAction();
		context.put("nc.ui.qcco.commission.action.CommissionBodyAddLineAction#38ba6e", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.qcco.commission.action.CommissionBodyInsertLineAction getCommissionBodyInsertLineAction_57a99e() {
		if (context.get("nc.ui.qcco.commission.action.CommissionBodyInsertLineAction#57a99e") != null)
			return (nc.ui.qcco.commission.action.CommissionBodyInsertLineAction) context
					.get("nc.ui.qcco.commission.action.CommissionBodyInsertLineAction#57a99e");
		nc.ui.qcco.commission.action.CommissionBodyInsertLineAction bean = new nc.ui.qcco.commission.action.CommissionBodyInsertLineAction();
		context.put("nc.ui.qcco.commission.action.CommissionBodyInsertLineAction#57a99e", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.qcco.commission.action.CommissionBodyDelLineAction getCommissionBodyDelLineAction_140f682() {
		if (context.get("nc.ui.qcco.commission.action.CommissionBodyDelLineAction#140f682") != null)
			return (nc.ui.qcco.commission.action.CommissionBodyDelLineAction) context
					.get("nc.ui.qcco.commission.action.CommissionBodyDelLineAction#140f682");
		nc.ui.qcco.commission.action.CommissionBodyDelLineAction bean = new nc.ui.qcco.commission.action.CommissionBodyDelLineAction();
		context.put("nc.ui.qcco.commission.action.CommissionBodyDelLineAction#140f682", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.actions.BodyCopyLineAction getBodyCopyLineAction_1f806ca() {
		if (context.get("nc.ui.pubapp.uif2app.actions.BodyCopyLineAction#1f806ca") != null)
			return (nc.ui.pubapp.uif2app.actions.BodyCopyLineAction) context.get("nc.ui.pubapp.uif2app.actions.BodyCopyLineAction#1f806ca");
		nc.ui.pubapp.uif2app.actions.BodyCopyLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyCopyLineAction();
		context.put("nc.ui.pubapp.uif2app.actions.BodyCopyLineAction#1f806ca", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.qcco.commission.action.CommissionBodyPasteTailAction getCommissionBodyPasteTailAction_85cc46() {
		if (context.get("nc.ui.qcco.commission.action.CommissionBodyPasteTailAction#85cc46") != null)
			return (nc.ui.qcco.commission.action.CommissionBodyPasteTailAction) context
					.get("nc.ui.qcco.commission.action.CommissionBodyPasteTailAction#85cc46");
		nc.ui.qcco.commission.action.CommissionBodyPasteTailAction bean = new nc.ui.qcco.commission.action.CommissionBodyPasteTailAction(
				getMainGrandbillForm());
		context.put("nc.ui.qcco.commission.action.CommissionBodyPasteTailAction#85cc46", bean);
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

	private nc.ui.pubapp.uif2app.actions.BodyLineEditAction getBodyLineEditAction_260e16() {
		if (context.get("nc.ui.pubapp.uif2app.actions.BodyLineEditAction#260e16") != null)
			return (nc.ui.pubapp.uif2app.actions.BodyLineEditAction) context.get("nc.ui.pubapp.uif2app.actions.BodyLineEditAction#260e16");
		nc.ui.pubapp.uif2app.actions.BodyLineEditAction bean = new nc.ui.pubapp.uif2app.actions.BodyLineEditAction();
		context.put("nc.ui.pubapp.uif2app.actions.BodyLineEditAction#260e16", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare getCompositeBillDataPrepare_66d8e4() {
		if (context.get("nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare#66d8e4") != null)
			return (nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare) context
					.get("nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare#66d8e4");
		nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare bean = new nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare();
		context.put("nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare#66d8e4", bean);
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
		list.add(getUserdefQueryParam_2980bd());
		list.add(getUserdefQueryParam_c3226f());
		return list;
	}

	private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_2980bd() {
		if (context.get("nc.ui.uif2.editor.UserdefQueryParam#2980bd") != null)
			return (nc.ui.uif2.editor.UserdefQueryParam) context.get("nc.ui.uif2.editor.UserdefQueryParam#2980bd");
		nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
		context.put("nc.ui.uif2.editor.UserdefQueryParam#2980bd", bean);
		bean.setMdfullname("qcco.commission");
		bean.setPos(0);
		bean.setPrefix("vdef");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_c3226f() {
		if (context.get("nc.ui.uif2.editor.UserdefQueryParam#c3226f") != null)
			return (nc.ui.uif2.editor.UserdefQueryParam) context.get("nc.ui.uif2.editor.UserdefQueryParam#c3226f");
		nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
		context.put("nc.ui.uif2.editor.UserdefQueryParam#c3226f", bean);
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
		list.add(getQueryParam_109b73d());
		list.add(getQueryParam_dd230c());
		list.add(getQueryParam_27ffd6());
		return list;
	}

	private nc.ui.uif2.userdefitem.QueryParam getQueryParam_109b73d() {
		if (context.get("nc.ui.uif2.userdefitem.QueryParam#109b73d") != null)
			return (nc.ui.uif2.userdefitem.QueryParam) context.get("nc.ui.uif2.userdefitem.QueryParam#109b73d");
		nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
		context.put("nc.ui.uif2.userdefitem.QueryParam#109b73d", bean);
		bean.setMdfullname("qcco.commission");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.userdefitem.QueryParam getQueryParam_dd230c() {
		if (context.get("nc.ui.uif2.userdefitem.QueryParam#dd230c") != null)
			return (nc.ui.uif2.userdefitem.QueryParam) context.get("nc.ui.uif2.userdefitem.QueryParam#dd230c");
		nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
		context.put("nc.ui.uif2.userdefitem.QueryParam#dd230c", bean);
		bean.setMdfullname("qcco.CommissionBVO");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.userdefitem.QueryParam getQueryParam_27ffd6() {
		if (context.get("nc.ui.uif2.userdefitem.QueryParam#27ffd6") != null)
			return (nc.ui.uif2.userdefitem.QueryParam) context.get("nc.ui.uif2.userdefitem.QueryParam#27ffd6");
		nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
		context.put("nc.ui.uif2.userdefitem.QueryParam#27ffd6", bean);
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
		bean.setTangramLayoutRoot(getTBNode_d14557());
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.TBNode getTBNode_d14557() {
		if (context.get("nc.ui.uif2.tangramlayout.node.TBNode#d14557") != null)
			return (nc.ui.uif2.tangramlayout.node.TBNode) context.get("nc.ui.uif2.tangramlayout.node.TBNode#d14557");
		nc.ui.uif2.tangramlayout.node.TBNode bean = new nc.ui.uif2.tangramlayout.node.TBNode();
		context.put("nc.ui.uif2.tangramlayout.node.TBNode#d14557", bean);
		bean.setShowMode("CardLayout");
		bean.setTabs(getManagedList12());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList12() {
		List list = new ArrayList();
		list.add(getVSNode_2d3011());
		list.add(getVSNode_9c2c1());
		return list;
	}

	private nc.ui.uif2.tangramlayout.node.VSNode getVSNode_2d3011() {
		if (context.get("nc.ui.uif2.tangramlayout.node.VSNode#2d3011") != null)
			return (nc.ui.uif2.tangramlayout.node.VSNode) context.get("nc.ui.uif2.tangramlayout.node.VSNode#2d3011");
		nc.ui.uif2.tangramlayout.node.VSNode bean = new nc.ui.uif2.tangramlayout.node.VSNode();
		context.put("nc.ui.uif2.tangramlayout.node.VSNode#2d3011", bean);
		bean.setShowMode("NoDivider");
		bean.setUp(getCNode_d710c());
		bean.setDown(getHSNode_1d50c95());
		bean.setDividerLocation(30f);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.CNode getCNode_d710c() {
		if (context.get("nc.ui.uif2.tangramlayout.node.CNode#d710c") != null)
			return (nc.ui.uif2.tangramlayout.node.CNode) context.get("nc.ui.uif2.tangramlayout.node.CNode#d710c");
		nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
		context.put("nc.ui.uif2.tangramlayout.node.CNode#d710c", bean);
		bean.setComponent(getOrgpanel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.HSNode getHSNode_1d50c95() {
		if (context.get("nc.ui.uif2.tangramlayout.node.HSNode#1d50c95") != null)
			return (nc.ui.uif2.tangramlayout.node.HSNode) context.get("nc.ui.uif2.tangramlayout.node.HSNode#1d50c95");
		nc.ui.uif2.tangramlayout.node.HSNode bean = new nc.ui.uif2.tangramlayout.node.HSNode();
		context.put("nc.ui.uif2.tangramlayout.node.HSNode#1d50c95", bean);
		bean.setLeft(getCNode_790032());
		bean.setRight(getVSNode_15e8a78());
		bean.setDividerLocation(210f);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.CNode getCNode_790032() {
		if (context.get("nc.ui.uif2.tangramlayout.node.CNode#790032") != null)
			return (nc.ui.uif2.tangramlayout.node.CNode) context.get("nc.ui.uif2.tangramlayout.node.CNode#790032");
		nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
		context.put("nc.ui.uif2.tangramlayout.node.CNode#790032", bean);
		bean.setComponent(getQueryArea());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.VSNode getVSNode_15e8a78() {
		if (context.get("nc.ui.uif2.tangramlayout.node.VSNode#15e8a78") != null)
			return (nc.ui.uif2.tangramlayout.node.VSNode) context.get("nc.ui.uif2.tangramlayout.node.VSNode#15e8a78");
		nc.ui.uif2.tangramlayout.node.VSNode bean = new nc.ui.uif2.tangramlayout.node.VSNode();
		context.put("nc.ui.uif2.tangramlayout.node.VSNode#15e8a78", bean);
		bean.setUp(getCNode_1fbf8d0());
		bean.setDown(getCNode_b66017());
		bean.setDividerLocation(25f);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.CNode getCNode_1fbf8d0() {
		if (context.get("nc.ui.uif2.tangramlayout.node.CNode#1fbf8d0") != null)
			return (nc.ui.uif2.tangramlayout.node.CNode) context.get("nc.ui.uif2.tangramlayout.node.CNode#1fbf8d0");
		nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
		context.put("nc.ui.uif2.tangramlayout.node.CNode#1fbf8d0", bean);
		bean.setComponent(getQueryInfo());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.CNode getCNode_b66017() {
		if (context.get("nc.ui.uif2.tangramlayout.node.CNode#b66017") != null)
			return (nc.ui.uif2.tangramlayout.node.CNode) context.get("nc.ui.uif2.tangramlayout.node.CNode#b66017");
		nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
		context.put("nc.ui.uif2.tangramlayout.node.CNode#b66017", bean);
		bean.setName(getI18nFB_18c47af());
		bean.setComponent(getMainGrandlistView());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_18c47af() {
		if (context.get("nc.ui.uif2.I18nFB#18c47af") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#18c47af");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#18c47af", bean);
		bean.setResDir("common");
		bean.setResId("UC001-0000107");
		bean.setDefaultValue("�б�");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#18c47af", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private nc.ui.uif2.tangramlayout.node.VSNode getVSNode_9c2c1() {
		if (context.get("nc.ui.uif2.tangramlayout.node.VSNode#9c2c1") != null)
			return (nc.ui.uif2.tangramlayout.node.VSNode) context.get("nc.ui.uif2.tangramlayout.node.VSNode#9c2c1");
		nc.ui.uif2.tangramlayout.node.VSNode bean = new nc.ui.uif2.tangramlayout.node.VSNode();
		context.put("nc.ui.uif2.tangramlayout.node.VSNode#9c2c1", bean);
		bean.setUp(getCNode_aece08());
		bean.setDown(getCNode_c1046d());
		bean.setDividerLocation(30f);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.CNode getCNode_aece08() {
		if (context.get("nc.ui.uif2.tangramlayout.node.CNode#aece08") != null)
			return (nc.ui.uif2.tangramlayout.node.CNode) context.get("nc.ui.uif2.tangramlayout.node.CNode#aece08");
		nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
		context.put("nc.ui.uif2.tangramlayout.node.CNode#aece08", bean);
		bean.setComponent(getViewb());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.CNode getCNode_c1046d() {
		if (context.get("nc.ui.uif2.tangramlayout.node.CNode#c1046d") != null)
			return (nc.ui.uif2.tangramlayout.node.CNode) context.get("nc.ui.uif2.tangramlayout.node.CNode#c1046d");
		nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
		context.put("nc.ui.uif2.tangramlayout.node.CNode#c1046d", bean);
		bean.setName(getI18nFB_588208());
		bean.setComponent(getMainGrandbillForm());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_588208() {
		if (context.get("nc.ui.uif2.I18nFB#588208") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#588208");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#588208", bean);
		bean.setResDir("common");
		bean.setResId("UC001-0000106");
		bean.setDefaultValue("��Ƭ");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#588208", product);
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
		list.add(getTemplateNotNullValidation_11f7506());
		return list;
	}

	private nc.ui.pubapp.uif2app.validation.TemplateNotNullValidation getTemplateNotNullValidation_11f7506() {
		if (context.get("nc.ui.pubapp.uif2app.validation.TemplateNotNullValidation#11f7506") != null)
			return (nc.ui.pubapp.uif2app.validation.TemplateNotNullValidation) context
					.get("nc.ui.pubapp.uif2app.validation.TemplateNotNullValidation#11f7506");
		nc.ui.pubapp.uif2app.validation.TemplateNotNullValidation bean = new nc.ui.pubapp.uif2app.validation.TemplateNotNullValidation();
		context.put("nc.ui.pubapp.uif2app.validation.TemplateNotNullValidation#11f7506", bean);
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

	public nc.ui.pubapp.uif2app.actions.CopyAction getCopyAction() {
		if (context.get("copyAction") != null)
			return (nc.ui.pubapp.uif2app.actions.CopyAction) context.get("copyAction");
		nc.ui.pubapp.uif2app.actions.CopyAction bean = new nc.ui.pubapp.uif2app.actions.CopyAction();
		context.put("copyAction", bean);
		bean.setModel(getBmModel());
		bean.setInterceptor(getShowCardInterceptor());
		bean.setEditor(getBillForm());
		bean.setCopyActionProcessor(getCommissionCopyActionProcessor_3bfa36());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.qcco.commission.action.CommissionCopyActionProcessor getCommissionCopyActionProcessor_3bfa36() {
		if (context.get("nc.ui.qcco.commission.action.CommissionCopyActionProcessor#3bfa36") != null)
			return (nc.ui.qcco.commission.action.CommissionCopyActionProcessor) context
					.get("nc.ui.qcco.commission.action.CommissionCopyActionProcessor#3bfa36");
		nc.ui.qcco.commission.action.CommissionCopyActionProcessor bean = new nc.ui.qcco.commission.action.CommissionCopyActionProcessor();
		context.put("nc.ui.qcco.commission.action.CommissionCopyActionProcessor#3bfa36", bean);
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
		bean.setName(getI18nFB_1a8102c());
		bean.setActions(getManagedList19());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_1a8102c() {
		if (context.get("nc.ui.uif2.I18nFB#1a8102c") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#1a8102c");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#1a8102c", bean);
		bean.setResDir("common");
		bean.setResId("UC001-0000007");
		bean.setDefaultValue("��ӡ");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#1a8102c", product);
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
		bean.setName("Ԥ��");
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
		list.add(getEventHandlerGroup_1687ec());
		list.add(getEventHandlerGroup_e07965());
		list.add(getEventHandlerGroup_178ab51());
		list.add(getEventHandlerGroup_186bbd3());
		list.add(getEventHandlerGroup_172ecbb());
		list.add(getEventHandlerGroup_163019f());
		return list;
	}

	private nc.ui.pubapp.uif2app.event.EventHandlerGroup getEventHandlerGroup_1687ec() {
		if (context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#1687ec") != null)
			return (nc.ui.pubapp.uif2app.event.EventHandlerGroup) context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#1687ec");
		nc.ui.pubapp.uif2app.event.EventHandlerGroup bean = new nc.ui.pubapp.uif2app.event.EventHandlerGroup();
		context.put("nc.ui.pubapp.uif2app.event.EventHandlerGroup#1687ec", bean);
		bean.setEvent("nc.ui.pubapp.uif2app.event.OrgChangedEvent");
		bean.setHandler(getAceOrgChangeHandler_bdc9f7());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.qcco.commission.ace.handler.AceOrgChangeHandler getAceOrgChangeHandler_bdc9f7() {
		if (context.get("nc.ui.qcco.commission.ace.handler.AceOrgChangeHandler#bdc9f7") != null)
			return (nc.ui.qcco.commission.ace.handler.AceOrgChangeHandler) context
					.get("nc.ui.qcco.commission.ace.handler.AceOrgChangeHandler#bdc9f7");
		nc.ui.qcco.commission.ace.handler.AceOrgChangeHandler bean = new nc.ui.qcco.commission.ace.handler.AceOrgChangeHandler();
		context.put("nc.ui.qcco.commission.ace.handler.AceOrgChangeHandler#bdc9f7", bean);
		bean.setBillForm(getBillForm());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.event.EventHandlerGroup getEventHandlerGroup_e07965() {
		if (context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#e07965") != null)
			return (nc.ui.pubapp.uif2app.event.EventHandlerGroup) context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#e07965");
		nc.ui.pubapp.uif2app.event.EventHandlerGroup bean = new nc.ui.pubapp.uif2app.event.EventHandlerGroup();
		context.put("nc.ui.pubapp.uif2app.event.EventHandlerGroup#e07965", bean);
		bean.setEvent("nc.ui.pubapp.uif2app.event.billform.AddEvent");
		bean.setHandler(getAceAddHandler_1359d27());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.qcco.commission.ace.handler.AceAddHandler getAceAddHandler_1359d27() {
		if (context.get("nc.ui.qcco.commission.ace.handler.AceAddHandler#1359d27") != null)
			return (nc.ui.qcco.commission.ace.handler.AceAddHandler) context.get("nc.ui.qcco.commission.ace.handler.AceAddHandler#1359d27");
		nc.ui.qcco.commission.ace.handler.AceAddHandler bean = new nc.ui.qcco.commission.ace.handler.AceAddHandler();
		context.put("nc.ui.qcco.commission.ace.handler.AceAddHandler#1359d27", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.event.EventHandlerGroup getEventHandlerGroup_178ab51() {
		if (context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#178ab51") != null)
			return (nc.ui.pubapp.uif2app.event.EventHandlerGroup) context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#178ab51");
		nc.ui.pubapp.uif2app.event.EventHandlerGroup bean = new nc.ui.pubapp.uif2app.event.EventHandlerGroup();
		context.put("nc.ui.pubapp.uif2app.event.EventHandlerGroup#178ab51", bean);
		bean.setEvent("nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent");
		bean.setHandler(getAceHeadTailBeforeEditHandler_1420586());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.qcco.commission.ace.handler.AceHeadTailBeforeEditHandler getAceHeadTailBeforeEditHandler_1420586() {
		if (context.get("nc.ui.qcco.commission.ace.handler.AceHeadTailBeforeEditHandler#1420586") != null)
			return (nc.ui.qcco.commission.ace.handler.AceHeadTailBeforeEditHandler) context
					.get("nc.ui.qcco.commission.ace.handler.AceHeadTailBeforeEditHandler#1420586");
		nc.ui.qcco.commission.ace.handler.AceHeadTailBeforeEditHandler bean = new nc.ui.qcco.commission.ace.handler.AceHeadTailBeforeEditHandler();
		context.put("nc.ui.qcco.commission.ace.handler.AceHeadTailBeforeEditHandler#1420586", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.event.EventHandlerGroup getEventHandlerGroup_186bbd3() {
		if (context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#186bbd3") != null)
			return (nc.ui.pubapp.uif2app.event.EventHandlerGroup) context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#186bbd3");
		nc.ui.pubapp.uif2app.event.EventHandlerGroup bean = new nc.ui.pubapp.uif2app.event.EventHandlerGroup();
		context.put("nc.ui.pubapp.uif2app.event.EventHandlerGroup#186bbd3", bean);
		bean.setEvent("nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent");
		bean.setHandler(getAceHeadTailAfterEditHandler_1376401());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.qcco.commission.ace.handler.AceHeadTailAfterEditHandler getAceHeadTailAfterEditHandler_1376401() {
		if (context.get("nc.ui.qcco.commission.ace.handler.AceHeadTailAfterEditHandler#1376401") != null)
			return (nc.ui.qcco.commission.ace.handler.AceHeadTailAfterEditHandler) context
					.get("nc.ui.qcco.commission.ace.handler.AceHeadTailAfterEditHandler#1376401");
		nc.ui.qcco.commission.ace.handler.AceHeadTailAfterEditHandler bean = new nc.ui.qcco.commission.ace.handler.AceHeadTailAfterEditHandler();
		context.put("nc.ui.qcco.commission.ace.handler.AceHeadTailAfterEditHandler#1376401", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.event.EventHandlerGroup getEventHandlerGroup_172ecbb() {
		if (context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#172ecbb") != null)
			return (nc.ui.pubapp.uif2app.event.EventHandlerGroup) context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#172ecbb");
		nc.ui.pubapp.uif2app.event.EventHandlerGroup bean = new nc.ui.pubapp.uif2app.event.EventHandlerGroup();
		context.put("nc.ui.pubapp.uif2app.event.EventHandlerGroup#172ecbb", bean);
		bean.setEvent("nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent");
		bean.setHandler(getAceBodyBeforeEditHandler_952bdd());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.qcco.commission.ace.handler.AceBodyBeforeEditHandler getAceBodyBeforeEditHandler_952bdd() {
		if (context.get("nc.ui.qcco.commission.ace.handler.AceBodyBeforeEditHandler#952bdd") != null)
			return (nc.ui.qcco.commission.ace.handler.AceBodyBeforeEditHandler) context
					.get("nc.ui.qcco.commission.ace.handler.AceBodyBeforeEditHandler#952bdd");
		nc.ui.qcco.commission.ace.handler.AceBodyBeforeEditHandler bean = new nc.ui.qcco.commission.ace.handler.AceBodyBeforeEditHandler();
		context.put("nc.ui.qcco.commission.ace.handler.AceBodyBeforeEditHandler#952bdd", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.event.EventHandlerGroup getEventHandlerGroup_163019f() {
		if (context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#163019f") != null)
			return (nc.ui.pubapp.uif2app.event.EventHandlerGroup) context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#163019f");
		nc.ui.pubapp.uif2app.event.EventHandlerGroup bean = new nc.ui.pubapp.uif2app.event.EventHandlerGroup();
		context.put("nc.ui.pubapp.uif2app.event.EventHandlerGroup#163019f", bean);
		bean.setEvent("nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent");
		bean.setHandler(getAceBodyAfterEditHandler_140153());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.qcco.commission.ace.handler.AceBodyAfterEditHandler getAceBodyAfterEditHandler_140153() {
		if (context.get("nc.ui.qcco.commission.ace.handler.AceBodyAfterEditHandler#140153") != null)
			return (nc.ui.qcco.commission.ace.handler.AceBodyAfterEditHandler) context
					.get("nc.ui.qcco.commission.ace.handler.AceBodyAfterEditHandler#140153");
		nc.ui.qcco.commission.ace.handler.AceBodyAfterEditHandler bean = new nc.ui.qcco.commission.ace.handler.AceBodyAfterEditHandler();
		context.put("nc.ui.qcco.commission.ace.handler.AceBodyAfterEditHandler#140153", bean);
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
		list.add(getCardPanelLazilyLoad_ff9670());
		list.add(getListPanelLazilyLoad_1edcaf7());
		return list;
	}

	private nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad getCardPanelLazilyLoad_ff9670() {
		if (context.get("nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad#ff9670") != null)
			return (nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad) context
					.get("nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad#ff9670");
		nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad bean = new nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad();
		context.put("nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad#ff9670", bean);
		bean.setBillform(getBillForm());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad getListPanelLazilyLoad_1edcaf7() {
		if (context.get("nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad#1edcaf7") != null)
			return (nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad) context
					.get("nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad#1edcaf7");
		nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad bean = new nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad();
		context.put("nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad#1edcaf7", bean);
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
		bean.setActionname("Ԥ��");
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
		bean.setActionname("��ӡ");
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
