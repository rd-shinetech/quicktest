package co.shinetech.gui.auth;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class ChangePassPanel extends JPanel {
	private JPasswordField oldPasswordField;
	private JPasswordField newPasswordField;
	private JPasswordField repeatPasswordField;

	/**
	 * Create the panel.
	 */
	public ChangePassPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 178, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNewLabel = new JLabel("Palavra-passe Antiga:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		oldPasswordField = new JPasswordField();
		GridBagConstraints gbc_oldPasswordField = new GridBagConstraints();
		gbc_oldPasswordField.insets = new Insets(0, 0, 5, 5);
		gbc_oldPasswordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_oldPasswordField.gridx = 1;
		gbc_oldPasswordField.gridy = 1;
		panel.add(oldPasswordField, gbc_oldPasswordField);
		
		JLabel lblNewLabel_1 = new JLabel("Nova Palavra-passe:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 2;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		newPasswordField = new JPasswordField();
		GridBagConstraints gbc_newPasswordField = new GridBagConstraints();
		gbc_newPasswordField.insets = new Insets(0, 0, 5, 5);
		gbc_newPasswordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_newPasswordField.gridx = 1;
		gbc_newPasswordField.gridy = 2;
		panel.add(newPasswordField, gbc_newPasswordField);
		
		JLabel lblNewLabel_2 = new JLabel("Repita a Nova Palavra-passe:");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 3;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		repeatPasswordField = new JPasswordField();
		GridBagConstraints gbc_repeatPasswordField = new GridBagConstraints();
		gbc_repeatPasswordField.insets = new Insets(0, 0, 0, 5);
		gbc_repeatPasswordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_repeatPasswordField.gridx = 1;
		gbc_repeatPasswordField.gridy = 3;
		panel.add(repeatPasswordField, gbc_repeatPasswordField);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBackground(Color.LIGHT_GRAY);
		add(panel_1, BorderLayout.SOUTH);
		
		JButton confirmButton = new JButton("Confirmar");
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panel_1.add(confirmButton);
		
		JPanel panel_2 = new JPanel();
		add(panel_2, BorderLayout.NORTH);
		panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblAtualizaoDePalavrapasse = new JLabel("Atualiza\u00E7\u00E3o de Seguran\u00E7a");
		lblAtualizaoDePalavrapasse.setIcon(new ImageIcon(ChangePassPanel.class.getResource("/image/auth.png")));
		lblAtualizaoDePalavrapasse.setVerticalAlignment(SwingConstants.TOP);
		lblAtualizaoDePalavrapasse.setSize(new Dimension(69, 11));
		lblAtualizaoDePalavrapasse.setPreferredSize(new Dimension(350, 80));
		lblAtualizaoDePalavrapasse.setOpaque(true);
		lblAtualizaoDePalavrapasse.setHorizontalAlignment(SwingConstants.LEFT);
		lblAtualizaoDePalavrapasse.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel_2.add(lblAtualizaoDePalavrapasse);

	}

}
