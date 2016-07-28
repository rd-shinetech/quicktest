/**
 * 
 */
package co.shinetech.gui.activity;

import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import co.shinetech.dao.db.PersistenceException;
import co.shinetech.gui.table.DynamicTableModel;
import co.shinetech.gui.table.GridDataPanel;
import co.shinetech.service.ServiceFactory;
import co.shinetech.service.impl.ActivityService;

/**
 * @author Ricardo
 *
 */
@SuppressWarnings("serial")
public class ActivityDataPanel extends GridDataPanel{

	public ActivityDataPanel(DynamicTableModel tm) {
		super(tm);
		loadData();
	}
	
	private void loadData() {
		ActivityService as = ServiceFactory.getService(ActivityService.class);
		
		try {
			tableModel.setData(as.retrieveAll());
		} catch (PersistenceException e) {
			JOptionPane.showMessageDialog(this, "Error loading data from database.");
		}
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
