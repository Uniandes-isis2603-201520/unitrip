/*
 * ItinerarioResource.java
 * Clase que representa el recurso "/itinerarios"
 * Implementa varios métodos para manipular los itinerarios
 */

package co.edu.uniandes.rest.resources;


import co.edu.uniandes.csw.unitrip.api.IItinerarioLogic;
import co.edu.uniandes.csw.unitrip.entities.ItinerarioEntity;
import co.edu.uniandes.csw.unitrip.entities.ParadaEntity;
import co.edu.uniandes.csw.unitrip.exceptions.BusinesLogicException;
import co.edu.uniandes.rest.converters.ItinerarioConverter;
import co.edu.uniandes.rest.converters.ParadaConverter;
import co.edu.uniandes.rest.dtos.ItinerarioDTO;
import co.edu.uniandes.rest.dtos.ParadaDTO;
import co.edu.uniandes.rest.exceptions.LogicException;


import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * Clase que implementa el recurso REST correspondiente a "itinerarios".
 *
 * Note que el aplicación (definida en RestConfig.java) define el ruta
 * "/api" y este recurso tiene el ruta "itinerarios".
 * Al ejecutar el aplicación, el recurse será accesible a través de el
 * ruta "/api/itinerarios"
 *
 * @author Asistente
 */
@Path("itinerarios")
@Produces("application/json")
public class ItinerarioResource {

    private static final Logger logger = Logger.getLogger(ItinerarioResource.class.getName());

        @Inject
	private IItinerarioLogic itinerarioLogic;


	/**
	 * Obtiene el listado de itinerarioes.
	 * @return lista de itinerarios
	 * @throws LogicException excepción retornada por el lógica
	 */
    @GET
    public List<ItinerarioDTO> getItinerarios() throws LogicException {
        return ItinerarioConverter.listEntity2DTO(itinerarioLogic.getItinerarios());

    }

    /**
     * Obtiene un itinerario
     * @param id identificador de el itinerario
     * @return itinerario encontrada
     * @throws LogicException cuando el itinerario no existe
     */
    @GET
    @Path("{id: \\d+}")
    public ItinerarioDTO getItinerario(@PathParam("id") Long id) {
        logger.log(Level.INFO, "Se ejecuta método getItinerario con id={0}", id);
        ItinerarioEntity itinerario = itinerarioLogic.getItinerario(id);
        return ItinerarioConverter.fullEntity2DTO(itinerario);

    }

    /**
     * Agrega un itinerario
     * @param dto itinerario a agregar
     * @return datos de el itinerario a agregar
     * @throws LogicException cuando ya existe un itinerario con el id suministrado
     */
    @POST
    public ItinerarioDTO createItinerario(ItinerarioDTO dto) throws LogicException {
        ItinerarioEntity entity = ItinerarioConverter.fullDTO2Entity(dto);
        ItinerarioEntity newEntity;
        try {
            newEntity = itinerarioLogic.createItinerario(entity);
        } catch (BusinesLogicException ex) {
            logger.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.BAD_REQUEST);
        }
        return ItinerarioConverter.fullEntity2DTO(newEntity);


    }

    /**
     * Actualiza los datos de un itinerario
     * @param id identificador de el itinerario a modificar
     * @param itinerario itinerario a modificar
     * @return datos de el itinerario modificada
     * @throws LogicException cuando no existe un itinerario con el id suministrado
     */
    @PUT
    @Path("{id: \\d+}")
    public ItinerarioDTO updateItinerario(@PathParam("id") Long id, ItinerarioDTO itinerario) throws LogicException {
        logger.log(Level.INFO, "Se ejecuta método updateItinerario con id={0}", id);
        ItinerarioEntity entity = ItinerarioConverter.fullDTO2Entity(itinerario);
        entity.setId(id);
        try{
            ItinerarioEntity oldEntity = itinerarioLogic.getItinerario(id);
            //entity.setParadas(oldEntity.getParadas()); Esto no, porque que tal si le cambiamos las paradas? obviamente las perderia, creo
            ItinerarioEntity savedItinerario = itinerarioLogic.updateItinerario(entity);
            return ItinerarioConverter.fullEntity2DTO(savedItinerario);
        }catch(BusinesLogicException ex){
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.NOT_FOUND);
        }

    }

    /**
     * Elimina los datos de un itinerario
     * @param id identificador de el itinerario a eliminar
     * @throws LogicException cuando no existe un itinerario con el id suministrado
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteItinerario(@PathParam("id") Long id) throws LogicException {
    	itinerarioLogic.deleteItinerario(id);
    }

    /**
     * Obtiene una colección de instancias de paradaDTO asociadas a una
     * instancia de Itinerario
     *
     * @param itinerarioId Identificador de la instancia de Itinerario
     * @return Colección de instancias de ItinerarioDTO asociadas a la instancia de
     * Viaje
     * @throws BusinesLogicException en caso que no existan paradas
     * @generated
     */
    @GET
    @Path("{itinerarioId: \\d+}/paradas")
    public List<ParadaDTO> listParadas(@PathParam("itinerarioId") Long itinerarioId) throws BusinesLogicException {
        // falta este metodo
        List<ParadaEntity> paradas = itinerarioLogic.getParadas(itinerarioId);
        return ParadaConverter.listEntity2DTO(paradas);

    }


}
