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
public class IntegerConditionCreator extends AbstractOneConditionCreator<Integer> {
    
    public IntegerConditionCreator(String tableName, String columnName) {
        super(tableName, columnName);
    }
    
    public IntegerConditionCreator(String query) {
        super(query);
    }

    @Override
    public void prepareSelectStatement(PreparedStatement ps) throws SQLException {
        ps.setInt(1, value);
    }
    
}
