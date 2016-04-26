package co.edu.uniandes.rest.resources;



import co.edu.uniandes.csw.unitrip.api.IViajesLogic;
import co.edu.uniandes.csw.unitrip.entities.ItinerarioEntity;
import co.edu.uniandes.csw.unitrip.entities.ViajeEntity;
import co.edu.uniandes.csw.unitrip.exceptions.BusinesLogicException;
import co.edu.uniandes.rest.converters.ItinerarioConverter;
import co.edu.uniandes.rest.converters.ViajesConverter;
import co.edu.uniandes.rest.dtos.ItinerarioDTO;
import co.edu.uniandes.rest.dtos.ViajesDTO;
import co.edu.uniandes.rest.exceptions.LogicException;
import co.edu.uniandes.rest.mocks.ViajesLogicMock;
import java.util.ArrayList;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



@Path("viajero/{idViajero: \\d+}/viajes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces("application/json")
public class ViajesResource {

    @Inject
    private IViajesLogic viajeLogic;

    /**
     * Obtiene la lista de los registros de Viajes.
     *
     * @return Colección de objetos de ViajesDTO cada uno con sus respectivos
     * Review
     * @throws LogicException Lanza excepcion cuando no hay viajes
     * @generated
     */
    @GET
    public List<ViajesDTO> getViajes() throws LogicException {
        return ViajesConverter.listEntity2DTO(viajeLogic.getViajes());

    }

    /**
     * Obtiene los datos de una instancia de Viaje a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de ViajesDTO con los datos del Viajes consultado y sus
     * Review
     * @throws LogicException en caso tal que no exista el viaje
     * @generated
     */
    @GET
    @Path("{id: \\d+}")
    public ViajesDTO getViaje(@PathParam("id") Long id) throws LogicException {
          try
        {
            return ViajesConverter.fullEntity2DTO(viajeLogic.getViaje(id));
        }
        catch(BusinesLogicException ex){
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.NOT_FOUND);
        }
    }

    /**
     * Se encarga de crear un viaje en la base de datos.
     *
     * @param dto Objeto de ViajesDTO con los datos nuevos
     * @return Objeto de ViajesDTO con los datos nuevos y su ID.
     * @throws LogicException en caso que ya exista el viaje
     * @generated
     */
    @POST
    @Path("{id: \\d+}")
    public ViajesDTO createViaje(ViajesDTO dto) throws LogicException {
        ViajeEntity entity = ViajesConverter.fullDTO2Entity(dto);
        return ViajesConverter.fullEntity2DTO(viajeLogic.createViaje(entity));

    }

    /**
     * Actualiza la información de una instancia de Viaje.
     *
     * @param id Identificador de la instancia de Viaje a modificar
     * @param dto Instancia de ViajeDTO con los nuevos datos.
     * @return Instancia de ViajeDTO con los datos actualizados.
     * @throws LogicException en caso que no exista el viaje
     * @generated
     */
    @PUT
    @Path("{id: \\d+}")
    public ViajesDTO updateViaje(@PathParam("id") Long id, ViajesDTO dto) throws LogicException {
               ViajeEntity entity = ViajesConverter.fullDTO2Entity(dto);
               entity.setId(id);
        try
        {
            ViajeEntity oldEntity = viajeLogic.getViaje(id);

        } catch (BusinesLogicException ex) {
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.NOT_FOUND);
        }
        return ViajesConverter.fullEntity2DTO(viajeLogic.updateViaje(entity));
    }

    /**
     * Elimina una instancia de Viaje de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @throws LogicException en caso que no exista el viaje
     * @generated
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteViaje(@PathParam("id") Long id) throws LogicException {
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
        List<ItinerarioEntity> list= viajeLogic.getItinerarios(viajeId);
        return ItinerarioConverter.listEntity2DTO(list);
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
        return ItinerarioConverter.fullEntity2DTO(viajeLogic.addItinerario(viajeId, itinerarioId));
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
    public List<ItinerarioDTO> replaceItinerarios(@PathParam("viajeId") Long viajeId, ArrayList<ItinerarioDTO> itinerarios) {
        return ItinerarioConverter.listEntity2DTO(viajeLogic.replaceItinerarios(ItinerarioConverter.listDTO2Entity(itinerarios), viajeId));
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
