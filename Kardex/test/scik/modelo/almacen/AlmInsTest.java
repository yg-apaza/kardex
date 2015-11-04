package scik.modelo.almacen;

import java.util.Arrays;
import java.util.Collection;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import scik.modelo.Almacen;
import scik.modelo.Utils;

@RunWith(Parameterized.class)
public class AlmInsTest
{
    private String AlmCod;
    private String AlmNom;
    private String AlmUbi;
    private String AlmEstReg;
    private String resultadoEsp;

    public AlmInsTest(String AlmCod, String AlmNom, String AlmUbi, String AlmEstReg, String resultadoEsp)
    {
        this.AlmCod = AlmCod;
        this.AlmNom = AlmNom;
        this.AlmUbi = AlmUbi;
        this.AlmEstReg = AlmEstReg;
        this.resultadoEsp = resultadoEsp;
    }
    
    @Parameters
    public static Collection data()
    {
        return  Arrays.asList(new Object[][]
                {
                    {"000001", "Almacen 1", "Planta 1", "1", ""},
                    {"000002", "", "Planta 2", "1", "Dato invalido para nombre de almacen"},
                    {"000002", "Almacen 2", "", "1", "Dato invalido para ubicacion de almacen"},
                });
    }
        
    @AfterClass
    public static void tearDownClass()
    {
        Utils.restore("ALMACEN");
    }

    @Test
    public void testInsertar()
    {
        System.out.println("UT1-001 - Almacen insertar");
        Almacen a = new Almacen(AlmCod, AlmNom, AlmUbi, AlmEstReg);
        assertEquals(resultadoEsp, a.insertar());
    }
}