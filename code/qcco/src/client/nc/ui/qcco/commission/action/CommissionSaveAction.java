package nc.ui.qcco.commission.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Vector;

import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.bs.uif2.BusinessExceptionAdapter;
import nc.bs.uif2.IActionCode;
import nc.bs.uif2.validation.IValidationService;
import nc.bs.uif2.validation.ValidationException;
import nc.itf.qcco.ITaskMaintain;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.bd.ref.AbstractRefModel;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.linkoperate.LinkEditData;
import nc.ui.pubapp.uif2app.actions.DifferentVOSaveAction;
import nc.ui.pubapp.uif2app.components.grand.CardGrandPanelComposite;
import nc.ui.pubapp.uif2app.components.grand.model.MainGrandModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.qcco.commission.ace.handler.CommissionShowTemplate;
import nc.ui.qcco.commission.model.MainSubBillModel;
import nc.ui.qcco.commission.refmodel.CustomerTypeRefModel;
import nc.ui.qcco.commission.refmodel.ProductAuthTypeRefModel;
import nc.ui.qcco.commission.refmodel.ProductPropertyRefModel;
import nc.ui.qcco.commission.refmodel.RatainHandleRefModel;
import nc.ui.qcco.commission.refmodel.SafeTypeRefModel;
import nc.ui.qcco.commission.refmodel.TestRequirementRefModel;
import nc.ui.qcco.commission.refmodel.TestTypeRefModel;
import nc.ui.uap.sf.SFClientUtil;
import nc.ui.uif2.IShowMsgConstant;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.UIState;
import nc.ui.uif2.actions.ActionInitializer;
import nc.util.mmf.framework.gc.GCClientBillCombinServer;
import nc.util.mmf.framework.gc.GCClientBillToServer;
import nc.util.mmf.framework.gc.GCPseudoColUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.qcco.commission.AggCommissionHVO;
import nc.vo.qcco.commission.CommissionCVO;
import nc.vo.qcco.commission.CommissionHVO;
import nc.vo.qcco.task.AggTaskHVO;
import nc.vo.qcco.task.TaskHVO;

