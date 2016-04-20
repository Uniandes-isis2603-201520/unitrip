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
import org.junit.Assert;
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
 */
@RunWith(Arquillian.class)

public class ExperienciaPersistenceTest {

    public ExperienciaPersistenceTest() {
    }

    @Inject
    private ExperienciaPersistence experienciaPersistence;

    @PersistenceContext
    private EntityManager em;

    private final PodamFactory factory = new PodamFactoryImpl();
    @Inject
    UserTransaction utx;
    private List<ExperienciaEntity> data = new ArrayList<>();

    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ExperienciaEntity.class.getPackage())
                .addPackage(ExperienciaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
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

    public void clearData()
    {
        em.createQuery("delete from ExperienciaEntity").executeUpdate();
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
        ExperienciaEntity resultado = experienciaPersistence.create(entidadExp);

        Assert.assertNotNull(resultado);
        ExperienciaEntity entidad = em.find(ExperienciaEntity.class, resultado.getId());
        Assert.assertEquals(entidadExp.getName(), entidad.getName());
        Assert.assertEquals(entidadExp.getDescripcion(), entidad.getDescripcion());
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

        }
        Assert.assertTrue(encontro);
    }
    }


    @Test
    public void getExperienciaTest() {
        ExperienciaEntity entid = data.get(0);
        ExperienciaEntity nuevaEn = experienciaPersistence.find(entid.getId());
        Assert.assertNotNull(nuevaEn);
        Assert.assertEquals(entid.getName(), nuevaEn.getName());
    }


    @Test
    public void deleteExperienciaTest() {
        ExperienciaEntity entity = data.get(0);
        experienciaPersistence.delete(entity.getId());
        ExperienciaEntity elim = em.find(ExperienciaEntity.class, entity.getId());
        Assert.assertNull(elim);
    }

    @Test
    public void updateExperienciaTest() {
        ExperienciaEntity entity = data.get(0);
        ExperienciaEntity nueva = factory.manufacturePojo(ExperienciaEntity.class);

        nueva.setId(entity.getId());

        experienciaPersistence.update(nueva);

        ExperienciaEntity resp = em.find(ExperienciaEntity.class, entity.getId());

        Assert.assertEquals(nueva.getName(), resp.getName());
    }

}
