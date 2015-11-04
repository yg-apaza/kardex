package scik.controlador.producto;

import com.mxrck.autocompleter.TextAutoCompleter;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import scik.controlador.CKardexMenu;
import scik.modelo.Producto;
import scik.modelo.Reporte;
import scik.vista.UIProducto;

public class CProducto implements IProducto
{
    private UIProducto ventana;
    private ArrayList<Producto> productos;
    
    public CProducto()
    {
        productos = Producto.getLista();
        ventana = new UIProducto(this);
    }
    
    @Override
    public void actualizarEst(JTable tblRegistros, JCheckBox chActivar)
    {
        int i = tblRegistros.getSelectedRow();
        if(i != -1)
        {
            chActivar.setEnabled(true);
            Producto p = productos.get(i);
            if(!p.getProEstReg().equals("3"))
            {
                if(p.getProEstReg().equals("1"))
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
    public void cargar(JTable tblRegistros)
    {
        DefaultTableModel model = (DefaultTableModel) tblRegistros.getModel();
        model.setRowCount(0);
        
        for(int i = 0; i < productos.size(); i++)
        {
            model.addRow(new Object[]{  productos.get(i).getProCod(),
                                        productos.get(i).getProNom(),
                                        productos.get(i).getUniCod(),
                                        productos.get(i).getProEstReg().equals("1")?"A":(productos.get(i).getProEstReg().equals("2")?"I":"*")});
        }
    }
    
    @Override
    public void insertar()
    {
        CProductoIns insertar = new CProductoIns();
        ventana.dispose();
    }
    
    @Override
    public void menu()
    {
        CKardexMenu menu = new CKardexMenu();
        ventana.dispose();
    }
    
    @Override
    public void modificar(JTable tblRegistros)
    {
        int i = tblRegistros.getSelectedRow();
        if(i != -1)
        {
            Producto p = productos.get(i);
            CProductoMod modificar;
            if(p.getProEstReg().equals("1"))
            {
                modificar = new CProductoMod(p.getProCod());
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
            Producto p = productos.get(i);
            if(!p.getProEstReg().equals("3"))
            {
                if(JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar el registro?", "Eliminar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                {
                    DefaultTableModel model = (DefaultTableModel) tblRegistros.getModel();
                    p.eliminar();
                    model.setValueAt("*", i, 3);
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
    public void activar_desactivar(JTable tblRegistros, JCheckBox chActivar)
    {
        int i = tblRegistros.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) tblRegistros.getModel();
        Producto p = productos.get(i);
        if(chActivar.isSelected())
        {
            p.activar();
            model.setValueAt("A", i, 3);
        }
        else
        {
            p.desactivar();
            model.setValueAt("I", i, 3);
        }
    }

    @Override
    public void generarReporte()
    {
        ArrayList <ArrayList<String>> lista = Producto.getVista();
        ArrayList <String> cab = new ArrayList <> ();
        cab.add("Código");
        cab.add("Nombre de Producto");
        cab.add("Unidad");
        Reporte.generarReporte("REPORTE DE PRODUCTOS ACTIVOS", "PRODUCTO", lista, cab);
    }
    
    /*
    Realizar búsquedas y los muestra en el JTextField
    */
    public void buscarProducto( JTextField buscar, JTable tablaProducto)
    {
        TextAutoCompleter textAutoAcompleter = new TextAutoCompleter( buscar );
        textAutoAcompleter.setMode(0); // infijo
        textAutoAcompleter.setCaseSensitive(false); //No sensible a mayúsculas
        TableModel tableModel = tablaProducto.getModel();
        String filtro = "Nombre";
        int i;
        for(i = 0; i < tableModel.getColumnCount(); i++)
            if(filtro.compareTo(tableModel.getColumnName(i)) == 0)
                break;
        for(int k = 0; k < tableModel.getRowCount(); k++)
            textAutoAcompleter.addItem(tableModel.getValueAt(k, i));
    }
    /*
    Selecciona la busqueda realizada en la tabla
    */
    public void seleccionarFila(JTextField buscar, JTable tablaProducto)
    {
        TableModel tableModel = tablaProducto.getModel();
        String dato = buscar.getText();
        String filtro = "Nombre";
        int col;
        for(col = 0; col < tableModel.getColumnCount(); col++)
            if(filtro.compareTo(tableModel.getColumnName(col)) == 0)
                break;
        int row;
        try
        {
            for(row = 0; row < tableModel.getRowCount(); row++)
                if(dato.compareTo((String) tableModel.getValueAt(row, col)) == 0)
                    break;

            if(row == 0)
                tablaProducto.changeSelection(0,0,false,true);
            else
                tablaProducto.getSelectionModel().setSelectionInterval(row - 1, row);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "No se encontraron los datos buscados", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}