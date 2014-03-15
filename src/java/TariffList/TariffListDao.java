/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TariffList;

import java.util.List;
import Service.Service;

/**
 *
 * @author Ольга
 */
public interface TariffListDao {
    
    public List<TariffList> getAllTariffList();

    public TariffList getTariffList(int idTariff);

    public void updateTariffList(TariffList tariff);

    public void deleteTariffList(int idTariff);
    
    public void addSTariffList(TariffList tariff);
}
