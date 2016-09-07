/*
 * DynamicTableModel.java
 * Created on 04/06/2004
 */
package co.shinetech.gui.table;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 * Class table model to dynamic utilities. 
 * @author rodrigo.dinis
 * @version 1.0
 * @since 06/04/2004
 * 
 * Changes:
 * @author antonio.walter
 * @version 1.1
 * @since 18/02/2005
 * 	Added the method removeValue.
 *  Added the method removeAll.
 * 
 * @author rodrigo.dinis
 * @version 1.2
 * @since 14/02/2008
 * Changes in somethings.
 *
 * @author rodrigo.dinis
 * @version 1.3
 * @since 16/07/2010
 * Added editable columns and rows features to table model.
 */
public class DynamicTableModel extends AbstractTableModel {
    /** table column titles **/ 
    private String [] tblTitle;
    /** table column fields **/
    private String [] tblFields;
    /** array list with the data **/	
    private List tblData = new ArrayList();
    /** the dto type **/
    private Class dtoClass;
    /** Editable columns flags **/
    private boolean [] editable;
    /** Parameter to DTO's setter classes **/
    private Class[] parameterTypes;
    /** Editable rows flags **/
    private boolean [] editableRows;
    
    /**
     * Constructor 
     */
    public DynamicTableModel(Class dtoClass) {
        super();
        this.dtoClass = dtoClass;
    }

    /**
     * Constructor
     */
    public DynamicTableModel(Class dtoClass,boolean[] editable,Class[] parameterTypes) {
        super();
        this.dtoClass = dtoClass;
        this.editable = editable;
        this.parameterTypes = parameterTypes;
    }

    /**
     * @see javax.swing.table.TableModel#getColumnCount()
     */
    @Override
    public int getColumnCount() {
        return tblTitle.length;
    }

    /**
     * @see javax.swing.table.TableModel#getRowCount()
     */
    @Override
    public int getRowCount() {
        return tblData.size();
    }

    /**
     * @see javax.swing.table.TableModel#getColumnName(int)
     */
    @Override
    public String getColumnName( int index ) {
        return tblTitle[index];
    }

    /**
     * @see javax.swing.table.TableModel#getValueAt(int, int)
     */
    public Object getValueAt( int r ){
        if( tblData.get(r) != null ) {
            return tblData.get(r);
        }
        return "";
    }

    public void setTblFields(String[] string) {
        this.tblFields = string;
    }

    /**
     * Method to set a model in a specific table row.
     * @param r row to set.
     * @param contractor contractor model to set.
     */
    public void setValueAt( int r, Object dm ) {
        if( tblData.get(r) != null ) {
            tblData.set(r,dm);
        }
        else { 
            tblData.set(r, "");
        }
    }
	
    /* (non-Javadoc)
     * @see javax.swing.table.TableModel#setValueAt(java.lang.Object, int, int)
     */
    @Override
    public void setValueAt(Object value, int row, int col) {
        String upperLetter;
        String remainingField;
        String field;

        try {
            field = tblFields[col];
            upperLetter = field.substring(0,1).toUpperCase();
            remainingField = field.substring(1);
            Method m = dtoClass.getMethod("set" +upperLetter+remainingField, new Class[]{parameterTypes[col]});
            m.invoke(tblData.get(row), new Object[]{value});
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DynamicTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            Logger.getLogger(DynamicTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(DynamicTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(DynamicTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(DynamicTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        fireTableCellUpdated(row, col);
    }	
	
    /* (non-Javadoc)
     * @see javax.swing.table.TableModel#getColumnClass(int)
     */
    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
    
    /**
     * Method to add a model.
     * @param contractor model with contractor data.
     */
    public void addValue( Object dm ) {
        tblData.add( dm );
    }
    
    /**
     * Method to remove a DynamicModel from DynamicTableModel.
     * @param dm DynamicModel that you want to remove.
     */
    public void removeValueAt(int r){
        tblData.remove(r);
    }

    /**
     * Method to remove a Object from DynamicTableModel.
     * @param dm Object that you want to remove.
     */
    public void removeValue(Object d){
        tblData.remove(d);
    }

    /**
     * Method to remove all elements from DynamicTableModel.
     */
    public void removeAll(){
        Object[] o = tblData.toArray();
        for(int i = 0 ; i < o.length; i++){
            tblData.remove(o[i]);
        }		
    }
        
    /**
     * @see javax.swing.table.TableModel#getValueAt(int, int)
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object ret = null;
        String upperLetter;
        String remainingField;
        String field;
        
        try {
            field = tblFields[columnIndex];
            upperLetter = field.substring(0,1).toUpperCase();
            remainingField = field.substring(1);            
            Method m = dtoClass.getMethod("get" +upperLetter+remainingField, new Class[]{});
            ret = m.invoke(tblData.get(rowIndex), new Object[]{});
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DynamicTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            Logger.getLogger(DynamicTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(DynamicTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(DynamicTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(DynamicTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret == null ? "" : ret;
    } 

    /**
     * Method to clear the table model. 
     */
    public void clear() {
        tblData.clear();
    }

    /**
     * @return Returns the tblTitle.
     */
    public String[] getTblTitle() {
        return tblTitle;
    }

    /**
     * @param tblTitle The tblTitle to set.
     */
    public void setTblTitle(String[] tblTitle) {
        this.tblTitle = tblTitle;
    }

    /**
     * @return
     */
    public List getData() {
        return tblData;
    }

    /**
     * @param tblData
     */
    public void setData(List tblData) {
        this.tblData = tblData;
    }
    
    @Override
    public boolean isCellEditable(int row, int col){
        boolean r;

        if( editableRows == null )
            r = ( editable == null ? false : editable[col] );
        else
            r = ( editableRows == null ? false : editableRows[row] );
        return r;
    }

    public Class getDtoClass() {
        return dtoClass;
    }

    public void setDtoClass(Class dtoClass) {
        this.dtoClass = dtoClass;
    }

    public boolean contains( Object o ) {
        return tblData.contains(o);
    }

    public void setEditable(boolean [] editable) {
        this.editable = editable;
    }

    public void setEditableRows(boolean[] editableRows) {
        this.editableRows = editableRows;
    }
}