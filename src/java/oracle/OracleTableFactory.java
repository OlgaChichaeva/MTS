/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oracle;

import factory.*;
import dao.*;
import java.util.Locale;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import static pack.LogManager.LOG;

/**
 *
 * @author Ольга
 */
public class OracleTableFactory extends TableFactory {

    DataSource sour;

    public OracleTableFactory() {
        Locale.setDefault(Locale.ENGLISH);
        try {
            InitialContext initContext;
            initContext = new InitialContext();
            sour = (DataSource) initContext.lookup("java:comp/env/jdbc/MTSDataSource");
        } catch (NamingException ex) {
            LOG.error("Ошибка чтения ресурса базы данных.", ex);
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

    @Override
    public ServiceInSimDAO makeServiceInSim() {
        return new ServiceInSimDaoImp(sour);
    }
}
