/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import objects.SimContr;

/**
 *
 * @author Ivan
 */
public interface SimContrDAO {
    boolean addSimContr(SimContr simContr);
    boolean deleteSimContrBySimID(int simID);
    boolean deleteSimContrsByContrID(int contrID);
    boolean deleteSimContr(SimContr simContr);
    List<SimContr> getAllSimContrs();
    List<SimContr> getSimContrsBySimID(int simID);
    List<SimContr> getSimContrsByContrID(int contrID);
    SimContr getConcreteSimContr(SimContr simContr);
}
