package scik.modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import static scik.KardexIni.con;

/**
 * Representacion de la entidad almacen de la base de datos
 * 
 * Controla y gestiona acceso a la base de datos, consultas y peticiones de 
 * manipulacion de datos para la tabla 'almacen' en MySQL. 
 * Incluye las funciones de insertar, modificar, eliminar, activar y desactivar.
 * 
 * @author Yuliana Apaza
 * @version 2.0
 * @since 2015-10-05
 */

public class Almacen
{
    private String almCod;
    private String almNom;
    private String almUbi;
    private String almEstReg;
    
    public Almacen()
    {
        this("-1", "-1", "NULL", "0");
    }
    
    public Almacen(String almCod, String almNom, String almUbi, String almEstReg)
    {
        this.almCod = almCod;
        this.almNom = almNom;
        this.almUbi = almUbi;
        this.almEstReg = almEstReg;
    }

    public String getAlmCod()
    {
        return almCod;
    }

    public void setAlmCod(String almCod)
    {
        this.almCod = almCod;
    }

    public String getAlmNom()
    {
        return almNom;
    }

    public void setAlmNom(String almNom)
    {
        this.almNom = almNom;
    }

    public String getAlmUbi()
    {
        return almUbi;
    }

    public void setAlmUbi(String almUbi)
    {
        this.almUbi = almUbi;
    }

    public String getAlmEstReg()
    {
        return almEstReg;
    }

    public void setAlmEstReg(String almEstReg)
    {
        this.almEstReg = almEstReg;
    }
    
    public String insertar()
    {
        String msg = "";
        try
        {
            con.ejecutar("INSERT INTO ALMACEN VALUES(DEFAULT, ?, ?, ?)", new String[] {almNom, almUbi, almEstReg}, false);
        }
        catch (SQLException ex)
        {
            msg = ex.getMessage();
        }
        return msg;
    }
    
    public String modificar()
    {
	String msg = "";
        try
        {
            con.ejecutar("UPDATE ALMACEN SET AlmNom = ?, AlmUbi = ? WHERE AlmCod = ?", new String[] {almNom, almUbi, almCod}, false);
        }
        catch (SQLException ex)
        {
            msg = ex.getMessage();
        }
        return msg;
    }
    
    public String eliminar()
    {
	String msg = "";
        try
        {
            this.setAlmEstReg("3");
            con.ejecutar("UPDATE ALMACEN SET AlmEstReg = 3 WHERE AlmCod = ?", new String[] {almCod}, false);
        }
        catch (SQLException ex)
        {
            msg = ex.getMessage();
        }
        return msg;
    }
    
    public static ArrayList<Almacen> getLista()
    {
        ArrayList<Almacen> almacenes = new ArrayList<> ();
        try
        {
            ResultSet rs = con.ejecutar("SELECT * FROM ALMACEN ORDER BY AlmEstReg ASC, AlmCod ASC", null, true);
            while(rs.next())
            {
                String codigo = rs.getString("AlmCod");
                String nombre = rs.getString("AlmNom");
                String ubicacion = rs.getString("AlmUbi");
                String estado = rs.getString("AlmEstReg");
                Almacen almacen = new Almacen(codigo, nombre, ubicacion, estado);
                almacenes.add(almacen);
            }
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos.\nConfigure la conexión correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
              
        return almacenes;
    }

    public String activar()
    {
	String msg = "";
        try
        {
            this.setAlmEstReg("1");
            con.ejecutar("UPDATE ALMACEN SET AlmEstReg = 1 WHERE AlmCod = ?", new String[] {almCod}, false);
        }
        catch (SQLException ex)
        {
            msg = ex.getMessage();
        }
        return msg;
    }
    
    public String desactivar()
    {
	String msg = "";
        try
        {
            this.setAlmEstReg("2");
            con.ejecutar("UPDATE ALMACEN SET AlmEstReg = 2 WHERE AlmCod = ?", new String[] {almCod}, false);
        }
        catch (SQLException ex)
        {
            msg = ex.getMessage();
        }
        return msg;
    }
    
    public static Almacen buscar(String codigo)
    {
        Almacen a = null;
        try
        {
            ResultSet rs = con.ejecutar("SELECT * FROM ALMACEN WHERE AlmCod = ?", new String[] {codigo}, true);
            rs.next();
            a = new Almacen();
            a.setAlmCod(rs.getString("AlmCod"));
            a.setAlmNom(rs.getString("AlmNom"));
            a.setAlmUbi(rs.getString("AlmUbi"));
            a.setAlmEstReg(rs.getString("AlmEstReg"));
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos.\nConfigure la conexión correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return a;
    }
    
    public static ArrayList<ArrayList<String>> getActivos()
    {
        ArrayList<ArrayList<String>> almacenes = new ArrayList<>();
        try
        {
            ResultSet rs = con.ejecutar("SELECT AlmCod, AlmNom FROM ALMACEN WHERE AlmEstReg = 1", null, true);
            
            while(rs.next())
            {
                ArrayList<String> data = new ArrayList<>();
                String codigo = rs.getString("AlmCod");
                String nombre = rs.getString("AlmNom");
                data.add(codigo);
                data.add(nombre);
                almacenes.add(data);
            }
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos.\nConfigure la conexión correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
        return almacenes;
    }
    
    public static String sgteCodigo()
    {
        String codigo = "000000";
        try
        {
            ResultSet rs = con.ejecutar("SELECT LPAD((SELECT COUNT(*) + 1 FROM ALMACEN), 6, '0') AS nextCod", null, true);
            rs.next();
            codigo = rs.getString("nextCod");
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos.\nConfigure la conexión correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return codigo;
    }
    
    public static ArrayList<ArrayList<String>> getVista()
    {
        ArrayList<ArrayList<String>> almacenes = new ArrayList<>();
        try
        {        
            ResultSet rs = con.ejecutar("SELECT * FROM VI_Alm", null, true);
            while(rs.next())
            {
                ArrayList<String> data = new ArrayList<>();
                String codigo = rs.getString("AlmCod");
                String nombre = rs.getString("AlmNom");
                String ubicacion = rs.getString("AlmUbi");
                data.add(codigo);
                data.add(nombre);
                data.add(ubicacion);
                almacenes.add(data);
            }
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos.\nConfigure la conexión correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return almacenes;
    }
}
