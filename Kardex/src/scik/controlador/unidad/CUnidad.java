package scik.controlador.unidad;

import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import scik.controlador.CKardexMenu;
import scik.modelo.Unidad;
import scik.vista.unidad.UIUnidad;

import com.mxrck.autocompleter.TextAutoCompleter;

/**
 * Controlador de la gestion de unidad
 * 
 * Carga las unidades existentes con sus datos, ademas de controlar el
 * redireccionamiento hacia las ventanas de insercion o modificacion. Las funciones
 * activar, desactivar y eliminar son realizadas aqui.
 *  
 * @author Yuliana Apaza
 * @version 2.0
 * @since 2015-10-05
 */

public class CUnidad implements IUnidad
{
    private UIUnidad ventana;
    private ArrayList<Unidad> unidades;
    
    public CUnidad()
    {
        unidades = Unidad.getLista();
        ventana = new UIUnidad(this);
    }
    
    @Override
    public void cargar(JTable tblRegistros)
    {
        DefaultTableModel model = (DefaultTableModel) tblRegistros.getModel();
        model.setRowCount(0);
        int unidadesSize = unidades.size();
        String estado = "";
        
        for(int i = 0; i < unidadesSize; i++)
        {
            if(unidades.get(i).getUniEstReg().equals("1"))
                estado = "A";
            else if(unidades.get(i).getUniEstReg().equals("2"))
                estado = "I";
            else
                estado = "*";
                
            model.addRow(new Object[]{  unidades.get(i).getUniCod(),
                                        unidades.get(i).getUniDes(),
                                        estado});
        }
    }
    
    @Override
    public void actualizarEst(JTable tblRegistros, JCheckBox chActivar)
    {
        int i = tblRegistros.getSelectedRow();
        if(i != -1)
        {
            chActivar.setEnabled(true);
            Unidad u = unidades.get(i);
            if(!u.getUniEstReg().equals("3"))
            {
                if(u.getUniEstReg().equals("1"))
                    chActivar.setSelected(true);
                else
                    chActivar.setSelected(false);
            }
            else
                chActivar.setEnabled(false);
        }
        else
            chActivar.setEnabled(false);
    }
    
    @Override
    public void menu()
    {
        new CKardexMenu();
        ventana.dispose();
    }
    
    @Override
    public void insertar()
    {
        new CUnidadIns();
        ventana.dispose();
    }
    
    @Override
    public void modificar(JTable tblRegistros)
    {
        int i = tblRegistros.getSelectedRow();
        if(i != -1)
        {
            Unidad u = unidades.get(i);
            CUnidadMod modificar;
            if(u.getUniEstReg().equals("1"))
            {
                modificar = new CUnidadMod(u.getUniCod());
                ventana.dispose();
            }
            else
                JOptionPane.showMessageDialog(null, "Solo se permite modificar registros activos", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        else
            JOptionPane.showMessageDialog(null, "Seleccione un registro a modificar", "ERROR", JOptionPane.ERROR_MESSAGE);
    }
    
    @Override
    public void eliminar(JTable tblRegistros, JCheckBox chActivar)
    {
        int i = tblRegistros.getSelectedRow();
        if(i != -1)
        {
            Unidad u = unidades.get(i);
            if(!u.getUniEstReg().equals("3"))
            {
                if(JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar el registro?", "Eliminar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                {
                    DefaultTableModel model = (DefaultTableModel) tblRegistros.getModel();
                    u.eliminar();
                    model.setValueAt("*", i, 2);
                    chActivar.setEnabled(false);
                }
            }
            else
                JOptionPane.showMessageDialog(null, "El registro ya está eliminado", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        else
            JOptionPane.showMessageDialog(null, "Seleccione un registro a eliminar", "ERROR", JOptionPane.ERROR_MESSAGE);
    }
    
    @Override
    public void activarDesactivar(JTable tblRegistros, JCheckBox chActivar)
    {
        int i = tblRegistros.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) tblRegistros.getModel();
        Unidad u = unidades.get(i);
        if(chActivar.isSelected())
        {
            u.activar();
            model.setValueAt("A", i, 2);
        }
        else
        {
            u.desactivar();
            model.setValueAt("I", i, 2);
        }
    }
    
    /*
    Realizar búsquedas y los muestra en el JTextField
    */
    public void buscarUnidad( JTextField buscar, JTable tablaUnidad)
    {
        TextAutoCompleter textAutoAcompleter = new TextAutoCompleter( buscar );
        textAutoAcompleter.setMode(0); // infijo
        textAutoAcompleter.setCaseSensitive(false); //No sensible a mayúsculas
        TableModel tableModel = tablaUnidad.getModel();
        String filtro = "Descripción";
        int i;
        
        for(i = 0; i < tableModel.getColumnCount(); i++)
        {
            if(filtro.compareTo(tableModel.getColumnName(i)) == 0)
                break;
        }
        
        for(int k = 0; k < tableModel.getRowCount(); k++)
        {
            textAutoAcompleter.addItem(tableModel.getValueAt(k, i));
        }
    }
    /*
    Selecciona la busqueda realizada en la tabla
    */
    public void seleccionarFila(JTextField buscar, JTable tablaUnidad)
    {
        TableModel tableModel = tablaUnidad.getModel();
        String dato = buscar.getText();
        String filtro = "Descripción";
        int col;
        
        for(col = 0; col < tableModel.getColumnCount(); col++)
        {
            if(filtro.compareTo(tableModel.getColumnName(col)) == 0)
                break;
        }
        
        int row;
        try
        {
            for(row = 0; row < tableModel.getRowCount(); row++)
            {
                if(dato.compareTo((String) tableModel.getValueAt(row, col)) == 0)
                    break;
            }

            if(row == 0)
                tablaUnidad.changeSelection(0,0,false,true);
            else
                tablaUnidad.getSelectionModel().setSelectionInterval(row - 1, row);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "No se encontraron los datos buscados", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}
