package co.edu.uniandes.csw.unitrip.ejbs;

import co.edu.uniandes.csw.unitrip.api.IItinerarioLogic;
import co.edu.uniandes.csw.unitrip.entities.ParadaEntity;
import co.edu.uniandes.csw.unitrip.entities.ItinerarioEntity;
import co.edu.uniandes.csw.unitrip.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.unitrip.persistence.ParadaPersistence;
import co.edu.uniandes.csw.unitrip.persistence.ItinerarioPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ItinerarioLogic implements IItinerarioLogic {

    @Inject
    private ItinerarioPersistence persistence;

    @Inject
    private ParadaPersistence authorPersistence;

    @Override
    public List<ItinerarioEntity> getItinerarios() {
        return persistence.findAll();
    }

    @Override
    public ItinerarioEntity getItinerario(Long id) throws BusinessLogicException {
        ItinerarioEntity itinerario = persistence.find(id);
        if (itinerario == null) {
            throw new BusinessLogicException("There's no itinerario with requested id");
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
    public ParadaEntity getParada(Long itinerarioId, Long authorId) {
        List<ParadaEntity> paradas = persistence.find(itinerarioId).getParadas();
        ParadaEntity authorEntity = new ParadaEntity();
        authorEntity.setId(authorId);
        int index = paradas.indexOf(authorEntity);
        if (index >= 0) {
            return paradas.get(index);
        }
        return null;
    }

    @Override
    public ParadaEntity addParada(Long authorId, Long itinerarioId) {
        ItinerarioEntity itinerarioEntity = persistence.find(itinerarioId);
        ParadaEntity authorEntity = authorPersistence.find(authorId);
        itinerarioEntity.getParadas().add(authorEntity);
        return authorEntity;
    }

    @Override
    public void removeParada(Long authorId, Long itinerarioId) {
        ItinerarioEntity itinerarioEntity = persistence.find(itinerarioId);
        ParadaEntity authorEntity = new ParadaEntity();
        authorEntity.setId(authorId);
        itinerarioEntity.getParadas().remove(authorEntity);
    }

    @Override
    public List<ParadaEntity> replaceParadas(List<ParadaEntity> paradas, Long itinerarioId) {
        ItinerarioEntity itinerarioEntity = persistence.find(itinerarioId);
        List<ParadaEntity> authorList = authorPersistence.findAll();
        itinerarioEntity.setParadas(paradas);
        return itinerarioEntity.getParadas();
    }
}