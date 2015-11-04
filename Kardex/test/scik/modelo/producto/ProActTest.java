package scik.modelo.producto;

import java.util.Arrays;
import java.util.Collection;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import scik.modelo.Producto;
import scik.modelo.Utils;

@RunWith(Parameterized.class)
public class ProActTest
{
    private String ProCod;
    private String ProNom;
    private String UniCod;
    private String ProEstReg;
    private String resultadoEsp;

    public ProActTest(String ProCod, String ProNom, String UniCod, String ProEstReg, String resultadoEsp)
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
                    {"000001", "Producto 1", "1", "2", ""}
                });
    }
    
    @BeforeClass
    public static void setUpClass()
    {
        Utils.ejecutarScript("UT2001.sql");
        Utils.ejecutarScript("UT2004.sql");
    }
    
    @AfterClass
    public static void tearDownClass()
    {
        Utils.restore("PRODUCTO");
        Utils.restore("UNIDAD");
    }
    
    @Test
    public void testActivar()
    {
        System.out.println("UT2-004 - Producto activar");
        Producto p = new Producto(ProCod, ProNom, UniCod, ProEstReg);
        assertEquals(resultadoEsp, p.activar());
        assertEquals("1", p.getProEstReg());
    }
}
