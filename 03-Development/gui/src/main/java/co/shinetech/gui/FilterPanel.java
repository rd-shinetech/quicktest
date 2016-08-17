/*
 * FilterGUI.java
 */
package co.shinetech.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

/**
 * Filter UI.
 * @author Rodrigo
 * @since 2016-08
 */
@SuppressWarnings("serial")
public class FilterPanel extends JPanel {
	private JTextField tipTextField;
	private JDialog parent;
	private JComboBox<String> fieldComboBox = new JComboBox<>();
	private JButton cancelButton;
	private boolean cancelled;
	
	/**
	 * Create the panel.
	 */
	public FilterPanel(JDialog parent) {
		setLayout(new BorderLayout(0, 0));
		this.parent = parent;
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Filtro a Aplicar", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNewLabel = new JLabel("Coluna:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 0;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		GridBagConstraints gbc_fieldComboBox = new GridBagConstraints();
		gbc_fieldComboBox.weightx = 1.0;
		gbc_fieldComboBox.insets = new Insets(5, 10, 10, 10);
		gbc_fieldComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldComboBox.gridx = 3;
		gbc_fieldComboBox.gridy = 0;
		panel.add(fieldComboBox, gbc_fieldComboBox);
		
		JLabel lblNewLabel_1 = new JLabel("Pista de Pesquisa:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 1;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		tipTextField = new JTextField();
		GridBagConstraints gbc_tipTextField = new GridBagConstraints();
		gbc_tipTextField.insets = new Insets(5, 10, 10, 10);
		gbc_tipTextField.weightx = 1.0;
		gbc_tipTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_tipTextField.gridx = 3;
		gbc_tipTextField.gridy = 1;
		panel.add(tipTextField, gbc_tipTextField);
		tipTextField.setColumns(40);
		
		JPanel controlPanel = new JPanel();
		controlPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(controlPanel, BorderLayout.SOUTH);
		
		JButton okButton = new JButton("Ok");
		controlPanel.add(okButton);
		
		cancelButton = new JButton("Cancelar");
		cancelButton.addActionListener(getDisposeActionListener());
		okButton.addActionListener(getDisposeActionListener());
		controlPanel.add(cancelButton);
	}
	
	private ActionListener getDisposeActionListener() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if( ae.getSource() == cancelButton ) {
					cancelled = true;
				}
				parent.dispose();
			}
		};
	}
	public void setFieldComboBoxModel(ComboBoxModel<String> model) {
		fieldComboBox.setModel(model);
	}

	/**
	 * @return the tipTextField
	 */
	public JTextField getTipTextField() {
		return tipTextField;
	}

	/**
	 * @return the fieldComboBox
	 */
	public JComboBox<String> getFieldComboBox() {
		return fieldComboBox;
	}

	/**
	 * @return the cancelled
	 */
	public boolean isCancelled() {
		return cancelled;
	}
}