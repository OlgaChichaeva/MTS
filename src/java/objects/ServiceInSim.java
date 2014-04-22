/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

/**
 *
 * @author Ivan
 */
public class ServiceInSim {
    private Sim sim;
    private Service service;

    /**
     * @return the idSim
     */
    public int getIdSim() {
        return sim.getSimId();
    }

    /**
     * @param idSim the idTariff to set
     */
    public void setIdSim(int idSim) {
        if (sim == null) {
            sim = new Sim();
        }
        sim.setSimId(idSim);
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
     * @return the sim
     */
    public Sim getSim() {
        return sim;
    }

    /**
     * @param sim the sim to set
     */
    public void setSim(Sim sim) {
        this.sim = sim;
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
