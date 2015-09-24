package scik.controlador.kardex;

import javax.swing.JTable;
import javax.swing.JTextField;
import scik.vista.UIKardex;

public class CKardex implements IKardex
{
    private UIKardex ventana;
    
    public CKardex()
    {
        ventana = new UIKardex(this);
    }
    
    @Override
    public void cargar(JTable tblRegistrosKC)
    {
        
    }
    
    @Override
    public void actualizar(JTable tblRegistrosKC, JTable tblRegistrosKD, JTextField txtCan, JTextField txtPreUni)
    {
        
    }
    
    @Override
    public void menu()
    {
        
    }
}
