/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import objects.Service;
import dao.ServiceDao;
import objects.ServiceInTariff;
import dao.ServiceInTariffDao;
import objects.Sim;
import dao.SimDao;
import objects.Tariff;
import dao.TariffDao;
import objects.Traffic;
import dao.TrafficDao;
import objects.TypeService;
import dao.TypeServiceDao;
import objects.Client;
import dao.ClientDAO;
import objects.ClientContr;
import dao.ClientContrDAO;
import java.util.Date;
import objects.LegalContr;
import dao.LegalContrDAO;
import objects.LegalEntity;
import dao.LegalEntityDAO;
import objects.PhoneNumber;
import dao.PhoneNumberDAO;
import objects.SimContr;
import dao.SimContrDAO;

/**
 *
 * @author Ольга
 */
public abstract class TableFactory {

    public TableFactory() {
        super();
    }

    public abstract ServiceDao makeService();

    public abstract ServiceInTariffDao makeServiceInTariff();

    public abstract SimDao makeSim();

    public abstract TariffDao makeTariffList();

    public abstract TrafficDao makeTraffic();

    public abstract TypeServiceDao makeTypeService();

    public abstract ClientDAO makeClient();

    public abstract  ClientContrDAO makeClientContr();
  
    public abstract LegalContrDAO makeLegalContr();

    public abstract LegalEntityDAO makeLegalEntity();

    public abstract PhoneNumberDAO makePhoneNamber();

    public abstract SimContrDAO makeSimContr();
}
