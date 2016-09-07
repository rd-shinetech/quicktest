package co.shinetech.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.SplashScreen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import co.shinetech.dto.Activity;
import co.shinetech.dto.ActivityArea;
import co.shinetech.dto.Group;
import co.shinetech.dto.Profile;
import co.shinetech.dto.QuestionType;
import co.shinetech.dto.User;
import co.shinetech.gui.activity.ActivityDataPanel;
import co.shinetech.gui.activityarea.ActivityAreaDataPanel;
import co.shinetech.gui.auth.AuthPanel;
import co.shinetech.gui.group.GroupDataPanel;
import co.shinetech.gui.profile.ProfileDataPanel;
import co.shinetech.gui.table.DynamicTableModel;
import co.shinetech.gui.table.GridDataPanel;
import co.shinetech.gui.user.UserDataPanel;

public class QTestMainWindow {
	private JFrame frmQtest;
	private JPanel currentPanel;
	private static JProgressBar progressBar = new JProgressBar();
	private static JLabel processingLabel = new JLabel("A Processar...");
	private static Properties sysProperties;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		GUIUtils.setUIFont(new javax.swing.plaf.FontUIResource("Arial",Font.PLAIN,12));		

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				SplashScreen splash = SplashScreen.getSplashScreen();
				
				if( splash != null ) {
					Graphics2D g2d = splash.createGraphics();
					
					for( int i = 0; i < 20; i++ ) {
						if( i == 0 ) {
							loadSysProperties();
						}
						GUIUtils.renderSplashFrame(g2d,i,sysProperties.getProperty("version", ""));
						splash.update();
						try {
							Thread.sleep(400);
						} catch (InterruptedException e) {
						}
					}
					splash.close();
				}				
			}
		});
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				JDialog d = new JDialog();
				d.setTitle("Autenticação de Utilizador");
				AuthPanel authPanel = new AuthPanel(d);
				
				d.setModal(true);
				d.setResizable(false);
				d.add(authPanel);
				d.pack(); 
				GUIUtils.centerOnParent(d, true);
				d.setVisible(true);
		
				if( ! authPanel.isCancelled() ) {
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								QTestMainWindow window = new QTestMainWindow();
								window.frmQtest.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public QTestMainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e1) {
		}
		frmQtest = new JFrame();
		frmQtest.setTitle("QuickTest "+sysProperties.getProperty("version"));
		frmQtest.setBounds(100, 100, 800, 600);
		frmQtest.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		frmQtest.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				if( exitConfirmation() == 0 ) {
					System.exit(0);
				}
			}
		});
		JMenuBar menuBar = new JMenuBar();
		frmQtest.setJMenuBar(menuBar);
		
		JMenu tableMenu = new JMenu("Tabelas");
		menuBar.add(tableMenu);
		
		JMenuItem groupMenuItem = new JMenuItem("Turma...");
		groupMenuItem.addActionListener(getGroupActionListener());
		tableMenu.add(groupMenuItem);
		
		JMenuItem questionMenuItem = new JMenuItem("Quest\u00E3o...");
		groupMenuItem.addActionListener(getQuestionTypeActionListener());
		tableMenu.add(questionMenuItem);
		
		JMenuItem activityMenuItem = new JMenuItem("Actividade...");
		groupMenuItem.addActionListener(getActivityActionListener());
		tableMenu.add(activityMenuItem);
		
		JMenuItem activityAreaMenuItem = new JMenuItem("\u00C1reas de actividade...");
		groupMenuItem.addActionListener(getActivityAreaActionListener());
		tableMenu.add(activityAreaMenuItem);
		
		JSeparator separator = new JSeparator();
		tableMenu.add(separator);
		
		JMenuItem exitMenuItem = new JMenuItem("Sa\u00EDda");
		exitMenuItem.addActionListener(getExitActionListener());
		tableMenu.add(exitMenuItem);
		
		JMenu runMenu = new JMenu("Teste");
		menuBar.add(runMenu);
		
		JMenuItem mntmCorrer = new JMenuItem("Correr...");
		runMenu.add(mntmCorrer);
		
		JMenuItem mntmResultado = new JMenuItem("Ver Resultado...");
		runMenu.add(mntmResultado);
		
		JMenu securityMenu = new JMenu("Seguran\u00E7a");
		menuBar.add(securityMenu);
		
		JMenuItem profileMenuItem = new JMenuItem("Perfil...");
		securityMenu.add(profileMenuItem);
		
		JMenuItem userMenuItem = new JMenuItem("Utilizador...");
		securityMenu.add(userMenuItem);
		
		JMenu helpMenu = new JMenu("Ajuda");
		menuBar.add(helpMenu);
		
		JMenuItem helpMenuItem = new JMenuItem("Ajuda");
		helpMenuItem.setMnemonic(KeyEvent.VK_F1);
		helpMenu.add(helpMenuItem);
		
		JSeparator separator_1 = new JSeparator();
		helpMenu.add(separator_1);
		
		JMenuItem mntmSobre = new JMenuItem("Sobre...");
		mntmSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog d = new JDialog();
				
				d.getContentPane().add(new AboutPanel(d,sysProperties.getProperty("version","")));
				d.pack();
				GUIUtils.centerOnParent(d, true);
				d.setVisible(true);
			}
		});
		helpMenu.add(mntmSobre);
		frmQtest.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel topPanel = new JPanel();
		topPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		topPanel.setBackground(Color.WHITE);
		frmQtest.getContentPane().add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel qTestImageLabel = new JLabel("<html><body>Plataforma para Actividades Educationais<br><br> &nbsp;</body></html>");
		qTestImageLabel.setHorizontalAlignment(SwingConstants.LEFT);
		qTestImageLabel.setVerticalAlignment(SwingConstants.TOP);
		qTestImageLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		qTestImageLabel.setIcon(new ImageIcon(QTestMainWindow.class.getResource("/image/test.gif")));
		topPanel.add(qTestImageLabel, BorderLayout.WEST);
		
		JLabel shineLabel = new JLabel();
		shineLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		shineLabel.setIcon(new ImageIcon(QTestMainWindow.class.getResource("/image/shine-small.png")));
		topPanel.add(shineLabel, BorderLayout.EAST);
		
		JToolBar toolBar = new JToolBar();
		topPanel.add(toolBar, BorderLayout.SOUTH);
		
		JButton classButton = new JButton("Turma");
		classButton.addActionListener(getGroupActionListener());
		toolBar.add(classButton);
		
		JButton questionButton = new JButton("Quest\u00E3o");
		toolBar.add(questionButton);		
		questionButton.addActionListener(getQuestionTypeActionListener());
		questionMenuItem.addActionListener(getQuestionTypeActionListener());
	    toolBar.add(questionButton);
		
		JButton activityButton = new JButton("Atividade");
		activityButton.addActionListener(getActivityActionListener());
		activityMenuItem.addActionListener(getActivityActionListener());
		
		JButton activityAreaButton = new JButton("\u00C1reas de Atividade");
		toolBar.add(activityAreaButton);
		toolBar.add(activityButton);
		activityAreaButton.addActionListener(getActivityAreaActionListener());
		activityAreaMenuItem.addActionListener(getActivityAreaActionListener());
		
		toolBar.addSeparator();
		
		JButton runButton = new JButton("Correr Teste");
		toolBar.add(runButton);
		
		JButton showResultButton = new JButton("Ver Resultado");
		toolBar.add(showResultButton);
		
		toolBar.addSeparator();
		
		JButton profileButton = new JButton("Perfil");
		profileButton.addActionListener(getProfileActionListener());
		toolBar.add(profileButton);
		profileMenuItem.addActionListener(getProfileActionListener());
		
		JButton userButton = new JButton("Utilizador");
		userButton.addActionListener(getUserActionListener());
		toolBar.add(userButton);
		userMenuItem.addActionListener(getUserActionListener());
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setMaximumSize(new Dimension(10, 30));
		bottomPanel.setMinimumSize(new Dimension(10, 30));
		bottomPanel.setPreferredSize(new Dimension(10, 30));
		bottomPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		frmQtest.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setLayout(new BorderLayout(0, 50));
		
		JLabel shinetechLabel = new JLabel("  ShineTech 2016");
		shinetechLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		bottomPanel.add(shinetechLabel, BorderLayout.WEST);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		bottomPanel.add(panel, BorderLayout.EAST);
		
		panel.add(processingLabel);
		processingLabel.setVisible(false);
		progressBar.setIndeterminate(true);
		panel.add(progressBar);
		progressBar.setVisible(false);
	}
	
	public static void processStart() {
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				@Override
				public void run() {
					progressBar.setVisible(true);
					processingLabel.setVisible(true);
				}
			});
		} catch (InvocationTargetException e) {
		} catch (InterruptedException e) {
		}		
	}
	
	public static void processUpdate(int pos) {
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				@Override
				public void run() {
					progressBar.setIndeterminate(false);
					progressBar.setValue(pos);
				}
			});
		} catch (InvocationTargetException e) {
		} catch (InterruptedException e) {
		}				
	}
	
	public static void processEnd() {
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				@Override
				public void run() {
					progressBar.setVisible(false);
					processingLabel.setVisible(false);
				}
			});
		} catch (InvocationTargetException e) {
		} catch (InterruptedException e) {
		}				
	}
	
	private ActionListener getGroupActionListener() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DynamicTableModel dtm = new DynamicTableModel(Group.class);
				
				dtm.setTblTitle(new String[] {"Código","Nome"});		// Table columns header		
				dtm.setTblFields(new String[]{"pk","name"});
				GroupDataPanel cdp = new GroupDataPanel(dtm);
				setCurrentPanel(cdp);
			}
		};
	}
	
	private ActionListener getQuestionTypeActionListener() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DynamicTableModel dtm = new DynamicTableModel(QuestionType.class);
				
				dtm.setTblTitle(new String[] {"Código","Tipo"});		// Table columns header		
				dtm.setTblFields(new String[]{"pk","name"});
				ActivityAreaDataPanel aadp = new ActivityAreaDataPanel(dtm);    
				setCurrentPanel(aadp);
			}
		};		
	} 
	
	private ActionListener getActivityAreaActionListener() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DynamicTableModel dtm = new DynamicTableModel(ActivityArea.class);
				
				dtm.setTblTitle(new String[] {"Código","Nome"});		// Table columns header		
				dtm.setTblFields(new String[]{"pk","name"});
				ActivityAreaDataPanel aadp = new ActivityAreaDataPanel(dtm);
				setCurrentPanel(aadp);
			}
		};		
	}
		
	private ActionListener getUserActionListener() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DynamicTableModel dtm = new DynamicTableModel(User.class);
				
				dtm.setTblTitle(new String[] {"ID", "Login", "Perfil"});
				dtm.setTblFields(new String[] {"pk", "login","profile"});
				UserDataPanel udp = new UserDataPanel(dtm);
				setCurrentPanel(udp);
			}
		};		
	}
	
	private ActionListener getProfileActionListener() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DynamicTableModel dtm = new DynamicTableModel(Profile.class);
				
				dtm.setTblTitle(new String[] {"ID", "Nome"});
				dtm.setTblFields(new String[] {"pk", "name"});
				ProfileDataPanel pdp = new ProfileDataPanel(dtm);
				if (currentPanel != null)
					frmQtest.getContentPane().remove(currentPanel);
				currentPanel = pdp;
				frmQtest.getContentPane().add(pdp, BorderLayout.CENTER);
				frmQtest.revalidate();
			}
		};
	}
	
	private ActionListener getActivityActionListener() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DynamicTableModel dtm = new DynamicTableModel(Activity.class);
				
				dtm.setTblTitle(new String[] {"ID", "Nome", "Hora Inicial", "Hora Final", "Professor", "Tipo", "Grupo Execução"});
				dtm.setTblFields(new String[] {"pk", "name", "startTime", "endTime", "teacher", "activityType", "group"});
				ActivityDataPanel adp = new ActivityDataPanel(dtm);
				if (currentPanel != null)
					frmQtest.getContentPane().remove(currentPanel);
				currentPanel = adp;
				frmQtest.getContentPane().add(adp, BorderLayout.CENTER);
				frmQtest.revalidate();				
			}
		};
	}
	
	private ActionListener getExitActionListener() {
		return new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if( exitConfirmation() == 0 ) {
					System.exit(0);
				}
			}
		};
	}
	
	/**
	 * Method to set the grid data panel in the main frame.
	 * @param cdp
	 */
	private void setCurrentPanel(GridDataPanel cdp) {
		if (currentPanel != null)
			frmQtest.getContentPane().remove(currentPanel);
		currentPanel = cdp;
		frmQtest.getContentPane().add(cdp, BorderLayout.CENTER); // adding the new panel to the frame		
		frmQtest.revalidate(); // invalidate the frame to paint again with new panel added.	
	}
	
	private static void loadSysProperties() {
		InputStream input = QTestMainWindow.class.getClassLoader().getResourceAsStream("qtest.properties");
		
		sysProperties = new Properties();
		
		if( QTestMainWindow.class.getPackage().getImplementationVersion() != null ) {
			sysProperties.setProperty("version", QTestMainWindow.class.getPackage().getImplementationVersion());
		}
		if (input == null) {
			return;
		}

		// load a properties file from class path, inside static method
		try {
			sysProperties.load(input);
		} catch (IOException e) {
		}
	}
	
	private int exitConfirmation() {
		Object[] options = { "Sim", "Não" }; 
        return JOptionPane.showOptionDialog(frmQtest, "Deseja mesmo sair da aplicação?","Confirmação", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);		
	}
}