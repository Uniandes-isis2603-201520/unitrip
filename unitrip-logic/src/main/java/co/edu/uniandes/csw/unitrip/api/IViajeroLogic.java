/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.unitrip.api;

import co.edu.uniandes.csw.unitrip.entities.ExperienciaEntity;
import co.edu.uniandes.csw.unitrip.entities.ViajeEntity;
import co.edu.uniandes.csw.unitrip.entities.ViajeroEntity;
import co.edu.uniandes.csw.unitrip.exceptions.BusinesLogicException;
import java.util.List;

/**
 *
 * @author ANDRES
 */
public interface IViajeroLogic {

    public List<ViajeroEntity> getViajeros();

    public ViajeroEntity getViajero(Long id) throws BusinesLogicException;

    public ViajeroEntity createViajero(ViajeroEntity entity);

    public ViajeroEntity updateViajero(ViajeroEntity entity);

    public void deleteViajero(Long id);

    public List<ViajeEntity> getViajes(Long viajeId);

    public ViajeEntity getViaje(Long viajeroId, Long viajeId);

    public ViajeEntity addViaje(Long viajeId, Long viajeroId);

    public void removeViaje(Long viajeroId, Long viajeId) throws BusinesLogicException;

    public List<ViajeEntity> replaceViajes(List<ViajeEntity> viajes, Long viajeroId);

    public ViajeroEntity updateViaje(ViajeEntity entity);
    
    public List<ExperienciaEntity> getExperiencias(Long viajeId);

    public ExperienciaEntity getExperiencia(Long viajeroId, Long experienciaId);

    public ExperienciaEntity addExperiencia(Long experienciaId, Long viajeroId);

    public void removeExperiencia(Long viajeroId, Long experienciaId) throws BusinesLogicException;

    public List<ExperienciaEntity> replaceExperiencias(List<ExperienciaEntity> experiencias, Long viajeroId);

    public ExperienciaEntity updateExperiencia(ExperienciaEntity entity);
    
}
