package scik.modelo.unidad;

import scik.modelo.almacen.*;
import java.util.Arrays;
import java.util.Collection;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import scik.modelo.Almacen;
import scik.modelo.Utils;

@RunWith(Parameterized.class)
public class UniSgteCodTest
{
    private String resultadoEsp;

    public UniSgteCodTest(String resultadoEsp)
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
    
    @AfterClass
    public static void tearDownClass()
    {
        Utils.restore("ALMACEN");
    }
    
    @Before
    public void setUp()
    {
        Utils.ejecutarScript("UT1003.sql");
        Utils.ejecutarScript("UT1003.sql");
    }
    
    @Test
    public void testSgteCodigo()
    {
        System.out.println("UT1-006 - Almacen sgteCodigo");
        assertEquals(resultadoEsp, Almacen.sgteCodigo());
    }
}
