/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TypeService;

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
public class TypeServiceDaoImp  extends Abstract implements TypeServiceDao  {
   
public TypeServiceDaoImp(DataSource sour){
   super(sour);
} 
    @Override
    public TypeService getIdType(int idType) {
        try (Connection con = getConn()) {

            PreparedStatement ps = con.prepareStatement("Select * from type_service where ID_type =?");// Connection con = getCon();
           ps.setInt(1,idType );
            ResultSet rs = ps.executeQuery();
            
            rs.next();
            TypeService type = new TypeService();
            type.setIdType(idType);
            type.setNameType(rs.getString("name_type"));
            type.setMeasua(rs.getString("measure"));
            return type;
        } catch (SQLException ex) {
            Logger.getLogger(TypeServiceDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    @Override
    public void update(TypeService type) {
        try (Connection con = getConn()){
            PreparedStatement ps = con.prepareStatement("Update  type_service set(name_type,measure) =?,? where ID_type = ?");
              ps.setString(1, type.getNameType());
            ps.setString(2, type.getMeasua());   
            ResultSet rs = ps.executeQuery();
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TypeServiceDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int idType) {
        try (Connection con = getConn()){
            PreparedStatement ps = con.prepareStatement("Delete nameType where idTariff = ? ");
            
            ps.setInt(1,idType );
            ResultSet rs = ps.executeQuery();
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TypeServiceDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void insert(TypeService type) {
        try (Connection con = getConn()){
            PreparedStatement ps = con.prepareStatement("INSERT INto (name_type,measure) values (?,?)");
            ps.setString(1, type.getNameType());
            ps.setString(2, type.getMeasua());
            ResultSet rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(TypeServiceDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    @Override
    public List<TypeService> getAllType() {
        try (Connection con = getConn()) {
            PreparedStatement ps = con.prepareStatement("Select * from type_service");
            ResultSet rs = ps.executeQuery();
            List<TypeService> types = new ArrayList<TypeService>();
            while (rs.next()) {
                TypeService type = new TypeService();
                type.setIdType(rs.getInt("ID_type"));
                type.setNameType(rs.getString("name_type"));
                type.setMeasua(rs.getString("measure"));
                types.add(type);
            }

            return types;
        } catch (SQLException ex) {
            Logger.getLogger(TypeServiceDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
