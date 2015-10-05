package scik;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import scik.modelo.AlmacenTest;
import scik.modelo.DocumentoTest;
import scik.modelo.Kardex_CabTest;
import scik.modelo.Kardex_DetTest;
import scik.modelo.ProductoTest;
import scik.modelo.UnidadTest;
import scik.modelo.UsuarioTest;

@RunWith(Suite.class)
@Suite.SuiteClasses
({
    AlmacenTest.class,
    DocumentoTest.class,
    Kardex_CabTest.class,
    Kardex_DetTest.class,
    ProductoTest.class,
    UnidadTest.class,
    UsuarioTest.class
})

public class TestSuite
{
}