/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.unitrip.persistence;

import co.edu.uniandes.csw.unitrip.entities.ExperienciaEntity;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import junit.framework.Assert;
import static org.glassfish.pfl.basic.tools.argparser.ElementParser.factory;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.AfterClass;
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
 *
@RunWith(Arquillian.class)
*/
public class ExperienciaPersistenceTest {
/*
    public ExperienciaPersistenceTest() {
    }

    @Inject
    private ExperienciaPersistence experienciaPersistence;

    @PersistenceContext
    private EntityManager em;

    private final PodamFactory factory = new PodamFactoryImpl();
    @Inject
    UserTransaction utx;
    private List<ExperienciaEntity> data = new ArrayList<ExperienciaEntity>();

    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ExperienciaEntity.class.getPackage())
                .addPackage(ExperienciaPersistence.class.getPackage())
                .addAsManifestResource("META=INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META=INF/beans.xml", "beans.xml");
    }

    @Before
    public void configTest()
    {
        try
        {
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            try
            {
                utx.rollback();

            }
            catch(Exception e1)
                    {
                        e.printStackTrace();
                    }
        }
    }
    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @After
    public void tearDown() {
    }


    public void clearData()
    {
        em.createQuery("delete from experienciaentity").executeUpdate();
    }


    private void insertData()
    {
        for(int i =0; i<3; i++)
        {
            ExperienciaEntity entid = factory.manufacturePojo(ExperienciaEntity.class);
            em.persist(entid);
            data.add(entid);
        }

    }


    @Test
    public void createExperienceText()
    {
        ExperienciaEntity entidadExp = factory.manufacturePojo(ExperienciaEntity.class);
    }
    /**
     * Test of find method, of class ExperienciaPersistence.
     *
    @Test
    public void testFind() throws Exception {
        System.out.println("find");
        Long id = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        ExperienciaPersistence instance = (ExperienciaPersistence)container.getContext().lookup("java:global/classes/ExperienciaPersistence");
        ExperienciaEntity expResult = null;
        ExperienciaEntity result = instance.find(id);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAll method, of class ExperienciaPersistence.
     *
    @Test
    public void testFindAll() throws Exception {
        System.out.println("findAll");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        ExperienciaPersistence instance = (ExperienciaPersistence)container.getContext().lookup("java:global/classes/ExperienciaPersistence");
        List<ExperienciaEntity> expResult = null;
        List<ExperienciaEntity> result = instance.findAll();
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of create method, of class ExperienciaPersistence.
     *
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        ExperienciaEntity entity = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        ExperienciaPersistence instance = (ExperienciaPersistence)container.getContext().lookup("java:global/classes/ExperienciaPersistence");
        ExperienciaEntity expResult = null;
        ExperienciaEntity result = instance.create(entity);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class ExperienciaPersistence.
     *
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        ExperienciaEntity entity = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        ExperienciaPersistence instance = (ExperienciaPersistence)container.getContext().lookup("java:global/classes/ExperienciaPersistence");
        ExperienciaEntity expResult = null;
        ExperienciaEntity result = instance.update(entity);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class ExperienciaPersistence.
     *
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        Long id = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        ExperienciaPersistence instance = (ExperienciaPersistence)container.getContext().lookup("java:global/classes/ExperienciaPersistence");
        instance.delete(id);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void getExperiencesTest()
    {
    List<ExperienciaEntity> list = experienciaPersistence.findAll();
    Assert.assertEquals(data.size(), list.size());
    for(ExperienciaEntity ent :list  )
    {
        boolean encontro=false;
        for(ExperienciaEntity entid : data)
        {
            if(ent.getId().equals(entid.getId()))
            {
                encontro=true;
            }
            Assert.assertTrue(encontro);
        }
    }
    }

*/
}
