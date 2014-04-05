/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oracle;

import objects.Sim;
import objects.Tariff;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import oracle.conditions.AbstractOneConditionCreator;
import oracle.conditions.IntegerConditionCreator;
import objects.PhoneNumber;
import dao.PhoneNumberDAO;

/**
 *
 * @author Ivan
 */
class OraclePhoneNumberDAO extends OracleUniversalDAO<PhoneNumber> implements PhoneNumberDAO {

    private static final String TABLE_NAME = "numbers";
    private static final String SIM_ID_COL = "sim_id";
    private static final String NUMBER_COL = "phone_number";
    private static final String SELECT_FOR_ALL = "SELECT num.phone_number, sim.*"
            + " FROM " + TABLE_NAME + " num"
            + " LEFT JOIN sim on num.sim_id=sim.sim_id"
            + " INNER JOIN tariff_list tar on sim.ID_tariff=tar.ID_tariff";
    private final NumberConditionCreator numberConditionCreator;
    private final IntegerConditionCreator simIDConditionCreator;

    public OraclePhoneNumberDAO(DataSource datasource) {
        super(datasource);
        numberConditionCreator = new NumberConditionCreator(SELECT_FOR_ALL + " WHERE " + NUMBER_COL + " = ?");
        simIDConditionCreator = new IntegerConditionCreator(SELECT_FOR_ALL + " WHERE " + SIM_ID_COL + " = ?");
    }

    @Override
    public boolean addNumber(long number) {
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setNumber(number);
        return addObject(phoneNumber);
    }

    @Override
    public boolean deleteNumber(long number) {
        return deleteObjectByID(number);
    }

    @Override
    public boolean updateNumber(PhoneNumber number) {
        return updateObject(number);
    }

    @Override
    public List<PhoneNumber> getAllNumbers() {
        return getAllObjects(TABLE_NAME);
    }

    @Override
    public PhoneNumber getNumber(long number) {
        numberConditionCreator.setValue(number);
        return getUniqueObject(numberConditionCreator);
    }

    @Override
    public List<PhoneNumber> getNumberBySimID(int simID) {
        simIDConditionCreator.setValue(simID);
        return getObjectsWithConditions(simIDConditionCreator);
    }

    @Override
    protected String makeInsertStatement() {
        final String INSERT = "INSERT INTO " + TABLE_NAME
                + "(" + NUMBER_COL + ") VALUES(?)";
        return INSERT;
    }

    @Override
    protected String makeDeleteStatement() {
        final String DELETE = "DELETE FROM " + TABLE_NAME + " WHERE " + NUMBER_COL + " = ?";
        return DELETE;
    }

    @Override
    protected String makeUpdateStatement() {
        final String UPDATE = "UPDATE " + TABLE_NAME + " SET "
                + SIM_ID_COL + "=?"
                + " WHERE " + NUMBER_COL + " = ?";
        return UPDATE;
    }

    @Override
    protected void prepareInsertStatement(PreparedStatement ps, PhoneNumber number) throws SQLException {
        ps.setLong(1, number.getNumber());
    }

    @Override
    protected void prepareUpdateStatement(PreparedStatement ps, PhoneNumber number) throws SQLException {
        prepareInsertStatement(ps, number);
        ps.setInt(2, number.getSimID());
    }

    @Override
    protected PhoneNumber makeObject(ResultSet rs) throws SQLException {
        Tariff tariff = makeTariff(rs);
        Sim sim = makeSim(rs, tariff);
        PhoneNumber phoneNumber = makePhoneNumber(rs, sim);
        return phoneNumber;
    }

    // --- Классы ConditionCreator ----------
    private static class NumberConditionCreator extends AbstractOneConditionCreator<Long> {

        public NumberConditionCreator(String tableName, String columnName) {
            super(tableName, columnName);
        }

        public NumberConditionCreator(String query) {
            super(query);
        }

        @Override
        public void prepareSelectStatement(PreparedStatement ps) throws SQLException {
            ps.setLong(1, value);
        }
    }
}
