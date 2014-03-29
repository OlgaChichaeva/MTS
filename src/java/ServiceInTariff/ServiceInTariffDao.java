/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiceInTariff;

import java.util.List;

/**
 *
 * @author Ольга
 */
public interface ServiceInTariffDao {
    public List<ServiceInTariff> getIdTariff(int idTariff);
    public void update(ServiceInTariff sInT);
    public void delete(int idTariff);
    public void insert(ServiceInTariff sInT);
    public List<ServiceInTariff> getAllType();
}
