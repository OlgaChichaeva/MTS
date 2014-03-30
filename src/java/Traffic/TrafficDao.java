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
     public List<Traffic> getBySimId(int simID);
    public void update(Traffic traffic);
    public void delete(int idService);
    public void insert(Traffic traffic);
    public List<Traffic> getByIDService(int idService);
}
