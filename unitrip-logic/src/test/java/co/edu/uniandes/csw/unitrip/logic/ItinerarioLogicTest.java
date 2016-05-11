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
import co.edu.uniandes.csw.unitrip.persistence.ParadaPersistence;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    @Inject
    private ItinerarioPersistence itiPersiste;
    @Inject
    private ParadaPersistence paradaPersiste;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<ItinerarioEntity> data = new ArrayList<ItinerarioEntity>();

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
        em.createQuery("delete from ParadaEntity").executeUpdate();
        em.createQuery("delete from ItinerarioEntity").executeUpdate();

    }

    private void insertData() {
        for (int i = 0; i < 3; i++) {
            ItinerarioEntity itinerario = factory.manufacturePojo(ItinerarioEntity.class);
            em.persist(itinerario);
            data.add(itinerario);
            ParadaEntity parada = factory.manufacturePojo(ParadaEntity.class);
            em.persist(parada);
            paradasData.add(parada);
            itinerario.getParadas().add(parada);
            parada.setItinerario(itinerario);
        }
        ItinerarioEntity itinerario = factory.manufacturePojo(ItinerarioEntity.class);
        em.persist(itinerario);
        data.add(itinerario);
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
        try {
            ItinerarioEntity result = itinerarioLogic.getItinerario(data.get(0).getId());
            ItinerarioEntity expected = em.find(ItinerarioEntity.class, data.get(0).getId());

            Assert.assertNotNull(expected);
            Assert.assertNotNull(result);
            Assert.assertEquals(expected.getId(), result.getId());
            Assert.assertEquals(expected.getName(), result.getName());
            Assert.assertEquals(expected.getDescripcion(), result.getDescripcion());
        } catch (Exception e) {
            Assert.fail("No debería mandar excepcíon");
        }
        try {
            ItinerarioEntity result = factory.manufacturePojo(ItinerarioEntity.class);
            itinerarioLogic.getItinerario(result.getId());
            Assert.fail("No");
        } catch (Exception e) {

        }
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

    @Test
    public void getParadaTest() {
        ItinerarioEntity entity = data.get(0);
        ParadaEntity paradaEntity = paradasData.get(0);
        ParadaEntity response = itinerarioLogic.getParada(entity.getId(), paradaEntity.getId());
        Assert.assertNotNull(response);
        Assert.assertEquals(entity.getId(), response.getItinerario().getId());
        response = itinerarioLogic.getParada(data.get(data.size() - 1).getId(), paradaEntity.getId());
        Assert.assertNull(response);
    }

    @Test
    public void addParadaTest() {
        ItinerarioEntity entity = data.get(0);
        ParadaEntity paradaEntity = paradasData.get(1);
        String fechaItiI = "2016-02-12";
        String fechaItiF = "2019-02-12";
        String fechaPI = "2016-02-25";
        String fechaPF = "2017-11-12";
        DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaItinerarioI;
        Date fechaItinerarioF;
        Date fechaParaI;
        Date fechaParaF;
        try {
            fechaItinerarioI = formato.parse(fechaItiI);
            fechaItinerarioF = formato.parse(fechaItiF);
            fechaParaI = formato.parse(fechaPI);
            fechaParaF = formato.parse(fechaPF);
            entity.setFechaI(fechaItinerarioI);
            entity.setFechaF(fechaItinerarioF);
            paradaEntity.setFechaI(fechaParaI);
            paradaEntity.setFechaF(fechaParaF);
            itiPersiste.update(entity);
            paradaPersiste.update(paradaEntity);
            try {
                ParadaEntity response = itinerarioLogic.addParada(paradaEntity.getId(), entity.getId());
                Assert.assertNotNull(response);
                Assert.assertEquals(entity.getId(), response.getItinerario().getId());
            } catch (Exception e) {
                Assert.fail(e.getMessage());
            }
        } catch (ParseException ex) {
            Logger.getLogger(ItinerarioLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Test
    public void removeParadaTest() {

        ItinerarioEntity entity = data.get(0);
        ParadaEntity paradaEntity = paradasData.get(0);
        Long actual = paradaEntity.getId();
        try {
            itinerarioLogic.removeParada(paradaEntity.getId(), entity.getId());
            for (ParadaEntity a : itinerarioLogic.getItinerario(entity.getId()).getParadas()) {
                if (a.getId() == actual) {
                    Assert.fail();
                }
            }
        } catch (Exception ex) {
            Assert.fail("no debería generar excepción");
        }

        entity = data.get(1);
        paradaEntity = paradasData.get(2);
        try {
            itinerarioLogic.removeParada(paradaEntity.getId(), entity.getId());
        } catch (Exception ex) {
            //debería generar excepcion
        }
    }

}
