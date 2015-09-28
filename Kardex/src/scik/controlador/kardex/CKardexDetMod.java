package scik.controlador.kardex;

import com.toedter.calendar.JDateChooser;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import scik.modelo.Documento;
import scik.modelo.Kardex_Det;
import scik.vista.UIKardexDetMod;
import static scik.KardexIni.user;

public class CKardexDetMod implements IKardexDetMod
{
    private UIKardexDetMod ventana;
    private ArrayList<ArrayList<String>> documentos;
    private String codigo;
    private String codigoProducto;
    private String codigoAlmacen;
    private String cantidad;
    private String valTot;
    private Kardex_Det kd;
    
    public CKardexDetMod(String codigo, String codigoProducto, String codigoAlmacen, String cantidad, String valTot)
    {
        this.codigo = codigo;
        this.codigoProducto = codigoProducto;
        this.codigoAlmacen = codigoAlmacen;
        this.cantidad = cantidad;
        this.valTot = valTot;
        documentos = Documento.getActivos();
        kd = Kardex_Det.buscar(codigo, codigoProducto, codigoAlmacen);
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
        CKardex kardex = new CKardex();
        ventana.dispose();
    }
    
    @Override
    public void calcular(JTextField txtCan, JTextField txtValUni, JTextField txtValTot, int s)
    {
        boolean cantidad    = !(txtCan.getText().length() == 0);
        boolean valUni      = !(txtValUni.getText().length() == 0);
        boolean valTot      = !(txtValTot.getText().length() == 0);
        
        double can  = 0;
        double vUni = 0;
        double vTot = 0;
        
        try
        {
            can = Double.parseDouble(txtCan.getText());
        }
        catch(NumberFormatException e)
        {
            cantidad = false;
        }
        
        try
        {
            vUni = Double.parseDouble(txtValUni.getText());
        }
        catch(NumberFormatException e)
        {
            valUni = false;
        }
        
        try
        {
            vTot = Double.parseDouble(txtValTot.getText());
        }
        catch(NumberFormatException e)
        {
            valTot = false;
        }
             
        if(cantidad && valUni && s != 3)
        {
            vTot = can * vUni;
            txtValTot.setText(String.valueOf(vTot));
        }
        else if(cantidad && valTot && s != 2)
        {
            vUni = vTot / can;
            txtValUni.setText(String.valueOf(Double.isFinite(vUni)?vUni:0));
        }
        else if(valUni && valTot && s != 1)
        {
            can = vTot / vUni;
            txtCan.setText(String.valueOf(Double.isFinite(can)?can:0));
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
        cbxOpe.setSelectedIndex(kd.getKarDetOpe().equals("1")?0:1);
        txtCan.setText(kd.getKarDetCan());
        txtValUni.setText(kd.getKarDetValUni());
        txtValTot.setText(kd.getKarDetValTot());

        txtObs.setText(kd.getKarDetObs());

        for(int i = 0; i < documentos.size(); i++)
            cbxDocNom.insertItemAt(documentos.get(i).get(1), i);
        
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
                salValUni = String.valueOf(Double.isFinite(saldoTotal)?saldoTotal:0);
            }
            else
            {
                salCan = String.valueOf(Double.parseDouble(cantidad) - Double.parseDouble(txtCan.getText()));
                salValTot = String.valueOf(Double.parseDouble(valTot) - Double.parseDouble(txtValTot.getText()));
                Double saldoTotal = Double.parseDouble(salValTot)/Double.parseDouble(salCan);
                salValUni = String.valueOf(Double.isFinite(saldoTotal)?saldoTotal:0);
            }
        
            kd.setKarDetAnio(String.valueOf(c.get(Calendar.YEAR)));
            kd.setKarDetMes(String.valueOf(c.get(Calendar.MONTH) + 1));
            kd.setKarDetDia(String.valueOf(c.get(Calendar.DATE)));
            kd.setUsrCod(user.getUsrCod());
            kd.setDocCod(txtDocCod.getText());
            kd.setKarDetDocNum(txtNumDoc.getText());
            kd.setKarDetOpe((cbxOpe.getSelectedIndex() == 0)?"1":"0");
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
                CKardex inicio = new CKardex();
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
