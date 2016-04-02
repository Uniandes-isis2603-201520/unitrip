package co.edu.uniandes.csw.unitrip.persistence;

import co.edu.uniandes.csw.unitrip.entities.ItinerarioEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ItinerarioPersistence {

    @PersistenceContext(unitName = "UnitripPU")
    protected EntityManager em;

    public ItinerarioEntity find(Long id) {
        return em.find(ItinerarioEntity.class, id);
    }

    public List<ItinerarioEntity> findAll() {
        Query q = em.createQuery("select u from ItinerarioEntity u");
        return q.getResultList();
    }

    public ItinerarioEntity create(ItinerarioEntity entity) {
        em.persist(entity);
        return entity;
    }

    public ItinerarioEntity update(ItinerarioEntity entity) {
        return em.merge(entity);
    }

    public void delete(Long id) {
        ItinerarioEntity entity = em.find(ItinerarioEntity.class, id);
        em.remove(entity);
    }
}