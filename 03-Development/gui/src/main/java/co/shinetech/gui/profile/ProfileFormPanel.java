/*
 * ProfileFormPanel.java
 */
package co.shinetech.gui.profile;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;

import co.shinetech.dao.db.PersistenceException;
import co.shinetech.dto.Profile;
import co.shinetech.gui.DomainGetter;
import co.shinetech.service.ServiceFactory;
import co.shinetech.service.impl.ProfileService;

import java.awt.Insets;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import java.awt.Color;

/**
 * Form GUI for Profile.
 * @author Robin
 * @date 03/08/2016
 */
@SuppressWarnings("serial")
public class ProfileFormPanel extends JPanel implements DomainGetter<Profile> {
	private JTextField textField;
	private Profile profil;
	private JDialog parent;

	public ProfileFormPanel(JDialog parent) {
		this.parent = parent;
		setLayout(new BorderLayout(0, 0));
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Perfil de Utilizador", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {0};
		gbl_panel.rowHeights = new int[] {0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0};
		gbl_panel.rowWeights = new double[]{0.0};
		panel.setLayout(gbl_panel);

		JLabel lblNome = new JLabel("Nome:");
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.insets = new Insets(10, 5, 10, 5);
		gbc_lblNome.anchor = GridBagConstraints.EAST;
		gbc_lblNome.gridx = 0;
		gbc_lblNome.gridy = 0;
		panel.add(lblNome, gbc_lblNome);

		textField = new JTextField();
		textField.setPreferredSize(new Dimension(150, 20));
		textField.setMinimumSize(new Dimension(150, 20));
		textField.setMaximumSize(new Dimension(150, 20));
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.weighty = 1.0;
		gbc_textField.weightx = 1.0;
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(10, 5, 10, 5);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		panel.add(textField, gbc_textField);
		textField.setColumns(40);

		JPanel controlPanel = new JPanel();
		controlPanel.setBackground(Color.LIGHT_GRAY);
		controlPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(controlPanel, BorderLayout.SOUTH);

		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProfileService ps = ServiceFactory.getService(ProfileService.class);
				Profile p = getDomainModel();
				try {
					if (ps.retrieveByID((int)p.getPk()) != null)
						ps.update(p);
					else
						ps.create(p);
				}
				catch (PersistenceException e1) {
					JOptionPane.showMessageDialog(parent, "Erro ao gravar da base de dados.", "Erro de Persistência", JOptionPane.ERROR_MESSAGE);
				}
				parent.dispose();
			}
		});
		controlPanel.add(btnOk);

		JButton btnCancel = new JButton("Cancelar");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.dispose();
			}
		});
		controlPanel.add(btnCancel);
	}

	@Override
	public void setDomainModel(Profile domainData) {
		this.profil = domainData;
		textField.setText(this.profil.getName());
	}

	@Override
	public Profile getDomainModel() {
		ProfileService ps = ServiceFactory.getService(ProfileService.class);

		try {
			if( this.profil == null ) {
				this.profil = new Profile(ps.nextId(), textField.getText());
			}
			else
				this.profil.setName(textField.getText());

		} catch (PersistenceException e) {
			JOptionPane.showMessageDialog(parent, "Erro ao ler da base de dados.", "Erro de Persistência", JOptionPane.ERROR_MESSAGE);
		}

		return this.profil;
	}
}