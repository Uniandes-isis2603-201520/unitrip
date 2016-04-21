package co.edu.uniandes.csw.unitrip.api;

import co.edu.uniandes.csw.unitrip.entities.ParadaEntity;
import co.edu.uniandes.csw.unitrip.entities.ItinerarioEntity;
import co.edu.uniandes.csw.unitrip.exceptions.BusinesLogicException;
import java.util.List;

public interface IItinerarioLogic {

    public List<ItinerarioEntity> getItinerarios();

    public ItinerarioEntity getItinerario(Long id);// throws BusinesLogicException;

    public ItinerarioEntity createItinerario(ItinerarioEntity entity) throws BusinesLogicException;

    public ItinerarioEntity updateItinerario(ItinerarioEntity entity) throws BusinesLogicException;

    public void deleteItinerario(Long id);

    public List<ParadaEntity> getParadas(Long itinerarioId);

    public ParadaEntity getParada(Long itinerarioId, Long paradaId);

    public ParadaEntity addParada(Long paradaId, Long itinerarioId) throws BusinesLogicException;

    public void removeParada(Long paradaId, Long itinerarioId);

    public List<ParadaEntity> replaceParadas(List<ParadaEntity> paradas, Long itinerarioId)throws BusinesLogicException;

}