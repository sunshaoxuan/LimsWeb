package nc.bs.qcco.qcanalyseresult.ace.bp;

import nc.bs.qcco.qcanalyseresult.plugin.bpplugin.QcanalyseresultPluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.InsertBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.qcco.qcanalyseresult.AggAnalyseResultHVO;

/**
 * ��׼��������BP
 */
public class AceQcanalyseresultInsertBP {

	public AggAnalyseResultHVO[] insert(AggAnalyseResultHVO[] bills) {

		InsertBPTemplate<AggAnalyseResultHVO> bp = new InsertBPTemplate<AggAnalyseResultHVO>(
				QcanalyseresultPluginPoint.INSERT);
		this.addBeforeRule(bp.getAroundProcesser());
		this.addAfterRule(bp.getAroundProcesser());
		return bp.insert(bills);

	}

	/**
	 * ���������
	 * 
	 * @param processor
	 */
	private void addAfterRule(AroundProcesser<AggAnalyseResultHVO> processor) {
		// TODO ���������
		IRule<AggAnalyseResultHVO> rule = null;
	}

	/**
	 * ����ǰ����
	 * 
	 * @param processor
	 */
	private void addBeforeRule(AroundProcesser<AggAnalyseResultHVO> processer) {
		// TODO ����ǰ����
		IRule<AggAnalyseResultHVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillInsertDataRule();
		processer.addBeforeRule(rule);
	}
}
