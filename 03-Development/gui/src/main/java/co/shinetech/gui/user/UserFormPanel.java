package co.shinetech.gui.user;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Dimension;

/**
 * 
 * @author Robin
 *
 */
public class UserFormPanel extends JPanel {
	private final JPanel panel_1 = new JPanel();
	private JTextField textFieldLogin;
	private JPasswordField passwordField;

	/**
	 * Create the panel.
	 */
	public UserFormPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{98, 244, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0};
		panel.setLayout(gbl_panel);
		
		JLabel lblLogin = new JLabel("Login:");
		GridBagConstraints gbc_lblLogin = new GridBagConstraints();
		gbc_lblLogin.anchor = GridBagConstraints.EAST;
		gbc_lblLogin.insets = new Insets(0, 0, 5, 5);
		gbc_lblLogin.gridx = 0;
		gbc_lblLogin.gridy = 0;
		panel.add(lblLogin, gbc_lblLogin);
		
		textFieldLogin = new JTextField();
		textFieldLogin.setMaximumSize(new Dimension(150, 20));
		textFieldLogin.setColumns(30);
		textFieldLogin.setMinimumSize(new Dimension(150, 20));
		textFieldLogin.setPreferredSize(new Dimension(150, 20));
		GridBagConstraints gbc_textFieldLogin = new GridBagConstraints();
		gbc_textFieldLogin.anchor = GridBagConstraints.WEST;
		gbc_textFieldLogin.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldLogin.gridx = 1;
		gbc_textFieldLogin.gridy = 0;
		panel.add(textFieldLogin, gbc_textFieldLogin);
		
		JLabel lblPassword = new JLabel("Password:");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.EAST;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 1;
		panel.add(lblPassword, gbc_lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(15);
		passwordField.setMinimumSize(new Dimension(150, 20));
		passwordField.setPreferredSize(new java.awt.Dimension(50, 20));
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.anchor = GridBagConstraints.WEST;
		gbc_passwordField.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 1;
		panel.add(passwordField, gbc_passwordField);
		
		JLabel lblNewLabel = new JLabel("Profile:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 2;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setMaximumSize(new Dimension(200, 20));
		comboBox.setMinimumSize(new Dimension(150, 20));
		comboBox.setPreferredSize(new Dimension(150, 20));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.anchor = GridBagConstraints.WEST;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 2;
		panel.add(comboBox, gbc_comboBox);
		add(panel_1, BorderLayout.SOUTH);
		
		JButton btnOk = new JButton("OK");
		panel_1.add(btnOk);
		
		JButton btnCancel = new JButton("Cancel");
		panel_1.add(btnCancel);

	}

}
