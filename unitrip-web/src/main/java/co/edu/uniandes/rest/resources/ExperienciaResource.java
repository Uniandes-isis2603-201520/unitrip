/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.resources;

import co.edu.uniandes.csw.unitrip.api.IExperienciaLogic;
import co.edu.uniandes.csw.unitrip.api.IViajeroLogic;
import co.edu.uniandes.csw.unitrip.entities.ExperienciaEntity;
import co.edu.uniandes.csw.unitrip.entities.ViajeroEntity;
import co.edu.uniandes.csw.unitrip.exceptions.BusinesLogicException;
import co.edu.uniandes.rest.converters.ExperienciaConverter;
import co.edu.uniandes.rest.dtos.ExperienciaDTO;
import co.edu.uniandes.rest.exceptions.LogicException;
import java.util.List;
import java.util.ArrayList;

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
 *viajeros/{idViajero: \\d+}
 * @author ANDRES
 */
@Path("viajeros/{viajeroId: \\d+}/experiencias")
@Produces("application/json")
public class ExperienciaResource {

    /**
     * Clase que implementa el recurso REST correspondiente a "experiencia".
     *
     * Note que la aplicación (definida en RestConfig.java) define la ruta
     * "/api" y este recurso tiene la ruta "experiencias". Al ejecutar la
     * aplicación, el recurse será accesibe a través de la ruta
     * "/api/experiencia"
     *
     * @author Asistente
     */
    @Inject
    private IExperienciaLogic experienciaLogic;

    // injection para poder hacer uso del padre
    @Inject
    private IViajeroLogic viajeroLogic;

    /**
     * Obtiene el listado de experiencia.
     *
     * @return lista de experiencia
     * @throws LogicException excepción retornada por la lógica
     */
    //@GET
    public List<ExperienciaDTO> getExperiencias(@PathParam("{viajeroId: \\d+}") Long idViajero) throws LogicException {
        return ExperienciaConverter.listEntity2DTO(experienciaLogic.getExperiencias());
    }

