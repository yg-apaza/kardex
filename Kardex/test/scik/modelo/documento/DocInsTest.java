package scik.modelo.documento;

import java.util.Arrays;
import java.util.Collection;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import scik.modelo.Documento;
import scik.modelo.Utils;

@RunWith(Parameterized.class)
public class DocInsTest
{
    private String DocCod;
    private String DocNom;
    private String DocEstReg;
    private String resultadoEsp;

    public DocInsTest(String DocCod, String DocNom, String DocEstReg, String resultadoEsp)
    {
        this.DocCod = DocCod;
        this.DocNom = DocNom;
        this.DocEstReg = DocEstReg;
        this.resultadoEsp = resultadoEsp;
    }

    @Parameters
    public static Collection data()
    {
        return  Arrays.asList(new Object[][]
                {
                    {"000001", "Documento 1", "1", ""},
                    {"000002", "", "1", "Dato invalido para nombre de documento"},
                });
    }
        
    @AfterClass
    public static void tearDownClass()
    {
        Utils.restore("DOCUMENTO");
    }

    @Test
    public void testInsertar()
    {
        System.out.println("UT3-001 - Documento insertar");
        Documento d = new Documento(DocCod, DocNom, DocEstReg);
        assertEquals(resultadoEsp, d.insertar());
    }
}