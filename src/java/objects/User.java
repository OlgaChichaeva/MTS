/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

/**
 *
 * @author Ivan
 */
public class User {
    private int idUser;
    private Role role;
    private String userName;
    private String userPassword;

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
}
