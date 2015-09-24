package scik.controlador.documento;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import scik.modelo.Documento;
import scik.vista.UIDocumentoMod;

public class CDocumentoMod implements IDocumentoMod
{
    private UIDocumentoMod ventana;
    private String codigo;
    private Documento d;
    
    public CDocumentoMod(String codigo)
    {
        this.codigo = codigo;
        d = Documento.buscar(codigo);
        ventana = new UIDocumentoMod(this);
    }
    
    @Override
    public void cargar(JTextField txtDocCod, JTextField txtDocNom)
    {
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
    public void aceptar(JTextField txtDocNom)
    {
        d.setDocNom(txtDocNom.getText());
        String err = d.modificar();
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
