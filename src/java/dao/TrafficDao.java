/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import objects.Traffic;
import java.util.List;

/**
 *
 * @author Ольга
 */
public interface TrafficDao {
     public List<Traffic> getBySimId(int simID) throws DaoException;
    public void update(Traffic traffic) throws DaoException;
    public void delete(int idService) throws DaoException;
    public void insert(Traffic traffic) throws DaoException;
    public List<Traffic> getByIDService(int idService) throws DaoException;
}
