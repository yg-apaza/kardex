package kardex.controlador.almacen;

import javax.swing.JCheckBox;
import javax.swing.JTable;

public interface IAlmacen
{
    public void cargar(JTable registros);
    public void actualizarEst(JTable registros, JCheckBox est);
    public void menu();

    public void insertar();

    public void modificar(JTable tblRegistros);
}
