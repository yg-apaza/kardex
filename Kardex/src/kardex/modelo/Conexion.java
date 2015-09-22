package kardex.modelo;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public void conectar()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + database, user, password);
        }
        catch (ClassNotFoundException | SQLException ex)
        {
            System.out.println("Access denied for User: " + user + ", Password: " + password + ". Configure DB connection.");
        }
    }
   
    public ResultSet ejecutar(String comando, String [] data)
    {
        ResultSet rs = null;
        try
        {
            PreparedStatement preparedStmt = con.prepareStatement(comando);
            for(int i = 0; i < data.length; i++)
                preparedStmt.setString(i + 1, data[i]);
            rs = preparedStmt.executeQuery();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
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
