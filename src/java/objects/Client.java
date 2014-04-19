/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

/**
 *
 * @author Ivan
 */
public class Client {
    private int сlientID;
    private int passportSeries;
    private int passportNumber;
    private String firstname;
    private String lastname;
    private String middlename;
    private long telephoneNumber;

    /**
     * @return the сlientID
     */
    public int getСlientID() {
        return сlientID;
    }

    /**
     * @param сlientID the сlientID to set
     */
    public void setСlientID(int сlientID) {
        this.сlientID = сlientID;
    }

    /**
     * @return the passportSeries
     */
    public int getPassportSeries() {
        return passportSeries;
    }

    /**
     * @param passportSeries the passportSeries to set
     */
    public void setPassportSeries(int passportSeries) {
        this.passportSeries = passportSeries;
    }

    /**
     * @return the passportNumber
     */
    public int getPassportNumber() {
        return passportNumber;
    }

    /**
     * @param passportNumber the passportNumber to set
     */
    public void setPassportNumber(int passportNumber) {
        this.passportNumber = passportNumber;
    }

    /**
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @param firstname the firstname to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return the middlename
     */
    public String getMiddlename() {
        return middlename;
    }

    /**
     * @param middlename the middlename to set
     */
    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    /**
     * @return the telephoneNumber
     */
    public long getTelephoneNumber() {
        return telephoneNumber;
    }

    /**
     * @param telephoneNumber the telephoneNumber to set
     */
    public void setTelephoneNumber(long telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }
}
