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
    
    private final String tableName;
    private final String columnName;
    
    public AbstractOneConditionCreator(String tableName, String columnName) {
        this.tableName = tableName;
        this.columnName = columnName;
    }

    @Override
    public String createSelect() {
        return "SELECT * FROM " + tableName + " WHERE " + columnName + " = ?";
    }


    public void setValue(T value) {
        this.value = value;
    }
}
