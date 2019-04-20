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
 * <b> ģ������� </b>
 * 
 * <p>
 * ����һ��nodekeyһ���Ի�ȡһ��ģ�塣��Ҫ����Ч�ʿ��ǣ�һ��Զ�̵��ü��ض��ģ�塣
 * 
 * <p>
 * ����nodekey��λ�ú�ҳǩ������ģ���ĳһ��λ
 * 
 * </p>
 * 
 * Create at 2008-3-31 ����01:14:47
 * 
 * @author bq
 * @since V5.5
 */

public class TemplateContainer implements IRemoteCallCombinatorUser {
	/** ��ͷλ�� */
	public static final int POS_HEAD = 0;
	/** ����λ�� */
	public static final int POS_BODY = 1;
	/** ��βλ�� */
	public static final int POS_TAIL = 2;

	/** nodekey */
	private List<String> nodeKeies = new ArrayList<String>();

	/** context */
	private LoginContext context = null;

	/** ģ�� */
	protected List<BillTempletVO> templates = new ArrayList<BillTempletVO>();

	// ���������LoginContext��Ҳ�������ô���������
	private String funcode;
	private String pk_loginuser;
	private String pk_group;
	private List<BillTempletBodyVO> listextra = new ArrayList<BillTempletBodyVO>();
	/**
	 * Ϊ��֧�ֲ����TemplateContainer������nodekeys��������init-method�м���ģ�� ��Ҫ�ĳ������صķ�ʽ
	 */
	protected boolean isLoaded = false;

	protected BillTemplateLoader loader = new BillTemplateLoader();

	// ��ֹû�н���prepare��ֱ�ӻ�ȡ���
	protected boolean prepared = false;

	/**
	 * ��������ģ��
	 */
	public void load() {
		// ���������ɶ�����ɡ�
		// �������������֮ǰ������bean�� init-method,����֮
		// ������load�߼�����realLoad��
	}

	/**
	 * Ϊ�����ü���ģ���Զ�̵���������Զ�̵��úϲ�������RemoteCallCombinatorEx�����е���
	 * RemoteCallCombinatorEx��Ҫ��Ϊ prepare��fetch�����׶�
	 * 
	 * ����TemplateContainerҪ֧�ֲ��ע��nodekeys,�����Ҫ��setNodekeys�����е���prepare
	 * Ϊ�˱������������֧������ע���setter�ж�������prepare
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
	 * ����nodekey��ÿ�Ƭģ��
	 * 
	 * @param nodeKey
	 * @return
	 */
	public BillTempletVO getTemplate(String nodeKey) {
		return getTemplate(nodeKey, -1, null);
	}

	/**
	 * ����nodeky��λ�ú�ҳǩ��ȡģ���ĳЩ����
	 * 
	 * @param nodeKey
	 * @param pos
	 *            ��ȡֵ "head"/"body"/"tail"
	 * @param tab
	 *            ҳǩ����
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
			Logger.debug("�����˴����pos, ���õ�pos����ֵ��head��tail��body");
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
	 * ����nodeky��λ�ú�ҳǩ��ȡģ���ĳЩ����
	 * 
	 * @param nodeKey
	 *            nodekey
	 * @param pos
	 *            ��ͷ�����塢��β
	 * @see TemplateContainer#POS_BODY
	 * @see TemplateContainer#POS_HEAD
	 * @see TemplateContainer#POS_TAIL
	 * @param tab
	 *            ҳǩ����
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

		// ȡ������ͷ/����
		if (tab == null || tab.size() == 0) {
			// ҳǩ
			for (BillTabVO oldTab : tabs) {
				if (oldTab.getPos() == pos)
					newTabs.add(oldTab);
			}
			// ��Ŀ
			for (BillTempletBodyVO bodyVO : bodyVOes) {
				if (bodyVO.getPos() == pos)
					newBodyVOes.add(bodyVO);
			}
		}
		// ȡ���õı�ͷ/�����ĳЩҳǩ
		else {
			// ҳǩ
			for (BillTabVO oldTab : tabs)
				if (oldTab.getPos() == pos && tab.contains(oldTab.getTabcode()))
					newTabs.add(oldTab);

			// ��Ŀ
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
