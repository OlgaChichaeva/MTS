/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oracle;

import objects.Sim;
import dao.SimDao;
import objects.Tariff;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import pack.Abstract;

/**
 *
 * @author Ольга
 */
class SimDaoImp extends Abstract implements SimDao {

    public SimDaoImp(DataSource sour) {
        super(sour);
    }

    @Override
    public Sim getIdSim(int idSim) {
        try (Connection con = getConn()) {
            PreparedStatement ps = con.prepareStatement("Select s.*, t.name_tariff, t.description from Sim s "
                    + "INNER JOIN tariff_list t"
                    + " on s.ID_tariff=t.ID_tariff where sim_id =?");
            ps.setInt(1, idSim);
            ResultSet rs = ps.executeQuery();
            rs.next();
            Tariff tariff = makeTariff(rs);
            Sim sim = makeSim(rs, tariff);
            return sim;
        } catch (SQLException ex) {
            Logger.getLogger(SimDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    @Override
    public void update(Sim sim) {
        try (Connection con = getConn()) {
            PreparedStatement ps = con.prepareStatement("Update  Sim set(ID_tariff,account) =?,? where sim_id  = ?");
            ps.setInt(1, sim.getTariffId());
            ps.setDouble(2, sim.getAccount());
            ResultSet rs = ps.executeQuery();
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SimDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int idSim) {
        try (Connection con = getConn()) {
            PreparedStatement ps = con.prepareStatement("Delete ID_tariff where sim_id = ? ");
            ps.setInt(1, idSim);
            ResultSet rs = ps.executeQuery();
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SimDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void insert(Sim sim) {
        try (Connection con = getConn()) {
            PreparedStatement ps = con.prepareStatement("INSERT INto (ID_tariff,account) values (?,?)");
            ps.setInt(1, sim.getTariffId());
            ps.setDouble(2, sim.getAccount());
            ResultSet rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(SimDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Sim> getAllSim() {
        try (Connection con = getConn()) {
            PreparedStatement ps = con.prepareStatement("Select s.*, t.name_tariff, t.description from Sim s "
                    + "INNER JOIN tariff_list t"
                    + " on s.ID_tariff=t.ID_tariff");
            ResultSet rs = ps.executeQuery();
            List<Sim> sims = new ArrayList<>();
            while (rs.next()) {
                Tariff tariff = makeTariff(rs);
                Sim sim = makeSim(rs, tariff);
                sims.add(sim);
            }
            return sims;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        
    }

    @Override
    public List<Sim> getSimListByClientID(int clientID) {
        try (Connection con = getConn()) {
            PreparedStatement ps = con.prepareStatement("Select s.*, t.name_tariff, t.description "
                    + " FROM client_contr cc, Sim s"
                    + " INNER JOIN tariff_list t"
                    + " on s.ID_tariff=t.ID_tariff"
                    + " WHERE s.sim_id=cc.sim_id AND cc.client_id=?");
            ps.setInt(1, clientID);
            ResultSet rs = ps.executeQuery();
            List<Sim> sims = new ArrayList<>();
            while (rs.next()) {
                Tariff tariff = makeTariff(rs);
                Sim sim = makeSim(rs, tariff);
                sims.add(sim);
            }
            return sims;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        
    }
}
