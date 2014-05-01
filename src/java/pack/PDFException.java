/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pack;

/**
 *
 * @author Ivan
 */
public class PDFException extends RuntimeException {

    /**
     * Creates a new instance of
     * <code>DaoException</code> without detail message.
     */
    public PDFException() {
    }

    /**
     * Constructs an instance of
     * <code>DaoException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public PDFException(String msg) {
        super(msg);
    }
    
    public PDFException(Throwable cause) {
        super(cause);
    }
    
    public PDFException(String message, Throwable cause) {
        super(message, cause);
    }
}
