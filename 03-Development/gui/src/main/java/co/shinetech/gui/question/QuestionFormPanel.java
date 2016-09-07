/**
 * 
 */
package co.shinetech.gui.question;

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
import javax.swing.border.TitledBorder;

import co.shinetech.dao.db.PersistenceException;
import co.shinetech.dto.ActivityArea;
import co.shinetech.dto.Question;
import co.shinetech.gui.DomainGetter;
import co.shinetech.service.ServiceFactory;
import co.shinetech.service.impl.ActivityAreaService;
import co.shinetech.service.impl.ProfileService;
import co.shinetech.service.impl.QuestionService;
import java.awt.Panel;

/**
 * Form gui for question.
 * @author Ricardo
 * @since 2016-08
 */
@SuppressWarnings("serial")
public class QuestionFormPanel extends JPanel implements DomainGetter<Question> {
	private JTextField questionTextField;
	private JTextField answerTextField;
	private Question question;
	private JButton cancelButton;
	private JDialog parent;
	
	public QuestionFormPanel(JDialog parent) {
		
		setLayout(new BorderLayout(0, 0));
		this.parent = parent;
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Catálogo de Questões", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {0};
		gbl_panel.rowHeights = new int[] {0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0};
		gbl_panel.rowWeights = new double[]{0.0};
		panel.setLayout(gbl_panel);
		
		// JPanel panel = new JPanel();
		// add(panel);
				
		JLabel lblQuestion = new JLabel("Quest\u00E3o");
		GridBagConstraints gbc_lblQuestion = new GridBagConstraints();
		gbc_lblQuestion.insets = new Insets(10, 10, 10, 5);
		gbc_lblQuestion.anchor = GridBagConstraints.EAST;
		gbc_lblQuestion.gridx = 0;
		gbc_lblQuestion.gridy = 0;
		panel.add(lblQuestion, gbc_lblQuestion);
		
		questionTextField = new JTextField();		
		questionTextField.setPreferredSize(new Dimension(300, 20));
		questionTextField.setMinimumSize(new Dimension(300, 20));
		questionTextField.setMaximumSize(new Dimension(300, 20));
		GridBagConstraints gbc_questionTextField = new GridBagConstraints();
		gbc_questionTextField.weighty = 1.0;
		gbc_questionTextField.weightx = 1.0;
		gbc_questionTextField.anchor = GridBagConstraints.EAST;
		gbc_questionTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_questionTextField.insets = new Insets(10, 5, 10, 10);
		gbc_questionTextField.gridx = 1;
		gbc_questionTextField.gridy = 0;
		panel.add(questionTextField, gbc_questionTextField);
		questionTextField.setColumns(30);
		
		JLabel lblAnswer = new JLabel("Resposta");
		panel.add(lblAnswer);
		
		answerTextField = new JTextField();
		panel.add(answerTextField);
		answerTextField.setColumns(10);
		
		JPanel controlPanel = new JPanel();
		controlPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(controlPanel);
		
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
		questionTextField.setText(this.question.getQuestion());
		answerTextField.setText(this.question.getAnswer());		
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
	
