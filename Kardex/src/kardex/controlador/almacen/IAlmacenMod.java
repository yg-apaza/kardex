package kardex.controlador.almacen;

import javax.swing.JTextField;

public interface IAlmacenMod
{
    public void cargar(JTextField txtAlmCod, JTextField txtAlmNom, JTextField txtAlmUbi);
    public void aceptar(JTextField txtAlmNom, JTextField txtAlmUbi);
    public void cancelar();
}
