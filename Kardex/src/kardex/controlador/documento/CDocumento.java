package kardex.controlador.documento;

import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import kardex.controlador.CKardex;
import kardex.controlador.almacen.CAlmacenMod;
import kardex.modelo.Almacen;
import kardex.modelo.Documento;
import kardex.vista.UIDocumento;

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
        
        for(int i = 0; i < documentos.size(); i++)
        {
            model.addRow(new Object[]{  documentos.get(i).getDocCod(),
                                        documentos.get(i).getDocNom(),
                                        documentos.get(i).getDocEstReg().equals("1")?"A":(documentos.get(i).getDocEstReg().equals("2")?"I":"*")});
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
        CKardex menu = new CKardex();
        ventana.dispose();
    }
    
    @Override
    public void insertar()
    {
        CDocumentoIns insertar = new CDocumentoIns();
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
    public void activar_desactivar(JTable tblRegistros, JCheckBox chActivar)
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
}
