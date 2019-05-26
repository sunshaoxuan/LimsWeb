package nc.ui.qcco.task.ace.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class Task_config extends AbstractJavaBeanDefinition{
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

public nc.ui.qcco.task.ace.serviceproxy.AceTaskDeleteProxy getDeleteProxy(){
 if(context.get("deleteProxy")!=null)
 return (nc.ui.qcco.task.ace.serviceproxy.AceTaskDeleteProxy)context.get("deleteProxy");
  nc.ui.qcco.task.ace.serviceproxy.AceTaskDeleteProxy bean = new nc.ui.qcco.task.ace.serviceproxy.AceTaskDeleteProxy();
  context.put("deleteProxy",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.qcco.task.ace.serviceproxy.AceTaskMaintainProxy getBmModelModelService(){
 if(context.get("bmModelModelService")!=null)
 return (nc.ui.qcco.task.ace.serviceproxy.AceTaskMaintainProxy)context.get("bmModelModelService");
  nc.ui.qcco.task.ace.serviceproxy.AceTaskMaintainProxy bean = new nc.ui.qcco.task.ace.serviceproxy.AceTaskMaintainProxy();
  context.put("bmModelModelService",bean);
  bean.setGrandTabAndVOMap(getManagedMap0());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private Map getManagedMap0(){  Map map = new HashMap();  map.put("pk_task_r",getTaskRVO());  map.put("pk_task_s",getTaskSVO());  return map;}

public nc.vo.bd.meta.GeneralBDObjectAdapterFactory getBOAdapterFactory(){
 if(context.get("BOAdapterFactory")!=null)
 return (nc.vo.bd.meta.GeneralBDObjectAdapterFactory)context.get("BOAdapterFactory");
  nc.vo.bd.meta.GeneralBDObjectAdapterFactory bean = new nc.vo.bd.meta.GeneralBDObjectAdapterFactory();
  context.put("BOAdapterFactory",bean);
  bean.setMode("MD");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.vo.bd.meta.BDObjectAdpaterFactory getBoadatorfactory2(){
 if(context.get("boadatorfactory2")!=null)
 return (nc.vo.bd.meta.BDObjectAdpaterFactory)context.get("boadatorfactory2");
  nc.vo.bd.meta.BDObjectAdpaterFactory bean = new nc.vo.bd.meta.BDObjectAdpaterFactory();
  context.put("boadatorfactory2",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.model.BillManageModel getManageAppModel(){
 if(context.get("manageAppModel")!=null)
 return (nc.ui.pubapp.uif2app.model.BillManageModel)context.get("manageAppModel");
  nc.ui.pubapp.uif2app.model.BillManageModel bean = new nc.ui.pubapp.uif2app.model.BillManageModel();
  context.put("manageAppModel",bean);
  bean.setContext(getContext());
  bean.setBusinessObjectAdapterFactory(getBOAdapterFactory());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.qcco.task.model.SubGrandBillModel getManageAppModel2(){
 if(context.get("manageAppModel2")!=null)
 return (nc.ui.qcco.task.model.SubGrandBillModel)context.get("manageAppModel2");
  nc.ui.qcco.task.model.SubGrandBillModel bean = new nc.ui.qcco.task.model.SubGrandBillModel();
  context.put("manageAppModel2",bean);
  bean.setBusinessObjectAdapterFactory(getBoadatorfactory2());
  bean.setContext(getContext());
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
  bean.setMainModel(getManageAppModel());
  bean.setGrandModel(getManageAppModel2());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.query2.model.ModelDataManager getBmModelModelDataManager(){
 if(context.get("bmModelModelDataManager")!=null)
 return (nc.ui.pubapp.uif2app.query2.model.ModelDataManager)context.get("bmModelModelDataManager");
  nc.ui.pubapp.uif2app.query2.model.ModelDataManager bean = new nc.ui.pubapp.uif2app.query2.model.ModelDataManager();
  context.put("bmModelModelDataManager",bean);
  bean.setModel(getManageAppModel());
  bean.setService(getBmModelModelService());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.components.grand.MainGrandRelationShip getMainGrandRelationShip(){
 if(context.get("mainGrandRelationShip")!=null)
 return (nc.ui.pubapp.uif2app.components.grand.MainGrandRelationShip)context.get("mainGrandRelationShip");
  nc.ui.pubapp.uif2app.components.grand.MainGrandRelationShip bean = new nc.ui.pubapp.uif2app.components.grand.MainGrandRelationShip();
  context.put("mainGrandRelationShip",bean);
  bean.setBodyTabTOGrandListComposite(getManagedMap1());
  bean.setBodyTabTOGrandCardComposite(getManagedMap2());
  bean.setGrandTabAndVOMap(getManagedMap3());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private Map getManagedMap1(){  Map map = new HashMap();  map.put("pk_task_b",getSunlistView1());  return map;}

private Map getManagedMap2(){  Map map = new HashMap();  map.put("pk_task_b",getSunbillFormEditor1());  return map;}

private Map getManagedMap3(){  Map map = new HashMap();  map.put("pk_task_r",getTaskRVO());  map.put("pk_task_s",getTaskSVO());  return map;}

public nc.vo.qcco.task.TaskRVO getTaskRVO(){
 if(context.get("TaskRVO")!=null)
 return (nc.vo.qcco.task.TaskRVO)context.get("TaskRVO");
  nc.vo.qcco.task.TaskRVO bean = new nc.vo.qcco.task.TaskRVO();
  context.put("TaskRVO",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.vo.qcco.task.TaskSVO getTaskSVO(){
 if(context.get("TaskSVO")!=null)
 return (nc.vo.qcco.task.TaskSVO)context.get("TaskSVO");
  nc.vo.qcco.task.TaskSVO bean = new nc.vo.qcco.task.TaskSVO();
  context.put("TaskSVO",bean);
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
  bean.setMainPanel(getListView());
  bean.setExpendShrinkGrandListAction(getExpendShrinkGrandListAction());
  bean.setGrandString("孙表信息");
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.components.grand.CardGrandPanelComposite getMainGrandbillFormEditor(){
 if(context.get("MainGrandbillFormEditor")!=null)
 return (nc.ui.pubapp.uif2app.components.grand.CardGrandPanelComposite)context.get("MainGrandbillFormEditor");
  nc.ui.pubapp.uif2app.components.grand.CardGrandPanelComposite bean = new nc.ui.pubapp.uif2app.components.grand.CardGrandPanelComposite();
  context.put("MainGrandbillFormEditor",bean);
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

public nc.ui.pubapp.uif2app.components.grand.MainGrandBlankFilter getMainGrandBlankFilter(){
 if(context.get("mainGrandBlankFilter")!=null)
 return (nc.ui.pubapp.uif2app.components.grand.MainGrandBlankFilter)context.get("mainGrandBlankFilter");
  nc.ui.pubapp.uif2app.components.grand.MainGrandBlankFilter bean = new nc.ui.pubapp.uif2app.components.grand.MainGrandBlankFilter();
  context.put("mainGrandBlankFilter",bean);
  bean.setChildFilterMap(getManagedMap4());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private Map getManagedMap4(){  Map map = new HashMap();  map.put("pk_task_b",getManagedList0());  return map;}

private List getManagedList0(){  List list = new ArrayList();  list.add("rowno");  return list;}

public nc.ui.pubapp.uif2app.components.grand.mediator.MainGrandMediator getMainGrandMediator(){
 if(context.get("mainGrandMediator")!=null)
 return (nc.ui.pubapp.uif2app.components.grand.mediator.MainGrandMediator)context.get("mainGrandMediator");
  nc.ui.pubapp.uif2app.components.grand.mediator.MainGrandMediator bean = new nc.ui.pubapp.uif2app.components.grand.mediator.MainGrandMediator();
  context.put("mainGrandMediator",bean);
  bean.setMainBillForm(getBillFormEditor());
  bean.setMainBillListView(getListView());
  bean.setMainGrandModel(getMainGrandModel());
  bean.setMainGrandRelationShip(getMainGrandRelationShip());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.view.ShowUpableBillListView getSunlistView1(){
 if(context.get("sunlistView1")!=null)
 return (nc.ui.pubapp.uif2app.view.ShowUpableBillListView)context.get("sunlistView1");
  nc.ui.pubapp.uif2app.view.ShowUpableBillListView bean = new nc.ui.pubapp.uif2app.view.ShowUpableBillListView();
  context.put("sunlistView1",bean);
  bean.setModel(getManageAppModel2());
  bean.setTemplateContainer(getTemplateContainer());
  bean.setNodekey("sunparas1");
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

public nc.ui.pubapp.uif2app.view.ShowUpableBillForm getSunbillFormEditor1(){
 if(context.get("sunbillFormEditor1")!=null)
 return (nc.ui.pubapp.uif2app.view.ShowUpableBillForm)context.get("sunbillFormEditor1");
  nc.ui.pubapp.uif2app.view.ShowUpableBillForm bean = new nc.ui.pubapp.uif2app.view.ShowUpableBillForm();
  context.put("sunbillFormEditor1",bean);
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

private List getManagedList1(){  List list = new ArrayList();  list.add(getBodyDelLineAction_14aa6685());  list.add(getBodyCopyLineAction_3f30b05c());  list.add(getBodyPasteLineAction_158a886d());  list.add(getBodyPasteToTailAction_3a02b727());  list.add(getBodyLineEditAction_8467f32());  return list;}

private nc.ui.pubapp.uif2app.actions.BodyDelLineAction getBodyDelLineAction_14aa6685(){
 if(context.get("nc.ui.pubapp.uif2app.actions.BodyDelLineAction#14aa6685")!=null)
 return (nc.ui.pubapp.uif2app.actions.BodyDelLineAction)context.get("nc.ui.pubapp.uif2app.actions.BodyDelLineAction#14aa6685");
  nc.ui.pubapp.uif2app.actions.BodyDelLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyDelLineAction();
  context.put("nc.ui.pubapp.uif2app.actions.BodyDelLineAction#14aa6685",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.actions.BodyCopyLineAction getBodyCopyLineAction_3f30b05c(){
 if(context.get("nc.ui.pubapp.uif2app.actions.BodyCopyLineAction#3f30b05c")!=null)
 return (nc.ui.pubapp.uif2app.actions.BodyCopyLineAction)context.get("nc.ui.pubapp.uif2app.actions.BodyCopyLineAction#3f30b05c");
  nc.ui.pubapp.uif2app.actions.BodyCopyLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyCopyLineAction();
  context.put("nc.ui.pubapp.uif2app.actions.BodyCopyLineAction#3f30b05c",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.actions.BodyPasteLineAction getBodyPasteLineAction_158a886d(){
 if(context.get("nc.ui.pubapp.uif2app.actions.BodyPasteLineAction#158a886d")!=null)
 return (nc.ui.pubapp.uif2app.actions.BodyPasteLineAction)context.get("nc.ui.pubapp.uif2app.actions.BodyPasteLineAction#158a886d");
  nc.ui.pubapp.uif2app.actions.BodyPasteLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyPasteLineAction();
  context.put("nc.ui.pubapp.uif2app.actions.BodyPasteLineAction#158a886d",bean);
  bean.setClearItems(getManagedList2());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList2(){  List list = new ArrayList();  list.add("pk_task_r");  list.add("dr");  return list;}

private nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction getBodyPasteToTailAction_3a02b727(){
 if(context.get("nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction#3a02b727")!=null)
 return (nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction)context.get("nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction#3a02b727");
  nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction bean = new nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction();
  context.put("nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction#3a02b727",bean);
  bean.setClearItems(getManagedList3());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList3(){  List list = new ArrayList();  list.add("pk_task_r");  list.add("dr");  return list;}

private nc.ui.pubapp.uif2app.actions.BodyLineEditAction getBodyLineEditAction_8467f32(){
 if(context.get("nc.ui.pubapp.uif2app.actions.BodyLineEditAction#8467f32")!=null)
 return (nc.ui.pubapp.uif2app.actions.BodyLineEditAction)context.get("nc.ui.pubapp.uif2app.actions.BodyLineEditAction#8467f32");
  nc.ui.pubapp.uif2app.actions.BodyLineEditAction bean = new nc.ui.pubapp.uif2app.actions.BodyLineEditAction();
  context.put("nc.ui.pubapp.uif2app.actions.BodyLineEditAction#8467f32",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.query2.model.ModelDataManager getModelDataManager(){
 if(context.get("modelDataManager")!=null)
 return (nc.ui.pubapp.uif2app.query2.model.ModelDataManager)context.get("modelDataManager");
  nc.ui.pubapp.uif2app.query2.model.ModelDataManager bean = new nc.ui.pubapp.uif2app.query2.model.ModelDataManager();
  context.put("modelDataManager",bean);
  bean.setModel(getManageAppModel());
  bean.setService(getBmModelModelService());
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
  bean.setNodeKeies(getManagedList4());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList4(){  List list = new ArrayList();  list.add("bt");  list.add("sunparas");  return list;}

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

public nc.ui.pubapp.uif2app.view.ShowUpableBillListView getListView(){
 if(context.get("listView")!=null)
 return (nc.ui.pubapp.uif2app.view.ShowUpableBillListView)context.get("listView");
  nc.ui.pubapp.uif2app.view.ShowUpableBillListView bean = new nc.ui.pubapp.uif2app.view.ShowUpableBillListView();
  context.put("listView",bean);
  bean.setModel(getManageAppModel());
  bean.setTemplateContainer(getTemplateContainer());
  bean.setNodekey("bt");
  bean.setUserdefitemListPreparator(getCompositeBillListDataPrepare_4c48afdc());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare getCompositeBillListDataPrepare_4c48afdc(){
 if(context.get("nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare#4c48afdc")!=null)
 return (nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare)context.get("nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare#4c48afdc");
  nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare bean = new nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare();
  context.put("nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare#4c48afdc",bean);
  bean.setBillListDataPrepares(getManagedList5());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList5(){  List list = new ArrayList();  list.add(getUserdefitemlistPreparator());  list.add(getMarAsstPreparator());  return list;}

public nc.ui.uif2.editor.UserdefitemContainerListPreparator getUserdefitemlistPreparator(){
 if(context.get("userdefitemlistPreparator")!=null)
 return (nc.ui.uif2.editor.UserdefitemContainerListPreparator)context.get("userdefitemlistPreparator");
  nc.ui.uif2.editor.UserdefitemContainerListPreparator bean = new nc.ui.uif2.editor.UserdefitemContainerListPreparator();
  context.put("userdefitemlistPreparator",bean);
  bean.setContainer(getUserdefitemContainer());
  bean.setParams(getManagedList6());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList6(){  List list = new ArrayList();  list.add(getUserdefQueryParam_3811bba4());  list.add(getUserdefQueryParam_143628be());  return list;}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_3811bba4(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#3811bba4")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#3811bba4");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#3811bba4",bean);
  bean.setMdfullname("qcco.task");
  bean.setPos(0);
  bean.setPrefix("vdef");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_143628be(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#143628be")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#143628be");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#143628be",bean);
  bean.setMdfullname("qcco.TaskBVO");
  bean.setPos(1);
  bean.setPrefix("vbdef");
  bean.setTabcode("TaskBVO");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.view.ShowUpableBillForm getBillFormEditor(){
 if(context.get("billFormEditor")!=null)
 return (nc.ui.pubapp.uif2app.view.ShowUpableBillForm)context.get("billFormEditor");
  nc.ui.pubapp.uif2app.view.ShowUpableBillForm bean = new nc.ui.pubapp.uif2app.view.ShowUpableBillForm();
  context.put("billFormEditor",bean);
  bean.setModel(getManageAppModel());
  bean.setTemplateContainer(getTemplateContainer());
  bean.setNodekey("bt");
  bean.setShowOrgPanel(true);
  bean.setAutoAddLine(false);
  bean.setBodyLineActions(getManagedList7());
  bean.setUserdefitemPreparator(getCompositeBillDataPrepare_48d9a85c());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList7(){  List list = new ArrayList();  list.add(getTaskBodyAddLineAction_6facdc4f());  list.add(getBodyInsertLineAction_3db3ffd0());  list.add(getTaskBodyDelLineAction_745fd055());  list.add(getBodyCopyLineAction_3e66b823());  list.add(getBodyPasteLineAction_49c321d6());  list.add(getBodyPasteToTailAction_29e28e6f());  list.add(getBodyLineEditAction_58f52ee4());  return list;}

private nc.ui.qcco.task.action.TaskBodyAddLineAction getTaskBodyAddLineAction_6facdc4f(){
 if(context.get("nc.ui.qcco.task.action.TaskBodyAddLineAction#6facdc4f")!=null)
 return (nc.ui.qcco.task.action.TaskBodyAddLineAction)context.get("nc.ui.qcco.task.action.TaskBodyAddLineAction#6facdc4f");
  nc.ui.qcco.task.action.TaskBodyAddLineAction bean = new nc.ui.qcco.task.action.TaskBodyAddLineAction();
  context.put("nc.ui.qcco.task.action.TaskBodyAddLineAction#6facdc4f",bean);
  bean.setMainBillForm(getBillFormEditor());
  bean.setGrandCard(getSunbillFormEditor1());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.actions.BodyInsertLineAction getBodyInsertLineAction_3db3ffd0(){
 if(context.get("nc.ui.pubapp.uif2app.actions.BodyInsertLineAction#3db3ffd0")!=null)
 return (nc.ui.pubapp.uif2app.actions.BodyInsertLineAction)context.get("nc.ui.pubapp.uif2app.actions.BodyInsertLineAction#3db3ffd0");
  nc.ui.pubapp.uif2app.actions.BodyInsertLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyInsertLineAction();
  context.put("nc.ui.pubapp.uif2app.actions.BodyInsertLineAction#3db3ffd0",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.qcco.task.action.TaskBodyDelLineAction getTaskBodyDelLineAction_745fd055(){
 if(context.get("nc.ui.qcco.task.action.TaskBodyDelLineAction#745fd055")!=null)
 return (nc.ui.qcco.task.action.TaskBodyDelLineAction)context.get("nc.ui.qcco.task.action.TaskBodyDelLineAction#745fd055");
  nc.ui.qcco.task.action.TaskBodyDelLineAction bean = new nc.ui.qcco.task.action.TaskBodyDelLineAction(getBillFormEditor(),getSunbillFormEditor1());  context.put("nc.ui.qcco.task.action.TaskBodyDelLineAction#745fd055",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.actions.BodyCopyLineAction getBodyCopyLineAction_3e66b823(){
 if(context.get("nc.ui.pubapp.uif2app.actions.BodyCopyLineAction#3e66b823")!=null)
 return (nc.ui.pubapp.uif2app.actions.BodyCopyLineAction)context.get("nc.ui.pubapp.uif2app.actions.BodyCopyLineAction#3e66b823");
  nc.ui.pubapp.uif2app.actions.BodyCopyLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyCopyLineAction();
  context.put("nc.ui.pubapp.uif2app.actions.BodyCopyLineAction#3e66b823",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.actions.BodyPasteLineAction getBodyPasteLineAction_49c321d6(){
 if(context.get("nc.ui.pubapp.uif2app.actions.BodyPasteLineAction#49c321d6")!=null)
 return (nc.ui.pubapp.uif2app.actions.BodyPasteLineAction)context.get("nc.ui.pubapp.uif2app.actions.BodyPasteLineAction#49c321d6");
  nc.ui.pubapp.uif2app.actions.BodyPasteLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyPasteLineAction();
  context.put("nc.ui.pubapp.uif2app.actions.BodyPasteLineAction#49c321d6",bean);
  bean.setClearItems(getManagedList8());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList8(){  List list = new ArrayList();  list.add("pk_task_b");  list.add("rowno");  return list;}

private nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction getBodyPasteToTailAction_29e28e6f(){
 if(context.get("nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction#29e28e6f")!=null)
 return (nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction)context.get("nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction#29e28e6f");
  nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction bean = new nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction();
  context.put("nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction#29e28e6f",bean);
  bean.setClearItems(getManagedList9());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList9(){  List list = new ArrayList();  list.add("pk_task_b");  list.add("rowno");  return list;}

private nc.ui.pubapp.uif2app.actions.BodyLineEditAction getBodyLineEditAction_58f52ee4(){
 if(context.get("nc.ui.pubapp.uif2app.actions.BodyLineEditAction#58f52ee4")!=null)
 return (nc.ui.pubapp.uif2app.actions.BodyLineEditAction)context.get("nc.ui.pubapp.uif2app.actions.BodyLineEditAction#58f52ee4");
  nc.ui.pubapp.uif2app.actions.BodyLineEditAction bean = new nc.ui.pubapp.uif2app.actions.BodyLineEditAction();
  context.put("nc.ui.pubapp.uif2app.actions.BodyLineEditAction#58f52ee4",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare getCompositeBillDataPrepare_48d9a85c(){
 if(context.get("nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare#48d9a85c")!=null)
 return (nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare)context.get("nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare#48d9a85c");
  nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare bean = new nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare();
  context.put("nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare#48d9a85c",bean);
  bean.setBillDataPrepares(getManagedList10());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList10(){  List list = new ArrayList();  list.add(getUserdefitemPreparator());  list.add(getMarAsstPreparator());  return list;}

public nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator getMarAsstPreparator(){
 if(context.get("marAsstPreparator")!=null)
 return (nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator)context.get("marAsstPreparator");
  nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator bean = new nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator();
  context.put("marAsstPreparator",bean);
  bean.setModel(getManageAppModel());
  bean.setContainer(getUserdefitemContainer());
  bean.setPrefix("vfree");
  bean.setMaterialField("pk_material");
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
  bean.setParams(getManagedList11());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList11(){  List list = new ArrayList();  list.add(getQueryParam_49bc5561());  list.add(getQueryParam_6b897918());  list.add(getQueryParam_e6d4dd0());  return list;}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_49bc5561(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#49bc5561")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#49bc5561");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#49bc5561",bean);
  bean.setMdfullname("qcco.task");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_6b897918(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#6b897918")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#6b897918");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#6b897918",bean);
  bean.setMdfullname("qcco.TaskBVO");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_e6d4dd0(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#e6d4dd0")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#e6d4dd0");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#e6d4dd0",bean);
  bean.setRulecode("materialassistant");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

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

private List getManagedList12(){  List list = new ArrayList();  list.add(getUserdefQueryParam_2a0f9a16());  list.add(getUserdefQueryParam_664d2fec());  return list;}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_2a0f9a16(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#2a0f9a16")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#2a0f9a16");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#2a0f9a16",bean);
  bean.setMdfullname("qcco.task");
  bean.setPos(0);
  bean.setPrefix("vdef");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_664d2fec(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#664d2fec")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#664d2fec");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#664d2fec",bean);
  bean.setMdfullname("task.TaskBVO");
  bean.setPos(1);
  bean.setPrefix("vbdef");
  bean.setTabcode("TaskBVO");
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
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel getCardInfoPnl(){
 if(context.get("cardInfoPnl")!=null)
 return (nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel)context.get("cardInfoPnl");
  nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel bean = new nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel();
  context.put("cardInfoPnl",bean);
  bean.setTitleAction(getReturnaction());
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.actions.UEReturnAction getReturnaction(){
 if(context.get("returnaction")!=null)
 return (nc.ui.pubapp.uif2app.actions.UEReturnAction)context.get("returnaction");
  nc.ui.pubapp.uif2app.actions.UEReturnAction bean = new nc.ui.pubapp.uif2app.actions.UEReturnAction();
  context.put("returnaction",bean);
  bean.setGoComponent(getMainGrandlistView());
  bean.setSaveAction(getSaveAction());
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.TangramContainer getContainer(){
 if(context.get("container")!=null)
 return (nc.ui.uif2.TangramContainer)context.get("container");
  nc.ui.uif2.TangramContainer bean = new nc.ui.uif2.TangramContainer();
  context.put("container",bean);
  bean.setModel(getManageAppModel());
  bean.setTangramLayoutRoot(getTBNode_3cc2fb2());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.TBNode getTBNode_3cc2fb2(){
 if(context.get("nc.ui.uif2.tangramlayout.node.TBNode#3cc2fb2")!=null)
 return (nc.ui.uif2.tangramlayout.node.TBNode)context.get("nc.ui.uif2.tangramlayout.node.TBNode#3cc2fb2");
  nc.ui.uif2.tangramlayout.node.TBNode bean = new nc.ui.uif2.tangramlayout.node.TBNode();
  context.put("nc.ui.uif2.tangramlayout.node.TBNode#3cc2fb2",bean);
  bean.setShowMode("CardLayout");
  bean.setTabs(getManagedList13());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList13(){  List list = new ArrayList();  list.add(getHSNode_2d0ac555());  list.add(getVSNode_40c5f2df());  return list;}

private nc.ui.uif2.tangramlayout.node.HSNode getHSNode_2d0ac555(){
 if(context.get("nc.ui.uif2.tangramlayout.node.HSNode#2d0ac555")!=null)
 return (nc.ui.uif2.tangramlayout.node.HSNode)context.get("nc.ui.uif2.tangramlayout.node.HSNode#2d0ac555");
  nc.ui.uif2.tangramlayout.node.HSNode bean = new nc.ui.uif2.tangramlayout.node.HSNode();
  context.put("nc.ui.uif2.tangramlayout.node.HSNode#2d0ac555",bean);
  bean.setLeft(getCNode_5585636c());
  bean.setRight(getVSNode_3320c784());
  bean.setDividerLocation(210f);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_5585636c(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#5585636c")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#5585636c");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#5585636c",bean);
  bean.setComponent(getQueryArea());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.VSNode getVSNode_3320c784(){
 if(context.get("nc.ui.uif2.tangramlayout.node.VSNode#3320c784")!=null)
 return (nc.ui.uif2.tangramlayout.node.VSNode)context.get("nc.ui.uif2.tangramlayout.node.VSNode#3320c784");
  nc.ui.uif2.tangramlayout.node.VSNode bean = new nc.ui.uif2.tangramlayout.node.VSNode();
  context.put("nc.ui.uif2.tangramlayout.node.VSNode#3320c784",bean);
  bean.setUp(getCNode_28427351());
  bean.setDown(getCNode_6fb0994d());
  bean.setDividerLocation(25f);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_28427351(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#28427351")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#28427351");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#28427351",bean);
  bean.setComponent(getQueryInfo());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_6fb0994d(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#6fb0994d")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#6fb0994d");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#6fb0994d",bean);
  bean.setName(getI18nFB_22a0df7d());
  bean.setComponent(getMainGrandlistView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_22a0df7d(){
 if(context.get("nc.ui.uif2.I18nFB#22a0df7d")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#22a0df7d");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#22a0df7d",bean);  bean.setResDir("common");
  bean.setResId("UC001-0000107");
  bean.setDefaultValue("列表");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#22a0df7d",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private nc.ui.uif2.tangramlayout.node.VSNode getVSNode_40c5f2df(){
 if(context.get("nc.ui.uif2.tangramlayout.node.VSNode#40c5f2df")!=null)
 return (nc.ui.uif2.tangramlayout.node.VSNode)context.get("nc.ui.uif2.tangramlayout.node.VSNode#40c5f2df");
  nc.ui.uif2.tangramlayout.node.VSNode bean = new nc.ui.uif2.tangramlayout.node.VSNode();
  context.put("nc.ui.uif2.tangramlayout.node.VSNode#40c5f2df",bean);
  bean.setUp(getCNode_1e21668d());
  bean.setDown(getCNode_7c821745());
  bean.setDividerLocation(30f);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_1e21668d(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#1e21668d")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#1e21668d");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#1e21668d",bean);
  bean.setComponent(getCardInfoPnl());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_7c821745(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#7c821745")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#7c821745");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#7c821745",bean);
  bean.setName(getI18nFB_22d3c26f());
  bean.setComponent(getMainGrandbillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_22d3c26f(){
 if(context.get("nc.ui.uif2.I18nFB#22d3c26f")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#22d3c26f");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#22d3c26f",bean);  bean.setResDir("common");
  bean.setResId("UC001-0000106");
  bean.setDefaultValue("卡片");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#22d3c26f",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

public nc.ui.pubapp.uif2app.event.ChildrenPicky getChildrenPicky(){
 if(context.get("childrenPicky")!=null)
 return (nc.ui.pubapp.uif2app.event.ChildrenPicky)context.get("childrenPicky");
  nc.ui.pubapp.uif2app.event.ChildrenPicky bean = new nc.ui.pubapp.uif2app.event.ChildrenPicky();
  context.put("childrenPicky",bean);
  bean.setBillform(getBillFormEditor());
  bean.setBodyVoClasses(getManagedList14());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList14(){  List list = new ArrayList();  list.add("nc.vo.qcco.task.TaskBVO");  return list;}

public nc.ui.pubapp.uif2app.model.AppEventHandlerMediator getGrandModelEventMediator(){
 if(context.get("grandModelEventMediator")!=null)
 return (nc.ui.pubapp.uif2app.model.AppEventHandlerMediator)context.get("grandModelEventMediator");
  nc.ui.pubapp.uif2app.model.AppEventHandlerMediator bean = new nc.ui.pubapp.uif2app.model.AppEventHandlerMediator();
  context.put("grandModelEventMediator",bean);
  bean.setModel(getManageAppModel2());
  bean.setHandlerGroup(getManagedList15());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList15(){  List list = new ArrayList();  list.add(getEventHandlerGroup_55531ba7());  list.add(getEventHandlerGroup_40540fe8());  return list;}

private nc.ui.pubapp.uif2app.event.EventHandlerGroup getEventHandlerGroup_55531ba7(){
 if(context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#55531ba7")!=null)
 return (nc.ui.pubapp.uif2app.event.EventHandlerGroup)context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#55531ba7");
  nc.ui.pubapp.uif2app.event.EventHandlerGroup bean = new nc.ui.pubapp.uif2app.event.EventHandlerGroup();
  context.put("nc.ui.pubapp.uif2app.event.EventHandlerGroup#55531ba7",bean);
  bean.setEvent("nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent");
  bean.setHandler(getGrandBodyBeforeEditHandler_524f5110());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.qcco.task.ace.handler.GrandBodyBeforeEditHandler getGrandBodyBeforeEditHandler_524f5110(){
 if(context.get("nc.ui.qcco.task.ace.handler.GrandBodyBeforeEditHandler#524f5110")!=null)
 return (nc.ui.qcco.task.ace.handler.GrandBodyBeforeEditHandler)context.get("nc.ui.qcco.task.ace.handler.GrandBodyBeforeEditHandler#524f5110");
  nc.ui.qcco.task.ace.handler.GrandBodyBeforeEditHandler bean = new nc.ui.qcco.task.ace.handler.GrandBodyBeforeEditHandler();
  context.put("nc.ui.qcco.task.ace.handler.GrandBodyBeforeEditHandler#524f5110",bean);
  bean.setMainBillForm(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.event.EventHandlerGroup getEventHandlerGroup_40540fe8(){
 if(context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#40540fe8")!=null)
 return (nc.ui.pubapp.uif2app.event.EventHandlerGroup)context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#40540fe8");
  nc.ui.pubapp.uif2app.event.EventHandlerGroup bean = new nc.ui.pubapp.uif2app.event.EventHandlerGroup();
  context.put("nc.ui.pubapp.uif2app.event.EventHandlerGroup#40540fe8",bean);
  bean.setEvent("nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent");
  bean.setHandler(getGrandBodyAfterEditHandler_433c9cb4());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.qcco.task.ace.handler.GrandBodyAfterEditHandler getGrandBodyAfterEditHandler_433c9cb4(){
 if(context.get("nc.ui.qcco.task.ace.handler.GrandBodyAfterEditHandler#433c9cb4")!=null)
 return (nc.ui.qcco.task.ace.handler.GrandBodyAfterEditHandler)context.get("nc.ui.qcco.task.ace.handler.GrandBodyAfterEditHandler#433c9cb4");
  nc.ui.qcco.task.ace.handler.GrandBodyAfterEditHandler bean = new nc.ui.qcco.task.ace.handler.GrandBodyAfterEditHandler();
  context.put("nc.ui.qcco.task.ace.handler.GrandBodyAfterEditHandler#433c9cb4",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.model.AppEventHandlerMediator getEventMediator(){
 if(context.get("eventMediator")!=null)
 return (nc.ui.pubapp.uif2app.model.AppEventHandlerMediator)context.get("eventMediator");
  nc.ui.pubapp.uif2app.model.AppEventHandlerMediator bean = new nc.ui.pubapp.uif2app.model.AppEventHandlerMediator();
  context.put("eventMediator",bean);
  bean.setModel(getManageAppModel());
  bean.setHandlerGroup(getManagedList16());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList16(){  List list = new ArrayList();  list.add(getEventHandlerGroup_6f23b58e());  list.add(getEventHandlerGroup_6b129040());  list.add(getEventHandlerGroup_24bdd29f());  list.add(getEventHandlerGroup_1e2a864a());  list.add(getEventHandlerGroup_6e48fd6f());  list.add(getEventHandlerGroup_356d6ffd());  return list;}

private nc.ui.pubapp.uif2app.event.EventHandlerGroup getEventHandlerGroup_6f23b58e(){
 if(context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#6f23b58e")!=null)
 return (nc.ui.pubapp.uif2app.event.EventHandlerGroup)context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#6f23b58e");
  nc.ui.pubapp.uif2app.event.EventHandlerGroup bean = new nc.ui.pubapp.uif2app.event.EventHandlerGroup();
  context.put("nc.ui.pubapp.uif2app.event.EventHandlerGroup#6f23b58e",bean);
  bean.setEvent("nc.ui.pubapp.uif2app.event.billform.AddEvent");
  bean.setHandler(getAceAddHandler_4ee06956());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.qcco.task.ace.handler.AceAddHandler getAceAddHandler_4ee06956(){
 if(context.get("nc.ui.qcco.task.ace.handler.AceAddHandler#4ee06956")!=null)
 return (nc.ui.qcco.task.ace.handler.AceAddHandler)context.get("nc.ui.qcco.task.ace.handler.AceAddHandler#4ee06956");
  nc.ui.qcco.task.ace.handler.AceAddHandler bean = new nc.ui.qcco.task.ace.handler.AceAddHandler();
  context.put("nc.ui.qcco.task.ace.handler.AceAddHandler#4ee06956",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.event.EventHandlerGroup getEventHandlerGroup_6b129040(){
 if(context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#6b129040")!=null)
 return (nc.ui.pubapp.uif2app.event.EventHandlerGroup)context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#6b129040");
  nc.ui.pubapp.uif2app.event.EventHandlerGroup bean = new nc.ui.pubapp.uif2app.event.EventHandlerGroup();
  context.put("nc.ui.pubapp.uif2app.event.EventHandlerGroup#6b129040",bean);
  bean.setEvent("nc.ui.pubapp.uif2app.event.OrgChangedEvent");
  bean.setHandler(getAceOrgChangeHandler_1164b6a3());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.qcco.task.ace.handler.AceOrgChangeHandler getAceOrgChangeHandler_1164b6a3(){
 if(context.get("nc.ui.qcco.task.ace.handler.AceOrgChangeHandler#1164b6a3")!=null)
 return (nc.ui.qcco.task.ace.handler.AceOrgChangeHandler)context.get("nc.ui.qcco.task.ace.handler.AceOrgChangeHandler#1164b6a3");
  nc.ui.qcco.task.ace.handler.AceOrgChangeHandler bean = new nc.ui.qcco.task.ace.handler.AceOrgChangeHandler();
  context.put("nc.ui.qcco.task.ace.handler.AceOrgChangeHandler#1164b6a3",bean);
  bean.setBillForm(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.event.EventHandlerGroup getEventHandlerGroup_24bdd29f(){
 if(context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#24bdd29f")!=null)
 return (nc.ui.pubapp.uif2app.event.EventHandlerGroup)context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#24bdd29f");
  nc.ui.pubapp.uif2app.event.EventHandlerGroup bean = new nc.ui.pubapp.uif2app.event.EventHandlerGroup();
  context.put("nc.ui.pubapp.uif2app.event.EventHandlerGroup#24bdd29f",bean);
  bean.setEvent("nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent");
  bean.setHandler(getAceBodyAfterEditHandler_fbc90ab());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.qcco.task.ace.handler.AceBodyAfterEditHandler getAceBodyAfterEditHandler_fbc90ab(){
 if(context.get("nc.ui.qcco.task.ace.handler.AceBodyAfterEditHandler#fbc90ab")!=null)
 return (nc.ui.qcco.task.ace.handler.AceBodyAfterEditHandler)context.get("nc.ui.qcco.task.ace.handler.AceBodyAfterEditHandler#fbc90ab");
  nc.ui.qcco.task.ace.handler.AceBodyAfterEditHandler bean = new nc.ui.qcco.task.ace.handler.AceBodyAfterEditHandler();
  context.put("nc.ui.qcco.task.ace.handler.AceBodyAfterEditHandler#fbc90ab",bean);
  bean.setMainBillForm(getBillFormEditor());
  bean.setGrandCard(getSunbillFormEditor1());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.event.EventHandlerGroup getEventHandlerGroup_1e2a864a(){
 if(context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#1e2a864a")!=null)
 return (nc.ui.pubapp.uif2app.event.EventHandlerGroup)context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#1e2a864a");
  nc.ui.pubapp.uif2app.event.EventHandlerGroup bean = new nc.ui.pubapp.uif2app.event.EventHandlerGroup();
  context.put("nc.ui.pubapp.uif2app.event.EventHandlerGroup#1e2a864a",bean);
  bean.setEvent("nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent");
  bean.setHandler(getAceBodyBeforeEditHandler_2a4ed791());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.qcco.task.ace.handler.AceBodyBeforeEditHandler getAceBodyBeforeEditHandler_2a4ed791(){
 if(context.get("nc.ui.qcco.task.ace.handler.AceBodyBeforeEditHandler#2a4ed791")!=null)
 return (nc.ui.qcco.task.ace.handler.AceBodyBeforeEditHandler)context.get("nc.ui.qcco.task.ace.handler.AceBodyBeforeEditHandler#2a4ed791");
  nc.ui.qcco.task.ace.handler.AceBodyBeforeEditHandler bean = new nc.ui.qcco.task.ace.handler.AceBodyBeforeEditHandler();
  context.put("nc.ui.qcco.task.ace.handler.AceBodyBeforeEditHandler#2a4ed791",bean);
  bean.setMainBillForm(getBillFormEditor());
  bean.setGrandCard(getSunbillFormEditor1());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.event.EventHandlerGroup getEventHandlerGroup_6e48fd6f(){
 if(context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#6e48fd6f")!=null)
 return (nc.ui.pubapp.uif2app.event.EventHandlerGroup)context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#6e48fd6f");
  nc.ui.pubapp.uif2app.event.EventHandlerGroup bean = new nc.ui.pubapp.uif2app.event.EventHandlerGroup();
  context.put("nc.ui.pubapp.uif2app.event.EventHandlerGroup#6e48fd6f",bean);
  bean.setEvent("nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent");
  bean.setHandler(getAceHeadTailAfterEditHandler_79d673be());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.qcco.task.ace.handler.AceHeadTailAfterEditHandler getAceHeadTailAfterEditHandler_79d673be(){
 if(context.get("nc.ui.qcco.task.ace.handler.AceHeadTailAfterEditHandler#79d673be")!=null)
 return (nc.ui.qcco.task.ace.handler.AceHeadTailAfterEditHandler)context.get("nc.ui.qcco.task.ace.handler.AceHeadTailAfterEditHandler#79d673be");
  nc.ui.qcco.task.ace.handler.AceHeadTailAfterEditHandler bean = new nc.ui.qcco.task.ace.handler.AceHeadTailAfterEditHandler();
  context.put("nc.ui.qcco.task.ace.handler.AceHeadTailAfterEditHandler#79d673be",bean);
  bean.setMainBillForm(getBillFormEditor());
  bean.setGrandCard(getSunbillFormEditor1());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.event.EventHandlerGroup getEventHandlerGroup_356d6ffd(){
 if(context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#356d6ffd")!=null)
 return (nc.ui.pubapp.uif2app.event.EventHandlerGroup)context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#356d6ffd");
  nc.ui.pubapp.uif2app.event.EventHandlerGroup bean = new nc.ui.pubapp.uif2app.event.EventHandlerGroup();
  context.put("nc.ui.pubapp.uif2app.event.EventHandlerGroup#356d6ffd",bean);
  bean.setEvent("nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent");
  bean.setHandler(getAceHeadTailBeforeEditHandler_79f6baf9());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.qcco.task.ace.handler.AceHeadTailBeforeEditHandler getAceHeadTailBeforeEditHandler_79f6baf9(){
 if(context.get("nc.ui.qcco.task.ace.handler.AceHeadTailBeforeEditHandler#79f6baf9")!=null)
 return (nc.ui.qcco.task.ace.handler.AceHeadTailBeforeEditHandler)context.get("nc.ui.qcco.task.ace.handler.AceHeadTailBeforeEditHandler#79f6baf9");
  nc.ui.qcco.task.ace.handler.AceHeadTailBeforeEditHandler bean = new nc.ui.qcco.task.ace.handler.AceHeadTailBeforeEditHandler();
  context.put("nc.ui.qcco.task.ace.handler.AceHeadTailBeforeEditHandler#79f6baf9",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.actions.ActionContributors getToftpanelActionContributors(){
 if(context.get("toftpanelActionContributors")!=null)
 return (nc.ui.uif2.actions.ActionContributors)context.get("toftpanelActionContributors");
  nc.ui.uif2.actions.ActionContributors bean = new nc.ui.uif2.actions.ActionContributors();
  context.put("toftpanelActionContributors",bean);
  bean.setContributors(getManagedList17());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList17(){  List list = new ArrayList();  list.add(getActionsOfList());  list.add(getActionsOfCard());  return list;}

public nc.ui.uif2.actions.StandAloneToftPanelActionContainer getActionsOfList(){
 if(context.get("actionsOfList")!=null)
 return (nc.ui.uif2.actions.StandAloneToftPanelActionContainer)context.get("actionsOfList");
  nc.ui.uif2.actions.StandAloneToftPanelActionContainer bean = new nc.ui.uif2.actions.StandAloneToftPanelActionContainer(getListView());  context.put("actionsOfList",bean);
  bean.setModel(getManageAppModel());
  bean.setActions(getManagedList18());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList18(){  List list = new ArrayList();  list.add(getAddAction());  list.add(getEditAction());  list.add(getDeleteAction());  list.add(getSeparatorAction());  list.add(getQueryAction());  list.add(getRefreshAction());  list.add(getSeparatorAction());  list.add(getPrintMenuAction());  list.add(getSeparatorAction());  list.add(getCommitScriptAction());  list.add(getUnCommitScriptAction());  list.add(getApproveScriptAction());  list.add(getUNApproveScriptAction());  list.add(getSeparatorAction());  list.add(getSeparatorAction());  list.add(getOutputAction());  list.add(getSeparatorAction());  list.add(getPFApproveStatusInfoAction());  list.add(getFileupload());  list.add(getSeparatorAction());  list.add(getQuotationAction());  list.add(getPayDemandAction());  list.add(getOfficialReportAction());  list.add(getSeparatorAction());  return list;}

public nc.ui.uif2.actions.StandAloneToftPanelActionContainer getActionsOfCard(){
 if(context.get("actionsOfCard")!=null)
 return (nc.ui.uif2.actions.StandAloneToftPanelActionContainer)context.get("actionsOfCard");
  nc.ui.uif2.actions.StandAloneToftPanelActionContainer bean = new nc.ui.uif2.actions.StandAloneToftPanelActionContainer(getBillFormEditor());  context.put("actionsOfCard",bean);
  bean.setModel(getManageAppModel());
  bean.setActions(getManagedList19());
  bean.setEditActions(getManagedList20());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList19(){  List list = new ArrayList();  list.add(getAddAction());  list.add(getEditAction());  list.add(getDeleteAction());  list.add(getSeparatorAction());  list.add(getQueryAction());  list.add(getCardRefreshAction());  list.add(getSeparatorAction());  list.add(getPrintMenuAction());  list.add(getSeparatorAction());  list.add(getCommitScriptAction());  list.add(getUnCommitScriptAction());  list.add(getApproveScriptAction());  list.add(getUNApproveScriptAction());  list.add(getSeparatorAction());  list.add(getSeparatorAction());  list.add(getOutputAction());  list.add(getSeparatorAction());  list.add(getPFApproveStatusInfoAction());  list.add(getFileupload());  list.add(getSeparatorAction());  list.add(getQuotationAction());  list.add(getPayDemandAction());  list.add(getOfficialReportAction());  list.add(getSeparatorAction());  return list;}

private List getManagedList20(){  List list = new ArrayList();  list.add(getSaveAction());  list.add(getTemporarilySaveAction());  list.add(getCancelAction());  return list;}

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
  bean.setShowUpComponent(getMainGrandbillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.actions.AddAction getAddAction(){
 if(context.get("addAction")!=null)
 return (nc.ui.uif2.actions.AddAction)context.get("addAction");
  nc.ui.uif2.actions.AddAction bean = new nc.ui.uif2.actions.AddAction();
  context.put("addAction",bean);
  bean.setModel(getManageAppModel());
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
  bean.setModel(getManageAppModel());
  bean.setInterceptor(getShowCardInterceptor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.qcco.task.action.TaskDeleteAction getDeleteAction(){
 if(context.get("deleteAction")!=null)
 return (nc.ui.qcco.task.action.TaskDeleteAction)context.get("deleteAction");
  nc.ui.qcco.task.action.TaskDeleteAction bean = new nc.ui.qcco.task.action.TaskDeleteAction();
  context.put("deleteAction",bean);
  bean.setModel(getManageAppModel());
  bean.setMainGrandModel(getMainGrandModel());
  bean.setSingleBillService(getDeleteProxy());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.qcco.task.action.TaskSaveAction getSaveAction(){
 if(context.get("saveAction")!=null)
 return (nc.ui.qcco.task.action.TaskSaveAction)context.get("saveAction");
  nc.ui.qcco.task.action.TaskSaveAction bean = new nc.ui.qcco.task.action.TaskSaveAction();
  context.put("saveAction",bean);
  bean.setModel(getManageAppModel());
  bean.setService(getBmModelModelService());
  bean.setEditor(getBillFormEditor());
  bean.setMainGrandModel(getMainGrandModel());
  bean.setRefresh(getCardRefreshAction());
  bean.setBillFormEditor(getBillFormEditor());
  bean.setBillForm(getMainGrandbillFormEditor());
  bean.setValidationService(getValidateService());
  bean.setExceptionHandler(getExceptionHandler());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.qcco.task.action.FileUploadAction getFileupload(){
 if(context.get("fileupload")!=null)
 return (nc.ui.qcco.task.action.FileUploadAction)context.get("fileupload");
  nc.ui.qcco.task.action.FileUploadAction bean = new nc.ui.qcco.task.action.FileUploadAction();
  context.put("fileupload",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.qcco.task.action.TaskTemporarilySaveAction getTemporarilySaveAction(){
 if(context.get("temporarilySaveAction")!=null)
 return (nc.ui.qcco.task.action.TaskTemporarilySaveAction)context.get("temporarilySaveAction");
  nc.ui.qcco.task.action.TaskTemporarilySaveAction bean = new nc.ui.qcco.task.action.TaskTemporarilySaveAction();
  context.put("temporarilySaveAction",bean);
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

public nc.ui.pubapp.uif2app.validation.CompositeValidation getValidateService(){
 if(context.get("validateService")!=null)
 return (nc.ui.pubapp.uif2app.validation.CompositeValidation)context.get("validateService");
  nc.ui.pubapp.uif2app.validation.CompositeValidation bean = new nc.ui.pubapp.uif2app.validation.CompositeValidation();
  context.put("validateService",bean);
  bean.setValidators(getManagedList21());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList21(){  List list = new ArrayList();  list.add(getTemplateNotNullValidation_23d36672());  return list;}

private nc.ui.pubapp.uif2app.validation.TemplateNotNullValidation getTemplateNotNullValidation_23d36672(){
 if(context.get("nc.ui.pubapp.uif2app.validation.TemplateNotNullValidation#23d36672")!=null)
 return (nc.ui.pubapp.uif2app.validation.TemplateNotNullValidation)context.get("nc.ui.pubapp.uif2app.validation.TemplateNotNullValidation#23d36672");
  nc.ui.pubapp.uif2app.validation.TemplateNotNullValidation bean = new nc.ui.pubapp.uif2app.validation.TemplateNotNullValidation();
  context.put("nc.ui.pubapp.uif2app.validation.TemplateNotNullValidation#23d36672",bean);
  bean.setBillForm(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.CancelAction getCancelAction(){
 if(context.get("cancelAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.CancelAction)context.get("cancelAction");
  nc.ui.pubapp.uif2app.actions.CancelAction bean = new nc.ui.pubapp.uif2app.actions.CancelAction();
  context.put("cancelAction",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.CopyAction getCopyAction(){
 if(context.get("copyAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.CopyAction)context.get("copyAction");
  nc.ui.pubapp.uif2app.actions.CopyAction bean = new nc.ui.pubapp.uif2app.actions.CopyAction();
  context.put("copyAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setExceptionHandler(getExceptionHandler());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction getQueryAction(){
 if(context.get("queryAction")!=null)
 return (nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction)context.get("queryAction");
  nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction bean = new nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction();
  context.put("queryAction",bean);
  bean.setModel(getManageAppModel());
  bean.setDataManager(getModelDataManager());
  bean.setShowUpComponent(getMainGrandlistView());
  bean.setTemplateContainer(getQueryTemplateContainer());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction getRefreshAction(){
 if(context.get("refreshAction")!=null)
 return (nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction)context.get("refreshAction");
  nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction bean = new nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction();
  context.put("refreshAction",bean);
  bean.setDataManager(getModelDataManager());
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.RefreshSingleAction getCardRefreshAction(){
 if(context.get("cardRefreshAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.RefreshSingleAction)context.get("cardRefreshAction");
  nc.ui.pubapp.uif2app.actions.RefreshSingleAction bean = new nc.ui.pubapp.uif2app.actions.RefreshSingleAction();
  context.put("cardRefreshAction",bean);
  bean.setModel(getManageAppModel());
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
  bean.setModel(getManageAppModel());
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
  bean.setModel(getManageAppModel());
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
  bean.setModel(getManageAppModel());
  bean.setParent(getBillFormEditor());
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
  bean.setName(getI18nFB_bead8be());
  bean.setActions(getManagedList22());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_bead8be(){
 if(context.get("nc.ui.uif2.I18nFB#bead8be")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#bead8be");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#bead8be",bean);  bean.setResDir("common");
  bean.setResId("UC001-0000007");
  bean.setDefaultValue("打印");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#bead8be",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList22(){  List list = new ArrayList();  list.add(getPrintAction());  list.add(getPreviewAction());  list.add(getOutputAction());  return list;}

public nc.ui.qcco.task.action.QuotationAction getQuotationAction(){
 if(context.get("quotationAction")!=null)
 return (nc.ui.qcco.task.action.QuotationAction)context.get("quotationAction");
  nc.ui.qcco.task.action.QuotationAction bean = new nc.ui.qcco.task.action.QuotationAction();
  context.put("quotationAction",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.qcco.task.action.PayDemandAction getPayDemandAction(){
 if(context.get("payDemandAction")!=null)
 return (nc.ui.qcco.task.action.PayDemandAction)context.get("payDemandAction");
  nc.ui.qcco.task.action.PayDemandAction bean = new nc.ui.qcco.task.action.PayDemandAction();
  context.put("payDemandAction",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.qcco.task.action.OfficialReportAction getOfficialReportAction(){
 if(context.get("officialReportAction")!=null)
 return (nc.ui.qcco.task.action.OfficialReportAction)context.get("officialReportAction");
  nc.ui.qcco.task.action.OfficialReportAction bean = new nc.ui.qcco.task.action.OfficialReportAction();
  context.put("officialReportAction",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.qcco.task.action.SatisfactionAction getSatisfactionAction(){
 if(context.get("satisfactionAction")!=null)
 return (nc.ui.qcco.task.action.SatisfactionAction)context.get("satisfactionAction");
  nc.ui.qcco.task.action.SatisfactionAction bean = new nc.ui.qcco.task.action.SatisfactionAction();
  context.put("satisfactionAction",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.funcnode.ui.action.GroupAction getPreviewActionGroup(){
 if(context.get("previewActionGroup")!=null)
 return (nc.funcnode.ui.action.GroupAction)context.get("previewActionGroup");
  nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
  context.put("previewActionGroup",bean);
  bean.setCode("previewActionGroup");
  bean.setName("预览");
  bean.setActions(getManagedList23());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList23(){  List list = new ArrayList();  list.add(getQuotationAction());  list.add(getPayDemandAction());  list.add(getOfficialReportAction());  return list;}

public nc.ui.pubapp.uif2app.lazilyload.DefaultBillLazilyLoader getBillLazilyLoader(){
 if(context.get("billLazilyLoader")!=null)
 return (nc.ui.pubapp.uif2app.lazilyload.DefaultBillLazilyLoader)context.get("billLazilyLoader");
  nc.ui.pubapp.uif2app.lazilyload.DefaultBillLazilyLoader bean = new nc.ui.pubapp.uif2app.lazilyload.DefaultBillLazilyLoader();
  context.put("billLazilyLoader",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager getLasilyLodadMediator(){
 if(context.get("lasilyLodadMediator")!=null)
 return (nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager)context.get("lasilyLodadMediator");
  nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager bean = new nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager();
  context.put("lasilyLodadMediator",bean);
  bean.setModel(getManageAppModel());
  bean.setLoader(getBillLazilyLoader());
  bean.setLazilyLoadSupporter(getManagedList24());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList24(){  List list = new ArrayList();  list.add(getCardPanelLazilyLoad_673ba80e());  list.add(getListPanelLazilyLoad_7d966414());  return list;}

private nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad getCardPanelLazilyLoad_673ba80e(){
 if(context.get("nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad#673ba80e")!=null)
 return (nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad)context.get("nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad#673ba80e");
  nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad bean = new nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad();
  context.put("nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad#673ba80e",bean);
  bean.setBillform(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad getListPanelLazilyLoad_7d966414(){
 if(context.get("nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad#7d966414")!=null)
 return (nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad)context.get("nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad#7d966414");
  nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad bean = new nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad();
  context.put("nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad#7d966414",bean);
  bean.setListView(getListView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.model.BillBodySortMediator getBillBodySortMediator(){
 if(context.get("billBodySortMediator")!=null)
 return (nc.ui.pubapp.uif2app.model.BillBodySortMediator)context.get("billBodySortMediator");
  nc.ui.pubapp.uif2app.model.BillBodySortMediator bean = new nc.ui.pubapp.uif2app.model.BillBodySortMediator(getManageAppModel(),getBillFormEditor(),getListView());  context.put("billBodySortMediator",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener getInitDataListener(){
 if(context.get("InitDataListener")!=null)
 return (nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener)context.get("InitDataListener");
  nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener bean = new nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener();
  context.put("InitDataListener",bean);
  bean.setModel(getManageAppModel());
  bean.setContext(getContext());
  bean.setVoClassName("nc.vo.qcco.task.AggTaskHVO");
  bean.setAutoShowUpComponent(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.common.validateservice.ClosingCheck getClosingListener(){
 if(context.get("ClosingListener")!=null)
 return (nc.ui.pubapp.common.validateservice.ClosingCheck)context.get("ClosingListener");
  nc.ui.pubapp.common.validateservice.ClosingCheck bean = new nc.ui.pubapp.common.validateservice.ClosingCheck();
  context.put("ClosingListener",bean);
  bean.setModel(getManageAppModel());
  bean.setSaveAction(getSaveAction());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.view.FractionFixMediator getFractionFixMediator(){
 if(context.get("fractionFixMediator")!=null)
 return (nc.ui.pubapp.uif2app.view.FractionFixMediator)context.get("fractionFixMediator");
  nc.ui.pubapp.uif2app.view.FractionFixMediator bean = new nc.ui.pubapp.uif2app.view.FractionFixMediator(getBillFormEditor());  context.put("fractionFixMediator",bean);
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator getMouseClickShowPanelMediator(){
 if(context.get("mouseClickShowPanelMediator")!=null)
 return (nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator)context.get("mouseClickShowPanelMediator");
  nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator bean = new nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator();
  context.put("mouseClickShowPanelMediator",bean);
  bean.setListView(getListView());
  bean.setShowUpComponent(getMainGrandbillFormEditor());
  bean.setHyperLinkColumn("vbillcode");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.bill.BillCodeMediator getBillCodeMediator(){
 if(context.get("billCodeMediator")!=null)
 return (nc.ui.pubapp.bill.BillCodeMediator)context.get("billCodeMediator");
  nc.ui.pubapp.bill.BillCodeMediator bean = new nc.ui.pubapp.bill.BillCodeMediator();
  context.put("billCodeMediator",bean);
  bean.setBillForm(getBillFormEditor());
  bean.setBillCodeKey("vbillcode");
  bean.setBillType("QC08");
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.view.RowNoMediator getRowNoMediator(){
 if(context.get("rowNoMediator")!=null)
 return (nc.ui.pubapp.uif2app.view.RowNoMediator)context.get("rowNoMediator");
  nc.ui.pubapp.uif2app.view.RowNoMediator bean = new nc.ui.pubapp.uif2app.view.RowNoMediator();
  context.put("rowNoMediator",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller getRemoteCallCombinatorCaller(){
 if(context.get("remoteCallCombinatorCaller")!=null)
 return (nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller)context.get("remoteCallCombinatorCaller");
  nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller bean = new nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller();
  context.put("remoteCallCombinatorCaller",bean);
  bean.setRemoteCallers(getManagedList25());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList25(){  List list = new ArrayList();  list.add(getQueryTemplateContainer());  list.add(getTemplateContainer());  list.add(getUserdefitemContainer());  return list;}

public nc.ui.pubapp.uif2app.actions.pflow.CommitScriptAction getCommitScriptAction(){
 if(context.get("commitScriptAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.pflow.CommitScriptAction)context.get("commitScriptAction");
  nc.ui.pubapp.uif2app.actions.pflow.CommitScriptAction bean = new nc.ui.pubapp.uif2app.actions.pflow.CommitScriptAction();
  context.put("commitScriptAction",bean);
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

public nc.ui.pubapp.uif2app.actions.pflow.UnCommitScriptAction getUnCommitScriptAction(){
 if(context.get("unCommitScriptAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.pflow.UnCommitScriptAction)context.get("unCommitScriptAction");
  nc.ui.pubapp.uif2app.actions.pflow.UnCommitScriptAction bean = new nc.ui.pubapp.uif2app.actions.pflow.UnCommitScriptAction();
  context.put("unCommitScriptAction",bean);
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

public nc.ui.pubapp.uif2app.actions.pflow.ApproveScriptAction getApproveScriptAction(){
 if(context.get("approveScriptAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.pflow.ApproveScriptAction)context.get("approveScriptAction");
  nc.ui.pubapp.uif2app.actions.pflow.ApproveScriptAction bean = new nc.ui.pubapp.uif2app.actions.pflow.ApproveScriptAction();
  context.put("approveScriptAction",bean);
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

public nc.ui.pubapp.uif2app.actions.pflow.UNApproveScriptAction getUNApproveScriptAction(){
 if(context.get("uNApproveScriptAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.pflow.UNApproveScriptAction)context.get("uNApproveScriptAction");
  nc.ui.pubapp.uif2app.actions.pflow.UNApproveScriptAction bean = new nc.ui.pubapp.uif2app.actions.pflow.UNApproveScriptAction();
  context.put("uNApproveScriptAction",bean);
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

public nc.ui.qcco.task.action.WriteBackLimsAction getWriteBackLims(){
 if(context.get("writeBackLims")!=null)
 return (nc.ui.qcco.task.action.WriteBackLimsAction)context.get("writeBackLims");
  nc.ui.qcco.task.action.WriteBackLimsAction bean = new nc.ui.qcco.task.action.WriteBackLimsAction();
  context.put("writeBackLims",bean);
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

public nc.ui.pubapp.uif2app.actions.LinkQueryAction getLinkQueryAction(){
 if(context.get("linkQueryAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.LinkQueryAction)context.get("linkQueryAction");
  nc.ui.pubapp.uif2app.actions.LinkQueryAction bean = new nc.ui.pubapp.uif2app.actions.LinkQueryAction();
  context.put("linkQueryAction",bean);
  bean.setModel(getManageAppModel());
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

public nc.ui.pubapp.uif2app.actions.pflow.PFApproveStatusInfoAction getPFApproveStatusInfoAction(){
 if(context.get("pFApproveStatusInfoAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.pflow.PFApproveStatusInfoAction)context.get("pFApproveStatusInfoAction");
  nc.ui.pubapp.uif2app.actions.pflow.PFApproveStatusInfoAction bean = new nc.ui.pubapp.uif2app.actions.pflow.PFApproveStatusInfoAction();
  context.put("pFApproveStatusInfoAction",bean);
  bean.setModel(getManageAppModel());
  bean.setExceptionHandler(getExceptionHandler());
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

}
