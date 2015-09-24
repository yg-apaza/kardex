package kardex.controlador;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import static kardex.KardexMain.user;
import static kardex.KardexMain.con;
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
            user = u;
            CKardex k = new CKardex();
            ventana.dispose();
        }
        else
            JOptionPane.showMessageDialog(null, "Nombre de usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE, null);
    }
    
    @Override
    public void configuracion()
    {
        CConfiguracion config = new CConfiguracion(true);
        ventana.dispose();
    }
    
    @Override
    public void salir()
    {
        ventana.dispose();
        con.desconectar();
    }
}
