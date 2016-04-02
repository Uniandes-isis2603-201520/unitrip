/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.resources;

import co.edu.uniandes.csw.unitrip.api.IExperienciaLogic;
import co.edu.uniandes.csw.unitrip.entities.ExperienciaEntity;
import co.edu.uniandes.csw.unitrip.exceptions.BusinesLogicException;
import co.edu.uniandes.rest.converters.ExperienciaConverter;
import co.edu.uniandes.rest.dtos.ExperienciaDTO;
import co.edu.uniandes.rest.exceptions.LogicException;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
/**
 *
 * @author ANDRES
 */
@Path("experiencias")
@Produces("application/json")
public class ExperienciaResource {


/**
 * Clase que implementa el recurso REST correspondiente a "experiencia".
 *
 * Note que la aplicación (definida en RestConfig.java) define la ruta
 * "/api" y este recurso tiene la ruta "experiencias".
 * Al ejecutar la aplicación, el recurse será accesibe a través de la
 * ruta "/api/experiencia"
 *
 * @author Asistente
 */



	@Inject
	private IExperienciaLogic experienciaLogic;

	/**
	 * Obtiene el listado de experiencia.
	 * @return lista de experiencia
	 * @throws LogicException excepción retornada por la lógica
	 */
    @GET
    public List<ExperienciaDTO> getExperiencias() throws LogicException {
        return ExperienciaConverter.listEntity2DTO(experienciaLogic.getExperiencias());
    }

    /**
     * Obtiene un evento
     * @param id identificador del evento
     * @return evento encontrado
     * @throws LogicException cuando el evento no existe
     */
    @GET
    @Path("{id: \\d+}")
    public ExperienciaDTO getExperiencia(@PathParam("id") Long id) throws LogicException {
        try
        {
            return ExperienciaConverter.fullEntity2DTO(experienciaLogic.getExperiencia(id));
        }
        catch(BusinesLogicException ex){
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.NOT_FOUND);
        }
    }

    /**
     * Agrega un evento
     * @param event evento a agregar
     * @return datos del evento a agregar
     * @throws LogicException cuando ya existe un evento con el id suministrado
     */
    @POST
    public ExperienciaDTO createEvent(ExperienciaDTO event) throws LogicException {
           ExperienciaEntity entity = ExperienciaConverter.fullDTO2Entity(event);
        return ExperienciaConverter.fullEntity2DTO(experienciaLogic.createExperiencia(entity));
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
    public ExperienciaDTO updateEvent(@PathParam("id") Long id, ExperienciaDTO event) throws LogicException {
           ExperienciaEntity entity = ExperienciaConverter.fullDTO2Entity(event);
        entity.setId(id);
        try
        {
            ExperienciaEntity oldEntity = experienciaLogic.getExperiencia(id);

        } catch (BusinesLogicException ex) {
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.NOT_FOUND);
        }
        return ExperienciaConverter.fullEntity2DTO(experienciaLogic.updateExperiencia(entity));
    }


    /**
     * Elimina los datos de un evento
     * @param id identificador del evento a eliminar
     * @throwsLogicException cuando no existe un evento con el id suministrado
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteExperiencia(@PathParam("id") Long id) throws LogicException {
    	experienciaLogic.deleteExperiencia(id);
    }
}