import org.apache.commons.lang.StringUtils;

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
			// 变更态时收集变化项目
			ISuperVO[] originVos = agghvo.getChildren(CommissionCVO.class);
			List<CommissionCVO> allVos = new ArrayList<CommissionCVO>();
			if (originVos != null && originVos.length > 0) {
				for (ISuperVO ovo : originVos) {
					ovo.setAttributeValue("pk_commission_h", agghvo.getPrimaryKey());
					ovo.setStatus(VOStatus.UNCHANGED);
					allVos.add((CommissionCVO) ovo);
				}
			}

			if (((MainSubBillModel) this.getModel()).isChangeStatus()) {
				collectCVOs(agghvo, allVos);
			}
			//
			// set 修改人
			if (null != agghvo && agghvo.getParentVO() != null) {
				String pk_user = billFormEditor.getModel().getContext().getPk_loginUser();
				agghvo.getParentVO().setModifier(pk_user);
			}
			if (allVos != null && allVos.size() > 0) {
				agghvo.setChildrenVO(allVos.toArray(new CommissionCVO[0]));
			}
			doEditSave(agghvo);
			this.getMainGrandModel().clearBufferData();
		}
		String typeName = loadTemplet(agghvo);
		if (null != typeName) {
			changeTemplet(typeName, this.billFormEditor.getBillCardPanel());
		}

		// 由变更状态返回时设置状态
		if (((MainSubBillModel) this.getModel()).isChangeStatus()) {
			((MainSubBillModel) this.getModel()).setChangeStatus(false);
			((MainSubBillModel) this.getModel()).resetBillFormEnableState();
		}
		//

		billForm.showMeUp();
		showSuccessInfo();
	}

	private void collectCVOs(AggCommissionHVO agghvo, List<CommissionCVO> allVos) {
		AbstractRefModel refModel = null;
		Vector matchValue = null;
		Map<String, String> newValue = new HashMap<String, String>();
		int row = allVos.size() + 1;
		// testaim
		newValue.put("测试目的", (String) agghvo.getParentVO().getTestaim());
		// progressneed
		newValue.put("进度要求", (String) agghvo.getParentVO().getProgressneed());
		// sampledealtype
		refModel = new RatainHandleRefModel();
		matchValue = refModel.matchPkData(agghvo.getParentVO().getSampledealtype());
		newValue.put("检后样品处理", (String) (matchValue == null ? null : ((Vector) matchValue.get(0)).get(1)));
		// productproperty
		refModel = new ProductPropertyRefModel();
		matchValue = refModel.matchPkData(agghvo.getParentVO().getProductproperty());
		newValue.put("产品属性", (String) (matchValue == null ? null : ((Vector) matchValue.get(0)).get(1)));
		// customername
		newValue.put("客户名称", (String) agghvo.getParentVO().getCustomername());
		// customertype
		refModel = new CustomerTypeRefModel();
		matchValue = refModel.matchPkData(agghvo.getParentVO().getCustomertype());
		newValue.put("客户类型", (String) (matchValue == null ? null : ((Vector) matchValue.get(0)).get(1)));
		// testrequirement
		refModel = new TestRequirementRefModel();
		matchValue = refModel.matchPkData(agghvo.getParentVO().getTestrequirement());
		newValue.put("测试需求", (String) (matchValue == null ? null : ((Vector) matchValue.get(0)).get(1)));
		// checkingproperty
		refModel = new TestTypeRefModel();
		matchValue = refModel.matchPkData(agghvo.getParentVO().getCheckingproperty());
		newValue.put("检测性质", (String) (matchValue == null ? null : ((Vector) matchValue.get(0)).get(1)));
		// productline
		newValue.put("生产产线", (String) agghvo.getParentVO().getProductline());
		// batchnumber
		newValue.put("生产批量", (String) agghvo.getParentVO().getBatchnumber());
		// productdate
		newValue.put("生产日期", agghvo.getParentVO().getProductdate() == null ? "" : agghvo.getParentVO().getProductdate()
				.toString());
		// batchserial
		newValue.put("生产批号", (String) agghvo.getParentVO().getBatchserial());
		// identificationtype
		refModel = new ProductAuthTypeRefModel();
		matchValue = refModel.matchPkData(agghvo.getParentVO().getIdentificationtype());
		newValue.put("产品鉴定类型", (String) (matchValue == null ? null : ((Vector) matchValue.get(0)).get(1)));
		// certificationtype
		refModel = new SafeTypeRefModel();
		matchValue = refModel.matchPkData(agghvo.getParentVO().getCertificationtype());
		newValue.put("认证类型", (String) (matchValue == null ? null : ((Vector) matchValue.get(0)).get(1)));
		// itemnumber
		newValue.put("项目号", (String) agghvo.getParentVO().getItemnumber());
		allVos.addAll(getChangedVOs(((MainSubBillModel) this.getModel()).getOldValue(), newValue,
				agghvo.getPrimaryKey(), row));
	}

	private List<CommissionCVO> getChangedVOs(Map<String, String> oldValue, Map<String, String> newValue,
			String pk_commission_h, int row) {
		List<CommissionCVO> retVOs = new ArrayList<CommissionCVO>();
		if (oldValue != null && newValue != null && oldValue.size() > 0 && newValue.size() > 0) {
			for (Entry<String, String> value : oldValue.entrySet()) {
				if (newValue.containsKey(value.getKey())) {
					if (value.getValue() != null && newValue.get(value.getKey()) != null
							&& !StringUtils.equals(newValue.get(value.getKey()), value.getValue())) {
						CommissionCVO cvo = new CommissionCVO();
						cvo.setPk_commission_h(pk_commission_h);
						cvo.setRowno(String.valueOf(row));
						cvo.setItemname(value.getKey());
						cvo.setOldvalue(value.getValue());
						cvo.setNewvalue(newValue.get(value.getKey()));
						cvo.setStatus(VOStatus.NEW);
						cvo.setModifier(billFormEditor.getModel().getContext().getPk_loginUser());
						cvo.setModifiedtime(new UFDateTime());
						retVOs.add(cvo);
						row++;
					}
				}
			}
		}
		return retVOs;
	}

	private String loadTemplet(AggCommissionHVO aggvo) {
		String typeName = null;
		if (aggvo != null && aggvo.getParentVO() != null && aggvo.getParentVO().getPk_commissiontype() != null) {
			String pk_commissiontype = aggvo.getParentVO().getPk_commissiontype();
			if (pk_commissiontype != null) {
				IUAPQueryBS iUAPQueryBS = (IUAPQueryBS) NCLocator.getInstance().lookup(IUAPQueryBS.class.getName());

				try {
					typeName = (String) iUAPQueryBS.executeQuery(" select NAME "
							+ " from NC_PROJ_TYPE WHERE ISENABLE=1 " + " and PK_PROJ_TYPE = '" + pk_commissiontype
							+ "'", new ColumnProcessor());
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
		//复制保存也会到这里,此时先清除掉子表的主键
		deal4CopyAdd(lightVOs);
		afterUpdateVOs = getService().insert(lightVOs);
		String pk_task_h = createNewTask(afterUpdateVOs);
		if (pk_task_h != null
				&& MessageDialog.ID_YES == MessageDialog.showYesNoDlg(billFormEditor.getBillCardPanel(), "跳转!",
						"是否跳转到新的任务单?")) {
			LinkEditData data = new LinkEditData();
			data.setBillID(pk_task_h);
			// TODO 当已经打开节点时,可以调转到编辑态...
			SFClientUtil.openNodeLinkedMaintain(FUN_CODE, data);
			/* SFClientUtil.openNodeLinkedMaintain(FUN_CODE, data); */
		}

		new GCClientBillCombinServer<IBill>().combine(clientVOs, afterUpdateVOs);

		getModel().setUiState(UIState.NOT_EDIT);
		getMainGrandModel().directlyAdd(clientVOs[0]);
	}

	private void deal4CopyAdd(IBill[] bills) {
		for (IBill bill : bills) {
			if (bill != null && bill.getParent() != null) {
				try {
					AggCommissionHVO hvo = (AggCommissionHVO)bill;
					CommissionCopyActionProcessor.processBodyVO(hvo);
				} catch (Exception e) {
					Logger.error(e.getMessage());
				}
			} else {
				continue;
			}
			
		}
		
		
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
			deleteOldTask(lightVOs);
			afterUpdateVOs = getService().update(lightVOs);
			pk_task_h = createNewTask(afterUpdateVOs);
		} else {
			return;
		}
		new GCClientBillCombinServer<IBill>().combine(clientVOs, afterUpdateVOs);

		getModel().setUiState(UIState.NOT_EDIT);
		// getMainGrandModel().directlyUpdate(clientVOs[0]);
		List<AggCommissionHVO> clientVOsList = new ArrayList();
		for (IBill bill : clientVOs) {
			if (bill instanceof AggCommissionHVO) {
				clientVOsList.add((AggCommissionHVO) bill);
			}
		}
		getMainGrandModel().getMainModel().initModel(clientVOsList.toArray(new AggCommissionHVO[0]));
		if (pk_task_h != null
				&& MessageDialog.ID_YES == MessageDialog.showYesNoDlg(billFormEditor.getBillCardPanel(), "跳转",
						"是否跳转到新的任务单?")) {
			LinkEditData data = new LinkEditData();
			data.setBillID(pk_task_h);
			// TODO 当已经打开节点时,可以调转到编辑态...
			SFClientUtil.openNodeLinkedMaintain(FUN_CODE, data);
			/* SFClientUtil.openNodeLinkedMaintain(FUN_CODE, data); */
		}
	}

	private void deleteOldTask(IBill[] bills) throws BusinessException {
		if (bills != null && bills.length >= 0) {
			List<AggCommissionHVO> deleteList = new ArrayList();
			for (IBill bill : bills) {
				if (bill != null && bill instanceof AggCommissionHVO && bill.getParent() != null) {
					deleteList.add(((AggCommissionHVO) bill));
				}
			}
			// 删除就的任务单
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
			parentVO.setCreator(commissionHVO.getCreator());
			parentVO.setCreationtime(commissionHVO.getCreationtime());
			parentVO.setModifier(commissionHVO.getModifier());
			parentVO.setModifiedtime(commissionHVO.getModifiedtime());
			parentVO.setLastmaketime(commissionHVO.getLastmaketime());
			parentVO.setApprover(null);
			parentVO.setBillmaker(commissionHVO.getModifier());
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

	private void changeTemplet(String typeName, BillCardPanel billCardPanel) {

		String[] templates = CommissionShowTemplate.getTemplateByName(typeName);
		String[] allTemplateFields = CommissionShowTemplate.getTemplateWithAllField();
		Set<String> templatesSet = new HashSet();

		// 先把模板字段设为null,如果是模板之外的,不清,反正是全部显示
		// 清空时,不清空此模板包含的字段
		if (templates != null && templates.length > 0) {
			for (String tmp : templates) {
				templatesSet.add(tmp);
			}
			for (String temp : allTemplateFields) {
				if (!templatesSet.contains(temp)) {
					billCardPanel.getHeadItem(temp).setValue(null);
				}

			}
		}

		billCardPanel.hideHeadItem(allTemplateFields);
		if (templates == null) {
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
