package scik.controlador.producto;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * Interfaz de la gestion de producto
 * 
 * Metodos disponibles para el controlador de gestion de producto
 *  
 * @author Yuliana Apaza
 * @version 2.0
 * @since 2015-10-05
 */
public interface IProducto
{
    public void actualizarEst(JTable tblRegistros, JCheckBox chActivar);
    public void cargar(JTable tblRegistros);
    public void insertar();
    public void menu();
    public void modificar(JTable tblRegistros);
    public void eliminar(JTable tblRegistros, JCheckBox chActivar);
    public void activarDesactivar(JTable tblRegistros, JCheckBox chActivar);
    public void generarReporte();
    public void buscarProducto( JTextField buscar, JTable tablaProducto);
    public void seleccionarFila(JTextField buscar, JTable tablaProducto);
}
