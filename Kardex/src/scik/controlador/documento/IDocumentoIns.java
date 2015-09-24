package scik.controlador.documento;

import javax.swing.JTextField;

public interface IDocumentoIns
{
    public void cargar(JTextField txtDocCod);
    public void cancelar();
    public void aceptar(JTextField txtDocCod, JTextField txtDocNom);
}
