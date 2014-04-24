/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pack;

import org.apache.log4j.Logger;

/**
 * Класс, хранящий логгеры.
 * @author Ivan
 */
public class LogManager {
    private final static Logger MTSLogger = Logger.getLogger("MTSLogger");
    
    public final static Logger LOG = MTSLogger;
    
    private LogManager() {}
}
