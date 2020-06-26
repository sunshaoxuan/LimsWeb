package nc.ui.qcco.task.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pubapp.uif2app.actions.pflow.CommitScriptAction;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.qcco.task.AggTaskHVO;

public class TaskCommitScriptAction extends CommitScriptAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5233378459958513820L;

	@Override
	public void doAction(ActionEvent e) throws Exception {
		if(!canCommit()){
			throw new BusinessException("不能提交暂存态的单据!");
		}
		super.doAction(e);
	}
	/**
	 * 暂存态不能提交
	 * @return
	 */
	private boolean canCommit(){
		AggTaskHVO obj = (AggTaskHVO)getModel().getSelectedData();
		if(null == obj){
			return false;
		}
		IUAPQueryBS bs = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		try {
			Integer billstatds = (Integer)bs.executeQuery("select billstatus from qc_task_h where pk_task_h = '"+obj.getPrimaryKey()+"' and dr = 0", 
					new ColumnProcessor());
			if(billstatds==null || -1 == billstatds){
				return true;
			}
		} catch (BusinessException e) {
			Logger.error(e);
			return false;
		}
		return false;
	}
	@Override
	protected boolean isActionEnable() {
		if(!canCommit()){
			return false;
		}
		return super.isActionEnable();
	}
}
