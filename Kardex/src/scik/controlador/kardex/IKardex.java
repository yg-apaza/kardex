package scik.controlador.kardex;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Interfaz de la gestión de kardex
 * 
 * Métodos disponibles para el controlador de gestión de kardex
 *  
 * @author Yuliana Apaza
 * @version 2.0
 * @since 2015-10-05
 */

public interface IKardex
{
    public void cargar(JTable tblRegistrosKC);
    public void actualizar(JTable tblRegistrosKC, JTable tblRegistrosKD, JTextField txtCan, JTextField txtValUni, JTextField txtValTot);
    public void menu();
    public void insertarKC();
    public void actualizarKD(JTable tblRegistrosKC, JTable tblRegistrosKD, JTextField txtUsr, JTextField txtDoc, JTextField txtNumDoc, JTextArea txtObs, JTextField txtEst);
    public void insertarKD(JTable tblRegistrosKC);
    public void eliminarKC(JTable tblRegistrosKC, JTable tblRegistrosKD, JTextField txtEst);
    public void modificarKD(JTable tblRegistrosKC);
    public void eliminarKD(JTable tblRegistrosKC);

    public void generarReporte(JTable tblRegistrosKC);
}
