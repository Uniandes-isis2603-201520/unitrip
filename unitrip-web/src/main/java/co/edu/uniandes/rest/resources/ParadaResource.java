package co.edu.uniandes.rest.resources;

import co.edu.uniandes.csw.unitrip.api.IItinerarioLogic;
import co.edu.uniandes.csw.unitrip.api.IParadaLogic;
import co.edu.uniandes.csw.unitrip.entities.EventoEntity;
import co.edu.uniandes.csw.unitrip.entities.ParadaEntity;
import co.edu.uniandes.csw.unitrip.entities.ItinerarioEntity;
import co.edu.uniandes.csw.unitrip.exceptions.BusinesLogicException;
import co.edu.uniandes.rest.converters.EventoConverter;
import co.edu.uniandes.rest.converters.ParadaConverter;
import co.edu.uniandes.rest.dtos.EventoDTO;
import co.edu.uniandes.rest.dtos.ParadaDTO;
import co.edu.uniandes.rest.exceptions.LogicException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("viajeros/{viajeroId: \\d+}/viajes/{viajeId: \\d+}/itinerarios/{itinerarioId: \\d+}/paradas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ParadaResource {

    private static final Logger logger = Logger.getLogger(ParadaResource.class.getName());

    @Inject
    private IParadaLogic paradaLogic;

    @Inject
    private IItinerarioLogic itinerarioLogic;

    /**
     * Obtiene la lista de los registros de Parada.
     *
     * @return Colección de objetos de ParadaDTO cada uno con sus respectivos
     * Review
     * @generated
     */
    //@GET
    public List<ParadaDTO> getParadas() {
        logger.info("Se ejecuta método getParadas");
        List<ParadaEntity> paradas = paradaLogic.getParadas();
        return ParadaConverter.listEntity2DTO(paradas);
    }

    /**
     * Obtiene los datos de un objeto de Parada a partir de su ID.
     *
     * @param id Identificador del objeto a consultar
     * @return Instancia de ParadaDTO con los datos del Parada consultado y sus
     * Review
     * @generated
     */
    //@GET
    //@Path("{id: \\d+}")
    public ParadaDTO getParada(@PathParam("id") Long id) {
        logger.log(Level.INFO, "Se ejecuta método getParada con id={0}", id);
        ParadaEntity parada = paradaLogic.getParada(id);
        return ParadaConverter.fullEntity2DTO(parada);
    }

    /**
     * Se encarga de crear un parada en la base de datos.
     *
     * @param dto Objeto de ParadaDTO con los datos nuevos
     * @return Objeto de ParadaDTO con los datos nuevos y su ID.
     * @generated
     */
    //@StatusCreated
    //@POST
    public ParadaDTO createParada(ParadaDTO dto) {
        logger.info("Se ejecuta método createParada");
        ParadaEntity entity = ParadaConverter.fullDTO2Entity(dto);
        ParadaEntity newEntity;
        try {
            newEntity = paradaLogic.createParada(entity);
        } catch (BusinesLogicException ex) {
            logger.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.BAD_REQUEST);
        }
        return ParadaConverter.fullEntity2DTO(newEntity);
    }

    /**
     * Actualiza la información de un objeto de Parada.
     *
     * @param id Identificador del objeto de Parada a modificar
     * @param dto Representación Basic de parada con los nuevos datos
     * @return Instancia de ParadaDTO con los datos actualizados.
     * @generated paradaDTO, paradaId, itinerarioId
     */
    //@PUT
    //@Path("{id: \\d+}")
    public ParadaDTO updateParada(@PathParam("idParada") Long paradaId, ParadaDTO dto, Long itinerarioId) {
        logger.log(Level.INFO, "Se ejecuta método updateParada con id={0}", paradaId);
        ParadaEntity entity = ParadaConverter.fullDTO2Entity(dto);
        entity.setId(paradaId);

        try {
            ItinerarioEntity itinerarioEntity = itinerarioLogic.getItinerario(itinerarioId);
            entity.setItinerario(itinerarioEntity);
            ParadaEntity oldEntity = paradaLogic.getParada(paradaId);
            entity.setEventos(oldEntity.getEventos());
            ParadaEntity savedParada = paradaLogic.updateParada(entity);
            return ParadaConverter.fullEntity2DTO(savedParada);
        } catch (BusinesLogicException ex) {
            logger.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.BAD_REQUEST);
        }
    }

    /**
     * Elimina un objeto de Parada de la base de datos.
     *
     * @param id Identificador del objeto a eliminar.
     * @generated
     */
    //@DELETE
    //@Path("{id: \\d+}")
    public void deleteParada(@PathParam("id") Long id) {
        logger.log(Level.INFO, "Se ejecuta método deleteParada con id={0}", id);
        paradaLogic.deleteParada(id);
    }

    // metodos

    /**
     * Obtiene una colección de instancias de paradaDTO asociadas a una
     * instancia de Itinerario
     *
     * @param itinerarioId Identificador de la instancia de Itinerario
     * @return Colección de instancias de ItinerarioDTO asociadas a la instancia
     * de Viaje
     * @generated
     */
    @GET
    public List<ParadaDTO> listParadasDeItinerario(@PathParam("itinerarioId") Long itinerarioId) {
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
    @Path("{paradaId: \\d+}")
    public ParadaDTO getParadaDeItinerario(@PathParam("itinerarioId") Long itinerarioId, @PathParam("paradaId") Long paradaId) {
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
    public ParadaDTO addParadaAItinerario(ParadaDTO paradaDTO, @PathParam("itinerarioId") Long itinerarioId) {
        try {
            ParadaEntity parada = ParadaConverter.fullDTO2Entity(paradaDTO);
            ParadaEntity actual = paradaLogic.createParada(parada);
            return ParadaConverter.fullEntity2DTO(itinerarioLogic.addParada(actual.getId(), itinerarioId));
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
    @Path("{paradaId: \\d+}")
    public void removeParadaDeItinerario(@PathParam("itinerarioId") Long itinerarioId, @PathParam("paradaId") Long paradaId) {
        itinerarioLogic.removeParada( paradaId, itinerarioId);
    }

    /**
     * Update de parada
     * @param paradaId
     * @return
     */
    @PUT
    @Path("{paradaId: \\d+}")
    public ParadaDTO updateParadaDeItinerario(ParadaDTO paradaDTO,
            @PathParam("itinerarioId") Long itinerarioId, @PathParam("paradaId") Long paradaId){
        return updateParada(paradaId,paradaDTO, itinerarioId);
    }


    // metodos que relacionan evento con parada, son mas parecidos a author-book

     /**
     * Obtiene una colección de objetos de EventoDTO asociados a un objeto de
     * Parada
     *
     * @param paradaId Identificador del objeto de Parada
     * @return Colección de objetos de EventoDTO en representación basic
     * @generated
     */
    @GET
    @Path("{paradaId: \\d+}/eventos")
    public List<EventoDTO> listEventosDeParada(@PathParam("paradaId") Long paradaId) {
        List<EventoEntity> eventos = paradaLogic.getEventos(paradaId);
        return EventoConverter.listEntity2DTO(eventos);
    }

    /**
     * Obtiene un objeto de Evento asociada a un objeto de Parada
     *
     * @param paradaId Identificador del objeto de Parada
     * @param eventoId Identificador del objeto de Evento
     * @generated
     */
    @GET
    @Path("{paradaId: \\d+}/eventos/{eventoId: \\d+}")
    public EventoDTO getEventoDeParada(@PathParam("paradaId") Long paradaId, @PathParam("eventoId") Long eventoId) {
        EventoEntity evento = paradaLogic.getEvento(paradaId, eventoId);
        return EventoConverter.fullEntity2DTO(evento);
    }

    /**
     * Asocia un Evento existente a un Parada
     *
     * @param paradaId Identificador del objeto de Parada
     * @param eventoId Identificador del objeto de Evento
     * @return Objeto de EventoDTO en representación full que fue asociado a
     * Parada
     * @generated
     */
    @POST
    @Path("{paradaId: \\d+}/eventos/{eventoId: \\d+}")
    public EventoDTO addEventoAParada(@PathParam("paradaId") Long paradaId, @PathParam("eventoId") Long eventoId) {
        try {
            EventoEntity evento = paradaLogic.addEvento(eventoId, paradaId);
            return EventoConverter.fullEntity2DTO(evento);

        } catch (BusinesLogicException ex) {
            logger.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.BAD_REQUEST);
        }
    }

    /**
     * Remplaza los objetos de Evento asociados a un objeto de Parada
     *
     * @param paradaId Identificador del objeto de Parada
     * @param eventos Colección de objetos de EventoDTO en representación
     * minimum a asociar a objeto de Parada
     * @return Nueva colección de EventoDTO en representación Basic
     * @generated
     */
    @PUT
    @Path("{itinerarioId: \\d+}/paradas/{paradaId: \\d+}/eventos")
    public List<EventoDTO> replaceEventos(@PathParam("paradaId") Long paradaId, List<EventoDTO> eventos) {
        try {
            List<EventoEntity> eventoList = EventoConverter.listDTO2Entity(eventos);
            List<EventoEntity> newEventos = paradaLogic.replaceEventos(eventoList, paradaId);
            return EventoConverter.listEntity2DTO(newEventos);
        } catch (BusinesLogicException ex) {
            logger.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.BAD_REQUEST);
        }
    }

    /**
     * Desasocia un Evento existente de un Parada existente
     *
     * @param paradaId Identificador del objeto de Parada
     * @param eventoId Identificador del objeto de Evento
     * @generated
     */
    @DELETE
    @Path("{paradaId: \\d+}/eventos/{eventoId: \\d+}")
    public void removeEventoDeParada(@PathParam("paradaId") Long paradaId, @PathParam("eventoId") Long eventoId) {
        paradaLogic.removeEvento(eventoId, paradaId);
    }












    /**
     * @GET public List<ParadaDTO> getParadas() { return
     * ParadaConverter.listEntity2DTO(paradaLogic.getParadas());
     *
     * }
     *
     *
     * @GET
     * @Path("{id: \\d+}") public ParadaDTO getParada(@PathParam("id") Long id)
     * throws LogicException { try{ return
     * ParadaConverter.fullEntity2DTO(paradaLogic.getParada(id)); }
     * catch(BusinesLogicException ex){ throw new
     * WebApplicationException(ex.getLocalizedMessage(),ex,Response.Status.NOT_FOUND);
     * }
     *
     * }
     *
     *
     * @POST public ParadaDTO createParada(ParadaDTO dto) throws LogicException
     * { ParadaEntity entity = ParadaConverter.fullDTO2Entity(dto); return
     * ParadaConverter.fullEntity2DTO(paradaLogic.createParada(entity));
     *
     * }
     *
     *
     * @PUT
     * @Path("{id: \\d+}") public ParadaDTO updateParada(@PathParam("id") Long
     * id,ParadaDTO dto) throws LogicException { ParadaEntity entity =
     * ParadaConverter.fullDTO2Entity(dto); entity.setId(id); try{ ParadaEntity
     * oldEntity = paradaLogic.getParada(id); } catch(BusinesLogicException ex){
     * throw new WebApplicationException(ex.getLocalizedMessage(), ex,
     * Response.Status.NOT_FOUND); } return
     * ParadaConverter.fullEntity2DTO(paradaLogic.updateParada(entity)); }
     *
     *
     * @DELETE
     * @Path("{id: \\d+}") public void deleteParada(@PathParam("id") Long id)
     * throws LogicException { paradaLogic.deleteParada(id); }
     *
     */
}
