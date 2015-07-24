/**
 * 
 */
package com.uesocc.sicmec.framework.general;

/**
 * @Autor Pablo.Portillo
 * @FechaCreacion 09/09/2014.11:07:37
 * @Empresa Grupo.GD
 */
public class InvalidDocumentException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidDocumentException() {
    }

    public InvalidDocumentException(String message) {
        super(message);
    }

    public InvalidDocumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidDocumentException(Throwable cause) {
        super(cause);
    }

    public InvalidDocumentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
