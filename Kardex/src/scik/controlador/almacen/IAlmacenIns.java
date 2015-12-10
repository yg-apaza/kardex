package scik.controlador.almacen;

import javax.swing.JTextField;

/**
 * Interfaz de la insercion de almacen
 * 
 * Metodos disponibles para el controlador de insercion de almacen
 *  
 * @author Yuliana Apaza
 * @version 2.0
 * @since 2015-10-05
 */

public interface IAlmacenIns
{
    public void cargar(JTextField txtAlmCod);
    public void aceptar(JTextField txtAlmCod, JTextField txtAlmNom, JTextField txtAlmUbi);
    public void cancelar();
}
