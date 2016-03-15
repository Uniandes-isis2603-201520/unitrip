package co.edu.uniandes.csw.unitrip.api;

import co.edu.uniandes.csw.unitrip.entities.ExperienciaEntity;


import java.util.List;

public interface IIninerarioLogic {

    public List<ExperienciaEntity> getExperiencias();

    public ExperienciaEntity getExperiencia(Long id) throws BusinessLogicException;

    public ExperienciaEntity createExperiencia(ExperienciaEntity entity);

    public ExperienciaEntity updateExperiencia(ExperienciaEntity entity);

    public void deleteExperiencia(Long id);
}