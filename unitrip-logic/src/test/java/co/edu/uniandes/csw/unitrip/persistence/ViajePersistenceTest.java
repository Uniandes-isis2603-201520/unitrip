/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.unitrip.persistence;

import co.edu.uniandes.csw.unitrip.entities.EventoEntity;
import co.edu.uniandes.csw.unitrip.entities.ViajeEntity;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import static org.glassfish.pfl.basic.tools.argparser.ElementParser.factory;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author je.molano1498
 */
@RunWith(Arquillian.class)
public class ViajePersistenceTest {

     @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ViajeEntity.class.getPackage())
                .addPackage(ViajePersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Inject
    private ViajePersistence viajePersistence;

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

    private List<ViajeEntity> data = new ArrayList<>();

    private void insertData() {
        for (int i = 0; i < 3; i++) {
            ViajeEntity entity = factory.manufacturePojo(ViajeEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    private void clearData() {
        em.createQuery("delete from ViajeEntity").executeUpdate();
    }



    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }


    @Test
    public void createViajeTest()
    {

    }

    /**
     * Test of create method, of class ViajePersistence.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        ViajeEntity entity = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        ViajePersistence instance = (ViajePersistence)container.getContext().lookup("java:global/classes/ViajePersistence");
        ViajeEntity expResult = null;
        ViajeEntity result = instance.create(entity);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class ViajePersistence.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        ViajeEntity entity = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        ViajePersistence instance = (ViajePersistence)container.getContext().lookup("java:global/classes/ViajePersistence");
        ViajeEntity expResult = null;
        ViajeEntity result = instance.update(entity);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class ViajePersistence.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        Long id = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        ViajePersistence instance = (ViajePersistence)container.getContext().lookup("java:global/classes/ViajePersistence");
        instance.delete(id);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }



}
