package nc.bs.qcco.qctestref.ace.bp;

import nc.bs.qcco.qctestref.plugin.bpplugin.QctestrefPluginPoint;
import nc.vo.qcco.qctestref.AggTestrefHVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * ��׼����ɾ��BP
 */
public class AceQctestrefDeleteBP {

	public void delete(AggTestrefHVO[] bills) {

		DeleteBPTemplate<AggTestrefHVO> bp = new DeleteBPTemplate<AggTestrefHVO>(
				QctestrefPluginPoint.DELETE);
		// ����ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ����ִ�к�ҵ�����
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<AggTestrefHVO> processer) {
		// TODO ǰ����
		IRule<AggTestrefHVO> rule = null;
		//rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * ɾ����ҵ�����
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggTestrefHVO> processer) {
		// TODO �����

	}
}
