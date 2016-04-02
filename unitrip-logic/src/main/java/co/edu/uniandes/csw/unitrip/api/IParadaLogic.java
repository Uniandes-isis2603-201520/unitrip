/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.unitrip.api;

import co.edu.uniandes.csw.unitrip.entities.ParadaEntity;
import co.edu.uniandes.csw.unitrip.exceptions.BusinesLogicException;
import java.util.List;

/**
 *
 * @author l.castro12
 */
public interface IParadaLogic {

     public List<ParadaEntity> getParadas();

    public ParadaEntity getParada(Long id) throws BusinesLogicException;

    public ParadaEntity createParada(ParadaEntity entity);

    public ParadaEntity updateParada(ParadaEntity entity);

    public void deleteParada(Long id);

}
