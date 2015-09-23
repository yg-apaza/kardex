package kardex.controlador.documento;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import kardex.modelo.Documento;
import kardex.vista.UIDocumentoMod;

public class CDocumentoMod implements IDocumentoMod
{
    private UIDocumentoMod ventana;
    private String codigo;
    
    public CDocumentoMod(String codigo)
    {
        this.codigo = codigo;
        ventana = new UIDocumentoMod(this);
    }
    
    @Override
    public void cargar(JTextField txtDocCod, JTextField txtDocNom)
    {
        Documento d = Documento.buscar(codigo);
        txtDocCod.setText(d.getDocCod());
        txtDocNom.setText(d.getDocNom());
    }
    
    @Override
    public void cancelar()
    {
        CDocumento documento = new CDocumento();
        ventana.dispose();
    }
    
    @Override
    public void aceptar(JTextField txtDocCod, JTextField txtDocNom)
    {
        Documento d = new Documento(txtDocCod.getText(), txtDocNom.getText(), "1");
        String err = d.modificar(this.codigo);
        if(err.equals(""))
        {
            JOptionPane.showMessageDialog(null, "Se ha modificado el registro", "MODIFICACIÓN", JOptionPane.INFORMATION_MESSAGE);
            CDocumento inicio = new CDocumento();
            ventana.dispose();
        }
        else
            JOptionPane.showMessageDialog(null, err, "ERROR", JOptionPane.ERROR_MESSAGE);
    }
}
