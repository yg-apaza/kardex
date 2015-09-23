package kardex.controlador;

import javax.swing.JButton;
import javax.swing.JTextField;

public interface IKardex
{
    public void cargar(JTextField txtNombre, JTextField txtDni, JButton btnUsuario,  JButton btnExistencia, JButton btnEntrada, JButton btnSalida);
    public void cerrarSesion();
    public void almacen();
    public void usuario();
    public void unidad();
    public void documento();
}
