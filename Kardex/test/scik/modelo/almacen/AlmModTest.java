package scik.modelo.almacen;

import java.util.Arrays;
import java.util.Collection;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import scik.modelo.Almacen;
import scik.modelo.Utils;

@RunWith(Parameterized.class)
public class AlmModTest
{
    private String AlmCod;
    private String AlmNom;
    private String AlmUbi;
    private String AlmEstReg;
    private String resultadoEsp;

    public AlmModTest(String AlmCod, String AlmNom, String AlmUbi, String AlmEstReg, String resultadoEsp)
    {
        this.AlmCod = AlmCod;
        this.AlmNom = AlmNom;
        this.AlmUbi = AlmUbi;
        this.AlmEstReg = AlmEstReg;
        this.resultadoEsp = resultadoEsp;
    }
    
    @Parameterized.Parameters
    public static Collection data()
    {
        return  Arrays.asList(new Object[][]
                {
                    {"000001", "Almacen 1", "Planta 1", "1", ""},
                    {"000002", "", "Planta 2", "1", "Dato invalido para nombre de almacen"},
                    {"000003", "Almacen 3", "", "1", "Dato invalido para ubicacion de almacen"},
                    
                });
    }
    
    @BeforeClass
    public static void setUpClass()
    {
        Utils.ejecutarScript("UT1002.sql");
    }
    
    @AfterClass
    public static void tearDownClass()
    {
        Utils.restore("ALMACEN");
    }

    /**
     * Test of modificar method, of class Almacen.
     */
    @Test
    public void testModificar()
    {
        System.out.println("UT1-002 - Almacen modificar");
        Almacen a = new Almacen(AlmCod, AlmNom, AlmUbi, AlmEstReg);
        assertEquals(resultadoEsp, a.modificar());
    }
}
