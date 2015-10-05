package scik;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import scik.modelo.Utils;
import scik.modelo.producto.ProActTest;
import scik.modelo.producto.ProDesTest;
import scik.modelo.producto.ProEliTest;
import scik.modelo.producto.ProInsTest;
import scik.modelo.producto.ProModTest;
import scik.modelo.producto.ProSgteCodTest;

@RunWith(Suite.class)
@Suite.SuiteClasses
({
    ProInsTest.class,
    ProModTest.class,
    ProEliTest.class,
    ProActTest.class,
    ProDesTest.class,
    ProSgteCodTest.class
})

public class ProductoTestSuite
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