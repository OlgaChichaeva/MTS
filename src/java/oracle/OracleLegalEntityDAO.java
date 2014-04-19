/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oracle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import objects.LegalEntity;
import dao.LegalEntityDAO;
import objects.Role;
import objects.User;
import oracle.conditions.IntegerConditionCreator;
import oracle.conditions.StringConditionCreator;

/**
 *
 * @author Ivan
 */
class OracleLegalEntityDAO extends OracleUniversalDAO<LegalEntity> implements LegalEntityDAO {

    private static final String TABLE_NAME = "legal_entity";
    private static final String ID_COL = "company_id";
    private static final String NAME_COL = "name_company";
    private static final String ADDR_COL = "address";
    private static final String PHONE_COL = "telephone";
    private static final String EMAIL_COL = "e_mail";
    private static final String DETAIL_COL = "details";
    private static final String SELECT_FOR_ALL = "SELECT cl.*, u.id_role, u.user_name, "
            + " u.user_password, r.role_name, r.read_only FROM legal_entity ent"
            + " INNER JOIN users u "
            + " ON ent.id_user=u.id_user"
            + " INNER JOIN roles r "
            + " ON r.id_role=u.id_role";
    
    private final IntegerConditionCreator idConditionCreator;
    private final StringConditionCreator nameConditionCreator;
    
    public OracleLegalEntityDAO(DataSource dataSource) {
        super(dataSource);
        idConditionCreator = new IntegerConditionCreator(TABLE_NAME, ID_COL);
        nameConditionCreator = new StringConditionCreator(TABLE_NAME, ID_COL);
    }

    @Override
    public boolean addLegalEntity(LegalEntity entity) {
        return addObject(entity);
    }

    @Override
    public boolean deleteLegalEntity(int companyID) {
        return deleteObjectByID(companyID);
    }

    @Override
    public boolean updateLegalEntity(LegalEntity entity) {
        return updateObject(entity);
    }

    @Override
    public List<LegalEntity> getAllEntities() {
        return getAllObjects(TABLE_NAME);
    }

    @Override
    public LegalEntity getEntityByID(int companyID) {
        idConditionCreator.setValue(companyID);
        return getUniqueObject(idConditionCreator);
    }

    @Override
    public List<LegalEntity> getEntitiesByName(String nameCompany) {
        nameConditionCreator.setValue(nameCompany);
        return getObjectsWithConditions(nameConditionCreator);
    }

    @Override
    protected String makeInsertStatement() {
        final String INSERT = "INSERT INTO " + TABLE_NAME
                + "(" + NAME_COL + "," + ADDR_COL + "," + PHONE_COL + "," + EMAIL_COL + "," + DETAIL_COL + ")"
                + " VALUES(?,?,?,?,?)";
        return INSERT;
    }

    @Override
    protected String makeDeleteStatement() {
        final String DELETE = "DELETE FROM " + TABLE_NAME + " WHERE " + ID_COL + " = ?";
        return DELETE;
    }

    @Override
    protected String makeUpdateStatement() {
        final String SEP = "=?,";
        final String UPDATE = "UPDATE " + TABLE_NAME + " SET "
                + NAME_COL + SEP + ADDR_COL + SEP + PHONE_COL + SEP + EMAIL_COL + SEP + DETAIL_COL + "=?"
                + " WHERE " + ID_COL + " = ?";
        return UPDATE;
    }

    @Override
    protected void prepareInsertStatement(PreparedStatement ps, LegalEntity entity) throws SQLException {
        ps.setString(1, entity.getNameCompany());
        ps.setString(2, entity.getAddress());
        ps.setString(3, entity.getTelephone());
        ps.setString(4, entity.getEmail());
        ps.setString(5, entity.getDetails());
    }

    @Override
    protected void prepareUpdateStatement(PreparedStatement ps, LegalEntity entity) throws SQLException {
        prepareInsertStatement(ps, entity);
        ps.setInt(6, entity.getCompanyID());
    }

    @Override
    protected LegalEntity makeObject(ResultSet rs) throws SQLException {
        Role role = makeRole(rs);
        User user = makeUser(rs, role);
        LegalEntity newEntity = makeLegalEntity(rs, user);
        
        /*newEntity.setCompanyID(rs.getInt(ID_COL));
        newEntity.setNameCompany(rs.getString(NAME_COL));
        newEntity.setAddress(rs.getString(ADDR_COL));
        newEntity.setTelephone(rs.getString(PHONE_COL));
        newEntity.setEmail(rs.getString(EMAIL_COL));
        newEntity.setDetails(rs.getString(DETAIL_COL));*/
        return newEntity;
    }
}
