/**
 * GroupDataPanel.java
 */
package co.shinetech.gui.group;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import co.shinetech.dao.db.PersistenceException;
import co.shinetech.dto.Group;
import co.shinetech.gui.GUIUtils;
import co.shinetech.gui.QTestMainWindow;
import co.shinetech.gui.table.DynamicTableModel;
import co.shinetech.gui.table.GridDataPanel;
import co.shinetech.service.ServiceFactory;
import co.shinetech.service.impl.GroupService;

/**
 * Panel to create Group Data Grid GUI.
 * @author Rodrigo
 * @since 2016-07-25
 */
@SuppressWarnings("serial")
public class GroupDataPanel extends GridDataPanel {
	private JPanel mySelf;
	
	public GroupDataPanel(DynamicTableModel tm) {
		super(tm);
		mySelf = this;
		loadData();
	}

	private void loadData() {
		GroupService gps = ServiceFactory.getService(GroupService.class);
		Thread t = new Thread(
			new Runnable() {			
			@Override
			public void run() {
				try {
					QTestMainWindow.processStart();
					Thread.sleep(1000L);
					tableModel.setData(gps.retrieveAll());
					tableModel.fireTableDataChanged();
					table.repaint();
					QTestMainWindow.processEnd();
				} catch (PersistenceException e) {
					JOptionPane.showMessageDialog(mySelf, "Error loading data from database.");
				} catch (InterruptedException e) {
				}				
			}
		});
		t.start();
	}
		
	/* (non-Javadoc)
	 * @see co.shinetech.gui.GridDataPanel#getCreateListener()
	 */
	@Override
	public ActionListener getCreateListener() {		
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame f = (JFrame) SwingUtilities.getWindowAncestor(mySelf);
				JDialog d = new JDialog(f,"Inclusão de Turma");

				d.setModal(true);
				d.setResizable(false);
				d.add(new GroupPanel(d));
				d.pack(); // redimention the JDialog to the JPanel size
				GUIUtils.centerOnParent(d, true);
				d.setVisible(true);
			}
		};
	}

	/* (non-Javadoc)
	 * @see co.shinetech.gui.GridDataPanel#getRetrieveListener()
	 */
	@Override
	public ActionListener getRetrieveListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame f = (JFrame) SwingUtilities.getWindowAncestor(mySelf);
				JDialog d = new JDialog(f,"Inclusão de Turma");

				d.setModal(true);
				d.setResizable(false);
				d.add(new GroupPanel(d));
				d.pack(); // redimention the JDialog to the JPanel size
				GUIUtils.centerOnParent(d, true);
				d.setVisible(true);
			}
		};
	}

	/* (non-Javadoc)
	 * @see co.shinetech.gui.GridDataPanel#getUpdateListener()
	 */
	@Override
	public ActionListener getUpdateListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame f = (JFrame) SwingUtilities.getWindowAncestor(mySelf);
				JDialog d = new JDialog(f,"Inclusão de Turma");
				GroupPanel gp;

				d.setModal(true);
				d.setResizable(false);
				d.add(gp = new GroupPanel(d));
				d.pack(); // redimention the JDialog to the JPanel size
				
				Group g = (Group) tableModel.getData().get(table.getSelectedRow());
				
				gp.setDomainModel(g);
				GUIUtils.centerOnParent(d, true);
				d.setVisible(true);
			}
		};
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