package scik.modelo.unidad;

import java.util.Arrays;
import java.util.Collection;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import scik.modelo.Unidad;
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
                    {"003"}, {"005"}, {"007"}
                });
    }
    
    @AfterClass
    public static void tearDownClass()
    {
        Utils.restore("UNIDAD");
    }
    
    @Before
    public void setUp()
    {
        Utils.ejecutarScript("UT4003.sql");
        Utils.ejecutarScript("UT4003.sql");
    }
    
    @Test
    public void testSgteCodigo()
    {
        System.out.println("UT4-006 - Unidad sgteCodigo");
        assertEquals(resultadoEsp, Unidad.sgteCodigo());
    }
}
