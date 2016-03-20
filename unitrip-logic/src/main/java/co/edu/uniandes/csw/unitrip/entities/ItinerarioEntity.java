package co.edu.uniandes.csw.unitrip.entities;

//import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
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

@Entity
public class ItinerarioEntity extends BaseEntity implements Serializable {
    
    @Id
    private Long id;
    private String name;
    private String descripcion;
    private String fechaI;
    private String fechaF;

    
    @ManyToOne
    private ViajeEntity viaje;

    @OneToMany //(mappedBy = "parada", cascade = CascadeType.ALL, orphanRemoval = true)
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
    public void addParada(ParadaEntity parada){
        paradas.add(parada);
    }

    public ViajeEntity getViaje() {
        return viaje;
    }

    public void setViaje(ViajeEntity viaje) {
        this.viaje = viaje;
    }

    public Long getId() {
        return id;
    }

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
     * @return fechaI fecha de inicio
     */
    public String getFechaI() {
        return fechaI;
    }

    /**
     * @param fechaI fecha Inicial a modificar
     */
    public void setFechaI(String fechaI) {
        this.fechaI = fechaI;
    }
    
         /**
     * @return fechaI fecha de inicio
     */
    public String getFechaF() {
        return fechaF;
    }

    /**
     * @param fechaF fecha Inicial a modificar
     */
    public void setFechaF(String fechaF) {
        this.fechaF = fechaF;
    }

}
