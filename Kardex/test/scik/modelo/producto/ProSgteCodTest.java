package scik.modelo.producto;

import java.util.Arrays;
import java.util.Collection;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import scik.modelo.Producto;
import scik.modelo.Utils;

@RunWith(Parameterized.class)
public class ProSgteCodTest
{
    private String resultadoEsp;

    public ProSgteCodTest(String resultadoEsp)
    {
        this.resultadoEsp = resultadoEsp;
    }
    
    @Parameterized.Parameters
    public static Collection data()
    {
        return  Arrays.asList(new Object[][]
                {
                    {"000003"}, {"000005"}, {"000007"}
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
    }
    
    @Before
    public void setUp()
    {
        Utils.ejecutarScript("UT2003.sql");
        Utils.ejecutarScript("UT2003.sql");
    }
    
    @Test
    public void testSgteCodigo()
    {
        System.out.println("UT2-006 - Producto sgteCodigo");
        assertEquals(resultadoEsp, Producto.sgteCodigo());
    }
}
