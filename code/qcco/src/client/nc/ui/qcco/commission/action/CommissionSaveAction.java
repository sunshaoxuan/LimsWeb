package nc.ui.qcco.commission.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.bs.uif2.BusinessExceptionAdapter;
import nc.bs.uif2.IActionCode;
import nc.bs.uif2.validation.IValidationService;
import nc.bs.uif2.validation.ValidationException;
import nc.desktop.ui.WorkbenchEnvironment;
import nc.itf.qcco.ICommissionMaintain;
import nc.itf.qcco.ITaskMaintain;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.linkoperate.LinkEditData;
import nc.ui.pubapp.uif2app.actions.DifferentVOSaveAction;
import nc.ui.pubapp.uif2app.components.grand.CardGrandPanelComposite;
import nc.ui.pubapp.uif2app.components.grand.model.MainGrandModel;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.qcco.commission.ace.handler.CommissionShowTemplate;
import nc.ui.uap.sf.SFClientUtil;
import nc.ui.uif2.IShowMsgConstant;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.UIState;
import nc.ui.uif2.actions.ActionInitializer;
import nc.util.mmf.framework.gc.GCClientBillCombinServer;
import nc.util.mmf.framework.gc.GCClientBillToServer;
import nc.util.mmf.framework.gc.GCPseudoColUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.qcco.commission.AggCommissionHVO;
import nc.vo.qcco.commission.CommissionHVO;
import nc.vo.qcco.task.AggTaskHVO;
import nc.vo.qcco.task.TaskHVO;

public class CommissionSaveAction extends DifferentVOSaveAction {

	/**
	 * SaveAction DifferentVOSaveAction
	 */
	private static final long serialVersionUID = -6804039169256642670L;

	private MainGrandModel mainGrandModel;
	private CardGrandPanelComposite billForm;
	private ShowUpableBillForm billFormEditor;
	private final String FUN_CODE = "C0J00203";

	// private IDataOperationService service;

	public CardGrandPanelComposite getBillForm() {
		return billForm;
	}

	public void setBillForm(CardGrandPanelComposite billForm) {
		this.billForm = billForm;
	}

	public ShowUpableBillForm getBillFormEditor() {
		return billFormEditor;
	}

	public void setBillFormEditor(ShowUpableBillForm billFormEditor) {
		this.billFormEditor = billFormEditor;
	}

	IValidationService validationService;

	public CommissionSaveAction() {
		super();
		ActionInitializer.initializeAction(this, IActionCode.SAVE);
	}

	// 注意将孙面板XXX属性设置
	@Override
	public void doAction(ActionEvent e) throws Exception {
		this.billFormEditor.getBillCardPanel().stopEditing();
		AggCommissionHVO agghvo = (AggCommissionHVO) this.getBillForm().getValue();
		if (null == agghvo) {
			return;
		}
		this.validate(agghvo);
		if (this.getModel().getUiState() == UIState.ADD) {
			// this.excuteInsert(agghvo);
			GCPseudoColUtil.getInstance().setPseudoColInfo(agghvo);
			doAddSave(agghvo);
			this.getMainGrandModel().clearBufferData();
		} else if (this.getModel().getUiState() == UIState.EDIT) {
			GCPseudoColUtil.getInstance().setPseudoColInfo(agghvo);
			//set 修改人
			if(null != agghvo && agghvo.getParentVO()!=null){
				
				String pk_user = billFormEditor.getModel().getContext().getPk_loginUser();
				agghvo.getParentVO().setModifier(pk_user);
			}
			doEditSave(agghvo);
			this.getMainGrandModel().clearBufferData();
		}
		String typeName = loadTemplet(agghvo);
		if(null != typeName){
			changeTemplet(typeName,this.billFormEditor.getBillCardPanel());
		}
		billForm.showMeUp();
		showSuccessInfo();
	}

