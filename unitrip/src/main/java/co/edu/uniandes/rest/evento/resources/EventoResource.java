/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.evento.resources;

/**
 *
 * @author l.castro12
 */
import co.edu.uniandes.rest.evento.dtos.EventoDTO;
import co.edu.uniandes.rest.evento.exceptions.EventoLogicException;
import co.edu.uniandes.rest.evento.mocks.EventoLogicMock;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * Clase que implementa el recurso REST correspondiente a "evento".
 *
 * Note que la aplicación (definida en RestConfig.java) define la ruta
 * "/api" y este recurso tiene la ruta "evento".
 * Al ejecutar la aplicación, el recurse será accesibe a través de la
 * ruta "/api/evento"
 *
 * @author Asistente
 */
@Path("evento")
@Produces("application/json")
public class EventoResource {

	@Inject
	EventoLogicMock eventoLogic;

	/**
	 * Obtiene el listado de eventos.
	 * @return lista de eventos
	 * @throws EventoLogicException excepción retornada por la lógica
	 */
    @GET
    public List<EventoDTO> getEventos() throws EventoLogicException {
        return eventoLogic.getEventos();
    }

    /**
     * Obtiene un evento
     * @param id identificador del evento
     * @return evento encontrado
     * @throws EventoLogicException cuando el evento no existe
     */
    @GET
    @Path("{id: \\d+}")
    public EventoDTO getEvento(@PathParam("id") Long id) throws EventoLogicException {
        return eventoLogic.getEvento(id);
    }

    /**
     * Agrega un evento
     * @param event evento a agregar
     * @return datos del evento a agregar
     * @throws EventoLogicException cuando ya existe un evento con el id suministrado
     */
    @POST
    public EventoDTO createEvent(EventoDTO event) throws EventoLogicException {
        return eventoLogic.createEvento(event);
    }

    /**
     * Actualiza los datos de un evento
     * @param id identificador del evento a modificar
     * @param event evento a modificar
     * @return datos del evento modificado
     * @throws EventoLogicException cuando no existe un evento con el id suministrado
     */
    @PUT
    @Path("{id: \\d+}")
    public EventoDTO updateEvent(@PathParam("id") Long id, EventoDTO event) throws EventoLogicException {
        return eventoLogic.updateEvento(id, event);
    }

    /**
     * Elimina los datos de un evento
     * @param id identificador del evento a eliminar
     * @throws EventoLogicException cuando no existe un evento con el id suministrado
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteEvent(@PathParam("id") Long id) throws EventoLogicException {
    	eventoLogic.deleteEvento(id);
    }

}