/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Traffic;

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
public class TrafficDaoImp extends Abstract implements TrafficDao {

    public TrafficDaoImp(DataSource sour){
   super(sour);
} 
    @Override
    public Traffic getIdService(int idService) {
    try (Connection con = getConn()){
        PreparedStatement ps = con.prepareStatement("Select * from traffic where ID_service =?");
       ps.setInt(1,idService );
        ResultSet rs = ps.executeQuery();
        
        rs.next();
        Traffic traffic = new Traffic();
        traffic.setIdService(idService);
        traffic.setIdSim(rs.getInt("sim_id"));
        traffic.setAmount(rs.getDouble("amount"));
        traffic.setCost(rs.getDouble("cost"));
        traffic.setDate(rs.getString("time"));
        return traffic;
    } catch (SQLException ex) {
        Logger.getLogger(TrafficDaoImp.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
    }

    @Override
    public void update(Traffic traffic) {
    try (Connection con = getConn()) {
        PreparedStatement ps = con.prepareStatement("Update  traffic set(sim_id,ID_service,amount,cost,time) =?,?,?,?,? where ID_service = ?");
               ps.setInt(1, traffic.getIdSim());
             ps.setInt(2, traffic.getIdService());  
             ps.setDouble(3, traffic.getAmount());
             ps.setDouble(4, traffic.getCost());
             ps.setString(5, traffic.getDate());
             ResultSet rs = ps.executeQuery();
             ps.executeUpdate();
    } catch (SQLException ex) {
        Logger.getLogger(TrafficDaoImp.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    @Override
    public void delete(int idService) {
    try (Connection con = getConn()) {
        PreparedStatement ps = con.prepareStatement("Delete nameType where idService = ? ");
              
              ps.setInt(1,idService );
              ResultSet rs = ps.executeQuery();
              ps.executeUpdate();
    } catch (SQLException ex) {
        Logger.getLogger(TrafficDaoImp.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    @Override
    public void insert(Traffic traffic) {
    try (Connection con = getConn()){
        PreparedStatement ps = con.prepareStatement("INSERT INto (sim_id,ID_service,amount,cost,time) values (?,?,?,?,?)");
            ps.setInt(1, traffic.getIdSim());
            ps.setInt(2, traffic.getIdService());
            ps.setDouble(3, traffic.getAmount());
            ps.setDouble(4, traffic.getCost());
            ps.setString(5, traffic.getDate());
            ResultSet rs = ps.executeQuery();
    } catch (SQLException ex) {
        Logger.getLogger(TrafficDaoImp.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    @Override
    public List<Traffic> getAllType() {
    try (Connection con = getConn()) {
        PreparedStatement ps = con.prepareStatement("Select * from traffic where ID_service =?");
           ResultSet rs = ps.executeQuery();
           List<Traffic> traffics = new ArrayList<Traffic>();
           while(rs.next());
           {
           Traffic traffic = new Traffic();
           traffic.setIdService(rs.getInt("ID_service"));
           traffic.setIdSim(rs.getInt("sim_id"));
           traffic.setAmount(rs.getDouble("amount"));
           traffic.setCost(rs.getDouble("cost"));
           traffic.setDate(rs.getString("time"));
          traffics.add(traffic);
           }
           return traffics;
    } catch (SQLException ex) {
        Logger.getLogger(TrafficDaoImp.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
    }
    
}
