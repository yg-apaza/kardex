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
public class UniModTest
{
    private String UniCod;
    private String UniDes;
    private String UniEstReg;
    private String resultadoEsp;

    public UniModTest(String UniCod, String UniDes, String UniEstReg, String resultadoEsp)
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
                    {"000001", "Unidad 1", "1", ""},
                    {"000002", "", "1", "Dato invalido para descripcion de unidad"},
                });
    }
    
    @BeforeClass
    public static void setUpClass()
    {
        Utils.ejecutarScript("UT4002.sql");
    }
    
    @AfterClass
    public static void tearDownClass()
    {
        Utils.restore("UNIDAD");
    }

    @Test
    public void testModificar()
    {
        System.out.println("UT4-002 - Unidad modificar");
        Unidad u = new Unidad(UniCod, UniDes, UniEstReg);
        assertEquals(resultadoEsp, u.modificar());
    }
}
