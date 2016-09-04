/*
 * ZebraTableCellRenderer.java
 * Created on 17/06/2004
 */
package co.shinetech.gui.table;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Class customized to show rows on JTable "stripped".
 * @author rodrigo.dinis
 * @version 1.0
 * @since 06/17/2004
 */
@SuppressWarnings("serial")
public class ZebraTableCellRenderer extends DefaultTableCellRenderer {

    /**
     * ZebraTableCellRenderer Constructor. 
     */
    public ZebraTableCellRenderer() {
        super();
    }

    /**
     * Getter to table cell renderer component.
     * @see javax.swing.table.TableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
     */
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        if( (row+1) % 2 == 0 && table.getSelectedRow() != row ) {
            c.setBackground(new Color(240, 95, 4));
        } else if( table.getSelectedRow() != row ) {
            c.setBackground(Color.WHITE);
        }
        return c;
    }
}