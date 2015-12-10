package scik.controlador.kardex;

import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 * Interfaz de la inserción de kardex
 * 
 * Métodos disponibles para el controlador de inserción de kardex
 *  
 * @author Yuliana Apaza
 * @version 2.0
 * @since 2015-10-05
 */

public interface IKardexCabIns
{
    public void cancelar();
    public void verProducto(JTextField txtProCod, JComboBox cbxProNom);
    public void verAlmacen(JTextField txtAlmCod, JComboBox cbxAlmNom);
    public void aceptar(JTextField txtProCod, JTextField txtAlmCod);
    public void cargar(JComboBox cbxProNom, JComboBox cbxAlmNom);
}
