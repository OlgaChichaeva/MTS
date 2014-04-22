/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import oracle.conditions.ConditionCreator;
import oracle.conditions.IntegerConditionCreator;
import objects.SimContr;
import dao.SimContrDAO;
import objects.LegalContr;
import objects.LegalEntity;
import objects.Sim;
import objects.Tariff;

/**
 *
 * @author Ivan
 */
class OracleSimContrDAO extends OracleUniversalDAO<SimContr> implements SimContrDAO {

    private static final String TABLE_NAME = "sim_contr";
    private static final String SIM_ID_COL = "sim_id";
    private static final String CONTR_ID_COL = "contr_id";
    private static final String SELECT_FOR_ALL = "SELECT sim.*, tar.name_tariff, tar.description,"
            + " con.contr_id, con.contr_doc, con.begin_date, ent.*"
            + " FROM sim_contr sc"
            + " INNER JOIN sim ON sc.sim_id=sim.sim_id"
            + " INNER JOIN tariff_list tar ON sim.ID_tariff=tar.ID_tariff"
            + " INNER JOIN legal_contr con ON sc.contr_id=con.contr_id"
            + " INNER JOIN legal_entity ent ON con.company_id=ent.company_id";
    
    private final IntegerConditionCreator simIDConditionCreator;
    private final IntegerConditionCreator contrIDConditionCreator;
    private final SimContrConditionCreator simContrConditionCreator;
    private String colToDelete; // Для методов Delete..By..ID
    

    public OracleSimContrDAO(DataSource dataSource) {
        super(dataSource);
        simIDConditionCreator = new IntegerConditionCreator(SELECT_FOR_ALL + " WHERE " + SIM_ID_COL + " = ?");
        contrIDConditionCreator = new IntegerConditionCreator(SELECT_FOR_ALL + " WHERE " + CONTR_ID_COL + " = ?");
        simContrConditionCreator = new SimContrConditionCreator();
    }

    @Override
    public boolean addSimContr(SimContr simContr) {
        return addObject(simContr);
    }

    @Override
    public boolean deleteSimContrBySimID(int simID) {
        setColToDelete(SIM_ID_COL);
        return deleteObjectByID(simID);
    }

    @Override
    public boolean deleteSimContrsByContrID(int contrID) {
        setColToDelete(CONTR_ID_COL);
        return deleteObjectByID(contrID);

    }

    @Override
    public boolean deleteSimContr(SimContr simContr) {
        try ( Connection con = getConn()) {
            PreparedStatement ps =
                con.prepareStatement("DELETE FROM " + TABLE_NAME
                + " WHERE " + SIM_ID_COL + "=? AND " + CONTR_ID_COL + "=?");
            ps.setInt(1, simContr.getSimID());
            ps.setInt(2, simContr.getContrID());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    @Override
    public List<SimContr> getAllSimContrs() {
        return getAllObjectsByCustomQuery(SELECT_FOR_ALL);
    }

    @Override
    public List<SimContr> getSimContrsBySimID(int simID) {
        simIDConditionCreator.setValue(simID);
        return getObjectsWithConditions(simIDConditionCreator);
    }

    @Override
    public List<SimContr> getSimContrsByContrID(int contrID) {
        contrIDConditionCreator.setValue(contrID);
        return getObjectsWithConditions(contrIDConditionCreator);
    }

    @Override
    public SimContr getConcreteSimContr(SimContr simContr) {
        simContrConditionCreator.setSimContr(simContr);
        return getUniqueObject(simContrConditionCreator);
    }

    @Override
    protected String makeInsertStatement() {
        final String INSERT = "INSERT INTO " + TABLE_NAME
                + "(" + SIM_ID_COL + "," + CONTR_ID_COL + ")"
                + " VALUES(?,?)";
        return INSERT;
    }

    /**
     * Только для методов deleteSimContrBySimID и deleteSimContrByContrID!
     */
    @Override
    protected String makeDeleteStatement() {
        final String DELETE = "DELETE FROM " + TABLE_NAME + " WHERE " + getColToDelete() + " = ?";
        return DELETE;
    }

    @Override
    protected String makeUpdateStatement() {
        return null;
    }

    @Override
    protected void prepareInsertStatement(PreparedStatement ps, SimContr simContr) throws SQLException {
        ps.setInt(1, simContr.getSimID());
        ps.setInt(2, simContr.getContrID());
    }

    @Override
    protected void prepareUpdateStatement(PreparedStatement ps, SimContr object) throws SQLException {
    }

    @Override
    protected SimContr makeObject(ResultSet rs) throws SQLException {
        SimContr newSimContr = new SimContr();
        Tariff tariff = makeTariff(rs);
        Sim sim = makeSim(rs, tariff);
        LegalEntity entity = makeLegalEntity(rs);
        LegalContr contr = makeLegalContr(rs, entity);
        newSimContr.setSim(sim);
        newSimContr.setLegalContr(contr);
        return newSimContr;
    }

    private void setColToDelete(String colName) {
        colToDelete = colName;
    }

    private String getColToDelete() {
        return colToDelete;
    }

    // Классы ConditionCreator ------------
    private static class SimContrConditionCreator extends ConditionCreator {

        private SimContr simContr;

        @Override
        public String createSelect() {
            final String SELECT = SELECT_FOR_ALL + " WHERE "
                    + SIM_ID_COL + "=? AND " + CONTR_ID_COL + "=?";
            return SELECT;
        }

        @Override
        public void prepareSelectStatement(PreparedStatement ps) throws SQLException {
            ps.setInt(1, simContr.getSimID());
            ps.setInt(2, simContr.getContrID());
        }

        public void setSimContr(SimContr simContr) {
            this.simContr = simContr;
        }
    }
}
