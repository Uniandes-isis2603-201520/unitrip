/*
 * paradaDTO
 * Objeto de transferencia de datos de paradas.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
package co.edu.uniandes.rest.dtos;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ParadaDTO {
    
    private Long id;
    private String name;
    private Long latitud;
    private Long longitude;
    private String descripcion;
    private String fechaI;
    private String fechaF;
    private List<EventoDTO> eventos;

    /**
     * Constructor por defecto
     */
    public ParadaDTO() {
	}

    /**
     * Constructor con parámetros.
     * @param id identificador de la parada
     * @param name nombre de la parada
     * @param latitud latitud terrestre de la parada
     * @param longitude longitud terrestre de la parada
     * @param descripcion descripcion de la parada
     * @param fechaI fecha de inicio de la parada
     * @param fechaF fecha final de la parada
     */
    public ParadaDTO(Long id, String name, Long latitud, 
            Long longitude, String descripcion,
            String fechaI, String fechaF) {
		super();
		this.id = id;
		this.name = name;
                this.descripcion=descripcion;
                this.latitud = latitud;
                this.fechaF = fechaF;
                this.fechaI = fechaI;
                this.longitude = longitude;
                
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
     * @return the latitud
     */
    public Long getLatitud(){
        return latitud;
    }
    
    /**
     * @return the longitude
     */
    public Long getLongitude(){
        return longitude;
    }
    
    /**
     * @return the description
     */
    public String getDescripcion() {
        return descripcion;
    }
    
    /**
     * @return Fecha de Inicio
     */
    public String getFechaI(){
        return fechaI;
    }
    
    /**
     * @return Fecha de Final
     */
    public String getFechaF(){
        return fechaF;
    }
    
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * @param lat latitud a introducir
     * 
     */
    public void setLatitud( Long lat){
        this.latitud = lat;
    }
    
    /**
     * @param longi longitude a introducir
     * 
     */
    public void setLongitude( Long longi){
        this.longitude = longi;
    }
    
    /**
     * @param descripcion descripcion a introducir
     */
    public void setDescripcion(String descripcion) {
        this.descripcion=descripcion;
    }
    
    /**
     * @param fechaI fecha de inicio de la parada
     */
    public void setFechaI(String fechaI){
        this.fechaI = fechaI;
    }
    
    /**
     * @param fechaF fecha final de la parada
     */
    public void setFechaF(String fechaF){
        this.fechaF = fechaF;
    }
    
     /**
     * @return eventos Lista de eventos asociados a una parada
     */
    public List<EventoDTO> getEventos(){
        return eventos;
    }
    
    /**
     * @param evento evento a asociar a la parada
     */
    public void addEvento(EventoDTO evento) {
        this.eventos.add(evento);
    }
    
    /**
     * @param eventos eventos a ingresar en la nueva lista
     */
    public void setEventos(List<EventoDTO> eventos){
        this.eventos=eventos;
    }
    
    /**
     * @param evento evento a desasociar de la parada
     */
    public void removeEvento(EventoDTO evento){
        eventos.remove(evento);
    }
    
    /**
     * Convierte el objeto a una cadena
     * @return cadena de caracteres que suministran informacion de la parada
     */
    @Override
    public String toString() {
    	return "{ id : " + getId() + ", name : \"" + getName() + "\" }" ;  
    }
}
