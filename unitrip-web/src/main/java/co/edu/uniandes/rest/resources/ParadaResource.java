package co.edu.uniandes.rest.resources;


import co.edu.uniandes.rest.dtos.EventoDTO;
import co.edu.uniandes.rest.dtos.ParadaDTO;
import co.edu.uniandes.rest.exceptions.LogicException;
import co.edu.uniandes.rest.mocks.ParadaLogicMock;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;


@Path("paradas")
@Produces("application/json")
public class ParadaResource {

    //@Inject
    //private IParadaLogic paradaLogic;
    private ParadaLogicMock paradaLogic;
    /**
     * Obtiene la lista de los registros de paradas.
     *
     * @return Colección de objetos de paradaDTO cada uno con sus respectivos
     * Review
     * @throws LogicException Lanza excepcion cuando no hay viajes
     * @generated
     */
    @GET
    public List<ParadaDTO> getParadas() throws LogicException {
        //return ParadaConverter.listEntity2DTO(paradaLogic.getViajes());
        return paradaLogic.getParadas();
    }

    /**
     * Obtiene los datos de una instancia de Parada a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de paradaDTO con los datos de la Parada consultado y sus
     * @throws LogicException en caso tal que no exista el viaje
     * @generated
     */
    @GET
    @Path("{id: \\d+}")
    public ParadaDTO getParada(@PathParam("id") Long id) throws LogicException {
        //return ParadaConverter.fullEntity2DTO(paradaLogic.getParada(id));
        return paradaLogic.getParada(id);
    }

    /**
     * Se encarga de crear una parada en la base de datos.
     *
     * @param dto Objeto de paradaDTO con los datos nuevos
     * @return Objeto de paradaDTO con los datos nuevos y su ID.
     * @throws LogicException en caso que ya exista el viaje
     * @generated
     */
    @POST
    public ParadaDTO createParada(ParadaDTO dto) throws LogicException {
        //ViajeEntity entity = ViajesConverter.fullDTO2Entity(dto);
        //return ViajesConverter.fullEntity2DTO(viajeLogic.createViaje(entity));
        return paradaLogic.createParada(dto);
    }

    /**
     * Actualiza la información de una instancia de Parada.
     *
     * @param id Identificador de la instancia de Parada a modificar
     * @param dto Instancia de paradaDTO con los nuevos datos.
     * @return Instancia de paradaDTO con los datos actualizados.
     * @throws LogicException en caso que no exista la parada
     * @generated
     */
    @PUT
    @Path("{id: \\d+}")
    public ParadaDTO updateViaje(@PathParam("id") Long id,ParadaDTO dto) throws LogicException {
        //ParadaEntity entity = ParadaConverter.fullDTO2Entity(dto);
        //entity.setId(id);
        //return ParadaConverter.fullEntity2DTO(paradaLogic.updateParada(entity));
        return paradaLogic.updateParada(id, dto);
    }

    /**
     * Elimina una instancia de parada de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @throws LogicException en caso que no exista la parada
     * @generated
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteViaje(@PathParam("id") Long id) throws LogicException {
        paradaLogic.deleteParada(id);
    }

    /**
     * Obtiene una colección de instancias de EventoDTO asociadas a una
     * instancia de parada
     *
     * @param paradaId Identificador de la instancia de Parada
     * @return Colección de instancias de EventoDTO asociadas a la instancia de
     * Parada
     * @generated
     */
    @GET
    @Path("{paradaId: \\d+}/eventos")
    public List<EventoDTO> listEventos(@PathParam("paradaId") Long paradaId) {
        //return EventoConverter.listEntity2DTO(paradaLogic.getEventos(paradaId));
        return null;
    }

    /**
     * Obtiene una instancia de Evento asociada a una instancia de Parada
     *
     * @param paradaId Identificador de la instancia de Parada
     * @param eventoId Identificador de la instancia de ItinerarioEvento
     * @return evento solicitado
     * @generated
     */
    @GET
    @Path("{paradaId: \\d+}/eventos/{eventoId: \\d+}")
    public EventoDTO getEvento(@PathParam("paradaId") Long paradaId, @PathParam("eventoId") Long eventoId) {
        //return EventoConverter.fullEntity2DTO(paradaLogic.getEvento(paradaId, eventoId));
        return null;
    }

    /**
     * Asocia una Evento existente a una Parada
     *
     * @param paradaId Identificador de la instancia de Parada
     * @param eventoId Identificador de la instancia de Evento
     * @return Instancia de EventoDTO que fue asociada a parada
     * @generated
     */
    @POST
    @Path("{paradaId: \\d+}/eventos/{eventoId: \\d+}")
    public EventoDTO addItinerario(@PathParam("paradaId") Long paradaId, @PathParam("eventoId") Long eventoId) {
        //return EventoConverter.fullEntity2DTO(viajeLogic.addEvento(paradaId, eventoId));
        return null;
    }

    /**
     * Remplaza las instancias de Evento asociadas a una instancia de Parada
     *
     * @param paradaId Identificador de la instancia de Parada
     * @param eventos Colección de instancias de EventoDTO a asociar a instancia
     * de Parada
     * @return Nueva colección de EventoDTO asociada a la instancia de Parada
     * @generated
     */
    @PUT
    @Path("{paradaId: \\d+}/eventos")
    public List<EventoDTO> replaceEventos(@PathParam("paradaId") Long paradaId, ArrayList<EventoDTO> eventos) {
        //return EventoConverter.listEntity2DTO(paradaLogic.replaceEventos(EventoConverter.listDTO2Entity(eventos), paradaId));
        return null;
    }

    /**
     * Desasocia un Evento existente de una Parada existente
     *
     * @param paradaId Identificador de la instancia de Parada
     * @param eventoId Identificador de la instancia de Evento
     * @generated
     */
    @DELETE
    @Path("{paradaId: \\d+}/eventos/{eventoId: \\d+}")
    public void removeEvento(@PathParam("paradaId") Long paradaId, @PathParam("eventoId") Long eventoId) {
        //paradaLogic.removeEvento(paradaId, eventoId);
        
    }

}
