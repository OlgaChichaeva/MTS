/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sim;

/**
 *create table sim
(
  sim_id            NUMBER   NOT NULL     CONSTRAINT sim_pk_sim_id   PRIMARY KEY,       
  ID_tariff         NUMBER NOT NULL CONSTRAINT sim_fk_tariff_id REFERENCES tariff_list(ID_tariff),
  account           NUMBER 
);  
 * @author Ольга
 */
public class Sim {
        private int simId;
        private int tariffId;
        private int account;

    /**
     * @return the simId
     */
    public int getSimId() {
        return simId;
    }

    /**
     * @param simId the simId to set
     */
    public void setSimId(int simId) {
        this.simId = simId;
    }

    /**
     * @return the tariffId
     */
    public int getTariffId() {
        return tariffId;
    }

    /**
     * @param tariffId the tariffId to set
     */
    public void setTariffId(int tariffId) {
        this.tariffId = tariffId;
    }

    /**
     * @return the account
     */
    public int getAccount() {
        return account;
    }

    /**
     * @param account the account to set
     */
    public void setAccount(int account) {
        this.account = account;
    }

}
