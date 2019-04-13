package nc.ui.qcco.commission.ace.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class commission_config extends AbstractJavaBeanDefinition{
	private Map<String, Object> context = new HashMap();
	public nc.vo.uif2.LoginContext getContext(){
		if(context.get("context")!=null)
			return (nc.vo.uif2.LoginContext)context.get("context");
		nc.vo.uif2.LoginContext bean = new nc.vo.uif2.LoginContext();
		context.put("context",bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.qcco.commission.ace.serviceproxy.AceCommissionDeleteProxy getDeleteProxy(){
		if(context.get("deleteProxy")!=null)
			return (nc.ui.qcco.commission.ace.serviceproxy.AceCommissionDeleteProxy)context.get("deleteProxy");
		nc.ui.qcco.commission.ace.serviceproxy.AceCommissionDeleteProxy bean = new nc.ui.qcco.commission.ace.serviceproxy.AceCommissionDeleteProxy();
		context.put("deleteProxy",bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.qcco.commission.ace.serviceproxy.AceCommissionMaintainProxy getBmModelModelService(){
		if(context.get("bmModelModelService")!=null)
			return (nc.ui.qcco.commission.ace.serviceproxy.AceCommissionMaintainProxy)context.get("bmModelModelService");
		nc.ui.qcco.commission.ace.serviceproxy.AceCommissionMaintainProxy bean = new nc.ui.qcco.commission.ace.serviceproxy.AceCommissionMaintainProxy();
		context.put("bmModelModelService",bean);
		bean.setGrandTabAndVOMap(getManagedMap0());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private Map getManagedMap0(){  Map map = new HashMap();  map.put("pk_commission_r",getCommissionRVO());  return map;}

	public nc.ui.pubapp.uif2app.view.value.AggVOMetaBDObjectAdapterFactory getBOAdapterFactory(){
		if(context.get("BOAdapterFactory")!=null)
			return (nc.ui.pubapp.uif2app.view.value.AggVOMetaBDObjectAdapterFactory)context.get("BOAdapterFactory");
		nc.ui.pubapp.uif2app.view.value.AggVOMetaBDObjectAdapterFactory bean = new nc.ui.pubapp.uif2app.view.value.AggVOMetaBDObjectAdapterFactory();
		context.put("BOAdapterFactory",bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.vo.bd.meta.BDObjectAdpaterFactory getBOAdapterFactory2(){
		if(context.get("BOAdapterFactory2")!=null)
			return (nc.vo.bd.meta.BDObjectAdpaterFactory)context.get("BOAdapterFactory2");
		nc.vo.bd.meta.BDObjectAdpaterFactory bean = new nc.vo.bd.meta.BDObjectAdpaterFactory();
		context.put("BOAdapterFactory2",bean);
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
		bean.setBusinessObjectAdapterFactory(getBOAdapterFactory());
		bean.setBillFormView(getMainGrandbillForm());
		bean.setBillListView(getMainGrandlistView());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.qcco.commission.model.SubGrandBillModel getBmModel2(){
		if(context.get("bmModel2")!=null)
			return (nc.ui.qcco.commission.model.SubGrandBillModel)context.get("bmModel2");
		nc.ui.qcco.commission.model.SubGrandBillModel bean = new nc.ui.qcco.commission.model.SubGrandBillModel();
		context.put("bmModel2",bean);
		bean.setContext(getContext());
		bean.setBusinessObjectAdapterFactory(getBOAdapterFactory2());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.components.grand.model.MainGrandModel getMainGrandModel(){
		if(context.get("mainGrandModel")!=null)
			return (nc.ui.pubapp.uif2app.components.grand.model.MainGrandModel)context.get("mainGrandModel");
		nc.ui.pubapp.uif2app.components.grand.model.MainGrandModel bean = new nc.ui.pubapp.uif2app.components.grand.model.MainGrandModel();
		context.put("mainGrandModel",bean);
		bean.setHandleListCardIsShow(true);
		bean.setMainModel(getBmModel());
		bean.setGrandModel(getBmModel2());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.components.grand.MainGrandBlankFilter getMainGrandBlankFilter(){
		if(context.get("mainGrandBlankFilter")!=null)
			return (nc.ui.pubapp.uif2app.components.grand.MainGrandBlankFilter)context.get("mainGrandBlankFilter");
		nc.ui.pubapp.uif2app.components.grand.MainGrandBlankFilter bean = new nc.ui.pubapp.uif2app.components.grand.MainGrandBlankFilter();
		context.put("mainGrandBlankFilter",bean);
		bean.setChildFilterMap(getManagedMap1());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private Map getManagedMap1(){  Map map = new HashMap();  map.put("pk_commission_b",getManagedList0());  return map;}

	private List getManagedList0(){  List list = new ArrayList();  list.add("rowno");  return list;}

	public nc.ui.pubapp.uif2app.components.grand.MainGrandRelationShip getMainGrandRelationShip(){
		if(context.get("mainGrandRelationShip")!=null)
			return (nc.ui.pubapp.uif2app.components.grand.MainGrandRelationShip)context.get("mainGrandRelationShip");
		nc.ui.pubapp.uif2app.components.grand.MainGrandRelationShip bean = new nc.ui.pubapp.uif2app.components.grand.MainGrandRelationShip();
		context.put("mainGrandRelationShip",bean);
		bean.setBodyTabTOGrandListComposite(getManagedMap2());
		bean.setBodyTabTOGrandCardComposite(getManagedMap3());
		bean.setGrandTabAndVOMap(getManagedMap4());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private Map getManagedMap2(){  Map map = new HashMap();  map.put("pk_commission_b",getSunlistView1());  return map;}

	private Map getManagedMap3(){  Map map = new HashMap();  map.put("pk_commission_b",getSunbillForm1());  return map;}

	private Map getManagedMap4(){  Map map = new HashMap();  map.put("pk_commission_r",getCommissionRVO());  return map;}

	public nc.vo.qcco.commission.CommissionRVO getCommissionRVO(){
		if(context.get("CommissionRVO")!=null)
			return (nc.vo.qcco.commission.CommissionRVO)context.get("CommissionRVO");
		nc.vo.qcco.commission.CommissionRVO bean = new nc.vo.qcco.commission.CommissionRVO();
		context.put("CommissionRVO",bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.components.grand.action.ExpendShrinkGrandListAction getExpendShrinkGrandListAction(){
		if(context.get("expendShrinkGrandListAction")!=null)
			return (nc.ui.pubapp.uif2app.components.grand.action.ExpendShrinkGrandListAction)context.get("expendShrinkGrandListAction");
		nc.ui.pubapp.uif2app.components.grand.action.ExpendShrinkGrandListAction bean = new nc.ui.pubapp.uif2app.components.grand.action.ExpendShrinkGrandListAction();
		context.put("expendShrinkGrandListAction",bean);
		bean.setMainGrandModel(getMainGrandModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.components.grand.action.ExpendShrinkGrandCardAction getExpendShrinkGrandCardAction(){
		if(context.get("expendShrinkGrandCardAction")!=null)
			return (nc.ui.pubapp.uif2app.components.grand.action.ExpendShrinkGrandCardAction)context.get("expendShrinkGrandCardAction");
		nc.ui.pubapp.uif2app.components.grand.action.ExpendShrinkGrandCardAction bean = new nc.ui.pubapp.uif2app.components.grand.action.ExpendShrinkGrandCardAction();
		context.put("expendShrinkGrandCardAction",bean);
		bean.setMainGrandModel(getMainGrandModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.components.grand.ListGrandPanelComposite getMainGrandlistView(){
		if(context.get("MainGrandlistView")!=null)
			return (nc.ui.pubapp.uif2app.components.grand.ListGrandPanelComposite)context.get("MainGrandlistView");
		nc.ui.pubapp.uif2app.components.grand.ListGrandPanelComposite bean = new nc.ui.pubapp.uif2app.components.grand.ListGrandPanelComposite();
		context.put("MainGrandlistView",bean);
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

	public nc.ui.pubapp.uif2app.components.grand.CardGrandPanelComposite getMainGrandbillForm(){
		if(context.get("MainGrandbillForm")!=null)
			return (nc.ui.pubapp.uif2app.components.grand.CardGrandPanelComposite)context.get("MainGrandbillForm");
		nc.ui.pubapp.uif2app.components.grand.CardGrandPanelComposite bean = new nc.ui.pubapp.uif2app.components.grand.CardGrandPanelComposite();
		context.put("MainGrandbillForm",bean);
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

	public nc.ui.pubapp.uif2app.components.grand.mediator.MainGrandMediator getMainGrandMediator(){
		if(context.get("mainGrandMediator")!=null)
			return (nc.ui.pubapp.uif2app.components.grand.mediator.MainGrandMediator)context.get("mainGrandMediator");
		nc.ui.pubapp.uif2app.components.grand.mediator.MainGrandMediator bean = new nc.ui.pubapp.uif2app.components.grand.mediator.MainGrandMediator();
		context.put("mainGrandMediator",bean);
		bean.setMainBillForm(getBillForm());
		bean.setMainBillListView(getBillListView());
		bean.setMainGrandModel(getMainGrandModel());
		bean.setMainGrandRelationShip(getMainGrandRelationShip());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.qcco.commission.ace.view.GrandBillList getSunlistView1(){
		if(context.get("sunlistView1")!=null)
			return (nc.ui.qcco.commission.ace.view.GrandBillList)context.get("sunlistView1");
		nc.ui.qcco.commission.ace.view.GrandBillList bean = new nc.ui.qcco.commission.ace.view.GrandBillList();
		context.put("sunlistView1",bean);
		bean.setModel(getBmModel2());
		bean.setTemplateContainer(getTemplateContainer());
		bean.setNodekey("param1");
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.editor.value.BillCardPanelMetaDataValueAdapter getComponentValueManager(){
		if(context.get("componentValueManager")!=null)
			return (nc.ui.uif2.editor.value.BillCardPanelMetaDataValueAdapter)context.get("componentValueManager");
		nc.ui.uif2.editor.value.BillCardPanelMetaDataValueAdapter bean = new nc.ui.uif2.editor.value.BillCardPanelMetaDataValueAdapter();
		context.put("componentValueManager",bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.qcco.commission.ace.view.CommissionGrandBillForm getSunbillForm1(){
		if(context.get("sunbillForm1")!=null)
			return (nc.ui.qcco.commission.ace.view.CommissionGrandBillForm)context.get("sunbillForm1");
		nc.ui.qcco.commission.ace.view.CommissionGrandBillForm bean = new nc.ui.qcco.commission.ace.view.CommissionGrandBillForm();
		context.put("sunbillForm1",bean);
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

	private List getManagedList1(){  List list = new ArrayList();  list.add(getSelectAllLineAction_1c1735f4());  list.add(getSelectNoneLineAction_37d73494());  list.add(getGrandBodyAddLineAction_11bcfd30());  list.add(getBodyInsertLineAction_e926369());  list.add(getBodyDelLineAction_2cbc89f8());  list.add(getBodyCopyLineAction_e060fba());  list.add(getBodyPasteLineAction_2ff28387());  list.add(getBodyPasteToTailAction_5a992ca8());  list.add(getBodyLineEditAction_7e42f80f());  return list;}

	private nc.ui.qcco.commission.action.SelectAllLineAction getSelectAllLineAction_1c1735f4(){
		if(context.get("nc.ui.qcco.commission.action.SelectAllLineAction#1c1735f4")!=null)
			return (nc.ui.qcco.commission.action.SelectAllLineAction)context.get("nc.ui.qcco.commission.action.SelectAllLineAction#1c1735f4");
		nc.ui.qcco.commission.action.SelectAllLineAction bean = new nc.ui.qcco.commission.action.SelectAllLineAction();
		context.put("nc.ui.qcco.commission.action.SelectAllLineAction#1c1735f4",bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.qcco.commission.action.SelectNoneLineAction getSelectNoneLineAction_37d73494(){
		if(context.get("nc.ui.qcco.commission.action.SelectNoneLineAction#37d73494")!=null)
			return (nc.ui.qcco.commission.action.SelectNoneLineAction)context.get("nc.ui.qcco.commission.action.SelectNoneLineAction#37d73494");
		nc.ui.qcco.commission.action.SelectNoneLineAction bean = new nc.ui.qcco.commission.action.SelectNoneLineAction();
		context.put("nc.ui.qcco.commission.action.SelectNoneLineAction#37d73494",bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.components.grand.action.GrandBodyAddLineAction getGrandBodyAddLineAction_11bcfd30(){
		if(context.get("nc.ui.pubapp.uif2app.components.grand.action.GrandBodyAddLineAction#11bcfd30")!=null)
			return (nc.ui.pubapp.uif2app.components.grand.action.GrandBodyAddLineAction)context.get("nc.ui.pubapp.uif2app.components.grand.action.GrandBodyAddLineAction#11bcfd30");
		nc.ui.pubapp.uif2app.components.grand.action.GrandBodyAddLineAction bean = new nc.ui.pubapp.uif2app.components.grand.action.GrandBodyAddLineAction();
		context.put("nc.ui.pubapp.uif2app.components.grand.action.GrandBodyAddLineAction#11bcfd30",bean);
		bean.setMainForm(getBillForm());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.actions.BodyInsertLineAction getBodyInsertLineAction_e926369(){
		if(context.get("nc.ui.pubapp.uif2app.actions.BodyInsertLineAction#e926369")!=null)
			return (nc.ui.pubapp.uif2app.actions.BodyInsertLineAction)context.get("nc.ui.pubapp.uif2app.actions.BodyInsertLineAction#e926369");
		nc.ui.pubapp.uif2app.actions.BodyInsertLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyInsertLineAction();
		context.put("nc.ui.pubapp.uif2app.actions.BodyInsertLineAction#e926369",bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.actions.BodyDelLineAction getBodyDelLineAction_2cbc89f8(){
		if(context.get("nc.ui.pubapp.uif2app.actions.BodyDelLineAction#2cbc89f8")!=null)
			return (nc.ui.pubapp.uif2app.actions.BodyDelLineAction)context.get("nc.ui.pubapp.uif2app.actions.BodyDelLineAction#2cbc89f8");
		nc.ui.pubapp.uif2app.actions.BodyDelLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyDelLineAction();
		context.put("nc.ui.pubapp.uif2app.actions.BodyDelLineAction#2cbc89f8",bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.actions.BodyCopyLineAction getBodyCopyLineAction_e060fba(){
		if(context.get("nc.ui.pubapp.uif2app.actions.BodyCopyLineAction#e060fba")!=null)
			return (nc.ui.pubapp.uif2app.actions.BodyCopyLineAction)context.get("nc.ui.pubapp.uif2app.actions.BodyCopyLineAction#e060fba");
		nc.ui.pubapp.uif2app.actions.BodyCopyLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyCopyLineAction();
		context.put("nc.ui.pubapp.uif2app.actions.BodyCopyLineAction#e060fba",bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.actions.BodyPasteLineAction getBodyPasteLineAction_2ff28387(){
		if(context.get("nc.ui.pubapp.uif2app.actions.BodyPasteLineAction#2ff28387")!=null)
			return (nc.ui.pubapp.uif2app.actions.BodyPasteLineAction)context.get("nc.ui.pubapp.uif2app.actions.BodyPasteLineAction#2ff28387");
		nc.ui.pubapp.uif2app.actions.BodyPasteLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyPasteLineAction();
		context.put("nc.ui.pubapp.uif2app.actions.BodyPasteLineAction#2ff28387",bean);
		bean.setClearItems(getManagedList2());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList2(){  List list = new ArrayList();  list.add("pk_commission_r");  list.add("rowno");  return list;}

	private nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction getBodyPasteToTailAction_5a992ca8(){
		if(context.get("nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction#5a992ca8")!=null)
			return (nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction)context.get("nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction#5a992ca8");
		nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction bean = new nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction();
		context.put("nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction#5a992ca8",bean);
		bean.setClearItems(getManagedList3());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList3(){  List list = new ArrayList();  list.add("pk_commission_r");  list.add("rowno");  return list;}

	private nc.ui.pubapp.uif2app.actions.BodyLineEditAction getBodyLineEditAction_7e42f80f(){
		if(context.get("nc.ui.pubapp.uif2app.actions.BodyLineEditAction#7e42f80f")!=null)
			return (nc.ui.pubapp.uif2app.actions.BodyLineEditAction)context.get("nc.ui.pubapp.uif2app.actions.BodyLineEditAction#7e42f80f");
		nc.ui.pubapp.uif2app.actions.BodyLineEditAction bean = new nc.ui.pubapp.uif2app.actions.BodyLineEditAction();
		context.put("nc.ui.pubapp.uif2app.actions.BodyLineEditAction#7e42f80f",bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.query2.model.ModelDataManager getBmModelModelDataManager(){
		if(context.get("bmModelModelDataManager")!=null)
			return (nc.ui.pubapp.uif2app.query2.model.ModelDataManager)context.get("bmModelModelDataManager");
		nc.ui.pubapp.uif2app.query2.model.ModelDataManager bean = new nc.ui.pubapp.uif2app.query2.model.ModelDataManager();
		context.put("bmModelModelDataManager",bean);
		bean.setModel(getBmModel());
		bean.setService(getBmModelModelService());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.model.AppEventHandlerMediator getGrandModelEventMediator(){
		if(context.get("grandModelEventMediator")!=null)
			return (nc.ui.pubapp.uif2app.model.AppEventHandlerMediator)context.get("grandModelEventMediator");
		nc.ui.pubapp.uif2app.model.AppEventHandlerMediator bean = new nc.ui.pubapp.uif2app.model.AppEventHandlerMediator();
		context.put("grandModelEventMediator",bean);
		bean.setModel(getBmModel2());
		bean.setHandlerGroup(getManagedList4());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList4(){  List list = new ArrayList();  list.add(getEventHandlerGroup_7e6efa94());  list.add(getEventHandlerGroup_7398e869());  return list;}

	private nc.ui.pubapp.uif2app.event.EventHandlerGroup getEventHandlerGroup_7e6efa94(){
		if(context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#7e6efa94")!=null)
			return (nc.ui.pubapp.uif2app.event.EventHandlerGroup)context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#7e6efa94");
		nc.ui.pubapp.uif2app.event.EventHandlerGroup bean = new nc.ui.pubapp.uif2app.event.EventHandlerGroup();
		context.put("nc.ui.pubapp.uif2app.event.EventHandlerGroup#7e6efa94",bean);
		bean.setEvent("nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent");
		bean.setHandler(getGrandBodyBeforeEditHandler_6538f546());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.qcco.commission.ace.handler.GrandBodyBeforeEditHandler getGrandBodyBeforeEditHandler_6538f546(){
		if(context.get("nc.ui.qcco.commission.ace.handler.GrandBodyBeforeEditHandler#6538f546")!=null)
			return (nc.ui.qcco.commission.ace.handler.GrandBodyBeforeEditHandler)context.get("nc.ui.qcco.commission.ace.handler.GrandBodyBeforeEditHandler#6538f546");
		nc.ui.qcco.commission.ace.handler.GrandBodyBeforeEditHandler bean = new nc.ui.qcco.commission.ace.handler.GrandBodyBeforeEditHandler(getBillForm());  context.put("nc.ui.qcco.commission.ace.handler.GrandBodyBeforeEditHandler#6538f546",bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.event.EventHandlerGroup getEventHandlerGroup_7398e869(){
		if(context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#7398e869")!=null)
			return (nc.ui.pubapp.uif2app.event.EventHandlerGroup)context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#7398e869");
		nc.ui.pubapp.uif2app.event.EventHandlerGroup bean = new nc.ui.pubapp.uif2app.event.EventHandlerGroup();
		context.put("nc.ui.pubapp.uif2app.event.EventHandlerGroup#7398e869",bean);
		bean.setEvent("nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent");
		bean.setHandler(getGrandBodyAfterEditHandler_28054b65());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.qcco.commission.ace.handler.GrandBodyAfterEditHandler getGrandBodyAfterEditHandler_28054b65(){
		if(context.get("nc.ui.qcco.commission.ace.handler.GrandBodyAfterEditHandler#28054b65")!=null)
			return (nc.ui.qcco.commission.ace.handler.GrandBodyAfterEditHandler)context.get("nc.ui.qcco.commission.ace.handler.GrandBodyAfterEditHandler#28054b65");
		nc.ui.qcco.commission.ace.handler.GrandBodyAfterEditHandler bean = new nc.ui.qcco.commission.ace.handler.GrandBodyAfterEditHandler();
		context.put("nc.ui.qcco.commission.ace.handler.GrandBodyAfterEditHandler#28054b65",bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.view.TemplateContainer getTemplateContainer(){
		if(context.get("templateContainer")!=null)
			return (nc.ui.pubapp.uif2app.view.TemplateContainer)context.get("templateContainer");
		nc.ui.pubapp.uif2app.view.TemplateContainer bean = new nc.ui.pubapp.uif2app.view.TemplateContainer();
		context.put("templateContainer",bean);
		bean.setContext(getContext());
		bean.setNodeKeies(getManagedList5());
		bean.load();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList5(){  List list = new ArrayList();  list.add("bt");  list.add("param");  return list;}

	public nc.ui.uif2.editor.QueryTemplateContainer getQueryTemplateContainer(){
		if(context.get("queryTemplateContainer")!=null)
			return (nc.ui.uif2.editor.QueryTemplateContainer)context.get("queryTemplateContainer");
		nc.ui.uif2.editor.QueryTemplateContainer bean = new nc.ui.uif2.editor.QueryTemplateContainer();
		context.put("queryTemplateContainer",bean);
		bean.setContext(getContext());
		bean.setNodeKey("qt");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell getViewa(){
		if(context.get("viewa")!=null)
			return (nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell)context.get("viewa");
		nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell bean = new nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell();
		context.put("viewa",bean);
		bean.setQueryAreaCreator((nc.ui.uif2.actions.IQueryAreaCreator)findBeanInUIF2BeanFactory("defaultQueryAction"));
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.qcco.commission.ace.view.MainBillList getBillListView(){
		if(context.get("billListView")!=null)
			return (nc.ui.qcco.commission.ace.view.MainBillList)context.get("billListView");
		nc.ui.qcco.commission.ace.view.MainBillList bean = new nc.ui.qcco.commission.ace.view.MainBillList();
		context.put("billListView",bean);
		bean.setModel(getBmModel());
		bean.setNodekey("bt");
		bean.setMultiSelectionEnable(false);
		bean.setTemplateContainer(getTemplateContainer());
		bean.setUserdefitemListPreparator(getCompositeBillListDataPrepare_3bc280c9());
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare getCompositeBillListDataPrepare_3bc280c9(){
		if(context.get("nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare#3bc280c9")!=null)
			return (nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare)context.get("nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare#3bc280c9");
		nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare bean = new nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare();
		context.put("nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare#3bc280c9",bean);
		bean.setBillListDataPrepares(getManagedList6());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList6(){  List list = new ArrayList();  list.add(getUserdefitemlistPreparator());  list.add(getMarAsstPreparator());  return list;}

	public nc.ui.uif2.editor.UserdefitemContainerListPreparator getUserdefitemlistPreparator(){
		if(context.get("userdefitemlistPreparator")!=null)
			return (nc.ui.uif2.editor.UserdefitemContainerListPreparator)context.get("userdefitemlistPreparator");
		nc.ui.uif2.editor.UserdefitemContainerListPreparator bean = new nc.ui.uif2.editor.UserdefitemContainerListPreparator();
		context.put("userdefitemlistPreparator",bean);
		bean.setContainer(getUserdefitemContainer());
		bean.setParams(getManagedList7());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList7(){  List list = new ArrayList();  list.add(getUserdefQueryParam_5e952ac5());  list.add(getUserdefQueryParam_132b1c06());  return list;}

	private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_5e952ac5(){
		if(context.get("nc.ui.uif2.editor.UserdefQueryParam#5e952ac5")!=null)
			return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#5e952ac5");
		nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
		context.put("nc.ui.uif2.editor.UserdefQueryParam#5e952ac5",bean);
		bean.setMdfullname("qcco.commission");
		bean.setPos(0);
		bean.setPrefix("vdef");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_132b1c06(){
		if(context.get("nc.ui.uif2.editor.UserdefQueryParam#132b1c06")!=null)
			return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#132b1c06");
		nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
		context.put("nc.ui.uif2.editor.UserdefQueryParam#132b1c06",bean);
		bean.setMdfullname("qcco.CommissionBVO");
		bean.setPos(1);
		bean.setPrefix("vbdef");
		bean.setTabcode("CommissionBVO");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.bd.pub.BDOrgPanel getOrgpanel(){
		if(context.get("orgpanel")!=null)
			return (nc.ui.bd.pub.BDOrgPanel)context.get("orgpanel");
		nc.ui.bd.pub.BDOrgPanel bean = new nc.ui.bd.pub.BDOrgPanel();
		context.put("orgpanel",bean);
		bean.setModel(getBmModel());
		bean.setDataManager(getBmModelModelDataManager());
		bean.setPk_orgtype("BUSINESSUNIT00000000");
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.view.ShowUpableBillForm getBillForm(){
		if(context.get("billForm")!=null)
			return (nc.ui.pubapp.uif2app.view.ShowUpableBillForm)context.get("billForm");
		nc.ui.pubapp.uif2app.view.ShowUpableBillForm bean = new nc.ui.pubapp.uif2app.view.ShowUpableBillForm();
		context.put("billForm",bean);
		bean.setModel(getBmModel());
		bean.setTemplateContainer(getTemplateContainer());
		bean.setNodekey("bt");
		bean.setShowOrgPanel(true);
		bean.setAutoAddLine(false);
		bean.setBodyLineActions(getManagedList8());
		bean.setUserdefitemPreparator(getCompositeBillDataPrepare_2b234767());
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList8(){  List list = new ArrayList();  list.add(getCommissionBodyAddLineAction_733cd7be());  list.add(getBodyInsertLineAction_1f78360d());  list.add(getBodyDelLineAction_d249f9f());  list.add(getBodyCopyLineAction_5f6b8676());  list.add(getBodyPasteLineAction_c48ddeb());  list.add(getBodyPasteToTailAction_3e1759a());  list.add(getBodyLineEditAction_46efd373());  return list;}

	private nc.ui.qcco.commission.action.CommissionBodyAddLineAction getCommissionBodyAddLineAction_733cd7be(){
		if(context.get("nc.ui.qcco.commission.action.CommissionBodyAddLineAction#733cd7be")!=null)
			return (nc.ui.qcco.commission.action.CommissionBodyAddLineAction)context.get("nc.ui.qcco.commission.action.CommissionBodyAddLineAction#733cd7be");
		nc.ui.qcco.commission.action.CommissionBodyAddLineAction bean = new nc.ui.qcco.commission.action.CommissionBodyAddLineAction();
		context.put("nc.ui.qcco.commission.action.CommissionBodyAddLineAction#733cd7be",bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.actions.BodyInsertLineAction getBodyInsertLineAction_1f78360d(){
		if(context.get("nc.ui.pubapp.uif2app.actions.BodyInsertLineAction#1f78360d")!=null)
			return (nc.ui.pubapp.uif2app.actions.BodyInsertLineAction)context.get("nc.ui.pubapp.uif2app.actions.BodyInsertLineAction#1f78360d");
		nc.ui.pubapp.uif2app.actions.BodyInsertLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyInsertLineAction();
		context.put("nc.ui.pubapp.uif2app.actions.BodyInsertLineAction#1f78360d",bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.actions.BodyDelLineAction getBodyDelLineAction_d249f9f(){
		if(context.get("nc.ui.pubapp.uif2app.actions.BodyDelLineAction#d249f9f")!=null)
			return (nc.ui.pubapp.uif2app.actions.BodyDelLineAction)context.get("nc.ui.pubapp.uif2app.actions.BodyDelLineAction#d249f9f");
		nc.ui.pubapp.uif2app.actions.BodyDelLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyDelLineAction();
		context.put("nc.ui.pubapp.uif2app.actions.BodyDelLineAction#d249f9f",bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.actions.BodyCopyLineAction getBodyCopyLineAction_5f6b8676(){
		if(context.get("nc.ui.pubapp.uif2app.actions.BodyCopyLineAction#5f6b8676")!=null)
			return (nc.ui.pubapp.uif2app.actions.BodyCopyLineAction)context.get("nc.ui.pubapp.uif2app.actions.BodyCopyLineAction#5f6b8676");
		nc.ui.pubapp.uif2app.actions.BodyCopyLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyCopyLineAction();
		context.put("nc.ui.pubapp.uif2app.actions.BodyCopyLineAction#5f6b8676",bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.actions.BodyPasteLineAction getBodyPasteLineAction_c48ddeb(){
		if(context.get("nc.ui.pubapp.uif2app.actions.BodyPasteLineAction#c48ddeb")!=null)
			return (nc.ui.pubapp.uif2app.actions.BodyPasteLineAction)context.get("nc.ui.pubapp.uif2app.actions.BodyPasteLineAction#c48ddeb");
		nc.ui.pubapp.uif2app.actions.BodyPasteLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyPasteLineAction();
		context.put("nc.ui.pubapp.uif2app.actions.BodyPasteLineAction#c48ddeb",bean);
		bean.setClearItems(getManagedList9());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList9(){  List list = new ArrayList();  list.add("pk_commission_b");  list.add("rowno");  return list;}

	private nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction getBodyPasteToTailAction_3e1759a(){
		if(context.get("nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction#3e1759a")!=null)
			return (nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction)context.get("nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction#3e1759a");
		nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction bean = new nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction();
		context.put("nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction#3e1759a",bean);
		bean.setClearItems(getManagedList10());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList10(){  List list = new ArrayList();  list.add("pk_commission_b");  list.add("rowno");  return list;}

	private nc.ui.pubapp.uif2app.actions.BodyLineEditAction getBodyLineEditAction_46efd373(){
		if(context.get("nc.ui.pubapp.uif2app.actions.BodyLineEditAction#46efd373")!=null)
			return (nc.ui.pubapp.uif2app.actions.BodyLineEditAction)context.get("nc.ui.pubapp.uif2app.actions.BodyLineEditAction#46efd373");
		nc.ui.pubapp.uif2app.actions.BodyLineEditAction bean = new nc.ui.pubapp.uif2app.actions.BodyLineEditAction();
		context.put("nc.ui.pubapp.uif2app.actions.BodyLineEditAction#46efd373",bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare getCompositeBillDataPrepare_2b234767(){
		if(context.get("nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare#2b234767")!=null)
			return (nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare)context.get("nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare#2b234767");
		nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare bean = new nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare();
		context.put("nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare#2b234767",bean);
		bean.setBillDataPrepares(getManagedList11());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList11(){  List list = new ArrayList();  list.add(getUserdefitemPreparator());  list.add(getMarAsstPreparator());  return list;}

	public nc.ui.uif2.editor.UserdefitemContainerPreparator getUserdefitemPreparator(){
		if(context.get("userdefitemPreparator")!=null)
			return (nc.ui.uif2.editor.UserdefitemContainerPreparator)context.get("userdefitemPreparator");
		nc.ui.uif2.editor.UserdefitemContainerPreparator bean = new nc.ui.uif2.editor.UserdefitemContainerPreparator();
		context.put("userdefitemPreparator",bean);
		bean.setContainer(getUserdefitemContainer());
		bean.setParams(getManagedList12());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList12(){  List list = new ArrayList();  list.add(getUserdefQueryParam_1924d151());  list.add(getUserdefQueryParam_402a29bc());  return list;}

	private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_1924d151(){
		if(context.get("nc.ui.uif2.editor.UserdefQueryParam#1924d151")!=null)
			return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#1924d151");
		nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
		context.put("nc.ui.uif2.editor.UserdefQueryParam#1924d151",bean);
		bean.setMdfullname("qcco.commission");
		bean.setPos(0);
		bean.setPrefix("vdef");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_402a29bc(){
		if(context.get("nc.ui.uif2.editor.UserdefQueryParam#402a29bc")!=null)
			return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#402a29bc");
		nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
		context.put("nc.ui.uif2.editor.UserdefQueryParam#402a29bc",bean);
		bean.setMdfullname("qcco.CommissionBVO");
		bean.setPos(1);
		bean.setPrefix("vbdef");
		bean.setTabcode("CommissionBVO");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator getMarAsstPreparator(){
		if(context.get("marAsstPreparator")!=null)
			return (nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator)context.get("marAsstPreparator");
		nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator bean = new nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator();
		context.put("marAsstPreparator",bean);
		bean.setModel(getBmModel());
		bean.setContainer(getUserdefitemContainer());
		bean.setPrefix("vfree");
		bean.setMaterialField("pk_productserial");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.userdefitem.UserDefItemContainer getUserdefitemContainer(){
		if(context.get("userdefitemContainer")!=null)
			return (nc.ui.uif2.userdefitem.UserDefItemContainer)context.get("userdefitemContainer");
		nc.ui.uif2.userdefitem.UserDefItemContainer bean = new nc.ui.uif2.userdefitem.UserDefItemContainer();
		context.put("userdefitemContainer",bean);
		bean.setContext(getContext());
		bean.setParams(getManagedList13());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList13(){  List list = new ArrayList();  list.add(getQueryParam_101a168d());  list.add(getQueryParam_20be8b3d());  list.add(getQueryParam_3da76296());  return list;}

	private nc.ui.uif2.userdefitem.QueryParam getQueryParam_101a168d(){
		if(context.get("nc.ui.uif2.userdefitem.QueryParam#101a168d")!=null)
			return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#101a168d");
		nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
		context.put("nc.ui.uif2.userdefitem.QueryParam#101a168d",bean);
		bean.setMdfullname("qcco.commission");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.userdefitem.QueryParam getQueryParam_20be8b3d(){
		if(context.get("nc.ui.uif2.userdefitem.QueryParam#20be8b3d")!=null)
			return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#20be8b3d");
		nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
		context.put("nc.ui.uif2.userdefitem.QueryParam#20be8b3d",bean);
		bean.setMdfullname("qcco.CommissionBVO");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.userdefitem.QueryParam getQueryParam_3da76296(){
		if(context.get("nc.ui.uif2.userdefitem.QueryParam#3da76296")!=null)
			return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#3da76296");
		nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
		context.put("nc.ui.uif2.userdefitem.QueryParam#3da76296",bean);
		bean.setRulecode("materialassistant");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell getQueryArea(){
		if(context.get("queryArea")!=null)
			return (nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell)context.get("queryArea");
		nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell bean = new nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell();
		context.put("queryArea",bean);
		bean.setQueryAreaCreator(getQueryAction());
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel getQueryInfo(){
		if(context.get("queryInfo")!=null)
			return (nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel)context.get("queryInfo");
		nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel bean = new nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel();
		context.put("queryInfo",bean);
		bean.setModel(getBmModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel getViewb(){
		if(context.get("viewb")!=null)
			return (nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel)context.get("viewb");
		nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel bean = new nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel();
		context.put("viewb",bean);
		bean.setModel(getBmModel());
		bean.setTitleAction(getReturnAction());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.actions.UEReturnAction getReturnAction(){
		if(context.get("returnAction")!=null)
			return (nc.ui.pubapp.uif2app.actions.UEReturnAction)context.get("returnAction");
		nc.ui.pubapp.uif2app.actions.UEReturnAction bean = new nc.ui.pubapp.uif2app.actions.UEReturnAction();
		context.put("returnAction",bean);
		bean.setGoComponent(getMainGrandlistView());
		bean.setSaveAction(getSaveAction());
		bean.setModel(getBmModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.TangramContainer getContainer(){
		if(context.get("container")!=null)
			return (nc.ui.uif2.TangramContainer)context.get("container");
		nc.ui.uif2.TangramContainer bean = new nc.ui.uif2.TangramContainer();
		context.put("container",bean);
		bean.setModel(getBmModel());
		bean.setTangramLayoutRoot(getTBNode_1c31e839());
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.TBNode getTBNode_1c31e839(){
		if(context.get("nc.ui.uif2.tangramlayout.node.TBNode#1c31e839")!=null)
			return (nc.ui.uif2.tangramlayout.node.TBNode)context.get("nc.ui.uif2.tangramlayout.node.TBNode#1c31e839");
		nc.ui.uif2.tangramlayout.node.TBNode bean = new nc.ui.uif2.tangramlayout.node.TBNode();
		context.put("nc.ui.uif2.tangramlayout.node.TBNode#1c31e839",bean);
		bean.setShowMode("CardLayout");
		bean.setTabs(getManagedList14());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList14(){  List list = new ArrayList();  list.add(getVSNode_4c93df57());  list.add(getVSNode_405d09da());  return list;}

	private nc.ui.uif2.tangramlayout.node.VSNode getVSNode_4c93df57(){
		if(context.get("nc.ui.uif2.tangramlayout.node.VSNode#4c93df57")!=null)
			return (nc.ui.uif2.tangramlayout.node.VSNode)context.get("nc.ui.uif2.tangramlayout.node.VSNode#4c93df57");
		nc.ui.uif2.tangramlayout.node.VSNode bean = new nc.ui.uif2.tangramlayout.node.VSNode();
		context.put("nc.ui.uif2.tangramlayout.node.VSNode#4c93df57",bean);
		bean.setShowMode("NoDivider");
		bean.setUp(getCNode_38398579());
		bean.setDown(getHSNode_51bee886());
		bean.setDividerLocation(30f);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.CNode getCNode_38398579(){
		if(context.get("nc.ui.uif2.tangramlayout.node.CNode#38398579")!=null)
			return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#38398579");
		nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
		context.put("nc.ui.uif2.tangramlayout.node.CNode#38398579",bean);
		bean.setComponent(getOrgpanel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.HSNode getHSNode_51bee886(){
		if(context.get("nc.ui.uif2.tangramlayout.node.HSNode#51bee886")!=null)
			return (nc.ui.uif2.tangramlayout.node.HSNode)context.get("nc.ui.uif2.tangramlayout.node.HSNode#51bee886");
		nc.ui.uif2.tangramlayout.node.HSNode bean = new nc.ui.uif2.tangramlayout.node.HSNode();
		context.put("nc.ui.uif2.tangramlayout.node.HSNode#51bee886",bean);
		bean.setLeft(getCNode_4869e7c5());
		bean.setRight(getVSNode_79bcabfa());
		bean.setDividerLocation(210f);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.CNode getCNode_4869e7c5(){
		if(context.get("nc.ui.uif2.tangramlayout.node.CNode#4869e7c5")!=null)
			return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#4869e7c5");
		nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
		context.put("nc.ui.uif2.tangramlayout.node.CNode#4869e7c5",bean);
		bean.setComponent(getQueryArea());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.VSNode getVSNode_79bcabfa(){
		if(context.get("nc.ui.uif2.tangramlayout.node.VSNode#79bcabfa")!=null)
			return (nc.ui.uif2.tangramlayout.node.VSNode)context.get("nc.ui.uif2.tangramlayout.node.VSNode#79bcabfa");
		nc.ui.uif2.tangramlayout.node.VSNode bean = new nc.ui.uif2.tangramlayout.node.VSNode();
		context.put("nc.ui.uif2.tangramlayout.node.VSNode#79bcabfa",bean);
		bean.setUp(getCNode_20ef5ba7());
		bean.setDown(getCNode_4e082903());
		bean.setDividerLocation(25f);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.CNode getCNode_20ef5ba7(){
		if(context.get("nc.ui.uif2.tangramlayout.node.CNode#20ef5ba7")!=null)
			return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#20ef5ba7");
		nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
		context.put("nc.ui.uif2.tangramlayout.node.CNode#20ef5ba7",bean);
		bean.setComponent(getQueryInfo());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.CNode getCNode_4e082903(){
		if(context.get("nc.ui.uif2.tangramlayout.node.CNode#4e082903")!=null)
			return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#4e082903");
		nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
		context.put("nc.ui.uif2.tangramlayout.node.CNode#4e082903",bean);
		bean.setName(getI18nFB_426a41d5());
		bean.setComponent(getMainGrandlistView());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_426a41d5(){
		if(context.get("nc.ui.uif2.I18nFB#426a41d5")!=null)
			return (java.lang.String)context.get("nc.ui.uif2.I18nFB#426a41d5");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#426a41d5",bean);  bean.setResDir("common");
		bean.setResId("UC001-0000107");
		bean.setDefaultValue("列表");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#426a41d5",product);
			return (java.lang.String)product;
		}
		catch(Exception e) { throw new RuntimeException(e);}}

	private nc.ui.uif2.tangramlayout.node.VSNode getVSNode_405d09da(){
		if(context.get("nc.ui.uif2.tangramlayout.node.VSNode#405d09da")!=null)
			return (nc.ui.uif2.tangramlayout.node.VSNode)context.get("nc.ui.uif2.tangramlayout.node.VSNode#405d09da");
		nc.ui.uif2.tangramlayout.node.VSNode bean = new nc.ui.uif2.tangramlayout.node.VSNode();
		context.put("nc.ui.uif2.tangramlayout.node.VSNode#405d09da",bean);
		bean.setUp(getCNode_2bf008());
		bean.setDown(getCNode_44748f12());
		bean.setDividerLocation(30f);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.CNode getCNode_2bf008(){
		if(context.get("nc.ui.uif2.tangramlayout.node.CNode#2bf008")!=null)
			return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#2bf008");
		nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
		context.put("nc.ui.uif2.tangramlayout.node.CNode#2bf008",bean);
		bean.setComponent(getViewb());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.CNode getCNode_44748f12(){
		if(context.get("nc.ui.uif2.tangramlayout.node.CNode#44748f12")!=null)
			return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#44748f12");
		nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
		context.put("nc.ui.uif2.tangramlayout.node.CNode#44748f12",bean);
		bean.setName(getI18nFB_449b9d4e());
		bean.setComponent(getMainGrandbillForm());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_449b9d4e(){
		if(context.get("nc.ui.uif2.I18nFB#449b9d4e")!=null)
			return (java.lang.String)context.get("nc.ui.uif2.I18nFB#449b9d4e");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#449b9d4e",bean);  bean.setResDir("common");
		bean.setResId("UC001-0000106");
		bean.setDefaultValue("卡片");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#449b9d4e",product);
			return (java.lang.String)product;
		}
		catch(Exception e) { throw new RuntimeException(e);}}

	public nc.ui.pubapp.uif2app.event.ChildrenPicky getChildrenPicky(){
		if(context.get("childrenPicky")!=null)
			return (nc.ui.pubapp.uif2app.event.ChildrenPicky)context.get("childrenPicky");
		nc.ui.pubapp.uif2app.event.ChildrenPicky bean = new nc.ui.pubapp.uif2app.event.ChildrenPicky();
		context.put("childrenPicky",bean);
		bean.setBillform(getBillForm());
		bean.setBodyVoClasses(getManagedList15());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList15(){  List list = new ArrayList();  list.add("nc.vo.qcco.commission.CommissionBVO");  return list;}

	public nc.ui.uif2.actions.ActionContributors getToftpanelActionContributors(){
		if(context.get("toftpanelActionContributors")!=null)
			return (nc.ui.uif2.actions.ActionContributors)context.get("toftpanelActionContributors");
		nc.ui.uif2.actions.ActionContributors bean = new nc.ui.uif2.actions.ActionContributors();
		context.put("toftpanelActionContributors",bean);
		bean.setContributors(getManagedList16());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList16(){  List list = new ArrayList();  list.add(getActionsOfList());  list.add(getActionsOfCard());  return list;}

	public nc.ui.uif2.actions.StandAloneToftPanelActionContainer getActionsOfList(){
		if(context.get("actionsOfList")!=null)
			return (nc.ui.uif2.actions.StandAloneToftPanelActionContainer)context.get("actionsOfList");
		nc.ui.uif2.actions.StandAloneToftPanelActionContainer bean = new nc.ui.uif2.actions.StandAloneToftPanelActionContainer(getBillListView());  context.put("actionsOfList",bean);
		bean.setModel(getBmModel());
		bean.setActions(getManagedList17());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList17(){  List list = new ArrayList();  list.add(getAddAction());  list.add(getEditAction());  list.add(getDeleteAction());  list.add(getCopyAction());  list.add(getSeparatorAction());  list.add(getQueryAction());  list.add(getRefreshAction());  list.add(getSeparatorAction());  list.add(getPrintMenuAction());  list.add(getSeparatorAction());  list.add(getEditTaskAction());  return list;}

	public nc.ui.uif2.actions.StandAloneToftPanelActionContainer getActionsOfCard(){
		if(context.get("actionsOfCard")!=null)
			return (nc.ui.uif2.actions.StandAloneToftPanelActionContainer)context.get("actionsOfCard");
		nc.ui.uif2.actions.StandAloneToftPanelActionContainer bean = new nc.ui.uif2.actions.StandAloneToftPanelActionContainer(getBillForm());  context.put("actionsOfCard",bean);
		bean.setModel(getBmModel());
		bean.setActions(getManagedList18());
		bean.setEditActions(getManagedList19());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList18(){  List list = new ArrayList();  list.add(getAddAction());  list.add(getEditAction());  list.add(getDeleteAction());  list.add(getCopyAction());  list.add(getSeparatorAction());  list.add(getQueryAction());  list.add(getCardRefreshAction());  list.add(getSeparatorAction());  list.add(getPrintMenuAction());  list.add(getSeparatorAction());  list.add(getEditTaskAction());  return list;}

	private List getManagedList19(){  List list = new ArrayList();  list.add(getSaveAction());  list.add(getCancelAction());  return list;}

	public nc.funcnode.ui.action.SeparatorAction getSeparatorAction(){
		if(context.get("separatorAction")!=null)
			return (nc.funcnode.ui.action.SeparatorAction)context.get("separatorAction");
		nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
		context.put("separatorAction",bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor getShowListInterceptor(){
		if(context.get("showListInterceptor")!=null)
			return (nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor)context.get("showListInterceptor");
		nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor bean = new nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor();
		context.put("showListInterceptor",bean);
		bean.setShowUpComponent(getMainGrandlistView());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor getShowCardInterceptor(){
		if(context.get("showCardInterceptor")!=null)
			return (nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor)context.get("showCardInterceptor");
		nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor bean = new nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor();
		context.put("showCardInterceptor",bean);
		bean.setShowUpComponent(getMainGrandbillForm());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.actions.AddAction getAddAction(){
		if(context.get("addAction")!=null)
			return (nc.ui.uif2.actions.AddAction)context.get("addAction");
		nc.ui.uif2.actions.AddAction bean = new nc.ui.uif2.actions.AddAction();
		context.put("addAction",bean);
		bean.setModel(getBmModel());
		bean.setInterceptor(getShowCardInterceptor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.actions.EditAction getEditAction(){
		if(context.get("editAction")!=null)
			return (nc.ui.uif2.actions.EditAction)context.get("editAction");
		nc.ui.uif2.actions.EditAction bean = new nc.ui.uif2.actions.EditAction();
		context.put("editAction",bean);
		bean.setModel(getBmModel());
		bean.setInterceptor(getShowCardInterceptor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.qcco.commission.action.CommissionDeleteAction getDeleteAction(){
		if(context.get("deleteAction")!=null)
			return (nc.ui.qcco.commission.action.CommissionDeleteAction)context.get("deleteAction");
		nc.ui.qcco.commission.action.CommissionDeleteAction bean = new nc.ui.qcco.commission.action.CommissionDeleteAction();
		context.put("deleteAction",bean);
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

	public nc.ui.qcco.commission.action.CommissionSaveAction getSaveAction(){
		if(context.get("saveAction")!=null)
			return (nc.ui.qcco.commission.action.CommissionSaveAction)context.get("saveAction");
		nc.ui.qcco.commission.action.CommissionSaveAction bean = new nc.ui.qcco.commission.action.CommissionSaveAction();
		context.put("saveAction",bean);
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

	public nc.ui.pubapp.uif2app.validation.CompositeValidation getValidateService(){
		if(context.get("validateService")!=null)
			return (nc.ui.pubapp.uif2app.validation.CompositeValidation)context.get("validateService");
		nc.ui.pubapp.uif2app.validation.CompositeValidation bean = new nc.ui.pubapp.uif2app.validation.CompositeValidation();
		context.put("validateService",bean);
		bean.setValidators(getManagedList20());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList20(){  List list = new ArrayList();  list.add(getTemplateNotNullValidation_108504a5());  return list;}

	private nc.ui.pubapp.uif2app.validation.TemplateNotNullValidation getTemplateNotNullValidation_108504a5(){
		if(context.get("nc.ui.pubapp.uif2app.validation.TemplateNotNullValidation#108504a5")!=null)
			return (nc.ui.pubapp.uif2app.validation.TemplateNotNullValidation)context.get("nc.ui.pubapp.uif2app.validation.TemplateNotNullValidation#108504a5");
		nc.ui.pubapp.uif2app.validation.TemplateNotNullValidation bean = new nc.ui.pubapp.uif2app.validation.TemplateNotNullValidation();
		context.put("nc.ui.pubapp.uif2app.validation.TemplateNotNullValidation#108504a5",bean);
		bean.setBillForm(getBillForm());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.CancelAction getCancelAction(){
		if(context.get("cancelAction")!=null)
			return (nc.ui.pubapp.uif2app.actions.CancelAction)context.get("cancelAction");
		nc.ui.pubapp.uif2app.actions.CancelAction bean = new nc.ui.pubapp.uif2app.actions.CancelAction();
		context.put("cancelAction",bean);
		bean.setModel(getBmModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.CopyAction getCopyAction(){
		if(context.get("copyAction")!=null)
			return (nc.ui.pubapp.uif2app.actions.CopyAction)context.get("copyAction");
		nc.ui.pubapp.uif2app.actions.CopyAction bean = new nc.ui.pubapp.uif2app.actions.CopyAction();
		context.put("copyAction",bean);
		bean.setModel(getBmModel());
		bean.setInterceptor(getShowCardInterceptor());
		bean.setEditor(getBillForm());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction getRefreshAction(){
		if(context.get("refreshAction")!=null)
			return (nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction)context.get("refreshAction");
		nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction bean = new nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction();
		context.put("refreshAction",bean);
		bean.setDataManager(getBmModelModelDataManager());
		bean.setModel(getBmModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.RefreshSingleAction getCardRefreshAction(){
		if(context.get("cardRefreshAction")!=null)
			return (nc.ui.pubapp.uif2app.actions.RefreshSingleAction)context.get("cardRefreshAction");
		nc.ui.pubapp.uif2app.actions.RefreshSingleAction bean = new nc.ui.pubapp.uif2app.actions.RefreshSingleAction();
		context.put("cardRefreshAction",bean);
		bean.setModel(getBmModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction getPrintAction(){
		if(context.get("printAction")!=null)
			return (nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction)context.get("printAction");
		nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction bean = new nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction();
		context.put("printAction",bean);
		bean.setPreview(false);
		bean.setModel(getBmModel());
		bean.setNodeKey("ot");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction getPreviewAction(){
		if(context.get("previewAction")!=null)
			return (nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction)context.get("previewAction");
		nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction bean = new nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction();
		context.put("previewAction",bean);
		bean.setPreview(true);
		bean.setModel(getBmModel());
		bean.setNodeKey("ot");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.OutputAction getOutputAction(){
		if(context.get("outputAction")!=null)
			return (nc.ui.pubapp.uif2app.actions.OutputAction)context.get("outputAction");
		nc.ui.pubapp.uif2app.actions.OutputAction bean = new nc.ui.pubapp.uif2app.actions.OutputAction();
		context.put("outputAction",bean);
		bean.setModel(getBmModel());
		bean.setParent(getBillForm());
		bean.setNodeKey("ot");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.funcnode.ui.action.GroupAction getPrintMenuAction(){
		if(context.get("printMenuAction")!=null)
			return (nc.funcnode.ui.action.GroupAction)context.get("printMenuAction");
		nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
		context.put("printMenuAction",bean);
		bean.setCode("printMenuAction");
		bean.setName(getI18nFB_cf3f51c());
		bean.setActions(getManagedList21());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_cf3f51c(){
		if(context.get("nc.ui.uif2.I18nFB#cf3f51c")!=null)
			return (java.lang.String)context.get("nc.ui.uif2.I18nFB#cf3f51c");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#cf3f51c",bean);  bean.setResDir("common");
		bean.setResId("UC001-0000007");
		bean.setDefaultValue("打印");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#cf3f51c",product);
			return (java.lang.String)product;
		}
		catch(Exception e) { throw new RuntimeException(e);}}

	private List getManagedList21(){  List list = new ArrayList();  list.add(getPrintAction());  list.add(getPreviewAction());  list.add(getOutputAction());  return list;}

	public nc.ui.pubapp.uif2app.model.BillBodySortMediator getBillBodySortMediator(){
		if(context.get("billBodySortMediator")!=null)
			return (nc.ui.pubapp.uif2app.model.BillBodySortMediator)context.get("billBodySortMediator");
		nc.ui.pubapp.uif2app.model.BillBodySortMediator bean = new nc.ui.pubapp.uif2app.model.BillBodySortMediator(getBmModel(),getBillForm(),getBillListView());  context.put("billBodySortMediator",bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener getInitDataListener(){
		if(context.get("InitDataListener")!=null)
			return (nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener)context.get("InitDataListener");
		nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener bean = new nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener();
		context.put("InitDataListener",bean);
		bean.setModel(getBmModel());
		bean.setContext(getContext());
		bean.setVoClassName("nc.vo.qcco.commission.AggCommissionHVO");
		bean.setAutoShowUpComponent(getBillForm());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.common.validateservice.ClosingCheck getClosingListener(){
		if(context.get("ClosingListener")!=null)
			return (nc.ui.pubapp.common.validateservice.ClosingCheck)context.get("ClosingListener");
		nc.ui.pubapp.common.validateservice.ClosingCheck bean = new nc.ui.pubapp.common.validateservice.ClosingCheck();
		context.put("ClosingListener",bean);
		bean.setModel(getBmModel());
		bean.setSaveAction(getSaveAction());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.model.AppEventHandlerMediator getBmModelEventMediator(){
		if(context.get("bmModelEventMediator")!=null)
			return (nc.ui.pubapp.uif2app.model.AppEventHandlerMediator)context.get("bmModelEventMediator");
		nc.ui.pubapp.uif2app.model.AppEventHandlerMediator bean = new nc.ui.pubapp.uif2app.model.AppEventHandlerMediator();
		context.put("bmModelEventMediator",bean);
		bean.setModel(getBmModel());
		bean.setHandlerGroup(getManagedList22());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList22(){  List list = new ArrayList();  list.add(getEventHandlerGroup_1e4e43d4());  list.add(getEventHandlerGroup_3a4063dc());  list.add(getEventHandlerGroup_2ecf6511());  list.add(getEventHandlerGroup_208243ee());  list.add(getEventHandlerGroup_4b40904d());  list.add(getEventHandlerGroup_72bd4f1e());  return list;}

	private nc.ui.pubapp.uif2app.event.EventHandlerGroup getEventHandlerGroup_1e4e43d4(){
		if(context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#1e4e43d4")!=null)
			return (nc.ui.pubapp.uif2app.event.EventHandlerGroup)context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#1e4e43d4");
		nc.ui.pubapp.uif2app.event.EventHandlerGroup bean = new nc.ui.pubapp.uif2app.event.EventHandlerGroup();
		context.put("nc.ui.pubapp.uif2app.event.EventHandlerGroup#1e4e43d4",bean);
		bean.setEvent("nc.ui.pubapp.uif2app.event.OrgChangedEvent");
		bean.setHandler(getAceOrgChangeHandler_646107e8());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.qcco.commission.ace.handler.AceOrgChangeHandler getAceOrgChangeHandler_646107e8(){
		if(context.get("nc.ui.qcco.commission.ace.handler.AceOrgChangeHandler#646107e8")!=null)
			return (nc.ui.qcco.commission.ace.handler.AceOrgChangeHandler)context.get("nc.ui.qcco.commission.ace.handler.AceOrgChangeHandler#646107e8");
		nc.ui.qcco.commission.ace.handler.AceOrgChangeHandler bean = new nc.ui.qcco.commission.ace.handler.AceOrgChangeHandler();
		context.put("nc.ui.qcco.commission.ace.handler.AceOrgChangeHandler#646107e8",bean);
		bean.setBillForm(getBillForm());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.event.EventHandlerGroup getEventHandlerGroup_3a4063dc(){
		if(context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#3a4063dc")!=null)
			return (nc.ui.pubapp.uif2app.event.EventHandlerGroup)context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#3a4063dc");
		nc.ui.pubapp.uif2app.event.EventHandlerGroup bean = new nc.ui.pubapp.uif2app.event.EventHandlerGroup();
		context.put("nc.ui.pubapp.uif2app.event.EventHandlerGroup#3a4063dc",bean);
		bean.setEvent("nc.ui.pubapp.uif2app.event.billform.AddEvent");
		bean.setHandler(getAceAddHandler_244722d7());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.qcco.commission.ace.handler.AceAddHandler getAceAddHandler_244722d7(){
		if(context.get("nc.ui.qcco.commission.ace.handler.AceAddHandler#244722d7")!=null)
			return (nc.ui.qcco.commission.ace.handler.AceAddHandler)context.get("nc.ui.qcco.commission.ace.handler.AceAddHandler#244722d7");
		nc.ui.qcco.commission.ace.handler.AceAddHandler bean = new nc.ui.qcco.commission.ace.handler.AceAddHandler();
		context.put("nc.ui.qcco.commission.ace.handler.AceAddHandler#244722d7",bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.event.EventHandlerGroup getEventHandlerGroup_2ecf6511(){
		if(context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#2ecf6511")!=null)
			return (nc.ui.pubapp.uif2app.event.EventHandlerGroup)context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#2ecf6511");
		nc.ui.pubapp.uif2app.event.EventHandlerGroup bean = new nc.ui.pubapp.uif2app.event.EventHandlerGroup();
		context.put("nc.ui.pubapp.uif2app.event.EventHandlerGroup#2ecf6511",bean);
		bean.setEvent("nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent");
		bean.setHandler(getAceHeadTailBeforeEditHandler_595c1e64());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.qcco.commission.ace.handler.AceHeadTailBeforeEditHandler getAceHeadTailBeforeEditHandler_595c1e64(){
		if(context.get("nc.ui.qcco.commission.ace.handler.AceHeadTailBeforeEditHandler#595c1e64")!=null)
			return (nc.ui.qcco.commission.ace.handler.AceHeadTailBeforeEditHandler)context.get("nc.ui.qcco.commission.ace.handler.AceHeadTailBeforeEditHandler#595c1e64");
		nc.ui.qcco.commission.ace.handler.AceHeadTailBeforeEditHandler bean = new nc.ui.qcco.commission.ace.handler.AceHeadTailBeforeEditHandler();
		context.put("nc.ui.qcco.commission.ace.handler.AceHeadTailBeforeEditHandler#595c1e64",bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.event.EventHandlerGroup getEventHandlerGroup_208243ee(){
		if(context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#208243ee")!=null)
			return (nc.ui.pubapp.uif2app.event.EventHandlerGroup)context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#208243ee");
		nc.ui.pubapp.uif2app.event.EventHandlerGroup bean = new nc.ui.pubapp.uif2app.event.EventHandlerGroup();
		context.put("nc.ui.pubapp.uif2app.event.EventHandlerGroup#208243ee",bean);
		bean.setEvent("nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent");
		bean.setHandler(getAceHeadTailAfterEditHandler_31f45719());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.qcco.commission.ace.handler.AceHeadTailAfterEditHandler getAceHeadTailAfterEditHandler_31f45719(){
		if(context.get("nc.ui.qcco.commission.ace.handler.AceHeadTailAfterEditHandler#31f45719")!=null)
			return (nc.ui.qcco.commission.ace.handler.AceHeadTailAfterEditHandler)context.get("nc.ui.qcco.commission.ace.handler.AceHeadTailAfterEditHandler#31f45719");
		nc.ui.qcco.commission.ace.handler.AceHeadTailAfterEditHandler bean = new nc.ui.qcco.commission.ace.handler.AceHeadTailAfterEditHandler();
		context.put("nc.ui.qcco.commission.ace.handler.AceHeadTailAfterEditHandler#31f45719",bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.event.EventHandlerGroup getEventHandlerGroup_4b40904d(){
		if(context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#4b40904d")!=null)
			return (nc.ui.pubapp.uif2app.event.EventHandlerGroup)context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#4b40904d");
		nc.ui.pubapp.uif2app.event.EventHandlerGroup bean = new nc.ui.pubapp.uif2app.event.EventHandlerGroup();
		context.put("nc.ui.pubapp.uif2app.event.EventHandlerGroup#4b40904d",bean);
		bean.setEvent("nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent");
		bean.setHandler(getAceBodyBeforeEditHandler_4839ceee());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.qcco.commission.ace.handler.AceBodyBeforeEditHandler getAceBodyBeforeEditHandler_4839ceee(){
		if(context.get("nc.ui.qcco.commission.ace.handler.AceBodyBeforeEditHandler#4839ceee")!=null)
			return (nc.ui.qcco.commission.ace.handler.AceBodyBeforeEditHandler)context.get("nc.ui.qcco.commission.ace.handler.AceBodyBeforeEditHandler#4839ceee");
		nc.ui.qcco.commission.ace.handler.AceBodyBeforeEditHandler bean = new nc.ui.qcco.commission.ace.handler.AceBodyBeforeEditHandler();
		context.put("nc.ui.qcco.commission.ace.handler.AceBodyBeforeEditHandler#4839ceee",bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.event.EventHandlerGroup getEventHandlerGroup_72bd4f1e(){
		if(context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#72bd4f1e")!=null)
			return (nc.ui.pubapp.uif2app.event.EventHandlerGroup)context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#72bd4f1e");
		nc.ui.pubapp.uif2app.event.EventHandlerGroup bean = new nc.ui.pubapp.uif2app.event.EventHandlerGroup();
		context.put("nc.ui.pubapp.uif2app.event.EventHandlerGroup#72bd4f1e",bean);
		bean.setEvent("nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent");
		bean.setHandler(getAceBodyAfterEditHandler_7bc1d5d3());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.qcco.commission.ace.handler.AceBodyAfterEditHandler getAceBodyAfterEditHandler_7bc1d5d3(){
		if(context.get("nc.ui.qcco.commission.ace.handler.AceBodyAfterEditHandler#7bc1d5d3")!=null)
			return (nc.ui.qcco.commission.ace.handler.AceBodyAfterEditHandler)context.get("nc.ui.qcco.commission.ace.handler.AceBodyAfterEditHandler#7bc1d5d3");
		nc.ui.qcco.commission.ace.handler.AceBodyAfterEditHandler bean = new nc.ui.qcco.commission.ace.handler.AceBodyAfterEditHandler();
		context.put("nc.ui.qcco.commission.ace.handler.AceBodyAfterEditHandler#7bc1d5d3",bean);
		bean.setGrandCard(getSunbillForm1());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.lazilyload.DefaultBillLazilyLoader getBillLazilyLoader(){
		if(context.get("billLazilyLoader")!=null)
			return (nc.ui.pubapp.uif2app.lazilyload.DefaultBillLazilyLoader)context.get("billLazilyLoader");
		nc.ui.pubapp.uif2app.lazilyload.DefaultBillLazilyLoader bean = new nc.ui.pubapp.uif2app.lazilyload.DefaultBillLazilyLoader();
		context.put("billLazilyLoader",bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager getBmModelLasilyLodadMediator(){
		if(context.get("bmModelLasilyLodadMediator")!=null)
			return (nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager)context.get("bmModelLasilyLodadMediator");
		nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager bean = new nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager();
		context.put("bmModelLasilyLodadMediator",bean);
		bean.setModel(getBmModel());
		bean.setLoader(getBillLazilyLoader());
		bean.setLazilyLoadSupporter(getManagedList23());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList23(){  List list = new ArrayList();  list.add(getCardPanelLazilyLoad_6a95756b());  list.add(getListPanelLazilyLoad_7a4ffa77());  return list;}

	private nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad getCardPanelLazilyLoad_6a95756b(){
		if(context.get("nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad#6a95756b")!=null)
			return (nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad)context.get("nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad#6a95756b");
		nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad bean = new nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad();
		context.put("nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad#6a95756b",bean);
		bean.setBillform(getBillForm());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad getListPanelLazilyLoad_7a4ffa77(){
		if(context.get("nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad#7a4ffa77")!=null)
			return (nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad)context.get("nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad#7a4ffa77");
		nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad bean = new nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad();
		context.put("nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad#7a4ffa77",bean);
		bean.setListView(getBillListView());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.view.RowNoMediator getRowNoMediator(){
		if(context.get("rowNoMediator")!=null)
			return (nc.ui.pubapp.uif2app.view.RowNoMediator)context.get("rowNoMediator");
		nc.ui.pubapp.uif2app.view.RowNoMediator bean = new nc.ui.pubapp.uif2app.view.RowNoMediator();
		context.put("rowNoMediator",bean);
		bean.setModel(getBmModel());
		bean.setEditor(getBillForm());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator getMouseClickShowPanelMediator(){
		if(context.get("mouseClickShowPanelMediator")!=null)
			return (nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator)context.get("mouseClickShowPanelMediator");
		nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator bean = new nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator();
		context.put("mouseClickShowPanelMediator",bean);
		bean.setListView(getBillListView());
		bean.setShowUpComponent(getMainGrandbillForm());
		bean.setHyperLinkColumn("null");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.editor.QueryTemplateContainer getDefaultQueryActionQueryTemplateContainer(){
		if(context.get("defaultQueryActionQueryTemplateContainer")!=null)
			return (nc.ui.uif2.editor.QueryTemplateContainer)context.get("defaultQueryActionQueryTemplateContainer");
		nc.ui.uif2.editor.QueryTemplateContainer bean = new nc.ui.uif2.editor.QueryTemplateContainer();
		context.put("defaultQueryActionQueryTemplateContainer",bean);
		bean.setNodeKey("qt");
		bean.setContext(getContext());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction getQueryAction(){
		if(context.get("queryAction")!=null)
			return (nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction)context.get("queryAction");
		nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction bean = new nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction();
		context.put("queryAction",bean);
		bean.setModel(getBmModel());
		bean.setTemplateContainer(getDefaultQueryActionQueryTemplateContainer());
		bean.setNodeKey("qt");
		bean.setDataManager(getBmModelModelDataManager());
		bean.setExceptionHandler(getExceptionHandler());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction getMetaDataBasedPrintAction(){
		if(context.get("metaDataBasedPrintAction")!=null)
			return (nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction)context.get("metaDataBasedPrintAction");
		nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction bean = new nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction();
		context.put("metaDataBasedPrintAction",bean);
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

	public nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction getMetaDataBasedPrintActiona(){
		if(context.get("metaDataBasedPrintActiona")!=null)
			return (nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction)context.get("metaDataBasedPrintActiona");
		nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction bean = new nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction();
		context.put("metaDataBasedPrintActiona",bean);
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

	public nc.ui.pubapp.uif2app.actions.pflow.SaveScriptAction getSaveScriptAction(){
		if(context.get("saveScriptAction")!=null)
			return (nc.ui.pubapp.uif2app.actions.pflow.SaveScriptAction)context.get("saveScriptAction");
		nc.ui.pubapp.uif2app.actions.pflow.SaveScriptAction bean = new nc.ui.pubapp.uif2app.actions.pflow.SaveScriptAction();
		context.put("saveScriptAction",bean);
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

	public nc.ui.qcco.commission.action.CheckAllAction getCheckAllAction(){
		if(context.get("checkAllAction")!=null)
			return (nc.ui.qcco.commission.action.CheckAllAction)context.get("checkAllAction");
		nc.ui.qcco.commission.action.CheckAllAction bean = new nc.ui.qcco.commission.action.CheckAllAction();
		context.put("checkAllAction",bean);
		bean.setBillListPanel(getBillListView());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.qcco.commission.action.EditTaskAction getEditTaskAction(){
		if(context.get("editTaskAction")!=null)
			return (nc.ui.qcco.commission.action.EditTaskAction)context.get("editTaskAction");
		nc.ui.qcco.commission.action.EditTaskAction bean = new nc.ui.qcco.commission.action.EditTaskAction();
		context.put("editTaskAction",bean);
		bean.setBillListPanel(getBillListView());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.DefaultExceptionHanler getExceptionHandler(){
		if(context.get("exceptionHandler")!=null)
			return (nc.ui.uif2.DefaultExceptionHanler)context.get("exceptionHandler");
		nc.ui.uif2.DefaultExceptionHanler bean = new nc.ui.uif2.DefaultExceptionHanler(getContainer());  context.put("exceptionHandler",bean);
		bean.setContext(getContext());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.view.FractionFixMediator getFractionFixMediator(){
		if(context.get("fractionFixMediator")!=null)
			return (nc.ui.pubapp.uif2app.view.FractionFixMediator)context.get("fractionFixMediator");
		nc.ui.pubapp.uif2app.view.FractionFixMediator bean = new nc.ui.pubapp.uif2app.view.FractionFixMediator(getBillForm());  context.put("fractionFixMediator",bean);
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller getRemoteCallCombinatorCaller(){
		if(context.get("remoteCallCombinatorCaller")!=null)
			return (nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller)context.get("remoteCallCombinatorCaller");
		nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller bean = new nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller();
		context.put("remoteCallCombinatorCaller",bean);
		bean.setRemoteCallers(getManagedList24());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList24(){  List list = new ArrayList();  list.add(getQueryTemplateContainer());  list.add(getTemplateContainer());  list.add(getUserdefitemContainer());  return list;}

}
