package scik.controlador.almacen;

import javax.swing.JTextField;

/**
 * Interfaz de la modificación de almacen
 * 
 * Métodos disponibles para el controlador de moficación de almacén
 *  
 * @author Yuliana Apaza
 * @version 2.0
 * @since 2015-10-05
 */

public interface IAlmacenMod
{
    public void cargar(JTextField txtAlmCod, JTextField txtAlmNom, JTextField txtAlmUbi);
    public void aceptar(JTextField txtAlmNom, JTextField txtAlmUbi);
    public void cancelar();
}
