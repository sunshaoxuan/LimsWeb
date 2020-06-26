/**
 * 
 */
package nc.itf.qcco;


import nc.bs.framework.common.NCLocator;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.querytemplate.querytree.QueryScheme;
import nc.vo.qcco.task.AggTaskHVO;
import junit.framework.TestCase;

/**
 * @author 91967
 *
 */
public class ITaskMaintainTest extends TestCase {
	private ITaskMaintain iTaskMaintainTest = NCLocator.getInstance().lookup(ITaskMaintain.class);
	/**
	 * @param name
	 */
	public ITaskMaintainTest(String name) {
		super(name);
	}

	/**
	 * {@link nc.itf.qcco.ITaskMaintain#insert(nc.vo.qcco.task.AggTaskHVO[])} �Ĳ��Է�����
	 */
	public void testInsert() {
		AggTaskHVO[] aggvos = new AggTaskHVO[]{new AggTaskHVO()};
		try{
			AggTaskHVO[] ex = iTaskMaintainTest.insert(aggvos);
			assertEquals(ex, ex);
		}catch(Exception e){
			fail("��������ʧ��!"); 
		}
		assertEquals(true, true);
	}

	/**
	 * {@link nc.itf.qcco.ITaskMaintain#delete(nc.vo.qcco.task.AggTaskHVO[])} �Ĳ��Է�����
	 */
	public void testDelete() {
		AggTaskHVO[] aggvos = new AggTaskHVO[]{new AggTaskHVO()};
		try{
			iTaskMaintainTest.delete(aggvos);
		}catch(Exception e){
			fail("ɾ������ʧ��!"); 
		}
		assertEquals(true, true);
	}

	/**
	 * {@link nc.itf.qcco.ITaskMaintain#update(nc.vo.qcco.task.AggTaskHVO[])} �Ĳ��Է�����
	 */
	public void testUpdate() {
		AggTaskHVO[] aggvos = new AggTaskHVO[]{new AggTaskHVO()};
		try{
			AggTaskHVO[] ex = iTaskMaintainTest.update(aggvos);
			assertEquals(ex, ex);
		}catch(Exception e){
			fail("��������ʧ��!"); 
		}
		assertEquals(true, true);
	}

	/**
	 * {@link nc.itf.qcco.ITaskMaintain#query(nc.ui.querytemplate.querytree.IQueryScheme)} �Ĳ��Է�����
	 */
	public void testQuery() {
		//AggTaskHVO[] aggvos = new AggTaskHVO[]{new AggTaskHVO()};
		try{
			IQueryScheme queryScheme = new QueryScheme();
			AggTaskHVO[] ex = iTaskMaintainTest.query(queryScheme);
			assertEquals(ex, ex);
		}catch(Exception e){
			fail("��ѯ����ʧ��!"); 
		}
		assertEquals(true, true);
	}

	/**
	 * {@link nc.itf.qcco.ITaskMaintain#save(nc.vo.qcco.task.AggTaskHVO[], nc.vo.qcco.task.AggTaskHVO[])} �Ĳ��Է�����
	 */
	public void testSave() {
		AggTaskHVO[] aggvos = new AggTaskHVO[]{new AggTaskHVO()};
		try {
			//IQueryScheme queryScheme = new QueryScheme();
			AggTaskHVO[] ex = iTaskMaintainTest.save(aggvos, aggvos);
			assertEquals(ex, ex);
		} catch (Exception e) {
			fail("��������ʧ��!");
		}
		assertEquals(true, true);
	}

	/**
	 * {@link nc.itf.qcco.ITaskMaintain#unsave(nc.vo.qcco.task.AggTaskHVO[], nc.vo.qcco.task.AggTaskHVO[])} �Ĳ��Է�����
	 */
	public void testUnsave() {
		AggTaskHVO[] aggvos = new AggTaskHVO[]{new AggTaskHVO()};
		try {
			//IQueryScheme queryScheme = new QueryScheme();
			AggTaskHVO[] ex = iTaskMaintainTest.unsave(aggvos, aggvos);
			assertEquals(ex, ex);
		} catch (Exception e) {
			fail("ȡ������ʧ��!");
		}
		assertEquals(true, true);
	}

	/**
	 * {@link nc.itf.qcco.ITaskMaintain#approve(nc.vo.qcco.task.AggTaskHVO[], nc.vo.qcco.task.AggTaskHVO[])} �Ĳ��Է�����
	 */
	public void testApprove() {
		AggTaskHVO[] aggvos = new AggTaskHVO[]{new AggTaskHVO()};
		try {
			//IQueryScheme queryScheme = new QueryScheme();
			AggTaskHVO[] ex = iTaskMaintainTest.approve(aggvos, aggvos);
			assertEquals(ex, ex);
		} catch (Exception e) {
			fail("����ʧ��!");
		}
		assertEquals(true, true);
	}

	/**
	 * {@link nc.itf.qcco.ITaskMaintain#unapprove(nc.vo.qcco.task.AggTaskHVO[], nc.vo.qcco.task.AggTaskHVO[])} �Ĳ��Է�����
	 */
	public void testUnapprove() {
		AggTaskHVO[] aggvos = new AggTaskHVO[]{new AggTaskHVO()};
		try {
			//IQueryScheme queryScheme = new QueryScheme();
			AggTaskHVO[] ex = iTaskMaintainTest.unapprove(aggvos, aggvos);
			assertEquals(ex, ex);
		} catch (Exception e) {
			fail("ȡ������ʧ��!");
		}
		assertEquals(true, true);
	}

	/**
	 * {@link nc.itf.qcco.ITaskMaintain#deleteOldList(java.util.List)} �Ĳ��Է�����
	 */
	public void testDeleteOldList() {
	}

	/**
	 * {@link nc.itf.qcco.ITaskMaintain#writeBackLims(nc.vo.qcco.task.AggTaskHVO)} �Ĳ��Է�����
	 */
	public void testWriteBackLims() {
	}

	/**
	 * {@link nc.itf.qcco.ITaskMaintain#updateCommissionReference(java.lang.String, java.lang.String)} �Ĳ��Է�����
	 */
	public void testUpdateCommissionReference() {
	}

	/**
	 * {@link nc.itf.qcco.ITaskMaintain#updateBillStatus(java.lang.Integer, java.lang.String)} �Ĳ��Է�����
	 */
	public void testUpdateBillStatus() {
	}

}
