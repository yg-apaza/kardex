package scik.controlador.consulta;

import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import scik.controlador.CKardexMenu;
import scik.modelo.Consultas;
import scik.modelo.Producto;
import scik.vista.UISalida;

public class CSalida implements ISalida
{
    private UISalida ventana;
    private ArrayList<ArrayList<String>> activosPro;
    private ArrayList<ArrayList<String>> resultado;
    private boolean consultaRealizada;
    private Consultas c;
    
    public CSalida()
    {
        consultaRealizada = false;
        c = new Consultas();
        resultado = new ArrayList<ArrayList<String>>();
        activosPro = Producto.getActivos();
        ventana = new UISalida(this);
    }

    @Override
    public void cargar(JComboBox cbxProNom)
    {
        for(int i = 0; i < activosPro.size(); i++)
            cbxProNom.insertItemAt(activosPro.get(i).get(1), i);
    }

    @Override
    public void verProducto(JTextField txtProCod, JComboBox cbxProNom)
    {
        txtProCod.setText(activosPro.get(cbxProNom.getSelectedIndex()).get(0));
    }

    @Override
    public void menu()
    {
        CKardexMenu inicio = new CKardexMenu();
        ventana.dispose();
    }

    @Override
    public void consultar(JTable tblConsultas, JTextField txtProCod, JComboBox cbxAnio, JComboBox cbxMes)
    {
        resultado = c.salidas(txtProCod.getText(), (String)cbxAnio.getSelectedItem(), String.valueOf(cbxMes.getSelectedIndex() + 1));
        DefaultTableModel model = (DefaultTableModel) tblConsultas.getModel();
        model.setRowCount(0);

        for(int i = 0; i < resultado.size(); i++)
        {
            model.addRow(new Object[]   {
                                            resultado.get(i).get(0),
                                            resultado.get(i).get(1),
                                            resultado.get(i).get(2),
                                            resultado.get(i).get(3)
                                        });
        }
        consultaRealizada = true;
    }

    @Override
    public void generarReporte(JTextField txtProCod, JComboBox cbxAnio, JComboBox cbxMes)
    {
        if(consultaRealizada)
        {
            Producto p = Producto.buscar(txtProCod.getText());
            //reporte.generarReporte2(p, (String)cbxAnio.getSelectedItem(),(String) cbxMes.getSelectedItem() , resultado);
        }
        else
            JOptionPane.showMessageDialog(null, "Realize primero una consulta", "ERROR", JOptionPane.ERROR_MESSAGE);
    }
    
}
