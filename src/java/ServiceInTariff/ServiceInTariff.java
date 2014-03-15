/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiceInTariff;

/**
 *
 * @author Ольга
 */
public class ServiceInTariff {
  private int idTariff;    //ID_tariff;
   private int idService;  // ID_service

    /**
     * @return the idTariff
     */
    public int getIdTariff() {
        return idTariff;
    }

    /**
     * @param idTariff the idTariff to set
     */
    public void setIdTariff(int idTariff) {
        this.idTariff = idTariff;
    }

    /**
     * @return the idService
     */
    public int getIdService() {
        return idService;
    }

    /**
     * @param idService the idService to set
     */
    public void setIdService(int idService) {
        this.idService = idService;
    }
}
