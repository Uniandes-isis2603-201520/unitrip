/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.dtos;

import co.edu.uniandes.rest.adapter.DateAdapter;
import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author
 */
@XmlRootElement
public class ExperienciaDTO {

    private Long id;
    private String name;
    private String descripcion;
    private String rutaImagen;

    private Date fechaP;


    /**
     * Constructor con parámetros.
     *
     * @param id identificador del Experiencia
     * @param name nombre del Experiencia
     * @param descripcion descripcion del Experiencia
     * @param rutaI rutaImagen de la Experiencia
     * @param fechaP fecha de Publicación de la Experiencia
     */

    /**
    public ExperienciaDTO(Long id, String name, String descripcion, String rutaI, Date fechaP) {
        super();
        this.id = id;
        this.name = name;
        this.descripcion = descripcion;
        this.rutaImagen = rutaI;
        this.fechaP = fechaP;

    }
    **/

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

    @XmlElement(name = "fechaP")
    @XmlJavaTypeAdapter(DateAdapter.class)
    public Date getFechaP() {
        return fechaP;
    }

    public void setFechaP(Date fechaP) {
        this.fechaP = fechaP;
    }

}
