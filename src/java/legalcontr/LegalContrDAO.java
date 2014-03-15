/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package legalcontr;

import java.util.List;

/**
 *
 * @author Ivan
 */
public interface LegalContrDAO {
    boolean addLegalContr(LegalContr contr);
    boolean deleteLegalContr(int contrID);
    boolean updateLegalContr(LegalContr contr);
    List<LegalContr> getAllContracts();
    LegalContr getContrByID(int contrID);
    List<LegalContr> getContractsByCompanyID(int companyID);
}
