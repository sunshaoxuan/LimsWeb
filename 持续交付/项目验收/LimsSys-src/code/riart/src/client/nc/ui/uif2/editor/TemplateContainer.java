package nc.ui.uif2.editor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import nc.bs.logging.Logger;
import nc.md.MDBaseQueryFacade;
import nc.md.MDPathInfo;
import nc.md.model.MetaDataException;
import nc.ui.pub.bill.BillTemplateLoader;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.bill.BillTabVO;
import nc.vo.pub.bill.BillTempletBodyVO;
import nc.vo.pub.bill.BillTempletHeadVO;
import nc.vo.pub.bill.BillTempletVO;
import nc.vo.uif2.LoginContext;
import nc.vo.util.remotecallcombination.IRemoteCallCombinatorUser;

/**
 * <b> 模板加载器 </b>
 * 
 * <p>
 * 根据一批nodekey一次性获取一批模板。主要基于效率考虑，一次远程调用加载多个模板。
 * 
 * <p>
 * 根据nodekey、位置和页签编码获得模板的某一部位
 * 
 * </p>
 * 
 * Create at 2008-3-31 下午01:14:47
 * 
 * @author bq
 * @since V5.5
 */

public class TemplateContainer implements IRemoteCallCombinatorUser {
	/** 表头位置 */
	public static final int POS_HEAD = 0;
	/** 表体位置 */
	public static final int POS_BODY = 1;
	/** 表尾位置 */
	public static final int POS_TAIL = 2;

	/** nodekey */
	private List<String> nodeKeies = new ArrayList<String>();

	/** context */
	private LoginContext context = null;

	/** 模板 */
	protected List<BillTempletVO> templates = new ArrayList<BillTempletVO>();

	// 如果不设置LoginContext，也可以设置此三个变量
	private String funcode;
	private String pk_loginuser;
	private String pk_group;
	private List<BillTempletBodyVO> listextra = new ArrayList<BillTempletBodyVO>();
	/**
	 * 为了支持插件往TemplateContainer中增加nodekeys，不能在init-method中加载模板 而要改成懒加载的方式
	 */
	protected boolean isLoaded = false;

	protected BillTemplateLoader loader = new BillTemplateLoader();

	// 防止没有进行prepare就直接获取结果
	protected boolean prepared = false;

	/**
	 * 加载所有模板
	 */
	public void load() {
		// 这个方法，啥都不干。
		// 但是这个方法，之前被用作bean的 init-method,保留之
		// 真正的load逻辑放在realLoad中
	}

	/**
	 * 为了能让加载模板的远程调用与其他远程调用合并，采用RemoteCallCombinatorEx来进行调用
	 * RemoteCallCombinatorEx需要分为 prepare和fetch两个阶段
	 * 
	 * 由于TemplateContainer要支持插件注册nodekeys,因此需要在setNodekeys方法中调用prepare
	 * 为了保险起见，几个支持属性注入的setter中都调用了prepare
	 * 
	 */
	public void prepare() {

		addNullAsDefaultNodeKeyIfNeeded();

		templates.clear();

		if (loader == null) {
			loader = new BillTemplateLoader();
			this.isLoaded = false;
		}

		if (!StringUtil.isEmptyWithTrim(funcode)) {
			loader.prepare(funcode, getPk_loginuser(), getPk_group(), nodeKeies.toArray(new String[0]));
		} else {
			loader.prepare(getContext().getNodeCode(), getContext().getPk_loginUser(), getContext().getPk_group(),
					nodeKeies.toArray(new String[0]));
		}

		prepared = true;
	}

	private void addNullAsDefaultNodeKeyIfNeeded() {
		if (nodeKeies == null) {
			nodeKeies = new ArrayList<String>();
			nodeKeies.add(null);
		} else {
			if (!nodeKeies.contains("") && !nodeKeies.contains(null)) {
				nodeKeies.remove(null);
				nodeKeies.add(0, null);
			}
		}
	}

	protected void realLoad() {
		if (!isLoaded) {
			try {
				if (!this.prepared)
					this.prepare();
				templates.addAll(Arrays.asList(loader.getTemplateVOs()));
				fetchMDInfo();
				loader = null;
			} catch (Exception e) {
				Logger.error(e.getMessage(), e);
			} finally {
				isLoaded = true;
			}
		}

	}

