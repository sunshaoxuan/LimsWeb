package nc.sso.bs;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nc.bcmanage.bs.IBusiCenterManageService;
import nc.bcmanage.vo.BusiCenterVO;
import nc.bs.framework.adaptor.IHttpServletAdaptor;
import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.login.bs.INCUserQueryService;
import nc.sso.vo.SSOAuthenConfVO;
import nc.sso.vo.SSORegInfo;
import nc.vo.sm.UserVO;

public class SSORegisterServlet extends HttpServlet implements IHttpServletAdaptor {

	private static final long serialVersionUID = 4216241764046437430L;
	private List<ISSOAuthenticator> authenList = null;
	private SecureRandom sRandom = new SecureRandom();
	private List<BusiCenterVO> bcList = null;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doAction(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doAction(req, resp);
	}

	@SuppressWarnings("restriction")
	@Override
	public void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String redir = "http://" + request.getServerName() + ":" + request.getServerPort() + "/login.jsp";

			response.setContentType("text/html;charset=GBK");
			ssoValidate(request);
			String ssoKey = request.getParameter("ssoKey");
			if (isNullStr(ssoKey)) {
				response.sendRedirect(redir);
			} else {
				SSORegInfo regInfo = new SSORegInfo();

				// busiCenter code
				String bcCode = getBusiCenterCode();
				if (!isNullStr(bcCode)) {
					regInfo.setBusiCenterCode(bcCode);
				} else {
					response.sendRedirect(redir);
				}

				// usercode
				String userCode = getUserCode(ssoKey, bcCode);
				if (isNullStr(userCode)) {
					response.sendRedirect(redir);
				}
				regInfo.setUserCode(userCode);
				// ssoKey = getUserSSOKey(userCode);
				regInfo.setSsoKey(ssoKey);

				// lang code
				regInfo.setLangCode("simpchn");

				//
				ISSOService service = NCLocator.getInstance().lookup(ISSOService.class);
				service.registerSSOInfo(regInfo);

				response.sendRedirect(redir + "?ssoKey=" + ssoKey);
			}

		} catch (Throwable th) {
			Logger.error(th.getMessage(), th);
			PrintWriter pw = response.getWriter();
			printErrorToClient(pw, th);
		}

	}

	private String getBusiCenterCode() {
		List<BusiCenterVO> bizCtVOs = getBusiCenterList();
		if (bizCtVOs == null || bizCtVOs.size() == 0) {
			return "";
		}

		return bizCtVOs.get(0).getCode();
	}

	private String getUserCode(String ssoKey, String regBSCode) {
		String userCode = "";
		userCode = ssoKey.substring(0, 1);
		userCode += ssoKey.substring(32, 34);
		userCode += ssoKey.substring(64, 66);
		userCode += ssoKey.substring(96, 98);

		userCode = userCode.replace("_", " ").trim();

		INCUserQueryService service = (INCUserQueryService) NCLocator.getInstance().lookup(INCUserQueryService.class);
		int count = getBusiCenterList().size();
		for (int i = 0; i < count; i++) {
			BusiCenterVO bcvo = (BusiCenterVO) getBusiCenterList().get(i);
			String bsCode = bcvo.getCode();
			if ((regBSCode == null) || (regBSCode.equals(bsCode))) {

				String dsName = bcvo.getDataSourceName();
				try {
					UserVO user = service.findUserVO(dsName, userCode);
					if (user != null) {
						return userCode;
					} else {
						userCode = "";
					}
				} catch (Exception e) {
					Logger.error(e.getMessage(), e);
				}
			}
		}

		return userCode;
	}

	private List<BusiCenterVO> getBusiCenterList() {
		if (bcList == null) {
			IBusiCenterManageService service = (IBusiCenterManageService) NCLocator.getInstance().lookup(
					IBusiCenterManageService.class);
			try {
				BusiCenterVO[] bcVOs = service.getBusiCenterVOs();
				bcList = new java.util.ArrayList(java.util.Arrays.asList(bcVOs));
			} catch (Exception e) {
				Logger.error(e.getMessage(), e);
			}
		}
		return bcList;
	}

	private void printErrorToClient(PrintWriter pw, Throwable th) {
		pw.println("Error:" + th.getMessage());
		th.printStackTrace(pw);

	}

	private void ssoValidate(HttpServletRequest request) throws Exception {
		int count = getAuthenList().size();
		for (int i = 0; i < count; i++) {
			ISSOAuthenticator authenticator = getAuthenList().get(i);
			authenticator.authenticate(request);
		}
	}

	private List<ISSOAuthenticator> getAuthenList() {
		if (authenList == null) {
			ISSOService service = NCLocator.getInstance().lookup(ISSOService.class);
			try {
				List<SSOAuthenConfVO> confList = service.getAuthenConfList();
				authenList = new ArrayList<ISSOAuthenticator>();
				int count = confList == null ? 0 : confList.size();
				for (int i = 0; i < count; i++) {
					SSOAuthenConfVO confVO = confList.get(i);
					ISSOAuthenticator authenticator = createAuthenticator(confVO);
					if (authenticator != null) {
						authenList.add(authenticator);
					}
				}
			} catch (Exception e) {
				Logger.error(e.getMessage(), e);
			}

		}
		return authenList;
	}

	private ISSOAuthenticator createAuthenticator(SSOAuthenConfVO confVO) {
		ISSOAuthenticator authenticator = null;
		String clsName = confVO.getAuthenClsName();

		try {
			Class<?> cls = Class.forName(clsName);
			if (ISSOAuthenticator.class.isAssignableFrom(cls)) {
				authenticator = (ISSOAuthenticator) cls.newInstance();
				if (authenticator instanceof AbstractSSOAuthenticator) {
					Map<String, String> paramMap = confVO.getParamMap();
					((AbstractSSOAuthenticator) authenticator).setParamMap(paramMap);
					Map<String, String[]> listValueMap = confVO.getListValueMap();
					((AbstractSSOAuthenticator) authenticator).setListValueMap(listValueMap);
				}
			} else {
				throw new RuntimeException(clsName + " must implements " + ISSOAuthenticator.class.getName());
			}
		} catch (Exception e) {
			Logger.error(e.getMessage(), e);
		}
		return authenticator;
	}

	private boolean isNullStr(String s) {
		return s == null || s.trim().length() == 0;
	}
}
