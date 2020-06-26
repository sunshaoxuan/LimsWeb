package nc.impl.pub.ace;

import nc.bs.qcco.qctestref.ace.bp.AceQctestrefInsertBP;
import nc.bs.qcco.qctestref.ace.bp.AceQctestrefUpdateBP;
import nc.bs.qcco.qctestref.ace.bp.AceQctestrefDeleteBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.qcco.qctestref.AggTestrefHVO;

public abstract class AceQctestrefPubServiceImpl {
	// ����
	public AggTestrefHVO[] pubinsertBills(AggTestrefHVO[] vos)
			throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<AggTestrefHVO> transferTool = new BillTransferTool<AggTestrefHVO>(
					vos);
			AggTestrefHVO[] mergedVO = transferTool.getClientFullInfoBill();

			// ����BP
			AceQctestrefInsertBP action = new AceQctestrefInsertBP();
			AggTestrefHVO[] retvos = action.insert(mergedVO);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(AggTestrefHVO[] vos) throws BusinessException {
		try {
			// ���� �Ƚ�ts
			BillTransferTool<AggTestrefHVO> transferTool = new BillTransferTool<AggTestrefHVO>(
					vos);
			AggTestrefHVO[] fullBills = transferTool.getClientFullInfoBill();
			AceQctestrefDeleteBP deleteBP = new AceQctestrefDeleteBP();
			deleteBP.delete(fullBills);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public AggTestrefHVO[] pubupdateBills(AggTestrefHVO[] vos)
			throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<AggTestrefHVO> transTool = new BillTransferTool<AggTestrefHVO>(
					vos);
			// ��ȫǰ̨VO
			AggTestrefHVO[] fullBills = transTool.getClientFullInfoBill();
			// ����޸�ǰvo
			AggTestrefHVO[] originBills = transTool.getOriginBills();
			// ����BP
			AceQctestrefUpdateBP bp = new AceQctestrefUpdateBP();
			AggTestrefHVO[] retBills = bp.update(fullBills, originBills);
			// ���췵������
			retBills = transTool.getBillForToClient(retBills);
			return retBills;
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggTestrefHVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggTestrefHVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggTestrefHVO> query = new BillLazyQuery<AggTestrefHVO>(
					AggTestrefHVO.class);
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