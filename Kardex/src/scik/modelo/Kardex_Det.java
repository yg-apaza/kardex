package scik.modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static scik.KardexIni.con;

public class Kardex_Det
{
    private String KarDetCod;
    private String ProCod;
    private String AlmCod;
    private String KarDetAnio;
    private String KarDetMes;
    private String KarDetDia;
    private String UsrCod;
    private String DocCod;
    private String KarDetDocNum;
    private String KarDetOpe;
    private String KarDetCan;
    private String KarDetValUni;
    private String KarDetValTot;
    private String KarDetSalCan;
    private String KarDetSalValUni;
    private String KarDetSalValTot;
    private String KarDetObs;
    private String KarDetEstReg;
    
    public Kardex_Det()
    {
        this("-1", "-1", "-1", "0", "0", "0", "-1", "-1", "0", "0", "0", "0", "0", "0", "0", "0", "NULL", "0");
    }

    public Kardex_Det(String KarDetCod, String ProCod, String AlmCod, String KarDetAnio, String KarDetMes, String KarDetDia, String UsrCod, String DocCod, String KarDetDocNum, String KarDetOpe, String KarDetCan, String KarDetValUni, String KarDetValTot, String KarDetSalCan, String KarDetSalValUni, String KarDetSalValTot, String KarDetObs, String KarDetEstReg)
    {
        this.KarDetCod = KarDetCod;
        this.ProCod = ProCod;
        this.AlmCod = AlmCod;
        this.KarDetAnio = KarDetAnio;
        this.KarDetMes = KarDetMes;
        this.KarDetDia = KarDetDia;
        this.UsrCod = UsrCod;
        this.DocCod = DocCod;
        this.KarDetDocNum = KarDetDocNum;
        this.KarDetOpe = KarDetOpe;
        this.KarDetCan = KarDetCan;
        this.KarDetValUni = KarDetValUni;
        this.KarDetValTot = KarDetValTot;
        this.KarDetSalCan = KarDetSalCan;
        this.KarDetSalValUni = KarDetSalValUni;
        this.KarDetSalValTot = KarDetSalValTot;
        this.KarDetObs = KarDetObs;
        this.KarDetEstReg = KarDetEstReg;
    }

    public String getKarDetCod()
    {
        return KarDetCod;
    }

