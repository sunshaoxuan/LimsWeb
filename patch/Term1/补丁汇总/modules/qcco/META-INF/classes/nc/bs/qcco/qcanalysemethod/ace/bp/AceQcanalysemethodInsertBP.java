package nc.bs.qcco.qcanalysemethod.ace.bp;

import nc.bs.qcco.qcanalysemethod.plugin.bpplugin.QcanalysemethodPluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.InsertBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.qcco.analysemethod.AggAnalyseMethodHVO;

/**
 * ��׼��������BP
 */
public class AceQcanalysemethodInsertBP {

	public AggAnalyseMethodHVO[] insert(AggAnalyseMethodHVO[] bills) {

		InsertBPTemplate<AggAnalyseMethodHVO> bp = new InsertBPTemplate<AggAnalyseMethodHVO>(
				QcanalysemethodPluginPoint.INSERT);
		this.addBeforeRule(bp.getAroundProcesser());
		this.addAfterRule(bp.getAroundProcesser());
		return bp.insert(bills);

	}

	/**
	 * ���������
	 * 
	 * @param processor
	 */
	private void addAfterRule(AroundProcesser<AggAnalyseMethodHVO> processor) {
		// TODO ���������
		IRule<AggAnalyseMethodHVO> rule = null;
	}

	/**
	 * ����ǰ����
	 * 
	 * @param processor
	 */
	private void addBeforeRule(AroundProcesser<AggAnalyseMethodHVO> processer) {
		// TODO ����ǰ����
		IRule<AggAnalyseMethodHVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillInsertDataRule();
		processer.addBeforeRule(rule);
	}
}
