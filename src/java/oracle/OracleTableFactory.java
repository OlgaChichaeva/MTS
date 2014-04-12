/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oracle;

import factory.*;
import dao.*;
import java.sql.SQLException;
import java.util.Locale;
import oracle.jdbc.pool.OracleDataSource;

/**
 *
 * @author Ольга
 */
public class OracleTableFactory extends TableFactory {

    OracleDataSource sour;
    static String user = "MTS";
    static String pass = "MTS";
    static String url = "jdbc:oracle:thin:@localhost";

    public OracleTableFactory() {

        super();
         Locale.setDefault(Locale.ENGLISH);
        try {

            sour = new OracleDataSource();
            sour.setURL(url);

            sour.setUser(user);

            sour.setPassword(pass);

        } catch (SQLException ex) {
            //Logger.getLogger(OracleTableFactory.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public ServiceDao makeService() {
        return new ServiceDaoImp(sour);

    }

    @Override
    public ServiceInTariffDao makeServiceInTariff() {
        return new ServiceInTariffDaoImp(sour);
    }

    @Override
    public SimDao makeSim() {
        return new SimDaoImp(sour);
    }

    @Override
    public TariffDao makeTariffList() {
        return new TariffDaoImp(sour);
    }

    @Override
    public TrafficDao makeTraffic() {
        return new TrafficDaoImp(sour);
    }

    @Override
    public TypeServiceDao makeTypeService() {
        return new TypeServiceDaoImp(sour);
    }

    @Override
    public ClientDAO makeClient() {
        return new OracleClientDAO(sour);
    }

    @Override
    public ClientContrDAO makeClientContr() {
        return new OracleClientContrDAO(sour);
    }

    @Override
    public LegalContrDAO makeLegalContr() {
        return new OracleLegalContrDAO(sour);
    }

    @Override
    public LegalEntityDAO makeLegalEntity() {
        return new OracleLegalEntityDAO(sour);
    }

    @Override
    public PhoneNumberDAO makePhoneNamber() {
        return new OraclePhoneNumberDAO(sour);
    }

    @Override
    public SimContrDAO makeSimContr() {
        return new OracleSimContrDAO(sour);
    }

    @Override
    public UserDAO makeUser() {
        return new OracleUserDAO(sour);
    }
}
