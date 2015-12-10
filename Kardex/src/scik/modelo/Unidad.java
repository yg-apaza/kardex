package scik.modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import static scik.KardexIni.con;

/**
 * Representación de la entidad unidad de la base de datos
 * 
 * Controla y gestiona acceso a la base de datos, consultas y peticiones de 
 * manipulación de datos para la tabla 'unidad' en MySQL. 
 * Incluye las funciones de insertar, modificar, eliminar, activar y desactivar.
 * 
 * 
 * @author Yuliana Apaza
 * @version 2.0
 * @since 2015-10-05
 */

public class Unidad
{
    private String uniCod;
    private String uniDes;
    private String uniEstReg;
    
    public Unidad()
    {
        this("-1", "NULL", "0");
    }
    
    public Unidad(String uniCod, String uniDes, String uniEstReg)
    {
        this.uniCod = uniCod;
        this.uniDes = uniDes;
        this.uniEstReg = uniEstReg;
    }

    public String getUniCod()
    {
        return uniCod;
    }

    public void setUniCod(String uniCod)
    {
        this.uniCod = uniCod;
    }

    public String getUniDes()
    {
        return uniDes;
    }

    public void setUniDes(String uniDes)
    {
        this.uniDes = uniDes;
    }

    public String getUniEstReg()
    {
        return uniEstReg;
    }

    public void setUniEstReg(String uniEstReg)
    {
        this.uniEstReg = uniEstReg;
    }
    
    public String insertar()
    {
        String msg = "";
        try
        {
            con.ejecutar("INSERT INTO UNIDAD VALUES(DEFAULT, ?, ?)", new String[] {uniDes, uniEstReg}, false);
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
            con.ejecutar("UPDATE UNIDAD SET UniDes = ? WHERE UniCod = ?", new String[] {uniDes, uniCod}, false);
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
            this.setUniEstReg("3");
            con.ejecutar("UPDATE UNIDAD SET UniEstReg = 3 WHERE UniCod = ?", new String[] {uniCod}, false);
        }
        catch (SQLException ex)
        {
             msg = ex.getMessage();
        }
        return msg;
    }
    
    public static ArrayList<Unidad> getLista()
    {
        ArrayList<Unidad> unidades = new ArrayList<> ();
        try
        {
            ResultSet resultado = con.ejecutar("SELECT * FROM UNIDAD ORDER BY UniEstReg ASC, UniCod ASC", null, true);
            while(resultado.next())
            {
                String codigo = resultado.getString("UniCod");
                String descripcion = resultado.getString("UniDes");
                String estado = resultado.getString("UniEstReg");
                Unidad unidad = new Unidad(codigo, descripcion, estado);
                unidades.add(unidad);
            }
        }
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos.\nConfigure la conexión correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return unidades;
    }
    
    public String activar()
    {
	String msg = "";
        try
        {
            this.setUniEstReg("1");
            con.ejecutar("UPDATE UNIDAD SET UniEstReg = 1 WHERE UniCod = ?", new String[] {uniCod}, false);
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
            this.setUniEstReg("2");
            con.ejecutar("UPDATE UNIDAD SET UniEstReg = 2 WHERE UniCod = ?", new String[] {uniCod}, false);
        }
        catch (SQLException ex)
        {
             msg = ex.getMessage();
        }
        return msg;
    }
    
    public static Unidad buscar(String codigo)
    {
        Unidad u = null;
        try
        {
            ResultSet rs = con.ejecutar("SELECT * FROM UNIDAD WHERE UniCod = ?", new String[] {codigo}, true);
            rs.next();
            u = new Unidad();
            u.setUniCod(rs.getString("UniCod"));
            u.setUniDes(rs.getString("UniDes"));
            u.setUniEstReg(rs.getString("UniEstReg"));
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos.\nConfigure la conexión correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return u;           
    }
    
    public static ArrayList<ArrayList<String>> getActivos()
    {
        ArrayList<ArrayList<String>> unidades = new ArrayList<>();
        try
        {        
            ResultSet rs = con.ejecutar("SELECT UniCod, UniDes FROM UNIDAD WHERE UniEstReg = 1", null, true);
            while(rs.next())
            {
                ArrayList<String> data = new ArrayList<>();
                String codigo = rs.getString("UniCod");
                String nombre = rs.getString("UniDes");
                data.add(codigo);
                data.add(nombre);
                unidades.add(data);
            }
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos.\nConfigure la conexión correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return unidades;
    }
    
    public static String sgteCodigo()
    {
        String codigo = "000";
        try
        {
            ResultSet rs = con.ejecutar("SELECT LPAD((SELECT COUNT(*) + 1 FROM UNIDAD), 3, '0') AS nextCod", null, true);
            rs.next();
            codigo = rs.getString("nextCod");
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos.\nConfigure la conexión correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return codigo;
    }
}