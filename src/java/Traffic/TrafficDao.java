/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Traffic;

import java.util.List;

/**
 *
 * @author Ольга
 */
public interface TrafficDao {
     public Traffic getIdService(int idService);
    public void update(Traffic traffic);
    public void delete(int idService);
    public void insert(Traffic traffic);
    public List<Traffic> getAllType();
}
