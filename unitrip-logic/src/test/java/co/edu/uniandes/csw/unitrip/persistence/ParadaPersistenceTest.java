/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.unitrip.persistence;

import co.edu.uniandes.csw.unitrip.entities.ItinerarioEntity;
import co.edu.uniandes.csw.unitrip.entities.ParadaEntity;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
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
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author ANDRES
 */
@RunWith(Arquillian.class)
public class ParadaPersistenceTest {

    @Inject
    private ParadaPersistence paradaPersistence;
    @PersistenceContext
    private EntityManager em;
    private final PodamFactory factory = new PodamFactoryImpl();
    @Inject
    UserTransaction utx;

    public ParadaPersistenceTest() {
    }

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ParadaEntity.class.getPackage())
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
    }

    private List<ParadaEntity> data = new ArrayList<>();

    private void insertData() {
        for (int i = 0; i < 3; i++) {
            ParadaEntity entity = factory.manufacturePojo(ParadaEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    @Test
    public void createParadaTest() {
        ParadaEntity newEntity = factory.manufacturePojo(ParadaEntity.class);
        ParadaEntity result = paradaPersistence.create(newEntity);

        Assert.assertNotNull(result);

        ParadaEntity entity = em.find(ParadaEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    @Test
    public void getParadasTest() {
        List<ParadaEntity> list = paradaPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (ParadaEntity ent : list) {
            boolean found = false;
            for (ParadaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void getParadaTest() {
        ParadaEntity entity = data.get(0);
        ParadaEntity newEntity = paradaPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }

    @Test
    public void deleteParadaTest() {
        ParadaEntity entity = data.get(0);
        paradaPersistence.delete(entity.getId());
        ParadaEntity deleted = em.find(ParadaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    @Test
    public void updateParadaTest() {
        ParadaEntity entity = data.get(0);
        ParadaEntity newEntity = factory.manufacturePojo(ParadaEntity.class);

        newEntity.setId(entity.getId());

        paradaPersistence.update(newEntity);

        ParadaEntity resp = em.find(ParadaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }

    /**
     *
     *
     * @Test public void testFind() throws Exception {
     * System.out.println("find"); Long id = null; EJBContainer container =
     * javax.ejb.embeddable.EJBContainer.createEJBContainer(); ParadaPersistence
     * instance =
     * (ParadaPersistence)container.getContext().lookup("java:global/classes/ParadaPersistence");
     * ParadaEntity expResult = null; ParadaEntity result = instance.find(id);
     * assertEquals(expResult, result); container.close(); // TODO review the
     * generated test code and remove the default call to fail. fail("The test
     * case is a prototype."); }
     *
     *
     *
     * @Test public void testFindAll() throws Exception {
     * System.out.println("findAll"); EJBContainer container =
     * javax.ejb.embeddable.EJBContainer.createEJBContainer(); ParadaPersistence
     * instance =
     * (ParadaPersistence)container.getContext().lookup("java:global/classes/ParadaPersistence");
     * List<ParadaEntity> expResult = null; List<ParadaEntity> result =
     * instance.findAll(); assertEquals(expResult, result); container.close();
     * // TODO review the generated test code and remove the default call to
     * fail. fail("The test case is a prototype."); }
     *
     *
     * @Test public void testCreate() throws Exception {
     * System.out.println("create"); ParadaEntity entity = null; EJBContainer
     * container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
     * ParadaPersistence instance =
     * (ParadaPersistence)container.getContext().lookup("java:global/classes/ParadaPersistence");
     * ParadaEntity expResult = null; ParadaEntity result =
     * instance.create(entity); assertEquals(expResult, result);
     * container.close(); // TODO review the generated test code and remove the
     * default call to fail. fail("The test case is a prototype."); }
     *
     *
     * @Test public void testUpdate() throws Exception {
     * System.out.println("update"); ParadaEntity entity = null; EJBContainer
     * container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
     * ParadaPersistence instance =
     * (ParadaPersistence)container.getContext().lookup("java:global/classes/ParadaPersistence");
     * ParadaEntity expResult = null; ParadaEntity result =
     * instance.update(entity); assertEquals(expResult, result);
     * container.close(); // TODO review the generated test code and remove the
     * default call to fail. fail("The test case is a prototype."); }
     *
     *
     * @Test public void testDelete() throws Exception {
     * System.out.println("delete"); Long id = null; EJBContainer container =
     * javax.ejb.embeddable.EJBContainer.createEJBContainer(); ParadaPersistence
     * instance =
     * (ParadaPersistence)container.getContext().lookup("java:global/classes/ParadaPersistence");
     * instance.delete(id); container.close(); // TODO review the generated test
     * code and remove the default call to fail. fail("The test case is a
     * prototype."); }
     * @org.junit.Test public void testSomeMethod() { }
     *
     *
     */
}
