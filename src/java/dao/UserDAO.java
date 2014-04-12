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
    boolean addUser(User user);
    boolean deleteUser(int userID);
    boolean updateUser(User user);
    List<User> getAllUsers();
    User getUserByUserName(String userName);
}
