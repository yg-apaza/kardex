package scik.modelo;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class DocumentoTest
{
    
    public DocumentoTest()
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
     * Test of insertar method, of class Documento.
     */
    @Test
    public void testInsertar()
    {
        System.out.println("insertar");
        Documento instance = new Documento();
        String expResult = "";
        String result = instance.insertar();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modificar method, of class Documento.
     */
    @Test
    public void testModificar()
    {
        System.out.println("modificar");
        Documento instance = new Documento();
        String expResult = "";
        String result = instance.modificar();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminar method, of class Documento.
     */
    @Test
    public void testEliminar()
    {
        System.out.println("eliminar");
        Documento instance = new Documento();
        String expResult = "";
        String result = instance.eliminar();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLista method, of class Documento.
     */
    @Test
    public void testGetLista()
    {
        System.out.println("getLista");
        ArrayList<Documento> expResult = null;
        ArrayList<Documento> result = Documento.getLista();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of activar method, of class Documento.
     */
    @Test
    public void testActivar()
    {
        System.out.println("activar");
        Documento instance = new Documento();
        String expResult = "";
        String result = instance.activar();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of desactivar method, of class Documento.
     */
    @Test
    public void testDesactivar()
    {
        System.out.println("desactivar");
        Documento instance = new Documento();
        String expResult = "";
        String result = instance.desactivar();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscar method, of class Documento.
     */
    @Test
    public void testBuscar()
    {
        System.out.println("buscar");
        String codigo = "";
        Documento expResult = null;
        Documento result = Documento.buscar(codigo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getActivos method, of class Documento.
     */
    @Test
    public void testGetActivos()
    {
        System.out.println("getActivos");
        ArrayList<ArrayList<String>> expResult = null;
        ArrayList<ArrayList<String>> result = Documento.getActivos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sgteCodigo method, of class Documento.
     */
    @Test
    public void testSgteCodigo()
    {
        System.out.println("sgteCodigo");
        String expResult = "";
        String result = Documento.sgteCodigo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
