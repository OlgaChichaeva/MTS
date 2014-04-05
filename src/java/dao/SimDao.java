/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import objects.Sim;
import java.util.List;

/**
 *
 * @author Ольга
 */
public interface SimDao {
     public Sim getIdSim(int idSim);
    public void update(Sim sim);
    public void delete(int idSim);
    public void insert(Sim sim);

    public List<Sim> getAllType(); 
}
