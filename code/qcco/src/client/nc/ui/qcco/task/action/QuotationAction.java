package nc.ui.qcco.task.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.qcco.commission.ace.view.WebBrowser;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.model.AbstractAppModel;

public class QuotationAction extends NCAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1L;



	public QuotationAction() {
		setBtnName("���۵�");
		setCode("quotation");
	}
	
	protected AbstractAppModel model = null;
	
	

	public AbstractAppModel getModel() {
		return model;
	}



	public void setModel(AbstractAppModel model) {
		this.model = model;
	}



	@Override
	public void doAction(ActionEvent paramActionEvent) throws Exception {
	try{
		//��ѯ
		IUAPQueryBS iUAPQueryBS = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName());   
		String url = (String)iUAPQueryBS
				.executeQuery("select vdef1 from report_path where nc_report_type = 'QUOTE'",
				new ColumnProcessor()); 
		if(null == url){
			url = "http://404";
		}
		WebBrowser.open(url,"���۵�");
	}catch(Exception e){
		Logger.error(e.getCause());
	}
		
		
	}

}