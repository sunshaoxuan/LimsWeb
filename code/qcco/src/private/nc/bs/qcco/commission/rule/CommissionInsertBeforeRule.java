package nc.bs.qcco.commission.rule;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.qcco.utils.CommisionUtils;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.qcco.commission.AggCommissionHVO;

public class CommissionInsertBeforeRule implements IRule<AggCommissionHVO>{


	@Override
	public void process(AggCommissionHVO[] aggvos) {
		try{
			//检查code不可重复
			checkUni(aggvos);
		}catch(Exception e){
			ExceptionUtils.wrappException(e);
		}
		
	}
	/**
	 * 检查code不可重复
	 * @param aggvos
	 * @throws BusinessException 
	 */
	private void checkUni(AggCommissionHVO[] aggvos) throws BusinessException {
		if(aggvos!=null && aggvos.length > 0){
			IUAPQueryBS query = NCLocator.getInstance().lookup(IUAPQueryBS.class);
			CommisionUtils utils = new CommisionUtils();
			
			for(AggCommissionHVO aggvo: aggvos){
				String billno = aggvo.getParentVO().getBillno();
				String sql = "select count(*) from qc_commission_h where billno = '"+billno+"'";
				Integer count = (Integer)query.executeQuery(sql, new ColumnProcessor());
				if(billno ==null || billno.equals("") || count > 0){
					sql = "select distinct NC_SAFE_NAME from NC_PROJ_PREFIX where 11 = 11 and ( PK_SAFE_TYPE = '"
							+aggvo.getParentVO().getCodeprefix()+"' ) order by NC_SAFE_CODE";
					String name = (String)query.executeQuery(sql, new ColumnProcessor());
					aggvo.getParentVO().setBillno(utils.getCommissionPreCode(name));
				}
			}
		}
		
	}

}
