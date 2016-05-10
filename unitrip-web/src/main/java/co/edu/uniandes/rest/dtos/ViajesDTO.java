/*
 * ViajesDTO
 * Objeto de transferencia de datos de viajes.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
package co.edu.uniandes.rest.dtos;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Objeto de transferencia de datos de itinerarios.
 *
 * @author Asistente
 */
@XmlRootElement
public class ViajesDTO {

    private Long id;
    private String name;
    private String descripcion;
    private String image;
    private List<ItinerarioDTO> itinerarios;
    private List<ExperienciaDTO> experiencias;

    /**
     * Constructor por defecto
     */
    public ViajesDTO() {
    }

    /**
     * Constructor con parámetros.
     *
     * @param id identificador del viaje
     * @param name nombre del viaje
     * @param desc descripcion del viaje
     * @param imagen Imagen descriptiva del viaje
     */

    /**
    public ViajesDTO(Long id, String name, String desc, String imagen) {
        super();
        this.id = id;
        this.name = name;
        this.descripcion = desc;
        this.image = imagen;
        this.experiencias = new ArrayList<>();
        this.itinerarios = new ArrayList<>();
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
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descrip the description to set
     */
    public void setDescripcion(String descrip) {
        this.descripcion = descrip;
    }

    /**
     * @return the Image URL
     */
    public String getImg() {
        return image;
    }

    /**
     * @param img the image ubication to set
     */
    public void setImg(String img) {
        this.image = img;
    }

    /**
     * @return the Itineraries colection
     */
    public List<ItinerarioDTO> getItinerarios() {
        return itinerarios;
    }

    /**
     * @param iti itinerario a agregar
     */
    public void addItinerario(ItinerarioDTO iti) {
        this.itinerarios.add(iti);
    }

    public void setItinerarios(List<ItinerarioDTO> iti) {
        this.itinerarios = (ArrayList<ItinerarioDTO>) iti;
    }

    /**
     * @return the expiriences colection
     */
    public List<ExperienciaDTO> getExperiencias() {
        return experiencias;
    }

    /**
     * @param exp itinerario a agregar
     */
    public void addExperiencia(ExperienciaDTO exp) {
        this.experiencias.add(exp);
    }

    public void setExperiencias(List<ExperienciaDTO> exp) {
        this.experiencias = exp;
    }


}
