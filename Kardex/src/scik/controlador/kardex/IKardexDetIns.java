package scik.controlador.kardex;

import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public interface IKardexDetIns
{
    public void calcular(JTextField txtCan, JTextField txtValUni, JTextField txtValTot, int i);
    public void cancelar();
    public void verDocumento(JComboBox cbxDocNom, JTextField txtDocCod);
    public void cargar(JComboBox cbxDocNom, JTextField txtKarDetCod, JTextField txtProCod, JTextField txtAlmCod);
    public void aceptar(JTextField txtKarDetCod, JTextField txtProCod, JTextField txtAlmCod, JDateChooser fecha, JTextField txtDocCod, JTextField txtNumDoc, JComboBox cbxOpe, JTextField txtCan, JTextField txtValUni, JTextField txtValTot, JTextArea txtObs);
}
