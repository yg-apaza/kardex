package kardex.controlador.almacen;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import kardex.modelo.Almacen;
import kardex.vista.UIAlmacenMod;

public class CAlmacenMod implements IAlmacenMod
{
    private UIAlmacenMod ventana;
    private String codigo;
    
    public CAlmacenMod(String codigo)
    {
        this.codigo = codigo;
        ventana = new UIAlmacenMod(this);
    }
    
    public void cargar(JTextField txtAlmCod, JTextField txtAlmNom, JTextField txtAlmUbi)
    {
        Almacen a = Almacen.buscar(codigo);
        txtAlmCod.setText(a.getAlmCod());
        txtAlmNom.setText(a.getAlmNom());
        txtAlmUbi.setText(a.getAlmUbi());
    }
    
    public void aceptar(JTextField txtAlmCod, JTextField txtAlmNom, JTextField txtAlmUbi)
    {
        Almacen a = new Almacen(txtAlmCod.getText(), txtAlmNom.getText(), txtAlmUbi.getText(), "1");
        String err = a.actualizar(this.codigo);
        if(err.equals(""))
        {
            JOptionPane.showMessageDialog(null, "Se ha modificado el registro", "MODIFICACIÓN", JOptionPane.INFORMATION_MESSAGE);
            CAlmacen inicio = new CAlmacen();
            ventana.dispose();
        }
        else
        {
            JOptionPane.showMessageDialog(null, err, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void cancelar()
    {
        CAlmacen almacen = new CAlmacen();
        ventana.dispose();
    }
}
