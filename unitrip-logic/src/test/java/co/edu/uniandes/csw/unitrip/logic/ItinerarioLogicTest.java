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
 */

@RunWith(Arquillian.class)
public class ItinerarioLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private IItinerarioLogic bookLogic;

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
            //System.out.println(viajes.getDescripcion());
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
//
//     @Test
//    public void createItinerarioTest() {
//        try {
//            ItinerarioEntity entity = factory.manufacturePojo(ItinerarioEntity.class);
//            entity.setDescripcion(getRandomDescripcion ());
//            ItinerarioEntity created = ItinerarioLogic.createItinerario(entity);
//
//            ItinerarioEntity result = em.find(ItinerarioEntity.class, created.getId());
//
//
//
//        } catch (BusinesLogicException ex) {
//            Assert.fail(ex.getLocalizedMessage());
//        }
//    }




    public String getRandomDescripcion (){
        return " random descripcion";
    }

}
