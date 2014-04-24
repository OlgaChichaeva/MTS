/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import objects.ClientContr;
import java.util.List;

/**
 *
 * @author Ivan
 */
public interface ClientContrDAO {
    boolean addContr(ClientContr contr) throws DaoException;
    boolean deleteContr(int contrID) throws DaoException;
    boolean updateContr(ClientContr contr) throws DaoException;
    List<ClientContr> getAllContrs() throws DaoException;
    ClientContr getContrByID(int contrID) throws DaoException;
    ClientContr getContrBySimID(int simID) throws DaoException;
    List<ClientContr> getContrsByClientID(int clientID) throws DaoException;
}
