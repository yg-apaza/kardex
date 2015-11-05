package scik.controlador;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Interfaz del controlador de la vista del Login
 * 
 * @author Yuliana Apaza
 * @version 2.0
 * @since 2015-10-05
 */
public interface ILogin
{
    /**
     * Valida el nombre de usuario y la contraseña ingresados, si los datos
     * son correctos accede a la ventana del menú principal.
     * @param txtUsuario    Nombre de usuario
     * @param txtPass       Contraseña
     */
    public void validar(JTextField txtUsuario, JPasswordField txtPass);
    
    /**
     * Acceso a la ventana de configuración de la base de datos.
     */
    public void configuracion();
    
    /**
     * Sale del software.
     */
    public void salir();
}
