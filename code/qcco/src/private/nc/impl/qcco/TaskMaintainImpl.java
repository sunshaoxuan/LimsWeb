package nc.impl.qcco;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.hr.utils.InSQLCreator;
import nc.impl.pub.ace.AceTaskPubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.qcco.commission.AggCommissionHVO;
import nc.vo.qcco.commission.CommissionBVO;
import nc.vo.qcco.commission.CommissionRVO;
import nc.vo.qcco.task.AggTaskHVO;
import nc.vo.qcco.task.TaskBVO;
import nc.vo.qcco.task.TaskHVO;
import nc.vo.qcco.task.TaskRVO;
import nc.itf.qcco.ITaskMaintain;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;

public class TaskMaintainImpl extends AceTaskPubServiceImpl
		implements ITaskMaintain {
private BaseDAO dao = null;
	

	public BaseDAO getDao() {
		if(dao == null){
			dao = new BaseDAO();
		}
		return dao;
	}
	/*@Override
	public void delete(AggTaskHVO[] clientFullVOs,
			AggTaskHVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public AggTaskHVO[] insert(AggTaskHVO[] clientFullVOs,
			AggTaskHVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public AggTaskHVO[] update(AggTaskHVO[] clientFullVOs,
			AggTaskHVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}*/
	@Override
	public AggTaskHVO[] insert(AggTaskHVO[] vos) throws BusinessException {
		return super.pubinsertBills(vos,null);
	}

	@Override
	public void delete(AggTaskHVO[] vos) throws BusinessException {
		List<String> list = new ArrayList<String>();
		for(AggTaskHVO vo : vos){
			list.add((String) vo.getParent().getAttributeValue("pk_commission_h"));
		}
		InSQLCreator insql = new InSQLCreator();
		String commissionInSQL = insql.getInSQL(list.toArray(new String[0]));
		List<TaskHVO> lists = (List<TaskHVO>) this.getDao().retrieveByClause(TaskHVO.class, "pk_commission_h in("+commissionInSQL+")");

		super.pubdeleteBills(vos);
		
	}

	@Override
	public AggTaskHVO[] update(AggTaskHVO[] vos)
			throws BusinessException {
		return super.pubupdateBills(vos);
	}

	@Override
	public AggTaskHVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public AggTaskHVO[] save(AggTaskHVO[] clientFullVOs,
			AggTaskHVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggTaskHVO[] unsave(AggTaskHVO[] clientFullVOs,
			AggTaskHVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggTaskHVO[] approve(AggTaskHVO[] clientFullVOs,
			AggTaskHVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggTaskHVO[] unapprove(AggTaskHVO[] clientFullVOs,
			AggTaskHVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}
	@Override
	public void deleteOldList(List<AggCommissionHVO> deleteList) throws BusinessException {
		Set<String> commissionPkSet = new HashSet();
		if(deleteList!=null){
			for(AggCommissionHVO hvo:deleteList){
				commissionPkSet.add(hvo.getPrimaryKey());
			}
			InSQLCreator insql = new InSQLCreator();
	        String pkInSQL = insql.getInSQL(commissionPkSet.toArray(new String[0]));
	        String delHSql = " update qc_task_h set dr = 1 "
	        		+" where pk_commission_h in ("+pkInSQL+") ";
	        getDao().executeUpdate(delHSql);
	        
	        String delBSql = " UPDATE qc_task_b set dr = 1 where pk_task_h "
	        		+ " in ( select pk_task_h from qc_task_h "
	        		+" where pk_commission_h in ("+pkInSQL+") "
	        		+" )";
	        getDao().executeUpdate(delBSql);
	        
	        String delRSql = " UPDATE qc_task_r set dr = 1 where pk_task_b "
	        		+ " in ( select pk_task_b from qc_task_b where pk_task_h "
	        		+ " in ( select pk_task_h from qc_task_h "
	        		+" where pk_commission_h in ("+pkInSQL+") "
	        		+" ))";
	        getDao().executeUpdate(delRSql);
	        
	        String delSSql = " UPDATE qc_task_s set dr = 1 where pk_task_b "
	        		+ " in ( select pk_task_b from qc_task_b where pk_task_h "
	        		+ " in ( select pk_task_h from qc_task_h  "
	        		+" where pk_commission_h in ("+pkInSQL+") "
	        		+" ))";
	        getDao().executeUpdate(delSSql);
		}

	}
	/**
	 * ªÿ–¥limsœµÕ≥
	 */
	@Override
	public void writeBackLims(AggTaskHVO aggvo) throws BusinessException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void updateCommissionReference(String pk_commission_h,String old_pk_commission_h) throws BusinessException {
		String sql = "update qc_task_h set pk_commission_h = '"+pk_commission_h
				+"' where pk_commission_h = '"+old_pk_commission_h+"'";
		getDao().executeUpdate(sql);
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
}
