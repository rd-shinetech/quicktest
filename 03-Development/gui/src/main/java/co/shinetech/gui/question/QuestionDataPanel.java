/**
 * 
 */
package co.shinetech.gui.question;

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
import co.shinetech.dto.Question;
import co.shinetech.gui.FilterPanel;
import co.shinetech.gui.GUIUtils;
import co.shinetech.gui.QTestMainWindow;
import co.shinetech.gui.activityarea.ActivityAreaFormPanel;
import co.shinetech.gui.group.GroupPanel;
import co.shinetech.gui.table.DynamicTableModel;
import co.shinetech.gui.table.GridDataPanel;
import co.shinetech.service.ServiceFactory;
import co.shinetech.service.impl.QuestionService;

/**
 * @author Ricardo
 * @since 2016-08
 */
@SuppressWarnings("serial")
public class QuestionDataPanel extends GridDataPanel {
	private JPanel mySelf;
	private QuestionService qs = ServiceFactory.getService(QuestionService.class);
	
	public QuestionDataPanel(DynamicTableModel tm) {
		super(tm, "Catálogo de Questões");
		mySelf = this;
		loadData();
	}
	
	private void loadData() {
		QuestionService qs = ServiceFactory.getService(QuestionService.class);
		new Thread(() -> {
			try {
				QTestMainWindow.processStart();
				Thread.sleep(1000L);
				tableModel.setData(qs.retrieveAll());
				tableModel.fireTableDataChanged();
				table.repaint();
				QTestMainWindow.processEnd();
			} catch (PersistenceException e) {
				JOptionPane.showMessageDialog(mySelf, "Erro a carregar dados da base de dados.", "Erro de Persistência.",
						JOptionPane.ERROR_MESSAGE);
			} catch (InterruptedException e) {
			}
		}).start();
	}
	
	@Override
	public ActionListener getCreateListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame f = (JFrame) SwingUtilities.getWindowAncestor(mySelf);
				JDialog d = new JDialog(f,"Nova Questão");
				d.setModal(true);
				d.setResizable(false);
				d.add(new QuestionFormPanel(d));
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
			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame f = (JFrame) SwingUtilities.getWindowAncestor(mySelf);
				JDialog d = new JDialog(f,"Pesquisar Questão");
				FilterPanel fp = new FilterPanel(d);
				DefaultComboBoxModel<String> cbm = new DefaultComboBoxModel<>();				
				cbm.addElement("ID");
				cbm.addElement("Tipo");
				cbm.addElement("Tema");
				cbm.addElement("Questão");
				cbm.addElement("Resposta");
				fp.setFieldComboBoxModel(cbm);
				d.setModal(true);
				d.setResizable(false);
				d.add(fp);
				d.pack(); // redimension the JDialog to the JPanel size
				GUIUtils.centerOnParent(d, true);
				d.setVisible(true);
				
				if( ! fp.isCancelled() ) {
					List<Question> list;
					
					if( fp.getFieldComboBox().getSelectedItem().equals("ID") ) {
						list = (List<Question>) tableModel.getData().stream().filter(o -> ((Question) o).getPk() == Long.valueOf(fp.getTipTextField().getText())).collect(Collectors.toList());
					} else {
						list = (List<Question>) tableModel.getData().stream().filter(o -> ((Question) o).getQuestion().contains(fp.getTipTextField().getText())).collect(Collectors.toList());
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
				JDialog d = new JDialog(f,"Alteração de Questão");
				QuestionFormPanel qfp;
				d.setModal(true);
				d.setResizable(false);
				d.add(qfp = new QuestionFormPanel(d));
				d.pack(); // redimension the JDialog to the JPanel size
				
				if (table.getSelectedRow() < 0) {
					JOptionPane.showMessageDialog(mySelf, "Por favor, Selecione uma Questão","Aviso",JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				Question q = (Question) tableModel.getData().get(table.getSelectedRow());				
				qfp.setDomainModel(q);
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
				JOptionPane.showMessageDialog(mySelf, "Por favor, Selecione uma Questão","Aviso",JOptionPane.WARNING_MESSAGE);
				return;
			}
			Question p = (Question) tableModel.getData().get(table.getSelectedRow());
			try {
				int i = JOptionPane.showConfirmDialog(mySelf, "Quer mesmo apagar a questão selecionada?", "Confirmação", JOptionPane.YES_NO_OPTION);
				if (i == JOptionPane.YES_OPTION) {
					qs.delete((int)p.getPk());
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
