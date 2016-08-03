package co.shinetech.gui.user;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import co.shinetech.dao.db.PersistenceException;
import co.shinetech.dto.User;
import co.shinetech.gui.GUIUtils;
import co.shinetech.gui.QTestMainWindow;
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
		new Thread(() -> {
				try {
					QTestMainWindow.processStart();
					Thread.sleep(1000L);
					tableModel.setData(us.retrieveAll());
					tableModel.fireTableDataChanged();
					table.repaint();
					QTestMainWindow.processEnd();
				} catch (PersistenceException e) {
					JOptionPane.showMessageDialog(mySelf, "Error loading data from database.");
				}
				catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}).start();
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
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame f = (JFrame) SwingUtilities.getWindowAncestor(mySelf);
				JDialog d = new JDialog(f,"Pesquisar utilizador");

				d.setModal(true);
				d.setResizable(false);
				d.add(new UserFormPanel(d));
				d.pack(); // redimention the JDialog to the JPanel size
				GUIUtils.centerOnParent(d, true);
				d.setVisible(true);
			}
		};
	}

	@Override
	public ActionListener getUpdateListener() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame f = (JFrame) SwingUtilities.getWindowAncestor(mySelf);
				JDialog d = new JDialog(f,"Atualização do utilizador");
				UserFormPanel ufp;

				d.setModal(true);
				d.setResizable(false);
				d.add(ufp = new UserFormPanel(d));
				d.pack(); // redimention the JDialog to the JPanel size
				
				User u = (User) tableModel.getData().get(table.getSelectedRow());
				
				ufp.setDomainModel(u);
				GUIUtils.centerOnParent(d, true);
				d.setVisible(true);
			}
		};
	}

	@Override
	public ActionListener getDeleteListener() {
		return null;
	}

}
