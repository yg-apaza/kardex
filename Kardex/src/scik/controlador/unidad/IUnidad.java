package scik.controlador.unidad;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * Interfaz de la gestion de unidad
 * 
 * Metodos disponibles para el controlador de gestion de unidad
 *  
 * @author Yuliana Apaza
 * @version 2.0
 * @since 2015-10-05
 */

public interface IUnidad
{
    public void cargar(JTable tblRegistros);
    public void actualizarEst(JTable tblRegistros, JCheckBox chActivar);
    public void menu();
    public void insertar();
    public void modificar(JTable tblRegistros);
    public void eliminar(JTable tblRegistros, JCheckBox chActivar);
    public void activarDesactivar(JTable tblRegistros, JCheckBox chActivar);
    public void buscarUnidad( JTextField buscar, JTable tablaUnidad);
    public void seleccionarFila(JTextField buscar, JTable tablaUnidad);
}
