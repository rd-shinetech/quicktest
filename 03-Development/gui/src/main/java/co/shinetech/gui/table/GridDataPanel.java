package co.shinetech.gui.table;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public abstract class GridDataPanel extends JPanel {
	private final JPanel panel = new JPanel();
	private JButton newButton;
	private JButton searchButton;
	private JButton editButton;
	private JButton deleteButton;
	private JButton closeButton;
	private JPanel mySelf;
	protected JTable table;
	protected DynamicTableModel tableModel;
	
	/**
	 * Create the panel.
	 */
	public GridDataPanel(DynamicTableModel tm) {
		mySelf = this;
		table = new JTable();
		table.setModel(tm);
		this.tableModel = tm;

		setLayout(new BorderLayout(0, 0));
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		scrollPane.setViewportView(table);
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		add(panel, BorderLayout.SOUTH);
		
		newButton = new JButton("Novo...");
		newButton.addActionListener(getCreateListener());
		panel.add(newButton);
		
		searchButton = new JButton("Buscar...");
		searchButton.addActionListener(getRetrieveListener());
		panel.add(searchButton);
		
		editButton = new JButton("Editar...");
		editButton.addActionListener(getUpdateListener());
		panel.add(editButton);
		
		deleteButton = new JButton("Excluir");
		editButton.addActionListener(getDeleteListener());
		panel.add(deleteButton);
		
		closeButton = new JButton("Fechar");
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame f = (JFrame) SwingUtilities.getWindowAncestor(mySelf);
				f.getContentPane().remove(mySelf);
				f.revalidate();
				f.repaint();
			}
		});
		panel.add(closeButton);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(10);
	}
		
	/** Abstract methods to be implemented by child classes **/
	public abstract ActionListener getCreateListener();
	public abstract ActionListener getRetrieveListener();
	public abstract ActionListener getUpdateListener();
	public abstract ActionListener getDeleteListener();
}