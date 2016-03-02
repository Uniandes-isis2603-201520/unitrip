/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cities.resources;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
 import javax.ws.rs.Produces;
/**
 *
 * @author je.molano1498
 */
@Path("viajes")
@Produces("application/json")
public class viajesResources {

    @GET
    public List<ViajesDTO> getViajes () {
        return null;
    }




}
