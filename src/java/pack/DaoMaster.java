/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pack;

import dao.*;
import factory.TableFactory;
import oracle.OracleTableFactory;

/**
 * Класс содержит статические методы для получения ДАО.
 * Информация о конкретной ДАО-фабрике содержится только здесь.
 * @author Ivan
 */
public class DaoMaster {
    
    private final static TableFactory factory = new OracleTableFactory();
    private final static ServiceDao serviceDao = factory.makeService();
    private final static ServiceInTariffDao serviceInTariffDao = factory.makeServiceInTariff();
    private final static SimDao simDao = factory.makeSim();
    private final static TariffDao tariffDao = factory.makeTariffList();
    private final static TrafficDao trafficDao = factory.makeTraffic();
    private final static TypeServiceDao typeServiceDao = factory.makeTypeService();
    private final static ClientDAO clientDao = factory.makeClient();
    private final static ClientContrDAO clientContrDao = factory.makeClientContr();
    private final static LegalContrDAO legalContrDao = factory.makeLegalContr();
    private final static LegalEntityDAO legalEntityDao = factory.makeLegalEntity();
    private final static PhoneNumberDAO phoneNumberDao = factory.makePhoneNamber();
    private final static SimContrDAO simContrDao = factory.makeSimContr();

    /**
     * @return the serviceDao
     */
    public static ServiceDao getServiceDao() {
        return serviceDao;
    }

    /**
     * @return the serviceInTariffDao
     */
    public static ServiceInTariffDao getServiceInTariffDao() {
        return serviceInTariffDao;
    }

    /**
     * @return the simDao
     */
    public static SimDao getSimDao() {
        return simDao;
    }

    /**
     * @return the tariffDao
     */
    public static TariffDao getTariffDao() {
        return tariffDao;
    }

    /**
     * @return the trafficDao
     */
    public static TrafficDao getTrafficDao() {
        return trafficDao;
    }

    /**
     * @return the typeServiceDao
     */
    public static TypeServiceDao getTypeServiceDao() {
        return typeServiceDao;
    }

    /**
     * @return the clientDao
     */
    public static ClientDAO getClientDao() {
        return clientDao;
    }

    /**
     * @return the clientContrDao
     */
    public static ClientContrDAO getClientContrDao() {
        return clientContrDao;
    }

    /**
     * @return the legalContrDao
     */
    public static LegalContrDAO getLegalContrDao() {
        return legalContrDao;
    }

    /**
     * @return the legalEntityDao
     */
    public static LegalEntityDAO getLegalEntityDao() {
        return legalEntityDao;
    }

    /**
     * @return the phoneNumberDao
     */
    public static PhoneNumberDAO getPhoneNumberDao() {
        return phoneNumberDao;
    }

    /**
     * @return the simContrDao
     */
    public static SimContrDAO getSimContrDao() {
        return simContrDao;
    }
    
    private DaoMaster() {}
    
 
}
