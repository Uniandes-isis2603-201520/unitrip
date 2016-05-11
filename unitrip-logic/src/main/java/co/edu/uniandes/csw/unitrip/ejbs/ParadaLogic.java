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

    private static final Logger LOGGER = Logger.getLogger(ParadaLogic.class.getName());
    @Inject
    private ParadaPersistence persistence;
    private static final String EVENTO_NO_EXISTE = "El evento no existe";
    @Inject
    private EventoPersistence eventoPersistence;

    @Override
    public List<ParadaEntity> getParadas() {
        LOGGER.info("Inicia proceso de consultar todos las paradas ");
        List<ParadaEntity> paradas = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todas las paradas");
        return paradas;
    }

    @Override
    public ParadaEntity getParada(Long id) throws BusinesLogicException{
        LOGGER.log(Level.INFO, "Inicia proceso de consultar parada con id={0}", id);
        ParadaEntity parada = persistence.find(id);
        if (parada == null) {
            LOGGER.log(Level.SEVERE, "La parada con el id {0} no existe", id);
            throw new BusinesLogicException("La parada solicitada ( " + id+" ) no existe");
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar parada con id={0}", id);
        return parada;
    }

    @Override
    public ParadaEntity createParada(ParadaEntity entity) throws BusinesLogicException {

        LOGGER.info("Inicia proceso de creación de parada");
        if (!validateDescripcion(entity.getDescripcion())) {
            throw new BusinesLogicException("La descripcion es inválida");
        }
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de parada");
        return entity;
    }

    @Override
    public ParadaEntity updateParada(ParadaEntity entity) throws BusinesLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar parada con id={0}", entity.getId());
        if (!validateDescripcion(entity.getDescripcion())) {
            throw new BusinesLogicException("La descripcion es inválido");
        }
        ParadaEntity newEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar parada con id={0}", entity.getId());
        return newEntity;
    }

    @Override
    public void deleteParada(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar parada con id={0}", id);
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar parada con id={0}", id);
    }

    @Override
    public List<EventoEntity> getEventos(Long paradaId)throws BusinesLogicException {
        return getParada(paradaId).getEventos();
    }

    @Override
    public EventoEntity getEvento(Long paradaId, Long eventoId)throws BusinesLogicException {
        List<EventoEntity> eventos = getParada(paradaId).getEventos();
        EventoEntity eventoEntity = eventoPersistence.find(eventoId);
        if (eventoEntity == null) {
            throw new IllegalArgumentException(EVENTO_NO_EXISTE);
        }
        int index = eventos.indexOf(eventoEntity);
        if (index >= 0) {
            return eventos.get(index);
        }
        throw new IllegalArgumentException("El evento no está asociado al parada");
    }

    @Override
    public EventoEntity addEvento(Long eventoId, Long paradaId) throws BusinesLogicException {
        ParadaEntity paradaEntity = getParada(paradaId);
        EventoEntity eventoEntity = eventoPersistence.find(eventoId);
        if (eventoEntity == null) {
            throw new IllegalArgumentException(EVENTO_NO_EXISTE);
        }
        Date paradaFechaI = paradaEntity.getFechaI();
        Date paradaFechaF = paradaEntity.getFechaF();
        Date eventoFechaI = eventoEntity.getFechaInicio();
        Date eventoFechaF = eventoEntity.getFechaFin();

        if(paradaFechaI.compareTo(eventoFechaI) > 0){
            throw new BusinesLogicException("La fecha de inicio del evento debe ser igual o despues a la fecha de inicio de parada");
        }
        if(paradaFechaF.compareTo(eventoFechaF) < 1){
            throw new BusinesLogicException("la fecha final del evento debe ser igual o antes de la fecha final de la parada");
        }

        paradaEntity.getEventos().add(eventoEntity);
        return eventoEntity;
    }

    @Override
    public void removeEvento(Long eventoId, Long paradaId) throws BusinesLogicException{
        ParadaEntity paradaEntity = getParada(paradaId);
        EventoEntity eventoEntity = eventoPersistence.find(eventoId);
        if (eventoEntity == null) {

            throw new IllegalArgumentException(EVENTO_NO_EXISTE);
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
        return !(desc == null || desc.isEmpty());
    }

    private boolean eventoDentroRango(Date fechaInicioEvento, Date fechaInicioParada) {
        if (fechaInicioEvento != null && fechaInicioParada != null) {
            if (inicioParadaBeforeInicioEvento(fechaInicioEvento, fechaInicioParada)) {
                return true;
            }
        }
        return false;
    }

    private boolean inicioParadaBeforeInicioEvento (Date fechaInicioEvento, Date fechaInicioParada){
        return fechaInicioParada.before(fechaInicioEvento);
    }

    /**
     * @Override public ParadaEntity createParada(ParadaEntity entity) {
     * persistence.create(entity); return entity; }
     *
     * @Override public ParadaEntity updateParada(ParadaEntity entity) {
     * ParadaEntity newEntity = entity; return persistence.update(newEntity); }
     *
     * @Override public void deleteParada(Long id) { persistence.delete(id); }
     *
     */
}
