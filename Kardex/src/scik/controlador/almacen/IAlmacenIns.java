package scik.controlador.almacen;

import javax.swing.JTextField;

public interface IAlmacenIns
{
    public void cargar(JTextField txtAlmCod);
    public void aceptar(JTextField txtAlmCod, JTextField txtAlmNom, JTextField txtAlmUbi);
    public void cancelar();
}
