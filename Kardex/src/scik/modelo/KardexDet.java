package scik.modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import static scik.KardexIni.con;

public class KardexDet
{
    private String karDetCod;
    private String proCod;
    private String almCod;
    private String karDetAnio;
    private String karDetMes;
    private String karDetDia;
    private String usrCod;
    private String docCod;
    private String karDetDocNum;
    private String karDetOpe;
    private String karDetCan;
    private String karDetValUni;
    private String karDetValTot;
    private String karDetSalCan;
    private String karDetSalValUni;
    private String karDetSalValTot;
    private String karDetObs;
    private String karDetEstReg;
    
    public KardexDet()
    {
        this("-1", "-1", "-1", "0", "0", "0", "-1", "-1", "0", "0", "0", "0", "0", "0", "0", "0", "NULL", "0");
    }

    public KardexDet(String karDetCod, String proCod, String almCod, String karDetAnio, String karDetMes, String karDetDia, String usrCod, String docCod, String karDetDocNum, String karDetOpe, String karDetCan, String karDetValUni, String karDetValTot, String karDetSalCan, String karDetSalValUni, String karDetSalValTot, String karDetObs, String karDetEstReg)
    {
        this.karDetCod = karDetCod;
        this.proCod = proCod;
        this.almCod = almCod;
        this.karDetAnio = karDetAnio;
        this.karDetMes = karDetMes;
        this.karDetDia = karDetDia;
        this.usrCod = usrCod;
        this.docCod = docCod;
        this.karDetDocNum = karDetDocNum;
        this.karDetOpe = karDetOpe;
        this.karDetCan = karDetCan;
        this.karDetValUni = karDetValUni;
        this.karDetValTot = karDetValTot;
        this.karDetSalCan = karDetSalCan;
        this.karDetSalValUni = karDetSalValUni;
        this.karDetSalValTot = karDetSalValTot;
        this.karDetObs = karDetObs;
        this.karDetEstReg = karDetEstReg;
    }

    public String getKarDetCod()
    {
        return karDetCod;
    }

