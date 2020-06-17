package nc.bs.qcco.qctestref.ace.bp;

import nc.bs.qcco.qctestref.plugin.bpplugin.QctestrefPluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.InsertBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.qcco.qctestref.AggTestrefHVO;

/**
 * ��׼��������BP
 */
public class AceQctestrefInsertBP {

	public AggTestrefHVO[] insert(AggTestrefHVO[] bills) {

		InsertBPTemplate<AggTestrefHVO> bp = new InsertBPTemplate<AggTestrefHVO>(
				QctestrefPluginPoint.INSERT);
		this.addBeforeRule(bp.getAroundProcesser());
		this.addAfterRule(bp.getAroundProcesser());
		return bp.insert(bills);

	}

	/**
	 * ���������
	 * 
	 * @param processor
	 */
	private void addAfterRule(AroundProcesser<AggTestrefHVO> processor) {
		// TODO ���������
		IRule<AggTestrefHVO> rule = null;
	}

	/**
	 * ����ǰ����
	 * 
	 * @param processor
	 */
	private void addBeforeRule(AroundProcesser<AggTestrefHVO> processer) {
		// TODO ����ǰ����
		IRule<AggTestrefHVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillInsertDataRule();
		processer.addBeforeRule(rule);
	}
}
