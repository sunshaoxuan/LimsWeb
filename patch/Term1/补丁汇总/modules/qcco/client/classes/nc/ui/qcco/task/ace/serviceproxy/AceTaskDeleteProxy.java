package nc.ui.qcco.task.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.qcco.IQctaskMaintain;
import nc.ui.pubapp.pub.task.ISingleBillService;
import nc.vo.qcco.commission.AggCommissionHVO;
import nc.vo.qcco.task.AggTaskHVO;

public class AceTaskDeleteProxy implements ISingleBillService<AggTaskHVO>{
	@Override
	public AggTaskHVO operateBill(AggTaskHVO bill)
			throws Exception {
		IQctaskMaintain operator = NCLocator.getInstance().lookup(
				IQctaskMaintain.class);
		operator.delete(new AggTaskHVO[] { bill });
		return bill;
	}
}
