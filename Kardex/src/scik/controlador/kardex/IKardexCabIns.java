package scik.controlador.kardex;

import javax.swing.JComboBox;
import javax.swing.JTextField;

public interface IKardexCabIns
{
    public void cancelar();
    public void verProducto(JTextField txtProCod, JComboBox cbxProNom);
    public void verAlmacen(JTextField txtAlmCod, JComboBox cbxAlmNom);
    public void aceptar(JTextField txtProCod, JTextField txtAlmCod);
    public void cargar(JComboBox cbxProNom, JComboBox cbxAlmNom);
}
