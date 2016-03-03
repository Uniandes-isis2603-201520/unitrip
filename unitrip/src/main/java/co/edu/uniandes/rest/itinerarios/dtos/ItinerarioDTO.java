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

    /**
     * Constructor por defecto
     */
    public ItinerarioDTO() {
	}

    /**
     * Constructor con parámetros.
     * @param id identificador de la ciudad
     * @param name nombre de la ciudad
     */
    public ItinerarioDTO(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
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
     * Convierte el objeto a una cadena
     */
    @Override
    public String toString() {
    	return "{ id : " + getId() + ", name : \"" + getName() + "\" }" ;  
    }
}
