/*
 * ItinerarioDTO
 * Objeto de transferencia de datos de itinerarios.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
package co.edu.uniandes.rest.dtos;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Objeto de transferencia de datos de itinerarios.
 * @author Asistente
 */
@XmlRootElement
public class ItinerarioDTO {
    private Long id;
    private String name;
    private String descripcion;
    private String fechaI;
    private String fechaF;
    private ViajesDTO viaje;
    private List<ParadaDTO> paradas;

    /**
     * Constructor por defecto
     */
    public ItinerarioDTO() {
	}

    /**
     * Constructor con parámetros.
     * @param id identificador del Itinerario
     * @param name nombre del Itinerario
     * @param descripcion descripcion del Itinerario
     * @param fechaI fecha inicial del Itinerario  
     * @param fechaF fecha final del Itinerario 
     */
    public ItinerarioDTO(Long id, String name, String descripcion,
            String fechaI, String fechaF) {
		super();
		this.id = id;
		this.name = name;
                this.descripcion = descripcion;
                this.fechaI = fechaI;
                this.fechaF = fechaF;                
	}

	/**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * @return la descripcion del itinerario
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion descripcion a asignar
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    /**
     * @return Fecha de Inicio
     */
    public String getFechaI() {
        return fechaI;
    }

    /**
     * @param fechaI fecha de inicion a asignar
     */
    public void setFechaI(String fechaI) {
        this.fechaI = fechaI;
    }
    
    /**
     * @return la fecha final
     */
    public String getFechaF() {
        return fechaF;
    }

    /**
     * @param fechaF fecha final del itinerario a asignar
     */
    public void setFechaF(String fechaF) {
        this.fechaF = fechaF;
    }
    
    /**
     * @return el viaje al que corresponde
     */
    public ViajesDTO getViaje() {
        return viaje;
    }

    /**
     * @param viaje viaje a asignar
     */
    public void setViaje(ViajesDTO viaje) {
        this.viaje = viaje;
    }
    
    /**
     * @return las paradas
     */
    public List<ParadaDTO> getParadas() {
        return paradas;
    }

    /**
     * @param parada viaje a agregar
     */
    public void addParadas(ParadaDTO parada) {
        paradas.add(parada);
    }
    
    /**
     * Convierte el objeto a una cadena
     * @return Cadena de caracteres con informacion de un Itinerario
     */
    @Override
    public String toString() {
    	return "{ id : " + getId() + ", name : \"" + getName() + ", descripcion: \""+ getDescripcion()+", fecha Inicio: \""+getFechaI()+ ", fecha Final: \""+getFechaF()+ "\" }" ;  
    }
}
