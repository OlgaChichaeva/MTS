/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import objects.LegalEntity;

/**
 *
 * @author Ivan
 */
public interface LegalEntityDAO {
    boolean addLegalEntity(LegalEntity entity) throws DaoException;
    boolean deleteLegalEntity(int companyID) throws DaoException;
    boolean updateLegalEntity(LegalEntity entity) throws DaoException;
    List<LegalEntity> getAllEntities() throws DaoException;
    LegalEntity getEntityByID(int companyID) throws DaoException;
    List<LegalEntity> getEntitiesByName(String nameCompany) throws DaoException;
}
