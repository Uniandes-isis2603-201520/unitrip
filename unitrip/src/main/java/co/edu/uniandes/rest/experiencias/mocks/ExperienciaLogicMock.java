package co.edu.uniandes.rest.experiencias.mocks;

/**
 * Mock del recurso Experiencias (Mock del servicio REST)
 * @author Asistente
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;


import co.edu.uniandes.rest.experiencias.dtos.ExperienciaDTO;
import co.edu.uniandes.rest.experiencias.exceptions.ExperienciaLogicException;

/*
 * ExperienciaLogicMock
 * Mock del recurso Experiencias (Mock del servicio REST)
 */
@Named
@ApplicationScoped
public class ExperienciaLogicMock {
	
	// objeto para presentar logs de las operaciones
	private final static Logger logger = Logger.getLogger(ExperienciaLogicMock.class.getName());
	
	// listado de Experiencias
    private static ArrayList<ExperienciaDTO> experiencias;

    /**
     * Constructor. Crea los datos de ejemplo.
     */
    public ExperienciaLogicMock() {

    	if (experiencias == null) {
            experiencias = new ArrayList<ExperienciaDTO>();
            experiencias.add(new ExperienciaDTO(1L, "Bogota","Muy bueno","rutaImagen1"));
            experiencias.add(new ExperienciaDTO(2L, "Cali","Muy bueno","rutaImagen1"));
            experiencias.add(new ExperienciaDTO(3L, "Medellin","Muy bueno","rutaImagen1"));
        }
        
    	// indica que se muestren todos los mensajes
    	logger.setLevel(Level.INFO);
    	
    	// muestra información 
    	logger.info("Inicializa la lista de Experiencias");
    	logger.info("Experiencias" + Experiencias );
    }    
    
	/**
	 * Obtiene el listado de Experiencias. 
	 * @return lista de Experiencias
	 * @throws ExperienciaLogicException cuando no existe la lista en memoria  
	 */    
    public List<ExperienciaDTO> getExperiencias() throws ExperienciaLogicException {
    	if (experiencias == null) {
    		logger.severe("Error interno: lista de Experiencias no existe.");
    		throw new ExperienciaLogicException("Error interno: lista de Experiencias no existe.");    		
    	}
    	
    	logger.info("retornando todas los Experiencias");
    	return experiencias;
    }

    /**
     * Obtiene un Experiencias
     * @param id identificador del Experiencia
     * @return Experiencias encontrado
     * @throws ExperienciasLogicException cuando el Experiencia no existe
     */
    public ExperienciaDTO getExperiencia(Long id) throws ExperienciaLogicException {
    	logger.info("recibiendo solicitud de Experiencia con id " + id);
    	
    	// busca el Experiencia con el id suministrado
        for (ExperienciaDTO experiencia : experiencias) {
            if (Objects.equals(experiencia.getId(), id)){
            	logger.info("retornando experiencia " + experiencia);
                return experiencia;
            }
        }
        
        // si no encuentra el Experiencia
        logger.severe("No existe Experiencia con ese id");
        throw new ExperienciaLogicException("No existe Experiencia con ese id");
    }

    /**
     * Agrega un Experiencia a la lista.
     * @param newExperiencia Experiencia a adicionar
     * @throws ExperienciaLogicException cuando ya existe un Experiencia con el id suministrado
     * @return Experiencia agregado
     */
    public ExperienciaDTO createExperiencia(ExperienciaDTO newExperiencia) throws ExperienciaLogicException {
    	logger.info("recibiendo solicitud de agregar Experiencia " + newExperiencia);
    	
    	// el nuevo Experiencia tiene id ?
    	if ( newExperiencia.getId() != null ) {
	    	// busca la ciudad con el id suministrado
	        for (ExperienciaDTO experiencia : experiencias) {
	        	// si existe un Experiencia con ese id
	            if (Objects.equals(Experiencia.getId(), newExperiencia.getId())){
	            	logger.severe("Ya existe una experiencia con ese id");
	                throw new ExperienciaLogicException("Ya existe una experiencia con ese id");
	            }
	        }
	        
	    // el nuevo Experiencia no tiene id ? 
    	} else {

    		// genera un id para el Experiencia
    		logger.info("Generando id para el nueva Experiencia");
    		long newId = 1;
	        for (ExperienciaDTO experiencia : experiencias) {
	            if (newId <= Experiencia.getId()){
	                newId =  Experiencia.getId() + 1;
	            }
	        }
	        newExperiencia.setId(newId);
    	}
    	
        // agrega el Experiencia
    	logger.info("agregando Experiencia " + newExperiencia);
        experiencias.add(newExperiencia);
        return newExperiencia;
    }

    /**
     * Actualiza los datos de un Experiencia
     * @param id identificador del Experiencia a modificar
     * @param Experiencia Experiencia a modificar
     * @return datos del Experiencia modificada 
     * @throws ExperienciasLogicException cuando no existe un Experiencia con el id suministrado
     */
    public ExperienciaDTO updateExperiencia(Long id, ExperienciaDTO updatedExperiencia) throws ExperienciaLogicException {
    	logger.info("recibiendo solictud de modificar Experiencia " + updatedExperiencia);
    	
    	// busca el Experiencia con el id suministrado
        for (ExperienciaDTO experiencia : experiencias) {
            if (Objects.equals(experiencia.getId(), id)) {
            	
            	// modifica el Experiencia
            	experiencia.setId(updatedExperiencia.getId());
                experiencia.setName(updatedExperiencia.getName());
                
                // retorna el Experiencia modificada
            	logger.info("Modificando experiencia " + experiencia);
                return experiencia;
            }
        }
        
        // no encontró el Experiencia con ese id ?
        logger.severe("No existe una experiencia con ese id");
        throw new ExperienciaLogicException("No existe un experiencia con ese id");
    }

    /**
     * Elimina los datos de un Experiencia
     * @param id identificador del Experiencias a eliminar
     * @throws ExperienciasLogicException cuando no existe un Experiencia con el id suministrado
     */
    public void deleteExperiencia(Long id) throws ExperienciaLogicException {
    	logger.info("recibiendo solictud de eliminar Experiencia con id " + id);
    	
    	// busca el Experiencia con el id suministrado
        for (ExperienciaDTO experiencia : experiencias) {
            if (Objects.equals(Experiencia.getId(), id)) {
            	
            	// elimina el Experiencia
            	logger.info("eliminando experiencia " + experiencia);
                experiencias.remove(Experiencia);
                return;
            }
        }

        // no encontró el experiencia con ese id ?
        logger.severe("No existe una experiencia con ese id");
        throw new ExperienciaLogicException("No existe una experiencia con ese id");
    }
}
