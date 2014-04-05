/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package legalcontr;

/**
 *
 * @author Ivan
 */
public class LegalContrFilter {
    private String contrID = "";
    private String legalEntity = "";
    private String contrDoc = "";
    private String beginDate = "";

    /**
     * @return the contrID
     */
    public String getContrID() {
        return contrID;
    }

    /**
     * @param contrID the contrID to set
     */
    public void setContrID(String contrID) {
        this.contrID = contrID;
    }

    /**
     * @return the legalEntity
     */
    public String getLegalEntity() {
        return legalEntity;
    }

    /**
     * @param legalEntity the legalEntity to set
     */
    public void setLegalEntity(String legalEntity) {
        this.legalEntity = legalEntity;
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
    public String getBeginDate() {
        return beginDate;
    }

    /**
     * @param beginDate the beginDate to set
     */
    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }
}
