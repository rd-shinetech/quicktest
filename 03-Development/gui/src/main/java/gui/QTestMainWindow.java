package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JProgressBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class QTestMainWindow {
	private JFrame frmQtest;
	private JProgressBar progressBar = new JProgressBar();
	private JLabel processingLabel = new JLabel("Processing...");

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
		frmQtest = new JFrame();
		frmQtest.setTitle("QTest 1.0");
		frmQtest.setBounds(100, 100, 800, 600);
		frmQtest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmQtest.setJMenuBar(menuBar);
		
		JMenu mnTabelas = new JMenu("Tabelas");
		menuBar.add(mnTabelas);
		
		JMenuItem mntmTurma = new JMenuItem("Turma...");
		mnTabelas.add(mntmTurma);
		
		JMenuItem mntmQuesto = new JMenuItem("Quest\u00E3o...");
		mnTabelas.add(mntmQuesto);
		
		JMenuItem mntmAtividade = new JMenuItem("Atividade...");
		mnTabelas.add(mntmAtividade);
		
		JSeparator separator = new JSeparator();
		mnTabelas.add(separator);
		
		JMenuItem mntmSada = new JMenuItem("Sa\u00EDda");
		mnTabelas.add(mntmSada);
		
		JMenu mnExecutar = new JMenu("Teste");
		menuBar.add(mnExecutar);
		
		JMenuItem mntmCorrer = new JMenuItem("Correr...");
		mnExecutar.add(mntmCorrer);
		
		JMenuItem mntmResultado = new JMenuItem("Ver Resultado...");
		mnExecutar.add(mntmResultado);
		
		JMenu mnSegurana = new JMenu("Seguran\u00E7a");
		menuBar.add(mnSegurana);
		
		JMenuItem mntmPerfil = new JMenuItem("Perfil...");
		mnSegurana.add(mntmPerfil);
		
		JMenuItem userMenu = new JMenuItem("Utilizador...");
		mnSegurana.add(userMenu);
		
		JMenu mnAjuda = new JMenu("Ajuda");
		menuBar.add(mnAjuda);
		
		JMenuItem mntmAjuda = new JMenuItem("Ajuda");
		mnAjuda.add(mntmAjuda);
		frmQtest.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel topPanel = new JPanel();
		topPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		topPanel.setBackground(Color.WHITE);
		frmQtest.getContentPane().add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("<html><body>Testing Software Platform<br>QTest version 1.0<br> &nbsp;</body></html>");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel.setIcon(new ImageIcon(QTestMainWindow.class.getResource("/image/test.gif")));
		topPanel.add(lblNewLabel, BorderLayout.WEST);
		
		JLabel lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setIcon(new ImageIcon(QTestMainWindow.class.getResource("/image/shine-small.png")));
		topPanel.add(lblNewLabel_1, BorderLayout.EAST);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		topPanel.add(toolBar, BorderLayout.SOUTH);
		
		JButton classButton = new JButton("Turma");
		classButton.addActionListener(e -> {}); // --> change anonymous to Lambda Expressions.
		toolBar.add(classButton);
		
		JButton questionButton = new JButton("Quest\u00E3o");
		toolBar.add(questionButton);
		
		JButton activityButton = new JButton("Atividade");
		toolBar.add(activityButton);
		
		JLabel label = new JLabel("|");
		toolBar.add(label);
		
		JButton runButton = new JButton("Correr Teste");
		toolBar.add(runButton);
		runButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnVerResultado = new JButton("Ver Resultado");
		toolBar.add(btnVerResultado);
		
		JLabel label_1 = new JLabel("|");
		toolBar.add(label_1);
		
		JButton btnPerfil = new JButton("Perfil");
		toolBar.add(btnPerfil);
		
		JButton btnUtilizador = new JButton("Utilizador");
		toolBar.add(btnUtilizador);
		
		JPanel bottomPanel = new JPanel();
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
		panel.add(progressBar);
	}
}
