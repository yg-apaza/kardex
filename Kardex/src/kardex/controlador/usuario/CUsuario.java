package kardex.controlador.usuario;

import java.util.ArrayList;
import javax.swing.JOptionPane;
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
                                        usuarios.get(i).getUsrEstReg().equals("1")?"A":"*"});
        }
    }
    
    @Override
    public void modificar(JTable tblRegistros)
    {
        int i = tblRegistros.getSelectedRow();
        if(i != -1)
        {
            Usuario u = usuarios.get(i);
            CUsuarioMod modificar;
            if(u.getUsrEstReg().equals("1"))
            {
                modificar = new CUsuarioMod(u.getUsrCod());
                ventana.dispose();
            }
            else
                JOptionPane.showMessageDialog(null, "Solo se permite modificar registros activos", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        else
            JOptionPane.showMessageDialog(null, "Seleccione un registro a modificar", "ERROR", JOptionPane.ERROR_MESSAGE);
    }
    
    @Override
    public void eliminar(JTable tblRegistros)
    {
        int i = tblRegistros.getSelectedRow();
        if(i != -1)
        {
            Usuario u = usuarios.get(i);
            if(!u.getUsrEstReg().equals("3"))
            {
                if(JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar el registro?", "Eliminar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                {
                    DefaultTableModel model = (DefaultTableModel) tblRegistros.getModel();
                    u.eliminar();
                    model.setValueAt("*", i, 6);
                }
            }
            else
                JOptionPane.showMessageDialog(null, "El registro ya está eliminado", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        else
            JOptionPane.showMessageDialog(null, "Seleccione un registro a eliminar", "ERROR", JOptionPane.ERROR_MESSAGE);
    }
}
