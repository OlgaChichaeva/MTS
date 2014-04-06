/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oracle;

import dao.ServiceDao;
import objects.Service;
import filters.ServiceFilter;
import objects.TypeService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import pack.Abstract;

/**
 *
 * @author Ольга
 */
class ServiceDaoImp extends Abstract implements ServiceDao {

    public ServiceDaoImp(DataSource sour) {
        super(sour);
    }

    @Override
    public List<Service> getAllServices() {
        try (Connection con = getConn()) {

            List<Service> services = new ArrayList<Service>();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select s.*,t.name_type, t.measure from Service s "
                    + " INNER JOIN   type_service t on s.ID_type = t.ID_type ");
            while (rs.next()) {
                TypeService type = makeTypeService(rs);
                Service service = makeService(rs, type);
                services.add(service);
            }

            return services;

        } catch (SQLException ex) {
            //System.out.println("DAO Exception");
            ex.printStackTrace();
        }
        return null;

    }

    @Override
    public Service getService(int idService) {
        try (Connection con = getConn()) {
            PreparedStatement ps = con.prepareStatement("select s.*,t.name_type, t.measure from Service s "
                    + " INNER JOIN   type_service t on s.ID_type = t.ID_type  where ID_Service=?");

            ps.setInt(1, idService);
            ResultSet rs = ps.executeQuery();

            rs.next();
            TypeService type = makeTypeService(rs);
            Service service = makeService(rs, type);

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

    @Override
    public List<Service> getFilteredServices(ServiceFilter service) {
        try (Connection con = getConn()) {

            List<Service> services = new ArrayList<>();
            PreparedStatement ps = con.prepareStatement("select s.*,t.name_type, t.measure from Service s "
                    + " INNER JOIN   type_service t on s.ID_type = t.ID_type "
                    + " WHERE lower(t.name_type) LIKE ?"
                    + " AND lower(s.name_service) LIKE ?"
                    + " AND s.cost LIKE ?");
            ps.setString(1, "%" + service.getTypeService().toLowerCase() + "%");
            ps.setString(2, "%" + service.getNameService().toLowerCase() + "%");
            ps.setString(3, "%" + service.getCost() + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TypeService type = makeTypeService(rs);
                Service newService = makeService(rs, type);
                services.add(newService);
            }

            return services;

        } catch (SQLException ex) {
            //System.out.println("DAO Exception");
            ex.printStackTrace();
        }
        return null;
    }
}
