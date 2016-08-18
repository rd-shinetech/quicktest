/**
 * 
 */
package co.shinetech.gui.activity;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import co.shinetech.dao.db.PersistenceException;
import co.shinetech.dto.Activity;
import co.shinetech.gui.GUIUtils;
import co.shinetech.gui.QTestMainWindow;
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
	private JPanel mySelf;

	public ActivityDataPanel(DynamicTableModel tm) {
		super(tm);
		mySelf = this;
		loadData();
	}
	
	private void loadData() {
		ActivityService as = ServiceFactory.getService(ActivityService.class);
		Thread t = new Thread(
				new Runnable() {
				@Override
				public void run() {
					try {
						QTestMainWindow.processStart();
						Thread.sleep(1000L);
						tableModel.setData(as.retrieveAll());
						tableModel.fireTableDataChanged();
						table.repaint();
						QTestMainWindow.processEnd();
					} catch (PersistenceException e) {
						JOptionPane.showMessageDialog(mySelf, "Error loading data from database." );
					} catch (InterruptedException e) {
						
					}
				}
		});
		t.start();
	}
	
	@Override
	public ActionListener getCreateListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame f = (JFrame) SwingUtilities.getWindowAncestor(mySelf);
				JDialog d = new JDialog(f,"Nova Actividade");

				d.setModal(true);
				d.setResizable(false);
				d.add(new ActivityFormPanel(d));
				d.pack(); // redimention the JDialog to the JPanel size
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
				JDialog d = new JDialog(f,"Pesquisar Actividade");

				d.setModal(true);
				d.setResizable(false);
				d.add(new ActivityFormPanel(d));
				d.pack(); // redimention the JDialog to the JPanel size
				GUIUtils.centerOnParent(d, false);
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
				JDialog d = new JDialog(f,"Editar Actividade");
				ActivityFormPanel afp;

				d.setModal(true);
				d.setResizable(false);
				d.add(afp = new ActivityFormPanel(d));
				d.pack(); // redimention the JDialog to the JPanel size
				
				Activity a = (Activity) tableModel.getData().get(table.getSelectedRow());
				
				afp.setDomainModel(a);
				GUIUtils.centerOnParent(d, true);
				d.setVisible(true);
			}
		};
	}

	@Override
	public ActionListener getDeleteListener() {
		// TODO Auto-generated method stub
		return null;
	}
}