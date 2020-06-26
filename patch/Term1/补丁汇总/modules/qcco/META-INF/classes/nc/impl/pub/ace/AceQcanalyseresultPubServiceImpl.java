package nc.impl.pub.ace;

import nc.bs.qcco.qcanalyseresult.ace.bp.AceQcanalyseresultInsertBP;
import nc.bs.qcco.qcanalyseresult.ace.bp.AceQcanalyseresultUpdateBP;
import nc.bs.qcco.qcanalyseresult.ace.bp.AceQcanalyseresultDeleteBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.qcco.qcanalyseresult.AggAnalyseResultHVO;

public abstract class AceQcanalyseresultPubServiceImpl {
	// ����
	public AggAnalyseResultHVO[] pubinsertBills(AggAnalyseResultHVO[] vos)
			throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<AggAnalyseResultHVO> transferTool = new BillTransferTool<AggAnalyseResultHVO>(
					vos);
			AggAnalyseResultHVO[] mergedVO = transferTool.getClientFullInfoBill();

			// ����BP
			AceQcanalyseresultInsertBP action = new AceQcanalyseresultInsertBP();
			AggAnalyseResultHVO[] retvos = action.insert(mergedVO);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(AggAnalyseResultHVO[] vos) throws BusinessException {
		try {
			// ���� �Ƚ�ts
			BillTransferTool<AggAnalyseResultHVO> transferTool = new BillTransferTool<AggAnalyseResultHVO>(
					vos);
			AggAnalyseResultHVO[] fullBills = transferTool.getClientFullInfoBill();
			AceQcanalyseresultDeleteBP deleteBP = new AceQcanalyseresultDeleteBP();
			deleteBP.delete(fullBills);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public AggAnalyseResultHVO[] pubupdateBills(AggAnalyseResultHVO[] vos)
			throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<AggAnalyseResultHVO> transTool = new BillTransferTool<AggAnalyseResultHVO>(
					vos);
			// ��ȫǰ̨VO
			AggAnalyseResultHVO[] fullBills = transTool.getClientFullInfoBill();
			// ����޸�ǰvo
			AggAnalyseResultHVO[] originBills = transTool.getOriginBills();
			// ����BP
			AceQcanalyseresultUpdateBP bp = new AceQcanalyseresultUpdateBP();
			AggAnalyseResultHVO[] retBills = bp.update(fullBills, originBills);
			// ���췵������
			retBills = transTool.getBillForToClient(retBills);
			return retBills;
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggAnalyseResultHVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggAnalyseResultHVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggAnalyseResultHVO> query = new BillLazyQuery<AggAnalyseResultHVO>(
					AggAnalyseResultHVO.class);
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