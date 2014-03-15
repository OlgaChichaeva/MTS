/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiceInTariff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
public class ServiceInTariffDaoImp extends Abstract implements ServiceInTariffDao {

   public ServiceInTariffDaoImp(DataSource sour) {
    super(sour);
    }

    @Override
    public ServiceInTariff getIdTariff(int idTariff) {
        try (Connection con = getConn()) {
            PreparedStatement ps = con.prepareStatement("select * from service_in_tariff where ID_tariff=?");
            ps.setInt(1, idTariff);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int idService = rs.getInt("ID_service");
            ServiceInTariff sInT = new ServiceInTariff();
            sInT.setIdTariff(idTariff);
            sInT.setIdService(idService);
            return sInT;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceInTariffDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void update(ServiceInTariff sInT) {
       try( Connection con = getConn()) {
          
              PreparedStatement ps = con.prepareStatement("update service_in_tariff set ID_tariff=?,ID_service=? where idTariff = ? and Id_Service = ? ");
                  ps.setInt(1, sInT.getIdTariff());
                  ps.setInt(2, sInT.getIdService());
                  ps.executeUpdate();
       } catch (SQLException ex) {
           Logger.getLogger(ServiceInTariffDaoImp.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

    @Override
    public void delete(int idTariff) {
       try(Connection con = getConn()) {
           PreparedStatement ps = con.prepareStatement("delete from  service_in_tariff where ID_tariff = ?");
                 ps.setInt(1, idTariff);
                 ps.executeUpdate();
       } catch (SQLException ex) {
           Logger.getLogger(ServiceInTariffDaoImp.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

    @Override
    public void insert(ServiceInTariff sInT) {
       try (Connection con = getConn()){
           
            PreparedStatement ps = con.prepareStatement("INSERT INTO  service_in_tariff (ID_tariff,ID_service) VALUES (?,?)");
                ps.setInt(1, sInT.getIdTariff());
                ps.setInt(2, sInT.getIdService());
                ps.executeUpdate();
       } catch (SQLException ex) {
           Logger.getLogger(ServiceInTariffDaoImp.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

    @Override
    public List<ServiceInTariff> getAllType() {
       try (Connection con = getConn()){
            List<ServiceInTariff> services = new ArrayList<ServiceInTariff>();          
             Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from service_in_tariff"); 
            while(rs.next()){  
                int idTariff = rs.getInt("ID_tariff");
                int idService = rs.getInt("ID_service");               
               ServiceInTariff sInT = new ServiceInTariff();              
                sInT.setIdTariff(idTariff);
                sInT.setIdService(idService);
                services.add(sInT);
                }
                return services;
       } catch (SQLException ex) {
           Logger.getLogger(ServiceInTariffDaoImp.class.getName()).log(Level.SEVERE, null, ex);
       }
       return null;
    }
}
