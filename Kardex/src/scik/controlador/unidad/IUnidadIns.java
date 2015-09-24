package scik.controlador.unidad;

import javax.swing.JTextField;

public interface IUnidadIns
{
    public void cargar(JTextField txtUniCod);
    public void aceptar(JTextField txtUniCod, JTextField txtUniDes);
    public void cancelar();
}
