package scik.modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

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
    
    @BeforeClass
    public static void setUpClass()
    {
        Utils.setup();
    }
    
    @AfterClass
    public static void tearDownClass()
    {
        Utils.restore("ALMACEN");
    }

    /**
     * Test of insertar method, of class Almacen.
     */
    @Test
    public void testInsertar()
    {
        System.out.println("UT1-001 - Almacen insertar");
        Almacen a = new Almacen(AlmCod, AlmNom, AlmUbi, AlmEstReg);
        assertEquals(resultadoEsp, a.insertar());
    }

    /**
     * Test of eliminar method, of class Almacen.
     */
    @Ignore
    @Test
    public void testEliminar()
    {
        System.out.println("eliminar");
        Almacen instance = new Almacen();
        String expResult = "";
        String result = instance.eliminar();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLista method, of class Almacen.
     */
    @Ignore
    @Test
    public void testGetLista()
    {
        System.out.println("getLista");
        ArrayList<Almacen> expResult = null;
        ArrayList<Almacen> result = Almacen.getLista();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of activar method, of class Almacen.
     */
    @Ignore
    @Test
    public void testActivar()
    {
        System.out.println("activar");
        Almacen instance = new Almacen();
        String expResult = "";
        String result = instance.activar();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of desactivar method, of class Almacen.
     */
    @Ignore
    @Test
    public void testDesactivar()
    {
        System.out.println("desactivar");
        Almacen instance = new Almacen();
        String expResult = "";
        String result = instance.desactivar();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscar method, of class Almacen.
     */
    @Ignore
    @Test
    public void testBuscar()
    {
        System.out.println("buscar");
        String codigo = "";
        Almacen expResult = null;
        Almacen result = Almacen.buscar(codigo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getActivos method, of class Almacen.
     */
    @Ignore
    @Test
    public void testGetActivos()
    {
        System.out.println("getActivos");
        ArrayList<ArrayList<String>> expResult = null;
        ArrayList<ArrayList<String>> result = Almacen.getActivos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sgteCodigo method, of class Almacen.
     */
    @Ignore
    @Test
    public void testSgteCodigo()
    {
        System.out.println("sgteCodigo");
        String expResult = "";
        String result = Almacen.sgteCodigo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getVista method, of class Almacen.
     */
    @Ignore
    @Test
    public void testGetVista()
    {
        System.out.println("getVista");
        ArrayList<ArrayList<String>> expResult = null;
        ArrayList<ArrayList<String>> result = Almacen.getVista();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}