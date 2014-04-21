/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

/**
Create table tariff_list(
  ID_tariff number CONSTRAINT tariff_list_pk_tariff_id   PRIMARY KEY,
  name_tariff varchar(255),
  description varchar(255)
);

 *
 * @author Ольга
 */
public class Tariff {
   
    private int idTariff;
    private String nameTariff;
    private String description;

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
