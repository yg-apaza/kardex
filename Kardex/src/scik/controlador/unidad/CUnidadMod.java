package scik.controlador.unidad;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import scik.modelo.Unidad;
import scik.vista.unidad.UIUnidadMod;

public class CUnidadMod implements IUnidadMod
{
    private UIUnidadMod ventana;
    private Unidad u;
    
    public CUnidadMod(String codigo)
    {
        u = Unidad.buscar(codigo);
        ventana = new UIUnidadMod(this);
    }
    
    @Override
    public void cargar(JTextField txtUniCod, JTextField txtUniDes)
    {
        txtUniCod.setText(u.getUniCod());
        txtUniDes.setText(u.getUniDes());
    }
    
    @Override
    public void aceptar(JTextField txtUniDes)
    {
        u.setUniDes(txtUniDes.getText());
        String err = u.modificar();
        
        if(err.equals(""))
        {
            JOptionPane.showMessageDialog(null, "Se ha modificado el registro", "MODIFICACIÓN", JOptionPane.INFORMATION_MESSAGE);
            new CUnidad();
            ventana.dispose();
        }
        else
            JOptionPane.showMessageDialog(null, err, "ERROR", JOptionPane.ERROR_MESSAGE);
    }
    
    @Override
    public void cancelar()
    {
        new CUnidad();
        ventana.dispose();
    }
}
