package scik.controlador.kardex;

import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import scik.modelo.Documento;
import scik.modelo.KardexDet;
import scik.vista.kardex.UIKardexDetMod;
import static scik.KardexIni.user;

import com.toedter.calendar.JDateChooser;

/**
 * Controlador de la modificacion de registro de detalle de kardex
 * 
 * Carga, recibe y valida datos sobre un registro existente de movimiento de
 * entrada o salida de un producto
 *  
 * @author Yuliana Apaza
 * @version 2.0
 * @since 2015-10-05
 */

public class CKardexDetMod implements IKardexDetMod
{
    private UIKardexDetMod ventana;
    private ArrayList<ArrayList<String>> documentos;
    private String codigoAlmacen;
    private String cantidad;
    private String valTot;
    private KardexDet kd;
    
    public CKardexDetMod(String codigo, String codigoProducto, String codigoAlmacen, String cantidad, String valTot)
    {
        this.cantidad = cantidad;
        this.valTot = valTot;
        documentos = Documento.getActivos();
        kd = KardexDet.buscar(codigo, codigoProducto, codigoAlmacen);
        ventana = new UIKardexDetMod(this);
    }
    
    @Override
    public void verDocumento(JComboBox cbxDocNom, JTextField txtDocCod)
    {
        txtDocCod.setText(documentos.get(cbxDocNom.getSelectedIndex()).get(0));
    }
    
    @Override
    public void cancelar()
    {
        new CKardex();
        ventana.dispose();
    }
    
    @Override
    public void calcular(JTextField txtCan, JTextField txtValUni, JTextField txtValTot, int s)
    {
        boolean canB    = !(txtCan.getText().length() == 0);
        boolean vUniB   = !(txtValUni.getText().length() == 0);
        boolean vTotB   = !(txtValTot.getText().length() == 0);
        
        double can  = 0;
        double vUni = 0;
        double vTot = 0;
        
        try
        {
            can = Double.parseDouble(txtCan.getText());
        }
        catch(NumberFormatException e)
        {
            canB = false;
        }
        
        try
        {
            vUni = Double.parseDouble(txtValUni.getText());
        }
        catch(NumberFormatException e)
        {
            vUniB = false;
        }
        
        try
        {
            vTot = Double.parseDouble(txtValTot.getText());
        }
        catch(NumberFormatException e)
        {
            vTotB = false;
        }
             
        if(canB && vUniB && s != 3)
        {
            vTot = can * vUni;
            txtValTot.setText(String.valueOf(vTot));
        }
        else if(canB && vTotB && s != 2)
        {
            vUni = vTot / can;
            if(!Double.isFinite(vUni))
                vUni = 0;
            txtValUni.setText(String.valueOf(vUni));
        }
        else if(vUniB && vTotB && s != 1)
        {
            can = vTot / vUni;
            if(!Double.isFinite(can))
                can = 0;
            txtCan.setText(String.valueOf(can));
        }
    }
    
    @Override
    public void cargar(JTextField txtKarDetCod, JTextField txtProCod, JTextField txtAlmCod, JDateChooser fecha, JTextField txtDocCod, JComboBox cbxDocNom, JTextField txtNumDoc, JComboBox cbxOpe, JTextField txtCan, JTextField txtValUni, JTextField txtValTot, JTextArea txtObs)
    {
        txtKarDetCod.setText(kd.getKarDetCod());
        txtProCod.setText(kd.getProCod());
        txtAlmCod.setText(kd.getAlmCod());
        
        Calendar c = Calendar.getInstance();
        c.set(Integer.parseInt(kd.getKarDetAnio()), Integer.parseInt(kd.getKarDetMes()) - 1, Integer.parseInt(kd.getKarDetDia()));
        
        fecha.setCalendar(c);
        txtDocCod.setText(kd.getDocCod());
        txtNumDoc.setText(kd.getKarDetDocNum());
        int ope = 1;
        if(kd.getKarDetOpe().equals("1"))
            ope = 0;
        cbxOpe.setSelectedIndex(ope);
        txtCan.setText(kd.getKarDetCan());
        txtValUni.setText(kd.getKarDetValUni());
        txtValTot.setText(kd.getKarDetValTot());

        txtObs.setText(kd.getKarDetObs());

        for(int i = 0; i < documentos.size(); i++) 
        {
            cbxDocNom.insertItemAt(documentos.get(i).get(1), i);
        }
        
        Documento d = Documento.buscar(kd.getDocCod());
        cbxDocNom.setSelectedItem(d.getDocNom());
    }
    
    @Override
    public void aceptar(JDateChooser fecha, JTextField txtDocCod, JTextField txtNumDoc, JComboBox cbxOpe, JTextField txtCan, JTextField txtValUni, JTextField txtValTot, JTextArea txtObs)
    {
        Calendar c = fecha.getCalendar();
        try
        {
            String salCan = "0";
            String salValTot = "0";
            String salValUni = "0";
            
            if(cbxOpe.getSelectedIndex() == 0)
            {
                salCan = String.valueOf(Double.parseDouble(cantidad) + Double.parseDouble(txtCan.getText()));
                salValTot = String.valueOf(Double.parseDouble(valTot) + Double.parseDouble(txtValTot.getText()));
                Double saldoTotal = Double.parseDouble(salValTot)/Double.parseDouble(salCan);
                if(!Double.isFinite(saldoTotal))
                    saldoTotal = 0.0;
                salValUni = String.valueOf(saldoTotal);
            }
            else
            {
                salCan = String.valueOf(Double.parseDouble(cantidad) - Double.parseDouble(txtCan.getText()));
                salValTot = String.valueOf(Double.parseDouble(valTot) - Double.parseDouble(txtValTot.getText()));
                Double saldoTotal = Double.parseDouble(salValTot)/Double.parseDouble(salCan);
                if(!Double.isFinite(saldoTotal))
                    saldoTotal = 0.0;
                salValUni = String.valueOf(saldoTotal);
            }
        
            kd.setKarDetAnio(String.valueOf(c.get(Calendar.YEAR)));
            kd.setKarDetMes(String.valueOf(c.get(Calendar.MONTH) + 1));
            kd.setKarDetDia(String.valueOf(c.get(Calendar.DATE)));
            kd.setUsrCod(user.getUsrCod());
            kd.setDocCod(txtDocCod.getText());
            kd.setKarDetDocNum(txtNumDoc.getText());
            String ope = "1";
            if(cbxOpe.getSelectedIndex() == 0)
                ope = "1";
            else
                ope = "0";
            kd.setKarDetOpe(ope);
            kd.setKarDetCan(txtCan.getText());
            kd.setKarDetValUni(txtValUni.getText());
            kd.setKarDetValTot(txtValTot.getText());
            kd.setKarDetSalCan(salCan);
            kd.setKarDetSalValUni(salValUni);
            kd.setKarDetSalValTot(salValTot);
            kd.setKarDetObs(txtObs.getText());
                                            
            String err = kd.modificar();
            if(err.equals(""))
            {
                JOptionPane.showMessageDialog(null, "Se ha modificado el registro", "MODIFICACIÓN", JOptionPane.INFORMATION_MESSAGE);
                new CKardex();
                ventana.dispose();
            }
            else
                JOptionPane.showMessageDialog(null, err, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        catch(NumberFormatException e)
        {
            JOptionPane.showMessageDialog(null, "Cantidad o Valor Total inválido", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}
