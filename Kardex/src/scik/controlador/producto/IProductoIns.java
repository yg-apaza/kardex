package scik.controlador.producto;

import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 * Interfaz de la insercion de producto
 * 
 * Metodos disponibles para el controlador de insercion de producto
 *  
 * @author Yuliana Apaza
 * @version 2.0
 * @since 2015-10-05
 */
public interface IProductoIns
{
    public void cancelar();
    public void cargar(JTextField txtProCod, JComboBox cbxUniDes);
    public void aceptar(JTextField txtProCod, JTextField txtProNom, JTextField txtUniCod);
    public void verUnidad(JComboBox cbxUniDes, JTextField txtUniCod);
}
