/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import objects.Service;
import objects.Service;
import filters.ServiceFilter;
import java.util.List;

/**
 *
 * @author Ольга
 */
public interface ServiceDao {

    public List<Service> getAllServices();

    public Service getService(int idService);

    public void updateService(Service service);

    public void deleteService(int idService);
    
    public void addService(Service service);
    
    List<Service> getFilteredServices(ServiceFilter service);
}
