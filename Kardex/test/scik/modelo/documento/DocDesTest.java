package scik.modelo.documento;

import java.util.Arrays;
import java.util.Collection;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import scik.modelo.Documento;
import scik.modelo.Utils;

@RunWith(Parameterized.class)
public class DocDesTest
{
    private String DocCod;
    private String DocNom;
    private String DocEstReg;
    private String resultadoEsp;

    public DocDesTest(String DocCod, String DocNom, String DocEstReg, String resultadoEsp)
    {
        this.DocCod = DocCod;
        this.DocNom = DocNom;
        this.DocEstReg = DocEstReg;
        this.resultadoEsp = resultadoEsp;
    }
    
    @Parameterized.Parameters
    public static Collection data()
    {
        return  Arrays.asList(new Object[][]
                {
                    {"000001", "Documento 1", "1", ""}
                });
    }
    
    @BeforeClass
    public static void setUpClass()
    {
        Utils.ejecutarScript("UT3003.sql");
    }
    
    @AfterClass
    public static void tearDownClass()
    {
        Utils.restore("DOCUMENTO");
    }
    
    @Test
    public void testDesactivar()
    {
        System.out.println("UT3-005 - Documento desactivar");
        Documento d = new Documento(DocCod, DocNom, DocEstReg);
        assertEquals(resultadoEsp, d.desactivar());
        assertEquals("2", d.getDocEstReg());
    }
}
