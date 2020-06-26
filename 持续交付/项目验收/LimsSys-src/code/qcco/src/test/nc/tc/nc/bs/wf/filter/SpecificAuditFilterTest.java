package nc.tc.nc.bs.wf.filter;

import junit.framework.TestCase;
import nc.bs.wf.filter.SpecificAuditFilter;
import nc.vo.pub.BusinessException;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class SpecificAuditFilterTest extends TestCase {
	public SpecificAuditFilterTest() {

	}

	protected void setUp() {

	}

	public void testMD5() {
		try {
			String md5Result = SpecificAuditFilter.MD5("HF_LIMS_2019_77SdT6Yk781111");
			assertTrue(md5Result.equals("BB920A2FF6CEA0FC028E004123475CCD"));
		} catch (BusinessException e) {
			fail(e.getMessage());
		}

	}

	public void testLink() {
		try {
			String accessToken = SpecificAuditFilter.getAccessToken("HF_LIMS_yyyy_77SdT6Yk78ddMM");
			String url = "http://eip.hongfa.cn:7001/eip/webApi/web_hrmResource^api_searchByCode.action?agentSecret=webAPi_hrmResource_pWRLYkl207C9pcKt0mmi1Ugzru45PJRPVZ2H&sysCode=HF_LIMS&accessToken="
					+ accessToken;
			String condition = "{\"code\":\"1002437\",\"typeFlag\":\"1\"}";
			JsonObject jObject = SpecificAuditFilter.executeQuery(url, condition);
			SpecificAuditFilter.checkError(jObject);
			JsonArray arrList_Hr = jObject.get("list_hr").getAsJsonArray();
			JsonObject userJObject = arrList_Hr.get(0).getAsJsonObject();
			JsonElement eleID = userJObject.get("loginid");
			assertTrue("1002437".equals(eleID.getAsString()));
		} catch (BusinessException e) {
			fail(e.getMessage());
		}
	}
}
