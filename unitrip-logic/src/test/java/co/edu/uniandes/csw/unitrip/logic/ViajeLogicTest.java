/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.unitrip.logic;

import co.edu.uniandes.csw.unitrip.api.IItinerarioLogic;
import co.edu.uniandes.csw.unitrip.api.IViajesLogic;
import co.edu.uniandes.csw.unitrip.ejbs.ViajeLogic;
import co.edu.uniandes.csw.unitrip.entities.ItinerarioEntity;
import co.edu.uniandes.csw.unitrip.entities.ViajeEntity;
import co.edu.uniandes.csw.unitrip.exceptions.BusinesLogicException;
import co.edu.uniandes.csw.unitrip.persistence.ViajePersistence;
import static org.junit.Assert.*;
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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author je.molano1498
 */
@RunWith(Arquillian.class)
public class ViajeLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private IViajesLogic viajesLogic;

    @Inject
    private IItinerarioLogic itinerarioLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<ViajeEntity> data = new ArrayList<ViajeEntity>();

    private List<ItinerarioEntity> itinerariosData = new ArrayList<>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ViajeEntity.class.getPackage())
                .addPackage(ViajeLogic.class.getPackage())
                .addPackage(IViajesLogic.class.getPackage())
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
        em.createQuery("delete from ItinerarioEntity").executeUpdate();
        em.createQuery("delete from ViajeEntity").executeUpdate();

    }

    private void insertData() {
        for (int i = 0; i < 3; i++) {
            ViajeEntity viaje = factory.manufacturePojo(ViajeEntity.class);
            em.persist(viaje);
            data.add(viaje);
            ItinerarioEntity itinerario = factory.manufacturePojo(ItinerarioEntity.class);
            em.persist(viaje);
            itinerariosData.add(itinerario);
            viaje.getItinerarios().add(itinerario);
            itinerario.setViaje(viaje);
        }
        ViajeEntity viaje = factory.manufacturePojo(ViajeEntity.class);
        em.persist(viaje);
        data.add(viaje);
    }

    public ViajeLogicTest() {
    }

    @Test
    public void createViajeTest() {
        ViajeEntity expected = factory.manufacturePojo(ViajeEntity.class);
        ViajeEntity created = viajesLogic.createViaje(expected);

        ViajeEntity result = em.find(ViajeEntity.class, created.getId());

        Assert.assertNotNull(result);
        Assert.assertEquals(expected.getId(), result.getId());
        Assert.assertEquals(expected.getName(), result.getName());
        Assert.assertEquals(expected.getImage(), result.getImage());
    }

    @Test
    public void getViajesTest() {
        List<ViajeEntity> list = viajesLogic.getViajes();
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
        ViajeEntity expected = factory.manufacturePojo(ViajeEntity.class);
        ViajeEntity created = viajesLogic.createViaje(expected);
        try {
            ViajeEntity created2 = viajesLogic.getViaje(created.getId());
            Assert.assertNotNull(created2);
            Assert.assertEquals(expected.getId(), created2.getId());
        } catch (Exception e) {
            fail("no deberia generar excepcion");

        }
        try {
            ViajeEntity expected2 = factory.manufacturePojo(ViajeEntity.class);
            viajesLogic.getViaje(expected2.getId());
            fail("deberia generar excepcion");
        } catch (Exception e) {
            //debe pasar por aqui
        }
    }

    @Test
    public void updateViajeTest() {
        try {
            ViajeEntity entity = data.get(0);
            ViajeEntity newEntity = factory.manufacturePojo(ViajeEntity.class);

            newEntity.setId(entity.getId());

            viajesLogic.updateViaje(newEntity);

            ViajeEntity resp = viajesLogic.getViaje(entity.getId());

            Assert.assertEquals(newEntity.getName(), resp.getName());
        } catch (BusinesLogicException ex) {
            fail("no deberia generar excepcion");
        }
    }

    @Test
    public void deleteViajeTest() {
        ViajeEntity entity = data.get(0);
        viajesLogic.deleteViaje(entity.getId());;
        try {
            viajesLogic.getViaje(entity.getId());
            fail("deberia generar excepcion");
        } catch (Exception e) {
            //debe pasar por aqui
        }
    }

    @Test
    public void getItinerarioTest (){
        ViajeEntity entity = data.get(0);
        ItinerarioEntity itinerarioEntity = itinerariosData.get(0);
        ItinerarioEntity response = viajesLogic.getItinerario(entity.getId(), itinerarioEntity.getId());
        Assert.assertNotNull(response);
        Assert.assertEquals(entity.getId(), response.getViaje().getId());
        response = viajesLogic.getItinerario(data.get(data.size()-1).getId(), itinerarioEntity.getId());
        Assert.assertNull(response);
    }


    @Test
    public void addItinerarioTest (){
        ViajeEntity entity = data.get(0);
        ItinerarioEntity itinerarioEntity = itinerariosData.get(1);

        ItinerarioEntity response = viajesLogic.addItinerario(itinerarioEntity.getId(),entity.getId());
        Assert.assertNotNull(response);
        Assert.assertEquals(entity.getId(), response.getViaje().getId());
    }

    @Test
    public void removeItinerarioTest (){

        ViajeEntity entity = data.get(0);
        ItinerarioEntity itinerarioEntity = itinerariosData.get(0);
        Long actual = itinerarioEntity.getId();
        try {
            viajesLogic.removeItinerario(entity.getId(), itinerarioEntity.getId());
            for (ItinerarioEntity a : viajesLogic.getViaje(entity.getId()).getItinerarios())
            {
                if(a.getId()==actual)
                {
                    Assert.fail();
                }
            }
        } catch (BusinesLogicException ex) {
            Assert.fail("no debería generar excepción");
        }


       entity = data.get(1);
       itinerarioEntity = itinerariosData.get(2);
       try {
            viajesLogic.removeItinerario(entity.getId(), itinerarioEntity.getId());
        } catch (Exception ex) {
            //debería generar excepcion
        }
    }

}
