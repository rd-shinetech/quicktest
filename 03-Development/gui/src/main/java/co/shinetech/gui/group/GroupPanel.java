package co.shinetech.gui.group;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import co.shinetech.dao.db.PersistenceException;
import co.shinetech.dto.Group;
import co.shinetech.gui.DomainGetter;
import co.shinetech.service.ServiceFactory;
import co.shinetech.service.impl.GroupService;

@SuppressWarnings("serial")
public class GroupPanel extends JPanel implements DomainGetter<Group>{
	private JTextField nameTextField;
	private JDialog parent;
	private Group group;
	private GroupService gs = ServiceFactory.getService(GroupService.class);

	/**
	 * Create the panel.
	 */
	public GroupPanel(JDialog parent) {
		setPreferredSize(new Dimension(380, 140));
		setMinimumSize(new Dimension(380, 140));
		setMaximumSize(new Dimension(380, 140));
		setLayout(new BorderLayout(0, 0));
		this.parent = parent;
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Turma", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {100, 250, 0};
		gbl_panel.rowHeights = new int[]{20, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.anchor = GridBagConstraints.EAST;
		gbc_lblNome.insets = new Insets(5, 5, 5, 5);
		gbc_lblNome.gridx = 0;
		gbc_lblNome.gridy = 1;
		panel.add(lblNome, gbc_lblNome);
		
		nameTextField = new JTextField();
		GridBagConstraints gbc_nameTextField = new GridBagConstraints();
		gbc_nameTextField.fill = GridBagConstraints.BOTH;
		gbc_nameTextField.insets = new Insets(5, 0, 5, 0);
		gbc_nameTextField.anchor = GridBagConstraints.NORTHWEST;
		gbc_nameTextField.gridx = 1;
		gbc_nameTextField.gridy = 1;
		panel.add(nameTextField, gbc_nameTextField);
		nameTextField.setColumns(50);
		
		JPanel controlPanel = new JPanel();
		controlPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		add(controlPanel, BorderLayout.SOUTH);
		
		JButton okButton = new JButton("Ok");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Group g = getDomainModel();
				try {
					if (gs.retrieveByID((int) g.getPk()) != null)
						gs.update(g);
					else
						gs.create(g);
				} catch (PersistenceException e1) {
					JOptionPane.showMessageDialog(parent, "Erro ao escrever na base de dados.", "Erro de Persistencia",JOptionPane.ERROR_MESSAGE);
				}
				parent.dispose();
			}
		});
		controlPanel.add(okButton);
		
		JButton cancelButton = new JButton("Cancelar");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.dispose();
			}
		});
		controlPanel.add(cancelButton);

	}

	@Override
	public void setDomainModel(Group domainData) {
		this.group = domainData;
		nameTextField.setText(this.group.getName());
	}

	@Override
	public Group getDomainModel() {		
		try {
			if( this.group == null ) {
				this.group = new Group(gs.nextId());
			}
			this.group.setName(nameTextField.getText());
		} catch (PersistenceException e) {
			JOptionPane.showMessageDialog(parent, "Erro a carregar dados da base de dados.", "Erro de Persistência", JOptionPane.ERROR_MESSAGE);
		}

		return this.group;
	}
}
