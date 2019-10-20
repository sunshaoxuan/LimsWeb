package nc.impl.qcco;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.logging.Logger;
import nc.hr.utils.InSQLCreator;
import nc.impl.pub.ace.AceTaskPubServiceImpl;
import nc.ui.pub.qcco.task.utils.WriteBackLimsUtils;
import nc.ui.pub.qcco.writeback.utils.mediator.WriteBackMediator;
import nc.ui.qcco.task.utils.FormulaUtilsBack;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.qcco.commission.AggCommissionHVO;
import nc.vo.qcco.commission.CommissionBVO;
import nc.vo.qcco.commission.CommissionRVO;
import nc.vo.qcco.task.AggTaskHVO;
import nc.vo.qcco.task.TaskBVO;
import nc.vo.qcco.task.TaskHVO;
import nc.vo.qcco.task.TaskRVO;
import nc.vo.qcco.task.TaskSVO;
import nc.itf.qcco.ITaskMaintain;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.jdbc.framework.processor.ResultSetProcessor;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;

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
		return calLastTime(super.pubinsertBills(vos,null));
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
		return calLastTime(super.pubupdateBills(vos));
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
		
		AggTaskHVO[] rtnVO = super.pubapprovebills(clientFullVOs, originBills);
		if(clientFullVOs!=null && clientFullVOs.length > 0){
			for(AggTaskHVO aggvo:clientFullVOs){
				if(aggvo!=null && aggvo.getParentVO()!=null && 1 == aggvo.getParentVO().getApprovestatus()){
					writeBackLims(aggvo);
				}
			}
		}
		return rtnVO;
	}

	@Override
	public AggTaskHVO[] unapprove(AggTaskHVO[] clientFullVOs,
			AggTaskHVO[] originBills) throws BusinessException {
		AggTaskHVO[] rtn = super.pubunapprovebills(clientFullVOs, originBills);
		
		if(clientFullVOs!=null && clientFullVOs.length > 0){
			for(AggTaskHVO aggvo:clientFullVOs){
				unWriteBackLims(aggvo);
			}
		}
		return rtn;
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
	 * 回写lims系统
	 */
	@Override
	public void writeBackLims(AggTaskHVO aggvo) throws BusinessException {
		if(null == aggvo || aggvo.getParentVO()==null || aggvo.getParentVO().getPk_commission_h()==null){
			throw new BusinessException("单据信息为空");
		}
		String pk_commission_h = aggvo.getParentVO().getPk_commission_h();
		//获取回写的sql
		List<String> insertSqls = new WriteBackMediator().getLIMSSQL(pk_commission_h);

		if(insertSqls!=null && insertSqls.size() > 0){
			for(String sql : insertSqls){
				Logger.error("LIMS SQL WRITE BACK 4 CHECK start:"+new UFDateTime().toStdString()+"--------------------------------");
				getDao().executeUpdate(sql);
				Logger.error("LIMS SQL WRITE BACK 4 CHECK end:"+new UFDateTime().toStdString()+"--------------------------------");
			}
		}
		
	}
	
	private void unWriteBackLims(AggTaskHVO aggvo) throws BusinessException {
		// 只有sample全部为U才可以取消审批
		String sql = "select count(*) from sample where project = '"+aggvo.getParentVO().getBillno()
				+"' and (status <> 'U' or status is null)";
		Integer num = (Integer) getDao().executeQuery(sql, new ColumnProcessor());
		if (num != null && num > 0) {
			throw new BusinessException("simple状态校验不通过,无法取消审批!");
		}
		
		
		String pk_commission_h = aggvo.getParentVO().getPk_commission_h();
		String unApproveSql = new WriteBackMediator().getLIMSCancelSQL(pk_commission_h);
		getDao().executeUpdate(unApproveSql);
	}
	
	@Override
	public void updateCommissionReference(String pk_commission_h,String old_pk_commission_h) throws BusinessException {
		 String sql = "update qc_task_h set pk_commission_h = '"+pk_commission_h
				+"' where pk_commission_h = '"+old_pk_commission_h+"'";
		getDao().executeUpdate(sql);
		
	}

	@Override
	public void updateBillStatus(Integer status,String pk_task_h) throws DAOException{
		if (null == status) {
			status = -1;
		}
		if (pk_task_h != null) {
			getDao().executeUpdate("update qc_task_h set billstatus = "+status+" where pk_task_h = '" + pk_task_h + "'");
		}
		
	}
	
	private AggTaskHVO[] calLastTime(AggTaskHVO[] aggvos) throws BusinessException{
		if(aggvos==null || aggvos.length <=0){
			return aggvos;
		}
		//需要计算的资料
		Set<String> pkSet = new HashSet<>();
		for(AggTaskHVO aggvo : aggvos){
			if(aggvo.getPrimaryKey()!=null){
				pkSet.add(aggvo.getPrimaryKey());
			}
		}
		InSQLCreator insql = new InSQLCreator();
		String pkInsql = insql.getInSQL(pkSet.toArray(new String[0]));
		String sql = " SELECT nvl(qtb.PK_TASK_H,''),nvl(qtb.standardclause,''),nvl(cv.COMPONENT,''),"
				+" nvl(c.SOURCE_CODE,''),nvl(cv.name,''),nvl(qts.TEXTVALUE,'') "
				+" FROM qc_task_s qts "
				+" inner join qc_task_b qtb on qtb.PK_TASK_B = qts.PK_TASK_b "
				+" left join qc_task_h qth on qth.pk_task_h = qtb.pk_task_h "
				+" INNER join calculation c on c.COMPONENT in ('duration','持续时间','Duration') and c.analysis =qtb.STANDARDCLAUSE "
				+" inner JOIN calc_variables cv ON (cv.COMPONENT IN ('duration', '持续时间', 'Duration') "
				+" AND cv.analysis =qtb.STANDARDCLAUSE and cv.ATTRIBUTE_1 = qts.PK_TESTCONDITIONITEM and cv.COMPONENT =c.COMPONENT ) "
				+" inner join qc_task_s qs on (qtb.PK_TASK_B = qs.pk_task_b and qts.dr = 0 and c.COMPONENT = qs.PK_TESTCONDITIONITEM) "
				+" WHERE qtb.PK_TASK_H IN("+pkInsql+") "
				+" AND qts.dr = 0 and qtb.dr = 0 and qth.dr = 0 ";
		@SuppressWarnings("unchecked")
		//map<key,map<公式参数名/key,公式参数值/公式>>   其中:key=pk_task_h::pk_task_b::持续时间项名称
		Map<String, Map<String,String>> rsMap = 
			(Map<String, Map<String,String>>) getDao().executeQuery(sql, new ResultSetProcessor() {

			private static final long serialVersionUID = -5612957278523735236L;
			Map<String, Map<String,String>> map = new HashMap<>();

			@Override
			public Object handleResultSet(ResultSet rs) throws SQLException {
				while(rs.next()){
					String key = rs.getString(1)+"::"+rs.getString(2)
							+"::"+rs.getString(3).replaceAll(" ", "");
					if(map.containsKey(key)){
						//参数
						map.get(key).put(rs.getString(5).replaceAll(" ", ""), rs.getString(6).replaceAll(" ", ""));
					}else{
						Map<String,String> tempMap = new HashMap<>();
						//公式
						tempMap.put(key, rs.getString(4).replaceAll(" ", ""));
						//参数
						tempMap.put(rs.getString(5).replaceAll(" ", ""), rs.getString(6).replaceAll(" ", ""));
						map.put(key,tempMap);
					}
				}
				return map;
			}
		});
		Set<String> updateSqlSet = new HashSet<>();
		//更新数据库和vo
		for(AggTaskHVO aggvo : aggvos){
			if(aggvo!=null&&aggvo.getChildren(TaskBVO.class)!=null
					&&aggvo.getChildren(TaskBVO.class).length > 0){
				ISuperVO[] bvos = aggvo.getChildren(TaskBVO.class);
				if(bvos!=null && bvos.length > 0){
					for(ISuperVO superVO : bvos){
						TaskBVO bvo = (TaskBVO)superVO;
						if(bvo!=null && bvo.getPk_task_s()!=null && bvo.getPk_task_s().length > 0){
							TaskSVO[] svos = bvo.getPk_task_s();
							for(TaskSVO svo : svos){
								if(svo!=null && svo.getPk_testconditionitem()!=null){
									String key = aggvo.getPrimaryKey()+"::" + bvo.getStandardclause()+"::"
											+(svo.getPk_testconditionitem()==null?"":
												svo.getPk_testconditionitem().replaceAll(" ", ""));
									if(rsMap.containsKey(key)){
										//计算持续时间
										double calResult = 0;
										try{
											calResult = FormulaUtilsBack.calFormula(key,rsMap.get(key));
										}catch(Exception e){
											calResult = 0.0d;
											Logger.info(e);
										}
										svo.setTextvalue(String.valueOf(calResult));
										String updateSql = "update qc_task_s set textvalue = '"+String.valueOf(calResult)
												+"' where pk_task_s = '"+svo.getPk_task_s()+"'";
										updateSqlSet.add(updateSql);
									}
								}
							}
						}
					}
				}
			}
		}
		if(updateSqlSet.size() > 0){
			for(String sqlLastTime : updateSqlSet){
				getDao().executeUpdate(sqlLastTime);
			}
		}
		return aggvos;
	}
	
	
	
	
	
	
	
	
	
	
	
}
