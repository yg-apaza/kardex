package scik.controlador.consulta;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;

public interface ISalida
{
    public void cargar(JComboBox cbxProNom);
    public void verProducto(JTextField txtProCod, JComboBox cbxProNom, JComboBox cbxAnio);
    public void menu();
    public void consultar(JTable tblConsultas, JTextField txtProCod);
    public void generarReporte(JTextField txtProCod, JComboBox cbxAnio, JComboBox cbxMes);
    public void actualizarAnio(JTextField txtProCod, JComboBox cbxAnio, JComboBox cbxMes);
    public void actualizarMes(JComboBox cbxMes);
}
