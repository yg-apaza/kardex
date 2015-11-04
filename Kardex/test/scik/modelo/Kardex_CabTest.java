package scik.modelo;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class Kardex_CabTest
{
    
    public Kardex_CabTest()
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
     * Test of insertar method, of class Kardex_Cab.
     */
    @Test
    public void testInsertar()
    {
        System.out.println("insertar");
        Kardex_Cab instance = new Kardex_Cab();
        String expResult = "";
        String result = instance.insertar();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminar method, of class Kardex_Cab.
     */
    @Test
    public void testEliminar()
    {
        System.out.println("eliminar");
        Kardex_Cab instance = new Kardex_Cab();
        String expResult = "";
        String result = instance.eliminar();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLista method, of class Kardex_Cab.
     */
    @Test
    public void testGetLista()
    {
        System.out.println("getLista");
        ArrayList<Kardex_Cab> expResult = null;
        ArrayList<Kardex_Cab> result = Kardex_Cab.getLista();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDetalles method, of class Kardex_Cab.
     */
    @Test
    public void testGetDetalles()
    {
        System.out.println("getDetalles");
        String p = "";
        String a = "";
        ArrayList<Kardex_Det> expResult = null;
        ArrayList<Kardex_Det> result = Kardex_Cab.getDetalles(p, a);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDetallesActivos method, of class Kardex_Cab.
     */
    @Test
    public void testGetDetallesActivos()
    {
        System.out.println("getDetallesActivos");
        String p = "";
        String a = "";
        ArrayList<Kardex_Det> expResult = null;
        ArrayList<Kardex_Det> result = Kardex_Cab.getDetallesActivos(p, a);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getVista method, of class Kardex_Cab.
     */
    @Test
    public void testGetVista()
    {
        System.out.println("getVista");
        String producto = "";
        String almacen = "";
        ArrayList<String> expResult = null;
        ArrayList<String> result = Kardex_Cab.getVista(producto, almacen);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
