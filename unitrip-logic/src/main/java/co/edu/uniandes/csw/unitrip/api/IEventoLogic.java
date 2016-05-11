/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.unitrip.api;

/**
 *
 * @author l.castro12
 */
import co.edu.uniandes.csw.unitrip.entities.EventoEntity;
import co.edu.uniandes.csw.unitrip.exceptions.BusinesLogicException;
import java.util.List;

public interface IEventoLogic {

    public List<EventoEntity> getEventos();

    public EventoEntity getEvento(Long id) throws BusinesLogicException;

    public EventoEntity createEvento(EventoEntity entity);

    public EventoEntity updateEvento(EventoEntity entity);

    public void deleteEvento(Long id);

    public List<EventoEntity> getEventosDeCiudad(String ciudad);

}
