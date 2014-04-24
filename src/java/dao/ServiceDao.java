/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import objects.Service;
import filters.ServiceFilter;
import java.util.List;

/**
 *
 * @author Ольга
 */
public interface ServiceDao {

    public List<Service> getAllServices() throws DaoException;

    public Service getService(int idService) throws DaoException;

    public void updateService(Service service) throws DaoException;

    public void deleteService(int idService) throws DaoException;
    
    public void addService(Service service) throws DaoException;
    
    List<Service> getFilteredServices(ServiceFilter service) throws DaoException;
}
