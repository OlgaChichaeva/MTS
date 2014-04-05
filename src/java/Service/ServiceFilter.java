/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

/**
 *
 * @author Ivan
 */
public class ServiceFilter {
    private String idService;
    private String typeService;
    private String nameService;
    private String cost;

    /**
     * @return the idService
     */
    public String getIdService() {
        return idService;
    }

    /**
     * @param idService the idService to set
     */
    public void setIdService(String idService) {
        this.idService = idService;
    }

    /**
     * @return the typeService
     */
    public String getTypeService() {
        return typeService;
    }

    /**
     * @param typeService the typeService to set
     */
    public void setTypeService(String typeService) {
        this.typeService = typeService;
    }

    /**
     * @return the nameService
     */
    public String getNameService() {
        return nameService;
    }

    /**
     * @param nameService the nameService to set
     */
    public void setNameService(String nameService) {
        this.nameService = nameService;
    }

    /**
     * @return the cost
     */
    public String getCost() {
        return cost;
    }

    /**
     * @param cost the cost to set
     */
    public void setCost(String cost) {
        this.cost = cost;
    }
}