    public void setKarDetCod(String KarDetCod)
    {
        this.KarDetCod = KarDetCod;
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

    public String getKarDetAnio()
    {
        return KarDetAnio;
    }

    public void setKarDetAnio(String KarDetAnio)
    {
        this.KarDetAnio = KarDetAnio;
    }

    public String getKarDetMes()
    {
        return KarDetMes;
    }

    public void setKarDetMes(String KarDetMes)
    {
        this.KarDetMes = KarDetMes;
    }

    public String getKarDetDia()
    {
        return KarDetDia;
    }

    public void setKarDetDia(String KarDetDia)
    {
        this.KarDetDia = KarDetDia;
    }

    public String getUsrCod()
    {
        return UsrCod;
    }

    public void setUsrCod(String UsrCod)
    {
        this.UsrCod = UsrCod;
    }

    public String getDocCod()
    {
        return DocCod;
    }

    public void setDocCod(String DocCod)
    {
        this.DocCod = DocCod;
    }

    public String getKarDetDocNum()
    {
        return KarDetDocNum;
    }

    public void setKarDetDocNum(String KarDetDocNum)
    {
        this.KarDetDocNum = KarDetDocNum;
    }

    public String getKarDetOpe()
    {
        return KarDetOpe;
    }

    public void setKarDetOpe(String KarDetOpe)
    {
        this.KarDetOpe = KarDetOpe;
    }

    public String getKarDetCan()
    {
        return KarDetCan;
    }

    public void setKarDetCan(String KarDetCan)
    {
        this.KarDetCan = KarDetCan;
    }

    public String getKarDetValUni()
    {
        return KarDetValUni;
    }

    public void setKarDetValUni(String KarDetValUni)
    {
        this.KarDetValUni = KarDetValUni;
    }

    public String getKarDetValTot()
    {
        return KarDetValTot;
    }

    public void setKarDetValTot(String KarDetValTot)
    {
        this.KarDetValTot = KarDetValTot;
    }

    public String getKarDetSalCan()
    {
        return KarDetSalCan;
    }

    public void setKarDetSalCan(String KarDetSalCan)
    {
        this.KarDetSalCan = KarDetSalCan;
    }

    public String getKarDetSalValUni()
    {
        return KarDetSalValUni;
    }

    public void setKarDetSalValUni(String KarDetSalValUni)
    {
        this.KarDetSalValUni = KarDetSalValUni;
    }

    public String getKarDetSalValTot()
    {
        return KarDetSalValTot;
    }

    public void setKarDetSalValTot(String KarDetSalValTot)
    {
        this.KarDetSalValTot = KarDetSalValTot;
    }

    public String getKarDetObs()
    {
        return KarDetObs;
    }

    public void setKarDetObs(String KarDetObs)
    {
        this.KarDetObs = KarDetObs;
    }

    public String getKarDetEstReg()
    {
        return KarDetEstReg;
    }

    public void setKarDetEstReg(String KarDetEstReg)
    {
        this.KarDetEstReg = KarDetEstReg;
    }
    
    public String insertar()
    {
        try
        {
            con.ejecutar(   "INSERT INTO KARDEX_DET VALUES(DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                            new String[] {
                                ProCod,
                                AlmCod,
                                KarDetAnio,
                                KarDetMes,
                                KarDetDia,
                                UsrCod,
                                DocCod,
                                KarDetDocNum,
                                KarDetOpe,
                                KarDetCan,
                                KarDetValUni,
                                KarDetValTot,
                                KarDetSalCan,
                                KarDetSalValUni,
                                KarDetSalValTot,
                                KarDetObs,
                                KarDetEstReg
                            },
                            false
                        );
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
            con.ejecutar("UPDATE KARDEX_DET SET KarDetAnio = ?, "
                                            + "KarDetMes = ?, "
                                            + "KarDetDia = ?, "
                                            + "UsrCod = ?, "
                                            + "DocCod = ?, "
                                            + "KarDetDocNum = ?, "
                                            + "KarDetOpe = ?, "
                                            + "KarDetCan = ?, "
                                            + "KarDetValUni = ?, "
                                            + "KarDetValTot = ?, "
                                            + "KarDetSalCan = ?, "
                                            + "KarDetSalValUni = ?, "
                                            + "KarDetSalValTot = ?, "
                                            + "KarDetObs = ? "
                                            + "WHERE (KarDetCod = ? AND ProCod = ? AND AlmCod = ?)",
                        new String[] {
                                            KarDetAnio,
                                            KarDetMes,
                                            KarDetDia,
                                            UsrCod,
                                            DocCod,
                                            KarDetDocNum,
                                            KarDetOpe,
                                            KarDetCan,
                                            KarDetValUni,
                                            KarDetValTot,
                                            KarDetSalCan,
                                            KarDetSalValUni,
                                            KarDetSalValTot,
                                            KarDetObs,
                                            KarDetCod,
                                            ProCod,
                                            AlmCod
                        },
                        false);
        }
        catch (SQLException ex)
        {
            return ex.getMessage();
        }
        
        return "";
    }
    
    public String eliminar(String codigo1, String codigo2, String codigo3)
    {
        
        try
        {
            this.setKarDetEstReg("3");
            con.ejecutar("UPDATE KARDEX_DET SET KarDetEstReg = 3 WHERE (KarDetCod = ? AND ProCod = ? AND AlmCod = ?)", new String[] {codigo1, codigo2, codigo3}, false);
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            return ex.getMessage();
        }
        return "";
    }
    
