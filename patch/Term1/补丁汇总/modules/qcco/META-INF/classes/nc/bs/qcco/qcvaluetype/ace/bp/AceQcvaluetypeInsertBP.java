package nc.bs.qcco.qcvaluetype.ace.bp;

import nc.bs.qcco.qcvaluetype.plugin.bpplugin.QcvaluetypePluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.InsertBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.qcco.qcvaluetype.AggValueTypeHVO;

/**
 * ��׼��������BP
 */
public class AceQcvaluetypeInsertBP {

	public AggValueTypeHVO[] insert(AggValueTypeHVO[] bills) {

		InsertBPTemplate<AggValueTypeHVO> bp = new InsertBPTemplate<AggValueTypeHVO>(
				QcvaluetypePluginPoint.INSERT);
		this.addBeforeRule(bp.getAroundProcesser());
		this.addAfterRule(bp.getAroundProcesser());
		return bp.insert(bills);

	}

	/**
	 * ���������
	 * 
	 * @param processor
	 */
	private void addAfterRule(AroundProcesser<AggValueTypeHVO> processor) {
		// TODO ���������
		IRule<AggValueTypeHVO> rule = null;
	}

	/**
	 * ����ǰ����
	 * 
	 * @param processor
	 */
	private void addBeforeRule(AroundProcesser<AggValueTypeHVO> processer) {
		// TODO ����ǰ����
		IRule<AggValueTypeHVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillInsertDataRule();
		processer.addBeforeRule(rule);
	}
}