	/**
	 * 根据nodekey获得卡片模板
	 * 
	 * @param nodeKey
	 * @return
	 */
	public BillTempletVO getTemplate(String nodeKey) {
		return getTemplate(nodeKey, -1, null);
	}

	/**
	 * 根据nodeky、位置和页签获取模板的某些部分
	 * 
	 * @param nodeKey
	 * @param pos
	 *            可取值 "head"/"body"/"tail"
	 * @param tab
	 *            页签编码
	 * @return
	 */
	public BillTempletVO getTemplate(String nodeKey, String pos, List<String> tab) {
		// nodeKey = null;

		int iPos = -1;
		if (pos == null)
			iPos = -1;
		else if (pos.trim().equalsIgnoreCase("head"))
			iPos = TemplateContainer.POS_HEAD;
		else if (pos.trim().equalsIgnoreCase("body"))
			iPos = TemplateContainer.POS_BODY;
		else if (pos.trim().equalsIgnoreCase("tail"))
			iPos = TemplateContainer.POS_TAIL;
		else
			Logger.debug("配置了错误的pos, 可用的pos配置值：head、tail、body");
		if (null != nodeKey && nodeKey.equals("param1")) {
			nodeKey = "param";

			BillTempletVO template = getTemplate(nodeKey, iPos, tab);
			BillTempletBodyVO[] bodyVO = template.getBodyVO();
			List<BillTempletBodyVO> list = new ArrayList<BillTempletBodyVO>();
			for (int i = 0; i < bodyVO.length; i++) {
				// &&
				// bodyVO[i].getMetadataproperty().contains("qcco.commission_h")
				if (!bodyVO[i].getTableCode().equals("AuditInfo")) {
					list.add(bodyVO[i]);
				} else {
					listextra.add(bodyVO[i]);
				}
			}
			template.setChildrenVO(list.toArray(new BillTempletBodyVO[0]));
			return template;
		} else if (null != nodeKey && nodeKey.equals("param")) {
			BillTempletVO template = getTemplate(nodeKey, iPos, tab);
			BillTempletBodyVO[] bodyVO = template.getBodyVO();
			List<BillTempletBodyVO> list = new ArrayList<BillTempletBodyVO>();
			for (int i = 0; i < bodyVO.length; i++) {
				list.add(bodyVO[i]);
			}
			for (int i = 0; i < listextra.size(); i++) {
				list.add(listextra.get(i));
			}
			template.setChildrenVO(list.toArray(new BillTempletBodyVO[0]));
			listextra.clear();
			return template;
		} else if (null != nodeKey && nodeKey.equals("sunparas1")) {
			nodeKey="sunparas";
			BillTempletVO template = getTemplate(nodeKey, iPos, tab);
			BillTempletBodyVO[] bodyVO = template.getBodyVO();
			List<BillTempletBodyVO> list = new ArrayList<BillTempletBodyVO>();
			for (int i = 0; i < bodyVO.length; i++) {
				if (bodyVO[i].getTableCode().equals("audittable") || bodyVO[i].getTable_code().equals("tailtable")) {
					listextra.add(bodyVO[i]);
				} else {
					list.add(bodyVO[i]);
				}
			}
			template.setChildrenVO(list.toArray(new BillTempletBodyVO[0]));
			return template;
		} else if (null != nodeKey && nodeKey.equals("sunparas")) {
			BillTempletVO template = getTemplate(nodeKey, iPos, tab);
			BillTempletBodyVO[] bodyVO = template.getBodyVO();
			List<BillTempletBodyVO> list = new ArrayList<BillTempletBodyVO>();
			for (int i = 0; i < bodyVO.length; i++) {
				list.add(bodyVO[i]);
			}
			for (int i = 0; i < listextra.size(); i++) {
				list.add(listextra.get(i));
			}
			template.setChildrenVO(list.toArray(new BillTempletBodyVO[0]));
			listextra.clear();
			return template;
		} else {

			return getTemplate(nodeKey, iPos, tab);
		}

	}