	private String loadTemplet(AggCommissionHVO aggvo){
		String typeName = null;
		if(aggvo!=null && aggvo.getParentVO()!=null && aggvo.getParentVO().getPk_commissiontype()!=null){
			String pk_commissiontype = aggvo.getParentVO().getPk_commissiontype();
			if(pk_commissiontype!=null){
				IUAPQueryBS iUAPQueryBS = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName());   
				
				try {
					typeName = (String)iUAPQueryBS. executeQuery(
							" select NAME "
							+ " from NC_PROJ_TYPE WHERE ISENABLE=1 "
							+ " and PK_PROJ_TYPE = '"+pk_commissiontype+"'",new ColumnProcessor());
				} catch (BusinessException e) {
					e.printStackTrace();
				}  
				
			}
		}
		return typeName;
	}
	@Override
	protected void doAddSave(Object value) throws Exception {
		IBill[] clientVOs = { (IBill) value };

		GCClientBillToServer<IBill> tool = new GCClientBillToServer<IBill>();

		IBill[] lightVOs = tool.constructInsert(clientVOs);

		IBill[] afterUpdateVOs = null;

		if (getService() == null) {
			throw new BusinessException("service不能为空。");
		}

		afterUpdateVOs = getService().insert(lightVOs);
		String pk_task_h = createNewTask(afterUpdateVOs);
		if (pk_task_h != null
				&& MessageDialog.ID_YES == MessageDialog.showYesNoDlg(billFormEditor.getBillCardPanel(), "跳转!",
						"是否跳转到新的任务单?")) {
			LinkEditData data = new LinkEditData();
			data.setBillID(pk_task_h);
			SFClientUtil.openNodeLinkedMaintain(FUN_CODE, data);
		}

		new GCClientBillCombinServer<IBill>().combine(clientVOs, afterUpdateVOs);

		getModel().setUiState(UIState.NOT_EDIT);
		getMainGrandModel().directlyAdd(clientVOs[0]);
	}

	@Override
	protected void doEditSave(Object value) throws Exception {

		IBill[] clientVOs = { (IBill) value };

		GCClientBillToServer<IBill> tool = new GCClientBillToServer<IBill>();

		IBill[] oldVO = { (IBill) getModel().getSelectedData() };

		IBill[] lightVOs = tool.construct(oldVO, clientVOs);

		IBill[] afterUpdateVOs = null;

		String pk_task_h = null;

		if (getService() == null) {
			throw new BusinessException("service不能为空。");
		}
		if (MessageDialog.ID_YES == MessageDialog.showYesNoDlg(billFormEditor.getBillCardPanel(), "注意!",
				"修改委托单会删除已有的任务单,并生成新任务单,是否继续?")) {
			afterUpdateVOs = getService().update(lightVOs);
			deleteOldTask(afterUpdateVOs);
			pk_task_h = createNewTask(afterUpdateVOs);
		} else {
			return;
		}
		new GCClientBillCombinServer<IBill>().combine(clientVOs, afterUpdateVOs);

		getModel().setUiState(UIState.NOT_EDIT);
		//getMainGrandModel().directlyUpdate(clientVOs[0]);
		List<AggCommissionHVO> clientVOsList = new ArrayList();
		for(IBill bill : clientVOs){
			if(bill instanceof AggCommissionHVO){
				clientVOsList.add((AggCommissionHVO)bill);
			}
		}
		getMainGrandModel().getMainModel().initModel(clientVOsList.toArray(new AggCommissionHVO[0]));
		if (pk_task_h != null
				&& MessageDialog.ID_YES == MessageDialog.showYesNoDlg(billFormEditor.getBillCardPanel(), "跳转",
						"是否跳转到新的任务单?")) {
			LinkEditData data = new LinkEditData();
			data.setBillID(pk_task_h);
			//TODO 跳转先不用
			//SFClientUtil.openNodeLinkedMaintain(FUN_CODE, data);
		}
	}

	private void deleteOldTask(IBill[] bills) throws BusinessException {
		if (bills != null && bills.length >= 0) {
			List<AggCommissionHVO> deleteList = new ArrayList();
			for (IBill bill : bills) {
				if (bill != null && bill instanceof AggCommissionHVO&& bill.getParent() != null) {
					deleteList.add(((AggCommissionHVO) bill));
				}
			}
			//删除就的任务单
			ITaskMaintain ITaskMaintain = (ITaskMaintain) NCLocator.getInstance().lookup(ITaskMaintain.class);
			ITaskMaintain.deleteOldList(deleteList);
		}

	}

	/**
	 * 创建新的任务单,当有多个委托单时,返回null,当只有一个任务时,返回主键
	 * 
	 * @param parentVO
	 * @return
	 * @throws BusinessException
	 */
	private String createNewTask(IBill[] bills) throws BusinessException {
		if (bills == null || bills.length <= 0) {
			return null;
		}
		String rt = null;
		for (IBill bill : bills) {
			CommissionHVO commissionHVO = null;
			if (bill != null && bill.getParent() != null) {
				try {
					commissionHVO = (CommissionHVO) (bill.getParent());
				} catch (Exception e) {
					commissionHVO = null;
				}
			} else {
				continue;
			}
			if (null == commissionHVO) {
				throw new BusinessException("未找到委托单主表消息!");
			}
			ITaskMaintain ITaskMaintain = (ITaskMaintain) NCLocator.getInstance().lookup(ITaskMaintain.class);
			AggTaskHVO newVO = new AggTaskHVO();
			TaskHVO parentVO = new TaskHVO();
			
			parentVO.setBillno(commissionHVO.getBillno());
			parentVO.setPk_commission_h(commissionHVO.getPk_commission_h());
			parentVO.setPk_group(commissionHVO.getPk_group());
			parentVO.setPk_org(commissionHVO.getPk_org());
			parentVO.setPk_org_v(commissionHVO.getPk_org_v());
			parentVO.setApprovestatus(-1);

			newVO.setParent(parentVO);
			AggTaskHVO[] newVOs = { newVO };
			AggTaskHVO[] results = ITaskMaintain.insert(newVOs);
			if (null == results || results.length <= 0 || null == results[0] || results[0].getPrimaryKey() == null) {
				throw new BusinessException("创建失败,请检查数据库连接!");
			}
			rt = results[0].getPrimaryKey();
		}
		if (bills.length == 1) {
			return rt;
		} else {
			return null;
		}
	}

	protected void showSuccessInfo() {
		ShowStatusBarMsgUtil.showStatusBarMsg(IShowMsgConstant.getSaveSuccessInfo(), getMainGrandModel().getMainModel()
				.getContext());
		// // 将消息栏字段隐藏标志位复位
		// if (getExceptionHandler() instanceof DefaultExceptionHanler) {
		// ((DefaultExceptionHanler)
		// getExceptionHandler()).setAutoClearError(true);
		// }
	}

	/**
	 * 此方法在调用模型的add或update调用。用来对从编辑器中取出的value对象进行校验。
	 * 
	 * @param value
	 */
	protected void validate(Object value) {
		if (validationService != null) {
			try {
				validationService.validate(value);
			} catch (ValidationException e) {
				throw new BusinessExceptionAdapter(e);
			}
		}
	}
	private void changeTemplet(String typeName,BillCardPanel billCardPanel){

		String[] templates = CommissionShowTemplate.getTemplateByName(typeName);
		String[] allTemplateFields = CommissionShowTemplate.getTemplateWithAllField();
		Set<String> templatesSet = new HashSet();
		
		//先把模板字段设为null,如果是模板之外的,不清,反正是全部显示
		//清空时,不清空此模板包含的字段
		if(templates!=null && templates.length > 0){
			for(String tmp : templates){
				templatesSet.add(tmp);
			}
			for(String temp : allTemplateFields){
				if(!templatesSet.contains(temp)){
					billCardPanel.getHeadItem(temp).setValue(null);
				}
				
			}
		}
		
		billCardPanel.hideHeadItem(allTemplateFields);
		if(templates == null){
			templates = allTemplateFields;
		}
		billCardPanel.showHeadItem(templates);
		
	
	}
	public MainGrandModel getMainGrandModel() {
		return mainGrandModel;
	}

	public void setMainGrandModel(MainGrandModel mainGrandModel) {
		this.mainGrandModel = mainGrandModel;
	}

	public IValidationService getValidationService() {
		return validationService;
	}

	public void setValidationService(IValidationService validationService) {
		this.validationService = validationService;
	}
}
