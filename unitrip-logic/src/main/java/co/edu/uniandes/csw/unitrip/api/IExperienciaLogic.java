package co.edu.uniandes.csw.unitrip.api;

import co.edu.uniandes.csw.unitrip.entities.ExperienciaEntity;
import co.edu.uniandes.csw.unitrip.exceptions.BusinesLogicException;

import java.util.List;

public interface IExperienciaLogic {

    public List<ExperienciaEntity> getExperiencias();

    public ExperienciaEntity getExperiencia(Long id) throws BusinesLogicException;

    public ExperienciaEntity createExperiencia(ExperienciaEntity entity);

    public ExperienciaEntity updateExperiencia(ExperienciaEntity entity);

    public void deleteExperiencia(Long id);
}
