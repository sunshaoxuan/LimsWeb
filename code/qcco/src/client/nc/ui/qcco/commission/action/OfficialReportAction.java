package nc.ui.qcco.commission.action;

import java.awt.event.ActionEvent;

import nc.bs.dao.DAOException;
import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.itf.qcco.ICommissionMaintain;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.qcco.commission.ace.view.ConfirmDialog;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.qcco.commission.AggCommissionHVO;
import nc.vo.qcco.commission.CommissionHVO;

public class OfficialReportAction extends NCAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1L;

	public OfficialReportAction() {
		setBtnName("正式报告预览");
		setCode("officialReport");
	}
	private IUAPQueryBS bs;
	
	private IUAPQueryBS getBS(){
		if(bs==null){
			bs = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		}
		return bs;
	}
	protected AbstractAppModel model = null;

	public AbstractAppModel getModel() {
		return model;
	}

	public void setModel(AbstractAppModel model) {
		this.model = model;
		this.model.addAppEventListener(this);
	}

	@Override
	public void doAction(ActionEvent paramActionEvent) throws Exception {
		try {
			// 查询
			IUAPQueryBS iUAPQueryBS = (IUAPQueryBS) NCLocator.getInstance().lookup(IUAPQueryBS.class.getName());
			String url = (String) iUAPQueryBS.executeQuery(
					"select vdef1 from report_path where nc_report_name = '正式报告'", new ColumnProcessor());
			if (null == url) {
				url = "http://404";
			} else {
				AggCommissionHVO aggvo = (AggCommissionHVO) this.getModel().getSelectedData();
				url = url.replace("%REPORT_NAME%", aggvo.getParentVO().getBillno());
				url = url.replace("%REPORT_NO%", aggvo.getParentVO().getBillno());
			}

			Object[] value = (Object[]) ConfirmDialog.showInputDlg(this.getModel().getContext().getEntranceUI(),
					ConfirmDialog.CONFIRM_REJECT_PREVIEW, "正式报告预览", "请输入意见", "", 200, 0, ConfirmDialog.TEXT_STR, url);

			int rtnID = (Integer) value[0];
			String txtMessage = (String) value[1];

			if (rtnID == ConfirmDialog.ID_CONFIRM) {

			} else if (rtnID == ConfirmDialog.ID_REJECT) {
				AggCommissionHVO aggvo = (AggCommissionHVO) this.getModel().getSelectedData();
				try{
					confirtm(aggvo.getParentVO(),txtMessage);
					MessageDialog.showHintDlg(getModel().getContext().getEntranceUI(), "消息", "驳回成功!");
				}catch (Exception ex) {
					MessageDialog.showErrorDlg(getModel().getContext().getEntranceUI(), "错误", ex.getMessage());
				}
				
			}
		} catch (Exception e) {
			Logger.error(e.getCause());

		}
	}

	private void confirtm(CommissionHVO parentVO,String txtMessage) throws DAOException {
		NCLocator.getInstance().lookup(ICommissionMaintain.class).officialReject(parentVO,txtMessage);
	}

	protected boolean isActionEnable() {
		AbstractBill aggVO = (AbstractBill) this.getModel().getSelectedData();
		if (aggVO == null) {
			return false;
		}
		SuperVO hvo = (SuperVO) aggVO.getParentVO();
		if (hvo == null) {
			return false;
		}
		// 没回写的单据,不能使用此按钮
		try {
			Integer count = (Integer) getBS().executeQuery(
					"select count(*) from project where name = '" + aggVO.getParent().getAttributeValue("billno") + "'",
					new ColumnProcessor());
			if (count <= 0) {
				return false;
			}
		} catch (BusinessException e) {
			ExceptionUtils.wrappException(e);
		}
		return this.getModel().getUiState() == UIState.NOT_EDIT;
	}
}
