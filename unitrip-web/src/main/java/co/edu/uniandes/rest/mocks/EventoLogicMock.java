/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.mocks;

/**
 *
 * @author l.castro12
 */
import co.edu.uniandes.rest.dtos.EventoDTO;
import co.edu.uniandes.rest.exceptions.LogicException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;




/*
 * CityLogicMock
 * Mock del recurso Ciudades (Mock del servicio REST)
 */
@Named
@ApplicationScoped
public class EventoLogicMock {

	// objeto para presentar logs de las operaciones
	private final static Logger logger = Logger.getLogger(EventoLogicMock.class.getName());

	// listado de ciudades
    private static ArrayList<EventoDTO> eventos;

    /**
     * Constructor. Crea los datos de ejemplo.
     */
    public EventoLogicMock() {

    	if (eventos == null) {
            eventos = new ArrayList<>();
            String fechaA = "07/06/2016";
            String fechaB="08/06/2016";
            DateFormat df2 = new SimpleDateFormat("dd/mm/yy");
            Date fecha1 = null;
            Date fecha2 = null;
            try{
	    fecha1 = df2.parse(fechaA);
            fecha2= df2.parse(fechaB);}
            catch(Exception e){

            }
            eventos.add(new EventoDTO(1L, "Bogota", 4L,4L,"Divertido",fecha1,fecha2));
            eventos.add(new EventoDTO(2L, "Cali",4L,4L,"Divertido",fecha1,fecha2));
            eventos.add(new EventoDTO(3L, "Medellin",4L,4L,"Divertido",fecha1,fecha2));
        }

    	// indica que se muestren todos los mensajes
    	logger.setLevel(Level.INFO);

    	// muestra información
    	logger.info("Inicializa la lista de eventos");
    	logger.info("eventos" + eventos );
    }

	/**
	 * Obtiene el listado de eventos.
	 * @return lista de eventos
	 * @throws LogicException cuando no existe la lista en memoria
	 */
    public List<EventoDTO> getEventos() throws LogicException {
    	if (eventos == null) {
    		logger.severe("Error interno: lista de eventos no existe.");
    		throw new LogicException("Error interno: lista de eventos no existe.");
    	}

    	logger.info("retornando todos las eventos");
    	return eventos;
    }

    /**
     * Obtiene un evento
     * @param id identificador del evento
     * @return evento encontrada
     * @throws LogicException cuando la ciudad no existe
     */
    public EventoDTO getEvento(Long id) throws LogicException {
    	logger.info("recibiendo solicitud de evento con id " + id);

    	// busca el evento con el id suministrado
        for (EventoDTO event : eventos) {
            if (Objects.equals(event.getId(), id)){
            	logger.info("retornando evento " + event);
                return event;
            }
        }

        // si no encuentra el evento
        logger.severe("No existe evento con ese id");
        throw new LogicException("No existe evento con ese id");
    }

    /**
     * Agrega un evento a la lista.
     * @param newEvent evento a adicionar
     * @throws LogicException cuando ya existe un evento con el id suministrado
     * @return evento agregado
     */
    public EventoDTO createEvento(EventoDTO newEvent) throws LogicException {
    	logger.info("recibiendo solicitud de agregar evento " + newEvent);

    	// la nueva ciudad tiene id ?
    	if ( newEvent.getId() != null ) {
	    	// busca la ciudad con el id suministrado
	        for (EventoDTO event : eventos) {
	        	// si existe una ciudad con ese id
	            if (Objects.equals(event.getId(), newEvent.getId())){
	            	logger.severe("Ya existe un evento con ese id");
	                throw new LogicException("Ya existe un evento con ese id");
	            }
	        }

	    // el nuevo evento no tiene id ?
    	} else {

    		// genera un id para la ciudad
    		logger.info("Generando id para el nuevo evento");
    		long newId = 1;
	        for (EventoDTO event : eventos) {
	            if (newId <= event.getId()){
	                newId =  event.getId() + 1;
	            }
	        }
	        newEvent.setId(newId);
    	}

        // agrega la ciudad
    	logger.info("agregando evento " + newEvent);
        eventos.add(newEvent);
        return newEvent;
    }

    /**
     * Actualiza los datos de un evento
     * @param id identificador del evento a modificar
     * @param updatedEvento evento a modificar
     * @return datos del evento modificada
     * @throws LogicException cuando no existe un evento con el id suministrado
     */
    public EventoDTO updateEvento(Long id, EventoDTO updatedEvento) throws LogicException {
    	logger.info("recibiendo solictud de modificar evento " + updatedEvento);

    	// busca el evento con el id suministrado
        for (EventoDTO event : eventos) {
            if (Objects.equals(event.getId(), id)) {

            	// modifica el evento
            	event.setId(updatedEvento.getId());
                event.setName(updatedEvento.getName());

                // retorna el evento modificad
            	logger.info("Modificando evento " + event);
                return event;
            }
        }

        // no encontró la ciudad con ese id ?
        logger.severe("No existe un evento con ese id");
        throw new LogicException("No existe un evento con ese id");
    }

    /**
     * Elimina los datos de un evento
     * @param id identificador del evento a eliminar
     * @throws LogicException cuando no existe un evento con el id suministrado
     */
    public void deleteEvento(Long id) throws LogicException {
    	logger.info("recibiendo solictud de eliminar evento con id " + id);

    	// busca el evento con el id suministrado
        for (EventoDTO event : eventos) {
            if (Objects.equals(event.getId(), id)) {

            	// elimina el evento
            	logger.info("eliminando ciudad " + event);
                eventos.remove(event);
                return;
            }
        }

        // no encontró el evento con ese id ?
        logger.severe("No existe un evento con ese id");
        throw new LogicException("No existe un evento con ese id");
    }
}
