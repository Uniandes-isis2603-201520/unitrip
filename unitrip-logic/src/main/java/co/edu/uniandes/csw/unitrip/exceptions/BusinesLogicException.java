/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.unitrip.exceptions;

/**
 *
 * @author ANDRES
 */
public class BusinesLogicException extends Exception {

    public BusinesLogicException() {
    }

    /**
     * Constructor con un mensaje
     *
     * @param message mensaje de la excepción
     */
    public BusinesLogicException(String message) {
        super(message);
    }

    /**
     * Constructor con una causa
     *
     * @param cause causa de la excepción. Usada para generar la traza.
     */
    public BusinesLogicException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor con mensaje y causa.
     *
     * @param message mensaje de la excepción
     * @param cause causa de la excepción. Usada para generar la traza.
     */
    public BusinesLogicException(String message, Throwable cause) {
        super(message, cause);
    }
}
