package co.shinetech.gui.table;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public abstract class GridDataPanel extends JPanel {
	private final JPanel panel = new JPanel();
	private JTable table;
	private JButton newButton;
	private JButton searchButton;
	private JButton editButton;
	private JButton deleteButton;
	private JButton btnFechar;
	
	/**
	 * Create the panel.
	 */
	public GridDataPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		add(panel, BorderLayout.SOUTH);
		
		newButton = new JButton("Novo...");
		panel.add(newButton);
		
		searchButton = new JButton("Buscar...");
		panel.add(searchButton);
		
		editButton = new JButton("Editar...");
		panel.add(editButton);
		
		deleteButton = new JButton("Excluir");
		panel.add(deleteButton);
		
		btnFechar = new JButton("Fechar");
		panel.add(btnFechar);
	}
	
	public void setTableModel(AbstractTableModel tm) {
		table.setModel(tm);		
	}
	
	/** Abstract methods to be implemented by child classes **/
	public abstract ActionListener getCreateListener();
	public abstract ActionListener getRetrieveListener();
	public abstract ActionListener getUpdateListener();
	public abstract ActionListener getDeleteListener();
}