/**
 * GroupDataPanel.java
 */
package co.shinetech.gui.group;

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
import co.shinetech.dto.Group;
import co.shinetech.gui.FilterPanel;
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
	private GroupService gps = ServiceFactory.getService(GroupService.class);
	
	public GroupDataPanel(DynamicTableModel tm) {
		super(tm,"Catálogo de Turmas");
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
				loadData();
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
				JDialog d = new JDialog(f,"Pesquisar Turma");
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
					List<Group> list;
					
					if( fp.getFieldComboBox().getSelectedItem().equals("ID") ) {
						list = (List<Group>) tableModel.getData().stream().filter(o -> ((Group) o).getPk() == Long.valueOf(fp.getTipTextField().getText())).collect(Collectors.toList());
					} else {
						list = (List<Group>) tableModel.getData().stream().filter(o -> ((Group) o).getName().contains(fp.getTipTextField().getText())).collect(Collectors.toList());
					}					
					tableModel.setData(list);
					tableModel.fireTableDataChanged();
					table.repaint();						
				}
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
				JDialog d = new JDialog(f,"Alteração de Turma");
				GroupPanel gp;

				d.setModal(true);
				d.setResizable(false);
				d.add(gp = new GroupPanel(d));
				d.pack(); // redimention the JDialog to the JPanel size
				
				Group g = (Group) tableModel.getData().get(table.getSelectedRow());
				
				gp.setDomainModel(g);
				GUIUtils.centerOnParent(d, true);
				d.setVisible(true);
				loadData();
			}
		};
	}

	/* (non-Javadoc)
	 * @see co.shinetech.gui.GridDataPanel#getDeleteListener()
	 */
	@Override
	public ActionListener getDeleteListener() {
		return e -> {
			if (table.getSelectedRow() < 0) {
				JOptionPane.showMessageDialog(mySelf, "Por favor, Selecione uma Turma","Aviso",JOptionPane.WARNING_MESSAGE);
				return;
			}
			Group g = (Group) tableModel.getData().get(table.getSelectedRow());
			try {
				int i = JOptionPane.showConfirmDialog(mySelf, "Quer mesmo apagar a Turma selecionada?", "Confirmação", JOptionPane.YES_NO_OPTION);
				if (i == JOptionPane.YES_OPTION) {
					gps.delete((int)g.getPk());
					loadData();
				}
			} catch (PersistenceException e1) {
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