/*
 * ItinerarioResource.java
 * Clase que representa el recurso "/itinerarios"
 * Implementa varios métodos para manipular los itinerarios
 */
package co.edu.uniandes.rest.resources;

import co.edu.uniandes.csw.unitrip.api.IItinerarioLogic;
import co.edu.uniandes.csw.unitrip.api.IViajesLogic;
import co.edu.uniandes.csw.unitrip.ejbs.ViajeLogic;
import co.edu.uniandes.csw.unitrip.entities.ItinerarioEntity;
import co.edu.uniandes.csw.unitrip.entities.ParadaEntity;
import co.edu.uniandes.csw.unitrip.exceptions.BusinesLogicException;
import co.edu.uniandes.rest.converters.ItinerarioConverter;
import co.edu.uniandes.rest.converters.ParadaConverter;
import co.edu.uniandes.rest.dtos.ItinerarioDTO;
import co.edu.uniandes.rest.dtos.ParadaDTO;
import co.edu.uniandes.rest.exceptions.LogicException;
import java.util.ArrayList;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("viajeros/{viajeroId: \\d+}/viajes/{viajeId: \\d+}/itinerarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ItinerarioResource {

    private static final Logger logger = Logger.getLogger(ItinerarioResource.class.getName());

    @Inject
    private IItinerarioLogic itinerarioLogic;

    @Inject
    private IViajesLogic viajeLogic;
    /**
     * Obtiene el listado de itinerarioes.
     *
     * @return lista de itinerarios
     */
    //@GET
    public List<ItinerarioDTO> getItinerarios() {
        logger.info("Se ejecuta método getItinerarios");
        List<ItinerarioEntity> itinerarios = itinerarioLogic.getItinerarios();
        return ItinerarioConverter.listEntity2DTO(itinerarios);

    }

    /**
     * Obtiene un itinerario
     *
     * @param id identificador de el itinerario
     * @return itinerario encontrada
     */
    //@GET
    //@Path("{id: \\d+}")
    public ItinerarioDTO getItinerario(@PathParam("id") Long id) {
        logger.log(Level.INFO, "Se ejecuta método getItinerario con id={0}", id);
        ItinerarioEntity itinerario = itinerarioLogic.getItinerario(id);
        return ItinerarioConverter.fullEntity2DTO(itinerario);

    }

    /**
     * Agrega un itinerario
     *
     * @param dto itinerario a agregar
     * @return datos de el itinerario a agregar
     */
    //@POST
    public ItinerarioDTO createItinerario(ItinerarioDTO dto) {
        logger.info("Se ejecuta método createItinerario");
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
     *
     * @param id identificador de el itinerario a modificar
     * @param itinerario itinerario a modificar
     * @return datos de el itinerario modificada
     */
    //@PUT
    //@Path("{id: \\d+}")
    public ItinerarioDTO updateItinerario(@PathParam("id") Long id, ItinerarioDTO itinerarioDTO) {
        logger.log(Level.INFO, "Se ejecuta método updateItinerario con id={0}", id);
        ItinerarioEntity entity = ItinerarioConverter.fullDTO2Entity(itinerarioDTO);
        entity.setId(id);
        ItinerarioEntity oldEntity = itinerarioLogic.getItinerario(id);
        entity.setParadas(oldEntity.getParadas());
        try {
            ItinerarioEntity savedItinerario = itinerarioLogic.updateItinerario(entity);
            return ItinerarioConverter.fullEntity2DTO(savedItinerario);
        } catch (BusinesLogicException ex) {
            logger.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.BAD_REQUEST);
        }

    }

    /**
     * Elimina los datos de un itinerario
     *
     * @param id identificador de el itinerario a eliminar
     */
    //@DELETE
    //@Path("{id: \\d+}")
    public void deleteItinerario(@PathParam("id") Long id) {
        logger.log(Level.INFO, "Se ejecuta método deleteItinerario con id={0}", id);
        itinerarioLogic.deleteItinerario(id);
    }

    //parte de viaje, con el fin de conservar el orden en las URI's

    @GET
    public List<ItinerarioDTO> listItinerariosDeViaje(@PathParam("viajeId") Long viajeId) {
        List<ItinerarioEntity> list = viajeLogic.getItinerarios(viajeId);
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
    @Path("{itinerarioId: \\d+}")
    public ItinerarioDTO getItinerarioDeViaje(@PathParam("viajeId") Long viajeId, @PathParam("itinerarioId") Long itinerarioId) {
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
    public ItinerarioDTO addItinerarioAViaje(ItinerarioDTO itinerarioDTO, @PathParam("viajeId") Long viajeId )  {
        try {
            ItinerarioEntity entity = ItinerarioConverter.fullDTO2Entity(itinerarioDTO);
            ItinerarioEntity actual = itinerarioLogic.createItinerario(entity);
            return ItinerarioConverter.fullEntity2DTO(viajeLogic.addItinerario(actual.getId(), viajeId));
        } catch (BusinesLogicException ex) {
            logger.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.BAD_REQUEST);
        }


    }

    /**
     * Remplaza las instancias de Itinerario asociadas a una instancia de Viaje
     *
     * @param viajeId Identificador de la instancia de Viaje
     * @param itinerarios Colección de instancias de ItinerarioDTO a asociar a
     * instancia de Viaje
     * @return Nueva colección de ItinerarioDTO asociada a la instancia de Viaje
     * @generated
     */
    @PUT
    public List<ItinerarioDTO> replaceItinerariosDeViaje(@PathParam("viajeId") Long viajeId, ArrayList<ItinerarioDTO> itinerarios) {
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
    @Path("{itinerarioId: \\d+}")
    public void removeItinerarioDeViaje(@PathParam("viajeId") Long viajeId, @PathParam("itinerarioId") Long itinerarioId) {
        try {
            viajeLogic.removeItinerario(viajeId, itinerarioId);
        } catch (BusinesLogicException ex) {
            Logger.getLogger(ItinerarioResource.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.BAD_REQUEST);
        }

    }















    // lista de cosas para la entidad hija



    /**
     * Obtiene una colección de instancias de paradaDTO asociadas a una
     * instancia de Itinerario
     *
     * @param itinerarioId Identificador de la instancia de Itinerario
     * @return Colección de instancias de ItinerarioDTO asociadas a la instancia
     * de Viaje
     * @generated
     */
    //@GET
    //@Path("{itinerarioId: \\d+}/paradas")
    public List<ParadaDTO> listParadas(@PathParam("itinerarioId") Long itinerarioId) {
        // falta este metodo
        List<ParadaEntity> paradas = itinerarioLogic.getParadas(itinerarioId);
        return ParadaConverter.listEntity2DTO(paradas);

    }



    /**
     * Obtiene un objeto de Parada asociada a un objeto de Itinerario
     *
     * @param itinerarioId Identificador del objeto de Itinerario
     * @param paradaId Identificador del objeto de Parada
     * @generated
     */
    @GET
    @Path("{itinerarioId: \\d+}/paradas/{paradaId: \\d+}")
    public ParadaDTO getParadas(@PathParam("itinerarioId") Long itinerarioId, @PathParam("paradaId") Long paradaId) {
        ParadaEntity parada = itinerarioLogic.getParada(itinerarioId, paradaId);
        return ParadaConverter.fullEntity2DTO(parada);
    }

    /**
     * Asocia un Parada existente a un Itinerario
     *
     * @param itinerarioId Identificador del objeto de Itinerario
     * @param paradaId Identificador del objeto de Parada
     * @return Objeto de ParadaDTO en representación full que fue asociado a
     * Itinerario
     * @generated
     */
    @POST
    @Path("{itinerarioId: \\d+}/paradas/{paradaId: \\d+}")
    public ParadaDTO addParadas(@PathParam("itinerarioId") Long itinerarioId, @PathParam("paradaId") Long paradaId) {
        try {
            ParadaEntity parada = itinerarioLogic.addParada(itinerarioId, paradaId);
            return ParadaConverter.fullEntity2DTO(parada);
        } catch (BusinesLogicException ex) {
            logger.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.BAD_REQUEST);
        }
    }

    /**
     * Remplaza los objetos de Parada asociados a un objeto de Itinerario
     *
     * @param itinerarioId Identificador del objeto de Itinerario
     * @param paradas Colección de objetos de ParadaDTO en representación
     * minimum a asociar a objeto de Itinerario
     * @return Nueva colección de ParadaDTO en representación Basic
     * @generated
     */
    @PUT
    @Path("{itinerarioId: \\d+}/paradas")
    public List<ParadaDTO> replaceParadas(@PathParam("itinerarioId") Long itinerarioId, List<ParadaDTO> paradas) {
        try {
            List<ParadaEntity> paradaList = ParadaConverter.listDTO2Entity(paradas);
            List<ParadaEntity> newParadas = itinerarioLogic.replaceParadas(paradaList, itinerarioId);
            return ParadaConverter.listEntity2DTO(newParadas);
        } catch (BusinesLogicException ex) {
            logger.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.BAD_REQUEST);
        }
    }

    /**
     * Desasocia un Parada existente de un Itinerario existente
     *
     * @param itinerarioId Identificador del objeto de Itinerario
     * @param paradaId Identificador del objeto de Parada
     * @generated
     */
    @DELETE
    @Path("{itinerarioId: \\d+}/paradas/{paradaId: \\d+}")
    public void removeParadas(@PathParam("itinerarioId") Long itinerarioId, @PathParam("paradaId") Long paradaId) {
        itinerarioLogic.removeParada(itinerarioId, paradaId);
    }

}
