/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.unitrip.persistence;

/**
 *
 * @author l.castro12
 */
import co.edu.uniandes.csw.unitrip.entities.EventoEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class EventoPersistence {

    @PersistenceContext(unitName = "UnitripPU")
    protected EntityManager em;

    public EventoEntity find(Long id) {
        return em.find(EventoEntity.class, id);
    }

    public List<EventoEntity> findAll() {
        Query q = em.createQuery("select u from EventoEntity u");
        return q.getResultList();
    }

    public EventoEntity create(EventoEntity entity) {
        em.persist(entity);
        return entity;
    }

    public EventoEntity update(EventoEntity entity) {
        return em.merge(entity);
    }

    public void delete(Long id) {
        EventoEntity entity = em.find(EventoEntity.class, id);
        em.remove(entity);
    }
    // "SELECT c FROM Country c WHERE c.name = 'Canada'"
    public List<EventoEntity> findPorCiudad(String ciudad){
        Query q=em.createQuery("select u from EventoEntity u where u.ciudad=" +  "'" + ciudad +  "'");
        return q.getResultList();
    }
}
