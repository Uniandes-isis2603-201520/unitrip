
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.unitrip.ejbs;

import co.edu.uniandes.csw.unitrip.api.IViajeroLogic;
import co.edu.uniandes.csw.unitrip.entities.ViajeEntity;
import co.edu.uniandes.csw.unitrip.entities.ViajeroEntity;
import co.edu.uniandes.csw.unitrip.exceptions.BusinesLogicException;
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
    public void removeViaje( Long viajeroId, Long viajeId) {
        ViajeroEntity viajeroEntity = persistence.find(viajeroId); //encuentra el viajero
        ViajeEntity  viajeEntity = viajePersistence.find(viajeId);
        if(viajeEntity == null)
            throw new IllegalArgumentException("El viaje no existe");

        viajeroEntity.getViajes().remove(viajeEntity);
        viajePersistence.delete(viajeId);
    }

    @Override
    public List<ViajeEntity> replaceViajes(List<ViajeEntity> viajes, Long viajeroId) {
        ViajeroEntity viajeroEntity = persistence.find(viajeroId);
        List<ViajeEntity> viajeList = viajePersistence.findAll();
        viajeroEntity.setViajes(viajes);
        return viajeroEntity.getViajes();
    }

    @Override
    public ViajeroEntity createViaje(ViajeEntity entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

}
