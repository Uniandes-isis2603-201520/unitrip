/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.unitrip.entities;

/**
 *
 * @author l.castro12
 */
import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import co.edu.uniandes.csw.unitrip.entities.ItinerarioEntity;

@Entity
public class EventoEntity extends BaseEntity implements Serializable {

    private Long id;
    private String nombre;
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    private Date fechaFin;
    private String descripcion;
    private Long latitud;
    private Long longitud;

     @ManyToOne
    private ItinerarioEntity itinerario;

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
     * @return nombre
     */
    public String getName() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setName(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return fechaInicio
     */
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
     * @return fechaFin
     */
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }


    public ItinerarioEntity getItinerario() {
        return itinerario;
    }

    public void setItinerario(ItinerarioEntity itinerario) {
        this.itinerario = itinerario;
    }

    public String getDescription() {
        return descripcion;
    }

    public void setDescription(ItinerarioEntity itinerario) {
        this.itinerario = itinerario;
    }
    public Long getLatitud() {
        return latitud;
    }

    public void setLatitud(Long latitud) {
        this.latitud = latitud;
    }
    public Long getLongitud() {
        return longitud;
    }

    public void setLongitud(Long longitud) {
        this.longitud = longitud;
    }
}