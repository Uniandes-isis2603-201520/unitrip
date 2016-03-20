/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.resources;

/**
 *
 * @author l.castro12
 */

import co.edu.uniandes.rest.dtos.EventoDTO;
import co.edu.uniandes.rest.exceptions.LogicException;
import co.edu.uniandes.rest.mocks.EventoLogicMock;
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
	 * @throws LogicException excepción retornada por la lógica
	 */
    @GET
    public List<EventoDTO> getEventos() throws LogicException {
        return eventoLogic.getEventos();
    }

    /**
     * Obtiene un evento
     * @param id identificador del evento
     * @return evento encontrado
     * @throws LogicException cuando el evento no existe
     */
    @GET
    @Path("{id: \\d+}")
    public EventoDTO getEvento(@PathParam("id") Long id) throws LogicException {
        return eventoLogic.getEvento(id);
    }

    /**
     * Agrega un evento
     * @param event evento a agregar
     * @return datos del evento a agregar
     * @throws LogicException cuando ya existe un evento con el id suministrado
     */
    @POST
    public EventoDTO createEvent(EventoDTO event) throws LogicException {
        return eventoLogic.createEvento(event);
    }

    /**
     * Actualiza los datos de un evento
     * @param id identificador del evento a modificar
     * @param event evento a modificar
     * @return datos del evento modificado
     * @throws LogicException cuando no existe un evento con el id suministrado
     */
    @PUT
    @Path("{id: \\d+}")
    public EventoDTO updateEvent(@PathParam("id") Long id, EventoDTO event) throws LogicException {
        return eventoLogic.updateEvento(id, event);
    }

    /**
     * Elimina los datos de un evento
     * @param id identificador del evento a eliminar
     * @throws LogicException cuando no existe un evento con el id suministrado
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteEvent(@PathParam("id") Long id) throws LogicException {
    	eventoLogic.deleteEvento(id);
    }

}