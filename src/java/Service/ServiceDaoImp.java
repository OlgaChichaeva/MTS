/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Service.ServiceDao;
import Service.Service;
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
public class ServiceDaoImp extends Abstract implements ServiceDao {

    public ServiceDaoImp(DataSource sour) {
        super(sour);
    }

    @Override
    public List<Service> getAllServices() {
        try (Connection con = getConn()) {

            List<Service> services = new ArrayList<Service>();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from Service");
            while (rs.next()) {
                int IdType = rs.getInt("ID_type");
                String nameService = rs.getString("name_service");
                double cost = rs.getDouble("cost");
                int idService = rs.getInt("ID_Service");
                Service service = new Service();
                service.setCost(cost);
                service.setIdService(idService);
                service.setIdType(IdType);
                service.setNameService(nameService);
                services.add(service);
            }

            return services;

        } catch (SQLException ex) {
        }
        return null;

    }

    @Override
    public Service getService(int idService) {
        try (Connection con = getConn()) {
            PreparedStatement ps = con.prepareStatement("select * from Service where ID_Service=?");

            ps.setInt(1, idService);
            ResultSet rs = ps.executeQuery();

            rs.next();
            int IdType = rs.getInt("ID_type");
            String nameService = rs.getString("name_service");
            double cost = rs.getDouble("cost");

            Service service = new Service();

            service.setCost(cost);
            service.setIdService(idService);
            service.setIdType(IdType);
            service.setNameService(nameService);

            return service;
        } catch (SQLException ex) {
        }
        return null;

    }

    @Override
    public void updateService(Service service) {
        try (Connection con = getConn()) {
            PreparedStatement ps = con.prepareStatement("update Service set name_service=?,cost=?,ID_type = ?,Id_Service = ? where Id_Service = ? ");
            ps.setString(1, service.getNameService());
            ps.setDouble(2, service.getCost());
            ps.setInt(3, service.getIdType());
            ps.setInt(4, service.getIdService());
            ps.executeUpdate();

        } catch (SQLException ex) {
        }
    }

    @Override
    public void deleteService(int idService) {
        try (Connection con = getConn()) {
            PreparedStatement ps = con.prepareStatement("delete from Service where Id_Service = ?");
            ps.setInt(1, idService);
            ps.executeUpdate();
        } catch (SQLException ex) {
        }
    }

    @Override
    public void addService(Service service) {
        try (Connection con = getConn()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO Service (name_service, cost,ID_type) VALUES (?,?,?)");
            ps.setString(1, service.getNameService());
            ps.setDouble(2, service.getCost());
            ps.setInt(3, service.getIdType());
            ps.executeUpdate();

        } catch (SQLException ex) {
        }

    }
}
