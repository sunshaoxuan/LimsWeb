package nc.bs.qcco.analysemethod.ace.bp;

import nc.bs.qcco.analysemethod.plugin.bpplugin.AnalysemethodPluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.qcco.analysemethod.AggAnalyseMethodHVO;

/**
 * �޸ı����BP
 * 
 */
public class AceAnalysemethodUpdateBP {

	public AggAnalyseMethodHVO[] update(AggAnalyseMethodHVO[] bills,
			AggAnalyseMethodHVO[] originBills) {
		// �����޸�ģ��
		UpdateBPTemplate<AggAnalyseMethodHVO> bp = new UpdateBPTemplate<AggAnalyseMethodHVO>(
				AnalysemethodPluginPoint.UPDATE);
		// ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ִ�к����
		this.addAfterRule(bp.getAroundProcesser());
		return bp.update(bills, originBills);
	}

	private void addAfterRule(CompareAroundProcesser<AggAnalyseMethodHVO> processer) {
		// TODO �����
		IRule<AggAnalyseMethodHVO> rule = null;

	}

	private void addBeforeRule(CompareAroundProcesser<AggAnalyseMethodHVO> processer) {
		// TODO ǰ����
		IRule<AggAnalyseMethodHVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillUpdateDataRule();
		processer.addBeforeRule(rule);
	}

}
