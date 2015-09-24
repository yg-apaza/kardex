package scik.modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static scik.KardexIni.con;

public class Kardex_Cab
{
    private String ProCod;
    private String AlmCod;
    private String KarCabCan;
    private String KarCabValUni;
    private String KarCabValTot;
    private String KarCabEstReg;
    
    public Kardex_Cab()
    {
        this("-1", "-1", "0", "0", "0", "1");
    }
    
    public Kardex_Cab(String ProCod, String AlmCod, String KarCabCan, String KarCabValUni, String KarCabValTot, String KarCabEstReg)
    {
        this.ProCod = ProCod;
        this.AlmCod = AlmCod;
        this.KarCabCan = KarCabCan;
        this.KarCabValUni = KarCabValUni;
        this.KarCabEstReg = KarCabEstReg;
        this.KarCabValTot = KarCabValTot;
    }

    public String getProCod()
    {
        return ProCod;
    }

    public void setProCod(String ProCod)
    {
        this.ProCod = ProCod;
    }

    public String getAlmCod()
    {
        return AlmCod;
    }

    public void setAlmCod(String AlmCod)
    {
        this.AlmCod = AlmCod;
    }

    public String getKarCabCan()
    {
        return KarCabCan;
    }

    public void setKarCabCan(String KarCabCan)
    {
        this.KarCabCan = KarCabCan;
    }

    public String getKarCabValUni()
    {
        return KarCabValUni;
    }

    public void setKarCabValUni(String KarCabValUni)
    {
        this.KarCabValUni = KarCabValUni;
    }

    public String getKarCabValTot()
    {
        return KarCabValTot;
    }

    public void setKarCabValTot(String KarCabValTot)
    {
        this.KarCabValTot = KarCabValTot;
    }

    public String getKarCabEstReg()
    {
        return KarCabEstReg;
    }

    public void setKarCabEstReg(String KarCabEstReg) 
    {
        this.KarCabEstReg = KarCabEstReg;
    }
    
    public String insertar()
    {
        try
        {
            con.ejecutar("INSERT INTO KARDEX VALUES(?, ?, ?, ?, ?, ?)", new String[] {ProCod, AlmCod, KarCabCan, KarCabValUni, KarCabValTot, KarCabEstReg}, false);
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
            this.setKarCabEstReg("3");
            con.ejecutar("UPDATE KARDEX SET KarEstReg = 3 WHERE ProCod = ? AND AlmCod = ?", new String[] {ProCod, AlmCod}, false);
            /*
            ResultSet rs = con.ejecutar("SELECT * FROM KARDEX_DET WHERE (ProCod = ? AND AlmCod = ?)", new String[] {ProCod, AlmCod}, true);
            while(rs.next())
            {
                String codigo = rs.getString("KarDetCod");
                (new Kardex_Det()).eliminar(codigo, ProCod, AlmCod);
            }
            */
        }
        catch (SQLException ex)
        {
             return ex.getMessage();
        }
        
        return "";
    }
           
    public static ArrayList<Kardex_Cab> getLista()
    {
        ArrayList<Kardex_Cab> kardex_cab = new ArrayList<>();
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
                Kardex_Cab kardex = new Kardex_Cab(producto, almacen, cantidad, valUni, valTot, estado);
                kardex_cab.add(kardex);
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
              
        return kardex_cab;
    }
    
    public static Kardex_Cab buscar(String codigo1, String codigo2)
    {
        Kardex_Cab k = null;
        try
        {
            ResultSet rs = con.ejecutar("SELECT * FROM KARDEX WHERE (ProCod = ? AND AlmCod = ?)", new String[] {codigo1, codigo2}, true);
            rs.next();
            k = new Kardex_Cab();
            k.setProCod(rs.getString("ProCod"));
            k.setAlmCod(rs.getString("AlmCod"));
            k.setKarCabCan(rs.getString("KarCan"));
            k.setKarCabValUni(rs.getString("KarValUni"));
            k.setKarCabValTot(rs.getString("KarValTot"));
            k.setKarCabEstReg(rs.getString("KarEstReg"));
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return k;
    }
    
    public static ArrayList<Kardex_Det> getDetalles(String p, String a)
    {
        ArrayList<Kardex_Det> detalles = new ArrayList < >();
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
                String usrCod = rs.getString("UsrNom") + " " + rs.getString("UsrApe");
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
                
                Kardex_Det kardex = new Kardex_Det(codigo, producto, almacen, anio, mes, dia, usrCod, docCod, docNum, ope, can, valUni, valTot, salCan, salValUni, salValTot, obs, estado);
                detalles.add(kardex);
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return detalles;
    }
    
    public static ArrayList<Kardex_Det> getDetallesActivos(String p, String a)
    {
        ArrayList<Kardex_Det> detalles = new ArrayList <>();
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
                
                Kardex_Det kardex = new Kardex_Det(codigo, producto, almacen, anio, mes, dia, usrCod, docCod, docNum, ope, can, valUni, valTot, salCan, salValUni, salValTot, obs, estado);
                detalles.add(kardex);
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return detalles;
    }
    
    /*
    public ArrayList<String> getVista(String producto, String almacen)
    {
        Conexion con =  new Conexion();
        con.conectar();
        ArrayList<String> karcab = new ArrayList<String>();
        
        try
        {        
            ResultSet resultado = con.receive("SELECT * FROM VI_KarCab WHERE ProCod = " + producto + " AND AlmCod = " + almacen);
            
            resultado.next();
            String proCod = resultado.getString("ProCod");
            String proNom = resultado.getString("ProNom");
            String uniDes = resultado.getString("UniDes");
            String almCod = resultado.getString("AlmCod");
            String almNom = resultado.getString("AlmNom");
            String can    = resultado.getString("KarCabCan");
            String precio = resultado.getString("KarCabPreUni");
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
            Logger.getLogger(Almacen.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return karcab;
    }
    */
}
