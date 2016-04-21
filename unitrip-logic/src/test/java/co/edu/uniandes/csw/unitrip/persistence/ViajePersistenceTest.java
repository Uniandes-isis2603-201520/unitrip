/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.unitrip.persistence;

import co.edu.uniandes.csw.unitrip.entities.ViajeEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
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
 * @author je.molano1498
 *

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

    private void clearData() {
        em.createQuery("delete from ViajeEntity").executeUpdate();
    }

    private List<ViajeEntity> data = new ArrayList<>();

    private void insertData() {
        for (int i = 0; i < 3; i++) {
            ViajeEntity entity = factory.manufacturePojo(ViajeEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }


    @Test
    public void createViajeTest() {
        ViajeEntity newEntity = factory.manufacturePojo(ViajeEntity.class);
        ViajeEntity result = viajePersistence.create(newEntity);

        Assert.assertNotNull(result);

        ViajeEntity entity = em.find(ViajeEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    @Test
    public void getViajesTest() {
        List<ViajeEntity> list = viajePersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (ViajeEntity ent : list) {
            boolean found = false;
            for (ViajeEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void getViajeTest() {
        ViajeEntity entity = data.get(0);
        ViajeEntity newEntity = viajePersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }

    @Test
    public void deleteViajeTest() {
        ViajeEntity entity = data.get(0);
        viajePersistence.delete(entity.getId());
        ViajeEntity deleted = em.find(ViajeEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

     @Test
    public void updateViajeTest() {
        ViajeEntity entity = data.get(0);
        ViajeEntity newEntity = factory.manufacturePojo(ViajeEntity.class);

        newEntity.setId(entity.getId());

        viajePersistence.update(newEntity);

        ViajeEntity resp = em.find(ViajeEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }


}
