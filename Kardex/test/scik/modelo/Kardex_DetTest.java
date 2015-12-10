package scik.modelo;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class Kardex_DetTest
{
    
    public Kardex_DetTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of insertar method, of class Kardex_Det.
     */
    @Test
    public void testInsertar()
    {
        System.out.println("insertar");
        KardexDet instance = new KardexDet();
        String expResult = "";
        String result = instance.insertar();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modificar method, of class Kardex_Det.
     */
    @Test
    public void testModificar()
    {
        System.out.println("modificar");
        KardexDet instance = new KardexDet();
        String expResult = "";
        String result = instance.modificar();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminar method, of class Kardex_Det.
     */
    @Test
    public void testEliminar()
    {
        System.out.println("eliminar");
        String codigo1 = "";
        String codigo2 = "";
        String codigo3 = "";
        KardexDet instance = new KardexDet();
        String expResult = "";
        String result = instance.eliminar(codigo1, codigo2, codigo3);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscar method, of class Kardex_Det.
     */
    @Test
    public void testBuscar()
    {
        System.out.println("buscar");
        String codigo1 = "";
        String codigo2 = "";
        String codigo3 = "";
        KardexDet expResult = null;
        KardexDet result = KardexDet.buscar(codigo1, codigo2, codigo3);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sgteCodigo method, of class Kardex_Det.
     */
    @Test
    public void testSgteCodigo()
    {
        System.out.println("sgteCodigo");
        String expResult = "";
        String result = KardexDet.sgteCodigo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getVista method, of class Kardex_Det.
     */
    @Test
    public void testGetVista()
    {
        System.out.println("getVista");
        String producto = "";
        String almacen = "";
        ArrayList<ArrayList<String>> expResult = null;
        ArrayList<ArrayList<String>> result = KardexDet.getVista(producto, almacen);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
