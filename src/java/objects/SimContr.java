/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import objects.Sim;
import objects.LegalContr;

/**
 *
 * @author Ivan
 */
public class SimContr {
    private Sim sim;
    private LegalContr legalContr;

    /**
     * @return the simID
     */
    public int getSimID() {
        return sim.getSimId();
    }

    /**
     * @param simID the simID to set
     */
    public void setSimID(int simID) {
        if (sim == null) {
            sim = new Sim();
        }
        sim.setSimId(simID);
    }

    /**
     * @return the contrID
     */
    public int getContrID() {
        return legalContr.getContrID();
    }

    /**
     * @param contrID the contrID to set
     */
    public void setContrID(int contrID) {
        if (legalContr == null) {
            legalContr = new LegalContr();
        }
        legalContr.setContrID(contrID);
    }
}