    public static ArrayList<Kardex_Det> getLista()
    {
        ArrayList<Kardex_Det> kardex_det = new ArrayList<> ();
        try
        {
            ResultSet rs = con.ejecutar("SELECT * FROM KARDEX_DET", null, true);
            
            while(rs.next())
            {
                String codigo = rs.getString("KarDetCod");
                String producto = rs.getString("ProCod");
                String almacen = rs.getString("AlmCod");
                String anio = rs.getString("KarDetAnio");
                String mes = rs.getString("KarDetMes");
                String dia = rs.getString("KarDetDia");
                String usuario = rs.getString("UsrCod");
                String documento = rs.getString("DocCod");
                String numDoc = rs.getString("KarDetDocNum");
                String operacion = rs.getString("KarDetOpe");
                String cantidad = rs.getString("KarDetCan");
                String valorU = rs.getString("KarDetValUni");
                String total = rs.getString("KarDetValTot");
                String s_cantidad = rs.getString("KarDetSalCan");
                String s_valorU = rs.getString("KarDetSalValUni");
                String s_total = rs.getString("KarDetSalValTot");
                String observ = rs.getString("KarDetObs");
                String estado = rs.getString("KarDetEstReg");

                Kardex_Det kardex = new Kardex_Det(codigo, producto, almacen, anio, mes, dia, usuario, documento, numDoc, operacion, cantidad, valorU, total, s_cantidad, s_valorU, s_total, observ, estado);
                kardex_det.add(kardex);
            }
            
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return kardex_det;
    }
    
    public static Kardex_Det buscar(String codigo1, String codigo2, String codigo3)
    {
        Kardex_Det detalle = null;
        try
        {
            ResultSet rs = con.ejecutar("SELECT * FROM KARDEX_DET WHERE (KarDetCod = ? AND ProCod = ? AND AlmCod = ?)",
                                                new String[] {codigo1, codigo2, codigo3}, true);
            rs.next();
            detalle = new Kardex_Det();
            detalle.setKarDetCod(rs.getString("KarDetCod"));
            detalle.setProCod(rs.getString("ProCod"));
            detalle.setAlmCod(rs.getString("AlmCod"));
            detalle.setKarDetAnio(rs.getString("KarDetAnio"));
            detalle.setKarDetMes(rs.getString("KarDetMes"));
            detalle.setKarDetDia(rs.getString("KarDetDia"));
            detalle.setUsrCod(rs.getString("UsrCod"));
            detalle.setDocCod(rs.getString("DocCod"));
            detalle.setKarDetDocNum(rs.getString("KarDetDocNum"));
            detalle.setKarDetOpe(rs.getString("KarDetOpe"));
            detalle.setKarDetCan(rs.getString("KarDetCan"));
            detalle.setKarDetValUni(rs.getString("KarDetValUni"));
            detalle.setKarDetValTot(rs.getString("KarDetValTot"));
            detalle.setKarDetSalCan(rs.getString("KarDetSalCan"));
            detalle.setKarDetSalValUni(rs.getString("KarDetSalValUni"));
            detalle.setKarDetSalValTot(rs.getString("KarDetSalValTot"));
            detalle.setKarDetObs(rs.getString("KarDetObs"));
            detalle.setKarDetEstReg(rs.getString("KarDetEstReg"));
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return detalle;
    }
    
    public static String sgteCodigo()
    {
        try
        {
            ResultSet rs = con.ejecutar("SELECT LPAD((SELECT COUNT(*) + 1 FROM KARDEX_DET), 6, '0') AS nextCod", null, true);
            rs.next();
            return rs.getString("nextCod");
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return "000000";
    }
    
    public static ArrayList<ArrayList<String>> getVista(String producto, String almacen)
    {
        ArrayList<ArrayList<String>> kardet = new ArrayList<>();
        try
        {        
            ResultSet resultado = con.ejecutar("SELECT * FROM VI_KarDet WHERE ProCod = ? AND AlmCod = ?", new String[] {producto, almacen}, true);
            while(resultado.next())
            {
                ArrayList<String> data = new ArrayList<>();
                String codigo = resultado.getString("KarDetCod");
                String fecha = resultado.getString("KarDetAnio") + "/" + resultado.getString("KarDetMes") + "/" + resultado.getString("KarDetDia");
                String documento = resultado.getString("DocNom");
                String numDoc = resultado.getString("KarDetDocNum");
                String operacion = resultado.getString("KarDetOpe").equals("1")?"Entrada":"Salida";
                String cantidad = resultado.getString("KarDetCan");
                String valorU = resultado.getString("KarDetValUni");
                String total = resultado.getString("KarDetValTot");
                String s_cantidad = resultado.getString("KarDetSalCan");
                String s_valorU = resultado.getString("KarDetSalValUni");
                String s_total = resultado.getString("KarDetSalValTot");
                String observ = resultado.getString("KarDetObs");
                
                data.add(codigo);
                data.add(fecha);
                data.add(documento);
                data.add(numDoc);
                data.add(operacion);
                data.add(cantidad);
                data.add(valorU);
                data.add(total);
                data.add(s_cantidad);
                data.add(s_valorU);
                data.add(s_total);
                data.add(observ);
                
                kardet.add(data);
            }
        
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return kardet;
    }
}
