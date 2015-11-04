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
public class ProEliTest
{
    private String ProCod;
    private String ProNom;
    private String UniCod;
    private String ProEstReg;
    private String resultadoEsp;

    public ProEliTest(String ProCod, String ProNom, String UniCod, String ProEstReg, String resultadoEsp)
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
                    {"000001", "Producto 1", "1", "1", ""}
                });
    }
    
    @BeforeClass
    public static void setUpClass()
    {
        Utils.ejecutarScript("UT2001.sql");
        Utils.ejecutarScript("UT2003.sql");
    }
    
    @AfterClass
    public static void tearDownClass()
    {
        Utils.restore("PRODUCTO");
        Utils.restore("UNIDAD");
    }
    
    @Test
    public void testEliminar()
    {
        System.out.println("UT2-003 - Producto eliminar");
        Producto p = new Producto(ProCod, ProNom, UniCod, ProEstReg);
        assertEquals(resultadoEsp, p.eliminar());
        assertEquals("3", p.getProEstReg());
    }
}
