/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.unitrip.ejbs;

/**
 *
 * @author l.castro12
 */
import co.edu.uniandes.csw.unitrip.api.IEventoLogic;
import co.edu.uniandes.csw.unitrip.entities.EventoEntity;
import co.edu.uniandes.csw.unitrip.exceptions.BusinesLogicException;
import co.edu.uniandes.csw.unitrip.persistence.EventoPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class EventoLogic implements IEventoLogic {

    @Inject
    private EventoPersistence persistence;


    @Override
    public List<EventoEntity> getEventos() {
        return persistence.findAll();
    }

    @Override
    public EventoEntity getEvento(Long id) throws BusinesLogicException {
        EventoEntity evento = persistence.find(id);
        if (evento == null) {
            throw new BusinesLogicException("There's no event with requested id");
        }
        return evento;
    }

    @Override
    public EventoEntity createEvento(EventoEntity entity) {
        persistence.create(entity);
        return entity;
    }

    @Override
    public EventoEntity updateEvento(EventoEntity entity) {
        EventoEntity newEntity = entity;
        return persistence.update(newEntity);
    }

    @Override
    public void deleteEvento(Long id) {
        persistence.delete(id);
    }



}