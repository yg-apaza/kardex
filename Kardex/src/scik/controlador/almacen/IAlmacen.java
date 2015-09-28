package scik.controlador.almacen;

import javax.swing.JCheckBox;
import javax.swing.JTable;

public interface IAlmacen
{
    public void cargar(JTable registros);
    public void actualizarEst(JTable registros, JCheckBox est);
    public void menu();
    public void insertar();
    public void modificar(JTable tblRegistros);
    public void activar_desactivar(JTable tblRegistros, JCheckBox chEst);
    public void eliminar(JTable tblRegistros, JCheckBox est);
    public void generarReporte();
}
