package scik.controlador.almacen;

import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import scik.controlador.CKardexMenu;
import scik.modelo.Almacen;
import scik.modelo.Reporte;
import scik.vista.almacen.UIAlmacen;

import com.mxrck.autocompleter.TextAutoCompleter;


/**
 * Controlador de la gestión de almacen
 * 
 * Carga los almacenes existentes con sus datos, además de controlar el
 * redireccionamiento hacia las ventanas de inserción o modificación. Provee
 * opciones de generar reporte y buscar registros. Las funciones activar,
 * desactivar y eliminar son realizadas aquí.
 *  
 * @author Yuliana Apaza
 * @version 2.0
 * @since 2015-10-05
 */


public class CAlmacen implements IAlmacen
{
    private UIAlmacen ventana;
    private ArrayList<Almacen> almacenes;
    
    public CAlmacen()
    {
        almacenes = Almacen.getLista();
        ventana = new UIAlmacen(this);
    }
    
    @Override
    public void cargar(JTable tblRegistros, JTextField txtBuscar)
    {
        DefaultTableModel model = (DefaultTableModel) tblRegistros.getModel();
        model.setRowCount(0);
        
        for(int i = 0; i < almacenes.size(); i++)
        {
            model.addRow(new Object[]{  almacenes.get(i).getAlmCod(),
                                        almacenes.get(i).getAlmNom(),
                                        almacenes.get(i).getAlmUbi(),
                                        almacenes.get(i).getAlmEstReg().equals("1")?"A":(almacenes.get(i).getAlmEstReg().equals("2")?"I":"*")});
        }
    }
    
    @Override
    public void actualizarEst(JTable registros, JCheckBox est)
    {
        int i = registros.getSelectedRow();
        if(i != -1)
        {
            est.setEnabled(true);
            Almacen a = almacenes.get(i);
            if(!a.getAlmEstReg().equals("3"))
            {
                if(a.getAlmEstReg().equals("1"))
                    est.setSelected(true);
                else
                    est.setSelected(false);
            }
            else
                est.setEnabled(false);
        }
        else
            est.setEnabled(false);
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
        new CAlmacenIns();
        ventana.dispose();
    }

    @Override
    public void modificar(JTable tblRegistros)
    {
        int i = tblRegistros.getSelectedRow();
        if(i != -1)
        {
            Almacen a = almacenes.get(i);
            CAlmacenMod modificar;
            if(a.getAlmEstReg().equals("1"))
            {
                modificar = new CAlmacenMod(a.getAlmCod());
                ventana.dispose();
            }
            else
                JOptionPane.showMessageDialog(null, "Solo se permite modificar registros activos", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        else
            JOptionPane.showMessageDialog(null, "Seleccione un registro a modificar", "ERROR", JOptionPane.ERROR_MESSAGE);
    }
    
    @Override
    public void activarDesactivar(JTable tblRegistros, JCheckBox chEst)
    {
        int i = tblRegistros.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) tblRegistros.getModel();
        Almacen a = almacenes.get(i);
        if(chEst.isSelected())
        {
            a.activar();
            model.setValueAt("A", i, 3);
        }
        else
        {
            a.desactivar();
            model.setValueAt("I", i, 3);
        }
    }
    
    @Override
    public void eliminar(JTable tblRegistros, JCheckBox est)
    {
        int i = tblRegistros.getSelectedRow();
        if(i != -1)
        {
            Almacen a = almacenes.get(i);
            if(!a.getAlmEstReg().equals("3"))
            {
                if(JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar el registro?", "Eliminar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                {
                    DefaultTableModel model = (DefaultTableModel) tblRegistros.getModel();
                    a.eliminar();
                    model.setValueAt("*", i, 3);
                    est.setEnabled(false);
                }
            }
            else
                JOptionPane.showMessageDialog(null, "El registro ya está eliminado", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        else
            JOptionPane.showMessageDialog(null, "Seleccione un registro a eliminar", "ERROR", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void generarReporte()
    {
        ArrayList <ArrayList<String>> lista = Almacen.getVista();
        ArrayList <String> cab = new ArrayList <> ();
        cab.add("Código");
        cab.add("Nombre de Almacen");
        cab.add("Ubicación");
        Reporte.generarReporte("REPORTE DE ALMACENES ACTIVOS", "ALMACEN", lista, cab);
    }
    
    /*
    Realizar búsquedas según el filtro seleccionado en el JComboBox convertido en String, y los muestra en el JTextField
    */
    @Override
    public void buscarAlmacen(String filtro, JTextField buscar, JTable tablaAlmacen)
    {
        buscar.setText("");
        TextAutoCompleter textAutoAcompleter = new TextAutoCompleter(buscar);
        textAutoAcompleter.removeAllItems();
        textAutoAcompleter.setMode(0); // infijo
        textAutoAcompleter.setCaseSensitive(false); //No sensible a mayúsculas
        TableModel tableModel = tablaAlmacen.getModel();
        int i;
        for(i = 0; i < tableModel.getColumnCount(); i++)
            if(filtro.compareTo(tableModel.getColumnName(i)) == 0)
                break;
        for(int k = 0; k < tableModel.getRowCount(); k++)
            textAutoAcompleter.addItem(tableModel.getValueAt(k, i));
    }
}
