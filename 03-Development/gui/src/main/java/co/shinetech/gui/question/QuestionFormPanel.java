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

/**
 * Form gui for question.
 * @author Ricardo
 * @since 2016-08
 */
/* @SuppressWarnings("serial")
public class QuestionFormPanel extends JPanel implements DomainGetter<Question> {
	} */
