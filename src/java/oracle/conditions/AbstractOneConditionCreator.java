/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oracle.conditions;

/**
 *
 * @author Ivan
 */
public abstract class AbstractOneConditionCreator<T> extends ConditionCreator {
    
    protected T value;
    
    private final String query;
    
    public AbstractOneConditionCreator(String tableName, String columnName) {
        query = "SELECT * FROM " + tableName + " WHERE " + columnName + " = ?";
    }
    
    public AbstractOneConditionCreator(String query) {
        this.query = query;
    }

    @Override
    public String createSelect() {
        return query;
    }


    public void setValue(T value) {
        this.value = value;
    }
}
