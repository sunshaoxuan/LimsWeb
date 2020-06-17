package nc.bs.qcco.qcanalysecondition.ace.bp;

import nc.bs.qcco.qcanalysecondition.plugin.bpplugin.QcanalyseconditionPluginPoint;
import nc.vo.qcco.qcanalysecondition.AggAnalyseConditionHVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * ��׼����ɾ��BP
 */
public class AceQcanalyseconditionDeleteBP {

	public void delete(AggAnalyseConditionHVO[] bills) {

		DeleteBPTemplate<AggAnalyseConditionHVO> bp = new DeleteBPTemplate<AggAnalyseConditionHVO>(
				QcanalyseconditionPluginPoint.DELETE);
		// ����ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ����ִ�к�ҵ�����
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<AggAnalyseConditionHVO> processer) {
		// TODO ǰ����
		IRule<AggAnalyseConditionHVO> rule = null;
		//rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * ɾ����ҵ�����
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggAnalyseConditionHVO> processer) {
		// TODO �����

	}
}
