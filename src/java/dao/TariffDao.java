/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import filters.TariffFilter;
import java.util.List;
import objects.Service;
import objects.Tariff;

/**
 *
 * @author Ольга
 */
public interface TariffDao {
    
    public List<Tariff> getAllTariffList();

    public Tariff getTariffList(int idTariff);

    public void updateTariffList(Tariff tariff);

    public void deleteTariffList(int idTariff);
    
    public void addSTariffList(Tariff tariff);
    
    List<Tariff> getFilteredTariffList(TariffFilter tariff);
}
