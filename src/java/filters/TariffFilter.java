/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

/**
 *
 * @author Ivan
 */
public class TariffFilter {
    private String idTariff;
    private String nameTariff;
    private String description; 

    /**
     * @return the idTariff
     */
    public String getIdTariff() {
        return idTariff;
    }

    /**
     * @param idTariff the idTariff to set
     */
    public void setIdTariff(String idTariff) {
        this.idTariff = idTariff;
    }

    /**
     * @return the nameTariff
     */
    public String getNameTariff() {
        return nameTariff;
    }

    /**
     * @param nameTariff the nameTariff to set
     */
    public void setNameTariff(String nameTariff) {
        this.nameTariff = nameTariff;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
