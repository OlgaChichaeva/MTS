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
    boolean addClient(Client client) throws DaoException;
    boolean deleteClient(int clientID) throws DaoException;
    boolean updateClient(Client client) throws DaoException;
    List<Client> getAllClients() throws DaoException;
    Client getClientByID(int clientID) throws DaoException;
    Client getClientByPassport(int series, int number) throws DaoException;
    List<Client> getClientsByFirstname(String firstname) throws DaoException;
    List<Client> getClientsByLastname(String lastname) throws DaoException;
    List<Client> getClientsByMiddlename(String middlename) throws DaoException;
    List<Client> getClientsByFullname(String firstname, String lastname, String middlename) throws DaoException;
    List<Client> getFilteredClients(ClientFilter client) throws DaoException;
}
