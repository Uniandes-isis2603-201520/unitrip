/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.unitrip.entities;

import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author je.molano1498
 */
@Entity
public class ViajeEntity extends BaseEntity implements Serializable {

    private String description;
    private String image;

    @OneToMany(mappedBy = "viaje", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItinerarioEntity> itinerarios = new ArrayList<>();

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

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    public List<ItinerarioEntity> getItinerarios() {
        return itinerarios;
    }

    public void setItinerarios(List<ItinerarioEntity> iti) {
        this.itinerarios = iti;
    }

}
