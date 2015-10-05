package scik.modelo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import static scik.KardexIni.con;

public class AlmacenTest
{
    public AlmacenTest()
    {
        
    }
    
    @BeforeClass
    public static void setUpClass()
    {
        FileReader fr = null;
        try
        {
            String [] conexion_data = new String[3];
            fr = new FileReader("conexion.dat");
            BufferedReader br = new BufferedReader(fr);
            String linea = "";
            int number = 0;
            while((linea = br.readLine()) != null)
            {
                conexion_data[number] = linea.substring(linea.indexOf("=") + 1, linea.length());
                number++;
                if(number > 2)
                    break;
            }
            con = new Conexion(conexion_data[0], "BD_KARDEX", conexion_data[1], conexion_data[2]);
            con.conectar(false);
        }
        catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                fr.close();
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
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
     * Test of insertar method, of class Almacen.
     */
    @Ignore
    @Test
    public void testInsertar()
    {
        System.out.println("insertar");
        Almacen instance = new Almacen();
        String expResult = "";
        String result = instance.insertar();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modificar method, of class Almacen.
     */
    @Ignore
    @Test
    public void testModificar()
    {
        System.out.println("modificar");
        Almacen instance = new Almacen();
        String expResult = "";
        String result = instance.modificar();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
