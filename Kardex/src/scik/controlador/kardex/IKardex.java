package scik.controlador.kardex;

import javax.swing.JTable;
import javax.swing.JTextField;

public interface IKardex
{
    public void cargar(JTable tblRegistrosKC);
    public void actualizar(JTable tblRegistrosKC, JTable tblRegistrosKD, JTextField txtCan, JTextField txtPreUni);
    public void menu();
}
