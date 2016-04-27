/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.unitrip.persistence;

import co.edu.uniandes.csw.unitrip.entities.EventoEntity;
import co.edu.uniandes.csw.unitrip.entities.ParadaEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author l.castro12
 */
@Stateless
public class ParadaPersistence {

    @PersistenceContext(unitName = "UnitripPU")
    protected EntityManager em;

    public ParadaEntity find(Long id) {
        return em.find(ParadaEntity.class, id);
    }

    public List<ParadaEntity> findAll() {
        Query q = em.createQuery("select u from ParadaEntity u");
        return q.getResultList();
    }

    public ParadaEntity create(ParadaEntity entity) {
        em.persist(entity);
        return entity;
    }

    public ParadaEntity update(ParadaEntity entity) {
        return em.merge(entity);
    }

    public void delete(Long id) {
        ParadaEntity entity = em.find(ParadaEntity.class, id);
        em.remove(entity);
    }
}
