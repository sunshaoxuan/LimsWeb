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
	 * ɾ���ɵ�����(��ί�е��޸�ʱ,ɾ������)
	 * @param deleteList
	 */
	public void deleteOldList(List<AggCommissionHVO> deleteList)throws BusinessException;
	/**
	 * 
	 */
	public void writeBackLims(AggTaskHVO aggvo)throws BusinessException;
	/**
	 * ί�е������ʱ���仯����,��Ҫ���������ϵ�����
	 * @param pk_commission_h
	 * @param old_pk_commission_h
	 * @throws BusinessException 
	 */
	public void updateCommissionReference(String pk_commission_h,String old_pk_commission_h) throws BusinessException;
}
