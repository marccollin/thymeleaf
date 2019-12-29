package com.laboiteaprog.thymeleaf.model;

/**
 *
 * @author Marc Collin
 */
public class User {

    private String firstname;
    private String lastname;

    private String userTypeId;

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
     * @return the userTypeId
     */
    public String getUserTypeId() {
        return userTypeId;
    }

    /**
     * @param userTypeId the userTypeId to set
     */
    public void setUserTypeId(String userTypeId) {
        this.userTypeId = userTypeId;
    }

}
