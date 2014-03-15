/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Traffic;

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
 private int idSim;
    private int idService;
    private double amount;  
    private double cost; 
    private String date;

    /**
     * @return the idSim
     */
    public int getIdSim() {
        return idSim;
    }

    /**
     * @param idSim the idSim to set
     */
    public void setIdSim(int idSim) {
        this.idSim = idSim;
    }

    /**
     * @return the idService
     */
    public int getIdService() {
        return idService;
    }

    /**
     * @param idService the idService to set
     */
    public void setIdService(int idService) {
        this.idService = idService;
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
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }
}
