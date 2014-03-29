/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oracle;

import Service.Service;
import ServiceInTariff.ServiceInTariff;
import ServiceInTariff.ServiceInTariffDao;
import Tariff.Tariff;
import TypeService.TypeService;
import factory.TableFactory;
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
class ServiceInTariffDaoImp extends Abstract implements ServiceInTariffDao {

    public ServiceInTariffDaoImp(DataSource sour) {
        super(sour);
    }

    @Override
    public List<ServiceInTariff> getIdTariff(int idTariff) {
        try (Connection con = getConn()) {
            PreparedStatement ps = con.prepareStatement("select s.*, tar.*, type.name_type, type.measure "
                    + "from service_in_tariff sInt "
                    + "INNER JOIN service s on s.ID_service=sInT.ID_service "
                    + "INNER JOIN tariff_list tar on tar.ID_tariff=sInT.ID_tariff "
                    + "INNER JOIN type_service type on type.ID_type=s.ID_type "
                    + "where ID_tariff=?");
            ps.setInt(1, idTariff);
            ResultSet rs = ps.executeQuery();
            List<ServiceInTariff> list = new ArrayList<>();
            while (rs.next()) {
                TypeService type = makeTypeService(rs);
                Service service = makeService(rs, type);
                Tariff tariff = makeTariff(rs);


                ServiceInTariff sInT = new ServiceInTariff();
                sInT.setTariff(tariff);
                sInT.setService(service);
                list.add(sInT);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceInTariffDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void update(ServiceInTariff sInT) {
        try (Connection con = getConn()) {

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
        try (Connection con = getConn()) {
            PreparedStatement ps = con.prepareStatement("delete from  service_in_tariff where ID_tariff = ?");
            ps.setInt(1, idTariff);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceInTariffDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void insert(ServiceInTariff sInT) {
        try (Connection con = getConn()) {

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
        try (Connection con = getConn()) {
            List<ServiceInTariff> services = new ArrayList<ServiceInTariff>();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select s.*, tar.*, type.name_type, type.measure "
                    + "from service_in_tariff sInt "
                    + "INNER JOIN service s on s.ID_service=sInT.ID_service "
                    + "INNER JOIN tariff_list tar on tar.ID_tariff=sInT.ID_tariff "
                    + "INNER JOIN type_service type on type.ID_type=s.ID_type ");
            while (rs.next()) {
                TypeService type = makeTypeService(rs);
                Service service = makeService(rs, type);
                Tariff tariff = makeTariff(rs);


                ServiceInTariff sInT = new ServiceInTariff();
                sInT.setTariff(tariff);
                sInT.setService(service);
                services.add(sInT);
            }
            return services;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceInTariffDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