    public void setKarDetCod(String karDetCod)
    {
        this.karDetCod = karDetCod;
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

    public String getKarDetAnio()
    {
        return karDetAnio;
    }

    public void setKarDetAnio(String karDetAnio)
    {
        this.karDetAnio = karDetAnio;
    }

    public String getKarDetMes()
    {
        return karDetMes;
    }

    public void setKarDetMes(String karDetMes)
    {
        this.karDetMes = karDetMes;
    }

    public String getKarDetDia()
    {
        return karDetDia;
    }

    public void setKarDetDia(String karDetDia)
    {
        this.karDetDia = karDetDia;
    }

    public String getUsrCod()
    {
        return usrCod;
    }

    public void setUsrCod(String usrCod)
    {
        this.usrCod = usrCod;
    }

    public String getDocCod()
    {
        return docCod;
    }

    public void setDocCod(String docCod)
    {
        this.docCod = docCod;
    }

    public String getKarDetDocNum()
    {
        return karDetDocNum;
    }

    public void setKarDetDocNum(String karDetDocNum)
    {
        this.karDetDocNum = karDetDocNum;
    }

    public String getKarDetOpe()
    {
        return karDetOpe;
    }

    public void setKarDetOpe(String karDetOpe)
    {
        this.karDetOpe = karDetOpe;
    }

    public String getKarDetCan()
    {
        return karDetCan;
    }

    public void setKarDetCan(String karDetCan)
    {
        this.karDetCan = karDetCan;
    }

    public String getKarDetValUni()
    {
        return karDetValUni;
    }

    public void setKarDetValUni(String karDetValUni)
    {
        this.karDetValUni = karDetValUni;
    }

    public String getKarDetValTot()
    {
        return karDetValTot;
    }

    public void setKarDetValTot(String karDetValTot)
    {
        this.karDetValTot = karDetValTot;
    }

    public String getKarDetSalCan()
    {
        return karDetSalCan;
    }

    public void setKarDetSalCan(String karDetSalCan)
    {
        this.karDetSalCan = karDetSalCan;
    }

    public String getKarDetSalValUni()
    {
        return karDetSalValUni;
    }

    public void setKarDetSalValUni(String karDetSalValUni)
    {
        this.karDetSalValUni = karDetSalValUni;
    }

    public String getKarDetSalValTot()
    {
        return karDetSalValTot;
    }

    public void setKarDetSalValTot(String karDetSalValTot)
    {
        this.karDetSalValTot = karDetSalValTot;
    }

    public String getKarDetObs()
    {
        return karDetObs;
    }

    public void setKarDetObs(String karDetObs)
    {
        this.karDetObs = karDetObs;
    }

    public String getKarDetEstReg()
    {
        return karDetEstReg;
    }

    public void setKarDetEstReg(String karDetEstReg)
    {
        this.karDetEstReg = karDetEstReg;
    }
    
    public String insertar()
    {
        String msg = "";
        try
        {
            con.ejecutar(   "INSERT INTO KARDEX_DET VALUES(DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                            new String[] {
                                proCod,
                                almCod,
                                karDetAnio,
                                karDetMes,
                                karDetDia,
                                usrCod,
                                docCod,
                                karDetDocNum,
                                karDetOpe,
                                karDetCan,
                                karDetValUni,
                                karDetValTot,
                                karDetSalCan,
                                karDetSalValUni,
                                karDetSalValTot,
                                karDetObs,
                                karDetEstReg
                            },
                            false
                        );
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
                                            karDetAnio,
                                            karDetMes,
                                            karDetDia,
                                            usrCod,
                                            docCod,
                                            karDetDocNum,
                                            karDetOpe,
                                            karDetCan,
                                            karDetValUni,
                                            karDetValTot,
                                            karDetSalCan,
                                            karDetSalValUni,
                                            karDetSalValTot,
                                            karDetObs,
                                            karDetCod,
                                            proCod,
                                            almCod
                        },
                        false);
        }
        catch (SQLException ex)
        {
            msg = ex.getMessage();
        }
        
        return msg;
    }
    
    public String eliminar(String codigo1, String codigo2, String codigo3)
    {
        String msg = "";
        try
        {
            this.setKarDetEstReg("3");
            con.ejecutar("UPDATE KARDEX_DET SET KarDetEstReg = 3 WHERE (KarDetCod = ? AND ProCod = ? AND AlmCod = ?)", new String[] {codigo1, codigo2, codigo3}, false);
        }
        catch (SQLException ex)
        {
            msg = ex.getMessage();
        }
        return msg;
    }

    public static KardexDet buscar(String codigo1, String codigo2, String codigo3)
    {
        KardexDet detalle = null;
        try
        {
            ResultSet rs = con.ejecutar("SELECT * FROM KARDEX_DET WHERE (KarDetCod = ? AND ProCod = ? AND AlmCod = ?)",
                                                new String[] {codigo1, codigo2, codigo3}, true);
            rs.next();
            detalle = new KardexDet();
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
            JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos.\nConfigure la conexión correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return detalle;
    }
    
    public static String sgteCodigo()
    {
        String codigo = "000000";
        try
        {
            ResultSet rs = con.ejecutar("SELECT LPAD((SELECT COUNT(*) + 1 FROM KARDEX_DET), 6, '0') AS nextCod", null, true);
            rs.next();
            codigo = rs.getString("nextCod");
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos.\nConfigure la conexión correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return codigo;
    }
    
    public static ArrayList<ArrayList<String>> getVista(String producto, String almacen)
    {
        ArrayList<ArrayList<String>> kardet = new ArrayList<>();
        try
        {        
            ResultSet resultado = con.ejecutar("SELECT * FROM VI_KarDet WHERE ProCod = ? AND AlmCod = ?", new String[] {producto, almacen}, true);
            String operacion = "";
            
            while(resultado.next())
            {
                if(resultado.getString("KarDetOpe").equals("1"))
                    operacion = "Entrada";
                else
                    operacion = "Salida";
                ArrayList<String> data = new ArrayList<>();
                String codigo = resultado.getString("KarDetCod");
                String fecha = resultado.getString("KarDetAnio") + "/" + resultado.getString("KarDetMes") + "/" + resultado.getString("KarDetDia");
                String documento = resultado.getString("DocNom");
                String numDoc = resultado.getString("KarDetDocNum");
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
            JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos.\nConfigure la conexión correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return kardet;
    }
}
