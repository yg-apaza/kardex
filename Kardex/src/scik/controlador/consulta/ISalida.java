package scik.controlador.consulta;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;

public interface ISalida
{
    public void cargar(JComboBox cbxProNom);
    public void verProducto(JTextField txtProCod, JComboBox cbxProNom);
    public void menu();
    public void consultar(JTable tblConsultas, JTextField txtProCod, JComboBox cbxAnio, JComboBox cbxMes);
    public void generarReporte(JTextField txtProCod, JComboBox cbxAnio, JComboBox cbxMes);
}
