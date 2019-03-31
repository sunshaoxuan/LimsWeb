package nc.ui.qcco.task.action;

import java.awt.Event;
import java.awt.event.ActionEvent;

import nc.bs.uif2.BusinessExceptionAdapter;
import nc.bs.uif2.IActionCode;
import nc.bs.uif2.validation.IValidationService;
import nc.bs.uif2.validation.ValidationException;
import nc.ui.pubapp.uif2app.actions.DifferentVOSaveAction;
import nc.ui.pubapp.uif2app.components.grand.CardGrandPanelComposite;
import nc.ui.pubapp.uif2app.components.grand.model.MainGrandModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.IShowMsgConstant;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.UIState;
import nc.ui.uif2.actions.ActionInitializer;
import nc.util.mmpub.dpub.gc.GCClientBillCombinServer;
import nc.util.mmpub.dpub.gc.GCClientBillToServer;
import nc.util.mmpub.dpub.gc.GCPseudoColUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.qcco.task.AggTaskHVO;

import javax.swing.Action;
import javax.swing.KeyStroke;

public class TaskTemporarilySaveAction extends DifferentVOSaveAction{
	/**
	 * SaveAction DifferentVOSaveAction
	 */
	private static final long serialVersionUID = -6804039169256642670L;

	private MainGrandModel mainGrandModel;
	private CardGrandPanelComposite billForm;
	private ShowUpableBillForm billFormEditor;
	//private IDataOperationService service;

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

	public TaskTemporarilySaveAction() {
		super();
		String name = "暂存"
		/*@res "暂存"*/;
		setBtnName(name);
		setCode("TaskTemporarilySave");
		putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke('I', Event.CTRL_MASK));
		putValue(Action.SHORT_DESCRIPTION, name+"(Ctrl+I)");
	}

	// 注意将孙面板XXX属性设置
	@Override
	public void doAction(ActionEvent e) throws Exception {
		
		this.billFormEditor.getBillCardPanel().stopEditing();
		AggTaskHVO agghvo = (AggTaskHVO)this.getBillForm().getValue();
		if(null == agghvo){
			return;
		}
		//this.validate(agghvo);
		if (this.getModel().getUiState() == UIState.ADD) {
			//this.excuteInsert(agghvo);
			GCPseudoColUtil.getInstance().setPseudoColInfo(agghvo);
			doAddSave(agghvo);
			this.getMainGrandModel().clearBufferData();
		} else if (this.getModel().getUiState() == UIState.EDIT) {
			GCPseudoColUtil.getInstance().setPseudoColInfo(agghvo);
			doEditSave(agghvo);
			this.getMainGrandModel().clearBufferData();
		}

		showSuccessInfo();
	}
	
	
	

	@Override
	protected void doAddSave(Object value) throws Exception {
		IBill[] clientVOs = { (IBill)value };


		GCClientBillToServer<IBill> tool = new GCClientBillToServer<IBill>();


	    IBill[] lightVOs = tool.constructInsert(clientVOs);

	    IBill[] afterUpdateVOs = null;



	    if (getService() == null) {
	      throw new BusinessException("service不能为空");
	    }
	    afterUpdateVOs = getService().insert(lightVOs);


	    new GCClientBillCombinServer<IBill>().combine(clientVOs, afterUpdateVOs);

	    getModel().setUiState(UIState.NOT_EDIT);
	    getMainGrandModel().directlyAdd(clientVOs[0]);
	}

	@Override
	protected void doEditSave(Object value) throws Exception {
	    IBill[] clientVOs = { (IBill)value };



	    GCClientBillToServer tool = new GCClientBillToServer();

	    IBill[] oldVO = { (IBill)getModel().getSelectedData() };



	    IBill[] lightVOs = tool.construct(oldVO, clientVOs);

	    IBill[] afterUpdateVOs = null;



	    if (getService() == null) {
	      throw new BusinessException("service祥夔峈諾﹝");
	    }
	    afterUpdateVOs = getService().update(lightVOs);


	    new GCClientBillCombinServer<IBill>().combine(clientVOs, afterUpdateVOs);

	    getModel().setUiState(UIState.NOT_EDIT);
	    getMainGrandModel().directlyUpdate(clientVOs[0]);
	}

	protected void showSuccessInfo() {
		ShowStatusBarMsgUtil.showStatusBarMsg(IShowMsgConstant.getSaveSuccessInfo(), getMainGrandModel().getMainModel().getContext());
//		// 蔚秏洘戲趼僇笐紲梓祩弇葩弇
//		if (getExceptionHandler() instanceof DefaultExceptionHanler) {
//			((DefaultExceptionHanler) getExceptionHandler()).setAutoClearError(true);
//		}
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
