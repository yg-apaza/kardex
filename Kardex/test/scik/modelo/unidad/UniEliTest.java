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
public class UniEliTest
{
    private String UniCod;
    private String UniDes;
    private String UniEstReg;
    private String resultadoEsp;

    public UniEliTest(String UniCod, String UniDes, String UniEstReg, String resultadoEsp)
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
                    {"000001", "Unidad 1", "1", ""}
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
    public void testEliminar()
    {
        System.out.println("UT4-003 - Unidad eliminar");
        Unidad u = new Unidad(UniCod, UniDes, UniEstReg);
        assertEquals(resultadoEsp, u.eliminar());
        assertEquals("3", u.getUniEstReg());
    }
}
