/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.unitrip.entities;

import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author ANDRES
 */
@Entity
public class ExperienciaEntity extends BaseEntity implements Serializable {

    private String description;

    @Temporal(TemporalType.DATE)
    private Date fechaP;
    
    
    
    @ManyToOne
    @PodamExclude
    private ViajeroEntity viajero;

    public ViajeroEntity getViajero() {
        return viajero;
    }

    public void setViajero(ViajeroEntity viajero) {
        this.viajero = viajero;
    }

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

    
    public Date getFechaP() {
        return fechaP;
    }

    public void setFechaP(Date fechaP) {
        this.fechaP = fechaP;
    }


}
