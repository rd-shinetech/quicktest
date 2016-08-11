package co.shinetech.gui.activity;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import co.shinetech.dao.db.PersistenceException;
import co.shinetech.dto.Activity;
import co.shinetech.dto.ActivityType;
import co.shinetech.dto.Group;
import co.shinetech.dto.User;
import co.shinetech.gui.DomainGetter;
import co.shinetech.service.ServiceFactory;
import co.shinetech.service.impl.ActivityService;
import co.shinetech.service.impl.GroupService;
import co.shinetech.service.impl.UserService;

@SuppressWarnings("serial")
public class ActivityFormPanel extends JPanel implements DomainGetter<Activity> {
	private JTextField nameTextField_1;
	private JTextField startDayTextField;
	private JTextField endTimeTextField;
	private JTextField startTimeTextField;
	private JTextField endDayTextField;
	@SuppressWarnings("unused")
	private JDialog parent;
	private Activity activity;
	private JComboBox teacherComboBox;
	private JComboBox activityTypeComboBox;
	private JComboBox groupComboBox;
	/**
	 * Create the panel.
	 */
	@SuppressWarnings("unchecked")
	public ActivityFormPanel(JDialog parent) {
		GroupService gs = ServiceFactory.getService(GroupService.class); 
		UserService us = ServiceFactory.getService(UserService.class);
		
		setLayout(new BorderLayout(0, 0));
		this.parent = parent;		
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
		
		nameTextField_1 = new JTextField();
		GridBagConstraints gbc_nameTextField_1 = new GridBagConstraints();
		gbc_nameTextField_1.insets = new Insets(0, 0, 5, 5);
		gbc_nameTextField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameTextField_1.gridx = 1;
		gbc_nameTextField_1.gridy = 1;
		panel.add(nameTextField_1, gbc_nameTextField_1);
		nameTextField_1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Start Day:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 2;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		startDayTextField = new JTextField();
		startDayTextField.setMinimumSize(new Dimension(100, 20));
		GridBagConstraints gbc_startDayTextField = new GridBagConstraints();
		gbc_startDayTextField.anchor = GridBagConstraints.WEST;
		gbc_startDayTextField.insets = new Insets(0, 0, 5, 5);
		gbc_startDayTextField.gridx = 1;
		gbc_startDayTextField.gridy = 2;
		panel.add(startDayTextField, gbc_startDayTextField);
		startDayTextField.setColumns(10);
		
		JLabel lblStarttime = new JLabel("Start Time:");
		GridBagConstraints gbc_lblStarttime = new GridBagConstraints();
		gbc_lblStarttime.anchor = GridBagConstraints.EAST;
		gbc_lblStarttime.insets = new Insets(0, 0, 5, 5);
		gbc_lblStarttime.gridx = 0;
		gbc_lblStarttime.gridy = 3;
		panel.add(lblStarttime, gbc_lblStarttime);
		
		startTimeTextField = new JTextField();
		startTimeTextField.setMinimumSize(new Dimension(100, 20));
		GridBagConstraints gbc_startTimeTextField = new GridBagConstraints();
		gbc_startTimeTextField.anchor = GridBagConstraints.WEST;
		gbc_startTimeTextField.insets = new Insets(0, 0, 5, 5);
		gbc_startTimeTextField.gridx = 1;
		gbc_startTimeTextField.gridy = 3;
		panel.add(startTimeTextField, gbc_startTimeTextField);
		startTimeTextField.setColumns(10);
		
		JLabel lblEndday = new JLabel("End Day:");
		GridBagConstraints gbc_lblEndday = new GridBagConstraints();
		gbc_lblEndday.anchor = GridBagConstraints.EAST;
		gbc_lblEndday.insets = new Insets(0, 0, 5, 5);
		gbc_lblEndday.gridx = 0;
		gbc_lblEndday.gridy = 4;
		panel.add(lblEndday, gbc_lblEndday);
		
		endDayTextField = new JTextField();
		endDayTextField.setMinimumSize(new Dimension(100, 20));
		GridBagConstraints gbc_endDAyTextField = new GridBagConstraints();
		gbc_endDAyTextField.anchor = GridBagConstraints.WEST;
		gbc_endDAyTextField.insets = new Insets(0, 0, 5, 5);
		gbc_endDAyTextField.gridx = 1;
		gbc_endDAyTextField.gridy = 4;
		panel.add(endDayTextField, gbc_endDAyTextField);
		endDayTextField.setColumns(10);
		
		JLabel lblEndtime = new JLabel("End Time:");
		GridBagConstraints gbc_lblEndtime = new GridBagConstraints();
		gbc_lblEndtime.anchor = GridBagConstraints.EAST;
		gbc_lblEndtime.insets = new Insets(0, 0, 5, 5);
		gbc_lblEndtime.gridx = 0;
		gbc_lblEndtime.gridy = 5;
		panel.add(lblEndtime, gbc_lblEndtime);
		
		endTimeTextField = new JTextField();
		endTimeTextField.setMinimumSize(new Dimension(100, 20));
		GridBagConstraints gbc_endTimeTextField = new GridBagConstraints();
		gbc_endTimeTextField.anchor = GridBagConstraints.WEST;
		gbc_endTimeTextField.insets = new Insets(0, 0, 5, 5);
		gbc_endTimeTextField.gridx = 1;
		gbc_endTimeTextField.gridy = 5;
		panel.add(endTimeTextField, gbc_endTimeTextField);
		endTimeTextField.setColumns(10);
		
		JLabel lblTeacher = new JLabel("Teacher:");
		GridBagConstraints gbc_lblTeacher = new GridBagConstraints();
		gbc_lblTeacher.anchor = GridBagConstraints.EAST;
		gbc_lblTeacher.insets = new Insets(0, 0, 5, 5);
		gbc_lblTeacher.gridx = 0;
		gbc_lblTeacher.gridy = 6;
		panel.add(lblTeacher, gbc_lblTeacher);
		
		teacherComboBox = new JComboBox();
		teacherComboBox.setMinimumSize(new Dimension(100, 20));
		GridBagConstraints gbc_teacherComboBox = new GridBagConstraints();
		gbc_teacherComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_teacherComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_teacherComboBox.gridx = 1;
		gbc_teacherComboBox.gridy = 6;
		panel.add(teacherComboBox, gbc_teacherComboBox);
		DefaultComboBoxModel<String> dcbml = new DefaultComboBoxModel<String>();
		try {
			us.retrieveAll().stream().filter(o-> o.getProfile().getName().equals("Professor")).forEach(o -> dcbml.addElement(o.getLogin()));
		} catch (PersistenceException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		teacherComboBox.setModel(dcbml);
		
		JLabel lblActivitytype = new JLabel("Activity Type:");
		GridBagConstraints gbc_lblActivitytype = new GridBagConstraints();
		gbc_lblActivitytype.anchor = GridBagConstraints.EAST;
		gbc_lblActivitytype.insets = new Insets(0, 0, 5, 5);
		gbc_lblActivitytype.gridx = 0;
		gbc_lblActivitytype.gridy = 7;
		panel.add(lblActivitytype, gbc_lblActivitytype);
		
		activityTypeComboBox = new JComboBox();
		GridBagConstraints gbc_activityTypeComboBox = new GridBagConstraints();
		gbc_activityTypeComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_activityTypeComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_activityTypeComboBox.gridx = 1;
		gbc_activityTypeComboBox.gridy = 7;
		panel.add(activityTypeComboBox, gbc_activityTypeComboBox);
		DefaultComboBoxModel<String> cbm = new DefaultComboBoxModel<String>();
		Arrays.asList(ActivityType.values()).forEach(o -> cbm.addElement(o.getType()));
		activityTypeComboBox.setModel(cbm);
		
		JLabel lblGroup = new JLabel("Group:");
		GridBagConstraints gbc_lblGroup = new GridBagConstraints();
		gbc_lblGroup.anchor = GridBagConstraints.EAST;
		gbc_lblGroup.insets = new Insets(0, 0, 0, 5);
		gbc_lblGroup.gridx = 0;
		gbc_lblGroup.gridy = 8;
		panel.add(lblGroup, gbc_lblGroup);
		
		groupComboBox = new JComboBox();
		GridBagConstraints gbc_groupComboBox = new GridBagConstraints();
		gbc_groupComboBox.insets = new Insets(0, 0, 0, 5);
		gbc_groupComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_groupComboBox.gridx = 1;
		gbc_groupComboBox.gridy = 8;
		panel.add(groupComboBox, gbc_groupComboBox);
		DefaultComboBoxModel<String> dcbm = new DefaultComboBoxModel<String>();
		try {
			gs.retrieveAll().forEach(o -> dcbm.addElement(o.getName()));
		} catch (PersistenceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		groupComboBox.setModel(dcbm);
			
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.SOUTH);
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_2.add(okButton);
		
		JButton cancelButton = new JButton("Cancelar");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.dispose();
			}
		});
		panel_2.add(cancelButton);

	}
	public void setDomainModel(Activity domainData) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyyHH:mm:SS");
		String s;
		
		this.activity = domainData;
		nameTextField_1.setText(this.activity.getName());
		s = dtf.format(this.activity.getStartTime());
		startDayTextField.setText(s.substring(0, 10));
		startTimeTextField.setText(s.substring(10, 18));
		endDayTextField.setText(s.substring(0, 10));
		endTimeTextField.setText(s.substring(10, 18));		
	}

	public Activity getDomainModel() {
		ActivityService as = ServiceFactory.getService(ActivityService.class);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyyHH:mm:SS");
		
		try {
			if( this.activity == null ) {
				this.activity = new Activity(as.nextId()) ;  
			}
			this.activity.setName(nameTextField_1.getText());
			this.activity.setStartTime(LocalDateTime.parse(startDayTextField.getText()+startTimeTextField,dtf));
			this.activity.setEndTime(LocalDateTime.parse(endDayTextField.getText()+endTimeTextField, dtf));
			this.activity.setTeacher((User)teacherComboBox.getSelectedItem());			
			this.activity.setActivityType((ActivityType) activityTypeComboBox.getSelectedItem());
			this.activity.setGroup((Group)groupComboBox.getSelectedItem());			
		} catch (DateTimeParseException e ) {
			JOptionPane.showConfirmDialog(null, "Formato de data ou hora não válido. (dd/MM/yyyy HH:mm:SS)");
		} catch (PersistenceException e) {
			// TODO: show a dialog message
		}

		return this.activity;
	}

}
