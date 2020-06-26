package nc.bs.qcco.qclistdoc.ace.bp;

import nc.bs.qcco.qclistdoc.plugin.bpplugin.QclistdocPluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.InsertBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.qcco.qclistdoc.AggListdocHVO;

/**
 * ��׼��������BP
 */
public class AceQclistdocInsertBP {

	public AggListdocHVO[] insert(AggListdocHVO[] bills) {

		InsertBPTemplate<AggListdocHVO> bp = new InsertBPTemplate<AggListdocHVO>(
				QclistdocPluginPoint.INSERT);
		this.addBeforeRule(bp.getAroundProcesser());
		this.addAfterRule(bp.getAroundProcesser());
		return bp.insert(bills);

	}

	/**
	 * ���������
	 * 
	 * @param processor
	 */
	private void addAfterRule(AroundProcesser<AggListdocHVO> processor) {
		// TODO ���������
		IRule<AggListdocHVO> rule = null;
	}

	/**
	 * ����ǰ����
	 * 
	 * @param processor
	 */
	private void addBeforeRule(AroundProcesser<AggListdocHVO> processer) {
		// TODO ����ǰ����
		IRule<AggListdocHVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillInsertDataRule();
		processer.addBeforeRule(rule);
	}
}
