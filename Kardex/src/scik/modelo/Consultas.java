package scik.modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static scik.KardexIni.con;

/**
 * Clase de consultas
 * 
 * Incluye las consultas específicas del sistema. 
 * 
 * @author Yuliana Apaza
 * @version 2.0
 * @since 2015-10-05
 */

public class Consultas
{
    public static ArrayList<ArrayList<String>> existenciaProducto(String codigoProducto)
    {
        ArrayList<ArrayList<String>> existencias = new ArrayList<ArrayList<String>>();       
        try
        {        
            ResultSet resultado = con.ejecutar("SELECT * FROM VI_ProAlmCan WHERE ProCod = ?", new String[] {codigoProducto}, true);
            while(resultado.next())
            {
                ArrayList<String> data = new ArrayList<>();
                String codigo = resultado.getString("AlmCod");
                String nombre = resultado.getString("AlmNom");
                String cantidad = resultado.getString("KarCan");
                data.add(codigo);
                data.add(nombre);
                data.add(cantidad);
                existencias.add(data);
            }
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos.\nConfigure la conexión correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return existencias;
    }
    
    public static String existenciaTotal(String codigoProducto)
    {
        String total = "0.00";
        
        try
        {        
            ResultSet resultado = con.ejecutar("SELECT SUM(KarCan) FROM VI_ProAlmCan WHERE ProCod = ?", new String[] {codigoProducto}, true);
            resultado.next();
            total = resultado.getString("SUM(KarCan)");
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos.\nConfigure la conexión correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
        return total;
    }
    
    public static ArrayList<ArrayList<String>> entradas(String codigoProducto, String anio, String mes)
    {
        ArrayList<ArrayList<String>> entradas = new ArrayList<>();       

        try
        {        
            ResultSet resultado = con.ejecutar("SELECT * FROM VI_ProEntMes WHERE ProCod = ? AND KarDetAnio = ? AND KarDetMes = ?", new String[] {codigoProducto, anio, mes}, true);
            while(resultado.next())
            {
                ArrayList<String> data = new ArrayList<>();
                String almCod = resultado.getString("AlmCod");
                String almNom = resultado.getString("AlmNom");
                String cantidad = resultado.getString("KarDetCan");
                String dia = resultado.getString("KarDetDia");
                
                data.add(dia);
                data.add(almCod);
                data.add(almNom);
                data.add(cantidad);
                
                entradas.add(data);
            }
        
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos.\nConfigure la conexión correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return entradas;
    }
    
    public static ArrayList<ArrayList<String>> salidas(String codigoProducto, String anio, String mes)
    {
        ArrayList<ArrayList<String>> salidas = new ArrayList<>();       

        try
        {        
            ResultSet resultado = con.ejecutar("SELECT * FROM VI_ProSalMes WHERE ProCod = ? AND KarDetAnio = ? AND KarDetMes = ? ORDER BY KarDetDia", new String[] {codigoProducto, anio, mes}, true);
            while(resultado.next())
            {
                ArrayList<String> data = new ArrayList<>();
                String almCod = resultado.getString("AlmCod");
                String almNom = resultado.getString("AlmNom");
                String cantidad = resultado.getString("KarDetCan");
                String dia = resultado.getString("KarDetDia");
                
                data.add(dia);
                data.add(almCod);
                data.add(almNom);
                data.add(cantidad);
                
                salidas.add(data);
            }
        
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos.\nConfigure la conexión correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return salidas;
    }
    
    public static ArrayList<String> getAnioEntrada(String producto)
    {
        ArrayList<String> anios = new ArrayList<>();       
        try
        {        
            ResultSet resultado = con.ejecutar("SELECT KarDetAnio FROM VI_ProEntMes WHERE ProCod = ? GROUP BY KarDetAnio", new String[] {producto}, true);
            while(resultado.next())
            {
                anios.add(resultado.getString("KarDetAnio"));
            }
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos.\nConfigure la conexión correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return anios;
    }
    
    public static ArrayList<String> getMesEntrada(String producto, String anio)
    {
        ArrayList<String> meses = new ArrayList<>();       
        try
        {        
            ResultSet resultado = con.ejecutar("SELECT KarDetMes FROM VI_ProEntMes WHERE ProCod = ? AND KarDetAnio = ? GROUP BY KarDetMes", new String[] {producto, anio}, true);
            while(resultado.next())
            {
                meses.add(resultado.getString("KarDetMes"));
            }
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos.\nConfigure la conexión correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return meses;
    }
    
    public static ArrayList<String> getAnioSalida(String producto)
    {
        ArrayList<String> anios = new ArrayList<>();       
        try
        {        
            ResultSet resultado = con.ejecutar("SELECT KarDetAnio FROM VI_ProSalMes WHERE ProCod = ? GROUP BY KarDetAnio", new String[] {producto}, true);
            while(resultado.next())
            {
                anios.add(resultado.getString("KarDetAnio"));
            }
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos.\nConfigure la conexión correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return anios;
    }
    
    public static ArrayList<String> getMesSalida(String producto, String anio)
    {
        ArrayList<String> meses = new ArrayList<>();       
        try
        {        
            ResultSet resultado = con.ejecutar("SELECT KarDetMes FROM VI_ProSalMes WHERE ProCod = ? AND KarDetAnio = ? GROUP BY KarDetMes;", new String[] {producto, anio}, true);
            while(resultado.next())
            {
                meses.add(resultado.getString("KarDetMes"));
            }
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos.\nConfigure la conexión correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return meses;
    }
}
