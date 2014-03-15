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
public class StringConditionCreator extends AbstractOneConditionCreator<String> {
   
    public StringConditionCreator(String tableName, String columnName) {
        super(tableName, columnName);
    }

    @Override
    public void prepareSelectStatement(PreparedStatement ps) throws SQLException {
        ps.setString(1, value);
    }
}
