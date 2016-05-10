/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.resources;

import co.edu.uniandes.rest.adapter.DateAdapter;
import co.edu.uniandes.rest.converters.ViajeroConverter;
import co.edu.uniandes.rest.dtos.ViajeroDTO;
import co.edu.uniandes.rest.dtos.ViajesDTO;
import co.edu.uniandes.rest.mappers.EJBExceptionMapper;
import co.edu.uniandes.rest.providers.CreatedFilter;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author n.vasquez10
 */
//@RunWith(Arquillian.class)
public class ViajeroResourceTest {

    private final int OK = Response.Status.OK.getStatusCode();
    private final int CREATED = Response.Status.CREATED.getStatusCode();
    private final int NO_CONTENT = Response.Status.NO_CONTENT.getStatusCode();

    private final String viajeroPath = "viajeros";
    private final static List<ViajeroDTO> oraculo = new ArrayList<>();
    private final String viajesPath = "viajes";
    private final static List<ViajesDTO> oraculoViaje = new ArrayList<>();

    private WebTarget target;
    private final String apiPath = "api";
    private static PodamFactory factory = new PodamFactoryImpl();

    @ArquillianResource
    private URL deploymentURL;

     @Deployment(testable = false)
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                // Se agrega la dependencia a la logica con el nombre groupid:artefactid:version (GAV)
                .addAsLibraries(Maven.resolver()
                        .resolve("co.edu.uniandes.csw.bookstore:bookstore-logic:1.0-SNAPSHOT")
                        .withTransitivity().asFile())
                // Se agregan los compilados de los paquetes de servicios
                .addPackage(ViajeroResource.class.getPackage())
                .addPackage(ViajeroDTO.class.getPackage())
                .addPackage(ViajeroConverter.class.getPackage())
                .addPackage(EJBExceptionMapper.class.getPackage())
                .addPackage(DateAdapter.class.getPackage())
                .addPackage(CreatedFilter.class.getPackage())
                // El archivo que contiene la configuracion a la base de datos.
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                // El archivo beans.xml es necesario para injeccion de dependencias.
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/beans.xml"))
                // El archivo web.xml es necesario para el despliegue de los servlets
                .setWebXML(new File("src/main/webapp/WEB-INF/web.xml"));
    }

    public ViajeroResourceTest() {
    }


    /**
     * Test of getViajeros method, of class ViajeroResource.
     */
    //@Test
    public void testGetViajeros() throws Exception {
        System.out.println("getViajeros");
        ViajeroResource instance = new ViajeroResource();
        List<ViajeroDTO> expResult = null;
        List<ViajeroDTO> result = instance.getViajeros();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getViajero method, of class ViajeroResource.
     */
    //@Test
    public void testGetViajero() throws Exception {
        System.out.println("getViajero");
        Long id = null;
        ViajeroResource instance = new ViajeroResource();
        ViajeroDTO expResult = null;
        ViajeroDTO result = instance.getViajero(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createViajero method, of class ViajeroResource.
     */
    //@Test
    public void testCreateViajero() throws Exception {
        System.out.println("createViajero");
        ViajeroDTO dto = null;
        ViajeroResource instance = new ViajeroResource();
        ViajeroDTO expResult = null;
        ViajeroDTO result = instance.createViajero(dto);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateViajero method, of class ViajeroResource.
     */
    //@Test
    public void testUpdateViajero() throws Exception {
        System.out.println("updateViajero");
        Long id = null;
        ViajeroDTO dto = null;
        ViajeroResource instance = new ViajeroResource();
        ViajeroDTO expResult = null;
        ViajeroDTO result = instance.updateViajero(id, dto);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteViajero method, of class ViajeroResource.
     */
    //@Test
    public void testDeleteViajero() throws Exception {
        System.out.println("deleteViajero");
        Long id = null;
        ViajeroResource instance = new ViajeroResource();
        instance.deleteViajero(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
