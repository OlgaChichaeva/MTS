/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oracle;

import dao.UserDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import objects.Role;
import objects.User;
import oracle.conditions.StringConditionCreator;

/**
 *
 * @author Ivan
 */
public class OracleUserDAO extends OracleUniversalDAO<User> implements UserDAO {
    
    private static final String TABLE_NAME = "users";
    private static final String USER_ID_COL = "id_user";
    private static final String ROLE_ID_COL = "id_role";
    private static final String USER_NAME_COL = "user_name";
    private static final String USER_PASSWORD_COL = "user_password";
    private static final String ROLE_NAME_COL = "role_name";
    private static final String READ_ONLY_COL = "read_only";
    
    private static final String SELECT_FOR_ALL = "SELECT u.*, r." + ROLE_NAME_COL 
            + ", r." + READ_ONLY_COL + " FROM "
            + TABLE_NAME + " u INNER JOIN roles r "
            + "ON u." + ROLE_ID_COL + "=r." + ROLE_ID_COL;
    
    private final StringConditionCreator userNameConditionCreator;
    
    public OracleUserDAO(DataSource dataSource) {
        super(dataSource);
        userNameConditionCreator = new StringConditionCreator(SELECT_FOR_ALL + " WHERE " + USER_NAME_COL + " = ?");
    }

    @Override
    public boolean addUser(User user) {
        return addObject(user);
    }

    @Override
    public boolean deleteUser(int userID) {
        return deleteObjectByID(userID);
    }

    @Override
    public boolean updateUser(User user) {
        return updateObject(user);
    }

    @Override
    public List<User> getAllUsers() {
        return getAllObjects(TABLE_NAME);
    }

    @Override
    public User getUserByUserName(String userName) {
        userNameConditionCreator.setValue(userName);
        return getUniqueObject(userNameConditionCreator);
    }

    @Override
    protected String makeInsertStatement() {
        final String INSERT = "INSERT INTO " + TABLE_NAME
                + "(" + ROLE_ID_COL + "," + USER_NAME_COL + "," + USER_PASSWORD_COL + ")"
                + " VALUES(?,?,?)";
        return INSERT;
    }

    @Override
    protected String makeDeleteStatement() {
        final String DELETE = "DELETE FROM " + TABLE_NAME + " WHERE " + USER_ID_COL + " = ?";
        return DELETE;
    }

    @Override
    protected String makeUpdateStatement() {
        final String SEP = "=?,";
        final String UPDATE = "UPDATE " + TABLE_NAME + " SET "
                    + ROLE_ID_COL + SEP + USER_NAME_COL + SEP + USER_PASSWORD_COL + "=?"
                    + " WHERE " + USER_ID_COL + " = ?";
        return UPDATE;
    }

    @Override
    protected void prepareInsertStatement(PreparedStatement ps, User user) throws SQLException {
        ps.setInt(1, user.getIdRole());
        ps.setString(2, user.getUserName());
        ps.setString(3, user.getUserPassword());
    }

    @Override
    protected void prepareUpdateStatement(PreparedStatement ps, User user) throws SQLException {
        prepareInsertStatement(ps, user);
        ps.setInt(4, user.getIdUser());
    }

    @Override
    protected User makeObject(ResultSet rs) throws SQLException {
        Role role = makeRole(rs);
        User user = makeUser(rs, role);
        return user;
    }
}
