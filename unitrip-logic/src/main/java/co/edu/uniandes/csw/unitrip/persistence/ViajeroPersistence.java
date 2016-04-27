/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.unitrip.persistence;

import co.edu.uniandes.csw.unitrip.entities.ViajeEntity;
import co.edu.uniandes.csw.unitrip.entities.ViajeroEntity;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author je.molano1498
 */
@Stateless
public class ViajeroPersistence {

    private static final Logger logger = Logger.getLogger(ViajePersistence.class.getName());

    @PersistenceContext(unitName = "UnitripPU")
    protected EntityManager em;

    public ViajeroEntity find(Long id) {
        return em.find(ViajeroEntity.class, id);
    }

    public List<ViajeroEntity> findAll() {
        Query q;
        logger.info("obteniendo viajeros");
        q = em.createQuery("select u from ViajeroEntity u");
        logger.info(q.getResultList().toString());
        return q.getResultList();
    }

    public ViajeroEntity create(ViajeroEntity entity) {
        logger.info("Creando un viajero nuevo");
        em.persist(entity);
        logger.info("viajero creado");
        return entity;
    }

    public ViajeroEntity update(ViajeroEntity entity) {
        return em.merge(entity);
    }

    public void delete(Long id) {
        ViajeroEntity entity = em.find(ViajeroEntity.class, id);
        em.remove(entity);
    }
}
