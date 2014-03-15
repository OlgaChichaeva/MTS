/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oracle;

import clientcontr.ClientContr;
import clientcontr.ClientContrDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import oracle.conditions.IntegerConditionCreator;

/**
 *
 * @author Ivan
 */
class OracleClientContrDAO extends OracleUniversalDAO<ClientContr> implements ClientContrDAO {
    
    private static final String TABLE_NAME = "client_contr";
    private static final String CONTR_ID_COL = "contr_id";
    private static final String CLIENT_ID_COL = "client_id";
    private static final String SIM_ID_COL = "sim_id";
    private static final String DOC_COL = "contr_doc";
    private static final String DATE_COL = "begin_date";
    
    private final IntegerConditionCreator contrIDConditionCreator;
    private final IntegerConditionCreator clientIDConditionCreator;
    private final IntegerConditionCreator simIDConditionCreator;

    public OracleClientContrDAO(DataSource dataSource) {
        super(dataSource);
        contrIDConditionCreator = new IntegerConditionCreator(TABLE_NAME, CONTR_ID_COL);
        clientIDConditionCreator = new IntegerConditionCreator(TABLE_NAME, CLIENT_ID_COL);
        simIDConditionCreator = new IntegerConditionCreator(TABLE_NAME, SIM_ID_COL);
    }
    
    @Override
    public boolean addContr(ClientContr contr) {
        return addObject(contr);
    }

    @Override
    public boolean deleteContr(int contrID) {
        return deleteObjectByID(contrID);
    }

    @Override
    public boolean updateContr(ClientContr contr) {
        return updateObject(contr);
    }

    @Override
    public List<ClientContr> getAllContrs() {
        return getAllObjects(TABLE_NAME);
    }

    @Override
    public ClientContr getContrByID(int contrID) {
        contrIDConditionCreator.setValue(contrID);
        return getUniqueObject(contrIDConditionCreator);
    }

    @Override
    public ClientContr getContrBySimID(int simID) {
        clientIDConditionCreator.setValue(simID);
        return getUniqueObject(clientIDConditionCreator);
    }

    @Override
    public List<ClientContr> getContrsByClientID(int clientID) {
        simIDConditionCreator.setValue(clientID);
        return getObjectsWithConditions(simIDConditionCreator);
    }

    @Override
    protected String makeInsertStatement() {
        final String INSERT = "INSERT INTO " + TABLE_NAME
                    + "(" + CLIENT_ID_COL + "," + SIM_ID_COL + "," + DOC_COL + "," + DATE_COL + ")"
                    + " VALUES(?,?,?,?)";
        return INSERT;
    }

    @Override
    protected String makeDeleteStatement() {
        final String DELETE = "DELETE FROM " + TABLE_NAME + " WHERE " + CONTR_ID_COL + " = ?";
        return DELETE;
    }

    @Override
    protected String makeUpdateStatement() {
        final String SEP = "=?,";
        final String UPDATE = "UPDATE " + TABLE_NAME + " SET "
                    + CLIENT_ID_COL + SEP + SIM_ID_COL + SEP + DOC_COL + SEP + DATE_COL + "=?"
                    + " WHERE " + CONTR_ID_COL + " = ?";
        return UPDATE;
    }

    @Override
    protected void prepareInsertStatement(PreparedStatement ps, ClientContr contr) throws SQLException {
        ps.setInt(1, contr.getClientID());
        ps.setInt(2, contr.getSimID());
        ps.setString(3, contr.getContrDoc());
        ps.setDate(4, new java.sql.Date(contr.getBeginDate().getTime()));
    }

    @Override
    protected void prepareUpdateStatement(PreparedStatement ps, ClientContr contr) throws SQLException {
        prepareInsertStatement(ps, contr);
        ps.setInt(5, contr.getContrID());
    }

    @Override
    protected ClientContr makeObject(ResultSet rs) throws SQLException {
        ClientContr newContr = new ClientContr();
        newContr.setContrID(rs.getInt(CONTR_ID_COL));
        newContr.setClientID(rs.getInt(CLIENT_ID_COL));
        newContr.setSimID(rs.getInt(SIM_ID_COL));
        newContr.setContrDoc(rs.getString(DOC_COL));
        newContr.setBeginDate(rs.getDate(DATE_COL));
        return newContr;
    }
}
