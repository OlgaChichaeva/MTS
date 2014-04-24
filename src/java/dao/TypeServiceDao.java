/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import objects.TypeService;
import java.util.List;

/**
 *
 * @author Ольга
 */
public interface TypeServiceDao {
    public TypeService getIdType(int idType) throws DaoException;
    public void update(TypeService type) throws DaoException;
    public void delete(int idType) throws DaoException;
    public void insert(TypeService type) throws DaoException;

    public List<TypeService> getAllType() throws DaoException;
}
