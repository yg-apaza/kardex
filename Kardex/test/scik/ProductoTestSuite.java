package scik;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import scik.modelo.Utils;
import scik.modelo.producto.ProEliTest;
import scik.modelo.producto.ProInsTest;
import scik.modelo.producto.ProModTest;

@RunWith(Suite.class)
@Suite.SuiteClasses
({
    ProInsTest.class,
    ProModTest.class,
    ProEliTest.class,
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