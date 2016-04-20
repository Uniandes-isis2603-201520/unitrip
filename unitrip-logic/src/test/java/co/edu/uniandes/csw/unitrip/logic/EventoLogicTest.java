/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.unitrip.logic;

import co.edu.uniandes.csw.unitrip.api.IEventoLogic;
import co.edu.uniandes.csw.unitrip.ejbs.EventoLogic;
import co.edu.uniandes.csw.unitrip.entities.EventoEntity;
import co.edu.uniandes.csw.unitrip.exceptions.BusinesLogicException;
import co.edu.uniandes.csw.unitrip.persistence.EventoPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author l.castro12
 */
@RunWith(Arquillian.class)
public class EventoLogicTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EventoEntity.class.getPackage())
                .addPackage(EventoLogic.class.getPackage())
                .addPackage(IEventoLogic.class.getPackage())
                .addPackage(EventoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");

    }
    @Inject
    private IEventoLogic eventoLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private final PodamFactory factory = new PodamFactoryImpl();

    @Before
    public void configTest() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
    private void clearData() {
        em.createQuery("delete from EventoEntity").executeUpdate();
    }

    private List<EventoEntity> data = new ArrayList<>();

    private void insertData() {
        for (int i = 0; i < 3; i++) {
            EventoEntity entity = factory.manufacturePojo(EventoEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    @Test
    public void createEventoTest() {
        EventoEntity entity = factory.manufacturePojo(EventoEntity.class);
        EventoEntity result = eventoLogic.createEvento(entity);

        EventoEntity resp = em.find(EventoEntity.class, entity.getId());

        Assert.assertNotNull(result);
        Assert.assertEquals(entity.getId(), resp.getId());
        Assert.assertEquals(entity.getName(), resp.getName());
        Assert.assertEquals(entity.getLatitud(), resp.getLatitud());
        Assert.assertEquals(entity.getLongitud(), resp.getLongitud());
        Assert.assertEquals(entity.getDescription(), resp.getDescription());
        Assert.assertEquals(entity.getFechaInicio(), resp.getFechaInicio());
        Assert.assertEquals(entity.getFechaFin(), resp.getFechaFin());
    }
    @Test
    public void getEventosTest() {
        List<EventoEntity> resultList = eventoLogic.getEventos();
        List<EventoEntity> expectedList = em.createQuery("SELECT u from EventoEntity u").getResultList();
        Assert.assertEquals(expectedList.size(), resultList.size());
        for (EventoEntity expected : expectedList) {
            boolean found = false;
            for (EventoEntity result : resultList) {
                if (result.getId().equals(expected.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
     @Test
    public void getEventoTest() {
        EventoEntity result;
        try {
            result = eventoLogic.getEvento(data.get(0).getId());
            EventoEntity expected = em.find(EventoEntity.class, data.get(0).getId());

        Assert.assertNotNull(result);
        Assert.assertNotNull(expected);
        Assert.assertEquals(expected.getId(), result.getId());
        Assert.assertEquals(expected.getName(), result.getName());
        Assert.assertEquals(expected.getLatitud(), result.getLatitud());
        Assert.assertEquals(expected.getLongitud(), result.getLongitud());
        Assert.assertEquals(expected.getDescription(), result.getDescription());
        Assert.assertEquals(expected.getFechaInicio(), result.getFechaInicio());
        Assert.assertEquals(expected.getFechaFin(), result.getFechaFin());
        } catch (BusinesLogicException ex) {
            Logger.getLogger(EventoLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
     @Test
    public void deleteEventoTest() {
        EventoEntity entity = data.get(1);
        eventoLogic.deleteEvento(entity.getId());
        EventoEntity deleted = em.find(EventoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    @Test
    public void updateEventoTest() {
        EventoEntity entity = data.get(0);
        EventoEntity expected = factory.manufacturePojo(EventoEntity.class);

        expected.setId(entity.getId());

        eventoLogic.updateEvento(expected);

        EventoEntity resp = em.find(EventoEntity.class, entity.getId());

        Assert.assertNotNull(expected);
        Assert.assertEquals(expected.getId(), resp.getId());
        Assert.assertEquals(expected.getName(), resp.getName());
        Assert.assertEquals(expected.getLatitud(), resp.getLatitud());
        Assert.assertEquals(expected.getLongitud(), resp.getLongitud());
        Assert.assertEquals(expected.getDescription(), resp.getDescription());
        Assert.assertEquals(expected.getFechaInicio(), resp.getFechaInicio());
        Assert.assertEquals(expected.getFechaFin(), resp.getFechaFin());
    }

}
