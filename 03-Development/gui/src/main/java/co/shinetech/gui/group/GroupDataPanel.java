/**
 * GroupDataPanel.java
 */
package co.shinetech.gui.group;

import java.awt.event.ActionListener;

import javax.print.ServiceUIFactory;
import javax.swing.JOptionPane;
import javax.swing.table.TableCellEditor;

import co.shinetech.dao.db.PersistenceException;
import co.shinetech.gui.table.DynamicTableModel;
import co.shinetech.gui.table.GridDataPanel;
import co.shinetech.service.ServiceFactory;
import co.shinetech.service.ServiceProvider;
import co.shinetech.service.impl.GroupService;

/**
 * Panel to create Group Data Grid GUI.
 * @author Rodrigo
 * @since 2016-07-25
 */
@SuppressWarnings("serial")
public class GroupDataPanel extends GridDataPanel {
	public GroupDataPanel(DynamicTableModel tm) {
		super(tm);
		loadData();
	}

	private void loadData() {
		GroupService gps = ServiceFactory.getService(GroupService.class);
		
		try {
			tableModel.setData(gps.retrieveAll());
		} catch (PersistenceException e) {
			JOptionPane.showMessageDialog(this, "Error loading data from database.");
		}
	}
		
	/* (non-Javadoc)
	 * @see co.shinetech.gui.GridDataPanel#getCreateListener()
	 */
	@Override
	public ActionListener getCreateListener() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see co.shinetech.gui.GridDataPanel#getRetrieveListener()
	 */
	@Override
	public ActionListener getRetrieveListener() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see co.shinetech.gui.GridDataPanel#getUpdateListener()
	 */
	@Override
	public ActionListener getUpdateListener() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see co.shinetech.gui.GridDataPanel#getDeleteListener()
	 */
	@Override
	public ActionListener getDeleteListener() {
		// TODO Auto-generated method stub
		return null;
	}
}