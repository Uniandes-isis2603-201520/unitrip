/*
 * ItinerarioResource.java
 * Clase que representa el recurso "/itinerarios"
 * Implementa varios métodos para manipular los itinerarios
 */

package co.edu.uniandes.rest.resources;


import co.edu.uniandes.csw.unitrip.exceptions.BusinesLogicException;
import co.edu.uniandes.rest.Itinerarios.mocks.ItinerarioLogicMock;
import co.edu.uniandes.rest.dtos.ItinerarioDTO;
import co.edu.uniandes.rest.dtos.ParadaDTO;
import co.edu.uniandes.rest.exceptions.LogicException;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

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

	
	private ItinerarioLogicMock itinerarioLogic;

	/**
	 * Obtiene el listado de itinerarioes.
	 * @return lista de itinerarios
	 * @throws LogicException excepción retornada por el lógica
	 */
    @GET
    public List<ItinerarioDTO> getItinerarios() throws LogicException {
        //return ItinerarioConverter.listEntity2DTO(itinerarioLogic.getItinerarios());
        return itinerarioLogic.getItinerarios();
    }

    /**
     * Obtiene un itinerario
     * @param id identificador de el itinerario
     * @return itinerario encontrada
     * @throws LogicException cuando el itinerario no existe
     */
    @GET
    @Path("{id: \\d+}")
    public ItinerarioDTO getItinerario(@PathParam("id") Long id) throws LogicException {
        //return ItinerarioConverter.fullEntity2DTO(itinerarioLogic.getItinerario(id));
        return itinerarioLogic.getItinerario(id);
    }

    /**
     * Agrega un itinerario
     * @param dto itinerario a agregar
     * @return datos de el itinerario a agregar
     * @throws LogicException cuando ya existe un itinerario con el id suministrado 
     */
    @POST
    public ItinerarioDTO createItinerario(ItinerarioDTO dto) throws LogicException {
        //ItinerarioEntity entity = ItinerarioConverter.fullDTO2Entity(dto);
        //return ItinerarioConverter.fullEntity2DTO(itinerarioLogic.createItinerario(entity));
        return itinerarioLogic.createItinerario(dto);
    }

    /**
     * Actualiza los datos de un itinerario
     * @param id identificador de el itinerario a modificar
     * @param itinerario itinerario a modificar
     * @return datos de el itinerario modificada
     * @throws ItinerarioLogicException cuando no existe un itinerario con el id suministrado
     */
    @PUT
    @Path("{id: \\d+}")
    public ItinerarioDTO updateItinerario(@PathParam("id") Long id, ItinerarioDTO itinerario) throws LogicException {
        //ItinerarioEntity entity = ItinerarioConverter.fullDTO2Entity(itinerario);
        //entity.setId(id);
        //return ItinerarioConverter.fullEntity2DTO(itinerarioLogic.updateItinerario(entity));
        return itinerarioLogic.updateItinerario(id,itinerario);
    }

    /**
     * Elimina los datos de un itinerario
     * @param id identificador de el itinerario a eliminar
     * @throws ItinerarioLogicException cuando no existe un itinerario con el id suministrado
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteItinrario(@PathParam("id") Long id) throws LogicException {
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
        return null;
    }
    

}
