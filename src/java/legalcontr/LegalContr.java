/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package legalcontr;

import java.util.Date;
import legalentity.LegalEntity;

/**
 *
 * @author Ivan
 */
public class LegalContr {
    private int contrID;
    private LegalEntity legalEntity;
    private String contrDoc;
    private Date beginDate;

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
     * @return the companyID
     */
    public int getCompanyID() {
        return getLegalEntity().getCompanyID();
    }

    /**
     * @param companyID the companyID to set
     */
    public void setCompanyID(int companyID) {
        if (getLegalEntity() == null) {
            setLegalEntity(new LegalEntity());
        }
        getLegalEntity().setCompanyID(companyID);
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
     * @return the legalEntity
     */
    public LegalEntity getLegalEntity() {
        return legalEntity;
    }

    /**
     * @param legalEntity the legalEntity to set
     */
    public void setLegalEntity(LegalEntity legalEntity) {
        this.legalEntity = legalEntity;
    }
}
