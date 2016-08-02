package co.shinetech.gui.user;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import co.shinetech.dao.db.PersistenceException;
import co.shinetech.gui.GUIUtils;
import co.shinetech.gui.group.GroupPanel;
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
	private JPanel mySelf;

	public UserDataPanel(DynamicTableModel tm) {
		super(tm);
		mySelf = this;
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
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame f = (JFrame) SwingUtilities.getWindowAncestor(mySelf);
				JDialog d = new JDialog(f,"Inclusão do Utilizador");
				d.setModal(true);
				d.add(new UserFormPanel(d));
				d.pack(); // redimention the JDialog to the JPanel size
				d.setResizable(false);
				GUIUtils.centerOnParent(d, true);
				d.setVisible(true);
			}
		};
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
