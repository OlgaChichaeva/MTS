/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

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
        return getSim().getSimId();
    }

    /**
     * @param simID the simID to set
     */
    public void setSimID(int simID) {
        if (getSim() == null) {
            setSim(new Sim());
        }
        getSim().setSimId(simID);
    }

    /**
     * @return the contrID
     */
    public int getContrID() {
        return getLegalContr().getContrID();
    }

    /**
     * @param contrID the contrID to set
     */
    public void setContrID(int contrID) {
        if (getLegalContr() == null) {
            setLegalContr(new LegalContr());
        }
        getLegalContr().setContrID(contrID);
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
     * @return the legalContr
     */
    public LegalContr getLegalContr() {
        return legalContr;
    }

    /**
     * @param legalContr the legalContr to set
     */
    public void setLegalContr(LegalContr legalContr) {
        this.legalContr = legalContr;
    }
}
