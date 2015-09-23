package kardex.modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import kardex.Kardex;

public class Usuario
{
    private String UsrCod;
    private String UsrIde;
    private String UsrDni;
    private String UsrNom;
    private String UsrApe;
    private String UsrPer;
    private String UsrEstReg;
    
    public Usuario()
    {
        this("-1", "NULL", "00000000", "NULL", "NULL", "0", "3");
    }
    
    public Usuario(String UsrCod, String UsrIde, String UsrDni, String UsrNom, String UsrApe, String UsrPer, String UsrEstReg)
    {
        this.UsrCod = UsrCod;
        this.UsrIde = UsrIde;
        this.UsrDni = UsrDni;
        this.UsrNom = UsrNom;
        this.UsrApe = UsrApe;
        this.UsrPer = UsrPer;
        this.UsrEstReg = UsrEstReg;
    }

    public String getUsrCod()
    {
        return UsrCod;
    }

    public void setUsrCod(String UsrCod)
    {
        this.UsrCod = UsrCod;
    }

    public String getUsrIde()
    {
        return UsrIde;
    }

    public void setUsrIde(String UsrIde)
    {
        this.UsrIde = UsrIde;
    }

    public String getUsrDni()
    {
        return UsrDni;
    }

    public void setUsrDni(String UsrDni)
    {
        this.UsrDni = UsrDni;
    }

    public String getUsrNom()
    {
        return UsrNom;
    }

    public void setUsrNom(String UsrNom)
    {
        this.UsrNom = UsrNom;
    }

    public String getUsrApe()
    {
        return UsrApe;
    }

    public void setUsrApe(String UsrApe)
    {
        this.UsrApe = UsrApe;
    }

    public String getUsrPer()
    {
        return UsrPer;
    }

    public void setUsrPer(String UsrPer)
    {
        this.UsrPer = UsrPer;
    }

    public String getUsrEstReg()
    {
        return UsrEstReg;
    }

    public void setUsrEstReg(String UsrEstReg)
    {
        this.UsrEstReg = UsrEstReg;
    }
    
    public static Usuario validar(String usr, String con)
    {
        Usuario user = null;
        String[] data = {usr, con};

        try
        {
            ResultSet rs = Kardex.con.ejecutar("SELECT * FROM USUARIO WHERE UsrIde = ? AND UsrCon = md5(?) AND UsrEstReg = 1", data, true);
            rs.next();
            if(rs.isLast())
            {
                user = new Usuario();
                user.setUsrCod(rs.getString("UsrCod"));
                user.setUsrIde(rs.getString("UsrIde"));
                user.setUsrDni(rs.getString("UsrDni"));
                user.setUsrNom(rs.getString("UsrNom"));
                user.setUsrApe(rs.getString("UsrApe"));
                user.setUsrPer(rs.getString("UsrPer"));
                user.setUsrEstReg(rs.getString("UsrEstReg"));
            }
        }
        catch (SQLException ex)
        {}
        
        return user;
    }
    
    public String insertar(String pass)
    {
        String err = "";
        try
        {
            Kardex.con.ejecutar("INSERT INTO USUARIO VALUES(DEFAULT, ?, MD5(?), ?, ?, ?, ?, ?)", new String[] {UsrIde, pass, UsrDni, UsrNom, UsrApe, UsrPer, UsrEstReg}, false);
        }
        catch (SQLException ex)
        {
            err = ex.getMessage();
        }
        return err;
    }
    
    public String modificar(String codigo, String pass)
    {
        String err = "";
        try
        {
            Kardex.con.ejecutar("UPDATE USUARIO SET UsrIde = ?, UsrCon = MD5(?), UsrDni = ?, UsrNom = ?, UsrApe = ?, UsrPer = ? WHERE UsrCod = ?", new String[] {UsrIde, pass, UsrDni, UsrNom, UsrApe, UsrPer, codigo}, false);
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
            this.setUsrEstReg("3");
            Kardex.con.ejecutar("UPDATE USUARIO SET UsrEstReg = 3 WHERE AlmCod = ?", new String[] {codigo}, false);
        }
        catch (SQLException ex)
        {
            return ex.getMessage();
        }
        return "";
    }
    
    public static ArrayList<Usuario> getLista()
    {
        ArrayList<Usuario> usuarios = new ArrayList<> ();
        try
        {
            ResultSet rs = Kardex.con.ejecutar("SELECT * FROM USUARIO ORDER BY UsrEstReg ASC, UsrCod ASC", null, true);
            
            while(rs.next())
            {
                String codigo = rs.getString("UsrCod");
                String ident = rs.getString("UsrIde");
                String dni = rs.getString("UsrDni");
                String nombre = rs.getString("UsrNom");
                String apellido = rs.getString("UsrApe");
                String permiso = rs.getString("UsrPer");
                String estado = rs.getString("UsrEstReg");
                
                Usuario u = new Usuario(codigo, ident, dni, nombre, apellido, permiso, estado);
                usuarios.add(u);
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        
        return usuarios;
    }
    
    public static Usuario buscar(String codigo)
    {
        Usuario u = null;
        try
        {
            ResultSet rs = Kardex.con.ejecutar("SELECT * FROM USUARIO WHERE UsrCod = ?", new String[] {codigo}, true);
            rs.next();
            u = new Usuario();
            u.setUsrCod(rs.getString("UsrCod"));
            u.setUsrIde(rs.getString("UsrIde"));
            u.setUsrDni(rs.getString("UsrDni"));
            u.setUsrNom(rs.getString("UsrNom"));
            u.setUsrApe(rs.getString("UsrApe"));
            u.setUsrPer(rs.getString("UsrPer"));
            u.setUsrEstReg(rs.getString("UsrEstReg"));
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return u;
    }
    
    public static String sgteCodigo()
    {
        String c = "000000";
        try
        {
            ResultSet rs = Kardex.con.ejecutar("SELECT LPAD((SELECT COUNT(*) + 1 FROM USUARIO), 6, '0') AS nextCod", null, true);
            rs.next();
            c = rs.getString("nextCod");
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return c;
    }
    
}
