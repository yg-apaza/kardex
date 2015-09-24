package scik.controlador.kardex;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import scik.controlador.CKardexMenu;
import scik.modelo.Almacen;
import scik.modelo.Kardex_Cab;
import scik.modelo.Kardex_Det;
import scik.modelo.Producto;
import scik.vista.UIKardex;

public class CKardex implements IKardex
{
    private UIKardex ventana;
    private ArrayList<Kardex_Cab> kc;
    private ArrayList<ArrayList<Kardex_Det>> kds;
    private ArrayList<ArrayList<Kardex_Det>> kds_activos;
    private String codigoProducto;
    private String codigoAlmacen;
    
    public CKardex()
    {
        kds = new ArrayList<>();
        kds_activos = new ArrayList<ArrayList<Kardex_Det>>();
        kc = Kardex_Cab.getLista();
        
        for(int i = 0; i < kc.size(); i++)
            kds.add(Kardex_Cab.getDetalles(kc.get(i).getProCod(), kc.get(i).getAlmCod()));
        for(int i = 0; i < kc.size(); i++)
            kds_activos.add(Kardex_Cab.getDetallesActivos(kc.get(i).getProCod(), kc.get(i).getAlmCod()));
        ventana = new UIKardex(this);
    }
    
    @Override
    public void cargar(JTable tblRegistrosKC)
    {
        DefaultTableModel model = (DefaultTableModel) tblRegistrosKC.getModel();
        model.setRowCount(0);
        
        Producto p = null;
        Almacen a = null;
        
        for(int i = 0; i < kc.size(); i++)
        {
            p = Producto.buscar(kc.get(i).getProCod());
            a = Almacen.buscar(kc.get(i).getAlmCod());
            
            model.addRow(new Object[]{  kc.get(i).getProCod(),
                                        p.getProNom(),
                                        kc.get(i).getAlmCod(),
                                        a.getAlmNom(),
                                        kc.get(i).getKarCabEstReg().equals("1")?"A":"*"});
        }
    }
    
    @Override
    public void actualizar(JTable tblRegistrosKC, JTable tblRegistrosKD, JTextField txtCan, JTextField txtValUni, JTextField txtValTot)
    {
        codigoProducto = kc.get(tblRegistrosKC.getSelectedRow()).getProCod();
        codigoAlmacen = kc.get(tblRegistrosKC.getSelectedRow()).getAlmCod();
        
        int i = tblRegistrosKC.getSelectedRow();
                       
        DefaultTableModel model = (DefaultTableModel) tblRegistrosKD.getModel();
        model.setRowCount(0);
        ArrayList<Kardex_Det> det = kds.get(i);
        
        txtCan.setText(kc.get(i).getKarCabCan());
        txtValUni.setText(kc.get(i).getKarCabValUni());
        txtValTot.setText(kc.get(i).getKarCabValTot());
        
        for(i = 0; i < det.size(); i++)
        {
            model.addRow(new Object[]{  det.get(i).getKarDetCod(),
                                        det.get(i).getKarDetDia() + "/" + 
                                        det.get(i).getKarDetMes() + "/" +
                                        det.get(i).getKarDetAnio(),
                                        det.get(i).getKarDetOpe().equals("1")?"Entrada":"Salida",
                                        det.get(i).getKarDetCan(),
                                        det.get(i).getKarDetValUni(),
                                        det.get(i).getKarDetValTot(),
                                        det.get(i).getKarDetSalCan(),
                                        det.get(i).getKarDetSalValUni(),
                                        det.get(i).getKarDetSalValTot(),
                                        det.get(i).getKarDetEstReg().equals("1")?"A":"*"
                                        });
        }
    }
    
    @Override
    public void menu()
    {
        CKardexMenu menu = new CKardexMenu();
        ventana.dispose();
    }
    
    @Override
    public void insertarKC()
    {
        CKardexCabIns insertar = new CKardexCabIns();
        ventana.dispose();
    }
    
    @Override
    public void actualizarKD(JTable tblRegistrosKC, JTable tblRegistrosKD, JTextField txtUsr, JTextField txtDoc, JTextField txtNumDoc, JTextArea txtObs, JTextField txtEst)
    {
        int i = tblRegistrosKC.getSelectedRow();
        int j = tblRegistrosKD.getSelectedRow();
        
        if(i != -1 && j != -1)
        {
            txtUsr.setText(kds.get(i).get(j).getUsrCod());
            txtDoc.setText(kds.get(i).get(j).getDocCod());
            txtNumDoc.setText(kds.get(i).get(j).getKarDetDocNum());
            txtObs.setText(kds.get(i).get(j).getKarDetObs());
            txtEst.setText(kds.get(i).get(j).getKarDetEstReg().equals("1")?"Activo":"Eliminado");
        }
        else
        {
            txtUsr.setText("");
            txtDoc.setText("");
            txtNumDoc.setText("");
            txtObs.setText("");
            txtEst.setText("");
        }
    }
    
    @Override
    public void insertarKD(JTable tblRegistrosKC)
    {
        int i = tblRegistrosKC.getSelectedRow();
        if(i != -1)
        {
            Kardex_Cab cab = kc.get(i);
            if(cab.getKarCabEstReg().equals("1"))
            {
                ArrayList<Kardex_Det> aux = kds_activos.get(i);
                CKardexDetIns k = new CKardexDetIns(codigoProducto, codigoAlmacen, cab.getKarCabCan(),
                                                    aux.size() == 0?"0":aux.get(aux.size() - 1).getKarDetSalValTot());
                ventana.dispose();
            }
            else
                JOptionPane.showMessageDialog(null, "Solo se permite insertar en registros activos", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        else
            JOptionPane.showMessageDialog(null, "Seleccione un Kardex Cabecera", "ERROR", JOptionPane.ERROR_MESSAGE);
    }
    
    @Override
    public void eliminarKC(JTable tblRegistrosKC, JTable tblRegistrosKD, JTextField txtEst)
    {
        int i = tblRegistrosKC.getSelectedRow();
        if(i != -1)
        {
            Kardex_Cab cab = kc.get(i);
            if(!cab.getKarCabEstReg().equals("3"))
            {
                if(JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar el registro?", "Eliminar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                {
                    cab.eliminar();
                    DefaultTableModel model = (DefaultTableModel) tblRegistrosKC.getModel();
                    model.setValueAt("*", i, 4);
                    if(tblRegistrosKD.getSelectedRow() != -1)
                        txtEst.setText("Eliminado");
                    for(int j = 0; j < kds.get(i).size(); j++)
                        kds.get(i).get(j).eliminar(kds.get(i).get(j).getKarDetCod(), codigoProducto, codigoAlmacen);
                }
            }
            else
                JOptionPane.showMessageDialog(null, "El registro ya está eliminado", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        else
             JOptionPane.showMessageDialog(null, "Seleccione un registro a eliminar", "ERROR", JOptionPane.ERROR_MESSAGE);
    }
}
