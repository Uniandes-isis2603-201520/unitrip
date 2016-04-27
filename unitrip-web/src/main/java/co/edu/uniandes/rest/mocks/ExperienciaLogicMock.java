package co.edu.uniandes.rest.mocks;

/**
 * Mock del recurso Experiencias (Mock del servicio REST)
 *
 * @author Asistente
 */
import co.edu.uniandes.rest.dtos.ExperienciaDTO;
import co.edu.uniandes.rest.exceptions.LogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/*
 * ExperienciaLogicMock
 * Mock del recurso Experiencias (Mock del servicio REST)
 */
@Named
@ApplicationScoped
public class ExperienciaLogicMock {

    // objeto para presentar logs de las operaciones
    private final static Logger logger = Logger.getLogger(ExperienciaLogicMock.class.getName());

    // listado de Experiencias
    private static ArrayList<ExperienciaDTO> experiencias;

    /**
     * Constructor. Crea los datos de ejemplo.
     */
    public ExperienciaLogicMock() {

        if (experiencias == null) {
            experiencias = new ArrayList<ExperienciaDTO>();
            experiencias.add(new ExperienciaDTO(1L, "Bogota", "Muy bueno", "rutaImagen1"));
            experiencias.add(new ExperienciaDTO(2L, "Cali", "Muy bueno", "rutaImagen1"));
            experiencias.add(new ExperienciaDTO(3L, "Medellin", "Muy bueno", "rutaImagen1"));
        }

        // indica que se muestren todos los mensajes
        logger.setLevel(Level.INFO);

        // muestra información
        logger.info("Inicializa la lista de Experiencias");
        logger.info("Experiencias" + experiencias);
    }

    /**
     * Obtiene el listado de Experiencias.
     *
     * @return lista de Experiencias
     * @throws LogicException cuando no existe la lista en memoria
     */
    public List<ExperienciaDTO> getExperiencias() throws LogicException {
        if (experiencias == null) {
            logger.severe("Error interno: lista de Experiencias no existe.");
            throw new LogicException("Error interno: lista de Experiencias no existe.");
        }

        logger.info("retornando todas los Experiencias");
        return experiencias;
    }

    /**
     * Obtiene un Experiencias
     *
     * @param id identificador del Experiencia
     * @return Experiencias encontrado
     * @throws LogicException cuando el Experiencia no existe
     */
    public ExperienciaDTO getExperiencia(Long id) throws LogicException {
        logger.info("recibiendo solicitud de Experiencia con id " + id);

        // busca el Experiencia con el id suministrado
        for (ExperienciaDTO experiencia : experiencias) {
            if (Objects.equals(experiencia.getId(), id)) {
                logger.info("retornando experiencia " + experiencia);
                return experiencia;
            }
        }

        // si no encuentra el Experiencia
        logger.severe("No existe Experiencia con ese id");
        throw new LogicException("No existe Experiencia con ese id");
    }

    /**
     * Agrega un Experiencia a la lista.
     *
     * @param newExperiencia Experiencia a adicionar
     * @throws LogicException cuando ya existe un Experiencia con el id
     * suministrado
     * @return Experiencia agregado
     */
    public ExperienciaDTO createExperiencia(ExperienciaDTO newExperiencia) throws LogicException {
        logger.info("recibiendo solicitud de agregar Experiencia " + newExperiencia);

        // el nuevo Experiencia tiene id ?
        if (newExperiencia.getId() != null) {
            // busca la ciudad con el id suministrado
            for (ExperienciaDTO experiencia : experiencias) {
                // si existe un Experiencia con ese id
                if (Objects.equals(experiencia.getId(), newExperiencia.getId())) {
                    logger.severe("Ya existe una experiencia con ese id");
                    throw new LogicException("Ya existe una experiencia con ese id");
                }
            }

            // el nuevo Experiencia no tiene id ?
        } else {

            // genera un id para el Experiencia
            logger.info("Generando id para el nueva Experiencia");
            long newId = 1;
            for (ExperienciaDTO experiencia : experiencias) {
                if (newId <= experiencia.getId()) {
                    newId = experiencia.getId() + 1;
                }
            }
            newExperiencia.setId(newId);
        }

        // agrega el Experiencia
        logger.info("agregando Experiencia " + newExperiencia);
        experiencias.add(newExperiencia);
        return newExperiencia;
    }

    /**
     * Actualiza los datos de un Experiencia
     *
     * @param id identificador del Experiencia a modificar
     * @param updatedExperiencia Experiencia a modificar
     * @return datos del Experiencia modificada
     * @throws LogicException cuando no existe un Experiencia con el id
     * suministrado
     */
    public ExperienciaDTO updateExperiencia(Long id, ExperienciaDTO updatedExperiencia) throws LogicException {
        logger.info("recibiendo solictud de modificar Experiencia " + updatedExperiencia);

        // busca el Experiencia con el id suministrado
        for (ExperienciaDTO experiencia : experiencias) {
            if (Objects.equals(experiencia.getId(), id)) {

                // modifica el Experiencia
                experiencia.setId(updatedExperiencia.getId());
                experiencia.setName(updatedExperiencia.getName());

                // retorna el Experiencia modificada
                logger.info("Modificando experiencia " + experiencia);
                return experiencia;
            }
        }

        // no encontró el Experiencia con ese id ?
        logger.severe("No existe una experiencia con ese id");
        throw new LogicException("No existe un experiencia con ese id");
    }

    /**
     * Elimina los datos de un Experiencia
     *
     * @param id identificador del Experiencias a eliminar
     * @throws LogicException cuando no existe un Experiencia con el id
     * suministrado
     */
    public void deleteExperiencia(Long id) throws LogicException {
        logger.info("recibiendo solictud de eliminar Experiencia con id " + id);

        // busca el Experiencia con el id suministrado
        for (ExperienciaDTO experiencia : experiencias) {
            if (Objects.equals(experiencia.getId(), id)) {

                // elimina el Experiencia
                logger.info("eliminando experiencia " + experiencia);
                experiencias.remove(experiencia);
                return;
            }
        }

        // no encontró el experiencia con ese id ?
        logger.severe("No existe una experiencia con ese id");
        throw new LogicException("No existe una experiencia con ese id");
    }
}
