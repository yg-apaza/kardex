package scik.controlador.unidad;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import scik.modelo.Unidad;
import scik.vista.unidad.UIUnidadIns;

/**
 * Controlador de la inserción de unidad
 * 
 * Recibe y valida datos sobre un nuevo registro de unidad
 *  
 * @author Yuliana Apaza
 * @version 2.0
 * @since 2015-10-05
 */

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
