package co.edu.uniandes.csw.unitrip.ejbs;

import co.edu.uniandes.csw.unitrip.api.IExperienciaLogic;
import co.edu.uniandes.csw.unitrip.entities.ExperienciaEntity;
import co.edu.uniandes.csw.unitrip.exceptions.BusinesLogicException;
import co.edu.uniandes.csw.unitrip.persistence.ExperienciaPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;


@Stateless
public class ExperienciaLogic implements IExperienciaLogic{
@Inject
    private ExperienciaPersistence persistence;

    @Override
    public List<ExperienciaEntity> getExperiencias() {
        return persistence.findAll();
    }

    @Override
    public ExperienciaEntity getExperiencia(Long id) throws BusinesLogicException {
        ExperienciaEntity experiencia = persistence.find(id);
        if (experiencia == null) {
            throw new BusinesLogicException("No hay experiencia con tal ID");
        }
        return experiencia;
    }

    public ExperienciaEntity createExperiencia(ExperienciaEntity entity) {
        persistence.create(entity);
        return entity;
    }

    public ExperienciaEntity updateExperiencia(ExperienciaEntity entity) {
        ExperienciaEntity newEntity = entity;
        return persistence.update(newEntity);
    }

    @Override
    public void deleteExperiencia(Long id) {
        persistence.delete(id);
    }

    
}
