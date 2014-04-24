/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

/**
 * Исключение безопасности.
 * @author Ivan
 */
public class SecurityException extends RuntimeException {

    /**
     * Creates a new instance of
     * <code>SecurityException</code> without detail message.
     */
    public SecurityException() {
    }

    /**
     * Constructs an instance of
     * <code>SecurityException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public SecurityException(String msg) {
        super(msg);
    }
    
    public SecurityException(Throwable cause) {
        super(cause);
    }
}
