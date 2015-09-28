package scik.controlador.consulta;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;

public interface IExistencia
{
    public void cargar(JComboBox cbxProNom);
    public void verProducto(JTextField txtProCod, JComboBox cbxProNom);
    public void menu();
    public void consultar(JTable tblConsultas, JTextField txtProCod, JTextField txtTotal);
    public void generarReporte(JTextField txtProCod);
}
