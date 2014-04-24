/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import objects.User;

/**
 *
 * @author Ivan
 */
public interface UserDAO {
    boolean addUser(User user) throws DaoException;
    boolean deleteUser(int userID) throws DaoException;
    boolean updateUser(User user) throws DaoException;
    List<User> getAllUsers() throws DaoException;
    User getUserByUserName(String userName) throws DaoException;
}
