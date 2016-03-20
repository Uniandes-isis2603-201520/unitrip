/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.experiencias.resources;

import co.edu.uniandes.rest.experiencias.dtos.ExperienciaDTO;
import co.edu.uniandes.rest.experiencias.exceptions.ExperienciaLogicException;
import co.edu.uniandes.rest.experiencias.mocks.ExperienciaLogicMock;

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
 *
 * @author ANDRES
 */
@Path("experiencia")
@Produces("application/json")
public class ExperienciaResource {


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



	@Inject
	ExperienciaLogicMock eventoLogic;

	/**
	 * Obtiene el listado de experiencia.
	 * @return lista de experiencia
	 * @throws ExperienciaLogicException excepción retornada por la lógica
	 */
    @GET
    public List<ExperienciaDTO> getExperiencias() throws ExperienciaLogicException {
        return eventoLogic.getExperiencias();
    }

    /**
     * Obtiene un evento
     * @param id identificador del evento
     * @return evento encontrado
     * @throws ExperienciaLogicException cuando el evento no existe
     */
    @GET
    @Path("{id: \\d+}")
    public ExperienciaDTO getExperiencia(@PathParam("id") Long id) throws ExperienciaLogicException {
        return eventoLogic.getExperiencia(id);
    }

    /**
     * Agrega un evento
     * @param event evento a agregar
     * @return datos del evento a agregar
     * @throws ExperienciaLogicException cuando ya existe un evento con el id suministrado
     */
    @POST
    public ExperienciaDTO createEvent(ExperienciaDTO event) throws ExperienciaLogicException {
        return eventoLogic.createExperiencia(event);
    }

    /**
     * Actualiza los datos de un evento
     * @param id identificador del evento a modificar
     * @param event evento a modificar
     * @return datos del evento modificado
     * @throws ExperienciaLogicException cuando no existe un evento con el id suministrado
     */
    @PUT
    @Path("{id: \\d+}")
    public ExperienciaDTO updateEvent(@PathParam("id") Long id, ExperienciaDTO event) throws ExperienciaLogicException {
        return eventoLogic.updateExperiencia(id, event);
    }

    /**
     * Elimina los datos de un evento
     * @param id identificador del evento a eliminar
     * @throws ExperienciaLogicException cuando no existe un evento con el id suministrado
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteEvent(@PathParam("id") Long id) throws ExperienciaLogicException {
    	eventoLogic.deleteExperiencia(id);
    }
}

