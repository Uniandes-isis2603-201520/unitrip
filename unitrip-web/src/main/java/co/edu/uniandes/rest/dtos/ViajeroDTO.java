/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.dtos;

import java.util.List;
//import java.util.logging.Logger;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author af.munoz1477
 */
@XmlRootElement
public class ViajeroDTO {

    private Long id;

    private String usuario;
        
    private String name;

    private int edad;

    private String password;

    private String mail;

    private List<ViajesDTO> viajes;

    private List<ExperienciaDTO> experiencias;

//    private static final Logger LOG = Logger.getLogger(ViajeroDTO.class.getName());

    
    /**
    public ViajeroDTO(){

    }
    
    public ViajeroDTO(String usuario, int edad, String password, String mail, List<ViajesDTO> viajes, List<ExperienciaDTO> experiencias) {
        this.usuario = usuario;
        this.edad = edad;
        this.password = password;
        this.mail = mail;
        this.viajes = viajes;
        this.experiencias = experiencias;
    }
    **/

    /**
     * public ViajeroDTO(){

    }
    
    public ViajeroDTO(String usuario, int edad, String password, String mail, List<ViajesDTO> viajes, List<ExperienciaDTO> experiencias) {
        this.usuario = usuario;
        this.edad = edad;
        this.password = password;
        this.mail = mail;
        this.viajes = viajes;
        this.experiencias = experiencias;
    }
     * @return
     */
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

        public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public List<ViajesDTO> getViajes() {
        return viajes;
    }

    public void setViajes(List<ViajesDTO> viajes) {
        this.viajes = viajes;
    }

    public List<ExperienciaDTO> getExperiencias() {
        return experiencias;
    }

    public void setExperiencias(List<ExperienciaDTO> experiencias) {
        this.experiencias = experiencias;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
