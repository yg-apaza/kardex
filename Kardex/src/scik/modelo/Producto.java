package scik.modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import static scik.KardexIni.con;

/**
 * Representacion de la entidad producto de la base de datos
 * 
 * Controla y gestiona acceso a la base de datos, consultas y peticiones de 
 * manipulacion de datos para la tabla 'producto' en MySQL. 
 * Incluye las funciones de insertar, modificar, eliminar, activar y desactivar.
 * 
 * 
 * @author Yuliana Apaza
 * @version 2.0
 * @since 2015-10-05
 */

public class Producto
{
    private String proCod;
    private String proNom;
    private String uniCod;
    private String proEstReg;
    
    public Producto()
    {
        this("-1", "NULL", "-1", "0");
    }
    
    public Producto(String proCod, String proNom, String uniCod, String proEstReg)
    {
        this.proCod = proCod;
        this.proNom = proNom;
        this.uniCod = uniCod;
        this.proEstReg = proEstReg;
    }

    public String getProCod()
    {
        return proCod;
    }

    public void setProCod(String proCod)
    {
        this.proCod = proCod;
    }

    public String getProNom()
    {
        return proNom;
    }

    public void setProNom(String proNom)
    {
        this.proNom = proNom;
    }

    public String getUniCod()
    {
        return uniCod;
    }

    public void setUniCod(String uniCod)
    {
        this.uniCod = uniCod;
    }

    public String getProEstReg()
    {
        return proEstReg;
    }

    public void setProEstReg(String proEstReg)
    {
        this.proEstReg = proEstReg;
    }
    
    public String insertar()
    {
        String msg = "";
        try
        {
            con.ejecutar("INSERT INTO PRODUCTO VALUES(DEFAULT, ?, ?, ?)", new String[] {proNom, uniCod, proEstReg}, false);
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
            con.ejecutar("UPDATE PRODUCTO SET ProNom = ?, UniCod = ? WHERE ProCod = ?", new String[] {proNom, uniCod, proCod}, false);
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
            this.setProEstReg("3");
            con.ejecutar("UPDATE PRODUCTO SET ProEstReg = 3 WHERE ProCod = ?", new String[] {proCod}, false);
        }
        catch (SQLException ex)
        {
             msg = ex.getMessage();
        }
        return msg;
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
	String msg = "";
        try
        {
            this.setProEstReg("1");
            con.ejecutar("UPDATE PRODUCTO SET ProEstReg = 1 WHERE ProCod = ?", new String[] {proCod}, false);
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
            this.setProEstReg("2");
            con.ejecutar("UPDATE PRODUCTO SET ProEstReg = 2 WHERE ProCod = ?", new String[] {proCod}, false);
        }
        catch (SQLException ex)
        {
             msg = ex.getMessage();
        }
        return msg;
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
        String codigo = "000000";
        try
        {
            ResultSet rs = con.ejecutar("SELECT LPAD((SELECT COUNT(*) + 1 FROM PRODUCTO), 6, '0') AS nextCod", null, true);
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