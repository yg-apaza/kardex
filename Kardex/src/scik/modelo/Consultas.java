package scik.modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static scik.KardexIni.con;

public class Consultas
{
    public ArrayList<ArrayList<String>> existenciaProducto(String codigoProducto)
    {
        ArrayList<ArrayList<String>> existencias = new ArrayList<ArrayList<String>>();       
        try
        {        
            ResultSet resultado = con.ejecutar("SELECT * FROM VI_ProAlmCan WHERE ProCod = " + codigoProducto, null, true);
            while(resultado.next())
            {
                ArrayList<String> data = new ArrayList<String>();
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
            ex.printStackTrace();
        }
        return existencias;
    }
    
    public String existenciaTotal(String codigoProducto)
    {
        String total = "0.00";
        
        try
        {        
            ResultSet resultado = con.ejecutar("SELECT SUM(KarCan) FROM VI_ProAlmCan WHERE ProCod = " + codigoProducto, null, true);
            resultado.next();
            total = resultado.getString("SUM(KarCan)");
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        
        return total;
    }
    
    public ArrayList<ArrayList<String>> entradas(String codigoProducto, String anio, String mes)
    {
        ArrayList<ArrayList<String>> entradas = new ArrayList<ArrayList<String>>();       

        try
        {        
            ResultSet resultado = con.ejecutar("SELECT * FROM VI_ProEntMes WHERE ProCod = " + codigoProducto + " AND KarDetAnio = " + anio + " AND KarDetMes = " + mes, null, true);
            while(resultado.next())
            {
                ArrayList<String> data = new ArrayList<String>();
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
            ex.printStackTrace();
        }
        return entradas;
    }
    
    public ArrayList<ArrayList<String>> salidas(String codigoProducto, String anio, String mes)
    {
        ArrayList<ArrayList<String>> salidas = new ArrayList<ArrayList<String>>();       

        try
        {        
            ResultSet resultado = con.ejecutar("SELECT * FROM VI_ProSalMes WHERE ProCod = " + codigoProducto + " AND KarDetAnio = " + anio + " AND KarDetMes = " + mes + " ORDER BY KarDetDia", null, true);
            while(resultado.next())
            {
                ArrayList<String> data = new ArrayList<String>();
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
            ex.printStackTrace();
        }
        return salidas;
    }
    
}
