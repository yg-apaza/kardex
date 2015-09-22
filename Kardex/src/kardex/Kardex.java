package kardex;

import javax.swing.UIManager;
import kardex.controlador.CLogin;
import kardex.modelo.Conexion;
import kardex.vista.UILogin;

public class Kardex
{
    public Conexion con;
    
    public Kardex()
    {
        try
        {
            UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException e)
        {
            e.printStackTrace();
        }
        
        con = new Conexion("localhost", "BD_KARDEX", "root", "123456");
        con.conectar();
        
        CLogin login = new CLogin();
    }
    
    public static void main(String[] args)
    {
        Kardex k = new Kardex();
    }
}
