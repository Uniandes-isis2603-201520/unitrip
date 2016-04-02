package co.edu.uniandes.rest.resources;


import co.edu.uniandes.csw.unitrip.api.IParadaLogic;
import co.edu.uniandes.csw.unitrip.entities.ParadaEntity;
import co.edu.uniandes.csw.unitrip.exceptions.BusinesLogicException;
import co.edu.uniandes.rest.converters.ParadaConverter;
import co.edu.uniandes.rest.dtos.ParadaDTO;
import co.edu.uniandes.rest.exceptions.LogicException;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;


@Path("paradas")
@Produces("application/json")
public class ParadaResource {

    @Inject
    private IParadaLogic paradaLogic;


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
        return ParadaConverter.listEntity2DTO(paradaLogic.getParadas());

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
        try{
        return ParadaConverter.fullEntity2DTO(paradaLogic.getParada(id));
        }
        catch(BusinesLogicException ex){
            throw new WebApplicationException(ex.getLocalizedMessage(),ex,Response.Status.NOT_FOUND);
        }

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
        ParadaEntity entity = ParadaConverter.fullDTO2Entity(dto);
        return ParadaConverter.fullEntity2DTO(paradaLogic.createParada(entity));

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
    public ParadaDTO updateParada(@PathParam("id") Long id,ParadaDTO dto) throws LogicException {
        ParadaEntity entity = ParadaConverter.fullDTO2Entity(dto);
        entity.setId(id);
        try{
        ParadaEntity oldEntity = paradaLogic.getParada(id);
        }
        catch(BusinesLogicException ex){
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.NOT_FOUND);
        }
        return ParadaConverter.fullEntity2DTO(paradaLogic.updateParada(entity));
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
    public void deleteParada(@PathParam("id") Long id) throws LogicException {
        paradaLogic.deleteParada(id);
    }
}