package scik.controlador.kardex;

import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import scik.modelo.Documento;
import scik.modelo.KardexDet;
import scik.vista.kardex.UIKardexDetIns;
import static scik.KardexIni.user;

import com.toedter.calendar.JDateChooser;

/**
 * Controlador de la inserción de registro de detalle de kardex
 * 
 * Recibe y valida datos sobre un nuevo registro de movimiento de entrada o
 * salida de un producto
 *  
 * @author Yuliana Apaza
 * @version 2.0
 * @since 2015-10-05
 */

public class CKardexDetIns implements IKardexDetIns
{
    private UIKardexDetIns ventana;
    private ArrayList<ArrayList<String>> documentos;
    private String codigoProducto;
    private String codigoAlmacen;
    private String cantidad;
    private String valTot;
    
    public CKardexDetIns(String codigoProducto, String codigoAlmacen, String cantidad, String valTot)
    {
        this.codigoProducto = codigoProducto;
        this.codigoAlmacen = codigoAlmacen;
        this.cantidad = cantidad;
        this.valTot = valTot;
        
        documentos = Documento.getActivos();
        ventana = new UIKardexDetIns(this);
    }
    
    @Override
    public void calcular(JTextField txtCan, JTextField txtValUni, JTextField txtValTot, int s)
    {
        boolean canB    = !(txtCan.getText().length() == 0);
        boolean valUniB = !(txtValUni.getText().length() == 0);
        boolean valTotB = !(txtValTot.getText().length() == 0);
        
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
            valUniB = false;
        }
        
        try
        {
            vTot = Double.parseDouble(txtValTot.getText());
        }
        catch(NumberFormatException e)
        {
            valTotB = false;
        }
             
        if(canB && valUniB && s != 3)
        {
            vTot = can * vUni;
            txtValTot.setText(String.valueOf(vTot));
        }
        else if(canB && valTotB && s != 2)
        {
            vUni = vTot / can;
            if(!Double.isFinite(vUni))
                vUni = 0;
            txtValUni.setText(String.valueOf(vUni));
        }
        else if(valUniB && valTotB && s != 1)
        {
            can = vTot / vUni;
            if(!Double.isFinite(can))
                can = 0;
            txtCan.setText(String.valueOf(can));
        }
    }
    
    @Override
    public void cancelar()
    {
        new CKardex();
        ventana.dispose();
    }
    
    @Override
    public void verDocumento(JComboBox cbxDocNom, JTextField txtDocCod)
    {
        txtDocCod.setText(documentos.get(cbxDocNom.getSelectedIndex()).get(0));
    }
    
    @Override
    public void cargar(JComboBox cbxDocNom, JTextField txtKarDetCod, JTextField txtProCod, JTextField txtAlmCod)
    {
        for(int i = 0; i < documentos.size(); i++)
        {
            cbxDocNom.insertItemAt(documentos.get(i).get(1), i);  
        }
        txtKarDetCod.setText(KardexDet.sgteCodigo());
        txtProCod.setText(codigoProducto);
        txtAlmCod.setText(codigoAlmacen);
    }
    
    @Override
    public void aceptar(JTextField txtKarDetCod, JTextField txtProCod, JTextField txtAlmCod, JDateChooser fecha, JTextField txtDocCod, JTextField txtNumDoc, JComboBox cbxOpe, JTextField txtCan, JTextField txtValUni, JTextField txtValTot, JTextArea txtObs)
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
            String ope = "";
            if(cbxOpe.getSelectedIndex() == 0)
                ope = "1";
            else
                ope = "0";
            KardexDet kd = new KardexDet( txtKarDetCod.getText(),
                                            txtProCod.getText(),
                                            txtAlmCod.getText(),
                                            String.valueOf(c.get(Calendar.YEAR)),
                                            String.valueOf(c.get(Calendar.MONTH) + 1),
                                            String.valueOf(c.get(Calendar.DATE)),
                                            user.getUsrCod(),
                                            txtDocCod.getText(),
                                            txtNumDoc.getText(),
                                            ope,
                                            txtCan.getText(),
                                            txtValUni.getText(),
                                            txtValTot.getText(),
                                            salCan,
                                            salValUni,
                                            salValTot,
                                            txtObs.getText(),
                                            "1"
                                            );

            String err = kd.insertar();
            if(err.equals(""))
            {
                JOptionPane.showMessageDialog(null, "Se ha agregado el registro nuevo", "INSERCION", JOptionPane.INFORMATION_MESSAGE);
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
