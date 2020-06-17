package nc.impl.pub.ace;

import nc.bs.qcco.analysemethod.ace.bp.AceAnalysemethodInsertBP;
import nc.bs.qcco.analysemethod.ace.bp.AceAnalysemethodUpdateBP;
import nc.bs.qcco.analysemethod.ace.bp.AceAnalysemethodDeleteBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.qcco.analysemethod.AggAnalyseMethodHVO;

public abstract class AceAnalysemethodPubServiceImpl {
	// ����
	public AggAnalyseMethodHVO[] pubinsertBills(AggAnalyseMethodHVO[] vos)
			throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<AggAnalyseMethodHVO> transferTool = new BillTransferTool<AggAnalyseMethodHVO>(
					vos);
			AggAnalyseMethodHVO[] mergedVO = transferTool.getClientFullInfoBill();

			// ����BP
			AceAnalysemethodInsertBP action = new AceAnalysemethodInsertBP();
			AggAnalyseMethodHVO[] retvos = action.insert(mergedVO);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(AggAnalyseMethodHVO[] vos) throws BusinessException {
		try {
			// ���� �Ƚ�ts
			BillTransferTool<AggAnalyseMethodHVO> transferTool = new BillTransferTool<AggAnalyseMethodHVO>(
					vos);
			AggAnalyseMethodHVO[] fullBills = transferTool.getClientFullInfoBill();
			AceAnalysemethodDeleteBP deleteBP = new AceAnalysemethodDeleteBP();
			deleteBP.delete(fullBills);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public AggAnalyseMethodHVO[] pubupdateBills(AggAnalyseMethodHVO[] vos)
			throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<AggAnalyseMethodHVO> transTool = new BillTransferTool<AggAnalyseMethodHVO>(
					vos);
			// ��ȫǰ̨VO
			AggAnalyseMethodHVO[] fullBills = transTool.getClientFullInfoBill();
			// ����޸�ǰvo
			AggAnalyseMethodHVO[] originBills = transTool.getOriginBills();
			// ����BP
			AceAnalysemethodUpdateBP bp = new AceAnalysemethodUpdateBP();
			AggAnalyseMethodHVO[] retBills = bp.update(fullBills, originBills);
			// ���췵������
			retBills = transTool.getBillForToClient(retBills);
			return retBills;
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggAnalyseMethodHVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggAnalyseMethodHVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggAnalyseMethodHVO> query = new BillLazyQuery<AggAnalyseMethodHVO>(
					AggAnalyseMethodHVO.class);
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