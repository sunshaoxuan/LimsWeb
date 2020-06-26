package nc.impl.pub.ace;

import nc.bs.qcco.qcvaluetype.ace.bp.AceQcvaluetypeInsertBP;
import nc.bs.qcco.qcvaluetype.ace.bp.AceQcvaluetypeUpdateBP;
import nc.bs.qcco.qcvaluetype.ace.bp.AceQcvaluetypeDeleteBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.qcco.qcvaluetype.AggValueTypeHVO;

public abstract class AceQcvaluetypePubServiceImpl {
	// ����
	public AggValueTypeHVO[] pubinsertBills(AggValueTypeHVO[] vos)
			throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<AggValueTypeHVO> transferTool = new BillTransferTool<AggValueTypeHVO>(
					vos);
			AggValueTypeHVO[] mergedVO = transferTool.getClientFullInfoBill();

			// ����BP
			AceQcvaluetypeInsertBP action = new AceQcvaluetypeInsertBP();
			AggValueTypeHVO[] retvos = action.insert(mergedVO);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(AggValueTypeHVO[] vos) throws BusinessException {
		try {
			// ���� �Ƚ�ts
			BillTransferTool<AggValueTypeHVO> transferTool = new BillTransferTool<AggValueTypeHVO>(
					vos);
			AggValueTypeHVO[] fullBills = transferTool.getClientFullInfoBill();
			AceQcvaluetypeDeleteBP deleteBP = new AceQcvaluetypeDeleteBP();
			deleteBP.delete(fullBills);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public AggValueTypeHVO[] pubupdateBills(AggValueTypeHVO[] vos)
			throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<AggValueTypeHVO> transTool = new BillTransferTool<AggValueTypeHVO>(
					vos);
			// ��ȫǰ̨VO
			AggValueTypeHVO[] fullBills = transTool.getClientFullInfoBill();
			// ����޸�ǰvo
			AggValueTypeHVO[] originBills = transTool.getOriginBills();
			// ����BP
			AceQcvaluetypeUpdateBP bp = new AceQcvaluetypeUpdateBP();
			AggValueTypeHVO[] retBills = bp.update(fullBills, originBills);
			// ���췵������
			retBills = transTool.getBillForToClient(retBills);
			return retBills;
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggValueTypeHVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggValueTypeHVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggValueTypeHVO> query = new BillLazyQuery<AggValueTypeHVO>(
					AggValueTypeHVO.class);
			bills = query.query(queryScheme, null);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return bills;
	}

	/**
	 * ������ʵ�֣���ѯ֮ǰ��queryScheme���мӹ��������Լ����߼�
	 * 
	 * @param queryScheme
	 */
	protected void preQuery(IQueryScheme queryScheme) {
		// ��ѯ֮ǰ��queryScheme���мӹ��������Լ����߼�
	}

}