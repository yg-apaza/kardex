package scik.modelo.producto;

import java.util.Arrays;
import java.util.Collection;
import org.junit.AfterClass;
import org.junit.Test;
import scik.modelo.Producto;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.runners.Parameterized;
import scik.modelo.Utils;

public class ProInsTest
{
    private String ProCod;
    private String ProNom;
    private String UniCod;
    private String ProEstReg;
    private String resultadoEsp;

    public ProInsTest(String ProCod, String ProNom, String UniCod, String ProEstReg, String resultadoEsp)
    {
        this.ProCod = ProCod;
        this.ProNom = ProNom;
        this.UniCod = UniCod;
        this.ProEstReg = ProEstReg;
        this.resultadoEsp = resultadoEsp;
    }

    @Parameterized.Parameters
    public static Collection data()
    {
        return  Arrays.asList(new Object[][]
                {
                    {"000001", "Almacen 1", "Planta 1", "1", ""},
                    {"000002", "", "Planta 2", "1", "Dato invalido para nombre de almacen"},
                    {"000002", "Almacen 2", "", "1", "Dato invalido para ubicacion de almacen"},
                });
    }
    
    @BeforeClass
    public static void setUpClass()
    {
        Utils.ejecutarScript("UT2001.sql");
    }
        
    @AfterClass
    public static void tearDownClass()
    {
        Utils.restore("PRODUCTO");
        Utils.restore("UNIDAD");
    }

    @Test
    public void testInsertar()
    {
        System.out.println("UT2-001 - Producto insertar");
        Producto p = new Producto(ProCod, ProNom, UniCod, ProEstReg);
        assertEquals(resultadoEsp, p.insertar());
    }
}
