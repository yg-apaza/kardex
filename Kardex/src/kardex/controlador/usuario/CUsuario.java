package kardex.controlador.usuario;

import java.util.ArrayList;
import javax.swing.JTable;
import kardex.controlador.CKardex;
import kardex.modelo.Almacen;
import kardex.vista.UIUsuario;

public class CUsuario implements IUsuario
{
    private UIUsuario ventana;
    private ArrayList<Almacen> usuarios;
    
    public CUsuario()
    {
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
        
    }
}
