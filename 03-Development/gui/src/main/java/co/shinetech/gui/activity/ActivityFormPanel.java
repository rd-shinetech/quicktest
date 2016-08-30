/*
 * ActivityFormPanel.java
 */
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
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * GUI class for Activity entity.
 * @author rdinis
 * @since 2016-08
 */
@SuppressWarnings("serial")
public class ActivityFormPanel extends JPanel implements DomainGetter<Activity> {
	private JTextField nameTextField;
	private JTextField startDayTextField;
	private JTextField endTimeTextField;
	private JTextField startTimeTextField;
	private JTextField endDayTextField;
	private JDialog parent;
	private Activity activity;
	private JComboBox<User> teacherComboBox;
	private JComboBox<ActivityType> activityTypeComboBox;
	private JComboBox<Group> groupComboBox;
	private JTable table;
	
	/**
	 * Create the panel.
	 */
	public ActivityFormPanel(JDialog parent) {
		GroupService gs = ServiceFactory.getService(GroupService.class); 
		UserService us = ServiceFactory.getService(UserService.class);
		
		setLayout(new BorderLayout(0, 0));
		this.parent = parent;		
		JPanel mainPanel = new JPanel();
		add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new BorderLayout(0, 0));
		DefaultComboBoxModel<User> dcbml = new DefaultComboBoxModel<User>();
		try {
			us.retrieveAll().stream().filter(o-> o.getProfile().getName().equals("Professor")).forEach(o -> dcbml.addElement(o));
		} catch (PersistenceException e2) {
			e2.printStackTrace();
		}
		DefaultComboBoxModel<ActivityType> cbm = new DefaultComboBoxModel<ActivityType>();
		Arrays.asList(ActivityType.values()).forEach(o -> cbm.addElement(o));
		DefaultComboBoxModel<Group> dcbm = new DefaultComboBoxModel<Group>();
		try {
			gs.retrieveAll().forEach(o -> dcbm.addElement(o));
		} catch (PersistenceException e1) {
			JOptionPane.showMessageDialog(parent, "Erro a carregar dados da base de dados.", "Erro de Persistência", JOptionPane.ERROR_MESSAGE);
		}
			
		JPanel controlPanel = new JPanel();
		controlPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		mainPanel.add(controlPanel, BorderLayout.SOUTH);
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		
		JPanel supportPanel = new JPanel();
		mainPanel.add(supportPanel, BorderLayout.CENTER);
		GridBagLayout gbl_supportPanel = new GridBagLayout();
		gbl_supportPanel.columnWidths = new int[]{614, 0, 0};
		gbl_supportPanel.rowHeights = new int[]{216, 216, 0};
		gbl_supportPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_supportPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		supportPanel.setLayout(gbl_supportPanel);
		
		JPanel dataPanel = new JPanel();
		GridBagConstraints gbc_dataPanel = new GridBagConstraints();
		gbc_dataPanel.weighty = 1.0;
		gbc_dataPanel.weightx = 1.0;
		gbc_dataPanel.fill = GridBagConstraints.BOTH;
		gbc_dataPanel.insets = new Insets(0, 0, 5, 5);
		gbc_dataPanel.gridx = 0;
		gbc_dataPanel.gridy = 0;
		supportPanel.add(dataPanel, gbc_dataPanel);
		dataPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Actividade", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagLayout gbl_dataPanel = new GridBagLayout();
		gbl_dataPanel.columnWidths = new int[]{79, 192, 0, 0};
		gbl_dataPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_dataPanel.columnWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_dataPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		dataPanel.setLayout(gbl_dataPanel);
		
		JLabel lblName = new JLabel("Name:");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 1;
		dataPanel.add(lblName, gbc_lblName);
		
		nameTextField = new JTextField();
		GridBagConstraints gbc_nameTextField = new GridBagConstraints();
		gbc_nameTextField.insets = new Insets(0, 0, 5, 5);
		gbc_nameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameTextField.gridx = 1;
		gbc_nameTextField.gridy = 1;
		dataPanel.add(nameTextField, gbc_nameTextField);
		nameTextField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Start Day:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 2;
		dataPanel.add(lblNewLabel, gbc_lblNewLabel);
		
