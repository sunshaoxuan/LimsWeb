package nc.ui.qcco.commission.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.qcco.commission.ace.view.WebBrowser;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.model.AbstractAppModel;

public class SatisfactionAction extends NCAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1L;



	public SatisfactionAction() {
		setBtnName("ÂúÒâ¶ÈÆÀ¼Û");
		setCode("satisfaction");
	}
	
	protected AbstractAppModel model = null;
	
	

	public AbstractAppModel getModel() {
		return model;
	}



	public void setModel(AbstractAppModel model) {
		this.model = model;
	}

	@Override
	protected boolean isActionEnable() {
		return false;
	}

	@Override
	public void doAction(ActionEvent paramActionEvent) throws Exception {
		

	}

}
