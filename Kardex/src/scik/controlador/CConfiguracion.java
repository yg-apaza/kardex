package scik.controlador;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import scik.modelo.Conexion;
import scik.vista.UIConfiguracion;
import static scik.KardexIni.con;

public class CConfiguracion implements IConfiguracion
{
    private UIConfiguracion ventana;
    private boolean retornar_login;
    
    public CConfiguracion(boolean retornar_login)
    {
        con.desconectar();
        this.retornar_login = retornar_login;
        ventana = new UIConfiguracion(this);
    }
    
    @Override
    public void cancelar()
    {
        CKardexMenu menu;
        CLogin login;
        if(retornar_login)
            login = new CLogin();
        else
            menu = new CKardexMenu();
        con.conectar(false);
        ventana.dispose();
    }
    
    @Override
    public void cargar(JTextField txtHost, JTextField txtUsuario)
    {
        FileReader fr = null;
        try
        {
            String [] conexion_data = new String[3];
            fr = new FileReader("conexion.dat");
            BufferedReader br = new BufferedReader(fr);
            String linea = "";
            int number = 0;
            while((linea = br.readLine()) != null)
            {
                conexion_data[number] = linea.substring(linea.indexOf("=") + 1, linea.length());
                number++;
                if(number > 2)
                    break;
            }
            txtHost.setText(conexion_data[0]);
            txtUsuario.setText(conexion_data[1]);
        }
        catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                fr.close();
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
    }
    
    @Override
    public void verificar(JTextField txtHost, JTextField txtUsuario, JPasswordField txtPass, JLabel lblEstado)
    {
        Conexion newCon = new Conexion(txtHost.getText(), "BD_KARDEX", txtUsuario.getText(), String.valueOf(txtPass.getPassword()));
        if(newCon.conectar(true))
        {
            lblEstado.setForeground(new Color(0, 150, 0));
            lblEstado.setText("Configuración correcta");
        }
        else
        {
            lblEstado.setForeground(new Color(255, 0, 0));
            lblEstado.setText("Configuración incorrecta");
        }
        newCon.desconectar();
    }
    
    @Override
    public void aceptar(JTextField txtHost, JTextField txtUsuario, JPasswordField txtPass)
    {
        PrintWriter out = null;
        try
        {
            con.setHost(txtHost.getText());
            con.setUser(txtUsuario.getText());
            con.setPassword(String.valueOf(txtPass.getPassword()));
            
            out = new PrintWriter("conexion.dat");
            out.print(  "host=" + txtHost.getText() +
                        "\nusuario=" + txtUsuario.getText() +
                        "\npassword=" + String.valueOf(txtPass.getPassword()) +
                        "\n\nNo editar este archivo.");
            
            con.conectar(false);
            
            CKardexMenu menu;
            CLogin login;
            if(retornar_login)
                login = new CLogin();
            else
                menu = new CKardexMenu();
            
            ventana.dispose();
        }
        catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        } finally
        {
            out.close();
        }
    }
}
