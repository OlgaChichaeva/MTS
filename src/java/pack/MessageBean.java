/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pack;

/**
 * JavaBean для передачи сообщений между страницами.
 * @author Ivan
 */
public class MessageBean {
    
    public final static String ATTR_NAME = "message";
    
    private String message = null;

    /**
     * @return the message
     */
    public String getMessage() {
        return (message == null ? "" : message);
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
