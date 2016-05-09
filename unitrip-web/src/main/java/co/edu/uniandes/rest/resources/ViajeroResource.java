/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.resources;

import co.edu.uniandes.csw.unitrip.api.IViajeroLogic;
import co.edu.uniandes.csw.unitrip.api.IViajesLogic;
import co.edu.uniandes.csw.unitrip.entities.ItinerarioEntity;
import co.edu.uniandes.csw.unitrip.entities.ViajeEntity;
import co.edu.uniandes.csw.unitrip.entities.ViajeroEntity;
import co.edu.uniandes.csw.unitrip.exceptions.BusinesLogicException;
import co.edu.uniandes.rest.converters.ItinerarioConverter;
import co.edu.uniandes.rest.converters.ViajeroConverter;
import co.edu.uniandes.rest.converters.ViajesConverter;
import co.edu.uniandes.rest.dtos.ItinerarioDTO;
import co.edu.uniandes.rest.dtos.ViajeroDTO;
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



@Path("viajeros")
@Consumes(MediaType.APPLICATION_JSON)
@Produces("application/json")
public class ViajeroResource {

    @Inject
    private IViajeroLogic viajeroLogic;

    @Inject
    private IViajesLogic viajeLogic;

    /**
     * Obtiene la lista de los registros de Viajeros.
     *
     * @return Colección de objetos de ViajerosDTO cada uno con sus respectivos
     * Review
     * @throws LogicException Lanza excepcion cuando no hay viajeros
     * @generated
     */
    @GET
    public List<ViajeroDTO> getViajeros() throws LogicException {
        return ViajeroConverter.listEntity2DTO(viajeroLogic.getViajeros());

    }

    /**
     * Obtiene los datos de una instancia de Viajero a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de ViajeroDTO con los datos del Viajero consultado y sus
     * Review
     * @throws LogicException en caso tal que no exista el viajero
     * @generated
     */
    @GET
    @Path("{id: \\d+}")
    public ViajeroDTO getViajero(@PathParam("id") Long id) throws LogicException {
          try
        {
            return ViajeroConverter.fullEntity2DTO(viajeroLogic.getViajero(id));
        }
        catch(BusinesLogicException ex){
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.NOT_FOUND);
        }
    }

    /**
     * Se encarga de crear un viajero en la base de datos.
     *
     * @param dto Objeto de ViajeroDTO con los datos nuevos
     * @return Objeto de ViajeroDTO con los datos nuevos y su ID.
     * @throws LogicException en caso que ya exista el viaje
     * @generated
     */
    @POST
    public ViajeroDTO createViajero(ViajeroDTO dto) throws LogicException {
        ViajeroEntity entity = ViajeroConverter.fullDTO2Entity(dto);
        return ViajeroConverter.fullEntity2DTO(viajeroLogic.createViajero(entity));

    }

    /**
     * Actualiza la información de una instancia de Viajero.
     *
     * @param id Identificador de la instancia de Viajero a modificar
     * @param dto Instancia de ViajeroDTO con los nuevos datos.
     * @return Instancia de ViajeroDTO con los datos actualizados.
     * @throws LogicException en caso que no exista el viajero
     * @generated
     */
    @PUT
    @Path("{id: \\d+}")
    public ViajeroDTO updateViajero(@PathParam("id") Long id, ViajeroDTO dto) throws LogicException {
               ViajeroEntity entity = ViajeroConverter.fullDTO2Entity(dto);
               entity.setId(id);
        try
        {
            ViajeroEntity oldEntity = viajeroLogic.getViajero(id);

        } catch (BusinesLogicException ex) {
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.NOT_FOUND);
        }
        return ViajeroConverter.fullEntity2DTO(viajeroLogic.updateViajero(entity));
    }

    /**
     * Elimina una instancia de Viajero de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @throws LogicException en caso que no exista el viajero
     * @generated
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteViajero(@PathParam("id") Long id) throws LogicException {
        viajeroLogic.deleteViajero(id);
    }

    /**
     * Obtiene una colección de instancias de ViajesDTO asociadas a una
     * instancia de Viajero
     *
     * @param viajeId Identificador de la instancia de Viaje
     * @return Colección de instancias de ItinerarioDTO asociadas a la instancia de
     * Viaje
     * @generated
     */
    @GET
    @Path("{viajeroId: \\d+}/viajes")
    public List<ViajesDTO> listViajes(@PathParam("viajeroId") Long viajeroId) {
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
    @Path("{viajeroId: \\d+}/viajes/{viajeId: \\d+}")
    public ViajesDTO getViajes(@PathParam("viajeroId") Long viajeroId,
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
    @Path("{viajeroId: \\d+}/viajes")
    public ViajesDTO addViaje(ViajesDTO dto, @PathParam("viajeroId") Long viajeroId) {
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
    @Path("{viajeroId: \\d+}/viajes")
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
    @Path("{viajeroId: \\d+}/viajes/{viajeId: \\d+}")
    public void removeViaje(@PathParam("viajeroId") Long viajeroId, @PathParam("viajeId") Long viajeId) {
        viajeroLogic.removeViaje(viajeroId, viajeId);

    }
}
