/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.converters;

import co.edu.uniandes.csw.unitrip.entities.ParadaEntity;
import co.edu.uniandes.rest.dtos.ParadaDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ANDRES
 */
public class ParadaConverter {

    /**
     * Constructor privado para evitar la creación del constructor implícito de
     * Java
     *
     * @generated
     */
    private ParadaConverter() {
    }

    /**
     * Realiza la conversión de ParadaEntity a ParadaDTO. Se invoca cuando otra
     * entidad tiene una referencia a ParadaEntity. Entrega únicamente los
     * atributos proprios de la entidad.
     *
     * @param entity instancia de ParadaEntity a convertir
     * @return instancia de ParadaDTO con los datos recibidos por parámetro
     * @generated
     */
    public static ParadaDTO refEntity2DTO(ParadaEntity entity) {
        if (entity != null) {
            ParadaDTO dto = new ParadaDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setDescripcion(entity.getDescripcion());
            dto.setLatitud(entity.getLatitud());
            dto.setLongitud(entity.getLongitud());
            dto.setFechaI(entity.getFechaI());
            dto.setFechaF(entity.getFechaF());
            dto.setCiudad(entity.getCiudad());

            return dto;
        } else {
            return null;
        }
    }

    /**
     * Realiza la conversión de ParaaDTO a ParadaEntity Se invoca cuando otro
     * DTO tiene una referencia a ParadaDTO Convierte únicamente el ID ya que es
     * el único atributo necesario para guardar la relación en la base de datos
     *
     * @param dto instancia de ParadaDTO a convertir
     * @return instancia de ParadaEntity con los datos recibidos por parámetro
     * @generated
     */
    public static ParadaEntity refDTO2Entity(ParadaDTO dto) {
        if (dto != null) {
            ParadaEntity entity = new ParadaEntity();
            entity.setId(dto.getId());

            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de ParadaEntity a ParadaDTO Se invoca cuando se
     * desea consultar la entidad y sus relaciones muchos a uno o uno a uno
     *
     * @param entity instancia de ParadaEntity a convertir
     * @return Instancia de ParadaDTO con los datos recibidos por parámetro
     * @generated
     */
    private static ParadaDTO basicEntity2DTO(ParadaEntity entity) {
        if (entity != null) {
            ParadaDTO dto = new ParadaDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setDescripcion(entity.getDescripcion());
            dto.setLatitud(entity.getLatitud());
            dto.setLongitud(entity.getLongitud());
            dto.setFechaI(entity.getFechaI());
            dto.setFechaF(entity.getFechaF());
            dto.setCiudad(entity.getCiudad());

            return dto;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de ParadaDTO a ParadaEntity Se invoca cuando se
     * necesita convertir una instancia de ParadaDTO con los atributos propios
     * de la entidad y con las relaciones uno a uno o muchos a uno
     *
     * @param dto instancia de ParadaDTO a convertir
     * @return Instancia de ParadaEntity creada a partir de los datos de dto
     * @generated
     */
    private static ParadaEntity basicDTO2Entity(ParadaDTO dto) {
        if (dto != null) {
            ParadaEntity entity = new ParadaEntity();
            entity.setId(dto.getId());
            entity.setName(dto.getName());
            entity.setDescription(dto.getDescripcion());
            entity.setLatitud(dto.getLatitud());
            entity.setLongitud(dto.getLongitud());
            entity.setFechaI(dto.getFechaI());
            entity.setFechaF(dto.getFechaF());
            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte instancias de ParadaEntity a ParadaDTO incluyendo sus
     * relaciones Uno a muchos y Muchos a muchos
     *
     * @param entity Instancia de ParadaEntity a convertir
     * @return Instancia de ParadaDTO con los datos recibidos por parámetro
     * @generated
     */
    public static ParadaDTO fullEntity2DTO(ParadaEntity entity) {
        if (entity != null) {
            ParadaDTO dto = basicEntity2DTO(entity);
            dto.setEventos(EventoConverter.listEntity2DTO(entity.getEventos()));
            return dto;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de ParadaDTO a ParadaEntity. Incluye todos los
     * atributos de ParadaEntity.
     *
     * @param dto Instancia de ParadaDTO a convertir
     * @return Instancia de ParadaEntity con los datos recibidos por parámetro
     * @generated
     */
    public static ParadaEntity fullDTO2Entity(ParadaDTO dto) {
        if (dto != null) {
            ParadaEntity entity = basicDTO2Entity(dto);
            entity.setEventos(EventoConverter.childListDTO2Entity(dto.getEventos(), entity));
            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte una colección de instancias de ParadaEntity a ParadaDTO. Para
     * cada instancia de ParadaEntity en la lista, invoca basicEntity2DTO y
     * añade el nuevo ParadaDTO a una nueva lista
     *
     * @param entities Colección de entidades a convertir
     * @return Collección de instancias de ParadaDTO
     * @generated
     */
    public static List<ParadaDTO> listEntity2DTO(List<ParadaEntity> entities) {
        List<ParadaDTO> dtos = new ArrayList<ParadaDTO>();
        if (entities != null) {
            for (ParadaEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    /**
     * Convierte una colección de instancias de ParadaDTO a instancias de
     * ParadaEntity Para cada instancia se invoca el método basicDTO2Entity
     *
     * @param dtos entities Colección de ParadaDTO a convertir
     * @return Collección de instancias de ParadaEntity
     * @generated
     */
    public static List<ParadaEntity> listDTO2Entity(List<ParadaDTO> dtos) {
        List<ParadaEntity> entities = new ArrayList<ParadaEntity>();
        if (dtos != null) {
            for (ParadaDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }
}