	/**
	 * 根据nodeky、位置和页签获取模板的某些部分
	 * 
	 * @param nodeKey
	 *            nodekey
	 * @param pos
	 *            表头、表体、表尾
	 * @see TemplateContainer#POS_BODY
	 * @see TemplateContainer#POS_HEAD
	 * @see TemplateContainer#POS_TAIL
	 * @param tab
	 *            页签编码
	 * @return
	 */
	public BillTempletVO getTemplate(String nodeKey, int pos, List<String> tab) {
		realLoad();
		if (nodeKeies == null || templates == null)
			return null;

		if ("".equals(nodeKey) && !nodeKeies.contains(""))
			nodeKey = null;

		int index = nodeKeies.indexOf(nodeKey);
		if (index == -1)
			return null;

		BillTempletVO template = templates.get(index);
		/*
		 * BillTabVO[] billTabVOs =
		 * template.getHeadVO().getStructvo().getBillTabVOs(); List<BillTabVO>
		 * list = new ArrayList<BillTabVO>(); for (int i = 0; i <
		 * billTabVOs.length; i++) { //tabcode=AuditInfo if
		 * (!billTabVOs[i].getTabcode().equals("AuditInfo")) {
		 * list.add(billTabVOs[i]); } }
		 * template.getHeadVO().getStructvo().setBillTabVOs(list.toArray(new
		 * BillTabVO[0]));
		 */
		if (pos < 0)
			return template;

		BillTabVO[] tabs = template.getHeadVO().getStructvo().getBillTabVOs();
		List<BillTabVO> newTabs = new ArrayList<BillTabVO>();

		BillTempletBodyVO[] bodyVOes = template.getBodyVO();
		Vector<BillTempletBodyVO> newBodyVOes = new Vector<BillTempletBodyVO>();

		// 取整个表头/表体
		if (tab == null || tab.size() == 0) {
			// 页签
			for (BillTabVO oldTab : tabs) {
				if (oldTab.getPos() == pos)
					newTabs.add(oldTab);
			}
			// 项目
			for (BillTempletBodyVO bodyVO : bodyVOes) {
				if (bodyVO.getPos() == pos)
					newBodyVOes.add(bodyVO);
			}
		}
		// 取配置的表头/表体的某些页签
		else {
			// 页签
			for (BillTabVO oldTab : tabs)
				if (oldTab.getPos() == pos && tab.contains(oldTab.getTabcode()))
					newTabs.add(oldTab);

			// 项目
			for (BillTempletBodyVO bodyVO : bodyVOes) {
				if (bodyVO.getPos() == pos && tab.contains(bodyVO.getTable_code()))
					newBodyVOes.add(bodyVO);
			}
		}

		BillTempletHeadVO newHeadVO = (BillTempletHeadVO) template.getHeadVO().clone();
		newHeadVO.getStructvo().setBillTabVOs(newTabs.toArray(new BillTabVO[0]));

		return new BillTempletVO(newHeadVO, newBodyVOes);

	}

	protected void fetchMDInfo() {
		MDPathInfo[] pathinfos = BillTemplateMDPropFetchUtil.fetchMDPathInfo(this.templates
				.toArray(new BillTempletVO[0]));
		if (pathinfos == null || pathinfos.length == 0)
			return;
		try {
			MDBaseQueryFacade.getInstance().loadMDByPath(pathinfos);
		} catch (MetaDataException e) {
			Logger.error(e.getMessage(), e);
		}
	}

	protected List<BillTempletVO> getTemplates() {
		return this.templates;
	}

	public List<String> getNodeKeies() {
		return nodeKeies;
	}

	public void setNodeKeies(List<String> nodeKeies) {
		this.nodeKeies = nodeKeies;
	}

	public LoginContext getContext() {
		return context;
	}

	public void setContext(LoginContext context) {
		this.context = context;
	}

	public String getFuncode() {
		return funcode;
	}

	public void setFuncode(String funcode) {
		this.funcode = funcode;
	}

	public String getPk_loginuser() {
		return pk_loginuser;
	}

	public void setPk_loginuser(String pk_loginuser) {
		this.pk_loginuser = pk_loginuser;

	}

	public String getPk_group() {
		return pk_group;
	}

	public void setPk_group(String pk_group) {
		this.pk_group = pk_group;
	}
}
