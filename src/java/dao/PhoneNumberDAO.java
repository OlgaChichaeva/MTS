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
    boolean addNumber(long number);
    boolean deleteNumber(long number);
    boolean updateNumber(PhoneNumber number);
    List<PhoneNumber> getAllNumbers();
    PhoneNumber getNumber(long number);
    List<PhoneNumber> getNumberBySimID(int simID);
}
