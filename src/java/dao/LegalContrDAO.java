/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import objects.LegalContr;
import filters.LegalContrFilter;

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
    List<LegalContr> getFilteredContracts(LegalContrFilter legalContr);
}