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
import javax.persistence.OneToMany;


@Entity
public class ViajeroEntity extends BaseEntity implements Serializable {

    private String Usuario;
    private int edad;
    private String password;
    private String mail;
     @OneToMany(mappedBy = "viajero", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ViajeEntity> viajes = new ArrayList<>();

    @OneToMany(mappedBy = "viajero", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExperienciaEntity> experiencias = new ArrayList<>();

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
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

    public List<ViajeEntity> getViajes() {
        return viajes;
    }

    public void setViajes(List<ViajeEntity> viajes) {
        this.viajes = viajes;
    }

    public List<ExperienciaEntity> getExperiencias() {
        return experiencias;
    }

    public void setExperiencias(List<ExperienciaEntity> experiencias) {
        this.experiencias = experiencias;
    }



}
