package scik.controlador.unidad;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import scik.controlador.almacen.CAlmacen;
import scik.modelo.Unidad;
import scik.vista.UIUnidadIns;

public class CUnidadIns implements IUnidadIns
{
    private UIUnidadIns ventana;
    
    public CUnidadIns()
    {
        ventana = new UIUnidadIns(this);
    }
    
    @Override
    public void cargar(JTextField txtUniCod)
    {
        txtUniCod.setText(Unidad.sgteCodigo());
    }
    
    @Override
    public void aceptar(JTextField txtUniCod, JTextField txtUniDes)
    {
        Unidad u = new Unidad(txtUniCod.getText(), txtUniDes.getText(), "1");
        String err = u.insertar();
        if(err.equals(""))
        {
            JOptionPane.showMessageDialog(null, "Se ha agregado el registro nuevo", "INSERCION", JOptionPane.INFORMATION_MESSAGE);
            CUnidad inicio = new CUnidad();
            ventana.dispose();
        }
        else
            JOptionPane.showMessageDialog(null, err, "ERROR", JOptionPane.ERROR_MESSAGE);
    }
    
    @Override
    public void cancelar()
    {
        CUnidad unidad = new CUnidad();
        ventana.dispose();
    }
}
