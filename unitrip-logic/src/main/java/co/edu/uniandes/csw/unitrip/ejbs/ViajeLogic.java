/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.unitrip.ejbs;

import co.edu.uniandes.csw.unitrip.api.IViajesLogic;
import co.edu.uniandes.csw.unitrip.entities.ItinerarioEntity;
import co.edu.uniandes.csw.unitrip.entities.ViajeEntity;
import co.edu.uniandes.csw.unitrip.exceptions.BusinesLogicException;
import co.edu.uniandes.csw.unitrip.persistence.ItinerarioPersistence;
import co.edu.uniandes.csw.unitrip.persistence.ViajePersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ANDRES
 */
@Stateless
public class ViajeLogic implements IViajesLogic {

    @Inject
    private ViajePersistence persistence;

    @Inject
    private ItinerarioPersistence itinerarioPersistence;

    @Override
    public List<ViajeEntity> getViajes() {
        return persistence.findAll();
    }

    @Override
    public ViajeEntity getViaje(Long id) throws BusinesLogicException {
        ViajeEntity viaje = persistence.find(id);
        if (viaje == null) {
            throw new BusinesLogicException("No hay viaje con tal ID");
        }
        return viaje;
    }

    public ViajeEntity createViaje(ViajeEntity entity) {
        persistence.create(entity);
        return entity;
    }

    public ViajeEntity updateViaje(ViajeEntity entity) {
        ViajeEntity newEntity = entity;
        return persistence.update(newEntity);
    }

    @Override
    public void deleteViaje(Long id) {
        persistence.delete(id);
    }

    @Override
    public List<ItinerarioEntity> getItinerarios(Long viajeId) {
        return persistence.find(viajeId).getItinerarios();
    }

    @Override
    public ItinerarioEntity getItinerario(Long viajeId, Long itinerarioId) {
        try {
            List<ItinerarioEntity> itinerarios = persistence.find(viajeId).getItinerarios();
            ItinerarioEntity itinerarioEntity = new ItinerarioEntity();
            itinerarioEntity.setId(itinerarioId);
            int index = itinerarios.indexOf(itinerarioEntity);
            if (index >= 0) {
                return itinerarios.get(index);
            }
            
        } catch (BusinesLogicException ex) {
            Logger.getLogger(ViajeLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ItinerarioEntity addItinerario(Long itiId, Long viajeId) {
        ViajeEntity viajeEntity = persistence.find(viajeId);
        ItinerarioEntity itiEntity = itinerarioPersistence.find(itiId);
        viajeEntity.getItinerarios().add(itiEntity);
        return itiEntity;
    }

    @Override
    public void removeItinerario(Long itinerarioId, Long viajeId) {
        try {
            ViajeEntity viajeEntity = persistence.find(viajeId);
            ItinerarioEntity itinerarioEntity = new ItinerarioEntity();
            itinerarioEntity.setId(itinerarioId);
            viajeEntity.getItinerarios().remove(itinerarioEntity);
        } catch (BusinesLogicException ex) {
            Logger.getLogger(ViajeLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<ItinerarioEntity> replaceItinerarios(List<ItinerarioEntity> itinerarios, Long viajeId) {
        ViajeEntity viajeEntity = persistence.find(viajeId);
        List<ItinerarioEntity> itinerarioList = itinerarioPersistence.findAll();
        viajeEntity.setItinerarios(itinerarios);
        return viajeEntity.getItinerarios();
    }

    @Override
    public ViajeEntity createBook(ViajeEntity entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ViajeEntity updateBook(ViajeEntity entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
