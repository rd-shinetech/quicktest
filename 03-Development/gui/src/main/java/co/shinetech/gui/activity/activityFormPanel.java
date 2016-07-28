package co.shinetech.gui.activity;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.FlowLayout;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import java.awt.Dimension;

public class activityFormPanel extends JPanel {
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField;
	private JTextField textField_4;

	/**
	 * Create the panel.
	 */
	public activityFormPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel_1.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{79, 192, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblName = new JLabel("Name:");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 1;
		panel.add(lblName, gbc_lblName);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 1;
		panel.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Start Day:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 2;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		textField_2 = new JTextField();
		textField_2.setMinimumSize(new Dimension(100, 20));
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.anchor = GridBagConstraints.WEST;
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 2;
		panel.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
		JLabel lblStarttime = new JLabel("Start Time:");
		GridBagConstraints gbc_lblStarttime = new GridBagConstraints();
		gbc_lblStarttime.anchor = GridBagConstraints.EAST;
		gbc_lblStarttime.insets = new Insets(0, 0, 5, 5);
		gbc_lblStarttime.gridx = 0;
		gbc_lblStarttime.gridy = 3;
		panel.add(lblStarttime, gbc_lblStarttime);
		
		textField = new JTextField();
		textField.setMinimumSize(new Dimension(100, 20));
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.WEST;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 3;
		panel.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblEndday = new JLabel("End Day:");
		GridBagConstraints gbc_lblEndday = new GridBagConstraints();
		gbc_lblEndday.anchor = GridBagConstraints.EAST;
		gbc_lblEndday.insets = new Insets(0, 0, 5, 5);
		gbc_lblEndday.gridx = 0;
		gbc_lblEndday.gridy = 4;
		panel.add(lblEndday, gbc_lblEndday);
		
		textField_4 = new JTextField();
		textField_4.setMinimumSize(new Dimension(100, 20));
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.anchor = GridBagConstraints.WEST;
		gbc_textField_4.insets = new Insets(0, 0, 5, 5);
		gbc_textField_4.gridx = 1;
		gbc_textField_4.gridy = 4;
		panel.add(textField_4, gbc_textField_4);
		textField_4.setColumns(10);
		
		JLabel lblEndtime = new JLabel("End Time:");
		GridBagConstraints gbc_lblEndtime = new GridBagConstraints();
		gbc_lblEndtime.anchor = GridBagConstraints.EAST;
		gbc_lblEndtime.insets = new Insets(0, 0, 5, 5);
		gbc_lblEndtime.gridx = 0;
		gbc_lblEndtime.gridy = 5;
		panel.add(lblEndtime, gbc_lblEndtime);
		
		textField_3 = new JTextField();
		textField_3.setMinimumSize(new Dimension(100, 20));
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.anchor = GridBagConstraints.WEST;
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.gridx = 1;
		gbc_textField_3.gridy = 5;
		panel.add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);
		
		JLabel lblTeacher = new JLabel("Teacher:");
		GridBagConstraints gbc_lblTeacher = new GridBagConstraints();
		gbc_lblTeacher.anchor = GridBagConstraints.EAST;
		gbc_lblTeacher.insets = new Insets(0, 0, 5, 5);
		gbc_lblTeacher.gridx = 0;
		gbc_lblTeacher.gridy = 6;
		panel.add(lblTeacher, gbc_lblTeacher);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setMinimumSize(new Dimension(100, 20));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 6;
		panel.add(comboBox, gbc_comboBox);
		
		JLabel lblActivitytype = new JLabel("Activity Type:");
		GridBagConstraints gbc_lblActivitytype = new GridBagConstraints();
		gbc_lblActivitytype.anchor = GridBagConstraints.EAST;
		gbc_lblActivitytype.insets = new Insets(0, 0, 5, 5);
		gbc_lblActivitytype.gridx = 0;
		gbc_lblActivitytype.gridy = 7;
		panel.add(lblActivitytype, gbc_lblActivitytype);
		
		JComboBox comboBox_2 = new JComboBox();
		GridBagConstraints gbc_comboBox_2 = new GridBagConstraints();
		gbc_comboBox_2.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_2.gridx = 1;
		gbc_comboBox_2.gridy = 7;
		panel.add(comboBox_2, gbc_comboBox_2);
		
		JLabel lblGroup = new JLabel("Group:");
		GridBagConstraints gbc_lblGroup = new GridBagConstraints();
		gbc_lblGroup.anchor = GridBagConstraints.EAST;
		gbc_lblGroup.insets = new Insets(0, 0, 0, 5);
		gbc_lblGroup.gridx = 0;
		gbc_lblGroup.gridy = 8;
		panel.add(lblGroup, gbc_lblGroup);
		
		JComboBox comboBox_1 = new JComboBox();
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.insets = new Insets(0, 0, 0, 5);
		gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.gridx = 1;
		gbc_comboBox_1.gridy = 8;
		panel.add(comboBox_1, gbc_comboBox_1);
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("OK");
		panel_2.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		panel_2.add(btnNewButton_1);

	}

}
