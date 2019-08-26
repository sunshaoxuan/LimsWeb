package nc.ui.pub.qcco.writeback.utils.LIMSVO;

import java.util.HashMap;
import java.util.Map;

import nc.vo.pub.SuperVO;


/**
 * 
 * @author 91967
 *
 */
public class Project extends SuperVO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5697559460265199279L;
	private Map<String,Object> atrrMap = new HashMap<>();

	@Override
	public String[] getAttributeNames() {
		return atrrMap.keySet().toArray(new String[0]);
	}

	@Override
	public Object getAttributeValue(String name) {
		return atrrMap.get(name);
	}

	@Override
	public void setAttributeValue(String name, Object value) {
		atrrMap.put(name, value);
	}
}
