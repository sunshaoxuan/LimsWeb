package nc.bs.qcco.qcanalysecondition.ace.bp;

import nc.bs.qcco.qcanalysecondition.plugin.bpplugin.QcanalyseconditionPluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.InsertBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.qcco.qcanalysecondition.AggAnalyseConditionHVO;

/**
 * ��׼��������BP
 */
public class AceQcanalyseconditionInsertBP {

	public AggAnalyseConditionHVO[] insert(AggAnalyseConditionHVO[] bills) {

		InsertBPTemplate<AggAnalyseConditionHVO> bp = new InsertBPTemplate<AggAnalyseConditionHVO>(
				QcanalyseconditionPluginPoint.INSERT);
		this.addBeforeRule(bp.getAroundProcesser());
		this.addAfterRule(bp.getAroundProcesser());
		return bp.insert(bills);

	}

	/**
	 * ���������
	 * 
	 * @param processor
	 */
	private void addAfterRule(AroundProcesser<AggAnalyseConditionHVO> processor) {
		// TODO ���������
		IRule<AggAnalyseConditionHVO> rule = null;
	}

	/**
	 * ����ǰ����
	 * 
	 * @param processor
	 */
	private void addBeforeRule(AroundProcesser<AggAnalyseConditionHVO> processer) {
		// TODO ����ǰ����
		IRule<AggAnalyseConditionHVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillInsertDataRule();
		processer.addBeforeRule(rule);
	}
}
