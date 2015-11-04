package scik;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import scik.modelo.Utils;
import scik.modelo.documento.DocActTest;
import scik.modelo.documento.DocDesTest;
import scik.modelo.documento.DocEliTest;
import scik.modelo.documento.DocInsTest;
import scik.modelo.documento.DocModTest;
import scik.modelo.documento.DocSgteCodTest;

@RunWith(Suite.class)
@Suite.SuiteClasses
({
    DocInsTest.class,
    DocModTest.class,
    DocEliTest.class,
    DocActTest.class,
    DocDesTest.class,
    DocSgteCodTest.class
})

public class DocumentoTestSuite
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