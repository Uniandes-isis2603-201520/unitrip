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
        return persistence.findAll();
    }

    @Override
    public ItinerarioEntity getItinerario(Long id) throws BusinesLogicException {
        ItinerarioEntity itinerario = persistence.find(id);
        if (itinerario == null) {
            throw new BusinesLogicException("There's no itinerario with requested id");
        }
        return itinerario;
    }

    @Override
    public ItinerarioEntity createItinerario(ItinerarioEntity entity) {
        persistence.create(entity);
        return entity;
    }

    @Override
    public ItinerarioEntity updateItinerario(ItinerarioEntity entity) {
        logger.log(Level.INFO, "Inicia proceso de actualizar itinerario con id={0}", entity.getId());
        ItinerarioEntity newEntity = entity;
        return persistence.update(newEntity);
    }

    @Override
    public void deleteItinerario(Long id) {
        persistence.delete(id);
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
    public ParadaEntity addParada(Long paradaId, Long itinerarioId) {
        ItinerarioEntity itinerarioEntity = persistence.find(itinerarioId);
        ParadaEntity paradaEntity = paradaPersistence.find(paradaId);
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
    public List<ParadaEntity> replaceParadas(List<ParadaEntity> paradas, Long itinerarioId) {
        ItinerarioEntity itinerarioEntity = persistence.find(itinerarioId);
        List<ParadaEntity> paradaList = paradaPersistence.findAll(); // esto como que no es necesario
        itinerarioEntity.setParadas(paradas);
        return itinerarioEntity.getParadas();
    }
}