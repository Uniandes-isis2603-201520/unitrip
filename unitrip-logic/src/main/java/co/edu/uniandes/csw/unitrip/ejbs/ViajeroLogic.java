
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.unitrip.ejbs;

import co.edu.uniandes.csw.unitrip.api.IViajeroLogic;
import co.edu.uniandes.csw.unitrip.entities.ExperienciaEntity;
import co.edu.uniandes.csw.unitrip.entities.ViajeEntity;
import co.edu.uniandes.csw.unitrip.entities.ViajeroEntity;
import co.edu.uniandes.csw.unitrip.exceptions.BusinesLogicException;
import co.edu.uniandes.csw.unitrip.persistence.ExperienciaPersistence;
import co.edu.uniandes.csw.unitrip.persistence.ViajePersistence;
import co.edu.uniandes.csw.unitrip.persistence.ViajeroPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author je.molano1498
 */
@Stateless
public class ViajeroLogic implements IViajeroLogic {

    @Inject
    private ViajeroPersistence persistence;
    @Inject
    private ViajePersistence viajePersistence;
    @Inject
    private ExperienciaPersistence experienciaPersistence;

    @Override
    public List<ViajeroEntity> getViajeros() {
        return persistence.findAll();
    }

    @Override
    public ViajeroEntity getViajero(Long id) throws BusinesLogicException {
        ViajeroEntity viajero = persistence.find(id);
        if (viajero == null) {
            throw new BusinesLogicException("No hay viajero con tal ID");
        }
        return viajero;
    }

    @Override
    public ViajeroEntity createViajero(ViajeroEntity entity) {
        persistence.create(entity);
        return entity;
    }

    @Override
    public ViajeroEntity updateViajero(ViajeroEntity entity) {
        ViajeroEntity newEntity = entity;
        return persistence.update(newEntity);
    }

    @Override
    public void deleteViajero(Long id) {
        persistence.delete(id);
    }

    @Override
    public List<ViajeEntity> getViajes(Long viajeroId) {
        List<ViajeEntity> viajes =null;
        try {
            viajes= getViajero(viajeroId).getViajes();
        } catch (BusinesLogicException ex) {
            Logger.getLogger(ViajeroLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return viajes;
    }

    @Override
    public ViajeEntity getViaje(Long viajeroId, Long viajeId) {
        List<ViajeEntity> viajes = persistence.find(viajeroId).getViajes();
        ViajeEntity viajeEntity = new ViajeEntity();
        viajeEntity.setId(viajeId);
        int index = viajes.indexOf(viajeEntity);
        if (index >= 0) {
            return viajes.get(index);
        }
        return null;
    }

    @Override
    public void removeViaje( Long viajeroId, Long viajeId) throws BusinesLogicException{
        ViajeroEntity viajeroEntity = persistence.find(viajeroId); //encuentra el viajero
        ViajeEntity  viajeEntity = viajePersistence.find(viajeId);
        if(viajeEntity == null)
            throw new IllegalArgumentException("El viaje no existe");

        if (viajeEntity.getViajero().getId()!= viajeroId)
            throw new BusinesLogicException("El viaje no pertenece al viajero");


        viajeroEntity.getViajes().remove(viajeEntity);
        viajePersistence.delete(viajeId);
    }

    @Override
    public List<ViajeEntity> replaceViajes(List<ViajeEntity> viajes, Long viajeroId) {
        ViajeroEntity viajeroEntity = persistence.find(viajeroId);
        viajeroEntity.setViajes(viajes);
        return viajeroEntity.getViajes();
    }

    @Override
    public ViajeroEntity updateViaje(ViajeEntity entity) {
        return null;
    }

    @Override
    public ViajeEntity addViaje( Long viajeroId,Long viajeId) {
        ViajeroEntity viajeroEntity = persistence.find(viajeroId); // encuentra el viajero y lo crea como entity
        ViajeEntity viajeEntity = viajePersistence.find(viajeId);   // encuentra el viaje y lo crea como entity
        viajeEntity.setViajero(viajeroEntity); // al viaje le pone el viajero
        viajeroEntity.getViajes().add(viajeEntity); // a la lista de de viajes del viajero le agrega el viaje
        return viajeEntity;
    }
    
    @Override
    public List<ExperienciaEntity> getExperiencias(Long viajeroId) {
        List<ExperienciaEntity> experiencias =null;
        try {
            experiencias= getViajero(viajeroId).getExperiencias();
        } catch (BusinesLogicException ex) {
            Logger.getLogger(ViajeroLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return experiencias;
    }

    @Override
    public ExperienciaEntity getExperiencia(Long viajeroId, Long experienciaId) {
        List<ExperienciaEntity> experiencias = persistence.find(viajeroId).getExperiencias();
        ExperienciaEntity experienciaEntity = new ExperienciaEntity();
        experienciaEntity.setId(experienciaId);
        int index = experiencias.indexOf(experienciaEntity);
        if (index >= 0) {
            return experiencias.get(index);
        }
        return null;
    }

    @Override
    public void removeExperiencia( Long viajeroId, Long experienciaId) throws BusinesLogicException{
        ViajeroEntity viajeroEntity = persistence.find(viajeroId); //encuentra el viajero
        ExperienciaEntity  experienciaEntity = experienciaPersistence.find(experienciaId);
        if(experienciaEntity == null)
        {
            throw new IllegalArgumentException("La experiencia no existe");
        }

        if (experienciaEntity.getViajero().getId()!= viajeroId)
        {
            throw new BusinesLogicException("La experiencia no pertenece al viajero");
        }


        viajeroEntity.getExperiencias().remove(experienciaEntity);
        viajePersistence.delete(experienciaId);
    }

    @Override
    public List<ExperienciaEntity> replaceExperiencias(List<ExperienciaEntity> experiencias, Long viajeroId) {
        ViajeroEntity viajeroEntity = persistence.find(viajeroId);
        viajeroEntity.setExperiencias(experiencias);
        return viajeroEntity.getExperiencias();
    }

    @Override
    public ExperienciaEntity updateExperiencia(ExperienciaEntity entity) {
        return null;
    }

    @Override
    public ExperienciaEntity addExperiencia( Long viajeroId, Long experienciaId) {
        ViajeroEntity viajeroEntity = persistence.find(viajeroId); // encuentra el viajero y lo crea como entity
        ExperienciaEntity experienciaEntity = experienciaPersistence.find(experienciaId);   // encuentra el viaje y lo crea como entity
        experienciaEntity.setViajero(viajeroEntity); // al viaje le pone el viajero
        viajeroEntity.getExperiencias().add(experienciaEntity); // a la lista de de viajes del viajero le agrega el viaje
        return experienciaEntity;
    }

}
