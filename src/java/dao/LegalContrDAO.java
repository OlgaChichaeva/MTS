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
    boolean addLegalContr(LegalContr contr) throws DaoException;
    boolean deleteLegalContr(int contrID) throws DaoException;
    boolean updateLegalContr(LegalContr contr) throws DaoException;
    List<LegalContr> getAllContracts() throws DaoException;
    LegalContr getContrByID(int contrID) throws DaoException;
    List<LegalContr> getContractsByCompanyID(int companyID) throws DaoException;
    List<LegalContr> getFilteredContracts(LegalContrFilter legalContr) throws DaoException;
}
