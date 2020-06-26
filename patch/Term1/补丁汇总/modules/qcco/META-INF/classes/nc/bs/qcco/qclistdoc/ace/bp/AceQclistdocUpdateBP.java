package nc.bs.qcco.qclistdoc.ace.bp;

import nc.bs.qcco.qclistdoc.plugin.bpplugin.QclistdocPluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.qcco.qclistdoc.AggListdocHVO;

/**
 * �޸ı����BP
 * 
 */
public class AceQclistdocUpdateBP {

	public AggListdocHVO[] update(AggListdocHVO[] bills,
			AggListdocHVO[] originBills) {
		// �����޸�ģ��
		UpdateBPTemplate<AggListdocHVO> bp = new UpdateBPTemplate<AggListdocHVO>(
				QclistdocPluginPoint.UPDATE);
		// ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ִ�к����
		this.addAfterRule(bp.getAroundProcesser());
		return bp.update(bills, originBills);
	}

	private void addAfterRule(CompareAroundProcesser<AggListdocHVO> processer) {
		// TODO �����
		IRule<AggListdocHVO> rule = null;

	}

	private void addBeforeRule(CompareAroundProcesser<AggListdocHVO> processer) {
		// TODO ǰ����
		IRule<AggListdocHVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillUpdateDataRule();
		processer.addBeforeRule(rule);
	}

}
