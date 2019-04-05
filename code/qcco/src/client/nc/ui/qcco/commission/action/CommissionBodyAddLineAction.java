package nc.ui.qcco.commission.action;


import org.jfree.util.Log;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pubapp.uif2app.actions.BodyAddLineAction;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pub.BusinessException;

public class CommissionBodyAddLineAction extends BodyAddLineAction {

	@Override
	protected void afterLineInsert(int index) {
		super.afterLineInsert(index);
		setGroup(index);
	}
	private void setGroup(int index){
		/*String sql = "select PK_SAMPLE_GROUP "
				+ " from NC_SAMPLE_GROUP "
				+ " WHERE ISENABLE=1 "
				+ " and NC_SAMPLE_NAME='"+ ((char)(index+65)) +"' ";
		String pk = null;
		IUAPQueryBS query = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		try {
			pk = (String) query
					.executeQuery(sql, new ColumnProcessor());
		} catch (BusinessException e1) {
			Log.debug(e1.getMessage());
		}*/
		if(index < 4 && index >=0){
			getCardPanel().setBodyValueAt((char)(index+65)+"", index, "samplegroup");
			
			getCardPanel().setBodyValueAt((char)(index+65)+"", index, "pk_samplegroup");
		}
		
		
	}
}
