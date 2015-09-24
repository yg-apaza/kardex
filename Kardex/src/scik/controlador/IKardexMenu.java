package scik.controlador;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public interface IKardexMenu
{
    public void cargar(JTextField txtNombre, JTextField txtDni, JLabel lblPermisos, JButton btnUsuario, JButton btnExistencia, JButton btnEntrada, JButton btnSalida);
    public void cerrarSesion();
    public void almacen();
    public void usuario();
    public void unidad();
    public void documento();
    public void producto();

    public void configuracion();
}
