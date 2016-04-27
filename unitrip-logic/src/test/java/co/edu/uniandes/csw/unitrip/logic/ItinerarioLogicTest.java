/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.unitrip.logic;

import co.edu.uniandes.csw.unitrip.api.IItinerarioLogic;
import co.edu.uniandes.csw.unitrip.ejbs.ItinerarioLogic;
import co.edu.uniandes.csw.unitrip.entities.ItinerarioEntity;
import co.edu.uniandes.csw.unitrip.entities.ParadaEntity;
import co.edu.uniandes.csw.unitrip.entities.ViajeEntity;
import co.edu.uniandes.csw.unitrip.exceptions.BusinesLogicException;
import co.edu.uniandes.csw.unitrip.persistence.ItinerarioPersistence;

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
 * @author dm.delgado10
 *
 */
@RunWith(Arquillian.class)
public class ItinerarioLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private IItinerarioLogic itinerarioLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<ItinerarioEntity> data = new ArrayList<ItinerarioEntity>();

    private List<ViajeEntity> viajeData = new ArrayList<>();

    private List<ParadaEntity> paradasData = new ArrayList<>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ItinerarioEntity.class.getPackage())
                .addPackage(ItinerarioLogic.class.getPackage())
                .addPackage(IItinerarioLogic.class.getPackage())
                .addPackage(ItinerarioPersistence.class.getPackage())
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
        em.createQuery("delete from ItinerarioEntity").executeUpdate();
        em.createQuery("delete from ParadaEntity").executeUpdate();
        em.createQuery("delete from ViajeEntity").executeUpdate(); // no se si este se deberia hacer
    }

    private void insertData() {
        for (int i = 0; i < 3; i++) {
            ViajeEntity viaje = factory.manufacturePojo(ViajeEntity.class);
            System.out.println(viaje.getDescripcion());
            em.persist(viaje);
            viajeData.add(viaje);
        }

        for (int i = 0; i < 3; i++) {
            ParadaEntity paradas = factory.manufacturePojo(ParadaEntity.class);
            em.persist(paradas);
            paradasData.add(paradas);
        }

        for (int i = 0; i < 3; i++) {
            ItinerarioEntity entity = factory.manufacturePojo(ItinerarioEntity.class);
            entity.setDescripcion(getRandomDescripcion());
            //entity.setPublishDate(getMaxDate());
            //System.out.println(entity.getPublishDate());
            for (ParadaEntity item : entity.getParadas()) {
                item.setItinerario(entity);
            }

            entity.getParadas().add(paradasData.get(0));

            entity.setViaje(viajeData.get(0));
            em.persist(entity);
            data.add(entity);
        }
    }

    @Test
    public void createItinerarioTest() {
        try {
            ItinerarioEntity entity = factory.manufacturePojo(ItinerarioEntity.class);
            entity.setDescripcion(getRandomDescripcion());
            ItinerarioEntity created = itinerarioLogic.createItinerario(entity);

            ItinerarioEntity result = em.find(ItinerarioEntity.class, created.getId());

            Assert.assertNotNull(result);
            Assert.assertEquals(entity.getId(), result.getId());
            Assert.assertEquals(entity.getName(), result.getName());
            //Assert.assertEquals(entity.getDescripcion(), result.getDescripcion());
            Assert.assertEquals(entity.getFechaI(), result.getFechaI());
            Assert.assertEquals(entity.getFechaF(), result.getFechaF());

        } catch (BusinesLogicException ex) {
            Assert.fail(ex.getLocalizedMessage());
        }
    }

    @Test
    public void getItinerariosTest() {
        List<ItinerarioEntity> resultList = itinerarioLogic.getItinerarios();
        List<ItinerarioEntity> expectedList = em.createQuery("SELECT u from ItinerarioEntity u").getResultList();
        Assert.assertEquals(expectedList.size(), resultList.size());
        for (ItinerarioEntity expected : expectedList) {
            boolean found = false;
            for (ItinerarioEntity result : resultList) {
                if (result.getId().equals(expected.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void getItinerarioTest() {
        ItinerarioEntity result = itinerarioLogic.getItinerario(data.get(0).getId());
        ItinerarioEntity expected = em.find(ItinerarioEntity.class, data.get(0).getId());

        Assert.assertNotNull(expected);
        Assert.assertNotNull(result);
        Assert.assertEquals(expected.getId(), result.getId());
        Assert.assertEquals(expected.getName(), result.getName());
        Assert.assertEquals(expected.getDescripcion(), result.getDescripcion());
    }

    @Test
    public void deleteItinerarioTest() {
        ItinerarioEntity entity = data.get(1);
        itinerarioLogic.deleteItinerario(entity.getId());
        ItinerarioEntity deleted = em.find(ItinerarioEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    @Test
    public void updateItinerarioTest() {
        try {
            ItinerarioEntity entity = data.get(0);
            ItinerarioEntity pojoEntity = factory.manufacturePojo(ItinerarioEntity.class);
            pojoEntity.setDescripcion(getRandomDescripcion());

            pojoEntity.setId(entity.getId());

            itinerarioLogic.updateItinerario(pojoEntity);

            ItinerarioEntity resp = em.find(ItinerarioEntity.class, entity.getId());

            Assert.assertEquals(pojoEntity.getId(), resp.getId());
            Assert.assertEquals(pojoEntity.getName(), resp.getName());
            Assert.assertEquals(pojoEntity.getFechaI(), resp.getFechaI());
            Assert.assertEquals(pojoEntity.getFechaF(), resp.getFechaF());
            //Assert.assertEquals(pojoEntity.getDescripcion(), resp.getDescriction());

        } catch (BusinesLogicException ex) {
            Assert.fail(ex.getLocalizedMessage());
        }
    }

    // vamos con los de relaciones
    /**
     * @Test public void getParadaTest() { ItinerarioEntity entity =
     * data.get(0); ParadaEntity paradaEntity = paradasData.get(0); ParadaEntity
     * response = itinerarioLogic.getParada(entity.getId(),
     * paradaEntity.getId());
     *
     * ParadaEntity expected = getItinerarioParada(entity.getId(),
     * paradaEntity.getId());
     *
     * Assert.assertNotNull(expected); Assert.assertNotNull(response);
     * Assert.assertEquals(expected.getId(), response.getId());
     * Assert.assertEquals(expected.getName(), response.getName());
     * //Assert.assertEquals(expected.getBirthDate(), response.getBirthDate());
     * }
     *
     */
    @Test
    public void listParadasTest() {
        List<ParadaEntity> list = itinerarioLogic.getParadas(data.get(0).getId());
        ItinerarioEntity expected = em.find(ItinerarioEntity.class, data.get(0).getId());

        Assert.assertNotNull(expected);
        Assert.assertEquals(expected.getParadas().size(), list.size());
    }

    /**
     * @Test public void addParadasTest() { try { ItinerarioEntity entity =
     * data.get(0); ParadaEntity paradaEntity = paradasData.get(1); ParadaEntity
     * response = itinerarioLogic.addParada(paradaEntity.getId(),
     * entity.getId());
     *
     * ParadaEntity expected = getItinerarioParada(entity.getId(),
     * paradaEntity.getId());
     *
     * Assert.assertNotNull(expected); Assert.assertNotNull(response);
     * Assert.assertEquals(expected.getId(), response.getId()); } catch
     * (BusinesLogicException ex) { Assert.fail(ex.getLocalizedMessage()); } }
     *
     */
    /**
     * @Test public void replaceParadasTest() { try { ItinerarioEntity entity =
     * data.get(0); List<ParadaEntity> list = paradasData.subList(1, 3);
     * itinerarioLogic.replaceParadas(list, entity.getId());
     *
     * ItinerarioEntity expected = em.find(ItinerarioEntity.class,
     * entity.getId());
     *
     * Assert.assertNotNull(expected);
     * Assert.assertFalse(expected.getParadas().contains(paradasData.get(0)));
     * Assert.assertTrue(expected.getParadas().contains(paradasData.get(1)));
     * Assert.assertTrue(expected.getParadas().contains(paradasData.get(2))); }
     * catch (BusinesLogicException ex) { Assert.fail(ex.getLocalizedMessage());
     * } }
     *
     */
    /**
     * @Test(expected = NoResultException.class) public void removeAuthorsTest()
     * { itinerarioLogic.removeParada(paradasData.get(0).getId(),
     * data.get(0).getId()); getItinerarioParada(data.get(0).getId(),
     * paradasData.get(0).getId()); }
     *
     */
    public String getRandomDescripcion() {
        return " random descripcion";
    }

    // metodo con el query
    private ParadaEntity getItinerarioParada(Long itinerarioId, Long paradaId) {
        Query q = em.createQuery("Select DISTINCT a from ItinerarioEntity b join b.paradas a where b.id = :itinerarioId and a.id=:paradaId");
        q.setParameter("itinerarioId", itinerarioId);
        q.setParameter("paradaId", paradaId);

        return (ParadaEntity) q.getSingleResult();
    }

}
