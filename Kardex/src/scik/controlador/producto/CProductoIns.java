package scik.controlador.producto;

import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import scik.modelo.Producto;
import scik.modelo.Unidad;
import scik.vista.producto.UIProductoIns;

/**
 * Controlador de la inserción de producto
 * 
 * Recibe y valida datos sobre un nuevo registro de producto
 *  
 * @author Yuliana Apaza
 * @version 2.0
 * @since 2015-10-05
 */

public class CProductoIns implements IProductoIns
{
    private UIProductoIns ventana;
    ArrayList<ArrayList<String>> unidades;
    
    public CProductoIns()
    {
        unidades = Unidad.getActivos();
        ventana = new UIProductoIns(this);
    }
    
    @Override
    public void cancelar()
    {
        new CProducto();
        ventana.dispose();
    }
    
    @Override
    public void cargar(JTextField txtProCod, JComboBox cbxUniDes)
    {
        txtProCod.setText(Producto.sgteCodigo());
        for(int i = 0; i < unidades.size(); i++)
        {
            cbxUniDes.insertItemAt(unidades.get(i).get(1), i);
        }
    }
    
    @Override
    public void aceptar(JTextField txtProCod, JTextField txtProNom, JTextField txtUniCod)
    {
        Producto p = new Producto(  txtProCod.getText(),
                                    txtProNom.getText(),
                                    txtUniCod.getText(),
                                    "1"
                                 );
        
        String err = p.insertar();
        if(err.equals(""))
        {
            JOptionPane.showMessageDialog(null, "Se ha agregado el registro nuevo", "INSERCION", JOptionPane.INFORMATION_MESSAGE);
            new CProducto();
            ventana.dispose();
        }
        else
            JOptionPane.showMessageDialog(null, err, "ERROR", JOptionPane.ERROR_MESSAGE);
    }
    
    @Override
    public void verUnidad(JComboBox cbxUniDes, JTextField txtUniCod)
    {
        txtUniCod.setText(unidades.get(cbxUniDes.getSelectedIndex()).get(0));
    }
}
