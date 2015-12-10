package scik.modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import static scik.KardexIni.con;

/**
 * Representacion de la entidad Cabecera de Kardex  de la base de datos
 * 
 * Controla y gestiona acceso a la base de datos, consultas y peticiones de 
 * manipulacion de datos para la tabla 'kardex' en MySQL. 
 * Incluye las funciones de insertar y eliminar.
 * 
 * @author Yuliana Apaza
 * @version 2.0
 * @since 2015-10-05
 */

public class KardexCab
{
    private String proCod;
    private String almCod;
    private String karCabCan;
    private String karCabValUni;
    private String karCabValTot;
    private String karCabEstReg;
    
    public KardexCab()
    {
        this("-1", "-1", "0", "0", "0", "1");
    }
    
    public KardexCab(String proCod, String almCod, String karCabCan, String karCabValUni, String karCabValTot, String karCabEstReg)
    {
        this.proCod = proCod;
        this.almCod = almCod;
        this.karCabCan = karCabCan;
        this.karCabValUni = karCabValUni;
        this.karCabEstReg = karCabEstReg;
        this.karCabValTot = karCabValTot;
    }

    public String getProCod()
    {
        return proCod;
    }

    public void setProCod(String proCod)
    {
        this.proCod = proCod;
    }

    public String getAlmCod()
    {
        return almCod;
    }

    public void setAlmCod(String almCod)
    {
        this.almCod = almCod;
    }

    public String getKarCabCan()
    {
        return karCabCan;
    }

    public void setKarCabCan(String karCabCan)
    {
        this.karCabCan = karCabCan;
    }

    public String getKarCabValUni()
    {
        return karCabValUni;
    }

    public void setKarCabValUni(String karCabValUni)
    {
        this.karCabValUni = karCabValUni;
    }

    public String getKarCabValTot()
    {
        return karCabValTot;
    }

    public void setKarCabValTot(String karCabValTot)
    {
        this.karCabValTot = karCabValTot;
    }

    public String getKarCabEstReg()
    {
        return karCabEstReg;
    }

    public void setKarCabEstReg(String karCabEstReg) 
    {
        this.karCabEstReg = karCabEstReg;
    }
    
    public String insertar()
    {
        String msg = "";
        try
        {
            con.ejecutar("INSERT INTO KARDEX VALUES(?, ?, ?, ?, ?, ?)", new String[] {proCod, almCod, karCabCan, karCabValUni, karCabValTot, karCabEstReg}, false);
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
            this.setKarCabEstReg("3");
            con.ejecutar("UPDATE KARDEX SET KarEstReg = 3 WHERE ProCod = ? AND AlmCod = ?", new String[] {proCod, almCod}, false);
        }
        catch (SQLException ex)
        {
             msg = ex.getMessage();
        }
        
        return msg;
    }
           
