/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.unitrip.entities;

import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author ANDRES
 */
@Entity
public class ParadaEntity extends BaseEntity implements Serializable{

    private Long latitud;
    private Long longitud;
    private String description;
    @Temporal(TemporalType.DATE)
    private Date fechaI;
    @Temporal(TemporalType.DATE)
    private Date fechaF;


    @ManyToOne
    @PodamExclude
    private ItinerarioEntity itinerario;


     @OneToMany(mappedBy = "parada", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventoEntity> eventos = new ArrayList<>();

    /**
     * @return the description
     */
    public String getDescripcion() {
        return description;
    }

    /**
     * @param descr the image to set
     */
    public void setDescription(String descr) {
        this.description = descr;
    }

    /**
     * @return the image
     */
    public Long getLatitud() {
        return latitud;
    }

    /**
     * @param lat the latitud to set
     */
    public void setLatitud(Long lat) {
        this.latitud =lat;
    }

    public List<EventoEntity> getEventos() {
        return eventos;
    }

    public void setEventos(List<EventoEntity> iti) {
        this.eventos = iti;
    }

    public Long getLongitud() {
        return longitud;
    }

    public void setLongitud(Long longi) {
        this.longitud = longi;
    }
    public Date getFechaI() {
        return fechaI;
    }

    public void setFechaI(Date fechaI) {
        this.fechaI = fechaI;
    }
    public Date getFechaF() {
        return fechaF;
    }

    public void setFechaF(Date fechaF) {
        this.fechaF = fechaF;
    }

    public ItinerarioEntity getItinerario() {
        return itinerario;
    }

    public void setItinerario(ItinerarioEntity itinerario) {
        this.itinerario = itinerario;
    }
}


