package nc.impl.pub.ace;

import nc.bs.qcco.qclistdoc.ace.bp.AceQclistdocInsertBP;
import nc.bs.qcco.qclistdoc.ace.bp.AceQclistdocUpdateBP;
import nc.bs.qcco.qclistdoc.ace.bp.AceQclistdocDeleteBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.qcco.qclistdoc.AggListdocHVO;

public abstract class AceQclistdocPubServiceImpl {
	// ����
	public AggListdocHVO[] pubinsertBills(AggListdocHVO[] vos)
			throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<AggListdocHVO> transferTool = new BillTransferTool<AggListdocHVO>(
					vos);
			AggListdocHVO[] mergedVO = transferTool.getClientFullInfoBill();

			// ����BP
			AceQclistdocInsertBP action = new AceQclistdocInsertBP();
			AggListdocHVO[] retvos = action.insert(mergedVO);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(AggListdocHVO[] vos) throws BusinessException {
		try {
			// ���� �Ƚ�ts
			BillTransferTool<AggListdocHVO> transferTool = new BillTransferTool<AggListdocHVO>(
					vos);
			AggListdocHVO[] fullBills = transferTool.getClientFullInfoBill();
			AceQclistdocDeleteBP deleteBP = new AceQclistdocDeleteBP();
			deleteBP.delete(fullBills);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public AggListdocHVO[] pubupdateBills(AggListdocHVO[] vos)
			throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<AggListdocHVO> transTool = new BillTransferTool<AggListdocHVO>(
					vos);
			// ��ȫǰ̨VO
			AggListdocHVO[] fullBills = transTool.getClientFullInfoBill();
			// ����޸�ǰvo
			AggListdocHVO[] originBills = transTool.getOriginBills();
			// ����BP
			AceQclistdocUpdateBP bp = new AceQclistdocUpdateBP();
			AggListdocHVO[] retBills = bp.update(fullBills, originBills);
			// ���췵������
			retBills = transTool.getBillForToClient(retBills);
			return retBills;
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggListdocHVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggListdocHVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggListdocHVO> query = new BillLazyQuery<AggListdocHVO>(
					AggListdocHVO.class);
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