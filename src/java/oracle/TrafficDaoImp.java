/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oracle;

import dao.DaoException;
import objects.Service;
import objects.Sim;
import objects.Tariff;
import objects.Traffic;
import dao.TrafficDao;
import objects.TypeService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import pack.Abstract;

/**
 *
 * @author Ольга
 */
class TrafficDaoImp extends Abstract implements TrafficDao {

    public TrafficDaoImp(DataSource sour) {
        super(sour);
    }

    @Override
    public List<Traffic> getBySimId(int simId) {
        try (Connection con = getConn()) {
            PreparedStatement ps = con.prepareStatement("SELECT tr.amount, tr.cost, tr.time,"
                    + " sim.*, serv.*, tar.name_tariff, tar.description,"
                    + " type.name_type, type.measure"
                    + " FROM traffic tr"
                    + " INNER JOIN sim on tr.sim_id=sim.sim_id"
                    + " INNER JOIN tariff_list tar on sim.id_tariff=tar.id_tariff"
                    + " INNER JOIN service serv on tr.ID_service=serv.ID_service"
                    + " INNER JOIN type_service type on serv.ID_type=type.ID_type"
                    + " where tr.sim_id =?");
            ps.setInt(1, simId);
            ResultSet rs = ps.executeQuery();
            List<Traffic> traffics = new ArrayList<Traffic>();
            while (rs.next()) {
                Traffic traffic = makeTraffic(rs);
                traffics.add(traffic);
            }
            return traffics;
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public void update(Traffic traffic) {
        try (Connection con = getConn()) {
            PreparedStatement ps = con.prepareStatement("Update  traffic set(sim_id,ID_service,amount,cost,time) =?,?,?,?,? where ID_service = ?");
            ps.setInt(1, traffic.getIdSim());
            ps.setInt(2, traffic.getIdService());
            ps.setDouble(3, traffic.getAmount());
            ps.setDouble(4, traffic.getCost());
            ps.setDate(5, new java.sql.Date(traffic.getDate().getTime()));
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public void delete(int idService) {
        try (Connection con = getConn()) {
            PreparedStatement ps = con.prepareStatement("Delete nameType where idService = ? ");

            ps.setInt(1, idService);
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public void insert(Traffic traffic) {
        try (Connection con = getConn()) {
            PreparedStatement ps = con.prepareStatement("INSERT INto (sim_id,ID_service,amount,cost,time) values (?,?,?,?,?)");
            ps.setInt(1, traffic.getIdSim());
            ps.setInt(2, traffic.getIdService());
            ps.setDouble(3, traffic.getAmount());
            ps.setDouble(4, traffic.getCost());
            ps.setTimestamp(5, new java.sql.Timestamp(traffic.getDate().getTime()));
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public List<Traffic> getByIDService(int idService) {
        try (Connection con = getConn()) {
            PreparedStatement ps = con.prepareStatement("SELECT tr.amount, tr.cost, tr.time,"
                    + " sim.*, serv.*, tar.name_tariff, tar.description,"
                    + " type.name_type, type.measure"
                    + " FROM traffic tr"
                    + " INNER JOIN sim on tr.sim_id=sim.sim_id"
                    + " INNER JOIN tariff_list tar on sim.id_tariff=tar.id_tariff"
                    + " INNER JOIN service serv on tr.ID_service=serv.ID_service"
                    + " INNER JOIN type_service type on serv.ID_type=type.ID_type"
                    + " where tr.ID_service =?");
            ps.setInt(1, idService);
            ResultSet rs = ps.executeQuery();
            List<Traffic> traffics = new ArrayList<Traffic>();
            while (rs.next()) {
                Traffic traffic = makeTraffic(rs);
                traffics.add(traffic);
            }
            return traffics;
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
    }

    private Traffic makeTraffic(ResultSet rs) throws SQLException {
        Tariff tariff = makeTariff(rs);
        Sim sim = makeSim(rs, tariff);
        TypeService type = makeTypeService(rs);
        Service service = makeService(rs, type);

        Traffic traffic = new Traffic();
        traffic.setService(service);
        traffic.setSim(sim);
        traffic.setAmount(rs.getDouble("amount"));
        traffic.setCost(rs.getDouble("cost"));
        traffic.setDate(rs.getTimestamp("time"));
        return traffic;
    }
}
