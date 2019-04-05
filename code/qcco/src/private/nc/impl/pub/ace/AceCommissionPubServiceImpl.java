package nc.impl.pub.ace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.bs.qcco.commission.ace.bp.AceCommissionApproveBP;
import nc.bs.qcco.commission.ace.bp.AceCommissionDeleteBP;
import nc.bs.qcco.commission.ace.bp.AceCommissionInsertBP;
import nc.bs.qcco.commission.ace.bp.AceCommissionSendApproveBP;
import nc.bs.qcco.commission.ace.bp.AceCommissionUnApproveBP;
import nc.bs.qcco.commission.ace.bp.AceCommissionUnSendApproveBP;
import nc.bs.qcco.commission.ace.bp.AceCommissionUpdateBP;
import nc.hr.utils.InSQLCreator;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.data.vo.VODelete;
import nc.impl.pubapp.pattern.data.vo.VOInsert;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.md.persist.framework.IMDPersistenceService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.qcco.commission.AggCommissionHVO;
import nc.vo.qcco.commission.CommissionBVO;
import nc.vo.qcco.commission.CommissionHVO;
import nc.vo.qcco.commission.CommissionRVO;

public abstract class AceCommissionPubServiceImpl {
	IMDPersistenceService persist = NCLocator.getInstance().lookup(
			IMDPersistenceService.class);
	IMDPersistenceQueryService query = NCLocator.getInstance().lookup(
			IMDPersistenceQueryService.class);
private BaseDAO dao = null;
	

	public BaseDAO getDao() {
		if(dao == null){
			dao = new BaseDAO();
		}
		return dao;
	}
	// ����
	public AggCommissionHVO[] pubinsertBills(AggCommissionHVO[] clientFullVOs,
			AggCommissionHVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<AggCommissionHVO> transferTool = new BillTransferTool<AggCommissionHVO>(
					clientFullVOs);
			// ����BP
			AceCommissionInsertBP action = new AceCommissionInsertBP();

			AggCommissionHVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			// return transferTool.getBillForToClient(retvos);
			//����ts����
			dealTs(retvos);
			return retvos;
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}
	/**
	 * ���洢ʱ,Ҫ���ts��һ�µ�����
	 * @param vos
	 * @throws BusinessException
	 */
	private void dealTs(AggCommissionHVO[] vos) throws BusinessException {
		if(vos!=null && vos.length > 0){
			for(AggCommissionHVO hvo : vos){
				if(hvo==null){
					continue;
				}
				//��������ts
				String sql = " update qc_commission_r "
				+" set ts = (select ts from qc_commission_h where PK_COMMISSION_H = '"+hvo.getPrimaryKey()+"') "
				+" where PK_COMMISSION_R in ( "
				+" select PK_COMMISSION_R from qc_commission_r r "
				+" left join qc_commission_b b on b.PK_COMMISSION_B = r.PK_COMMISSION_B "
				+" left join qc_commission_h h on h.PK_COMMISSION_H = h.PK_COMMISSION_H "
				+" where h.PK_COMMISSION_H = '"+hvo.getPrimaryKey()+"' "
				+"  ) ";
				this.getDao().executeUpdate(sql);
			}
			
		}
		
	}
	// ɾ��
	public void pubdeleteBills(AggCommissionHVO[] vos) throws BusinessException {
		try {
					List<String> list = new ArrayList<String>();
			for(AggCommissionHVO vo : vos){
				list.add((String) vo.getParent().getAttributeValue("pk_commission_h"));
			}
			InSQLCreator insql = new InSQLCreator();
			String commissionInSQL = insql.getInSQL(list.toArray(new String[0]));
			List<CommissionHVO> lists = (List<CommissionHVO>) this.getDao().retrieveByClause(CommissionHVO.class, "pk_commission_h in("+commissionInSQL+")");
			if(lists.size() > 0){
				for(AggCommissionHVO vo : vos){
					CommissionBVO[] bvos = (CommissionBVO[])vo.getChildrenVO();
					for(CommissionHVO hvo : lists){
						vo.getParentVO().setTs(hvo.getTs());
						if(null != hvo.getAttributeValue("pk_commission_h") && vo.getParentVO().getAttributeValue("pk_commission_h") !=null && hvo.getAttributeValue("pk_commission_h").equals(vo.getParentVO().getAttributeValue("pk_commission_h"))){
							for(CommissionBVO bvo : bvos){
								bvo.setTs(hvo.getTs());
								CommissionRVO[] rvos = bvo.getPk_commission_r();
								if (null != rvos) {
									for(CommissionRVO rvo : rvos){
										rvo.setTs(hvo.getTs());
									}
								}
							}
						}
					}
					
				}
			}
			BillTransferTool<AggCommissionHVO> transferTool = new BillTransferTool<AggCommissionHVO>(
					(AggCommissionHVO[]) vos);
			AggCommissionHVO[] fullBills = transferTool.getClientFullInfoBill();
			String[] tableCodes = fullBills[0].getTableCodes();
			for (String tableCode : tableCodes) {
				SuperVO[] originChildrens = (SuperVO[]) fullBills[0]
						.getTableVO(tableCode);
				// ע����ӶԶ���,map��Ҫ����
				Map<String, SuperVO[]> originGrandMap = new HashMap<String, SuperVO[]>();
				for (SuperVO childVO : originChildrens) {
					// ����ǰҳǩ�µĵ�ǰ�ӵ������ﶼ��ѯ������,����ֵ��originBills�е��
					String originChildPK = ((CommissionBVO) childVO)
							.getPrimaryKey();
					Collection originGVOs = query.queryBillOfVOByCond(
							CommissionRVO.class, "pk_commission_b = '"
									+ originChildPK + "'", false);
					CommissionRVO[] originGrandvos = (CommissionRVO[]) originGVOs
							.toArray(new CommissionRVO[originGVOs.size()]);
					((CommissionBVO) childVO)
							.setPk_commission_r(originGrandvos);
					for (int i = 0; originGrandvos != null
							&& i < originGrandvos.length; i++) {
						persist.deleteBill(originGrandvos[i]);
					}
					// this.deleteVO((List<ISuperVO>) originGVOs);
				}
			}

			AceCommissionDeleteBP deleteBP = new AceCommissionDeleteBP();
			deleteBP.delete(fullBills);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}

	}

