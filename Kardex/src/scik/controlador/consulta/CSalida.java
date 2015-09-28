package scik.controlador.consulta;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import scik.vista.UISalida;

public class CSalida implements ISalida
{
    private UISalida ventana;
    
    public CSalida()
    {
        ventana = new UISalida(this);
    }

    @Override
    public void cargar(JComboBox cbxProNom)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void verProducto(JTextField txtProCod, JComboBox cbxProNom)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void menu()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void consultar(JTable tblConsultas, JTextField txtProCod, JComboBox cbxAnio, JComboBox cbxMes)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void generarReporte(JTextField txtProCod, JComboBox cbxAnio, JComboBox cbxMes)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
