package nc.bs.qcco.qctestlist.ace.bp;

import nc.bs.qcco.qctestlist.plugin.bpplugin.QctestlistPluginPoint;
import nc.vo.qcco.qctestlist.AggTestlistHVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * ��׼����ɾ��BP
 */
public class AceQctestlistDeleteBP {

	public void delete(AggTestlistHVO[] bills) {

		DeleteBPTemplate<AggTestlistHVO> bp = new DeleteBPTemplate<AggTestlistHVO>(
				QctestlistPluginPoint.DELETE);
		// ����ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ����ִ�к�ҵ�����
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<AggTestlistHVO> processer) {
		// TODO ǰ����
		IRule<AggTestlistHVO> rule = null;
		//rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * ɾ����ҵ�����
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggTestlistHVO> processer) {
		// TODO �����

	}
}
