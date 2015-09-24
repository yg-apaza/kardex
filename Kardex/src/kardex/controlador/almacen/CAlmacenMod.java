package kardex.controlador.almacen;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import kardex.modelo.Almacen;
import kardex.vista.UIAlmacenMod;

public class CAlmacenMod implements IAlmacenMod
{
    private UIAlmacenMod ventana;
    private String codigo;
    private Almacen a;
    
    public CAlmacenMod(String codigo)
    {
        this.codigo = codigo;
        a = Almacen.buscar(codigo);
        ventana = new UIAlmacenMod(this);
    }
    
    @Override
    public void cargar(JTextField txtAlmCod, JTextField txtAlmNom, JTextField txtAlmUbi)
    {
        txtAlmCod.setText(a.getAlmCod());
        txtAlmNom.setText(a.getAlmNom());
        txtAlmUbi.setText(a.getAlmUbi());
    }
    
    @Override
    public void aceptar(JTextField txtAlmNom, JTextField txtAlmUbi)
    {
        a.setAlmNom(txtAlmNom.getText());
        a.setAlmUbi(txtAlmUbi.getText());
        String err = a.modificar();
        
        if(err.equals(""))
        {
            JOptionPane.showMessageDialog(null, "Se ha modificado el registro", "MODIFICACIÓN", JOptionPane.INFORMATION_MESSAGE);
            CAlmacen inicio = new CAlmacen();
            ventana.dispose();
        }
        else
            JOptionPane.showMessageDialog(null, err, "ERROR", JOptionPane.ERROR_MESSAGE);
    }
    
    @Override
    public void cancelar()
    {
        CAlmacen almacen = new CAlmacen();
        ventana.dispose();
    }
}
