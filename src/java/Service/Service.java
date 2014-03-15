/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Service;

/**
 *
 * @author Ольга
 */
public class Service {

    private int idService;
    private int idType;
    private String nameService;
    private double cost;
    
  /* Service(int idService, int idType,String nameService,int cost){
      this.idService = idService;
      this.idType = idType;
      this.nameService = nameService;
      this.cost = cost;
   }*/
  
   public int getIdService() {
      return idService;
   }

   public void setIdService(int idService) {
      this.idService = idService;
   } 

   
    public int getIdType() {
        return idType;
    }


    public void setIdType(int idType) {
        this.idType = idType;
    }

    
    public String getNameService() {
        return nameService;
    }

    public void setNameService(String nameService) {
        this.nameService = nameService;
    }

  
    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
