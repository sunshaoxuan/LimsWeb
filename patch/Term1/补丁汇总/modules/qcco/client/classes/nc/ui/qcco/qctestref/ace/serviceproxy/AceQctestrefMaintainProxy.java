package nc.ui.qcco.qctestref.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.ui.pubapp.pub.task.ISingleBillService;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.pubapp.uif2app.actions.IDataOperationService;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.uif2.components.pagination.IPaginationQueryService;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.qcco.qctestref.AggTestrefHVO;
import nc.itf.qcco.IQctestrefMaintain;

/**
 * ʾ�����ݵĲ�������
 * 
 * @author author
 * @version tempProject version
 */
public class AceQctestrefMaintainProxy implements IDataOperationService,
		IQueryService ,ISingleBillService<AggTestrefHVO>{
	@Override
	public IBill[] insert(IBill[] value) throws BusinessException {
		IQctestrefMaintain operator = NCLocator.getInstance().lookup(
				IQctestrefMaintain.class);
		AggTestrefHVO[] vos = operator.insert((AggTestrefHVO[]) value);
		return vos;
	}

	@Override
	public IBill[] update(IBill[] value) throws BusinessException {
		IQctestrefMaintain operator = NCLocator.getInstance().lookup(
				IQctestrefMaintain.class);
		AggTestrefHVO[] vos = operator.update((AggTestrefHVO[]) value);
		return vos;
	}

	@Override
	public IBill[] delete(IBill[] value) throws BusinessException {
		// Ŀǰ��ɾ�����������������������pubapp��֧�ִ����������ִ��ɾ������
		// ���ݵ�ɾ��ʵ����ʹ�õ��ǣ�ISingleBillService<AggSingleBill>��operateBill
		IQctestrefMaintain operator = NCLocator.getInstance().lookup(
				IQctestrefMaintain.class);
		operator.delete((AggTestrefHVO[]) value);
		return value;
	}
	
	@Override
	public AggTestrefHVO operateBill(AggTestrefHVO bill) throws Exception {
		IQctestrefMaintain operator = NCLocator.getInstance().lookup(
				IQctestrefMaintain.class);
		operator.delete(new AggTestrefHVO[] { bill });
		return bill;
	}

	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		IQctestrefMaintain query = NCLocator.getInstance().lookup(
				IQctestrefMaintain.class);
		return query.query(queryScheme);
	}

}
