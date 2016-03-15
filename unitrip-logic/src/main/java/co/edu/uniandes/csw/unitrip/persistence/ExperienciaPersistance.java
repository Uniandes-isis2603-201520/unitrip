package co.edu.uniandes.csw.unitrip.persistence;

import co.edu.uniandes.csw.unitrip.entities.ExperienciaEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public interface ExperienciaPersistance {

     @PersistenceContext(unitName = "ExperienciaStorePU")
    protected EntityManager em;

    public ExperienciaEntity find(Long id) {
        return em.find(ExperienciaEntity.class, id);
    }

    public List<ExperienciaEntity> findAll() {
        Query q;
        q = em.createQuery("select u from BookEntity u");
        return q.getResultList();
    }

    public ExperienciaEntity create(ExperienciaEntity entity) {
        em.persist(entity);
        return entity;
    }

    public ExperienciaEntity update(ExperienciaEntity entity) {
        return em.merge(entity);
    }

    public void delete(Long id) {
        ExperienciaEntity entity = em.find(ExperienciaEntity.class, id);
        em.remove(entity);
    }
    
}