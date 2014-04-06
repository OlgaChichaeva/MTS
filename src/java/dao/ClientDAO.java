/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import filters.ClientFilter;
import objects.Client;
import java.util.List;

/**
 *
 * @author Ivan
 */
public interface ClientDAO {
    boolean addClient(Client client);
    boolean deleteClient(int clientID);
    boolean updateClient(Client client);
    List<Client> getAllClients();
    Client getClientByID(int clientID);
    Client getClientByPassport(int series, int number);
    List<Client> getClientsByFirstname(String firstname);
    List<Client> getClientsByLastname(String lastname);
    List<Client> getClientsByMiddlename(String middlename);
    List<Client> getClientsByFullname(String firstname, String lastname, String middlename);
    List<Client> getFilteredClients(ClientFilter client);
}