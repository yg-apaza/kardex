package scik.controlador.almacen;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.JTextField;


/**
 * Interfaz de la gestión de almacen
 * 
 * Métodos disponibles para el controlador de gestión de almacén
 *  
 * @author Yuliana Apaza
 * @version 2.0
 * @since 2015-10-05
 */

public interface IAlmacen
{
    public void cargar(JTable tblRegistros, JTextField txtBuscar);
    public void actualizarEst(JTable registros, JCheckBox est);
    public void menu();
    public void insertar();
    public void modificar(JTable tblRegistros);
    public void activarDesactivar(JTable tblRegistros, JCheckBox chEst);
    public void eliminar(JTable tblRegistros, JCheckBox est);
    public void generarReporte();
    public void buscarAlmacen(String filtro, JTextField buscar, JTable tablaAlmacen);
}
