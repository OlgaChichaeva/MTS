/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pack;

import objects.Service;
import objects.Sim;
import objects.Tariff;
import objects.TypeService;
import objects.Client;
import objects.ClientContr;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import objects.LegalContr;
import objects.LegalEntity;
import objects.PhoneNumber;

/**
 *
 * @author Ольга
 */
public abstract class Abstract {

    private DataSource sour;

    public Abstract(DataSource sour) {
        this.sour = sour;
    }

    protected Connection getConn() throws SQLException {
        return sour.getConnection();
    }

    protected Service makeService(ResultSet rs, TypeService typeService) throws SQLException {
        Service service = new Service();
        service.setCost(rs.getDouble("cost"));
        service.setIdService(rs.getInt("ID_Service"));
        service.setNameService(rs.getString("name_service"));
        service.setTypeService(typeService);
        return service;
    }

    protected Sim makeSim(ResultSet rs, Tariff tariff) throws SQLException {
        Sim sim = new Sim();
        sim.setSimId(rs.getInt("sim_id"));
        sim.setTariff(tariff);
        sim.setAccount(rs.getDouble("account"));
        return sim;
    }

    protected Tariff makeTariff(ResultSet rs) throws SQLException {
        Tariff tariff = new Tariff();
        tariff.setIdTariff(rs.getInt(" ID_tariff"));
        tariff.setNameTariff(rs.getString("name_tariff"));
        tariff.setDescription(rs.getString("description"));
        return tariff;
    }

    protected TypeService makeTypeService(ResultSet rs) throws SQLException {
        TypeService type = new TypeService();
        type.setIdType(rs.getInt("ID_type"));
        type.setNameType(rs.getString("name_type"));
        type.setMeasure(rs.getString("measure"));
        return type;
    }

    protected Client makeClient(ResultSet rs) throws SQLException {
        Client newClient = new Client();
        newClient.setСlientID(rs.getInt("client_id"));
        newClient.setPassportSeries(rs.getInt("passport_series"));
        newClient.setPassportNumber(rs.getInt("passport_number"));
        newClient.setFirstname(rs.getString("firstname"));
        newClient.setLastname(rs.getString("lastname"));
        newClient.setMiddlename(rs.getString("middlename"));
        newClient.setTelephoneNumber(rs.getLong("telephone_number"));
        return newClient;
    }

    protected ClientContr makeClientContr(ResultSet rs, Client client, Sim sim) throws SQLException {
        ClientContr newContr = new ClientContr();
        newContr.setContrID(rs.getInt("contr_id"));
        newContr.setClient(client);
        newContr.setSim(sim);
        newContr.setContrDoc(rs.getString("contr_doc"));
        newContr.setBeginDate(rs.getDate("begin_date"));
        return newContr;
    }

    protected LegalContr makeLegalContr(ResultSet rs, LegalEntity legalEntity) throws SQLException {
        LegalContr newContr = new LegalContr();
        newContr.setLegalEntity(legalEntity);
        newContr.setContrID(rs.getInt("contr_id"));
        newContr.setContrDoc(rs.getString("contr_doc"));
        newContr.setBeginDate(rs.getDate("begin_date"));
        return newContr;
    }

    protected LegalEntity makeLegalEntity(ResultSet rs) throws SQLException {
        LegalEntity newEntity = new LegalEntity();
        newEntity.setCompanyID(rs.getInt("company_id"));
        newEntity.setNameCompany(rs.getString("name_company"));
        newEntity.setAddress(rs.getString("address"));
        newEntity.setTelephone(rs.getString("telephone"));
        newEntity.setEmail(rs.getString("e_mail"));
        newEntity.setDetails(rs.getString("details"));
        return newEntity;
    }

    protected PhoneNumber makePhoneNumber(ResultSet rs, Sim sim) throws SQLException {
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setSim(sim);
        phoneNumber.setNumber(rs.getLong("phone_number"));
        return phoneNumber;
    }
}
