package kardex.controlador.usuario;

import javax.swing.ButtonGroup;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import kardex.modelo.Usuario;
import kardex.vista.UIUsuarioIns;

public class CUsuarioIns implements IUsuarioIns
{
    private UIUsuarioIns ventana;
    
    public CUsuarioIns()
    {
        ventana = new UIUsuarioIns(this);
    }
    
    @Override
    public void cancelar()
    {
        CUsuario usuario = new CUsuario();
        ventana.dispose();
    }
    
    @Override
    public void cargar(JTextField txtUsrCod)
    {
        txtUsrCod.setText(Usuario.sgteCodigo());
    }
    
    @Override
    public void aceptar(JTextField txtUsrCod, JTextField txtUsrIde, JPasswordField txtCon, JPasswordField txtRepCon, JFormattedTextField txtDNI, JTextField txtUsrNom, JTextField txtUsrApe, JRadioButton rbAdmin)
    {
        Usuario u = new Usuario(txtUsrCod.getText(),
                                txtUsrIde.getText(),
                                txtDNI.getText(),
                                txtUsrNom.getText(),
                                txtUsrApe.getText(),
                                rbAdmin.isSelected()?"1":"0",
                                "1");
        
        if(String.valueOf(txtRepCon.getPassword()).equals(String.valueOf(txtCon.getPassword())))
        {
            if(String.valueOf(txtCon.getPassword()).length() >= 5 && String.valueOf(txtCon.getPassword()).length() <= 16)
            {
                String err = u.insertar(String.valueOf(txtRepCon.getPassword()));
                if(err.equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Se ha agregado el registro nuevo", "INSERCION", JOptionPane.INFORMATION_MESSAGE);
                    CUsuario usuario = new CUsuario();
                    ventana.dispose();
                }
                else
                    JOptionPane.showMessageDialog(null, err, "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "La contraseña debe tener entre 5 y 12 caracteres.", "ERROR", JOptionPane.ERROR_MESSAGE);
                txtCon.setText("");
                txtRepCon.setText("");
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden.\nIntente de nuevo", "ERROR", JOptionPane.ERROR_MESSAGE);
            txtCon.setText("");
            txtRepCon.setText("");
        }
    }
}
