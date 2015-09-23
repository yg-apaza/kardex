package kardex.controlador.unidad;

import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import kardex.controlador.CKardex;
import kardex.modelo.Unidad;
import kardex.vista.UIUnidad;

public class CUnidad implements IUnidad
{
    private UIUnidad ventana;
    private ArrayList<Unidad> unidades;
    
    public CUnidad()
    {
        unidades = Unidad.getLista();
        ventana = new UIUnidad(this);
    }
    
    @Override
    public void cargar(JTable tblRegistros)
    {
        DefaultTableModel model = (DefaultTableModel) tblRegistros.getModel();
        model.setRowCount(0);
        
        for(int i = 0; i < unidades.size(); i++)
        {
            model.addRow(new Object[]{  unidades.get(i).getUniCod(),
                                        unidades.get(i).getUniDes(),
                                        unidades.get(i).getUniEstReg().equals("1")?"A":(unidades.get(i).getUniEstReg().equals("2")?"I":"*")});
        }
    }
    
    @Override
    public void actualizarEst(JTable tblRegistros, JCheckBox chActivar)
    {
        int i = tblRegistros.getSelectedRow();
        if(i != -1)
        {
            chActivar.setEnabled(true);
            Unidad u = unidades.get(i);
            if(!u.getUniEstReg().equals("3"))
            {
                if(u.getUniEstReg().equals("1"))
                    chActivar.setSelected(true);
                else
                    chActivar.setSelected(false);
            }
            else
                chActivar.setEnabled(false);
        }
        else
            chActivar.setEnabled(false);
    }
    
    @Override
    public void menu()
    {
        CKardex menu = new CKardex();
        ventana.dispose();
    }
    
    @Override
    public void insertar()
    {
        
    }
    
    @Override
    public void modificar(JTable tblRegistros)
    {
        
    }
    
    @Override
    public void eliminar(JTable tblRegistros, JCheckBox chActivar)
    {
        
    }
    
    @Override
    public void activar_desactivar(JTable tblRegistros, JCheckBox chActivar)
    {
        
    }
}