    /**
     * Obtiene un evento
     *
     * @param id identificador del evento
     * @return evento encontrado
     * @throws LogicException cuando el evento no existe
     */
    //@GET
    //@Path("/{experienciaId: \\d+}")
    public ExperienciaDTO getExperiencia(@PathParam("viajeroId") Long viajeroId, @PathParam("experienciaId") Long expId) throws LogicException {
        try {
            return ExperienciaConverter.fullEntity2DTO(experienciaLogic.getExperiencia(expId));
        } catch (BusinesLogicException ex) {
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.NOT_FOUND);
        }
    }

    /**
     * Agrega un evento
     *
     * @param event evento a agregar
     * @return datos del evento a agregar
     * @throws LogicException cuando ya existe un evento con el id suministrado
     */
    //@POST
    public ExperienciaDTO createExperiencia(@PathParam("viajeroId") Long viajeroId, ExperienciaDTO exp) throws LogicException {
        ExperienciaEntity entity = ExperienciaConverter.fullDTO2Entity(exp);
        return ExperienciaConverter.fullEntity2DTO(experienciaLogic.createExperiencia(entity));
    }

    /**
     * Actualiza los datos de un evento
     *
     * @param viajeroId
     * @param exp
     * @return datos del evento modificado
     * @throws LogicException cuando no existe un evento con el id suministrado
     */
    //@PUT
    //@Path("/experiencia")
    public ExperienciaDTO updateExperiencia(@PathParam("experienciaId") Long experienciaId, ExperienciaDTO exp, Long viajeroId) throws LogicException {
        ExperienciaEntity entity = ExperienciaConverter.fullDTO2Entity(exp);
        entity.setId(experienciaId);
        try {
            ViajeroEntity viajeroEntity = viajeroLogic.getViajero(viajeroId);
            entity.setViajero(viajeroEntity);

        } catch (BusinesLogicException ex) {
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.NOT_FOUND);
        }
        return ExperienciaConverter.fullEntity2DTO(experienciaLogic.updateExperiencia(entity));
    }

    /**
     * Elimina los datos de un evento
     *
     * @param id identificador del evento a eliminar
     * @throwsLogicException cuando no existe un evento con el id suministrado
     */
    //@DELETE
    //@Path("/{experienciaId: \\d+}")
    public void deleteExperiencia(@PathParam("viajeroId") Long id, @PathParam("experienciaId") Long expId) throws LogicException {
        experienciaLogic.deleteExperiencia(id);
    }


    // parte de viajero, con el fin de conservar el orden en las URIS

    @GET
    public List<ExperienciaDTO> listViajesDeViajero(@PathParam("viajeroId") Long viajeroId) {
        List<ExperienciaEntity> list= viajeroLogic.getExperiencias(viajeroId);
        return ExperienciaConverter.listEntity2DTO(list);
    }

    /**
     * Obtiene una instancia de Viaje asociada a una instancia de Viajero
     *
     * @param viajeroId Identificador de la instancia de Viaje
     * @param experienciaId Identificador de la instancia de Viaje
     * @return ItinerarioDTO una instancia de itinerario
     * @generated
     */
    @GET
    @Path("{experienciaId: \\d+}")
    public ExperienciaDTO getViajeDeViajero(@PathParam("viajeroId") Long viajeroId,
            @PathParam("experienciaId") Long experienciaId) {
        return ExperienciaConverter.fullEntity2DTO(viajeroLogic.getExperiencia(viajeroId, experienciaId));
    }

    /**
     * Asocia un Viaje existente a un Viajero
     *
     * @param dto
     * @param viajeroId Identificador de la instancia de Viajero
     * @return Instancia de ViajeDTO que fue asociada a Viajero
     * @generated
     */
    @POST
    public ExperienciaDTO addExperienciaAViajero(ExperienciaDTO dto, @PathParam("viajeroId") Long viajeroId) {
        ExperienciaEntity entity = ExperienciaConverter.fullDTO2Entity(dto);
        ExperienciaEntity actual = experienciaLogic.createExperiencia(entity);
        return ExperienciaConverter.fullEntity2DTO(viajeroLogic.addExperiencia(viajeroId, actual.getId()));
    }

    /**
     * Remplaza las instancias de Viaje asociadas a una instancia de Viajero
     *
     * @param experienciaId Identificador de la instancia de Viaje
     * @param experiencias Colección de instancias de ViajeDTO a asociar a instancia
     * de Viajero
     * @return Nueva colección de ViajeDTO asociada a la instancia de Viajero
     * @generated
     */
    @PUT
    public List<ExperienciaDTO> replaceViajes(@PathParam("viajeroId") Long viajeroId, ArrayList<ExperienciaDTO> experiencias) {
        return ExperienciaConverter.listEntity2DTO(viajeroLogic.replaceExperiencias(ExperienciaConverter.listDTO2Entity(experiencias), viajeroId));
    }

    /**
     * Desasocia un Vaije existente de un Viajero existente
     *
     * @param viajeroId Identificador de la instancia de Viajero
     * @param viajeId Identificador de la instancia de viaje
     * @generated
     */
    @DELETE
    @Path("{experienciaId: \\d+}")
    public void removeExperienciadeViajero(@PathParam("experienciaId") Long viajeId) throws BusinesLogicException {
        experienciaLogic.deleteExperiencia( viajeId);

    }

    /**
     * Hace un update de un viaje especifico de un viajero especifico
     * @param viajeId
     * @return
     */
    @PUT
    @Path("{experienciaId: \\d+}")
    public void updateExperienciaDeViajero(@PathParam("viajeroId") Long viajeroId, @PathParam("experienciaId") Long experienciaId, ExperienciaDTO experienciaDTO) throws LogicException {
        updateExperiencia(experienciaId, experienciaDTO, viajeroId);
    }

}
