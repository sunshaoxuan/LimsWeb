package nc.ui.uif2;

import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.AbstractAction;

import nc.bs.logging.Logger;
import nc.funcnode.ui.action.INCAction;
import nc.ui.uif2.actions.ActionInterceptor;
import nc.ui.uif2.model.AbstractUIAppModel;
import nc.vo.uif2.LoginContext;


public abstract class NCAction extends AbstractAction implements AppEventListener {
	
	private static final long serialVersionUID = 2471962024773715385L;

	protected ActionInterceptor interceptor;
	
	protected IExceptionHandler exceptionHandler;
	
	protected INCActionStatusJudge ncActionStatusJudge = null;
	
	public static final String TOOLBAR_SHOWNAME_KEY = "TOOLBAR_SHOWNAME_KEY";
	
	public NCAction() {
		
		setShowNameInToolbar(false);
	}
	
	public String getBtnName() {
		return (String) getValue(NAME); 
	}   

	public void setBtnName(String btnName) {
		putValue(NAME,btnName);
	} 
	
	public void setBtnResdir(String resdir)
	{
		putValue(INCAction.NAMERESDIR, resdir);
	}
	
	public void setBtnResid(String resid)
	{
		putValue(INCAction.NAMERESID, resid);
	}
 
	public void setCode(String code)
	{
		putValue(INCAction.CODE, code);
	}

	public void handleEvent(AppEvent event) {
		
		updateStatus();
	}

	public void updateStatus() {
		
		boolean isEnable = isActionEnable();
		setEnabled(getNcActionStatusJudge() == null ? isEnable : getNcActionStatusJudge().isActionEnable(this, isEnable));
	}
	protected boolean isActionEnable() 
	{
		return true;
	} 
	
	public void setShowNameInToolbar(boolean isShow)
	{
		putValue(NCAction.TOOLBAR_SHOWNAME_KEY, isShow?Boolean.TRUE:Boolean.FALSE);
	}
	 
	public void actionPerformed(ActionEvent e) {
		Logger.debug("Entering " + getClass().toString() + ".actionPerformed");
		beforeDoAction();
		try {
			if (interceptor == null || interceptor.beforeDoAction(this, e)) {
				try {
					doAction(e);
					if (interceptor != null)
						interceptor.afterDoActionSuccessed(this, e);
				} catch (Exception ex) {
					if (!"class nc.ui.qcco.commission.action.CommissionCopyAction".equals(getClass().toString())) {
						if (interceptor == null || interceptor.afterDoActionFailed(this, e, ex)) {
							if (getExceptionHandler() != null) {
								processExceptionHandler(ex);
							} else if (ex instanceof RuntimeException) {
								throw (RuntimeException) ex;
							}

							throw new RuntimeException(ex);

						}
					}
				}
			}
		} finally {
			Logger.debug("Leaving " + getClass().toString() + ".actionPerformed");
		}

	}

	protected void processExceptionHandler(Exception ex) {
		
//		exceptionHandler.handlerExeption(ex);
		new ExceptionHandlerUtil().processErrorMsg4SpecialAction(this, getExceptionHandler(), ex);
	}
	
	protected void beforeDoAction()
	{
		Method[] ms = this.getClass().getMethods();
		for(Method m : ms)
		{
			Class<?> clazz = m.getReturnType();
			if(AbstractUIAppModel.class.isAssignableFrom(clazz))
			{
				try {
					AbstractUIAppModel model = (AbstractUIAppModel) m.invoke(this, null);
					if(model == null)
						return;
					LoginContext ctx = model.getContext();
					if(ctx == null)
						break;
					ShowStatusBarMsgUtil.showStatusBarMsg("", ctx);
				} catch (IllegalArgumentException e) {
					Logger.debug(e.getMessage());
				} catch (IllegalAccessException e) { 
					Logger.debug(e.getMessage());
				} catch (InvocationTargetException e) {
					Logger.debug(e.getMessage());
				}
				break;
			}
		}
		
	}
	public abstract void doAction(ActionEvent e) throws Exception ;

	public ActionInterceptor getInterceptor() {
		return interceptor;
	}

	public void setInterceptor(ActionInterceptor interceptor) {
		this.interceptor = interceptor;
	}

	public IExceptionHandler getExceptionHandler() {
		return exceptionHandler;
	}

	public void setExceptionHandler(IExceptionHandler exceptionHandler) {
		this.exceptionHandler = exceptionHandler;
	}

	public INCActionStatusJudge getNcActionStatusJudge() {
		return ncActionStatusJudge;
	}

	public void setNcActionStatusJudge(INCActionStatusJudge ncActionStatusJudge) {
		this.ncActionStatusJudge = ncActionStatusJudge;
	}
}
