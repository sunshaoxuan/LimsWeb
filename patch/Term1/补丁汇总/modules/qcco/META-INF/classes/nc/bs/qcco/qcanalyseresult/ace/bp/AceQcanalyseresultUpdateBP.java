package nc.bs.qcco.qcanalyseresult.ace.bp;

import nc.bs.qcco.qcanalyseresult.plugin.bpplugin.QcanalyseresultPluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.qcco.qcanalyseresult.AggAnalyseResultHVO;

/**
 * �޸ı����BP
 * 
 */
public class AceQcanalyseresultUpdateBP {

	public AggAnalyseResultHVO[] update(AggAnalyseResultHVO[] bills,
			AggAnalyseResultHVO[] originBills) {
		// �����޸�ģ��
		UpdateBPTemplate<AggAnalyseResultHVO> bp = new UpdateBPTemplate<AggAnalyseResultHVO>(
				QcanalyseresultPluginPoint.UPDATE);
		// ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ִ�к����
		this.addAfterRule(bp.getAroundProcesser());
		return bp.update(bills, originBills);
	}

	private void addAfterRule(CompareAroundProcesser<AggAnalyseResultHVO> processer) {
		// TODO �����
		IRule<AggAnalyseResultHVO> rule = null;

	}

	private void addBeforeRule(CompareAroundProcesser<AggAnalyseResultHVO> processer) {
		// TODO ǰ����
		IRule<AggAnalyseResultHVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillUpdateDataRule();
		processer.addBeforeRule(rule);
	}

}
