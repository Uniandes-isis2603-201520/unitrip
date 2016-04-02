/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.unitrip.ejbs;

import co.edu.uniandes.csw.unitrip.api.IParadaLogic;
import co.edu.uniandes.csw.unitrip.entities.ParadaEntity;
import co.edu.uniandes.csw.unitrip.exceptions.BusinesLogicException;
import co.edu.uniandes.csw.unitrip.persistence.ParadaPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author l.castro12
 */
@Stateless
public class ParadaLogic implements IParadaLogic {

    @Inject
    private ParadaPersistence persistence;


    @Override
    public List<ParadaEntity> getParadas() {
        return persistence.findAll();
    }

    @Override
    public ParadaEntity getParada(Long id) throws BusinesLogicException {
        ParadaEntity parada = persistence.find(id);
        if (parada == null) {
            throw new BusinesLogicException("There's no parada with requested id");
        }
        return parada;
    }

    @Override
    public ParadaEntity createParada(ParadaEntity entity) {
        persistence.create(entity);
        return entity;
    }

    @Override
    public ParadaEntity updateParada(ParadaEntity entity) {
        ParadaEntity newEntity = entity;
        return persistence.update(newEntity);
    }

    @Override
    public void deleteParada(Long id) {
        persistence.delete(id);
    }
}

