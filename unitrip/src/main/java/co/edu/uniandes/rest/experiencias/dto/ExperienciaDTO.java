/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.experiencias.dto;

/**
 *
 * @author ANDRES
 */
public class ExperienciaDTO {
     private Long id;
    private String name;
    private String descripcion;
    private String rutaImagen;

    /**
     * Constructor por defecto
     */
    public ExperienciaDTO() {
	}

    /**
     * Constructor con parámetros.
     * @param id identificador del Experiencia
     * @param name nombre del Experiencia
     * @param descripcion descripcion del Experiencia
     * @param rutaI rutaImagen de la Experiencia  
     
     */
    public ExperienciaDTO(Long id, String name, String descripcion
    , String rutaI) {
		super();
		this.id = id;
		this.name = name;
                this.descripcion = descripcion;
                this.rutaImagen = rutaI;                
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
     * @return la descripcion del Experiencia
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
     * @return Ruta de la imagen
     */
    public String getRutaI() {
        return rutaImagen;
    }

    /**
     * @param Ruta de la imagen
     */
    public void setRutaI(String rutaI) {
        this.rutaImagen = rutaI;
    }
    
    
    /**
     * Convierte el objeto a una cadena
     */
    @Override
    public String toString() {
    	return "{ id : " + getId() + ", name : \"" + getName() + ", descripcion: \""+ getDescripcion()+", ruta imagen: \""+getRutaI()+ "\" }" ;  
    }   
}
