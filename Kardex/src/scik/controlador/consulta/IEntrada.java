package scik.controlador.consulta;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * Interfaz de la consulta de entrada de productos
 * 
 * Metodos disponibles para el controlador de consulta de entradas.
 *  
 * @author Yuliana Apaza
 * @version 2.0
 * @since 2015-10-05
 */

public interface IEntrada
{
    public void cargar(JComboBox cbxProNom);
    public void verProducto(JTextField txtProCod, JComboBox cbxProNom, JComboBox cbxAnio);
    public void menu();
    public void consultar(JTable tblConsultas, JTextField txtProCod);
    public void generarReporte(JTextField txtProCod, JComboBox cbxAnio, JComboBox cbxMes);
    public void actualizarAnio(JTextField txtProCod, JComboBox cbxAnio, JComboBox cbxMes);
    public void actualizarMes(JComboBox cbxMes);
}
