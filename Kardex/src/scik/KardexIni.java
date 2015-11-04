package scik;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.UIManager;
import scik.controlador.CLogin;
import scik.modelo.Conexion;
import scik.modelo.Usuario;

/**
 * Clase principal del Sistema de Control de Inventarios
 * 
 * @author Yuliana Apaza
 * @version 2.0
 * @since 2015-10-05
 */

public class KardexIni
{
    /**
     * Objeto global utilizado para la conexión con la base de datos, sus atributos
     * van cambiando durante la ejecución del software y dependiendo de la configuración.
     */
    public static Conexion con;
    /**
     * Objeto global que identifica al usuario actual que hace uso del sistema con
     * sus respectivos permisos, ya sea de administrador o de usuario.
     */
    public static Usuario user;
    
    /**
     * Constructor principal, inicializa el LaF JTattoo y lee archivo 'conexion.dat'
     * donde se encuentran datos de configuración de la conexión con la base de datos
     * en MySQL.
     */
    public KardexIni()
    {
        try
        {
            UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
            
            String [] conexion_data = new String[3];
            FileReader fr = new FileReader("conexion.dat");
            BufferedReader br = new BufferedReader(fr);
            String linea = "";
            int number = 0;
            
            while((linea = br.readLine()) != null)
            {
                conexion_data[number] = linea.substring(linea.indexOf("=") + 1, linea.length());
                number++;
                if(number > 2)
                    break;
            }
                
            con = new Conexion(conexion_data[0], "BD_KARDEX", conexion_data[1], conexion_data[2]);
            con.conectar(false);
            user = new Usuario();
            
            CLogin login = new CLogin();
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException | FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
    
    public static void main(String[] args)
    {
        KardexIni k = new KardexIni();
    }
}
