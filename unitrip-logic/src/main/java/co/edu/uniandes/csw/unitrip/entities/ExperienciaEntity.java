/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.unitrip.entities;


import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import java.io.Serializable;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ANDRES
 */
public class ExperienciaEntity extends BaseEntity implements Serializable{
  
    @Id
    private Long id;
    private String name;
    private String description;
    private String image;
    @Temporal(TemporalType.DATE)
   

    //@ManyToMany
    //private List<AuthorEntity> authors = new ArrayList<>();

    //@ManyToOne
    //private EditorialEntity editorial;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)    
    private List<ExperienciaEntity> experiencias = new ArrayList<>();
    

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
     * @param name the image to set
     */
    public void setName(String name) {
        this.name = name;
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

    
    public List<ExperienciaEntity> getExperiencias() {
        return experiencias;
    }

    public void setExperiencias(List<ExperienciaEntity> experiencias) {
        this.experiencias = experiencias;
    }
}
