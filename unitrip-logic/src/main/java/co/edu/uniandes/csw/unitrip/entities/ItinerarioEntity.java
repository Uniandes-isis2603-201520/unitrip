package co.edu.uniandes.csw.unitrip.entities;

//import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import co.edu.uniandes.csw.crud.api.podam.strategy.DateStrategy;
import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamStrategyValue;

@Entity
public class ItinerarioEntity extends BaseEntity implements Serializable {

    private String descripcion;
    @PodamStrategyValue(DateStrategy.class)
    @Temporal(TemporalType.DATE)
    private Date fechaI;
    @PodamStrategyValue(DateStrategy.class)
    @Temporal(TemporalType.DATE)
    private Date fechaF;

    @ManyToOne
    @PodamExclude
    private ViajeEntity viaje;

    @PodamExclude
    @OneToMany(mappedBy = "itinerario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ParadaEntity> paradas = new ArrayList<>();

    /**
     * @return the description
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the description to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Retorna el listado de los registros de parada
     *
     * @return paradas paradas del itinerario
     */
    public List<ParadaEntity> getParadas() {
        return paradas;
    }

    /**
     *
     * @param paradas
     */
    public void setParadas(List<ParadaEntity> paradas) {
        this.paradas = paradas;
    }

    /**
     *
     * @param parada
     */
    public void addParada(ParadaEntity parada) {
        paradas.add(parada);
    }

    public ViajeEntity getViaje() {
        return viaje;
    }

    public void setViaje(ViajeEntity viaje) {
        this.viaje = viaje;
    }

    /**
     * @return fechaI fecha de inicio
     */
    public Date getFechaI() {
        return fechaI;
    }

    /**
     * @param fechaI fecha Inicial a modificar
     */
    public void setFechaI(Date fechaI) {
        this.fechaI = fechaI;
    }

    /**
     * @return fechaI fecha de inicio
     */
    public Date getFechaF() {
        return fechaF;
    }

    /**
     * @param fechaF fecha Inicial a modificar
     */
    public void setFechaF(Date fechaF) {
        this.fechaF = fechaF;
    }

}
