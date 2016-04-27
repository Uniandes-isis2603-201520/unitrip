package co.edu.uniandes.csw.unitrip.persistence;

import co.edu.uniandes.csw.unitrip.entities.ExperienciaEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ANDRES
 */
@Stateless
public class ExperienciaPersistence {

    @PersistenceContext(unitName = "UnitripPU")
    protected EntityManager em;

    /**
     *
     * @param id
     * @return
     */
    public ExperienciaEntity find(Long id) {
        return em.find(ExperienciaEntity.class, id);
    }

    /**
     *
     * @return
     */
    public List<ExperienciaEntity> findAll() {
        Query q;
        q = em.createQuery("select u from ExperienciaEntity u");
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
