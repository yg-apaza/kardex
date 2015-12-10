package scik.controlador.kardex;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import scik.controlador.CKardexMenu;
import scik.modelo.Almacen;
import scik.modelo.KardexCab;
import scik.modelo.KardexDet;
import scik.modelo.Producto;
import scik.modelo.Reporte;
import scik.vista.kardex.UIKardex;

/**
 * Controlador principal de la gestion de kardex
 * 
 * Carga los productos - almacenes existentes con sus datos, ademas de controlar el
 * redireccionamiento hacia las ventanas de inserción o modificación. La eliminacion
 * de kardex se realiza aqui.
 *  
 * @author Yuliana Apaza
 * @version 2.0
 * @since 2015-10-05
 */

public class CKardex implements IKardex
{
    private UIKardex ventana;
    private ArrayList<KardexCab> kc;
    private ArrayList<ArrayList<KardexDet>> kds;
    private ArrayList<ArrayList<KardexDet>> kds_activos;
    private String codigoProducto;
    private String codigoAlmacen;
    
    public CKardex()
    {
        kds = new ArrayList<>();
        kds_activos = new ArrayList<ArrayList<KardexDet>>();
        kc = KardexCab.getLista();
        int kcSize = kc.size();
        
        for(int i = 0; i < kcSize; i++)
        {
            kds.add(KardexCab.getDetalles(kc.get(i).getProCod(), kc.get(i).getAlmCod()));
        }
        for(int i = 0; i < kcSize; i++)
        {
            kds_activos.add(KardexCab.getDetallesActivos(kc.get(i).getProCod(), kc.get(i).getAlmCod()));
        }
        ventana = new UIKardex(this);
    }
    
