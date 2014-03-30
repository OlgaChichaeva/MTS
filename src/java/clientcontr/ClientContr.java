/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clientcontr;

import Sim.Sim;
import client.Client;
import java.util.Date;

/**
 *
 * @author Ivan
 */
public class ClientContr {
    private Client client;
    private int contrID;
    private Sim sim;
    private String contrDoc;
    private Date beginDate;

    /**
     * @return the clientID
     */
    public int getClientID() {
        return getClient().getСlientID();
    }

    /**
     * @param clientID the clientID to set
     */
    public void setClientID(int clientID) {
        if (getClient() == null) {
            setClient(new Client());
        }
        getClient().setСlientID(clientID);
    }

    /**
     * @return the contrID
     */
    public int getContrID() {
        return contrID;
    }

    /**
     * @param contrID the contrID to set
     */
    public void setContrID(int contrID) {
        this.contrID = contrID;
    }

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
     * @return the contrDoc
     */
    public String getContrDoc() {
        return contrDoc;
    }

    /**
     * @param contrDoc the contrDoc to set
     */
    public void setContrDoc(String contrDoc) {
        this.contrDoc = contrDoc;
    }

    /**
     * @return the beginDate
     */
    public Date getBeginDate() {
        return beginDate;
    }

    /**
     * @param beginDate the beginDate to set
     */
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    /**
     * @return the client
     */
    public Client getClient() {
        return client;
    }

    /**
     * @param client the client to set
     */
    public void setClient(Client client) {
        this.client = client;
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
