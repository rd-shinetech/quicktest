/*
 * AuthPanel.java
 */
package co.shinetech.gui.auth;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import co.shinetech.dao.db.PersistenceException;
import co.shinetech.dto.User;
import co.shinetech.service.ServiceFactory;
import co.shinetech.service.impl.UserService;
import java.awt.Color;

@SuppressWarnings("serial")
/**
 * Panel to authenticate users.
 * @author rodrigo
 * @since 2016
 *
 */
public class AuthPanel extends JPanel {
	private JTextField loginTextField;
	private JPasswordField passwordField;
	private boolean cancelled;
	private int loginCount;

	/**
	 * Create the panel.
	 */
	public AuthPanel(JDialog parent) {
		setLayout(new BorderLayout(0, 0));
		
		JPanel controlPanel = new JPanel();
		controlPanel.setBackground(Color.LIGHT_GRAY);
		controlPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(controlPanel, BorderLayout.SOUTH);
		
		JButton okButton = new JButton("Ok");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserService us = ServiceFactory.getService(UserService.class);
				User u;
				try {
					u = us.retrieveByLogin(loginTextField.getText());
					if( u == null || ! Arrays.equals(passwordField.getPassword(), u.getPassword()) ) {
						JOptionPane.showMessageDialog(parent, "Erro ao autenticar-se no aplicativo. Tente novamente.", "Erro de Login", JOptionPane.ERROR_MESSAGE);
						loginTextField.requestFocus();
						loginCount++;
						if( loginCount == 3 ) {
							JOptionPane.showMessageDialog(parent, "Erro ao autenticar-se.\nPor favor pedir atendimento técnico.", "Erro de Login", JOptionPane.ERROR_MESSAGE);
							System.exit(0);							
						}
					} else {
						parent.dispose();						
					}
					
				} catch (PersistenceException e1) {
					JOptionPane.showMessageDialog(parent, "Erro ao inicializar utilizador.\nPor favor pedir atendimento técnico.", "Erro de Persistência", JOptionPane.ERROR_MESSAGE);
					System.exit(0);					
				}												
			}
		});
		controlPanel.add(okButton);
		
		JButton cancelButton = new JButton("Cancelar");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cancelled = true;
				parent.dispose();
			}
		});
		controlPanel.add(cancelButton);
		
		JPanel topPanel = new JPanel()/*{
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
		}*/;
		topPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(topPanel, BorderLayout.NORTH);
		FlowLayout fl_topPanel = new FlowLayout(FlowLayout.LEFT, 5, 5);
		topPanel.setLayout(fl_topPanel);
		
		JLabel topLabel = new JLabel("Authentica\u00E7\u00E3o");
		topLabel.setPreferredSize(new Dimension(250, 80));
		topLabel.setSize(new Dimension(69, 11));
		topLabel.setOpaque(true);
		topLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		topLabel.setHorizontalAlignment(SwingConstants.LEFT);
		topLabel.setVerticalAlignment(SwingConstants.TOP);
		topLabel.setIcon(new ImageIcon(AuthPanel.class.getResource("/image/auth.png")));
		topPanel.add(topLabel);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(centerPanel, BorderLayout.CENTER);
		GridBagLayout gbl_centerPanel = new GridBagLayout();
		gbl_centerPanel.columnWidths = new int[] {20, 20, 0};
		gbl_centerPanel.rowHeights = new int[] {0, 20};
		gbl_centerPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0};
		gbl_centerPanel.rowWeights = new double[]{0.0, 0.0};
		centerPanel.setLayout(gbl_centerPanel);
		
		JLabel lblUsurio = new JLabel("Utilizador:");
		GridBagConstraints gbc_lblUsurio = new GridBagConstraints();
		gbc_lblUsurio.anchor = GridBagConstraints.EAST;
		gbc_lblUsurio.insets = new Insets(40, 0, 5, 5);
		gbc_lblUsurio.gridx = 2;
		gbc_lblUsurio.gridy = 0;
		centerPanel.add(lblUsurio, gbc_lblUsurio);
		
		loginTextField = new JTextField();
		GridBagConstraints gbc_loginTextField = new GridBagConstraints();
		gbc_loginTextField.anchor = GridBagConstraints.WEST;
		gbc_loginTextField.insets = new Insets(40, 0, 5, 15);
		gbc_loginTextField.gridx = 3;
		gbc_loginTextField.gridy = 0;
		centerPanel.add(loginTextField, gbc_loginTextField);
		loginTextField.setColumns(20);
		
		JLabel lblPalavraPasse = new JLabel("Palavra Passe:");
		GridBagConstraints gbc_lblPalavraPasse = new GridBagConstraints();
		gbc_lblPalavraPasse.anchor = GridBagConstraints.WEST;
		gbc_lblPalavraPasse.insets = new Insets(5, 0, 30, 5);
		gbc_lblPalavraPasse.gridx = 2;
		gbc_lblPalavraPasse.gridy = 1;
		centerPanel.add(lblPalavraPasse, gbc_lblPalavraPasse);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(20);
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.anchor = GridBagConstraints.WEST;
		gbc_passwordField.insets = new Insets(5, 0, 30, 15);
		gbc_passwordField.gridx = 3;
		gbc_passwordField.gridy = 1;
		centerPanel.add(passwordField, gbc_passwordField);

	}

	public boolean isCancelled() {
		return cancelled;
	}
}