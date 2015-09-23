package kardex.controlador.usuario;

import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import kardex.controlador.CKardex;
import kardex.modelo.Usuario;
import kardex.vista.UIUsuario;

public class CUsuario implements IUsuario
{
    private UIUsuario ventana;
    private ArrayList<Usuario> usuarios;
    
    public CUsuario()
    {
        usuarios = Usuario.getLista();
        ventana = new UIUsuario(this);
    }
    
    @Override
    public void menu()
    {
        CKardex menu = new CKardex();
        ventana.dispose();
    }
    
    @Override
    public void registrar()
    {
        CUsuarioIns insertar = new CUsuarioIns();
        ventana.dispose();
    }
    
    @Override
    public void cargar(JTable tblRegistros)
    {
        DefaultTableModel model = (DefaultTableModel) tblRegistros.getModel();
        model.setRowCount(0);
        
        for(int i = 0; i < usuarios.size(); i++)
        {
            model.addRow(new Object[]{  usuarios.get(i).getUsrCod(),
                                        usuarios.get(i).getUsrIde(),
                                        usuarios.get(i).getUsrDni(),
                                        usuarios.get(i).getUsrNom(),
                                        usuarios.get(i).getUsrApe(),
                                        usuarios.get(i).getUsrPer().equals("1")?"Administrador":"Usuario",
                                        usuarios.get(i).getUsrEstReg().equals("1")?"A":(usuarios.get(i).getUsrEstReg().equals("2")?"I":"*")});
        }
    }
}
