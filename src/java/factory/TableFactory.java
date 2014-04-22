/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import dao.*;

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
    
    public abstract UserDAO makeUser();
    
    public abstract ServiceInSimDAO makeServiceInSim();
}
