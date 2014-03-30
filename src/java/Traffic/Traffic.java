/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Traffic;

import Service.Service;
import Sim.Sim;
import java.util.Date;

/**
 *create table traffic(
  sim_id    number NOT NULL CONSTRAINT traffic_fk_sim_id REFERENCES sim(sim_id)
  ON DELETE CASCADE,
  ID_service number CONSTRAINT traffic_fk_service_id REFERENCES service(ID_service)
  ON DELETE CASCADE,
  amount    number,
  cost      number,
  time       date
);
 * @author Ольга
 */
public class Traffic {
    private Sim sim;;
    private Service service;;
    private double amount;  
    private double cost; 
    private Date date;

    /**
     * @return the idSim
     */
    public int getIdSim() {
        return getSim().getSimId();
    }

    /**
     * @param idSim the idSim to set
     */
    public void setIdSim(int idSim) {
        if (getSim() == null) {
            setSim(new Sim());
        }
        getSim().setSimId(idSim);
    }

    /**
     * @return the idService
     */
    public int getIdService() {
        return getService().getIdService();
    }

    /**
     * @param idService the idService to set
     */
    public void setIdService(int idService) {
        if (getService() == null) {
            setService(new Service());
        }
        getService().setIdService(idService);
    }

    /**
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * @return the cost
     */
    public double getCost() {
        return cost;
    }

    /**
     * @param cost the cost to set
     */
    public void setCost(double cost) {
        this.cost = cost;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the sim
     */
    public Sim getSim() {
        return sim;
    }

    /**
     * @param sim the sim to set
     */
    public void setSim(Sim sim) {
        this.sim = sim;
    }

    /**
     * @return the service
     */
    public Service getService() {
        return service;
    }

    /**
     * @param service the service to set
     */
    public void setService(Service service) {
        this.service = service;
    }
}
