/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oracle;

import dao.ServiceInSimDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;
import objects.Service;
import objects.ServiceInSim;
import objects.Sim;
import objects.Tariff;
import objects.TypeService;
import oracle.conditions.IntegerConditionCreator;

/**
 *
 * @author Ольга
 */
class ServiceInSimDaoImp extends OracleUniversalDAO<ServiceInSim> implements ServiceInSimDAO {

    private static final String SELECT_FOR_ALL = "SELECT sim.*, tar.name_tariff, tar.description,"
            + " s.*, type.name_type, type.measure"
            + " FROM service_in_sim sis"
            + " INNER JOIN sim ON sis.sim_id=sim.sim_id"
            + " INNER JOIN tariff_list tar ON sim.ID_tariff=tar.ID_tariff"
            + " INNER JOIN service s ON s.ID_service=sis.ID_service"
            + " INNER JOIN type_service type ON type.ID_type=s.ID_type";
    
    private final IntegerConditionCreator simIDConditionCreator;

    public ServiceInSimDaoImp(DataSource sour) {
        super(sour);
        simIDConditionCreator = new IntegerConditionCreator(SELECT_FOR_ALL + " WHERE sis.sim_id = ?");
    }

    @Override
    public List<ServiceInSim> getIdSim(int idSim) {
        simIDConditionCreator.setValue(idSim);
        return getObjectsWithConditions(simIDConditionCreator);
    }

    @Override
    public boolean deleteBySimID(int idSim) {
        return deleteObjectByID(idSim);
    }

    @Override
    public boolean insert(ServiceInSim sInT) {
        return addObject(sInT);
    }

    @Override
    public List<ServiceInSim> getAll() {
        return getAllObjectsByCustomQuery(SELECT_FOR_ALL);
    }

    @Override
    public boolean deleteConcreteServiceInSim(ServiceInSim sInS) {
        try ( Connection con = getConn()) {
            PreparedStatement ps =
                con.prepareStatement("DELETE FROM service_in_sim WHERE sim_id = ? AND ID_service = ?");
            ps.setInt(1, sInS.getIdSim());
            ps.setInt(2, sInS.getIdService());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    @Override
    protected String makeInsertStatement() {
        final String INSERT = "INSERT INTO service_in_sim"
                + "(sim_id, ID_service)"
                + " VALUES(?,?)";
        return INSERT;
    }

    @Override
    protected String makeDeleteStatement() {
        final String DELETE = "DELETE FROM service_in_sim WHERE sim_id = ?";
        return DELETE;
    }

    @Override
    protected String makeUpdateStatement() {
        return null;
    }

    @Override
    protected void prepareInsertStatement(PreparedStatement ps, ServiceInSim object) throws SQLException {
        ps.setInt(1, object.getIdSim());
        ps.setInt(2, object.getIdService());
    }

    @Override
    protected void prepareUpdateStatement(PreparedStatement ps, ServiceInSim object) throws SQLException {
        // nothing
    }

    @Override
    protected ServiceInSim makeObject(ResultSet rs) throws SQLException {
        ServiceInSim sInS = new ServiceInSim();
        Tariff tariff = makeTariff(rs);
        Sim sim = makeSim(rs, tariff);
        TypeService type = makeTypeService(rs);
        Service service = makeService(rs, type);
        sInS.setService(service);
        sInS.setSim(sim);
        return sInS;
    }
}
