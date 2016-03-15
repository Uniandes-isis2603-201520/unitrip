package co.edu.uniandes.rest.Viajes.resources;

import co.edu.uniandes.rest.Viajes.converters.ViajesConverter;
import co.edu.uniandes.rest.Viajes.dtos.ViajesDTO;
import co.edu.uniandes.rest.Viajes.exceptions.ViajesLogicException;
import co.edu.uniandes.rest.Viajes.mocks.ViajesLogicMock;
//
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;



@Path("viajes")
@Produces("application/json")
public class ViajesResource {

    @Inject
    private ViajesLogicMock viajeLogic;

    /**
     * Obtiene la lista de los registros de Viajes.
     *
     * @return Colección de objetos de ViajesDTO cada uno con sus respectivos
     * Review
     * @throws ViajesLogicException Lanza excepcion cuando no hay viajes
     * @generated
     *
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
     *
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
     *
    @POST
    public ViajesDTO createViaje(ViajesDTO dto) {
        ViajesEntity entity = BookConverter.fullDTO2Entity(dto);
        return BookConverter.fullEntity2DTO(bookLogic.createBook(entity));
    }

    /**
     * Actualiza la información de una instancia de Book.
     *
     * @param id Identificador de la instancia de Book a modificar
     * @param dto Instancia de BookDTO con los nuevos datos.
     * @return Instancia de BookDTO con los datos actualizados.
     * @generated
     *
    @PUT
    @Path("{id: \\d+}")
    public BookDTO updateBook(@PathParam("id") Long id, BookDTO dto) {
        BookEntity entity = BookConverter.fullDTO2Entity(dto);
        entity.setId(id);
        return BookConverter.fullEntity2DTO(bookLogic.updateBook(entity));
    }

    /**
     * Elimina una instancia de Book de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @generated
     *
    @DELETE
    @Path("{id: \\d+}")
    public void deleteBook(@PathParam("id") Long id) {
        bookLogic.deleteBook(id);
    }

    /**
     * Obtiene una colección de instancias de AuthorDTO asociadas a una
     * instancia de Book
     *
     * @param bookId Identificador de la instancia de Book
     * @return Colección de instancias de AuthorDTO asociadas a la instancia de
     * Book
     * @generated
     *
    @GET
    @Path("{bookId: \\d+}/authors")
    public List<AuthorDTO> listAuthors(@PathParam("bookId") Long bookId) {
        return AuthorConverter.listEntity2DTO(bookLogic.getAuthors(bookId));
    }

    /**
     * Obtiene una instancia de Author asociada a una instancia de Book
     *
     * @param bookId Identificador de la instancia de Book
     * @param authorId Identificador de la instancia de Author
     * @generated
     *
    @GET
    @Path("{bookId: \\d+}/authors/{authorId: \\d+}")
    public AuthorDTO getAuthors(@PathParam("bookId") Long bookId, @PathParam("authorId") Long authorId) {
        return AuthorConverter.fullEntity2DTO(bookLogic.getAuthor(bookId, authorId));
    }

    /**
     * Asocia un Author existente a un Book
     *
     * @param bookId Identificador de la instancia de Book
     * @param authorId Identificador de la instancia de Author
     * @return Instancia de AuthorDTO que fue asociada a Book
     * @generated
     *
    @POST
    @Path("{bookId: \\d+}/authors/{authorId: \\d+}")
    public AuthorDTO addAuthors(@PathParam("bookId") Long bookId, @PathParam("authorId") Long authorId) {
        return AuthorConverter.fullEntity2DTO(bookLogic.addAuthor(bookId, authorId));
    }

    /**
     * Remplaza las instancias de Author asociadas a una instancia de Book
     *
     * @param bookId Identificador de la instancia de Book
     * @param authors Colección de instancias de AuthorDTO a asociar a instancia
     * de Book
     * @return Nueva colección de AuthorDTO asociada a la instancia de Book
     * @generated
     *
    @PUT
    @Path("{bookId: \\d+}/authors")
    public List<AuthorDTO> replaceAuthors(@PathParam("bookId") Long bookId, List<AuthorDTO> authors) {
        return AuthorConverter.listEntity2DTO(bookLogic.replaceAuthors(AuthorConverter.listDTO2Entity(authors), bookId));
    }

    /**
     * Desasocia un Author existente de un Book existente
     *
     * @param bookId Identificador de la instancia de Book
     * @param authorId Identificador de la instancia de Author
     * @generated
     *
    @DELETE
    @Path("{bookId: \\d+}/authors/{authorId: \\d+}")
    public void removeAuthors(@PathParam("bookId") Long bookId, @PathParam("authorId") Long authorId) {
        bookLogic.removeAuthor(bookId, authorId);
    }
    */
}
