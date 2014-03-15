/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oracle.conditions;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Ivan
 */
public abstract class ConditionCreator {

    public abstract String createSelect();
    
    public abstract void prepareSelectStatement(PreparedStatement ps) throws SQLException;
}
