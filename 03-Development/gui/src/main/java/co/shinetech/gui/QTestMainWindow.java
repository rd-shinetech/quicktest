package co.shinetech.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import co.shinetech.dto.Activity;
import co.shinetech.dto.Group;
import co.shinetech.dto.Profile;
import co.shinetech.dto.User;
import co.shinetech.gui.activity.ActivityDataPanel;
import co.shinetech.gui.group.GroupDataPanel;
import co.shinetech.gui.profile.ProfileDataPanel;
import co.shinetech.gui.table.DynamicTableModel;
import co.shinetech.gui.user.UserDataPanel;
import java.awt.Dimension;
import java.awt.event.KeyEvent;

public class QTestMainWindow {
	private JFrame frmQtest;
	private JPanel currentPanel;
	private static JProgressBar progressBar = new JProgressBar();
	private static JLabel processingLabel = new JLabel("Processing...");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
		frmQtest.setTitle("QTest 1.0");
		frmQtest.setBounds(100, 100, 800, 600);
		frmQtest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmQtest.setJMenuBar(menuBar);
		
		JMenu tableMenu = new JMenu("Tabelas");
		menuBar.add(tableMenu);
		
		JMenuItem mntmTurma = new JMenuItem("Turma...");
		mntmTurma.addActionListener(getGroupActionListener());
		tableMenu.add(mntmTurma);
		
		JMenuItem mntmQuesto = new JMenuItem("Quest\u00E3o...");
		tableMenu.add(mntmQuesto);
		
		JMenuItem mntmAtividade = new JMenuItem("Atividade...");
		tableMenu.add(mntmAtividade);
		
		JSeparator separator = new JSeparator();
		tableMenu.add(separator);
		
		JMenuItem mntmSada = new JMenuItem("Sa\u00EDda");
		tableMenu.add(mntmSada);
		
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
		helpMenu.add(mntmSobre);
		frmQtest.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel topPanel = new JPanel();
		topPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		topPanel.setBackground(Color.WHITE);
		frmQtest.getContentPane().add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel qTestImageLabel = new JLabel("<html><body>Testing Software Platform<br>QTest version 1.0<br> &nbsp;</body></html>");
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
		questionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		toolBar.add(questionButton);
		
		JButton activityButton = new JButton("Atividade");
		activityButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DynamicTableModel dtm = new DynamicTableModel(Activity.class);
				dtm.setTblTitle(new String[] {"ID", "Name", "StartTime", "EndTime", "Teacher", "ActivityType", "Group"});
				dtm.setTblFields(new String[] {"pk", "name", "startTime", "endTime", "teacher", "activityType", "group"});
				ActivityDataPanel adp = new ActivityDataPanel(dtm);
				if (currentPanel != null)
					frmQtest.getContentPane().remove(currentPanel);
				currentPanel = adp;
				frmQtest.getContentPane().add(adp, BorderLayout.CENTER);
				frmQtest.revalidate();				
			}
		});
		toolBar.add(activityButton);
		
		toolBar.addSeparator();
		
		JButton runButton = new JButton("Correr Teste");
		toolBar.add(runButton);
		
		JButton btnVerResultado = new JButton("Ver Resultado");
		toolBar.add(btnVerResultado);
		
		toolBar.addSeparator();
		
		JButton btnPerfil = new JButton("Perfil");
		btnPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DynamicTableModel dtm = new DynamicTableModel(Profile.class);
				dtm.setTblTitle(new String[] {"ID", "Name"});
				dtm.setTblFields(new String[] {"pk", "name"});
				ProfileDataPanel pdp = new ProfileDataPanel(dtm);
				if (currentPanel != null)
					frmQtest.getContentPane().remove(currentPanel);
				currentPanel = pdp;
				frmQtest.getContentPane().add(pdp, BorderLayout.CENTER);
				frmQtest.revalidate();
			}
		});
		toolBar.add(btnPerfil);
		
		JButton btnUtilizador = new JButton("Utilizador");
		btnUtilizador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DynamicTableModel dtm = new DynamicTableModel(User.class);
				dtm.setTblTitle(new String[] {"ID", "Login", "Password", "Profile"});
				dtm.setTblFields(new String[] {"pk", "login", "password", "profile"});
				UserDataPanel udp = new UserDataPanel(dtm);
				if (currentPanel != null)
					frmQtest.getContentPane().remove(currentPanel);
				currentPanel = udp;
				frmQtest.getContentPane().add(udp, BorderLayout.CENTER);
				frmQtest.revalidate();
			}
		});
		toolBar.add(btnUtilizador);
		
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
				if (currentPanel != null)
					frmQtest.getContentPane().remove(currentPanel);
				currentPanel = cdp;
				frmQtest.getContentPane().add(cdp, BorderLayout.CENTER); // adding the new panel to the frame		
				frmQtest.revalidate(); // invalidate the frame to paint again with new panel added.
			}
		};
	}
}