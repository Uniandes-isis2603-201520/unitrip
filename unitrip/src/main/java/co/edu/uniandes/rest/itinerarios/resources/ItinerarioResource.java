/*
 * ItinerarioResource.java
 * Clase que representa el recurso "/itinerarios"
 * Implementa varios métodos para manipular los itinerarios
 */

package co.edu.uniandes.rest.itinerarios.resources;

import co.edu.uniandes.rest.Itinerarios.mocks.ItinerarioLogicMock;
import co.edu.uniandes.rest.itinerarios.dtos.ItinerarioDTO;
import co.edu.uniandes.rest.itinerarios.exceptions.ItinerarioLogicException;



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

	@Inject
	ItinerarioLogicMock itinerarioLogic;

	/**
	 * Obtiene el listado de itinerarioes.
	 * @return lista de itinerarios
	 * @throws ItinerarioLogicException excepción retornada por el lógica
	 */
    @GET
    public List<ItinerarioDTO> getItinerarios() throws ItinerarioLogicException {
        return itinerarioLogic.getItinerarios();
    }

    /**
     * Obtiene un itinerario
     * @param id identificador de el itinerario
     * @return itinerario encontrada
     * @throws ItinerarioLogicException cuando el itinerario no existe
     */
    @GET
    @Path("{id: \\d+}")
    public ItinerarioDTO getItinerarios(@PathParam("id") Long id) throws ItinerarioLogicException {
        return itinerarioLogic.getItinerario(id);
    }

    /**
     * Agrega un itinerario
     * @param itinerario itinerario a agregar
     * @return datos de el itinerario a agregar
     * @throws ItinerarioLogicException cuando ya existe un itinerario con el id suministrado
     */
    @POST
    public ItinerarioDTO createItinerario(ItinerarioDTO itinerario) throws ItinerarioLogicException {
        return itinerarioLogic.createItinerario(itinerario);
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
    public ItinerarioDTO updateItinerario(@PathParam("id") Long id, ItinerarioDTO itinerario) throws ItinerarioLogicException {
        return itinerarioLogic.updateItinerario(id, itinerario);
    }

    /**
     * Elimina los datos de un itinerario
     * @param id identificador de el itinerario a eliminar
     * @throws ItinerarioLogicException cuando no existe un itinerario con el id suministrado
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteItinrario(@PathParam("id") Long id) throws ItinerarioLogicException {
    	itinerarioLogic.deleteItinerario(id);
    }

}
