/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import objects.ServiceInSim;

/**
 *
 * @author Ivan
 */
public interface ServiceInSimDAO {
    public List<ServiceInSim> getIdSim(int idSim) throws DaoException;
    boolean deleteBySimID(int simID) throws DaoException;
    boolean deleteConcreteServiceInSim(ServiceInSim sInS) throws DaoException;
    public boolean insert(ServiceInSim sInS) throws DaoException;
    public List<ServiceInSim> getAll() throws DaoException;
}
