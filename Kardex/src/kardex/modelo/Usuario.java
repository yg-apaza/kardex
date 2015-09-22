package kardex.modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        ResultSet rs = Kardex.con.ejecutar("SELECT * FROM USUARIO WHERE UsrIde = ? AND UsrCon = md5(?) AND UsrEstReg = 1", data);

        try
        {
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
}
