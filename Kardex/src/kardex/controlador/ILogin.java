package kardex.controlador;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

public interface ILogin
{
    public void validar(JTextField txtUsuario, JPasswordField txtPass);
    public void configuracion();
    public void salir();
}
