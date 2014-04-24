/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import filters.TariffFilter;
import java.util.List;
import objects.Tariff;

/**
 *
 * @author Ольга
 */
public interface TariffDao {
    
    public List<Tariff> getAllTariffList() throws DaoException;

    public Tariff getTariffList(int idTariff) throws DaoException;

    public void updateTariffList(Tariff tariff) throws DaoException;

    public void deleteTariffList(int idTariff) throws DaoException;
    
    public void addSTariffList(Tariff tariff) throws DaoException;
    
    List<Tariff> getFilteredTariffList(TariffFilter tariff) throws DaoException;
}
