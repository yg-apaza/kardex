package kardex.controlador;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public interface IConfiguracion
{
    public void cancelar();
    public void cargar(JTextField txtHost, JTextField txtUsuario);
    public void verificar(JTextField txtHost, JTextField txtUsuario, JPasswordField txtPass, JLabel lblEstado);
    public void aceptar(JTextField txtHost, JTextField txtUsuario, JPasswordField txtPass);
}
