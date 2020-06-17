package nc.impl.pub.ace;

import nc.bs.qcco.qcanalysecondition.ace.bp.AceQcanalyseconditionInsertBP;
import nc.bs.qcco.qcanalysecondition.ace.bp.AceQcanalyseconditionUpdateBP;
import nc.bs.qcco.qcanalysecondition.ace.bp.AceQcanalyseconditionDeleteBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.qcco.qcanalysecondition.AggAnalyseConditionHVO;

public abstract class AceQcanalyseconditionPubServiceImpl {
	// ����
	public AggAnalyseConditionHVO[] pubinsertBills(AggAnalyseConditionHVO[] vos)
			throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<AggAnalyseConditionHVO> transferTool = new BillTransferTool<AggAnalyseConditionHVO>(
					vos);
			AggAnalyseConditionHVO[] mergedVO = transferTool.getClientFullInfoBill();

			// ����BP
			AceQcanalyseconditionInsertBP action = new AceQcanalyseconditionInsertBP();
			AggAnalyseConditionHVO[] retvos = action.insert(mergedVO);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(AggAnalyseConditionHVO[] vos) throws BusinessException {
		try {
			// ���� �Ƚ�ts
			BillTransferTool<AggAnalyseConditionHVO> transferTool = new BillTransferTool<AggAnalyseConditionHVO>(
					vos);
			AggAnalyseConditionHVO[] fullBills = transferTool.getClientFullInfoBill();
			AceQcanalyseconditionDeleteBP deleteBP = new AceQcanalyseconditionDeleteBP();
			deleteBP.delete(fullBills);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public AggAnalyseConditionHVO[] pubupdateBills(AggAnalyseConditionHVO[] vos)
			throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<AggAnalyseConditionHVO> transTool = new BillTransferTool<AggAnalyseConditionHVO>(
					vos);
			// ��ȫǰ̨VO
			AggAnalyseConditionHVO[] fullBills = transTool.getClientFullInfoBill();
			// ����޸�ǰvo
			AggAnalyseConditionHVO[] originBills = transTool.getOriginBills();
			// ����BP
			AceQcanalyseconditionUpdateBP bp = new AceQcanalyseconditionUpdateBP();
			AggAnalyseConditionHVO[] retBills = bp.update(fullBills, originBills);
			// ���췵������
			retBills = transTool.getBillForToClient(retBills);
			return retBills;
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggAnalyseConditionHVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggAnalyseConditionHVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggAnalyseConditionHVO> query = new BillLazyQuery<AggAnalyseConditionHVO>(
					AggAnalyseConditionHVO.class);
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