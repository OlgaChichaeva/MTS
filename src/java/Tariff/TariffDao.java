/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tariff;

import java.util.List;
import Service.Service;

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
}
