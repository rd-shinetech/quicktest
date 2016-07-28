package co.shinetech.gui.user;

import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import co.shinetech.dao.db.PersistenceException;
import co.shinetech.gui.table.DynamicTableModel;
import co.shinetech.gui.table.GridDataPanel;
import co.shinetech.service.ServiceFactory;
import co.shinetech.service.impl.UserService;

/**
 * 
 * @author Robin
 *
 */
@SuppressWarnings("serial")
public class UserDataPanel extends GridDataPanel{

	public UserDataPanel(DynamicTableModel tm) {
		super(tm);
		loadData();
	}

	public void loadData() {
		UserService us = ServiceFactory.getService(UserService.class);

		try {
			tableModel.setData(us.retrieveAll());
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
