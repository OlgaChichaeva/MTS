 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import objects.Service;
import objects.Tariff;
import dao.TariffDao;
import javax.sql.DataSource;
import pack.Abstract;


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
                int IdTariff = rs.getInt("ID_tariff");
                String nameTariff = rs.getString("name_tariff");
                String description = rs.getString("description");
                Tariff tariff= new Tariff();
                tariff.setIdTariff(IdTariff);
                tariff.setNameTariff(nameTariff);
                tariff.setDescription(description);                
                tariffs.add(tariff);
            }

            return tariffs;

        } catch (SQLException ex) {
            ex.printStackTrace();
             return null;
        }
    }

    @Override
    public Tariff getTariffList(int idTariff) {
        try (Connection con = getConn()){
            PreparedStatement ps = con.prepareStatement("select * from tariff_list where ID_tariff=?");
                        ps.setInt(1, idTariff);
                        ResultSet rs = ps.executeQuery("select * from tariff_list");
                        rs.next();
                            int IdTariff = rs.getInt(" ID_tariff");
                            String nameTariff = rs.getString("name_tariff");
                            String description = rs.getString("description");
                            
                            Tariff tariff= new Tariff();
                            tariff.setIdTariff(IdTariff);
                            tariff.setNameTariff(nameTariff);
                            tariff.setDescription(description);                                  
           

                        return  tariff;
        } catch (SQLException ex) {
            Logger.getLogger(TariffDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void updateTariffList(Tariff tariff) {
    try (Connection con = getConn()) {
        PreparedStatement ps = con.prepareStatement("update tariff_list set ID_tariff=?,name_tariff=?,description = ? where ID_tariff = ? "); 
        ps.setInt(1,tariff.getIdTariff() );
            ps.setString(2,tariff.getNameTariff());
            ps.setString(3,tariff.getDescription());
            ps.executeUpdate();

        } catch (SQLException ex) {
        }
    }

    @Override
    public void deleteTariffList(int idTariff) {
        try (Connection con = getConn()) {
           PreparedStatement ps = con.prepareStatement("delete from tariff_list where ID_tariff = ?");
            ps.setInt(1, idTariff);
            ps.executeUpdate();
        } catch (SQLException ex) {
        }
    }

    @Override
    public void addSTariffList(Tariff tariff) {
         try (Connection con = getConn()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO tariff_list (name_tariff,description ) VALUES (?,?)");
             ps.setString(1, tariff.getNameTariff());
            ps.setString(2,tariff.getDescription());
                    ps.executeUpdate();
            
        } catch (SQLException ex) {
        }

    }   
}

