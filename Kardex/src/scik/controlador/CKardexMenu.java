package scik.controlador;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import scik.vista.UIKardexMenu;
import scik.controlador.almacen.CAlmacen;
import scik.controlador.documento.CDocumento;
import scik.controlador.unidad.CUnidad;
import scik.controlador.usuario.CUsuario;
import static scik.KardexIni.user;
import scik.controlador.consulta.CExistencia;
import scik.controlador.kardex.CKardex;
import scik.controlador.producto.CProducto;

public class CKardexMenu implements IKardexMenu
{
    private UIKardexMenu ventana;
    
    public CKardexMenu()
    {
        ventana = new UIKardexMenu(this);
    }
    
    @Override
    public void almacen()
    {
        CAlmacen almacen = new CAlmacen();
        ventana.dispose();
    }
    @Override
    public void cargar(JTextField txtNombre, JTextField txtDni, JLabel lblPermisos, JButton btnUsuario, JButton btnExistencia, JButton btnEntrada, JButton btnSalida)
    {
        txtNombre.setText(user.getUsrNom() + " " + user.getUsrApe());
        txtDni.setText("DNI Nº " + user.getUsrDni());
        lblPermisos.setText(user.getUsrPer().equals("1")?"Administrador":"Usuario");
        if(user.getUsrPer().equals("0"))
        {
            btnUsuario.setEnabled(false);
            btnExistencia.setEnabled(false);
            btnEntrada.setEnabled(false);
            btnSalida.setEnabled(false);
        }
    }
    
    @Override
    public void cerrarSesion()
    {
        CLogin login = new CLogin();
        ventana.dispose();
    }
    
    @Override
    public void usuario()
    {
        CUsuario usuario = new CUsuario();
        ventana.dispose();
    }
    
    @Override
    public void unidad()
    {
        CUnidad unidad = new CUnidad();
        ventana.dispose();
    }
    
    @Override
    public void documento()
    {
        CDocumento documento = new CDocumento();
        ventana.dispose();
    }
    
    @Override
    public void producto()
    {
        CProducto producto = new CProducto();
        ventana.dispose();
    }
    
    @Override
    public void configuracion()
    {
        CConfiguracion config = new CConfiguracion(false);
        ventana.dispose();
    }
    
    @Override
    public void kardex()
    {
        CKardex kardex = new CKardex();
        ventana.dispose();
    }

    @Override
    public void existenciaProducto()
    {
        CExistencia existencia = new CExistencia();
        ventana.dispose();
    }

    @Override
    public void salida()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void entrada()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
