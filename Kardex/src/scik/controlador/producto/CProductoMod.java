package scik.controlador.producto;

import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import scik.modelo.Producto;
import scik.modelo.Unidad;
import scik.vista.producto.UIProductoMod;

/**
 * Controlador de la modificacion de producto
 * 
 * Carga datos del producto seleccionado, recibe nuevos valores y los valida
 *  
 * @author Yuliana Apaza
 * @version 2.0
 * @since 2015-10-05
 */

public class CProductoMod implements IProductoMod
{
    private UIProductoMod ventana;
    private Producto p;
    ArrayList<ArrayList<String>> unidades;
    
    public CProductoMod(String codigo)
    {
        p = Producto.buscar(codigo);
        unidades = Unidad.getActivos();
        ventana = new UIProductoMod(this);
    }
    
    @Override
    public void cargar(JTextField txtProCod, JTextField txtProNom, JTextField txtUniCod, JComboBox cbxUniDes)
    {
        txtProCod.setText(p.getProCod());
        txtProNom.setText(p.getProNom());
        txtUniCod.setText(p.getUniCod());
        
        for(int i = 0; i < unidades.size(); i++)
        {
            cbxUniDes.insertItemAt(unidades.get(i).get(1), i);
        }
        
        cbxUniDes.setSelectedItem(Unidad.buscar(p.getUniCod()).getUniDes());
    }
    
    @Override
    public void cancelar()
    {
        new CProducto();
        ventana.dispose();
    }
    
    @Override
    public void verUnidad(JComboBox cbxUniDes, JTextField txtUniCod)
    {
        txtUniCod.setText(unidades.get(cbxUniDes.getSelectedIndex()).get(0));
    }
    
    @Override
    public void aceptar(JTextField txtProCod, JTextField txtProNom, JTextField txtUniCod)
    {
        p.setProNom(txtProNom.getText());
        p.setUniCod(txtUniCod.getText());
        
        String err = p.modificar();
        if(err.equals(""))
        {
            JOptionPane.showMessageDialog(null, "Se ha modificado el registro", "MODIFICACIÓN", JOptionPane.INFORMATION_MESSAGE);
            new CProducto();
            ventana.dispose();
        }
        else
            JOptionPane.showMessageDialog(null, err, "ERROR", JOptionPane.ERROR_MESSAGE);
    }
}
