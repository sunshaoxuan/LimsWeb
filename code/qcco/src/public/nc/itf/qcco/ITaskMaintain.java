package nc.itf.qcco;

import java.util.List;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.qcco.commission.AggCommissionHVO;
import nc.vo.qcco.task.AggTaskHVO;
import nc.vo.pub.BusinessException;

public interface ITaskMaintain {

	/*public void delete(AggTaskHVO[] clientFullVOs,
			AggTaskHVO[] originBills) throws BusinessException;

	public AggTaskHVO[] insert(AggTaskHVO[] clientFullVOs,
			AggTaskHVO[] originBills) throws BusinessException;

	public AggTaskHVO[] update(AggTaskHVO[] clientFullVOs,
			AggTaskHVO[] originBills) throws BusinessException;*/
	public AggTaskHVO[] insert(AggTaskHVO[] vos) throws BusinessException;

	public void delete(AggTaskHVO[] vos) throws BusinessException;

	public AggTaskHVO[] update(AggTaskHVO[] vos) throws BusinessException;
	public AggTaskHVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public AggTaskHVO[] save(AggTaskHVO[] clientFullVOs,
			AggTaskHVO[] originBills) throws BusinessException;

	public AggTaskHVO[] unsave(AggTaskHVO[] clientFullVOs,
			AggTaskHVO[] originBills) throws BusinessException;

	public AggTaskHVO[] approve(AggTaskHVO[] clientFullVOs,
			AggTaskHVO[] originBills) throws BusinessException;

	public AggTaskHVO[] unapprove(AggTaskHVO[] clientFullVOs,
			AggTaskHVO[] originBills) throws BusinessException;
	/**
	 * 删除旧的任务单(当委托单修改时,删除任务单)
	 * @param deleteList
	 */
	public void deleteOldList(List<AggCommissionHVO> deleteList)throws BusinessException;
}
