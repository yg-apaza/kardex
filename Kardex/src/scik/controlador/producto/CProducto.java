package scik.controlador.producto;

import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import scik.controlador.CKardexMenu;
import scik.modelo.Producto;
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
        
    }
}