/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.unitrip.logic;

import co.edu.uniandes.csw.unitrip.api.IItinerarioLogic;
import co.edu.uniandes.csw.unitrip.api.IViajeroLogic;
import co.edu.uniandes.csw.unitrip.api.IViajesLogic;
import co.edu.uniandes.csw.unitrip.ejbs.ViajeLogic;
import co.edu.uniandes.csw.unitrip.ejbs.ViajeroLogic;
import co.edu.uniandes.csw.unitrip.entities.ItinerarioEntity;
import co.edu.uniandes.csw.unitrip.entities.ViajeEntity;
import co.edu.uniandes.csw.unitrip.entities.ViajeroEntity;
import co.edu.uniandes.csw.unitrip.exceptions.BusinesLogicException;
import co.edu.uniandes.csw.unitrip.persistence.ViajePersistence;
import co.edu.uniandes.csw.unitrip.persistence.ViajeroPersistence;
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
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author je.molano1498
 */
@RunWith(Arquillian.class)
public class ViajeroLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private IViajeroLogic viajeroLogic;

    @Inject
    private IViajesLogic viajeLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<ViajeroEntity> data = new ArrayList<ViajeroEntity>();

    private List<ViajeEntity> dataViajes = new ArrayList<ViajeEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ViajeroEntity.class.getPackage())
                .addPackage(ViajeroLogic.class.getPackage())
                .addPackage(IViajeroLogic.class.getPackage())
                .addPackage(ViajeroPersistence.class.getPackage())
                .addPackage(ViajeEntity.class.getPackage())
                .addPackage(ViajeLogic.class.getPackage())
                .addPackage(ViajePersistence.class.getPackage())
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
        em.createQuery("delete from ViajeroEntity").executeUpdate();
        em.createQuery("delete from ViajeEntity").executeUpdate();
    }

    private void insertData() {
        for (int i = 0; i < 3; i++) {
            ViajeroEntity viajeros = factory.manufacturePojo(ViajeroEntity.class);
            em.persist(viajeros);
            data.add(viajeros);
        }

        for (int i = 0; i < 3; i++) {
            ViajeEntity viajes = factory.manufacturePojo(ViajeEntity.class);
            em.persist(viajes);
            dataViajes.add(viajes);
            data.get(0).getViajes().add(viajes);
        }
    }

    public ViajeroLogicTest() {
    }

    @Test
    public void createViajeroTest() {
        ViajeroEntity expected = factory.manufacturePojo(ViajeroEntity.class);
        ViajeroEntity created = viajeroLogic.createViajero(expected);

        ViajeroEntity result = em.find(ViajeroEntity.class, created.getId());

        Assert.assertNotNull(result);
        Assert.assertEquals(expected.getId(), result.getId());
        Assert.assertEquals(expected.getName(), result.getName());
    }

    @Test
    public void getViajerosTest() {
        List<ViajeroEntity> list = viajeroLogic.getViajeros();
        Assert.assertEquals(data.size(), list.size());
        for (ViajeroEntity ent : list) {
            boolean found = false;
            for (ViajeroEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void getViajeroTest() {
        ViajeroEntity expected = factory.manufacturePojo(ViajeroEntity.class);
        ViajeroEntity created = viajeroLogic.createViajero(expected);
        try {
            ViajeroEntity created2 = viajeroLogic.getViajero(created.getId());
            Assert.assertNotNull(created2);
            Assert.assertEquals(expected.getId(), created2.getId());
        } catch (Exception e) {
            fail("no deberia generar excepcion");

        }
        try {
            ViajeroEntity expected2 = factory.manufacturePojo(ViajeroEntity.class);
            viajeroLogic.getViajero(expected2.getId());
            fail("deberia generar excepcion");
        } catch (Exception e) {
            //debe pasar por aqui
        }
    }

    @Test
    public void updateViajeTest() {
        try {
            ViajeroEntity entity = data.get(0);
            ViajeroEntity newEntity = factory.manufacturePojo(ViajeroEntity.class);

            newEntity.setId(entity.getId());

            viajeroLogic.updateViajero(newEntity);

            ViajeroEntity resp = viajeroLogic.getViajero(entity.getId());

            Assert.assertEquals(newEntity.getName(), resp.getName());
        } catch (BusinesLogicException ex) {
            fail("no deberia generar excepcion");
        }
    }

    @Test
    public void deleteViajeTest() {
        ViajeroEntity entity = data.get(0);
        viajeroLogic.deleteViajero(entity.getId());;
        try {
            viajeroLogic.getViajero(entity.getId());
            fail("deberia generar excepcion");
        } catch (Exception e) {
            //debe pasar por aqui
        }
    }

}
