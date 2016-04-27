package co.edu.uniandes.rest.exceptions;

/**
 * Representa las excepciones de la lógica de ItinerarioLogic
 */
public class LogicException extends Exception {

    /**
     * versión usada en la serialización de la clase
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor por defecto
     */
    public LogicException() {
    }

    /**
     * Constructor con un mensaje
     *
     * @param message mensaje de la excepción
     */
    public LogicException(String message) {
        super(message);
    }

    /**
     * Constructor con una causa
     *
     * @param cause causa de la excepción. Usada para generar la traza.
     */
    public LogicException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor con mensaje y causa.
     *
     * @param message mensaje de la excepción
     * @param cause causa de la excepción. Usada para generar la traza.
     */
    public LogicException(String message, Throwable cause) {
        super(message, cause);
    }

}
