/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import objects.PhoneNumber;

/**
 *
 * @author Ivan
 */
public interface PhoneNumberDAO {
    boolean addNumber(long number) throws DaoException;
    boolean deleteNumber(long number) throws DaoException;
    boolean updateNumber(PhoneNumber number) throws DaoException;
    List<PhoneNumber> getAllNumbers() throws DaoException;
    PhoneNumber getNumber(long number) throws DaoException;
    PhoneNumber getNumberBySimID(int simID) throws DaoException;
}
