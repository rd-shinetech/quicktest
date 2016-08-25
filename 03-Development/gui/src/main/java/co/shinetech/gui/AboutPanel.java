package co.shinetech.gui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class AboutPanel extends JPanel {
	private JDialog parent;
	
	/**
	 * Create the panel.
	 */
	public AboutPanel(JDialog parent,String version) {
		this.parent = parent;
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);
		
		JButton okButton = new JButton("Ok");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.dispose();
			}
		});
		panel.add(okButton);
		
		JPanel bodyPanel = new JPanel();
		add(bodyPanel, BorderLayout.CENTER);
		bodyPanel.setLayout(new BorderLayout(0, 0));
		
		AboutLabel label = new AboutLabel(version);
		label.setIcon(new ImageIcon(AboutPanel.class.getResource("/image/splash.png")));
		bodyPanel.add(label, BorderLayout.CENTER);
	}
}
