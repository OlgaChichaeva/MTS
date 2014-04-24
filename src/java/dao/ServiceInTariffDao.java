/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import objects.ServiceInTariff;
import java.util.List;

/**
 *
 * @author Ольга
 */
public interface ServiceInTariffDao {
    public List<ServiceInTariff> getIdTariff(int idTariff) throws DaoException;
    public void update(ServiceInTariff sInT) throws DaoException;
    public void delete(int idTariff) throws DaoException;
    public void deleteConcreteServiceInTariff(ServiceInTariff sInT) throws DaoException;
    public void insert(ServiceInTariff sInT) throws DaoException;
    public List<ServiceInTariff> getAllType() throws DaoException;
}
