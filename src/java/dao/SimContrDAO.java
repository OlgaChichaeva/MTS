/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import objects.SimContr;

/**
 *
 * @author Ivan
 */
public interface SimContrDAO {
    boolean addSimContr(SimContr simContr) throws DaoException;
    boolean deleteSimContrBySimID(int simID) throws DaoException;
    boolean deleteSimContrsByContrID(int contrID) throws DaoException;
    boolean deleteSimContr(SimContr simContr) throws DaoException;
    List<SimContr> getAllSimContrs() throws DaoException;
    List<SimContr> getSimContrsBySimID(int simID) throws DaoException;
    List<SimContr> getSimContrsByContrID(int contrID) throws DaoException;
    SimContr getConcreteSimContr(SimContr simContr) throws DaoException;
}
