/*
 * ViajesDTO
 * Objeto de transferencia de datos de viajes.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
package co.edu.uniandes.rest.Viajes.dtos;

/**
 * Objeto de transferencia de datos de itinerarios.
 * @author Asistente
 */
public class ViajesDTO {
    private Long id;
    private String name;

    /**
     * Constructor por defecto
     */
    public ViajesDTO() {
	}

    /**
     * Constructor con parámetros.
     * @param id identificador del  viaje
     * @param name nombre del viaje
     */
    public ViajesDTO(Long id, String name) {
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
