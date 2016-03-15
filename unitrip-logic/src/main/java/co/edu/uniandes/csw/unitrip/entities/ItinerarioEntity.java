package co.edu.uniandes.csw.unitrip.entities;

//import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
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

@Entity
public class ItinerarioEntity {
    

    private String description;

    
    @ManyToOne
    private ViajeEntity viaje;

    @OneToMany //(mappedBy = "parada", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ParadaEntity> paradas = new ArrayList<>();



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

    public List<ParadaEntity> getParadas() {
        return paradas;
    }

    public void setParadas(List<ParadaEntity> paradas) {
        this.paradas = paradas;
    }

    public ViajeEntity getViaje() {
        return viaje;
    }

    public void setViaje(ViajeEntity viaje) {
        this.viaje = viaje;
    }

}
