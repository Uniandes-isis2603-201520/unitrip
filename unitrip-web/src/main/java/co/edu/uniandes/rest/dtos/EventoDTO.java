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
 * @author l.castro12
 */
@XmlRootElement
public class EventoDTO {

    private Long id;
    private String name;
    private Long latitud;
    private Long longitud;
    private String description;
    private Date fechaInicio;
    private Date fechaFin;
    private ParadaDTO parada;


    /**
     * Constructor con parámetros.
     *
     * @param id identificador del evento
     * @param name nombre del evento
     * @param latitud coordenada de latitud del evento
     * @param longitud coordenada de ongitud del evento
     * @param description descripcion del evento
     * @param fechaInicio fecha de inicio del evento
     * @param fechaFin fecha donde el evento finaliza
     */

    /**
    public EventoDTO(Long id, String name, Long latitud, long longitud, String description, Date fechaInicio, Date fechaFin) {
        super();
        this.id = id;
        this.name = name;
        this.latitud = latitud;
        this.longitud = longitud;
        this.description = description;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
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
     * @return the latitud
     */
    public Long getLatitud() {
        return latitud;
    }

    /**
     * @param latitud the latitud to set
     */
    public void setLatitud(Long latitud) {
        this.latitud = latitud;
    }

    /**
     * @return the longitud
     */
    public Long getLongitud() {
        return longitud;
    }

    /**
     * @param longitud the longitud to set
     */
    public void setLongitud(Long longitud) {
        this.longitud = longitud;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the fechaInicio
     */
    @XmlElement(name = "fechaInicio")
    @XmlJavaTypeAdapter(DateAdapter.class)
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the fechaFin
     */
    @XmlElement(name = "fechaFin")
    @XmlJavaTypeAdapter(DateAdapter.class)
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }


    public ParadaDTO getParada() {
        return parada;
    }

    public void setParada(ParadaDTO parada) {
        this.parada = parada;
    }


}
