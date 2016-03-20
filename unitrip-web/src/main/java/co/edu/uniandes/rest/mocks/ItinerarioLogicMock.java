package co.edu.uniandes.rest.mocks;

import co.edu.uniandes.rest.dtos.ItinerarioDTO;
import co.edu.uniandes.rest.exceptions.LogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/*
 * ItinerarioLogicMock
 */
@Named
@ApplicationScoped
public class ItinerarioLogicMock {
	
	// objeto para presentar logs de las operaciones
	private final static Logger logger = Logger.getLogger(ItinerarioLogicMock.class.getName());
	
	// listado de Itinerarios
    private static List<ItinerarioDTO> itinerarios;

    /**
     * Constructor. Crea los datos de ejemplo.
     */
    public ItinerarioLogicMock() {

    	if (itinerarios == null) {
            itinerarios = new ArrayList<>();
            itinerarios.add(new ItinerarioDTO(1L, "Bogota","Muy bueno","2016-10-12","2016-10-12"));
            itinerarios.add(new ItinerarioDTO(2L, "Cali","Muy bueno","2016-10-12","2016-10-12"));
            itinerarios.add(new ItinerarioDTO(3L, "Medellin","Muy bueno","2016-10-12","2016-10-12"));
        }
        
    	// indica que se muestren todos los mensajes
    	logger.setLevel(Level.INFO);
    	
    	// muestra información 
    	logger.info("Inicializa la lista de Itinerarios");
    	logger.info("Itinerarios" + itinerarios );
    }    
    
	/**
	 * Obtiene el listado de Itinerarios. 
	 * @return lista de Itinerarios
	 * @throws LogicException cuando no existe la lista en memoria  
	 */
    public List<ItinerarioDTO> getItinerarios() throws LogicException {
    	if (itinerarios == null) {
    		logger.severe("Error interno: lista de Itinerarios no existe.");
    		throw new LogicException("Error interno: lista de Itinerarios no existe.");    		
    	}
    	
    	logger.info("retornando todas los Itinerarios");
    	return itinerarios;
    }

    /**
     * Obtiene un Itinerarios
     * @param id identificador del Itinerario
     * @return Itinerarios encontrado
     * @throws LogicException cuando el Itinerario no existe
     */
    public ItinerarioDTO getItinerario(Long id) throws LogicException {
    	logger.info("recibiendo solicitud de Itinerario con id " + id);
    	
    	// busca el Itinerario con el id suministrado
        for (ItinerarioDTO itinerario : itinerarios) {
            if (Objects.equals(itinerario.getId(), id)){
            	logger.info("retornando itinerario " + itinerario);
                return itinerario;
            }
        }
        
        // si no encuentra el Itinerario
        logger.severe("No existe Itinerario con ese id");
        throw new LogicException("No existe Itinerario con ese id");
    }

    /**
     * Agrega un Itinerario a la lista.
     * @param newItinerario Itinerario a adicionar
     * @throws LogicException cuando ya existe un Itinerario con el id suministrado
     * @return Itinerario agregado
     */
    public ItinerarioDTO createItinerario(ItinerarioDTO newItinerario) throws LogicException {
    	logger.info("recibiendo solicitud de agregar Itinerario " + newItinerario);
    	
    	// el nuevo Itinerario tiene id ?
    	if ( newItinerario.getId() != null ) {
	    	// busca la ciudad con el id suministrado
	        for (ItinerarioDTO itinerario : itinerarios) {
	        	// si existe un Itinerario con ese id
	            if (Objects.equals(itinerario.getId(), newItinerario.getId())){
	            	logger.severe("Ya existe un Itinerario con ese id");
	                throw new LogicException("Ya existe un Itinerario con ese id");
	            }
	        }
	        
	    // el nuevo Itinerario no tiene id ? 
    	} else {

    		// genera un id para el Itinerario
    		logger.info("Generando id para el nueva Itinerario");
    		long newId = 1;
	        for (ItinerarioDTO itinerario : itinerarios) {
	            if (newId <= itinerario.getId()){
	                newId =  itinerario.getId() + 1;
	            }
	        }
	        newItinerario.setId(newId);
    	}
    	
        // agrega el Itinerario
    	logger.info("agregando Itinerario " + newItinerario);
        itinerarios.add(newItinerario);
        return newItinerario;
    }

    /**
     * Actualiza los datos de un Itinerario
     * @param id identificador del Itinerario a modificar
     * @param updatedItinerario Itinerario a modificar
     * @return datos del Itinerario modificada 
     * @throws LogicException cuando no existe un Itinerario con el id suministrado
     */
    public ItinerarioDTO updateItinerario(Long id, ItinerarioDTO updatedItinerario) throws LogicException {
    	logger.info("recibiendo solictud de modificar Itinerario " + updatedItinerario);
    	
    	// busca el Itinerario con el id suministrado
        for (ItinerarioDTO itinerario : itinerarios) {
            if (Objects.equals(itinerario.getId(), id)) {
            	
            	// modifica el Itinerario
            	itinerario.setId(updatedItinerario.getId());
                itinerario.setName(updatedItinerario.getName());
                
                // retorna el Itinerario modificada
            	logger.info("Modificando Itinerario " + itinerario);
                return itinerario;
            }
        }
        
        // no encontró el Itinerario con ese id ?
        logger.severe("No existe un Itinerario con ese id");
        throw new LogicException("No existe un Itinerario con ese id");
    }

    /**
     * Elimina los datos de un viaje
     * @param id identificador del viaje a eliminar
     * @throws LogicException cuando no existe un itinerario con el id suministrado
     */
    public void deleteItinerario(Long id) throws LogicException {
    	logger.info("recibiendo solictud de eliminar un itinerario con id " + id);
    	
    	// busca el itinerario con el id suministrado
        for (ItinerarioDTO itinerario : itinerarios) {
            if (Objects.equals(itinerario.getId(), id)) {
            	
            	// elimina el itinerario
            	logger.info("eliminandoitinerario " + itinerario);
                itinerarios.remove(itinerario);
                return;
            }
        }

        // no encontró el itinerario con ese id ?
        logger.severe("No existe un itinerario con ese id");
        throw new LogicException("No existe un itinerario con ese id");
    }
  
}