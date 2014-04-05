/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import objects.Service;
import objects.Tariff;

/**
 *
 * @author Ольга
 */
public class ServiceInTariff {
    private Tariff tariff;
    private Service service;

    /**
     * @return the idTariff
     */
    public int getIdTariff() {
        return getTariff().getIdTariff();
    }

    /**
     * @param idTariff the idTariff to set
     */
    public void setIdTariff(int idTariff) {
        if (getTariff() == null) {
            setTariff(new Tariff());
        }
        getTariff().setIdTariff(idTariff);
    }

    /**
     * @return the idService
     */
    public int getIdService() {
        return getService().getIdService();
    }

    /**
     * @param idService the idService to set
     */
    public void setIdService(int idService) {
        if (getService() == null) {
            setService(new Service());
        }
        getService().setIdService(idService);
    }

    /**
     * @return the tariff
     */
    public Tariff getTariff() {
        return tariff;
    }

    /**
     * @param tariff the tariff to set
     */
    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    /**
     * @return the service
     */
    public Service getService() {
        return service;
    }

    /**
     * @param service the service to set
     */
    public void setService(Service service) {
        this.service = service;
    }
}
