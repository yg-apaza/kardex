package kardex.modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import kardex.Kardex;

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
        String err = "";
        try
        {
            Kardex.con.ejecutar("INSERT INTO ALMACEN VALUES(DEFAULT, ?, ?, ?)", new String[] {AlmNom, AlmUbi, AlmEstReg}, false);
        }
        catch (SQLException ex)
        {
            err = ex.getMessage();
        }
        return err;
    }
    
    public String actualizar(String codigo)
    {
        String err = "";
        try
        {
            Kardex.con.ejecutar("UPDATE ALMACEN SET AlmNom = ?, AlmUbi = ? WHERE AlmCod = ?", new String[] {AlmNom, AlmUbi, codigo}, false);
        }
        catch (SQLException ex)
        {
            err = ex.getMessage();
        }
        return err;
    }
    
    public String eliminar(String codigo)
    {
        try
        {
            this.setAlmEstReg("3");
            Kardex.con.ejecutar("UPDATE ALMACEN SET AlmEstReg = 3 WHERE AlmCod = ?", new String[] {codigo}, false);
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
            ResultSet resultado = Kardex.con.ejecutar("SELECT * FROM ALMACEN ORDER BY AlmEstReg ASC, AlmCod ASC", null, true);
            
            while(resultado.next())
            {
                String codigo = resultado.getString("AlmCod");
                String nombre = resultado.getString("AlmNom");
                String ubicacion = resultado.getString("AlmUbi");
                String estado = resultado.getString("AlmEstReg");
                
                Almacen almacen = new Almacen(codigo, nombre, ubicacion, estado);
                almacenes.add(almacen);
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
              
        return almacenes;
    }

    public String activar(String codigo)
    {
        try
        {
            this.setAlmEstReg("1");
            Kardex.con.ejecutar("UPDATE ALMACEN SET AlmEstReg = 1 WHERE AlmCod = ?", new String[] {codigo}, false);
        }
        catch (SQLException ex)
        {
            return ex.getMessage();
        }
        return "";
    }
    
    public String desactivar(String codigo)
    {
        try
        {
            this.setAlmEstReg("2");
            Kardex.con.ejecutar("UPDATE ALMACEN SET AlmEstReg = 2 WHERE AlmCod = ?", new String[] {codigo}, false);
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
            ResultSet resultado = Kardex.con.ejecutar("SELECT * FROM ALMACEN WHERE AlmCod = ?", new String[] {codigo}, true);
            resultado.next();
            a = new Almacen();
            a.setAlmCod(resultado.getString("AlmCod"));
            a.setAlmNom(resultado.getString("AlmNom"));
            a.setAlmUbi(resultado.getString("AlmUbi"));
            a.setAlmEstReg(resultado.getString("AlmEstReg"));
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return a;
    }
    
    public static ArrayList<ArrayList<String>> getActivos()
    {
        ArrayList<ArrayList<String>> almacenes = new ArrayList<>();
        try
        {
            ResultSet resultado = Kardex.con.ejecutar("SELECT AlmCod, AlmNom FROM ALMACEN WHERE AlmEstReg = 1", null, true);
            ArrayList<String> data = new ArrayList<>();
            while(resultado.next())
            {
                String codigo = resultado.getString("AlmCod");
                String nombre = resultado.getString("AlmNom");
                data.add(codigo);
                data.add(nombre);
                almacenes.add(data);
                data.clear();
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        
        return almacenes;
    }
    
    public static String sgteCodigo()
    {
        String c = "0";
        try
        {
            ResultSet rs = Kardex.con.ejecutar("SELECT LPAD((SELECT COUNT(*) + 1 FROM ALMACEN), 6, '0') AS nextCod", null, true);
            rs.next();
            c = rs.getString("nextCod");
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return c;
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
