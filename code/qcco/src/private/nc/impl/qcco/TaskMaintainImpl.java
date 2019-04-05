package nc.impl.qcco;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nc.bs.dao.BaseDAO;
import nc.hr.utils.InSQLCreator;
import nc.impl.pub.ace.AceTaskPubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.qcco.commission.AggCommissionHVO;
import nc.vo.qcco.commission.CommissionBVO;
import nc.vo.qcco.commission.CommissionRVO;
import nc.vo.qcco.task.AggTaskHVO;
import nc.vo.qcco.task.TaskHVO;
import nc.itf.qcco.ITaskMaintain;
import nc.vo.pub.BusinessException;

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
		if(lists.size() > 0){
			for(AggTaskHVO vo : vos){
				CommissionBVO[] bvos = (CommissionBVO[])vo.getChildrenVO();
				for(TaskHVO hvo : lists){
					if(null != hvo.getAttributeValue("pk_commission_h") && vo.getParentVO().getAttributeValue("pk_commission_h") !=null && hvo.getAttributeValue("pk_commission_h").equals(vo.getParentVO().getAttributeValue("pk_commission_h"))){
						for(CommissionBVO bvo : bvos){
							bvo.setTs(hvo.getTs());
							CommissionRVO[] rvos = bvo.getPk_commission_r();
							if (null != rvos) {
								for(CommissionRVO rvo : rvos){
									rvo.setTs(hvo.getTs());
								}
							}
						}
					}
				}
				
			}
		}
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
	        String sql = "update qc_commission_r "
	        		+" set dr = 1 "
	        		+" where PK_COMMISSION_R in ( "
	        		+" select PK_COMMISSION_R from qc_commission_r r "
	        		+" left join qc_commission_b b on b.PK_COMMISSION_B = r.PK_COMMISSION_B "
	        		+" left join qc_commission_h h on h.PK_COMMISSION_H = h.PK_COMMISSION_H "
	        		+" where h.PK_COMMISSION_H in ("+pkInSQL+") "
	        		+" )";
	        getDao().executeUpdate(sql);
		}

	}

	
	
	
	
	
	
	
	
	
	
	
	
}
