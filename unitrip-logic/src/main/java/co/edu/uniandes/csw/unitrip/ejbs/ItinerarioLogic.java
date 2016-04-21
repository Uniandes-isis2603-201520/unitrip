package co.edu.uniandes.csw.unitrip.ejbs;


import co.edu.uniandes.csw.unitrip.api.IItinerarioLogic;
import co.edu.uniandes.csw.unitrip.entities.ParadaEntity;
import co.edu.uniandes.csw.unitrip.entities.ItinerarioEntity;
import co.edu.uniandes.csw.unitrip.exceptions.BusinesLogicException;
import co.edu.uniandes.csw.unitrip.persistence.ItinerarioPersistence;
import co.edu.uniandes.csw.unitrip.persistence.ParadaPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ItinerarioLogic implements IItinerarioLogic {

    private static final Logger logger = Logger.getLogger(ItinerarioLogic.class.getName());
    @Inject
    private ItinerarioPersistence persistence;

    @Inject
    private ParadaPersistence paradaPersistence;

    @Override
    public List<ItinerarioEntity> getItinerarios() {
         logger.info("Inicia proceso de consultar todos los Itinerarios");
        List<ItinerarioEntity> itinerarios = persistence.findAll();
        logger.info("Termina proceso de consultar todos los Itinerarios");
        return itinerarios;
    }

    @Override
    public ItinerarioEntity getItinerario(Long id) {
        logger.log(Level.INFO, "Inicia proceso de consultar itinerario con id={0}", id);
        ItinerarioEntity itinerario = persistence.find(id);
        if (itinerario == null) {
            logger.log(Level.SEVERE, "El itinerario con el id {0} no existe", id);
            throw new IllegalArgumentException("El itinerario solicitado no existe");
        }
        logger.log(Level.INFO, "Termina proceso de consultar itinerario con id={0}", id);
        return itinerario;
    }

    @Override
    public ItinerarioEntity createItinerario(ItinerarioEntity entity) throws BusinesLogicException {
         logger.info("Inicia proceso de creación de itinerario");
        if (!validateDescripcion(entity.getDescripcion())) {
            throw new BusinesLogicException("la descripcion es inválida");
        }
        persistence.create(entity);
        logger.info("Termina proceso de creación de itinerario");
        return entity;
    }

    @Override
    public ItinerarioEntity updateItinerario(ItinerarioEntity entity) throws BusinesLogicException {
        logger.log(Level.INFO, "Inicia proceso de actualizar libro con id={0}", entity.getId());
        if (!validateDescripcion(entity.getDescripcion())) {
            throw new BusinesLogicException("la descripcion no es valido");
        }
        ItinerarioEntity newEntity = persistence.update(entity);
        logger.log(Level.INFO, "Termina proceso de actualizar itinerario con id={0}", entity.getId());
        return newEntity;
    }

    @Override
    public void deleteItinerario(Long id) {
       logger.log(Level.INFO, "Inicia proceso de borrar itinerario con id={0}", id);
        persistence.delete(id);
        logger.log(Level.INFO, "Termina proceso de borrar itinerario con id={0}", id);
    }

    @Override
    public List<ParadaEntity> getParadas(Long itinerarioId) {
        return persistence.find(itinerarioId).getParadas();
    }

    @Override
    public ParadaEntity getParada(Long itinerarioId, Long paradaId) {
        List<ParadaEntity> paradas = persistence.find(itinerarioId).getParadas();
        ParadaEntity paradaEntity = new ParadaEntity();
        paradaEntity.setId(paradaId);
        int index = paradas.indexOf(paradaEntity);
        if (index >= 0) {
            return paradas.get(index);
        }
        return null;
    }

    @Override
    public ParadaEntity addParada(Long paradaId, Long itinerarioId) throws BusinesLogicException {
        ItinerarioEntity itinerarioEntity = persistence.find(itinerarioId);
        ParadaEntity paradaEntity = paradaPersistence.find(paradaId);
        if (paradaEntity == null) {
            throw new IllegalArgumentException("La parada no existe");
        }
        itinerarioEntity.getParadas().add(paradaEntity);
        return paradaEntity;


    }

    @Override
    public void removeParada(Long Id, Long itinerarioId) {
        ItinerarioEntity itinerarioEntity = persistence.find(itinerarioId);
        ParadaEntity paradaEntity = new ParadaEntity();
        itinerarioEntity.setId(itinerarioId);
        itinerarioEntity.getParadas().remove(paradaEntity);
    }

    @Override
    public List<ParadaEntity> replaceParadas(List<ParadaEntity> paradas, Long itinerarioId) throws BusinesLogicException {
        ItinerarioEntity itinerarioEntity = persistence.find(itinerarioId);
        List<ParadaEntity> paradaList = paradaPersistence.findAll(); // esto como que no es necesario
        itinerarioEntity.setParadas(paradas);
        return itinerarioEntity.getParadas();
    }

    private boolean validateDescripcion(String desc) {
        if (desc == null || desc.isEmpty()) {
            return false;
        }
        return true;
    }


}