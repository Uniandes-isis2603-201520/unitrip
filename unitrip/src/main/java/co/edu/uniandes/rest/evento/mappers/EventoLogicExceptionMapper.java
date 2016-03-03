/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.evento.mappers;

/**
 *
 * @author l.castro12
 */
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import co.edu.uniandes.rest.evento.exceptions.EventoLogicException;

/**
 * Convertidor de Excepciones EventoLogicException a mensajes REST.
 */
@Provider
public class EventoLogicExceptionMapper implements ExceptionMapper<EventoLogicException> {

	/**
	 * Generador de una respuesta a partir de una excepción
	 * @param ex excecpión a convertir a una respuesta REST
	 */
	@Override
	public Response toResponse(EventoLogicException ex) {
		// retorna una respuesta
		return Response
				.status(Response.Status.NOT_FOUND)	// estado HTTP 404
				.entity(ex.getMessage())			// mensaje adicional
				.type("text/plain")
				.build();
	}

}
