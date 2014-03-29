/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import Service.Service;
import Service.ServiceDao;
import ServiceInTariff.ServiceInTariff;
import ServiceInTariff.ServiceInTariffDao;
import Sim.Sim;
import Sim.SimDao;
import Tariff.Tariff;
import Tariff.TariffDao;
import Traffic.Traffic;
import Traffic.TrafficDao;
import TypeService.TypeService;
import TypeService.TypeServiceDao;
import client.Client;
import client.ClientDAO;
import clientcontr.ClientContr;
import clientcontr.ClientContrDAO;
import java.util.Date;
import legalcontr.LegalContr;
import legalcontr.LegalContrDAO;
import legalentity.LegalEntity;
import legalentity.LegalEntityDAO;
import phonenumber.PhoneNumber;
import phonenumber.PhoneNumberDAO;
import simcontr.SimContr;
import simcontr.SimContrDAO;

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
