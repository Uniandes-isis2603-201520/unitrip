package co.edu.uniandes.rest.parada.mocks;

/**
 * Mock del recurso parada (Mock del servicio REST)
 * @author Asistente
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;


import co.edu.uniandes.rest.parada.dtos.paradaDTO;
import co.edu.uniandes.rest.parada.exceptions.paradaLogicException;

/*
 * ViajeLogicMock
 * Mock del recurso parada (Mock del servicio REST)
 */
@Named
@ApplicationScoped
public class paradaLogicMock {
	
	// objeto para presentar logs de las operaciones
	private final static Logger logger = Logger.getLogger(paradaLogicMock.class.getName());
	
	// listado de parada
    private static ArrayList<paradaDTO> paradas;

    /**
     * Constructor. Crea los datos de ejemplo.
     */
    public paradaLogicMock() {

    	if (paradas == null) {
            paradas = new ArrayList<>();
            paradas.add(new paradaDTO(1L, "Bogota", 1L, 1L,"lo mejor","2016-10-12","2016-10-12"));
            paradas.add(new paradaDTO(2L, "Cali", 1L, 1L,"lo mejor","2016-10-12","2016-10-12"));
            paradas.add(new paradaDTO(3L, "Cartagena", 1L, 1L,"lo mejor","2016-10-12","2016-10-12"));
            paradas.add(new paradaDTO(4L, "Yopal", 1L, 1L,"lo mejor","2016-10-12","2016-10-12"));
        }
        
    	// indica que se muestren todos los mensajes
    	logger.setLevel(Level.INFO);
    	
    	// muestra información 
    	logger.info("Inicializa la lista de paradas");
    	logger.info("paradas" + paradas );
    }    
    
	/**
	 * Obtiene el listado de paradas. 
	 * @return lista de paradas
	 * @throws paradaLogicException cuando no existe la lista en memoria  
	 */    
    public List<paradaDTO> getParada() throws paradaLogicException {
    	if (paradas == null) {
    		logger.severe("Error interno: lista de paradas no existe.");
    		throw new paradaLogicException("Error interno: lista de paradas no existe.");    		
    	}
    	
    	logger.info("retornando todas las paradas");
    	return paradas;
    }

    /**
     * Obtiene una parada
     * @param id identificador de la parada
     * @return parada encontrada
     * @throws paradaLogicException cuando la parada no existe
     */
    public paradaDTO getParada(Long id) throws paradaLogicException {
    	logger.info("recibiendo solicitud de parada con id " + id);
    	
    	// busca la parada con el id suministrado
        for (paradaDTO parada : paradas) {
            if (Objects.equals(parada.getId(), id)){
            	logger.info("retornando parada " + parada);
                return parada;
            }
        }
        
        // si no encuentra la parada
        logger.severe("No existe parada con ese id");
        throw new paradaLogicException("No existe parada con ese id");
    }

    /**
     * Agrega una parada a la lista.
     * @param newParada parada a adicionar
     * @throws paradaLogicException cuando ya existe una parada con el id suministrado
     * @return parada agregada
     */
    public paradaDTO createParada(paradaDTO newParada) throws paradaLogicException {
    	logger.info("recibiendo solicitud de agregar viaje " + newParada);
    	
    	// la nueva parada tiene id ?
    	if ( newParada.getId() != null ) {
	    	// busca la parada con el id suministrado
	        for (paradaDTO parada : paradas) {
	        	// si existe una parada con ese id
	            if (Objects.equals(parada.getId(), newParada.getId())){
	            	logger.severe("Ya existe una parada con ese id");
	                throw new paradaLogicException("Ya existe una parada con ese id");
	            }
	        }
	        
	    // la nueva parada no tiene id ? 
    	} else {

    		// genera un id para la parada
    		logger.info("Generando id para la nueva parada");
    		long newId = 1;
	        for (paradaDTO parada : paradas) {
	            if (newId <= parada.getId()){
	                newId =  parada.getId() + 1;
	            }
	        }
	        newParada.setId(newId);
    	}
    	
        // agrega la parada
    	logger.info("agregando parada " + newParada);
        paradas.add(newParada);
        return newParada;
    }

    /**
     * Actualiza los datos de una parada
     * @param id identificador de la parada a modificar
     * @param viaje parada a modificar
     * @return datos de la parada modificada 
     * @throws paradaLogicException cuando no existe una parada con el id suministrado
     */
    public paradaDTO updateParada(Long id, paradaDTO updatedParada) throws paradaLogicException {
    	logger.info("recibiendo solictud de modificar parada " + updatedParada);
    	
    	// busca la parada con el id suministrado
        for (paradaDTO parada : paradas) {
            if (Objects.equals(parada.getId(), id)) {
            	
            	// modifica la parada
            	parada.setId(updatedParada.getId());
                parada.setName(updatedParada.getName());
                
                // retorna la parada modificada
            	logger.info("Modificando parada " + parada);
                return parada;
            }
        }
        
        // no encontró la parada con ese id ?
        logger.severe("No existe una parada con ese id");
        throw new paradaLogicException("No existe una parada con ese id");
    }

    /**
     * Elimina los datos de una parada
     * @param id identificador de la parada a eliminar
     * @throws paradaLogicException cuando no existe una parada con el id suministrado
     */
    public void deleteParada(Long id) throws paradaLogicException {
    	logger.info("recibiendo solictud de eliminar una parada con id " + id);
    	
    	// busca la parada con el id suministrado
        for (paradaDTO parada : paradas) {
            if (Objects.equals(parada.getId(), id)) {
            	
            	// elimina la parada
            	logger.info("eliminando parada " + parada);
                paradas.remove(parada);
                return;
            }
        }

        // no encontró la parada con ese id ?
        logger.severe("No existe una parada con ese id");
        throw new paradaLogicException("No existe una parada con ese id");
    }
}
