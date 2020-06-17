package nc.impl.qcco.qcmodelno;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import nc.itf.qcco.IQcmodelnoService;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.md.persist.framework.MDPersistenceService;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.qcco.qcmodelno.ModelnoVO;

public class QcmodelnoServiceImpl implements IQcmodelnoService{
	@Override
	public List<Object> queryAllModelNo() throws BusinessException {
		String con = "dr = 0";
		return queryModelNoByCondition(con);
	}

	@Override
	public List<Object> queryModelNoByCondition(String con)
			throws BusinessException {
		if (StringUtil.isEmpty(con)) {
		      con = "1=1 and dr=0 ";
		    }
		    IMDPersistenceQueryService service = MDPersistenceService.lookupPersistenceQueryService();

		    Collection col = service.queryBillOfVOByCond(ModelnoVO.class, con, false, false);
		    return new ArrayList(col);
	}
}
