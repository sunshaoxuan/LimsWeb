package nc.itf.qcco;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.qcco.commission.AggCommissionHVO;
import nc.vo.pub.BusinessException;

public interface IQccommissionMaintain {

	public AggCommissionHVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public AggCommissionHVO[] save(AggCommissionHVO[] clientFullVOs,
			AggCommissionHVO[] originBills) throws BusinessException;

	public AggCommissionHVO[] unsave(AggCommissionHVO[] clientFullVOs,
			AggCommissionHVO[] originBills) throws BusinessException;

	public AggCommissionHVO[] approve(AggCommissionHVO[] clientFullVOs,
			AggCommissionHVO[] originBills) throws BusinessException;

	public AggCommissionHVO[] unapprove(AggCommissionHVO[] clientFullVOs,
			AggCommissionHVO[] originBills) throws BusinessException;

	public AggCommissionHVO[] insert(AggCommissionHVO[] vos) throws BusinessException;

	public void delete(AggCommissionHVO[] aggCommissionHVOs) throws BusinessException;

	public AggCommissionHVO[] update(AggCommissionHVO[] vos) throws BusinessException;
}
