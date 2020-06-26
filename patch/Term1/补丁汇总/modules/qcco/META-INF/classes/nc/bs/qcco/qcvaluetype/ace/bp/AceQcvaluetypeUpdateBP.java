package nc.bs.qcco.qcvaluetype.ace.bp;

import nc.bs.qcco.qcvaluetype.plugin.bpplugin.QcvaluetypePluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.qcco.qcvaluetype.AggValueTypeHVO;

/**
 * �޸ı����BP
 * 
 */
public class AceQcvaluetypeUpdateBP {

	public AggValueTypeHVO[] update(AggValueTypeHVO[] bills,
			AggValueTypeHVO[] originBills) {
		// �����޸�ģ��
		UpdateBPTemplate<AggValueTypeHVO> bp = new UpdateBPTemplate<AggValueTypeHVO>(
				QcvaluetypePluginPoint.UPDATE);
		// ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ִ�к����
		this.addAfterRule(bp.getAroundProcesser());
		return bp.update(bills, originBills);
	}

	private void addAfterRule(CompareAroundProcesser<AggValueTypeHVO> processer) {
		// TODO �����
		IRule<AggValueTypeHVO> rule = null;

	}

	private void addBeforeRule(CompareAroundProcesser<AggValueTypeHVO> processer) {
		// TODO ǰ����
		IRule<AggValueTypeHVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillUpdateDataRule();
		processer.addBeforeRule(rule);
	}

}
