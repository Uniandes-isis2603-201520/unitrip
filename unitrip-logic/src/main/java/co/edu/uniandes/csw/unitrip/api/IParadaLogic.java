/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.unitrip.api;

import co.edu.uniandes.csw.unitrip.entities.EventoEntity;
import co.edu.uniandes.csw.unitrip.entities.ParadaEntity;
import co.edu.uniandes.csw.unitrip.exceptions.BusinesLogicException;
import java.util.List;

/**
 *
 * @author l.castro12
 */
public interface IParadaLogic {

    public List<ParadaEntity> getParadas();

    public ParadaEntity getParada(Long id) throws BusinesLogicException;

    public ParadaEntity createParada(ParadaEntity entity) throws BusinesLogicException;

    public ParadaEntity updateParada(ParadaEntity entity) throws BusinesLogicException;

    public EventoEntity addEvento(Long eventoId, Long paradaId) throws BusinesLogicException;

    public void removeEvento(Long eventoId, Long paradaId)throws BusinesLogicException;

    public List<EventoEntity> getEventos(Long paradaId)throws BusinesLogicException;

    public EventoEntity getEvento(Long paradaId, Long eventoId)throws BusinesLogicException;

    public List<EventoEntity> replaceEventos(List<EventoEntity> eventos, Long paradaId) throws BusinesLogicException;

    public void deleteParada(Long id);

}
