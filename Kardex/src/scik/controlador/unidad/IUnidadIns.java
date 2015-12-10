package scik.controlador.unidad;

import javax.swing.JTextField;

/**
 * Interfaz de la insercion de unidad
 * 
 * Metodos disponibles para el controlador de insercion de unidad
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
