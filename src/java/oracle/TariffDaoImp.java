 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oracle;

import dao.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import objects.Tariff;
import dao.TariffDao;
import filters.TariffFilter;
import javax.sql.DataSource;
import pack.Abstract;
import static pack.LogManager.LOG;

/**
 *
 * @author Ольга
 */
class TariffDaoImp extends Abstract implements TariffDao {

    public TariffDaoImp(DataSource sour) {
        super(sour);

    }

    @Override
    public List<Tariff> getAllTariffList() {
        try (Connection con = getConn()) {

            List<Tariff> tariffs = new ArrayList<Tariff>();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from tariff_list");
            while (rs.next()) {
                Tariff tariff = makeTariff(rs);
                tariffs.add(tariff);
            }

            return tariffs;

        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public Tariff getTariffList(int idTariff) {
        try (Connection con = getConn()) {
            PreparedStatement ps = con.prepareStatement("select * from tariff_list where ID_tariff=?");
            ps.setInt(1, idTariff);
            ResultSet rs = ps.executeQuery();
            rs.next();
            Tariff tariff = makeTariff(rs);

            return tariff;
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public void updateTariffList(Tariff tariff) {
        try (Connection con = getConn()) {
            PreparedStatement ps = con.prepareStatement("update tariff_list set name_tariff=?,description = ? where ID_tariff = ? ");
            ps.setString(1, tariff.getNameTariff());
            ps.setString(2, tariff.getDescription());
            ps.setInt(3, tariff.getIdTariff());
            ps.executeUpdate();

        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public void deleteTariffList(int idTariff) {
        try (Connection con = getConn()) {
            PreparedStatement ps = con.prepareStatement("delete from tariff_list where ID_tariff = ?");
            ps.setInt(1, idTariff);
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public void addSTariffList(Tariff tariff) {
        try (Connection con = getConn()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO tariff_list (name_tariff,description) VALUES (?,?)");
            ps.setString(1, tariff.getNameTariff());
            ps.setNString(2, tariff.getDescription());
            if (LOG.isDebugEnabled()) {
                LOG.debug("Имя тарифа: " + tariff.getNameTariff() + ", описание: " + tariff.getDescription());
            }
            ps.executeUpdate();

        } catch (SQLException ex) {
            throw new DaoException(ex);
        }

    }

    @Override
    public List<Tariff> getFilteredTariffList(TariffFilter tariff) {
        try (Connection con = getConn()) {

            List<Tariff> tariffList = new ArrayList<>();
            PreparedStatement ps = con.prepareStatement("select * from tariff_list "
                    + " WHERE lower(name_tariff) LIKE ?"
                    + " AND lower(description) LIKE ?");
            ps.setString(1, "%" + tariff.getNameTariff().toLowerCase() + "%");
            ps.setString(2, "%" + tariff.getDescription().toLowerCase() + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Tariff newTariff = makeTariff(rs);
                tariffList.add(newTariff);
            }

            return tariffList;

        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
    }
}
