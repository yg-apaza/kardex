package kardex.controlador.unidad;

import javax.swing.JCheckBox;
import javax.swing.JTable;

public interface IUnidad
{
    public void cargar(JTable tblRegistros);
    public void actualizarEst(JTable tblRegistros, JCheckBox chActivar);
    public void menu();
}
