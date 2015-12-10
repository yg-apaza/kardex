package scik.controlador.usuario;

import javax.swing.JTable;

/**
 * Interfaz de la gestión de usuario
 * 
 * Métodos disponibles para el controlador de gestión de usuario
 *  
 * @author Yuliana Apaza
 * @version 2.0
 * @since 2015-10-05
 */

public interface IUsuario
{
    public void menu();
    public void registrar();
    public void cargar(JTable tblRegistros);
    public void modificar(JTable tblRegistros);
    public void eliminar(JTable tblRegistros);
}