    @Override
    public void cargar(JTable tblRegistrosKC)
    {
        DefaultTableModel model = (DefaultTableModel) tblRegistrosKC.getModel();
        model.setRowCount(0);
        
        Producto p = null;
        Almacen a = null;
        
	int kcSize = kc.size();
        for(int i = 0; i < kcSize; i++)
        {
            p = Producto.buscar(kc.get(i).getProCod());
            a = Almacen.buscar(kc.get(i).getAlmCod());
            String estado = "";
            if(kc.get(i).getKarCabEstReg().equals("1"))
                estado = "A";
            else
                estado = "*";
            model.addRow(new Object[]{  kc.get(i).getProCod(),
                                        p.getProNom(),
                                        kc.get(i).getAlmCod(),
                                        a.getAlmNom(),
                                        estado});
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
        ArrayList<KardexDet> det = kds.get(i);
        
        txtCan.setText(kc.get(i).getKarCabCan());
        txtValUni.setText(kc.get(i).getKarCabValUni());
        txtValTot.setText(kc.get(i).getKarCabValTot());
        
	int detSize = det.size();
        String tipo = "";
        String estado = "";
        
        for(i = 0; i < detSize; i++)
        {
            if(det.get(i).getKarDetOpe().equals("1"))
                tipo = "Entrada";
            else
                tipo = "Salida";
            
            if(det.get(i).getKarDetEstReg().equals("1"))
                estado = "A";
            else
                estado = "*";

            model.addRow(new Object[]{  det.get(i).getKarDetCod(),
                                        new StringBuffer(   det.get(i).getKarDetDia().length() + 
                                                            det.get(i).getKarDetMes().length() +
                                                            det.get(i).getKarDetAnio() + 2
                                                            ).append(det.get(i).getKarDetDia())
                                                            .append('/')
                                                            .append(det.get(i).getKarDetMes())
                                                            .append('/')
                                                            .append(det.get(i).getKarDetAnio()),
                                        tipo,
                                        det.get(i).getKarDetCan(),
                                        det.get(i).getKarDetValUni(),
                                        det.get(i).getKarDetValTot(),
                                        det.get(i).getKarDetSalCan(),
                                        det.get(i).getKarDetSalValUni(),
                                        det.get(i).getKarDetSalValTot(),
                                        estado
                                        });
        }
    }
    
    @Override
    public void menu()
    {
        new CKardexMenu();
        ventana.dispose();
    }
    
    @Override
    public void insertarKC()
    {
        new CKardexCabIns();
        ventana.dispose();
    }
    
    @Override
    public void actualizarKD(JTable tblRegistrosKC, JTable tblRegistrosKD, JTextField txtUsr, JTextField txtDoc, JTextField txtNumDoc, JTextArea txtObs, JTextField txtEst)
    {
        int i = tblRegistrosKC.getSelectedRow();
        int j = tblRegistrosKD.getSelectedRow();
        String estado = "";
        if(i != -1 && j != -1)
        {
            txtUsr.setText(kds.get(i).get(j).getUsrCod());
            txtDoc.setText(kds.get(i).get(j).getDocCod());
            txtNumDoc.setText(kds.get(i).get(j).getKarDetDocNum());
            txtObs.setText(kds.get(i).get(j).getKarDetObs());
            if(kds.get(i).get(j).getKarDetEstReg().equals("1"))
                estado = "Activo";
            else
                estado = "Eliminado";
            txtEst.setText(estado);
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
            KardexCab cab = kc.get(i);
            if(cab.getKarCabEstReg().equals("1"))
            {
                String vTot = "";
                
                ArrayList<KardexDet> aux = kds_activos.get(i);
                if(aux.isEmpty())
                    vTot = "0";
                else
                    vTot = aux.get(aux.size() - 1).getKarDetSalValTot();
                new CKardexDetIns(codigoProducto, codigoAlmacen, cab.getKarCabCan(), vTot);
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
            KardexCab cab = kc.get(i);
            if(!cab.getKarCabEstReg().equals("3") && 
                JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar el registro?", "Eliminar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            {
                cab.eliminar();
                DefaultTableModel model = (DefaultTableModel) tblRegistrosKC.getModel();
                model.setValueAt("*", i, 4);
                if(tblRegistrosKD.getSelectedRow() != -1)
                    txtEst.setText("Eliminado");
                int kdsSize = kds.get(i).size();
                int kdsActivosSize = kds_activos.get(i).size();

                for(int j = 0; j < kdsSize; j++)
                {
                    kds.get(i).get(j).eliminar(kds.get(i).get(j).getKarDetCod(), codigoProducto, codigoAlmacen);
                }

                for(int j = 0; j < kdsActivosSize; j++)
                {
                    kds_activos.get(i).get(j).setKarDetEstReg("3");
                }
            }
            else
                JOptionPane.showMessageDialog(null, "El registro ya está eliminado", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        else
             JOptionPane.showMessageDialog(null, "Seleccione un registro a eliminar", "ERROR", JOptionPane.ERROR_MESSAGE);
    }
    
    @Override
    public void modificarKD(JTable tblRegistrosKC)
    {
        int i = tblRegistrosKC.getSelectedRow();
        if(i != -1)
        {
            try
            {
                KardexDet d = kds_activos.get(i).get(kds_activos.get(i).size() - 1);
                if(d.getKarDetEstReg().equals("1"))
                {
                        boolean nuevo = (kds_activos.get(i).size() == 1);
                        String cantidad = "";
                        String vTot = "";
                        
                        if(nuevo)
                        {
                            cantidad = "0";
                            vTot = "0";
                        }
                        else
                        {
                            cantidad = kds_activos.get(i).get(kds_activos.get(i).size() - 2).getKarDetSalCan();
                            vTot = kds_activos.get(i).get(kds_activos.get(i).size() - 2).getKarDetSalValTot();
                        }
                        new CKardexDetMod(d.getKarDetCod(), d.getProCod(), d.getAlmCod(), cantidad, vTot);
                        ventana.dispose();
                }
                else
                    JOptionPane.showMessageDialog(null, "Solo se permite modificar registros activos", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            catch(ArrayIndexOutOfBoundsException e)
            {
                JOptionPane.showMessageDialog(null, "Nada por modificar", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
        else
            JOptionPane.showMessageDialog(null, "Seleccione un Kardex Cabecera", "ERROR", JOptionPane.ERROR_MESSAGE);
    }
    
    @Override
    public void eliminarKD(JTable tblRegistrosKC)
    {
        int i = tblRegistrosKC.getSelectedRow();
        if(i != -1)
        {
            try
            {
                KardexDet d = kds_activos.get(i).get(kds_activos.get(i).size() - 1);
                if(!d.getKarDetEstReg().equals("3"))
                {
                    if(JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar el registro?", "Eliminar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                    {
                        d.eliminar(d.getKarDetCod(), d.getProCod(), d.getAlmCod());
                        new CKardex();
                        ventana.dispose();
                    }
                }
                else
                    JOptionPane.showMessageDialog(null, "El registro ya está eliminado", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            catch(ArrayIndexOutOfBoundsException e)
            {
                JOptionPane.showMessageDialog(null, "Nada por eliminar", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Seleccione un Kardex Cabecera", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void generarReporte(JTable tblRegistrosKC)
    {
        int i = tblRegistrosKC.getSelectedRow();
        if(i != -1)
        {
            KardexCab cab = kc.get(i);
            if(cab.getKarCabEstReg().equals("1"))
            {
                ArrayList<String> karcab = KardexCab.getVista(codigoProducto, codigoAlmacen);
                ArrayList<ArrayList<String>> kardet = KardexDet.getVista(codigoProducto, codigoAlmacen);
                Reporte.generarReporteKardex(karcab, kardet);
            }
            else
                JOptionPane.showMessageDialog(null, "El registro no está activo", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        else
             JOptionPane.showMessageDialog(null, "Seleccione un registro de Kardex Cabecera", "ERROR", JOptionPane.ERROR_MESSAGE);
    }
}
