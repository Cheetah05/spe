
package uk.me.graphe.client.dialogs;

import uk.me.graphe.client.Graphemeui;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ShareGraphDialog extends PopupPanel
{
	private static ShareGraphDialog sInstance;
	
	private final Graphemeui parent;
	private final VerticalPanel pnlCont;
	private final Label lblTitle;
	private final TextBox txtParam;
	
	private boolean isValid;
	
	public ShareGraphDialog (Graphemeui gui)
	{
		this.parent = gui;
		this.setAnimationEnabled(true);
		this.setGlassEnabled(true);
		this.setAutoHideEnabled(true);
		this.setTitle("Share graph");		
		this.setStyleName("paramDialog");
		
		lblTitle = new Label("Share graph with:");
		txtParam = new TextBox();
		
		pnlCont = new VerticalPanel();
		
		pnlCont.add(lblTitle);
		pnlCont.add(txtParam);
		
		isValid = true;

		txtParam.addKeyDownHandler(new KeyDownHandler()
		{
			@Override
			public void onKeyDown(KeyDownEvent arg0)
			{
				//TODO: Text validation.....set isValid true or false
			}
		});
		
		txtParam.addKeyUpHandler(new KeyUpHandler()
		{
			public void onKeyUp(KeyUpEvent e)
			{
				if (e.getNativeKeyCode() == KeyCodes.KEY_ESCAPE)
				{
					cancel();
				}
				else if (e.getNativeKeyCode() == KeyCodes.KEY_ENTER)
				{
					ok();
				}
				else
				{
					if (txtParam.getText().trim().length() > 0)
					{
						isValid = true;
					}
					else
					{
						isValid = false;
					}	
				}
			}
		});
		
		super.add(pnlCont);
	}

	public static ShareGraphDialog getInstance (Graphemeui gui)
	{
		if (sInstance == null) sInstance = new ShareGraphDialog(gui);
        return sInstance;
	}
	
	public static ShareGraphDialog getInstance ()
	{
		return sInstance;
	}
	
	private void ok ()
	{
		if (isValid)
		{
			//TODO: Here is where you actually call the method to share the graph
			parent.isHotkeysEnabled = true;
			super.hide();
		}
	}
	
	private void cancel ()
	{
		parent.isHotkeysEnabled = true;
		super.hide();
	}
	
	public void show (String initialValue)
	{
		parent.isHotkeysEnabled = false;
		txtParam.setText(initialValue);
		super.center();
		super.show();		
		txtParam.setFocus(true);
		txtParam.selectAll();
	}
}