	// �޸�
	public AggCommissionHVO[] pubupdateBills(AggCommissionHVO[] vos)
			throws BusinessException {
		try {
			BillTransferTool<AggCommissionHVO> transTool = new BillTransferTool<AggCommissionHVO>(
					(AggCommissionHVO[]) vos);
			AggCommissionHVO[] fullBills = transTool.getClientFullInfoBill();
			AggCommissionHVO[] originBills = transTool.getOriginBills();

			// ��VO���޸�
			// nc.impl.pubapp.pattern.data.vo.template.UpdateBPTemplate

			AggCommissionHVO[] aggvos = (AggCommissionHVO[]) vos;
			String[] tableCodes = originBills[0].getTableCodes();

			Map<IVOMeta, List<ISuperVO>> fullGrandVOs = new HashMap<IVOMeta, List<ISuperVO>>();
			Map<IVOMeta, List<ISuperVO>> originGrandVOs = new HashMap<IVOMeta, List<ISuperVO>>();
			for (String tableCode : tableCodes) {
				SuperVO[] originChildrens = (SuperVO[]) originBills[0]
						.getTableVO(tableCode);
				for (SuperVO childVO : originChildrens) {
					// ����ǰҳǩ�µĵ�ǰ�ӵ������ﶼ��ѯ������,����ֵ��originBills�е��
					if (tableCode.equals("pk_commission_b")) {
						String originChildPK = ((CommissionBVO) childVO)
								.getPrimaryKey();
						Collection originRVOs = query.queryBillOfVOByCond(
								CommissionRVO.class, "pk_commission_b = '"
										+ originChildPK + "'", false);
						if (originRVOs != null && originRVOs.size() != 0) {
							CommissionRVO[] originGrandvos = (CommissionRVO[]) originRVOs
									.toArray(new CommissionRVO[originRVOs
											.size()]);
							((CommissionBVO) childVO)
									.setPk_commission_r(originGrandvos);
							IVOMeta meta = ((SuperVO) (originRVOs.iterator()
									.next())).getMetaData();
							if (originGrandVOs.get(meta) == null) {
								originGrandVOs.put(meta,
										(List<ISuperVO>) originRVOs);
							} else {
								originGrandVOs.get(meta).addAll(originRVOs);
							}
						}
					}
				}

				SuperVO[] currentChildrens = (SuperVO[]) aggvos[0]
						.getTableVO(tableCode);
				if (currentChildrens != null && currentChildrens.length > 0) {
					for (SuperVO childVO : currentChildrens) {
						ISuperVO[] currentGrandvos = (CommissionRVO[]) ((CommissionBVO) childVO)
								.getPk_commission_r();
						for (int i = 0; currentGrandvos != null
								&& i < currentGrandvos.length; i++) {
							((CommissionRVO) currentGrandvos[i])
									.setPk_commission_b((childVO
											.getPrimaryKey()));
						}
						if (currentGrandvos != null
								&& currentGrandvos.length != 0) {
							IVOMeta meta = ((SuperVO) (currentGrandvos[0]))
									.getMetaData();
							List arrayList = new ArrayList(
									Arrays.asList(currentGrandvos));
							if (fullGrandVOs.get(meta) == null) {
								fullGrandVOs.put(meta, arrayList);
							} else {
								fullGrandVOs.get(meta).addAll(arrayList);
							}
						}

					}
				}
			}
			fullGrandVOs = this.getFullGrandVOs(fullGrandVOs, originGrandVOs);
			this.persistent(fullGrandVOs, originGrandVOs);

			AceCommissionUpdateBP bp = new AceCommissionUpdateBP();
			AggCommissionHVO[] retBills = bp.update(fullBills, originBills);

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

	// �ο�BillIndex���������vo������Ԫ���ݿ��е�vo��pk�ڽ��洫����ֵ�в���
	private Map<IVOMeta, List<ISuperVO>> getFullGrandVOs(
			Map<IVOMeta, List<ISuperVO>> fullGrandVOs,
			Map<IVOMeta, List<ISuperVO>> originGrandVOs) {
		if (originGrandVOs == null || originGrandVOs.size() == 0)
			return fullGrandVOs;
		//
		// Ӧ����λ�ȡmeta��
		// ���ܻ�������
		//
		for (Iterator itmeta = originGrandVOs.keySet().iterator(); itmeta
				.hasNext();) {
			IVOMeta meta = (IVOMeta) itmeta.next();
			List<ISuperVO> originvos = originGrandVOs.get(meta);
			if (originvos == null || originvos.size() == 0)
				continue;
			for (Iterator itvo = originvos.iterator(); itvo.hasNext();) {
				ISuperVO originvo = (ISuperVO) itvo.next();
				String pk = originvo.getPrimaryKey();
				if (pk != null) {
					ISuperVO vo = findGrandVOByPk(fullGrandVOs.get(meta), pk);
					if (vo == null) {
						originvo.setStatus(VOStatus.DELETED);
						if (fullGrandVOs.get(meta) == null
								|| fullGrandVOs.get(meta).size() == 0) {
							List<ISuperVO> list = new ArrayList<ISuperVO>();
							list.add(originvo);
							fullGrandVOs.put(meta, list);
						} else
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
		if (originGrandVOs == null || originGrandVOs.size() == 0)
			return null;
		Iterator it = originGrandVOs.iterator();
		while (it.hasNext()) {
			SuperVO grandvo = (SuperVO) it.next();
			if (grandvo.getPrimaryKey() != null
					&& grandvo.getPrimaryKey().equals(key)) {
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

	public AggCommissionHVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggCommissionHVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggCommissionHVO> query = new BillLazyQuery<AggCommissionHVO>(
					AggCommissionHVO.class);
			bills = query.query(queryScheme, null);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return bills;
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
	public AggCommissionHVO[] pubsendapprovebills(
			AggCommissionHVO[] clientFullVOs, AggCommissionHVO[] originBills)
			throws BusinessException {
		AceCommissionSendApproveBP bp = new AceCommissionSendApproveBP();
		AggCommissionHVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public AggCommissionHVO[] pubunsendapprovebills(
			AggCommissionHVO[] clientFullVOs, AggCommissionHVO[] originBills)
			throws BusinessException {
		AceCommissionUnSendApproveBP bp = new AceCommissionUnSendApproveBP();
		AggCommissionHVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public AggCommissionHVO[] pubapprovebills(AggCommissionHVO[] clientFullVOs,
			AggCommissionHVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceCommissionApproveBP bp = new AceCommissionApproveBP();
		AggCommissionHVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public AggCommissionHVO[] pubunapprovebills(
			AggCommissionHVO[] clientFullVOs, AggCommissionHVO[] originBills)
			throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceCommissionUnApproveBP bp = new AceCommissionUnApproveBP();
		AggCommissionHVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}