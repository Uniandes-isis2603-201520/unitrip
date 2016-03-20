package co.edu.uniandes.rest.mocks;

/**
 * Mock del recurso parada (Mock del servicio REST)
 * @author Asistente
 */
import co.edu.uniandes.rest.dtos.ParadaDTO;
import co.edu.uniandes.rest.exceptions.LogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;


/*
 * ViajeLogicMock
 * Mock del recurso parada (Mock del servicio REST)
 */
@Named
@ApplicationScoped
public class ParadaLogicMock {
	
	// objeto para presentar logs de las operaciones
	private final static Logger logger = Logger.getLogger(ParadaLogicMock.class.getName());
	
	// listado de parada
    private static ArrayList<ParadaDTO> paradas;

    /**
     * Constructor. Crea los datos de ejemplo.
     */
    public ParadaLogicMock() {

    	if (paradas == null) {
            paradas = new ArrayList<>();
            paradas.add(new ParadaDTO(1L, "Bogota", 1L, 1L,"lo mejor","2016-10-12","2016-10-12"));
            paradas.add(new ParadaDTO(2L, "Cali", 1L, 1L,"lo mejor","2016-10-12","2016-10-12"));
            paradas.add(new ParadaDTO(3L, "Cartagena", 1L, 1L,"lo mejor","2016-10-12","2016-10-12"));
            paradas.add(new ParadaDTO(4L, "Yopal", 1L, 1L,"lo mejor","2016-10-12","2016-10-12"));
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
	 * @throws LogicException cuando no existe la lista en memoria  
	 */    
    public List<ParadaDTO> getParadas() throws LogicException {
    	if (paradas == null) {
    		logger.severe("Error interno: lista de paradas no existe.");
    		throw new LogicException("Error interno: lista de paradas no existe.");    		
    	}
    	
    	logger.info("retornando todas las paradas");
    	return paradas;
    }

    /**
     * Obtiene una parada
     * @param id identificador de la parada
     * @return parada encontrada
     * @throws LogicException cuando la parada no existe
     */
    public ParadaDTO getParada(Long id) throws LogicException {
    	logger.info("recibiendo solicitud de parada con id " + id);
    	
    	// busca la parada con el id suministrado
        for (ParadaDTO parada : paradas) {
            if (Objects.equals(parada.getId(), id)){
            	logger.info("retornando parada " + parada);
                return parada;
            }
        }
        
        // si no encuentra la parada
        logger.severe("No existe parada con ese id");
        throw new LogicException("No existe parada con ese id");
    }

    /**
     * Agrega una parada a la lista.
     * @param newParada parada a adicionar
     * @throws LogicException cuando ya existe una parada con el id suministrado
     * @return parada agregada
     */
    public ParadaDTO createParada(ParadaDTO newParada) throws LogicException {
    	logger.info("recibiendo solicitud de agregar viaje " + newParada);
    	
    	// la nueva parada tiene id ?
    	if ( newParada.getId() != null ) {
	    	// busca la parada con el id suministrado
	        for (ParadaDTO parada : paradas) {
	        	// si existe una parada con ese id
	            if (Objects.equals(parada.getId(), newParada.getId())){
	            	logger.severe("Ya existe una parada con ese id");
	                throw new LogicException("Ya existe una parada con ese id");
	            }
	        }
	        
	    // la nueva parada no tiene id ? 
    	} else {

    		// genera un id para la parada
    		logger.info("Generando id para la nueva parada");
    		long newId = 1;
	        for (ParadaDTO parada : paradas) {
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
     * @param updatedParada parada a modificar
     * @return datos de la parada modificada 
     * @throws LogicException cuando no existe una parada con el id suministrado
     */
    public ParadaDTO updateParada(Long id, ParadaDTO updatedParada) throws LogicException {
    	logger.info("recibiendo solictud de modificar parada " + updatedParada);
    	
    	// busca la parada con el id suministrado
        for (ParadaDTO parada : paradas) {
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
        throw new LogicException("No existe una parada con ese id");
    }

    /**
     * Elimina los datos de una parada
     * @param id identificador de la parada a eliminar
     * @throws LogicException cuando no existe una parada con el id suministrado
     */
    public void deleteParada(Long id) throws LogicException {
    	logger.info("recibiendo solictud de eliminar una parada con id " + id);
    	
    	// busca la parada con el id suministrado
        for (ParadaDTO parada : paradas) {
            if (Objects.equals(parada.getId(), id)) {
            	
            	// elimina la parada
            	logger.info("eliminando parada " + parada);
                paradas.remove(parada);
                return;
            }
        }

        // no encontró la parada con ese id ?
        logger.severe("No existe una parada con ese id");
        throw new LogicException("No existe una parada con ese id");
    }
}
