package scik.controlador.unidad;

import javax.swing.JTextField;

public interface IUnidadMod
{
    public void cargar(JTextField txtUniCod, JTextField txtUniDes);
    public void aceptar(JTextField txtUniDes);
    public void cancelar();
}
