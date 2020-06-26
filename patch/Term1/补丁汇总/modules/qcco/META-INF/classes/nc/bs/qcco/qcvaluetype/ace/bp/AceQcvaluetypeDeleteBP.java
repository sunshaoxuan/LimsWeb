package nc.bs.qcco.qcvaluetype.ace.bp;

import nc.bs.qcco.qcvaluetype.plugin.bpplugin.QcvaluetypePluginPoint;
import nc.vo.qcco.qcvaluetype.AggValueTypeHVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * ��׼����ɾ��BP
 */
public class AceQcvaluetypeDeleteBP {

	public void delete(AggValueTypeHVO[] bills) {

		DeleteBPTemplate<AggValueTypeHVO> bp = new DeleteBPTemplate<AggValueTypeHVO>(
				QcvaluetypePluginPoint.DELETE);
		// ����ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ����ִ�к�ҵ�����
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<AggValueTypeHVO> processer) {
		// TODO ǰ����
		IRule<AggValueTypeHVO> rule = null;
		/*rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);*/
	}

	/**
	 * ɾ����ҵ�����
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggValueTypeHVO> processer) {
		// TODO �����

	}
}
