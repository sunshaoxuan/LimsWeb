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
	 * {@link nc.itf.qcco.ITaskMaintain#insert(nc.vo.qcco.task.AggTaskHVO[])} 的测试方法。
	 */
	public void testInsert() {
		AggTaskHVO[] aggvos = new AggTaskHVO[]{new AggTaskHVO()};
		try{
			AggTaskHVO[] ex = iTaskMaintainTest.insert(aggvos);
			assertEquals(ex, ex);
		}catch(Exception e){
			fail("插入数据失败!"); 
		}
		assertEquals(true, true);
	}

	/**
	 * {@link nc.itf.qcco.ITaskMaintain#delete(nc.vo.qcco.task.AggTaskHVO[])} 的测试方法。
	 */
	public void testDelete() {
		AggTaskHVO[] aggvos = new AggTaskHVO[]{new AggTaskHVO()};
		try{
			iTaskMaintainTest.delete(aggvos);
		}catch(Exception e){
			fail("删除数据失败!"); 
		}
		assertEquals(true, true);
	}

	/**
	 * {@link nc.itf.qcco.ITaskMaintain#update(nc.vo.qcco.task.AggTaskHVO[])} 的测试方法。
	 */
	public void testUpdate() {
		AggTaskHVO[] aggvos = new AggTaskHVO[]{new AggTaskHVO()};
		try{
			AggTaskHVO[] ex = iTaskMaintainTest.update(aggvos);
			assertEquals(ex, ex);
		}catch(Exception e){
			fail("更新数据失败!"); 
		}
		assertEquals(true, true);
	}

	/**
	 * {@link nc.itf.qcco.ITaskMaintain#query(nc.ui.querytemplate.querytree.IQueryScheme)} 的测试方法。
	 */
	public void testQuery() {
		//AggTaskHVO[] aggvos = new AggTaskHVO[]{new AggTaskHVO()};
		try{
			IQueryScheme queryScheme = new QueryScheme();
			AggTaskHVO[] ex = iTaskMaintainTest.query(queryScheme);
			assertEquals(ex, ex);
		}catch(Exception e){
			fail("查询数据失败!"); 
		}
		assertEquals(true, true);
	}

	/**
	 * {@link nc.itf.qcco.ITaskMaintain#save(nc.vo.qcco.task.AggTaskHVO[], nc.vo.qcco.task.AggTaskHVO[])} 的测试方法。
	 */
	public void testSave() {
		AggTaskHVO[] aggvos = new AggTaskHVO[]{new AggTaskHVO()};
		try {
			//IQueryScheme queryScheme = new QueryScheme();
			AggTaskHVO[] ex = iTaskMaintainTest.save(aggvos, aggvos);
			assertEquals(ex, ex);
		} catch (Exception e) {
			fail("保存数据失败!");
		}
		assertEquals(true, true);
	}

	/**
	 * {@link nc.itf.qcco.ITaskMaintain#unsave(nc.vo.qcco.task.AggTaskHVO[], nc.vo.qcco.task.AggTaskHVO[])} 的测试方法。
	 */
	public void testUnsave() {
		AggTaskHVO[] aggvos = new AggTaskHVO[]{new AggTaskHVO()};
		try {
			//IQueryScheme queryScheme = new QueryScheme();
			AggTaskHVO[] ex = iTaskMaintainTest.unsave(aggvos, aggvos);
			assertEquals(ex, ex);
		} catch (Exception e) {
			fail("取消审批失败!");
		}
		assertEquals(true, true);
	}

	/**
	 * {@link nc.itf.qcco.ITaskMaintain#approve(nc.vo.qcco.task.AggTaskHVO[], nc.vo.qcco.task.AggTaskHVO[])} 的测试方法。
	 */
	public void testApprove() {
		AggTaskHVO[] aggvos = new AggTaskHVO[]{new AggTaskHVO()};
		try {
			//IQueryScheme queryScheme = new QueryScheme();
			AggTaskHVO[] ex = iTaskMaintainTest.approve(aggvos, aggvos);
			assertEquals(ex, ex);
		} catch (Exception e) {
			fail("审批失败!");
		}
		assertEquals(true, true);
	}

	/**
	 * {@link nc.itf.qcco.ITaskMaintain#unapprove(nc.vo.qcco.task.AggTaskHVO[], nc.vo.qcco.task.AggTaskHVO[])} 的测试方法。
	 */
	public void testUnapprove() {
		AggTaskHVO[] aggvos = new AggTaskHVO[]{new AggTaskHVO()};
		try {
			//IQueryScheme queryScheme = new QueryScheme();
			AggTaskHVO[] ex = iTaskMaintainTest.unapprove(aggvos, aggvos);
			assertEquals(ex, ex);
		} catch (Exception e) {
			fail("取消审批失败!");
		}
		assertEquals(true, true);
	}

	/**
	 * {@link nc.itf.qcco.ITaskMaintain#deleteOldList(java.util.List)} 的测试方法。
	 */
	public void testDeleteOldList() {
	}

	/**
	 * {@link nc.itf.qcco.ITaskMaintain#writeBackLims(nc.vo.qcco.task.AggTaskHVO)} 的测试方法。
	 */
	public void testWriteBackLims() {
	}

	/**
	 * {@link nc.itf.qcco.ITaskMaintain#updateCommissionReference(java.lang.String, java.lang.String)} 的测试方法。
	 */
	public void testUpdateCommissionReference() {
	}

	/**
	 * {@link nc.itf.qcco.ITaskMaintain#updateBillStatus(java.lang.Integer, java.lang.String)} 的测试方法。
	 */
	public void testUpdateBillStatus() {
	}

}
