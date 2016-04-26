/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.unitrip.logic;

import co.edu.uniandes.csw.unitrip.api.IExperienciaLogic;
import co.edu.uniandes.csw.unitrip.ejbs.ExperienciaLogic;
import co.edu.uniandes.csw.unitrip.entities.ExperienciaEntity;
import co.edu.uniandes.csw.unitrip.entities.ViajeEntity;
import co.edu.uniandes.csw.unitrip.entities.ViajeroEntity;
import co.edu.uniandes.csw.unitrip.exceptions.BusinesLogicException;
import co.edu.uniandes.csw.unitrip.persistence.ExperienciaPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author n.vasquez10
 */
@RunWith(Arquillian.class)
public class ExperienciaLogicTest {



    public ExperienciaLogicTest() {
    }


    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private IExperienciaLogic experienciaLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;


    private List<ExperienciaEntity> data = new ArrayList<ExperienciaEntity>();
    private List<ViajeroEntity> viajeroData = new ArrayList<>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ExperienciaEntity.class.getPackage())
                .addPackage(ExperienciaLogic.class.getPackage())
                .addPackage(IExperienciaLogic.class.getPackage())
                .addPackage(ExperienciaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }



    @Before
    public void setUp() {
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
        em.createQuery("delete from ExperienciaEntity").executeUpdate();
        em.createQuery("delete from ViajeEntity").executeUpdate(); // no se si este se deberia hacer
    }

    private void insertData() {
        for (int i = 0; i < 3; i++) {
            ViajeroEntity viajero = factory.manufacturePojo(ViajeroEntity.class);
            //System.out.println(viajero.getDescripcion());
            em.persist(viajero);
            viajeroData.add(viajero);
        }

            for (int i = 0; i < 3; i++) {
            ExperienciaEntity entity = factory.manufacturePojo(ExperienciaEntity.class);
            entity.setDescription(randomDescripcion());

            entity.setViajero(viajeroData.get(0));
            em.persist(entity);
            data.add(entity);
        }
    }

    public String randomDescripcion()
    {
        return "Descripcion de experiencia";
    }

    /**
     * Test of getExperiencias method, of class ExperienciaLogic.
     */
    @Test
    public void testGetExperiencias() throws Exception {
          List<ExperienciaEntity> resultList = experienciaLogic.getExperiencias();
        List<ExperienciaEntity> expectedList = em.createQuery("SELECT u from ExperienciaEntity u").getResultList();
        Assert.assertEquals(expectedList.size(), resultList.size());
        for (ExperienciaEntity expected : expectedList) {
            boolean found = false;
            for (ExperienciaEntity result : resultList) {
                if (result.getId().equals(expected.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Test of getExperiencia method, of class ExperienciaLogic.
     */
    @Test
    public void testGetExperiencia() throws Exception {
         ExperienciaEntity result = experienciaLogic.getExperiencia(data.get(0).getId());
        ExperienciaEntity expected = em.find(ExperienciaEntity.class, data.get(0).getId());

        Assert.assertNotNull(expected);
        Assert.assertNotNull(result);
        Assert.assertEquals(expected.getId(), result.getId());
        Assert.assertEquals(expected.getName(), result.getName());
        Assert.assertEquals(expected.getDescripcion(), result.getDescripcion());
    }

    /**
     * Test of createExperiencia method, of class ExperienciaLogic.
     */
    @Test
    public void testCreateExperiencia() throws Exception {

        ExperienciaEntity entity = factory.manufacturePojo(ExperienciaEntity.class);
        entity.setDescription(randomDescripcion ());
        ExperienciaEntity created = experienciaLogic.createExperiencia(entity);
        ExperienciaEntity result = em.find(ExperienciaEntity.class, created.getId());
        Assert.assertNotNull(result);
        Assert.assertEquals(entity.getId(), result.getId());
        Assert.assertEquals(entity.getName(), result.getName());
        Assert.assertEquals(entity.getDescripcion(), result.getDescripcion());
    }

    /**
     * Test of updateExperiencia method, of class ExperienciaLogic.
     */
    @Test
    public void testUpdateExperiencia() throws Exception {
        ExperienciaEntity entity = data.get(0);
            ExperienciaEntity pojoEntity = factory.manufacturePojo(ExperienciaEntity.class);
            pojoEntity.setDescription(randomDescripcion());

            pojoEntity.setId(entity.getId());

            experienciaLogic.updateExperiencia(pojoEntity);

            ExperienciaEntity resp = em.find(ExperienciaEntity.class, entity.getId());

            Assert.assertEquals(pojoEntity.getId(), resp.getId());
            Assert.assertEquals(pojoEntity.getName(), resp.getName());

    }


    /**
     * Test of deleteExperiencia method, of class ExperienciaLogic.
     */
    @Test
    public void testDeleteExperiencia() throws Exception {
        ExperienciaEntity entity = data.get(1);
        experienciaLogic.deleteExperiencia(data.get(1).getId());
        ExperienciaEntity deleted = em.find(ExperienciaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }



}
