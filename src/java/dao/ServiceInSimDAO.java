/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import objects.ServiceInSim;

/**
 *
 * @author Ivan
 */
public interface ServiceInSimDAO {
    public List<ServiceInSim> getIdSim(int idSim);
    boolean deleteBySimID(int simID);
    boolean deleteConcreteServiceInSim(ServiceInSim sInS);
    public boolean insert(ServiceInSim sInS);
    public List<ServiceInSim> getAll();
}
