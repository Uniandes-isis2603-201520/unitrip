/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.converters;


import co.edu.uniandes.csw.unitrip.entities.ViajeEntity;
import co.edu.uniandes.csw.unitrip.entities.ViajeroEntity;
import co.edu.uniandes.rest.dtos.ViajeroDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author af.munoz1477
 */
public class ViajeroConverter {

    /**
     * Constructor privado para evitar la creación del constructor implícito de
     * Java
     *
     * @generated
     */
    private ViajeroConverter() {
    }

    /**
     * Realiza la conversión de ViajeroEntity a ViajeroDTO. Se invoca cuando otra
     * entidad tiene una referencia a ViajeroEntity. Entrega únicamente los
     * atributos propios de la entidad.
     *
     * @param entity instancia de ViajeroEntity a convertir
     * @return instancia de ViajeroDTO con los datos recibidos por parámetro
     * @generated
     */
    public static ViajeroDTO refEntity2DTO(ViajeroEntity entity) {
        if (entity != null) {
            ViajeroDTO dto = new ViajeroDTO();
            dto.setId(entity.getId());
            dto.setUsuario(entity.getUsuario());
            dto.setEdad(entity.getEdad());
            dto.setMail(entity.getMail());
            dto.setPassword(entity.getPassword());

            return dto;
        } else {
            return null;
        }
    }

    /**
     * Realiza la conversión de ViajeroDTO a ViajeroEntity Se invoca cuando otro DTO
     * tiene una referencia a ViajeroDTO Convierte únicamente el ID ya que es el
     * único atributo necesario para guardar la relación en la base de datos
     *
     * @param dto instancia de ViajeDTO a convertir
     * @return instancia de ViajeEntity con los datos recibidos por parámetro
     * @generated
     */
    public static ViajeroEntity refDTO2Entity(ViajeroDTO dto) {
        if (dto != null) {
            ViajeroEntity entity = new ViajeroEntity();
            entity.setId(dto.getId());

            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de ViajeroEntity a ViajeroDTO Se invoca cuando se desea
     * consultar la entidad y sus relaciones muchos a uno o uno a uno
     *
     * @param entity instancia de ViajeroEntity a convertir

     * @generated
     */
    private static ViajeroDTO basicEntity2DTO(ViajeroEntity entity) {
        if (entity != null) {
            ViajeroDTO dto = new ViajeroDTO();
            dto.setId(entity.getId());
            dto.setUsuario(entity.getUsuario());
            dto.setEdad(entity.getEdad());
            dto.setMail(entity.getMail());
            dto.setPassword(entity.getPassword());

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
    private static ViajeroEntity basicDTO2Entity(ViajeroDTO dto) {
        if (dto != null) {
            ViajeroEntity entity = new ViajeroEntity();
            entity.setId(dto.getId());
            entity.setUsuario(dto.getUsuario());
            entity.setEdad(dto.getEdad());
            entity.setMail(dto.getMail());
            entity.setPassword(dto.getPassword());


            return entity;
        } else {
            return null;
        }
    }


    /**
     * Convierte instancias de ViajeroEntity a ViajeroDTO incluyendo sus relaciones
     * Uno a muchos y Muchos a muchos
     *
     * @param entity Instancia de ViajeroEntity a convertir
     * @return Instancia de ViajeroDTO con los datos recibidos por parámetro
     * @generated
     */
    public static ViajeroDTO fullEntity2DTO(ViajeroEntity entity) {
        if (entity != null) {
            ViajeroDTO dto = basicEntity2DTO(entity);
            dto.setViajes(ViajesConverter.listEntity2DTO(entity.getViajes()));
            //dto.setExperiencias(ExperienciaConverter.listEntity2DTO(entity.getExperiencias()));
            return dto;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de ViajeroDTO a ViajeroroEntity. Incluye todos los
     * atributos de ViajeroEntity.
     *
     * @param dto Instancia de ViajeroDTO a convertir
     * @return Instancia de ViajeroEntity con los datos recibidos por parámetro
     * @generated
     */
    public static ViajeroEntity fullDTO2Entity(ViajeroDTO dto) {
        if (dto != null) {
            ViajeroEntity entity = basicDTO2Entity(dto);
            entity.setViajes(ViajesConverter.listDTO2Entity(dto.getViajes()));
            //entity.setExperiencias(ExperienciaConverter.listDTO2Entity(dto.getExperiencias()));

            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte una colección de instancias de ViajeroEntity a ViajeroDTO. Para cada
     * instancia de ViajeroEntity en la lista, invoca basicEntity2DTO y añade el
     * nuevo ViajeroDTO a una nueva lista
     *
     * @param entities Colección de entidades a convertir
     * @return Collección de instancias de ViajeroDTO
     * @generated
     */
    public static List<ViajeroDTO> listEntity2DTO(List<ViajeroEntity> entities) {
        List<ViajeroDTO> dtos = new ArrayList<>();
        if (entities != null) {
            for (ViajeroEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    /**
     * Convierte una colección de instancias de ViajeroDTO a instancias de
     * ViajeroEntity Para cada instancia se invoca el método basicDTO2Entity
     *
     * @param dtos entities Colección de ViajeroDTO a convertir
     * @return Collección de instancias de ViajeroEntity
     * @generated
     */
    public static List<ViajeroEntity> listDTO2Entity(List<ViajeroDTO> dtos) {
        List<ViajeroEntity> entities = new ArrayList<ViajeroEntity>();
        if (dtos != null) {
            for (ViajeroDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }

}
