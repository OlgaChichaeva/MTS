/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oracle;

import Sim.Sim;
import Sim.SimDao;
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

    public SimDaoImp(DataSource sour){
   super(sour);
} 

    @Override
    public Sim getIdSim(int idSim) {
        try (Connection con = getConn()){
            PreparedStatement ps = con.prepareStatement("Select * from Sim where sim_id =?");
            ps.setInt(1, idSim);
            ResultSet rs = ps.executeQuery();
            rs.next();
            Sim sim = new Sim();
            sim.setSimId(idSim);
            sim.setTariffId(rs.getInt("ID_tariff "));
            sim.setAccount(rs.getInt("account"));
            return sim;
        } catch (SQLException ex) {
            Logger.getLogger(SimDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    @Override
    public void update(Sim sim) {
        try (Connection con = getConn()){
            PreparedStatement ps = con.prepareStatement("Update  Sim set(ID_tariff,account) =?,? where sim_id  = ?");
            ps.setInt(1, sim.getTariffId());
            ps.setInt(2, sim.getAccount());
            ResultSet rs = ps.executeQuery();
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SimDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int idSim) {
        try (Connection con = getConn()){
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
        try (Connection con = getConn()){
            PreparedStatement ps = con.prepareStatement("INSERT INto (ID_tariff,account) values (?,?)");
            ps.setInt(1, sim.getTariffId());
            ps.setInt(2, sim.getAccount());
            ResultSet rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(SimDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Sim> getAllType() {
        try (Connection con = getConn()){
           PreparedStatement ps = con.prepareStatement("Select * from sim");
                   ResultSet rs = ps.executeQuery();
              List<Sim> sims = new ArrayList<Sim>();
              while (rs.next()) {
                  Sim sim = new Sim();
                  sim.setSimId(rs.getInt("sim_id"));
                  sim.setTariffId(rs.getInt("ID_tariffe"));
                  sim.setAccount(rs.getInt("account"));
                  sims.add(sim);
      } } catch (SQLException ex) {
            Logger.getLogger(SimDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }}
