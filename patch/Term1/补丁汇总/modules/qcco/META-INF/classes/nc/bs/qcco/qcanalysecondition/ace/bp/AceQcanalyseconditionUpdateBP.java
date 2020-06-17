package nc.bs.qcco.qcanalysecondition.ace.bp;

import nc.bs.qcco.qcanalysecondition.plugin.bpplugin.QcanalyseconditionPluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.qcco.qcanalysecondition.AggAnalyseConditionHVO;

/**
 * �޸ı����BP
 * 
 */
public class AceQcanalyseconditionUpdateBP {

	public AggAnalyseConditionHVO[] update(AggAnalyseConditionHVO[] bills,
			AggAnalyseConditionHVO[] originBills) {
		// �����޸�ģ��
		UpdateBPTemplate<AggAnalyseConditionHVO> bp = new UpdateBPTemplate<AggAnalyseConditionHVO>(
				QcanalyseconditionPluginPoint.UPDATE);
		// ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ִ�к����
		this.addAfterRule(bp.getAroundProcesser());
		return bp.update(bills, originBills);
	}

	private void addAfterRule(CompareAroundProcesser<AggAnalyseConditionHVO> processer) {
		// TODO �����
		IRule<AggAnalyseConditionHVO> rule = null;

	}

	private void addBeforeRule(CompareAroundProcesser<AggAnalyseConditionHVO> processer) {
		// TODO ǰ����
		IRule<AggAnalyseConditionHVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillUpdateDataRule();
		processer.addBeforeRule(rule);
	}

}
