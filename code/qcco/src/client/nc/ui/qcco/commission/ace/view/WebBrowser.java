package nc.ui.qcco.commission.ace.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class WebBrowser 
{
    public static void open(String url,String title)
    {
        Display display=new Display();
        Shell shell=new Shell(display);
        shell.setText(title);
        shell.setSize(800,600);
        final Text text=new Text(shell,SWT.BORDER);
        text.setBounds(110,5,560,25);
        text.setText(url);
        text.setEditable(false);
        final Browser browser=new Browser(shell,SWT.FILL);
        browser.setBounds(5,30,780,560);
        browser.setUrl(url);

        
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
              display.sleep();
          }
          display.dispose();
        
    }
}
