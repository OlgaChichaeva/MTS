/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import objects.Sim;
import java.util.List;

/**
 *
 * @author Ольга
 */
public interface SimDao {
    public Sim getIdSim(int idSim) throws DaoException;
    public void update(Sim sim) throws DaoException;
    public void delete(int idSim) throws DaoException;
    public void insert(Sim sim) throws DaoException;

    public List<Sim> getAllSim() throws DaoException;
    public List<Sim> getSimListByClientID(int clientID) throws DaoException; 
}
