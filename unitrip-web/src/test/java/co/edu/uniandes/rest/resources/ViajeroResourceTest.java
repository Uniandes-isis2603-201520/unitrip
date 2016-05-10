/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.resources;

import co.edu.uniandes.rest.adapter.DateAdapter;
import co.edu.uniandes.rest.converters.ViajeroConverter;
import co.edu.uniandes.rest.dtos.ExperienciaDTO;
import co.edu.uniandes.rest.dtos.ViajeroDTO;
import co.edu.uniandes.rest.dtos.ViajesDTO;
import co.edu.uniandes.rest.mappers.EJBExceptionMapper;
import co.edu.uniandes.rest.providers.CreatedFilter;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
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
    private final String experienciasPath = "experiencias";
    private final static List<ExperienciaDTO> oraculoExperiencia = new ArrayList<>();

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
                        .resolve("co.edu.uniandes.csw.unitrip:unitrip-logic:1.0-SNAPSHOT")
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

    @Before
    public void setUpTest() {
        target = ClientBuilder.newClient().target(deploymentURL.toString()).path(apiPath);
    }

    @BeforeClass
    public static void setUp() {
        insertData();
    }

    public static void insertData() {
        for (int i = 0; i < 5; i++) {
            ViajeroDTO viajero = factory.manufacturePojo(ViajeroDTO.class);

            viajero.setId(i + 1L);

            oraculo.add(viajero);

            ViajesDTO viajes = factory.manufacturePojo(ViajesDTO.class);
            viajes.setId(i + 1L);
            oraculoViaje.add(viajes);

            ExperienciaDTO experiencias = factory.manufacturePojo(ExperienciaDTO.class);
            experiencias.setFechaP(getMaxDate());
            experiencias.setId(i + 1L);
            oraculoExperiencia.add(experiencias);
        }
    }

    /**
     * Test of createViajero method, of class ViajeroResource.
     */
    //@Test
    //@InSequence(1)
    public void testCreateViajero() throws Exception {
         ViajeroDTO viajero = oraculo.get(0);
        Response response = target.path(viajeroPath).request()
                .post(Entity.entity(viajero, MediaType.APPLICATION_JSON));
        ViajeroDTO viajeroTest = (ViajeroDTO) response.readEntity(ViajeroDTO.class);

        Assert.assertEquals(viajero.getUsuario(), viajeroTest.getUsuario());
        Assert.assertEquals(viajero.getMail(), viajeroTest.getMail());
        Assert.assertEquals(CREATED, response.getStatus());
    }


    /**
     *
     * Test of getViajeros method, of class ViajeroResource.
     */
    //@Test
    public void testGetViajeros() throws Exception {

    }

    /**
     * Test of getViajero method, of class ViajeroResource.
     */
    //@Test
    public void testGetViajero() throws Exception {

    }



    /**
     * Test of updateViajero method, of class ViajeroResource.
     */
    //@Test
    public void testUpdateViajero() throws Exception {

    }

    /**
     * Test of deleteViajero method, of class ViajeroResource.
     */
    //@Test
    public void testDeleteViajero() throws Exception {

    }

    private static Date getMaxDate() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 9999);
        c.set(Calendar.DAY_OF_YEAR, c.getActualMaximum(Calendar.DAY_OF_YEAR));
        c.set(Calendar.HOUR_OF_DAY, c.getActualMinimum(Calendar.HOUR_OF_DAY));
        c.set(Calendar.MINUTE, c.getActualMinimum(Calendar.MINUTE));
        c.set(Calendar.SECOND, c.getActualMinimum(Calendar.SECOND));
        c.set(Calendar.MILLISECOND, c.getActualMinimum(Calendar.MILLISECOND));
        return c.getTime();
    }
}
