/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.providers;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
/**
 *
 * @author n.vasquez10
 */
@StatusCreated
@Provider
public class CreatedFilter implements ContainerResponseFilter  {

    @Override
    public void filter(ContainerRequestContext req, ContainerResponseContext resp) throws IOException {
        if (resp.getStatus() == HttpServletResponse.SC_OK) {
            resp.setStatus(HttpServletResponse.SC_CREATED);
        }
    }
}
