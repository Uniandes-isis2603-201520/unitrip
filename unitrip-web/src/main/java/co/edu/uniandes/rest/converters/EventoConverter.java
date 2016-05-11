package co.edu.uniandes.rest.converters;

import co.edu.uniandes.csw.unitrip.entities.EventoEntity;
import co.edu.uniandes.csw.unitrip.entities.ParadaEntity;
import co.edu.uniandes.rest.dtos.EventoDTO;
import java.util.ArrayList;
import java.util.List;

public abstract class EventoConverter {

    /**
     * Constructor privado para evitar la creación del constructor implícito de
     * Java
     *
     * @generated
     */
    private EventoConverter() {
    }

    /**
     * Realiza la conversión de EventoEntity a EventoDTO. Se invoca cuando otra
     * entidad tiene una referencia a EventoEntity. Entrega únicamente los
     * atributos propios de la entidad.
     *
     * @param entity instancia de EventoEntity a convertir
     * @return instancia de EventoDTO con los datos recibidos por parámetro
     * @generated
     */
    public static EventoDTO refEntity2DTO(EventoEntity entity) {
        if (entity != null) {
            EventoDTO dto = new EventoDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setDescription(entity.getDescription());
            dto.setLatitud(entity.getLatitud());
            dto.setLongitud(entity.getLongitud());
            dto.setFechaInicio(entity.getFechaInicio());
            dto.setFechaFin(entity.getFechaFin());
            dto.setCiudad(entity.getCiudad());

            return dto;
        } else {
            return null;
        }
    }

    /**
     * Realiza la conversión de EventoDTO a EventoEntity Se invoca cuando otro
     * DTO tiene una referencia a EventoDTO Convierte únicamente el ID ya que es
     * el único atributo necesario para guardar la relación en la base de datos
     *
     * @param dto instancia de EventoDTO a convertir
     * @return instancia de EventoEntity con los datos recibidos por parámetro
     * @generated
     */
    public static EventoEntity refDTO2Entity(EventoDTO dto) {
        if (dto != null) {
            EventoEntity entity = new EventoEntity();
            entity.setId(dto.getId());

            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de EventoEntity a EventoDTO Se invoca cuando se
     * desea consultar la entidad y sus relaciones muchos a uno o uno a uno
     *
     * @param entity instancia de EventoEntity a convertir
     * @return Instancia de EventoDTO con los datos recibidos por parámetro
     * @generated
     */
    private static EventoDTO basicEntity2DTO(EventoEntity entity) {
        if (entity != null) {
            EventoDTO dto = new EventoDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setDescription(entity.getDescription());
            dto.setLatitud(entity.getLatitud());
            dto.setLongitud(entity.getLongitud());
            dto.setFechaInicio(entity.getFechaInicio());
            dto.setFechaFin(entity.getFechaFin());
            dto.setCiudad(entity.getCiudad());

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
    private static EventoEntity basicDTO2Entity(EventoDTO dto) {
        if (dto != null) {
            EventoEntity entity = new EventoEntity();
            entity.setId(dto.getId());
            entity.setName(dto.getName());
            entity.setDescription(dto.getDescription());
            entity.setLatitud(dto.getLatitud());
            entity.setLongitud(dto.getLongitud());
            entity.setFechaInicio(dto.getFechaInicio());
            entity.setFechaFin(dto.getFechaFin());
            entity.setCiudad(dto.getCiudad());


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
    public static EventoDTO fullEntity2DTO(EventoEntity entity) {
        if (entity != null) {
            EventoDTO dto = basicEntity2DTO(entity);
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
    public static EventoEntity fullDTO2Entity(EventoDTO entity) {
        if (entity != null) {
            EventoEntity dto = basicDTO2Entity(entity);
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
    public static List<EventoDTO> listEntity2DTO(List<EventoEntity> entities) {
        List<EventoDTO> dtos = new ArrayList<>();
        if (entities != null) {
            for (EventoEntity entity : entities) {
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
    public static List<EventoEntity> listDTO2Entity(List<EventoDTO> dtos) {
        List<EventoEntity> entities = new ArrayList<EventoEntity>();
        if (dtos != null) {
            for (EventoDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }

    /**
     * Convierte una instancia de EventoDTO a EventoEntity asignando un valor al
     * atributo org.eclipse.uml2.uml.internal.impl.PropertyImpl@394af115 (name:
     * book, visibility: <unset>) (isLeaf: false) (isStatic: false) (isOrdered:
     * false, isUnique: true, isReadOnly: false) (aggregation: none, isDerived:
     * false, isDerivedUnion: false, isID: false) de ReviewEntity. Se usa cuando
     * se necesita convertir un EventoDTO asignando el libro asociado
     *
     * @param dto Instancia de EventoDTO
     * @param parent Instancia de ParadaEntity
     * @return Instancia de EventoEntity con ParadaEntity asociado
     * @generated
     */
    public static EventoEntity childDTO2Entity(EventoDTO dto, ParadaEntity parent) {
        EventoEntity entity = basicDTO2Entity(dto);
//        entity.setParada(parent);
        return entity;
    }

    /**
     * Convierte una colección de instancias de ventoDTO a EventoEntity
     * asignando el mismo padre para todos. Se usa cuando se necesita crear o
     * actualizar varios EventoEntity con el mismo
     * org.eclipse.uml2.uml.internal.impl.PropertyImpl@394af115 (name: parada,
     * visibility: <unset>) (isLeaf: false) (isStatic: false) (isOrdered: false,
     * isUnique: true, isReadOnly: false) (aggregation: none, isDerived: false,
     * isDerivedUnion: false, isID: false)
     *
     * @param dtos Colección de instancias de EventoDTO
     * @param parent Instancia de ParadaEntity
     * @return Colección de EventoEntity con el atributo
     * org.eclipse.uml2.uml.internal.impl.PropertyImpl@394af115 (name: parada,
     * visibility: <unset>) (isLeaf: false) (isStatic: false) (isOrdered: false,
     * isUnique: true, isReadOnly: false) (aggregation: none, isDerived: false,
     * isDerivedUnion: false, isID: false) asignado
     * @generated
     */
    public static List<EventoEntity> childListDTO2Entity(List<EventoDTO> dtos, ParadaEntity parent) {
        List<EventoEntity> entities = new ArrayList<EventoEntity>();
        if (dtos != null) {
            for (EventoDTO dto : dtos) {
                entities.add(childDTO2Entity(dto, parent));
            }
        }
        return entities;
    }
}
