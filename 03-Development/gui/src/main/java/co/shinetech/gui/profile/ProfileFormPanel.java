package co.shinetech.gui.profile;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;

import co.shinetech.dao.db.PersistenceException;
import co.shinetech.dto.Profile;
import co.shinetech.dto.User;
import co.shinetech.gui.DomainGetter;
import co.shinetech.service.ServiceFactory;
import co.shinetech.service.impl.ProfileService;
import co.shinetech.service.impl.UserService;

import java.awt.Insets;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 * @author Robin
 * @date 03/08/2016
 */
@SuppressWarnings("serial")
public class ProfileFormPanel extends JPanel implements DomainGetter<Profile> {
	private JTextField textField;
	private JDialog parent;
	private Profile profil;

	public ProfileFormPanel(JDialog parent) {
		setLayout(new BorderLayout(0, 0));
		this.parent = parent;

		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {0};
		gbl_panel.rowHeights = new int[] {0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0};
		gbl_panel.rowWeights = new double[]{0.0};
		panel.setLayout(gbl_panel);

		JLabel lblNome = new JLabel("Nome:");
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.insets = new Insets(0, 0, 5, 5);
		gbc_lblNome.anchor = GridBagConstraints.EAST;
		gbc_lblNome.gridx = 0;
		gbc_lblNome.gridy = 0;
		panel.add(lblNome, gbc_lblNome);

		textField = new JTextField();
		textField.setPreferredSize(new Dimension(150, 20));
		textField.setMinimumSize(new Dimension(150, 20));
		textField.setMaximumSize(new Dimension(150, 20));
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		panel.add(textField, gbc_textField);
		textField.setColumns(10);

		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.SOUTH);

		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProfileService ps = ServiceFactory.getService(ProfileService.class);
				try {
					Profile p = new Profile(ps.nextId(), textField.getText());
					ps.create(p);
					parent.dispose();
				}
				catch (PersistenceException e1) {
					e1.printStackTrace();
				}

			}
		});
		panel_1.add(btnOk);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.dispose();
			}
		});
		panel_1.add(btnCancel);
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
			// TODO: show a dialog message
		}

		return this.profil;
	}

}