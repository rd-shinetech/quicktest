/**
 * ActivityAreaDataPanel.java
 * Copyright ShineTech Lda, 2016.
 */
package co.shinetech.gui.activityarea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import co.shinetech.dao.db.PersistenceException;
import co.shinetech.dto.ActivityArea;
import co.shinetech.gui.GUIUtils;
import co.shinetech.gui.QTestMainWindow;
import co.shinetech.gui.table.DynamicTableModel;
import co.shinetech.gui.table.GridDataPanel;
import co.shinetech.service.ServiceFactory;
import co.shinetech.service.impl.ActivityAreaService;

/**
 * @author Rodrigo
 * @since 2016
 * @version 1.0
 */
@SuppressWarnings("serial")
public class ActivityAreaDataPanel extends GridDataPanel{
	private JPanel mySelf;

	public ActivityAreaDataPanel(DynamicTableModel tm) {
		super(tm);
		mySelf = this;
		loadData();
	}
	
	private void loadData() {
		ActivityAreaService as = ServiceFactory.getService(ActivityAreaService.class);
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
				JDialog d = new JDialog(f,"Nova Área de Actividade");

				d.setModal(true);
				d.setResizable(false);
				d.add(new ActivityAreaFormPanel(d));
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
				JDialog d = new JDialog(f,"Pesquisar Área de Actividade");

				d.setModal(true);
				d.setResizable(false);
				d.add(new ActivityAreaFormPanel(d));
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
				JDialog d = new JDialog(f,"Editar Área de Actividade");
				ActivityAreaFormPanel afp;

				d.setModal(true);
				d.setResizable(false);
				d.add(afp = new ActivityAreaFormPanel(d));
				d.pack(); // redimention the JDialog to the JPanel size
				
				ActivityArea a = (ActivityArea) tableModel.getData().get(table.getSelectedRow());
				
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
