package kardex.controlador.documento;

import javax.swing.JTextField;

public interface IDocumentoMod
{
    public void cargar(JTextField txtDocCod, JTextField txtDocNom);
    public void cancelar();
    public void aceptar(JTextField txtDocNom);
}
