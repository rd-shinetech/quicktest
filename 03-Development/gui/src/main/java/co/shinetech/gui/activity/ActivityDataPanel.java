/**
 * 
 */
package co.shinetech.gui.activity;

import java.awt.event.ActionListener;

import co.shinetech.gui.table.DynamicTableModel;
import co.shinetech.gui.table.GridDataPanel;

/**
 * @author Ricardo
 *
 */
public class ActivityDataPanel extends GridDataPanel{

	public ActivityDataPanel(DynamicTableModel tm) {
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
