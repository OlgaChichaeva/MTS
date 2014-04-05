/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import objects.Sim;

/**
 *
 * @author Ivan
 */
public class PhoneNumber {
    private Sim sim;
    private long number;

    /**
     * @return the simID
     */
    public int getSimID() {
        return getSim().getSimId();
    }

    /**
     * @param simID the simID to set
     */
    public void setSimID(int simID) {
        if(getSim() == null) {
            setSim(new Sim());
        }
        getSim().setSimId(simID);
    }

    /**
     * @return the number
     */
    public long getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(long number) {
        this.number = number;
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
}
