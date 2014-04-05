/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oracle;

import objects.Sim;
import objects.Tariff;
import objects.Client;
import objects.ClientContr;
import dao.ClientContrDAO;
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
    private static final String SELECT_FOR_ALL = "SELECT con.contr_id, con.contr_doc, con.begin_date,"
                + " cl.*, sim.*, tar.name_tariff, tar.description"
                + " FROM " + TABLE_NAME + " con"
                + " INNER JOIN client.cl on con.client_id=cl.client_id"
                + " INNER JOIN sim on con.sim_id=sim.sim_id"
                + " INNER JOIN tariff_list tar on sim.ID_tariff=tar.ID_tariff";
    
    private final IntegerConditionCreator contrIDConditionCreator;
    private final IntegerConditionCreator clientIDConditionCreator;
    private final IntegerConditionCreator simIDConditionCreator;

    public OracleClientContrDAO(DataSource dataSource) {
        super(dataSource);
        
        contrIDConditionCreator = new IntegerConditionCreator(SELECT_FOR_ALL + " WHERE " + CONTR_ID_COL + " = ?");
        clientIDConditionCreator = new IntegerConditionCreator(SELECT_FOR_ALL + " WHERE " + CLIENT_ID_COL + " = ?");
        simIDConditionCreator = new IntegerConditionCreator(SELECT_FOR_ALL + " WHERE " + SIM_ID_COL + " = ?");
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
        return getAllObjectsByCustomQuery(SELECT_FOR_ALL);
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
        Client client = makeClient(rs);
        Tariff tariff = makeTariff(rs);
        Sim sim = makeSim(rs, tariff);
        
        ClientContr newContr = makeClientContr(rs, client, sim);
        return newContr;
    }
}
