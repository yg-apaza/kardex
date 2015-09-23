package kardex.controlador.almacen;

import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import kardex.controlador.CKardex;
import kardex.modelo.Almacen;
import kardex.vista.UIAlmacen;

public class CAlmacen implements IAlmacen
{
    private UIAlmacen ventana;
    private ArrayList<Almacen> almacenes;
    
    public CAlmacen()
    {
        almacenes = (new Almacen()).getLista();
        ventana = new UIAlmacen(this);
    }
    
    public void cargar(JTable registros)
    {
        DefaultTableModel model = (DefaultTableModel) registros.getModel();
        model.setRowCount(0);
        
        for(int i = 0; i < almacenes.size(); i++)
        {
            model.addRow(new Object[]{  almacenes.get(i).getAlmCod(),
                                        almacenes.get(i).getAlmNom(),
                                        almacenes.get(i).getAlmUbi(),
                                        almacenes.get(i).getAlmEstReg().equals("1")?"A":(almacenes.get(i).getAlmEstReg().equals("2")?"I":"*")});
        }
    }
    
    public void actualizarEst(JTable registros, JCheckBox est)
    {
        int i = registros.getSelectedRow();
        if(i != -1)
        {
            est.setEnabled(true);
            Almacen a = almacenes.get(i);
            if(!a.getAlmEstReg().equals("3"))
            {
                if(a.getAlmEstReg().equals("1"))
                    est.setSelected(true);
                else
                    est.setSelected(false);
            }
            else
                est.setEnabled(false);
        }
        else
            est.setEnabled(false);
    }
    
    public void menu()
    {
        CKardex menu = new CKardex();
        ventana.dispose();
    }
    
    public void insertar()
    {
        CAlmacenIns insertar = new CAlmacenIns();
        ventana.dispose();
    }

    public void modificar(JTable tblRegistros)
    {
        
    }
}
