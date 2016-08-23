/*
 * ActivityAreaDataPanel.java
 * Copyright ShineTech Lda, 2016.
 */
package co.shinetech.gui.activityarea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import co.shinetech.dao.db.PersistenceException;
import co.shinetech.dto.ActivityArea;
import co.shinetech.gui.FilterPanel;
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
	private ActivityAreaService as = ServiceFactory.getService(ActivityAreaService.class);

	public ActivityAreaDataPanel(DynamicTableModel tm) {
		super(tm,"Catálogo de Áreas de Actividade");
		mySelf = this;
		loadData();
	}
	
	private void loadData() {
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
						JOptionPane.showMessageDialog(mySelf, "Erro a carregar dados da base de dados.", "Erro de Persistência", JOptionPane.ERROR_MESSAGE);
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
				d.pack(); // redimension the JDialog to the JPanel size
				GUIUtils.centerOnParent(d, true);
				d.setVisible(true);
				loadData();
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
				FilterPanel fp = new FilterPanel(d);
				DefaultComboBoxModel<String> cbm = new DefaultComboBoxModel<>();
				
				cbm.addElement("ID");
				cbm.addElement("Nome");
				fp.setFieldComboBoxModel(cbm);
				d.setModal(true);
				d.setResizable(false);
				d.add(fp);
				d.pack(); // redimension the JDialog to the JPanel size
				GUIUtils.centerOnParent(d, true);
				d.setVisible(true);
				
				if( ! fp.isCancelled() ) {
					List<ActivityArea> list;
					
					if( fp.getFieldComboBox().getSelectedItem().equals("ID") ) {
						list = (List<ActivityArea>) tableModel.getData().stream().filter(o -> ((ActivityArea) o).getPk() == Long.valueOf(fp.getTipTextField().getText())).collect(Collectors.toList());
					} else {
						list = (List<ActivityArea>) tableModel.getData().stream().filter(o -> ((ActivityArea) o).getName().contains(fp.getTipTextField().getText())).collect(Collectors.toList());
					}					
					tableModel.setData(list);
					tableModel.fireTableDataChanged();
					table.repaint();						
				}
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
				d.pack(); // redimension the JDialog to the JPanel size				
				ActivityArea a = (ActivityArea) tableModel.getData().get(table.getSelectedRow());				
				afp.setDomainModel(a);
				GUIUtils.centerOnParent(d, true);
				d.setVisible(true);
				loadData();
			}
		};		
	}

	@Override
	public ActionListener getDeleteListener() {
		return e -> {
			if (table.getSelectedRow() < 0) {
				JOptionPane.showMessageDialog(mySelf, "Por favor, Selecione uma Área de Actividade","Aviso",JOptionPane.WARNING_MESSAGE);
				return;
			}
			ActivityArea p = (ActivityArea) tableModel.getData().get(table.getSelectedRow());
			try {
				int i = JOptionPane.showConfirmDialog(mySelf, "Quer mesmo apagar a Área de Actividade selecionada?", "Confirmação", JOptionPane.YES_NO_OPTION);
				if (i == JOptionPane.YES_OPTION) {
					as.delete((int)p.getPk());
					loadData();
				}
			}catch (PersistenceException e1) {
				JOptionPane.showMessageDialog(mySelf, "Erro a remover dados da base de dados.", "Erro de Persistência", JOptionPane.ERROR_MESSAGE);
			}
		};
	}

	@Override
	public ActionListener getReloadListener() {
		return e -> {
			loadData();
		};
	}
}
