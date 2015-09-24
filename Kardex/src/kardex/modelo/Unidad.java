package kardex.modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static kardex.KardexMain.con;

public class Unidad
{
    private String UniCod;
    private String UniDes;
    private String UniEstReg;
    
    public Unidad()
    {
        this("-1", "NULL", "0");
    }
    
    public Unidad(String UniCod, String UniDes, String UniEstReg)
    {
        this.UniCod = UniCod;
        this.UniDes = UniDes;
        this.UniEstReg = UniEstReg;
    }

    public String getUniCod()
    {
        return UniCod;
    }

    public void setUniCod(String UniCod)
    {
        this.UniCod = UniCod;
    }

    public String getUniDes()
    {
        return UniDes;
    }

    public void setUniDes(String UniDes)
    {
        this.UniDes = UniDes;
    }

    public String getUniEstReg()
    {
        return UniEstReg;
    }

    public void setUniEstReg(String UniEstReg)
    {
        this.UniEstReg = UniEstReg;
    }
    
    public String insertar()
    {
        try
        {
            con.ejecutar("INSERT INTO UNIDAD VALUES(DEFAULT, ?, ?)", new String[] {UniDes, UniEstReg}, false);
        }
        catch (SQLException ex)
        {
            return ex.getMessage();
        }
        return "";
    }
    
    public String modificar()
    {
        try
        {
            con.ejecutar("UPDATE UNIDAD SET UniDes = ? WHERE UniCod = ?", new String[] {UniDes, UniCod}, false);
        }
        catch (SQLException ex)
        {
            return ex.getMessage();
        }
        return "";
    }
    
    public String eliminar()
    {
        try
        {
            this.setUniEstReg("3");
            con.ejecutar("UPDATE UNIDAD SET UniEstReg = 3 WHERE UniCod = ?", new String[] {UniCod}, false);
        }
        catch (SQLException ex)
        {
             return ex.getMessage();
        }
        return "";
    }
    
    public static ArrayList<Unidad> getLista()
    {
        ArrayList<Unidad> unidades = new ArrayList<> ();
        try
        {
            ResultSet resultado = con.ejecutar("SELECT * FROM UNIDAD ORDER BY UniEstReg ASC", null, true);
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
        try
        {
            this.setUniEstReg("1");
            con.ejecutar("UPDATE UNIDAD SET UniEstReg = 1 WHERE UniCod = ?", new String[] {UniCod}, false);
        }
        catch (SQLException ex)
        {
             return ex.getMessage();
        }
        return "";
    }
    
    public String desactivar()
    {
        try
        {
            this.setUniEstReg("2");
            con.ejecutar("UPDATE UNIDAD SET UniEstReg = 2 WHERE UniCod = ?", new String[] {UniCod}, false);
        }
        catch (SQLException ex)
        {
             return ex.getMessage();
        }
        return "";
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
    
    public static String sgteCodigo()
    {
        try
        {
            ResultSet rs = con.ejecutar("SELECT LPAD((SELECT COUNT(*) + 1 FROM UNIDAD), 3, '0') AS nextCod", null, true);
            rs.next();
            return rs.getString("nextCod");
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos.\nConfigure la conexión correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return "000";
    }
}