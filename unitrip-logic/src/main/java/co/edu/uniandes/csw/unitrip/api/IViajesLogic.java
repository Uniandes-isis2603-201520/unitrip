/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.unitrip.api;

import co.edu.uniandes.csw.unitrip.entities.ItinerarioEntity;
import co.edu.uniandes.csw.unitrip.entities.ViajeEntity;
import co.edu.uniandes.csw.unitrip.exceptions.BusinesLogicException;
import java.util.List;

/**
 *
 * @author ANDRES
 */
public interface IViajesLogic {
    
    public List<ViajeEntity> getViajes();

    public ViajeEntity getViaje(Long id) throws BusinesLogicException;

    public ViajeEntity createViaje(ViajeEntity entity);

    public ViajeEntity updateViaje(ViajeEntity entity);

    public void deleteViaje(Long id);

    public List<ItinerarioEntity> getItinerarios(Long viajeId);

    public ItinerarioEntity getItinerario(Long viajeId, Long itinerarioId);

    public ItinerarioEntity addItinerario(Long itinerarioId, Long viajeId);

    public void removeItinerario(Long itinerarioId, Long viajeId);

    public List<ItinerarioEntity> replaceItinerarios(List<ItinerarioEntity> itinerarios, Long viajeId);
    
}
