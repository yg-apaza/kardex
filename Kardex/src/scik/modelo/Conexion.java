package scik.modelo;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion
{
    private Connection con;
    private String host;
    private String database;
    private String user;
    private String password;

    public Conexion(String host, String database, String user, String password)
    {
        this.host = host;
        this.database = database;
        this.user = user;
        this.password = password;
    }

    public Connection getCon()
    {
        return con;
    }

    public void setCon(Connection con)
    {
        this.con = con;
    }

    public String getHost()
    {
        return host;
    }

    public void setHost(String host)
    {
        this.host = host;
    }

    public String getDatabase()
    {
        return database;
    }

    public void setDatabase(String database)
    {
        this.database = database;
    }

    public String getUser()
    {
        return user;
    }

    public void setUser(String user)
    {
        this.user = user;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public boolean conectar(boolean verificar)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + database, user, password);
        }
        catch (ClassNotFoundException | SQLException ex)
        {
            if(!verificar)
                JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos.\nConfigure la conexión correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    public ResultSet ejecutar(String comando, String [] data,  boolean receive) throws SQLException
    {
        ResultSet rs = null;
        if(con != null)
        {
            PreparedStatement preparedStmt = con.prepareStatement(comando);
            if(data != null)
                for(int i = 0; i < data.length; i++)
                    preparedStmt.setString(i + 1, data[i]);
            if(receive)
                rs = preparedStmt.executeQuery();
            else
                preparedStmt.execute();
        }
        return rs;
    }
    
    public void desconectar()
    {
        try
        {
            con.close();
        }
        catch (SQLException | NullPointerException ex)
        {
            System.out.println("Access denied for User: " + user + ", Password: " + password + ". Configure DB connection.");
        }
    }
}
