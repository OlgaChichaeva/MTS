/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TypeService;

import java.util.List;

/**
 *
 * @author Ольга
 */
public interface TypeServiceDao {
    public TypeService getIdType(int idType);
    public void update(TypeService type);
    public void delete(int idType);
    public void insert(TypeService type);

    public List<TypeService> getAllType();
}
