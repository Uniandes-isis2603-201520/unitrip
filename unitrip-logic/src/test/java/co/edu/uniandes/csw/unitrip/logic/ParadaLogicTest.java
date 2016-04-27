/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.unitrip.logic;

import co.edu.uniandes.csw.unitrip.api.IParadaLogic;
import co.edu.uniandes.csw.unitrip.ejbs.ParadaLogic;
import co.edu.uniandes.csw.unitrip.entities.EventoEntity;
import co.edu.uniandes.csw.unitrip.entities.ParadaEntity;
import co.edu.uniandes.csw.unitrip.entities.ItinerarioEntity;
import co.edu.uniandes.csw.unitrip.exceptions.BusinesLogicException;
import co.edu.uniandes.csw.unitrip.persistence.ParadaPersistence;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author af.munoz1477
 */
@RunWith(Arquillian.class)
public class ParadaLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private IParadaLogic paradaLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<ParadaEntity> data = new ArrayList<ParadaEntity>();

    private List<EventoEntity> eventosData = new ArrayList<>();

    private List<ItinerarioEntity> itinerarioData = new ArrayList<>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ParadaEntity.class.getPackage())
                .addPackage(ParadaLogic.class.getPackage())
                .addPackage(IParadaLogic.class.getPackage())
                .addPackage(ParadaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Before
    public void configTest() {
        try {
            utx.begin();
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
        em.createQuery("delete from ParadaEntity").executeUpdate();
        em.createQuery("delete from EventoEntity").executeUpdate();
        em.createQuery("delete from ItinerarioEntity").executeUpdate();
    }

    private void insertData() {
        for (int i = 0; i < 3; i++) {
            EventoEntity eventos = factory.manufacturePojo(EventoEntity.class);
            em.persist(eventos);
            eventosData.add(eventos);
        }

        for (int i = 0; i < 3; i++) {
            ItinerarioEntity itinerario = factory.manufacturePojo(ItinerarioEntity.class);
            em.persist(itinerario);
            itinerarioData.add(itinerario);
        }

        for (int i = 0; i < 3; i++) {
            ParadaEntity entity = factory.manufacturePojo(ParadaEntity.class);
            entity.setDescription(getRandomDescripcion());
            entity.getEventos().add(eventosData.get(0));

            entity.setItinerario(itinerarioData.get(0));

            em.persist(entity);
            data.add(entity);
        }
    }

    @Test
    public void createParadaTest() {
        try {
            ParadaEntity entity = factory.manufacturePojo(ParadaEntity.class);
            entity.setDescription(getRandomDescripcion());
            ParadaEntity created = paradaLogic.createParada(entity);

            ParadaEntity result = em.find(ParadaEntity.class, created.getId());

            Assert.assertNotNull(result);
            Assert.assertEquals(entity.getId(), result.getId());
            Assert.assertEquals(entity.getName(), result.getName());
            Assert.assertEquals(entity.getDescripcion(), result.getDescripcion());

        } catch (BusinesLogicException ex) {
            Assert.fail(ex.getLocalizedMessage());
        }
    }

    @Test
    public void getParadasTest() {
        List<ParadaEntity> resultList = paradaLogic.getParadas();
        List<ParadaEntity> expectedList = em.createQuery("SELECT u from ParadaEntity u").getResultList();
        Assert.assertEquals(expectedList.size(), resultList.size());
        for (ParadaEntity expected : expectedList) {
            boolean found = false;
            for (ParadaEntity result : resultList) {
                if (result.getId().equals(expected.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void getParadaTest() {
        ParadaEntity result = paradaLogic.getParada(data.get(0).getId());
        ParadaEntity expected = em.find(ParadaEntity.class, data.get(0).getId());

        Assert.assertNotNull(expected);
        Assert.assertNotNull(result);
        Assert.assertEquals(expected.getId(), result.getId());
        Assert.assertEquals(expected.getName(), result.getName());
        Assert.assertEquals(expected.getDescripcion(), result.getDescripcion());
    }

    @Test
    public void deleteParadaTest() {
        ParadaEntity entity = data.get(1);
        paradaLogic.deleteParada(entity.getId());
        ParadaEntity deleted = em.find(ParadaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    @Test
    public void updateParadaTest() {
        try {
            ParadaEntity entity = data.get(0);
            ParadaEntity pojoEntity = factory.manufacturePojo(ParadaEntity.class);
            pojoEntity.setId(entity.getId());

            paradaLogic.updateParada(pojoEntity);

            ParadaEntity resp = em.find(ParadaEntity.class, entity.getId());

            Assert.assertEquals(pojoEntity.getId(), resp.getId());
            Assert.assertEquals(pojoEntity.getName(), resp.getName());
            Assert.assertEquals(pojoEntity.getDescripcion(), resp.getDescripcion());

        } catch (BusinesLogicException ex) {
            Assert.fail(ex.getLocalizedMessage());
        }
    }

    public String getRandomDescripcion() {
        return " random descripcion";
    }

}
