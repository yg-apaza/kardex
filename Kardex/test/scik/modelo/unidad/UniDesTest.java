package scik.modelo.unidad;

import java.util.Arrays;
import java.util.Collection;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import scik.modelo.Unidad;
import scik.modelo.Utils;

@RunWith(Parameterized.class)
public class UniDesTest
{
    private String UniCod;
    private String UniDes;
    private String UniEstReg;
    private String resultadoEsp;

    public UniDesTest(String UniCod, String UniDes, String UniEstReg, String resultadoEsp)
    {
        this.UniCod = UniCod;
        this.UniDes = UniDes;
        this.UniEstReg = UniEstReg;
        this.resultadoEsp = resultadoEsp;
    }
    
    @Parameterized.Parameters
    public static Collection data()
    {
        return  Arrays.asList(new Object[][]
                {
                    {"001", "Unidad 1", "1", ""}
                });
    }
    
    @BeforeClass
    public static void setUpClass()
    {
        Utils.ejecutarScript("UT4003.sql");
    }
    
    @AfterClass
    public static void tearDownClass()
    {
        Utils.restore("UNIDAD");
    }
    
    @Test
    public void testDesactivar()
    {
        System.out.println("UT4-005 - Unidad desactivar");
        Unidad u = new Unidad(UniCod, UniDes, UniEstReg);
        assertEquals(resultadoEsp, u.desactivar());
        assertEquals("2", u.getUniEstReg());
    }
}
