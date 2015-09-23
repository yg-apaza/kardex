package kardex.controlador;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import kardex.Kardex;
import kardex.modelo.Usuario;
import kardex.vista.UILogin;

public class CLogin implements ILogin
{
    private UILogin ventana;
    
    public CLogin()
    {
        this.ventana = new UILogin(this);
    }
    
    @Override
    public void validar(JTextField txtUsuario, JPasswordField txtPass)
    {
        Usuario u = Usuario.validar(txtUsuario.getText(), String.valueOf(txtPass.getPassword()));
        if(u != null)
        {
            Kardex.user = u;
            CKardex k = new CKardex();
            ventana.dispose();
        }
        else
            JOptionPane.showMessageDialog(null, "Nombre de usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE, null);
    }
    
    public void salir()
    {
        ventana.dispose();
        Kardex.con.desconectar();
    }
}
