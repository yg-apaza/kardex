package scik.modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import static scik.KardexIni.con;

/**
 * Representación de la entidad usuario de la base de datos
 * 
 * Controla y gestiona acceso a la base de datos, consultas y peticiones de 
 * manipulación de datos para la tabla 'usuario' en MySQL. 
 * Incluye las funciones de insertar, modificar, eliminar y validar.
 * 
 * 
 * @author Yuliana Apaza
 * @version 2.0
 * @since 2015-10-05
 */

public class Usuario
{
    private String usrCod;
    private String usrIde;
    private String usrDni;
    private String usrNom;
    private String usrApe;
    private String usrPer;
    private String usrEstReg;
    
    public Usuario()
    {
        this("-1", "NULL", "00000000", "NULL", "NULL", "0", "3");
    }
    
    public Usuario(String usrCod, String usrIde, String usrDni, String usrNom, String usrApe, String usrPer, String usrEstReg)
    {
        this.usrCod = usrCod;
        this.usrIde = usrIde;
        this.usrDni = usrDni;
        this.usrNom = usrNom;
        this.usrApe = usrApe;
        this.usrPer = usrPer;
        this.usrEstReg = usrEstReg;
    }

    public String getUsrCod()
    {
        return usrCod;
    }

    public void setUsrCod(String usrCod)
    {
        this.usrCod = usrCod;
    }

    public String getUsrIde()
    {
        return usrIde;
    }

    public void setUsrIde(String usrIde)
    {
        this.usrIde = usrIde;
    }

    public String getUsrDni()
    {
        return usrDni;
    }

    public void setUsrDni(String usrDni)
    {
        this.usrDni = usrDni;
    }

    public String getUsrNom()
    {
        return usrNom;
    }

    public void setUsrNom(String usrNom)
    {
        this.usrNom = usrNom;
    }

    public String getUsrApe()
    {
        return usrApe;
    }

    public void setUsrApe(String usrApe)
    {
        this.usrApe = usrApe;
    }

    public String getUsrPer()
    {
        return usrPer;
    }

    public void setUsrPer(String usrPer)
    {
        this.usrPer = usrPer;
    }

    public String getUsrEstReg()
    {
        return usrEstReg;
    }

    public void setUsrEstReg(String usrEstReg)
    {
        this.usrEstReg = usrEstReg;
    }
    
    public static Usuario validar(String usr, String pass)
    {
        Usuario user = null;
        try
        {
            ResultSet rs = con.ejecutar("SELECT * FROM USUARIO WHERE BINARY UsrIde = ? AND UsrCon = MD5(?) AND UsrEstReg = 1", new String[] {usr, pass}, true);
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
        catch (SQLException | NullPointerException ex)
        {
            JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos.\nConfigure la conexión correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return user;
    }
    
    public String insertar(String pass)
    {
	String msg = "";
        try
        {
            con.ejecutar("INSERT INTO USUARIO VALUES(DEFAULT, ?, MD5(?), ?, ?, ?, ?, ?)", new String[] {usrIde, pass, usrDni, usrNom, usrApe, usrPer, usrEstReg}, false);
        }
        catch (SQLException ex)
        {
            msg = ex.getMessage();
        }
        return msg;
    }
    
    public String modificar(String pass)
    {
	String msg = "";
        try
        {
            con.ejecutar("UPDATE USUARIO SET UsrIde = ?, UsrCon = MD5(?), UsrDni = ?, UsrNom = ?, UsrApe = ?, UsrPer = ? WHERE UsrCod = ?", new String[] {usrIde, pass, usrDni, usrNom, usrApe, usrPer, usrCod}, false);
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
            this.setUsrEstReg("3");
            con.ejecutar("UPDATE USUARIO SET UsrEstReg = 3 WHERE UsrCod = ?", new String[] {usrCod}, false);
        }
        catch (SQLException ex)
        {
            msg = ex.getMessage();
        }
        return msg;
    }
    
    public static ArrayList<Usuario> getLista()
    {
        ArrayList<Usuario> usuarios = new ArrayList<> ();
        try
        {
            ResultSet rs = con.ejecutar("SELECT * FROM USUARIO ORDER BY UsrEstReg ASC, UsrCod ASC", null, true);
            
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
            JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos.\nConfigure la conexión correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
        return usuarios;
    }
    
    public static Usuario buscar(String codigo)
    {
        Usuario u = null;
        try
        {
            ResultSet rs = con.ejecutar("SELECT * FROM USUARIO WHERE UsrCod = ?", new String[] {codigo}, true);
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
            JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos.\nConfigure la conexión correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return u;
    }
    
    public static String sgteCodigo()
    {
        String codigo = "000000";
        try
        {
            ResultSet rs = con.ejecutar("SELECT LPAD((SELECT COUNT(*) + 1 FROM USUARIO), 6, '0') AS nextCod", null, true);
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
