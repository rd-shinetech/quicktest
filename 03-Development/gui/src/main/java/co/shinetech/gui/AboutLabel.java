/**
 * AboutLabel.java
 */
package co.shinetech.gui;

import java.awt.Graphics;

import javax.swing.JLabel;

/**
 * Customized JLabel for about windows.
 * @author Rodrigo
 */
public class AboutLabel extends JLabel {
	private String version;
	
	/**
	 * @param version
	 */
	public AboutLabel(String version) {
		super();
		this.version = version;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawString(version, 10, 250);		
	}
}
