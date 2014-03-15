/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clientcontr;

import java.util.Date;

/**
 *
 * @author Ivan
 */
public class ClientContr {
    private int clientID;
    private int contrID;
    private int simID;
    private String contrDoc;
    private Date beginDate;

    /**
     * @return the clientID
     */
    public int getClientID() {
        return clientID;
    }

    /**
     * @param clientID the clientID to set
     */
    public void setClientID(int clientID) {
        this.clientID = clientID;
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
        return simID;
    }

    /**
     * @param simID the simID to set
     */
    public void setSimID(int simID) {
        this.simID = simID;
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
}
