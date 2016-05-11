/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.unitrip.logic;

import co.edu.uniandes.csw.unitrip.api.IParadaLogic;
import co.edu.uniandes.csw.unitrip.ejbs.ParadaLogic;
import co.edu.uniandes.csw.unitrip.entities.EventoEntity;
import co.edu.uniandes.csw.unitrip.entities.ParadaEntity;
import co.edu.uniandes.csw.unitrip.entities.ItinerarioEntity;
import co.edu.uniandes.csw.unitrip.exceptions.BusinesLogicException;
import co.edu.uniandes.csw.unitrip.persistence.EventoPersistence;
import co.edu.uniandes.csw.unitrip.persistence.ParadaPersistence;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
 * @author af.munoz1477
 */
@RunWith(Arquillian.class)
public class ParadaLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private IParadaLogic paradaLogic;

    @Inject
    private ParadaPersistence paradaPersiste;

    @Inject
    private EventoPersistence eventoPersiste;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<ParadaEntity> data = new ArrayList<ParadaEntity>();

    private List<EventoEntity> eventosData = new ArrayList<>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ParadaEntity.class.getPackage())
                .addPackage(ParadaLogic.class.getPackage())
                .addPackage(IParadaLogic.class.getPackage())
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
        //em.createQuery("delete from PRDNTTYVNTNTTVNTSD").executeUpdate();
        //em.createQuery("delete from EventoEntity_ParadaEntity").executeUpdate();
        em.createQuery("delete from ParadaEntity").executeUpdate();


    }

    private void insertData() {
        for (int i = 0; i < 3; i++) {
            EventoEntity eventos = factory.manufacturePojo(EventoEntity.class);
            em.persist(eventos);
            eventosData.add(eventos);
        }
        for (int i = 0; i < 3; i++) {
            ParadaEntity entity = factory.manufacturePojo(ParadaEntity.class);
            entity.setDescription(getRandomDescripcion());
            entity.getEventos().add(eventosData.get(0));

            em.persist(entity);
            data.add(entity);
        }
    }

    @Test
    public void createParadaTest() {
        try {
            ParadaEntity entity = factory.manufacturePojo(ParadaEntity.class);
            entity.setDescription(getRandomDescripcion());
            ParadaEntity created = paradaLogic.createParada(entity);

            ParadaEntity result = em.find(ParadaEntity.class, created.getId());

            Assert.assertNotNull(result);
            Assert.assertEquals(entity.getId(), result.getId());
            Assert.assertEquals(entity.getName(), result.getName());
            Assert.assertEquals(entity.getDescripcion(), result.getDescripcion());

        } catch (BusinesLogicException ex) {
            Assert.fail(ex.getLocalizedMessage());
        }
    }

    @Test
    public void getParadasTest() {
        List<ParadaEntity> resultList = paradaLogic.getParadas();
        List<ParadaEntity> expectedList = em.createQuery("SELECT u from ParadaEntity u").getResultList();
        Assert.assertEquals(expectedList.size(), resultList.size());
        for (ParadaEntity expected : expectedList) {
            boolean found = false;
            for (ParadaEntity result : resultList) {
                if (result.getId().equals(expected.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void getParadaTest() {
        try{
        ParadaEntity result = paradaLogic.getParada(data.get(0).getId());
        ParadaEntity expected = em.find(ParadaEntity.class, data.get(0).getId());

        Assert.assertNotNull(expected);
        Assert.assertNotNull(result);
        Assert.assertEquals(expected.getId(), result.getId());
        Assert.assertEquals(expected.getName(), result.getName());
        Assert.assertEquals(expected.getDescripcion(), result.getDescripcion());
        }
        catch(Exception e){
            Assert.fail("No debería entrar en excepción");
        }
        try{
            ParadaEntity result = factory.manufacturePojo(ParadaEntity.class);
            paradaLogic.getParada(result.getId());
            Assert.fail("Debería generar excepcíon");
        }
        catch(Exception e){

        }

    }

    @Test
    public void deleteParadaTest() {
        ParadaEntity entity = data.get(1);
        paradaLogic.deleteParada(entity.getId());
        ParadaEntity deleted = em.find(ParadaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    @Test
    public void updateParadaTest() {
        try {
            ParadaEntity entity = data.get(0);
            ParadaEntity pojoEntity = factory.manufacturePojo(ParadaEntity.class);
            pojoEntity.setId(entity.getId());

            paradaLogic.updateParada(pojoEntity);

            ParadaEntity resp = em.find(ParadaEntity.class, entity.getId());

            Assert.assertEquals(pojoEntity.getId(), resp.getId());
            Assert.assertEquals(pojoEntity.getName(), resp.getName());
            Assert.assertEquals(pojoEntity.getDescripcion(), resp.getDescripcion());

        } catch (BusinesLogicException ex) {
            Assert.fail(ex.getLocalizedMessage());
        }
    }

    @Test
    public void addGetRemoveEventoTest(){
         EventoEntity evento= eventosData.get(2);
        ParadaEntity parada = data.get(0);
         String fechaPI = "2016-02-12";
        String fechaPF = "2019-02-12";
        String fechaEI = "2016-02-25";
        String fechaEF = "2017-11-12";
        DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaParadaI;
        Date fechaParadaF;
        Date fechaEventoI;
        Date fechaEventoF;
        try {
            fechaParadaI = formato.parse(fechaPI);
            fechaParadaF = formato.parse(fechaPF);
            fechaEventoI = formato.parse(fechaEI);
            fechaEventoF = formato.parse(fechaEF);
            parada.setFechaI(fechaParadaI);
            parada.setFechaF(fechaParadaF);
            evento.setFechaInicio(fechaEventoI);
            evento.setFechaFin(fechaEventoF);
            paradaPersiste.update(parada);
            eventoPersiste.update(evento);
        try{

        EventoEntity eventoR = paradaLogic.addEvento(evento.getId(), parada.getId());
        EventoEntity evento2 = paradaLogic.getEvento(parada.getId(), eventoR.getId());
        Assert.assertNotNull(eventoR);
        Assert.assertEquals(eventoR.getId(),evento.getId());
        Assert.assertNotNull(evento2);
        Assert.assertEquals(eventoR.getId(),evento2.getId());
        paradaLogic.removeEvento(eventoR.getId(), parada.getId());
        try{
        eventoR = paradaLogic.getEvento(parada.getId(), eventoR.getId());
        }
        catch(Exception ex){
        }
        }
        catch(Exception e){
            Assert.fail(e.getMessage());
        }
        }
        catch(Exception e){

        }
    }


    public String getRandomDescripcion() {
        return " random descripcion";
    }

}
