package scik.controlador.documento;

import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import scik.controlador.CKardexMenu;
import scik.modelo.Documento;
import scik.vista.documento.UIDocumento;

import com.mxrck.autocompleter.TextAutoCompleter;

/**
 * Controlador de la gestion de documento
 * 
 * Carga los documentos existentes con sus datos, ademas de controlar el
 * redireccionamiento hacia las ventanas de insercion o modificacion. Provee
 * opciones de buscar registros. Las funciones activar, desactivar y eliminar
 * son realizadas aqui.
 *  
 * @author Yuliana Apaza
 * @version 2.0
 * @since 2015-10-05
 */

public class CDocumento implements IDocumento
{
    private UIDocumento ventana;
    private ArrayList<Documento> documentos;
    
    public CDocumento()
    {
        documentos = Documento.getLista();
        ventana = new UIDocumento(this);
    }
    
    @Override
    public void cargar(JTable tblRegistros)
    {
        DefaultTableModel model = (DefaultTableModel) tblRegistros.getModel();
        model.setRowCount(0);
        String estado = "";
                
        for(int i = 0; i < documentos.size(); i++)
        {
            if(documentos.get(i).getDocEstReg().equals("1"))
                estado = "A";
            else if(documentos.get(i).getDocEstReg().equals("2"))
                estado = "I";
            else
                estado = "*";
            model.addRow(new Object[]{  documentos.get(i).getDocCod(),
                                        documentos.get(i).getDocNom(),
                                        estado});
        }
    }
    
    @Override
    public void actualizarEst(JTable tblRegistros, JCheckBox chActivar)
    {
        int i = tblRegistros.getSelectedRow();
        if(i != -1)
        {
            chActivar.setEnabled(true);
            Documento d = documentos.get(i);
            if(!d.getDocEstReg().equals("3"))
            {
                if(d.getDocEstReg().equals("1"))
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
        new CKardexMenu();
        ventana.dispose();
    }
    
    @Override
    public void insertar()
    {
        new CDocumentoIns();
        ventana.dispose();
    }
    
    @Override
    public void modificar(JTable tblRegistros)
    {
        int i = tblRegistros.getSelectedRow();
        if(i != -1)
        {
            Documento d = documentos.get(i);
            CDocumentoMod modificar;
            if(d.getDocEstReg().equals("1"))
            {
                modificar = new CDocumentoMod(d.getDocCod());
                ventana.dispose();
            }
            else
                JOptionPane.showMessageDialog(null, "Solo se permite modificar registros activos", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        else
            JOptionPane.showMessageDialog(null, "Seleccione un registro a modificar", "ERROR", JOptionPane.ERROR_MESSAGE);
    }
    
    @Override
    public void eliminar(JTable tblRegistros, JCheckBox chActivar)
    {
        int i = tblRegistros.getSelectedRow();
        if(i != -1)
        {
            Documento d = documentos.get(i);
            if(!d.getDocEstReg().equals("3"))
            {
                if(JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar el registro?", "Eliminar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                {
                    DefaultTableModel model = (DefaultTableModel) tblRegistros.getModel();
                    d.eliminar();
                    model.setValueAt("*", i, 2);
                    chActivar.setEnabled(false);
                }
            }
            else
                JOptionPane.showMessageDialog(null, "El registro ya está eliminado", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        else
            JOptionPane.showMessageDialog(null, "Seleccione un registro a eliminar", "ERROR", JOptionPane.ERROR_MESSAGE);
    }
    
    @Override
    public void activarDesactivar(JTable tblRegistros, JCheckBox chActivar)
    {
        int i = tblRegistros.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) tblRegistros.getModel();
        Documento d = documentos.get(i);
        if(chActivar.isSelected())
        {
            d.activar();
            model.setValueAt("A", i, 2);
        }
        else
        {
            d.desactivar();
            model.setValueAt("I", i, 2);
        }
    }
    /*
    Realizar búsquedas y los muestra en el JTextField
    */
    public void buscarDocumento( JTextField buscar, JTable tablaDocumento)
    {
        TextAutoCompleter textAutoAcompleter = new TextAutoCompleter( buscar );
        textAutoAcompleter.setMode(0); // infijo
        textAutoAcompleter.setCaseSensitive(false); //No sensible a mayúsculas
        TableModel tableModel = tablaDocumento.getModel();
        String filtro = "Nombre";
        int i;
        for(i = 0; i < tableModel.getColumnCount(); i++)
        {
            if(filtro.compareTo(tableModel.getColumnName(i)) == 0)
                break;
        }
        
        for(int k = 0; k < tableModel.getRowCount(); k++)
        {
            textAutoAcompleter.addItem(tableModel.getValueAt(k, i));
        }
    }
    /*
    Selecciona la busqueda realizada en la tabla
    */
    public void seleccionarFila(JTextField buscar, JTable tablaDocumento)
    {
        TableModel tableModel = tablaDocumento.getModel();
        String dato = buscar.getText();
        String filtro = "Nombre";
        int col;
        
        for(col = 0; col < tableModel.getColumnCount(); col++)
        {
            if(filtro.compareTo(tableModel.getColumnName(col)) == 0)
                break;
        }
        
        int row;
        try
        {
            for(row = 0; row < tableModel.getRowCount(); row++)
            {
                if(dato.compareTo((String) tableModel.getValueAt(row, col)) == 0)
                    break;
            }

            if(row == 0)
                tablaDocumento.changeSelection(0,0,false,true);
            else
                tablaDocumento.getSelectionModel().setSelectionInterval(row - 1, row);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "No se encontraron los datos buscados", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}
