package scik.modelo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import static scik.KardexIni.con;

public class Utils
{
    public static void setup()
    {
        FileReader fr = null;
        try
        {
            String [] conexion_data = new String[3];
            fr = new FileReader("conexion.dat");
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
        }
        catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                fr.close();
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
    }
    
    public static void restore(String table)
    {
        try
        {
            con.ejecutar("DELETE FROM " + table, null, false);
            con.ejecutar("ALTER TABLE " + table + " AUTO_INCREMENT = 1", null, false);
        }
        catch (SQLException ex)
        {
            System.out.println("Error de configuracion de la BD, test unitario imposible de evaluar");
        }
    }
}
