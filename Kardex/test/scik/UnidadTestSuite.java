package scik;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import scik.modelo.Utils;
import scik.modelo.unidad.UniActTest;
import scik.modelo.unidad.UniDesTest;
import scik.modelo.unidad.UniEliTest;
import scik.modelo.unidad.UniInsTest;
import scik.modelo.unidad.UniModTest;
import scik.modelo.unidad.UniSgteCodTest;

@RunWith(Suite.class)
@Suite.SuiteClasses
({
    UniInsTest.class,
    UniModTest.class,
    UniEliTest.class,
    UniActTest.class,
    UniDesTest.class,
    UniSgteCodTest.class
})

public class UnidadTestSuite
{
    @BeforeClass
    public static void setUpClass()
    {
        Utils.conectar();
    }
    
    @AfterClass
    public static void tearDownClass()
    {
        Utils.desconectar();
    }
}