package scik.modelo;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ProductoTest
{
    
    public ProductoTest()
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
     * Test of insertar method, of class Producto.
     */
    @Test
    public void testInsertar()
    {
        System.out.println("insertar");
        Producto instance = new Producto();
        String expResult = "";
        String result = instance.insertar();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modificar method, of class Producto.
     */
    @Test
    public void testModificar()
    {
        System.out.println("modificar");
        Producto instance = new Producto();
        String expResult = "";
        String result = instance.modificar();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminar method, of class Producto.
     */
    @Test
    public void testEliminar()
    {
        System.out.println("eliminar");
        Producto instance = new Producto();
        String expResult = "";
        String result = instance.eliminar();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLista method, of class Producto.
     */
    @Test
    public void testGetLista()
    {
        System.out.println("getLista");
        ArrayList<Producto> expResult = null;
        ArrayList<Producto> result = Producto.getLista();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of activar method, of class Producto.
     */
    @Test
    public void testActivar()
    {
        System.out.println("activar");
        Producto instance = new Producto();
        String expResult = "";
        String result = instance.activar();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of desactivar method, of class Producto.
     */
    @Test
    public void testDesactivar()
    {
        System.out.println("desactivar");
        Producto instance = new Producto();
        String expResult = "";
        String result = instance.desactivar();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscar method, of class Producto.
     */
    @Test
    public void testBuscar()
    {
        System.out.println("buscar");
        String codigo = "";
        Producto expResult = null;
        Producto result = Producto.buscar(codigo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getActivos method, of class Producto.
     */
    @Test
    public void testGetActivos()
    {
        System.out.println("getActivos");
        ArrayList<ArrayList<String>> expResult = null;
        ArrayList<ArrayList<String>> result = Producto.getActivos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sgteCodigo method, of class Producto.
     */
    @Test
    public void testSgteCodigo()
    {
        System.out.println("sgteCodigo");
        String expResult = "";
        String result = Producto.sgteCodigo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getVista method, of class Producto.
     */
    @Test
    public void testGetVista()
    {
        System.out.println("getVista");
        ArrayList<ArrayList<String>> expResult = null;
        ArrayList<ArrayList<String>> result = Producto.getVista();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
