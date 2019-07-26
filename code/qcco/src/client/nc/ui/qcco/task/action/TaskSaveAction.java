package nc.ui.qcco.task.action;

import java.awt.event.ActionEvent;

import nc.bs.uif2.BusinessExceptionAdapter;
import nc.bs.uif2.IActionCode;
import nc.bs.uif2.validation.IValidationService;
import nc.bs.uif2.validation.ValidationException;
import nc.ui.pubapp.uif2app.actions.DifferentVOSaveAction;
import nc.ui.pubapp.uif2app.actions.RefreshSingleAction;
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
import nc.vo.pub.ISuperVO;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.qcco.commission.AggCommissionHVO;
import nc.vo.qcco.task.AggTaskHVO;
import nc.vo.qcco.task.TaskBVO;
import nc.vo.qcco.task.TaskSVO;

public class TaskSaveAction extends DifferentVOSaveAction {
	/**
	 * SaveAction DifferentVOSaveAction
	 */
	private static final long serialVersionUID = -6804039169256642670L;
	private ShowUpableBillForm grandCard;// mainBillForm
	private MainGrandModel mainGrandModel;
	private CardGrandPanelComposite billForm;
	private ShowUpableBillForm billFormEditor;
	//private IDataOperationService service;
	private RefreshSingleAction refresh;

	public RefreshSingleAction getRefresh() {
		return refresh;
	}

	public void setRefresh(RefreshSingleAction refresh) {
		this.refresh = refresh;
	}

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

	public ShowUpableBillForm getGrandCard() {
		return grandCard;
	}

	public void setGrandCard(ShowUpableBillForm grandCard) {
		this.grandCard = grandCard;
	}

	IValidationService validationService;

	public TaskSaveAction() {
		super();
		ActionInitializer.initializeAction(this, IActionCode.SAVE);
	}

	// ע�⽫�����XXX��������
	@Override
	public void doAction(ActionEvent e) throws Exception {
		
		this.billFormEditor.getBillCardPanel().stopEditing();
		AggTaskHVO agghvo = (AggTaskHVO)this.getBillForm().getValue();
		AggTaskHVO origanVO= (AggTaskHVO)getModel().getSelectedData();
		if(null == agghvo){
			return;
		}
		if(origanVO != null && origanVO.getParentVO()!=null){
			agghvo.getParentVO().setCreator(origanVO.getParentVO().getCreator());
			agghvo.getParentVO().setCreationtime(origanVO.getParentVO().getCreationtime());
			agghvo.getParentVO().setBillmaker(origanVO.getParentVO().getBillmaker());
		}
		this.validate(agghvo);
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
		refresh.doAction(e);
		showSuccessInfo();
	}
	
	
	

	@Override
	protected void doAddSave(Object value) throws Exception {
		IBill[] clientVOs = { (IBill)value };


		GCClientBillToServer<IBill> tool = new GCClientBillToServer<IBill>();


	    IBill[] lightVOs = tool.constructInsert(clientVOs);

	    IBill[] afterUpdateVOs = null;



	    if (getService() == null) {
	      throw new BusinessException("service����Ϊ�ա�");
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
	      throw new BusinessException("service����Ϊ�ա�");
	    }
	    afterUpdateVOs = getService().update(lightVOs);


	    new GCClientBillCombinServer<IBill>().combine(clientVOs, afterUpdateVOs);

	    getModel().setUiState(UIState.NOT_EDIT);
	    getMainGrandModel().directlyUpdate(clientVOs[0]);
	}

	protected void showSuccessInfo() {
		ShowStatusBarMsgUtil.showStatusBarMsg(IShowMsgConstant.getSaveSuccessInfo(), getMainGrandModel().getMainModel().getContext());
//		// ����Ϣ���ֶ����ر�־λ��λ
//		if (getExceptionHandler() instanceof DefaultExceptionHanler) {
//			((DefaultExceptionHanler) getExceptionHandler()).setAutoClearError(true);
//		}
	}

	/**
	 * �˷����ڵ���ģ�͵�add��update���á������Դӱ༭����ȡ����value�������У�顣
	 * @param value
	 */
	protected void validate(Object value) { 
		if(validationService!=null)
		{
			try {
				validationService.validate(value);
				validateGrand(value);
			} catch (ValidationException e) {
				throw new BusinessExceptionAdapter(e);
			}
		}
	}
	

	private void validateGrand(Object value) throws BusinessExceptionAdapter{
		// ���Ƿ��ѡ��û���ģ����Ǳ��������˼�������ʱ���У��ֵ����Ϊ��
		if(value!=null && value instanceof AggTaskHVO){
			AggTaskHVO aggvo = (AggTaskHVO)value;
			if(aggvo.getChildren(TaskBVO.class)!=null){
				ISuperVO[] superVOs = aggvo.getChildren(TaskBVO.class);
				if(superVOs.length > 0){
					for(ISuperVO superVO : superVOs){
						TaskBVO bvo = (TaskBVO)superVO;
						if(bvo!=null && bvo.getPk_task_s()!=null && bvo.getPk_task_s().length > 0){
							TaskSVO[] svos =  bvo.getPk_task_s();
							for(TaskSVO svo : svos){
								if(svo!=null && (svo.getIsoptional()==null || !(svo.getIsoptional().booleanValue())) ){
									//������,�ı�ֵ�����ֵ����ͬʱΪ��
									if((svo.getTextvalue()==null||"".equals(svo.getTextvalue()))&&svo.getPk_refvalue()==null ){
										throw new BusinessExceptionAdapter(new BusinessException("����������:["+svo.getPk_testconditionitem()+"],ֵ����Ϊ��"));
									}
								}
							}
						}
					}
				}
			}
		}
		
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
