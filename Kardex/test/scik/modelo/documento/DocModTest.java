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
public class DocModTest
{
    private String DocCod;
    private String DocNom;
    private String DocEstReg;
    private String resultadoEsp;

    public DocModTest(String DocCod, String DocNom, String DocEstReg, String resultadoEsp)
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
                    {"000001", "Documento 1", "1", ""},
                    {"000002", "", "1", "Dato invalido para nombre de documento"},
                });
    }
    
    @BeforeClass
    public static void setUpClass()
    {
        Utils.ejecutarScript("UT3002.sql");
    }
    
    @AfterClass
    public static void tearDownClass()
    {
        Utils.restore("DOCUMENTO");
    }

    @Test
    public void testModificar()
    {
        System.out.println("UT3-002 - Documento modificar");
        Documento d = new Documento(DocCod, DocNom, DocEstReg);
        assertEquals(resultadoEsp, d.modificar());
    }
}
