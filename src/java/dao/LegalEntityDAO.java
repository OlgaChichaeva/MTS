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
    boolean addLegalEntity(LegalEntity entity);
    boolean deleteLegalEntity(int companyID);
    boolean updateLegalEntity(LegalEntity entity);
    List<LegalEntity> getAllEntities();
    LegalEntity getEntityByID(int companyID);
    List<LegalEntity> getEntitiesByName(String nameCompany);
    LegalEntity getEntityByUserID(int userID);
}