		startDayTextField = new JTextField();
		startDayTextField.setMinimumSize(new Dimension(100, 20));
		GridBagConstraints gbc_startDayTextField = new GridBagConstraints();
		gbc_startDayTextField.anchor = GridBagConstraints.WEST;
		gbc_startDayTextField.insets = new Insets(0, 0, 5, 5);
		gbc_startDayTextField.gridx = 1;
		gbc_startDayTextField.gridy = 2;
		dataPanel.add(startDayTextField, gbc_startDayTextField);
		startDayTextField.setColumns(10);
		
		JLabel lblStarttime = new JLabel("Start Time:");
		GridBagConstraints gbc_lblStarttime = new GridBagConstraints();
		gbc_lblStarttime.anchor = GridBagConstraints.EAST;
		gbc_lblStarttime.insets = new Insets(0, 0, 5, 5);
		gbc_lblStarttime.gridx = 0;
		gbc_lblStarttime.gridy = 3;
		dataPanel.add(lblStarttime, gbc_lblStarttime);
		
		startTimeTextField = new JTextField();
		startTimeTextField.setMinimumSize(new Dimension(100, 20));
		GridBagConstraints gbc_startTimeTextField = new GridBagConstraints();
		gbc_startTimeTextField.anchor = GridBagConstraints.WEST;
		gbc_startTimeTextField.insets = new Insets(0, 0, 5, 5);
		gbc_startTimeTextField.gridx = 1;
		gbc_startTimeTextField.gridy = 3;
		dataPanel.add(startTimeTextField, gbc_startTimeTextField);
		startTimeTextField.setColumns(10);
		
		JLabel lblEndday = new JLabel("End Day:");
		GridBagConstraints gbc_lblEndday = new GridBagConstraints();
		gbc_lblEndday.anchor = GridBagConstraints.EAST;
		gbc_lblEndday.insets = new Insets(0, 0, 5, 5);
		gbc_lblEndday.gridx = 0;
		gbc_lblEndday.gridy = 4;
		dataPanel.add(lblEndday, gbc_lblEndday);
		
		endDayTextField = new JTextField();
		endDayTextField.setMinimumSize(new Dimension(100, 20));
		GridBagConstraints gbc_endDAyTextField = new GridBagConstraints();
		gbc_endDAyTextField.anchor = GridBagConstraints.WEST;
		gbc_endDAyTextField.insets = new Insets(0, 0, 5, 5);
		gbc_endDAyTextField.gridx = 1;
		gbc_endDAyTextField.gridy = 4;
		dataPanel.add(endDayTextField, gbc_endDAyTextField);
		endDayTextField.setColumns(10);
		
		JLabel lblEndtime = new JLabel("End Time:");
		GridBagConstraints gbc_lblEndtime = new GridBagConstraints();
		gbc_lblEndtime.anchor = GridBagConstraints.EAST;
		gbc_lblEndtime.insets = new Insets(0, 0, 5, 5);
		gbc_lblEndtime.gridx = 0;
		gbc_lblEndtime.gridy = 5;
		dataPanel.add(lblEndtime, gbc_lblEndtime);
		
		endTimeTextField = new JTextField();
		endTimeTextField.setMinimumSize(new Dimension(100, 20));
		GridBagConstraints gbc_endTimeTextField = new GridBagConstraints();
		gbc_endTimeTextField.anchor = GridBagConstraints.WEST;
		gbc_endTimeTextField.insets = new Insets(0, 0, 5, 5);
		gbc_endTimeTextField.gridx = 1;
		gbc_endTimeTextField.gridy = 5;
		dataPanel.add(endTimeTextField, gbc_endTimeTextField);
		endTimeTextField.setColumns(10);
		
		JLabel lblTeacher = new JLabel("Teacher:");
		GridBagConstraints gbc_lblTeacher = new GridBagConstraints();
		gbc_lblTeacher.anchor = GridBagConstraints.EAST;
		gbc_lblTeacher.insets = new Insets(0, 0, 5, 5);
		gbc_lblTeacher.gridx = 0;
		gbc_lblTeacher.gridy = 6;
		dataPanel.add(lblTeacher, gbc_lblTeacher);
		
