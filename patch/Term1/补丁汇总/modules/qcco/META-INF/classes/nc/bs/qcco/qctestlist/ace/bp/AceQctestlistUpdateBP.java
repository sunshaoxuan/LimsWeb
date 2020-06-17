package nc.bs.qcco.qctestlist.ace.bp;

import nc.bs.qcco.qctestlist.plugin.bpplugin.QctestlistPluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.qcco.qctestlist.AggTestlistHVO;

/**
 * �޸ı����BP
 * 
 */
public class AceQctestlistUpdateBP {

	public AggTestlistHVO[] update(AggTestlistHVO[] bills,
			AggTestlistHVO[] originBills) {
		// �����޸�ģ��
		UpdateBPTemplate<AggTestlistHVO> bp = new UpdateBPTemplate<AggTestlistHVO>(
				QctestlistPluginPoint.UPDATE);
		// ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ִ�к����
		this.addAfterRule(bp.getAroundProcesser());
		return bp.update(bills, originBills);
	}

	private void addAfterRule(CompareAroundProcesser<AggTestlistHVO> processer) {
		// TODO �����
		IRule<AggTestlistHVO> rule = null;

	}

	private void addBeforeRule(CompareAroundProcesser<AggTestlistHVO> processer) {
		// TODO ǰ����
		IRule<AggTestlistHVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillUpdateDataRule();
		processer.addBeforeRule(rule);
	}

}
