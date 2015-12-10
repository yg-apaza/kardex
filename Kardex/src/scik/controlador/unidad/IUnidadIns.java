package scik.controlador.unidad;

import javax.swing.JTextField;

/**
 * Interfaz de la inserción de unidad
 * 
 * Métodos disponibles para el controlador de inserción de unidad
 *  
 * @author Yuliana Apaza
 * @version 2.0
 * @since 2015-10-05
 */

public interface IUnidadIns
{
    public void cargar(JTextField txtUniCod);
    public void aceptar(JTextField txtUniCod, JTextField txtUniDes);
    public void cancelar();
}
