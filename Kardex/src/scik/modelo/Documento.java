package scik.modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import static scik.KardexIni.con;

/**
 * Representacion de la entidad documento de la base de datos
 * 
 * Controla y gestiona acceso a la base de datos, consultas y peticiones de 
 * manipulacion de datos para la tabla 'documento' en MySQL. 
 * Incluye las funciones de insertar, modificar, eliminar, activar y desactivar.
 * 
 * @author Yuliana Apaza
 * @version 2.0
 * @since 2015-10-05
 */

public class Documento
{
    private String docCod;
    private String docNom;
    private String docEstReg;
        
    public Documento()
    {
        this("-1","NULL", "0");
    }
    
    public Documento(String docCod, String docNom, String docEstReg)
    {
        this.docCod = docCod;
        this.docNom = docNom;
        this.docEstReg = docEstReg;
    }

    public String getDocCod()
    {
        return docCod;
    }

    public void setDocCod(String docCod)
    {
        this.docCod = docCod;
    }

    public String getDocNom()
    {
        return docNom;
    }

    public void setDocNom(String docNom)
    {
        this.docNom = docNom;
    }

    public String getDocEstReg()
    {
        return docEstReg;
    }

    public void setDocEstReg(String docEstReg)
    {
        this.docEstReg = docEstReg;
    }
    
    public String insertar()
    {
        String msg = "";
        try
        {
            con.ejecutar("INSERT INTO DOCUMENTO VALUES(DEFAULT, ?, ?)", new String[] {docNom, docEstReg}, false);
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
            con.ejecutar("UPDATE DOCUMENTO SET DocNom = ? WHERE DocCod = ?", new String[] {docNom, docCod}, false);
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
            this.setDocEstReg("3");
            con.ejecutar("UPDATE DOCUMENTO SET DocEstReg = 3 WHERE DocCod = ?", new String[] {docCod}, false);
        }
        catch (SQLException ex)
        {
             msg = ex.getMessage();
        }
        return msg;
    }
    
    public static ArrayList<Documento> getLista()
    {
        ArrayList<Documento> documentos = new ArrayList<> ();
        try
        {
            ResultSet rs = con.ejecutar("SELECT * FROM DOCUMENTO ORDER BY DocEstReg ASC, DocCod ASC", null, true);
            while(rs.next())
            {
                String codigo = rs.getString("DocCod");
                String nombre = rs.getString("DocNom");
                String estado = rs.getString("DocEstReg");
                Documento doc = new Documento(codigo, nombre, estado);
                documentos.add(doc);
            }
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos.\nConfigure la conexión correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return documentos;
    }   

    public String activar()
    {
        String msg = "";
        try
        {
            this.setDocEstReg("1");
            con.ejecutar("UPDATE DOCUMENTO SET DocEstReg = 1 WHERE DocCod = ?", new String[] {docCod}, false);
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
            this.setDocEstReg("2");
            con.ejecutar("UPDATE DOCUMENTO SET DocEstReg = 2 WHERE DocCod = ?", new String[] {docCod}, false);
        }
        catch (SQLException ex)
        {
             msg = ex.getMessage();
        }
        return msg;
    }
    
    public static Documento buscar(String codigo)
    {
        Documento d = null;
        try
        {
            ResultSet rs = con.ejecutar("SELECT * FROM DOCUMENTO WHERE DocCod = ?", new String[] {codigo}, true);
            rs.next();
            d = new Documento();
            d.setDocCod(rs.getString("DocCod"));
            d.setDocNom(rs.getString("DocNom"));
            d.setDocEstReg(rs.getString("DocEstReg"));
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos.\nConfigure la conexión correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return d;
    }   
    
    public static ArrayList<ArrayList<String>> getActivos()
    {
        ArrayList<ArrayList<String>> documentos = new ArrayList<>();
        try
        {        
            ResultSet rs = con.ejecutar("SELECT DocCod, DocNom FROM DOCUMENTO WHERE DocEstReg = 1", null, true);
            while(rs.next())
            {
                ArrayList<String> data = new ArrayList<>();
                String codigo = rs.getString("DocCod");
                String nombre = rs.getString("DocNom");
                data.add(codigo);
                data.add(nombre);
                documentos.add(data);
            }
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos.\nConfigure la conexión correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return documentos;
    }
    
    public static String sgteCodigo()
    {
        String codigo = "000000";
        try
        {
            ResultSet rs = con.ejecutar("SELECT LPAD((SELECT COUNT(*) + 1 FROM DOCUMENTO), 6, '0') AS nextCod", null, true);
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