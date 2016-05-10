/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.mappers;

import javax.ejb.EJBException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
/**
 *
 * @author n.vasquez10
 */
public class EJBExceptionMapper implements ExceptionMapper<EJBException>{


    @Override
    public Response toResponse(EJBException exception) {
        return Response.serverError()
                .entity(getInitCause(exception).getLocalizedMessage())
                .type(MediaType.TEXT_PLAIN_TYPE)
                .build();
    }

    /**
     * Recursively retrieves the root cause of an exception.
     *
     * @param e Thrown exception
     * @return Root cause
     */
    private Throwable getInitCause(Throwable e) {
        if (e.getCause() != null) {
            return getInitCause(e.getCause());
        } else {
            return e;
        }
    }
}
