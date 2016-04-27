package co.edu.uniandes.rest.converters;

import co.edu.uniandes.csw.unitrip.entities.ExperienciaEntity;
import co.edu.uniandes.rest.dtos.ExperienciaDTO;
import java.util.ArrayList;
import java.util.List;

public abstract class ExperienciaConverter {

    /**
     * Constructor privado para evitar la creación del constructor implícito de
     * Java
     *
     * @generated
     */
    private ExperienciaConverter() {
    }

    /**
     * Realiza la conversión de ExperienciaEntity a ExperienciaDTO. Se invoca
     * cuando otra entidad tiene una referencia a ExperiencaEntity. Entrega
     * únicamente los atributos propios de la entidad.
     *
     * @param entity instancia de ExperienciasEntity a convertir
     * @return instancia de ExperienciaDTO con los datos recibidos por parámetro
     * @generated
     */
    public static ExperienciaDTO refEntity2DTO(ExperienciaEntity entity) {
        if (entity != null) {
            ExperienciaDTO dto = new ExperienciaDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setDescripcion(entity.getDescripcion());
            dto.setRutaI(entity.getImage());

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
    public static ExperienciaEntity refDTO2Entity(ExperienciaDTO dto) {
        if (dto != null) {
            ExperienciaEntity entity = new ExperienciaEntity();
            entity.setId(dto.getId());

            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de ViajeEntity a ViajeDTO Se invoca cuando se
     * desea consultar la entidad y sus relaciones muchos a uno o uno a uno
     *
     * @param entity instancia de ViajeEntity a convertir
     * @return Instancia de BookDTO con los datos recibidos por parámetro
     * @generated
     */
    private static ExperienciaDTO basicEntity2DTO(ExperienciaEntity entity) {
        if (entity != null) {
            ExperienciaDTO dto = new ExperienciaDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setDescripcion(entity.getDescripcion());
            dto.setRutaI(entity.getImage());

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
    private static ExperienciaEntity basicDTO2Entity(ExperienciaDTO dto) {
        if (dto != null) {
            ExperienciaEntity entity = new ExperienciaEntity();
            entity.setId(dto.getId());
            entity.setName(dto.getName());
            entity.setDescription(dto.getDescripcion());
            entity.setImage(dto.getRutaI());

            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte instancias de EventoEntity a EvenoDTO incluyendo sus relaciones
     * Uno a muchos y Muchos a muchos
     *
     * @param entity Instancia de EventoEntity a convertir
     * @return Instancia de EventoDTO con los datos recibidos por parámetro
     * @generated
     */
    public static ExperienciaDTO fullEntity2DTO(ExperienciaEntity entity) {
        if (entity != null) {
            ExperienciaDTO dto = basicEntity2DTO(entity);
            return dto;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de EventoDTO a EventoEntity. Incluye todos los
     * atributos de EventoEntity.
     *
     * @param entity Instancia de EventoDTO a convertir
     * @return Instancia de ViajeEntity con los datos recibidos por parámetro
     * @generated
     */
    public static ExperienciaEntity fullDTO2Entity(ExperienciaDTO entity) {
        if (entity != null) {
            ExperienciaEntity dto = basicDTO2Entity(entity);
            return dto;
        } else {
            return null;
        }
    }

    /**
     * Convierte una colección de instancias de ViajeEntity a ViajeDTO. Para
     * cada instancia de ViajeEntity en la lista, invoca basicEntity2DTO y añade
     * el nuevo ViajeDTO a una nueva lista
     *
     * @param entities Colección de entidades a convertir
     * @return Collección de instancias de ViajeDTO
     * @generated
     */
    public static List<ExperienciaDTO> listEntity2DTO(List<ExperienciaEntity> entities) {
        List<ExperienciaDTO> dtos = new ArrayList<>();
        if (entities != null) {
            for (ExperienciaEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    /**
     * Convierte una colección de instancias de ExperienciaDTO a instancias de
     * ExperienciaEntity Para cada instancia se invoca el método basicDTO2Entity
     *
     * @param dtos entities Colección de ExperienciaDTO a convertir
     * @return Collección de instancias de ExperienciaEntity
     * @generated
     */
    public static List<ExperienciaEntity> listDTO2Entity(List<ExperienciaDTO> dtos) {
        List<ExperienciaEntity> entities = new ArrayList<ExperienciaEntity>();
        if (dtos != null) {
            for (ExperienciaDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }
}
