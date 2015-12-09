package scik.controlador.documento;

import javax.swing.JTextField;

/**
 * Interfaz de la inserción de documento
 * 
 * Métodos disponibles para el controlador de inserción de documento
 *  
 * @author Yuliana Apaza
 * @version 2.0
 * @since 2015-10-05
 */

public interface IDocumentoIns
{
    public void cargar(JTextField txtDocCod);
    public void cancelar();
    public void aceptar(JTextField txtDocCod, JTextField txtDocNom);
}
