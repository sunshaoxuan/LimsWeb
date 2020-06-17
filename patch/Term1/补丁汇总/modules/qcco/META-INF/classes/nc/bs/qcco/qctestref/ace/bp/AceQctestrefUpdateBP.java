package nc.bs.qcco.qctestref.ace.bp;

import nc.bs.qcco.qctestref.plugin.bpplugin.QctestrefPluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.qcco.qctestref.AggTestrefHVO;

/**
 * �޸ı����BP
 * 
 */
public class AceQctestrefUpdateBP {

	public AggTestrefHVO[] update(AggTestrefHVO[] bills,
			AggTestrefHVO[] originBills) {
		// �����޸�ģ��
		UpdateBPTemplate<AggTestrefHVO> bp = new UpdateBPTemplate<AggTestrefHVO>(
				QctestrefPluginPoint.UPDATE);
		// ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ִ�к����
		this.addAfterRule(bp.getAroundProcesser());
		return bp.update(bills, originBills);
	}

	private void addAfterRule(CompareAroundProcesser<AggTestrefHVO> processer) {
		// TODO �����
		IRule<AggTestrefHVO> rule = null;

	}

	private void addBeforeRule(CompareAroundProcesser<AggTestrefHVO> processer) {
		// TODO ǰ����
		IRule<AggTestrefHVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillUpdateDataRule();
		processer.addBeforeRule(rule);
	}

}
