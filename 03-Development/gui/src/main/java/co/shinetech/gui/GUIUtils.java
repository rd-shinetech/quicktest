/**
 * GUIUtils.java
 */
package co.shinetech.gui;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Window;
import java.util.Enumeration;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 * @author Rodrigo
 * @since 2016-08-02
 */
public final class GUIUtils {
	private GUIUtils() {}
	
	public static void centerOnParent(final Window child, final boolean absolute) {
	    child.pack();
	    boolean useChildsOwner = child.getOwner() != null ? ((child.getOwner() instanceof JFrame) || (child.getOwner() instanceof JDialog)) : false;
	    final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    final Dimension parentSize = useChildsOwner ? child.getOwner().getSize() : screenSize ;
	    final Point parentLocationOnScreen = useChildsOwner ? child.getOwner().getLocationOnScreen() : new Point(0,0) ;
	    final Dimension childSize = child.getSize();
	    childSize.width = Math.min(childSize.width, screenSize.width);
	    childSize.height = Math.min(childSize.height, screenSize.height);
	    child.setSize(childSize);        
	    int x;
	    int y;
	    if ((child.getOwner() != null) && child.getOwner().isShowing()) {
	        x = (parentSize.width - childSize.width) / 2;
	        y = (parentSize.height - childSize.height) / 2;
	        x += parentLocationOnScreen.x;
	        y += parentLocationOnScreen.y;
	    } else {
	        x = (screenSize.width - childSize.width) / 2;
	        y = (screenSize.height - childSize.height) / 2;
	    }
	    if (!absolute) {
	        x /= 2;
	        y /= 2;
	    }
	    child.setLocation(x, y);
	}
	
	public static void setUIFont(javax.swing.plaf.FontUIResource f) {
		Enumeration<Object> keys = UIManager.getDefaults().keys();
		
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value != null && value instanceof javax.swing.plaf.FontUIResource){
				UIManager.put(key, f);				
			}
		}
	}
}
