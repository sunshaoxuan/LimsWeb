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
  bean.setNodekey("param");
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

private List getManagedList1(){  List list = new ArrayList();  list.add(getSelectAllLineAction_4b9d7ce8());  list.add(getSelectNoneLineAction_50738a20());  list.add(getGrandBodyAddLineAction_51716423());  list.add(getBodyInsertLineAction_6b655f9a());  list.add(getBodyDelLineAction_4c67ae8b());  list.add(getBodyCopyLineAction_2af44edd());  list.add(getBodyPasteLineAction_d65a533());  list.add(getBodyPasteToTailAction_c40be24());  list.add(getBodyLineEditAction_6e8333c4());  return list;}

private nc.ui.qcco.commission.action.SelectAllLineAction getSelectAllLineAction_4b9d7ce8(){
 if(context.get("nc.ui.qcco.commission.action.SelectAllLineAction#4b9d7ce8")!=null)
 return (nc.ui.qcco.commission.action.SelectAllLineAction)context.get("nc.ui.qcco.commission.action.SelectAllLineAction#4b9d7ce8");
  nc.ui.qcco.commission.action.SelectAllLineAction bean = new nc.ui.qcco.commission.action.SelectAllLineAction();
  context.put("nc.ui.qcco.commission.action.SelectAllLineAction#4b9d7ce8",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.qcco.commission.action.SelectNoneLineAction getSelectNoneLineAction_50738a20(){
 if(context.get("nc.ui.qcco.commission.action.SelectNoneLineAction#50738a20")!=null)
 return (nc.ui.qcco.commission.action.SelectNoneLineAction)context.get("nc.ui.qcco.commission.action.SelectNoneLineAction#50738a20");
  nc.ui.qcco.commission.action.SelectNoneLineAction bean = new nc.ui.qcco.commission.action.SelectNoneLineAction();
  context.put("nc.ui.qcco.commission.action.SelectNoneLineAction#50738a20",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.components.grand.action.GrandBodyAddLineAction getGrandBodyAddLineAction_51716423(){
 if(context.get("nc.ui.pubapp.uif2app.components.grand.action.GrandBodyAddLineAction#51716423")!=null)
 return (nc.ui.pubapp.uif2app.components.grand.action.GrandBodyAddLineAction)context.get("nc.ui.pubapp.uif2app.components.grand.action.GrandBodyAddLineAction#51716423");
  nc.ui.pubapp.uif2app.components.grand.action.GrandBodyAddLineAction bean = new nc.ui.pubapp.uif2app.components.grand.action.GrandBodyAddLineAction();
  context.put("nc.ui.pubapp.uif2app.components.grand.action.GrandBodyAddLineAction#51716423",bean);
  bean.setMainForm(getBillForm());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.actions.BodyInsertLineAction getBodyInsertLineAction_6b655f9a(){
 if(context.get("nc.ui.pubapp.uif2app.actions.BodyInsertLineAction#6b655f9a")!=null)
 return (nc.ui.pubapp.uif2app.actions.BodyInsertLineAction)context.get("nc.ui.pubapp.uif2app.actions.BodyInsertLineAction#6b655f9a");
  nc.ui.pubapp.uif2app.actions.BodyInsertLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyInsertLineAction();
  context.put("nc.ui.pubapp.uif2app.actions.BodyInsertLineAction#6b655f9a",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.actions.BodyDelLineAction getBodyDelLineAction_4c67ae8b(){
 if(context.get("nc.ui.pubapp.uif2app.actions.BodyDelLineAction#4c67ae8b")!=null)
 return (nc.ui.pubapp.uif2app.actions.BodyDelLineAction)context.get("nc.ui.pubapp.uif2app.actions.BodyDelLineAction#4c67ae8b");
  nc.ui.pubapp.uif2app.actions.BodyDelLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyDelLineAction();
  context.put("nc.ui.pubapp.uif2app.actions.BodyDelLineAction#4c67ae8b",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.actions.BodyCopyLineAction getBodyCopyLineAction_2af44edd(){
 if(context.get("nc.ui.pubapp.uif2app.actions.BodyCopyLineAction#2af44edd")!=null)
 return (nc.ui.pubapp.uif2app.actions.BodyCopyLineAction)context.get("nc.ui.pubapp.uif2app.actions.BodyCopyLineAction#2af44edd");
  nc.ui.pubapp.uif2app.actions.BodyCopyLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyCopyLineAction();
  context.put("nc.ui.pubapp.uif2app.actions.BodyCopyLineAction#2af44edd",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.actions.BodyPasteLineAction getBodyPasteLineAction_d65a533(){
 if(context.get("nc.ui.pubapp.uif2app.actions.BodyPasteLineAction#d65a533")!=null)
 return (nc.ui.pubapp.uif2app.actions.BodyPasteLineAction)context.get("nc.ui.pubapp.uif2app.actions.BodyPasteLineAction#d65a533");
  nc.ui.pubapp.uif2app.actions.BodyPasteLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyPasteLineAction();
  context.put("nc.ui.pubapp.uif2app.actions.BodyPasteLineAction#d65a533",bean);
  bean.setClearItems(getManagedList2());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList2(){  List list = new ArrayList();  list.add("pk_commission_r");  list.add("rowno");  return list;}

private nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction getBodyPasteToTailAction_c40be24(){
 if(context.get("nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction#c40be24")!=null)
 return (nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction)context.get("nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction#c40be24");
  nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction bean = new nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction();
  context.put("nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction#c40be24",bean);
  bean.setClearItems(getManagedList3());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList3(){  List list = new ArrayList();  list.add("pk_commission_r");  list.add("rowno");  return list;}

private nc.ui.pubapp.uif2app.actions.BodyLineEditAction getBodyLineEditAction_6e8333c4(){
 if(context.get("nc.ui.pubapp.uif2app.actions.BodyLineEditAction#6e8333c4")!=null)
 return (nc.ui.pubapp.uif2app.actions.BodyLineEditAction)context.get("nc.ui.pubapp.uif2app.actions.BodyLineEditAction#6e8333c4");
  nc.ui.pubapp.uif2app.actions.BodyLineEditAction bean = new nc.ui.pubapp.uif2app.actions.BodyLineEditAction();
  context.put("nc.ui.pubapp.uif2app.actions.BodyLineEditAction#6e8333c4",bean);
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

private List getManagedList4(){  List list = new ArrayList();  list.add(getEventHandlerGroup_7b6d1965());  list.add(getEventHandlerGroup_420007e());  return list;}

private nc.ui.pubapp.uif2app.event.EventHandlerGroup getEventHandlerGroup_7b6d1965(){
 if(context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#7b6d1965")!=null)
 return (nc.ui.pubapp.uif2app.event.EventHandlerGroup)context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#7b6d1965");
  nc.ui.pubapp.uif2app.event.EventHandlerGroup bean = new nc.ui.pubapp.uif2app.event.EventHandlerGroup();
  context.put("nc.ui.pubapp.uif2app.event.EventHandlerGroup#7b6d1965",bean);
  bean.setEvent("nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent");
  bean.setHandler(getGrandBodyBeforeEditHandler_460909f6());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.qcco.commission.ace.handler.GrandBodyBeforeEditHandler getGrandBodyBeforeEditHandler_460909f6(){
 if(context.get("nc.ui.qcco.commission.ace.handler.GrandBodyBeforeEditHandler#460909f6")!=null)
 return (nc.ui.qcco.commission.ace.handler.GrandBodyBeforeEditHandler)context.get("nc.ui.qcco.commission.ace.handler.GrandBodyBeforeEditHandler#460909f6");
  nc.ui.qcco.commission.ace.handler.GrandBodyBeforeEditHandler bean = new nc.ui.qcco.commission.ace.handler.GrandBodyBeforeEditHandler(getBillForm());  context.put("nc.ui.qcco.commission.ace.handler.GrandBodyBeforeEditHandler#460909f6",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.event.EventHandlerGroup getEventHandlerGroup_420007e(){
 if(context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#420007e")!=null)
 return (nc.ui.pubapp.uif2app.event.EventHandlerGroup)context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#420007e");
  nc.ui.pubapp.uif2app.event.EventHandlerGroup bean = new nc.ui.pubapp.uif2app.event.EventHandlerGroup();
  context.put("nc.ui.pubapp.uif2app.event.EventHandlerGroup#420007e",bean);
  bean.setEvent("nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent");
  bean.setHandler(getGrandBodyAfterEditHandler_379e7531());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.qcco.commission.ace.handler.GrandBodyAfterEditHandler getGrandBodyAfterEditHandler_379e7531(){
 if(context.get("nc.ui.qcco.commission.ace.handler.GrandBodyAfterEditHandler#379e7531")!=null)
 return (nc.ui.qcco.commission.ace.handler.GrandBodyAfterEditHandler)context.get("nc.ui.qcco.commission.ace.handler.GrandBodyAfterEditHandler#379e7531");
  nc.ui.qcco.commission.ace.handler.GrandBodyAfterEditHandler bean = new nc.ui.qcco.commission.ace.handler.GrandBodyAfterEditHandler();
  context.put("nc.ui.qcco.commission.ace.handler.GrandBodyAfterEditHandler#379e7531",bean);
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
  bean.setUserdefitemListPreparator(getCompositeBillListDataPrepare_5100524f());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare getCompositeBillListDataPrepare_5100524f(){
 if(context.get("nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare#5100524f")!=null)
 return (nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare)context.get("nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare#5100524f");
  nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare bean = new nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare();
  context.put("nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare#5100524f",bean);
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

private List getManagedList7(){  List list = new ArrayList();  list.add(getUserdefQueryParam_6c1bea14());  list.add(getUserdefQueryParam_1ca4fe7f());  return list;}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_6c1bea14(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#6c1bea14")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#6c1bea14");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#6c1bea14",bean);
  bean.setMdfullname("qcco.commission");
  bean.setPos(0);
  bean.setPrefix("vdef");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_1ca4fe7f(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#1ca4fe7f")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#1ca4fe7f");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#1ca4fe7f",bean);
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
  bean.setUserdefitemPreparator(getCompositeBillDataPrepare_1db4156b());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList8(){  List list = new ArrayList();  list.add(getBodyAddLineAction_1440528a());  list.add(getBodyInsertLineAction_aeaec69());  list.add(getBodyDelLineAction_4546e718());  list.add(getBodyCopyLineAction_31edfc30());  list.add(getBodyPasteLineAction_7f47cceb());  list.add(getBodyPasteToTailAction_42de9995());  list.add(getBodyLineEditAction_2435277f());  return list;}

private nc.ui.pubapp.uif2app.actions.BodyAddLineAction getBodyAddLineAction_1440528a(){
 if(context.get("nc.ui.pubapp.uif2app.actions.BodyAddLineAction#1440528a")!=null)
 return (nc.ui.pubapp.uif2app.actions.BodyAddLineAction)context.get("nc.ui.pubapp.uif2app.actions.BodyAddLineAction#1440528a");
  nc.ui.pubapp.uif2app.actions.BodyAddLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyAddLineAction();
  context.put("nc.ui.pubapp.uif2app.actions.BodyAddLineAction#1440528a",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.actions.BodyInsertLineAction getBodyInsertLineAction_aeaec69(){
 if(context.get("nc.ui.pubapp.uif2app.actions.BodyInsertLineAction#aeaec69")!=null)
 return (nc.ui.pubapp.uif2app.actions.BodyInsertLineAction)context.get("nc.ui.pubapp.uif2app.actions.BodyInsertLineAction#aeaec69");
  nc.ui.pubapp.uif2app.actions.BodyInsertLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyInsertLineAction();
  context.put("nc.ui.pubapp.uif2app.actions.BodyInsertLineAction#aeaec69",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.actions.BodyDelLineAction getBodyDelLineAction_4546e718(){
 if(context.get("nc.ui.pubapp.uif2app.actions.BodyDelLineAction#4546e718")!=null)
 return (nc.ui.pubapp.uif2app.actions.BodyDelLineAction)context.get("nc.ui.pubapp.uif2app.actions.BodyDelLineAction#4546e718");
  nc.ui.pubapp.uif2app.actions.BodyDelLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyDelLineAction();
  context.put("nc.ui.pubapp.uif2app.actions.BodyDelLineAction#4546e718",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.actions.BodyCopyLineAction getBodyCopyLineAction_31edfc30(){
 if(context.get("nc.ui.pubapp.uif2app.actions.BodyCopyLineAction#31edfc30")!=null)
 return (nc.ui.pubapp.uif2app.actions.BodyCopyLineAction)context.get("nc.ui.pubapp.uif2app.actions.BodyCopyLineAction#31edfc30");
  nc.ui.pubapp.uif2app.actions.BodyCopyLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyCopyLineAction();
  context.put("nc.ui.pubapp.uif2app.actions.BodyCopyLineAction#31edfc30",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.actions.BodyPasteLineAction getBodyPasteLineAction_7f47cceb(){
 if(context.get("nc.ui.pubapp.uif2app.actions.BodyPasteLineAction#7f47cceb")!=null)
 return (nc.ui.pubapp.uif2app.actions.BodyPasteLineAction)context.get("nc.ui.pubapp.uif2app.actions.BodyPasteLineAction#7f47cceb");
  nc.ui.pubapp.uif2app.actions.BodyPasteLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyPasteLineAction();
  context.put("nc.ui.pubapp.uif2app.actions.BodyPasteLineAction#7f47cceb",bean);
  bean.setClearItems(getManagedList9());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList9(){  List list = new ArrayList();  list.add("pk_commission_b");  list.add("rowno");  return list;}

private nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction getBodyPasteToTailAction_42de9995(){
 if(context.get("nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction#42de9995")!=null)
 return (nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction)context.get("nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction#42de9995");
  nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction bean = new nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction();
  context.put("nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction#42de9995",bean);
  bean.setClearItems(getManagedList10());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList10(){  List list = new ArrayList();  list.add("pk_commission_b");  list.add("rowno");  return list;}

private nc.ui.pubapp.uif2app.actions.BodyLineEditAction getBodyLineEditAction_2435277f(){
 if(context.get("nc.ui.pubapp.uif2app.actions.BodyLineEditAction#2435277f")!=null)
 return (nc.ui.pubapp.uif2app.actions.BodyLineEditAction)context.get("nc.ui.pubapp.uif2app.actions.BodyLineEditAction#2435277f");
  nc.ui.pubapp.uif2app.actions.BodyLineEditAction bean = new nc.ui.pubapp.uif2app.actions.BodyLineEditAction();
  context.put("nc.ui.pubapp.uif2app.actions.BodyLineEditAction#2435277f",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare getCompositeBillDataPrepare_1db4156b(){
 if(context.get("nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare#1db4156b")!=null)
 return (nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare)context.get("nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare#1db4156b");
  nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare bean = new nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare();
  context.put("nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare#1db4156b",bean);
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

private List getManagedList12(){  List list = new ArrayList();  list.add(getUserdefQueryParam_19ea3309());  list.add(getUserdefQueryParam_5ebca129());  return list;}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_19ea3309(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#19ea3309")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#19ea3309");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#19ea3309",bean);
  bean.setMdfullname("qcco.commission");
  bean.setPos(0);
  bean.setPrefix("vdef");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_5ebca129(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#5ebca129")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#5ebca129");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#5ebca129",bean);
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

private List getManagedList13(){  List list = new ArrayList();  list.add(getQueryParam_31f8bb56());  list.add(getQueryParam_40d324bb());  list.add(getQueryParam_6610923c());  return list;}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_31f8bb56(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#31f8bb56")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#31f8bb56");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#31f8bb56",bean);
  bean.setMdfullname("qcco.commission");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_40d324bb(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#40d324bb")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#40d324bb");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#40d324bb",bean);
  bean.setMdfullname("qcco.CommissionBVO");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_6610923c(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#6610923c")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#6610923c");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#6610923c",bean);
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
  bean.setTangramLayoutRoot(getTBNode_47f420be());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.TBNode getTBNode_47f420be(){
 if(context.get("nc.ui.uif2.tangramlayout.node.TBNode#47f420be")!=null)
 return (nc.ui.uif2.tangramlayout.node.TBNode)context.get("nc.ui.uif2.tangramlayout.node.TBNode#47f420be");
  nc.ui.uif2.tangramlayout.node.TBNode bean = new nc.ui.uif2.tangramlayout.node.TBNode();
  context.put("nc.ui.uif2.tangramlayout.node.TBNode#47f420be",bean);
  bean.setShowMode("CardLayout");
  bean.setTabs(getManagedList14());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList14(){  List list = new ArrayList();  list.add(getVSNode_168af766());  list.add(getVSNode_47da397c());  return list;}

private nc.ui.uif2.tangramlayout.node.VSNode getVSNode_168af766(){
 if(context.get("nc.ui.uif2.tangramlayout.node.VSNode#168af766")!=null)
 return (nc.ui.uif2.tangramlayout.node.VSNode)context.get("nc.ui.uif2.tangramlayout.node.VSNode#168af766");
  nc.ui.uif2.tangramlayout.node.VSNode bean = new nc.ui.uif2.tangramlayout.node.VSNode();
  context.put("nc.ui.uif2.tangramlayout.node.VSNode#168af766",bean);
  bean.setShowMode("NoDivider");
  bean.setUp(getCNode_49f0d57d());
  bean.setDown(getHSNode_1711e007());
  bean.setDividerLocation(30f);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_49f0d57d(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#49f0d57d")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#49f0d57d");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#49f0d57d",bean);
  bean.setComponent(getOrgpanel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.HSNode getHSNode_1711e007(){
 if(context.get("nc.ui.uif2.tangramlayout.node.HSNode#1711e007")!=null)
 return (nc.ui.uif2.tangramlayout.node.HSNode)context.get("nc.ui.uif2.tangramlayout.node.HSNode#1711e007");
  nc.ui.uif2.tangramlayout.node.HSNode bean = new nc.ui.uif2.tangramlayout.node.HSNode();
  context.put("nc.ui.uif2.tangramlayout.node.HSNode#1711e007",bean);
  bean.setLeft(getCNode_62502777());
  bean.setRight(getVSNode_6d3a6e17());
  bean.setDividerLocation(210f);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_62502777(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#62502777")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#62502777");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#62502777",bean);
  bean.setComponent(getQueryArea());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.VSNode getVSNode_6d3a6e17(){
 if(context.get("nc.ui.uif2.tangramlayout.node.VSNode#6d3a6e17")!=null)
 return (nc.ui.uif2.tangramlayout.node.VSNode)context.get("nc.ui.uif2.tangramlayout.node.VSNode#6d3a6e17");
  nc.ui.uif2.tangramlayout.node.VSNode bean = new nc.ui.uif2.tangramlayout.node.VSNode();
  context.put("nc.ui.uif2.tangramlayout.node.VSNode#6d3a6e17",bean);
  bean.setUp(getCNode_7c4f280d());
  bean.setDown(getCNode_50cf98d3());
  bean.setDividerLocation(25f);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_7c4f280d(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#7c4f280d")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#7c4f280d");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#7c4f280d",bean);
  bean.setComponent(getQueryInfo());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_50cf98d3(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#50cf98d3")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#50cf98d3");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#50cf98d3",bean);
  bean.setName(getI18nFB_30cead3d());
  bean.setComponent(getMainGrandlistView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_30cead3d(){
 if(context.get("nc.ui.uif2.I18nFB#30cead3d")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#30cead3d");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#30cead3d",bean);  bean.setResDir("common");
  bean.setResId("UC001-0000107");
  bean.setDefaultValue("列表");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#30cead3d",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private nc.ui.uif2.tangramlayout.node.VSNode getVSNode_47da397c(){
 if(context.get("nc.ui.uif2.tangramlayout.node.VSNode#47da397c")!=null)
 return (nc.ui.uif2.tangramlayout.node.VSNode)context.get("nc.ui.uif2.tangramlayout.node.VSNode#47da397c");
  nc.ui.uif2.tangramlayout.node.VSNode bean = new nc.ui.uif2.tangramlayout.node.VSNode();
  context.put("nc.ui.uif2.tangramlayout.node.VSNode#47da397c",bean);
  bean.setUp(getCNode_7d785519());
  bean.setDown(getCNode_24f6d021());
  bean.setDividerLocation(30f);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_7d785519(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#7d785519")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#7d785519");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#7d785519",bean);
  bean.setComponent(getViewb());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_24f6d021(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#24f6d021")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#24f6d021");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#24f6d021",bean);
  bean.setName(getI18nFB_671b1ca9());
  bean.setComponent(getMainGrandbillForm());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_671b1ca9(){
 if(context.get("nc.ui.uif2.I18nFB#671b1ca9")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#671b1ca9");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#671b1ca9",bean);  bean.setResDir("common");
  bean.setResId("UC001-0000106");
  bean.setDefaultValue("卡片");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#671b1ca9",product);
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

private List getManagedList20(){  List list = new ArrayList();  list.add(getTemplateNotNullValidation_6c91bed9());  return list;}

private nc.ui.pubapp.uif2app.validation.TemplateNotNullValidation getTemplateNotNullValidation_6c91bed9(){
 if(context.get("nc.ui.pubapp.uif2app.validation.TemplateNotNullValidation#6c91bed9")!=null)
 return (nc.ui.pubapp.uif2app.validation.TemplateNotNullValidation)context.get("nc.ui.pubapp.uif2app.validation.TemplateNotNullValidation#6c91bed9");
  nc.ui.pubapp.uif2app.validation.TemplateNotNullValidation bean = new nc.ui.pubapp.uif2app.validation.TemplateNotNullValidation();
  context.put("nc.ui.pubapp.uif2app.validation.TemplateNotNullValidation#6c91bed9",bean);
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
  bean.setName(getI18nFB_5488d03e());
  bean.setActions(getManagedList21());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_5488d03e(){
 if(context.get("nc.ui.uif2.I18nFB#5488d03e")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#5488d03e");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#5488d03e",bean);  bean.setResDir("common");
  bean.setResId("UC001-0000007");
  bean.setDefaultValue("打印");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#5488d03e",product);
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

private List getManagedList22(){  List list = new ArrayList();  list.add(getEventHandlerGroup_12ecac0f());  list.add(getEventHandlerGroup_36d0a207());  list.add(getEventHandlerGroup_482df0e4());  list.add(getEventHandlerGroup_3e9facc());  list.add(getEventHandlerGroup_19231e62());  list.add(getEventHandlerGroup_282c4458());  return list;}

private nc.ui.pubapp.uif2app.event.EventHandlerGroup getEventHandlerGroup_12ecac0f(){
 if(context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#12ecac0f")!=null)
 return (nc.ui.pubapp.uif2app.event.EventHandlerGroup)context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#12ecac0f");
  nc.ui.pubapp.uif2app.event.EventHandlerGroup bean = new nc.ui.pubapp.uif2app.event.EventHandlerGroup();
  context.put("nc.ui.pubapp.uif2app.event.EventHandlerGroup#12ecac0f",bean);
  bean.setEvent("nc.ui.pubapp.uif2app.event.OrgChangedEvent");
  bean.setHandler(getAceOrgChangeHandler_621fc1cd());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.qcco.commission.ace.handler.AceOrgChangeHandler getAceOrgChangeHandler_621fc1cd(){
 if(context.get("nc.ui.qcco.commission.ace.handler.AceOrgChangeHandler#621fc1cd")!=null)
 return (nc.ui.qcco.commission.ace.handler.AceOrgChangeHandler)context.get("nc.ui.qcco.commission.ace.handler.AceOrgChangeHandler#621fc1cd");
  nc.ui.qcco.commission.ace.handler.AceOrgChangeHandler bean = new nc.ui.qcco.commission.ace.handler.AceOrgChangeHandler();
  context.put("nc.ui.qcco.commission.ace.handler.AceOrgChangeHandler#621fc1cd",bean);
  bean.setBillForm(getBillForm());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.event.EventHandlerGroup getEventHandlerGroup_36d0a207(){
 if(context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#36d0a207")!=null)
 return (nc.ui.pubapp.uif2app.event.EventHandlerGroup)context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#36d0a207");
  nc.ui.pubapp.uif2app.event.EventHandlerGroup bean = new nc.ui.pubapp.uif2app.event.EventHandlerGroup();
  context.put("nc.ui.pubapp.uif2app.event.EventHandlerGroup#36d0a207",bean);
  bean.setEvent("nc.ui.pubapp.uif2app.event.billform.AddEvent");
  bean.setHandler(getAceAddHandler_7014167d());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.qcco.commission.ace.handler.AceAddHandler getAceAddHandler_7014167d(){
 if(context.get("nc.ui.qcco.commission.ace.handler.AceAddHandler#7014167d")!=null)
 return (nc.ui.qcco.commission.ace.handler.AceAddHandler)context.get("nc.ui.qcco.commission.ace.handler.AceAddHandler#7014167d");
  nc.ui.qcco.commission.ace.handler.AceAddHandler bean = new nc.ui.qcco.commission.ace.handler.AceAddHandler();
  context.put("nc.ui.qcco.commission.ace.handler.AceAddHandler#7014167d",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.event.EventHandlerGroup getEventHandlerGroup_482df0e4(){
 if(context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#482df0e4")!=null)
 return (nc.ui.pubapp.uif2app.event.EventHandlerGroup)context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#482df0e4");
  nc.ui.pubapp.uif2app.event.EventHandlerGroup bean = new nc.ui.pubapp.uif2app.event.EventHandlerGroup();
  context.put("nc.ui.pubapp.uif2app.event.EventHandlerGroup#482df0e4",bean);
  bean.setEvent("nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent");
  bean.setHandler(getAceHeadTailBeforeEditHandler_3b3d95ae());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.qcco.commission.ace.handler.AceHeadTailBeforeEditHandler getAceHeadTailBeforeEditHandler_3b3d95ae(){
 if(context.get("nc.ui.qcco.commission.ace.handler.AceHeadTailBeforeEditHandler#3b3d95ae")!=null)
 return (nc.ui.qcco.commission.ace.handler.AceHeadTailBeforeEditHandler)context.get("nc.ui.qcco.commission.ace.handler.AceHeadTailBeforeEditHandler#3b3d95ae");
  nc.ui.qcco.commission.ace.handler.AceHeadTailBeforeEditHandler bean = new nc.ui.qcco.commission.ace.handler.AceHeadTailBeforeEditHandler();
  context.put("nc.ui.qcco.commission.ace.handler.AceHeadTailBeforeEditHandler#3b3d95ae",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.event.EventHandlerGroup getEventHandlerGroup_3e9facc(){
 if(context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#3e9facc")!=null)
 return (nc.ui.pubapp.uif2app.event.EventHandlerGroup)context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#3e9facc");
  nc.ui.pubapp.uif2app.event.EventHandlerGroup bean = new nc.ui.pubapp.uif2app.event.EventHandlerGroup();
  context.put("nc.ui.pubapp.uif2app.event.EventHandlerGroup#3e9facc",bean);
  bean.setEvent("nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent");
  bean.setHandler(getAceHeadTailAfterEditHandler_40222dc1());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.qcco.commission.ace.handler.AceHeadTailAfterEditHandler getAceHeadTailAfterEditHandler_40222dc1(){
 if(context.get("nc.ui.qcco.commission.ace.handler.AceHeadTailAfterEditHandler#40222dc1")!=null)
 return (nc.ui.qcco.commission.ace.handler.AceHeadTailAfterEditHandler)context.get("nc.ui.qcco.commission.ace.handler.AceHeadTailAfterEditHandler#40222dc1");
  nc.ui.qcco.commission.ace.handler.AceHeadTailAfterEditHandler bean = new nc.ui.qcco.commission.ace.handler.AceHeadTailAfterEditHandler();
  context.put("nc.ui.qcco.commission.ace.handler.AceHeadTailAfterEditHandler#40222dc1",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.event.EventHandlerGroup getEventHandlerGroup_19231e62(){
 if(context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#19231e62")!=null)
 return (nc.ui.pubapp.uif2app.event.EventHandlerGroup)context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#19231e62");
  nc.ui.pubapp.uif2app.event.EventHandlerGroup bean = new nc.ui.pubapp.uif2app.event.EventHandlerGroup();
  context.put("nc.ui.pubapp.uif2app.event.EventHandlerGroup#19231e62",bean);
  bean.setEvent("nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent");
  bean.setHandler(getAceBodyBeforeEditHandler_76506915());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.qcco.commission.ace.handler.AceBodyBeforeEditHandler getAceBodyBeforeEditHandler_76506915(){
 if(context.get("nc.ui.qcco.commission.ace.handler.AceBodyBeforeEditHandler#76506915")!=null)
 return (nc.ui.qcco.commission.ace.handler.AceBodyBeforeEditHandler)context.get("nc.ui.qcco.commission.ace.handler.AceBodyBeforeEditHandler#76506915");
  nc.ui.qcco.commission.ace.handler.AceBodyBeforeEditHandler bean = new nc.ui.qcco.commission.ace.handler.AceBodyBeforeEditHandler();
  context.put("nc.ui.qcco.commission.ace.handler.AceBodyBeforeEditHandler#76506915",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.event.EventHandlerGroup getEventHandlerGroup_282c4458(){
 if(context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#282c4458")!=null)
 return (nc.ui.pubapp.uif2app.event.EventHandlerGroup)context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#282c4458");
  nc.ui.pubapp.uif2app.event.EventHandlerGroup bean = new nc.ui.pubapp.uif2app.event.EventHandlerGroup();
  context.put("nc.ui.pubapp.uif2app.event.EventHandlerGroup#282c4458",bean);
  bean.setEvent("nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent");
  bean.setHandler(getAceBodyAfterEditHandler_509fc0d2());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.qcco.commission.ace.handler.AceBodyAfterEditHandler getAceBodyAfterEditHandler_509fc0d2(){
 if(context.get("nc.ui.qcco.commission.ace.handler.AceBodyAfterEditHandler#509fc0d2")!=null)
 return (nc.ui.qcco.commission.ace.handler.AceBodyAfterEditHandler)context.get("nc.ui.qcco.commission.ace.handler.AceBodyAfterEditHandler#509fc0d2");
  nc.ui.qcco.commission.ace.handler.AceBodyAfterEditHandler bean = new nc.ui.qcco.commission.ace.handler.AceBodyAfterEditHandler();
  context.put("nc.ui.qcco.commission.ace.handler.AceBodyAfterEditHandler#509fc0d2",bean);
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

private List getManagedList23(){  List list = new ArrayList();  list.add(getCardPanelLazilyLoad_723b0202());  list.add(getListPanelLazilyLoad_10be5());  return list;}

private nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad getCardPanelLazilyLoad_723b0202(){
 if(context.get("nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad#723b0202")!=null)
 return (nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad)context.get("nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad#723b0202");
  nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad bean = new nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad();
  context.put("nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad#723b0202",bean);
  bean.setBillform(getBillForm());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad getListPanelLazilyLoad_10be5(){
 if(context.get("nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad#10be5")!=null)
 return (nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad)context.get("nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad#10be5");
  nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad bean = new nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad();
  context.put("nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad#10be5",bean);
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
  bean.setShowUpComponent(getBillForm());
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
