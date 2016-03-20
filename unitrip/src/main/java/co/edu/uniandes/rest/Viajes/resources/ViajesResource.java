package co.edu.uniandes.rest.Viajes.resources;

import co.edu.uniandes.rest.Viajes.converters.ViajesConverter;
import co.edu.uniandes.rest.Viajes.dtos.ViajesDTO;
import co.edu.uniandes.rest.Viajes.exceptions.ViajesLogicException;
import co.edu.uniandes.rest.itinerarios.dtos.ItinerarioDTO;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;



@Path("viajes")
@Produces("application/json")
public class ViajesResource {

    @Inject
    private IViajesLogic viajeLogic;

    /**
     * Obtiene la lista de los registros de Viajes.
     *
     * @return Colección de objetos de ViajesDTO cada uno con sus respectivos
     * Review
     * @throws ViajesLogicException Lanza excepcion cuando no hay viajes
     * @generated
     */
    @GET
    public List<ViajesDTO> getViajes() throws ViajesLogicException {
        return ViajesConverter.listEntity2DTO(viajeLogic.getViajes());
    }

    /**
     * Obtiene los datos de una instancia de Viaje a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de ViajesDTO con los datos del Viajes consultado y sus
     * Review
     * @generated
     */
    @GET
    @Path("{id: \\d+}")
    public ViajesDTO getViaje(@PathParam("id") Long id) throws BusinessLogicException {
        return ViajesConverter.fullEntity2DTO(viajeLogic.getViaje(id));
    }

    /**
     * Se encarga de crear un viaje en la base de datos.
     *
     * @param dto Objeto de ViajesDTO con los datos nuevos
     * @return Objeto de ViajesDTO con los datos nuevos y su ID.
     * @generated
     */
    @POST
    public ViajesDTO createViaje(ViajesDTO dto) {
        ViajesEntity entity = ViajesConverter.fullDTO2Entity(dto);
        return ViajesConverter.fullEntity2DTO(viajeLogic.createViaje(entity));
    }

    /**
     * Actualiza la información de una instancia de Viaje.
     *
     * @param id Identificador de la instancia de Viaje a modificar
     * @param dto Instancia de ViajeDTO con los nuevos datos.
     * @return Instancia de ViajeDTO con los datos actualizados.
     * @generated
     */
    @PUT
    @Path("{id: \\d+}")
    public ViajesDTO updateBook(@PathParam("id") Long id, ViajesDTO dto) {
        ViajesEntity entity = ViajesConverter.fullDTO2Entity(dto);
        entity.setId(id);
        return ViajesConverter.fullEntity2DTO(viajeLogic.updateViajes(entity));
    }

    /**
     * Elimina una instancia de Viaje de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @generated
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteViaje(@PathParam("id") Long id) {
        viajeLogic.deleteViaje(id);
    }

    /**
     * Obtiene una colección de instancias de ItinerarioDTO asociadas a una
     * instancia de Viaje
     *
     * @param viajeId Identificador de la instancia de Viaje
     * @return Colección de instancias de ItinerarioDTO asociadas a la instancia de
     * Viaje
     * @generated
     */
    @GET
    @Path("{viajeId: \\d+}/itinerarios")
    public List<ItinerarioDTO> listItinerarios(@PathParam("viajeId") Long viajeId) {
        return ItinerarioConverter.listEntity2DTO(viajeLogic.getItinerarios(viajeId));
    }

    /**
     * Obtiene una instancia de Itinerario asociada a una instancia de Viaje
     *
     * @param viajeId Identificador de la instancia de Viaje
     * @param itinerarioId Identificador de la instancia de Itinerario
     * @return ItinerarioDTO una instancia de itinerario
     * @generated
     */
    @GET
    @Path("{viajeId: \\d+}/itinerarios/{itinerarioId: \\d+}")
    public ItinerarioDTO getItinerario(@PathParam("viajeId") Long viajeId, @PathParam("itinerarioId") Long itinerarioId) {
        return ItinerarioConverter.fullEntity2DTO(viajeLogic.getItinerario(viajeId, itinerarioId));
    }

    /**
     * Asocia un Itinerario existente a un Viaje
     *
     * @param viajeId Identificador de la instancia de Viaje
     * @param itinerarioId Identificador de la instancia de Itinerario
     * @return Instancia de ItinerarioDTO que fue asociada a Viaje
     * @generated
     */
    @POST
    @Path("{viajeId: \\d+}/itinerarios/{itinerarioId: \\d+}")
    public ItinerarioDTO addItinerario(@PathParam("viajeId") Long viajeId, @PathParam("itinerarioId") Long itinerarioId) {
        return ItinerarioConverter.fullEntity2DTO(viajeLogic.addAuthor(viajeId, itinerarioId));
    }

    /**
     * Remplaza las instancias de Itinerario asociadas a una instancia de Viaje
     *
     * @param viajeId Identificador de la instancia de Viaje
     * @param itinerarios Colección de instancias de ItinerarioDTO a asociar a instancia
     * de Viaje
     * @return Nueva colección de ItinerarioDTO asociada a la instancia de Viaje
     * @generated
     */
    @PUT
    @Path("{viajeId: \\d+}/itinerarios")
    public List<ItinerarioDTO> replaceAuthors(@PathParam("viajeId") Long viajeId, List<ItinerarioDTO> itinerarios) {
        return ItinerarioConverter.listEntity2DTO(viajeLogic.replaceItinerario(ItinerarioConverter.listDTO2Entity(itinerarios), viajeId));
    }

    /**
     * Desasocia un Itinerario existente de un Viaje existente
     *
     * @param viajeId Identificador de la instancia de Viaje
     * @param itinerarioId Identificador de la instancia de Itinerario
     * @generated
     */
    @DELETE
    @Path("{viajeId: \\d+}/itinerarios/{itinerarioId: \\d+}")
    public void removeItinerario(@PathParam("viajeId") Long viajeId, @PathParam("itinerarioId") Long itinerarioId) {
        viajeLogic.removeItinerario(viajeId, itinerarioId);
    }
}
