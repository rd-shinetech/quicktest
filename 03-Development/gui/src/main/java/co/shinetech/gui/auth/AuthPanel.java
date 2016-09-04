package co.shinetech.gui.auth;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.border.TitledBorder;

public class AuthPanel extends JPanel {
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Create the panel.
	 */
	public AuthPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel controlPanel = new JPanel();
		controlPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(controlPanel, BorderLayout.SOUTH);
		
		JButton okButton = new JButton("Ok");
		controlPanel.add(okButton);
		
		JButton cancelButton = new JButton("Cancelar");
		controlPanel.add(cancelButton);
		
		JPanel topPanel = new JPanel(){
			@Override
	        protected void paintComponent(Graphics grphcs) {
	            super.paintComponent(grphcs);
	            Graphics2D g2d = (Graphics2D) grphcs;
	            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	                    RenderingHints.VALUE_ANTIALIAS_ON);
	            GradientPaint gp = new GradientPaint(0, 0,
	                    Color.WHITE, 0, getHeight(),
	                    Color.BLUE.brighter());
	            g2d.setPaint(gp);
	            g2d.fillRect(0, 0, getWidth(), getHeight()); 

	        }
		};
		topPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(topPanel, BorderLayout.NORTH);
		FlowLayout fl_topPanel = new FlowLayout(FlowLayout.LEFT, 5, 5);
		topPanel.setLayout(fl_topPanel);
		
		JLabel lblNewLabel = new JLabel("Authentica\u00E7\u00E3o");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setIcon(new ImageIcon(AuthPanel.class.getResource("/image/auth.png")));
		topPanel.add(lblNewLabel);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(centerPanel, BorderLayout.CENTER);
		GridBagLayout gbl_centerPanel = new GridBagLayout();
		gbl_centerPanel.columnWidths = new int[] {30, 30, 0};
		gbl_centerPanel.rowHeights = new int[] {0, 30};
		gbl_centerPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0};
		gbl_centerPanel.rowWeights = new double[]{0.0, 0.0};
		centerPanel.setLayout(gbl_centerPanel);
		
		JLabel lblUsurio = new JLabel("Utilizador:");
		GridBagConstraints gbc_lblUsurio = new GridBagConstraints();
		gbc_lblUsurio.anchor = GridBagConstraints.EAST;
		gbc_lblUsurio.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsurio.gridx = 2;
		gbc_lblUsurio.gridy = 0;
		centerPanel.add(lblUsurio, gbc_lblUsurio);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.WEST;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.gridx = 3;
		gbc_textField.gridy = 0;
		centerPanel.add(textField, gbc_textField);
		textField.setColumns(30);
		
		JLabel lblPalavraPasse = new JLabel("Palavra Passe:");
		GridBagConstraints gbc_lblPalavraPasse = new GridBagConstraints();
		gbc_lblPalavraPasse.insets = new Insets(0, 0, 0, 5);
		gbc_lblPalavraPasse.gridx = 2;
		gbc_lblPalavraPasse.gridy = 1;
		centerPanel.add(lblPalavraPasse, gbc_lblPalavraPasse);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(30);
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.anchor = GridBagConstraints.WEST;
		gbc_passwordField.insets = new Insets(10, 0, 0, 0);
		gbc_passwordField.gridx = 3;
		gbc_passwordField.gridy = 1;
		centerPanel.add(passwordField, gbc_passwordField);

	}

}
