package co.edu.uniandes.rest.mappers;

import co.edu.uniandes.rest.exceptions.LogicException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;



/**
 * Convertidor de Excepciones ItinerarioLogicException a mensajes REST.
 */
@Provider
public class LogicExceptionMapper implements ExceptionMapper<LogicException> {

	/**
	 * Generador de una respuesta a partir de una excepción
	 * @param ex excecpión a convertir a una respuesta REST
         * @return 
	 */
	@Override
	public Response toResponse(LogicException ex) {
		// retorna una respuesta
		return Response
				.status(Response.Status.NOT_FOUND)	// estado HTTP 404
				.entity(ex.getMessage())			// mensaje adicional
				.type("text/plain")
				.build();
	}
	
}
