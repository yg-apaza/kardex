package scik.modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static scik.KardexIni.con;

public class Producto
{
    private String ProCod;
    private String ProNom;
    private String UniCod;
    private String ProEstReg;
    
    public Producto()
    {
        this("-1", "NULL", "-1", "0");
    }
    
    public Producto(String ProCod, String ProNom, String UniCod, String ProEstReg)
    {
        this.ProCod = ProCod;
        this.ProNom = ProNom;
        this.UniCod = UniCod;
        this.ProEstReg = ProEstReg;
    }

    public String getProCod()
    {
        return ProCod;
    }

    public void setProCod(String ProCod)
    {
        this.ProCod = ProCod;
    }

    public String getProNom()
    {
        return ProNom;
    }

    public void setProNom(String ProNom)
    {
        this.ProNom = ProNom;
    }

    public String getUniCod()
    {
        return UniCod;
    }

    public void setUniCod(String UniCod)
    {
        this.UniCod = UniCod;
    }

    public String getProEstReg()
    {
        return ProEstReg;
    }

    public void setProEstReg(String ProEstReg)
    {
        this.ProEstReg = ProEstReg;
    }
    
    public String insertar()
    {
        try
        {
            con.ejecutar("INSERT INTO PRODUCTO VALUES(DEFAULT, ?, ?, ?)", new String[] {ProNom, UniCod, ProEstReg}, false);
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
            con.ejecutar("UPDATE PRODUCTO SET ProNom = ?, UniCod = ? WHERE ProCod = ?", new String[] {ProNom, UniCod, ProCod}, false);
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
            this.setProEstReg("3");
            con.ejecutar("UPDATE PRODUCTO SET ProEstReg = 3 WHERE ProCod = ?", new String[] {ProCod}, false);
        }
        catch (SQLException ex)
        {
             return ex.getMessage();
        }
        return "";
    }
    
    public static ArrayList<Producto> getLista()
    {
        ArrayList<Producto> productos = new ArrayList<>();
        try
        {
            ResultSet rs = con.ejecutar("SELECT ProCod, ProNom, UniDes, ProEstReg FROM PRODUCTO INNER JOIN UNIDAD ON PRODUCTO.UniCod = UNIDAD.UniCod ORDER BY ProEstReg ASC, ProCod ASC", null, true);
            while(rs.next())
            {
                String codigo = rs.getString("ProCod");
                String nombre = rs.getString("ProNom");
                String unidad = rs.getString("UniDes");
                String estado = rs.getString("ProEstReg");
                Producto producto = new Producto(codigo, nombre, unidad, estado);
                productos.add(producto);
            }
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos.\nConfigure la conexión correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return productos;
    }
    
    public String activar()
    {
        try
        {
            this.setProEstReg("1");
            con.ejecutar("UPDATE PRODUCTO SET ProEstReg = 1 WHERE ProCod = ?", new String[] {ProCod}, false);
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
            this.setProEstReg("2");
            con.ejecutar("UPDATE PRODUCTO SET ProEstReg = 2 WHERE ProCod = ?", new String[] {ProCod}, false);
        }
        catch (SQLException ex)
        {
             return ex.getMessage();
        }
        return "";
    }
    
    public static Producto buscar(String codigo)
    {
        Producto p = null;
        try
        {
            ResultSet rs = con.ejecutar("SELECT * FROM PRODUCTO WHERE ProCod = ?", new String[] {codigo}, true);
            rs.next();
            p = new Producto();
            p.setProCod(rs.getString("ProCod"));
            p.setProNom(rs.getString("ProNom"));
            p.setUniCod(rs.getString("UniCod"));
            p.setProEstReg(rs.getString("ProEstReg"));
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos.\nConfigure la conexión correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return p;
    }
    
    public static ArrayList<ArrayList<String>> getActivos()
    {
        ArrayList<ArrayList<String>> productos =  new ArrayList<>();
        try
        {        
            ResultSet resultado = con.ejecutar("SELECT ProCod, ProNom FROM PRODUCTO WHERE ProEstReg = 1 ", null, true);
            while(resultado.next())
            {
                ArrayList<String> data = new ArrayList<>();
                String codigo = resultado.getString("ProCod");
                String nombre = resultado.getString("ProNom");
                data.add(codigo);
                data.add(nombre);
                productos.add(data);
            }
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos.\nConfigure la conexión correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return productos;
    }
    
    public static String sgteCodigo()
    {
        try
        {
            ResultSet rs = con.ejecutar("SELECT LPAD((SELECT COUNT(*) + 1 FROM PRODUCTO), 6, '0') AS nextCod", null, true);
            rs.next();
            return rs.getString("nextCod");
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos.\nConfigure la conexión correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return "000000";
    }
    
    public static ArrayList<ArrayList<String>> getVista()
    {
        ArrayList<ArrayList<String>> productos =  new ArrayList<>();
        
        try
        {        
            ResultSet rs = con.ejecutar("SELECT * FROM VI_Pro", null, true);
            while(rs.next())
            {
                String codigo = rs.getString("ProCod");
                String nombre = rs.getString("ProNom");
                String unidad = rs.getString("UniDes");
                ArrayList<String> data = new ArrayList<>();
                data.add(codigo);
                data.add(nombre);
                data.add(unidad);
                productos.add(data);
            }
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos.\nConfigure la conexión correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return productos;
    }
}