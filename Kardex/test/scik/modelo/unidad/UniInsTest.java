package scik.modelo.unidad;

import java.util.Arrays;
import java.util.Collection;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import scik.modelo.Unidad;
import scik.modelo.Utils;

@RunWith(Parameterized.class)
public class UniInsTest
{
    private String UniCod;
    private String UniDes;
    private String UniEstReg;
    private String resultadoEsp;

    public UniInsTest(String UniCod, String UniDes, String UniEstReg, String resultadoEsp)
    {
        this.UniCod = UniCod;
        this.UniDes = UniDes;
        this.UniEstReg = UniEstReg;
        this.resultadoEsp = resultadoEsp;
    }
    
    @Parameters
    public static Collection data()
    {
        return  Arrays.asList(new Object[][]
                {
                    {"001", "Unidad 1", "1", ""},
                    {"002", "", "1", "Dato invalido para descripcion de unidad"},
                });
    }
        
    @AfterClass
    public static void tearDownClass()
    {
        Utils.restore("UNIDAD");
    }

    @Test
    public void testInsertar()
    {
        System.out.println("UT4-001 - Unidad insertar");
        Unidad u = new Unidad(UniCod, UniDes, UniEstReg);
        assertEquals(resultadoEsp, u.insertar());
    }
}