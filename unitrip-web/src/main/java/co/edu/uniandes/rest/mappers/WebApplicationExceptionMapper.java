/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.mappers;

/**
 *
 * @author l.castro12
 */
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Convertidor de Excepciones BusinessLogicException a mensajes REST.
 */
@Provider
public class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException> {

    /**
     * Generador de una respuesta a partir de una excepción
     *
     * @param ex excecpión a convertir a una respuesta REST
     * @return Respuesta modificada
     */
    @Override
    public Response toResponse(WebApplicationException ex) {
        return Response.status(ex.getResponse().getStatus())
                .entity(ex.getMessage()) // mensaje adicional
                .type("text/plain")
                .build();
    }
}
