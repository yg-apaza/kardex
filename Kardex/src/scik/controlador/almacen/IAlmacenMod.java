package scik.controlador.almacen;

import javax.swing.JTextField;

/**
 * Interfaz de la modificacion de almacen
 * 
 * Metodos disponibles para el controlador de moficacion de almacen
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
