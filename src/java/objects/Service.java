/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package objects;

import objects.TypeService;

/**
 *
 * @author Ольга
 */
public class Service {

    private int idService;
    private TypeService typeService;
    private String nameService;
    private double cost;
    
  /* Service(int idService, int idType,String nameService,int cost){
      this.idService = idService;
      this.idType = idType;
      this.nameService = nameService;
      this.cost = cost;
   }*/
  
   public void setIdType(int idType)  {
       if (typeService == null) {
           typeService = new TypeService();
       }
       typeService.setIdType(idType);
   }
   
   public int getIdType() {
       return typeService.getIdType();
   }
    
   public int getIdService() {
      return idService;
   }

   public void setIdService(int idService) {
      this.idService = idService;
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

    /**
     * @return the typeService
     */
    public TypeService getTypeService() {
        return typeService;
    }

    /**
     * @param typeService the typeService to set
     */
    public void setTypeService(TypeService typeService) {
        this.typeService = typeService;
    }
}
