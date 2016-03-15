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
     *
    private ViajesConverter() {
    }

    /**
     * Realiza la conversión de VaijesEntity a ViajesDTO. Se invoca cuando otra
     * entidad tiene una referencia a ViajesEntity. Entrega únicamente los
     * atributos proprios de la entidad.
     *
     * @param entity instancia de ViajesEntity a convertir
     * @return instancia de ViajesDTO con los datos recibidos por parámetro
     * @generated
     *
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
     * Realiza la conversión de BookDTO a BookEntity Se invoca cuando otro DTO
     * tiene una referencia a BookDTO Convierte únicamente el ID ya que es el
     * único atributo necesario para guardar la relación en la base de datos
     *
     * @param dto instancia de BookDTO a convertir
     * @return instancia de BookEntity con los datos recibidos por parámetro
     * @generated
     *
    public static BookEntity refDTO2Entity(BookDTO dto) {
        if (dto != null) {
            BookEntity entity = new BookEntity();
            entity.setId(dto.getId());

            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de BookEntity a BookDTO Se invoca cuando se desea
     * consultar la entidad y sus relaciones muchos a uno o uno a uno
     *
     * @param entity instancia de BookEntity a convertir
     * @return Instancia de BookDTO con los datos recibidos por parámetro
     * @generated
     *
    private static BookDTO basicEntity2DTO(BookEntity entity) {
        if (entity != null) {
            BookDTO dto = new BookDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setDescription(entity.getDescription());
            dto.setIsbn(entity.getIsbn());
            dto.setImage(entity.getImage());
            dto.setEditorial(EditorialConverter.refEntity2DTO(entity.getEditorial()));

            return dto;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de BookDTO a BookEntity Se invoca cuando se
     * necesita convertir una instancia de BookDTO con los atributos propios de
     * la entidad y con las relaciones uno a uno o muchos a uno
     *
     * @param dto instancia de BookDTO a convertir
     * @return Instancia de BookEntity creada a partir de los datos de dto
     * @generated
     *
    private static BookEntity basicDTO2Entity(BookDTO dto) {
        if (dto != null) {
            BookEntity entity = new BookEntity();
            entity.setId(dto.getId());
            entity.setName(dto.getName());
            entity.setDescription(dto.getDescription());
            entity.setIsbn(dto.getIsbn());
            entity.setImage(dto.getImage());
            entity.setEditorial(EditorialConverter.refDTO2Entity(dto.getEditorial()));

            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte instancias de BookEntity a BookDTO incluyendo sus relaciones
     * Uno a muchos y Muchos a muchos
     *
     * @param entity Instancia de BookEntity a convertir
     * @return Instancia de BookDTO con los datos recibidos por parámetro
     * @generated
     *
    public static BookDTO fullEntity2DTO(BookEntity entity) {
        if (entity != null) {
            BookDTO dto = basicEntity2DTO(entity);
            dto.setAuthors(AuthorConverter.listEntity2DTO(entity.getAuthors()));
            dto.setReviews(ReviewConverter.listEntity2DTO(entity.getReviews()));
            return dto;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de BookDTO a BookEntity. Incluye todos los
     * atributos de BookEntity.
     *
     * @param dto Instancia de BookDTO a convertir
     * @return Instancia de BookEntity con los datos recibidos por parámetro
     * @generated
     *
    public static BookEntity fullDTO2Entity(BookDTO dto) {
        if (dto != null) {
            BookEntity entity = basicDTO2Entity(dto);
            entity.setAuthors(AuthorConverter.listDTO2Entity(dto.getAuthors()));
            entity.setReviews(ReviewConverter.childListDTO2Entity(dto.getReviews(), entity));
            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte una colección de instancias de BookEntity a BookDTO. Para cada
     * instancia de BookEntity en la lista, invoca basicEntity2DTO y añade el
     * nuevo BookDTO a una nueva lista
     *
     * @param entities Colección de entidades a convertir
     * @return Collección de instancias de BookDTO
     * @generated
     *
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
     * Convierte una colección de instancias de BookDTO a instancias de
     * BookEntity Para cada instancia se invoca el método basicDTO2Entity
     *
     * @param dtos entities Colección de BookDTO a convertir
     * @return Collección de instancias de BookEntity
     * @generated
     *
    public static List<BookEntity> listDTO2Entity(List<BookDTO> dtos) {
        List<BookEntity> entities = new ArrayList<BookEntity>();
        if (dtos != null) {
            for (BookDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }
    * */
}
