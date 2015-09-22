package kardex.modelo;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexion
{
    private Connection con;
    private String direccion;
    private String database;
    private String user;
    private String password;

    public Conexion(String direccion, String database, String user, String password)
    {
        this.direccion = direccion;
        this.database = database;
        this.user = user;
        this.password = password;
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
            con = (Connection) DriverManager.getConnection("jdbc:mysql://" + direccion + ":3306/" + database, user, password);
        }
        catch (ClassNotFoundException | SQLException ex)
        {
            System.out.println("Access denied for User: " + user + ", Password: " + password + ". Configure DB connection.");
        }
    }
    
    public ResultSet receive(String consulta) throws SQLException
    {
        ResultSet resultado = null;
        Statement stat = (Statement) con.createStatement();
        resultado = stat.executeQuery(consulta);
        return resultado;
    }
    
    public void send(String consulta) throws SQLException
    {
        PreparedStatement preparedStmt = con.prepareStatement(consulta);
        preparedStmt.execute();
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
