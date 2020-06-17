package nc.ui.qcco.qclistdoc.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.ui.pubapp.pub.task.ISingleBillService;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.pubapp.uif2app.actions.IDataOperationService;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.uif2.components.pagination.IPaginationQueryService;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.qcco.qclistdoc.AggListdocHVO;
import nc.itf.qcco.IQclistdocMaintain;

/**
 * ʾ�����ݵĲ�������
 * 
 * @author author
 * @version tempProject version
 */
public class AceQclistdocMaintainProxy implements IDataOperationService,
		IQueryService ,ISingleBillService<AggListdocHVO>{
	@Override
	public IBill[] insert(IBill[] value) throws BusinessException {
		IQclistdocMaintain operator = NCLocator.getInstance().lookup(
				IQclistdocMaintain.class);
		AggListdocHVO[] vos = operator.insert((AggListdocHVO[]) value);
		return vos;
	}

	@Override
	public IBill[] update(IBill[] value) throws BusinessException {
		IQclistdocMaintain operator = NCLocator.getInstance().lookup(
				IQclistdocMaintain.class);
		AggListdocHVO[] vos = operator.update((AggListdocHVO[]) value);
		return vos;
	}

	@Override
	public IBill[] delete(IBill[] value) throws BusinessException {
		// Ŀǰ��ɾ�����������������������pubapp��֧�ִ����������ִ��ɾ������
		// ���ݵ�ɾ��ʵ����ʹ�õ��ǣ�ISingleBillService<AggSingleBill>��operateBill
		IQclistdocMaintain operator = NCLocator.getInstance().lookup(
				IQclistdocMaintain.class);
		operator.delete((AggListdocHVO[]) value);
		return value;
	}
	
	@Override
	public AggListdocHVO operateBill(AggListdocHVO bill) throws Exception {
		IQclistdocMaintain operator = NCLocator.getInstance().lookup(
				IQclistdocMaintain.class);
		operator.delete(new AggListdocHVO[] { bill });
		return bill;
	}

	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		IQclistdocMaintain query = NCLocator.getInstance().lookup(
				IQclistdocMaintain.class);
		return query.query(queryScheme);
	}

}