		teacherComboBox = new JComboBox<User>();
		teacherComboBox.setMinimumSize(new Dimension(100, 20));
		GridBagConstraints gbc_teacherComboBox = new GridBagConstraints();
		gbc_teacherComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_teacherComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_teacherComboBox.gridx = 1;
		gbc_teacherComboBox.gridy = 6;
		dataPanel.add(teacherComboBox, gbc_teacherComboBox);
		teacherComboBox.setModel(dcbml);
		
		JLabel lblActivitytype = new JLabel("Activity Type:");
		GridBagConstraints gbc_lblActivitytype = new GridBagConstraints();
		gbc_lblActivitytype.anchor = GridBagConstraints.EAST;
		gbc_lblActivitytype.insets = new Insets(0, 0, 5, 5);
		gbc_lblActivitytype.gridx = 0;
		gbc_lblActivitytype.gridy = 7;
		dataPanel.add(lblActivitytype, gbc_lblActivitytype);
		
		activityTypeComboBox = new JComboBox<ActivityType>();
		GridBagConstraints gbc_activityTypeComboBox = new GridBagConstraints();
		gbc_activityTypeComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_activityTypeComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_activityTypeComboBox.gridx = 1;
		gbc_activityTypeComboBox.gridy = 7;
		dataPanel.add(activityTypeComboBox, gbc_activityTypeComboBox);
		activityTypeComboBox.setModel(cbm);
		
		JLabel lblGroup = new JLabel("Group:");
		GridBagConstraints gbc_lblGroup = new GridBagConstraints();
		gbc_lblGroup.anchor = GridBagConstraints.EAST;
		gbc_lblGroup.insets = new Insets(0, 0, 0, 5);
		gbc_lblGroup.gridx = 0;
		gbc_lblGroup.gridy = 8;
		dataPanel.add(lblGroup, gbc_lblGroup);
		
		groupComboBox = new JComboBox<Group>();
		GridBagConstraints gbc_groupComboBox = new GridBagConstraints();
		gbc_groupComboBox.insets = new Insets(0, 0, 0, 5);
		gbc_groupComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_groupComboBox.gridx = 1;
		gbc_groupComboBox.gridy = 8;
		dataPanel.add(groupComboBox, gbc_groupComboBox);
		groupComboBox.setModel(dcbm);
		
		JPanel questionsPanel = new JPanel();
		questionsPanel.setBorder(new TitledBorder(null, "Quest\u00F5es da Actividade", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_questionsPanel = new GridBagConstraints();
		gbc_questionsPanel.insets = new Insets(0, 0, 0, 5);
		gbc_questionsPanel.weighty = 1.0;
		gbc_questionsPanel.weightx = 1.0;
		gbc_questionsPanel.fill = GridBagConstraints.BOTH;
		gbc_questionsPanel.gridx = 0;
		gbc_questionsPanel.gridy = 1;
		supportPanel.add(questionsPanel, gbc_questionsPanel);
		questionsPanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		questionsPanel.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column"
			}
		));
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		questionsPanel.add(panel, BorderLayout.NORTH);

	}
	public void setDomainModel(Activity domainData) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyyHH:mm:SS");
		String s;
		
		this.activity = domainData;
		nameTextField.setText(this.activity.getName());
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
			this.activity.setName(nameTextField.getText());
			this.activity.setStartTime(LocalDateTime.parse(startDayTextField.getText()+startTimeTextField,dtf));
			this.activity.setEndTime(LocalDateTime.parse(endDayTextField.getText()+endTimeTextField, dtf));
			this.activity.setTeacher((User)teacherComboBox.getSelectedItem());			
			this.activity.setActivityType((ActivityType) activityTypeComboBox.getSelectedItem());
			this.activity.setGroup((Group)groupComboBox.getSelectedItem());			
		} catch (DateTimeParseException e ) {
			JOptionPane.showConfirmDialog(parent, "Formato de data ou hora não válido. (dd/MM/yyyy HH:mm:SS)");
		} catch (PersistenceException e) {
			JOptionPane.showMessageDialog(parent, "Erro a carregar dados da base de dados.", "Erro de Persistência", JOptionPane.ERROR_MESSAGE);
		}

		return this.activity;
	}

}
