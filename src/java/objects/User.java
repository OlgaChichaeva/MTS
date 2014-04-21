/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import security.SecurityBean;

/**
 * По умолчанию создаётся с ID роли = NOT_LOGGED (не авторизованный)
 * @author Ivan
 */
public class User {
    private int idUser = -1;
    private Role role;
    private String userName;
    private String userPassword;
    private Client client;
    private LegalEntity legalEntity;

    public User() {
        setIdRole(SecurityBean.NOT_LOGGED);
        setReadOnly(true);
    }
    /**
     * @return the ideUser
     */
    public int getIdUser() {
        return idUser;
    }

    /**
     * @param ideUser the ideUser to set
     */
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    /**
     * @return the role
     */
    public Role getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the userPassword
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * @param userPassword the userPassword to set
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    
    public int getIdRole() {
        return role.getIdRole();
    }
    
    public void setIdRole(int idRole) {
        if(role == null) {
            role = new Role();
        }
        role.setIdRole(idRole);
    }
    
    public boolean getReadOnly() {
        return role.isReadOnly();
    }
    
    public void setReadOnly(boolean readOnly) {
        if(role == null) {
            role = new Role();
        }
        role.setReadOnly(readOnly);
    }

    /**
     * @return the client
     */
    public Client getClient() {
        return client;
    }

    /**
     * @param client the client to set
     */
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * @return the legalEntity
     */
    public LegalEntity getLegalEntity() {
        return legalEntity;
    }

    /**
     * @param legalEntity the legalEntity to set
     */
    public void setLegalEntity(LegalEntity legalEntity) {
        this.legalEntity = legalEntity;
    }
    
    public int getIdClient() {
        return client.getСlientID();
    }
    
    public void setIdClient(int idClient) {
        if(client == null) {
            client = new Client();
        }
        client.setСlientID(idClient);
    }
    
    public int getCompanyId() {
        return legalEntity.getCompanyID();
    }
    
    public void setCompanyId(int companyId) {
        if(legalEntity == null) {
            legalEntity = new LegalEntity();
        }
        legalEntity.setCompanyID(companyId);
    }
}
