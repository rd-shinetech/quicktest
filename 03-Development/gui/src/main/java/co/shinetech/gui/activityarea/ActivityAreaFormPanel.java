/*
 * ActivityAreaFormPanel.java
 */
package co.shinetech.gui.activityarea;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import co.shinetech.dao.db.PersistenceException;
import co.shinetech.dto.ActivityArea;
import co.shinetech.gui.DomainGetter;
import co.shinetech.service.ServiceFactory;
import co.shinetech.service.impl.ActivityAreaService;
import co.shinetech.service.impl.ProfileService;
import javax.swing.border.TitledBorder;

/**
 * Form gui for activity area.
 * @author Rodrigo
 * @since 2016-08-15
 */
@SuppressWarnings("serial")
public class ActivityAreaFormPanel extends JPanel implements DomainGetter<ActivityArea> {
	private JTextField nameTextField;
	private ActivityArea activityArea;
	private JButton cancelButton;
	private JDialog parent;
	
	public ActivityAreaFormPanel(JDialog parent) {
		setLayout(new BorderLayout(0, 0));
		this.parent = parent;
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u00C1rea de Atividade", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {0};
		gbl_panel.rowHeights = new int[] {0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0};
		gbl_panel.rowWeights = new double[]{0.0};
		panel.setLayout(gbl_panel);

		JLabel lblNome = new JLabel("Nome:");
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.insets = new Insets(10, 10, 10, 5);
		gbc_lblNome.anchor = GridBagConstraints.EAST;
		gbc_lblNome.gridx = 0;
		gbc_lblNome.gridy = 0;
		panel.add(lblNome, gbc_lblNome);

		nameTextField = new JTextField();
		nameTextField.setPreferredSize(new Dimension(300, 20));
		nameTextField.setMinimumSize(new Dimension(300, 20));
		nameTextField.setMaximumSize(new Dimension(300, 20));
		GridBagConstraints gbc_nameTextField = new GridBagConstraints();
		gbc_nameTextField.weighty = 1.0;
		gbc_nameTextField.weightx = 1.0;
		gbc_nameTextField.anchor = GridBagConstraints.EAST;
		gbc_nameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameTextField.insets = new Insets(10, 5, 10, 10);
		gbc_nameTextField.gridx = 1;
		gbc_nameTextField.gridy = 0;
		panel.add(nameTextField, gbc_nameTextField);
		nameTextField.setColumns(30);

		JPanel controlPanel = new JPanel();
		controlPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(controlPanel, BorderLayout.SOUTH);

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ActivityAreaService as = ServiceFactory.getService(ActivityAreaService.class);
				ActivityArea p = getDomainModel();
				try {
					if (as.retrieveByID((int)p.getPk()) != null)
						as.update(p);
					else
						as.create(p);
				}
				catch (PersistenceException e1) {
					JOptionPane.showMessageDialog(parent, "Erro ao escrever na base de dados.", "Erro de Persistencia", JOptionPane.ERROR_MESSAGE);
				}
				parent.dispose();
			}
		});
		controlPanel.add(okButton);

		cancelButton = new JButton("Cancelar");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.dispose();
			}
		});
		controlPanel.add(cancelButton);
	}

	@Override
	public void setDomainModel(ActivityArea domainData) {
		this.activityArea = domainData;
		nameTextField.setText(this.activityArea.getName());
	}

	@Override
	public ActivityArea getDomainModel() {
		ProfileService ps = ServiceFactory.getService(ProfileService.class);

		try {
			if( this.activityArea == null ) {
				this.activityArea = new ActivityArea(ps.nextId(), nameTextField.getText());
			}
			else
				this.activityArea.setName(nameTextField.getText());

		} catch (PersistenceException e) {
			JOptionPane.showMessageDialog(parent, "Error a carregar da base de dados.", "Erro de Persistência", JOptionPane.ERROR_MESSAGE);
		}
		return this.activityArea;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}
}
