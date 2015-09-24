package scik.controlador.producto;

import javax.swing.JCheckBox;
import javax.swing.JTable;

public interface IProducto
{
    public void actualizarEst(JTable tblRegistros, JCheckBox chActivar);
    public void cargar(JTable tblRegistros);
    public void insertar();
    public void menu();
    public void modificar(JTable tblRegistros);
    public void eliminar(JTable tblRegistros, JCheckBox chActivar);
    public void activar_desactivar(JTable tblRegistros, JCheckBox chActivar);
}
