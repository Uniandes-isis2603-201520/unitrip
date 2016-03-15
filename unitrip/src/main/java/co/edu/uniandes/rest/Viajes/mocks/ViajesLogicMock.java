package co.edu.uniandes.rest.Viajes.mocks;

/**
 * Mock del recurso Viajes (Mock del servicio REST)
 * @author Asistente
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;


import co.edu.uniandes.rest.Viajes.dtos.ViajesDTO;
import co.edu.uniandes.rest.Viajes.exceptions.ViajesLogicException;

/*
 * ViajeLogicMock
 * Mock del recurso Viajes (Mock del servicio REST)
 */
@Named
@ApplicationScoped
public class ViajesLogicMock {
	
	// objeto para presentar logs de las operaciones
	private final static Logger logger = Logger.getLogger(ViajesLogicMock.class.getName());
	
	// listado de viajes
    private static ArrayList<ViajesDTO> viajes;

    /**
     * Constructor. Crea los datos de ejemplo.
     */
    public ViajesLogicMock() {

    	if (viajes == null) {
            viajes = new ArrayList<>();
            viajes.add(new ViajesDTO(1L, "Bogota", "lo mejor", "imagen"));
            viajes.add(new ViajesDTO(2L, "Cali", "la mejor", "image"));
            viajes.add(new ViajesDTO(3L, "Medellin","lo mejor", "image"));
        }
        
    	// indica que se muestren todos los mensajes
    	logger.setLevel(Level.INFO);
    	
    	// muestra información 
    	logger.info("Inicializa la lista de viajes");
    	logger.info("viajes" + viajes );
    }    
    
	/**
	 * Obtiene el listado de personas. 
	 * @return lista de ciudades
	 * @throws ViajesLogicException cuando no existe la lista en memoria  
	 */    
    public List<ViajesDTO> getViajes() throws ViajesLogicException {
    	if (viajes == null) {
    		logger.severe("Error interno: lista de viajes no existe.");
    		throw new ViajesLogicException("Error interno: lista de viajes no existe.");    		
    	}
    	
    	logger.info("retornando todas las viajes");
    	return viajes;
    }

    /**
     * Obtiene un viaje
     * @param id identificador del viaje
     * @return viaje encontrado
     * @throws ViajesLogicException cuando el viaje no existe
     */
    public ViajesDTO getViaje(Long id) throws ViajesLogicException {
    	logger.info("recibiendo solicitud de viaje con id " + id);
    	
    	// busca la ciudad con el id suministrado
        for (ViajesDTO viaje : viajes) {
            if (Objects.equals(viaje.getId(), id)){
            	logger.info("retornando viaje " + viaje);
                return viaje;
            }
        }
        
        // si no encuentra el viaje
        logger.severe("No existe viaje con ese id");
        throw new ViajesLogicException("No existe viaje con ese id");
    }

    /**
     * Agrega un vijae a la lista.
     * @param newViaje viaje a adicionar
     * @throws ViajesLogicException cuando ya existe un viaje con el id suministrado
     * @return ciudad agregada
     */
    public ViajesDTO createViaje(ViajesDTO newViaje) throws ViajesLogicException {
    	logger.info("recibiendo solicitud de agregar viaje " + newViaje);
    	
    	// la nueva ciudad tiene id ?
    	if ( newViaje.getId() != null ) {
	    	// busca la ciudad con el id suministrado
	        for (ViajesDTO viaje : viajes) {
	        	// si existe una ciudad con ese id
	            if (Objects.equals(viaje.getId(), newViaje.getId())){
	            	logger.severe("Ya existe un viaje con ese id");
	                throw new ViajesLogicException("Ya existe un viaje con ese id");
	            }
	        }
	        
	    // el nuevo viaje no tiene id ? 
    	} else {

    		// genera un id para el viaje
    		logger.info("Generando id para lel nuevo viaje");
    		long newId = 1;
	        for (ViajesDTO viaje : viajes) {
	            if (newId <= viaje.getId()){
	                newId =  viaje.getId() + 1;
	            }
	        }
	        newViaje.setId(newId);
    	}
    	
        // agrega el viaje
    	logger.info("agregando viaje " + newViaje);
        viajes.add(newViaje);
        return newViaje;
    }

    /**
     * Actualiza los datos de una ciudad
     * @param id identificador de la ciudad a modificar
     * @param updatedViaje viaje a modificar
     * @return datos del viaje modificada 
     * @throws ViajesLogicException cuando no existe un viaje con el id suministrado
     */
    public ViajesDTO updateViaje(Long id, ViajesDTO updatedViaje) throws ViajesLogicException {
    	logger.info("recibiendo solictud de modificar viaje " + updatedViaje);
    	
    	// busca el viaje con el id suministrado
        for (ViajesDTO viaje : viajes) {
            if (Objects.equals(viaje.getId(), id)) {
            	
            	// modifica el viaje
            	viaje.setId(updatedViaje.getId());
                viaje.setName(updatedViaje.getName());
                
                // retorna la ciudad modificada
            	logger.info("Modificando viaje " + viaje);
                return viaje;
            }
        }
        
        // no encontró el viaje con ese id ?
        logger.severe("No existe un viaje con ese id");
        throw new ViajesLogicException("No existe un viaje con ese id");
    }

    /**
     * Elimina los datos de un viaje
     * @param id identificador del viaje a eliminar
     * @throws ViajesLogicException cuando no existe un viaje con el id suministrado
     */
    public void deleteViaje(Long id) throws ViajesLogicException {
    	logger.info("recibiendo solictud de eliminar un viaje con id " + id);
    	
    	// busca el viaje con el id suministrado
        for (ViajesDTO viaje : viajes) {
            if (Objects.equals(viaje.getId(), id)) {
            	
            	// elimina el viaje
            	logger.info("eliminando viaje " + viaje);
                viajes.remove(viaje);
                return;
            }
        }

        // no encontró el viaje con ese id ?
        logger.severe("No existe un viaje con ese id");
        throw new ViajesLogicException("No existe un viaje con ese id");
    }
}
