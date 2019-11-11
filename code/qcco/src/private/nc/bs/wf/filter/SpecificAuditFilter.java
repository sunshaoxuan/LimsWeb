package nc.bs.wf.filter;

import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.HashSet;

import nc.bs.dao.BaseDAO;
import nc.bs.pub.pf.IParticipantFilter;
import nc.bs.pub.pf.ParticipantFilterContext;
import nc.pubitf.para.SysInitQuery;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFLiteralDate;
import nc.vo.sm.UserVO;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class SpecificAuditFilter implements IParticipantFilter {
	private BaseDAO baseDao;

	@Override
	public HashSet<String> filterUsers(ParticipantFilterContext pfc) throws BusinessException {
		String pk_org = pfc.getTask().getPk_org();
		String appCode = SysInitQuery.getParaString(pk_org, "OASYS01");
		String tokenSeed = SysInitQuery.getParaString(pk_org, "OASYS02");
		String accessSecret = SysInitQuery.getParaString(pk_org, "OASYS03");
		String queryServiceByCode = SysInitQuery.getParaString(pk_org, "OASYS04");
		String queryServiceByID = SysInitQuery.getParaString(pk_org, "OASYS05");

		String pk_userid = pfc.getSenderman();
		UserVO uservo = (UserVO) this.getBaseDao().retrieveByPK(UserVO.class, pk_userid);
		if (uservo == null) {
			throw new BusinessException("�޷��ҵ���ǰ�ύԱ��������");
		}

		// �õ�ǰ�û�Code��OAȡ�û����������л�ȡֱ���ϼ�ID
		String requestUrl = queryServiceByCode.replace("{agentSecret}", accessSecret).replace("{sysCode}", appCode)
				.replace("{accessToken}", getAccessToken(tokenSeed));
		String condition = "{\"code\":\"" + uservo.getUser_code_q() + "\",\"typeFlag\":\"1\"}";
		JsonObject jObject = executeQuery(requestUrl, condition);

		checkError(jObject);

		JsonArray arrList_Hr = jObject.get("list_hr").getAsJsonArray();
		JsonObject userJObject = arrList_Hr.get(0).getAsJsonObject();
		JsonElement eleManager = userJObject.get("managerid");
		String manageID = eleManager.getAsString();

		if (StringUtils.isEmpty(manageID)) {
			throw new BusinessException("δ�ҵ�Ա�� [" + uservo.getUser_code() + "] ��ֱ���ϼ���");
		}

		// ʹ��ֱ���ϼ�ID����OAȡ�ϼ��û����������л�ȡֱ���ϼ�Code
		requestUrl = queryServiceByID.replace("{agentSecret}", accessSecret).replace("{sysCode}", appCode)
				.replace("{accessToken}", getAccessToken(tokenSeed));
		condition = "{\"id\":\"" + manageID + "\"";
		jObject = executeQuery(requestUrl, condition);

		checkError(jObject);

		arrList_Hr = jObject.get("list_hr").getAsJsonArray();
		userJObject = arrList_Hr.get(0).getAsJsonObject();
		eleManager = userJObject.get("workcode");
		String manageCode = eleManager.getAsString();

		UserVO manageVO = (UserVO) this.getBaseDao().retrieveByClause(UserVO.class, "user_code='" + manageCode + "'");
		if (manageVO == null) {
			throw new BusinessException("δ�ҵ�Ա�� [manageCode] �Ĳ���Ա������");
		}

		HashSet<String> rtn = new HashSet<String>();
		rtn.add(manageVO.getCuserid());

		return rtn;
	}

	public static void checkError(JsonObject jObject) throws BusinessException {
		JsonElement eleErrCode = jObject.get("errcode");
		String errCode = eleErrCode.getAsString();

		if (errCode.equals("100001")) {
			JsonElement eleErrMsg = jObject.get("errmsg");
			throw new BusinessException("��OA��ѯԱ��ֱ���ϼ�ʧ�ܣ�" + eleErrMsg.getAsString());
		}
	}

	public static String getAccessToken(String tokenSeed) throws BusinessException {
		UFLiteralDate today = new UFLiteralDate();
		String cyear = today.toString().substring(0, 4);
		String cmonth = today.toString().substring(5, 7);
		String cday = today.toString().substring(8);
		tokenSeed = tokenSeed.replace("yyyy", cyear).replace("MM", cmonth).replace("dd", cday);
		return MD5(tokenSeed);
	}

	/**
	 * 32λMD5���ܵĴ�д�ַ���
	 * 
	 * @param s
	 * @return
	 * @throws BusinessException
	 */
	public final static String MD5(String s) throws BusinessException {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			byte[] btInput = s.getBytes();
			// ���MD5ժҪ�㷨�� MessageDigest ����
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// ʹ��ָ�����ֽڸ���ժҪ
			mdInst.update(btInput);
			// �������
			byte[] md = mdInst.digest();
			// ������ת����ʮ�����Ƶ��ַ�����ʽ
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}

	public static JsonObject executeQuery(String requestUrl, String jsonCondition) throws BusinessException {
		JsonObject object;
		HttpClient httpclient = new DefaultHttpClient();
		try {
			String host = requestUrl.replace("http://", "").substring(0,
					requestUrl.replace("http://", "").indexOf('/') + 1);
			String url = "http://" + host
					+ URLEncoder.encode(requestUrl.replace("http://", "").replace(host, ""), "UTF-8");
			HttpPost post = new HttpPost(requestUrl.replace("^", "%5E"));
			post.setHeader("Content-Type", "application/json");
			StringEntity s = new StringEntity(jsonCondition.replace("\\", ""));
			s.setContentEncoding("UTF-8");
			s.setContentType("application/json");// ����json������Ҫ����contentType
			post.setEntity(s);
			HttpResponse res = httpclient.execute(post);
			if (res.getStatusLine().getStatusCode() == 200) {
				String result = EntityUtils.toString(res.getEntity());// ����json��ʽ��
				JsonParser parse = new JsonParser();
				object = (JsonObject) parse.parse(result);
				return object;
			}
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		throw new BusinessException("��OA��ѯԱ��ֱ���ϼ�ʧ�ܡ�");
	}

	public BaseDAO getBaseDao() {
		if (baseDao == null) {
			baseDao = new BaseDAO();
		}
		return baseDao;
	}

}
