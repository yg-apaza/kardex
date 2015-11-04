package scik;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import scik.modelo.Utils;
import scik.modelo.almacen.AlmEliTest;
import scik.modelo.almacen.AlmInsTest;
import scik.modelo.almacen.AlmModTest;
import scik.modelo.almacen.AlmActTest;
import scik.modelo.almacen.AlmDesTest;
import scik.modelo.almacen.AlmSgteCodTest;

@RunWith(Suite.class)
@Suite.SuiteClasses
({
    AlmInsTest.class,
    AlmModTest.class,
    AlmEliTest.class,
    AlmActTest.class,
    AlmDesTest.class,
    AlmSgteCodTest.class
})

public class AlmacenTestSuite
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