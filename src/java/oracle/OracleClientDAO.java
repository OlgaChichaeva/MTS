/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oracle;

import client.Client;
import client.ClientDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import oracle.conditions.ConditionCreator;
import oracle.conditions.IntegerConditionCreator;
import oracle.conditions.StringConditionCreator;

/**
 *
 * @author Ivan
 */
class OracleClientDAO extends OracleUniversalDAO<Client> implements ClientDAO {
    
    private static final String TABLE_NAME = "client";
    private static final String ID_COL = "client_id";
    private static final String SERIES_COL = "passport_series";
    private static final String NUMBER_COL = "passport_number";
    private static final String FIRSTNAME_COL = "firstname";
    private static final String LASTNAME_COL = "lastname";
    private static final String MIDDLENAME_COL = "middlename";
    private static final String PHONE_COL = "telephone_number";
    
    private final IntegerConditionCreator clientIDConditionCreator;
    private final StringConditionCreator firstnameConditionCreator;
    private final StringConditionCreator lastnameConditionCreator;
    private final StringConditionCreator middlenameConditionCreator;
    private final PassportConditionCreator passportConditionCreator;
    private final FullnameConditionCreator fullnameConditionCreator;
    
    public OracleClientDAO(DataSource dataSource) {
        super(dataSource);
        clientIDConditionCreator = new IntegerConditionCreator(TABLE_NAME, ID_COL);
        firstnameConditionCreator = new StringConditionCreator(TABLE_NAME, FIRSTNAME_COL);
        lastnameConditionCreator = new StringConditionCreator(TABLE_NAME, LASTNAME_COL);
        middlenameConditionCreator = new StringConditionCreator(TABLE_NAME, MIDDLENAME_COL);
        passportConditionCreator = new PassportConditionCreator();
        fullnameConditionCreator = new FullnameConditionCreator();
    }
    
    @Override
    public boolean addClient(Client client) {
        return addObject(client);
    }

    @Override
    public boolean deleteClient(int clientID) {
        return deleteObjectByID(clientID);
    }

    @Override
    public boolean updateClient(Client client) {
        return updateObject(client);
    }

    @Override
    public List<Client> getAllClients() {
        return getAllObjects(TABLE_NAME);
    }

    @Override
    public Client getClientByID(int clientID) {
        clientIDConditionCreator.setValue(clientID);
        return getUniqueObject(clientIDConditionCreator);
    }

    @Override
    public Client getClientByPassport(int series, int number) {
        passportConditionCreator.setSeries(series);
        passportConditionCreator.setNumber(number);
        return getUniqueObject(passportConditionCreator);
    }

    @Override
    public List<Client> getClientsByFirstname(String firstname) {
        firstnameConditionCreator.setValue(firstname);
        return getObjectsWithConditions(clientIDConditionCreator);
    }

    @Override
    public List<Client> getClientsByLastname(String lastname) {
        lastnameConditionCreator.setValue(lastname);
        return getObjectsWithConditions(clientIDConditionCreator);
    }

    @Override
    public List<Client> getClientsByMiddlename(String middlename) {
        middlenameConditionCreator.setValue(middlename);
        return getObjectsWithConditions(clientIDConditionCreator);
    }

    @Override
    public List<Client> getClientsByFullname(String firstname, String lastname, String middlename) {
        fullnameConditionCreator.setFirstname(firstname);
        fullnameConditionCreator.setLastname(lastname);
        fullnameConditionCreator.setMiddlename(middlename);
        return getObjectsWithConditions(fullnameConditionCreator);
    }

    @Override
    protected String makeInsertStatement() {
        final String INSERT = "INSERT INTO " + TABLE_NAME
                    + "(" + SERIES_COL + "," + NUMBER_COL + "," + FIRSTNAME_COL + "," + LASTNAME_COL + "," + MIDDLENAME_COL + "," + PHONE_COL + ")"
                    + " VALUES(?,?,?,?,?,?)";
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
                    + SERIES_COL + SEP + NUMBER_COL + SEP + FIRSTNAME_COL + "=?" + LASTNAME_COL + SEP + MIDDLENAME_COL + SEP + PHONE_COL + "=?"
                    + " WHERE " + ID_COL + " = ?";
        return UPDATE;
    }

    @Override
    protected void prepareInsertStatement(PreparedStatement ps, Client client) throws SQLException {
        ps.setInt(1, client.getPassportSeries());
        ps.setInt(2, client.getPassportNumber());
        ps.setString(3, client.getFirstname());
        ps.setString(4, client.getLastname());
        ps.setString(5, client.getMiddlename());
        ps.setLong(6, client.getTelephoneNumber());
    }

    @Override
    protected void prepareUpdateStatement(PreparedStatement ps, Client client) throws SQLException {
        prepareInsertStatement(ps, client);
        ps.setInt(7, client.getСlientID());
    }

    @Override
    protected Client makeObject(ResultSet rs) throws SQLException {
        Client newClient = new Client();
        newClient.setСlientID(rs.getInt(ID_COL));
        newClient.setPassportSeries(rs.getInt(SERIES_COL));
        newClient.setPassportNumber(rs.getInt(NUMBER_COL));
        newClient.setFirstname(rs.getString(FIRSTNAME_COL));
        newClient.setLastname(rs.getString(LASTNAME_COL));
        newClient.setMiddlename(rs.getString(MIDDLENAME_COL));
        newClient.setTelephoneNumber(rs.getLong(PHONE_COL));
        return newClient;
    }
    
    // ------------------- Классы ConditionCreator --------------------
    private static class PassportConditionCreator extends ConditionCreator {
        
        private int series;
        private int number;
        

        @Override
        public String createSelect() {
            final String SELECT = "SELECT * FROM " + TABLE_NAME + " WHERE " + SERIES_COL + " = ? AND " + NUMBER_COL + " = ?";
            return SELECT;
        }

        @Override
        public void prepareSelectStatement(PreparedStatement ps) throws SQLException {
            ps.setInt(1, series);
            ps.setInt(2, number);
        }

        public void setSeries(int series) {
            this.series = series;
        }

        public void setNumber(int number) {
            this.number = number;
        }       
    }
    
    private static class FullnameConditionCreator extends ConditionCreator {
        
        private String firstname;
        private String lastname;
        private String middlename;

        @Override
        public String createSelect() {
            final String SELECT = "SELECT * FROM " + TABLE_NAME
                    + " WHERE " + FIRSTNAME_COL + " = ? AND "
                    + LASTNAME_COL + " = ? AND "
                    + MIDDLENAME_COL + " = ?";
            return SELECT;
        }

        @Override
        public void prepareSelectStatement(PreparedStatement ps) throws SQLException {
            ps.setString(1, firstname);
            ps.setString(2, lastname);
            ps.setString(3, middlename);
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public void setMiddlename(String middlename) {
            this.middlename = middlename;
        }
        
    }
}
