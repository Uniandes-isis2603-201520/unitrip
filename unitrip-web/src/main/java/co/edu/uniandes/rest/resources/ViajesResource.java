package co.edu.uniandes.rest.resources;

import co.edu.uniandes.csw.unitrip.api.IViajeroLogic;
import co.edu.uniandes.csw.unitrip.api.IViajesLogic;
import co.edu.uniandes.csw.unitrip.entities.ItinerarioEntity;
import co.edu.uniandes.csw.unitrip.entities.ViajeEntity;
import co.edu.uniandes.csw.unitrip.entities.ViajeroEntity;

import co.edu.uniandes.csw.unitrip.exceptions.BusinesLogicException;
import co.edu.uniandes.rest.converters.ItinerarioConverter;
import co.edu.uniandes.rest.converters.ViajesConverter;
import co.edu.uniandes.rest.dtos.ItinerarioDTO;
import co.edu.uniandes.rest.dtos.ViajesDTO;
import co.edu.uniandes.rest.exceptions.LogicException;
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

@Path("viajeros/{viajeroId: \\d+}/viajes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces("application/json")
public class ViajesResource {

    // injection para poder hacer uso del padre
    @Inject
    private IViajeroLogic viajeroLogic;

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
    //@GET
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
    //@GET
    //@Path("{idViaje: \\d+}")
    public ViajesDTO getViaje(@PathParam("idViaje") Long id) throws LogicException {
        try {
            return ViajesConverter.fullEntity2DTO(viajeLogic.getViaje(id));
        } catch (BusinesLogicException ex) {
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
    //@POST
    public ViajesDTO createViaje(ViajesDTO dto) throws LogicException {
        ViajeEntity entity = ViajesConverter.fullDTO2Entity(dto);
        return ViajesConverter.fullEntity2DTO(viajeLogic.createViaje(entity));

    }

    /**
     * Actualiza la información de una instancia de Viaje.
     *
     * @param id Identificador de la instancia de Viaje a modificar
     * @param dto Instancia de ViajeDTO con los nuevos datos.
     * @param idViajero
     * @return Instancia de ViajeDTO con los datos actualizados.
     * @throws LogicException en caso que no exista el viaje
     * @generated
     */
    //@PUT
    //@Path("{idViaje: \\d+}")
    public ViajesDTO updateViaje(@PathParam("idViaje") Long id, ViajesDTO dto, Long idViajero) throws LogicException {
        ViajeEntity entity = ViajesConverter.fullDTO2Entity(dto);
        entity.setId(id);

        try {
            ViajeroEntity viajeroEntity = viajeroLogic.getViajero(idViajero);
            entity.setViajero(viajeroEntity);
            ViajeEntity oldEntity = viajeLogic.getViaje(id);
            entity.setItinerarios(oldEntity.getItinerarios());

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
    //@DELETE
    //@Path("{idViaje: \\d+}")
    public void deleteViaje(@PathParam("idViaje") Long id) throws LogicException {
        viajeLogic.deleteViaje(id);
    }

    /**
     * Obtiene una colección de instancias de ItinerarioDTO asociadas a una
     * instancia de Viaje
     *
     * @param viajeId Identificador de la instancia de Viaje
     * @return Colección de instancias de ItinerarioDTO asociadas a la instancia
     * de Viaje
     * @generated
     */


    // parte de viajero, con el fin de conservar el orden en las URIS

    @GET
    public List<ViajesDTO> listViajesDeViajero(@PathParam("viajeroId") Long viajeroId) {
        List<ViajeEntity> list= viajeroLogic.getViajes(viajeroId);
        return ViajesConverter.listEntity2DTO(list);
    }

    /**
     * Obtiene una instancia de Viaje asociada a una instancia de Viajero
     *
     * @param viajeroId Identificador de la instancia de Viaje
     * @param viajeId Identificador de la instancia de Viaje
     * @return ItinerarioDTO una instancia de itinerario
     * @generated
     */
    @GET
    @Path("{viajeId: \\d+}")
    public ViajesDTO getViajeDeViajero(@PathParam("viajeroId") Long viajeroId,
            @PathParam("viajeId") Long viajeId) {
        return ViajesConverter.fullEntity2DTO(viajeroLogic.getViaje(viajeroId, viajeId));
    }

    /**
     * Asocia un Viaje existente a un Viajero
     *
     * @param viajeroId Identificador de la instancia de Viajero
     * @param viajeId Identificador de la instancia de Viaje
     * @return Instancia de ViajeDTO que fue asociada a Viajero
     * @generated
     */
    
    @POST
    public ViajesDTO addViajeAViajero(ViajesDTO dto, @PathParam("viajeroId") Long viajeroId) {
        ViajeEntity entity = ViajesConverter.fullDTO2Entity(dto);
        ViajeEntity actual = viajeLogic.createViaje(entity);
        return ViajesConverter.fullEntity2DTO(viajeroLogic.addViaje(viajeroId, actual.getId()));
    }

    /**
     * Remplaza las instancias de Viaje asociadas a una instancia de Viajero
     *
     * @param viajeroId Identificador de la instancia de Viaje
     * @param viajes Colección de instancias de ViajeDTO a asociar a instancia
     * de Viajero
     * @return Nueva colección de ViajeDTO asociada a la instancia de Viajero
     * @generated
     */
    @PUT
    public List<ViajesDTO> replaceViajes(@PathParam("viajeroId") Long viajeroId, ArrayList<ViajesDTO> viajes) {
        return ViajesConverter.listEntity2DTO(viajeroLogic.replaceViajes(ViajesConverter.listDTO2Entity(viajes), viajeroId));
    }

    /**
     * Desasocia un Vaije existente de un Viajero existente
     *
     * @param viajeroId Identificador de la instancia de Viajero
     * @param viajeId Identificador de la instancia de viaje
     * @generated
     */
    @DELETE
    @Path("{viajeId: \\d+}")
    public void removeViajedeViajero(@PathParam("viajeroId") Long viajeroId, @PathParam("viajeId") Long viajeId) throws BusinesLogicException {
        viajeroLogic.removeViaje(viajeroId, viajeId);

    }

    /**
     * Hace un update de un viaje especifico de un viajero especifico
     * @param viajeId
     * @return
     */
    @PUT
    @Path("{viajeId: \\d+}")
    public void updateViajeDeViajero(@PathParam("viajeroId") Long viajeroId, @PathParam("viajeId") Long viajeId, ViajesDTO viajeDTO) throws LogicException {
        updateViaje(viajeId, viajeDTO, viajeroId);
    }



}
