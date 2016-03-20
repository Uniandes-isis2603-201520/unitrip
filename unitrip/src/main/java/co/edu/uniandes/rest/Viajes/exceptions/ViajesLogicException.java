package co.edu.uniandes.rest.Viajes.exceptions;

/**
 * Representa las excepciones de la lógica de ViajesLogic 
 */
public class ViajesLogicException extends Exception {

	/**
	 * versión usada en la serialización de la clase
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor por defecto
	 */
	public ViajesLogicException() {
	}

	/**
	 * Constructor con un mensaje
	 * @param message mensaje de la excepción
	 */
	public ViajesLogicException(String message) {
		super(message);
	}

	/**
	 * Constructor con una causa
	 * @param cause causa de la excepción. Usada para generar la traza.
	 */
	public ViajesLogicException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructor con mensaje y causa.
	 * @param message mensaje de la excepción
	 * @param cause causa de la excepción. Usada para generar la traza.
	 */
	public ViajesLogicException(String message, Throwable cause) {
		super(message, cause);
	}

}
