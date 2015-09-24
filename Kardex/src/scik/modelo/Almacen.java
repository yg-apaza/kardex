package scik.modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static scik.KardexIni.con;

public class Almacen
{
    private String AlmCod;
    private String AlmNom;
    private String AlmUbi;
    private String AlmEstReg;
    
    public Almacen()
    {
        this("-1", "-1", "NULL", "0");
    }
    
    public Almacen(String AlmCod, String AlmNom, String AlmUbi, String AlmEstReg)
    {
        this.AlmCod = AlmCod;
        this.AlmNom = AlmNom;
        this.AlmUbi = AlmUbi;
        this.AlmEstReg = AlmEstReg;
    }

    public String getAlmCod()
    {
        return AlmCod;
    }

    public void setAlmCod(String AlmCod)
    {
        this.AlmCod = AlmCod;
    }

    public String getAlmNom()
    {
        return AlmNom;
    }

    public void setAlmNom(String AlmNom)
    {
        this.AlmNom = AlmNom;
    }

    public String getAlmUbi()
    {
        return AlmUbi;
    }

    public void setAlmUbi(String AlmUbi)
    {
        this.AlmUbi = AlmUbi;
    }

    public String getAlmEstReg()
    {
        return AlmEstReg;
    }

    public void setAlmEstReg(String AlmEstReg)
    {
        this.AlmEstReg = AlmEstReg;
    }
    
    public String insertar()
    {
        try
        {
            con.ejecutar("INSERT INTO ALMACEN VALUES(DEFAULT, ?, ?, ?)", new String[] {AlmNom, AlmUbi, AlmEstReg}, false);
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
            con.ejecutar("UPDATE ALMACEN SET AlmNom = ?, AlmUbi = ? WHERE AlmCod = ?", new String[] {AlmNom, AlmUbi, AlmCod}, false);
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
            this.setAlmEstReg("3");
            con.ejecutar("UPDATE ALMACEN SET AlmEstReg = 3 WHERE AlmCod = ?", new String[] {AlmCod}, false);
        }
        catch (SQLException ex)
        {
            return ex.getMessage();
        }
        return "";
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
        try
        {
            this.setAlmEstReg("1");
            con.ejecutar("UPDATE ALMACEN SET AlmEstReg = 1 WHERE AlmCod = ?", new String[] {AlmCod}, false);
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
            this.setAlmEstReg("2");
            con.ejecutar("UPDATE ALMACEN SET AlmEstReg = 2 WHERE AlmCod = ?", new String[] {AlmCod}, false);
        }
        catch (SQLException ex)
        {
            return ex.getMessage();
        }
        return "";
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
        try
        {
            ResultSet rs = con.ejecutar("SELECT LPAD((SELECT COUNT(*) + 1 FROM ALMACEN), 6, '0') AS nextCod", null, true);
            rs.next();
            return rs.getString("nextCod");
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos.\nConfigure la conexión correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return "000000";
    }
    
    /*
    public ArrayList<ArrayList<String>> getVista()
    {
        Conexion con =  new Conexion();
        con.conectar();
        ArrayList<ArrayList<String>> almacenes = new ArrayList<ArrayList<String>>();
        
        try {        
            ResultSet resultado = con.receive("SELECT * FROM VI_Alm");
            while(resultado.next())
            {
                ArrayList<String> data = new ArrayList<String>();
                String codigo = resultado.getString("AlmCod");
                String nombre = resultado.getString("AlmNom");
                String ubicacion = resultado.getString("AlmUbi");
                data.add(codigo);
                data.add(nombre);
                data.add(ubicacion);
                almacenes.add(data);
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(Almacen.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return almacenes;
    }
    */
}
