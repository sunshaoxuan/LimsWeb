package nc.impl.pub.ace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.bs.framework.common.NCLocator;
import nc.bs.qcco.commission.ace.bp.AceCommissionDeleteBP;
import nc.bs.qcco.task.ace.bp.AceTaskInsertBP;
import nc.bs.qcco.task.ace.bp.AceTaskUpdateBP;
import nc.bs.qcco.task.ace.bp.AceTaskDeleteBP;
import nc.bs.qcco.task.ace.bp.AceTaskSendApproveBP;
import nc.bs.qcco.task.ace.bp.AceTaskUnSendApproveBP;
import nc.bs.qcco.task.ace.bp.AceTaskApproveBP;
import nc.bs.qcco.task.ace.bp.AceTaskUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.data.vo.VODelete;
import nc.impl.pubapp.pattern.data.vo.VOInsert;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.md.persist.framework.IMDPersistenceService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.qcco.task.AggTaskHVO;
import nc.vo.qcco.task.TaskRVO;
import nc.vo.qcco.task.TaskBVO;
import nc.vo.qcco.task.TaskSVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceTaskPubServiceImpl {
	IMDPersistenceService persist = NCLocator.getInstance().lookup(
			IMDPersistenceService.class);
	IMDPersistenceQueryService query = NCLocator.getInstance().lookup(
			IMDPersistenceQueryService.class);
	// ����
	public AggTaskHVO[] pubinsertBills(AggTaskHVO[] clientFullVOs,
			AggTaskHVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<AggTaskHVO> transferTool = new BillTransferTool<AggTaskHVO>(
					clientFullVOs);
			// ����BP
			AceTaskInsertBP action = new AceTaskInsertBP();
			AggTaskHVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			//return transferTool.getBillForToClient(retvos);
			return retvos;
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(AggTaskHVO[] vos) throws BusinessException {
		try { 
			BillTransferTool<AggTaskHVO> transferTool = new BillTransferTool<AggTaskHVO>(
					(AggTaskHVO[]) vos);
			AggTaskHVO[] fullBills = transferTool.getClientFullInfoBill();
			String[] tableCodes = fullBills[0].getTableCodes();
			for (String tableCode : tableCodes) {
				SuperVO[] originChildrens = (SuperVO[]) fullBills[0]
						.getTableVO(tableCode);
				// ע����ӶԶ���,map��Ҫ����
				Map<String, SuperVO[]> originGrandMap = new HashMap<String, SuperVO[]>();
				for (SuperVO childVO : originChildrens) {
					// ����ǰҳǩ�µĵ�ǰ�ӵ������ﶼ��ѯ������,����ֵ��originBills�е��
						String originChildPK = ((TaskBVO) childVO)
								.getPrimaryKey();
						Collection originRVOs = query.queryBillOfVOByCond(
								TaskRVO.class, "pk_task_b = '"
										+ originChildPK + "'", false);
						TaskRVO[] originGrandvos = (TaskRVO[]) originRVOs
								.toArray(new TaskRVO[originRVOs.size()]);
						((TaskBVO) childVO)
								.setPk_task_r(originGrandvos);
						for (int i = 0; originGrandvos != null
								&& i < originGrandvos.length; i++) {
							//originGrandvos[i].setDr(1);
							persist.deleteBill(originGrandvos[i]);
						}
						Collection originSVOs = query.queryBillOfVOByCond(
								TaskSVO.class, "pk_task_b = '"
										+ originChildPK + "'", false);
						TaskSVO[] soriginGrandvos = (TaskSVO[]) originSVOs
								.toArray(new TaskSVO[originSVOs.size()]);
						((TaskBVO) childVO).setPk_task_s(soriginGrandvos);
								
						for (int i = 0; soriginGrandvos != null
								&& i < soriginGrandvos.length; i++) {
							//soriginGrandvos[i].setDr(1);
							persist.deleteBill(soriginGrandvos[i]);
						}
//						this.deleteVO((List<ISuperVO>) originRVOs);
				}
			}

			AceTaskDeleteBP deleteBP = new AceTaskDeleteBP();
			deleteBP.delete(fullBills);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}

	}

	// �޸�
	public AggTaskHVO[] pubupdateBills(AggTaskHVO[] vos) throws BusinessException {
		try {
			BillTransferTool<AggTaskHVO> transTool = new BillTransferTool<AggTaskHVO>((AggTaskHVO[]) vos); 
			AggTaskHVO[] fullBills = transTool.getClientFullInfoBill();
			AggTaskHVO[] originBills = transTool.getOriginBills(); 
			
			// ��VO���޸�
			// nc.impl.pubapp.pattern.data.vo.template.UpdateBPTemplate

			AggTaskHVO[] aggvos = (AggTaskHVO[]) vos;
			String[] tableCodes = originBills[0].getTableCodes();
			
			
			Map<IVOMeta, List<ISuperVO>> fullGrandVOs = new HashMap<IVOMeta, List<ISuperVO>>();
			Map<IVOMeta, List<ISuperVO>> originGrandVOs = new HashMap<IVOMeta, List<ISuperVO>>();
			for (String tableCode : tableCodes) {
				SuperVO[] originChildrens = (SuperVO[]) originBills[0].getTableVO(tableCode);
				for (SuperVO childVO : originChildrens) { 
					// ����ǰҳǩ�µĵ�ǰ�ӵ������ﶼ��ѯ������,����ֵ��originBills�е��
					if (tableCode.equals("pk_task_b")) {
						String originChildPK = ((TaskBVO) childVO).getPrimaryKey();
						Collection originRVOs = query.queryBillOfVOByCond(
								TaskRVO.class, "pk_task_b = '"+ originChildPK + "'", false);
						Collection originSVOs = query.queryBillOfVOByCond(
								TaskSVO.class, "pk_task_b = '"+ originChildPK + "' and dr = 0", false);
						if(originRVOs != null && originRVOs.size()!=0){
							TaskRVO[] originGrandvos = (TaskRVO[]) originRVOs.toArray(new TaskRVO[originRVOs.size()]);
							((TaskBVO) childVO).setPk_task_r(originGrandvos);
							IVOMeta meta = ((SuperVO)(originRVOs.iterator().next())).getMetaData();
							if(originGrandVOs.get(meta) == null){
								originGrandVOs.put(meta, (List<ISuperVO>) originRVOs);
							}else{
								originGrandVOs.get(meta).addAll(originRVOs);
							}
						}
						if(originSVOs != null && originSVOs.size()!=0){
							TaskSVO[] originGrandvos = (TaskSVO[]) originSVOs.toArray(new TaskSVO[originSVOs.size()]);
							( (TaskBVO) childVO).setPk_task_s(originGrandvos);
							IVOMeta meta = ((SuperVO)(originSVOs.iterator().next())).getMetaData();
							if(originGrandVOs.get(meta) == null){
								originGrandVOs.put(meta, (List<ISuperVO>) originSVOs);
							}else{
								originGrandVOs.get(meta).addAll(originSVOs);
							}
						}
					} 
				}

				SuperVO[] currentChildrens = (SuperVO[]) aggvos[0].getTableVO(tableCode);
				for (SuperVO childVO : currentChildrens) {
					if (tableCode.equals("pk_task_b")) {
						ISuperVO[] currentGrandvos = (TaskRVO[]) ((TaskBVO) childVO).getPk_task_r();
						for (int i = 0; currentGrandvos != null && i < currentGrandvos.length; i++) {
							((TaskRVO) currentGrandvos[i]).setPk_task_b(childVO.getPrimaryKey());
						}
						if(currentGrandvos != null && currentGrandvos.length!=0){
							IVOMeta meta = ((SuperVO)(currentGrandvos[0])).getMetaData();
							List arrayList = new ArrayList(Arrays.asList(currentGrandvos));
							if(fullGrandVOs.get(meta) == null){
								fullGrandVOs.put(meta, arrayList);
							}else{
								fullGrandVOs.get(meta).addAll(arrayList);
							}
						}
						//��Ʒ��
						ISuperVO[] currentGrandsvos = (TaskSVO[]) ((TaskBVO) childVO).getPk_task_s();
						for (int i = 0; currentGrandsvos != null && i < currentGrandsvos.length; i++) {
							((TaskSVO) currentGrandsvos[i]).setPk_task_b((childVO.getPrimaryKey()));
						}
						if(currentGrandsvos != null && currentGrandsvos.length!=0){
							IVOMeta meta = ((SuperVO)(currentGrandsvos[0])).getMetaData();
							List arrayList = new ArrayList(Arrays.asList(currentGrandsvos));
							if(fullGrandVOs.get(meta) == null){
								fullGrandVOs.put(meta, arrayList);
							}else{
								fullGrandVOs.get(meta).addAll(arrayList);
							}
						}
					} 
				}
			}
			fullGrandVOs = this.getFullGrandVOs(fullGrandVOs, originGrandVOs);
			String pk_task_b = null;
			for (IVOMeta vo : fullGrandVOs.keySet()) {
				//fullGrandVOs.get(vo);
				for(ISuperVO voss:fullGrandVOs.get(vo)){
					pk_task_b = voss.getAttributeValue("pk_task_b")== null?null:String.valueOf(voss.getAttributeValue("pk_task_b"));
				}
			}
			
			if(pk_task_b != null){
				
				this.persistent(fullGrandVOs, originGrandVOs);
				
				AceTaskUpdateBP bp = new AceTaskUpdateBP();
				AggTaskHVO[] retBills = bp.update(fullBills, originBills);
			}else {
				
				AceTaskUpdateBP bp = new AceTaskUpdateBP();
				AggTaskHVO[] retBills = bp.update(fullBills, originBills);
				List<ISuperVO> lists = new ArrayList<>();
				List<ISuperVO> listr = new ArrayList<>();
				for (int i = 0; i < retBills.length; i++) {
					TaskBVO[] childrenVO = (TaskBVO[])(retBills[i].getChildrenVO());
					for (TaskBVO childrenVOs : childrenVO) {
						TaskSVO[] taskSVO =childrenVOs.getPk_task_s();
						if(null != taskSVO&& taskSVO.length>0){
							for (TaskSVO taskSVO2 : taskSVO) {
								taskSVO2.setPk_task_b(childrenVOs.getPk_task_b());
								lists.add(taskSVO2);
							}
						}
						TaskRVO[] taskRVO =childrenVOs.getPk_task_r();
						if(null != taskRVO&& taskRVO.length>0){
							for (TaskRVO taskRVO2 : taskRVO) {
								taskRVO2.setPk_task_b(childrenVOs.getPk_task_b());
								listr.add(taskRVO2);
							}
						}
					}
					
				}
				for (IVOMeta vo : fullGrandVOs.keySet()) {
					if(vo.getEntityName().equals("qcco.task_s")){
						fullGrandVOs.put(vo, lists);
					}else {
						fullGrandVOs.put(vo, listr);
					}
					
					
				}
				this.persistent(fullGrandVOs, originGrandVOs);
			}
			
			return aggvos;
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}
	// �ο� BillUpdate.persistent����
	private void persistent(Map<IVOMeta, List<ISuperVO>> fullGrandVOs,
			Map<IVOMeta, List<ISuperVO>> originGrandVOs) {
		Map<IVOMeta, List<ISuperVO>> originIndex = new HashMap<IVOMeta, List<ISuperVO>>();
		Map<IVOMeta, List<ISuperVO>> deleteIndex = new HashMap<IVOMeta, List<ISuperVO>>();
		Map<IVOMeta, List<ISuperVO>> newIndex = new HashMap<IVOMeta, List<ISuperVO>>();
		Map<IVOMeta, List<ISuperVO>> updateIndex = new HashMap<IVOMeta, List<ISuperVO>>();

		for (List<ISuperVO> list : fullGrandVOs.values()) {
			this.process(list, originGrandVOs, originIndex, deleteIndex,
					newIndex, updateIndex);
		}
		this.persistent(originIndex, deleteIndex, newIndex, updateIndex);

	}
	private void process(List<ISuperVO> list,
			Map<IVOMeta, List<ISuperVO>> originGrandVOs,
			Map<IVOMeta, List<ISuperVO>> originIndex,
			Map<IVOMeta, List<ISuperVO>> deleteIndex,
			Map<IVOMeta, List<ISuperVO>> newIndex,
			Map<IVOMeta, List<ISuperVO>> updateIndex) {
		for (ISuperVO vo : list) {
			this.process(vo, originGrandVOs, originIndex, deleteIndex,
					newIndex, updateIndex);
		}
	}
	private void process(ISuperVO vo,
			Map<IVOMeta, List<ISuperVO>> originGrandVOs,
			Map<IVOMeta, List<ISuperVO>> originIndex,
			Map<IVOMeta, List<ISuperVO>> deleteIndex,
			Map<IVOMeta, List<ISuperVO>> newIndex,
			Map<IVOMeta, List<ISuperVO>> updateIndex) {
		IVOMeta voMeta = vo.getMetaData();

		int status = vo.getStatus();
		if (status == VOStatus.NEW) {
			List<ISuperVO> list = this.get(voMeta, newIndex);
			list.add(vo);
		} else if (status == VOStatus.UPDATED) {
			List<ISuperVO> updateList = this.get(voMeta, updateIndex);
			updateList.add(vo);

			// ���ݵ�ǰvo��ȡԭʼvo
			ISuperVO originVO = this.get(originGrandVOs, vo.getMetaData(),
					vo.getPrimaryKey());
			List<ISuperVO> originList = this.get(voMeta, originIndex);
			originList.add(originVO);
		} else if (status == VOStatus.DELETED) {
			List<ISuperVO> list = this.get(voMeta, deleteIndex);
			list.add(vo);
		}
	}
	
	private void persistent(Map<IVOMeta, List<ISuperVO>> originIndex,
			Map<IVOMeta, List<ISuperVO>> deleteIndex,
			Map<IVOMeta, List<ISuperVO>> newIndex,
			Map<IVOMeta, List<ISuperVO>> updateIndex) {
		for (List<ISuperVO> list : deleteIndex.values()) {
			this.deleteVO(list);
		}
		for (List<ISuperVO> list : newIndex.values()) {
			this.insertVO(list);
		}
		for (Entry<IVOMeta, List<ISuperVO>> entry : updateIndex.entrySet()) {
			List<ISuperVO> list = entry.getValue();
			List<ISuperVO> originList = originIndex.get(entry.getKey());
			this.updateVO(list, originList);
		}
	}
	public AggTaskHVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggTaskHVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggTaskHVO> query = new BillLazyQuery<AggTaskHVO>(
					AggTaskHVO.class);
			bills = query.query(queryScheme, null);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return bills;
	}
//	�ο�BillIndex���������vo������Ԫ���ݿ��е�vo��pk�ڽ��洫����ֵ�в���
	private Map<IVOMeta, List<ISuperVO>> getFullGrandVOs(Map<IVOMeta, List<ISuperVO>> fullGrandVOs,
			Map<IVOMeta, List<ISuperVO>> originGrandVOs){
		if(originGrandVOs == null || originGrandVOs.size()==0)
			return fullGrandVOs;
//		
//		Ӧ����λ�ȡmeta��
//		���ܻ�������
//		
		for(Iterator itmeta = originGrandVOs.keySet().iterator(); itmeta.hasNext();){
			IVOMeta meta  = (IVOMeta) itmeta.next();
			List<ISuperVO> originvos = originGrandVOs.get(meta);
			if(originvos == null || originvos.size() == 0)
				continue;
			for(Iterator itvo = originvos.iterator(); itvo.hasNext();){
				ISuperVO originvo = (ISuperVO) itvo.next();
				String pk = originvo.getPrimaryKey();
				if(pk != null){
					ISuperVO vo = findGrandVOByPk(fullGrandVOs.get(meta),pk);
					if(vo == null){
						originvo.setStatus(VOStatus.DELETED);
						if(fullGrandVOs.get(meta) == null || fullGrandVOs.get(meta).size() == 0){
							List<ISuperVO> list = new ArrayList<ISuperVO>();
							list.add(originvo);
							fullGrandVOs.put(meta,list);
						}else
							fullGrandVOs.get(meta).add(originvo);
					}
				}
			}
		}
		return fullGrandVOs;
	}
	/**
	 * ������ʵ��Ԫ���ݡ���ʵ��������ȡʵ��
	 * 
	 * @param voMeta
	 *            ��ʵ��Ԫ����
	 * @param key
	 *            ��ʵ������
	 * @return ��ʵ��
	 */
	public ISuperVO get(Map<IVOMeta, List<ISuperVO>> originGrandVOs,
			IVOMeta voMeta, String key) {
		if (originGrandVOs.containsKey(voMeta)) {
			return findGrandVOByPk(originGrandVOs.get(voMeta), key);
		}
		return null;
	}

	private ISuperVO findGrandVOByPk(List<ISuperVO> originGrandVOs, String key) {
		if(originGrandVOs == null || originGrandVOs.size() == 0 )
			return null;
		Iterator it = originGrandVOs.iterator();
		while (it.hasNext()) {
			SuperVO grandvo = (SuperVO) it.next();
			if (grandvo.getPrimaryKey()!=null && grandvo.getPrimaryKey().equals(key)) {
				return grandvo;
			}
		}
		return null;
	}
	private void updateVO(List<ISuperVO> list, List<ISuperVO> originList) {
		VOUpdate<ISuperVO> bo = new VOUpdate<ISuperVO>();
		int length = list.size();
		if (length > 0) {
			ISuperVO[] vos = new ISuperVO[length];
			vos = list.toArray(vos);

			ISuperVO[] originVOs = new ISuperVO[length];
			originVOs = originList.toArray(originVOs);

			bo.update(vos, originVOs);
		}
	}

	private void deleteVO(List<ISuperVO> list) {
		VODelete<ISuperVO> bo = new VODelete<ISuperVO>();
		int length = list.size();
		if (length > 0) {
			ISuperVO[] vos = new ISuperVO[length];
			vos = list.toArray(vos);
			bo.delete(vos);
		}
	}

	private void insertVO(List<ISuperVO> list) {
		VOInsert<ISuperVO> bo = new VOInsert<ISuperVO>();
		int length = list.size();
		if (length > 0) {
			ISuperVO[] vos = new ISuperVO[length];
			vos = list.toArray(vos);
			bo.insert(vos);
		}
	}				
	private List<ISuperVO> get(IVOMeta voMeta,
			Map<IVOMeta, List<ISuperVO>> index) {
		if (index.containsKey(voMeta)) {
			return index.get(voMeta);
		}
		List<ISuperVO> list = new ArrayList<ISuperVO>();
		index.put(voMeta, list);
		return list;
	}
	/**
	 * ������ʵ�֣���ѯ֮ǰ��queryScheme���мӹ��������Լ����߼�
	 * 
	 * @param queryScheme
	 */
	protected void preQuery(IQueryScheme queryScheme) {
		// ��ѯ֮ǰ��queryScheme���мӹ��������Լ����߼�
	}

	// �ύ
	public AggTaskHVO[] pubsendapprovebills(
			AggTaskHVO[] clientFullVOs, AggTaskHVO[] originBills)
			throws BusinessException {
		AceTaskSendApproveBP bp = new AceTaskSendApproveBP();
		AggTaskHVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public AggTaskHVO[] pubunsendapprovebills(
			AggTaskHVO[] clientFullVOs, AggTaskHVO[] originBills)
			throws BusinessException {
		AceTaskUnSendApproveBP bp = new AceTaskUnSendApproveBP();
		AggTaskHVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public AggTaskHVO[] pubapprovebills(AggTaskHVO[] clientFullVOs,
			AggTaskHVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceTaskApproveBP bp = new AceTaskApproveBP();
		AggTaskHVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public AggTaskHVO[] pubunapprovebills(AggTaskHVO[] clientFullVOs,
			AggTaskHVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceTaskUnApproveBP bp = new AceTaskUnApproveBP();
		AggTaskHVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}