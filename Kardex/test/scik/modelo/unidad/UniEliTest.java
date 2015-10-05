package scik.modelo.unidad;

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
public class UniEliTest
{
    private String AlmCod;
    private String AlmNom;
    private String AlmUbi;
    private String AlmEstReg;
    private String resultadoEsp;

    public UniEliTest(String AlmCod, String AlmNom, String AlmUbi, String AlmEstReg, String resultadoEsp)
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
                    {"000001", "Almacen 1", "Planta 1", "1", ""}
                });
    }
    
    @BeforeClass
    public static void setUpClass()
    {
        Utils.ejecutarScript("UT1003.sql");
    }
    
    @AfterClass
    public static void tearDownClass()
    {
        Utils.restore("ALMACEN");
    }
    
    @Test
    public void testEliminar()
    {
        System.out.println("UT1-003 - Almacen eliminar");
        Almacen a = new Almacen(AlmCod, AlmNom, AlmUbi, AlmEstReg);
        assertEquals(resultadoEsp, a.eliminar());
        assertEquals("3", a.getAlmEstReg());
    }
}
