/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.unitrip.ejbs;

import co.edu.uniandes.csw.unitrip.api.IParadaLogic;
import co.edu.uniandes.csw.unitrip.entities.EventoEntity;
import co.edu.uniandes.csw.unitrip.entities.ParadaEntity;
import co.edu.uniandes.csw.unitrip.exceptions.BusinesLogicException;
import co.edu.uniandes.csw.unitrip.persistence.EventoPersistence;
import co.edu.uniandes.csw.unitrip.persistence.ParadaPersistence;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 */
@Stateless
public class ParadaLogic implements IParadaLogic {

     private static final Logger logger = Logger.getLogger(ParadaLogic.class.getName());
    @Inject
    private ParadaPersistence persistence;

    @Inject
    private EventoPersistence eventoPersistence;

    @Override
    public List<ParadaEntity> getParadas() {
        logger.info("Inicia proceso de consultar todos las paradas ");
        List<ParadaEntity> paradas = persistence.findAll();
        logger.info("Termina proceso de consultar todos las paradas");
        return paradas;
    }

    @Override
    public ParadaEntity getParada(Long id) {
        logger.log(Level.INFO, "Inicia proceso de consultar parada con id={0}", id);
        ParadaEntity parada = persistence.find(id);
        if (parada == null) {
            logger.log(Level.SEVERE, "La parada con el id {0} no existe", id);
            throw new IllegalArgumentException("La parada solicitado no existe");
        }
        logger.log(Level.INFO, "Termina proceso de consultar parada con id={0}", id);
        return parada;
    }

    @Override
    public ParadaEntity createParada(ParadaEntity entity) throws BusinesLogicException {
        logger.info("Inicia proceso de creación de parada");
        if (!validateDescripcion(entity.getDescripcion())) {
            throw new BusinesLogicException("La descripcion es inválida");
        }
        persistence.create(entity);
        logger.info("Termina proceso de creación de parada");
        return entity;
    }

    @Override
    public ParadaEntity updateParada(ParadaEntity entity) throws BusinesLogicException {
        logger.log(Level.INFO, "Inicia proceso de actualizar parada con id={0}", entity.getId());
        if (!validateDescripcion(entity.getDescripcion())) {
            throw new BusinesLogicException("La descripcion es inválido");
        }
        ParadaEntity newEntity = persistence.update(entity);
        logger.log(Level.INFO, "Termina proceso de actualizar parada con id={0}", entity.getId());
        return newEntity;
    }

    @Override
    public void deleteParada(Long id) {
        logger.log(Level.INFO, "Inicia proceso de borrar parada con id={0}", id);
        persistence.delete(id);
        logger.log(Level.INFO, "Termina proceso de borrar parada con id={0}", id);
    }

    @Override
    public List<EventoEntity> getEventos(Long paradaId) {
        return getParada(paradaId).getEventos();
    }

    @Override
    public EventoEntity getEvento(Long paradaId, Long eventoId) {
        List<EventoEntity> eventos = getParada(paradaId).getEventos();
        EventoEntity eventoEntity = eventoPersistence.find(eventoId);
        if (eventoEntity == null) {
            throw new IllegalArgumentException("El evento no existe");
        }
        int index = eventos.indexOf(eventoEntity);
        if (index >= 0) {
            return eventos.get(index);
        }
        throw new IllegalArgumentException("El autor no está asociado al parada");
    }

    @Override
    public EventoEntity addEvento(Long eventoId, Long paradaId) throws BusinesLogicException {
        ParadaEntity paradaEntity = getParada(paradaId);
        EventoEntity eventoEntity = eventoPersistence.find(eventoId);
        if (eventoEntity == null) {
            throw new IllegalArgumentException("El evento no existe");
        }
        if (!eventoDentroRango(eventoEntity.getFechaInicio(), paradaEntity.getFechaI())) {
            throw new BusinesLogicException(" el evento no puede comenzar antes de la parada");
        }
        paradaEntity.getEventos().add(eventoEntity);
        return eventoEntity;
    }

    @Override
    public void removeEvento(Long eventoId, Long paradaId) {
        ParadaEntity paradaEntity = getParada(paradaId);
        EventoEntity eventoEntity = eventoPersistence.find(eventoId);
        if (eventoEntity == null) {
            throw new IllegalArgumentException("El evento no existe");
        }
        paradaEntity.getEventos().remove(eventoEntity);
    }

    @Override
    public List<EventoEntity> replaceEventos(List<EventoEntity> eventos, Long paradaId) throws BusinesLogicException {
        ParadaEntity paradaEntity = getParada(paradaId);
        paradaEntity.setEventos(eventos);
        for (EventoEntity evento : eventos) {
            if (!eventoDentroRango(evento.getFechaInicio(), paradaEntity.getFechaI())) {
                throw new BusinesLogicException(" el evento no puede comenzar antes de la parada");
            }
        }
        return paradaEntity.getEventos();
    }

    private boolean validateDescripcion(String desc) {
        if (desc == null || desc.isEmpty()) {
            return false;
        }
        return true;
    }

    private boolean eventoDentroRango(Date fechaInicioEvento, Date fechaInicioParada) {
        if (fechaInicioEvento != null && fechaInicioParada != null) {
            if (fechaInicioParada.before(fechaInicioEvento)) {
                return true;
            }
        }
        return false;
    }

    /**
    @Override
    public ParadaEntity createParada(ParadaEntity entity) {
        persistence.create(entity);
        return entity;
    }

    @Override
    public ParadaEntity updateParada(ParadaEntity entity) {
        ParadaEntity newEntity = entity;
        return persistence.update(newEntity);
    }

    @Override
    public void deleteParada(Long id) {
        persistence.delete(id);
    }
    **/
}

