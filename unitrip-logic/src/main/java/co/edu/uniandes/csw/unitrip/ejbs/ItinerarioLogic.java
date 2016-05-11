package co.edu.uniandes.csw.unitrip.ejbs;

import co.edu.uniandes.csw.unitrip.api.IItinerarioLogic;
import co.edu.uniandes.csw.unitrip.entities.ParadaEntity;
import co.edu.uniandes.csw.unitrip.entities.ItinerarioEntity;
import co.edu.uniandes.csw.unitrip.exceptions.BusinesLogicException;
import co.edu.uniandes.csw.unitrip.persistence.ItinerarioPersistence;
import co.edu.uniandes.csw.unitrip.persistence.ParadaPersistence;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ItinerarioLogic implements IItinerarioLogic {

    private static final Logger LOGGER = Logger.getLogger(ItinerarioLogic.class.getName());
    @Inject
    private ItinerarioPersistence persistence;

    @Inject
    private ParadaPersistence paradaPersistence;

    @Override
    public List<ItinerarioEntity> getItinerarios() {
        LOGGER.info("Inicia proceso de consultar todos los Itinerarios");
        List<ItinerarioEntity> itinerarios = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los Itinerarios");
        return itinerarios;
    }

    @Override
    public ItinerarioEntity getItinerario(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar itinerario con id={0}", id);
        ItinerarioEntity itinerario = persistence.find(id);
        if (itinerario == null) {
            LOGGER.log(Level.SEVERE, "El itinerario con el id {0} no existe", id);
            throw new IllegalArgumentException("El itinerario solicitado no existe");
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar itinerario con id={0}", id);
        return itinerario;
    }

    @Override
    public ItinerarioEntity createItinerario(ItinerarioEntity entity) throws BusinesLogicException {
        LOGGER.info("Inicia proceso de creación de itinerario");
        if (!validateDescripcion(entity.getDescripcion())) {
            throw new BusinesLogicException("la descripcion es inválida");
        }
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de itinerario");
        return entity;
    }

    @Override
    public ItinerarioEntity updateItinerario(ItinerarioEntity entity) throws BusinesLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar libro con id={0}", entity.getId());
        if (!validateDescripcion(entity.getDescripcion())) {
            throw new BusinesLogicException("la descripcion no es valido");
        }
        ItinerarioEntity newEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar itinerario con id={0}", entity.getId());
        return newEntity;
    }

    @Override
    public void deleteItinerario(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar itinerario con id={0}", id);
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar itinerario con id={0}", id);
    }

    @Override
    public List<ParadaEntity> getParadas(Long itinerarioId) {
        return persistence.find(itinerarioId).getParadas();
    }

    @Override
    public ParadaEntity getParada(Long itinerarioId, Long paradaId) {
        List<ParadaEntity> paradas = persistence.find(itinerarioId).getParadas();
        ParadaEntity paradaEntity = new ParadaEntity();
        paradaEntity.setId(paradaId);
        int index = paradas.indexOf(paradaEntity);
        if (index >= 0) {
            return paradas.get(index);
        }
        return null;
    }

    @Override
    public ParadaEntity addParada(Long paradaId, Long itinerarioId) throws BusinesLogicException {
        ItinerarioEntity itinerarioEntity = persistence.find(itinerarioId);
        ParadaEntity paradaEntity = paradaPersistence.find(paradaId);
        if (paradaEntity == null) {
            throw new IllegalArgumentException("La parada no existe");
        }
        Date fechaIParada = paradaEntity.getFechaI();
        Date fechaFParada = paradaEntity.getFechaF();
        Date fechaIItinerario = itinerarioEntity.getFechaI();
        Date fechaFItinerario = itinerarioEntity.getFechaF();

        if(fechaIItinerario.compareTo(fechaIParada) > 0){
            throw new BusinesLogicException("La fecha de inicio del itinerario no es antes o igual de la de inicio de la parada");
        }
        if(fechaFItinerario.compareTo(fechaFParada) < 1){
            throw new BusinesLogicException("La fecha de fin de itineraro no es despues o igual a la fecha fin de parada");
        }
        
        paradaEntity.setItinerario(itinerarioEntity);
        itinerarioEntity.getParadas().add(paradaEntity);
        return paradaEntity;

    }

    @Override
    public void removeParada(Long IdParada, Long itinerarioId) {
        ItinerarioEntity itinerarioEntity= persistence.find(itinerarioId);
        ParadaEntity paradaEntity;
        paradaEntity = paradaPersistence.find(IdParada);
        if(paradaEntity == null){
            throw new IllegalArgumentException("La parada no existe");
        }
        if(paradaEntity.getItinerario().getId() != itinerarioId){
            throw new IllegalArgumentException("La parada no pertnece al itinerario");
        }
        itinerarioEntity.getParadas().remove(paradaEntity);
        paradaPersistence.delete(IdParada);
    }

    @Override
    public List<ParadaEntity> replaceParadas(List<ParadaEntity> paradas, Long itinerarioId) throws BusinesLogicException {
        ItinerarioEntity itinerarioEntity = persistence.find(itinerarioId);
        itinerarioEntity.setParadas(paradas);
        return itinerarioEntity.getParadas();
    }

    private boolean validateDescripcion(String desc) {
        return !(desc == null || desc.isEmpty());
    }

}
