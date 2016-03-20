/*
 * ItinerarioDTO
 * Objeto de transferencia de datos de itinerarios.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
package co.edu.uniandes.rest.itinerarios.dtos;

/**
 * Objeto de transferencia de datos de itinerarios.
 * @author Asistente
 */
public class ItinerarioDTO {
    private Long id;
    private String name;
    private String descripcion;
    private String fechaI;
    private String fechaF;

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
    public ItinerarioDTO(Long id, String name, String descripcion
    ,String fechaI, String fechaF) {
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
    public void setFeschaI(String fechaI) {
        this.fechaI = fechaI;
    }
    
    /**
     * @return la fecha final
     */
    public String getFechaF() {
        return name;
    }

    /**
     * @param fechaF fecha final del itinerario a asignar
     */
    public void setFechaF(String fechaF) {
        this.fechaF = fechaF;
    }
    
    /**
     * Convierte el objeto a una cadena
     */
    @Override
    public String toString() {
    	return "{ id : " + getId() + ", name : \"" + getName() + ", descripcion: \""+ getDescripcion()+", fecha Inicio: \""+getFechaI()+ ", fecha Final: \""+getFechaF()+ "\" }" ;  
    }
}
