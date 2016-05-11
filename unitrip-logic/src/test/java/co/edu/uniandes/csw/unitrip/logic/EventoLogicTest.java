/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.unitrip.logic;

import co.edu.uniandes.csw.unitrip.api.IEventoLogic;
import co.edu.uniandes.csw.unitrip.ejbs.EventoLogic;
import co.edu.uniandes.csw.unitrip.entities.EventoEntity;
import co.edu.uniandes.csw.unitrip.entities.ParadaEntity;
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
        //em.createQuery("delete from ParadaEntity").executeUpdate();
        em.createQuery("delete from EventoEntity").executeUpdate();
    }

    private List<EventoEntity> data = new ArrayList<>();


    private void insertData() {
        for (int i = 0; i < 3; i++) {
            EventoEntity entity = factory.manufacturePojo(EventoEntity.class);
            em.persist(entity);
            data.add(entity);
        }
        EventoEntity evento = factory.manufacturePojo(EventoEntity.class);
        em.persist(evento);
        data.add(evento);
    }

    @Test
    public void createEventoTest() {
        EventoEntity expected = factory.manufacturePojo(EventoEntity.class);
        EventoEntity created = eventoLogic.createEvento(expected);
        EventoEntity result = em.find(EventoEntity.class, created.getId());
        Assert.assertNotNull(result);
        Assert.assertEquals(expected.getId(), result.getId());
        Assert.assertEquals(expected.getName(), result.getName());
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
       EventoEntity expected = factory.manufacturePojo(EventoEntity.class);
        EventoEntity created = eventoLogic.createEvento(expected);
        try {
            EventoEntity created2 = eventoLogic.getEvento(created.getId());
            Assert.assertNotNull(created2);
            Assert.assertEquals(expected.getId(), created2.getId());
        } catch (Exception e) {
            fail("no deberia generar excepcion");
        }
        try {
            EventoEntity expected2 = factory.manufacturePojo(EventoEntity.class);
            eventoLogic.getEvento(expected2.getId());
            fail("deberia generar excepcion");
        } catch (Exception e) {
            //debe pasar por aqui
        }
    }

    @Test
    public void deleteEventoTest() {
        EventoEntity entity = data.get(0);
        eventoLogic.deleteEvento(entity.getId());;
        try {
            eventoLogic.getEvento(entity.getId());
            fail("deberia generar excepcion");
        } catch (Exception e) {
            //debe pasar por aqui
        }
    }

    @Test
    public void updateEventoTest() {
        try {
            EventoEntity entity = data.get(0);
            EventoEntity newEntity = factory.manufacturePojo(EventoEntity.class);

            newEntity.setId(entity.getId());

            eventoLogic.updateEvento(newEntity);

            EventoEntity resp = eventoLogic.getEvento(entity.getId());

            Assert.assertEquals(newEntity.getName(), resp.getName());
        } catch (BusinesLogicException ex) {
            fail("no deberia generar excepcion");
        }
    }

}
