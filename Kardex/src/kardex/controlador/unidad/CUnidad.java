package kardex.controlador.unidad;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import kardex.vista.UIUnidad;

public class CUnidad implements IUnidad
{
    private UIUnidad ventana;
    
    public CUnidad()
    {
        ventana = new UIUnidad(this);
    }
    
    @Override
    public void cargar(JTable tblRegistros)
    {
        
    }
    
    @Override
    public void actualizarEst(JTable tblRegistros, JCheckBox chActivar)
    {
        
    }
    
    @Override
    public void menu()
    {
        
    }
}
