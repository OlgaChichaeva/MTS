/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import objects.Tariff;

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
        private Tariff tariff;
        private double account;

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
        return tariff.getIdTariff();
    }

    /**
     * @param tariffId the tariffId to set
     */
    /*public void setTariffId(int tariffId) {
        this.tariffId = tariffId;
    }*/

    /**
     * @return the account
     */
    public double getAccount() {
        return account;
    }

    /**
     * @param account the account to set
     */
    public void setAccount(double account) {
        this.account = account;
    }

    /**
     * @return the tariff
     */
    public Tariff getTariff() {
        return tariff;
    }

    /**
     * @param tariff the tariff to set
     */
    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

}
