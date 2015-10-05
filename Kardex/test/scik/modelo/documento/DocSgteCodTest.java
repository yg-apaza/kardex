package scik.modelo.documento;

import java.util.Arrays;
import java.util.Collection;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import scik.modelo.Documento;
import scik.modelo.Utils;

@RunWith(Parameterized.class)
public class DocSgteCodTest
{
    private String resultadoEsp;

    public DocSgteCodTest(String resultadoEsp)
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
        Utils.restore("DOCUMENTO");
    }
    
    @Before
    public void setUp()
    {
        Utils.ejecutarScript("UT3003.sql");
        Utils.ejecutarScript("UT3003.sql");
    }
    
    @Test
    public void testSgteCodigo()
    {
        System.out.println("UT3-006 - Documento sgteCodigo");
        assertEquals(resultadoEsp, Documento.sgteCodigo());
    }
}
