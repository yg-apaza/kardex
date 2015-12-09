package scik.controlador.kardex;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

public interface IKardexDetMod
{
    public void verDocumento(JComboBox cbxDocNom, JTextField txtDocCod);
    public void cancelar();
    public void calcular(JTextField txtCan, JTextField txtValUni, JTextField txtValTot, int i);
    public void cargar(JTextField txtKarDetCod, JTextField txtProCod, JTextField txtAlmCod, JDateChooser fecha, JTextField txtDocCod, JComboBox cbxDocNom, JTextField txtNumDoc, JComboBox cbxOpe, JTextField txtCan, JTextField txtValUni, JTextField txtValTot, JTextArea txtObs);
    public void aceptar(JDateChooser fecha, JTextField txtDocCod, JTextField txtNumDoc, JComboBox cbxOpe, JTextField txtCan, JTextField txtValUni, JTextField txtValTot, JTextArea txtObs);
}
