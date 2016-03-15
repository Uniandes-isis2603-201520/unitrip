/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.Viajes.converters;

import co.edu.uniandes.rest.Viajes.dtos.ViajesDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ANDRES
 */
public abstract class ViajesConverter {

    /**
     * Constructor privado para evitar la creación del constructor implícito de
     * Java
     *
     * @generated
     */
    private ViajesConverter() {
    }

    /**
     * Realiza la conversión de ViajesEntity a ViajesDTO. Se invoca cuando otra
     * entidad tiene una referencia a ViajesEntity. Entrega únicamente los
     * atributos propios de la entidad.
     *
     * @param entity instancia de ViajesEntity a convertir
     * @return instancia de ViajesDTO con los datos recibidos por parámetro
     * @generated
     */
    public static ViajesDTO refEntity2DTO(ViajesEntity entity) {
        if (entity != null) {
            ViajesDTO dto = new ViajesDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setDescription(entity.getDescription());
            dto.setImage(entity.getImage());
            

            return dto;
        } else {
            return null;
        }
    }

    /**
     * Realiza la conversión de ViajeDTO a ViajeEntity Se invoca cuando otro DTO
     * tiene una referencia a ViajeDTO Convierte únicamente el ID ya que es el
     * único atributo necesario para guardar la relación en la base de datos
     *
     * @param dto instancia de ViajeDTO a convertir
     * @return instancia de ViajeEntity con los datos recibidos por parámetro
     * @generated
     */
    public static ViajeEntity refDTO2Entity(ViajeDTO dto) {
        if (dto != null) {
            ViajeEntity entity = new ViajeEntity();
            entity.setId(dto.getId());

            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de ViajeEntity a ViajeDTO Se invoca cuando se desea
     * consultar la entidad y sus relaciones muchos a uno o uno a uno
     *
     * @param entity instancia de ViajeEntity a convertir
     * @return Instancia de BookDTO con los datos recibidos por parámetro
     * @generated
     */
    private static ViajeDTO basicEntity2DTO(ViajeEntity entity) {
        if (entity != null) {
            ViajeDTO dto = new ViajeDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setDescription(entity.getDescription());
            dto.setIsbn(entity.getIsbn());
            dto.setImage(entity.getImage());

            return dto;
        } else {
            return null;
        }
    }
    

    /**
     * Convierte una instancia de ViajeDTO a ViajeEntity Se invoca cuando se
     * necesita convertir una instancia de ViajeDTO con los atributos propios de
     * la entidad y con las relaciones uno a uno o muchos a uno
     *
     * @param dto instancia de ViajeDTO a convertir
     * @return Instancia de ViajeEntity creada a partir de los datos de dto
     * @generated
     */
    private static ViajeEntity basicDTO2Entity(ViajeDTO dto) {
        if (dto != null) {
            ViajeEntity entity = new ViajeEntity();
            entity.setId(dto.getId());
            entity.setName(dto.getName());
            entity.setDescription(dto.getDescription());
            entity.setIsbn(dto.getIsbn());
            entity.setImage(dto.getImage());

            return entity;
        } else {
            return null;
        }
    }
  

    /**
     * Convierte instancias de ViajeEntity a ViajeDTO incluyendo sus relaciones
     * Uno a muchos y Muchos a muchos
     *
     * @param entity Instancia de ViajeEntity a convertir
     * @return Instancia de ViajeDTO con los datos recibidos por parámetro
     * @generated
     */
    public static ViajeDTO fullEntity2DTO(ViajeEntity entity) {
        if (entity != null) {
            ViajeDTO dto = basicEntity2DTO(entity);
            dto.setItinerarios(ItinerarioConverter.listEntity2DTO(entity.getItinerarios()));
            return dto;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de ViajeDTO a ViajeEntity. Incluye todos los
     * atributos de ViajeEntity.
     *
     * @param dto Instancia de ViajeDTO a convertir
     * @return Instancia de ViajeEntity con los datos recibidos por parámetro
     * @generated
     */
    public static ViajeEntity fullDTO2Entity(ViajeDTO dto) {
        if (dto != null) {
            ViajeEntity entity = basicDTO2Entity(dto);
            entity.setItinerarios(ItinerarioConverter.listDTO2Entity(dto.getItinerarios()));
           
            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte una colección de instancias de ViajeEntity a ViajeDTO. Para cada
     * instancia de ViajeEntity en la lista, invoca basicEntity2DTO y añade el
     * nuevo ViajeDTO a una nueva lista
     *
     * @param entities Colección de entidades a convertir
     * @return Collección de instancias de ViajeDTO
     * @generated
     */
    public static List<ViajesDTO> listEntity2DTO(List<ViajesEntity> entities) {
        List<ViajesDTO> dtos = new ArrayList<ViajesDTO>();
        if (entities != null) {
            for (ViajesEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    /**
     * Convierte una colección de instancias de ViajeDTO a instancias de
     * ViajeEntity Para cada instancia se invoca el método basicDTO2Entity
     *
     * @param dtos entities Colección de ViajeDTO a convertir
     * @return Collección de instancias de ViajeEntity
     * @generated
     */
    public static List<ViajeEntity> listDTO2Entity(List<ViajeDTO> dtos) {
        List<ViajeEntity> entities = new ArrayList<ViajeEntity>();
        if (dtos != null) {
            for (ViajeDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }
}
