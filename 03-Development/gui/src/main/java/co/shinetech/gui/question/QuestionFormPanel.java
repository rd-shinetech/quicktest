/**
 * QuestionFormPanel.java
 */
package co.shinetech.gui.question;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import co.shinetech.dao.db.PersistenceException;
import co.shinetech.dto.ActivityArea;
import co.shinetech.dto.Question;
import co.shinetech.dto.QuestionType;
import co.shinetech.gui.DomainGetter;
import co.shinetech.service.ServiceFactory;
import co.shinetech.service.impl.ActivityAreaService;
import co.shinetech.service.impl.ProfileService;
import co.shinetech.service.impl.QuestionService;
import javax.swing.JComboBox;
import java.awt.Color;

/**
 * Form gui for question.
 * @author Ricardo
 * @since 2016-08
 */
@SuppressWarnings("serial")
public class QuestionFormPanel extends JPanel implements DomainGetter<Question> {
	private JTextField questionTextField;
	private JTextField answerTextField;
	private JComboBox<String> questionTypeComboBox;
	private JComboBox<ActivityArea> activityAreaComboBox;
	private JButton cancelButton;
	private JDialog parent;
	private Question question;
	
	public QuestionFormPanel(JDialog parent) {
		
		setLayout(new BorderLayout(0, 0));
		this.parent = parent;
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Catálogo de Questões", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {0, 1, 0};
		gbl_panel.rowHeights = new int[] {0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		panel.setLayout(gbl_panel);
		
		JLabel lblTipoDaQuesto = new JLabel("Tipo:");
		GridBagConstraints gbc_lblTipoDaQuesto = new GridBagConstraints();
		gbc_lblTipoDaQuesto.anchor = GridBagConstraints.EAST;
		gbc_lblTipoDaQuesto.insets = new Insets(5, 5, 5, 5);
		gbc_lblTipoDaQuesto.gridx = 1;
		gbc_lblTipoDaQuesto.gridy = 0;
		panel.add(lblTipoDaQuesto, gbc_lblTipoDaQuesto);
		
		questionTypeComboBox = new JComboBox<String>();
		DefaultComboBoxModel<String> dcbm = new DefaultComboBoxModel<String>(new String[] {QuestionType.MULTIPLE_CHOICE.getName(),
																						   QuestionType.TEXTUAL.getName()});
		questionTypeComboBox.setModel(dcbm);
		questionTypeComboBox.setMinimumSize(new Dimension(250, 20));
		questionTypeComboBox.setPreferredSize(new Dimension(250, 20));
		GridBagConstraints gbc_questionTypeComboBox = new GridBagConstraints();
		gbc_questionTypeComboBox.anchor = GridBagConstraints.WEST;
		gbc_questionTypeComboBox.insets = new Insets(5, 0, 5, 10);
		gbc_questionTypeComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_questionTypeComboBox.gridx = 2;
		gbc_questionTypeComboBox.gridy = 0;
		panel.add(questionTypeComboBox, gbc_questionTypeComboBox);
		
		JLabel lblTemaDaQuesto = new JLabel("Tema:");
		GridBagConstraints gbc_lblTemaDaQuesto = new GridBagConstraints();
		gbc_lblTemaDaQuesto.anchor = GridBagConstraints.EAST;
		gbc_lblTemaDaQuesto.insets = new Insets(5, 5, 5, 5);
		gbc_lblTemaDaQuesto.gridx = 1;
		gbc_lblTemaDaQuesto.gridy = 1;
		panel.add(lblTemaDaQuesto, gbc_lblTemaDaQuesto);
		
		activityAreaComboBox = new JComboBox<>();
		activityAreaComboBox.setMinimumSize(new Dimension(250, 20));
		activityAreaComboBox.setPreferredSize(new Dimension(250, 20));		
		ActivityAreaService aas = ServiceFactory.getService(ActivityAreaService.class);
		try {
			aas.retrieveAll().forEach(p -> activityAreaComboBox.addItem(p));
		}
		catch (PersistenceException e) {
			e.printStackTrace();
		}	
		
		GridBagConstraints gbc_questionAreaComboBox = new GridBagConstraints();
		gbc_questionAreaComboBox.anchor = GridBagConstraints.WEST;
		gbc_questionAreaComboBox.insets = new Insets(5, 0, 5, 10);
		gbc_questionAreaComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_questionAreaComboBox.gridx = 2;
		gbc_questionAreaComboBox.gridy = 1;
		panel.add(activityAreaComboBox, gbc_questionAreaComboBox);
		
		JLabel lblQuestion = new JLabel("Quest\u00E3o:");
		GridBagConstraints gbc_lblQuestion = new GridBagConstraints();
		gbc_lblQuestion.insets = new Insets(5, 5, 5, 5);
		gbc_lblQuestion.anchor = GridBagConstraints.EAST;
		gbc_lblQuestion.gridx = 1;
		gbc_lblQuestion.gridy = 2;
		panel.add(lblQuestion, gbc_lblQuestion);
		
		questionTextField = new JTextField();		
		questionTextField.setPreferredSize(new Dimension(300, 20));
		questionTextField.setMinimumSize(new Dimension(300, 20));
		questionTextField.setMaximumSize(new Dimension(300, 20));
		GridBagConstraints gbc_questionTextField = new GridBagConstraints();
		gbc_questionTextField.weightx = 1.0;
		gbc_questionTextField.gridwidth = 2;
		gbc_questionTextField.anchor = GridBagConstraints.EAST;
		gbc_questionTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_questionTextField.insets = new Insets(5, 0, 5, 10);
		gbc_questionTextField.gridx = 2;
		gbc_questionTextField.gridy = 2;
		panel.add(questionTextField, gbc_questionTextField);
		questionTextField.setColumns(30);
		
		JLabel lblAnswer = new JLabel("Resposta:");
		GridBagConstraints gbc_lblAnswer = new GridBagConstraints();
		gbc_lblAnswer.anchor = GridBagConstraints.EAST;
		gbc_lblAnswer.insets = new Insets(5, 5, 0, 5);
		gbc_lblAnswer.gridx = 1;
		gbc_lblAnswer.gridy = 3;
		panel.add(lblAnswer, gbc_lblAnswer);
		
		answerTextField = new JTextField();
		GridBagConstraints gbc_answerTextField = new GridBagConstraints();
		gbc_answerTextField.gridwidth = 2;
		gbc_answerTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_answerTextField.anchor = GridBagConstraints.WEST;
		gbc_answerTextField.insets = new Insets(5, 0, 0, 10);
		gbc_answerTextField.gridx = 2;
		gbc_answerTextField.gridy = 3;
		panel.add(answerTextField, gbc_answerTextField);
		answerTextField.setColumns(10);
		
		JPanel controlPanel = new JPanel();
		controlPanel.setBackground(Color.LIGHT_GRAY);
		controlPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(controlPanel, BorderLayout.SOUTH);
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QuestionService qs = ServiceFactory.getService(QuestionService.class);
				Question q = getDomainModel();
				try {
					if (qs.retrieveByID((int)q.getPk()) != null)
						qs.update(q);
					else
						qs.create(q);
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
	public void setDomainModel(Question domainData) {
		this.question = domainData;
		questionTextField.setText(domainData.getQuestion());
		answerTextField.setText(domainData.getAnswer());		
	}

	@Override
	public Question getDomainModel() {
		QuestionService qs = ServiceFactory.getService(QuestionService.class);

		try {
			if( this.question == null ) {
				this.question = new Question(qs.nextId());
			}
			else
				this.question.setQuestion(questionTextField.getText());

		} catch (PersistenceException e) {
			JOptionPane.showMessageDialog(parent, "Erro a carregar da base de dados.", "Erro de Persistência", JOptionPane.ERROR_MESSAGE);
		}
		return this.question;
	}
}
	
