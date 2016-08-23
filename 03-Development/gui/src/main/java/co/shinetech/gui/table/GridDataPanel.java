package co.shinetech.gui.table;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public abstract class GridDataPanel extends JPanel {
	private final JPanel controlPanel = new JPanel();
	private JButton newButton;
	private JButton searchButton;
	private JButton editButton;
	private JButton deleteButton;
	private JButton closeButton;
	private JPanel mySelf;
	protected JTable table;
	protected DynamicTableModel tableModel;
	private JPanel titlePanel;
	private JButton reloadButton;
	private JLabel titleLabel;
	
	/**
	 * Create the panel.
	 */
	public GridDataPanel(DynamicTableModel tm,String title) {
		mySelf = this;
		table = new JTable();
		table.setModel(tm);
		this.tableModel = tm;

		setLayout(new BorderLayout(0, 0));
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		scrollPane.setViewportView(table);
		FlowLayout fl_controlPanel = (FlowLayout) controlPanel.getLayout();
		add(controlPanel, BorderLayout.SOUTH);
		
		newButton = new JButton("Novo...");
		newButton.addActionListener(getCreateListener());
		controlPanel.add(newButton);
		
		searchButton = new JButton("Buscar...");
		searchButton.addActionListener(getRetrieveListener());
		controlPanel.add(searchButton);
		
		editButton = new JButton("Editar...");
		editButton.addActionListener(getUpdateListener());
		controlPanel.add(editButton);
		
		deleteButton = new JButton("Excluir");
		deleteButton.addActionListener(getDeleteListener());
		controlPanel.add(deleteButton);
		
		reloadButton = new JButton("Recarregar");
		reloadButton.addActionListener(getReloadListener());
		controlPanel.add(reloadButton);
		
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
		controlPanel.add(closeButton);
		
		titlePanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) titlePanel.getLayout();
		flowLayout.setHgap(20);
		flowLayout.setVgap(10);
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(titlePanel, BorderLayout.NORTH);
		
		titleLabel = new JLabel("");
		titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
		titleLabel.setFont(new Font("Arial", titleLabel.getFont().getStyle() | Font.BOLD, 18));
		titlePanel.add(titleLabel);
		titleLabel.setText(title);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(10);
	}
		
	/** Abstract methods to be implemented by child classes **/
	public abstract ActionListener getCreateListener();
	public abstract ActionListener getRetrieveListener();
	public abstract ActionListener getUpdateListener();
	public abstract ActionListener getDeleteListener();
	public abstract ActionListener getReloadListener();
}