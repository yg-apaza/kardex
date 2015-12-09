package scik.controlador.consulta;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * Interfaz de la consulta de existencia de productos
 * 
 * Métodos disponibles para el controlador de consulta de existencias.
 *  
 * @author Yuliana Apaza
 * @version 2.0
 * @since 2015-10-05
 */

public interface IExistencia
{
    public void cargar(JComboBox cbxProNom);
    public void verProducto(JTextField txtProCod, JComboBox cbxProNom);
    public void menu();
    public void consultar(JTable tblConsultas, JTextField txtProCod, JTextField txtTotal);
    public void generarReporte(JTextField txtProCod);
}