    public static ArrayList<KardexCab> getLista()
    {
        ArrayList<KardexCab> kardex_cab = new ArrayList<>();
        try
        {
            ResultSet rs = con.ejecutar("SELECT * FROM KARDEX ORDER BY KarEstReg ASC, ProCod ASC, AlmCod ASC", null, true);
            while(rs.next())
            {
                String producto = rs.getString("ProCod");
                String almacen = rs.getString("AlmCod");
                String cantidad = rs.getString("KarCan");
                String valUni = rs.getString("KarValUni");
                String valTot = rs.getString("KarValTot");
                String estado = rs.getString("KarEstReg");
                KardexCab kardex = new KardexCab(producto, almacen, cantidad, valUni, valTot, estado);
                kardex_cab.add(kardex);
            }
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos.\nConfigure la conexión correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
              
        return kardex_cab;
    }

    public static ArrayList<KardexDet> getDetalles(String p, String a)
    {
        ArrayList<KardexDet> detalles = new ArrayList < >();
        try
        {
            ResultSet rs = con.ejecutar("SELECT KarDetCod, ProCod, AlmCod, KarDetAnio, KarDetMes, KarDetDia, UsrNom, UsrApe, DocNom, KarDetDocNum, KarDetOpe, KarDetCan, KarDetValUni, KarDetValTot, KarDetSalCan, KarDetSalValUni, KarDetSalValTot, KarDetObs, KarDetEstReg FROM KARDEX_DET INNER JOIN DOCUMENTO ON KARDEX_DET.DocCod = DOCUMENTO.DocCod INNER JOIN USUARIO ON KARDEX_DET.UsrCod = USUARIO.UsrCod WHERE ProCod = ? AND AlmCod = ?", new String[] {p, a}, true);
            while(rs.next())
            {
                String codigo = rs.getString("KarDetCod");
                String producto = rs.getString("ProCod");
                String almacen = rs.getString("AlmCod");
                String anio = rs.getString("KarDetAnio");
                String mes = rs.getString("KarDetMes");
                String dia = rs.getString("KarDetDia");
                StringBuffer usrCod = new StringBuffer( rs.getString("UsrNom").length() + 
                                                        rs.getString("UsrApe").length() + 1
                                                        ).append(rs.getString("UsrNom"))
                                                        .append(' ')
                                                        .append(rs.getString("UsrApe"));
                String docCod = rs.getString("DocNom");
                String docNum = rs.getString("KarDetDocNum");
                String ope = rs.getString("KarDetOpe");
                String can = rs.getString("KarDetCan");
                String valUni = rs.getString("KarDetValUni");
                String valTot = rs.getString("KarDetValTot");
                String salCan = rs.getString("KarDetSalCan");
                String salValUni = rs.getString("KarDetSalValUni");
                String salValTot = rs.getString("KarDetSalValTot");
                String obs = rs.getString("KarDetObs");
                String estado = rs.getString("KarDetEstReg");
                
                KardexDet kardex = new KardexDet(codigo, producto, almacen, anio, mes, dia, usrCod.toString(), docCod, docNum, ope, can, valUni, valTot, salCan, salValUni, salValTot, obs, estado);
                detalles.add(kardex);
            }
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos.\nConfigure la conexión correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return detalles;
    }
    
    public static ArrayList<KardexDet> getDetallesActivos(String p, String a)
    {
        ArrayList<KardexDet> detalles = new ArrayList <>();
        try
        {
            ResultSet rs = con.ejecutar("SELECT * FROM KARDEX_DET WHERE ProCod = ? AND AlmCod = ? AND KarDetEstReg = 1", new String[] {p, a}, true);
            while(rs.next())
            {
                String codigo = rs.getString("KarDetCod");
                String producto = rs.getString("ProCod");
                String almacen = rs.getString("AlmCod");
                String anio = rs.getString("KarDetAnio");
                String mes = rs.getString("KarDetMes");
                String dia = rs.getString("KarDetDia");
                String usrCod = rs.getString("UsrCod");
                String docCod = rs.getString("DocCod");
                String docNum = rs.getString("KarDetDocNum");
                String ope = rs.getString("KarDetOpe");
                String can = rs.getString("KarDetCan");
                String valUni = rs.getString("KarDetValUni");
                String valTot = rs.getString("KarDetValTot");
                String salCan = rs.getString("KarDetSalCan");
                String salValUni = rs.getString("KarDetSalValUni");
                String salValTot = rs.getString("KarDetSalValTot");
                String obs = rs.getString("KarDetObs");
                String estado = rs.getString("KarDetEstReg");
                
                KardexDet kardex = new KardexDet(codigo, producto, almacen, anio, mes, dia, usrCod, docCod, docNum, ope, can, valUni, valTot, salCan, salValUni, salValTot, obs, estado);
                detalles.add(kardex);
            }
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos.\nConfigure la conexión correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return detalles;
    }
    
    public static ArrayList<String> getVista(String producto, String almacen)
    {
        ArrayList<String> karcab = new ArrayList<>();
        try
        {        
            ResultSet resultado = con.ejecutar("SELECT * FROM VI_KarCab WHERE ProCod = ? AND AlmCod = ?", new String[] {producto, almacen}, true);
            resultado.next();
            String proCod = resultado.getString("ProCod");
            String proNom = resultado.getString("ProNom");
            String uniDes = resultado.getString("UniDes");
            String almCod = resultado.getString("AlmCod");
            String almNom = resultado.getString("AlmNom");
            String can    = resultado.getString("KarCan");
            String precio = resultado.getString("KarValUni");
            karcab.add(proCod);
            karcab.add(proNom);
            karcab.add(uniDes);
            karcab.add(almCod);
            karcab.add(almNom);
            karcab.add(can);
            karcab.add(precio);
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos.\nConfigure la conexión correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return karcab;
    }
}
