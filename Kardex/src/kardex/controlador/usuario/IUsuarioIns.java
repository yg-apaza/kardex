package kardex.controlador.usuario;

import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public interface IUsuarioIns
{
    public void cancelar();
    public void aceptar(JTextField txtUsrCod, JTextField txtUsrIde, JPasswordField txtCon, JPasswordField txtRepCon, JFormattedTextField txtDNI, JTextField txtUsrNom, JTextField txtUsrApe, JRadioButton rbAdmin);
    public void cargar(JTextField txtUsrCod);
}
