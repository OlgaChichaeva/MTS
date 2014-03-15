/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clientcontr;

import java.util.List;

/**
 *
 * @author Ivan
 */
public interface ClientContrDAO {
    boolean addContr(ClientContr contr);
    boolean deleteContr(int contrID);
    boolean updateContr(ClientContr contr);
    List<ClientContr> getAllContrs();
    ClientContr getContrByID(int contrID);
    ClientContr getContrBySimID(int simID);
    List<ClientContr> getContrsByClientID(int clientID);
}
