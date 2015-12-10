package scik.controlador.documento;

import javax.swing.JTextField;

/**
 * Interfaz de la modificacion de documento
 * 
 * Metodos disponibles para el controlador de moficacion de documento
 *  
 * @author Yuliana Apaza
 * @version 2.0
 * @since 2015-10-05
 */

public interface IDocumentoMod
{
    public void cargar(JTextField txtDocCod, JTextField txtDocNom);
    public void cancelar();
    public void aceptar(JTextField txtDocNom);
}
