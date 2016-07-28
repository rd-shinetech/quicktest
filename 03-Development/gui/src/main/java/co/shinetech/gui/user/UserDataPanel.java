package co.shinetech.gui.user;

import java.awt.event.ActionListener;

import co.shinetech.gui.table.DynamicTableModel;
import co.shinetech.gui.table.GridDataPanel;

/**
 * 
 * @author Robin
 *
 */
public class UserDataPanel extends GridDataPanel{

	public UserDataPanel(DynamicTableModel tm) {
		super(tm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ActionListener getCreateListener() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActionListener getRetrieveListener() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActionListener getUpdateListener() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActionListener getDeleteListener() {
		// TODO Auto-generated method stub
		return null;
	}

}
