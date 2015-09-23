package kardex.controlador.usuario;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import kardex.modelo.Usuario;
import kardex.vista.UIUsuarioMod;

public class CUsuarioMod implements IUsuarioMod
{
    private UIUsuarioMod ventana;
    private String codigo;
    
    public CUsuarioMod(String codigo)
    {
        this.codigo = codigo;
        ventana = new UIUsuarioMod(this);
    }
    
    @Override
    public void cancelar()
    {
        CUsuario usuario = new CUsuario();
        ventana.dispose();
    }
    
    @Override
    public void cargar( JTextField txtUsrCod, JTextField txtUsrIde, JFormattedTextField txtDNI,
                        JTextField txtUsrNom, JTextField txtUsrApe, JRadioButton rbAdmin, JRadioButton rbUsuario)
    {
        Usuario u = Usuario.buscar(codigo);
        txtUsrCod.setText(u.getUsrCod());
        txtUsrIde.setText(u.getUsrIde());
        txtDNI.setText(u.getUsrDni());
        txtUsrNom.setText(u.getUsrNom());
        txtUsrApe.setText(u.getUsrApe());
        if(u.getUsrPer().equals("1"))
        {
            rbAdmin.setSelected(true);
            rbUsuario.setSelected(false);
        }
        else
        {
            rbAdmin.setSelected(false);
            rbUsuario.setSelected(true);
        }
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
                String err = u.modificar(codigo, String.valueOf(txtRepCon.getPassword()));
                if(err.equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Se ha modificado el registro", "MODIFICACIÓN", JOptionPane.INFORMATION_MESSAGE);
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
