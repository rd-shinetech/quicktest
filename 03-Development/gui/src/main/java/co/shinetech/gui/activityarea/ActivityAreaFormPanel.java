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
import javax.swing.JPanel;
import javax.swing.JTextField;

import co.shinetech.dao.db.PersistenceException;
import co.shinetech.dto.ActivityArea;
import co.shinetech.gui.DomainGetter;
import co.shinetech.service.ServiceFactory;
import co.shinetech.service.impl.ActivityAreaService;
import co.shinetech.service.impl.ProfileService;

/**
 * Form gui for activity area.
 * @author Rodrigo
 * @since 2016-08-15
 */
@SuppressWarnings("serial")
public class ActivityAreaFormPanel extends JPanel implements DomainGetter<ActivityArea> {
	private JTextField textField;
	private ActivityArea activityArea;

	public ActivityAreaFormPanel(JDialog parent) {
		setLayout(new BorderLayout(0, 0));

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
		gbc_lblNome.insets = new Insets(10, 10, 10, 5);
		gbc_lblNome.anchor = GridBagConstraints.EAST;
		gbc_lblNome.gridx = 0;
		gbc_lblNome.gridy = 0;
		panel.add(lblNome, gbc_lblNome);

		textField = new JTextField();
		textField.setPreferredSize(new Dimension(300, 20));
		textField.setMinimumSize(new Dimension(300, 20));
		textField.setMaximumSize(new Dimension(300, 20));
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.weighty = 1.0;
		gbc_textField.weightx = 1.0;
		gbc_textField.anchor = GridBagConstraints.EAST;
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(10, 5, 10, 10);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		panel.add(textField, gbc_textField);
		textField.setColumns(30);

		JPanel controlPanel = new JPanel();
		add(controlPanel, BorderLayout.SOUTH);

		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
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
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				parent.dispose();
			}
		});
		controlPanel.add(btnOk);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.dispose();
			}
		});
		controlPanel.add(btnCancel);
	}

	@Override
	public void setDomainModel(ActivityArea domainData) {
		this.activityArea = domainData;
		textField.setText(this.activityArea.getName());
	}

	@Override
	public ActivityArea getDomainModel() {
		ProfileService ps = ServiceFactory.getService(ProfileService.class);

		try {
			if( this.activityArea == null ) {
				this.activityArea = new ActivityArea(ps.nextId(), textField.getText());
			}
			else
				this.activityArea.setName(textField.getText());

		} catch (PersistenceException e) {
			// TODO: show a dialog message
		}

		return this.activityArea;
	}

}
